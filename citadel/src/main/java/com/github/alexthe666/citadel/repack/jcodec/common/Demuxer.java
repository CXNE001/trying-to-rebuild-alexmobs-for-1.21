/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrack;
import java.io.Closeable;
import java.util.List;

public interface Demuxer
extends Closeable {
    public List<? extends DemuxerTrack> getTracks();

    public List<? extends DemuxerTrack> getVideoTracks();

    public List<? extends DemuxerTrack> getAudioTracks();
}
