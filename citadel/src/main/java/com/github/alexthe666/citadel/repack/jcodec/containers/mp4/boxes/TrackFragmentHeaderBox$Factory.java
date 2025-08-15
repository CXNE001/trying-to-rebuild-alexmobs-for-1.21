/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrackFragmentHeaderBox;

public static class TrackFragmentHeaderBox.Factory {
    private TrackFragmentHeaderBox box;

    public TrackFragmentHeaderBox.Factory(TrackFragmentHeaderBox box) {
        this.box = box;
    }

    public TrackFragmentHeaderBox.Factory baseDataOffset(long baseDataOffset) {
        this.box.flags |= 1;
        this.box.baseDataOffset = (int)baseDataOffset;
        return this;
    }

    public TrackFragmentHeaderBox.Factory sampleDescriptionIndex(long sampleDescriptionIndex) {
        this.box.flags |= 2;
        this.box.sampleDescriptionIndex = (int)sampleDescriptionIndex;
        return this;
    }

    public TrackFragmentHeaderBox.Factory defaultSampleDuration(long defaultSampleDuration) {
        this.box.flags |= 8;
        this.box.defaultSampleDuration = (int)defaultSampleDuration;
        return this;
    }

    public TrackFragmentHeaderBox.Factory defaultSampleSize(long defaultSampleSize) {
        this.box.flags |= 0x10;
        this.box.defaultSampleSize = (int)defaultSampleSize;
        return this;
    }

    public TrackFragmentHeaderBox.Factory defaultSampleFlags(long defaultSampleFlags) {
        this.box.flags |= 0x20;
        this.box.defaultSampleFlags = (int)defaultSampleFlags;
        return this;
    }

    public TrackFragmentHeaderBox create() {
        try {
            TrackFragmentHeaderBox trackFragmentHeaderBox = this.box;
            return trackFragmentHeaderBox;
        }
        finally {
            this.box = null;
        }
    }
}
