/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FullBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class SampleToChunkBox
extends FullBox {
    private SampleToChunkEntry[] sampleToChunk;

    public SampleToChunkBox(Header atom) {
        super(atom);
    }

    public static String fourcc() {
        return "stsc";
    }

    public static SampleToChunkBox createSampleToChunkBox(SampleToChunkEntry[] sampleToChunk) {
        SampleToChunkBox box = new SampleToChunkBox(new Header(SampleToChunkBox.fourcc()));
        box.sampleToChunk = sampleToChunk;
        return box;
    }

    @Override
    public void parse(ByteBuffer input) {
        super.parse(input);
        int size = input.getInt();
        this.sampleToChunk = new SampleToChunkEntry[size];
        for (int i = 0; i < size; ++i) {
            this.sampleToChunk[i] = new SampleToChunkEntry(input.getInt(), input.getInt(), input.getInt());
        }
    }

    public SampleToChunkEntry[] getSampleToChunk() {
        return this.sampleToChunk;
    }

    @Override
    public void doWrite(ByteBuffer out) {
        super.doWrite(out);
        out.putInt(this.sampleToChunk.length);
        for (int i = 0; i < this.sampleToChunk.length; ++i) {
            SampleToChunkEntry stc = this.sampleToChunk[i];
            out.putInt((int)stc.getFirst());
            out.putInt(stc.getCount());
            out.putInt(stc.getEntry());
        }
    }

    @Override
    public int estimateSize() {
        return 16 + this.sampleToChunk.length * 12;
    }

    public void setSampleToChunk(SampleToChunkEntry[] sampleToChunk) {
        this.sampleToChunk = sampleToChunk;
    }

    public static class SampleToChunkEntry {
        private long first;
        private int count;
        private int entry;

        public SampleToChunkEntry(long first, int count, int entry) {
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
}
