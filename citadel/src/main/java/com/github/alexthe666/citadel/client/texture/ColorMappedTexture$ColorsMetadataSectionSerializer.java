/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.metadata.MetadataSectionSerializer
 *  net.minecraft.util.GsonHelper
 */
package com.github.alexthe666.citadel.client.texture;

import com.github.alexthe666.citadel.client.texture.ColorMappedTexture;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.util.GsonHelper;

private static class ColorMappedTexture.ColorsMetadataSectionSerializer
implements MetadataSectionSerializer<ColorMappedTexture.ColorsMetadataSection> {
    private ColorMappedTexture.ColorsMetadataSectionSerializer() {
    }

    public ColorMappedTexture.ColorsMetadataSection fromJson(JsonObject json) {
        return new ColorMappedTexture.ColorsMetadataSection(new ResourceLocation(GsonHelper.m_13906_((JsonObject)json, (String)"color_ramp")));
    }

    public String m_7991_() {
        return "colors";
    }
}
