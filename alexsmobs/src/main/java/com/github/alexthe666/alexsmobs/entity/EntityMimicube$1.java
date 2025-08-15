/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EquipmentSlot
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.EquipmentSlot;

static class EntityMimicube.1 {
    static final /* synthetic */ int[] $SwitchMap$net$minecraft$world$entity$EquipmentSlot;

    static {
        $SwitchMap$net$minecraft$world$entity$EquipmentSlot = new int[EquipmentSlot.values().length];
        try {
            EntityMimicube.1.$SwitchMap$net$minecraft$world$entity$EquipmentSlot[EquipmentSlot.HEAD.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            EntityMimicube.1.$SwitchMap$net$minecraft$world$entity$EquipmentSlot[EquipmentSlot.MAINHAND.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            EntityMimicube.1.$SwitchMap$net$minecraft$world$entity$EquipmentSlot[EquipmentSlot.OFFHAND.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
