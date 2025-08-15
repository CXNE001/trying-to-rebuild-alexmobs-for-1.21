/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpa;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpa.MpaConst;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpa.MpaHeader;
import com.github.alexthe666.citadel.repack.jcodec.common.Vector2Int;
import com.github.alexthe666.citadel.repack.jcodec.common.Vector4Int;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;

public class Mp3Bitstream {
    static MP3SideInfo readSideInfo(MpaHeader header, ByteBuffer src, int channels) {
        MP3SideInfo si = new MP3SideInfo();
        BitReader stream = BitReader.createBitReader(src);
        if (header.version == 1) {
            si.mainDataBegin = stream.readNBit(9);
            si.privateBits = channels == 1 ? stream.readNBit(5) : stream.readNBit(3);
            for (int ch = 0; ch < channels; ++ch) {
                si.scfsi[ch][0] = stream.read1Bit() == 0;
                si.scfsi[ch][1] = stream.read1Bit() == 0;
                si.scfsi[ch][2] = stream.read1Bit() == 0;
                si.scfsi[ch][3] = stream.read1Bit() == 0;
            }
            for (int gr = 0; gr < 2; ++gr) {
                for (int ch = 0; ch < channels; ++ch) {
                    Granule granule = si.granule[ch][gr];
                    granule.part23Length = stream.readNBit(12);
                    granule.bigValues = stream.readNBit(9);
                    granule.globalGain = stream.readNBit(8);
                    granule.scalefacCompress = stream.readNBit(4);
                    boolean bl = granule.windowSwitchingFlag = stream.readNBit(1) != 0;
                    if (granule.windowSwitchingFlag) {
                        granule.blockType = stream.readNBit(2);
                        granule.mixedBlockFlag = stream.readNBit(1) != 0;
                        granule.tableSelect[0] = stream.readNBit(5);
                        granule.tableSelect[1] = stream.readNBit(5);
                        granule.subblockGain[0] = stream.readNBit(3);
                        granule.subblockGain[1] = stream.readNBit(3);
                        granule.subblockGain[2] = stream.readNBit(3);
                        if (granule.blockType == 0) {
                            return null;
                        }
                        granule.region0Count = granule.blockType == 2 && !granule.mixedBlockFlag ? 8 : 7;
                        granule.region1Count = 20 - granule.region0Count;
                    } else {
                        granule.tableSelect[0] = stream.readNBit(5);
                        granule.tableSelect[1] = stream.readNBit(5);
                        granule.tableSelect[2] = stream.readNBit(5);
                        granule.region0Count = stream.readNBit(4);
                        granule.region1Count = stream.readNBit(3);
                        granule.blockType = 0;
                    }
                    granule.preflag = stream.readNBit(1) != 0;
                    granule.scalefacScale = stream.readNBit(1);
                    granule.count1tableSelect = stream.readNBit(1);
                }
            }
        } else {
            si.mainDataBegin = stream.readNBit(8);
            si.privateBits = channels == 1 ? stream.readNBit(1) : stream.readNBit(2);
            for (int ch = 0; ch < channels; ++ch) {
                Granule granule = si.granule[ch][0];
                granule.part23Length = stream.readNBit(12);
                granule.bigValues = stream.readNBit(9);
                granule.globalGain = stream.readNBit(8);
                granule.scalefacCompress = stream.readNBit(9);
                boolean bl = granule.windowSwitchingFlag = stream.readNBit(1) != 0;
                if (granule.windowSwitchingFlag) {
                    granule.blockType = stream.readNBit(2);
                    granule.mixedBlockFlag = stream.readNBit(1) != 0;
                    granule.tableSelect[0] = stream.readNBit(5);
                    granule.tableSelect[1] = stream.readNBit(5);
                    granule.subblockGain[0] = stream.readNBit(3);
                    granule.subblockGain[1] = stream.readNBit(3);
                    granule.subblockGain[2] = stream.readNBit(3);
                    if (granule.blockType == 0) {
                        return null;
                    }
                    if (granule.blockType == 2 && !granule.mixedBlockFlag) {
                        granule.region0Count = 8;
                    } else {
                        granule.region0Count = 7;
                        granule.region1Count = 20 - granule.region0Count;
                    }
                } else {
                    granule.tableSelect[0] = stream.readNBit(5);
                    granule.tableSelect[1] = stream.readNBit(5);
                    granule.tableSelect[2] = stream.readNBit(5);
                    granule.region0Count = stream.readNBit(4);
                    granule.region1Count = stream.readNBit(3);
                    granule.blockType = 0;
                }
                granule.scalefacScale = stream.readNBit(1);
                granule.count1tableSelect = stream.readNBit(1);
            }
        }
        stream.terminate();
        return si;
    }

