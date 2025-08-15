/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_AviDmlSuperIndex
extends AVIReader.AVIChunk {
    protected short wLongsPerEntry;
    protected byte bIndexSubType;
    protected byte bIndexType;
    protected int nEntriesInUse;
    protected int dwChunkId;
    protected int[] dwReserved = new int[3];
    protected long[] qwOffset;
    protected int[] dwSize;
    protected int[] dwDuration;
    private int numIndex;
    private int numIndexFill;
    StringBuilder sb = new StringBuilder();
    private int streamNo = 0;

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        this.wLongsPerEntry = raf.readShort();
        this.bIndexSubType = raf.readByte();
        this.bIndexType = raf.readByte();
        this.nEntriesInUse = raf.readInt();
        this.dwChunkId = raf.readInt();
        this.dwReserved[0] = raf.readInt();
        this.dwReserved[1] = raf.readInt();
        this.dwReserved[2] = raf.readInt();
        this.qwOffset = new long[this.nEntriesInUse];
        this.dwSize = new int[this.nEntriesInUse];
        this.dwDuration = new int[this.nEntriesInUse];
        String chunkIdStr = AVIReader.toFourCC(this.dwChunkId);
        this.sb.append(String.format("\tAvi DML Super Index List - ChunkSize=%d, NumIndexes = %d, longsPerEntry = %d, Stream = %s, Type = %s", this.getChunkSize(), this.nEntriesInUse, this.wLongsPerEntry, chunkIdStr.substring(0, 2), chunkIdStr.substring(2)));
        for (int i = 0; i < this.nEntriesInUse; ++i) {
            this.qwOffset[i] = raf.readLong();
            this.dwSize[i] = raf.readInt();
            this.dwDuration[i] = raf.readInt();
            this.sb.append(String.format("\n\t\tStandard Index - Offset [%d], Size [%d], Duration [%d]", this.qwOffset[i], this.dwSize[i], this.dwDuration[i]));
        }
        raf.setPosition(this.getEndOfChunk());
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}
