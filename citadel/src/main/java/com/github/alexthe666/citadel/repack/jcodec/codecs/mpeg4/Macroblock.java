/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4;

public class Macroblock {
    public static final int MBPRED_SIZE = 15;
    public Vector[] mvs = new Vector[4];
    public short[][] predValues;
    public int[] acpredDirections;
    public int mode;
    public int quant;
    public boolean fieldDCT;
    public boolean fieldPred;
    public boolean fieldForTop;
    public boolean fieldForBottom;
    private Vector[] pmvs = new Vector[4];
    private Vector[] qmvs = new Vector[4];
    public int cbp;
    public Vector[] bmvs = new Vector[4];
    public Vector[] bqmvs = new Vector[4];
    public Vector amv;
    public Vector mvsAvg;
    public int x;
    public int y;
    public int bound;
    public boolean acpredFlag;
    public short[] predictors;
    public short[][] block;
    public boolean coded;
    public boolean mcsel;
    public byte[][] pred;

    public static Vector vec() {
        return new Vector(0, 0);
    }

    public Macroblock() {
        for (int i = 0; i < 4; ++i) {
            this.mvs[i] = Macroblock.vec();
            this.pmvs[i] = Macroblock.vec();
            this.qmvs[i] = Macroblock.vec();
            this.bmvs[i] = Macroblock.vec();
            this.bqmvs[i] = Macroblock.vec();
        }
        this.pred = new byte[][]{new byte[256], new byte[64], new byte[64], new byte[256], new byte[64], new byte[64]};
        this.predValues = new short[6][15];
        this.acpredDirections = new int[6];
        this.amv = Macroblock.vec();
        this.predictors = new short[8];
        this.block = new short[6][64];
    }

    public void reset(int x2, int y2, int bound2) {
        this.x = x2;
        this.y = y2;
        this.bound = bound2;
    }

    public static class Vector {
        public int x;
        public int y;

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
