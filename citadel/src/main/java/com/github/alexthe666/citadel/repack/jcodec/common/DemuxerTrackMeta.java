/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import java.nio.ByteBuffer;

public class DemuxerTrackMeta {
    private TrackType type;
    private Codec codec;
    private double totalDuration;
    private int[] seekFrames;
    private int totalFrames;
    private ByteBuffer codecPrivate;
    private VideoCodecMeta videoCodecMeta;
    private AudioCodecMeta audioCodecMeta;
    private int index;
    private Orientation orientation;

    public DemuxerTrackMeta(TrackType type, Codec codec, double totalDuration, int[] seekFrames, int totalFrames, ByteBuffer codecPrivate, VideoCodecMeta videoCodecMeta, AudioCodecMeta audioCodecMeta) {
        this.type = type;
        this.codec = codec;
        this.totalDuration = totalDuration;
        this.seekFrames = seekFrames;
        this.totalFrames = totalFrames;
        this.codecPrivate = codecPrivate;
        this.videoCodecMeta = videoCodecMeta;
        this.audioCodecMeta = audioCodecMeta;
        this.orientation = Orientation.D_0;
    }

    public TrackType getType() {
        return this.type;
    }

    public Codec getCodec() {
        return this.codec;
    }

    public double getTotalDuration() {
        return this.totalDuration;
    }

    public int[] getSeekFrames() {
        return this.seekFrames;
    }

    public int getTotalFrames() {
        return this.totalFrames;
    }

    public int getIndex() {
        return this.index;
    }

    public ByteBuffer getCodecPrivate() {
        return this.codecPrivate;
    }

    public VideoCodecMeta getVideoCodecMeta() {
        return this.videoCodecMeta;
    }

    public AudioCodecMeta getAudioCodecMeta() {
        return this.audioCodecMeta;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public static enum Orientation {
        D_0,
        D_90,
        D_180,
        D_270;

    }
}
