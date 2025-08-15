/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.aac;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ChannelLabel;

public static class AACUtils.AACMetadata {
    private AudioFormat format;
    private ChannelLabel[] labels;

    public AACUtils.AACMetadata(AudioFormat format, ChannelLabel[] labels) {
        this.format = format;
        this.labels = labels;
    }

    public AudioFormat getFormat() {
        return this.format;
    }

    public ChannelLabel[] getLabels() {
        return this.labels;
    }
}
