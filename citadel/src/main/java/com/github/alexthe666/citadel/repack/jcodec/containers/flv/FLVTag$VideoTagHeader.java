/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;

public static class FLVTag.VideoTagHeader
extends FLVTag.TagHeader {
    private int frameType;

    public FLVTag.VideoTagHeader(Codec codec, int frameType) {
        super(codec);
        this.frameType = frameType;
    }

    public int getFrameType() {
        return this.frameType;
    }
}
