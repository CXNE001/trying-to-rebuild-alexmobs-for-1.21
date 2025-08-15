/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.client.render;

import java.util.Random;

public static interface LightningBoltData.RandomFunction {
    public static final LightningBoltData.RandomFunction UNIFORM = Random::nextFloat;
    public static final LightningBoltData.RandomFunction GAUSSIAN = rand -> (float)rand.nextGaussian();

    public float getRandom(Random var1);
}
