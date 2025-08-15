/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.prores;

import com.github.alexthe666.citadel.repack.jcodec.codecs.prores.ProresConsts;

public static final class ProresEncoder.Profile {
    public static final ProresEncoder.Profile PROXY = new ProresEncoder.Profile("PROXY", ProresConsts.QMAT_LUMA_APCO, ProresConsts.QMAT_CHROMA_APCO, "apco", 1000, 4, 8);
    public static final ProresEncoder.Profile LT = new ProresEncoder.Profile("LT", ProresConsts.QMAT_LUMA_APCS, ProresConsts.QMAT_CHROMA_APCS, "apcs", 2100, 1, 9);
    public static final ProresEncoder.Profile STANDARD = new ProresEncoder.Profile("STANDARD", ProresConsts.QMAT_LUMA_APCN, ProresConsts.QMAT_CHROMA_APCN, "apcn", 3500, 1, 6);
    public static final ProresEncoder.Profile HQ = new ProresEncoder.Profile("HQ", ProresConsts.QMAT_LUMA_APCH, ProresConsts.QMAT_CHROMA_APCH, "apch", 5400, 1, 6);
    private static final ProresEncoder.Profile[] _values = new ProresEncoder.Profile[]{PROXY, LT, STANDARD, HQ};
    final String name;
    final int[] qmatLuma;
    final int[] qmatChroma;
    public final String fourcc;
    final int bitrate;
    final int firstQp;
    final int lastQp;

    public static ProresEncoder.Profile[] values() {
        return _values;
    }

    public static ProresEncoder.Profile valueOf(String name) {
        String nameU = name.toUpperCase();
        for (ProresEncoder.Profile profile2 : _values) {
            if (!name.equals(nameU)) continue;
            return profile2;
        }
        return null;
    }

    private ProresEncoder.Profile(String name, int[] qmatLuma, int[] qmatChroma, String fourcc, int bitrate, int firstQp, int lastQp) {
        this.name = name;
        this.qmatLuma = qmatLuma;
        this.qmatChroma = qmatChroma;
        this.fourcc = fourcc;
        this.bitrate = bitrate;
        this.firstQp = firstQp;
        this.lastQp = lastQp;
    }
}
