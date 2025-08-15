/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

import com.github.alexthe666.citadel.repack.jaad.aac.Profile;

static class ICSInfo.1 {
    static final /* synthetic */ int[] $SwitchMap$net$sourceforge$jaad$aac$Profile;

    static {
        $SwitchMap$net$sourceforge$jaad$aac$Profile = new int[Profile.values().length];
        try {
            ICSInfo.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.AAC_MAIN.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ICSInfo.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.AAC_LTP.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ICSInfo.1.$SwitchMap$net$sourceforge$jaad$aac$Profile[Profile.ER_AAC_LTP.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
