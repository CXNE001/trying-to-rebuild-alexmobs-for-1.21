/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.MuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import java.io.IOException;

public interface Muxer {
    public MuxerTrack addVideoTrack(Codec var1, VideoCodecMeta var2);

    public MuxerTrack addAudioTrack(Codec var1, AudioCodecMeta var2);

    public void finish() throws IOException;
}
