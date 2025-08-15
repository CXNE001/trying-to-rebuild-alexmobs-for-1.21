/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.advancements.CriterionTrigger
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.misc.AMAdvancementTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceLocation;

public class AMAdvancementTriggerRegistry {
    public static final AMAdvancementTrigger MOSQUITO_SICK = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:mosquito_sick"));
    public static final AMAdvancementTrigger EMU_DODGE = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:emu_dodge"));
    public static final AMAdvancementTrigger STOMP_LEAFCUTTER_ANTHILL = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:stomp_leafcutter_anthill"));
    public static final AMAdvancementTrigger BALD_EAGLE_CHALLENGE = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:bald_eagle_challenge"));
    public static final AMAdvancementTrigger VOID_WORM_SUMMON = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:void_worm_summon"));
    public static final AMAdvancementTrigger VOID_WORM_SPLIT = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:void_worm_split"));
    public static final AMAdvancementTrigger VOID_WORM_SLAY_HEAD = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:void_worm_kill"));
    public static final AMAdvancementTrigger SEAGULL_STEAL = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:seagull_steal"));
    public static final AMAdvancementTrigger LAVIATHAN_FOUR_PASSENGERS = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:laviathan_four_passengers"));
    public static final AMAdvancementTrigger TRANSMUTE_1000_ITEMS = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:transmute_1000_items"));
    public static final AMAdvancementTrigger UNDERMINE_UNDERMINER = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:undermine_underminer"));
    public static final AMAdvancementTrigger ELEPHANT_SWAG = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:elephant_swag"));
    public static final AMAdvancementTrigger SKUNK_SPRAY = new AMAdvancementTrigger(new ResourceLocation("alexsmobs:skunk_spray"));

    public static void init() {
        CriteriaTriggers.m_10595_((CriterionTrigger)MOSQUITO_SICK);
        CriteriaTriggers.m_10595_((CriterionTrigger)EMU_DODGE);
        CriteriaTriggers.m_10595_((CriterionTrigger)STOMP_LEAFCUTTER_ANTHILL);
        CriteriaTriggers.m_10595_((CriterionTrigger)BALD_EAGLE_CHALLENGE);
        CriteriaTriggers.m_10595_((CriterionTrigger)VOID_WORM_SUMMON);
        CriteriaTriggers.m_10595_((CriterionTrigger)VOID_WORM_SPLIT);
        CriteriaTriggers.m_10595_((CriterionTrigger)VOID_WORM_SLAY_HEAD);
        CriteriaTriggers.m_10595_((CriterionTrigger)SEAGULL_STEAL);
        CriteriaTriggers.m_10595_((CriterionTrigger)LAVIATHAN_FOUR_PASSENGERS);
        CriteriaTriggers.m_10595_((CriterionTrigger)TRANSMUTE_1000_ITEMS);
        CriteriaTriggers.m_10595_((CriterionTrigger)UNDERMINE_UNDERMINER);
        CriteriaTriggers.m_10595_((CriterionTrigger)ELEPHANT_SWAG);
        CriteriaTriggers.m_10595_((CriterionTrigger)SKUNK_SPRAY);
    }
}
