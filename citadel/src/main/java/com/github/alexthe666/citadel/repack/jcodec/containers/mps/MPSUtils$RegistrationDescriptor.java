/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.RegistrationDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private int formatIdentifier;
    private IntArrayList additionalFormatIdentifiers = IntArrayList.createIntArrayList();

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        this.formatIdentifier = buf.getInt();
        while (buf.hasRemaining()) {
            this.additionalFormatIdentifiers.add(buf.get() & 0xFF);
        }
    }

    public int getFormatIdentifier() {
        return this.formatIdentifier;
    }

    public IntArrayList getAdditionalFormatIdentifiers() {
        return this.additionalFormatIdentifiers;
    }
}
