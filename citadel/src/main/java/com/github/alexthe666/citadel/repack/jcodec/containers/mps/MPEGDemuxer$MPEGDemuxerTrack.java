/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public static interface MPEGDemuxer.MPEGDemuxerTrack
extends DemuxerTrack {
    public Packet nextFrameWithBuffer(ByteBuffer var1) throws IOException;

    @Override
    public DemuxerTrackMeta getMeta();

    public int getSid();

    public List<PESPacket> getPending();

    public void ignore();
}
