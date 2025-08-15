/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;

public static class FLVTag.AudioTagHeader
extends FLVTag.TagHeader {
    private AudioFormat audioFormat;

    public FLVTag.AudioTagHeader(Codec codec, AudioFormat audioFormat) {
        super(codec);
        this.audioFormat = audioFormat;
    }

    public AudioFormat getAudioFormat() {
        return this.audioFormat;
    }
}
