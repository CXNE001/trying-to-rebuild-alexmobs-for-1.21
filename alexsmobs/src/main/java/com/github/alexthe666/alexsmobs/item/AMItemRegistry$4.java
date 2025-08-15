/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Position
 *  net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.entity.EntityEmuEgg;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

static class AMItemRegistry.4
extends AbstractProjectileDispenseBehavior {
    AMItemRegistry.4() {
    }

    protected Projectile m_6895_(Level worldIn, Position position, ItemStack stackIn) {
        EntityEmuEgg entityarrow = new EntityEmuEgg(worldIn, position.m_7096_(), position.m_7098_(), position.m_7094_());
        return entityarrow;
    }
}
