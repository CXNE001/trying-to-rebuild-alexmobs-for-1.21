/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl;

import com.github.alexthe666.citadel.repack.jaad.mp4.MP4InputStream;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.ItemInformationEntry;
import java.io.IOException;

public static abstract class ItemInformationEntry.Extension {
    private static final int TYPE_FDEL = 1717855596;

    static ItemInformationEntry.Extension forType(int type) {
        return switch (type) {
            case 1717855596 -> new ItemInformationEntry.FDExtension();
            default -> null;
        };
    }

    abstract void decode(MP4InputStream var1) throws IOException;
}
