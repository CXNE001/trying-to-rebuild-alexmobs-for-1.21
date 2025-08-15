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

import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityElephant.AIWalkIdle
extends RandomStrollGoal {
    public EntityElephant.AIWalkIdle(EntityElephant e, double v) {
        super((PathfinderMob)e, v);
    }

    public boolean m_8036_() {
        this.f_25730_ = EntityElephant.this.isTusked() || !EntityElephant.this.inCaravan() ? 50 : 120;
        return super.m_8036_();
    }

    @Nullable
    protected Vec3 m_7037_() {
        return LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)(EntityElephant.this.isTusked() || !EntityElephant.this.inCaravan() ? 25 : 10), (int)7);
    }
}
