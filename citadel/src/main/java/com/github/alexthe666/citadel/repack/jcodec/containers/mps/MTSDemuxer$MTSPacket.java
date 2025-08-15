/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import java.nio.ByteBuffer;

public static class MTSDemuxer.MTSPacket {
    public ByteBuffer payload;
    public boolean payloadStart;
    public int pid;

    public MTSDemuxer.MTSPacket(int guid, boolean payloadStart, ByteBuffer payload) {
        this.pid = guid;
        this.payloadStart = payloadStart;
        this.payload = payload;
    }
}
