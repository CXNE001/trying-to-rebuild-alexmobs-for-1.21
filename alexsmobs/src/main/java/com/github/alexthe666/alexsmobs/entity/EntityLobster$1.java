/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticPathNavigator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

class EntityLobster.1
extends SemiAquaticPathNavigator {
    EntityLobster.1(Mob entitylivingIn, Level worldIn) {
        super(entitylivingIn, worldIn);
    }

    @Override
    public boolean m_6342_(BlockPos pos) {
        return this.f_26495_.m_8055_(pos).m_60819_().m_76178_();
    }
}
