/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.bitstream;

import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;

public static class PictureCodingExtension.CompositeDisplay {
    public int v_axis;
    public int field_sequence;
    public int sub_carrier;
    public int burst_amplitude;
    public int sub_carrier_phase;

    public static PictureCodingExtension.CompositeDisplay read(BitReader _in) {
        PictureCodingExtension.CompositeDisplay cd = new PictureCodingExtension.CompositeDisplay();
        cd.v_axis = _in.read1Bit();
        cd.field_sequence = _in.readNBit(3);
        cd.sub_carrier = _in.read1Bit();
        cd.burst_amplitude = _in.readNBit(7);
        cd.sub_carrier_phase = _in.readNBit(8);
        return cd;
    }

    public void write(BitWriter out) {
        out.write1Bit(this.v_axis);
        out.writeNBit(this.field_sequence, 3);
        out.write1Bit(this.sub_carrier);
        out.writeNBit(this.burst_amplitude, 7);
        out.writeNBit(this.sub_carrier_phase, 8);
    }
}
