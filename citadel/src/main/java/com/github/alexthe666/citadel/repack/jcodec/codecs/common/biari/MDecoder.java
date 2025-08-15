/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari;

import com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari.MConst;
import java.nio.ByteBuffer;

public class MDecoder {
    private ByteBuffer _in;
    private int range;
    private int code;
    private int nBitsPending;
    private int[][] cm;

    public MDecoder(ByteBuffer _in, int[][] cm) {
        this._in = _in;
        this.range = 510;
        this.cm = cm;
        this.initCodeRegister();
    }

    protected void initCodeRegister() {
        this.readOneByte();
        if (this.nBitsPending != 8) {
            throw new RuntimeException("Empty stream");
        }
        this.code <<= 8;
        this.readOneByte();
        this.code <<= 1;
        this.nBitsPending -= 9;
    }

    protected void readOneByte() {
        if (!this._in.hasRemaining()) {
            return;
        }
        int b = this._in.get() & 0xFF;
        this.code |= b;
        this.nBitsPending += 8;
    }

    public int decodeBin(int m) {
        int bin;
        int qIdx = this.range >> 6 & 3;
        int rLPS = MConst.rangeLPS[qIdx][this.cm[0][m]];
        this.range -= rLPS;
        int rs8 = this.range << 8;
        if (this.code < rs8) {
            if (this.cm[0][m] < 62) {
                int[] nArray = this.cm[0];
                int n = m;
                nArray[n] = nArray[n] + 1;
            }
            this.renormalize();
            bin = this.cm[1][m];
        } else {
            this.range = rLPS;
            this.code -= rs8;
            this.renormalize();
            bin = 1 - this.cm[1][m];
            if (this.cm[0][m] == 0) {
                this.cm[1][m] = 1 - this.cm[1][m];
            }
            this.cm[0][m] = MConst.transitLPS[this.cm[0][m]];
        }
        return bin;
    }

    public int decodeFinalBin() {
        this.range -= 2;
        if (this.code < this.range << 8) {
            this.renormalize();
            return 0;
        }
        return 1;
    }

    public int decodeBinBypass() {
        int tmp;
        this.code <<= 1;
        --this.nBitsPending;
        if (this.nBitsPending <= 0) {
            this.readOneByte();
        }
        if ((tmp = this.code - (this.range << 8)) < 0) {
            return 0;
        }
        this.code = tmp;
        return 1;
    }

    private void renormalize() {
        while (this.range < 256) {
            this.range <<= 1;
            this.code <<= 1;
            this.code &= 0x1FFFF;
            --this.nBitsPending;
            if (this.nBitsPending > 0) continue;
            this.readOneByte();
        }
    }
}
