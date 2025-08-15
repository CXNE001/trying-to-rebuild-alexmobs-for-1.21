/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.io;

import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public static abstract class NIOUtils.FileReader {
    private int oldPd;

    protected abstract void data(ByteBuffer var1, long var2);

    protected abstract void done();

    public void readChannel(SeekableByteChannel ch, int bufferSize, NIOUtils.FileReaderListener listener) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(bufferSize);
        long size = ch.size();
        long pos = ch.position();
        while (ch.read(buf) != -1) {
            buf.flip();
            this.data(buf, pos);
            buf.flip();
            if (listener != null) {
                int newPd = (int)(100L * pos / size);
                if (newPd != this.oldPd) {
                    listener.progress(newPd);
                }
                this.oldPd = newPd;
            }
            pos = ch.position();
        }
        this.done();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void readFile(File source, int bufferSize, NIOUtils.FileReaderListener listener) throws IOException {
        FileChannelWrapper ch = null;
        try {
            ch = NIOUtils.readableChannel(source);
            this.readChannel(ch, bufferSize, listener);
        }
        finally {
            NIOUtils.closeQuietly(ch);
        }
    }
}
