/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx;

import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VP8Util;

static class VP8Util.1 {
    static final /* synthetic */ int[] $SwitchMap$org$jcodec$codecs$vpx$VP8Util$PLANE;

    static {
        $SwitchMap$org$jcodec$codecs$vpx$VP8Util$PLANE = new int[VP8Util.PLANE.values().length];
        try {
            VP8Util.1.$SwitchMap$org$jcodec$codecs$vpx$VP8Util$PLANE[VP8Util.PLANE.Y2.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            VP8Util.1.$SwitchMap$org$jcodec$codecs$vpx$VP8Util$PLANE[VP8Util.PLANE.Y1.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            VP8Util.1.$SwitchMap$org$jcodec$codecs$vpx$VP8Util$PLANE[VP8Util.PLANE.U.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            VP8Util.1.$SwitchMap$org$jcodec$codecs$vpx$VP8Util$PLANE[VP8Util.PLANE.V.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
