/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGPred;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGPredOct;

public class MPEGPredDbl
extends MPEGPredOct {
    public MPEGPredDbl(MPEGPred other) {
        super(other);
    }

    @Override
    public void predictPlane(byte[] ref, int refX, int refY, int refW, int refH, int refVertStep, int refVertOff, int[] tgt, int tgtY, int tgtW, int tgtH, int tgtVertStep) {
        super.predictPlane(ref, refX << 1, refY << 1, refW, refH, refVertStep, refVertOff, tgt, tgtY, tgtW << 2, tgtH << 2, tgtVertStep);
    }
}
