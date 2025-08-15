/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer;

import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.model.TapeTimecode;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer.MKVDemuxer;
import java.io.IOException;

public static class MKVDemuxer.AudioTrack
extends MKVDemuxer.MkvTrack {
    public double samplingFrequency;

    public MKVDemuxer.AudioTrack(int trackNo, MKVDemuxer demuxer) {
        super(trackNo, demuxer);
    }

    @Override
    public Packet nextFrame() throws IOException {
        MKVDemuxer.MkvBlockData b = this.nextBlock();
        if (b == null) {
            return null;
        }
        return Packet.createPacket(b.data, b.block.absoluteTimecode, (int)Math.round(this.samplingFrequency), 1L, 0L, Packet.FrameType.KEY, TapeTimecode.ZERO_TAPE_TIMECODE);
    }

    @Override
    public Packet getFrames(int count) {
        MKVDemuxer.MkvBlockData frameBlock = this.getFrameBlock(count);
        if (frameBlock == null) {
            return null;
        }
        return Packet.createPacket(frameBlock.data, frameBlock.block.absoluteTimecode, (int)Math.round(this.samplingFrequency), frameBlock.count, 0L, Packet.FrameType.KEY, TapeTimecode.ZERO_TAPE_TIMECODE);
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
