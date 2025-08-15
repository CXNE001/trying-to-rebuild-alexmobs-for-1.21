/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.AACConts;
import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.ADTSParser;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public static class MPSDemuxer.AACTrack
extends MPSDemuxer.PlainTrack {
    private List<Packet> audioStash = new ArrayList<Packet>();

    public MPSDemuxer.AACTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
        super(demuxer, streamId, pkt);
    }

    @Override
    public Packet nextFrame() throws IOException {
        Packet nextFrame;
        if (this.audioStash.size() == 0 && (nextFrame = this.nextFrameWithBuffer(null)) != null) {
            ByteBuffer data = nextFrame.getData();
            ADTSParser.Header adts = ADTSParser.read(data.duplicate());
            long nextPts = nextFrame.getPts();
            while (data.hasRemaining()) {
                ByteBuffer data2 = NIOUtils.read(data, adts.getSize());
                Packet pkt = Packet.createPacketWithData(nextFrame, data2);
                pkt.setDuration(pkt.getTimescale() * 1024 / AACConts.AAC_SAMPLE_RATES[adts.getSamplingIndex()]);
                pkt.setPts(nextPts);
                nextPts += pkt.getDuration();
                this.audioStash.add(pkt);
                if (!data.hasRemaining()) continue;
                adts = ADTSParser.read(data.duplicate());
            }
        }
        if (this.audioStash.size() == 0) {
            return null;
        }
        return this.audioStash.remove(0);
    }
}
