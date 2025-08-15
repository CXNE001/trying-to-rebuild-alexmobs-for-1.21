/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.api.transcode.AudioFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Options;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PixelStore;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.VideoFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import java.io.IOException;

public interface Source {
    public void init(PixelStore var1) throws IOException;

    public void seekFrames(int var1) throws IOException;

    public VideoFrameWithPacket getNextVideoFrame() throws IOException;

    public AudioFrameWithPacket getNextAudioFrame() throws IOException;

    public void finish();

    public boolean haveAudio();

    public void setOption(Options var1, Object var2);

    public VideoCodecMeta getVideoCodecMeta();

    public AudioCodecMeta getAudioCodecMeta();

    public boolean isVideo();

    public boolean isAudio();
}
