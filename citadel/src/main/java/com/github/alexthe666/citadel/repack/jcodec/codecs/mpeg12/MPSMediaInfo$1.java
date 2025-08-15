/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.nio.ByteBuffer;

class MPSMediaInfo.1
extends NIOUtils.FileReader {
    MPSMediaInfo.1() {
    }

    @Override
    protected void data(ByteBuffer data, long filePos) {
        MPSMediaInfo.this.analyseBuffer(data, filePos);
    }

    @Override
    protected void done() {
    }
}
