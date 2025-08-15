/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer;

import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.SeekableDemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.model.TapeTimecode;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.MkvBlock;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer.MKVDemuxer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public static class MKVDemuxer.MkvTrack
implements SeekableDemuxerTrack {
    public final int trackNo;
    List<MKVDemuxer.IndexedBlock> blocks = new ArrayList<MKVDemuxer.IndexedBlock>();
    int framesCount = 0;
    private int frameIdx = 0;
    private int blockIdx = 0;
    private int frameInBlockIdx = 0;
    private MKVDemuxer demuxer;

    public MKVDemuxer.MkvTrack(int trackNo, MKVDemuxer demuxer) {
        this.trackNo = trackNo;
        this.demuxer = demuxer;
    }

    @Override
    public Packet nextFrame() throws IOException {
        MKVDemuxer.MkvBlockData bd = this.nextBlock();
        if (bd == null) {
            return null;
        }
        return Packet.createPacket(bd.data, bd.block.absoluteTimecode, this.demuxer.timescale, 1L, this.frameIdx - 1, Packet.FrameType.KEY, TapeTimecode.ZERO_TAPE_TIMECODE);
    }

    protected MKVDemuxer.MkvBlockData nextBlock() throws IOException {
        ByteBuffer data;
        if (this.frameIdx >= this.blocks.size() || this.blockIdx >= this.blocks.size()) {
            return null;
        }
        MkvBlock b = this.blocks.get((int)this.blockIdx).block;
        if (b == null) {
            throw new RuntimeException("Something somewhere went wrong.");
        }
        if (b.frames == null || b.frames.length == 0) {
            this.demuxer.channel.setPosition(b.dataOffset);
            data = ByteBuffer.allocate(b.dataLen);
            this.demuxer.channel.read(data);
            b.readFrames(data);
        }
        data = b.frames[this.frameInBlockIdx].duplicate();
        ++this.frameInBlockIdx;
        ++this.frameIdx;
        if (this.frameInBlockIdx >= b.frames.length) {
            ++this.blockIdx;
            this.frameInBlockIdx = 0;
        }
        return new MKVDemuxer.MkvBlockData(b, data, 1);
    }

    @Override
    public boolean gotoFrame(long i) {
        if (i > Integer.MAX_VALUE) {
            return false;
        }
        if (i > (long)this.framesCount) {
            return false;
        }
        int frameBlockIdx = this.findBlockIndex(i);
        if (frameBlockIdx == -1) {
            return false;
        }
        this.frameIdx = (int)i;
        this.blockIdx = frameBlockIdx;
        this.frameInBlockIdx = (int)i - this.blocks.get((int)this.blockIdx).firstFrameNo;
        return true;
    }

    private int findBlockIndex(long i) {
        for (int blockIndex = 0; blockIndex < this.blocks.size(); ++blockIndex) {
            if (i < (long)this.blocks.get((int)blockIndex).block.frameSizes.length) {
                return blockIndex;
            }
            i -= (long)this.blocks.get((int)blockIndex).block.frameSizes.length;
        }
        return -1;
    }

    @Override
    public long getCurFrame() {
        return this.frameIdx;
    }

    @Override
    public void seek(double second) {
        throw new RuntimeException("Not implemented yet");
    }

    public Packet getFrames(int count) {
        MKVDemuxer.MkvBlockData frameBlock = this.getFrameBlock(count);
        if (frameBlock == null) {
            return null;
        }
        return Packet.createPacket(frameBlock.data, frameBlock.block.absoluteTimecode, this.demuxer.timescale, frameBlock.count, 0L, Packet.FrameType.KEY, TapeTimecode.ZERO_TAPE_TIMECODE);
    }

    MKVDemuxer.MkvBlockData getFrameBlock(int count) {
        ByteBuffer data;
        if (count + this.frameIdx >= this.framesCount) {
            return null;
        }
        ArrayList<ByteBuffer> packetFrames = new ArrayList<ByteBuffer>();
        MkvBlock firstBlockInAPacket = this.blocks.get((int)this.blockIdx).block;
        while (count > 0) {
            MkvBlock b = this.blocks.get((int)this.blockIdx).block;
            if (b.frames == null || b.frames.length == 0) {
                try {
                    this.demuxer.channel.setPosition(b.dataOffset);
                    data = ByteBuffer.allocate(b.dataLen);
                    this.demuxer.channel.read(data);
                    b.readFrames(data);
                }
                catch (IOException ioe) {
                    throw new RuntimeException("while reading frames of a Block at offset 0x" + Long.toHexString(b.dataOffset).toUpperCase() + ")", ioe);
                }
            }
            packetFrames.add(b.frames[this.frameInBlockIdx].duplicate());
            ++this.frameIdx;
            ++this.frameInBlockIdx;
            if (this.frameInBlockIdx >= b.frames.length) {
                this.frameInBlockIdx = 0;
                ++this.blockIdx;
            }
            --count;
        }
        int size = 0;
        for (ByteBuffer aFrame : packetFrames) {
            size += aFrame.limit();
        }
        data = ByteBuffer.allocate(size);
        for (ByteBuffer aFrame : packetFrames) {
            data.put(aFrame);
        }
        return new MKVDemuxer.MkvBlockData(firstBlockInAPacket, data, packetFrames.size());
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        return null;
    }

    @Override
    public boolean gotoSyncFrame(long frame) {
        return this.gotoFrame(frame);
    }
}
