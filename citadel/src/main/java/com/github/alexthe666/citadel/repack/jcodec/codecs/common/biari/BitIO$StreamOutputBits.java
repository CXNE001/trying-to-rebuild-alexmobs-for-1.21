/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari;

import com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari.BitIO;
import java.io.IOException;
import java.io.OutputStream;

public static class BitIO.StreamOutputBits
implements BitIO.OutputBits {
    private OutputStream out;
    private int cur;
    private int bit;

    public BitIO.StreamOutputBits(OutputStream out) {
        this.out = out;
    }

    @Override
    public void putBit(int symbol) throws IOException {
        if (this.bit > 7) {
            this.out.write(this.cur);
            this.cur = 0;
            this.bit = 0;
        }
        this.cur |= (symbol & 1) << 7 - this.bit++;
    }

    @Override
    public void flush() throws IOException {
        if (this.bit > 0) {
            this.out.write(this.cur);
        }
    }
}
