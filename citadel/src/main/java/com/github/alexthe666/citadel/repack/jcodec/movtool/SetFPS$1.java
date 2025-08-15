/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.RationalLarge;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieFragmentBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TimeToSampleBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.TrakBox;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;

static final class SetFPS.1
implements MP4Edit {
    final /* synthetic */ RationalLarge val$newFPS;

    SetFPS.1(RationalLarge rationalLarge) {
        this.val$newFPS = rationalLarge;
    }

    @Override
    public void apply(MovieBox mov) {
        TrakBox vt = mov.getVideoTrack();
        TimeToSampleBox stts = vt.getStts();
        TimeToSampleBox.TimeToSampleEntry[] entries = stts.getEntries();
        long nSamples = 0L;
        long totalDuration = 0L;
        for (TimeToSampleBox.TimeToSampleEntry e : entries) {
            nSamples += (long)e.getSampleCount();
            totalDuration += (long)(e.getSampleCount() * e.getSampleDuration());
        }
        int newTimescale = (int)this.val$newFPS.multiply(new RationalLarge(totalDuration, nSamples)).scalarClip();
        if (newTimescale >= 25) {
            vt.setTimescale(newTimescale);
        } else {
            double mul = new RationalLarge((long)vt.getTimescale() * totalDuration, nSamples).divideBy(this.val$newFPS).scalar();
            Logger.info("Applying multiplier to sample durations: " + mul);
            for (TimeToSampleBox.TimeToSampleEntry e : entries) {
                e.setSampleDuration((int)((double)e.getSampleDuration() * mul * 100.0));
            }
            vt.setTimescale(vt.getTimescale() * 100);
        }
        if (newTimescale != vt.getTimescale()) {
            Logger.info("Changing timescale to: " + vt.getTimescale());
            long newDuration = totalDuration * (long)mov.getTimescale() / (long)vt.getTimescale();
            mov.setDuration(newDuration);
            vt.setDuration(newDuration);
        } else {
            Logger.info("Already at " + this.val$newFPS.toString() + "fps, not changing.");
        }
    }

    @Override
    public void applyToFragment(MovieBox mov, MovieFragmentBox[] fragmentBox) {
        throw new RuntimeException("Unsupported");
    }
}
