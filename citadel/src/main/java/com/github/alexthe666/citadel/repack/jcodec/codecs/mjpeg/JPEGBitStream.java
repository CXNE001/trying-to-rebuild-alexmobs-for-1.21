/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg;

import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLC;
import java.nio.ByteBuffer;

public class JPEGBitStream {
    private VLC[] huff;
    private BitReader _in;
    private int[] dcPredictor = new int[3];
    private int lumaLen;

    public JPEGBitStream(ByteBuffer b, VLC[] huff, int lumaLen) {
        this._in = BitReader.createBitReader(b);
        this.huff = huff;
        this.lumaLen = lumaLen;
    }

    public void readMCU(int[][] buf) {
        int blk = 0;
        int i = 0;
        while (i < this.lumaLen) {
            int n = this.readDCValue(this.dcPredictor[0], this.huff[0]);
            buf[blk][0] = n;
            this.dcPredictor[0] = n;
            this.readACValues(buf[blk], this.huff[2]);
            ++i;
            ++blk;
        }
        int n = this.readDCValue(this.dcPredictor[1], this.huff[1]);
        buf[blk][0] = n;
        this.dcPredictor[1] = n;
        this.readACValues(buf[blk], this.huff[3]);
        int n2 = this.readDCValue(this.dcPredictor[2], this.huff[1]);
        buf[++blk][0] = n2;
        this.dcPredictor[2] = n2;
        this.readACValues(buf[blk], this.huff[3]);
        ++blk;
    }

    public int readDCValue(int prevDC, VLC table) {
        int code = table.readVLC(this._in);
        return code != 0 ? this.toValue(this._in.readNBit(code), code) + prevDC : prevDC;
    }

    public void readACValues(int[] target, VLC table) {
        int code;
        int curOff = 1;
        do {
            if ((code = table.readVLC(this._in)) == 240) {
                curOff += 16;
                continue;
            }
            if (code <= 0) continue;
            int rle = code >> 4;
            int len = code & 0xF;
            target[curOff += rle] = this.toValue(this._in.readNBit(len), len);
            ++curOff;
        } while (code != 0 && curOff < 64);
    }

    public final int toValue(int raw, int length) {
        return length >= 1 && raw < 1 << length - 1 ? -(1 << length) + 1 + raw : raw;
    }
}
