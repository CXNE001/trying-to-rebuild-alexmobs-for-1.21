/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance
 *  net.minecraft.advancements.critereon.ConstructBeaconTrigger$TriggerInstance
 *  net.minecraft.advancements.critereon.ContextAwarePredicate
 *  net.minecraft.advancements.critereon.MinMaxBounds$Ints
 *  net.minecraft.advancements.critereon.SerializationContext
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.misc;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ConstructBeaconTrigger;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.resources.ResourceLocation;

public static class AMAdvancementTrigger.Instance
extends AbstractCriterionTriggerInstance {
    public AMAdvancementTrigger.Instance(ContextAwarePredicate p_i231507_1_, ResourceLocation res) {
        super(res, p_i231507_1_);
    }

    public static ConstructBeaconTrigger.TriggerInstance forLevel(MinMaxBounds.Ints p_203912_0_) {
        return new ConstructBeaconTrigger.TriggerInstance(ContextAwarePredicate.f_285567_, p_203912_0_);
    }

    public JsonObject m_7683_(SerializationContext p_230240_1_) {
        JsonObject lvt_2_1_ = super.m_7683_(p_230240_1_);
        return lvt_2_1_;
    }
}
