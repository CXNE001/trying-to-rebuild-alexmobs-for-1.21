/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.tuple.Pair
 */
package com.github.alexthe666.citadel.client.render;

import java.util.Random;
import org.apache.commons.lang3.tuple.Pair;

public static interface LightningBoltData.SpawnFunction {
    public static final LightningBoltData.SpawnFunction NO_DELAY = rand -> Pair.of((Object)Float.valueOf(0.0f), (Object)Float.valueOf(0.0f));
    public static final LightningBoltData.SpawnFunction CONSECUTIVE = new LightningBoltData.SpawnFunction(){

        @Override
        public Pair<Float, Float> getSpawnDelayBounds(Random rand) {
            return Pair.of((Object)Float.valueOf(0.0f), (Object)Float.valueOf(0.0f));
        }

        @Override
        public boolean isConsecutive() {
            return true;
        }
    };

    public static LightningBoltData.SpawnFunction delay(float delay) {
        return rand -> Pair.of((Object)Float.valueOf(delay), (Object)Float.valueOf(delay));
    }

    public static LightningBoltData.SpawnFunction noise(float delay, float noise) {
        return rand -> Pair.of((Object)Float.valueOf(delay - noise), (Object)Float.valueOf(delay + noise));
    }

    public Pair<Float, Float> getSpawnDelayBounds(Random var1);

    default public float getSpawnDelay(Random rand) {
        Pair<Float, Float> bounds = this.getSpawnDelayBounds(rand);
        return ((Float)bounds.getLeft()).floatValue() + (((Float)bounds.getRight()).floatValue() - ((Float)bounds.getLeft()).floatValue()) * rand.nextFloat();
    }

    default public boolean isConsecutive() {
        return false;
    }
}
