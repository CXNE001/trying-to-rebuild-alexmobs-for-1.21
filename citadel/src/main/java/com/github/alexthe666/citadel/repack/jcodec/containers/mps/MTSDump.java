/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.IntIntMap;
import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSDump;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PATSection;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PMTSection;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.HashSet;

public class MTSDump
extends MPSDump {
    private static final MainUtils.Flag DUMP_FROM = MainUtils.Flag.flag("dump-from", null, "Stop reading at timestamp");
    private static final MainUtils.Flag STOP_AT = MainUtils.Flag.flag("stop-at", null, "Start dumping from timestamp");
    private static final MainUtils.Flag[] ALL_FLAGS = new MainUtils.Flag[]{DUMP_FROM, STOP_AT};
    private int guid;
    private ByteBuffer buf = ByteBuffer.allocate(192512);
    private ByteBuffer tsBuf = ByteBuffer.allocate(188);
    private int tsNo;
    private int globalPayload;
    private int[] payloads;
    private int[] nums;
    private int[] prevPayloads;
    private int[] prevNums;

    public MTSDump(ReadableByteChannel ch, int targetGuid) {
        super(ch);
        this.guid = targetGuid;
        this.buf.position(this.buf.limit());
        this.tsBuf.position(this.tsBuf.limit());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main2(String[] args) throws IOException {
        MainUtils.Cmd cmd;
        FileChannelWrapper ch;
        block5: {
            block4: {
                ch = null;
                try {
                    cmd = MainUtils.parseArguments(args, ALL_FLAGS);
                    if (cmd.args.length >= 1) break block4;
                    MainUtils.printHelp(ALL_FLAGS, Arrays.asList("file name", "guid"));
                }
                catch (Throwable throwable) {
                    NIOUtils.closeQuietly(ch);
                    throw throwable;
                }
                NIOUtils.closeQuietly(ch);
                return;
            }
            if (cmd.args.length != 1) break block5;
            System.out.println("MTS programs:");
            MTSDump.dumpProgramPids(NIOUtils.readableChannel(new File(cmd.args[0])));
            NIOUtils.closeQuietly(ch);
            return;
        }
        ch = NIOUtils.readableChannel(new File(cmd.args[0]));
        Long dumpAfterPts = cmd.getLongFlag(DUMP_FROM);
        Long stopPts = cmd.getLongFlag(STOP_AT);
        new MTSDump(ch, Integer.parseInt(cmd.args[1])).dump(dumpAfterPts, stopPts);
        NIOUtils.closeQuietly(ch);
    }

    private static void dumpProgramPids(ReadableByteChannel readableFileChannel) throws IOException {
        HashSet<Integer> pids = new HashSet<Integer>();
        ByteBuffer buf = ByteBuffer.allocate(1925120);
        readableFileChannel.read(buf);
        buf.flip();
        buf.limit(buf.limit() - buf.limit() % 188);
        int pmtPid = -1;
        while (buf.hasRemaining()) {
            ByteBuffer tsBuf = NIOUtils.read(buf, 188);
            Preconditions.checkState(71 == (tsBuf.get() & 0xFF));
            int guidFlags = (tsBuf.get() & 0xFF) << 8 | tsBuf.get() & 0xFF;
            int guid = guidFlags & 0x1FFF;
            System.out.println(guid);
            if (guid != 0) {
                pids.add(guid);
            }
            if (guid != 0 && guid != pmtPid) continue;
            int payloadStart = guidFlags >> 14 & 1;
            int b0 = tsBuf.get() & 0xFF;
            int counter = b0 & 0xF;
            boolean payloadOff = false;
            if ((b0 & 0x20) != 0) {
                NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
            }
            if (payloadStart == 1) {
                NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
            }
            if (guid == 0) {
                PATSection pat = PATSection.parsePAT(tsBuf);
                IntIntMap programs = pat.getPrograms();
                pmtPid = programs.values()[0];
                MTSDump.printPat(pat);
                continue;
            }
            if (guid != pmtPid) continue;
            PMTSection pmt = PMTSection.parsePMT(tsBuf);
            MTSDump.printPmt(pmt);
            return;
        }
        for (Integer pid : pids) {
            System.out.println(pid);
        }
    }

    private static void printPat(PATSection pat) {
        int[] keys;
        IntIntMap programs = pat.getPrograms();
        System.out.print("PAT: ");
        for (int i : keys = programs.keys()) {
            System.out.print(i + ":" + programs.get(i) + ", ");
        }
        System.out.println();
    }

    private static void printPmt(PMTSection pmt) {
        System.out.print("PMT: ");
        for (PMTSection.PMTStream pmtStream : pmt.getStreams()) {
            System.out.print(pmtStream.getPid() + ":" + pmtStream.getStreamTypeTag() + ", ");
            for (MPSUtils.MPEGMediaDescriptor descriptor : pmtStream.getDesctiptors()) {
                System.out.println(Platform.toJSON(descriptor));
            }
        }
        System.out.println();
    }

    @Override
    protected void logPes(PESPacket pkt, int hdrSize, ByteBuffer payload) {
        System.out.println(pkt.streamId + "(" + (pkt.streamId >= 224 ? "video" : "audio") + ") [ts#" + this.mapPos(pkt.pos) + ", " + (payload.remaining() + hdrSize) + "b], pts: " + pkt.pts + ", dts: " + pkt.dts);
    }

    private int mapPos(long pos) {
        int i;
        int left = this.globalPayload;
        for (i = this.payloads.length - 1; i >= 0; --i) {
            if ((long)(left -= this.payloads[i]) > pos) continue;
            return this.nums[i];
        }
        if (this.prevPayloads != null) {
            for (i = this.prevPayloads.length - 1; i >= 0; --i) {
                if ((long)(left -= this.prevPayloads[i]) > pos) continue;
                return this.prevNums[i];
            }
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int fillBuffer(ByteBuffer dst) throws IOException {
        IntArrayList payloads = IntArrayList.createIntArrayList();
        IntArrayList nums = IntArrayList.createIntArrayList();
        int remaining = dst.remaining();
        try {
            dst.put(NIOUtils.read(this.tsBuf, Math.min(dst.remaining(), this.tsBuf.remaining())));
            while (dst.hasRemaining()) {
                if (!this.buf.hasRemaining()) {
                    ByteBuffer dub = this.buf.duplicate();
                    dub.clear();
                    int read = this.ch.read(dub);
                    if (read == -1) {
                        int n = dst.remaining() != remaining ? remaining - dst.remaining() : -1;
                        return n;
                    }
                    dub.flip();
                    dub.limit(dub.limit() - dub.limit() % 188);
                    this.buf = dub;
                }
                this.tsBuf = NIOUtils.read(this.buf, 188);
                Preconditions.checkState(71 == (this.tsBuf.get() & 0xFF));
                ++this.tsNo;
                int guidFlags = (this.tsBuf.get() & 0xFF) << 8 | this.tsBuf.get() & 0xFF;
                int guid = guidFlags & 0x1FFF;
                if (guid != this.guid) continue;
                int payloadStart = guidFlags >> 14 & 1;
                int b0 = this.tsBuf.get() & 0xFF;
                int counter = b0 & 0xF;
                if ((b0 & 0x20) != 0) {
                    NIOUtils.skip(this.tsBuf, this.tsBuf.get() & 0xFF);
                }
                this.globalPayload += this.tsBuf.remaining();
                payloads.add(this.tsBuf.remaining());
                nums.add(this.tsNo - 1);
                dst.put(NIOUtils.read(this.tsBuf, Math.min(dst.remaining(), this.tsBuf.remaining())));
            }
        }
        finally {
            this.prevPayloads = this.payloads;
            this.payloads = payloads.toArray();
            this.prevNums = this.nums;
            this.nums = nums.toArray();
        }
        return remaining - dst.remaining();
    }
}
