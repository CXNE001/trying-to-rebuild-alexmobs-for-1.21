/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Const;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.Frame;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PredictionWeightTable;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceHeader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceType;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;

public class PredictionMerger {
    public static void mergePrediction(SliceHeader sh, int refIdxL0, int refIdxL1, H264Const.PartPred predType, int comp, byte[] pred0, byte[] pred1, int off, int stride, int blkW, int blkH, byte[] out, Frame[][] refs, int thisPoc) {
        PictureParameterSet pps = sh.pps;
        if (sh.sliceType == SliceType.P) {
            PredictionMerger.weightPrediction(sh, refIdxL0, comp, pred0, off, stride, blkW, blkH, out);
        } else if (!pps.weightedPredFlag || sh.pps.weightedBipredIdc == 0 || sh.pps.weightedBipredIdc == 2 && predType != H264Const.PartPred.Bi) {
            PredictionMerger.mergeAvg(pred0, pred1, stride, predType, off, blkW, blkH, out);
        } else if (sh.pps.weightedBipredIdc == 1) {
            int o0;
            int w1;
            int w0;
            PredictionWeightTable w = sh.predWeightTable;
            int n = refIdxL0 == -1 ? 0 : (w0 = comp == 0 ? w.lumaWeight[0][refIdxL0] : w.chromaWeight[0][comp - 1][refIdxL0]);
            int n2 = refIdxL1 == -1 ? 0 : (w1 = comp == 0 ? w.lumaWeight[1][refIdxL1] : w.chromaWeight[1][comp - 1][refIdxL1]);
            int n3 = refIdxL0 == -1 ? 0 : (o0 = comp == 0 ? w.lumaOffset[0][refIdxL0] : w.chromaOffset[0][comp - 1][refIdxL0]);
            int o1 = refIdxL1 == -1 ? 0 : (comp == 0 ? w.lumaOffset[1][refIdxL1] : w.chromaOffset[1][comp - 1][refIdxL1]);
            PredictionMerger.mergeWeight(pred0, pred1, stride, predType, off, blkW, blkH, comp == 0 ? w.lumaLog2WeightDenom : w.chromaLog2WeightDenom, w0, w1, o0, o1, out);
        } else {
            int tx;
            int dsf;
            int tb = MathUtil.clip(thisPoc - refs[0][refIdxL0].getPOC(), -128, 127);
            int td = MathUtil.clip(refs[1][refIdxL1].getPOC() - refs[0][refIdxL0].getPOC(), -128, 127);
            int w0 = 32;
            int w1 = 32;
            if (td != 0 && refs[0][refIdxL0].isShortTerm() && refs[1][refIdxL1].isShortTerm() && (dsf = MathUtil.clip(tb * (tx = (16384 + Math.abs(td / 2)) / td) + 32 >> 6, -1024, 1023) >> 2) >= -64 && dsf <= 128) {
                w1 = dsf;
                w0 = 64 - dsf;
            }
            PredictionMerger.mergeWeight(pred0, pred1, stride, predType, off, blkW, blkH, 5, w0, w1, 0, 0, out);
        }
    }

    public static void weightPrediction(SliceHeader sh, int refIdxL0, int comp, byte[] pred0, int off, int stride, int blkW, int blkH, byte[] out) {
        PictureParameterSet pps = sh.pps;
        if (pps.weightedPredFlag && sh.predWeightTable != null) {
            PredictionWeightTable w = sh.predWeightTable;
            PredictionMerger.weight(pred0, stride, off, blkW, blkH, comp == 0 ? w.lumaLog2WeightDenom : w.chromaLog2WeightDenom, comp == 0 ? w.lumaWeight[0][refIdxL0] : w.chromaWeight[0][comp - 1][refIdxL0], comp == 0 ? w.lumaOffset[0][refIdxL0] : w.chromaOffset[0][comp - 1][refIdxL0], out);
        } else {
            PredictionMerger.copyPrediction(pred0, stride, off, blkW, blkH, out);
        }
    }

