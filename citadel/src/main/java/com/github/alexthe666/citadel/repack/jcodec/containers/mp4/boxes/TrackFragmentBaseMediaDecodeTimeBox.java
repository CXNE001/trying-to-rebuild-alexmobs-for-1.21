/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FullBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class TrackFragmentBaseMediaDecodeTimeBox
extends FullBox {
    private long baseMediaDecodeTime;

    public TrackFragmentBaseMediaDecodeTimeBox(Header atom) {
        super(atom);
    }

    public static TrackFragmentBaseMediaDecodeTimeBox createTrackFragmentBaseMediaDecodeTimeBox(long baseMediaDecodeTime) {
        TrackFragmentBaseMediaDecodeTimeBox box = new TrackFragmentBaseMediaDecodeTimeBox(new Header(TrackFragmentBaseMediaDecodeTimeBox.fourcc()));
        box.baseMediaDecodeTime = baseMediaDecodeTime;
        if (box.baseMediaDecodeTime > Integer.MAX_VALUE) {
            box.version = 1;
        }
        return box;
    }

    public static String fourcc() {
        return "tfdt";
    }

    @Override
    public void parse(ByteBuffer input) {
        super.parse(input);
        if (this.version == 0) {
            this.baseMediaDecodeTime = input.getInt();
        } else if (this.version == 1) {
            this.baseMediaDecodeTime = input.getLong();
        } else {
            throw new RuntimeException("Unsupported tfdt version");
        }
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        super.doWrite(out);
        if (this.version == 0) {
            out.putInt((int)this.baseMediaDecodeTime);
        } else if (this.version == 1) {
            out.putLong(this.baseMediaDecodeTime);
        } else {
            throw new RuntimeException("Unsupported tfdt version");
        }
    }

    @Override
    public int estimateSize() {
        return 20;
    }

    public long getBaseMediaDecodeTime() {
        return this.baseMediaDecodeTime;
    }

    public void setBaseMediaDecodeTime(long baseMediaDecodeTime) {
        this.baseMediaDecodeTime = baseMediaDecodeTime;
    }

    public static Factory copy(TrackFragmentBaseMediaDecodeTimeBox other) {
        return new Factory(other);
    }

    public static class Factory {
        private TrackFragmentBaseMediaDecodeTimeBox box;

        protected Factory(TrackFragmentBaseMediaDecodeTimeBox other) {
            this.box = TrackFragmentBaseMediaDecodeTimeBox.createTrackFragmentBaseMediaDecodeTimeBox(other.baseMediaDecodeTime);
            this.box.version = other.version;
            this.box.flags = other.flags;
        }

        public Factory baseMediaDecodeTime(long val) {
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
}
