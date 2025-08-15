/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.PathFinder
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityStraddler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

static class EntityStraddler.LavaPathNavigator
extends GroundPathNavigation {
    EntityStraddler.LavaPathNavigator(EntityStraddler p_i231565_1_, Level p_i231565_2_) {
        super((Mob)p_i231565_1_, p_i231565_2_);
    }

    protected PathFinder m_5532_(int p_179679_1_) {
        this.f_26508_ = new WalkNodeEvaluator();
        return new PathFinder(this.f_26508_, p_179679_1_);
    }

    protected boolean m_7367_(BlockPathTypes p_230287_1_) {
        return p_230287_1_ == BlockPathTypes.LAVA || p_230287_1_ == BlockPathTypes.DAMAGE_FIRE || p_230287_1_ == BlockPathTypes.DANGER_FIRE || super.m_7367_(p_230287_1_);
    }

    public boolean m_6342_(BlockPos pos) {
        return this.f_26495_.m_8055_(pos).m_60713_(Blocks.f_49991_) || super.m_6342_(pos);
    }
}
