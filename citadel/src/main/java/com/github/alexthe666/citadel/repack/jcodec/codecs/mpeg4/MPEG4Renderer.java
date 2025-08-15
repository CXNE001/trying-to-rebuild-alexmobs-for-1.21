/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.MPEG4Consts;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.MPEG4DCT;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.MPEG4DecodingContext;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.MPEG4Interpolator;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.Macroblock;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;

public class MPEG4Renderer {
    private static void checkMV(Macroblock.Vector mv, int xHigh, int xLow, int yHigh, int yLow) {
        if (mv.x > xHigh) {
            mv.x = xHigh;
        } else if (mv.x < xLow) {
            mv.x = xLow;
        }
        if (mv.y > yHigh) {
            mv.y = yHigh;
        } else if (mv.y < yLow) {
            mv.y = yLow;
        }
    }

    static void validateVector(Macroblock.Vector[] mvs, MPEG4DecodingContext ctx, int xPos, int yPos) {
        int shift = 5 + (ctx.quarterPel ? 1 : 0);
        int xHigh = ctx.mbWidth - xPos << shift;
        int xLow = -xPos - 1 << shift;
        int yHigh = ctx.mbHeight - yPos << shift;
        int yLow = -yPos - 1 << shift;
        MPEG4Renderer.checkMV(mvs[0], xHigh, xLow, yHigh, yLow);
        MPEG4Renderer.checkMV(mvs[1], xHigh, xLow, yHigh, yLow);
        MPEG4Renderer.checkMV(mvs[2], xHigh, xLow, yHigh, yLow);
        MPEG4Renderer.checkMV(mvs[3], xHigh, xLow, yHigh, yLow);
    }

