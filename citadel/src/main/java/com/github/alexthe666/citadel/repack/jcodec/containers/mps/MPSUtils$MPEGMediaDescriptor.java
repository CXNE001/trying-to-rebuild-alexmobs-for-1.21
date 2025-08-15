/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import java.nio.ByteBuffer;

public static class MPSUtils.MPEGMediaDescriptor {
    private int tag;
    private int len;

    public void parse(ByteBuffer buf) {
        this.tag = buf.get() & 0xFF;
        this.len = buf.get() & 0xFF;
    }

    public int getTag() {
        return this.tag;
    }

    public int getLen() {
        return this.len;
    }
}
