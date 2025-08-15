/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.DataStreamAlignmentDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private int alignmentType;

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        this.alignmentType = buf.get() & 0xFF;
    }

    public int getAlignmentType() {
        return this.alignmentType;
    }
}
