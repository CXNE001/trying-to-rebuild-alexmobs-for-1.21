/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.nio.ByteBuffer;

public static class MPSUtils.VideoStreamDescriptor
extends MPSUtils.MPEGMediaDescriptor {
    private int multipleFrameRate;
    private int frameRateCode;
    private boolean mpeg1Only;
    private int constrainedParameter;
    private int stillPicture;
    private int profileAndLevel;
    private int chromaFormat;
    private int frameRateExtension;
    Rational[] frameRates = new Rational[]{null, new Rational(24000, 1001), new Rational(24, 1), new Rational(25, 1), new Rational(30000, 1001), new Rational(30, 1), new Rational(50, 1), new Rational(60000, 1001), new Rational(60, 1), null, null, null, null, null, null, null};

    @Override
    public void parse(ByteBuffer buf) {
        super.parse(buf);
        int b0 = buf.get() & 0xFF;
        this.multipleFrameRate = b0 >> 7 & 1;
        this.frameRateCode = b0 >> 3 & 0xF;
        this.mpeg1Only = (b0 >> 2 & 1) == 0;
        this.constrainedParameter = b0 >> 1 & 1;
        this.stillPicture = b0 & 1;
        if (!this.mpeg1Only) {
            this.profileAndLevel = buf.get() & 0xFF;
            int b1 = buf.get() & 0xFF;
            this.chromaFormat = b1 >> 6;
            this.frameRateExtension = b1 >> 5 & 1;
        }
    }

    public Rational getFrameRate() {
        return this.frameRates[this.frameRateCode];
    }

    public int getMultipleFrameRate() {
        return this.multipleFrameRate;
    }

    public int getFrameRateCode() {
        return this.frameRateCode;
    }

    public boolean isMpeg1Only() {
        return this.mpeg1Only;
    }

    public int getConstrainedParameter() {
        return this.constrainedParameter;
    }

    public int getStillPicture() {
        return this.stillPicture;
    }

    public int getProfileAndLevel() {
        return this.profileAndLevel;
    }

    public int getChromaFormat() {
        return this.chromaFormat;
    }

    public int getFrameRateExtension() {
        return this.frameRateExtension;
    }
}
