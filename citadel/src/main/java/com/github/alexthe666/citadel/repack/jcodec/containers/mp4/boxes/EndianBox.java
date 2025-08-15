/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndianBox
extends Box {
    private ByteOrder endian;

    public static String fourcc() {
        return "enda";
    }

    public static EndianBox createEndianBox(ByteOrder endian) {
        EndianBox endianBox = new EndianBox(new Header(EndianBox.fourcc()));
        endianBox.endian = endian;
        return endianBox;
    }

    public EndianBox(Header header) {
        super(header);
    }

    @Override
    public void parse(ByteBuffer input) {
        long end = input.getShort();
        this.endian = end == 1L ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        out.putShort((short)(this.endian == ByteOrder.LITTLE_ENDIAN ? 1 : 0));
    }

    @Override
    public int estimateSize() {
        return 10;
    }

    public ByteOrder getEndian() {
        return this.endian;
    }

    protected int calcSize() {
        return 2;
    }
}
