/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import java.io.File;
import java.io.FilenameFilter;

static final class HLSFixPMT.1
implements FilenameFilter {
    HLSFixPMT.1() {
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".ts");
    }
}
