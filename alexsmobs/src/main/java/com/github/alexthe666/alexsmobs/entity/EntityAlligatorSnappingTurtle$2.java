/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.phys.AABB
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import java.util.function.Predicate;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;

class EntityAlligatorSnappingTurtle.2
extends EntityAINearestTarget3D {
    EntityAlligatorSnappingTurtle.2(Mob goalOwnerIn, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, Predicate targetPredicate) {
        super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
    }

    @Override
    protected AABB m_7255_(double targetDistance) {
        return this.f_26135_.m_20191_().m_82377_(0.5, 2.0, 0.5);
    }
}
