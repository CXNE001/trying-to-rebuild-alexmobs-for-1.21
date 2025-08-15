/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model;

public static class SEI.SEIMessage {
    public int payloadType;
    public int payloadSize;
    public byte[] payload;

    public SEI.SEIMessage(int payloadType2, int payloadSize2, byte[] payload2) {
        this.payload = payload2;
        this.payloadType = payloadType2;
        this.payloadSize = payloadSize2;
    }
}
