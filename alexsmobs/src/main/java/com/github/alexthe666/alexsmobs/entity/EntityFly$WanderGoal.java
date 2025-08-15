/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.AirAndWaterRandomPos
 *  net.minecraft.world.entity.ai.util.HoverRandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;

class EntityFly.WanderGoal
extends Goal {
    EntityFly.WanderGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return EntityFly.this.f_21344_.m_26571_() && EntityFly.this.f_19796_.m_188503_(3) == 0;
    }

    public boolean m_8045_() {
        return EntityFly.this.f_21344_.m_26572_();
    }

    public void m_8056_() {
        Vec3 vector3d = this.getRandomLocation();
        if (vector3d != null) {
            EntityFly.this.f_21344_.m_26536_(EntityFly.this.f_21344_.m_7864_(AMBlockPos.fromVec3(vector3d), 1), 1.0);
        }
    }

    @Nullable
    private Vec3 getRandomLocation() {
        Vec3 vec3 = EntityFly.this.m_20252_(0.0f);
        int i = 8;
        Vec3 vec32 = HoverRandomPos.m_148465_((PathfinderMob)EntityFly.this, (int)8, (int)7, (double)vec3.f_82479_, (double)vec3.f_82481_, (float)1.5707964f, (int)3, (int)1);
        return vec32 != null ? vec32 : AirAndWaterRandomPos.m_148357_((PathfinderMob)EntityFly.this, (int)8, (int)4, (int)-2, (double)vec3.f_82479_, (double)vec3.f_82481_, (double)1.5707963705062866);
    }
}
