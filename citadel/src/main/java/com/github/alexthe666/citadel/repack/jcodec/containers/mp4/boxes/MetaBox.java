/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.DataBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.IListBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.KeysBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MdtaBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MetaValue;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MetaBox
extends NodeBox {
    private static final String FOURCC = "meta";

    public MetaBox(Header atom) {
        super(atom);
    }

    public static MetaBox createMetaBox() {
        return new MetaBox(Header.createHeader(MetaBox.fourcc(), 0L));
    }

    public Map<String, MetaValue> getKeyedMeta() {
        LinkedHashMap<String, MetaValue> result = new LinkedHashMap<String, MetaValue>();
        IListBox ilst = NodeBox.findFirst(this, IListBox.class, IListBox.fourcc());
        MdtaBox[] keys = (MdtaBox[])NodeBox.findAllPath((Box)this, MdtaBox.class, (String[])new String[]{KeysBox.fourcc(), MdtaBox.fourcc()});
        if (ilst == null || keys.length == 0) {
            return result;
        }
        for (Map.Entry<Integer, List<Box>> entry : ilst.getValues().entrySet()) {
            DataBox db;
            Integer index = entry.getKey();
            if (index == null || (db = this.getDataBox(entry.getValue())) == null) continue;
            MetaValue value = MetaValue.createOtherWithLocale(db.getType(), db.getLocale(), db.getData());
            if (index <= 0 || index > keys.length) continue;
            result.put(keys[index - 1].getKey(), value);
        }
        return result;
    }

    private DataBox getDataBox(List<Box> value) {
        for (Box box : value) {
            if (!(box instanceof DataBox)) continue;
            return (DataBox)box;
        }
        return null;
    }

    public Map<Integer, MetaValue> getItunesMeta() {
        LinkedHashMap<Integer, MetaValue> result = new LinkedHashMap<Integer, MetaValue>();
        IListBox ilst = NodeBox.findFirst(this, IListBox.class, IListBox.fourcc());
        if (ilst == null) {
            return result;
        }
        for (Map.Entry<Integer, List<Box>> entry : ilst.getValues().entrySet()) {
            DataBox db;
            Integer index = entry.getKey();
            if (index == null || (db = this.getDataBox(entry.getValue())) == null) continue;
            MetaValue value = MetaValue.createOtherWithLocale(db.getType(), db.getLocale(), db.getData());
            result.put(index, value);
        }
        return result;
    }

    public void setKeyedMeta(Map<String, MetaValue> map) {
        if (map.isEmpty()) {
            return;
        }
        KeysBox keys = KeysBox.createKeysBox();
        LinkedHashMap<Integer, List<Box>> data = new LinkedHashMap<Integer, List<Box>>();
        int i = 1;
        for (Map.Entry<String, MetaValue> entry : map.entrySet()) {
            keys.add(MdtaBox.createMdtaBox(entry.getKey()));
            MetaValue v = entry.getValue();
            ArrayList<DataBox> children = new ArrayList<DataBox>();
            children.add(DataBox.createDataBox(v.getType(), v.getLocale(), v.getData()));
            data.put(i, children);
            ++i;
        }
        IListBox ilst = IListBox.createIListBox(data);
        this.replaceBox(keys);
        this.replaceBox(ilst);
    }

    public void setItunesMeta(Map<Integer, MetaValue> map) {
        DataBox dataBox;
        MetaValue v;
        int index;
        Map<Integer, List<Box>> data;
        if (map.isEmpty()) {
            return;
        }
        LinkedHashMap<Integer, MetaValue> copy = new LinkedHashMap<Integer, MetaValue>();
        copy.putAll(map);
        IListBox ilst = NodeBox.findFirst(this, IListBox.class, IListBox.fourcc());
        if (ilst == null) {
            data = new LinkedHashMap<Integer, List<Box>>();
        } else {
            data = ilst.getValues();
            for (Map.Entry<Integer, List<Box>> entry : data.entrySet()) {
                index = entry.getKey();
                v = (MetaValue)copy.get(index);
                if (v == null) continue;
                dataBox = DataBox.createDataBox(v.getType(), v.getLocale(), v.getData());
                this.dropChildBox(entry.getValue(), DataBox.fourcc());
                entry.getValue().add(dataBox);
                copy.remove(index);
            }
        }
        for (Map.Entry<Integer, List<Box>> entry : copy.entrySet()) {
            index = entry.getKey();
            v = (MetaValue)((Object)entry.getValue());
            dataBox = DataBox.createDataBox(v.getType(), v.getLocale(), v.getData());
            ArrayList<DataBox> children = new ArrayList<DataBox>();
            data.put(index, children);
            children.add(dataBox);
        }
        HashSet<Integer> keySet = new HashSet<Integer>(data.keySet());
        keySet.removeAll(map.keySet());
        for (Integer dropped : keySet) {
            data.remove(dropped);
        }
        this.replaceBox(IListBox.createIListBox(data));
    }

    private void dropChildBox(List<Box> children, String fourcc2) {
        ListIterator<Box> listIterator = children.listIterator();
        while (listIterator.hasNext()) {
            Box next = listIterator.next();
            if (!fourcc2.equals(next.getFourcc())) continue;
            listIterator.remove();
        }
    }

    public static String fourcc() {
        return FOURCC;
    }
}
