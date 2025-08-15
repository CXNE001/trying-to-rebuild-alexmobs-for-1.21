/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.MPSIndexer;
import java.nio.ByteBuffer;

class MPSIndexer.1
extends NIOUtils.FileReader {
    final /* synthetic */ MPSIndexer val$self;

    MPSIndexer.1(MPSIndexer mPSIndexer) {
        this.val$self = mPSIndexer;
    }

    @Override
    protected void data(ByteBuffer data, long filePos) {
        this.val$self.analyseBuffer(data, filePos);
    }

    @Override
    protected void done() {
        this.val$self.finishAnalyse();
    }
}
