/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx;

import com.github.alexthe666.citadel.repack.jcodec.api.NotImplementedException;
import com.github.alexthe666.citadel.repack.jcodec.api.NotSupportedException;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VP8DCT;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VP8Util;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VPXBooleanDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VPXMacroblock;
import java.util.Arrays;

public static class VPXMacroblock.Subblock {
    public int[] val;
    public int[] _predict;
    public int[] residue;
    private int col;
    private int row;
    private VP8Util.PLANE plane;
    public int mode;
    public boolean someValuePresent;
    private int[] tokens;
    private VPXMacroblock self;

    public VPXMacroblock.Subblock(VPXMacroblock self, int row, int col, VP8Util.PLANE plane) {
        this.self = self;
        this.row = row;
        this.col = col;
        this.plane = plane;
        this.tokens = new int[16];
    }

    public void predict(VPXMacroblock[][] mbs) {
        VPXMacroblock.Subblock aboveSb = this.getAbove(this.plane, mbs);
        VPXMacroblock.Subblock leftSb = this.getLeft(this.plane, mbs);
        int[] above = new int[4];
        int[] left = new int[4];
        int[] aboveValues = aboveSb.val != null ? aboveSb.val : VP8Util.PRED_BLOCK_127;
        above[0] = aboveValues[12];
        above[1] = aboveValues[13];
        above[2] = aboveValues[14];
        above[3] = aboveValues[15];
        int[] leftValues = leftSb.val != null ? leftSb.val : VP8Util.pickDefaultPrediction(this.mode);
        left[0] = leftValues[3];
        left[1] = leftValues[7];
        left[2] = leftValues[11];
        left[3] = leftValues[15];
        VPXMacroblock.Subblock aboveLeftSb = aboveSb.getLeft(this.plane, mbs);
        int aboveLeft = leftSb.val == null && aboveSb.val == null ? 127 : (aboveSb.val == null ? 127 : (aboveLeftSb.val != null ? aboveLeftSb.val[15] : VP8Util.pickDefaultPrediction(this.mode)[15]));
        int[] ar = this.getAboveRightLowestRow(mbs);
        switch (this.mode) {
            case 0: {
                this._predict = VP8Util.predictDC(above, left);
                break;
            }
            case 1: {
                this._predict = VP8Util.predictTM(above, left, aboveLeft);
                break;
            }
            case 2: {
                this._predict = VP8Util.predictVE(above, aboveLeft, ar);
                break;
            }
            case 3: {
                this._predict = VP8Util.predictHE(left, aboveLeft);
                break;
            }
            case 4: {
                this._predict = VP8Util.predictLD(above, ar);
                break;
            }
            case 5: {
                this._predict = VP8Util.predictRD(above, left, aboveLeft);
                break;
            }
            case 6: {
                this._predict = VP8Util.predictVR(above, left, aboveLeft);
                break;
            }
            case 7: {
                this._predict = VP8Util.predictVL(above, ar);
                break;
            }
            case 8: {
                this._predict = VP8Util.predictHD(above, left, aboveLeft);
                break;
            }
            case 9: {
                this._predict = VP8Util.predictHU(left);
                break;
            }
            default: {
                throw new NotSupportedException("TODO: unknowwn mode: " + this.mode);
            }
        }
    }

    public void reconstruct() {
        int[] p = this.val != null ? this.val : this._predict;
        int[] dest = new int[16];
        for (int aRow = 0; aRow < 4; ++aRow) {
            for (int aCol = 0; aCol < 4; ++aCol) {
                int a;
                dest[aRow * 4 + aCol] = a = VP8Util.QuantizationParams.clip255(this.residue[aRow * 4 + aCol] + p[aRow * 4 + aCol]);
            }
        }
        this.val = dest;
    }

    public VPXMacroblock.Subblock getAbove(VP8Util.PLANE plane, VPXMacroblock[][] mbs) {
        if (this.row > 0) {
            if (VP8Util.PLANE.Y1.equals((Object)this.plane)) {
                return this.self.ySubblocks[this.row - 1][this.col];
            }
            if (VP8Util.PLANE.U.equals((Object)this.plane)) {
                return this.self.uSubblocks[this.row - 1][this.col];
            }
            if (VP8Util.PLANE.V.equals((Object)this.plane)) {
                return this.self.vSubblocks[this.row - 1][this.col];
            }
        }
        int x = this.col;
        VPXMacroblock mb2 = mbs[this.self.Rrow - 1][this.self.column];
        if (plane == VP8Util.PLANE.Y2) {
            while (mb2.lumaMode == 4) {
                mb2 = mbs[mb2.Rrow - 1][mb2.column];
            }
        }
        return mb2.getBottomSubblock(x, plane);
    }

    public VPXMacroblock.Subblock getLeft(VP8Util.PLANE p, VPXMacroblock[][] mbs) {
        if (this.col > 0) {
            if (VP8Util.PLANE.Y1.equals((Object)this.plane)) {
                return this.self.ySubblocks[this.row][this.col - 1];
            }
            if (VP8Util.PLANE.U.equals((Object)this.plane)) {
                return this.self.uSubblocks[this.row][this.col - 1];
            }
            if (VP8Util.PLANE.V.equals((Object)this.plane)) {
                return this.self.vSubblocks[this.row][this.col - 1];
            }
        }
        int y = this.row;
        VPXMacroblock mb2 = mbs[this.self.Rrow][this.self.column - 1];
        if (p == VP8Util.PLANE.Y2) {
            while (mb2.lumaMode == 4) {
                mb2 = mbs[mb2.Rrow][mb2.column - 1];
            }
        }
        return mb2.getRightSubBlock(y, p);
    }

