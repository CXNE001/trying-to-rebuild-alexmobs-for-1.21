/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpa;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpa.Mp3Bitstream;

static class Mp3Bitstream.MP3SideInfo {
    int mainDataBegin;
    int privateBits;
    boolean[][] scfsi = new boolean[2][4];
    Mp3Bitstream.Granule[][] granule = new Mp3Bitstream.Granule[][]{{new Mp3Bitstream.Granule(), new Mp3Bitstream.Granule()}, {new Mp3Bitstream.Granule(), new Mp3Bitstream.Granule()}};

    Mp3Bitstream.MP3SideInfo() {
    }
}
