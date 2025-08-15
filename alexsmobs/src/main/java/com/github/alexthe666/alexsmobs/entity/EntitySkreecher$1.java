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

class EntitySkreecher.1
extends EntityAINearestTarget3D {
    EntitySkreecher.1(Mob goalOwnerIn, Class targetClassIn, boolean checkSight) {
        super(goalOwnerIn, targetClassIn, checkSight);
    }

    @Override
    protected AABB m_7255_(double targetDistance) {
        AABB bb = this.f_26135_.m_20191_().m_82377_(16.0, 1.0, 16.0);
        return new AABB(bb.f_82288_, -64.0, bb.f_82290_, bb.f_82291_, 320.0, bb.f_82293_);
    }
}
