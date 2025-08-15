/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

public static class TimeToSampleBox.TimeToSampleEntry {
    int sampleCount;
    int sampleDuration;

    public TimeToSampleBox.TimeToSampleEntry(int sampleCount, int sampleDuration) {
        this.sampleCount = sampleCount;
        this.sampleDuration = sampleDuration;
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public int getSampleDuration() {
        return this.sampleDuration;
    }

    public void setSampleDuration(int sampleDuration) {
        this.sampleDuration = sampleDuration;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public long getSegmentDuration() {
        return this.sampleCount * this.sampleDuration;
    }
}
