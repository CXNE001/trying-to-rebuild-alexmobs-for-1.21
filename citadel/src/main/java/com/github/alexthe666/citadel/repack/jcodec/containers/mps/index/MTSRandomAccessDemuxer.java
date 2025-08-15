/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSRandomAccessDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MTSIndex;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MTSRandomAccessDemuxer {
    private MTSIndex.MTSProgram[] programs;
    private SeekableByteChannel ch;

    public MTSRandomAccessDemuxer(SeekableByteChannel ch, MTSIndex index) {
        this.programs = index.getPrograms();
        this.ch = ch;
    }

    public int[] getGuids() {
        int[] guids = new int[this.programs.length];
        for (int i = 0; i < this.programs.length; ++i) {
            guids[i] = this.programs[i].getTargetGuid();
        }
        return guids;
    }

    public MPSRandomAccessDemuxer getProgramDemuxer(final int tgtGuid) throws IOException {
        MPSIndex index = this.getProgram(tgtGuid);
        return new MPSRandomAccessDemuxer(this.ch, index){

            @Override
            protected MPSRandomAccessDemuxer.Stream newStream(SeekableByteChannel ch, MPSIndex.MPSStreamIndex streamIndex) throws IOException {
                return new MPSRandomAccessDemuxer.Stream(this, streamIndex, ch){

                    @Override
                    protected ByteBuffer fetch(int pesLen) throws IOException {
                        ByteBuffer bb = ByteBuffer.allocate(pesLen * 188);
                        for (int i = 0; i < pesLen; ++i) {
                            ByteBuffer tsBuf = NIOUtils.fetchFromChannel(this.source, 188);
                            Preconditions.checkState(71 == (tsBuf.get() & 0xFF));
                            int guidFlags = (tsBuf.get() & 0xFF) << 8 | tsBuf.get() & 0xFF;
                            int guid = guidFlags & 0x1FFF;
                            if (guid != tgtGuid) continue;
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
                };
            }
        };
    }

    private MPSIndex getProgram(int guid) {
        for (MTSIndex.MTSProgram mtsProgram : this.programs) {
            if (mtsProgram.getTargetGuid() != guid) continue;
            return mtsProgram;
        }
        return null;
    }
}
