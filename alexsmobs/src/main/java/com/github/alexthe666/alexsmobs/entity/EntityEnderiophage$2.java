/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import java.util.function.Predicate;
import net.minecraft.world.entity.Mob;

class EntityEnderiophage.2
extends EntityAINearestTarget3D {
    EntityEnderiophage.2(Mob goalOwnerIn, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, Predicate targetPredicate) {
        super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
    }

    public boolean m_8036_() {
        return !EntityEnderiophage.this.isMissingEye() && EntityEnderiophage.this.fleeAfterStealTime == 0 && super.m_8036_();
    }

    public boolean m_8045_() {
        return !EntityEnderiophage.this.isMissingEye() && super.m_8045_();
    }
}
