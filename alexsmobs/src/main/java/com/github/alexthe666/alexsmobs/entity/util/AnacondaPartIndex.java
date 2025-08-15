/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.alexsmobs.entity.util;

public enum AnacondaPartIndex {
    HEAD(0.0f),
    NECK(0.5f),
    BODY(0.5f),
    TAIL(0.4f);

    private final float backOffset;

    private AnacondaPartIndex(float partOffset) {
        this.backOffset = partOffset;
    }

    public static AnacondaPartIndex fromOrdinal(int i) {
        return switch (i) {
            case 0 -> HEAD;
            case 1 -> NECK;
            case 3 -> TAIL;
            default -> BODY;
        };
    }

    public static AnacondaPartIndex sizeAt(int bodyindex) {
        return switch (bodyindex) {
            case 0 -> HEAD;
            case 1, 5, 6 -> NECK;
            case 7 -> TAIL;
            default -> BODY;
        };
    }

    public float getBackOffset() {
        return this.backOffset;
    }
}
