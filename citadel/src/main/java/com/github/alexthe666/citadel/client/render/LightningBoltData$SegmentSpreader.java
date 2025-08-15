/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.citadel.client.render;

import net.minecraft.world.phys.Vec3;

public static interface LightningBoltData.SegmentSpreader {
    public static final LightningBoltData.SegmentSpreader NO_MEMORY = (perpendicularDist, randVec, maxDiff, scale, progress) -> randVec.m_82490_((double)maxDiff);

    public static LightningBoltData.SegmentSpreader memory(float memoryFactor) {
        return (perpendicularDist, randVec, maxDiff, spreadScale, progress) -> {
            float nextDiff = maxDiff * (1.0f - memoryFactor);
            Vec3 cur = randVec.m_82490_((double)nextDiff);
            if (progress > 0.5f) {
                cur = cur.m_82549_(perpendicularDist.m_82490_((double)(-1.0f * (1.0f - spreadScale))));
            }
            return perpendicularDist.m_82549_(cur);
        };
    }

    public Vec3 getSegmentAdd(Vec3 var1, Vec3 var2, float var3, float var4, float var5);
}
