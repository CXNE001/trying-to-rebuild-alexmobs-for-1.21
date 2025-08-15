/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.model.TapeTimecode;
import java.nio.ByteBuffer;

public static class MXFDemuxer.MXFPacket
extends Packet {
    private long offset;
    private int len;

    public MXFDemuxer.MXFPacket(ByteBuffer data, long pts, int timescale, long duration, long frameNo, Packet.FrameType frameType, TapeTimecode tapeTimecode, long offset, int len) {
        super(data, pts, timescale, duration, frameNo, frameType, tapeTimecode, 0);
        this.offset = offset;
        this.len = len;
    }

    public long getOffset() {
        return this.offset;
    }

    public int getLen() {
        return this.len;
    }
}
