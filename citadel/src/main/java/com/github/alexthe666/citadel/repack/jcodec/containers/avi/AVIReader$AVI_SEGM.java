/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVI_SEGM
extends AVIReader.AVIChunk {
    AVIReader.AVI_SEGM() {
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
    }

    @Override
    public int getChunkSize() {
        if (this.dwChunkSize == 0) {
            return 0;
        }
        return this.dwChunkSize + 1;
    }

    @Override
    public String toString() {
        return "SEGMENT Align, Size [" + this.dwChunkSize + "], StartOfChunk [" + this.getStartOfChunk() + "]";
    }
}
