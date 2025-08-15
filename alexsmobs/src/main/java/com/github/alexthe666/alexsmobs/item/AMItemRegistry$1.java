/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Position
 *  net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntitySharkToothArrow;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

static class AMItemRegistry.1
extends AbstractProjectileDispenseBehavior {
    AMItemRegistry.1() {
    }

    protected Projectile m_6895_(Level worldIn, Position position, ItemStack stackIn) {
        EntitySharkToothArrow entityarrow = new EntitySharkToothArrow((EntityType)AMEntityRegistry.SHARK_TOOTH_ARROW.get(), position.m_7096_(), position.m_7098_(), position.m_7094_(), worldIn);
        entityarrow.f_36705_ = AbstractArrow.Pickup.ALLOWED;
        return entityarrow;
    }
}
