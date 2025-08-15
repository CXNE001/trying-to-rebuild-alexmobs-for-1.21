/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 */
package com.github.alexthe666.citadel.client.render;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import com.github.alexthe666.citadel.client.render.LightningRender;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Set;

public class LightningRender.BoltOwnerData {
    private final Set<LightningRender.BoltInstance> bolts = new ObjectOpenHashSet();
    private LightningBoltData lastBolt;
    private LightningRender.Timestamp lastBoltTimestamp = new LightningRender.Timestamp(LightningRender.this);
    private LightningRender.Timestamp lastUpdateTimestamp = new LightningRender.Timestamp(LightningRender.this);
    private double lastBoltDelay;

    private void addBolt(LightningRender.BoltInstance instance, LightningRender.Timestamp timestamp) {
        this.bolts.add(instance);
        this.lastBoltDelay = instance.bolt.getSpawnFunction().getSpawnDelay(LightningRender.this.random);
        this.lastBoltTimestamp = timestamp;
    }
}
