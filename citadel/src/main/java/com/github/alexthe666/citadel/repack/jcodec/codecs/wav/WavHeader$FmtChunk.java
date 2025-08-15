/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public static class WavHeader.FmtChunk {
    public short audioFormat;
    public short numChannels;
    public int sampleRate;
    public int byteRate;
    public short blockAlign;
    public short bitsPerSample;

    public WavHeader.FmtChunk(short audioFormat, short numChannels, int sampleRate, int byteRate, short blockAlign, short bitsPerSample) {
        this.audioFormat = audioFormat;
        this.numChannels = numChannels;
        this.sampleRate = sampleRate;
        this.byteRate = byteRate;
        this.blockAlign = blockAlign;
        this.bitsPerSample = bitsPerSample;
    }

    public static WavHeader.FmtChunk get(ByteBuffer bb) throws IOException {
        ByteOrder old = bb.order();
        try {
            bb.order(ByteOrder.LITTLE_ENDIAN);
            WavHeader.FmtChunk fmtChunk = new WavHeader.FmtChunk(bb.getShort(), bb.getShort(), bb.getInt(), bb.getInt(), bb.getShort(), bb.getShort());
            return fmtChunk;
        }
        finally {
            bb.order(old);
        }
    }

    public void put(ByteBuffer bb) throws IOException {
        ByteOrder old = bb.order();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putShort(this.audioFormat);
        bb.putShort(this.numChannels);
        bb.putInt(this.sampleRate);
        bb.putInt(this.byteRate);
        bb.putShort(this.blockAlign);
        bb.putShort(this.bitsPerSample);
        bb.order(old);
    }

    public int size() {
        return 16;
    }
}
