/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FullBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class TrackFragmentHeaderBox
extends FullBox {
    public static final int FLAG_BASE_DATA_OFFSET = 1;
    public static final int FLAG_SAMPLE_DESCRIPTION_INDEX = 2;
    public static final int FLAG_DEFAILT_SAMPLE_DURATION = 8;
    public static final int FLAG_DEFAULT_SAMPLE_SIZE = 16;
    public static final int FLAG_DEFAILT_SAMPLE_FLAGS = 32;
    private int trackId;
    private long baseDataOffset;
    private int sampleDescriptionIndex;
    private int defaultSampleDuration;
    private int defaultSampleSize;
    private int defaultSampleFlags;

    public TrackFragmentHeaderBox(Header atom) {
        super(atom);
    }

    public static String fourcc() {
        return "tfhd";
    }

    public static TrackFragmentHeaderBox tfhd(int trackId, long baseDataOffset, int sampleDescriptionIndex, int defaultSampleDuration, int defaultSampleSize, int defaultSampleFlags) {
        TrackFragmentHeaderBox box = new TrackFragmentHeaderBox(new Header(TrackFragmentHeaderBox.fourcc()));
        box.trackId = trackId;
        box.baseDataOffset = baseDataOffset;
        box.sampleDescriptionIndex = sampleDescriptionIndex;
        box.defaultSampleDuration = defaultSampleDuration;
        box.defaultSampleSize = defaultSampleSize;
        box.defaultSampleFlags = defaultSampleFlags;
        return box;
    }

    public static Factory create(int trackId) {
        return new Factory(TrackFragmentHeaderBox.createTrackFragmentHeaderBoxWithId(trackId));
    }

    public static Factory copy(TrackFragmentHeaderBox other) {
        TrackFragmentHeaderBox box = TrackFragmentHeaderBox.tfhd(other.trackId, other.baseDataOffset, other.sampleDescriptionIndex, other.defaultSampleDuration, other.defaultSampleSize, other.defaultSampleFlags);
        box.setFlags(other.getFlags());
        box.setVersion(other.getVersion());
        return new Factory(box);
    }

    public static TrackFragmentHeaderBox createTrackFragmentHeaderBoxWithId(int trackId) {
        TrackFragmentHeaderBox box = new TrackFragmentHeaderBox(new Header(TrackFragmentHeaderBox.fourcc()));
        box.trackId = trackId;
        return box;
    }

    @Override
    public void parse(ByteBuffer input) {
        super.parse(input);
        this.trackId = input.getInt();
        if (this.isBaseDataOffsetAvailable()) {
            this.baseDataOffset = input.getLong();
        }
        if (this.isSampleDescriptionIndexAvailable()) {
            this.sampleDescriptionIndex = input.getInt();
        }
        if (this.isDefaultSampleDurationAvailable()) {
            this.defaultSampleDuration = input.getInt();
        }
        if (this.isDefaultSampleSizeAvailable()) {
            this.defaultSampleSize = input.getInt();
        }
        if (this.isDefaultSampleFlagsAvailable()) {
            this.defaultSampleFlags = input.getInt();
        }
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        super.doWrite(out);
        out.putInt(this.trackId);
        if (this.isBaseDataOffsetAvailable()) {
            out.putLong(this.baseDataOffset);
        }
        if (this.isSampleDescriptionIndexAvailable()) {
            out.putInt(this.sampleDescriptionIndex);
        }
        if (this.isDefaultSampleDurationAvailable()) {
            out.putInt(this.defaultSampleDuration);
        }
        if (this.isDefaultSampleSizeAvailable()) {
            out.putInt(this.defaultSampleSize);
        }
        if (this.isDefaultSampleFlagsAvailable()) {
            out.putInt(this.defaultSampleFlags);
        }
    }

    @Override
    public int estimateSize() {
        return 40;
    }

    public int getTrackId() {
        return this.trackId;
    }

    public long getBaseDataOffset() {
        return this.baseDataOffset;
    }

    public int getSampleDescriptionIndex() {
        return this.sampleDescriptionIndex;
    }

    public int getDefaultSampleDuration() {
        return this.defaultSampleDuration;
    }

    public int getDefaultSampleSize() {
        return this.defaultSampleSize;
    }

    public int getDefaultSampleFlags() {
        return this.defaultSampleFlags;
    }

    public boolean isBaseDataOffsetAvailable() {
        return (this.flags & 1) != 0;
    }

    public boolean isSampleDescriptionIndexAvailable() {
        return (this.flags & 2) != 0;
    }

    public boolean isDefaultSampleDurationAvailable() {
        return (this.flags & 8) != 0;
    }

    public boolean isDefaultSampleSizeAvailable() {
        return (this.flags & 0x10) != 0;
    }

    public boolean isDefaultSampleFlagsAvailable() {
        return (this.flags & 0x20) != 0;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public void setDefaultSampleFlags(int defaultSampleFlags) {
        this.defaultSampleFlags = defaultSampleFlags;
    }

    public static TrackFragmentHeaderBox createTrackFragmentHeaderBox() {
        return new TrackFragmentHeaderBox(new Header(TrackFragmentHeaderBox.fourcc()));
    }

    public static class Factory {
        private TrackFragmentHeaderBox box;

        public Factory(TrackFragmentHeaderBox box) {
            this.box = box;
        }

        public Factory baseDataOffset(long baseDataOffset) {
            this.box.flags |= 1;
            this.box.baseDataOffset = (int)baseDataOffset;
            return this;
        }

        public Factory sampleDescriptionIndex(long sampleDescriptionIndex) {
            this.box.flags |= 2;
            this.box.sampleDescriptionIndex = (int)sampleDescriptionIndex;
            return this;
        }

        public Factory defaultSampleDuration(long defaultSampleDuration) {
            this.box.flags |= 8;
            this.box.defaultSampleDuration = (int)defaultSampleDuration;
            return this;
        }

        public Factory defaultSampleSize(long defaultSampleSize) {
            this.box.flags |= 0x10;
            this.box.defaultSampleSize = (int)defaultSampleSize;
            return this;
        }

        public Factory defaultSampleFlags(long defaultSampleFlags) {
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
}
