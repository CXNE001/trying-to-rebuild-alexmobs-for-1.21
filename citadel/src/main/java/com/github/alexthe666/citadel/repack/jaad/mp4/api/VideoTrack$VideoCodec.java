/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.api.Track;

public static enum VideoTrack.VideoCodec implements Track.Codec
{
    AVC,
    H263,
    MP4_ASP,
    UNKNOWN_VIDEO_CODEC;


    static Track.Codec forType(long type) {
        VideoTrack.VideoCodec ac = type == 1635148593L ? AVC : (type == 1932670515L ? H263 : (type == 1836070006L ? MP4_ASP : UNKNOWN_VIDEO_CODEC));
        return ac;
    }
}
