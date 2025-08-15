/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.Frame;
import java.util.Comparator;

static final class Frame.2
implements Comparator<Frame> {
    Frame.2() {
    }

    @Override
    public int compare(Frame o1, Frame o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        return o1.poc < o2.poc ? 1 : (o1.poc == o2.poc ? 0 : -1);
    }
}
