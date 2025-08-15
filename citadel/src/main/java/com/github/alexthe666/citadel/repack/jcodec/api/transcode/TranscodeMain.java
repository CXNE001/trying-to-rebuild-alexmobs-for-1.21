/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Filter;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Options;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.SinkImpl;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.SourceImpl;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.Transcoder;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.filters.DumpMvFilter;
import com.github.alexthe666.citadel.repack.jcodec.api.transcode.filters.ScaleFilter;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.Demuxer;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.Format;
import com.github.alexthe666.citadel.repack.jcodec.common.JCodecUtil;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.Tuple;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.LogLevel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.OutLogSink;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TranscodeMain {
    private static final MainUtils.Flag FLAG_INPUT = new MainUtils.Flag("input", "i", "Designates an input argument", MainUtils.FlagType.VOID);
    private static final MainUtils.Flag FLAG_MAP_VIDEO = MainUtils.Flag.flag("map:v", "mv", "Map a video from a specified input into this output");
    private static final MainUtils.Flag FLAG_MAP_AUDIO = MainUtils.Flag.flag("map:a", "ma", "Map a audio from a specified input into this output");
    private static final MainUtils.Flag FLAG_SEEK_FRAMES = MainUtils.Flag.flag("seek-frames", null, "Seek frames");
    private static final MainUtils.Flag FLAG_MAX_FRAMES = MainUtils.Flag.flag("max-frames", "limit", "Max frames");
    private static final MainUtils.Flag FLAG_AUDIO_CODEC = MainUtils.Flag.flag("codec:audio", "acodec", "Audio codec [default=auto].");
    private static final MainUtils.Flag FLAG_VIDEO_CODEC = MainUtils.Flag.flag("codec:video", "vcodec", "Video codec [default=auto].");
    private static final MainUtils.Flag FLAG_FORMAT = MainUtils.Flag.flag("format", "f", "Format [default=auto].");
    private static final MainUtils.Flag FLAG_PROFILE = MainUtils.Flag.flag("profile", null, "Profile to use (supported by some encoders).");
    private static final MainUtils.Flag FLAG_INTERLACED = MainUtils.Flag.flag("interlaced", null, "Encode output as interlaced (supported by Prores encoder).");
    private static final MainUtils.Flag FLAG_DUMPMV = MainUtils.Flag.flag("dumpMv", null, "Dump motion vectors (supported by h.264 decoder).");
    private static final MainUtils.Flag FLAG_DUMPMVJS = MainUtils.Flag.flag("dumpMvJs", null, "Dump motion vectors in form of JASON file (supported by h.264 decoder).");
    private static final MainUtils.Flag FLAG_DOWNSCALE = MainUtils.Flag.flag("downscale", null, "Decode frames in downscale (supported by MPEG, Prores and Jpeg decoders).");
    private static final MainUtils.Flag FLAG_VIDEO_FILTER = MainUtils.Flag.flag("videoFilter", "vf", "Contains a comma separated list of video filters with arguments.");
    private static final MainUtils.Flag[] ALL_FLAGS = new MainUtils.Flag[]{FLAG_INPUT, FLAG_FORMAT, FLAG_VIDEO_CODEC, FLAG_AUDIO_CODEC, FLAG_SEEK_FRAMES, FLAG_MAX_FRAMES, FLAG_PROFILE, FLAG_INTERLACED, FLAG_DUMPMV, FLAG_DUMPMVJS, FLAG_DOWNSCALE, FLAG_MAP_VIDEO, FLAG_MAP_AUDIO, FLAG_VIDEO_FILTER};
    private static Map<String, Format> extensionToF = new HashMap<String, Format>();
    private static Map<String, Codec> extensionToC = new HashMap<String, Codec>();
    private static Map<Format, Codec> videoCodecsForF = new HashMap<Format, Codec>();
    private static Map<Format, Codec> audioCodecsForF = new HashMap<Format, Codec>();
    private static Set<Codec> supportedDecoders = new HashSet<Codec>();
    private static Map<String, Class<? extends Filter>> knownFilters = new HashMap<String, Class<? extends Filter>>();

    public static void main(String[] args) throws Exception {
        Logger.addSink(new OutLogSink(System.out, new OutLogSink.SimpleFormat("#message"), LogLevel.INFO));
        MainUtils.Cmd cmd = MainUtils.parseArguments(args, ALL_FLAGS);
        Transcoder.TranscoderBuilder builder = Transcoder.newTranscoder();
        ArrayList<SourceImpl> sources = new ArrayList<SourceImpl>();
        ArrayList<Tuple._3<Integer, Integer, Codec>> inputCodecsVideo = new ArrayList<Tuple._3<Integer, Integer, Codec>>();
        ArrayList<Tuple._3<Integer, Integer, Codec>> inputCodecsAudio = new ArrayList<Tuple._3<Integer, Integer, Codec>>();
        for (int index = 0; index < cmd.argsLength(); ++index) {
            String inputCodecAudioRaw;
            Format inputFormat;
            if (!cmd.getBooleanFlagI(index, FLAG_INPUT).booleanValue()) continue;
            Tuple._3<Integer, Integer, Codec> inputCodecVideo = null;
            Tuple._3<Integer, Integer, Codec> inputCodecAudio = null;
            String input = cmd.getArg(index);
            String inputFormatRaw = cmd.getStringFlagI(index, FLAG_FORMAT);
            if (inputFormatRaw == null) {
                Format detectFormat;
                inputFormat = TranscodeMain.getFormatFromExtension(input);
                if (inputFormat != Format.IMG && (detectFormat = JCodecUtil.detectFormat(new File(input))) != null) {
                    inputFormat = detectFormat;
                }
            } else {
                inputFormat = Format.valueOf(inputFormatRaw.toUpperCase());
            }
            if (inputFormat == null) {
                Logger.error("Input format could not be detected");
                return;
            }
            Logger.info(String.format("Input stream %d: %s", index, String.valueOf(inputFormat)));
            int videoTrackNo = -1;
            String inputCodecVideoRaw = cmd.getStringFlagI(index, FLAG_VIDEO_CODEC);
            if (inputCodecVideoRaw == null) {
                if (inputFormat == Format.IMG) {
                    inputCodecVideo = Tuple.triple(0, 0, TranscodeMain.getCodecFromExtension(input));
                } else if (inputFormat.isVideo()) {
                    inputCodecVideo = TranscodeMain.selectSuitableTrack(input, inputFormat, TrackType.VIDEO);
                }
            } else {
                inputCodecVideo = Tuple.triple(0, 0, Codec.valueOf(inputCodecVideoRaw.toUpperCase()));
            }
            if (inputCodecVideo != null) {
                if (inputFormat == Format.MPEG_TS) {
                    Logger.info(String.format("Video codec: %s[pid=%d,stream=%d]", String.valueOf(inputCodecVideo.v2), inputCodecVideo.v0, inputCodecVideo.v1));
                } else {
                    Logger.info(String.format("Video codec: %s", String.valueOf(inputCodecVideo.v2)));
                }
            }
            if ((inputCodecAudioRaw = cmd.getStringFlagI(index, FLAG_AUDIO_CODEC)) == null) {
                if (inputFormat.isAudio()) {
                    inputCodecAudio = TranscodeMain.selectSuitableTrack(input, inputFormat, TrackType.AUDIO);
                }
            } else {
                inputCodecAudio = Tuple.triple(0, 0, Codec.valueOf(inputCodecAudioRaw.toUpperCase()));
            }
            if (inputCodecAudio != null) {
                if (inputFormat == Format.MPEG_TS) {
                    Logger.info(String.format("Audio codec: %s[pid=%d,stream=%d]", String.valueOf(inputCodecAudio.v2), inputCodecAudio.v0, inputCodecAudio.v1));
                } else {
                    Logger.info(String.format("Audio codec: %s", String.valueOf(inputCodecAudio.v2)));
                }
            }
            SourceImpl source = new SourceImpl(input, inputFormat, inputCodecVideo, inputCodecAudio);
            Integer downscale = cmd.getIntegerFlagID(index, FLAG_DOWNSCALE, 1);
            if (downscale != null && 1 << MathUtil.log2(downscale) != downscale) {
                Logger.error("Only values [2, 4, 8] are supported for " + FLAG_DOWNSCALE + ", the option will have no effect.");
            } else {
                source.setOption(Options.DOWNSCALE, downscale);
            }
            source.setOption(Options.PROFILE, cmd.getStringFlagI(index, FLAG_PROFILE));
            source.setOption(Options.INTERLACED, cmd.getBooleanFlagID(index, FLAG_INTERLACED, false));
            sources.add(source);
            inputCodecsVideo.add(inputCodecVideo);
            inputCodecsAudio.add(inputCodecAudio);
            builder.addSource(source);
            builder.setSeekFrames(sources.size() - 1, cmd.getIntegerFlagID(index, FLAG_SEEK_FRAMES, 0)).setMaxFrames(sources.size() - 1, cmd.getIntegerFlagID(index, FLAG_MAX_FRAMES, Integer.MAX_VALUE));
        }
        if (sources.isEmpty()) {
            MainUtils.printHelpArgs(ALL_FLAGS, new String[]{"input", "output"});
            return;
        }
        ArrayList<SinkImpl> sinks = new ArrayList<SinkImpl>();
        for (int index = 0; index < cmd.argsLength(); ++index) {
            int videoMap;
            int audioMap;
            if (cmd.getBooleanFlagI(index, FLAG_INPUT).booleanValue()) continue;
            String output = cmd.getArg(index);
            String outputFormatRaw = cmd.getStringFlagI(index, FLAG_FORMAT);
            Format outputFormat = outputFormatRaw == null ? TranscodeMain.getFormatFromExtension(output) : Format.valueOf(outputFormatRaw.toUpperCase());
            String outputCodecVideoRaw = cmd.getStringFlagI(index, FLAG_VIDEO_CODEC);
            Codec outputCodecVideo = null;
            boolean videoCopy = false;
            if (outputCodecVideoRaw == null) {
                outputCodecVideo = TranscodeMain.getCodecFromExtension(output);
                if (outputCodecVideo == null) {
                    outputCodecVideo = TranscodeMain.getFirstVideoCodecForFormat(outputFormat);
                }
            } else if ("copy".equalsIgnoreCase(outputCodecVideoRaw)) {
                videoCopy = true;
            } else {
                outputCodecVideo = "none".equalsIgnoreCase(outputCodecVideoRaw) ? null : Codec.valueOf(outputCodecVideoRaw.toUpperCase());
            }
            String outputCodecAudioRaw = cmd.getStringFlagI(index, FLAG_AUDIO_CODEC);
            Codec outputCodecAudio = null;
            boolean audioCopy = false;
            if (outputCodecAudioRaw == null) {
                if (outputFormat.isAudio()) {
                    outputCodecAudio = TranscodeMain.getFirstAudioCodecForFormat(outputFormat);
                }
            } else if ("copy".equalsIgnoreCase(outputCodecAudioRaw)) {
                audioCopy = true;
            } else {
                outputCodecAudio = "none".equalsIgnoreCase(outputCodecVideoRaw) ? null : Codec.valueOf(outputCodecAudioRaw.toUpperCase());
            }
            if ((audioMap = cmd.getIntegerFlagID(index, FLAG_MAP_AUDIO, 0).intValue()) > sources.size()) {
                Logger.error("Can not map audio from source " + audioMap + ", " + sources.size() + " sources specified.");
            }
            if ((videoMap = cmd.getIntegerFlagID(index, FLAG_MAP_VIDEO, 0).intValue()) > sources.size()) {
                Logger.error("Can not map video from source " + videoMap + ", " + sources.size() + " sources specified.");
            }
            if (videoCopy) {
                Tuple._3 inputCodecVideo = (Tuple._3)inputCodecsVideo.get(videoMap);
                Codec codec = outputCodecVideo = inputCodecVideo != null ? (Codec)inputCodecVideo.v2 : null;
            }
            if (audioCopy) {
                Tuple._3 inputCodecAudio = (Tuple._3)inputCodecsAudio.get(audioMap);
                outputCodecAudio = inputCodecAudio != null ? (Codec)inputCodecAudio.v2 : null;
            }
            SinkImpl sink = new SinkImpl(output, outputFormat, outputCodecVideo, outputCodecAudio);
            sinks.add(sink);
            builder.addSink(sink);
            builder.setAudioMapping(audioMap, sinks.size() - 1, audioCopy);
            builder.setVideoMapping(videoMap, sinks.size() - 1, videoCopy);
            if (cmd.getBooleanFlagI(index, FLAG_DUMPMV).booleanValue()) {
                builder.addFilter(sinks.size() - 1, new DumpMvFilter(false));
            } else if (cmd.getBooleanFlagI(index, FLAG_DUMPMVJS).booleanValue()) {
                builder.addFilter(sinks.size() - 1, new DumpMvFilter(true));
            }
            String vf = cmd.getStringFlagI(index, FLAG_VIDEO_FILTER);
            if (vf == null) continue;
            TranscodeMain.addVideoFilters(vf, builder, sinks.size() - 1);
        }
        if (sources.isEmpty() || sinks.isEmpty()) {
            MainUtils.printHelpArgs(ALL_FLAGS, new String[]{"input", "output"});
            return;
        }
        Transcoder transcoder = builder.create();
        transcoder.transcode();
    }

    private static void addVideoFilters(String vf, Transcoder.TranscoderBuilder builder, int sinkIndex) {
        if (vf == null) {
            return;
        }
        for (String filter : vf.split(",")) {
            String[] parts = filter.split("=");
            String filterName = parts[0];
            Class<? extends Filter> filterClass = knownFilters.get(filterName);
            if (filterClass == null) {
                Logger.error("Unknown filter: " + filterName);
                throw new RuntimeException("Unknown filter: " + filterName);
            }
            if (parts.length <= 1) continue;
            String filterArgs = parts[1];
            String[] split = filterArgs.split(":");
            Object[] params = new Integer[split.length];
            for (int i = 0; i < split.length; ++i) {
                params[i] = Integer.parseInt(split[i]);
            }
            try {
                Filter f = Platform.newInstance(filterClass, params);
                builder.addFilter(sinkIndex, f);
            }
            catch (Exception e) {
                String message = "The filter " + filterName + " doesn't take " + split.length + " arguments.";
                Logger.error(message);
                throw new RuntimeException(message);
            }
        }
    }

    private static Codec getFirstAudioCodecForFormat(Format inputFormat) {
        return audioCodecsForF.get(inputFormat);
    }

    private static Codec getFirstVideoCodecForFormat(Format inputFormat) {
        return videoCodecsForF.get(inputFormat);
    }

    private static Codec detectVideoDecoder(DemuxerTrack track) throws IOException {
        Codec codec;
        DemuxerTrackMeta meta = track.getMeta();
        if (meta != null && (codec = meta.getCodec()) != null) {
            return codec;
        }
        Packet packet = track.nextFrame();
        if (packet == null) {
            return null;
        }
        return JCodecUtil.detectDecoder(packet.getData());
    }

    private static Tuple._3<Integer, Integer, Codec> selectSuitableTrack(String input, Format format, TrackType targetType) throws IOException {
        Tuple._2<Integer, Demuxer> demuxerPid = format == Format.MPEG_TS ? JCodecUtil.createM2TSDemuxer(new File(input), targetType) : Tuple.pair(0, JCodecUtil.createDemuxer(format, new File(input)));
        if (demuxerPid == null || demuxerPid.v1 == null) {
            return null;
        }
        int trackNo = 0;
        List<? extends DemuxerTrack> tracks = targetType == TrackType.VIDEO ? ((Demuxer)demuxerPid.v1).getVideoTracks() : ((Demuxer)demuxerPid.v1).getAudioTracks();
        for (DemuxerTrack demuxerTrack : tracks) {
            Codec codec = TranscodeMain.detectVideoDecoder(demuxerTrack);
            if (supportedDecoders.contains(codec)) {
                return Tuple.triple(demuxerPid.v0, trackNo, codec);
            }
            ++trackNo;
        }
        return null;
    }

    private static Format getFormatFromExtension(String output) {
        String extension = output.replaceFirst(".*\\.([^\\.]+$)", "$1");
        return extensionToF.get(extension);
    }

    private static Codec getCodecFromExtension(String output) {
        String extension = output.replaceFirst(".*\\.([^\\.]+$)", "$1");
        return extensionToC.get(extension);
    }

    static {
        extensionToF.put("mp3", Format.MPEG_AUDIO);
        extensionToF.put("mp2", Format.MPEG_AUDIO);
        extensionToF.put("mp1", Format.MPEG_AUDIO);
        extensionToF.put("mpg", Format.MPEG_PS);
        extensionToF.put("mpeg", Format.MPEG_PS);
        extensionToF.put("m2p", Format.MPEG_PS);
        extensionToF.put("ps", Format.MPEG_PS);
        extensionToF.put("vob", Format.MPEG_PS);
        extensionToF.put("evo", Format.MPEG_PS);
        extensionToF.put("mod", Format.MPEG_PS);
        extensionToF.put("tod", Format.MPEG_PS);
        extensionToF.put("ts", Format.MPEG_TS);
        extensionToF.put("m2t", Format.MPEG_TS);
        extensionToF.put("mp4", Format.MOV);
        extensionToF.put("m4a", Format.MOV);
        extensionToF.put("m4v", Format.MOV);
        extensionToF.put("mov", Format.MOV);
        extensionToF.put("3gp", Format.MOV);
        extensionToF.put("mkv", Format.MKV);
        extensionToF.put("webm", Format.MKV);
        extensionToF.put("264", Format.H264);
        extensionToF.put("jsv", Format.H264);
        extensionToF.put("h264", Format.H264);
        extensionToF.put("raw", Format.RAW);
        extensionToF.put("", Format.RAW);
        extensionToF.put("flv", Format.FLV);
        extensionToF.put("avi", Format.AVI);
        extensionToF.put("jpg", Format.IMG);
        extensionToF.put("jpeg", Format.IMG);
        extensionToF.put("png", Format.IMG);
        extensionToF.put("mjp", Format.MJPEG);
        extensionToF.put("ivf", Format.IVF);
        extensionToF.put("y4m", Format.Y4M);
        extensionToF.put("wav", Format.WAV);
        extensionToC.put("mpg", Codec.MPEG2);
        extensionToC.put("mpeg", Codec.MPEG2);
        extensionToC.put("m2p", Codec.MPEG2);
        extensionToC.put("ps", Codec.MPEG2);
        extensionToC.put("vob", Codec.MPEG2);
        extensionToC.put("evo", Codec.MPEG2);
        extensionToC.put("mod", Codec.MPEG2);
        extensionToC.put("tod", Codec.MPEG2);
        extensionToC.put("ts", Codec.MPEG2);
        extensionToC.put("m2t", Codec.MPEG2);
        extensionToC.put("m4a", Codec.AAC);
        extensionToC.put("mkv", Codec.H264);
        extensionToC.put("webm", Codec.VP8);
        extensionToC.put("264", Codec.H264);
        extensionToC.put("raw", Codec.RAW);
        extensionToC.put("jpg", Codec.JPEG);
        extensionToC.put("jpeg", Codec.JPEG);
        extensionToC.put("png", Codec.PNG);
        extensionToC.put("mjp", Codec.JPEG);
        extensionToC.put("y4m", Codec.RAW);
        videoCodecsForF.put(Format.MPEG_PS, Codec.MPEG2);
        audioCodecsForF.put(Format.MPEG_PS, Codec.MP2);
        videoCodecsForF.put(Format.MOV, Codec.H264);
        audioCodecsForF.put(Format.MOV, Codec.AAC);
        videoCodecsForF.put(Format.MKV, Codec.VP8);
        audioCodecsForF.put(Format.MKV, Codec.VORBIS);
        audioCodecsForF.put(Format.WAV, Codec.PCM);
        videoCodecsForF.put(Format.H264, Codec.H264);
        videoCodecsForF.put(Format.RAW, Codec.RAW);
        videoCodecsForF.put(Format.FLV, Codec.H264);
        videoCodecsForF.put(Format.AVI, Codec.MPEG4);
        videoCodecsForF.put(Format.IMG, Codec.PNG);
        videoCodecsForF.put(Format.MJPEG, Codec.JPEG);
        videoCodecsForF.put(Format.IVF, Codec.VP8);
        videoCodecsForF.put(Format.Y4M, Codec.RAW);
        supportedDecoders.add(Codec.AAC);
        supportedDecoders.add(Codec.H264);
        supportedDecoders.add(Codec.JPEG);
        supportedDecoders.add(Codec.MPEG2);
        supportedDecoders.add(Codec.PCM);
        supportedDecoders.add(Codec.PNG);
        supportedDecoders.add(Codec.MPEG4);
        supportedDecoders.add(Codec.PRORES);
        supportedDecoders.add(Codec.RAW);
        supportedDecoders.add(Codec.VP8);
        supportedDecoders.add(Codec.MP3);
        supportedDecoders.add(Codec.MP2);
        supportedDecoders.add(Codec.MP1);
        knownFilters.put("scale", ScaleFilter.class);
    }
}
