/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Decoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.DeblockerInput;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.SliceDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.SliceReader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.deblock.DeblockingFilter;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.Frame;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnit;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnitType;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.RefPicMarking;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.RefPicMarkingIDR;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SeqParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceHeader;
import com.github.alexthe666.citadel.repack.jcodec.common.IntObjectMap;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

static class H264Decoder.FrameDecoder {
    private SeqParameterSet activeSps;
    private DeblockingFilter filter;
    private SliceHeader firstSliceHeader;
    private NALUnit firstNu;
    private H264Decoder dec;
    private DeblockerInput di;

    public H264Decoder.FrameDecoder(H264Decoder decoder) {
        this.dec = decoder;
    }

    public Frame decodeFrame(List<ByteBuffer> nalUnits, byte[][] buffer) {
        List<SliceReader> sliceReaders = this.dec.reader.readFrame(nalUnits);
        if (sliceReaders == null || sliceReaders.size() == 0) {
            return null;
        }
        Frame result = this.init(sliceReaders.get(0), buffer);
        if (this.dec.threaded && sliceReaders.size() > 1) {
            ArrayList futures = new ArrayList();
            for (SliceReader sliceReader : sliceReaders) {
                futures.add(this.dec.tp.submit(new H264Decoder.SliceDecoderRunnable(this, sliceReader, result, null)));
            }
            for (Future future : futures) {
                this.waitForSure(future);
            }
        } else {
            for (SliceReader sliceReader : sliceReaders) {
                new SliceDecoder(this.activeSps, this.dec.sRefs, this.dec.lRefs, this.di, result).decodeFromReader(sliceReader);
            }
        }
        this.filter.deblockFrame(result);
        this.updateReferences(result);
        return result;
    }

