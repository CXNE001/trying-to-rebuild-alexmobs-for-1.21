/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

private static class Transcoder.Mapping {
    private int source;
    private boolean copy;

    public Transcoder.Mapping(int source, boolean copy) {
        this.source = source;
        this.copy = copy;
    }

    static /* synthetic */ boolean access$000(Transcoder.Mapping x0) {
        return x0.copy;
    }

    static /* synthetic */ int access$100(Transcoder.Mapping x0) {
        return x0.source;
    }
}
