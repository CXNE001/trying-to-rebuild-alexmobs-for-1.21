/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_AviIndex
extends AVIReader.AVIChunk {
    protected int numIndexes = 0;
    protected int[] ckid;
    protected int[] dwFlags;
    protected int[] dwChunkOffset;
    protected int[] dwChunkLength;

    AVIReader.AVITag_AviIndex() {
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        this.numIndexes = this.getChunkSize() >> 4;
        this.ckid = new int[this.numIndexes];
        this.dwFlags = new int[this.numIndexes];
        this.dwChunkOffset = new int[this.numIndexes];
        this.dwChunkLength = new int[this.numIndexes];
        for (int i = 0; i < this.numIndexes; ++i) {
            this.ckid[i] = raf.readInt();
            this.dwFlags[i] = raf.readInt();
            this.dwChunkOffset[i] = raf.readInt();
            this.dwChunkLength[i] = raf.readInt();
        }
        raf.setPosition(this.getEndOfChunk());
        int alignment = this.getChunkSize() - this.dwChunkSize;
        if (alignment > 0) {
            raf.skipBytes(alignment);
        }
    }

    public int getNumIndexes() {
        return this.numIndexes;
    }

    public int[] getCkid() {
        return this.ckid;
    }

    public int[] getDwFlags() {
        return this.dwFlags;
    }

    public int[] getDwChunkOffset() {
        return this.dwChunkOffset;
    }

    public int[] getDwChunkLength() {
        return this.dwChunkLength;
    }

    public void debugOut() {
        for (int i = 0; i < this.numIndexes; ++i) {
            Logger.debug("\t");
        }
    }

    @Override
    public String toString() {
        return String.format("\tAvi Index List, StartOfChunk [%d], ChunkSize [%d], NumIndexes [%d]", this.getStartOfChunk(), this.dwChunkSize, this.getChunkSize() >> 4);
    }
}
