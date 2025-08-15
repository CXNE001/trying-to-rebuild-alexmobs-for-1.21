/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import com.github.alexthe666.citadel.repack.jcodec.audio.AudioFilter;
import java.nio.FloatBuffer;

public class LanczosInterpolator
implements AudioFilter {
    private double rateStep;

    public static double lanczos(double x, int a) {
        return x < (double)(-a) ? 0.0 : (x > (double)a ? 0.0 : (double)a * Math.sin(Math.PI * x) * Math.sin(Math.PI * x / (double)a) / (Math.PI * Math.PI * x * x));
    }

    public LanczosInterpolator(int fromRate, int toRate) {
        this.rateStep = (double)fromRate / (double)toRate;
    }

    @Override
    public void filter(FloatBuffer[] _in, long[] pos, FloatBuffer[] out) {
        int p0i;
        if (_in.length != 1) {
            throw new IllegalArgumentException(this.getClass().getName() + " filter is designed to work only on one input");
        }
        if (out.length != 1) {
            throw new IllegalArgumentException(this.getClass().getName() + " filter is designed to work only on one output");
        }
        FloatBuffer in0 = _in[0];
        FloatBuffer out0 = out[0];
        if ((double)out0.remaining() < (double)(in0.remaining() - 6) / this.rateStep) {
            throw new IllegalArgumentException("Output buffer is too small");
        }
        if (in0.remaining() <= 6) {
            throw new IllegalArgumentException("Input buffer should contain > 6 samples.");
        }
        int outSample = 0;
        while (true) {
            double inSample = 3.0 + (double)outSample * this.rateStep + Math.ceil((double)pos[0] / this.rateStep) * this.rateStep - (double)pos[0];
            p0i = (int)Math.floor(inSample);
            int q0i = (int)Math.ceil(inSample);
            if (p0i >= in0.limit() - 3) break;
            double p0d = (double)p0i - inSample;
            if (p0d < -0.001) {
                double q0d = (double)q0i - inSample;
                double p0c = LanczosInterpolator.lanczos(p0d, 3);
                double q0c = LanczosInterpolator.lanczos(q0d, 3);
                double p1c = LanczosInterpolator.lanczos(p0d - 1.0, 3);
                double q1c = LanczosInterpolator.lanczos(q0d + 1.0, 3);
                double p2c = LanczosInterpolator.lanczos(p0d - 2.0, 3);
                double q2c = LanczosInterpolator.lanczos(q0d + 2.0, 3);
                double factor = 1.0 / (p0c + p1c + p2c + q0c + q1c + q2c);
                out0.put((float)(((double)in0.get(q0i) * q0c + (double)in0.get(q0i + 1) * q1c + (double)in0.get(q0i + 2) * q2c + (double)in0.get(p0i) * p0c + (double)in0.get(p0i - 1) * p1c + (double)in0.get(p0i - 2) * p2c) * factor));
            } else {
                out0.put(in0.get(p0i));
            }
            ++outSample;
        }
        in0.position(p0i - 3);
    }

    @Override
    public int getDelay() {
        return 3;
    }

    @Override
    public int getNInputs() {
        return 1;
    }

    @Override
    public int getNOutputs() {
        return 1;
    }
}
