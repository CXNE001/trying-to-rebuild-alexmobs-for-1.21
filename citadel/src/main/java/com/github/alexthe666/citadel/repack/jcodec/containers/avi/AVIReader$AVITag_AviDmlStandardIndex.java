/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_AviDmlStandardIndex
extends AVIReader.AVIChunk {
    protected short wLongsPerEntry;
    protected byte bIndexSubType;
    protected byte bIndexType;
    protected int nEntriesInUse;
    protected int dwChunkId;
    protected long qwBaseOffset;
    protected int dwReserved2;
    protected int[] dwOffset;
    protected int[] dwDuration;
    int lastOffset = -1;
    int lastDuration = -1;

    AVIReader.AVITag_AviDmlStandardIndex() {
    }

    @Override
    public int getChunkSize() {
        return this.dwChunkSize;
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        this.wLongsPerEntry = raf.readShort();
        this.bIndexSubType = raf.readByte();
        this.bIndexType = raf.readByte();
        this.nEntriesInUse = raf.readInt();
        this.dwChunkId = raf.readInt();
        this.qwBaseOffset = raf.readLong();
        this.dwReserved2 = raf.readInt();
        this.dwOffset = new int[this.nEntriesInUse];
        this.dwDuration = new int[this.nEntriesInUse];
        try {
            for (int i = 0; i < this.nEntriesInUse; ++i) {
                this.dwOffset[i] = raf.readInt();
                this.dwDuration[i] = raf.readInt();
                this.lastOffset = this.dwOffset[i];
                this.lastDuration = this.dwDuration[i];
            }
        }
        catch (Exception e) {
            Logger.debug("Failed to read : " + this.toString());
        }
        raf.setPosition(this.getEndOfChunk());
    }

    @Override
    public String toString() {
        return String.format("\tAvi DML Standard Index List Type=%d, SubType=%d, ChunkId=%s, StartOfChunk=%d, NumIndexes=%d, LongsPerEntry=%d, ChunkSize=%d, FirstOffset=%d, FirstDuration=%d,LastOffset=%d, LastDuration=%d", this.bIndexType, this.bIndexSubType, AVIReader.toFourCC(this.dwChunkId), this.getStartOfChunk(), this.nEntriesInUse, this.wLongsPerEntry, this.getChunkSize(), this.dwOffset[0], this.dwDuration[0], this.lastOffset, this.lastDuration);
    }
}
