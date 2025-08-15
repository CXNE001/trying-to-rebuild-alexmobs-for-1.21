/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps.index;

import com.github.alexthe666.citadel.repack.jcodec.containers.mps.index.BaseIndexer;
import java.util.Comparator;

class BaseIndexer.MPEGVideoAnalyser.1
implements Comparator<BaseIndexer.MPEGVideoAnalyser.Frame> {
    BaseIndexer.MPEGVideoAnalyser.1() {
    }

    @Override
    public int compare(BaseIndexer.MPEGVideoAnalyser.Frame o1, BaseIndexer.MPEGVideoAnalyser.Frame o2) {
        return o1.tempRef > o2.tempRef ? 1 : (o1.tempRef == o2.tempRef ? 0 : -1);
    }
}
