/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBin;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.util.EbmlUtil;
import java.nio.ByteBuffer;

public class EbmlSint
extends EbmlBin {
    public static final long[] signedComplement = new long[]{0L, 63L, 8191L, 1048575L, 0x7FFFFFFL, 0x3FFFFFFFFL, 0x1FFFFFFFFFFL, 0xFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFL};

    public EbmlSint(byte[] id) {
        super(id);
    }

    public void setLong(long value) {
        this.data = ByteBuffer.wrap(EbmlSint.convertToBytes(value));
    }

    public long getLong() {
        if (this.data.limit() - this.data.position() == 8) {
            return this.data.duplicate().getLong();
        }
        byte[] b = this.data.array();
        long l = 0L;
        for (int i = b.length - 1; i >= 0; --i) {
            l |= ((long)b[i] & 0xFFL) << 8 * (b.length - 1 - i);
        }
        return l;
    }

    public static int ebmlSignedLength(long val) {
        if (val <= 64L && val >= -63L) {
            return 1;
        }
        if (val <= 8192L && val >= -8191L) {
            return 2;
        }
        if (val <= 0x100000L && val >= -1048575L) {
            return 3;
        }
        if (val <= 0x8000000L && val >= -134217727L) {
            return 4;
        }
        if (val <= 0x400000000L && val >= -17179869183L) {
            return 5;
        }
        if (val <= 0x20000000000L && val >= -2199023255551L) {
            return 6;
        }
        if (val <= 0x1000000000000L && val >= -281474976710655L) {
            return 7;
        }
        return 8;
    }

    public static byte[] convertToBytes(long val) {
        int num = EbmlSint.ebmlSignedLength(val);
        return EbmlUtil.ebmlEncodeLen(val += signedComplement[num], num);
    }
}
