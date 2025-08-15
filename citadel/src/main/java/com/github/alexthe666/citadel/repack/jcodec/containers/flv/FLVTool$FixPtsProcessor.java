/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public static class FLVTool.FixPtsProcessor
implements FLVTool.PacketProcessor {
    private double lastPtsAudio = 0.0;
    private double lastPtsVideo = 0.0;
    private List<FLVTag> tags = new ArrayList<FLVTag>();
    private int audioTagsInQueue;
    private int videoTagsInQueue;
    private static final double CORRECTION_PACE = 0.33;

    @Override
    public boolean processPacket(FLVTag pkt, FLVWriter writer) throws IOException {
        this.tags.add(pkt);
        if (pkt.getType() == FLVTag.Type.AUDIO) {
            ++this.audioTagsInQueue;
        } else if (pkt.getType() == FLVTag.Type.VIDEO) {
            ++this.videoTagsInQueue;
        }
        if (this.tags.size() < 600) {
            return true;
        }
        this.processOneTag(writer);
        return true;
    }

    private void processOneTag(FLVWriter writer) throws IOException {
        FLVTag tag = this.tags.remove(0);
        if (tag.getType() == FLVTag.Type.AUDIO) {
            tag.setPts((int)Math.round(this.lastPtsAudio * 1000.0));
            this.lastPtsAudio += this.audioFrameDuration((FLVTag.AudioTagHeader)tag.getTagHeader());
            --this.audioTagsInQueue;
        } else if (tag.getType() == FLVTag.Type.VIDEO) {
            double duration = 1024.0 * (double)this.audioTagsInQueue / (double)(48000 * this.videoTagsInQueue);
            tag.setPts((int)Math.round(this.lastPtsVideo * 1000.0));
            this.lastPtsVideo += Math.min(1.33 * duration, Math.max(0.6699999999999999 * duration, duration + Math.min(1.0, Math.abs(this.lastPtsAudio - this.lastPtsVideo)) * (this.lastPtsAudio - this.lastPtsVideo)));
            --this.videoTagsInQueue;
            System.out.println(this.lastPtsVideo + " - " + this.lastPtsAudio);
        } else {
            tag.setPts((int)Math.round(this.lastPtsVideo * 1000.0));
        }
        writer.addPacket(tag);
    }

    private double audioFrameDuration(FLVTag.AudioTagHeader audioTagHeader) {
        if (Codec.AAC == audioTagHeader.getCodec()) {
            return 1024.0 / (double)audioTagHeader.getAudioFormat().getSampleRate();
        }
        if (Codec.MP3 == audioTagHeader.getCodec()) {
            return 1152.0 / (double)audioTagHeader.getAudioFormat().getSampleRate();
        }
        throw new RuntimeException("Audio codec:" + audioTagHeader.getCodec() + " is not supported.");
    }

    @Override
    public void finish(FLVWriter muxer) throws IOException {
        while (this.tags.size() > 0) {
            this.processOneTag(muxer);
        }
    }

    @Override
    public boolean hasOutput() {
        return true;
    }

    public static class Factory
    implements FLVTool.PacketProcessorFactory {
        @Override
        public FLVTool.PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
            return new FLVTool.FixPtsProcessor();
        }

        @Override
        public MainUtils.Flag[] getFlags() {
            return new MainUtils.Flag[0];
        }
    }
}
