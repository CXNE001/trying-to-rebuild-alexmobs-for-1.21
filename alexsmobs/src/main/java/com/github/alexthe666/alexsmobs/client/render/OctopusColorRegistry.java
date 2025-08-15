/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class OctopusColorRegistry {
    public static final BlockState FALLBACK_BLOCK = Blocks.f_49992_.m_49966_();
    public static Object2IntMap<String> TEXTURES_TO_COLOR = new Object2IntOpenHashMap();

    public static int getBlockColor(BlockState stack) {
        String blockName = stack.toString();
        if (TEXTURES_TO_COLOR.containsKey((Object)blockName)) {
            return TEXTURES_TO_COLOR.getInt((Object)blockName);
        }
        int colorizer = -1;
        try {
            colorizer = Minecraft.m_91087_().m_91298_().m_92577_(stack, null, null, 0);
        }
        catch (Exception e) {
            AlexsMobs.LOGGER.warn("Another mod did not use block colorizers correctly.");
        }
        int color = 0xFFFFFF;
        if (colorizer == -1) {
            Object texture = null;
            try {
                Color texColour = OctopusColorRegistry.getAverageColour(OctopusColorRegistry.getTextureAtlas(stack));
                color = texColour.getRGB();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            color = colorizer;
        }
        TEXTURES_TO_COLOR.put((Object)blockName, color);
        return color;
    }

    private static Color getAverageColour(TextureAtlasSprite image) {
        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;
        float count = 0.0f;
        int uMax = image.m_245424_().m_246492_();
        int vMax = image.m_245424_().m_245330_();
        for (float i = 0.0f; i < (float)uMax; i += 1.0f) {
            for (float j = 0.0f; j < (float)vMax; j += 1.0f) {
                int alpha = image.getPixelRGBA(0, (int)i, (int)j) >> 24 & 0xFF;
                if (alpha == 0) continue;
                red += (float)(image.getPixelRGBA(0, (int)i, (int)j) >> 0 & 0xFF);
                green += (float)(image.getPixelRGBA(0, (int)i, (int)j) >> 8 & 0xFF);
                blue += (float)(image.getPixelRGBA(0, (int)i, (int)j) >> 16 & 0xFF);
                count += 1.0f;
            }
        }
        return new Color((int)(red / count), (int)(green / count), (int)(blue / count));
    }

    private static TextureAtlasSprite getTextureAtlas(BlockState state) {
        return Minecraft.m_91087_().m_91289_().m_110907_().m_110893_(state).m_6160_();
    }
}
