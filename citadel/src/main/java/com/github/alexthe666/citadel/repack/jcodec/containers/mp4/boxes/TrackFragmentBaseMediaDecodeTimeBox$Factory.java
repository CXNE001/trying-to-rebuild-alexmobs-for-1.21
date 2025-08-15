/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrackFragmentBaseMediaDecodeTimeBox;

public static class TrackFragmentBaseMediaDecodeTimeBox.Factory {
    private TrackFragmentBaseMediaDecodeTimeBox box;

    protected TrackFragmentBaseMediaDecodeTimeBox.Factory(TrackFragmentBaseMediaDecodeTimeBox other) {
        this.box = TrackFragmentBaseMediaDecodeTimeBox.createTrackFragmentBaseMediaDecodeTimeBox(other.baseMediaDecodeTime);
        this.box.version = other.version;
        this.box.flags = other.flags;
    }

    public TrackFragmentBaseMediaDecodeTimeBox.Factory baseMediaDecodeTime(long val) {
        this.box.baseMediaDecodeTime = val;
        return this;
    }

    public TrackFragmentBaseMediaDecodeTimeBox create() {
        try {
            TrackFragmentBaseMediaDecodeTimeBox trackFragmentBaseMediaDecodeTimeBox = this.box;
            return trackFragmentBaseMediaDecodeTimeBox;
        }
        finally {
            this.box = null;
        }
    }
}
