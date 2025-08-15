/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.util.DefaultRandomPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.pathfinder.PathComputationType
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

public class AnimalAISwimBottom
extends RandomStrollGoal {
    public AnimalAISwimBottom(PathfinderMob p_i48937_1_, double p_i48937_2_, int p_i48937_4_) {
        super(p_i48937_1_, p_i48937_2_, p_i48937_4_);
    }

    @Nullable
    protected Vec3 m_7037_() {
        Vec3 vec = DefaultRandomPos.m_148403_((PathfinderMob)this.f_25725_, (int)10, (int)7);
        int var2 = 0;
        while (vec != null && !this.f_25725_.m_9236_().m_8055_(AMBlockPos.fromVec3(vec)).m_60647_((BlockGetter)this.f_25725_.m_9236_(), AMBlockPos.fromVec3(vec), PathComputationType.WATER) && var2++ < 10) {
            vec = DefaultRandomPos.m_148403_((PathfinderMob)this.f_25725_, (int)10, (int)7);
        }
        int yDrop = 1 + this.f_25725_.m_217043_().m_188503_(3);
        if (vec != null) {
            BlockPos pos = AMBlockPos.fromVec3(vec);
            while (this.f_25725_.m_9236_().m_6425_(pos).m_205070_(FluidTags.f_13131_) && this.f_25725_.m_9236_().m_8055_(pos).m_60647_((BlockGetter)this.f_25725_.m_9236_(), AMBlockPos.fromVec3(vec), PathComputationType.WATER) && pos.m_123342_() > 1) {
                pos = pos.m_7495_();
            }
            pos = pos.m_7494_();
            for (int yUp = 0; this.f_25725_.m_9236_().m_6425_(pos).m_205070_(FluidTags.f_13131_) && this.f_25725_.m_9236_().m_8055_(pos).m_60647_((BlockGetter)this.f_25725_.m_9236_(), AMBlockPos.fromVec3(vec), PathComputationType.WATER) && yUp < yDrop; ++yUp) {
                pos = pos.m_7494_();
            }
            return Vec3.m_82512_((Vec3i)pos);
        }
        return vec;
    }
}
