/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari;

import com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari.BitIO;
import java.io.IOException;
import java.io.InputStream;

public static class BitIO.StreamInputBits
implements BitIO.InputBits {
    private InputStream _in;
    private int cur;
    private int bit;

    public BitIO.StreamInputBits(InputStream _in) {
        this._in = _in;
        this.bit = 8;
    }

    @Override
    public int getBit() throws IOException {
        if (this.bit > 7) {
            this.cur = this._in.read();
            if (this.cur == -1) {
                return -1;
            }
            this.bit = 0;
        }
        return this.cur >> 7 - this.bit++ & 1;
    }
}
