/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import java.nio.FloatBuffer;

public interface AudioFilter {
    public void filter(FloatBuffer[] var1, long[] var2, FloatBuffer[] var3);

    public int getDelay();

    public int getNInputs();

    public int getNOutputs();
}
