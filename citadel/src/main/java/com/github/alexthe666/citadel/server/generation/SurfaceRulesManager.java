/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.levelgen.SurfaceRules
 *  net.minecraft.world.level.levelgen.SurfaceRules$BiomeConditionSource
 *  net.minecraft.world.level.levelgen.SurfaceRules$ConditionSource
 *  net.minecraft.world.level.levelgen.SurfaceRules$RuleSource
 *  net.minecraft.world.level.levelgen.SurfaceRules$SequenceRuleSource
 *  net.minecraft.world.level.levelgen.SurfaceRules$TestRuleSource
 */
package com.github.alexthe666.citadel.server.generation;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.generation.CitadelSurfaceRuleWrapper;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class SurfaceRulesManager {
    private static final List<SurfaceRules.RuleSource> OVERWORLD_REGISTRY = new ArrayList<SurfaceRules.RuleSource>();
    private static final List<SurfaceRules.RuleSource> NETHER_REGISTRY = new ArrayList<SurfaceRules.RuleSource>();
    private static final List<SurfaceRules.RuleSource> END_REGISTRY = new ArrayList<SurfaceRules.RuleSource>();
    private static final List<SurfaceRules.RuleSource> CAVE_REGISTRY = new ArrayList<SurfaceRules.RuleSource>();

    public static void registerOverworldSurfaceRule(SurfaceRules.ConditionSource condition, SurfaceRules.RuleSource rule) {
        SurfaceRulesManager.registerOverworldSurfaceRule(SurfaceRules.m_189394_((SurfaceRules.ConditionSource)condition, (SurfaceRules.RuleSource)rule));
    }

    public static void registerOverworldSurfaceRule(SurfaceRules.RuleSource rule) {
        OVERWORLD_REGISTRY.add(rule);
    }

    @Deprecated
    public static void registerNetherSurfaceRule(SurfaceRules.ConditionSource condition, SurfaceRules.RuleSource rule) {
        SurfaceRulesManager.registerNetherSurfaceRule(SurfaceRules.m_189394_((SurfaceRules.ConditionSource)condition, (SurfaceRules.RuleSource)rule));
    }

    @Deprecated
    public static void registerNetherSurfaceRule(SurfaceRules.RuleSource rule) {
        NETHER_REGISTRY.add(rule);
    }

    @Deprecated
    public static void registerEndSurfaceRule(SurfaceRules.ConditionSource condition, SurfaceRules.RuleSource rule) {
        SurfaceRulesManager.registerEndSurfaceRule(SurfaceRules.m_189394_((SurfaceRules.ConditionSource)condition, (SurfaceRules.RuleSource)rule));
    }

    @Deprecated
    public static void registerEndSurfaceRule(SurfaceRules.RuleSource rule) {
        END_REGISTRY.add(rule);
    }

    @Deprecated
    public static void registerCaveSurfaceRule(SurfaceRules.ConditionSource condition, SurfaceRules.RuleSource rule) {
        SurfaceRulesManager.registerCaveSurfaceRule(SurfaceRules.m_189394_((SurfaceRules.ConditionSource)condition, (SurfaceRules.RuleSource)rule));
    }

    @Deprecated
    public static void registerCaveSurfaceRule(SurfaceRules.RuleSource rule) {
        CAVE_REGISTRY.add(rule);
    }

    public static boolean hasOverworldModifications() {
        return !OVERWORLD_REGISTRY.isEmpty();
    }

    public static SurfaceRules.RuleSource mergeOverworldRules(SurfaceRules.RuleSource rulesIn) {
        Citadel.LOGGER.info("merged {} surface rules with vanilla rule {}", (Object)OVERWORLD_REGISTRY.size(), (Object)rulesIn.getClass().getSimpleName());
        return SurfaceRulesManager.mergeRules(rulesIn, SurfaceRules.m_198272_((SurfaceRules.RuleSource[])((SurfaceRules.RuleSource[])OVERWORLD_REGISTRY.toArray(SurfaceRules.RuleSource[]::new))));
    }

    public static Map<String, SurfaceRules.RuleSource> getOverworldRulesByBiomeForTerrablender(boolean vanilla) {
        HashMap<String, SurfaceRules.RuleSource> map = new HashMap<String, SurfaceRules.RuleSource>();
        for (SurfaceRules.RuleSource ruleSource : OVERWORLD_REGISTRY) {
            SurfaceRules.TestRuleSource testRuleSource;
            SurfaceRules.ConditionSource conditionSource;
            if (!(ruleSource instanceof SurfaceRules.TestRuleSource) || !((conditionSource = (testRuleSource = (SurfaceRules.TestRuleSource)ruleSource).f_189808_()) instanceof SurfaceRules.BiomeConditionSource)) continue;
            SurfaceRules.BiomeConditionSource biomeRule = (SurfaceRules.BiomeConditionSource)conditionSource;
            if (biomeRule.f_189489_.isEmpty()) continue;
            String namespace = ((ResourceKey)biomeRule.f_189489_.get(0)).m_135782_().m_135827_();
            boolean vanillaBiome = namespace.equals("minecraft");
            if (vanilla && vanillaBiome) {
                map.put(namespace, (SurfaceRules.RuleSource)testRuleSource);
            }
            if (vanilla || vanillaBiome) continue;
            if (map.containsKey(namespace)) {
                SurfaceRules.RuleSource ruleSource1 = (SurfaceRules.RuleSource)map.get(namespace);
                if (ruleSource1 instanceof SurfaceRules.SequenceRuleSource) {
                    SurfaceRules.SequenceRuleSource sequenceRuleSource = (SurfaceRules.SequenceRuleSource)ruleSource1;
                    ImmutableList.Builder ruleSources = ImmutableList.builder();
                    ruleSources.addAll((Iterable)sequenceRuleSource.f_189697_());
                    ruleSources.add((Object)testRuleSource);
                    map.put(namespace, SurfaceRules.m_198272_((SurfaceRules.RuleSource[])((SurfaceRules.RuleSource[])ruleSources.build().toArray(SurfaceRules.RuleSource[]::new))));
                    continue;
                }
                map.put(namespace, SurfaceRules.m_198272_((SurfaceRules.RuleSource[])new SurfaceRules.RuleSource[]{ruleSource1, testRuleSource}));
                continue;
            }
            map.put(namespace, (SurfaceRules.RuleSource)testRuleSource);
        }
        return map;
    }

    private static SurfaceRules.RuleSource mergeRules(SurfaceRules.RuleSource prev, SurfaceRules.RuleSource toMerge) {
        CitadelSurfaceRuleWrapper result;
        if (prev instanceof CitadelSurfaceRuleWrapper) {
            CitadelSurfaceRuleWrapper wrapper = (CitadelSurfaceRuleWrapper)prev;
            result = new CitadelSurfaceRuleWrapper(wrapper.vanillaRules(), toMerge);
        } else {
            result = new CitadelSurfaceRuleWrapper(prev, toMerge);
        }
        Citadel.LOGGER.debug("surface rule recursive depth: {}", (Object)SurfaceRulesManager.calculateSurfaceRuleDepth(result, 1));
        return result;
    }

    private static int calculateSurfaceRuleDepth(SurfaceRules.RuleSource source, int depthIn) {
        if (source instanceof SurfaceRules.SequenceRuleSource) {
            SurfaceRules.SequenceRuleSource sequenceRuleSource = (SurfaceRules.SequenceRuleSource)source;
            int j = depthIn;
            for (SurfaceRules.RuleSource ruleSource : sequenceRuleSource.f_189697_()) {
                j = Math.max(SurfaceRulesManager.calculateSurfaceRuleDepth(ruleSource, depthIn + 1), j);
            }
            return j;
        }
        if (source instanceof SurfaceRules.TestRuleSource) {
            SurfaceRules.TestRuleSource testRuleSource = (SurfaceRules.TestRuleSource)source;
            depthIn = Math.max(SurfaceRulesManager.calculateSurfaceRuleDepth(testRuleSource.f_189809_(), depthIn + 1), depthIn);
        } else if (source instanceof CitadelSurfaceRuleWrapper) {
            CitadelSurfaceRuleWrapper citadelSurfaceRuleWrapper = (CitadelSurfaceRuleWrapper)source;
            depthIn = Math.max(SurfaceRulesManager.calculateSurfaceRuleDepth(citadelSurfaceRuleWrapper.vanillaRules(), depthIn + 1), depthIn);
        }
        return depthIn;
    }
}
