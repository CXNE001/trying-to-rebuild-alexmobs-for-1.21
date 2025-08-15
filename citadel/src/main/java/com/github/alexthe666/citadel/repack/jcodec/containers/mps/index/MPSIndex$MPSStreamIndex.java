/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import java.nio.ByteBuffer;

public static class MPSIndex.MPSStreamIndex {
    protected int streamId;
    protected int[] fsizes;
    protected int[] fpts;
    protected int[] fdur;
    protected int[] sync;

    public MPSIndex.MPSStreamIndex(int streamId, int[] fsizes, int[] fpts, int[] fdur, int[] sync) {
        this.streamId = streamId;
        this.fsizes = fsizes;
        this.fpts = fpts;
        this.fdur = fdur;
        this.sync = sync;
    }

    public int getStreamId() {
        return this.streamId;
    }

    public int[] getFsizes() {
        return this.fsizes;
    }

    public int[] getFpts() {
        return this.fpts;
    }

    public int[] getFdur() {
        return this.fdur;
    }

    public int[] getSync() {
        return this.sync;
    }

    public static MPSIndex.MPSStreamIndex parseIndex(ByteBuffer index) {
        int streamId = index.get() & 0xFF;
        int fCnt = index.getInt();
        int[] fsizes = new int[fCnt];
        for (int i = 0; i < fCnt; ++i) {
            fsizes[i] = index.getInt();
        }
        int fptsCnt = index.getInt();
        int[] fpts = new int[fptsCnt];
        for (int i = 0; i < fptsCnt; ++i) {
            fpts[i] = index.getInt();
        }
        int fdurCnt = index.getInt();
        int[] fdur = new int[fdurCnt];
        for (int i = 0; i < fdurCnt; ++i) {
            fdur[i] = index.getInt();
        }
        int syncCount = index.getInt();
        int[] sync = new int[syncCount];
        for (int i = 0; i < syncCount; ++i) {
            sync[i] = index.getInt();
        }
        return new MPSIndex.MPSStreamIndex(streamId, fsizes, fpts, fdur, sync);
    }

    public void serialize(ByteBuffer index) {
        int i;
        index.put((byte)this.streamId);
        index.putInt(this.fsizes.length);
        for (i = 0; i < this.fsizes.length; ++i) {
            index.putInt(this.fsizes[i]);
        }
        index.putInt(this.fpts.length);
        for (i = 0; i < this.fpts.length; ++i) {
            index.putInt(this.fpts[i]);
        }
        index.putInt(this.fdur.length);
        for (i = 0; i < this.fdur.length; ++i) {
            index.putInt(this.fdur[i]);
        }
        index.putInt(this.sync.length);
        for (i = 0; i < this.sync.length; ++i) {
            index.putInt(this.sync[i]);
        }
    }

    public int estimateSize() {
        return (this.fpts.length << 2) + (this.fdur.length << 2) + (this.sync.length << 2) + (this.fsizes.length << 2) + 64;
    }
}
