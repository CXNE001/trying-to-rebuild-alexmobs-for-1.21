/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.ArmorMaterial
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.citadel.server.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class CustomArmorMaterial
implements ArmorMaterial {
    private final String name;
    private final int durability;
    private final int[] damageReduction;
    private final int encantability;
    private final SoundEvent sound;
    private final float toughness;
    private Ingredient ingredient = null;
    public float knockbackResistance = 0.0f;

    public CustomArmorMaterial(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, float toughness, float knockbackResistance) {
        this.name = name;
        this.durability = durability;
        this.damageReduction = damageReduction;
        this.encantability = encantability;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    public int m_266425_(ArmorItem.Type slotIn) {
        return this.durability;
    }

    public int m_7366_(ArmorItem.Type slotIn) {
        return this.damageReduction[slotIn.ordinal()];
    }

    public int m_6646_() {
        return this.encantability;
    }

    public SoundEvent m_7344_() {
        return this.sound;
    }

    public Ingredient m_6230_() {
        return this.ingredient == null ? Ingredient.f_43901_ : this.ingredient;
    }

    public void setRepairMaterial(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String m_6082_() {
        return this.name;
    }

    public float m_6651_() {
        return this.toughness;
    }

    public float m_6649_() {
        return this.knockbackResistance;
    }
}
