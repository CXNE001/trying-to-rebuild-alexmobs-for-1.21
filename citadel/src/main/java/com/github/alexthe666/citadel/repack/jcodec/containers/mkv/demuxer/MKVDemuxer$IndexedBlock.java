/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.MkvBlock;

public static class MKVDemuxer.IndexedBlock {
    public int firstFrameNo;
    public MkvBlock block;

    public static MKVDemuxer.IndexedBlock make(int no, MkvBlock b) {
        MKVDemuxer.IndexedBlock ib = new MKVDemuxer.IndexedBlock();
        ib.firstFrameNo = no;
        ib.block = b;
        return ib;
    }
}
