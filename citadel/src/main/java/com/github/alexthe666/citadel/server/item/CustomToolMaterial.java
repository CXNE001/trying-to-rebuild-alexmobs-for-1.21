/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.citadel.server.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class CustomToolMaterial
implements Tier {
    private final String name;
    private final int harvestLevel;
    private final int durability;
    private final float damage;
    private final float speed;
    private final int enchantability;
    private Ingredient ingredient = null;

    public CustomToolMaterial(String name, int harvestLevel, int durability, float damage, float speed, int enchantability) {
        this.name = name;
        this.harvestLevel = harvestLevel;
        this.durability = durability;
        this.damage = damage;
        this.speed = speed;
        this.enchantability = enchantability;
    }

    public String getName() {
        return this.name;
    }

    public int m_6609_() {
        return this.durability;
    }

    public float m_6624_() {
        return this.speed;
    }

    public float m_6631_() {
        return this.damage;
    }

    public int m_6604_() {
        return this.harvestLevel;
    }

    public int m_6601_() {
        return this.enchantability;
    }

    public Ingredient m_6282_() {
        return this.ingredient == null ? Ingredient.f_43901_ : this.ingredient;
    }

    public void setRepairMaterial(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
