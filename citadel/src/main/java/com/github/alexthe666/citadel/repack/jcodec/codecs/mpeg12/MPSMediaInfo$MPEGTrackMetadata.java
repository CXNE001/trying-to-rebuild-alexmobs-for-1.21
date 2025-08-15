/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPSMediaInfo;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ChannelLabel;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import java.nio.ByteBuffer;

public static class MPSMediaInfo.MPEGTrackMetadata {
    int streamId;
    Codec codec;
    ByteBuffer probeData;

    public MPSMediaInfo.MPEGTrackMetadata(int streamId) {
        this.streamId = streamId;
    }

    public AudioFormat getAudioFormat() {
        return null;
    }

    public ChannelLabel[] getChannelLables() {
        return null;
    }

    public Size getDisplaySize() {
        return null;
    }

    public Size getCodedSize() {
        return null;
    }

    public float getFps() {
        return 0.0f;
    }

    public float getDuration() {
        return 0.0f;
    }

    public String getFourcc() {
        return null;
    }

    public Rational getFpsR() {
        return null;
    }

    public int getNumFrames() {
        return 0;
    }

    public MPSMediaInfo.MPEGTimecodeMetadata getTimecode() {
        return null;
    }
}
