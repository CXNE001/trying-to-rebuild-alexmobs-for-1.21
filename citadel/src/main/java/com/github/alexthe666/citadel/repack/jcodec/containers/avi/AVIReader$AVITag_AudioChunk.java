/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_AudioChunk
extends AVIReader.AVIChunk {
    protected int streamNo;
    private DataReader raf;

    AVIReader.AVITag_AudioChunk() {
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        this.raf = raf;
        super.read(dwFourCC, raf);
        String fourccStr = AVIReader.toFourCC(dwFourCC);
        this.streamNo = Integer.parseInt(fourccStr.substring(0, 2));
    }

    @Override
    public int getChunkSize() {
        if ((this.dwChunkSize & 1) == 1) {
            return this.dwChunkSize + 1;
        }
        return this.dwChunkSize;
    }

    public byte[] getAudioPacket() throws IOException {
        byte[] audioFrameData = new byte[this.dwChunkSize];
        int bytesRead = this.raf.readFully(audioFrameData);
        if (bytesRead != this.dwChunkSize) {
            throw new IOException("Read mismatch expected chunksize [" + this.dwChunkSize + "], Actual read [" + bytesRead + "]");
        }
        int alignment = this.getChunkSize() - this.dwChunkSize;
        if (alignment > 0) {
            this.raf.skipBytes(alignment);
        }
        return audioFrameData;
    }

    @Override
    public String toString() {
        return "\tAUDIO CHUNK - Stream " + this.streamNo + ", StartOfChunk=" + this.getStartOfChunk() + ", ChunkSize=" + this.getChunkSize();
    }
}
