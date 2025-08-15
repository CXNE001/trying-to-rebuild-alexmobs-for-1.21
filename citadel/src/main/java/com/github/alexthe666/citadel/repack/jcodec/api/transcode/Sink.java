/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.api.transcode.AudioFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Options;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.VideoFrameWithPacket;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import java.io.IOException;

public interface Sink {
    public void init() throws IOException;

    public void outputVideoFrame(VideoFrameWithPacket var1) throws IOException;

    public void outputAudioFrame(AudioFrameWithPacket var1) throws IOException;

    public void finish() throws IOException;

    public ColorSpace getInputColor();

    public void setOption(Options var1, Object var2);

    public boolean isVideo();

    public boolean isAudio();
}
