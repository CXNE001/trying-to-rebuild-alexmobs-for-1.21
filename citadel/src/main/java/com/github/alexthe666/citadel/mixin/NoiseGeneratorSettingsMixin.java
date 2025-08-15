/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.levelgen.NoiseGeneratorSettings
 *  net.minecraft.world.level.levelgen.SurfaceRules$RuleSource
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.compat.ModCompatBridge;
import com.github.alexthe666.citadel.server.generation.NoiseGeneratorSettingsAccessor;
import com.github.alexthe666.citadel.server.generation.SurfaceRulesManager;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={NoiseGeneratorSettings.class}, priority=300)
public class NoiseGeneratorSettingsMixin
implements NoiseGeneratorSettingsAccessor {
    @Mutable
    @Final
    @Shadow
    private SurfaceRules.RuleSource f_188871_;
    @Unique
    private SurfaceRules.RuleSource unmodifiedSurfaceRule;
    @Unique
    private SurfaceRules.RuleSource citadelSurfaceRule = null;
    @Unique
    private boolean hasModifiedRules = false;
    @Unique
    private boolean saving = false;
    @Unique
    private boolean requiresSurfaceRuleSwapping = false;
    @Unique
    private SurfaceRules.RuleSource swapSurfaceRule = null;

    @Inject(method={"surfaceRule"}, at={@At(value="HEAD")}, cancellable=true)
    private void surfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir) {
        if (!(this.hasModifiedRules || this.saving || ModCompatBridge.usingTerrablender())) {
            this.unmodifiedSurfaceRule = this.f_188871_;
            if (SurfaceRulesManager.hasOverworldModifications()) {
                this.requiresSurfaceRuleSwapping = true;
                this.f_188871_ = this.citadelSurfaceRule = SurfaceRulesManager.mergeOverworldRules(this.f_188871_);
            } else {
                Citadel.LOGGER.info("vanilla surface rule behavior unchanged");
                this.requiresSurfaceRuleSwapping = false;
            }
            this.hasModifiedRules = true;
        }
        if (this.hasModifiedRules && this.requiresSurfaceRuleSwapping) {
            cir.setReturnValue((Object)this.f_188871_);
        }
    }

    @Override
    public void onSaveData(boolean saving) {
        this.saving = saving;
        if (!ModCompatBridge.usingTerrablender() && this.requiresSurfaceRuleSwapping && this.hasModifiedRules) {
            if (saving) {
                this.swapSurfaceRule = this.f_188871_;
                this.f_188871_ = this.unmodifiedSurfaceRule;
                Citadel.LOGGER.debug("saving unmodified surface rules as type {}", (Object)this.f_188871_.getClass().getSimpleName());
            } else {
                this.f_188871_ = this.swapSurfaceRule == null ? this.citadelSurfaceRule : this.swapSurfaceRule;
                Citadel.LOGGER.debug("modified surface rules to type {}", (Object)this.f_188871_.getClass().getSimpleName());
            }
        }
    }
}
