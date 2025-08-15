/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import java.io.IOException;
import java.io.InputStream;

public abstract class ReaderLE {
    public static short readShort(InputStream input) throws IOException {
        int b2 = input.read();
        int b1 = input.read();
        if (b1 == -1 || b2 == -1) {
            return -1;
        }
        return (short)((b1 << 8) + b2);
    }

    public static int readInt(InputStream input) throws IOException {
        long b4 = input.read();
        long b3 = input.read();
        long b2 = input.read();
        long b1 = input.read();
        if (b1 == -1L || b2 == -1L || b3 == -1L || b4 == -1L) {
            return -1;
        }
        return (int)((b1 << 24) + (b2 << 16) + (b3 << 8) + b4);
    }

    public static long readLong(InputStream input) throws IOException {
        long b8 = input.read();
        long b7 = input.read();
        long b6 = input.read();
        long b5 = input.read();
        long b4 = input.read();
        long b3 = input.read();
        long b2 = input.read();
        long b1 = input.read();
        if (b1 == -1L || b2 == -1L || b3 == -1L || b4 == -1L || b5 == -1L || b6 == -1L || b7 == -1L || b8 == -1L) {
            return -1L;
        }
        return (int)((b1 << 56) + (b2 << 48) + (b3 << 40) + (b4 << 32) + (b5 << 24) + (b6 << 16) + (b7 << 8) + b8);
    }
}