    private static void mergeAvg(byte[] blk0, byte[] blk1, int stride, H264Const.PartPred p0, int off, int blkW, int blkH, byte[] out) {
        if (p0 == H264Const.PartPred.Bi) {
            PredictionMerger._mergePrediction(blk0, blk1, stride, p0, off, blkW, blkH, out);
        } else if (p0 == H264Const.PartPred.L0) {
            PredictionMerger.copyPrediction(blk0, stride, off, blkW, blkH, out);
        } else if (p0 == H264Const.PartPred.L1) {
            PredictionMerger.copyPrediction(blk1, stride, off, blkW, blkH, out);
        }
    }

    private static void mergeWeight(byte[] blk0, byte[] blk1, int stride, H264Const.PartPred partPred, int off, int blkW, int blkH, int logWD, int w0, int w1, int o0, int o1, byte[] out) {
        if (partPred == H264Const.PartPred.L0) {
            PredictionMerger.weight(blk0, stride, off, blkW, blkH, logWD, w0, o0, out);
        } else if (partPred == H264Const.PartPred.L1) {
            PredictionMerger.weight(blk1, stride, off, blkW, blkH, logWD, w1, o1, out);
        } else if (partPred == H264Const.PartPred.Bi) {
            PredictionMerger._weightPrediction(blk0, blk1, stride, off, blkW, blkH, logWD, w0, w1, o0, o1, out);
        }
    }

    private static void copyPrediction(byte[] _in, int stride, int off, int blkW, int blkH, byte[] out) {
        int i = 0;
        while (i < blkH) {
            int j = 0;
            while (j < blkW) {
                out[off] = _in[off];
                ++j;
                ++off;
            }
            ++i;
            off += stride - blkW;
        }
    }

    private static void _mergePrediction(byte[] blk0, byte[] blk1, int stride, H264Const.PartPred p0, int off, int blkW, int blkH, byte[] out) {
        int i = 0;
        while (i < blkH) {
            int j = 0;
            while (j < blkW) {
                out[off] = (byte)(blk0[off] + blk1[off] + 1 >> 1);
                ++j;
                ++off;
            }
            ++i;
            off += stride - blkW;
        }
    }

    private static void _weightPrediction(byte[] blk0, byte[] blk1, int stride, int off, int blkW, int blkH, int logWD, int w0, int w1, int o0, int o1, byte[] out) {
        int round = (1 << logWD) + (w0 + w1 << 7);
        int sum = (o0 + o1 + 1 >> 1) - 128;
        int logWDCP1 = logWD + 1;
        int i = 0;
        while (i < blkH) {
            int j = 0;
            while (j < blkW) {
                out[off] = (byte)MathUtil.clip((blk0[off] * w0 + blk1[off] * w1 + round >> logWDCP1) + sum, -128, 127);
                ++j;
                ++off;
            }
            ++i;
            off += stride - blkW;
        }
    }

    private static void weight(byte[] blk0, int stride, int off, int blkW, int blkH, int logWD, int w, int o, byte[] out) {
        int round = 1 << logWD - 1;
        if (logWD >= 1) {
            o -= 128;
            round += w << 7;
            int i = 0;
            while (i < blkH) {
                int j = 0;
                while (j < blkW) {
                    out[off] = (byte)MathUtil.clip((blk0[off] * w + round >> logWD) + o, -128, 127);
                    ++j;
                    ++off;
                }
                ++i;
                off += stride - blkW;
            }
        } else {
            o += (w << 7) - 128;
            int i = 0;
            while (i < blkH) {
                int j = 0;
                while (j < blkW) {
                    out[off] = (byte)MathUtil.clip(blk0[off] * w + o, -128, 127);
                    ++j;
                    ++off;
                }
                ++i;
                off += stride - blkW;
            }
        }
    }
}
