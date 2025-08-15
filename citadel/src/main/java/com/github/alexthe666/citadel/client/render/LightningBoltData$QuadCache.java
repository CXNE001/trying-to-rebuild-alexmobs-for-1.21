/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.citadel.client.render;

import net.minecraft.world.phys.Vec3;

private static class LightningBoltData.QuadCache {
    private final Vec3 prevEnd;
    private final Vec3 prevEndRight;
    private final Vec3 prevEndBack;

    private LightningBoltData.QuadCache(Vec3 prevEnd, Vec3 prevEndRight, Vec3 prevEndBack) {
        this.prevEnd = prevEnd;
        this.prevEndRight = prevEndRight;
        this.prevEndBack = prevEndBack;
    }
}
