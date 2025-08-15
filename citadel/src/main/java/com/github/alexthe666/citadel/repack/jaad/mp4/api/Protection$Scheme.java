/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

public static enum Protection.Scheme {
    ITUNES_FAIR_PLAY(1769239918L),
    UNKNOWN(-1L);

    private long type;

    private Protection.Scheme(long type) {
        this.type = type;
    }
}
