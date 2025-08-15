/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.SurfaceRules$Context
 *  net.minecraft.world.level.levelgen.SurfaceRules$SurfaceRule
 */
package com.github.alexthe666.citadel.server.generation;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;

record CitadelSurfaceRuleWrapper.CitadelSurfaceRule(SurfaceRules.Context context, SurfaceRules.SurfaceRule vanillaRule, SurfaceRules.SurfaceRule citadelRule) implements SurfaceRules.SurfaceRule
{
    public BlockState m_183550_(int x, int y, int z) {
        BlockState citadelState = this.citadelRule.m_183550_(x, y, z);
        return citadelState == null ? this.vanillaRule.m_183550_(x, y, z) : citadelState;
    }
}
