/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.Comparator;
import net.minecraft.world.entity.Entity;

public record EntityCrow.AIDepositChests.Sorter(Entity theEntity) implements Comparator<Entity>
{
    @Override
    public int compare(Entity p_compare_1_, Entity p_compare_2_) {
        double d0 = this.theEntity.m_20280_(p_compare_1_);
        double d1 = this.theEntity.m_20280_(p_compare_2_);
        return Double.compare(d0, d1);
    }
}
