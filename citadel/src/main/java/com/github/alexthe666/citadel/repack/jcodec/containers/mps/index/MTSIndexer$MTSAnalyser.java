/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.BaseIndexer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MTSIndex;
import java.nio.ByteBuffer;

private static class MTSIndexer.MTSAnalyser
extends BaseIndexer {
    private int targetGuid;
    private long predFileStartInTsPkt;

    public MTSIndexer.MTSAnalyser(int targetGuid) {
        this.targetGuid = targetGuid;
    }

    public MTSIndex.MTSProgram serializeTo() {
        return MTSIndex.createMTSProgram(super.serialize(), this.targetGuid);
    }

    @Override
    protected void pes(ByteBuffer pesBuffer, long start, int pesLen, int stream) {
        if (!MPSUtils.mediaStream(stream)) {
            return;
        }
        Logger.debug(String.format("PES: %08x, %d", start, pesLen));
        PESPacket pesHeader = MPSUtils.readPESHeader(pesBuffer, start);
        int leadingTsPkt = 0;
        if (this.predFileStartInTsPkt != start) {
            leadingTsPkt = (int)(start / 188L - this.predFileStartInTsPkt);
        }
        this.predFileStartInTsPkt = (start + (long)pesLen) / 188L;
        int tsPktInPes = (int)(this.predFileStartInTsPkt - start / 188L);
        this.savePESMeta(stream, MPSIndex.makePESToken(leadingTsPkt, tsPktInPes, pesBuffer.remaining()));
        this.getAnalyser(stream).pkt(pesBuffer, pesHeader);
    }

    static /* synthetic */ int access$100(MTSIndexer.MTSAnalyser x0) {
        return x0.targetGuid;
    }
}
