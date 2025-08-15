/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.y4m;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.Demuxer;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.StringUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Y4MDemuxer
implements DemuxerTrack,
Demuxer {
    private SeekableByteChannel is;
    private int width;
    private int height;
    private String invalidFormat;
    private Rational fps;
    private int bufSize;
    private int frameNum;
    private int totalFrames;
    private int totalDuration;

    public Y4MDemuxer(SeekableByteChannel _is) throws IOException {
        this.is = _is;
        ByteBuffer buf = NIOUtils.fetchFromChannel(this.is, 2048);
        String[] header = StringUtils.splitC(Y4MDemuxer.readLine(buf), ' ');
        if (!"YUV4MPEG2".equals(header[0])) {
            this.invalidFormat = "Not yuv4mpeg stream";
            return;
        }
        String chroma = Y4MDemuxer.find(header, 'C');
        if (chroma != null && !chroma.startsWith("420")) {
            this.invalidFormat = "Only yuv420p is supported";
            return;
        }
        this.width = Integer.parseInt(Y4MDemuxer.find(header, 'W'));
        this.height = Integer.parseInt(Y4MDemuxer.find(header, 'H'));
        String fpsStr = Y4MDemuxer.find(header, 'F');
        if (fpsStr != null) {
            String[] numden = StringUtils.splitC(fpsStr, ':');
            this.fps = new Rational(Integer.parseInt(numden[0]), Integer.parseInt(numden[1]));
        }
        this.is.setPosition(buf.position());
        this.bufSize = this.width * this.height;
        this.bufSize += this.bufSize / 2;
        long fileSize = this.is.size();
        this.totalFrames = (int)(fileSize / (long)(this.bufSize + 7));
        this.totalDuration = this.totalFrames * this.fps.getDen() / this.fps.getNum();
    }

    @Override
    public Packet nextFrame() throws IOException {
        if (this.invalidFormat != null) {
            throw new RuntimeException("Invalid input: " + this.invalidFormat);
        }
        ByteBuffer buf = NIOUtils.fetchFromChannel(this.is, 2048);
        String frame = Y4MDemuxer.readLine(buf);
        if (frame == null || !frame.startsWith("FRAME")) {
            return null;
        }
        this.is.setPosition(this.is.position() - (long)buf.remaining());
        ByteBuffer pix = NIOUtils.fetchFromChannel(this.is, this.bufSize);
        Packet packet = new Packet(pix, this.frameNum * this.fps.getDen(), this.fps.getNum(), this.fps.getDen(), this.frameNum, Packet.FrameType.KEY, null, this.frameNum);
        ++this.frameNum;
        return packet;
    }

    private static String find(String[] header, char c) {
        for (int i = 0; i < header.length; ++i) {
            String string = header[i];
            if (string.charAt(0) != c) continue;
            return string.substring(1);
        }
        return null;
    }

    private static String readLine(ByteBuffer y4m) {
        ByteBuffer duplicate = y4m.duplicate();
        while (y4m.hasRemaining() && y4m.get() != 10) {
        }
        if (y4m.hasRemaining()) {
            duplicate.limit(y4m.position() - 1);
        }
        return Platform.stringFromBytes(NIOUtils.toArray(duplicate));
    }

    public Rational getFps() {
        return this.fps;
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        return new DemuxerTrackMeta(TrackType.VIDEO, Codec.RAW, this.totalDuration, null, this.totalFrames, null, VideoCodecMeta.createSimpleVideoCodecMeta(new Size(this.width, this.height), ColorSpace.YUV420), null);
    }

    @Override
    public void close() throws IOException {
        this.is.close();
    }

    @Override
    public List<? extends DemuxerTrack> getTracks() {
        ArrayList<Y4MDemuxer> list = new ArrayList<Y4MDemuxer>();
        list.add(this);
        return list;
    }

    @Override
    public List<? extends DemuxerTrack> getVideoTracks() {
        return this.getTracks();
    }

    @Override
    public List<? extends DemuxerTrack> getAudioTracks() {
        return new ArrayList();
    }
}
