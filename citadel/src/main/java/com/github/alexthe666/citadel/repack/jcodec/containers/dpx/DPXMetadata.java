/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.dpx;

import com.github.alexthe666.citadel.repack.jcodec.common.StringUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.FileHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.FilmHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.ImageHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.ImageSourceHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.TelevisionHeader;

public class DPXMetadata {
    public static final String V2 = "V2.0";
    public static final String V1 = "V1.0";
    public FileHeader file;
    public ImageHeader image;
    public ImageSourceHeader imageSource;
    public FilmHeader film;
    public TelevisionHeader television;
    public String userId;

    private static String smpteTC(int tcsmpte, boolean prevent_dropframe) {
        int ff = DPXMetadata.bcd2uint(tcsmpte & 0x3F);
        int ss = DPXMetadata.bcd2uint(tcsmpte >> 8 & 0x7F);
        int mm = DPXMetadata.bcd2uint(tcsmpte >> 16 & 0x7F);
        int hh = DPXMetadata.bcd2uint(tcsmpte >> 24 & 0x3F);
        boolean drop = (long)(tcsmpte & 0x40000000) > 0L && !prevent_dropframe;
        return StringUtils.zeroPad2(hh) + ":" + StringUtils.zeroPad2(mm) + ":" + StringUtils.zeroPad2(ss) + (drop ? ";" : ":") + StringUtils.zeroPad2(ff);
    }

    private static int bcd2uint(int bcd) {
        int low = bcd & 0xF;
        int high = bcd >> 4;
        if (low > 9 || high > 9) {
            return 0;
        }
        return low + 10 * high;
    }

    public String getTimecodeString() {
        return DPXMetadata.smpteTC(this.television.timecode, false);
    }
}
