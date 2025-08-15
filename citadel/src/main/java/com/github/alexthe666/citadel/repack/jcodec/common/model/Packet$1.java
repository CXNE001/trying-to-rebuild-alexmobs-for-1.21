/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.model;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.util.Comparator;

static final class Packet.1
implements Comparator<Packet> {
    Packet.1() {
    }

    @Override
    public int compare(Packet o1, Packet o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.frameNo < o2.frameNo ? -1 : (o1.frameNo == o2.frameNo ? 0 : 1);
    }
}
