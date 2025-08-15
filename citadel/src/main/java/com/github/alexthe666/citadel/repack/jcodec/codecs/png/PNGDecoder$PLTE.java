/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.png;

import java.nio.ByteBuffer;

private static class PNGDecoder.PLTE {
    int[] palette;

    private PNGDecoder.PLTE() {
    }

    public void parse(ByteBuffer data, int length) {
        int i;
        if (length % 3 != 0 || length > 768) {
            throw new RuntimeException("Invalid data");
        }
        int n = length / 3;
        this.palette = new int[n];
        for (i = 0; i < n; ++i) {
            this.palette[i] = 0xFF000000 | (data.get() & 0xFF) << 16 | (data.get() & 0xFF) << 8 | data.get() & 0xFF;
        }
        while (i < 256) {
            this.palette[i] = -16777216;
            ++i;
        }
        data.getInt();
    }
}
