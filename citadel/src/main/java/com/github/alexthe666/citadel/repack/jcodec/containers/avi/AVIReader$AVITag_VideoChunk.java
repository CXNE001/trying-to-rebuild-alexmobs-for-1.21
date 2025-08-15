/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_VideoChunk
extends AVIReader.AVIChunk {
    protected int streamNo;
    protected boolean compressed = false;
    protected int frameNo = -1;
    private DataReader raf;

    public AVIReader.AVITag_VideoChunk(boolean compressed, DataReader raf) {
        this.compressed = compressed;
        this.raf = raf;
    }

    public int getStreamNo() {
        return this.streamNo;
    }

    public void setFrameNo(int frameNo) {
        this.frameNo = frameNo;
    }

    @Override
    public int getChunkSize() {
        if ((this.dwChunkSize & 1) == 1) {
            return this.dwChunkSize + 1;
        }
        return this.dwChunkSize;
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        String fourccStr = AVIReader.toFourCC(dwFourCC);
        this.streamNo = Integer.parseInt(fourccStr.substring(0, 2));
    }

    public byte[] getVideoPacket() throws IOException {
        byte[] videoFrameData = new byte[this.dwChunkSize];
        int bytesRead = this.raf.readFully(videoFrameData);
        if (bytesRead != this.dwChunkSize) {
            throw new IOException("Read mismatch expected chunksize [" + this.dwChunkSize + "], Actual read [" + bytesRead + "]");
        }
        int alignment = this.getChunkSize() - this.dwChunkSize;
        if (alignment > 0) {
            this.raf.skipBytes(alignment);
        }
        return videoFrameData;
    }

    @Override
    public String toString() {
        return "\tVIDEO CHUNK - Stream " + this.streamNo + ",  chunkStart=" + this.getStartOfChunk() + ", " + (this.compressed ? "compressed" : "uncompressed") + ", ChunkSize=" + this.getChunkSize() + ", FrameNo=" + this.frameNo;
    }
}
