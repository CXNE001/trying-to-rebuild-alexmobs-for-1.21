/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;
import java.nio.channels.FileChannel;

public static abstract class QTEdit.BaseCommand
implements MP4Edit {
    public void applyRefs(MovieBox movie, FileChannel[][] refs) {
        this.apply(movie);
    }

    @Override
    public abstract void apply(MovieBox var1);
}
