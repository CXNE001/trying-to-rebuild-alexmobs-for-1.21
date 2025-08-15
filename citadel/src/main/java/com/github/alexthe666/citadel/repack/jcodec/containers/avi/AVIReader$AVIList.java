/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVIList
extends AVIReader.AVIChunk {
    protected int dwListTypeFourCC;
    protected String dwListTypeFourCCStr;

    AVIReader.AVIList() {
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        this.dwChunkSize -= 4;
        this.dwListTypeFourCC = raf.readInt();
        this.dwListTypeFourCCStr = AVIReader.toFourCC(this.dwListTypeFourCC);
    }

    public int getListType() {
        return this.dwListTypeFourCC;
    }

    @Override
    public String toString() {
        String dwFourCCStr = AVIReader.toFourCC(this.dwFourCC);
        return dwFourCCStr + " [" + this.dwListTypeFourCCStr + "], Size [" + this.dwChunkSize + "], StartOfChunk [" + this.getStartOfChunk() + "]";
    }
}
