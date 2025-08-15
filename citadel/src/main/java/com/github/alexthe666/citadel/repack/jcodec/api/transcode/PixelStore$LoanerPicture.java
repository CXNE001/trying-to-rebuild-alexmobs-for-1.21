/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;

public static class PixelStore.LoanerPicture {
    private Picture picture;
    private int refCnt;

    public PixelStore.LoanerPicture(Picture picture, int refCnt) {
        this.picture = picture;
        this.refCnt = refCnt;
    }

    public Picture getPicture() {
        return this.picture;
    }

    public int getRefCnt() {
        return this.refCnt;
    }

    public void decRefCnt() {
        --this.refCnt;
    }

    public boolean unused() {
        return this.refCnt <= 0;
    }

    public void incRefCnt() {
        ++this.refCnt;
    }
}
