/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PATSection;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.HashSet;

public class HLSRelocatePMT {
    private static final int TS_START_CODE = 71;
    private static final int CHUNK_SIZE_PKT = 1024;
    private static final int TS_PKT_SIZE = 188;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main1(String[] args) throws IOException {
        MainUtils.Cmd cmd = MainUtils.parseArguments(args, new MainUtils.Flag[0]);
        if (cmd.args.length < 2) {
            MainUtils.printHelpNoFlags("file _in", "file out");
            return;
        }
        FileChannelWrapper _in = null;
        FileChannelWrapper out = null;
        try {
            _in = NIOUtils.readableChannel(new File(cmd.args[0]));
            out = NIOUtils.writableChannel(new File(cmd.args[1]));
            System.err.println("Processed: " + HLSRelocatePMT.replocatePMT(_in, out) + " packets.");
        }
        catch (Throwable throwable) {
            NIOUtils.closeQuietly(_in);
            NIOUtils.closeQuietly(out);
            throw throwable;
        }
        NIOUtils.closeQuietly(_in);
        NIOUtils.closeQuietly(out);
    }

    private static int replocatePMT(ReadableByteChannel _in, WritableByteChannel out) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(192512);
        HashSet<Integer> pmtPids = new HashSet<Integer>();
        ArrayList<ByteBuffer> held = new ArrayList<ByteBuffer>();
        ByteBuffer patPkt = null;
        ByteBuffer pmtPkt = null;
        int totalPkt = 0;
        while (_in.read(buf) != -1) {
            buf.flip();
            buf.limit(buf.limit() / 188 * 188);
            while (buf.hasRemaining()) {
                ByteBuffer pkt = NIOUtils.read(buf, 188);
                ByteBuffer pktRead = pkt.duplicate();
                Preconditions.checkState(71 == (pktRead.get() & 0xFF));
                ++totalPkt;
                int guidFlags = (pktRead.get() & 0xFF) << 8 | pktRead.get() & 0xFF;
                int guid = guidFlags & 0x1FFF;
                int payloadStart = guidFlags >> 14 & 1;
                int b0 = pktRead.get() & 0xFF;
                int counter = b0 & 0xF;
                if ((b0 & 0x20) != 0) {
                    NIOUtils.skip(pktRead, pktRead.get() & 0xFF);
                }
                if (guid == 0 || pmtPids.contains(guid)) {
                    if (payloadStart == 1) {
                        NIOUtils.skip(pktRead, pktRead.get() & 0xFF);
                    }
                    if (guid == 0) {
                        patPkt = pkt;
                        PATSection pat = PATSection.parsePAT(pktRead);
                        int[] values = pat.getPrograms().values();
                        for (int i = 0; i < values.length; ++i) {
                            int pmtPid = values[i];
                            pmtPids.add(pmtPid);
                        }
                        continue;
                    }
                    if (!pmtPids.contains(guid)) continue;
                    pmtPkt = pkt;
                    out.write(patPkt);
                    out.write(pmtPkt);
                    for (ByteBuffer heldPkt : held) {
                        out.write(heldPkt);
                    }
                    held.clear();
                    continue;
                }
                if (pmtPkt == null) {
                    held.add(pkt);
                    continue;
                }
                out.write(pkt);
            }
            buf.clear();
        }
        return totalPkt;
    }
}
