/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.bitstream;

import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;

public static class SequenceDisplayExtension.ColorDescription {
    int colour_primaries;
    int transfer_characteristics;
    int matrix_coefficients;

    public static SequenceDisplayExtension.ColorDescription read(BitReader _in) {
        SequenceDisplayExtension.ColorDescription cd = new SequenceDisplayExtension.ColorDescription();
        cd.colour_primaries = _in.readNBit(8);
        cd.transfer_characteristics = _in.readNBit(8);
        cd.matrix_coefficients = _in.readNBit(8);
        return cd;
    }

    public void write(BitWriter out) {
        out.writeNBit(this.colour_primaries, 8);
        out.writeNBit(this.transfer_characteristics, 8);
        out.writeNBit(this.matrix_coefficients, 8);
    }
}
