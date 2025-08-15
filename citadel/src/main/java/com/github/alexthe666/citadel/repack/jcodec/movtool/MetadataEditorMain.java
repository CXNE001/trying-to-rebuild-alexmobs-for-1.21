/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.common.io.IOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MetaValue;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MetadataEditor;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class MetadataEditorMain {
    private static final String TYPENAME_FLOAT = "float";
    private static final String TYPENAME_INT2 = "integer";
    private static final String TYPENAME_INT = "int";
    private static final MainUtils.Flag FLAG_SET_KEYED = MainUtils.Flag.flag("set-keyed", "sk", "key1[,type1]=value1:key2[,type2]=value2[,...] Sets the metadata piece into a file.");
    private static final MainUtils.Flag FLAG_SET_ITUNES = MainUtils.Flag.flag("set-itunes", "si", "key1[,type1]=value1:key2[,type2]=value2[,...] Sets the metadata piece into a file.");
    private static final MainUtils.Flag FLAG_SET_ITUNES_BLOB = MainUtils.Flag.flag("set-itunes-blob", "sib", "key[,type]=file Sets the data read from a file into the metadata field 'key'. If file is not present stdin is read.");
    private static final MainUtils.Flag FLAG_QUERY = MainUtils.Flag.flag("query", "q", "Query the value of one key from the metadata set.");
    private static final MainUtils.Flag FLAG_FAST = new MainUtils.Flag("fast", "f", "Fast edit, will move the header to the end of the file when ther's no room to fit it.", MainUtils.FlagType.VOID);
    private static final MainUtils.Flag FLAG_DROP_KEYED = MainUtils.Flag.flag("drop-keyed", "dk", "Drop the field(s) from keyed metadata, format: key1,key2,key3,...");
    private static final MainUtils.Flag FLAG_DROP_ITUNES = MainUtils.Flag.flag("drop-itunes", "di", "Drop the field(s) from iTunes metadata, format: key1,key2,key3,...");
    private static final MainUtils.Flag[] flags = new MainUtils.Flag[]{FLAG_SET_KEYED, FLAG_SET_ITUNES, FLAG_QUERY, FLAG_FAST, FLAG_SET_ITUNES_BLOB, FLAG_DROP_KEYED, FLAG_DROP_ITUNES};
    private static Map<String, Integer> strToType = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        Map<Integer, MetaValue> itunesMeta;
        Map<String, MetaValue> keyedMeta;
        String flagSetItunesBlob;
        String flagSetItunes;
        String flagDropItunes;
        String flagDropKeyed;
        MainUtils.Cmd cmd = MainUtils.parseArguments(args, flags);
        if (cmd.argsLength() < 1) {
            MainUtils.printHelpCmdVa("metaedit", flags, "file name");
            System.exit(-1);
            return;
        }
        MetadataEditor mediaMeta = MetadataEditor.createFrom(new File(cmd.getArg(0)));
        boolean save = false;
        String flagSetKeyed = cmd.getStringFlag(FLAG_SET_KEYED);
        if (flagSetKeyed != null) {
            Map<String, MetaValue> map = MetadataEditorMain.parseMetaSpec(flagSetKeyed);
            save |= map.size() > 0;
            mediaMeta.getKeyedMeta().putAll(map);
        }
        if ((flagDropKeyed = cmd.getStringFlag(FLAG_DROP_KEYED)) != null) {
            String[] keys = flagDropKeyed.split(",");
            Map<String, MetaValue> keyedMeta2 = mediaMeta.getKeyedMeta();
            for (String key : keys) {
                save |= keyedMeta2.remove(key) != null;
            }
        }
        if ((flagDropItunes = cmd.getStringFlag(FLAG_DROP_ITUNES)) != null) {
            String[] keys = flagDropItunes.split(",");
            Map<Integer, MetaValue> itunesMeta2 = mediaMeta.getItunesMeta();
            for (String key : keys) {
                int fourcc = MetadataEditorMain.stringToFourcc(key);
                save |= itunesMeta2.remove(fourcc) != null;
            }
        }
        if ((flagSetItunes = cmd.getStringFlag(FLAG_SET_ITUNES)) != null) {
            Map<Integer, MetaValue> map = MetadataEditorMain.toFourccMeta(MetadataEditorMain.parseMetaSpec(flagSetItunes));
            save |= map.size() > 0;
            mediaMeta.getItunesMeta().putAll(map);
        }
        if ((flagSetItunesBlob = cmd.getStringFlag(FLAG_SET_ITUNES_BLOB)) != null) {
            String[] lr = flagSetItunesBlob.split("=");
            String[] kt = lr[0].split(",");
            String key = kt[0];
            Integer type = 1;
            if (kt.length > 1) {
                type = strToType.get(kt[1]);
            }
            if (type != null) {
                byte[] data = MetadataEditorMain.readStdin(lr.length > 1 ? lr[1] : null);
                mediaMeta.getItunesMeta().put(MetadataEditorMain.stringToFourcc(key), MetaValue.createOther(type, data));
                save = true;
            } else {
                System.err.println("Unsupported metadata type: " + kt[1]);
            }
        }
        if (save) {
            mediaMeta.save(cmd.getBooleanFlag(FLAG_FAST));
            mediaMeta = MetadataEditor.createFrom(new File(cmd.getArg(0)));
        }
        if ((keyedMeta = mediaMeta.getKeyedMeta()) != null) {
            String flagQuery = cmd.getStringFlag(FLAG_QUERY);
            if (flagQuery == null) {
                System.out.println("Keyed metadata:");
                for (Map.Entry<String, MetaValue> entry : keyedMeta.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } else {
                MetadataEditorMain.printValue(keyedMeta.get(flagQuery));
            }
        }
        if ((itunesMeta = mediaMeta.getItunesMeta()) != null) {
            String flagQuery = cmd.getStringFlag(FLAG_QUERY);
            if (flagQuery == null) {
                System.out.println("iTunes metadata:");
                for (Map.Entry<Integer, MetaValue> entry : itunesMeta.entrySet()) {
                    System.out.println(MetadataEditorMain.fourccToString(entry.getKey()) + ": " + entry.getValue());
                }
            } else {
                MetadataEditorMain.printValue(itunesMeta.get(MetadataEditorMain.stringToFourcc(flagQuery)));
            }
        }
    }

    private static byte[] readStdin(String fileName) throws IOException {
        FileInputStream fis;
        block4: {
            fis = null;
            if (fileName == null) break block4;
            fis = new FileInputStream(new File(fileName));
            byte[] byArray = IOUtils.toByteArray(fis);
            IOUtils.closeQuietly(fis);
            return byArray;
        }
        try {
            byte[] byArray = IOUtils.toByteArray(Platform.stdin());
            return byArray;
        }
        finally {
            IOUtils.closeQuietly(fis);
        }
    }

    private static void printValue(MetaValue value) throws IOException {
        if (value == null) {
            return;
        }
        if (value.isBlob()) {
            System.out.write(value.getData());
        } else {
            System.out.println(value);
        }
    }

    private static Map<Integer, MetaValue> toFourccMeta(Map<String, MetaValue> keyed) {
        HashMap<Integer, MetaValue> ret = new HashMap<Integer, MetaValue>();
        for (Map.Entry<String, MetaValue> entry : keyed.entrySet()) {
            ret.put(MetadataEditorMain.stringToFourcc(entry.getKey()), entry.getValue());
        }
        return ret;
    }

    private static Map<String, MetaValue> parseMetaSpec(String flagSetKeyed) {
        HashMap<String, MetaValue> map = new HashMap<String, MetaValue>();
        for (String value : flagSetKeyed.split(":")) {
            String[] lr = value.split("=");
            String[] kt = lr[0].split(",");
            map.put(kt[0], MetadataEditorMain.typedValue(lr.length > 1 ? lr[1] : null, kt.length > 1 ? kt[1] : null));
        }
        return map;
    }

    private static String fourccToString(int key) {
        byte[] bytes = new byte[4];
        ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).putInt(key);
        return Platform.stringFromCharset(bytes, "iso8859-1");
    }

    private static int stringToFourcc(String fourcc) {
        if (fourcc.length() != 4) {
            return 0;
        }
        byte[] bytes = Platform.getBytesForCharset(fourcc, "iso8859-1");
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getInt();
    }

    private static MetaValue typedValue(String value, String type) {
        if (TYPENAME_INT.equalsIgnoreCase(type) || TYPENAME_INT2.equalsIgnoreCase(type)) {
            return MetaValue.createInt(Integer.parseInt(value));
        }
        if (TYPENAME_FLOAT.equalsIgnoreCase(type)) {
            return MetaValue.createFloat(Float.parseFloat(value));
        }
        return MetaValue.createString(value);
    }

    static {
        strToType.put("utf8", 1);
        strToType.put("utf16", 2);
        strToType.put(TYPENAME_FLOAT, 23);
        strToType.put(TYPENAME_INT, 21);
        strToType.put(TYPENAME_INT2, 21);
        strToType.put("jpeg", 13);
        strToType.put("jpg", 13);
        strToType.put("png", 14);
        strToType.put("bmp", 27);
    }
}
