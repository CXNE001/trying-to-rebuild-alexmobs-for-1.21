/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.boxes;

public final class Utils {
    private static final long UNDETERMINED = 0xFFFFFFFFL;

    public static String getLanguageCode(long l) {
        char[] c = new char[]{(char)((l >> 10 & 0x1FL) + 96L), (char)((l >> 5 & 0x1FL) + 96L), (char)((l & 0x1FL) + 96L)};
        return new String(c);
    }

    public static long detectUndetermined(long l) {
        long x = l == 0xFFFFFFFFL ? -1L : l;
        return x;
    }
}
