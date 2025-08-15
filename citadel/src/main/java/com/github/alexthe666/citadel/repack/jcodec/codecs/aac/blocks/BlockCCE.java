/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.aac.blocks;

import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.BlockType;
import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.blocks.AACTab;
import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.blocks.Block;
import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.blocks.BlockICS;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLC;

public class BlockCCE
extends Block {
    private int coupling_point;
    private int num_coupled;
    private BlockType[] type;
    private int[] id_select;
    private int[] ch_select;
    private int sign;
    private Object scale;
    private Object[] cce_scale;
    private BlockICS blockICS;
    private BlockICS.BandType[] bandType;
    static VLC vlc = new VLC(AACTab.ff_aac_scalefactor_code, AACTab.ff_aac_scalefactor_bits);

    public BlockCCE(BlockICS.BandType[] bandType) {
        this.bandType = bandType;
    }

    @Override
    public void parse(BitReader _in) {
        int c;
        int num_gain = 0;
        this.coupling_point = 2 * _in.read1Bit();
        this.num_coupled = _in.readNBit(3);
        for (c = 0; c <= this.num_coupled; ++c) {
            ++num_gain;
            this.type[c] = _in.read1Bit() != 0 ? BlockType.TYPE_CPE : BlockType.TYPE_SCE;
            this.id_select[c] = _in.readNBit(4);
            if (this.type[c] == BlockType.TYPE_CPE) {
                this.ch_select[c] = _in.readNBit(2);
                if (this.ch_select[c] != 3) continue;
                ++num_gain;
                continue;
            }
            this.ch_select[c] = 2;
        }
        this.coupling_point += _in.read1Bit() | this.coupling_point >> 1;
        this.sign = _in.read1Bit();
        this.scale = this.cce_scale[_in.readNBit(2)];
        this.blockICS = new BlockICS();
        this.blockICS.parse(_in);
        for (c = 0; c < num_gain; ++c) {
            int idx = 0;
            int cge = 1;
            int gain = 0;
            if (c != 0) {
                cge = this.coupling_point == CouplingPoint.AFTER_IMDCT.ordinal() ? 1 : _in.read1Bit();
                int n = gain = cge != 0 ? vlc.readVLC(_in) - 60 : 0;
            }
            if (this.coupling_point == CouplingPoint.AFTER_IMDCT.ordinal()) continue;
            for (int g = 0; g < this.blockICS.num_window_groups; ++g) {
                int sfb = 0;
                while (sfb < this.blockICS.maxSfb) {
                    if (this.bandType[idx] != BlockICS.BandType.ZERO_BT && cge == 0) {
                        int n = vlc.readVLC(_in) - 60;
                    }
                    ++sfb;
                    ++idx;
                }
            }
        }
    }

    static enum CouplingPoint {
        BEFORE_TNS,
        BETWEEN_TNS_AND_IMDCT,
        UNDEF,
        AFTER_IMDCT;

    }
}
