/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityDropBear;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

class EntityDropBear.AIUpsideDownWander
extends RandomStrollGoal {
    public EntityDropBear.AIUpsideDownWander() {
        super((PathfinderMob)EntityDropBear.this, 1.0, 50);
    }

    @Nullable
    protected Vec3 m_7037_() {
        if (EntityDropBear.this.isUpsideDown()) {
            for (int i = 0; i < 15; ++i) {
                Random rand = new Random();
                BlockPos randPos = EntityDropBear.this.m_20183_().m_7918_(rand.nextInt(16) - 8, -2, rand.nextInt(16) - 8);
                BlockPos lowestPos = EntityDropBear.getLowestPos((LevelAccessor)EntityDropBear.this.m_9236_(), randPos);
                if (!EntityDropBear.this.m_9236_().m_8055_(lowestPos).m_60783_((BlockGetter)EntityDropBear.this.m_9236_(), lowestPos, Direction.DOWN)) continue;
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
        if (EntityDropBear.this.isUpsideDown()) {
            double d2;
            double d0 = EntityDropBear.this.m_20185_() - this.f_25726_;
            double d4 = d0 * d0 + (d2 = EntityDropBear.this.m_20189_() - this.f_25728_) * d2;
            return d4 > 4.0;
        }
        return super.m_8045_();
    }

    public void m_8041_() {
        super.m_8041_();
        this.f_25726_ = 0.0;
        this.f_25727_ = 0.0;
        this.f_25728_ = 0.0;
    }

    public void m_8056_() {
        if (EntityDropBear.this.isUpsideDown()) {
            this.f_25725_.m_21566_().m_6849_(this.f_25726_, this.f_25727_, this.f_25728_, this.f_25729_ * (double)0.7f);
        } else {
            this.f_25725_.m_21573_().m_26519_(this.f_25726_, this.f_25727_, this.f_25728_, this.f_25729_);
        }
    }
}
