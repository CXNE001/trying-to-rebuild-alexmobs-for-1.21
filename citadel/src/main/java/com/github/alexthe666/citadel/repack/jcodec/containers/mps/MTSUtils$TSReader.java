/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import java.io.IOException;
import java.nio.ByteBuffer;

public static abstract class MTSUtils.TSReader {
    private static final int TS_SYNC_MARKER = 71;
    private static final int TS_PKT_SIZE = 188;
    public static final int BUFFER_SIZE = 96256;
    private boolean flush;

    public MTSUtils.TSReader(boolean flush) {
        this.flush = flush;
    }

    public void readTsFile(SeekableByteChannel ch) throws IOException {
        ch.setPosition(0L);
        ByteBuffer buf = ByteBuffer.allocate(96256);
        long pos = ch.position();
        while (ch.read(buf) >= 188) {
            long posRem = pos;
            buf.flip();
            while (buf.remaining() >= 188) {
                boolean sectionSyntax;
                ByteBuffer tsBuf = NIOUtils.read(buf, 188);
                ByteBuffer fullPkt = tsBuf.duplicate();
                pos += 188L;
                Preconditions.checkState(71 == (tsBuf.get() & 0xFF));
                int guidFlags = (tsBuf.get() & 0xFF) << 8 | tsBuf.get() & 0xFF;
                int guid = guidFlags & 0x1FFF;
                int payloadStart = guidFlags >> 14 & 1;
                int b0 = tsBuf.get() & 0xFF;
                int counter = b0 & 0xF;
                if ((b0 & 0x20) != 0) {
                    NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
                }
                boolean bl = sectionSyntax = payloadStart == 1 && (NIOUtils.getRel(tsBuf, NIOUtils.getRel(tsBuf, 0) + 2) & 0x80) == 128;
                if (sectionSyntax) {
                    NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
                }
                if (this.onPkt(guid, payloadStart == 1, tsBuf, pos - (long)tsBuf.remaining(), sectionSyntax, fullPkt)) continue;
                return;
            }
            if (this.flush) {
                buf.flip();
                ch.setPosition(posRem);
                ch.write(buf);
            }
            buf.clear();
            pos = ch.position();
        }
    }

    protected boolean onPkt(int guid, boolean payloadStart, ByteBuffer tsBuf, long filePos, boolean sectionSyntax, ByteBuffer fullPkt) {
        return true;
    }
}
