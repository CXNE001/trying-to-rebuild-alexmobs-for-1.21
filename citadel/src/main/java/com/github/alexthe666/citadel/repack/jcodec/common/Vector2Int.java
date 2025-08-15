/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

public class Vector2Int {
    public static int el16_0(int packed) {
        return packed << 16 >> 16;
    }

    public static int el16_1(int packed) {
        return packed >> 16;
    }

    public static int el16(int packed, int n) {
        switch (n) {
            case 0: {
                return Vector2Int.el16_0(packed);
            }
        }
        return Vector2Int.el16_1(packed);
    }

    public static int set16_0(int packed, int el) {
        return packed & 0xFFFF0000 | el & 0xFFFF;
    }

    public static int set16_1(int packed, int el) {
        return packed & 0xFFFF | (el & 0xFFFF) << 16;
    }

    public static int set16(int packed, int el, int n) {
        switch (n) {
            case 0: {
                return Vector2Int.set16_0(packed, el);
            }
        }
        return Vector2Int.set16_1(packed, el);
    }

    public static int pack16(int el0, int el1) {
        return (el1 & 0xFFFF) << 16 | el0 & 0xFFFF;
    }
}
