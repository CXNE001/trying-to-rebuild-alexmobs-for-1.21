/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.filterbank;

import com.github.alexthe666.citadel.repack.jaad.aac.syntax.ICSInfo;

static class FilterBank.1 {
    static final /* synthetic */ int[] $SwitchMap$net$sourceforge$jaad$aac$syntax$ICSInfo$WindowSequence;

    static {
        $SwitchMap$net$sourceforge$jaad$aac$syntax$ICSInfo$WindowSequence = new int[ICSInfo.WindowSequence.values().length];
        try {
            FilterBank.1.$SwitchMap$net$sourceforge$jaad$aac$syntax$ICSInfo$WindowSequence[ICSInfo.WindowSequence.ONLY_LONG_SEQUENCE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FilterBank.1.$SwitchMap$net$sourceforge$jaad$aac$syntax$ICSInfo$WindowSequence[ICSInfo.WindowSequence.LONG_START_SEQUENCE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FilterBank.1.$SwitchMap$net$sourceforge$jaad$aac$syntax$ICSInfo$WindowSequence[ICSInfo.WindowSequence.EIGHT_SHORT_SEQUENCE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FilterBank.1.$SwitchMap$net$sourceforge$jaad$aac$syntax$ICSInfo$WindowSequence[ICSInfo.WindowSequence.LONG_STOP_SEQUENCE.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
