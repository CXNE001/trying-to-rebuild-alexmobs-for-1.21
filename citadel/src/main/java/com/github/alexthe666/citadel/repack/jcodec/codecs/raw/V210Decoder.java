/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.raw;

import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class V210Decoder {
    private int width;
    private int height;

    public V210Decoder(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Picture decode(byte[] data) {
        ByteBuffer littleEndian = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        IntBuffer dat = littleEndian.asIntBuffer();
        ByteBuffer y = ByteBuffer.wrap(new byte[this.width * this.height]);
        ByteBuffer cb = ByteBuffer.wrap(new byte[this.width * this.height / 2]);
        ByteBuffer cr = ByteBuffer.wrap(new byte[this.width * this.height / 2]);
        while (dat.hasRemaining()) {
            int i = dat.get();
            cr.put(this.to8Bit(i >> 20));
            y.put(this.to8Bit(i >> 10 & 0x3FF));
            cb.put(this.to8Bit(i & 0x3FF));
            i = dat.get();
            y.put(this.to8Bit(i & 0x3FF));
            y.put(this.to8Bit(i >> 20));
            cb.put(this.to8Bit(i >> 10 & 0x3FF));
            i = dat.get();
            cb.put(this.to8Bit(i >> 20));
            y.put(this.to8Bit(i >> 10 & 0x3FF));
            cr.put(this.to8Bit(i & 0x3FF));
            i = dat.get();
            y.put(this.to8Bit(i & 0x3FF));
            y.put(this.to8Bit(i >> 20));
            cr.put(this.to8Bit(i >> 10 & 0x3FF));
        }
        return Picture.createPicture(this.width, this.height, new byte[][]{y.array(), cb.array(), cr.array()}, ColorSpace.YUV422);
    }

    private byte to8Bit(int i) {
        return (byte)((i + 2 >> 2) - 128);
    }
}
