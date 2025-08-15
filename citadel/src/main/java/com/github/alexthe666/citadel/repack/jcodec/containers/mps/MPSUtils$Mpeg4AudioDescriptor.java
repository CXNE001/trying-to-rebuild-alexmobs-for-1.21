/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.Mpeg4AudioDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private int profileLevel;

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        this.profileLevel = buf.get() & 0xFF;
    }

    public int getProfileLevel() {
        return this.profileLevel;
    }
}
