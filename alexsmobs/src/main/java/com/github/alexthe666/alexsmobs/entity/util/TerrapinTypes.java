/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.RandomSource
 */
package com.github.alexthe666.alexsmobs.entity.util;

import java.util.Locale;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public enum TerrapinTypes {
    GREEN(new ResourceLocation("alexsmobs:textures/entity/terrapin/terrapin_green.png"), 8.0f),
    BLACK(new ResourceLocation("alexsmobs:textures/entity/terrapin/terrapin_black.png"), 11.0f),
    BROWN(new ResourceLocation("alexsmobs:textures/entity/terrapin/terrapin_brown.png"), 10.0f),
    KOOPA(new ResourceLocation("alexsmobs:textures/entity/terrapin/terrapin_koopa.png"), 0.05f),
    PAINTED(new ResourceLocation("alexsmobs:textures/entity/terrapin/terrapin_painted.png"), 8.0f),
    RED_EARED(new ResourceLocation("alexsmobs:textures/entity/terrapin/terrapin_red_eared.png"), 13.0f),
    OVERLAY(new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_with_overlays.png"), 9.0f);

    private final ResourceLocation texture;
    private final float weight;
    private static final int[] DEFAULT_COLORS;

    private TerrapinTypes(ResourceLocation texture, float weight) {
        this.texture = texture;
        this.weight = weight;
    }

    public static TerrapinTypes getRandomType(RandomSource random) {
        float totalWeight = 0.0f;
        for (TerrapinTypes type : TerrapinTypes.values()) {
            totalWeight += type.weight;
        }
        int randomIndex = -1;
        double randomWeightSample = random.m_188500_() * (double)totalWeight;
        for (int i = 0; i < TerrapinTypes.values().length; ++i) {
            if (!((randomWeightSample -= (double)TerrapinTypes.values()[i].weight) <= 0.0)) continue;
            randomIndex = i;
            break;
        }
        return TerrapinTypes.values()[randomIndex];
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public static int generateRandomColor(RandomSource random) {
        return DEFAULT_COLORS[random.m_188503_(DEFAULT_COLORS.length) % DEFAULT_COLORS.length];
    }

    public String getTranslationName() {
        return "entity.alexsmobs.terrapin.variant_" + this.name().toLowerCase(Locale.ROOT);
    }

    static {
        DEFAULT_COLORS = new int[]{11225397, 12756521, 0x363533, 15460833, 6333298, 12753778};
    }
}
