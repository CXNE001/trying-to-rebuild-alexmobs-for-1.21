/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode;

import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import java.util.Arrays;

static class MBlock.IPCM {
    public int[] samplesLuma = new int[256];
    public int[] samplesChroma;

    public MBlock.IPCM(ColorSpace chromaFormat) {
        int MbWidthC = 16 >> chromaFormat.compWidth[1];
        int MbHeightC = 16 >> chromaFormat.compHeight[1];
        this.samplesChroma = new int[2 * MbWidthC * MbHeightC];
    }

    public void clean() {
        Arrays.fill(this.samplesLuma, 0);
        Arrays.fill(this.samplesChroma, 0);
    }
}
