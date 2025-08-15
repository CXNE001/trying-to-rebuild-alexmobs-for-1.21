/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

public static class CompositionOffsetsBox.Entry {
    public int count;
    public int offset;

    public CompositionOffsetsBox.Entry(int count, int offset) {
        this.count = count;
        this.offset = offset;
    }

    public int getCount() {
        return this.count;
    }

    public int getOffset() {
        return this.offset;
    }
}
