/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.api.Protection;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.Box;

private static class Protection.UnknownProtection
extends Protection {
    Protection.UnknownProtection(Box sinf) {
        super(sinf);
    }

    @Override
    public Protection.Scheme getScheme() {
        return Protection.Scheme.UNKNOWN;
    }
}
