/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import com.github.alexthe666.citadel.repack.jcodec.audio.AudioSource;
import com.github.alexthe666.citadel.repack.jcodec.codecs.wav.WavInput;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioUtil;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public static class WavInput.Source
implements AudioSource,
Closeable {
    private WavInput src;
    private AudioFormat format;
    private int pos;

    public WavInput.Source(WavInput src) {
        this.src = src;
        this.format = src.getFormat();
    }

    @Override
    public AudioFormat getFormat() {
        return this.src.getFormat();
    }

    @Override
    public void close() throws IOException {
        this.src.close();
    }

    public int read(int[] samples, int max) throws IOException {
        max = Math.min(max, samples.length);
        ByteBuffer bb = ByteBuffer.allocate(this.format.samplesToBytes(max));
        int read = this.src.read(bb);
        bb.flip();
        AudioUtil.toInt(this.format, bb, samples);
        return this.format.bytesToFrames(read);
    }

    @Override
    public int readFloat(FloatBuffer samples) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(this.format.samplesToBytes(samples.remaining()));
        int i = this.src.read(bb);
        if (i == -1) {
            return -1;
        }
        bb.flip();
        AudioUtil.toFloat(this.format, bb, samples);
        int read = this.format.bytesToFrames(i);
        this.pos += read;
        return read;
    }
}
