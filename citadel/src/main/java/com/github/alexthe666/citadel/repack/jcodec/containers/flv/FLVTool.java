/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Utils;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SeqParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.mp4.AvcCBox;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.StringUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.IOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVMetadata;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVWriter;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FLVTool {
    private static Map<String, PacketProcessorFactory> processors = new HashMap<String, PacketProcessorFactory>();
    private static final MainUtils.Flag FLAG_MAX_PACKETS;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main1(String[] args) throws IOException {
        if (args.length < 1) {
            FLVTool.printGenericHelp();
            return;
        }
        String command = args[0];
        PacketProcessorFactory processorFactory = processors.get(command);
        if (processorFactory == null) {
            System.err.println("Unknown command: " + command);
            FLVTool.printGenericHelp();
            return;
        }
        MainUtils.Cmd cmd = MainUtils.parseArguments(Platform.copyOfRangeO(args, 1, args.length), processorFactory.getFlags());
        if (cmd.args.length < 1) {
            MainUtils.printHelpCmd(command, processorFactory.getFlags(), Arrays.asList("file in", "?file out"));
            return;
        }
        PacketProcessor processor = processorFactory.newPacketProcessor(cmd);
        int maxPackets = cmd.getIntegerFlagD(FLAG_MAX_PACKETS, Integer.MAX_VALUE);
        FileChannelWrapper _in = null;
        FileChannelWrapper out = null;
        try {
            _in = NIOUtils.readableChannel(new File(cmd.getArg(0)));
            if (processor.hasOutput()) {
                out = NIOUtils.writableChannel(new File(cmd.getArg(1)));
            }
            FLVReader demuxer = new FLVReader(_in);
            FLVWriter muxer = new FLVWriter(out);
            FLVTag pkt = null;
            for (int i = 0; i < maxPackets && (pkt = demuxer.readNextPacket()) != null && processor.processPacket(pkt, muxer); ++i) {
            }
            processor.finish(muxer);
            if (processor.hasOutput()) {
                muxer.finish();
            }
        }
        catch (Throwable throwable) {
            IOUtils.closeQuietly(_in);
            IOUtils.closeQuietly(out);
            throw throwable;
        }
        IOUtils.closeQuietly(_in);
        IOUtils.closeQuietly(out);
    }

    private static void printGenericHelp() {
        System.err.println("Syntax: <command> [flags] <file in> [file out]\nWhere command is: [" + StringUtils.joinS(processors.keySet().toArray(new String[0]), ", ") + "].");
    }

    private static PacketProcessor getProcessor(String command, MainUtils.Cmd cmd) {
        PacketProcessorFactory factory = processors.get(command);
        if (factory == null) {
            return null;
        }
        return factory.newPacketProcessor(cmd);
    }

    static {
        processors.put("clip", new ClipPacketProcessor.Factory());
        processors.put("fix_pts", new FixPtsProcessor.Factory());
        processors.put("info", new InfoPacketProcessor.Factory());
        processors.put("shift_pts", new ShiftPtsProcessor.Factory());
        FLAG_MAX_PACKETS = MainUtils.Flag.flag("max-packets", "m", "Maximum number of packets to process");
    }

    public static class ShiftPtsProcessor
    implements PacketProcessor {
        private static final long WRAP_AROUND_VALUE = 0x80000000L;
        private static final int HALF_WRAP_AROUND_VALUE = 0x40000000;
        private static final MainUtils.Flag FLAG_TO = MainUtils.Flag.flag("to", null, "Shift first pts to this value, and all subsequent pts accordingly.");
        private static final MainUtils.Flag FLAG_BY = MainUtils.Flag.flag("by", null, "Shift all pts by this value.");
        private static final MainUtils.Flag FLAG_WRAP_AROUND = MainUtils.Flag.flag("wrap-around", null, "Expect wrap around of timestamps.");
        private int shiftTo;
        private Integer shiftBy;
        private long ptsDelta;
        private boolean firstPtsSeen;
        private List<FLVTag> savedTags = new LinkedList<FLVTag>();
        private boolean expectWrapAround;
        private int prevPts;

        public ShiftPtsProcessor(int shiftTo, Integer shiftBy, boolean expectWrapAround) {
            this.shiftTo = shiftTo;
            this.shiftBy = shiftBy;
            this.expectWrapAround = true;
        }

        @Override
        public boolean processPacket(FLVTag pkt, FLVWriter writer) throws IOException {
            boolean validPkt;
            boolean avcPrivatePacket = pkt.getType() == FLVTag.Type.VIDEO && ((FLVTag.VideoTagHeader)pkt.getTagHeader()).getCodec() == Codec.H264 && ((FLVTag.AvcVideoTagHeader)pkt.getTagHeader()).getAvcPacketType() == 0;
            boolean aacPrivatePacket = pkt.getType() == FLVTag.Type.AUDIO && ((FLVTag.AudioTagHeader)pkt.getTagHeader()).getCodec() == Codec.AAC && ((FLVTag.AacAudioTagHeader)pkt.getTagHeader()).getPacketType() == 0;
            boolean bl = validPkt = pkt.getType() != FLVTag.Type.SCRIPT && !avcPrivatePacket && !aacPrivatePacket;
            if (this.expectWrapAround && validPkt && pkt.getPts() < this.prevPts && (long)this.prevPts - (long)pkt.getPts() > 0x40000000L) {
                Logger.warn("Wrap around detected: " + this.prevPts + " -> " + pkt.getPts());
                if (pkt.getPts() < -1073741824) {
                    this.ptsDelta += 0x100000000L;
                } else if (pkt.getPts() >= 0) {
                    this.ptsDelta += 0x80000000L;
                }
            }
            if (validPkt) {
                this.prevPts = pkt.getPts();
            }
            if (this.firstPtsSeen) {
                this.writePacket(pkt, writer);
            } else if (!validPkt) {
                this.savedTags.add(pkt);
            } else {
                if (this.shiftBy != null) {
                    this.ptsDelta = this.shiftBy.intValue();
                    if (this.ptsDelta + (long)pkt.getPts() < 0L) {
                        this.ptsDelta = -pkt.getPts();
                    }
                } else {
                    this.ptsDelta = this.shiftTo - pkt.getPts();
                }
                this.firstPtsSeen = true;
                this.emptySavedTags(writer);
                this.writePacket(pkt, writer);
            }
            return true;
        }

        private void writePacket(FLVTag pkt, FLVWriter writer) throws IOException {
            long newPts = (long)pkt.getPts() + this.ptsDelta;
            if (newPts < 0L) {
                Logger.warn("Preventing negative pts for tag @" + pkt.getPosition());
                newPts = this.shiftBy != null ? 0L : (long)this.shiftTo;
            } else if (newPts >= 0x80000000L) {
                Logger.warn("PTS wrap around @" + pkt.getPosition());
                this.ptsDelta = (newPts -= 0x80000000L) - (long)pkt.getPts();
            }
            pkt.setPts((int)newPts);
            writer.addPacket(pkt);
        }

        private void emptySavedTags(FLVWriter muxer) throws IOException {
            while (this.savedTags.size() > 0) {
                this.writePacket(this.savedTags.remove(0), muxer);
            }
        }

        @Override
        public void finish(FLVWriter muxer) throws IOException {
            this.emptySavedTags(muxer);
        }

        @Override
        public boolean hasOutput() {
            return true;
        }

        public static class Factory
        implements PacketProcessorFactory {
            @Override
            public PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
                return new ShiftPtsProcessor(flags.getIntegerFlagD(FLAG_TO, 0), flags.getIntegerFlag(FLAG_BY), flags.getBooleanFlagD(FLAG_WRAP_AROUND, false));
            }

            @Override
            public MainUtils.Flag[] getFlags() {
                return new MainUtils.Flag[]{FLAG_TO, FLAG_BY, FLAG_WRAP_AROUND};
            }
        }
    }

    public static class InfoPacketProcessor
    implements PacketProcessor {
        private FLVTag prevVideoTag;
        private FLVTag prevAudioTag;
        private boolean checkOnly;
        private FLVTag.Type streamType;

        public InfoPacketProcessor(boolean checkOnly, FLVTag.Type streamType) {
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
        implements PacketProcessorFactory {
            private static final MainUtils.Flag FLAG_CHECK = MainUtils.Flag.flag("check", null, "Check sanity and report errors only, no packet dump will be generated.");
            private static final MainUtils.Flag FLAG_STREAM = MainUtils.Flag.flag("stream", null, "Stream selector, can be one of: ['video', 'audio', 'script'].");

            @Override
            public PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
                return new InfoPacketProcessor(flags.getBooleanFlagD(FLAG_CHECK, false), flags.getEnumFlagD(FLAG_STREAM, null, FLVTag.Type.class));
            }

            @Override
            public MainUtils.Flag[] getFlags() {
                return new MainUtils.Flag[]{FLAG_CHECK, FLAG_STREAM};
            }
        }
    }

    public static class FixPtsProcessor
    implements PacketProcessor {
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
        implements PacketProcessorFactory {
            @Override
            public PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
                return new FixPtsProcessor();
            }

            @Override
            public MainUtils.Flag[] getFlags() {
                return new MainUtils.Flag[0];
            }
        }
    }

    public static class ClipPacketProcessor
    implements PacketProcessor {
        private static FLVTag h264Config;
        private boolean copying = false;
        private Double from;
        private Double to;
        private static final MainUtils.Flag FLAG_FROM;
        private static final MainUtils.Flag FLAG_TO;

        public ClipPacketProcessor(Double from, Double to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean processPacket(FLVTag pkt, FLVWriter writer) throws IOException {
            if (pkt.getType() == FLVTag.Type.VIDEO && pkt.getTagHeader().getCodec() == Codec.H264 && ((FLVTag.AvcVideoTagHeader)pkt.getTagHeader()).getAvcPacketType() == 0) {
                h264Config = pkt;
                System.out.println("GOT AVCC");
            }
            if (!this.copying && (this.from == null || pkt.getPtsD() > this.from) && pkt.getType() == FLVTag.Type.VIDEO && pkt.isKeyFrame() && h264Config != null) {
                System.out.println("Starting at packet: " + Platform.toJSON(pkt));
                this.copying = true;
                h264Config.setPts(pkt.getPts());
                writer.addPacket(h264Config);
            }
            if (this.to != null && pkt.getPtsD() >= this.to) {
                System.out.println("Stopping at packet: " + Platform.toJSON(pkt));
                return false;
            }
            if (this.copying) {
                writer.addPacket(pkt);
            }
            return true;
        }

        @Override
        public void finish(FLVWriter muxer) {
        }

        @Override
        public boolean hasOutput() {
            return true;
        }

        static {
            FLAG_FROM = MainUtils.Flag.flag("from", null, "From timestamp (in seconds, i.e 67.49)");
            FLAG_TO = MainUtils.Flag.flag("to", null, "To timestamp");
        }

        public static class Factory
        implements PacketProcessorFactory {
            @Override
            public PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
                return new ClipPacketProcessor(flags.getDoubleFlag(FLAG_FROM), flags.getDoubleFlag(FLAG_TO));
            }

            @Override
            public MainUtils.Flag[] getFlags() {
                return new MainUtils.Flag[]{FLAG_FROM, FLAG_TO};
            }
        }
    }

    public static interface PacketProcessorFactory {
        public PacketProcessor newPacketProcessor(MainUtils.Cmd var1);

        public MainUtils.Flag[] getFlags();
    }

    public static interface PacketProcessor {
        public boolean processPacket(FLVTag var1, FLVWriter var2) throws IOException;

        public boolean hasOutput();

        public void finish(FLVWriter var1) throws IOException;
    }
}
