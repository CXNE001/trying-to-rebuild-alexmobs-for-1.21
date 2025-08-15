/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import java.io.IOException;
import java.nio.ByteBuffer;

public static class MPSDemuxer.PlainTrack
extends MPSDemuxer.BaseTrack {
    private int frameNo;
    private Packet lastFrame;
    private long lastKnownDuration = 3003L;

    public MPSDemuxer.PlainTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
        super(demuxer, streamId, pkt);
    }

    public boolean isOpen() {
        return true;
    }

    public void close() throws IOException {
    }

    @Override
    public Packet nextFrameWithBuffer(ByteBuffer buf) throws IOException {
        PESPacket pkt;
        if (this._pending.size() > 0) {
            pkt = (PESPacket)this._pending.remove(0);
        } else {
            while ((pkt = this.demuxer.nextPacket(this.demuxer.getBuffer())) != null && pkt.streamId != this.streamId) {
                this.demuxer.addToStream(pkt);
            }
        }
        return pkt == null ? null : Packet.createPacket(pkt.data, pkt.pts, 90000, 0L, this.frameNo++, Packet.FrameType.UNKNOWN, null);
    }

    @Override
    public Packet nextFrame() throws IOException {
        if (this.lastFrame == null) {
            this.lastFrame = this.nextFrameWithBuffer(null);
        }
        if (this.lastFrame == null) {
            return null;
        }
        Packet toReturn = this.lastFrame;
        this.lastFrame = this.nextFrameWithBuffer(null);
        if (this.lastFrame != null) {
            this.lastKnownDuration = this.lastFrame.getPts() - toReturn.getPts();
        }
        toReturn.setDuration(this.lastKnownDuration);
        return toReturn;
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        TrackType t = MPSUtils.videoStream(this.streamId) ? TrackType.VIDEO : (MPSUtils.audioStream(this.streamId) ? TrackType.AUDIO : TrackType.OTHER);
        return null;
    }
}
