/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.BaseIndexer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import java.nio.ByteBuffer;

private static class BaseIndexer.GenericAnalyser
extends BaseIndexer.BaseAnalyser {
    private IntArrayList sizes = new IntArrayList(250000);
    private int knownDuration;
    private long lastPts;

    @Override
    public void pkt(ByteBuffer pkt, PESPacket pesHeader) {
        this.sizes.add(pkt.remaining());
        if (pesHeader.pts == -1L) {
            pesHeader.pts = this.lastPts + (long)this.knownDuration;
        } else {
            this.knownDuration = (int)(pesHeader.pts - this.lastPts);
            this.lastPts = pesHeader.pts;
        }
        this.pts.add((int)pesHeader.pts);
        this.dur.add(this.knownDuration);
    }

    @Override
    public MPSIndex.MPSStreamIndex serialize(int streamId) {
        return new MPSIndex.MPSStreamIndex(streamId, this.sizes.toArray(), this.pts.toArray(), this.dur.toArray(), new int[0]);
    }

    @Override
    public int estimateSize() {
        return super.estimateSize() + (this.sizes.size() << 2) + 32;
    }

    @Override
    public void finishAnalyse() {
    }
}
