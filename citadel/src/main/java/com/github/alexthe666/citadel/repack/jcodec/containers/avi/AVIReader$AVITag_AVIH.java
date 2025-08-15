/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_AVIH
extends AVIReader.AVIChunk {
    public String _getHeight;
    static final int AVIF_HASINDEX = 16;
    static final int AVIF_MUSTUSEINDEX = 32;
    static final int AVIF_ISINTERLEAVED = 256;
    static final int AVIF_TRUSTCKTYPE = 2048;
    static final int AVIF_WASCAPTUREFILE = 65536;
    static final int AVIF_COPYRIGHTED = 131072;
    private int dwMicroSecPerFrame;
    private int dwMaxBytesPerSec;
    private int dwPaddingGranularity;
    private int dwFlags;
    private int dwTotalFrames;
    private int dwInitialFrames;
    private int dwStreams;
    private int dwSuggestedBufferSize;
    private int dwWidth;
    private int dwHeight;
    private int[] dwReserved = new int[4];

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        if (dwFourCC != 1751742049) {
            throw new IOException("Unexpected AVI header : " + AVIReader.toFourCC(dwFourCC));
        }
        if (this.getChunkSize() != 56) {
            throw new IOException("Expected dwSize=56");
        }
        this.dwMicroSecPerFrame = raf.readInt();
        this.dwMaxBytesPerSec = raf.readInt();
        this.dwPaddingGranularity = raf.readInt();
        this.dwFlags = raf.readInt();
        this.dwTotalFrames = raf.readInt();
        this.dwInitialFrames = raf.readInt();
        this.dwStreams = raf.readInt();
        this.dwSuggestedBufferSize = raf.readInt();
        this.dwWidth = raf.readInt();
        this.dwHeight = raf.readInt();
        this.dwReserved[0] = raf.readInt();
        this.dwReserved[1] = raf.readInt();
        this.dwReserved[2] = raf.readInt();
        this.dwReserved[3] = raf.readInt();
    }

    public int getWidth() {
        return this.dwWidth;
    }

    public int getHeight() {
        return this.dwHeight;
    }

    public int getStreams() {
        return this.dwStreams;
    }

    public int getTotalFrames() {
        return this.dwTotalFrames;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if ((this.dwFlags & 0x10) != 0) {
            sb.append("HASINDEX ");
        }
        if ((this.dwFlags & 0x20) != 0) {
            sb.append("MUSTUSEINDEX ");
        }
        if ((this.dwFlags & 0x100) != 0) {
            sb.append("ISINTERLEAVED ");
        }
        if ((this.dwFlags & 0x10000) != 0) {
            sb.append("AVIF_WASCAPTUREFILE ");
        }
        if ((this.dwFlags & 0x20000) != 0) {
            sb.append("AVIF_COPYRIGHTED ");
        }
        return "AVIH Resolution [" + this.dwWidth + "x" + this.dwHeight + "], NumFrames [" + this.dwTotalFrames + "], Flags [" + Integer.toHexString(this.dwFlags) + "] - [" + sb.toString().trim() + "]";
    }
}
