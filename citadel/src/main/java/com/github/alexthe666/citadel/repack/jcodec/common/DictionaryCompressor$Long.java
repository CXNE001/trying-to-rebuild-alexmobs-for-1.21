/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.DictionaryCompressor;
import com.github.alexthe666.citadel.repack.jcodec.common.RunLength;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLC;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.nio.ByteBuffer;
import java.util.Arrays;

public static class DictionaryCompressor.Long
extends DictionaryCompressor {
    public void compress(long[] values, ByteBuffer bb) {
        RunLength.Long rl = this.getValueStats(values);
        int[] counts = rl.getCounts();
        long[] keys = rl.getValues();
        VLC vlc = this.buildCodes(counts, values.length / 10);
        int[] codes = vlc.getCodes();
        int[] codeSizes = vlc.getCodeSizes();
        bb.putInt(codes.length);
        for (int i = 0; i < codes.length; ++i) {
            bb.put((byte)codeSizes[i]);
            bb.putShort((short)(codes[i] >>> 16));
            bb.putLong(keys[i]);
        }
        BitWriter br = new BitWriter(bb);
        for (int j = 0; j < values.length; ++j) {
            long l = values[j];
            for (int i = 0; i < keys.length; ++i) {
                if (keys[i] != l) continue;
                vlc.writeVLC(br, i);
                if (codes[i] != 15) continue;
                br.writeNBit(16, i);
            }
        }
        br.flush();
    }

    private RunLength.Long getValueStats(long[] values) {
        long[] copy = Platform.copyOfLong(values, values.length);
        Arrays.sort(copy);
        RunLength.Long rl = new RunLength.Long();
        for (int i = 0; i < copy.length; ++i) {
            long l = copy[i];
            rl.add(l);
        }
        return rl;
    }
}
