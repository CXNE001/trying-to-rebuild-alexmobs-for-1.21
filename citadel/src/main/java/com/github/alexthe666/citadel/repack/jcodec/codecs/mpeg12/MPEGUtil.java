/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MPEGUtil {
    public static final ByteBuffer gotoNextMarker(ByteBuffer buf) {
        return MPEGUtil.gotoMarker(buf, 0, 256, 511);
    }

    public static final ByteBuffer gotoMarker(ByteBuffer buf, int n, int mmin, int mmax) {
        if (!buf.hasRemaining()) {
            return null;
        }
        int from = buf.position();
        ByteBuffer result = buf.slice();
        result.order(ByteOrder.BIG_ENDIAN);
        int val = -1;
        while (buf.hasRemaining()) {
            if ((val = val << 8 | buf.get() & 0xFF) < mmin || val > mmax) continue;
            if (n == 0) {
                buf.position(buf.position() - 4);
                result.limit(buf.position() - from);
                break;
            }
            --n;
        }
        return result;
    }

    public static final ByteBuffer nextSegment(ByteBuffer buf) {
        MPEGUtil.gotoMarker(buf, 0, 256, 511);
        return MPEGUtil.gotoMarker(buf, 1, 256, 511);
    }
}
