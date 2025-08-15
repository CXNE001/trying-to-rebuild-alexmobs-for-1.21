/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.util.KeyDispatchDataCodec
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.SurfaceRules$Context
 *  net.minecraft.world.level.levelgen.SurfaceRules$RuleSource
 *  net.minecraft.world.level.levelgen.SurfaceRules$SurfaceRule
 */
package com.github.alexthe666.citadel.server.generation;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;

public record CitadelSurfaceRuleWrapper(SurfaceRules.RuleSource vanillaRules, SurfaceRules.RuleSource citadelRules) implements SurfaceRules.RuleSource
{
    public static final KeyDispatchDataCodec<CitadelSurfaceRuleWrapper> CODEC = KeyDispatchDataCodec.m_216238_((MapCodec)RecordCodecBuilder.mapCodec(builder -> builder.group((App)SurfaceRules.RuleSource.f_189682_.fieldOf("vanilla_rules").forGetter(CitadelSurfaceRuleWrapper::vanillaRules), (App)SurfaceRules.RuleSource.f_189682_.fieldOf("citadel_rules").forGetter(CitadelSurfaceRuleWrapper::citadelRules)).apply((Applicative)builder, CitadelSurfaceRuleWrapper::new)));

    public KeyDispatchDataCodec<? extends SurfaceRules.RuleSource> m_213795_() {
        return CODEC;
    }

    public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
        if (this.vanillaRules == null) {
            return (SurfaceRules.SurfaceRule)this.citadelRules.apply((Object)context);
        }
        if (this.citadelRules == null) {
            return (SurfaceRules.SurfaceRule)this.vanillaRules.apply((Object)context);
        }
        return new CitadelSurfaceRule(context, (SurfaceRules.SurfaceRule)this.vanillaRules.apply((Object)context), (SurfaceRules.SurfaceRule)this.citadelRules.apply((Object)context));
    }

    record CitadelSurfaceRule(SurfaceRules.Context context, SurfaceRules.SurfaceRule vanillaRule, SurfaceRules.SurfaceRule citadelRule) implements SurfaceRules.SurfaceRule
    {
        public BlockState m_183550_(int x, int y, int z) {
            BlockState citadelState = this.citadelRule.m_183550_(x, y, z);
            return citadelState == null ? this.vanillaRule.m_183550_(x, y, z) : citadelState;
        }
    }
}
