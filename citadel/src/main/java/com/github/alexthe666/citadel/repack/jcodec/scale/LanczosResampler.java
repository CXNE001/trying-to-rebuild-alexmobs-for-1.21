/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.scale.BaseResampler;

public class LanczosResampler
extends BaseResampler {
    private static final int _nTaps = 6;
    private int precision = 256;
    private short[][] tapsXs;
    private short[][] tapsYs;
    private double _scaleFactorX;
    private double _scaleFactorY;

    public LanczosResampler(Size from, Size to) {
        super(from, to);
        this._scaleFactorX = (double)to.getWidth() / (double)from.getWidth();
        this._scaleFactorY = (double)to.getHeight() / (double)from.getHeight();
        this.tapsXs = new short[this.precision][6];
        this.tapsYs = new short[this.precision][6];
        LanczosResampler.buildTaps(6, this.precision, this._scaleFactorX, this.tapsXs);
        LanczosResampler.buildTaps(6, this.precision, this._scaleFactorY, this.tapsYs);
    }

    private static double sinc(double x) {
        return x == 0.0 ? 1.0 : Math.sin(x) / x;
    }

    private static void buildTaps(int nTaps, int precision, double scaleFactor, short[][] tapsOut) {
        double[] taps = new double[nTaps];
        for (int i = 0; i < precision; ++i) {
            double o = (double)i / (double)precision;
            int j = -nTaps / 2 + 1;
            int t = 0;
            while (j < nTaps / 2 + 1) {
                double x = -o + (double)j;
                double sinc_val = scaleFactor * LanczosResampler.sinc(scaleFactor * x * Math.PI);
                double wnd_val = Math.sin(x * Math.PI / (double)(nTaps - 1) + 1.5707963267948966);
                taps[t] = sinc_val * wnd_val;
                ++j;
                ++t;
            }
            LanczosResampler.normalizeAndGenerateFixedPrecision(taps, 7, tapsOut[i]);
        }
    }

    @Override
    protected short[] getTapsX(int dstX) {
        int oi = (int)((double)(dstX * this.precision) / this._scaleFactorX);
        int sub_pel = oi % this.precision;
        return this.tapsXs[sub_pel];
    }

    @Override
    protected short[] getTapsY(int dstY) {
        int oy = (int)((double)(dstY * this.precision) / this._scaleFactorY);
        int sub_pel = oy % this.precision;
        return this.tapsYs[sub_pel];
    }

    @Override
    protected int nTaps() {
        return 6;
    }
}
