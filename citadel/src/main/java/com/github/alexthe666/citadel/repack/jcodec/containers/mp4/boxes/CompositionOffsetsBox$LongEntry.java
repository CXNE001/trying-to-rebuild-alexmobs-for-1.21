/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

public static class CompositionOffsetsBox.LongEntry {
    public long count;
    public long offset;

    public CompositionOffsetsBox.LongEntry(long count, long offset) {
        this.count = count;
        this.offset = offset;
    }

    public long getCount() {
        return this.count;
    }

    public long getOffset() {
        return this.offset;
    }
}
