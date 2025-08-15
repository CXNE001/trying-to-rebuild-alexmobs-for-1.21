/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.client.render;

public static interface LightningBoltData.SpreadFunction {
    public static final LightningBoltData.SpreadFunction LINEAR_ASCENT = progress -> progress;
    public static final LightningBoltData.SpreadFunction LINEAR_ASCENT_DESCENT = progress -> (progress - Math.max(0.0f, 2.0f * progress - 1.0f)) / 0.5f;
    public static final LightningBoltData.SpreadFunction SINE = progress -> (float)Math.sin(Math.PI * (double)progress);

    public float getMaxSpread(float var1);
}
