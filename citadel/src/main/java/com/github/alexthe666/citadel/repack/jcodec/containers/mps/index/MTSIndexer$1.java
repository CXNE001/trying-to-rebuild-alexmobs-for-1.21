/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;

static final class MTSIndexer.1
implements NIOUtils.FileReaderListener {
    MTSIndexer.1() {
    }

    @Override
    public void progress(int percentDone) {
        System.out.println(percentDone);
    }
}
