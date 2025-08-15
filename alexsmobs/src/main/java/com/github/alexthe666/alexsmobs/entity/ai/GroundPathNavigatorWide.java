/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GroundPathNavigatorWide
extends GroundPathNavigation {
    private float distancemodifier = 0.75f;

    public GroundPathNavigatorWide(Mob entitylivingIn, Level worldIn) {
        super(entitylivingIn, worldIn);
    }

    public GroundPathNavigatorWide(Mob entitylivingIn, Level worldIn, float distancemodifier) {
        super(entitylivingIn, worldIn);
        this.distancemodifier = distancemodifier;
    }

    protected void m_7636_() {
        boolean flag;
        Vec3 vector3d = this.m_7475_();
        this.f_26505_ = this.f_26494_.m_20205_() * this.distancemodifier;
        BlockPos vector3i = this.f_26496_.m_77400_();
        double d0 = Math.abs(this.f_26494_.m_20185_() - ((double)vector3i.m_123341_() + 0.5));
        double d1 = Math.abs(this.f_26494_.m_20186_() - (double)vector3i.m_123342_());
        double d2 = Math.abs(this.f_26494_.m_20189_() - ((double)vector3i.m_123343_() + 0.5));
        boolean bl = flag = d0 < (double)this.f_26505_ && d2 < (double)this.f_26505_ && d1 < 1.0;
        if (flag || this.m_264193_(this.f_26496_.m_77401_().f_77282_) && this.m_26559_(vector3d)) {
            this.f_26496_.m_77374_();
        }
        this.m_6481_(vector3d);
    }

    private boolean m_26559_(Vec3 currentPosition) {
        Vec3 vector3d3;
        if (this.f_26496_.m_77399_() + 1 >= this.f_26496_.m_77398_()) {
            return false;
        }
        Vec3 vector3d = Vec3.m_82539_((Vec3i)this.f_26496_.m_77400_());
        if (!currentPosition.m_82509_((Position)vector3d, 2.0)) {
            return false;
        }
        Vec3 vector3d1 = Vec3.m_82539_((Vec3i)this.f_26496_.m_77396_(this.f_26496_.m_77399_() + 1));
        Vec3 vector3d2 = vector3d1.m_82546_(vector3d);
        return vector3d2.m_82526_(vector3d3 = currentPosition.m_82546_(vector3d)) > 0.0;
    }
}
