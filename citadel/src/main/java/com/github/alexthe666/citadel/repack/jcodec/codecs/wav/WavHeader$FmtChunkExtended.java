/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import com.github.alexthe666.citadel.repack.jcodec.codecs.wav.WavHeader;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ChannelLabel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public static class WavHeader.FmtChunkExtended
extends WavHeader.FmtChunk {
    short cbSize;
    short bitsPerCodedSample;
    int channelLayout;
    int guid;

    public WavHeader.FmtChunkExtended(WavHeader.FmtChunk other, short cbSize, short bitsPerCodedSample, int channelLayout, int guid) {
        super(other.audioFormat, other.numChannels, other.sampleRate, other.byteRate, other.blockAlign, other.bitsPerSample);
        this.cbSize = cbSize;
        this.bitsPerCodedSample = bitsPerCodedSample;
        this.channelLayout = channelLayout;
        this.guid = guid;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static WavHeader.FmtChunk read(ByteBuffer bb) throws IOException {
        WavHeader.FmtChunk fmtChunk = WavHeader.FmtChunk.get(bb);
        ByteOrder old = bb.order();
        try {
            bb.order(ByteOrder.LITTLE_ENDIAN);
            WavHeader.FmtChunkExtended fmtChunkExtended = new WavHeader.FmtChunkExtended(fmtChunk, bb.getShort(), bb.getShort(), bb.getInt(), bb.getInt());
            return fmtChunkExtended;
        }
        finally {
            bb.order(old);
        }
    }

    @Override
    public void put(ByteBuffer bb) throws IOException {
        super.put(bb);
        ByteOrder old = bb.order();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putShort(this.cbSize);
        bb.putShort(this.bitsPerCodedSample);
        bb.putInt(this.channelLayout);
        bb.putInt(this.guid);
        bb.order(old);
    }

    @Override
    public int size() {
        return super.size() + 12;
    }

    public ChannelLabel[] getLabels() {
        ArrayList<ChannelLabel> labels = new ArrayList<ChannelLabel>();
        for (int i = 0; i < mapping.length; ++i) {
            if ((this.channelLayout & 1 << i) == 0) continue;
            labels.add(mapping[i]);
        }
        return labels.toArray(new ChannelLabel[0]);
    }
}
