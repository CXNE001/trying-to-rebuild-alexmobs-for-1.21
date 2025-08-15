/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.RunLength;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MTSIndex;
import java.nio.ByteBuffer;

public static class MTSIndex.MTSProgram
extends MPSIndex {
    private int targetGuid;

    public MTSIndex.MTSProgram(long[] pesTokens, RunLength.Integer pesStreamIds, MPSIndex.MPSStreamIndex[] streams, int targetGuid) {
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

    public static MTSIndex.MTSProgram parse(ByteBuffer read) {
        int targetGuid = read.getInt();
        return MTSIndex.createMTSProgram(MPSIndex.parseIndex(read), targetGuid);
    }
}
