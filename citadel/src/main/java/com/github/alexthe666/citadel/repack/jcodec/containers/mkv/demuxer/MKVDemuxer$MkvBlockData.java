/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.MkvBlock;
import java.nio.ByteBuffer;

private static class MKVDemuxer.MkvBlockData {
    final MkvBlock block;
    final ByteBuffer data;
    final int count;

    MKVDemuxer.MkvBlockData(MkvBlock block, ByteBuffer data, int count) {
        this.block = block;
        this.data = data;
        this.count = count;
    }
}
