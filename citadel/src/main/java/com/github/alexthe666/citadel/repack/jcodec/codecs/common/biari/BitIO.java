/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari;

import com.github.alexthe666.citadel.repack.jcodec.platform.BaseOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitIO {
    public static InputBits inputFromStream(InputStream is) {
        return new StreamInputBits(is);
    }

    public static OutputBits outputFromStream(OutputStream out) {
        return new StreamOutputBits(out);
    }

    public static InputBits inputFromArray(byte[] bytes) {
        return new StreamInputBits(new ByteArrayInputStream(bytes));
    }

    public static OutputBits outputFromArray(final byte[] bytes) {
        return new StreamOutputBits(new BaseOutputStream(){
            int ptr;

            @Override
            protected void writeByte(int b) throws IOException {
                if (this.ptr >= bytes.length) {
                    throw new IOException("Buffer is full");
                }
                bytes[this.ptr++] = (byte)b;
            }
        });
    }

    public static byte[] compressBits(int[] decompressed) {
        byte[] compressed = new byte[(decompressed.length >> 3) + 1];
        OutputBits out = BitIO.outputFromArray(compressed);
        try {
            for (int i = 0; i < decompressed.length; ++i) {
                int bit = decompressed[i];
                out.putBit(bit);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return compressed;
    }

    public static int[] decompressBits(byte[] compressed) {
        int[] decompressed = new int[compressed.length << 3];
        InputBits inputFromArray = BitIO.inputFromArray(compressed);
        try {
            int read;
            int i = 0;
            while ((read = inputFromArray.getBit()) != -1) {
                decompressed[i] = read;
                ++i;
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return decompressed;
    }

    public static class StreamOutputBits
    implements OutputBits {
        private OutputStream out;
        private int cur;
        private int bit;

        public StreamOutputBits(OutputStream out) {
            this.out = out;
        }

        @Override
        public void putBit(int symbol) throws IOException {
            if (this.bit > 7) {
                this.out.write(this.cur);
                this.cur = 0;
                this.bit = 0;
            }
            this.cur |= (symbol & 1) << 7 - this.bit++;
        }

        @Override
        public void flush() throws IOException {
            if (this.bit > 0) {
                this.out.write(this.cur);
            }
        }
    }

    public static class StreamInputBits
    implements InputBits {
        private InputStream _in;
        private int cur;
        private int bit;

        public StreamInputBits(InputStream _in) {
            this._in = _in;
            this.bit = 8;
        }

        @Override
        public int getBit() throws IOException {
            if (this.bit > 7) {
                this.cur = this._in.read();
                if (this.cur == -1) {
                    return -1;
                }
                this.bit = 0;
            }
            return this.cur >> 7 - this.bit++ & 1;
        }
    }

    public static interface OutputBits {
        public void putBit(int var1) throws IOException;

        public void flush() throws IOException;
    }

    public static interface InputBits {
        public int getBit() throws IOException;
    }
}
