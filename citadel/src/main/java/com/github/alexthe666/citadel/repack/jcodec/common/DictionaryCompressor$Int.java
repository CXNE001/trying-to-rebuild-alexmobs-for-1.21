/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.DictionaryCompressor;
import com.github.alexthe666.citadel.repack.jcodec.common.RunLength;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLC;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.nio.ByteBuffer;
import java.util.Arrays;

public static class DictionaryCompressor.Int
extends DictionaryCompressor {
    public void compress(int[] values, ByteBuffer bb) {
        RunLength.Integer rl = this.getValueStats(values);
        int[] counts = rl.getCounts();
        int[] keys = rl.getValues();
        int esc = Math.max(1, (1 << MathUtil.log2(counts.length) - 2) - 1);
        VLC vlc = this.buildCodes(counts, esc);
        int[] codes = vlc.getCodes();
        int[] codeSizes = vlc.getCodeSizes();
        bb.putInt(codes.length);
        for (int i = 0; i < codes.length; ++i) {
            bb.put((byte)codeSizes[i]);
            bb.putShort((short)(codes[i] >>> 16));
            bb.putInt(keys[i]);
        }
        BitWriter br = new BitWriter(bb);
        for (int j = 0; j < values.length; ++j) {
            int l = values[j];
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] != l) continue;
                vlc.writeVLC(br, i);
                if (codes[i] != esc) continue;
                br.writeNBit(i, 16);
            }
        }
        br.flush();
    }

    private RunLength.Integer getValueStats(int[] values) {
        int[] copy = Platform.copyOfInt(values, values.length);
        Arrays.sort(copy);
        RunLength.Integer rl = new RunLength.Integer();
        for (int i = 0; i < copy.length; ++i) {
            int l = copy[i];
            rl.add(l);
        }
        return rl;
    }
}
