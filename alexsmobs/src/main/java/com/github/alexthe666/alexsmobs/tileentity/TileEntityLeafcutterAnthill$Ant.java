/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 */
package com.github.alexthe666.alexsmobs.tileentity;

import net.minecraft.nbt.CompoundTag;

static class TileEntityLeafcutterAnthill.Ant {
    private final CompoundTag entityData;
    private final int minOccupationTicks;
    private int ticksInHive;
    private final boolean queen;

    private TileEntityLeafcutterAnthill.Ant(CompoundTag p_i225767_1_, int p_i225767_2_, int p_i225767_3_, boolean queen) {
        p_i225767_1_.m_128473_("UUID");
        this.entityData = p_i225767_1_;
        this.ticksInHive = p_i225767_2_;
        this.minOccupationTicks = p_i225767_3_;
        this.queen = queen;
    }
}
