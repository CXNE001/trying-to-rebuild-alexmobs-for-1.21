/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import com.github.alexthe666.citadel.repack.jcodec.audio.AudioSink;
import com.github.alexthe666.citadel.repack.jcodec.codecs.wav.WavOutput;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioUtil;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public static class WavOutput.Sink
implements AudioSink,
Closeable {
    private WavOutput out;

    public WavOutput.Sink(WavOutput out) {
        this.out = out;
    }

    @Override
    public void writeFloat(FloatBuffer data) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(this.out.format.samplesToBytes(data.remaining()));
        AudioUtil.fromFloat(data, this.out.format, buf);
        buf.flip();
        this.out.write(buf);
    }

    public void write(int[] data, int len) throws IOException {
        len = Math.min(data.length, len);
        ByteBuffer buf = ByteBuffer.allocate(this.out.format.samplesToBytes(len));
        AudioUtil.fromInt(data, len, this.out.format, buf);
        buf.flip();
        this.out.write(buf);
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }
}
