/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

public static class SampleToChunkBox.SampleToChunkEntry {
    private long first;
    private int count;
    private int entry;

    public SampleToChunkBox.SampleToChunkEntry(long first, int count, int entry) {
        this.first = first;
        this.count = count;
        this.entry = entry;
    }

    public long getFirst() {
        return this.first;
    }

    public void setFirst(long first) {
        this.first = first;
    }

    public int getCount() {
        return this.count;
    }

    public int getEntry() {
        return this.entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
