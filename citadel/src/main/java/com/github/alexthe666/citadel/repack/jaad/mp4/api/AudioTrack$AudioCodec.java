/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.api.Track;

public static enum AudioTrack.AudioCodec implements Track.Codec
{
    AAC,
    AC3,
    AMR,
    AMR_WIDE_BAND,
    EVRC,
    EXTENDED_AC3,
    QCELP,
    SMV,
    UNKNOWN_AUDIO_CODEC;


    static Track.Codec forType(long type) {
        AudioTrack.AudioCodec ac = type == 1836069985L ? AAC : (type == 1633889587L ? AC3 : (type == 1935764850L ? AMR : (type == 1935767394L ? AMR_WIDE_BAND : (type == 1936029283L ? EVRC : (type == 1700998451L ? EXTENDED_AC3 : (type == 1936810864L ? QCELP : (type == 1936944502L ? SMV : UNKNOWN_AUDIO_CODEC)))))));
        return ac;
    }
}
