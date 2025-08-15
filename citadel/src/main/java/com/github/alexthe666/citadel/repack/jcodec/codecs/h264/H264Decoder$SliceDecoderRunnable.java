/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Decoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.SliceDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.SliceReader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.Frame;

private static final class H264Decoder.SliceDecoderRunnable
implements Runnable {
    private final SliceReader sliceReader;
    private final Frame result;
    private H264Decoder.FrameDecoder fdec;

    private H264Decoder.SliceDecoderRunnable(H264Decoder.FrameDecoder fdec, SliceReader sliceReader, Frame result) {
        this.fdec = fdec;
        this.sliceReader = sliceReader;
        this.result = result;
    }

    @Override
    public void run() {
        new SliceDecoder(this.fdec.activeSps, this.fdec.dec.sRefs, this.fdec.dec.lRefs, this.fdec.di, this.result).decodeFromReader(this.sliceReader);
    }
}