    private int[] getAboveRightLowestRow(VPXMacroblock[][] mbs) {
        int[] aboveRightDistValues;
        if (!VP8Util.PLANE.Y1.equals((Object)this.plane)) {
            throw new NotImplementedException("Decoder.getAboveRight: not implemented for Y2 and chroma planes");
        }
        if (this.row == 0 && this.col < 3) {
            VPXMacroblock mb2 = mbs[this.self.Rrow - 1][this.self.column];
            VPXMacroblock.Subblock aboveRight = mb2.ySubblocks[3][this.col + 1];
            aboveRightDistValues = aboveRight.val;
        } else if (this.row > 0 && this.col < 3) {
            VPXMacroblock.Subblock aboveRight = this.self.ySubblocks[this.row - 1][this.col + 1];
            aboveRightDistValues = aboveRight.val;
        } else if (this.row == 0 && this.col == 3) {
            VPXMacroblock aboveRightMb = mbs[this.self.Rrow - 1][this.self.column + 1];
            if (aboveRightMb.column < mbs[0].length - 1) {
                VPXMacroblock.Subblock aboveRightSb = aboveRightMb.ySubblocks[3][0];
                aboveRightDistValues = aboveRightSb.val;
            } else {
                aboveRightDistValues = new int[16];
                int fillVal = aboveRightMb.Rrow == 0 ? 127 : mbs[this.self.Rrow - 1][this.self.column].ySubblocks[3][3].val[15];
                Arrays.fill(aboveRightDistValues, fillVal);
            }
        } else {
            VPXMacroblock.Subblock sb2 = this.self.ySubblocks[0][3];
            return sb2.getAboveRightLowestRow(mbs);
        }
        if (aboveRightDistValues == null) {
            aboveRightDistValues = VP8Util.PRED_BLOCK_127;
        }
        int[] ar = new int[]{aboveRightDistValues[12], aboveRightDistValues[13], aboveRightDistValues[14], aboveRightDistValues[15]};
        return ar;
    }

    public void decodeSubBlock(VPXBooleanDecoder decoder, int[][][][] allProbs, int ilc, int type, boolean withY2) {
        int startAt = 0;
        if (withY2) {
            startAt = 1;
        }
        int lc = ilc;
        int count = 0;
        int v = 1;
        boolean skip = false;
        this.someValuePresent = false;
        while (v != 11 && count + startAt < 16) {
            int[] probs = allProbs[type][VP8Util.SubblockConstants.vp8CoefBands[count + startAt]][lc];
            v = !skip ? decoder.readTree(VP8Util.SubblockConstants.vp8CoefTree, probs) : decoder.readTreeSkip(VP8Util.SubblockConstants.vp8CoefTree, probs, 1);
            int dv = this.decodeToken(decoder, v);
            lc = 0;
            skip = false;
            if (dv == 1 || dv == -1) {
                lc = 1;
            } else if (dv > 1 || dv < -1) {
                lc = 2;
            } else if (dv == 0) {
                skip = true;
            }
            if (v != 11) {
                this.tokens[VP8Util.SubblockConstants.vp8defaultZigZag1d[count + startAt]] = dv;
            }
            ++count;
        }
        for (int x = 0; x < 16; ++x) {
            if (this.tokens[x] == 0) continue;
            this.someValuePresent = true;
        }
    }

    private int decodeToken(VPXBooleanDecoder decoder, int initialValue) {
        int token = initialValue;
        if (initialValue == 5) {
            token = 5 + this.DCTextra(decoder, VP8Util.SubblockConstants.Pcat1);
        }
        if (initialValue == 6) {
            token = 7 + this.DCTextra(decoder, VP8Util.SubblockConstants.Pcat2);
        }
        if (initialValue == 7) {
            token = 11 + this.DCTextra(decoder, VP8Util.SubblockConstants.Pcat3);
        }
        if (initialValue == 8) {
            token = 19 + this.DCTextra(decoder, VP8Util.SubblockConstants.Pcat4);
        }
        if (initialValue == 9) {
            token = 35 + this.DCTextra(decoder, VP8Util.SubblockConstants.Pcat5);
        }
        if (initialValue == 10) {
            token = 67 + this.DCTextra(decoder, VP8Util.SubblockConstants.Pcat6);
        }
        if (initialValue != 0 && initialValue != 11 && decoder.readBitEq() > 0) {
            token = -token;
        }
        return token;
    }

    private int DCTextra(VPXBooleanDecoder decoder, int[] p) {
        int v = 0;
        int offset = 0;
        do {
            v += v + decoder.readBit(p[offset]);
        } while (p[++offset] > 0);
        return v;
    }

    public void dequantSubblock(int dc, int ac, Integer Dc) {
        int[] adjustedValues = new int[16];
        adjustedValues[0] = this.tokens[0] * dc;
        for (int i = 1; i < 16; ++i) {
            adjustedValues[i] = this.tokens[i] * ac;
        }
        if (Dc != null) {
            adjustedValues[0] = Dc;
        }
        this.residue = VP8DCT.decodeDCT(adjustedValues);
    }

    static /* synthetic */ int[] access$000(VPXMacroblock.Subblock x0) {
        return x0.tokens;
    }
}
