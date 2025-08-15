/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndex;
import java.nio.ByteBuffer;

protected static abstract class BaseIndexer.BaseAnalyser {
    protected IntArrayList pts = new IntArrayList(250000);
    protected IntArrayList dur = new IntArrayList(250000);

    public abstract void pkt(ByteBuffer var1, PESPacket var2);

    public abstract void finishAnalyse();

    public int estimateSize() {
        return (this.pts.size() << 2) + 4;
    }

    public abstract MPSIndex.MPSStreamIndex serialize(int var1);
}
