/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityGorilla.AIWalkIdle
extends RandomStrollGoal {
    public EntityGorilla.AIWalkIdle(EntityGorilla entityGorilla2, double v) {
        super((PathfinderMob)entityGorilla2, v);
    }

    public boolean m_8036_() {
        this.f_25730_ = EntityGorilla.this.isSilverback() ? 10 : 120;
        return super.m_8036_();
    }

    @Nullable
    protected Vec3 m_7037_() {
        return LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)(EntityGorilla.this.isSilverback() ? 25 : 10), (int)7);
    }
}
