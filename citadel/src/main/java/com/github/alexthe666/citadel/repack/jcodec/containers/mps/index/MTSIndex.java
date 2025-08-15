/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.RunLength;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import java.nio.ByteBuffer;

public class MTSIndex {
    private MTSProgram[] programs;

    public static MTSProgram createMTSProgram(MPSIndex mpsIndex, int target) {
        MTSProgram m = new MTSProgram(mpsIndex.pesTokens, mpsIndex.pesStreamIds, mpsIndex.streams, target);
        return m;
    }

    public MTSIndex(MTSProgram[] programs) {
        this.programs = programs;
    }

    public MTSProgram[] getPrograms() {
        return this.programs;
    }

    public static MTSIndex parse(ByteBuffer buf) {
        int numPrograms = buf.getInt();
        MTSProgram[] programs = new MTSProgram[numPrograms];
        for (int i = 0; i < numPrograms; ++i) {
            int programDataSize = buf.getInt();
            programs[i] = MTSProgram.parse(NIOUtils.read(buf, programDataSize));
        }
        return new MTSIndex(programs);
    }

    public int estimateSize() {
        int totalSize = 64;
        for (MTSProgram mtsProgram : this.programs) {
            totalSize += 4 + mtsProgram.estimateSize();
        }
        return totalSize;
    }

    public void serializeTo(ByteBuffer buf) {
        buf.putInt(this.programs.length);
        for (MTSProgram mtsAnalyser : this.programs) {
            ByteBuffer dup = buf.duplicate();
            NIOUtils.skip(buf, 4);
            mtsAnalyser.serializeTo(buf);
            dup.putInt(buf.position() - dup.position() - 4);
        }
    }

    public ByteBuffer serialize() {
        ByteBuffer bb = ByteBuffer.allocate(this.estimateSize());
        this.serializeTo(bb);
        bb.flip();
        return bb;
    }

    public static class MTSProgram
    extends MPSIndex {
        private int targetGuid;

        public MTSProgram(long[] pesTokens, RunLength.Integer pesStreamIds, MPSIndex.MPSStreamIndex[] streams, int targetGuid) {
            super(pesTokens, pesStreamIds, streams);
            this.targetGuid = targetGuid;
        }

        public int getTargetGuid() {
            return this.targetGuid;
        }

        @Override
        public void serializeTo(ByteBuffer index) {
            index.putInt(this.targetGuid);
            super.serializeTo(index);
        }

        public static MTSProgram parse(ByteBuffer read) {
            int targetGuid = read.getInt();
            return MTSIndex.createMTSProgram(MPSIndex.parseIndex(read), targetGuid);
        }
    }
}
