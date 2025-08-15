/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.IntObjectMap;
import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.UsedViaReflection;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSDemuxer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MTSDemuxer {
    private SeekableByteChannel channel;
    private Map<Integer, ProgramChannel> programs;

    public Set<Integer> getPrograms() {
        return this.programs.keySet();
    }

    public Set<Integer> findPrograms(SeekableByteChannel src) throws IOException {
        MTSPacket pkt;
        long rem = src.position();
        HashSet<Integer> guids = new HashSet<Integer>();
        for (int i = 0; (guids.size() == 0 || i < guids.size() * 500) && (pkt = MTSDemuxer.readPacket(src)) != null; ++i) {
            if (pkt.payload == null) continue;
            ByteBuffer payload = pkt.payload;
            if (guids.contains(pkt.pid) || (payload.duplicate().getInt() & 0xFFFFFF00) != 256) continue;
            guids.add(pkt.pid);
        }
        src.setPosition(rem);
        return guids;
    }

    public MTSDemuxer(SeekableByteChannel src) throws IOException {
        this.channel = src;
        this.programs = new HashMap<Integer, ProgramChannel>();
        for (int pid : this.findPrograms(src)) {
            this.programs.put(pid, new ProgramChannel(this));
        }
        src.setPosition(0L);
    }

    public ReadableByteChannel getProgram(int pid) {
        return this.programs.get(pid);
    }

    private boolean readAndDispatchNextTSPacket() throws IOException {
        MTSPacket pkt = MTSDemuxer.readPacket(this.channel);
        if (pkt == null) {
            return false;
        }
        ProgramChannel program = this.programs.get(pkt.pid);
        if (program != null) {
            program.storePacket(pkt);
        }
        return true;
    }

    public static MTSPacket readPacket(ReadableByteChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(188);
        if (NIOUtils.readFromChannel(channel, buffer) != 188) {
            return null;
        }
        buffer.flip();
        return MTSDemuxer.parsePacket(buffer);
    }

    public static MTSPacket parsePacket(ByteBuffer buffer) {
        int marker = buffer.get() & 0xFF;
        Preconditions.checkState(71 == marker);
        short guidFlags = buffer.getShort();
        int guid = guidFlags & 0x1FFF;
        int payloadStart = guidFlags >> 14 & 1;
        int b0 = buffer.get() & 0xFF;
        int counter = b0 & 0xF;
        if ((b0 & 0x20) != 0) {
            int taken = 0;
            taken = (buffer.get() & 0xFF) + 1;
            NIOUtils.skip(buffer, taken - 1);
        }
        return new MTSPacket(guid, payloadStart == 1, (b0 & 0x10) != 0 ? buffer : null);
    }

    @UsedViaReflection
    public static int probe(ByteBuffer b_) {
        int[] keys;
        ByteBuffer b = b_.duplicate();
        IntObjectMap streams = new IntObjectMap();
        try {
            MTSPacket tsPkt;
            ByteBuffer sub;
            while ((sub = NIOUtils.read(b, 188)).remaining() >= 188 && (tsPkt = MTSDemuxer.parsePacket(sub)) != null) {
                ArrayList<ByteBuffer> data = (ArrayList<ByteBuffer>)streams.get(tsPkt.pid);
                if (data == null) {
                    data = new ArrayList<ByteBuffer>();
                    streams.put(tsPkt.pid, data);
                }
                if (tsPkt.payload == null) continue;
                data.add(tsPkt.payload);
            }
        }
        catch (Throwable t) {
            // empty catch block
        }
        int maxScore = 0;
        for (int i : keys = streams.keys()) {
            List packets = (List)streams.get(i);
            int score = MPSDemuxer.probe(NIOUtils.combineBuffers(packets));
            if (score <= maxScore) continue;
            maxScore = score + (packets.size() > 20 ? 50 : 0);
        }
        return maxScore;
    }

    public static class MTSPacket {
        public ByteBuffer payload;
        public boolean payloadStart;
        public int pid;

        public MTSPacket(int guid, boolean payloadStart, ByteBuffer payload) {
            this.pid = guid;
            this.payloadStart = payloadStart;
            this.payload = payload;
        }
    }

    private static class ProgramChannel
    implements ReadableByteChannel {
        private final MTSDemuxer demuxer;
        private List<ByteBuffer> data;
        private boolean closed;

        public ProgramChannel(MTSDemuxer demuxer) {
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

        public void storePacket(MTSPacket pkt) {
            if (this.closed) {
                return;
            }
            this.data.add(pkt.payload);
        }
    }
}
