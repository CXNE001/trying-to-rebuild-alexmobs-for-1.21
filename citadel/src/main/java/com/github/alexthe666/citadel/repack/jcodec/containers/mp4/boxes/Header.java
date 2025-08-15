/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.JCodecUtil2;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.io.StringReader;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Header {
    public static final byte[] FOURCC_FREE = new byte[]{102, 114, 101, 101};
    private static final long MAX_UNSIGNED_INT = 0x100000000L;
    private String fourcc;
    private long size;
    private boolean lng;

    public Header(String fourcc) {
        this.fourcc = fourcc;
    }

    public static Header createHeader(String fourcc, long size) {
        Header header = new Header(fourcc);
        header.size = size;
        return header;
    }

    public static Header newHeader(String fourcc, long size, boolean lng) {
        Header header = new Header(fourcc);
        header.size = size;
        header.lng = lng;
        return header;
    }

    public static Header read(ByteBuffer input) {
        long size = 0L;
        while (input.remaining() >= 4 && (size = Platform.unsignedInt(input.getInt())) == 0L) {
        }
        if (input.remaining() < 4 || size < 8L && size != 1L) {
            Logger.error("Broken atom of size " + size);
            return null;
        }
        String fourcc = NIOUtils.readString(input, 4);
        boolean lng = false;
        if (size == 1L) {
            if (input.remaining() >= 8) {
                lng = true;
                size = input.getLong();
            } else {
                Logger.error("Broken atom of size " + size);
                return null;
            }
        }
        return Header.newHeader(fourcc, size, lng);
    }

    public void skip(InputStream di) throws IOException {
        StringReader.sureSkip(di, this.size - this.headerSize());
    }

    public long headerSize() {
        return this.lng || this.size > 0x100000000L ? 16L : 8L;
    }

    public static int estimateHeaderSize(int size) {
        return (long)(size + 8) > 0x100000000L ? 16 : 8;
    }

    public byte[] readContents(InputStream di) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = 0;
        while ((long)i < this.size - this.headerSize()) {
            baos.write(di.read());
            ++i;
        }
        return baos.toByteArray();
    }

    public String getFourcc() {
        return this.fourcc;
    }

    public long getBodySize() {
        return this.size - this.headerSize();
    }

    public void setBodySize(int length) {
        this.size = (long)length + this.headerSize();
    }

    public void write(ByteBuffer out) {
        if (this.size > 0x100000000L) {
            out.putInt(1);
        } else {
            out.putInt((int)this.size);
        }
        byte[] bt = JCodecUtil2.asciiString(this.fourcc);
        if (bt != null && bt.length == 4) {
            out.put(bt);
        } else {
            out.put(FOURCC_FREE);
        }
        if (this.size > 0x100000000L) {
            out.putLong(this.size);
        }
    }

    public void writeChannel(SeekableByteChannel output) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(16);
        this.write(bb);
        bb.flip();
        output.write(bb);
    }

    public long getSize() {
        return this.size;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.fourcc == null ? 0 : this.fourcc.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Header other = (Header)obj;
        return !(this.fourcc == null ? other.fourcc != null : !this.fourcc.equals(other.fourcc));
    }
}
