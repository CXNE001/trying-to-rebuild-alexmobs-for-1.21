/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FullBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class CompositionOffsetsBox
extends FullBox {
    private Entry[] entries;

    public CompositionOffsetsBox(Header header) {
        super(header);
    }

    public static String fourcc() {
        return "ctts";
    }

    public static CompositionOffsetsBox createCompositionOffsetsBox(Entry[] entries) {
        CompositionOffsetsBox ctts = new CompositionOffsetsBox(new Header(CompositionOffsetsBox.fourcc()));
        ctts.entries = entries;
        return ctts;
    }

    @Override
    public void parse(ByteBuffer input) {
        super.parse(input);
        int num = input.getInt();
        this.entries = new Entry[num];
        for (int i = 0; i < num; ++i) {
            this.entries[i] = new Entry(input.getInt(), input.getInt());
        }
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        super.doWrite(out);
        out.putInt(this.entries.length);
        for (int i = 0; i < this.entries.length; ++i) {
            out.putInt(this.entries[i].count);
            out.putInt(this.entries[i].offset);
        }
    }

    @Override
    public int estimateSize() {
        return 16 + this.entries.length * 8;
    }

    public Entry[] getEntries() {
        return this.entries;
    }

    public static class LongEntry {
        public long count;
        public long offset;

        public LongEntry(long count, long offset) {
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

    public static class Entry {
        public int count;
        public int offset;

        public Entry(int count, int offset) {
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
}
