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

class EntityCaiman.3
extends EntityAINearestTarget3D {
    EntityCaiman.3(Mob goalOwnerIn, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, Predicate targetPredicate) {
        super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
    }

    public boolean m_8036_() {
        return !EntityCaiman.this.m_6162_() && !EntityCaiman.this.m_21824_() && super.m_8036_();
    }
}
