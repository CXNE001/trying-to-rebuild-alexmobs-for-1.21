/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.phys.AABB
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;

class EntityDropBear.2
extends EntityAINearestTarget3D {
    EntityDropBear.2(Mob goalOwnerIn, Class targetClassIn, boolean checkSight) {
        super(goalOwnerIn, targetClassIn, checkSight);
    }

    @Override
    protected AABB m_7255_(double targetDistance) {
        AABB bb = this.f_26135_.m_20191_().m_82377_(targetDistance, targetDistance, targetDistance);
        return new AABB(bb.f_82288_, 0.0, bb.f_82290_, bb.f_82291_, 256.0, bb.f_82293_);
    }
}
