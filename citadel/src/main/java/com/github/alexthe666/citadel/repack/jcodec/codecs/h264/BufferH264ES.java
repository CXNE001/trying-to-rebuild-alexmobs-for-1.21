/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Utils;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.SliceHeaderReader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnit;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnitType;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.RefPicMarking;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SeqParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceHeader;
import com.github.alexthe666.citadel.repack.jcodec.common.Demuxer;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrack;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.IntObjectMap;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BufferH264ES
implements DemuxerTrack,
Demuxer {
    private ByteBuffer bb;
    private IntObjectMap<PictureParameterSet> pps = new IntObjectMap();
    private IntObjectMap<SeqParameterSet> sps = new IntObjectMap();
    private int prevFrameNumOffset;
    private int prevFrameNum;
    private int prevPicOrderCntMsb;
    private int prevPicOrderCntLsb;
    private int frameNo;

    public BufferH264ES(ByteBuffer bb) {
        this.bb = bb;
        this.frameNo = 0;
    }

    @Override
    public Packet nextFrame() {
        ByteBuffer result = this.bb.duplicate();
        NALUnit prevNu = null;
        SliceHeader prevSh = null;
        while (true) {
            Object read;
            this.bb.mark();
            ByteBuffer buf = H264Utils.nextNALUnit(this.bb);
            if (buf == null) break;
            NALUnit nu = NALUnit.read(buf);
            if (nu.type == NALUnitType.IDR_SLICE || nu.type == NALUnitType.NON_IDR_SLICE) {
                SliceHeader sh = this.readSliceHeader(buf, nu);
                if (prevNu != null && prevSh != null && !this.sameFrame(prevNu, nu, prevSh, sh)) {
                    this.bb.reset();
                    break;
                }
                prevSh = sh;
                prevNu = nu;
                continue;
            }
            if (nu.type == NALUnitType.PPS) {
                read = PictureParameterSet.read(buf);
                this.pps.put(((PictureParameterSet)read).picParameterSetId, (PictureParameterSet)read);
                continue;
            }
            if (nu.type != NALUnitType.SPS) continue;
            read = SeqParameterSet.read(buf);
            this.sps.put(((SeqParameterSet)read).seqParameterSetId, (SeqParameterSet)read);
        }
        result.limit(this.bb.position());
        return prevSh == null ? null : this.detectPoc(result, prevNu, prevSh);
    }

    private SliceHeader readSliceHeader(ByteBuffer buf, NALUnit nu) {
        BitReader br = BitReader.createBitReader(buf);
        SliceHeader sh = SliceHeaderReader.readPart1(br);
        PictureParameterSet pp = this.pps.get(sh.picParameterSetId);
        SliceHeaderReader.readPart2(sh, nu, this.sps.get(pp.seqParameterSetId), pp, br);
        return sh;
    }

    private boolean sameFrame(NALUnit nu1, NALUnit nu2, SliceHeader sh1, SliceHeader sh2) {
        if (sh1.picParameterSetId != sh2.picParameterSetId) {
            return false;
        }
        if (sh1.frameNum != sh2.frameNum) {
            return false;
        }
        SeqParameterSet sps = sh1.sps;
        if (sps.picOrderCntType == 0 && sh1.picOrderCntLsb != sh2.picOrderCntLsb) {
            return false;
        }
        if (sps.picOrderCntType == 1 && (sh1.deltaPicOrderCnt[0] != sh2.deltaPicOrderCnt[0] || sh1.deltaPicOrderCnt[1] != sh2.deltaPicOrderCnt[1])) {
            return false;
        }
        if ((nu1.nal_ref_idc == 0 || nu2.nal_ref_idc == 0) && nu1.nal_ref_idc != nu2.nal_ref_idc) {
            return false;
        }
        if (nu1.type == NALUnitType.IDR_SLICE != (nu2.type == NALUnitType.IDR_SLICE)) {
            return false;
        }
        return sh1.idrPicId == sh2.idrPicId;
    }

    private Packet detectPoc(ByteBuffer result, NALUnit nu, SliceHeader sh) {
        int maxFrameNum = 1 << sh.sps.log2MaxFrameNumMinus4 + 4;
        if (this.detectGap(sh, maxFrameNum)) {
            this.issueNonExistingPic(sh, maxFrameNum);
        }
        int absFrameNum = this.updateFrameNumber(sh.frameNum, maxFrameNum, this.detectMMCO5(sh.refPicMarkingNonIDR));
        int poc = 0;
        if (nu.type == NALUnitType.NON_IDR_SLICE) {
            poc = this.calcPoc(absFrameNum, nu, sh);
        }
        return new Packet(result, absFrameNum, 1, 1L, this.frameNo++, nu.type == NALUnitType.IDR_SLICE ? Packet.FrameType.KEY : Packet.FrameType.INTER, null, poc);
    }

    private int updateFrameNumber(int frameNo, int maxFrameNum, boolean mmco5) {
        int frameNumOffset = this.prevFrameNum > frameNo ? this.prevFrameNumOffset + maxFrameNum : this.prevFrameNumOffset;
        int absFrameNum = frameNumOffset + frameNo;
        this.prevFrameNum = mmco5 ? 0 : frameNo;
        this.prevFrameNumOffset = frameNumOffset;
        return absFrameNum;
    }

    private void issueNonExistingPic(SliceHeader sh, int maxFrameNum) {
        int nextFrameNum;
        this.prevFrameNum = nextFrameNum = (this.prevFrameNum + 1) % maxFrameNum;
    }

    private boolean detectGap(SliceHeader sh, int maxFrameNum) {
        return sh.frameNum != this.prevFrameNum && sh.frameNum != (this.prevFrameNum + 1) % maxFrameNum;
    }

    private int calcPoc(int absFrameNum, NALUnit nu, SliceHeader sh) {
        if (sh.sps.picOrderCntType == 0) {
            return this.calcPOC0(nu, sh);
        }
        if (sh.sps.picOrderCntType == 1) {
            return this.calcPOC1(absFrameNum, nu, sh);
        }
        return this.calcPOC2(absFrameNum, nu, sh);
    }

    private int calcPOC2(int absFrameNum, NALUnit nu, SliceHeader sh) {
        if (nu.nal_ref_idc == 0) {
            return 2 * absFrameNum - 1;
        }
        return 2 * absFrameNum;
    }

    private int calcPOC1(int absFrameNum, NALUnit nu, SliceHeader sh) {
        int expectedPicOrderCnt;
        if (sh.sps.numRefFramesInPicOrderCntCycle == 0) {
            absFrameNum = 0;
        }
        if (nu.nal_ref_idc == 0 && absFrameNum > 0) {
            --absFrameNum;
        }
        int expectedDeltaPerPicOrderCntCycle = 0;
        for (int i = 0; i < sh.sps.numRefFramesInPicOrderCntCycle; ++i) {
            expectedDeltaPerPicOrderCntCycle += sh.sps.offsetForRefFrame[i];
        }
        if (absFrameNum > 0) {
            int picOrderCntCycleCnt = (absFrameNum - 1) / sh.sps.numRefFramesInPicOrderCntCycle;
            int frameNumInPicOrderCntCycle = (absFrameNum - 1) % sh.sps.numRefFramesInPicOrderCntCycle;
            expectedPicOrderCnt = picOrderCntCycleCnt * expectedDeltaPerPicOrderCntCycle;
            for (int i = 0; i <= frameNumInPicOrderCntCycle; ++i) {
                expectedPicOrderCnt += sh.sps.offsetForRefFrame[i];
            }
        } else {
            expectedPicOrderCnt = 0;
        }
        if (nu.nal_ref_idc == 0) {
            expectedPicOrderCnt += sh.sps.offsetForNonRefPic;
        }
        return expectedPicOrderCnt + sh.deltaPicOrderCnt[0];
    }

    private int calcPOC0(NALUnit nu, SliceHeader sh) {
        int pocCntLsb = sh.picOrderCntLsb;
        int maxPicOrderCntLsb = 1 << sh.sps.log2MaxPicOrderCntLsbMinus4 + 4;
        int picOrderCntMsb = pocCntLsb < this.prevPicOrderCntLsb && this.prevPicOrderCntLsb - pocCntLsb >= maxPicOrderCntLsb / 2 ? this.prevPicOrderCntMsb + maxPicOrderCntLsb : (pocCntLsb > this.prevPicOrderCntLsb && pocCntLsb - this.prevPicOrderCntLsb > maxPicOrderCntLsb / 2 ? this.prevPicOrderCntMsb - maxPicOrderCntLsb : this.prevPicOrderCntMsb);
        if (nu.nal_ref_idc != 0) {
            this.prevPicOrderCntMsb = picOrderCntMsb;
            this.prevPicOrderCntLsb = pocCntLsb;
        }
        return picOrderCntMsb + pocCntLsb;
    }

    private boolean detectMMCO5(RefPicMarking refPicMarkingNonIDR) {
        if (refPicMarkingNonIDR == null) {
            return false;
        }
        RefPicMarking.Instruction[] instructions = refPicMarkingNonIDR.getInstructions();
        for (int i = 0; i < instructions.length; ++i) {
            RefPicMarking.Instruction instr = instructions[i];
            if (instr.getType() != RefPicMarking.InstrType.CLEAR) continue;
            return true;
        }
        return false;
    }

    public SeqParameterSet[] getSps() {
        return this.sps.values((SeqParameterSet[])new SeqParameterSet[0]);
    }

    public PictureParameterSet[] getPps() {
        return this.pps.values((PictureParameterSet[])new PictureParameterSet[0]);
    }

    @Override
    public DemuxerTrackMeta getMeta() {
        return null;
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public List<? extends DemuxerTrack> getTracks() {
        return this.getVideoTracks();
    }

    @Override
    public List<? extends DemuxerTrack> getVideoTracks() {
        ArrayList<BufferH264ES> tracks = new ArrayList<BufferH264ES>();
        tracks.add(this);
        return tracks;
    }

    @Override
    public List<? extends DemuxerTrack> getAudioTracks() {
        ArrayList tracks = new ArrayList();
        return tracks;
    }
}
