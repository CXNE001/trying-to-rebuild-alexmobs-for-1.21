/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class SegmentReader {
    private ReadableByteChannel channel;
    private ByteBuffer buf;
    protected int curMarker;
    private int fetchSize;
    protected boolean done;
    private long pos;
    private int bytesInMarker;
    private int bufferIncrement = 32768;

    public SegmentReader(ReadableByteChannel channel, int fetchSize) throws IOException {
        this.channel = channel;
        this.fetchSize = fetchSize;
        this.buf = NIOUtils.fetchFromChannel(channel, 4);
        this.pos = this.buf.remaining();
        this.curMarker = this.buf.getInt();
        this.bytesInMarker = 4;
    }

    public int getBufferIncrement() {
        return this.bufferIncrement;
    }

    public void setBufferIncrement(int bufferIncrement) {
        this.bufferIncrement = bufferIncrement;
    }

    public final State readToNextMarkerPartial(ByteBuffer out) throws IOException {
        if (this.done) {
            return State.STOP;
        }
        int skipOneMarker = this.curMarker >= 256 && this.curMarker <= 511 ? 1 : 0;
        int written = out.position();
        while (true) {
            if (this.buf.hasRemaining()) {
                if (this.curMarker >= 256 && this.curMarker <= 511) {
                    if (skipOneMarker == 0) {
                        return State.DONE;
                    }
                    --skipOneMarker;
                }
                if (!out.hasRemaining()) {
                    return State.MORE_DATA;
                }
                out.put((byte)(this.curMarker >>> 24));
                this.curMarker = this.curMarker << 8 | this.buf.get() & 0xFF;
                continue;
            }
            this.buf = NIOUtils.fetchFromChannel(this.channel, this.fetchSize);
            this.pos += (long)this.buf.remaining();
            if (!this.buf.hasRemaining()) break;
        }
        written = out.position() - written;
        if (written > 0 && this.curMarker >= 256 && this.curMarker <= 511) {
            return State.DONE;
        }
        while (this.bytesInMarker > 0 && out.hasRemaining()) {
            out.put((byte)(this.curMarker >>> 24));
            this.curMarker <<= 8;
            --this.bytesInMarker;
            if (this.curMarker < 256 || this.curMarker > 511) continue;
            return State.DONE;
        }
        if (this.bytesInMarker == 0) {
            this.done = true;
            return State.STOP;
        }
        return State.MORE_DATA;
    }

    public ByteBuffer readToNextMarkerNewBuffer() throws IOException {
        if (this.done) {
            return null;
        }
        ArrayList<ByteBuffer> buffers = new ArrayList<ByteBuffer>();
        this.readToNextMarkerBuffers(buffers);
        return NIOUtils.combineBuffers(buffers);
    }

    public void readToNextMarkerBuffers(List<ByteBuffer> buffers) throws IOException {
        State state;
        do {
            ByteBuffer curBuffer = ByteBuffer.allocate(this.bufferIncrement);
            state = this.readToNextMarkerPartial(curBuffer);
            curBuffer.flip();
            buffers.add(curBuffer);
        } while (state == State.MORE_DATA);
    }

    public final boolean readToNextMarker(ByteBuffer out) throws IOException {
        State state = this.readToNextMarkerPartial(out);
        if (state == State.MORE_DATA) {
            throw new BufferOverflowException();
        }
        return state == State.DONE;
    }

    public final boolean skipToMarker() throws IOException {
        if (this.done) {
            return false;
        }
        while (true) {
            if (this.buf.hasRemaining()) {
                this.curMarker = this.curMarker << 8 | this.buf.get() & 0xFF;
                if (this.curMarker < 256 || this.curMarker > 511) continue;
                return true;
            }
            this.buf = NIOUtils.fetchFromChannel(this.channel, this.fetchSize);
            this.pos += (long)this.buf.remaining();
            if (!this.buf.hasRemaining()) break;
        }
        this.done = true;
        return false;
    }

    public final boolean read(ByteBuffer out, int length) throws IOException {
        if (this.done) {
            return false;
        }
        while (true) {
            if (this.buf.hasRemaining()) {
                if (length-- == 0) {
                    return true;
                }
                out.put((byte)(this.curMarker >>> 24));
                this.curMarker = this.curMarker << 8 | this.buf.get() & 0xFF;
                continue;
            }
            this.buf = NIOUtils.fetchFromChannel(this.channel, this.fetchSize);
            this.pos += (long)this.buf.remaining();
            if (!this.buf.hasRemaining()) break;
        }
        out.putInt(this.curMarker);
        this.done = true;
        return false;
    }

    public final long curPos() {
        return this.pos - (long)this.buf.remaining() - 4L;
    }

    public static enum State {
        MORE_DATA,
        DONE,
        STOP;

    }
}
