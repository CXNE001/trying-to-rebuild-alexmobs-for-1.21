/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.prores;

public static class ProresConsts.FrameHeader {
    public int payloadSize;
    public int width;
    public int height;
    public int frameType;
    public boolean topFieldFirst;
    public int chromaType;
    public int[] scan;
    public int[] qMatLuma;
    public int[] qMatChroma;

    public ProresConsts.FrameHeader(int frameSize, int width, int height, int frameType, boolean topFieldFirst, int[] scan, int[] qMatLuma, int[] qMatChroma, int chromaType) {
        this.payloadSize = frameSize;
        this.width = width;
        this.height = height;
        this.frameType = frameType;
        this.topFieldFirst = topFieldFirst;
        this.scan = scan;
        this.qMatChroma = qMatChroma;
        this.qMatLuma = qMatLuma;
        this.chromaType = chromaType;
    }
}