    static ScaleFactors readScaleFactors(BitReader br, Granule granule, boolean[] b) {
        if (granule.windowSwitchingFlag && granule.blockType == 2) {
            if (granule.mixedBlockFlag) {
                return Mp3Bitstream.readScaleFacMixed(br, granule);
            }
            return Mp3Bitstream.readScaleFacShort(br, granule);
        }
        return Mp3Bitstream.readScaleFacNonSwitch(br, granule, b);
    }

    private static ScaleFactors readScaleFacMixed(BitReader br, Granule granule) {
        int window;
        int sfb;
        ScaleFactors sf = new ScaleFactors();
        for (sfb = 0; sfb < 8; ++sfb) {
            sf.large[sfb] = br.readNBit(MpaConst.scaleFactorLen[0][granule.scalefacCompress]);
        }
        for (sfb = 3; sfb < 6; ++sfb) {
            for (window = 0; window < 3; ++window) {
                sf.small[window][sfb] = br.readNBit(MpaConst.scaleFactorLen[0][granule.scalefacCompress]);
            }
        }
        for (sfb = 6; sfb < 12; ++sfb) {
            for (window = 0; window < 3; ++window) {
                sf.small[window][sfb] = br.readNBit(MpaConst.scaleFactorLen[1][granule.scalefacCompress]);
            }
        }
        sfb = 12;
        for (window = 0; window < 3; ++window) {
            sf.small[window][sfb] = 0;
        }
        return sf;
    }

    private static ScaleFactors readScaleFacNonSwitch(BitReader br, Granule granule, boolean[] b) {
        int i;
        ScaleFactors sf = new ScaleFactors();
        int length0 = MpaConst.scaleFactorLen[0][granule.scalefacCompress];
        int length1 = MpaConst.scaleFactorLen[1][granule.scalefacCompress];
        if (b[0]) {
            for (i = 0; i < 6; ++i) {
                sf.large[i] = br.readNBit(length0);
            }
        }
        if (b[1]) {
            for (i = 6; i < 11; ++i) {
                sf.large[i] = br.readNBit(length0);
            }
        }
        if (b[2]) {
            for (i = 11; i < 16; ++i) {
                sf.large[i] = br.readNBit(length1);
            }
        }
        if (b[3]) {
            for (i = 16; i < 21; ++i) {
                sf.large[i] = br.readNBit(length1);
            }
        }
        sf.large[21] = 0;
        sf.large[22] = 0;
        return sf;
    }

    private static ScaleFactors readScaleFacShort(BitReader br, Granule granule) {
        int j;
        int i;
        ScaleFactors sf = new ScaleFactors();
        int length0 = MpaConst.scaleFactorLen[0][granule.scalefacCompress];
        int length1 = MpaConst.scaleFactorLen[1][granule.scalefacCompress];
        for (i = 0; i < 6; ++i) {
            for (j = 0; j < 3; ++j) {
                sf.small[j][i] = br.readNBit(length0);
            }
        }
        for (i = 6; i < 12; ++i) {
            for (j = 0; j < 3; ++j) {
                sf.small[j][i] = br.readNBit(length1);
            }
        }
        sf.small[0][12] = 0;
        sf.small[1][12] = 0;
        sf.small[2][12] = 0;
        return sf;
    }

