/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.TameableAIRide;
import net.minecraft.world.entity.PathfinderMob;

class EntityTusklin.1
extends TameableAIRide {
    EntityTusklin.1(PathfinderMob dragon, double speed, boolean strafe) {
        super(dragon, speed, strafe);
    }

    @Override
    public boolean shouldMoveForward() {
        return true;
    }

    @Override
    public boolean shouldMoveBackwards() {
        return false;
    }
}
