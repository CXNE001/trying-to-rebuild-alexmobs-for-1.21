/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;

public class Fourcc {
    public static final int ftyp = Fourcc.intFourcc("ftyp");
    public static final int free = Fourcc.intFourcc("free");
    public static final int moov = Fourcc.intFourcc("moov");
    public static final int mdat = Fourcc.intFourcc("mdat");
    public static final int wide = Fourcc.intFourcc("wide");

    public static int makeInt(byte b3, byte b2, byte b1, byte b0) {
        return b3 << 24 | (b2 & 0xFF) << 16 | (b1 & 0xFF) << 8 | b0 & 0xFF;
    }

    public static int intFourcc(String string) {
        byte[] b = Platform.getBytes(string);
        return Fourcc.makeInt(b[0], b[1], b[2], b[3]);
    }
}