    static ScaleFactors readLSFScaleFactors(BitReader br, MpaHeader header, Granule granule, int ch) {
        ScaleFactors scalefac = new ScaleFactors();
        int[] scalefacBuffer = Mp3Bitstream.readLSFScaleData(br, header, granule, ch);
        int m = 0;
        if (granule.windowSwitchingFlag && granule.blockType == 2) {
            if (granule.mixedBlockFlag) {
                int sfb;
                for (sfb = 0; sfb < 8; ++sfb) {
                    scalefac.large[sfb] = scalefacBuffer[m];
                    ++m;
                }
                for (sfb = 3; sfb < 12; ++sfb) {
                    for (int window = 0; window < 3; ++window) {
                        scalefac.small[window][sfb] = scalefacBuffer[m];
                        ++m;
                    }
                }
                for (int window = 0; window < 3; ++window) {
                    scalefac.small[window][12] = 0;
                }
            } else {
                for (int sfb = 0; sfb < 12; ++sfb) {
                    for (int window = 0; window < 3; ++window) {
                        scalefac.small[window][sfb] = scalefacBuffer[m];
                        ++m;
                    }
                }
                for (int window = 0; window < 3; ++window) {
                    scalefac.small[window][12] = 0;
                }
            }
        } else {
            for (int sfb = 0; sfb < 21; ++sfb) {
                scalefac.large[sfb] = scalefacBuffer[m];
                ++m;
            }
            scalefac.large[21] = 0;
            scalefac.large[22] = 0;
        }
        return scalefac;
    }

    private static int[] readLSFScaleData(BitReader br, MpaHeader header, Granule granule, int ch) {
        int[] result = new int[54];
        int[] scaleFacLen = new int[4];
        int comp = granule.scalefacCompress;
        int blockType = granule.blockType == 2 ? (granule.mixedBlockFlag ? 2 : 1) : 0;
        boolean ch1 = (header.modeExtension == 1 || header.modeExtension == 3) && ch == 1;
        int lenType = 0;
        if (!ch1) {
            if (comp < 400) {
                scaleFacLen[0] = (comp >>> 4) / 5;
                scaleFacLen[1] = (comp >>> 4) % 5;
                scaleFacLen[2] = (comp & 0xF) >>> 2;
                scaleFacLen[3] = comp & 3;
                granule.preflag = false;
                lenType = 0;
            } else if (comp < 500) {
                scaleFacLen[0] = (comp - 400 >>> 2) / 5;
                scaleFacLen[1] = (comp - 400 >>> 2) % 5;
                scaleFacLen[2] = comp - 400 & 3;
                scaleFacLen[3] = 0;
                granule.preflag = false;
                lenType = 1;
            } else if (comp < 512) {
                scaleFacLen[0] = (comp - 500) / 3;
                scaleFacLen[1] = (comp - 500) % 3;
                scaleFacLen[2] = 0;
                scaleFacLen[3] = 0;
                granule.preflag = true;
                lenType = 2;
            }
        } else {
            int halfComp = comp >>> 1;
            if (halfComp < 180) {
                scaleFacLen[0] = halfComp / 36;
                scaleFacLen[1] = halfComp % 36 / 6;
                scaleFacLen[2] = halfComp % 36 % 6;
                scaleFacLen[3] = 0;
                granule.preflag = false;
                lenType = 3;
            } else if (halfComp < 244) {
                scaleFacLen[0] = (halfComp - 180 & 0x3F) >>> 4;
                scaleFacLen[1] = (halfComp - 180 & 0xF) >>> 2;
                scaleFacLen[2] = halfComp - 180 & 3;
                scaleFacLen[3] = 0;
                granule.preflag = false;
                lenType = 4;
            } else if (halfComp < 255) {
                scaleFacLen[0] = (halfComp - 244) / 3;
                scaleFacLen[1] = (halfComp - 244) % 3;
                scaleFacLen[2] = 0;
                scaleFacLen[3] = 0;
                granule.preflag = false;
                lenType = 5;
            }
        }
        int m = 0;
        for (int i = 0; i < 4; ++i) {
            int j = 0;
            while (j < MpaConst.numberOfScaleFactors[lenType][blockType][i]) {
                result[m] = scaleFacLen[i] == 0 ? 0 : br.readNBit(scaleFacLen[i]);
                ++j;
                ++m;
            }
        }
        return result;
    }

