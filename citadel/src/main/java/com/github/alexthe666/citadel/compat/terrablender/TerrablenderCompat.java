/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.levelgen.SurfaceRules$RuleSource
 *  terrablender.api.SurfaceRuleManager
 *  terrablender.api.SurfaceRuleManager$RuleCategory
 *  terrablender.api.SurfaceRuleManager$RuleStage
 */
package com.github.alexthe666.citadel.compat.terrablender;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.generation.SurfaceRulesManager;
import java.util.Map;
import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.SurfaceRuleManager;

public class TerrablenderCompat {
    public static void setup() {
        Map<String, SurfaceRules.RuleSource> vanillaBiomeRules = SurfaceRulesManager.getOverworldRulesByBiomeForTerrablender(true);
        for (Map.Entry<String, SurfaceRules.RuleSource> entry : vanillaBiomeRules.entrySet()) {
            SurfaceRuleManager.addToDefaultSurfaceRulesAtStage((SurfaceRuleManager.RuleCategory)SurfaceRuleManager.RuleCategory.OVERWORLD, (SurfaceRuleManager.RuleStage)SurfaceRuleManager.RuleStage.BEFORE_BEDROCK, (int)0, (SurfaceRules.RuleSource)entry.getValue());
        }
        Citadel.LOGGER.info("Added {} vanilla biome surface rule types via terrablender", (Object)vanillaBiomeRules.size());
        Map<String, SurfaceRules.RuleSource> moddedBiomeRules = SurfaceRulesManager.getOverworldRulesByBiomeForTerrablender(false);
        for (Map.Entry<String, SurfaceRules.RuleSource> entry : moddedBiomeRules.entrySet()) {
            SurfaceRuleManager.addSurfaceRules((SurfaceRuleManager.RuleCategory)SurfaceRuleManager.RuleCategory.OVERWORLD, (String)entry.getKey(), (SurfaceRules.RuleSource)entry.getValue());
        }
        Citadel.LOGGER.info("Added {} modded biome surface rule types via terrablender", (Object)moddedBiomeRules.size());
    }
}
