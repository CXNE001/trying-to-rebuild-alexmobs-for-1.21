/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.AVCVideoDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private int profileIdc;
    private int flags;
    private int level;

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        this.profileIdc = buf.get() & 0xFF;
        this.flags = buf.get() & 0xFF;
        this.level = buf.get() & 0xFF;
    }

    public int getProfileIdc() {
        return this.profileIdc;
    }

    public int getFlags() {
        return this.flags;
    }

    public int getLevel() {
        return this.level;
    }
}