    static int readCoeffs(BitReader br, Granule granule, int ch, int part2_start, int sfreq, int[] out) {
        int part23End = part2_start + granule.part23Length;
        int region1Start = sfreq == 8 ? 72 : 36;
        int region2Start = 576;
        if (!granule.windowSwitchingFlag || granule.blockType != 2) {
            int region1StartIdx = MathUtil.clip(granule.region0Count + granule.region1Count + 2, 0, MpaConst.sfbLong[sfreq].length - 1);
            region1Start = MpaConst.sfbLong[sfreq][granule.region0Count + 1];
            region2Start = MpaConst.sfbLong[sfreq][region1StartIdx];
        }
        int index = 0;
        for (int i = 0; i < granule.bigValues << 1; i += 2) {
            int tab = 0;
            tab = i < region1Start ? granule.tableSelect[0] : (i < region2Start ? granule.tableSelect[1] : granule.tableSelect[2]);
            if (tab == 0 || tab == 4 || tab == 14) {
                out[index++] = 0;
                out[index++] = 0;
                continue;
            }
            int packed = Mp3Bitstream.readBigVal(tab, br);
            out[index++] = Vector2Int.el16_0(packed);
            out[index++] = Vector2Int.el16_1(packed);
        }
        while (br.position() < part23End && index < 576) {
            int packed = Mp3Bitstream.readCount1(granule.count1tableSelect, br);
            out[index++] = Vector4Int.el8_0(packed);
            out[index++] = Vector4Int.el8_1(packed);
            out[index++] = Vector4Int.el8_2(packed);
            out[index++] = Vector4Int.el8_3(packed);
        }
        if (br.position() < part23End) {
            br.readNBit(part23End - br.position());
        }
        return MathUtil.clip(index, 0, 576);
    }

    static int readBigVal(int tab, BitReader br) {
        int res = MpaConst.bigValVlc[tab].readVLC(br);
        int x = res >>> 4;
        int y = res & 0xF;
        if (MpaConst.bigValEscBits[tab] != 0 && MpaConst.bigValMaxval[tab] - 1 == x) {
            x += br.readNBit(MpaConst.bigValEscBits[tab]);
        }
        if (x != 0 && br.read1Bit() != 0) {
            x = -x;
        }
        if (MpaConst.bigValEscBits[tab] != 0 && MpaConst.bigValMaxval[tab] - 1 == y) {
            y += br.readNBit(MpaConst.bigValEscBits[tab]);
        }
        if (y != 0 && br.read1Bit() != 0) {
            y = -y;
        }
        return Vector2Int.pack16(x, y);
    }

    static int readCount1(int tab, BitReader br) {
        int res = (tab == 0 ? MpaConst.cnt1A : MpaConst.cnt1B).readVLC(br);
        int v = res >> 3 & 1;
        int w = res >> 2 & 1;
        int x = res >> 1 & 1;
        int y = res & 1;
        if (v != 0 && br.read1Bit() != 0) {
            v = -v;
        }
        if (w != 0 && br.read1Bit() != 0) {
            w = -w;
        }
        if (x != 0 && br.read1Bit() != 0) {
            x = -x;
        }
        if (y != 0 && br.read1Bit() != 0) {
            y = -y;
        }
        return Vector4Int.pack8(v, w, x, y);
    }

    static class ScaleFactors {
        int[] large = new int[23];
        int[][] small = new int[3][13];

        ScaleFactors() {
        }
    }

    static class Granule {
        int part23Length;
        int bigValues;
        int globalGain;
        int scalefacCompress;
        boolean windowSwitchingFlag;
        int blockType;
        boolean mixedBlockFlag;
        int[] tableSelect = new int[3];
        int[] subblockGain = new int[3];
        int region0Count;
        int region1Count;
        boolean preflag;
        int scalefacScale;
        int count1tableSelect;

        Granule() {
        }
    }

    static class MP3SideInfo {
        int mainDataBegin;
        int privateBits;
        boolean[][] scfsi = new boolean[2][4];
        Granule[][] granule = new Granule[][]{{new Granule(), new Granule()}, {new Granule(), new Granule()}};

        MP3SideInfo() {
        }
    }
}
