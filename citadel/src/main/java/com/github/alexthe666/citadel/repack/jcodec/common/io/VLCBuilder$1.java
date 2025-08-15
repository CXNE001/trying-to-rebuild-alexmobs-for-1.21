/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.io;

import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLC;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLCBuilder;

class VLCBuilder.1
extends VLC {
    final /* synthetic */ VLCBuilder val$self;

    VLCBuilder.1(int[] codes, int[] codeSizes, VLCBuilder vLCBuilder) {
        this.val$self = vLCBuilder;
        super(codes, codeSizes);
    }

    @Override
    public int readVLC(BitReader _in) {
        return this.val$self.inverse.get(super.readVLC(_in));
    }

    @Override
    public int readVLC16(BitReader _in) {
        return this.val$self.inverse.get(super.readVLC16(_in));
    }

    @Override
    public void writeVLC(BitWriter out, int code) {
        super.writeVLC(out, this.val$self.forward.get(code));
    }
}
