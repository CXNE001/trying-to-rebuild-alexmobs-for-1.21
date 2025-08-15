/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;

class EntitySkreecher.WanderUpsideDownGoal
extends RandomStrollGoal {
    private int stillTicks;

    public EntitySkreecher.WanderUpsideDownGoal() {
        super((PathfinderMob)EntitySkreecher.this, 1.0, 25);
        this.stillTicks = 0;
    }

    @Nullable
    protected Vec3 m_7037_() {
        if (EntitySkreecher.this.isClinging()) {
            int distance = 16;
            int i = 0;
            if (i < 15) {
                Random rand = new Random();
                BlockPos randPos = EntitySkreecher.this.m_20183_().m_7918_(rand.nextInt(distance * 2) - distance, -4, rand.nextInt(distance * 2) - distance);
                BlockPos lowestPos = EntitySkreecher.this.getCeilingOf(randPos).m_6625_(rand.nextInt(4));
                return Vec3.m_82512_((Vec3i)lowestPos);
            }
            return null;
        }
        return super.m_7037_();
    }

    public boolean m_8036_() {
        return super.m_8036_();
    }

    public boolean m_8045_() {
        return super.m_8045_();
    }

    public void m_8041_() {
        super.m_8041_();
        this.f_25726_ = 0.0;
        this.f_25727_ = 0.0;
        this.f_25728_ = 0.0;
    }

    public void m_8056_() {
        this.stillTicks = 0;
        this.f_25725_.m_21573_().m_26519_(this.f_25726_, this.f_25727_, this.f_25728_, this.f_25729_);
    }
}
