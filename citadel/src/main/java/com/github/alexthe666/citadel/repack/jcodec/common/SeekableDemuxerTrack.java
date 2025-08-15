/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrack;
import java.io.IOException;

public interface SeekableDemuxerTrack
extends DemuxerTrack {
    public boolean gotoFrame(long var1) throws IOException;

    public boolean gotoSyncFrame(long var1) throws IOException;

    public long getCurFrame();

    public void seek(double var1) throws IOException;
}
