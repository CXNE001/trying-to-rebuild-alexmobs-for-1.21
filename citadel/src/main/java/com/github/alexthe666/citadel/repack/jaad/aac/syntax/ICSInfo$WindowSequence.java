/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;

public static enum ICSInfo.WindowSequence {
    ONLY_LONG_SEQUENCE,
    LONG_START_SEQUENCE,
    EIGHT_SHORT_SEQUENCE,
    LONG_STOP_SEQUENCE;


    public static ICSInfo.WindowSequence forInt(int i) throws AACException {
        return switch (i) {
            case 0 -> ONLY_LONG_SEQUENCE;
            case 1 -> LONG_START_SEQUENCE;
            case 2 -> EIGHT_SHORT_SEQUENCE;
            case 3 -> LONG_STOP_SEQUENCE;
            default -> throw new AACException("unknown window sequence type");
        };
    }
}
