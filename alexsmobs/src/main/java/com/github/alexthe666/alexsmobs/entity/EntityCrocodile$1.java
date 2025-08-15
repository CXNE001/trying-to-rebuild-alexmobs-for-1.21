/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.entity.Mob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import java.util.function.Predicate;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Mob;

class EntityCrocodile.1
extends EntityAINearestTarget3D {
    EntityCrocodile.1(Mob goalOwnerIn, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, Predicate targetPredicate) {
        super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
    }

    public boolean m_8036_() {
        return !EntityCrocodile.this.m_6162_() && !EntityCrocodile.this.m_21824_() && EntityCrocodile.this.m_9236_().m_46791_() != Difficulty.PEACEFUL && super.m_8036_();
    }
}
