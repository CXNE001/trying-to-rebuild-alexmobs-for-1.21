/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBin;
import java.nio.ByteBuffer;

public class EbmlFloat
extends EbmlBin {
    public EbmlFloat(byte[] id) {
        super(id);
    }

    public void setDouble(double value) {
        if (value < 3.4028234663852886E38) {
            ByteBuffer bb = ByteBuffer.allocate(4);
            bb.putFloat((float)value);
            bb.flip();
            this.data = bb;
        } else if (value < Double.MAX_VALUE) {
            ByteBuffer bb = ByteBuffer.allocate(8);
            bb.putDouble(value);
            bb.flip();
            this.data = bb;
        }
    }

    public double getDouble() {
        if (this.data.limit() == 4) {
            return this.data.duplicate().getFloat();
        }
        return this.data.duplicate().getDouble();
    }
}
