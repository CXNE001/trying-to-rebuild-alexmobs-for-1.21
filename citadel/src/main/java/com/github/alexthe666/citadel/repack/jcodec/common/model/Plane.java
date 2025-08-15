/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.model;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;

public class Plane {
    int[] data;
    Size size;

    public Plane(int[] data, Size size) {
        this.data = data;
        this.size = size;
    }

    public int[] getData() {
        return this.data;
    }

    public Size getSize() {
        return this.size;
    }
}
