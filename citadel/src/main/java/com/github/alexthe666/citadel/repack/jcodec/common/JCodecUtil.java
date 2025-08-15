/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.AACDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.BufferH264ES;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Decoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg.JpegDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.MPEG4Decoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.ppm.PPMEncoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.prores.ProresDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VP8Decoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.wav.WavDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.Demuxer;
import com.github.alexthe666.citadel.repack.jcodec.common.Format;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.Tuple;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import com.github.alexthe666.citadel.repack.jcodec.containers.imgseq.ImageSequenceDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer.MKVDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp3.MPEGAudioDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.demuxer.MP4Demuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.webp.WebpDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.y4m.Y4MDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import com.github.alexthe666.citadel.repack.jcodec.scale.ColorUtil;
import com.github.alexthe666.citadel.repack.jcodec.scale.Transform;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JCodecUtil {
    private static final Map<Codec, Class<?>> decoders = new HashMap();
    private static final Map<Format, Class<?>> demuxers = new HashMap();

    public static Format detectFormat(File f) throws IOException {
        return JCodecUtil.detectFormatBuffer(NIOUtils.fetchFromFileL(f, 204800));
    }

    public static Format detectFormatChannel(ReadableByteChannel f) throws IOException {
        return JCodecUtil.detectFormatBuffer(NIOUtils.fetchFromChannel(f, 204800));
    }

    public static Format detectFormatBuffer(ByteBuffer b) {
        int maxScore = 0;
        Format selected = null;
        for (Map.Entry<Format, Class<?>> vd : demuxers.entrySet()) {
            int score = JCodecUtil.probe(b.duplicate(), vd.getValue());
            if (score <= maxScore) continue;
            selected = vd.getKey();
            maxScore = score;
        }
        return selected;
    }

    public static Codec detectDecoder(ByteBuffer b) {
        int maxScore = 0;
        Codec selected = null;
        for (Map.Entry<Codec, Class<?>> vd : decoders.entrySet()) {
            int score = JCodecUtil.probe(b.duplicate(), vd.getValue());
            if (score <= maxScore) continue;
            selected = vd.getKey();
            maxScore = score;
        }
        return selected;
    }

    private static int probe(ByteBuffer b, Class<?> vd) {
        try {
            return (Integer)Platform.invokeStaticMethod(vd, "probe", new Object[]{b});
        }
        catch (Exception exception) {
            return 0;
        }
    }

    public static VideoDecoder getVideoDecoder(String fourcc) {
        if ("apch".equals(fourcc) || "apcs".equals(fourcc) || "apco".equals(fourcc) || "apcn".equals(fourcc) || "ap4h".equals(fourcc)) {
            return new ProresDecoder();
        }
        if ("m2v1".equals(fourcc)) {
            return new MPEGDecoder();
        }
        return null;
    }

    public static void savePictureAsPPM(Picture pic, File file) throws IOException {
        Transform transform = ColorUtil.getTransform(pic.getColor(), ColorSpace.RGB);
        Picture rgb = Picture.create(pic.getWidth(), pic.getHeight(), ColorSpace.RGB);
        transform.transform(pic, rgb);
        NIOUtils.writeTo(new PPMEncoder().encodeFrame(rgb), file);
    }

    public static byte[] asciiString(String fourcc) {
        char[] ch = fourcc.toCharArray();
        byte[] result = new byte[ch.length];
        for (int i = 0; i < ch.length; ++i) {
            result[i] = (byte)ch[i];
        }
        return result;
    }

    public static void writeBER32(ByteBuffer buffer, int value) {
        buffer.put((byte)(value >> 21 | 0x80));
        buffer.put((byte)(value >> 14 | 0x80));
        buffer.put((byte)(value >> 7 | 0x80));
        buffer.put((byte)(value & 0x7F));
    }

    public static void writeBER32Var(ByteBuffer bb, int value) {
        int bits = MathUtil.log2(value);
        for (int i = 0; i < 4 && bits > 0; ++i) {
            int out = value >> (bits -= 7);
            if (bits > 0) {
                out |= 0x80;
            }
            bb.put((byte)out);
        }
    }

    public static int readBER32(ByteBuffer input) {
        int size = 0;
        for (int i = 0; i < 4; ++i) {
            byte b = input.get();
            size = size << 7 | b & 0x7F;
            if ((b & 0xFF) >> 7 == 0) break;
        }
        return size;
    }

    public static int[] getAsIntArray(ByteBuffer yuv, int size) {
        byte[] b = new byte[size];
        int[] result = new int[size];
        yuv.get(b);
        for (int i = 0; i < b.length; ++i) {
            result[i] = b[i] & 0xFF;
        }
        return result;
    }

    public static String removeExtension(String name) {
        if (name == null) {
            return null;
        }
        return name.replaceAll("\\.[^\\.]+$", "");
    }

    public static Demuxer createDemuxer(Format format, File input) throws IOException {
        FileChannelWrapper ch = null;
        if (format != Format.IMG) {
            ch = NIOUtils.readableChannel(input);
        }
        if (Format.MOV == format) {
            return MP4Demuxer.createMP4Demuxer(ch);
        }
        if (Format.MPEG_PS == format) {
            return new MPSDemuxer(ch);
        }
        if (Format.MKV == format) {
            return new MKVDemuxer(ch);
        }
        if (Format.IMG == format) {
            return new ImageSequenceDemuxer(input.getAbsolutePath(), Integer.MAX_VALUE);
        }
        if (Format.Y4M == format) {
            return new Y4MDemuxer(ch);
        }
        if (Format.WEBP == format) {
            return new WebpDemuxer(ch);
        }
        if (Format.H264 == format) {
            return new BufferH264ES(NIOUtils.fetchAllFromChannel(ch));
        }
        if (Format.WAV == format) {
            return new WavDemuxer(ch);
        }
        if (Format.MPEG_AUDIO == format) {
            return new MPEGAudioDemuxer(ch);
        }
        Logger.error("Format " + format + " is not supported");
        return null;
    }

    public static Tuple._2<Integer, Demuxer> createM2TSDemuxer(File input, TrackType targetTrack) throws IOException {
        FileChannelWrapper ch = NIOUtils.readableChannel(input);
        MTSDemuxer mts = new MTSDemuxer(ch);
        Set<Integer> programs = mts.getPrograms();
        if (programs.size() == 0) {
            Logger.error("The MPEG TS stream contains no programs");
            return null;
        }
        Tuple._2<Integer, MPSDemuxer> found = null;
        for (Integer pid : programs) {
            ReadableByteChannel program = mts.getProgram(pid);
            if (found != null) {
                program.close();
                continue;
            }
            MPSDemuxer demuxer = new MPSDemuxer(program);
            if (targetTrack == TrackType.AUDIO && demuxer.getAudioTracks().size() > 0 || targetTrack == TrackType.VIDEO && demuxer.getVideoTracks().size() > 0) {
                found = Tuple.pair(pid, demuxer);
                Logger.info("Using M2TS program: " + pid + " for " + (Object)((Object)targetTrack) + " track.");
                continue;
            }
            program.close();
        }
        return found;
    }

    public static AudioDecoder createAudioDecoder(Codec codec, ByteBuffer decoderSpecific) throws IOException {
        if (Codec.AAC == codec) {
            return new AACDecoder(decoderSpecific);
        }
        Logger.error("Codec " + codec + " is not supported");
        return null;
    }

    public static VideoDecoder createVideoDecoder(Codec codec, ByteBuffer decoderSpecific) {
        if (Codec.H264 == codec) {
            return decoderSpecific != null ? H264Decoder.createH264DecoderFromCodecPrivate(decoderSpecific) : new H264Decoder();
        }
        if (Codec.MPEG2 == codec) {
            return new MPEGDecoder();
        }
        if (Codec.VP8 == codec) {
            return new VP8Decoder();
        }
        if (Codec.JPEG == codec) {
            return new JpegDecoder();
        }
        Logger.error("Codec " + codec + " is not supported");
        return null;
    }

    static {
        decoders.put(Codec.VP8, VP8Decoder.class);
        decoders.put(Codec.PRORES, ProresDecoder.class);
        decoders.put(Codec.MPEG2, MPEGDecoder.class);
        decoders.put(Codec.H264, H264Decoder.class);
        decoders.put(Codec.AAC, AACDecoder.class);
        decoders.put(Codec.MPEG4, MPEG4Decoder.class);
        demuxers.put(Format.MPEG_TS, MTSDemuxer.class);
        demuxers.put(Format.MPEG_PS, MPSDemuxer.class);
        demuxers.put(Format.MOV, MP4Demuxer.class);
        demuxers.put(Format.WEBP, WebpDemuxer.class);
        demuxers.put(Format.MPEG_AUDIO, MPEGAudioDemuxer.class);
    }
}
