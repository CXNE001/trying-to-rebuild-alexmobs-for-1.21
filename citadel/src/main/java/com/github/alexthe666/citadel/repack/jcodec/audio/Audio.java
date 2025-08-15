/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import com.github.alexthe666.citadel.repack.jcodec.audio.AudioFilter;
import com.github.alexthe666.citadel.repack.jcodec.audio.AudioSink;
import com.github.alexthe666.citadel.repack.jcodec.audio.AudioSource;
import java.io.IOException;
import java.nio.FloatBuffer;

public class Audio {
    public static void transfer(AudioSource src, AudioSink sink) throws IOException {
        Audio.filterTransfer(src, new DummyFilter(1), sink);
    }

    public static void filterTransfer(AudioSource src, AudioFilter filter, AudioSink sink) throws IOException {
        if (filter.getNInputs() != 1) {
            throw new IllegalArgumentException("Audio filter has # inputs != 1");
        }
        if (filter.getNOutputs() != 1) {
            throw new IllegalArgumentException("Audio filter has # outputs != 1");
        }
        if (filter.getDelay() != 0) {
            throw new IllegalArgumentException("Audio filter has delay");
        }
        FloatBuffer[] ins = new FloatBuffer[]{FloatBuffer.allocate(4096)};
        FloatBuffer[] outs = new FloatBuffer[]{FloatBuffer.allocate(8192)};
        long[] pos = new long[1];
        while (src.readFloat(ins[0]) != -1) {
            ins[0].flip();
            filter.filter(ins, pos, outs);
            pos[0] = pos[0] + (long)ins[0].position();
            Audio.rotate(ins[0]);
            outs[0].flip();
            sink.writeFloat(outs[0]);
            outs[0].clear();
        }
    }

    public static void print(FloatBuffer buf) {
        FloatBuffer dup = buf.duplicate();
        while (dup.hasRemaining()) {
            System.out.print(String.format("%.3f,", Float.valueOf(dup.get())));
        }
        System.out.println();
    }

    public static void rotate(FloatBuffer buf) {
        int pos = 0;
        while (buf.hasRemaining()) {
            buf.put(pos, buf.get());
            ++pos;
        }
        buf.position(pos);
        buf.limit(buf.capacity());
    }

    public static class DummyFilter
    implements AudioFilter {
        private int nInputs;

        public DummyFilter(int nInputs) {
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
}
