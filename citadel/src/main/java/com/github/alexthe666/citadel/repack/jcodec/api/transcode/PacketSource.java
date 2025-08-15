/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.io.IOException;

public interface PacketSource {
    public Packet inputVideoPacket() throws IOException;

    public Packet inputAudioPacket() throws IOException;
}
