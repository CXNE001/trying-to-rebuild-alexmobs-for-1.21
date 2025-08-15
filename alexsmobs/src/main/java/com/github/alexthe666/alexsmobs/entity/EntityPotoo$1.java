/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.AdvancedPathNavigate$MovementType
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AdvancedPathNavigateNoTeleport;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.AdvancedPathNavigate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

class EntityPotoo.1
extends AdvancedPathNavigateNoTeleport {
    EntityPotoo.1(Mob entity, Level world, AdvancedPathNavigate.MovementType type, boolean climbing, boolean wide) {
        super(entity, world, type, climbing, wide);
    }

    public boolean m_6342_(BlockPos pos) {
        return !this.f_26495_.m_8055_(pos.m_6625_(2)).m_60795_();
    }
}
