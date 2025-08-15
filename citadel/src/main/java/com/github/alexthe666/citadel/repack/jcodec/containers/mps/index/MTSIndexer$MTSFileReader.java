/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MTSIndexer;
import java.nio.ByteBuffer;

private static final class MTSIndexer.MTSFileReader
extends NIOUtils.FileReader {
    private MTSIndexer indexer;

    public MTSIndexer.MTSFileReader(MTSIndexer indexer) {
        this.indexer = indexer;
    }

    @Override
    protected void data(ByteBuffer data, long filePos) {
        this.analyseBuffer(data, filePos);
    }

    protected void analyseBuffer(ByteBuffer buf, long pos) {
        while (buf.hasRemaining()) {
            ByteBuffer tsBuf = NIOUtils.read(buf, 188);
            pos += 188L;
            Preconditions.checkState(71 == (tsBuf.get() & 0xFF));
            int guidFlags = (tsBuf.get() & 0xFF) << 8 | tsBuf.get() & 0xFF;
            int guid = guidFlags & 0x1FFF;
            for (int i = 0; i < this.indexer.indexers.length; ++i) {
                if (guid != this.indexer.indexers[i].targetGuid) continue;
                int payloadStart = guidFlags >> 14 & 1;
                int b0 = tsBuf.get() & 0xFF;
                int counter = b0 & 0xF;
                if ((b0 & 0x20) != 0) {
                    NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
                }
                this.indexer.indexers[i].analyseBuffer(tsBuf, pos - (long)tsBuf.remaining());
            }
        }
    }

    @Override
    protected void done() {
        for (MTSIndexer.MTSAnalyser mtsAnalyser : this.indexer.indexers) {
            mtsAnalyser.finishAnalyse();
        }
    }
}
