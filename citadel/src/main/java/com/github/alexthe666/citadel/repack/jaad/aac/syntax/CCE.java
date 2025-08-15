/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.DecoderConfig;
import com.github.alexthe666.citadel.repack.jaad.aac.huffman.Huffman;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.BitStream;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.Constants;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.Element;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.ICSInfo;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.ICStream;

class CCE
extends Element
implements Constants {
    public static final int BEFORE_TNS = 0;
    public static final int AFTER_TNS = 1;
    public static final int AFTER_IMDCT = 2;
    private static final float[] CCE_SCALE = new float[]{1.0905077f, 1.1892071f, 1.4142135f, 2.0f};
    private final ICStream ics;
    private int couplingPoint;
    private int coupledCount;
    private final boolean[] channelPair;
    private final int[] idSelect;
    private final int[] chSelect;
    private final float[][] gain;

    CCE(DecoderConfig config) {
        this.ics = new ICStream(config);
        this.channelPair = new boolean[8];
        this.idSelect = new int[8];
        this.chSelect = new int[8];
        this.gain = new float[16][120];
    }

    int getCouplingPoint() {
        return this.couplingPoint;
    }

    int getCoupledCount() {
        return this.coupledCount;
    }

    boolean isChannelPair(int index) {
        return this.channelPair[index];
    }

    int getIDSelect(int index) {
        return this.idSelect[index];
    }

    int getCHSelect(int index) {
        return this.chSelect[index];
    }

    void decode(BitStream in, DecoderConfig conf) throws AACException {
        int i;
        this.readElementInstanceTag(in);
        this.couplingPoint = 2 * in.readBit();
        this.coupledCount = in.readBits(3);
        int gainCount = 0;
        for (i = 0; i <= this.coupledCount; ++i) {
            ++gainCount;
            this.channelPair[i] = in.readBool();
            this.idSelect[i] = in.readBits(4);
            if (this.channelPair[i]) {
                this.chSelect[i] = in.readBits(2);
                if (this.chSelect[i] != 3) continue;
                ++gainCount;
                continue;
            }
            this.chSelect[i] = 2;
        }
        this.couplingPoint += in.readBit();
        this.couplingPoint |= this.couplingPoint >> 1;
        boolean sign = in.readBool();
        double scale = CCE_SCALE[in.readBits(2)];
        this.ics.decode(in, false, conf);
        ICSInfo info = this.ics.getInfo();
        int windowGroupCount = info.getWindowGroupCount();
        int maxSFB = info.getMaxSFB();
        int[] sfbCB = this.ics.getSfbCB();
        for (i = 0; i < gainCount; ++i) {
            int idx = 0;
            int cge = 1;
            int xg = 0;
            float gainCache = 1.0f;
            if (i > 0) {
                cge = this.couplingPoint == 2 ? 1 : in.readBit();
                xg = cge == 0 ? 0 : Huffman.decodeScaleFactor(in) - 60;
                gainCache = (float)Math.pow(scale, -xg);
            }
            if (this.couplingPoint == 2) {
                this.gain[i][0] = gainCache;
                continue;
            }
            for (int g = 0; g < windowGroupCount; ++g) {
                int sfb = 0;
                while (sfb < maxSFB) {
                    if (sfbCB[idx] != 0) {
                        int t;
                        if (cge == 0 && (t = Huffman.decodeScaleFactor(in) - 60) != 0) {
                            int s = 1;
                            xg += t;
                            t = xg;
                            if (!sign) {
                                s -= 2 * (t & 1);
                                t >>= 1;
                            }
                            gainCache = (float)(Math.pow(scale, -t) * (double)s);
                        }
                        this.gain[i][idx] = gainCache;
                    }
                    ++sfb;
                    ++idx;
                }
            }
        }
    }

    void process() {
    }

    void applyIndependentCoupling(int index, float[] data) {
        double g = this.gain[index][0];
        float[] iqData = this.ics.getInvQuantData();
        for (int i = 0; i < data.length; ++i) {
            int n = i;
            data[n] = (float)((double)data[n] + g * (double)iqData[i]);
        }
    }

    void applyDependentCoupling(int index, float[] data) {
        ICSInfo info = this.ics.getInfo();
        int[] swbOffsets = info.getSWBOffsets();
        int windowGroupCount = info.getWindowGroupCount();
        int maxSFB = info.getMaxSFB();
        int[] sfbCB = this.ics.getSfbCB();
        float[] iqData = this.ics.getInvQuantData();
        int srcOff = 0;
        int dstOff = 0;
        int idx = 0;
        for (int g = 0; g < windowGroupCount; ++g) {
            int len = info.getWindowGroupLength(g);
            int sfb = 0;
            while (sfb < maxSFB) {
                if (sfbCB[idx] != 0) {
                    float x = this.gain[index][idx];
                    for (int group = 0; group < len; ++group) {
                        for (int k = swbOffsets[sfb]; k < swbOffsets[sfb + 1]; ++k) {
                            int n = dstOff + group * 128 + k;
                            data[n] = data[n] + x * iqData[srcOff + group * 128 + k];
                        }
                    }
                }
                ++sfb;
                ++idx;
            }
            dstOff += len * 128;
            srcOff += len * 128;
        }
    }
}
