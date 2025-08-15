/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.aac.blocks;

public static class BlockICS.Pulse {
    private int numPulse;
    private int[] pos;
    private int[] amp;

    public BlockICS.Pulse(int numPulse, int[] pos, int[] amp) {
        this.numPulse = numPulse;
        this.pos = pos;
        this.amp = amp;
    }

    public int getNumPulse() {
        return this.numPulse;
    }

    public int[] getPos() {
        return this.pos;
    }

    public int[] getAmp() {
        return this.amp;
    }
}
