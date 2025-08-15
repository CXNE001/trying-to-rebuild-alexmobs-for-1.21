/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.io.IOException;

public interface PacketSink {
    public void outputVideoPacket(Packet var1, VideoCodecMeta var2) throws IOException;

    public void outputAudioPacket(Packet var1, AudioCodecMeta var2) throws IOException;
}
