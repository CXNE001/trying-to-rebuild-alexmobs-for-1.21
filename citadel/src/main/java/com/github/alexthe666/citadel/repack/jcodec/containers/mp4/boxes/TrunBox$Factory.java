/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrunBox;

public static class TrunBox.Factory {
    private TrunBox box;

    protected TrunBox.Factory(TrunBox box) {
        this.box = box;
    }

    public TrunBox.Factory dataOffset(long dataOffset) {
        this.box.flags |= 1;
        this.box.dataOffset = (int)dataOffset;
        return this;
    }

    public TrunBox.Factory firstSampleFlags(int firstSampleFlags) {
        if (this.box.isSampleFlagsAvailable()) {
            throw new IllegalStateException("Sample flags already set on this object");
        }
        this.box.flags |= 4;
        this.box.firstSampleFlags = firstSampleFlags;
        return this;
    }

    public TrunBox.Factory sampleDuration(int[] sampleDuration) {
        if (sampleDuration.length != this.box.sampleCount) {
            throw new IllegalArgumentException("Argument array length not equal to sampleCount");
        }
        this.box.flags |= 0x100;
        TrunBox.access$302(this.box, sampleDuration);
        return this;
    }

    public TrunBox.Factory sampleSize(int[] sampleSize) {
        if (sampleSize.length != this.box.sampleCount) {
            throw new IllegalArgumentException("Argument array length not equal to sampleCount");
        }
        this.box.flags |= 0x200;
        TrunBox.access$402(this.box, sampleSize);
        return this;
    }

    public TrunBox.Factory sampleFlags(int[] sampleFlags) {
        if (sampleFlags.length != this.box.sampleCount) {
            throw new IllegalArgumentException("Argument array length not equal to sampleCount");
        }
        if (this.box.isFirstSampleFlagsAvailable()) {
            throw new IllegalStateException("First sample flags already set on this object");
        }
        this.box.flags |= 0x400;
        TrunBox.access$502(this.box, sampleFlags);
        return this;
    }

    public TrunBox.Factory sampleCompositionOffset(int[] sampleCompositionOffset) {
        if (sampleCompositionOffset.length != this.box.sampleCount) {
            throw new IllegalArgumentException("Argument array length not equal to sampleCount");
        }
        this.box.flags |= 0x800;
        TrunBox.access$602(this.box, sampleCompositionOffset);
        return this;
    }

    public TrunBox create() {
        try {
            TrunBox trunBox = this.box;
            return trunBox;
        }
        finally {
            this.box = null;
        }
    }
}
