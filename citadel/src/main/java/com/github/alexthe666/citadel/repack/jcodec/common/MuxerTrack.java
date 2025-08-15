/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.io.IOException;

public interface MuxerTrack {
    public void addFrame(Packet var1) throws IOException;
}
