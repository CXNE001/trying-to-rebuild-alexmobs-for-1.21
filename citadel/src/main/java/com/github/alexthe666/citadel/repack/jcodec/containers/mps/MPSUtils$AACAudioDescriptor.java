/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.AACAudioDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private int profile;
    private int channel;
    private int flags;

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        this.profile = buf.get() & 0xFF;
        this.channel = buf.get() & 0xFF;
        this.flags = buf.get() & 0xFF;
    }

    public int getProfile() {
        return this.profile;
    }

    public int getChannel() {
        return this.channel;
    }

    public int getFlags() {
        return this.flags;
    }
}
