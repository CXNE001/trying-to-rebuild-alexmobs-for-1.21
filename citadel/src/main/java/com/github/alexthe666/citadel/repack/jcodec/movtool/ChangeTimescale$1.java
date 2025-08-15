/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MediaHeaderBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieFragmentBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;

static final class ChangeTimescale.1
implements MP4Edit {
    final /* synthetic */ int val$ts;

    ChangeTimescale.1(int n) {
        this.val$ts = n;
    }

    @Override
    public void apply(MovieBox mov) {
        TrakBox vt = mov.getVideoTrack();
        MediaHeaderBox mdhd = NodeBox.findFirstPath(vt, MediaHeaderBox.class, Box.path("mdia.mdhd"));
        int oldTs = mdhd.getTimescale();
        if (oldTs > this.val$ts) {
            throw new RuntimeException("Old timescale (" + oldTs + ") is greater then new timescale (" + this.val$ts + "), not touching.");
        }
        vt.fixMediaTimescale(this.val$ts);
        mov.fixTimescale(this.val$ts);
    }

    @Override
    public void applyToFragment(MovieBox mov, MovieFragmentBox[] fragmentBox) {
        throw new RuntimeException("Unsupported");
    }
}
