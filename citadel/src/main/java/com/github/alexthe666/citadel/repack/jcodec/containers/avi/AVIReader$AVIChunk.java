/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVIChunk {
    protected int dwFourCC;
    protected String fwFourCCStr;
    protected int dwChunkSize;
    protected long startOfChunk;

    AVIReader.AVIChunk() {
    }

    public void read(int dwFourCC, DataReader raf) throws IOException {
        this.startOfChunk = raf.position() - 4L;
        this.dwFourCC = dwFourCC;
        this.fwFourCCStr = AVIReader.toFourCC(dwFourCC);
        this.dwChunkSize = raf.readInt();
    }

    public long getStartOfChunk() {
        return this.startOfChunk;
    }

    public long getEndOfChunk() {
        return this.startOfChunk + 8L + (long)this.getChunkSize();
    }

    public int getFourCC() {
        return this.dwFourCC;
    }

    public void skip(DataReader raf) throws IOException {
        int chunkSize = this.getChunkSize();
        if (chunkSize < 0) {
            throw new IOException("Negative chunk size for chunk [" + AVIReader.toFourCC(this.dwFourCC) + "]");
        }
        raf.skipBytes(chunkSize);
    }

    public int getChunkSize() {
        if ((this.dwChunkSize & 1) == 1) {
            return this.dwChunkSize + 1;
        }
        return this.dwChunkSize;
    }

    public String toString() {
        String chunkStr = AVIReader.toFourCC(this.dwFourCC);
        if (chunkStr.trim().length() == 0) {
            chunkStr = Integer.toHexString(this.dwFourCC);
        }
        return "\tCHUNK [" + chunkStr + "], Size [" + this.dwChunkSize + "], StartOfChunk [" + this.getStartOfChunk() + "]";
    }
}
