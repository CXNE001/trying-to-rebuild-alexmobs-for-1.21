/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Utils;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.mp4.AvcCBox;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.SeekableDemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.common.model.TapeTimecode;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.MkvBlock;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.demuxer.MKVDemuxer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public static class MKVDemuxer.VideoTrack
implements SeekableDemuxerTrack {
    private ByteBuffer state;
    public final int trackNo;
    private int frameIdx = 0;
    List<MkvBlock> blocks = new ArrayList<MkvBlock>();
    private MKVDemuxer demuxer;
    private Codec codec;
    private AvcCBox avcC;

    public MKVDemuxer.VideoTrack(MKVDemuxer demuxer, int trackNo, ByteBuffer state, Codec codec) {
        this.demuxer = demuxer;
        this.trackNo = trackNo;
        this.codec = codec;
        if (codec == Codec.H264) {
            this.avcC = H264Utils.parseAVCCFromBuffer(state);
            this.state = H264Utils.avcCToAnnexB(this.avcC);
        } else {
            this.state = state;
        }
    }

    @Override
    public Packet nextFrame() throws IOException {
        if (this.frameIdx >= this.blocks.size()) {
            return null;
        }
        MkvBlock b = this.blocks.get(this.frameIdx);
        if (b == null) {
            throw new RuntimeException("Something somewhere went wrong.");
        }
        ++this.frameIdx;
        this.demuxer.channel.setPosition(b.dataOffset);
        ByteBuffer data = ByteBuffer.allocate(b.dataLen);
        this.demuxer.channel.read(data);
        data.flip();
        b.readFrames(data.duplicate());
        long duration = 1L;
        if (this.frameIdx < this.blocks.size()) {
            duration = this.blocks.get((int)this.frameIdx).absoluteTimecode - b.absoluteTimecode;
        }
        ByteBuffer result = b.frames[0].duplicate();
        if (this.codec == Codec.H264) {
            result = H264Utils.decodeMOVPacket(result, this.avcC);
        }
        return Packet.createPacket(result, b.absoluteTimecode, this.demuxer.timescale, duration, this.frameIdx - 1, b._keyFrame ? Packet.FrameType.KEY : Packet.FrameType.INTER, TapeTimecode.ZERO_TAPE_TIMECODE);
    }

    @Override
    public boolean gotoFrame(long i) {
        if (i > Integer.MAX_VALUE) {
            return false;
        }
        if (i > (long)this.blocks.size()) {
            return false;
        }
        this.frameIdx = (int)i;
        return true;
    }

    @Override
    public long getCurFrame() {
        return this.frameIdx;
    }

    @Override
    public void seek(double second) {
        throw new RuntimeException("Not implemented yet");
    }

    public int getFrameCount() {
        return this.blocks.size();
    }

    public ByteBuffer getCodecState() {
        return this.state;
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        return new DemuxerTrackMeta(TrackType.VIDEO, this.codec, 0.0, null, 0, this.state, VideoCodecMeta.createSimpleVideoCodecMeta(new Size(this.demuxer.pictureWidth, this.demuxer.pictureHeight), ColorSpace.YUV420), null);
    }

    @Override
    public boolean gotoSyncFrame(long i) {
        throw new RuntimeException("Unsupported");
    }
}
