/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.BoxUtil;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieFragmentBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SampleDescriptionBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.VideoSampleEntry;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;

static final class SetPAR.1
implements MP4Edit {
    final /* synthetic */ Rational val$newPAR;

    SetPAR.1(Rational rational) {
        this.val$newPAR = rational;
    }

    @Override
    public void apply(MovieBox mov) {
        TrakBox vt = mov.getVideoTrack();
        vt.setPAR(this.val$newPAR);
        Box box = NodeBox.findFirstPath(vt, SampleDescriptionBox.class, Box.path("mdia.minf.stbl.stsd")).getBoxes().get(0);
        if (box != null && box instanceof VideoSampleEntry) {
            VideoSampleEntry vs = (VideoSampleEntry)box;
            int codedWidth = vs.getWidth();
            int codedHeight = vs.getHeight();
            int displayWidth = codedWidth * this.val$newPAR.getNum() / this.val$newPAR.getDen();
            vt.getTrackHeader().setWidth(displayWidth);
            if (BoxUtil.containsBox(vt, "tapt")) {
                vt.setAperture(new Size(codedWidth, codedHeight), new Size(displayWidth, codedHeight));
            }
        }
    }

    @Override
    public void applyToFragment(MovieBox mov, MovieFragmentBox[] fragmentBox) {
        throw new RuntimeException("Unsupported");
    }
}
