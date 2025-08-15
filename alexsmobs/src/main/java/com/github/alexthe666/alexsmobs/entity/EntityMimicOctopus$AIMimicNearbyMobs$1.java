/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.animal.Pufferfish
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Guardian
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Guardian;

class EntityMimicOctopus.AIMimicNearbyMobs.1
implements Predicate<Entity> {
    final /* synthetic */ EntityMimicOctopus val$this$0;

    EntityMimicOctopus.AIMimicNearbyMobs.1() {
        this.val$this$0 = entityMimicOctopus;
    }

    public boolean apply(@Nullable Entity e) {
        return e.m_6084_() && (e instanceof Creeper || e instanceof Guardian || e instanceof Pufferfish);
    }
}
