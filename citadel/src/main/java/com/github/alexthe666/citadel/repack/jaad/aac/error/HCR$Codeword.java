/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.error;

import com.github.alexthe666.citadel.repack.jaad.aac.error.BitsBuffer;

private static class HCR.Codeword {
    int cb;
    int decoded;
    int sp_offset;
    BitsBuffer bits;

    private HCR.Codeword() {
    }

    private void fill(int sp, int cb) {
        this.sp_offset = sp;
        this.cb = cb;
        this.decoded = 0;
        this.bits = new BitsBuffer();
    }
}
