/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.LongArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.SeekableDemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTrackDemuxer;
import java.io.IOException;
import java.nio.ByteBuffer;

public static class FLVTrackDemuxer.FLVDemuxerTrack
implements SeekableDemuxerTrack {
    private FLVTag.Type type;
    private int curFrame;
    private Codec codec;
    private LongArrayList framePositions = LongArrayList.createLongArrayList();
    private byte[] codecPrivate;
    private FLVTrackDemuxer demuxer;

    public FLVTrackDemuxer.FLVDemuxerTrack(FLVTrackDemuxer demuxer, FLVTag.Type type) throws IOException {
        this.demuxer = demuxer;
        this.type = type;
        FLVTag frame = demuxer.nextFrameI(type, false);
        this.codec = frame.getTagHeader().getCodec();
    }

    @Override
    public Packet nextFrame() throws IOException {
        FLVTag frame = this.demuxer.nextFrameI(this.type, true);
        this.framePositions.add(frame.getPosition());
        return this.toPacket(frame);
    }

    public Packet prevFrame() throws IOException {
        FLVTag frame = this.demuxer.prevFrameI(this.type, true);
        return this.toPacket(frame);
    }

    public Packet pickFrame() throws IOException {
        FLVTag frame = this.demuxer.nextFrameI(this.type, false);
        return this.toPacket(frame);
    }

    private Packet toPacket(FLVTag frame) {
        return null;
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        TrackType t = this.type == FLVTag.Type.VIDEO ? TrackType.VIDEO : TrackType.AUDIO;
        return new DemuxerTrackMeta(t, this.codec, 0.0, null, 0, ByteBuffer.wrap(this.codecPrivate), null, null);
    }

    @Override
    public boolean gotoFrame(long i) throws IOException {
        if (i >= (long)this.framePositions.size()) {
            return false;
        }
        this.demuxer.resetToPosition(this.framePositions.get((int)i));
        return true;
    }

    @Override
    public boolean gotoSyncFrame(long i) {
        throw new RuntimeException();
    }

    @Override
    public long getCurFrame() {
        return this.curFrame;
    }

    @Override
    public void seek(double second) throws IOException {
        this.demuxer.seekI(second);
    }
}
