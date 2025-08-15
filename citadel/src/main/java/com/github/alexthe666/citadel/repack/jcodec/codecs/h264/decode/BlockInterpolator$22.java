/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.BlockInterpolator;

class BlockInterpolator.22
implements BlockInterpolator.LumaInterpolator {
    final /* synthetic */ BlockInterpolator val$self;

    BlockInterpolator.22(BlockInterpolator blockInterpolator) {
        this.val$self = blockInterpolator;
    }

    @Override
    public void getLuma(byte[] pels, int picW, int imgH, byte[] blk, int blkOff, int blkStride, int x, int y, int blkW, int blkH) {
        this.val$self.getLuma11Unsafe(pels, picW, imgH, blk, blkOff, blkStride, x, y, blkW, blkH);
    }
}
