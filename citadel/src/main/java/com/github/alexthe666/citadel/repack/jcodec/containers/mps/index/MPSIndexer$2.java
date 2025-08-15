/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;

static final class MPSIndexer.2
implements NIOUtils.FileReaderListener {
    MPSIndexer.2() {
    }

    @Override
    public void progress(int percentDone) {
        System.out.println(percentDone);
    }
}
