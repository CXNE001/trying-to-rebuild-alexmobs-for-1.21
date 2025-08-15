/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.muxer;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.util.Comparator;

class TimecodeMP4MuxerTrack.1
implements Comparator<Packet> {
    TimecodeMP4MuxerTrack.1() {
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
        return o1.getDisplayOrder() > o2.getDisplayOrder() ? 1 : (o1.getDisplayOrder() == o2.getDisplayOrder() ? 0 : -1);
    }
}
