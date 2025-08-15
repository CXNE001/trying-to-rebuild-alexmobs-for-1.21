/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSRandomAccessDemuxer;
import java.io.IOException;
import java.nio.ByteBuffer;

class MTSRandomAccessDemuxer.1
extends MPSRandomAccessDemuxer.Stream {
    MTSRandomAccessDemuxer.1(MPSRandomAccessDemuxer demuxer, MPSIndex.MPSStreamIndex streamIndex, SeekableByteChannel source) {
        super(demuxer, streamIndex, source);
    }

    @Override
    protected ByteBuffer fetch(int pesLen) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(pesLen * 188);
        for (int i = 0; i < pesLen; ++i) {
            ByteBuffer tsBuf = NIOUtils.fetchFromChannel(this.source, 188);
            Preconditions.checkState(71 == (tsBuf.get() & 0xFF));
            int guidFlags = (tsBuf.get() & 0xFF) << 8 | tsBuf.get() & 0xFF;
            int guid = guidFlags & 0x1FFF;
            if (guid != val$tgtGuid) continue;
            int payloadStart = guidFlags >> 14 & 1;
            int b0 = tsBuf.get() & 0xFF;
            int counter = b0 & 0xF;
            if ((b0 & 0x20) != 0) {
                NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
            }
            bb.put(tsBuf);
        }
        bb.flip();
        return bb;
    }

    @Override
    protected void skip(long leadingSize) throws IOException {
        this.source.setPosition(this.source.position() + leadingSize * 188L);
    }

    @Override
    protected void reset() throws IOException {
        this.source.setPosition(0L);
    }
}
