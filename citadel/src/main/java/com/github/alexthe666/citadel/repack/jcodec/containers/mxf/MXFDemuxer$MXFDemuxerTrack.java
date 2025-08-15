/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf;

import com.github.alexthe666.citadel.repack.jcodec.api.NotSupportedException;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.SeekableDemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.MXFCodec;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.MXFDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.GenericDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.GenericPictureEssenceDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.GenericSoundEssenceDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.IndexSegment;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.KLV;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFPartition;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.TimelineTrack;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.WaveAudioDescriptor;
import java.io.IOException;

public static class MXFDemuxer.MXFDemuxerTrack
implements SeekableDemuxerTrack {
    private UL essenceUL;
    private int dataLen;
    private int indexSegmentIdx;
    private int indexSegmentSubIdx;
    private int frameNo;
    private long pts;
    private int partIdx;
    private long partEssenceOffset;
    private GenericDescriptor descriptor;
    private TimelineTrack track;
    private boolean video;
    private boolean audio;
    private MXFCodec codec;
    private int audioFrameDuration;
    private int audioTimescale;
    private MXFDemuxer demuxer;

    public MXFDemuxer.MXFDemuxerTrack(MXFDemuxer demuxer, UL essenceUL, TimelineTrack track, GenericDescriptor descriptor) throws IOException {
        this.demuxer = demuxer;
        this.essenceUL = essenceUL;
        this.track = track;
        this.descriptor = descriptor;
        if (descriptor instanceof GenericPictureEssenceDescriptor) {
            this.video = true;
        } else if (descriptor instanceof GenericSoundEssenceDescriptor) {
            this.audio = true;
        }
        this.codec = this.resolveCodec();
        if (this.codec != null || descriptor instanceof WaveAudioDescriptor) {
            Logger.warn("Track type: " + this.video + ", " + this.audio);
            if (this.audio && descriptor instanceof WaveAudioDescriptor) {
                WaveAudioDescriptor wave = (WaveAudioDescriptor)descriptor;
                this.cacheAudioFrameSizes(demuxer.ch);
                this.audioFrameDuration = this.dataLen / ((wave.getQuantizationBits() >> 3) * wave.getChannelCount());
                this.audioTimescale = (int)wave.getAudioSamplingRate().scalar();
            }
        }
    }

    public boolean isAudio() {
        return this.audio;
    }

    public boolean isVideo() {
        return this.video;
    }

    public double getDuration() {
        return this.demuxer.duration;
    }

    public int getNumFrames() {
        return this.demuxer.totalFrames;
    }

    public String getName() {
        return this.track.getName();
    }

    private void cacheAudioFrameSizes(SeekableByteChannel ch) throws IOException {
        for (MXFPartition mxfPartition : this.demuxer.partitions) {
            KLV kl;
            if (mxfPartition.getEssenceLength() <= 0L) continue;
            ch.setPosition(mxfPartition.getEssenceFilePos());
            while ((kl = KLV.readKL(ch)) != null) {
                ch.setPosition(ch.position() + kl.len);
                if (!this.essenceUL.equals(kl.key)) continue;
            }
            if (kl == null || !this.essenceUL.equals(kl.key)) continue;
            this.dataLen = (int)kl.len;
            break;
        }
    }

    @Override
    public Packet nextFrame() throws IOException {
        MXFDemuxer.MXFPacket result;
        boolean kf;
        if (this.indexSegmentIdx >= this.demuxer.indexSegments.size()) {
            return null;
        }
        IndexSegment seg = this.demuxer.indexSegments.get(this.indexSegmentIdx);
        long[] off = seg.getIe().getFileOff();
        int erDen = seg.getIndexEditRateNum();
        int erNum = seg.getIndexEditRateDen();
        long frameEssenceOffset = off[this.indexSegmentSubIdx];
        byte toff = seg.getIe().getDisplayOff()[this.indexSegmentSubIdx];
        boolean bl = kf = seg.getIe().getKeyFrameOff()[this.indexSegmentSubIdx] == 0;
        while (frameEssenceOffset >= this.partEssenceOffset + this.demuxer.partitions.get(this.partIdx).getEssenceLength() && this.partIdx < this.demuxer.partitions.size() - 1) {
            this.partEssenceOffset += this.demuxer.partitions.get(this.partIdx).getEssenceLength();
            ++this.partIdx;
        }
        long frameFileOffset = frameEssenceOffset - this.partEssenceOffset + this.demuxer.partitions.get(this.partIdx).getEssenceFilePos();
        if (!this.audio) {
            result = this.readPacket(frameFileOffset, this.dataLen, this.pts + (long)(erNum * toff), erDen, erNum, this.frameNo++, kf);
            this.pts += (long)erNum;
        } else {
            result = this.readPacket(frameFileOffset, this.dataLen, this.pts, this.audioTimescale, this.audioFrameDuration, this.frameNo++, kf);
            this.pts += (long)this.audioFrameDuration;
        }
        ++this.indexSegmentSubIdx;
        if (this.indexSegmentSubIdx >= off.length) {
            ++this.indexSegmentIdx;
            this.indexSegmentSubIdx = 0;
            if (this.dataLen == 0 && this.indexSegmentIdx < this.demuxer.indexSegments.size()) {
                IndexSegment nseg = this.demuxer.indexSegments.get(this.indexSegmentIdx);
                this.pts = this.pts * (long)nseg.getIndexEditRateNum() / (long)erDen;
            }
        }
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public MXFDemuxer.MXFPacket readPacket(long off, int len, long pts, int timescale, int duration, int frameNo, boolean kf) throws IOException {
        SeekableByteChannel ch;
        SeekableByteChannel seekableByteChannel = ch = this.demuxer.ch;
        synchronized (seekableByteChannel) {
            ch.setPosition(off);
            KLV kl = KLV.readKL(ch);
            while (kl != null && !this.essenceUL.equals(kl.key)) {
                ch.setPosition(ch.position() + kl.len);
                kl = KLV.readKL(ch);
            }
            return kl != null && this.essenceUL.equals(kl.key) ? new MXFDemuxer.MXFPacket(NIOUtils.fetchFromChannel(ch, (int)kl.len), pts, timescale, duration, frameNo, kf ? Packet.FrameType.KEY : Packet.FrameType.INTER, null, off, len) : null;
        }
    }

    @Override
    public boolean gotoFrame(long frameNo) {
        if (frameNo == (long)this.frameNo) {
            return true;
        }
        this.indexSegmentSubIdx = (int)frameNo;
        this.indexSegmentIdx = 0;
        while (this.indexSegmentIdx < this.demuxer.indexSegments.size() && (long)this.indexSegmentSubIdx >= this.demuxer.indexSegments.get(this.indexSegmentIdx).getIndexDuration()) {
            this.indexSegmentSubIdx = (int)((long)this.indexSegmentSubIdx - this.demuxer.indexSegments.get(this.indexSegmentIdx).getIndexDuration());
            ++this.indexSegmentIdx;
        }
        this.indexSegmentSubIdx = Math.min(this.indexSegmentSubIdx, (int)this.demuxer.indexSegments.get(this.indexSegmentIdx).getIndexDuration());
        return true;
    }

    @Override
    public boolean gotoSyncFrame(long frameNo) {
        if (!this.gotoFrame(frameNo)) {
            return false;
        }
        IndexSegment seg = this.demuxer.indexSegments.get(this.indexSegmentIdx);
        byte kfOff = seg.getIe().getKeyFrameOff()[this.indexSegmentSubIdx];
        return this.gotoFrame(frameNo + (long)kfOff);
    }

    @Override
    public long getCurFrame() {
        return this.frameNo;
    }

    @Override
    public void seek(double second) {
        throw new NotSupportedException("");
    }

    public UL getEssenceUL() {
        return this.essenceUL;
    }

    public GenericDescriptor getDescriptor() {
        return this.descriptor;
    }

    public MXFCodec getCodec() {
        return this.codec;
    }

    private MXFCodec resolveCodec() {
        UL codecUL;
        if (this.video) {
            codecUL = ((GenericPictureEssenceDescriptor)this.descriptor).getPictureEssenceCoding();
        } else if (this.audio) {
            codecUL = ((GenericSoundEssenceDescriptor)this.descriptor).getSoundEssenceCompression();
        } else {
            return null;
        }
        MXFCodec[] values = MXFCodec.values();
        for (int i = 0; i < values.length; ++i) {
            MXFCodec codec = values[i];
            if (!codec.getUl().equals(codecUL)) continue;
            return codec;
        }
        Logger.warn("Unknown codec: " + codecUL);
        return null;
    }

    public int getTrackId() {
        return this.track.getTrackId();
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        Size size = null;
        if (this.video) {
            GenericPictureEssenceDescriptor pd = (GenericPictureEssenceDescriptor)this.descriptor;
            size = new Size(pd.getStoredWidth(), pd.getStoredHeight());
        }
        TrackType t = this.video ? TrackType.VIDEO : (this.audio ? TrackType.AUDIO : TrackType.OTHER);
        return new DemuxerTrackMeta(t, this.getCodec().getCodec(), this.demuxer.duration, null, this.demuxer.totalFrames, null, VideoCodecMeta.createSimpleVideoCodecMeta(size, ColorSpace.YUV420), null);
    }

    public Rational getEditRate() {
        return this.track.getEditRate();
    }
}