    public static void renderIntra(Macroblock mb, MPEG4DecodingContext ctx) {
        MPEG4DCT.idctPut(mb.pred, mb.block, ctx.interlacing && mb.fieldDCT);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void renderInter(MPEG4DecodingContext ctx, Picture[] refs, Macroblock mb, int fcode, int ref, boolean bvop) {
        if (mb.coded) {
            if (mb.mcsel) {
                throw new RuntimeException("GMC");
            }
            if (mb.mode == 0 || mb.mode == 1 || mb.mode == 2) {
                if (mb.fieldPred) throw new RuntimeException("interlaced");
                MPEG4Renderer.renderMBInter(ctx, refs, mb, ref, bvop);
                return;
            } else {
                MPEG4Renderer.renderIntra(mb, ctx);
            }
            return;
        } else {
            MPEG4Renderer.renderMBInter(ctx, refs, mb, ref, bvop);
        }
    }

    static void renderMBInter(MPEG4DecodingContext ctx, Picture[] refs, Macroblock mb, int ref, boolean bvop) {
        int uv_dy;
        int uv_dx;
        Picture backward;
        Macroblock.Vector[] mv = new Macroblock.Vector[4];
        for (int i = 0; i < 4; ++i) {
            mv[i] = new Macroblock.Vector(mb.mvs[i].x, mb.mvs[i].y);
        }
        MPEG4Renderer.validateVector(mv, ctx, mb.x, mb.y);
        int mbX = mb.x << 4;
        int mbY = mb.y << 4;
        int codedW = ctx.mbWidth << 4;
        int codedH = ctx.mbHeight << 4;
        int codedWcr = ctx.mbWidth << 3;
        int codedHcr = ctx.mbHeight << 3;
        if (mb.mode != 2 || bvop) {
            backward = refs[ref];
            uv_dx = MPEG4Renderer.calcChromaMv(ctx, mv[0].x);
            uv_dy = MPEG4Renderer.calcChromaMv(ctx, mv[0].y);
            if (ctx.quarterPel) {
                MPEG4Interpolator.interpolate16x16QP(mb.pred[0], backward.getPlaneData(0), mbX, mbY, codedW, codedH, mv[0].x, mv[0].y, backward.getWidth(), ctx.rounding);
            } else {
                MPEG4Interpolator.interpolate16x16Planar(mb.pred[0], backward.getPlaneData(0), mbX, mbY, codedW, codedH, mv[0].x, mv[0].y, backward.getWidth(), ctx.rounding);
            }
        } else {
            uv_dx = MPEG4Renderer.calcChromaMvAvg(ctx, mv, true);
            uv_dy = MPEG4Renderer.calcChromaMvAvg(ctx, mv, false);
            backward = refs[0];
            byte[] lumaPlane = backward.getPlaneData(0);
            int lumaStride = backward.getWidth();
            if (ctx.quarterPel) {
                MPEG4Interpolator.interpolate8x8QP(mb.pred[0], 0, lumaPlane, mbX, mbY, codedW, codedH, mv[0].x, mv[0].y, lumaStride, ctx.rounding);
                MPEG4Interpolator.interpolate8x8QP(mb.pred[0], 8, lumaPlane, mbX + 8, mbY, codedW, codedH, mv[1].x, mv[1].y, lumaStride, ctx.rounding);
                MPEG4Interpolator.interpolate8x8QP(mb.pred[0], 128, lumaPlane, mbX, mbY + 8, codedW, codedH, mv[2].x, mv[2].y, lumaStride, ctx.rounding);
                MPEG4Interpolator.interpolate8x8QP(mb.pred[0], 136, lumaPlane, mbX + 8, mbY + 8, codedW, codedH, mv[3].x, mv[3].y, lumaStride, ctx.rounding);
            } else {
                MPEG4Interpolator.interpolate8x8Planar(mb.pred[0], 0, 16, lumaPlane, mbX, mbY, codedW, codedH, mv[0].x, mv[0].y, lumaStride, ctx.rounding);
                MPEG4Interpolator.interpolate8x8Planar(mb.pred[0], 8, 16, lumaPlane, mbX + 8, mbY, codedW, codedH, mv[1].x, mv[1].y, lumaStride, ctx.rounding);
                MPEG4Interpolator.interpolate8x8Planar(mb.pred[0], 128, 16, lumaPlane, mbX, mbY + 8, codedW, codedH, mv[2].x, mv[2].y, lumaStride, ctx.rounding);
                MPEG4Interpolator.interpolate8x8Planar(mb.pred[0], 136, 16, lumaPlane, mbX + 8, mbY + 8, codedW, codedH, mv[3].x, mv[3].y, lumaStride, ctx.rounding);
            }
        }
        MPEG4Interpolator.interpolate8x8Planar(mb.pred[1], 0, 8, refs[ref].getPlaneData(1), 8 * mb.x, 8 * mb.y, codedWcr, codedHcr, uv_dx, uv_dy, refs[ref].getPlaneWidth(1), ctx.rounding);
        MPEG4Interpolator.interpolate8x8Planar(mb.pred[2], 0, 8, refs[ref].getPlaneData(2), 8 * mb.x, 8 * mb.y, codedWcr, codedHcr, uv_dx, uv_dy, refs[ref].getPlaneWidth(2), ctx.rounding);
        if (mb.cbp != 0) {
            for (int i = 0; i < 6; ++i) {
                short[] block = mb.block[i];
                if ((mb.cbp & 1 << 5 - i) == 0) continue;
                MPEG4DCT.idctAdd(mb.pred, block, i, ctx.interlacing && mb.fieldDCT);
            }
        }
    }

    static int calcChromaMv(MPEG4DecodingContext ctx, int ret) {
        if (ctx.quarterPel) {
            ret = ctx.bsVersion <= 1 ? ret >> 1 | ret & 1 : (ret /= 2);
        }
        return (ret >> 1) + MPEG4Consts.ROUNDTAB_79[ret & 3];
    }

    static int calcChromaMvAvg(MPEG4DecodingContext ctx, Macroblock.Vector[] mv, boolean x) {
        int ret;
        if (ctx.quarterPel) {
            if (ctx.bsVersion <= 1) {
                ret = 0;
                for (int z = 0; z < 4; ++z) {
                    if (x) {
                        ret += mv[z].x >> 1 | mv[z].x & 1;
                        continue;
                    }
                    ret += mv[z].y >> 1 | mv[z].y & 1;
                }
            } else {
                ret = x ? mv[0].x / 2 + mv[1].x / 2 + mv[2].x / 2 + mv[3].x / 2 : mv[0].y / 2 + mv[1].y / 2 + mv[2].y / 2 + mv[3].y / 2;
            }
        } else {
            ret = x ? mv[0].x + mv[1].x + mv[2].x + mv[3].x : mv[0].y + mv[1].y + mv[2].y + mv[3].y;
        }
        ret = (ret >> 3) + MPEG4Consts.ROUNDTAB_76[ret & 0xF];
        return ret;
    }

    public static final int sanitize(int value, boolean quarterPel, int fcode) {
        int length = 1 << fcode + 4;
        if (value < -length) {
            return -length;
        }
        if (value >= length) {
            return length - 1;
        }
        return value;
    }
}
