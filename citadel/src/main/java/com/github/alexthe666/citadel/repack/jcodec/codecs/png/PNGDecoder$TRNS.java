/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.png;

import java.nio.ByteBuffer;

public static class PNGDecoder.TRNS {
    private int colorType;
    byte[] alphaPal;
    byte alphaGrey;
    byte alphaR;
    byte alphaG;
    byte alphaB;

    PNGDecoder.TRNS(byte colorType) {
        this.colorType = colorType;
    }

    public void parse(ByteBuffer data, int length) {
        if (this.colorType == 3) {
            this.alphaPal = new byte[256];
            data.get(this.alphaPal, 0, length);
            for (int i = length; i < 256; ++i) {
                this.alphaPal[i] = -1;
            }
        } else if (this.colorType == 0) {
            this.alphaGrey = data.get();
        } else if (this.colorType == 2) {
            this.alphaR = data.get();
            this.alphaG = data.get();
            this.alphaG = data.get();
        }
        data.getInt();
    }
}
