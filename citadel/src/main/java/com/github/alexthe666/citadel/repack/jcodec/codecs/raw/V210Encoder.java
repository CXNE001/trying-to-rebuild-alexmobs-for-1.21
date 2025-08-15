/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.raw;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class V210Encoder {
    public ByteBuffer encodeFrame(ByteBuffer _out, Picture frame) throws IOException {
        ByteBuffer out = _out.duplicate();
        out.order(ByteOrder.LITTLE_ENDIAN);
        int tgtStride = (frame.getPlaneWidth(0) + 47) / 48 * 48;
        byte[][] data = frame.getData();
        byte[] tmpY = new byte[tgtStride];
        byte[] tmpCb = new byte[tgtStride >> 1];
        byte[] tmpCr = new byte[tgtStride >> 1];
        int yOff = 0;
        int cbOff = 0;
        int crOff = 0;
        for (int yy = 0; yy < frame.getHeight(); ++yy) {
            System.arraycopy(data[0], yOff, tmpY, 0, frame.getPlaneWidth(0));
            System.arraycopy(data[1], cbOff, tmpCb, 0, frame.getPlaneWidth(1));
            System.arraycopy(data[2], crOff, tmpCr, 0, frame.getPlaneWidth(2));
            int yi = 0;
            int cbi = 0;
            int cri = 0;
            while (yi < tgtStride) {
                int i = 0;
                i |= V210Encoder.clip(tmpCr[cri++]) << 20;
                i |= V210Encoder.clip(tmpY[yi++]) << 10;
                out.putInt(i |= V210Encoder.clip(tmpCb[cbi++]));
                i = 0;
                i |= V210Encoder.clip(tmpY[yi++]);
                i |= V210Encoder.clip(tmpY[yi++]) << 20;
                out.putInt(i |= V210Encoder.clip(tmpCb[cbi++]) << 10);
                i = 0;
                i |= V210Encoder.clip(tmpCb[cbi++]) << 20;
                i |= V210Encoder.clip(tmpY[yi++]) << 10;
                out.putInt(i |= V210Encoder.clip(tmpCr[cri++]));
                i = 0;
                i |= V210Encoder.clip(tmpY[yi++]);
                i |= V210Encoder.clip(tmpY[yi++]) << 20;
                out.putInt(i |= V210Encoder.clip(tmpCr[cri++]) << 10);
            }
            yOff += frame.getPlaneWidth(0);
            cbOff += frame.getPlaneWidth(1);
            crOff += frame.getPlaneWidth(2);
        }
        out.flip();
        return out;
    }

    static final int clip(byte val) {
        return MathUtil.clip(val + 128 << 2, 8, 1019);
    }
}
