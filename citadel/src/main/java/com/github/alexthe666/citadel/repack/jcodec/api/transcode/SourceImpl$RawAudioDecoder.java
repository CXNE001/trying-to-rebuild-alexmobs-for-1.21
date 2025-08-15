/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.model.AudioBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;

private static class SourceImpl.RawAudioDecoder
implements AudioDecoder {
    private AudioFormat format;

    public SourceImpl.RawAudioDecoder(AudioFormat format) {
        this.format = format;
    }

    @Override
    public AudioBuffer decodeFrame(ByteBuffer frame, ByteBuffer dst) throws IOException {
        return new AudioBuffer(frame, this.format, frame.remaining() / this.format.getFrameSize());
    }

    @Override
    public AudioCodecMeta getCodecMeta(ByteBuffer data) throws IOException {
        return AudioCodecMeta.fromAudioFormat(this.format);
    }
}
