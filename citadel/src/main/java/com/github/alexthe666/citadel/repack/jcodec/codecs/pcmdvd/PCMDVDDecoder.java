/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.pcmdvd;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.AudioBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PCMDVDDecoder
implements AudioDecoder {
    private static final int[] lpcm_freq_tab = new int[]{48000, 96000, 44100, 32000};

    @Override
    public AudioBuffer decodeFrame(ByteBuffer _frame, ByteBuffer _dst) throws IOException {
        ByteBuffer dst = _dst.duplicate();
        ByteBuffer frame = _frame.duplicate();
        frame.order(ByteOrder.BIG_ENDIAN);
        dst.order(ByteOrder.LITTLE_ENDIAN);
        int dvdaudioSubstreamType = frame.get() & 0xFF;
        NIOUtils.skip(frame, 3);
        if ((dvdaudioSubstreamType & 0xE0) != 160) {
            if ((dvdaudioSubstreamType & 0xE0) == 128) {
                if ((dvdaudioSubstreamType & 0xF8) == 136) {
                    throw new RuntimeException("CODEC_ID_DTS");
                }
                throw new RuntimeException("CODEC_ID_AC3");
            }
            throw new RuntimeException("MPEG DVD unknown coded");
        }
        int b0 = frame.get() & 0xFF;
        int b1 = frame.get() & 0xFF;
        int b2 = frame.get() & 0xFF;
        int freq = b1 >> 4 & 3;
        int sampleRate = lpcm_freq_tab[freq];
        int channelCount = 1 + (b1 & 7);
        int sampleSizeInBits = 16 + (b1 >> 6 & 3) * 4;
        int nFrames = frame.remaining() / (channelCount * (sampleSizeInBits >> 3));
        switch (sampleSizeInBits) {
            case 20: {
                for (int n = 0; n < nFrames >> 1; ++n) {
                    for (int c = 0; c < channelCount; ++c) {
                        short s0 = frame.getShort();
                        dst.putShort(s0);
                        short s1 = frame.getShort();
                        dst.putShort(s1);
                    }
                    NIOUtils.skip(frame, channelCount);
                }
                break;
            }
            case 24: {
                for (int n = 0; n < nFrames >> 1; ++n) {
                    for (int c = 0; c < channelCount; ++c) {
                        short s0 = frame.getShort();
                        dst.putShort(s0);
                        short s1 = frame.getShort();
                        dst.putShort(s1);
                    }
                    NIOUtils.skip(frame, channelCount << 1);
                }
                break;
            }
        }
        dst.flip();
        return new AudioBuffer(dst, new AudioFormat(sampleRate, sampleSizeInBits, channelCount, true, false), nFrames);
    }

    @Override
    public AudioCodecMeta getCodecMeta(ByteBuffer _frame) throws IOException {
        ByteBuffer frame = _frame.duplicate();
        frame.order(ByteOrder.BIG_ENDIAN);
        int dvdaudioSubstreamType = frame.get() & 0xFF;
        NIOUtils.skip(frame, 3);
        if ((dvdaudioSubstreamType & 0xE0) != 160) {
            if ((dvdaudioSubstreamType & 0xE0) == 128) {
                if ((dvdaudioSubstreamType & 0xF8) == 136) {
                    throw new RuntimeException("CODEC_ID_DTS");
                }
                throw new RuntimeException("CODEC_ID_AC3");
            }
            throw new RuntimeException("MPEG DVD unknown coded");
        }
        int b0 = frame.get() & 0xFF;
        int b1 = frame.get() & 0xFF;
        int b2 = frame.get() & 0xFF;
        int freq = b1 >> 4 & 3;
        int sampleRate = lpcm_freq_tab[freq];
        int channelCount = 1 + (b1 & 7);
        int sampleSizeInBits = 16 + (b1 >> 6 & 3) * 4;
        return AudioCodecMeta.fromAudioFormat(new AudioFormat(sampleRate, sampleSizeInBits, channelCount, true, false));
    }
}
