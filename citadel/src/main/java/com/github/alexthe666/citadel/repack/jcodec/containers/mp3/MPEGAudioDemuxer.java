/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp3;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.Demuxer;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.UsedViaReflection;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class MPEGAudioDemuxer
implements Demuxer,
DemuxerTrack {
    private static final int MAX_FRAME_SIZE = 1728;
    private static final int MIN_FRAME_SIZE = 52;
    private static final int CHANNELS = MPEGAudioDemuxer.field(6, 2);
    private static final int PADDING = MPEGAudioDemuxer.field(9, 1);
    private static final int SAMPLE_RATE = MPEGAudioDemuxer.field(10, 2);
    private static final int BITRATE = MPEGAudioDemuxer.field(12, 4);
    private static final int VERSION = MPEGAudioDemuxer.field(19, 2);
    private static final int LAYER = MPEGAudioDemuxer.field(17, 2);
    private static final int SYNC = MPEGAudioDemuxer.field(21, 11);
    private static final int MPEG1 = 3;
    private static final int MPEG2 = 2;
    private static final int MPEG25 = 0;
    private static int[][][] bitrateTable = new int[][][]{new int[][]{{0, 32, 64, 96, 128, 160, 192, 224, 256, 288, 320, 352, 384, 416, 448}, {0, 32, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384}, {0, 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320}}, new int[][]{{0, 32, 48, 56, 64, 80, 96, 112, 128, 144, 160, 176, 192, 224, 256}, {0, 8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160}, {0, 8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160}}};
    private static int[] freqTab = new int[]{44100, 48000, 32000};
    private static int[] rateReductTab = new int[]{2, 0, 1, 0};
    private SeekableByteChannel ch;
    private List<DemuxerTrack> tracks;
    private int frameNo;
    private ByteBuffer readBuffer;
    private int runningFour;
    private boolean eof;
    private DemuxerTrackMeta meta;
    private int sampleRate;

    private static int field(int off, int size) {
        return (1 << size) - 1 << 16 | off;
    }

    private static int getField(int header, int field) {
        return header >> (field & 0xFFFF) & field >> 16;
    }

    public MPEGAudioDemuxer(SeekableByteChannel ch) throws IOException {
        this.ch = ch;
        this.readBuffer = ByteBuffer.allocate(262144);
        this.readMoreData();
        if (this.readBuffer.remaining() < 4) {
            this.eof = true;
        } else {
            this.runningFour = this.readBuffer.getInt();
            if (!MPEGAudioDemuxer.validHeader(this.runningFour)) {
                this.eof = this.skipJunk();
            }
            this.extractMeta();
        }
        this.tracks = new ArrayList<DemuxerTrack>();
        this.tracks.add(this);
    }

    private void extractMeta() {
        if (!MPEGAudioDemuxer.validHeader(this.runningFour)) {
            return;
        }
        int layer = 3 - MPEGAudioDemuxer.getField(this.runningFour, LAYER);
        int channelCount = MPEGAudioDemuxer.getField(this.runningFour, CHANNELS) == 3 ? 1 : 2;
        int version = MPEGAudioDemuxer.getField(this.runningFour, VERSION);
        this.sampleRate = freqTab[MPEGAudioDemuxer.getField(this.runningFour, SAMPLE_RATE)] >> rateReductTab[version];
        AudioCodecMeta codecMeta = AudioCodecMeta.createAudioCodecMeta(".mp3", 16, channelCount, this.sampleRate, ByteOrder.LITTLE_ENDIAN, false, null, null);
        Codec codec = layer == 2 ? Codec.MP3 : (layer == 1 ? Codec.MP2 : Codec.MP1);
        this.meta = new DemuxerTrackMeta(TrackType.AUDIO, codec, 0.0, null, 0, null, null, codecMeta);
    }

    @Override
    public void close() throws IOException {
        this.ch.close();
    }

    @Override
    public List<? extends DemuxerTrack> getTracks() {
        return this.tracks;
    }

    @Override
    public List<? extends DemuxerTrack> getVideoTracks() {
        return null;
    }

    @Override
    public List<? extends DemuxerTrack> getAudioTracks() {
        return this.tracks;
    }

    @Override
    public Packet nextFrame() throws IOException {
        if (this.eof) {
            return null;
        }
        if (!MPEGAudioDemuxer.validHeader(this.runningFour)) {
            this.eof = this.skipJunk();
        }
        int frameSize = MPEGAudioDemuxer.calcFrameSize(this.runningFour);
        ByteBuffer frame = ByteBuffer.allocate(frameSize);
        this.eof = this.readFrame(frame);
        frame.flip();
        Packet pkt = new Packet(frame, this.frameNo * 1152, this.sampleRate, 1152L, this.frameNo, Packet.FrameType.KEY, null, 0);
        ++this.frameNo;
        return pkt;
    }

    private static boolean validHeader(int four) {
        if (MPEGAudioDemuxer.getField(four, SYNC) != 2047) {
            return false;
        }
        if (MPEGAudioDemuxer.getField(four, LAYER) == 0) {
            return false;
        }
        if (MPEGAudioDemuxer.getField(four, SAMPLE_RATE) == 3) {
            return false;
        }
        return MPEGAudioDemuxer.getField(four, BITRATE) != 15;
    }

    private void readMoreData() throws IOException {
        this.readBuffer.clear();
        this.ch.read(this.readBuffer);
        this.readBuffer.flip();
    }

    private static int calcFrameSize(int header) {
        int bitrateIdx = MPEGAudioDemuxer.getField(header, BITRATE);
        int layer = 3 - MPEGAudioDemuxer.getField(header, LAYER);
        int version = MPEGAudioDemuxer.getField(header, VERSION);
        int mpeg2 = version != 3 ? 1 : 0;
        int bitRate = bitrateTable[mpeg2][layer][bitrateIdx] * 1000;
        int sampleRate = freqTab[MPEGAudioDemuxer.getField(header, SAMPLE_RATE)] >> rateReductTab[version];
        int padding = MPEGAudioDemuxer.getField(header, PADDING);
        int lsf = version == 0 || version == 2 ? 1 : 0;
        switch (layer) {
            case 0: {
                return (bitRate * 12 / sampleRate + padding) * 4;
            }
            case 1: {
                return bitRate * 144 / sampleRate + padding;
            }
        }
        return bitRate * 144 / (sampleRate << lsf) + padding;
    }

    private boolean readFrame(ByteBuffer frame) throws IOException {
        boolean eof = false;
        while (frame.hasRemaining()) {
            frame.put((byte)(this.runningFour >> 24));
            this.runningFour <<= 8;
            if (!this.readBuffer.hasRemaining()) {
                this.readMoreData();
            }
            if (this.readBuffer.hasRemaining()) {
                this.runningFour |= this.readBuffer.get() & 0xFF;
                continue;
            }
            eof = true;
        }
        return eof;
    }

    private boolean skipJunk() throws IOException {
        boolean eof = false;
        int total = 0;
        while (!MPEGAudioDemuxer.validHeader(this.runningFour)) {
            if (!this.readBuffer.hasRemaining()) {
                this.readMoreData();
            }
            if (!this.readBuffer.hasRemaining()) {
                eof = true;
                break;
            }
            this.runningFour <<= 8;
            this.runningFour |= this.readBuffer.get() & 0xFF;
            ++total;
        }
        Logger.warn(String.format("[mp3demuxer] Skipped %d bytes of junk", total));
        return eof;
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        return this.meta;
    }

    @UsedViaReflection
    public static int probe(ByteBuffer b) {
        ByteBuffer fork = b.duplicate();
        int valid = 0;
        int total = 0;
        int header = fork.getInt();
        do {
            if (!MPEGAudioDemuxer.validHeader(header)) {
                header = MPEGAudioDemuxer.skipJunkBB(header, fork);
            }
            int size = MPEGAudioDemuxer.calcFrameSize(header);
            if (fork.remaining() < size) break;
            ++total;
            if (size > 0) {
                NIOUtils.skip(fork, size - 4);
            } else {
                header = MPEGAudioDemuxer.skipJunkBB(header, fork);
            }
            if (fork.remaining() < 4) continue;
            header = fork.getInt();
            if (size < 52 || size > 1728 || !MPEGAudioDemuxer.validHeader(header)) continue;
            ++valid;
        } while (fork.remaining() >= 4);
        return 100 * valid / total;
    }

    private static int skipJunkBB(int header, ByteBuffer fork) {
        while (!MPEGAudioDemuxer.validHeader(header) && fork.hasRemaining()) {
            header <<= 8;
            header |= fork.get() & 0xFF;
        }
        return header;
    }
}
