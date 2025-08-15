/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;

public static class FLVTag.AacAudioTagHeader
extends FLVTag.AudioTagHeader {
    private int packetType;

    public FLVTag.AacAudioTagHeader(Codec codec, AudioFormat audioFormat, int packetType) {
        super(codec, audioFormat);
        this.packetType = packetType;
    }

    public int getPacketType() {
        return this.packetType;
    }
}
