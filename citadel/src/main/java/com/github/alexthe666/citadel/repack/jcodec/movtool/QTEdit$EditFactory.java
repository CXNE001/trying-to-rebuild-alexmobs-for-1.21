/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;
import java.util.List;

public static interface QTEdit.EditFactory {
    public String getName();

    public MP4Edit parseArgs(List<String> var1);

    public String getHelp();
}
