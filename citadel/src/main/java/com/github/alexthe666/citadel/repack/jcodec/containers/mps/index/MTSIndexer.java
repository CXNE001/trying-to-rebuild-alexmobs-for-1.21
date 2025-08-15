/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.BaseIndexer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MTSIndex;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MTSIndexer {
    public static final int BUFFER_SIZE = 96256;
    private MTSAnalyser[] indexers;

    public void index(File source, NIOUtils.FileReaderListener listener) throws IOException {
        this.indexReader(listener, MTSUtils.getMediaPids(source)).readFile(source, 96256, listener);
    }

    public void indexChannel(SeekableByteChannel source, NIOUtils.FileReaderListener listener) throws IOException {
        this.indexReader(listener, MTSUtils.getMediaPidsFromChannel(source)).readChannel(source, 96256, listener);
    }

    public NIOUtils.FileReader indexReader(NIOUtils.FileReaderListener listener, int[] targetGuids) throws IOException {
        this.indexers = new MTSAnalyser[targetGuids.length];
        for (int i = 0; i < targetGuids.length; ++i) {
            this.indexers[i] = new MTSAnalyser(targetGuids[i]);
        }
        return new MTSFileReader(this);
    }

    public MTSIndex serialize() {
        MTSIndex.MTSProgram[] programs = new MTSIndex.MTSProgram[this.indexers.length];
        for (int i = 0; i < this.indexers.length; ++i) {
            programs[i] = this.indexers[i].serializeTo();
        }
        return new MTSIndex(programs);
    }

    public static void main1(String[] args) throws IOException {
        File src = new File(args[0]);
        MTSIndexer indexer = new MTSIndexer();
        indexer.index(src, new NIOUtils.FileReaderListener(){

            @Override
            public void progress(int percentDone) {
                System.out.println(percentDone);
            }
        });
        MTSIndex index = indexer.serialize();
        NIOUtils.writeTo(index.serialize(), new File(args[1]));
    }

    private static class MTSAnalyser
    extends BaseIndexer {
        private int targetGuid;
        private long predFileStartInTsPkt;

        public MTSAnalyser(int targetGuid) {
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
    }

    private static final class MTSFileReader
    extends NIOUtils.FileReader {
        private MTSIndexer indexer;

        public MTSFileReader(MTSIndexer indexer) {
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
            for (MTSAnalyser mtsAnalyser : this.indexer.indexers) {
                mtsAnalyser.finishAnalyse();
            }
        }
    }
}
