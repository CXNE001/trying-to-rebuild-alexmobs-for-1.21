/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.pathfinder.PathComputationType
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityStradpole;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

static class EntityStradpole.StradpoleAISwim
extends RandomStrollGoal {
    public EntityStradpole.StradpoleAISwim(EntityStradpole creature, double speed, int chance) {
        super((PathfinderMob)creature, speed, chance, false);
    }

    public boolean m_8036_() {
        if (!this.f_25725_.m_20077_() && !this.f_25725_.m_20069_() || this.f_25725_.m_20159_() || this.f_25725_.m_5448_() != null || !this.f_25725_.m_20069_() && !this.f_25725_.m_20077_() && this.f_25725_ instanceof ISemiAquatic && !((ISemiAquatic)this.f_25725_).shouldEnterWater()) {
            return false;
        }
        if (!this.f_25731_ && this.f_25725_.m_217043_().m_188503_(this.f_25730_) != 0) {
            return false;
        }
        Vec3 vector3d = this.m_7037_();
        if (vector3d == null) {
            return false;
        }
        this.f_25726_ = vector3d.f_82479_;
        this.f_25727_ = vector3d.f_82480_;
        this.f_25728_ = vector3d.f_82481_;
        this.f_25731_ = false;
        return true;
    }

    @Nullable
    protected Vec3 m_7037_() {
        Vec3 vector3d;
        if (this.f_25725_.m_217043_().m_188501_() < 0.3f && (vector3d = this.findSurfaceTarget(this.f_25725_, 15, 7)) != null) {
            return vector3d;
        }
        vector3d = LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)7, (int)3);
        int i = 0;
        while (vector3d != null && !this.f_25725_.m_9236_().m_6425_(AMBlockPos.fromVec3(vector3d)).m_205070_(FluidTags.f_13132_) && !this.f_25725_.m_9236_().m_8055_(AMBlockPos.fromVec3(vector3d)).m_60647_((BlockGetter)this.f_25725_.m_9236_(), AMBlockPos.fromVec3(vector3d), PathComputationType.WATER) && i++ < 15) {
            vector3d = LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)10, (int)7);
        }
        return vector3d;
    }

    private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
        BlockPos blockpos = pos.m_7918_(dx * scale, 0, dz * scale);
        return this.f_25725_.m_9236_().m_6425_(blockpos).m_205070_(FluidTags.f_13132_) || this.f_25725_.m_9236_().m_6425_(blockpos).m_205070_(FluidTags.f_13131_) && !this.f_25725_.m_9236_().m_8055_(blockpos).m_280555_();
    }

    private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
        return this.f_25725_.m_9236_().m_8055_(pos.m_7918_(dx * scale, 1, dz * scale)).m_60795_() && this.f_25725_.m_9236_().m_8055_(pos.m_7918_(dx * scale, 2, dz * scale)).m_60795_();
    }

    private Vec3 findSurfaceTarget(PathfinderMob creature, int i, int i1) {
        BlockPos upPos = creature.m_20183_();
        while (creature.m_9236_().m_6425_(upPos).m_205070_(FluidTags.f_13131_) || creature.m_9236_().m_6425_(upPos).m_205070_(FluidTags.f_13132_)) {
            upPos = upPos.m_7494_();
        }
        if (this.isAirAbove(upPos.m_7495_(), 0, 0, 0) && this.canJumpTo(upPos.m_7495_(), 0, 0, 0)) {
            return new Vec3((double)((float)upPos.m_123341_() + 0.5f), (double)((float)upPos.m_123342_() - 1.0f), (double)((float)upPos.m_123343_() + 0.5f));
        }
        return null;
    }
}
