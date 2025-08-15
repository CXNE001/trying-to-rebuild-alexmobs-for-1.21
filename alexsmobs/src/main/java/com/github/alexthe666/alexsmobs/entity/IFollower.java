/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;

public interface IFollower {
    public boolean shouldFollow();

    default public void followEntity(TamableAnimal tameable, LivingEntity owner, double followSpeed) {
        tameable.f_21344_.m_5624_((Entity)owner, followSpeed);
    }
}
