/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.common.Tuple;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.MP4Util;

class InplaceMP4Editor.1
implements Tuple.Mapper<MP4Util.Atom, Long> {
    InplaceMP4Editor.1() {
    }

    @Override
    public Long map(MP4Util.Atom t) {
        return t.getOffset();
    }
}
