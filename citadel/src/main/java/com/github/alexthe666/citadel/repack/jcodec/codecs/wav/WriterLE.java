/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import java.io.IOException;
import java.io.OutputStream;

public abstract class WriterLE {
    public static void writeShort(OutputStream out, short s) throws IOException {
        out.write(s & 0xFF);
        out.write(s >> 8 & 0xFF);
    }

    public static void writeInt(OutputStream out, int i) throws IOException {
        out.write(i & 0xFF);
        out.write(i >> 8 & 0xFF);
        out.write(i >> 16 & 0xFF);
        out.write(i >> 24 & 0xFF);
    }

    public static void writeLong(OutputStream out, long l) throws IOException {
        out.write((int)(l & 0xFFL));
        out.write((int)(l >> 8 & 0xFFL));
        out.write((int)(l >> 16 & 0xFFL));
        out.write((int)(l >> 24 & 0xFFL));
        out.write((int)(l >> 32 & 0xFFL));
        out.write((int)(l >> 40 & 0xFFL));
        out.write((int)(l >> 48 & 0xFFL));
        out.write((int)(l >> 56 & 0xFFL));
    }
}
