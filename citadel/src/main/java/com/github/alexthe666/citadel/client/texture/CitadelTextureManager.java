/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.NativeImage
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.citadel.client.texture;

import com.github.alexthe666.citadel.client.texture.ColorMappedTexture;
import com.github.alexthe666.citadel.client.texture.VideoFrameTexture;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

public class CitadelTextureManager {
    public static ResourceLocation getColorMappedTexture(ResourceLocation textureLoc, int[] colors) {
        return CitadelTextureManager.getColorMappedTexture(textureLoc, textureLoc, colors);
    }

    public static ResourceLocation getColorMappedTexture(ResourceLocation namespace, ResourceLocation textureLoc, int[] colors) {
        TextureManager textureManager = Minecraft.m_91087_().m_91097_();
        AbstractTexture abstracttexture = textureManager.m_174786_(namespace, (AbstractTexture)MissingTextureAtlasSprite.m_118080_());
        if (abstracttexture == MissingTextureAtlasSprite.m_118080_()) {
            textureManager.m_118495_(namespace, (AbstractTexture)new ColorMappedTexture(textureLoc, colors));
        }
        return namespace;
    }

    public static VideoFrameTexture getVideoTexture(ResourceLocation namespace, int defaultWidth, int defaultHeight) {
        TextureManager textureManager = Minecraft.m_91087_().m_91097_();
        Object abstracttexture = textureManager.m_174786_(namespace, (AbstractTexture)MissingTextureAtlasSprite.m_118080_());
        if (abstracttexture == MissingTextureAtlasSprite.m_118080_()) {
            abstracttexture = new VideoFrameTexture(new NativeImage(defaultWidth, defaultHeight, false));
            textureManager.m_118495_(namespace, abstracttexture);
        }
        return abstracttexture instanceof VideoFrameTexture ? (VideoFrameTexture)((Object)abstracttexture) : null;
    }
}
