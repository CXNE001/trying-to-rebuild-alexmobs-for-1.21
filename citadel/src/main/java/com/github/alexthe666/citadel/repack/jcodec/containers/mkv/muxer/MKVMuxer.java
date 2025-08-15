/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.muxer;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.Muxer;
import com.github.alexthe666.citadel.repack.jcodec.common.MuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.CuesFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.MKVType;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.SeekHeadFactory;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBase;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBin;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlDate;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlFloat;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlMaster;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlString;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlUint;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.MkvBlock;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.muxer.MKVMuxerTrack;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MKVMuxer
implements Muxer {
    private List<MKVMuxerTrack> tracks;
    private MKVMuxerTrack audioTrack;
    private MKVMuxerTrack videoTrack;
    private EbmlMaster mkvInfo;
    private EbmlMaster mkvTracks;
    private EbmlMaster mkvCues;
    private EbmlMaster mkvSeekHead;
    private List<EbmlMaster> clusterList;
    private SeekableByteChannel sink;
    private static Map<Codec, String> codec2mkv = new HashMap<Codec, String>();

    public MKVMuxer(SeekableByteChannel s) {
        this.sink = s;
        this.tracks = new ArrayList<MKVMuxerTrack>();
        this.clusterList = new LinkedList<EbmlMaster>();
    }

    public MKVMuxerTrack createVideoTrack(VideoCodecMeta meta, String codecId) {
        if (this.videoTrack == null) {
            this.videoTrack = new MKVMuxerTrack();
            this.tracks.add(this.videoTrack);
            this.videoTrack.codecId = codecId;
            this.videoTrack.videoMeta = meta;
            this.videoTrack.trackNo = this.tracks.size();
        }
        return this.videoTrack;
    }

    @Override
    public void finish() throws IOException {
        ArrayList<EbmlMaster> mkvFile = new ArrayList<EbmlMaster>();
        EbmlMaster ebmlHeader = this.defaultEbmlHeader();
        mkvFile.add(ebmlHeader);
        EbmlMaster segmentElem = (EbmlMaster)MKVType.createByType(MKVType.Segment);
        this.mkvInfo = this.muxInfo();
        this.mkvTracks = this.muxTracks();
        this.mkvCues = (EbmlMaster)MKVType.createByType(MKVType.Cues);
        this.mkvSeekHead = this.muxSeekHead();
        this.muxCues();
        segmentElem.add(this.mkvSeekHead);
        segmentElem.add(this.mkvInfo);
        segmentElem.add(this.mkvTracks);
        segmentElem.add(this.mkvCues);
        for (EbmlMaster aCluster : this.clusterList) {
            segmentElem.add(aCluster);
        }
        mkvFile.add(segmentElem);
        for (EbmlMaster el : mkvFile) {
            el.mux(this.sink);
        }
    }

    private EbmlMaster defaultEbmlHeader() {
        EbmlMaster master = (EbmlMaster)MKVType.createByType(MKVType.EBML);
        MKVMuxer.createLong(master, MKVType.EBMLVersion, 1L);
        MKVMuxer.createLong(master, MKVType.EBMLReadVersion, 1L);
        MKVMuxer.createLong(master, MKVType.EBMLMaxIDLength, 4L);
        MKVMuxer.createLong(master, MKVType.EBMLMaxSizeLength, 8L);
        MKVMuxer.createString(master, MKVType.DocType, "webm");
        MKVMuxer.createLong(master, MKVType.DocTypeVersion, 2L);
        MKVMuxer.createLong(master, MKVType.DocTypeReadVersion, 2L);
        return master;
    }

    private EbmlMaster muxInfo() {
        EbmlMaster master = (EbmlMaster)MKVType.createByType(MKVType.Info);
        int frameDurationInNanoseconds = 40000000;
        MKVMuxer.createLong(master, MKVType.TimecodeScale, frameDurationInNanoseconds);
        MKVMuxer.createString(master, MKVType.WritingApp, "JCodec");
        MKVMuxer.createString(master, MKVType.MuxingApp, "JCodec");
        List<MKVMuxerTrack> tracks2 = this.tracks;
        long max = 0L;
        for (MKVMuxerTrack track : tracks2) {
            MkvBlock lastBlock = track.trackBlocks.get(track.trackBlocks.size() - 1);
            if (lastBlock.absoluteTimecode <= max) continue;
            max = lastBlock.absoluteTimecode;
        }
        MKVMuxer.createDouble(master, MKVType.Duration, (double)((max + 1L) * (long)frameDurationInNanoseconds) * 1.0);
        MKVMuxer.createDate(master, MKVType.DateUTC, new Date());
        return master;
    }

    private EbmlMaster muxTracks() {
        EbmlMaster master = (EbmlMaster)MKVType.createByType(MKVType.Tracks);
        for (int i = 0; i < this.tracks.size(); ++i) {
            MKVMuxerTrack track = this.tracks.get(i);
            EbmlMaster trackEntryElem = (EbmlMaster)MKVType.createByType(MKVType.TrackEntry);
            MKVMuxer.createLong(trackEntryElem, MKVType.TrackNumber, track.trackNo);
            MKVMuxer.createLong(trackEntryElem, MKVType.TrackUID, track.trackNo);
            if (MKVMuxerTrack.MKVMuxerTrackType.VIDEO.equals((Object)track.type)) {
                MKVMuxer.createLong(trackEntryElem, MKVType.TrackType, 1L);
                MKVMuxer.createString(trackEntryElem, MKVType.Name, "Track " + (i + 1) + " Video");
                MKVMuxer.createString(trackEntryElem, MKVType.CodecID, track.codecId);
                EbmlMaster trackVideoElem = (EbmlMaster)MKVType.createByType(MKVType.Video);
                MKVMuxer.createLong(trackVideoElem, MKVType.PixelWidth, track.videoMeta.getSize().getWidth());
                MKVMuxer.createLong(trackVideoElem, MKVType.PixelHeight, track.videoMeta.getSize().getHeight());
                trackEntryElem.add(trackVideoElem);
            } else {
                MKVMuxer.createLong(trackEntryElem, MKVType.TrackType, 2L);
                MKVMuxer.createString(trackEntryElem, MKVType.Name, "Track " + (i + 1) + " Audio");
                MKVMuxer.createString(trackEntryElem, MKVType.CodecID, track.codecId);
            }
            master.add(trackEntryElem);
        }
        return master;
    }

    private void muxCues() {
        CuesFactory cf = new CuesFactory(this.mkvSeekHead.size() + this.mkvInfo.size() + this.mkvTracks.size(), this.videoTrack.trackNo);
        for (MkvBlock aBlock : this.videoTrack.trackBlocks) {
            EbmlMaster mkvCluster = this.singleBlockedCluster(aBlock);
            this.clusterList.add(mkvCluster);
            cf.add(CuesFactory.CuePointMock.make(mkvCluster));
        }
        EbmlMaster indexedCues = cf.createCues();
        for (EbmlBase aCuePoint : indexedCues.children) {
            this.mkvCues.add(aCuePoint);
        }
    }

    private EbmlMaster singleBlockedCluster(MkvBlock aBlock) {
        EbmlMaster mkvCluster = (EbmlMaster)MKVType.createByType(MKVType.Cluster);
        MKVMuxer.createLong(mkvCluster, MKVType.Timecode, aBlock.absoluteTimecode - (long)aBlock.timecode);
        mkvCluster.add(aBlock);
        return mkvCluster;
    }

    private EbmlMaster muxSeekHead() {
        SeekHeadFactory shi = new SeekHeadFactory();
        shi.add(this.mkvInfo);
        shi.add(this.mkvTracks);
        shi.add(this.mkvCues);
        return shi.indexSeekHead();
    }

    public static void createLong(EbmlMaster parent, MKVType type, long value) {
        EbmlUint se = (EbmlUint)MKVType.createByType(type);
        se.setUint(value);
        parent.add(se);
    }

    public static void createString(EbmlMaster parent, MKVType type, String value) {
        EbmlString se = (EbmlString)MKVType.createByType(type);
        se.setString(value);
        parent.add(se);
    }

    public static void createDate(EbmlMaster parent, MKVType type, Date value) {
        EbmlDate se = (EbmlDate)MKVType.createByType(type);
        se.setDate(value);
        parent.add(se);
    }

    public static void createBuffer(EbmlMaster parent, MKVType type, ByteBuffer value) {
        EbmlBin se = (EbmlBin)MKVType.createByType(type);
        se.setBuf(value);
        parent.add(se);
    }

    public static void createDouble(EbmlMaster parent, MKVType type, double value) {
        try {
            EbmlFloat se = (EbmlFloat)MKVType.createByType(type);
            se.setDouble(value);
            parent.add(se);
        }
        catch (ClassCastException cce) {
            throw new RuntimeException("Element of type " + type + " can't be cast to EbmlFloat", cce);
        }
    }

    @Override
    public MuxerTrack addVideoTrack(Codec codec, VideoCodecMeta meta) {
        return this.createVideoTrack(meta, codec2mkv.get(codec));
    }

    @Override
    public MuxerTrack addAudioTrack(Codec codec, AudioCodecMeta meta) {
        this.audioTrack = new MKVMuxerTrack();
        this.tracks.add(this.audioTrack);
        this.audioTrack.codecId = codec2mkv.get(codec);
        this.audioTrack.trackNo = this.tracks.size();
        return this.audioTrack;
    }

    static {
        codec2mkv.put(Codec.H264, "V_MPEG4/ISO/AVC");
        codec2mkv.put(Codec.VP8, "V_VP8");
        codec2mkv.put(Codec.VP9, "V_VP9");
    }
}
