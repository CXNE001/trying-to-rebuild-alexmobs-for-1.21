/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.ISO639LanguageDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private IntArrayList languageCodes = IntArrayList.createIntArrayList();

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        while (buf.remaining() >= 4) {
            this.languageCodes.add(buf.getInt());
        }
    }

    public IntArrayList getLanguageCodes() {
        return this.languageCodes;
    }
}
