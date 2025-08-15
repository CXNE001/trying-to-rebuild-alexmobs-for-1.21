/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSDemuxer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

private static class MTSDemuxer.ProgramChannel
implements ReadableByteChannel {
    private final MTSDemuxer demuxer;
    private List<ByteBuffer> data;
    private boolean closed;

    public MTSDemuxer.ProgramChannel(MTSDemuxer demuxer) {
        this.demuxer = demuxer;
        this.data = new ArrayList<ByteBuffer>();
    }

    @Override
    public boolean isOpen() {
        return !this.closed && this.demuxer.channel.isOpen();
    }

    @Override
    public void close() throws IOException {
        this.closed = true;
        this.data.clear();
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        int bytesRead = 0;
        while (dst.hasRemaining()) {
            while (this.data.size() == 0) {
                if (this.demuxer.readAndDispatchNextTSPacket()) continue;
                return bytesRead > 0 ? bytesRead : -1;
            }
            ByteBuffer first = this.data.get(0);
            int toRead = Math.min(dst.remaining(), first.remaining());
            dst.put(NIOUtils.read(first, toRead));
            if (!first.hasRemaining()) {
                this.data.remove(0);
            }
            bytesRead += toRead;
        }
        return bytesRead;
    }

    public void storePacket(MTSDemuxer.MTSPacket pkt) {
        if (this.closed) {
            return;
        }
        this.data.add(pkt.payload);
    }
}
