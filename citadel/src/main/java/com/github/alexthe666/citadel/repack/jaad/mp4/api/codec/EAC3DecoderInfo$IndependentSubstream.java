/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api.codec;

import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.EAC3DecoderInfo;
import java.util.ArrayList;

public class EAC3DecoderInfo.IndependentSubstream {
    private final int index;
    private final EAC3DecoderInfo.DependentSubstream[] dependentSubstreams;

    private EAC3DecoderInfo.IndependentSubstream(int index) {
        this.index = index;
        int loc = EAC3DecoderInfo.this.box.getDependentSubstreamLocation()[index];
        ArrayList<EAC3DecoderInfo.DependentSubstream> list = new ArrayList<EAC3DecoderInfo.DependentSubstream>();
        for (int i = 0; i < 9; ++i) {
            if ((loc >> 8 - i & 1) != 1) continue;
            list.add(EAC3DecoderInfo.DependentSubstream.values()[i]);
        }
        this.dependentSubstreams = list.toArray(new EAC3DecoderInfo.DependentSubstream[list.size()]);
    }

    public int getFscod() {
        return EAC3DecoderInfo.this.box.getFscods()[this.index];
    }

    public int getBsid() {
        return EAC3DecoderInfo.this.box.getBsids()[this.index];
    }

    public int getBsmod() {
        return EAC3DecoderInfo.this.box.getBsmods()[this.index];
    }

    public int getAcmod() {
        return EAC3DecoderInfo.this.box.getAcmods()[this.index];
    }

    public boolean isLfeon() {
        return EAC3DecoderInfo.this.box.getLfeons()[this.index];
    }

    public EAC3DecoderInfo.DependentSubstream[] getDependentSubstreams() {
        return this.dependentSubstreams;
    }
}
