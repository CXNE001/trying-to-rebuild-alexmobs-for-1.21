/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.syntax;

public static class PCE.TaggedElement {
    private final boolean isCPE;
    private final int tag;

    public PCE.TaggedElement(boolean isCPE, int tag) {
        this.isCPE = isCPE;
        this.tag = tag;
    }

    public boolean isIsCPE() {
        return this.isCPE;
    }

    public int getTag() {
        return this.tag;
    }
}
