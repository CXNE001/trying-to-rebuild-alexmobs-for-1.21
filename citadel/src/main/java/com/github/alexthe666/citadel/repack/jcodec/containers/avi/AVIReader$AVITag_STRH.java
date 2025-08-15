/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_STRH
extends AVIReader.AVIChunk {
    static final int AVISF_DISABLED = 1;
    static final int AVISF_VIDEO_PALCHANGES = 65536;
    private int fccType;
    private int fccCodecHandler;
    private int dwFlags = 0;
    private short wPriority = 0;
    private short wLanguage = 0;
    private int dwInitialFrames = 0;
    private int dwScale = 0;
    private int dwRate = 1000000;
    private int dwStart = 0;
    private int dwLength = 0;
    private int dwSuggestedBufferSize = 0;
    private int dwQuality = -1;
    private int dwSampleSize = 0;
    private short left = 0;
    private short top = 0;
    private short right = 0;
    private short bottom = 0;

    AVIReader.AVITag_STRH() {
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        if (dwFourCC != 1752331379) {
            throw new IOException("Expected 'strh' fourcc got [" + AVIReader.toFourCC(this.dwFourCC) + "]");
        }
        this.fccType = raf.readInt();
        this.fccCodecHandler = raf.readInt();
        this.dwFlags = raf.readInt();
        this.wPriority = raf.readShort();
        this.wLanguage = raf.readShort();
        this.dwInitialFrames = raf.readInt();
        this.dwScale = raf.readInt();
        this.dwRate = raf.readInt();
        this.dwStart = raf.readInt();
        this.dwLength = raf.readInt();
        this.dwSuggestedBufferSize = raf.readInt();
        this.dwQuality = raf.readInt();
        this.dwSampleSize = raf.readInt();
        this.left = raf.readShort();
        this.top = raf.readShort();
        this.right = raf.readShort();
        this.bottom = raf.readShort();
    }

    public int getType() {
        return this.fccType;
    }

    public int getHandler() {
        return this.fccCodecHandler;
    }

    public String getHandlerStr() {
        if (this.fccCodecHandler != 0) {
            return AVIReader.toFourCC(this.fccCodecHandler);
        }
        return "";
    }

    public int getInitialFrames() {
        return this.dwInitialFrames;
    }

    @Override
    public String toString() {
        return "\tCHUNK [" + AVIReader.toFourCC(this.dwFourCC) + "], Type[" + (this.fccType > 0 ? AVIReader.toFourCC(this.fccType) : "    ") + "], Handler [" + (this.fccCodecHandler > 0 ? AVIReader.toFourCC(this.fccCodecHandler) : "    ") + "]";
    }
}
