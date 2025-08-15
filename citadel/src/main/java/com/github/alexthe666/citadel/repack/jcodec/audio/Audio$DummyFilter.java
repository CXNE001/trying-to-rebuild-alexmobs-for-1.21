/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import com.github.alexthe666.citadel.repack.jcodec.audio.AudioFilter;
import java.nio.FloatBuffer;

public static class Audio.DummyFilter
implements AudioFilter {
    private int nInputs;

    public Audio.DummyFilter(int nInputs) {
        this.nInputs = nInputs;
    }

    @Override
    public void filter(FloatBuffer[] _in, long[] inPos, FloatBuffer[] out) {
        for (int i = 0; i < _in.length; ++i) {
            if (out[i].remaining() >= _in[i].remaining()) {
                out[i].put(_in[i]);
                continue;
            }
            FloatBuffer duplicate = _in[i].duplicate();
            duplicate.limit(_in[i].position() + out[i].remaining());
            out[i].put(duplicate);
        }
    }

    @Override
    public int getDelay() {
        return 0;
    }

    @Override
    public int getNInputs() {
        return this.nInputs;
    }

    @Override
    public int getNOutputs() {
        return this.nInputs;
    }
}
