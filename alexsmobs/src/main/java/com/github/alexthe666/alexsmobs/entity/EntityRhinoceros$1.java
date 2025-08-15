/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.raid.Raider
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.function.Predicate;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.raid.Raider;

class EntityRhinoceros.1
extends NearestAttackableTargetGoal<Raider> {
    EntityRhinoceros.1(Mob p_26053_, Class p_26054_, int p_26055_, boolean p_26056_, boolean p_26057_, Predicate p_26058_) {
        super(p_26053_, p_26054_, p_26055_, p_26056_, p_26057_, p_26058_);
    }

    public boolean m_8036_() {
        return super.m_8036_() && !EntityRhinoceros.this.m_6162_();
    }
}
