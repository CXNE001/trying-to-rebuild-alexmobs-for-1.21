/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;

public static class FLVTag.AvcVideoTagHeader
extends FLVTag.VideoTagHeader {
    private int compOffset;
    private byte avcPacketType;

    public FLVTag.AvcVideoTagHeader(Codec codec, int frameType, byte avcPacketType, int compOffset) {
        super(codec, frameType);
        this.avcPacketType = avcPacketType;
        this.compOffset = compOffset;
    }

    public int getCompOffset() {
        return this.compOffset;
    }

    public byte getAvcPacketType() {
        return this.avcPacketType;
    }
}
