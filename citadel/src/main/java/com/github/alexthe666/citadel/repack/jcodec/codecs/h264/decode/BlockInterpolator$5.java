/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.BlockInterpolator;

class BlockInterpolator.5
implements BlockInterpolator.LumaInterpolator {
    BlockInterpolator.5() {
    }

    @Override
    public void getLuma(byte[] pels, int picW, int imgH, byte[] blk, int blkOff, int blkStride, int x, int y, int blkW, int blkH) {
        BlockInterpolator.getLuma01(pels, picW, blk, blkOff, blkStride, x, y, blkW, blkH);
    }
}
