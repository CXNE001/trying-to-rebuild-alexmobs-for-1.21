/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPEGDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public static abstract class MPSDemuxer.BaseTrack
implements MPEGDemuxer.MPEGDemuxerTrack {
    protected int streamId;
    protected List<PESPacket> _pending = new ArrayList<PESPacket>();
    protected MPSDemuxer demuxer;

    public MPSDemuxer.BaseTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
        this.demuxer = demuxer;
        this.streamId = streamId;
        this._pending.add(pkt);
    }

    @Override
    public int getSid() {
        return this.streamId;
    }

    public void pending(PESPacket pkt) {
        if (this._pending != null) {
            this._pending.add(pkt);
        } else {
            this.demuxer.putBack(pkt.data);
        }
    }

    @Override
    public List<PESPacket> getPending() {
        return this._pending;
    }

    @Override
    public void ignore() {
        if (this._pending == null) {
            return;
        }
        for (PESPacket pesPacket : this._pending) {
            this.demuxer.putBack(pesPacket.data);
        }
        this._pending = null;
    }
}
