/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.AC3DecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.AMRDecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.AVCDecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.EAC3DecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.EVRCDecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.H263DecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.QCELPDecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.api.codec.SMVDecoderInfo;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.sampleentries.codec.CodecSpecificBox;

public abstract class DecoderInfo {
    static DecoderInfo parse(CodecSpecificBox css) {
        long l = css.getType();
        DecoderInfo info = l == 1681012275L ? new H263DecoderInfo(css) : (l == 1684106610L ? new AMRDecoderInfo(css) : (l == 1684371043L ? new EVRCDecoderInfo(css) : (l == 1685152624L ? new QCELPDecoderInfo(css) : (l == 1685286262L ? new SMVDecoderInfo(css) : (l == 1635148611L ? new AVCDecoderInfo(css) : (l == 1684103987L ? new AC3DecoderInfo(css) : (l == 1684366131L ? new EAC3DecoderInfo(css) : new UnknownDecoderInfo())))))));
        return info;
    }

    private static class UnknownDecoderInfo
    extends DecoderInfo {
        private UnknownDecoderInfo() {
        }
    }
}
