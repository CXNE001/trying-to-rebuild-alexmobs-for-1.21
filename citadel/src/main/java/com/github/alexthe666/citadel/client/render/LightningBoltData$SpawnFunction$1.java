/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.tuple.Pair
 */
package com.github.alexthe666.citadel.client.render;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import java.util.Random;
import org.apache.commons.lang3.tuple.Pair;

class LightningBoltData.SpawnFunction.1
implements LightningBoltData.SpawnFunction {
    LightningBoltData.SpawnFunction.1() {
    }

    @Override
    public Pair<Float, Float> getSpawnDelayBounds(Random rand) {
        return Pair.of((Object)Float.valueOf(0.0f), (Object)Float.valueOf(0.0f));
    }

    @Override
    public boolean isConsecutive() {
        return true;
    }
}
