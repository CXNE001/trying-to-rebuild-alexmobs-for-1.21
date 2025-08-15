/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityFly;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

class EntityFly.AnnoyZombieGoal.1
implements Predicate<Entity> {
    final /* synthetic */ EntityFly val$this$0;

    EntityFly.AnnoyZombieGoal.1() {
        this.val$this$0 = entityFly;
    }

    public boolean apply(@Nullable Entity e) {
        return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.FLY_TARGETS) && (!(e instanceof LivingEntity) || (double)((LivingEntity)e).m_21223_() >= 2.0);
    }
}
