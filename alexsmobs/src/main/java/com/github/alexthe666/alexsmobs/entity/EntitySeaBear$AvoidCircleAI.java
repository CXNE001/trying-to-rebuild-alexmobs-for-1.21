/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.DefaultRandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntitySeaBear.AvoidCircleAI
extends Goal {
    private Vec3 target = null;

    public EntitySeaBear.AvoidCircleAI() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return EntitySeaBear.this.circleCooldown > 0 && EntitySeaBear.this.lastCircle != null && EntitySeaBear.this.getAnimation() != ANIMATION_POINT;
    }

    public void m_8037_() {
        BlockPos pos = EntitySeaBear.this.lastCircle;
        if (this.target == null || EntitySeaBear.this.m_20238_(this.target) < 2.0 || !EntitySeaBear.this.m_9236_().m_6425_(AMBlockPos.fromVec3(this.target).m_7494_()).m_205070_(FluidTags.f_13131_)) {
            this.target = DefaultRandomPos.m_148407_((PathfinderMob)EntitySeaBear.this, (int)20, (int)7, (Vec3)Vec3.m_82512_((Vec3i)pos));
        }
        if (this.target != null && EntitySeaBear.this.m_9236_().m_6425_(AMBlockPos.fromVec3(this.target).m_7494_()).m_205070_(FluidTags.f_13131_)) {
            EntitySeaBear.this.m_21573_().m_26519_(this.target.f_82479_, this.target.f_82480_, this.target.f_82481_, 1.0);
        }
    }
}
