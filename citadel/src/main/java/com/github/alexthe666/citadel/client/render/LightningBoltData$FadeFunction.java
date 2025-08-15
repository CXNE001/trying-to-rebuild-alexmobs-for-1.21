/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.tuple.Pair
 */
package com.github.alexthe666.citadel.client.render;

import org.apache.commons.lang3.tuple.Pair;

public static interface LightningBoltData.FadeFunction {
    public static final LightningBoltData.FadeFunction NONE = (totalBolts, lifeScale) -> Pair.of((Object)0, (Object)totalBolts);

    public static LightningBoltData.FadeFunction fade(float fade) {
        return (totalBolts, lifeScale) -> {
            int start = lifeScale > 1.0f - fade ? (int)((float)totalBolts * (lifeScale - (1.0f - fade)) / fade) : 0;
            int end = lifeScale < fade ? (int)((float)totalBolts * (lifeScale / fade)) : totalBolts;
            return Pair.of((Object)start, (Object)end);
        };
    }

    public Pair<Integer, Integer> getRenderBounds(int var1, float var2);
}
