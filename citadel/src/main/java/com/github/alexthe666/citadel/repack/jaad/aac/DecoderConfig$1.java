/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac;

import com.github.alexthe666.citadel.repack.jaad.aac.Profile;

static class DecoderConfig.1 {
    static final /* synthetic */ int[] $SwitchMap$net$sourceforge$jaad$aac$Profile;

    static {
        $SwitchMap$net$sourceforge$jaad$aac$Profile = new int[Profile.values().length];
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.AAC_SBR.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.AAC_MAIN.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.AAC_LC.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.AAC_SSR.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.AAC_LTP.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.ER_AAC_LC.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.ER_AAC_LTP.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            DecoderConfig.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.ER_AAC_LD.ordinal()] = 8;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
