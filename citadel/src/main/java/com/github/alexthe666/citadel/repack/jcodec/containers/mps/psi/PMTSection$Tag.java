/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi;

import java.nio.ByteBuffer;

public static class PMTSection.Tag {
    private int tag;
    private ByteBuffer content;

    public PMTSection.Tag(int tag, ByteBuffer content) {
        this.tag = tag;
        this.content = content;
    }

    public int getTag() {
        return this.tag;
    }

    public ByteBuffer getContent() {
        return this.content;
    }
}
