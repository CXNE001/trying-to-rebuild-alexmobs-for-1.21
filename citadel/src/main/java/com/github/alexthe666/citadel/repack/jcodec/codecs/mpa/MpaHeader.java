/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpa;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpa.MpaConst;
import java.nio.ByteBuffer;

class MpaHeader {
    int layer;
    int protectionBit;
    int bitrateIndex;
    int paddingBit;
    int modeExtension;
    int version;
    int mode;
    int sampleFreq;
    int numSubbands;
    int intensityStereoBound;
    boolean copyright;
    boolean original;
    int framesize;
    int frameBytes;

    MpaHeader() {
    }

    static MpaHeader read_header(ByteBuffer bb) {
        MpaHeader ret = new MpaHeader();
        int headerstring = bb.getInt();
        ret.version = headerstring >>> 19 & 1;
        if ((headerstring >>> 20 & 1) == 0) {
            if (ret.version == 0) {
                ret.version = 2;
            } else {
                throw new RuntimeException("UNKNOWN_ERROR");
            }
        }
        if ((ret.sampleFreq = headerstring >>> 10 & 3) == 3) {
            throw new RuntimeException("UNKNOWN_ERROR");
        }
        ret.layer = 4 - (headerstring >>> 17) & 3;
        ret.protectionBit = headerstring >>> 16 & 1;
        ret.bitrateIndex = headerstring >>> 12 & 0xF;
        ret.paddingBit = headerstring >>> 9 & 1;
        ret.mode = headerstring >>> 6 & 3;
        ret.modeExtension = headerstring >>> 4 & 3;
        ret.intensityStereoBound = ret.mode == 1 ? (ret.modeExtension << 2) + 4 : 0;
        if ((headerstring >>> 3 & 1) == 1) {
            ret.copyright = true;
        }
        if ((headerstring >>> 2 & 1) == 1) {
            ret.original = true;
        }
        if (ret.layer == 1) {
            ret.numSubbands = 32;
        } else {
            int channel_bitrate = ret.bitrateIndex;
            if (ret.mode != 3) {
                channel_bitrate = channel_bitrate == 4 ? 1 : (channel_bitrate -= 4);
            }
            ret.numSubbands = channel_bitrate == 1 || channel_bitrate == 2 ? (ret.sampleFreq == 2 ? 12 : 8) : (ret.sampleFreq == 1 || channel_bitrate >= 3 && channel_bitrate <= 5 ? 27 : 30);
        }
        if (ret.intensityStereoBound > ret.numSubbands) {
            ret.intensityStereoBound = ret.numSubbands;
        }
        MpaHeader.calculateFramesize(ret);
        return ret;
    }

    public static void calculateFramesize(MpaHeader ret) {
        if (ret.layer == 1) {
            ret.framesize = 12 * MpaConst.bitrates[ret.version][0][ret.bitrateIndex] / MpaConst.frequencies[ret.version][ret.sampleFreq];
            if (ret.paddingBit != 0) {
                ++ret.framesize;
            }
            ret.framesize <<= 2;
            ret.frameBytes = 0;
        } else {
            ret.framesize = 144 * MpaConst.bitrates[ret.version][ret.layer - 1][ret.bitrateIndex] / MpaConst.frequencies[ret.version][ret.sampleFreq];
            if (ret.version == 0 || ret.version == 2) {
                ret.framesize >>= 1;
            }
            if (ret.paddingBit != 0) {
                ++ret.framesize;
            }
            ret.frameBytes = ret.layer == 3 ? (ret.version == 1 ? ret.framesize - (ret.mode == 3 ? 17 : 32) - (ret.protectionBit != 0 ? 0 : 2) - 4 : ret.framesize - (ret.mode == 3 ? 9 : 17) - (ret.protectionBit != 0 ? 0 : 2) - 4) : 0;
        }
        ret.framesize -= 4;
    }
}
