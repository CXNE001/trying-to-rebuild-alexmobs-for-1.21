/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;

public static class FLVTag.TagHeader {
    private Codec codec;

    public FLVTag.TagHeader(Codec codec) {
        this.codec = codec;
    }

    public Codec getCodec() {
        return this.codec;
    }
}
