/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.item.ArrowItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.entity.EntitySharkToothArrow;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemModArrow
extends ArrowItem {
    public ItemModArrow(Item.Properties group) {
        super(group);
    }

    public AbstractArrow m_6394_(Level worldIn, ItemStack stack, LivingEntity shooter) {
        if (this == AMItemRegistry.SHARK_TOOTH_ARROW.get()) {
            EntitySharkToothArrow arrowentity = new EntitySharkToothArrow(worldIn, shooter);
            arrowentity.m_36878_(stack);
            return arrowentity;
        }
        return super.m_6394_(worldIn, stack, shooter);
    }
}
