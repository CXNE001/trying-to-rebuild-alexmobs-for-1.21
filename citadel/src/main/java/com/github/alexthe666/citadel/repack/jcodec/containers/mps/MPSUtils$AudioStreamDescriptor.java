/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.AudioStreamDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private int variableRateAudioIndicator;
    private int freeFormatFlag;
    private int id;
    private int layer;

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        int b0 = buf.get() & 0xFF;
        this.freeFormatFlag = b0 >> 7 & 1;
        this.id = b0 >> 6 & 1;
        this.layer = b0 >> 5 & 3;
        this.variableRateAudioIndicator = b0 >> 3 & 1;
    }

    public int getVariableRateAudioIndicator() {
        return this.variableRateAudioIndicator;
    }

    public int getFreeFormatFlag() {
        return this.freeFormatFlag;
    }

    public int getId() {
        return this.id;
    }

    public int getLayer() {
        return this.layer;
    }
}