    private void waitForSure(Future<?> future) {
        try {
            future.get();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateReferences(Frame picture) {
        if (this.firstNu.nal_ref_idc != 0) {
            if (this.firstNu.type == NALUnitType.IDR_SLICE) {
                this.performIDRMarking(this.firstSliceHeader.refPicMarkingIDR, picture);
            } else {
                this.performMarking(this.firstSliceHeader.refPicMarkingNonIDR, picture);
            }
        }
    }

    private Frame init(SliceReader sliceReader, byte[][] buffer) {
        this.firstNu = sliceReader.getNALUnit();
        this.firstSliceHeader = sliceReader.getSliceHeader();
        this.activeSps = this.firstSliceHeader.sps;
        this.validateSupportedFeatures(this.firstSliceHeader.sps, this.firstSliceHeader.pps);
        int picWidthInMbs = this.activeSps.picWidthInMbsMinus1 + 1;
        if (this.dec.sRefs == null) {
            H264Decoder.access$202(this.dec, new Frame[1 << this.firstSliceHeader.sps.log2MaxFrameNumMinus4 + 4]);
            this.dec.lRefs = new IntObjectMap();
        }
        this.di = new DeblockerInput(this.activeSps);
        Frame result = H264Decoder.createFrame(this.activeSps, buffer, this.firstSliceHeader.frameNum, this.firstSliceHeader.sliceType, this.di.mvs, this.di.refsUsed, this.dec.poc.calcPOC(this.firstSliceHeader, this.firstNu));
        this.filter = new DeblockingFilter(picWidthInMbs, this.activeSps.bitDepthChromaMinus8 + 8, this.di);
        return result;
    }

    private void validateSupportedFeatures(SeqParameterSet sps, PictureParameterSet pps) {
        if (sps.mbAdaptiveFrameFieldFlag) {
            throw new RuntimeException("Unsupported h264 feature: MBAFF.");
        }
        if (sps.bitDepthLumaMinus8 != 0 || sps.bitDepthChromaMinus8 != 0) {
            throw new RuntimeException("Unsupported h264 feature: High bit depth.");
        }
        if (sps.chromaFormatIdc != ColorSpace.YUV420J) {
            throw new RuntimeException("Unsupported h264 feature: " + sps.chromaFormatIdc + " color.");
        }
        if (!sps.frameMbsOnlyFlag || sps.fieldPicFlag) {
            throw new RuntimeException("Unsupported h264 feature: interlace.");
        }
        if (pps.constrainedIntraPredFlag) {
            throw new RuntimeException("Unsupported h264 feature: constrained intra prediction.");
        }
        if (sps.qpprimeYZeroTransformBypassFlag) {
            throw new RuntimeException("Unsupported h264 feature: qprime zero transform bypass.");
        }
        if (sps.profileIdc != 66 && sps.profileIdc != 77 && sps.profileIdc != 100) {
            throw new RuntimeException("Unsupported h264 feature: " + sps.profileIdc + " profile.");
        }
    }

    public void performIDRMarking(RefPicMarkingIDR refPicMarkingIDR, Frame picture) {
        this.clearAll();
        this.dec.pictureBuffer.clear();
        Frame saved = this.saveRef(picture);
        if (refPicMarkingIDR.isUseForlongTerm()) {
            this.dec.lRefs.put(0, saved);
            saved.setShortTerm(false);
        } else {
            ((H264Decoder)this.dec).sRefs[this.firstSliceHeader.frameNum] = saved;
        }
    }

    private Frame saveRef(Frame decoded) {
        Frame frame = this.dec.pictureBuffer.size() > 0 ? (Frame)this.dec.pictureBuffer.remove(0) : Frame.createFrame(decoded);
        frame.copyFromFrame(decoded);
        return frame;
    }

    private void releaseRef(Frame picture) {
        if (picture != null) {
            this.dec.pictureBuffer.add(picture);
        }
    }

    public void clearAll() {
        for (int i = 0; i < this.dec.sRefs.length; ++i) {
            this.releaseRef(this.dec.sRefs[i]);
            ((H264Decoder)this.dec).sRefs[i] = null;
        }
        int[] keys = this.dec.lRefs.keys();
        for (int i = 0; i < keys.length; ++i) {
            this.releaseRef((Frame)this.dec.lRefs.get(keys[i]));
        }
        this.dec.lRefs.clear();
    }

    public void performMarking(RefPicMarking refPicMarking, Frame picture) {
        Frame saved = this.saveRef(picture);
        if (refPicMarking != null) {
            RefPicMarking.Instruction[] instructions = refPicMarking.getInstructions();
            block8: for (int i = 0; i < instructions.length; ++i) {
                RefPicMarking.Instruction instr = instructions[i];
                switch (instr.getType()) {
                    case REMOVE_SHORT: {
                        this.unrefShortTerm(instr.getArg1());
                        continue block8;
                    }
                    case REMOVE_LONG: {
                        this.unrefLongTerm(instr.getArg1());
                        continue block8;
                    }
                    case CONVERT_INTO_LONG: {
                        this.convert(instr.getArg1(), instr.getArg2());
                        continue block8;
                    }
                    case TRUNK_LONG: {
                        this.truncateLongTerm(instr.getArg1() - 1);
                        continue block8;
                    }
                    case CLEAR: {
                        this.clearAll();
                        continue block8;
                    }
                    case MARK_LONG: {
                        this.saveLong(saved, instr.getArg1());
                        saved = null;
                    }
                }
            }
        }
        if (saved != null) {
            this.saveShort(saved);
        }
        int maxFrames = 1 << this.activeSps.log2MaxFrameNumMinus4 + 4;
        if (refPicMarking == null) {
            int maxShort = Math.max(1, this.activeSps.numRefFrames - this.dec.lRefs.size());
            int min = Integer.MAX_VALUE;
            int num = 0;
            int minFn = 0;
            for (int i = 0; i < this.dec.sRefs.length; ++i) {
                if (this.dec.sRefs[i] == null) continue;
                int fnWrap = this.unwrap(this.firstSliceHeader.frameNum, this.dec.sRefs[i].getFrameNo(), maxFrames);
                if (fnWrap < min) {
                    min = fnWrap;
                    minFn = this.dec.sRefs[i].getFrameNo();
                }
                ++num;
            }
            if (num > maxShort) {
                this.releaseRef(this.dec.sRefs[minFn]);
                ((H264Decoder)this.dec).sRefs[minFn] = null;
            }
        }
    }

    private int unwrap(int thisFrameNo, int refFrameNo, int maxFrames) {
        return refFrameNo > thisFrameNo ? refFrameNo - maxFrames : refFrameNo;
    }

    private void saveShort(Frame saved) {
        ((H264Decoder)this.dec).sRefs[this.firstSliceHeader.frameNum] = saved;
    }

    private void saveLong(Frame saved, int longNo) {
        Frame prev = (Frame)this.dec.lRefs.get(longNo);
        if (prev != null) {
            this.releaseRef(prev);
        }
        saved.setShortTerm(false);
        this.dec.lRefs.put(longNo, saved);
    }

    private void truncateLongTerm(int maxLongNo) {
        int[] keys = this.dec.lRefs.keys();
        for (int i = 0; i < keys.length; ++i) {
            if (keys[i] <= maxLongNo) continue;
            this.releaseRef((Frame)this.dec.lRefs.get(keys[i]));
            this.dec.lRefs.remove(keys[i]);
        }
    }

    private void convert(int shortNo, int longNo) {
        int ind = MathUtil.wrap(this.firstSliceHeader.frameNum - shortNo, 1 << this.firstSliceHeader.sps.log2MaxFrameNumMinus4 + 4);
        this.releaseRef((Frame)this.dec.lRefs.get(longNo));
        this.dec.lRefs.put(longNo, this.dec.sRefs[ind]);
        ((H264Decoder)this.dec).sRefs[ind] = null;
        ((Frame)this.dec.lRefs.get(longNo)).setShortTerm(false);
    }

    private void unrefLongTerm(int longNo) {
        this.releaseRef((Frame)this.dec.lRefs.get(longNo));
        this.dec.lRefs.remove(longNo);
    }

    private void unrefShortTerm(int shortNo) {
        int ind = MathUtil.wrap(this.firstSliceHeader.frameNum - shortNo, 1 << this.firstSliceHeader.sps.log2MaxFrameNumMinus4 + 4);
        this.releaseRef(this.dec.sRefs[ind]);
        ((H264Decoder)this.dec).sRefs[ind] = null;
    }

    static /* synthetic */ SeqParameterSet access$000(H264Decoder.FrameDecoder x0) {
        return x0.activeSps;
    }

    static /* synthetic */ H264Decoder access$100(H264Decoder.FrameDecoder x0) {
        return x0.dec;
    }

    static /* synthetic */ DeblockerInput access$400(H264Decoder.FrameDecoder x0) {
        return x0.di;
    }
}
