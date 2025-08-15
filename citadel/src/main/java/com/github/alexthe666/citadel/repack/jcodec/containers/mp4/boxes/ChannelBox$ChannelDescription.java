/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Label;

public static class ChannelBox.ChannelDescription {
    private int channelLabel;
    private int channelFlags;
    private float[] coordinates = new float[3];

    public ChannelBox.ChannelDescription(int channelLabel, int channelFlags, float[] coordinates) {
        this.channelLabel = channelLabel;
        this.channelFlags = channelFlags;
        this.coordinates = coordinates;
    }

    public int getChannelLabel() {
        return this.channelLabel;
    }

    public int getChannelFlags() {
        return this.channelFlags;
    }

    public float[] getCoordinates() {
        return this.coordinates;
    }

    public Label getLabel() {
        return Label.getByVal(this.channelLabel);
    }
}
