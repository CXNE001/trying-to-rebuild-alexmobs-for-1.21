/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.Profile;
import com.github.alexthe666.citadel.repack.jaad.aac.SampleFrequency;
import com.github.alexthe666.citadel.repack.jaad.aac.filterbank.FilterBank;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.IBitStream;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.ICSInfo;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.ICStream;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.SyntaxConstants;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;

public static class ICSInfo.LTPrediction
implements SyntaxConstants {
    private static final float[] CODEBOOK = new float[]{0.570829f, 0.696616f, 0.813004f, 0.911304f, 0.9849f, 1.067894f, 1.194601f, 1.369533f};
    private final int frameLength;
    private final int[] states;
    private int coef;
    private int lag;
    private int lastBand;
    private boolean lagUpdate;
    private boolean[] shortUsed;
    private boolean[] shortLagPresent;
    private boolean[] longUsed;
    private int[] shortLag;

    public ICSInfo.LTPrediction(int frameLength) {
        this.frameLength = frameLength;
        this.states = new int[4 * frameLength];
    }

    public void decode(IBitStream _in, ICSInfo info, Profile profile) throws AACException {
        this.lag = 0;
        if (((Object)((Object)profile)).equals((Object)Profile.AAC_LD)) {
            this.lagUpdate = _in.readBool();
            if (this.lagUpdate) {
                this.lag = _in.readBits(10);
            }
        } else {
            this.lag = _in.readBits(11);
        }
        if (this.lag > this.frameLength << 1) {
            throw new AACException("LTP lag too large: " + this.lag);
        }
        this.coef = _in.readBits(3);
        int windowCount = info.getWindowCount();
        if (info.isEightShortFrame()) {
            this.shortUsed = new boolean[windowCount];
            this.shortLagPresent = new boolean[windowCount];
            this.shortLag = new int[windowCount];
            for (int w = 0; w < windowCount; ++w) {
                this.shortUsed[w] = _in.readBool();
                if (!this.shortUsed[w]) continue;
                this.shortLagPresent[w] = _in.readBool();
                if (!this.shortLagPresent[w]) continue;
                this.shortLag[w] = _in.readBits(4);
            }
        } else {
            this.lastBand = Math.min(info.getMaxSFB(), 40);
            this.longUsed = new boolean[this.lastBand];
            for (int i = 0; i < this.lastBand; ++i) {
                this.longUsed[i] = _in.readBool();
            }
        }
    }

    public void setPredictionUnused(int sfb) {
        if (this.longUsed != null) {
            this.longUsed[sfb] = false;
        }
    }

    public void process(ICStream ics, float[] data, FilterBank filterBank, SampleFrequency sf) {
        ICSInfo info = ics.getInfo();
        if (!info.isEightShortFrame()) {
            int samples = this.frameLength << 1;
            float[] _in = new float[2048];
            float[] out = new float[2048];
            for (int i = 0; i < samples; ++i) {
                _in[i] = (float)this.states[samples + i - this.lag] * CODEBOOK[this.coef];
            }
            filterBank.processLTP(info.getWindowSequence(), info.getWindowShape(1), info.getWindowShape(0), _in, out);
            if (ics.isTNSDataPresent()) {
                ics.getTNS().process(ics, out, sf, true);
            }
            int[] swbOffsets = info.getSWBOffsets();
            int swbOffsetMax = info.getSWBOffsetMax();
            for (int sfb = 0; sfb < this.lastBand; ++sfb) {
                if (!this.longUsed[sfb]) continue;
                int low = swbOffsets[sfb];
                int high = Math.min(swbOffsets[sfb + 1], swbOffsetMax);
                for (int bin = low; bin < high; ++bin) {
                    int n = bin;
                    data[n] = data[n] + out[bin];
                }
            }
        }
    }

    public void updateState(float[] time, float[] overlap, Profile profile) {
        if (((Object)((Object)profile)).equals((Object)Profile.AAC_LD)) {
            for (int i = 0; i < this.frameLength; ++i) {
                this.states[i] = this.states[i + this.frameLength];
                this.states[this.frameLength + i] = this.states[i + this.frameLength * 2];
                this.states[this.frameLength * 2 + i] = Math.round(time[i]);
                this.states[this.frameLength * 3 + i] = Math.round(overlap[i]);
            }
        } else {
            for (int i = 0; i < this.frameLength; ++i) {
                this.states[i] = this.states[i + this.frameLength];
                this.states[this.frameLength + i] = Math.round(time[i]);
                this.states[this.frameLength * 2 + i] = Math.round(overlap[i]);
            }
        }
    }

    public static boolean isLTPProfile(Profile profile) {
        return ((Object)((Object)profile)).equals((Object)Profile.AAC_LTP) || ((Object)((Object)profile)).equals((Object)Profile.ER_AAC_LTP) || ((Object)((Object)profile)).equals((Object)Profile.AAC_LD);
    }

    public void copy(ICSInfo.LTPrediction ltp) {
        System.arraycopy(ltp.states, 0, this.states, 0, this.states.length);
        this.coef = ltp.coef;
        this.lag = ltp.lag;
        this.lastBand = ltp.lastBand;
        this.lagUpdate = ltp.lagUpdate;
        this.shortUsed = Platform.copyOfBool(ltp.shortUsed, ltp.shortUsed.length);
        this.shortLagPresent = Platform.copyOfBool(ltp.shortLagPresent, ltp.shortLagPresent.length);
        this.shortLag = Platform.copyOfInt(ltp.shortLag, ltp.shortLag.length);
        this.longUsed = Platform.copyOfBool(ltp.longUsed, ltp.longUsed.length);
    }
}
