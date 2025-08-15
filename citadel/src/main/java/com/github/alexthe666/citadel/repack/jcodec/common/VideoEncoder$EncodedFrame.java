/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import java.nio.ByteBuffer;

public static class VideoEncoder.EncodedFrame {
    private ByteBuffer data;
    private boolean keyFrame;

    public VideoEncoder.EncodedFrame(ByteBuffer data, boolean keyFrame) {
        this.data = data;
        this.keyFrame = keyFrame;
    }

    public ByteBuffer getData() {
        return this.data;
    }

    public boolean isKeyFrame() {
        return this.keyFrame;
    }
}
