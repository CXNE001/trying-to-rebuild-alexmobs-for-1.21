/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx;

import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.FilterUtil;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VP8Util;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VPXBooleanDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VPXMacroblock;
import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.UsedViaReflection;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;

public class VP8Decoder
extends VideoDecoder {
    private byte[][] segmentationMap;
    private int[] refLoopFilterDeltas = new int[4];
    private int[] modeLoopFilterDeltas = new int[4];

    @Override
    public Picture decodeFrame(ByteBuffer frame, byte[][] buffer) {
        int mbCol;
        int mbRow;
        int log2OfPartCnt;
        int loopFilterDeltaUpdate;
        boolean keyFrame;
        byte[] firstThree = new byte[3];
        frame.get(firstThree);
        boolean bl = keyFrame = VP8Util.getBitInBytes(firstThree, 0) == 0;
        if (!keyFrame) {
            return null;
        }
        int version = VP8Util.getBitsInBytes(firstThree, 1, 3);
        boolean showFrame = VP8Util.getBitInBytes(firstThree, 4) > 0;
        int partitionSize = VP8Util.getBitsInBytes(firstThree, 5, 19);
        String threeByteToken = VP8Decoder.printHexByte(frame.get()) + " " + VP8Decoder.printHexByte(frame.get()) + " " + VP8Decoder.printHexByte(frame.get());
        int twoBytesWidth = frame.get() & 0xFF | (frame.get() & 0xFF) << 8;
        int twoBytesHeight = frame.get() & 0xFF | (frame.get() & 0xFF) << 8;
        int width = twoBytesWidth & 0x3FFF;
        int height = twoBytesHeight & 0x3FFF;
        int numberOfMBRows = VP8Util.getMacroblockCount(height);
        int numberOfMBCols = VP8Util.getMacroblockCount(width);
        if (this.segmentationMap == null) {
            this.segmentationMap = new byte[numberOfMBRows][numberOfMBCols];
        }
        VPXMacroblock[][] mbs = new VPXMacroblock[numberOfMBRows + 2][numberOfMBCols + 2];
        for (int row = 0; row < numberOfMBRows + 2; ++row) {
            for (int col = 0; col < numberOfMBCols + 2; ++col) {
                mbs[row][col] = new VPXMacroblock(row, col);
            }
        }
        int headerOffset = frame.position();
        VPXBooleanDecoder headerDecoder = new VPXBooleanDecoder(frame, 0);
        boolean isYUVColorSpace = headerDecoder.readBitEq() == 0;
        boolean clampingRequired = headerDecoder.readBitEq() == 0;
        int segmentation = headerDecoder.readBitEq();
        SegmentBasedAdjustments segmentBased = null;
        if (segmentation != 0) {
            segmentBased = this.updateSegmentation(headerDecoder);
            for (int row = 0; row < numberOfMBRows; ++row) {
                for (int col = 0; col < numberOfMBCols; ++col) {
                    mbs[row + 1][col + 1].segment = this.segmentationMap[row][col];
                }
            }
        }
        int simpleFilter = headerDecoder.readBitEq();
        int filterLevel = headerDecoder.decodeInt(6);
        int filterType = filterLevel == 0 ? 0 : (simpleFilter > 0 ? 1 : 2);
        int sharpnessLevel = headerDecoder.decodeInt(3);
        int loopFilterDeltaFlag = headerDecoder.readBitEq();
        if (loopFilterDeltaFlag == 1 && (loopFilterDeltaUpdate = headerDecoder.readBitEq()) == 1) {
            int i;
            for (i = 0; i < 4; ++i) {
                if (headerDecoder.readBitEq() <= 0) continue;
                this.refLoopFilterDeltas[i] = headerDecoder.decodeInt(6);
                if (headerDecoder.readBitEq() <= 0) continue;
                this.refLoopFilterDeltas[i] = this.refLoopFilterDeltas[i] * -1;
            }
            for (i = 0; i < 4; ++i) {
                if (headerDecoder.readBitEq() <= 0) continue;
                this.modeLoopFilterDeltas[i] = headerDecoder.decodeInt(6);
                if (headerDecoder.readBitEq() <= 0) continue;
                this.modeLoopFilterDeltas[i] = this.modeLoopFilterDeltas[i] * -1;
            }
        }
        Preconditions.checkState(0 == (log2OfPartCnt = headerDecoder.decodeInt(2)));
        boolean partitionsCount = true;
        long runningSize = 0L;
        long zSize = frame.limit() - (partitionSize + headerOffset);
        ByteBuffer tokenBuffer = frame.duplicate();
        tokenBuffer.position(partitionSize + headerOffset);
        VPXBooleanDecoder decoder = new VPXBooleanDecoder(tokenBuffer, 0);
        int yacIndex = headerDecoder.decodeInt(7);
        int ydcDelta = headerDecoder.readBitEq() > 0 ? VP8Util.delta(headerDecoder) : 0;
        int y2dcDelta = headerDecoder.readBitEq() > 0 ? VP8Util.delta(headerDecoder) : 0;
        int y2acDelta = headerDecoder.readBitEq() > 0 ? VP8Util.delta(headerDecoder) : 0;
        int chromaDCDelta = headerDecoder.readBitEq() > 0 ? VP8Util.delta(headerDecoder) : 0;
        int chromaACDelta = headerDecoder.readBitEq() > 0 ? VP8Util.delta(headerDecoder) : 0;
        boolean refreshProbs = headerDecoder.readBitEq() == 0;
        VP8Util.QuantizationParams quants = new VP8Util.QuantizationParams(yacIndex, ydcDelta, y2dcDelta, y2acDelta, chromaDCDelta, chromaACDelta);
        int[][][][] coefProbs = VP8Util.getDefaultCoefProbs();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 8; ++j) {
                for (int k = 0; k < 3; ++k) {
                    for (int l = 0; l < 11; ++l) {
                        int newp;
                        if (headerDecoder.readBit(VP8Util.vp8CoefUpdateProbs[i][j][k][l]) <= 0) continue;
                        coefProbs[i][j][k][l] = newp = headerDecoder.decodeInt(8);
                    }
                }
            }
        }
        int macroBlockNoCoeffSkip = headerDecoder.readBitEq();
        Preconditions.checkState(1 == macroBlockNoCoeffSkip);
        int probSkipFalse = headerDecoder.decodeInt(8);
        for (mbRow = 0; mbRow < numberOfMBRows; ++mbRow) {
            for (mbCol = 0; mbCol < numberOfMBCols; ++mbCol) {
                VPXMacroblock mb = mbs[mbRow + 1][mbCol + 1];
                if (segmentation != 0 && segmentBased != null && segmentBased.segmentProbs != null) {
                    mb.segment = headerDecoder.readTree(VP8Util.segmentTree, segmentBased.segmentProbs);
                    this.segmentationMap[mbRow][mbCol] = (byte)mb.segment;
                }
                if (segmentation != 0 && segmentBased != null && segmentBased.qp != null) {
                    int qIndex = yacIndex;
                    qIndex = segmentBased.abs != 0 ? segmentBased.qp[mb.segment] : (qIndex += segmentBased.qp[mb.segment]);
                    quants = new VP8Util.QuantizationParams(qIndex, ydcDelta, y2dcDelta, y2acDelta, chromaDCDelta, chromaACDelta);
                }
                mb.quants = quants;
                if (loopFilterDeltaFlag != 0) {
                    int level = filterLevel;
                    level += this.refLoopFilterDeltas[0];
                    mb.filterLevel = level = MathUtil.clip(level, 0, 63);
                } else {
                    mb.filterLevel = filterLevel;
                }
                if (segmentation != 0 && segmentBased != null && segmentBased.lf != null) {
                    if (segmentBased.abs != 0) {
                        mb.filterLevel = segmentBased.lf[mb.segment];
                    } else {
                        mb.filterLevel += segmentBased.lf[mb.segment];
                        mb.filterLevel = MathUtil.clip(mb.filterLevel, 0, 63);
                    }
                }
                if (macroBlockNoCoeffSkip > 0) {
                    mb.skipCoeff = headerDecoder.readBit(probSkipFalse);
                }
                mb.lumaMode = headerDecoder.readTree(VP8Util.keyFrameYModeTree, VP8Util.keyFrameYModeProb);
                if (mb.lumaMode == 4) {
                    for (int sbRow = 0; sbRow < 4; ++sbRow) {
                        for (int sbCol = 0; sbCol < 4; ++sbCol) {
                            VPXMacroblock.Subblock sb = mb.ySubblocks[sbRow][sbCol];
                            VPXMacroblock.Subblock A = sb.getAbove(VP8Util.PLANE.Y1, mbs);
                            VPXMacroblock.Subblock L = sb.getLeft(VP8Util.PLANE.Y1, mbs);
                            sb.mode = headerDecoder.readTree(VP8Util.SubblockConstants.subblockModeTree, VP8Util.SubblockConstants.keyFrameSubblockModeProb[A.mode][L.mode]);
                        }
                    }
                } else {
                    int fixedMode;
                    switch (mb.lumaMode) {
                        case 0: {
                            fixedMode = 0;
                            break;
                        }
                        case 1: {
                            fixedMode = 2;
                            break;
                        }
                        case 2: {
                            fixedMode = 3;
                            break;
                        }
                        case 3: {
                            fixedMode = 1;
                            break;
                        }
                        default: {
                            fixedMode = 0;
                        }
                    }
                    mb.lumaMode = this.edgeEmu(mb.lumaMode, mbCol, mbRow);
                    for (int x = 0; x < 4; ++x) {
                        for (int y = 0; y < 4; ++y) {
                            mb.ySubblocks[y][x].mode = fixedMode;
                        }
                    }
                }
                mb.chromaMode = headerDecoder.readTree(VP8Util.vp8UVModeTree, VP8Util.vp8KeyFrameUVModeProb);
            }
        }
        for (mbRow = 0; mbRow < numberOfMBRows; ++mbRow) {
            for (mbCol = 0; mbCol < numberOfMBCols; ++mbCol) {
                VPXMacroblock mb = mbs[mbRow + 1][mbCol + 1];
                mb.decodeMacroBlock(mbs, decoder, coefProbs);
                mb.dequantMacroBlock(mbs);
            }
        }
        if (filterType > 0 && filterLevel != 0) {
            if (filterType == 2) {
                FilterUtil.loopFilterUV(mbs, sharpnessLevel, keyFrame);
                FilterUtil.loopFilterY(mbs, sharpnessLevel, keyFrame);
            } else if (filterType == 1) {
                // empty if block
            }
        }
        Picture p = Picture.createPicture(width, height, buffer, ColorSpace.YUV420);
        int mbWidth = VP8Util.getMacroblockCount(width);
        int mbHeight = VP8Util.getMacroblockCount(height);
        for (int mbRow2 = 0; mbRow2 < mbHeight; ++mbRow2) {
            for (int mbCol2 = 0; mbCol2 < mbWidth; ++mbCol2) {
                VPXMacroblock mb = mbs[mbRow2 + 1][mbCol2 + 1];
                mb.put(mbRow2, mbCol2, p);
            }
        }
        return p;
    }

    private int edgeEmu(int mode, int mbCol, int mbRow) {
        switch (mode) {
            case 1: {
                return mbRow == 0 ? 0 : mode;
            }
            case 2: {
                return mbCol == 0 ? 0 : mode;
            }
            case 3: {
                return this.edgeEmuTm(mode, mbCol, mbRow);
            }
        }
        return mode;
    }

    private int edgeEmuTm(int mode, int mbCol, int mbRow) {
        if (mbCol == 0) {
            return mbRow != 0 ? 1 : 0;
        }
        return mbRow != 0 ? mode : 2;
    }

    private SegmentBasedAdjustments updateSegmentation(VPXBooleanDecoder headerDecoder) {
        int updateMBSegmentationMap = headerDecoder.readBitEq();
        int updateSegmentFeatureData = headerDecoder.readBitEq();
        int[] qp = null;
        int[] lf = null;
        int abs = 0;
        if (updateSegmentFeatureData != 0) {
            int i;
            qp = new int[4];
            lf = new int[4];
            abs = headerDecoder.readBitEq();
            for (i = 0; i < 4; ++i) {
                int quantizerUpdate = headerDecoder.readBitEq();
                if (quantizerUpdate == 0) continue;
                qp[i] = headerDecoder.decodeInt(7);
                qp[i] = headerDecoder.readBitEq() != 0 ? -qp[i] : qp[i];
            }
            for (i = 0; i < 4; ++i) {
                int loopFilterUpdate = headerDecoder.readBitEq();
                if (loopFilterUpdate == 0) continue;
                lf[i] = headerDecoder.decodeInt(6);
                lf[i] = headerDecoder.readBitEq() != 0 ? -lf[i] : lf[i];
            }
        }
        int[] segmentProbs = new int[3];
        if (updateMBSegmentationMap != 0) {
            for (int i = 0; i < 3; ++i) {
                int segmentProbUpdate = headerDecoder.readBitEq();
                segmentProbs[i] = segmentProbUpdate != 0 ? headerDecoder.decodeInt(8) : 255;
            }
        }
        return new SegmentBasedAdjustments(segmentProbs, qp, lf, abs);
    }

    @UsedViaReflection
    public static int probe(ByteBuffer data) {
        if ((data.get(3) & 0xFF) == 157 && (data.get(4) & 0xFF) == 1 && (data.get(5) & 0xFF) == 42) {
            return 100;
        }
        return 0;
    }

    public static String printHexByte(byte b) {
        return "0x" + Integer.toHexString(b & 0xFF);
    }

    @Override
    public VideoCodecMeta getCodecMeta(ByteBuffer frame) {
        NIOUtils.skip(frame, 6);
        int twoBytesWidth = frame.get() & 0xFF | (frame.get() & 0xFF) << 8;
        int twoBytesHeight = frame.get() & 0xFF | (frame.get() & 0xFF) << 8;
        int width = twoBytesWidth & 0x3FFF;
        int height = twoBytesHeight & 0x3FFF;
        return VideoCodecMeta.createSimpleVideoCodecMeta(new Size(width, height), ColorSpace.YUV420);
    }

    private static class SegmentBasedAdjustments {
        private int[] segmentProbs;
        private int[] qp;
        private int[] lf;
        private int abs;

        public SegmentBasedAdjustments(int[] segmentProbs, int[] qp, int[] lf, int abs) {
            this.segmentProbs = segmentProbs;
            this.qp = qp;
            this.lf = lf;
            this.abs = abs;
        }
    }
}
