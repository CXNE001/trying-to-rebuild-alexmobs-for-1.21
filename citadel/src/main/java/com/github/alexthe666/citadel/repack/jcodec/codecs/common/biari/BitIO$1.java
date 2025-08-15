/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari;

import com.github.alexthe666.citadel.repack.jcodec.platform.BaseOutputStream;
import java.io.IOException;

static final class BitIO.1
extends BaseOutputStream {
    int ptr;
    final /* synthetic */ byte[] val$bytes;

    BitIO.1(byte[] byArray) {
        this.val$bytes = byArray;
    }

    @Override
    protected void writeByte(int b) throws IOException {
        if (this.ptr >= this.val$bytes.length) {
            throw new IOException("Buffer is full");
        }
        this.val$bytes[this.ptr++] = (byte)b;
    }
}
