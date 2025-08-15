/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model;

public class AspectRatio {
    public static final AspectRatio Extended_SAR = new AspectRatio(255);
    private int value;

    private AspectRatio(int value) {
        this.value = value;
    }

    public static AspectRatio fromValue(int value) {
        if (value == AspectRatio.Extended_SAR.value) {
            return Extended_SAR;
        }
        return new AspectRatio(value);
    }

    public int getValue() {
        return this.value;
    }
}
