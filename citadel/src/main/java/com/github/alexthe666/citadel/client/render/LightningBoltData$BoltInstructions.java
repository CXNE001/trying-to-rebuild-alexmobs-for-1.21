/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.citadel.client.render;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import net.minecraft.world.phys.Vec3;

protected static class LightningBoltData.BoltInstructions {
    private final Vec3 start;
    private final Vec3 perpendicularDist;
    private final LightningBoltData.QuadCache cache;
    private final float progress;
    private final boolean isBranch;

    private LightningBoltData.BoltInstructions(Vec3 start, float progress, Vec3 perpendicularDist, LightningBoltData.QuadCache cache, boolean isBranch) {
        this.start = start;
        this.perpendicularDist = perpendicularDist;
        this.progress = progress;
        this.cache = cache;
        this.isBranch = isBranch;
    }
}
