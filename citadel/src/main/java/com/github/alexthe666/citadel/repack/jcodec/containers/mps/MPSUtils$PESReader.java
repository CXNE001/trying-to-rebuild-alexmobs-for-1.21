/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.nio.ByteBuffer;

public static abstract class MPSUtils.PESReader {
    private int marker = -1;
    private int lenFieldLeft;
    private int pesLen;
    private long pesFileStart = -1L;
    private int stream;
    private boolean _pes;
    private int pesLeft;
    private ByteBuffer pesBuffer = ByteBuffer.allocate(0x200000);

    protected abstract void pes(ByteBuffer var1, long var2, int var4, int var5);

    public void analyseBuffer(ByteBuffer buf, long pos) {
        int init = buf.position();
        while (buf.hasRemaining()) {
            long filePos;
            if (this.pesLeft > 0) {
                int toRead = Math.min(buf.remaining(), this.pesLeft);
                this.pesBuffer.put(NIOUtils.read(buf, toRead));
                this.pesLeft -= toRead;
                if (this.pesLeft != 0) continue;
                filePos = pos + (long)buf.position() - (long)init;
                this.pes1(this.pesBuffer, this.pesFileStart, (int)(filePos - this.pesFileStart), this.stream);
                this.pesFileStart = -1L;
                this._pes = false;
                this.stream = -1;
                continue;
            }
            int bt = buf.get() & 0xFF;
            if (this._pes) {
                this.pesBuffer.put((byte)(this.marker >>> 24));
            }
            this.marker = this.marker << 8 | bt;
            if (this.marker >= 443 && this.marker <= 495) {
                filePos = pos + (long)buf.position() - (long)init - 4L;
                if (this._pes) {
                    this.pes1(this.pesBuffer, this.pesFileStart, (int)(filePos - this.pesFileStart), this.stream);
                }
                this.pesFileStart = filePos;
                this._pes = true;
                this.stream = this.marker & 0xFF;
                this.lenFieldLeft = 2;
                this.pesLen = 0;
                continue;
            }
            if (this.marker >= 441 && this.marker <= 511) {
                if (this._pes) {
                    filePos = pos + (long)buf.position() - (long)init - 4L;
                    this.pes1(this.pesBuffer, this.pesFileStart, (int)(filePos - this.pesFileStart), this.stream);
                }
                this.pesFileStart = -1L;
                this._pes = false;
                this.stream = -1;
                continue;
            }
            if (this.lenFieldLeft <= 0) continue;
            this.pesLen = this.pesLen << 8 | bt;
            --this.lenFieldLeft;
            if (this.lenFieldLeft != 0) continue;
            this.pesLeft = this.pesLen;
            if (this.pesLen == 0) continue;
            this.flushMarker();
            this.marker = -1;
        }
    }

    private void flushMarker() {
        this.pesBuffer.put((byte)(this.marker >>> 24));
        this.pesBuffer.put((byte)(this.marker >>> 16 & 0xFF));
        this.pesBuffer.put((byte)(this.marker >>> 8 & 0xFF));
        this.pesBuffer.put((byte)(this.marker & 0xFF));
    }

    private void pes1(ByteBuffer pesBuffer, long start, int pesLen, int stream) {
        pesBuffer.flip();
        this.pes(pesBuffer, start, pesLen, stream);
        pesBuffer.clear();
    }

    public void finishRead() {
        if (this.pesLeft <= 4) {
            this.flushMarker();
            this.pes1(this.pesBuffer, this.pesFileStart, this.pesBuffer.position(), this.stream);
        }
    }
}
