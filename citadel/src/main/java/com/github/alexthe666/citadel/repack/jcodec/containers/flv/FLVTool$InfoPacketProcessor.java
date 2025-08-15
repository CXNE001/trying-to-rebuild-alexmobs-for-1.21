/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Utils;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SeqParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.mp4.AvcCBox;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVMetadata;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVWriter;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.IOException;
import java.nio.ByteBuffer;

public static class FLVTool.InfoPacketProcessor
implements FLVTool.PacketProcessor {
    private FLVTag prevVideoTag;
    private FLVTag prevAudioTag;
    private boolean checkOnly;
    private FLVTag.Type streamType;

    public FLVTool.InfoPacketProcessor(boolean checkOnly, FLVTag.Type streamType) {
        this.checkOnly = checkOnly;
        this.streamType = streamType;
    }

    @Override
    public boolean processPacket(FLVTag pkt, FLVWriter writer) throws IOException {
        if (this.checkOnly) {
            return true;
        }
        if (pkt.getType() == FLVTag.Type.VIDEO) {
            if (this.streamType == FLVTag.Type.VIDEO || this.streamType == null) {
                if (this.prevVideoTag != null) {
                    this.dumpOnePacket(this.prevVideoTag, pkt.getPts() - this.prevVideoTag.getPts());
                }
                this.prevVideoTag = pkt;
            }
        } else if (pkt.getType() == FLVTag.Type.AUDIO) {
            if (this.streamType == FLVTag.Type.AUDIO || this.streamType == null) {
                if (this.prevAudioTag != null) {
                    this.dumpOnePacket(this.prevAudioTag, pkt.getPts() - this.prevAudioTag.getPts());
                }
                this.prevAudioTag = pkt;
            }
        } else {
            this.dumpOnePacket(pkt, 0);
        }
        return true;
    }

    private void dumpOnePacket(FLVTag pkt, int duration) {
        FLVMetadata metadata;
        System.out.print("T=" + this.typeString(pkt.getType()) + "|PTS=" + pkt.getPts() + "|DUR=" + duration + "|" + (pkt.isKeyFrame() ? "K" : " ") + "|POS=" + pkt.getPosition());
        if (pkt.getTagHeader() instanceof FLVTag.VideoTagHeader) {
            FLVTag.VideoTagHeader vt = (FLVTag.VideoTagHeader)pkt.getTagHeader();
            System.out.print("|C=" + vt.getCodec() + "|FT=" + vt.getFrameType());
            if (vt instanceof FLVTag.AvcVideoTagHeader) {
                FLVTag.AvcVideoTagHeader avct = (FLVTag.AvcVideoTagHeader)vt;
                System.out.print("|PKT_TYPE=" + avct.getAvcPacketType() + "|COMP_OFF=" + avct.getCompOffset());
                if (avct.getAvcPacketType() == 0) {
                    ByteBuffer frameData = pkt.getData().duplicate();
                    FLVReader.parseVideoTagHeader(frameData);
                    AvcCBox avcc = H264Utils.parseAVCCFromBuffer(frameData);
                    for (SeqParameterSet sps : H264Utils.readSPSFromBufferList(avcc.getSpsList())) {
                        System.out.println();
                        System.out.print("  SPS[" + sps.getSeqParameterSetId() + "]:" + Platform.toJSON(sps));
                    }
                    for (PictureParameterSet pps : H264Utils.readPPSFromBufferList(avcc.getPpsList())) {
                        System.out.println();
                        System.out.print("  PPS[" + pps.getPicParameterSetId() + "]:" + Platform.toJSON(pps));
                    }
                }
            }
        } else if (pkt.getTagHeader() instanceof FLVTag.AudioTagHeader) {
            FLVTag.AudioTagHeader at = (FLVTag.AudioTagHeader)pkt.getTagHeader();
            AudioFormat format = at.getAudioFormat();
            System.out.print("|C=" + at.getCodec() + "|SR=" + format.getSampleRate() + "|SS=" + (format.getSampleSizeInBits() >> 3) + "|CH=" + format.getChannels());
        } else if (pkt.getType() == FLVTag.Type.SCRIPT && (metadata = FLVReader.parseMetadata(pkt.getData().duplicate())) != null) {
            System.out.println();
            System.out.print("  Metadata:" + Platform.toJSON(metadata));
        }
        System.out.println();
    }

    private String typeString(FLVTag.Type type) {
        return type.toString().substring(0, 1);
    }

    @Override
    public void finish(FLVWriter muxer) throws IOException {
        if (this.prevVideoTag != null) {
            this.dumpOnePacket(this.prevVideoTag, 0);
        }
        if (this.prevAudioTag != null) {
            this.dumpOnePacket(this.prevAudioTag, 0);
        }
    }

    @Override
    public boolean hasOutput() {
        return false;
    }

    public static class Factory
    implements FLVTool.PacketProcessorFactory {
        private static final MainUtils.Flag FLAG_CHECK = MainUtils.Flag.flag("check", null, "Check sanity and report errors only, no packet dump will be generated.");
        private static final MainUtils.Flag FLAG_STREAM = MainUtils.Flag.flag("stream", null, "Stream selector, can be one of: ['video', 'audio', 'script'].");

        @Override
        public FLVTool.PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
            return new FLVTool.InfoPacketProcessor(flags.getBooleanFlagD(FLAG_CHECK, false), flags.getEnumFlagD(FLAG_STREAM, null, FLVTag.Type.class));
        }

        @Override
        public MainUtils.Flag[] getFlags() {
            return new MainUtils.Flag[]{FLAG_CHECK, FLAG_STREAM};
        }
    }
}
