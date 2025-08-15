/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.demuxer;

import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.SeekableDemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.model.RationalLarge;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.MP4Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.MP4TrackType;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.ChunkOffsets64Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.ChunkOffsetsBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Edit;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.EditListBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NameBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SampleEntry;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SampleToChunkBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TimeToSampleBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.demuxer.MP4DemuxerTrackMeta;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public abstract class AbstractMP4DemuxerTrack
implements SeekableDemuxerTrack {
    protected TrakBox box;
    private MP4TrackType type;
    private int no;
    protected SampleEntry[] sampleEntries;
    protected TimeToSampleBox.TimeToSampleEntry[] timeToSamples;
    protected SampleToChunkBox.SampleToChunkEntry[] sampleToChunks;
    protected long[] chunkOffsets;
    protected long duration;
    protected int sttsInd;
    protected int sttsSubInd;
    protected int stcoInd;
    protected int stscInd;
    protected long pts;
    protected long curFrame;
    protected int timescale;

    public AbstractMP4DemuxerTrack(TrakBox trak) {
        this.no = trak.getTrackHeader().getNo();
        this.type = TrakBox.getTrackType(trak);
        this.sampleEntries = (SampleEntry[])NodeBox.findAllPath((Box)trak, SampleEntry.class, (String[])new String[]{"mdia", "minf", "stbl", "stsd", null});
        NodeBox stbl = trak.getMdia().getMinf().getStbl();
        TimeToSampleBox stts = NodeBox.findFirst(stbl, TimeToSampleBox.class, "stts");
        SampleToChunkBox stsc = NodeBox.findFirst(stbl, SampleToChunkBox.class, "stsc");
        ChunkOffsetsBox stco = NodeBox.findFirst(stbl, ChunkOffsetsBox.class, "stco");
        ChunkOffsets64Box co64 = NodeBox.findFirst(stbl, ChunkOffsets64Box.class, "co64");
        this.timeToSamples = stts.getEntries();
        this.sampleToChunks = stsc.getSampleToChunk();
        this.chunkOffsets = stco != null ? stco.getChunkOffsets() : co64.getChunkOffsets();
        for (int i = 0; i < this.timeToSamples.length; ++i) {
            TimeToSampleBox.TimeToSampleEntry ttse = this.timeToSamples[i];
            this.duration += (long)(ttse.getSampleCount() * ttse.getSampleDuration());
        }
        this.box = trak;
        this.timescale = trak.getTimescale();
    }

    public int pts2Sample(long _tv, int _timescale) {
        int a;
        int ttsInd;
        long tv = _tv * (long)this.timescale / (long)_timescale;
        int sample = 0;
        for (ttsInd = 0; ttsInd < this.timeToSamples.length - 1 && tv >= (long)(a = this.timeToSamples[ttsInd].getSampleCount() * this.timeToSamples[ttsInd].getSampleDuration()); ++ttsInd) {
            tv -= (long)a;
            sample += this.timeToSamples[ttsInd].getSampleCount();
        }
        return sample + (int)(tv / (long)this.timeToSamples[ttsInd].getSampleDuration());
    }

    public MP4TrackType getType() {
        return this.type;
    }

    public int getNo() {
        return this.no;
    }

    public SampleEntry[] getSampleEntries() {
        return this.sampleEntries;
    }

    public TrakBox getBox() {
        return this.box;
    }

    public long getTimescale() {
        return this.timescale;
    }

    protected abstract void seekPointer(long var1);

    public boolean canSeek(long pts) {
        return pts >= 0L && pts < this.duration;
    }

    public synchronized boolean seekPts(long pts) {
        if (pts < 0L) {
            throw new IllegalArgumentException("Seeking to negative pts");
        }
        if (pts >= this.duration) {
            return false;
        }
        long prevDur = 0L;
        int frameNo = 0;
        this.sttsInd = 0;
        while (pts > prevDur + (long)(this.timeToSamples[this.sttsInd].getSampleCount() * this.timeToSamples[this.sttsInd].getSampleDuration()) && this.sttsInd < this.timeToSamples.length - 1) {
            prevDur += (long)(this.timeToSamples[this.sttsInd].getSampleCount() * this.timeToSamples[this.sttsInd].getSampleDuration());
            frameNo += this.timeToSamples[this.sttsInd].getSampleCount();
            ++this.sttsInd;
        }
        this.sttsSubInd = (int)((pts - prevDur) / (long)this.timeToSamples[this.sttsInd].getSampleDuration());
        this.pts = prevDur + (long)(this.timeToSamples[this.sttsInd].getSampleDuration() * this.sttsSubInd);
        this.seekPointer(frameNo += this.sttsSubInd);
        return true;
    }

    protected void shiftPts(long frames) {
        this.pts -= (long)(this.sttsSubInd * this.timeToSamples[this.sttsInd].getSampleDuration());
        this.sttsSubInd = (int)((long)this.sttsSubInd + frames);
        while (this.sttsInd < this.timeToSamples.length - 1 && this.sttsSubInd >= this.timeToSamples[this.sttsInd].getSampleCount()) {
            this.pts += this.timeToSamples[this.sttsInd].getSegmentDuration();
            this.sttsSubInd -= this.timeToSamples[this.sttsInd].getSampleCount();
            ++this.sttsInd;
        }
        this.pts += (long)(this.sttsSubInd * this.timeToSamples[this.sttsInd].getSampleDuration());
    }

    protected void nextChunk() {
        if (this.stcoInd >= this.chunkOffsets.length) {
            return;
        }
        ++this.stcoInd;
        if (this.stscInd + 1 < this.sampleToChunks.length && (long)(this.stcoInd + 1) == this.sampleToChunks[this.stscInd + 1].getFirst()) {
            ++this.stscInd;
        }
    }

    @Override
    public synchronized boolean gotoFrame(long frameNo) {
        if (frameNo < 0L) {
            throw new IllegalArgumentException("negative frame number");
        }
        if (frameNo >= this.getFrameCount()) {
            return false;
        }
        if (frameNo == this.curFrame) {
            return true;
        }
        this.seekPointer(frameNo);
        this.seekFrame(frameNo);
        return true;
    }

    @Override
    public void seek(double second) {
        this.seekPts((long)(second * (double)this.timescale));
    }

    private void seekFrame(long frameNo) {
        this.sttsSubInd = 0;
        this.sttsInd = 0;
        this.pts = 0;
        this.shiftPts(frameNo);
    }

    public RationalLarge getDuration() {
        return new RationalLarge(this.box.getMediaDuration(), this.box.getTimescale());
    }

    public abstract long getFrameCount();

    @Override
    public long getCurFrame() {
        return this.curFrame;
    }

    public List<Edit> getEdits() {
        EditListBox editListBox = NodeBox.findFirstPath(this.box, EditListBox.class, Box.path("edts.elst"));
        if (editListBox != null) {
            return editListBox.getEdits();
        }
        return null;
    }

    public String getName() {
        NameBox nameBox = NodeBox.findFirstPath(this.box, NameBox.class, Box.path("udta.name"));
        return nameBox != null ? nameBox.getName() : null;
    }

    public String getFourcc() {
        SampleEntry[] entries = this.getSampleEntries();
        SampleEntry se = entries == null || entries.length == 0 ? null : entries[0];
        String fourcc = se == null ? null : se.getHeader().getFourcc();
        return fourcc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected ByteBuffer readPacketData(SeekableByteChannel input, ByteBuffer buffer, long offset, int size) throws IOException {
        ByteBuffer result = buffer.duplicate();
        SeekableByteChannel seekableByteChannel = input;
        synchronized (seekableByteChannel) {
            input.setPosition(offset);
            NIOUtils.readL(input, result, size);
        }
        result.flip();
        return result;
    }

    public abstract MP4Packet getNextFrame(ByteBuffer var1) throws IOException;

    public ByteBuffer convertPacket(ByteBuffer _in) {
        return _in;
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        return MP4DemuxerTrackMeta.fromTrack(this);
    }
}
