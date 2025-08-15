/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.citadel.client.texture;

import com.github.alexthe666.citadel.client.texture.ColorMappedTexture;
import net.minecraft.resources.ResourceLocation;

private static class ColorMappedTexture.ColorsMetadataSection {
    public static final ColorMappedTexture.ColorsMetadataSectionSerializer SERIALIZER = new ColorMappedTexture.ColorsMetadataSectionSerializer();
    private final ResourceLocation colorRamp;

    public ColorMappedTexture.ColorsMetadataSection(ResourceLocation colorRamp) {
        this.colorRamp = colorRamp;
    }

    private boolean areColorsEqual(int color1, int color2) {
        int r1 = color1 >> 16 & 0xFF;
        int g1 = color1 >> 8 & 0xFF;
        int b1 = color1 & 0xFF;
        int r2 = color2 >> 16 & 0xFF;
        int g2 = color2 >> 8 & 0xFF;
        int b2 = color2 & 0xFF;
        return r1 == r2 && g1 == g2 && b1 == b2;
    }

    public ResourceLocation getColorRamp() {
        return this.colorRamp;
    }
}
