/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.write;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.RefPicMarking;

static class SliceHeaderWriter.1 {
    static final /* synthetic */ int[] $SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType;

    static {
        $SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType = new int[RefPicMarking.InstrType.values().length];
        try {
            SliceHeaderWriter.1.$SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType[RefPicMarking.InstrType.REMOVE_SHORT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SliceHeaderWriter.1.$SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType[RefPicMarking.InstrType.REMOVE_LONG.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SliceHeaderWriter.1.$SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType[RefPicMarking.InstrType.CONVERT_INTO_LONG.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SliceHeaderWriter.1.$SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType[RefPicMarking.InstrType.TRUNK_LONG.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SliceHeaderWriter.1.$SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType[RefPicMarking.InstrType.CLEAR.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SliceHeaderWriter.1.$SwitchMap$org$jcodec$codecs$h264$io$model$RefPicMarking$InstrType[RefPicMarking.InstrType.MARK_LONG.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
