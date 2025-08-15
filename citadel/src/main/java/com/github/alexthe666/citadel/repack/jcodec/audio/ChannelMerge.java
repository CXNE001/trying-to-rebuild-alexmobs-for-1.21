/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import com.github.alexthe666.citadel.repack.jcodec.audio.AudioFilter;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import java.nio.FloatBuffer;

public class ChannelMerge
implements AudioFilter {
    private AudioFormat format;

    public ChannelMerge(AudioFormat format) {
        this.format = format;
    }

    @Override
    public void filter(FloatBuffer[] _in, long[] inPos, FloatBuffer[] out) {
        int i;
        if (_in.length != this.format.getChannels()) {
            throw new IllegalArgumentException("Channel merge must be supplied with " + this.format.getChannels() + " input buffers to hold the channels.");
        }
        if (out.length != 1) {
            throw new IllegalArgumentException("Channel merget invoked on more then one output");
        }
        FloatBuffer out0 = out[0];
        int min = Integer.MAX_VALUE;
        for (i = 0; i < _in.length; ++i) {
            if (_in[i].remaining() >= min) continue;
            min = _in[i].remaining();
        }
        for (i = 0; i < _in.length; ++i) {
            Preconditions.checkState(_in[i].remaining() == min);
        }
        if (out0.remaining() < min * _in.length) {
            throw new IllegalArgumentException("Supplied output buffer is not big enough to hold " + min + " * " + _in.length + " = " + min * _in.length + " output samples.");
        }
        for (i = 0; i < min; ++i) {
            for (int j = 0; j < _in.length; ++j) {
                out0.put(_in[j].get());
            }
        }
    }

    @Override
    public int getDelay() {
        return 0;
    }

    @Override
    public int getNInputs() {
        return this.format.getChannels();
    }

    @Override
    public int getNOutputs() {
        return 1;
    }
}
