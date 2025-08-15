/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.Boxes;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.IBoxFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.DataBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SimpleBoxFactory;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IListBox
extends Box {
    private static final String FOURCC = "ilst";
    private Map<Integer, List<Box>> values;
    private IBoxFactory factory = new SimpleBoxFactory(new LocalBoxes());

    public IListBox(Header atom) {
        super(atom);
        this.values = new LinkedHashMap<Integer, List<Box>>();
    }

    public static IListBox createIListBox(Map<Integer, List<Box>> values) {
        IListBox box = new IListBox(Header.createHeader(FOURCC, 0L));
        box.values = values;
        return box;
    }

    @Override
    public void parse(ByteBuffer input) {
        while (input.remaining() >= 4) {
            int size = input.getInt();
            ByteBuffer local = NIOUtils.read(input, size - 4);
            int index = local.getInt();
            ArrayList<Box> children = new ArrayList<Box>();
            this.values.put(index, children);
            while (local.hasRemaining()) {
                Header childAtom = Header.read(local);
                if (childAtom == null || (long)local.remaining() < childAtom.getBodySize()) continue;
                Box box = Box.parseBox(NIOUtils.read(local, (int)childAtom.getBodySize()), childAtom, this.factory);
                children.add(box);
            }
        }
    }

    public Map<Integer, List<Box>> getValues() {
        return this.values;
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        for (Map.Entry<Integer, List<Box>> entry : this.values.entrySet()) {
            ByteBuffer fork = out.duplicate();
            out.putInt(0);
            out.putInt(entry.getKey());
            for (Box box : entry.getValue()) {
                box.write(out);
            }
            fork.putInt(out.position() - fork.position());
        }
    }

    @Override
    public int estimateSize() {
        int sz = 8;
        for (Map.Entry<Integer, List<Box>> entry : this.values.entrySet()) {
            for (Box box : entry.getValue()) {
                sz += 8 + box.estimateSize();
            }
        }
        return sz;
    }

    public static String fourcc() {
        return FOURCC;
    }

    private static class LocalBoxes
    extends Boxes {
        LocalBoxes() {
            this.mappings.put(DataBox.fourcc(), DataBox.class);
        }
    }
}
