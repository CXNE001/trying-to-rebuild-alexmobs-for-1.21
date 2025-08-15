/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  com.mojang.math.Axis
 *  net.minecraft.Util
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.resources.MobEffectTextureManager
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.citadel.client;

import com.github.alexthe666.citadel.Citadel;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Matrix4f;

public class CitadelItemstackRenderer
extends BlockEntityWithoutLevelRenderer {
    private static final ResourceLocation DEFAULT_ICON_TEXTURE = new ResourceLocation("citadel:textures/gui/book/icon_default.png");
    private static final Map<String, ResourceLocation> LOADED_ICONS = new HashMap<String, ResourceLocation>();
    private static List<MobEffect> mobEffectList = null;

    public CitadelItemstackRenderer() {
        super(null, null);
    }

    public void m_108829_(ItemStack stack, ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        int id;
        float partialTicks = Minecraft.m_91087_().m_91296_();
        float ticksExisted = (float)Util.m_137550_() / 50.0f + partialTicks;
        int n = id = Minecraft.m_91087_().f_91074_ == null ? 0 : Minecraft.m_91087_().f_91074_.m_19879_();
        if (stack.m_41720_() == Citadel.FANCY_ITEM.get()) {
            Random random = new Random();
            boolean animateAnyways = false;
            ItemStack toRender = null;
            if (stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplayItem")) {
                String displayID = stack.m_41783_().m_128461_("DisplayItem");
                toRender = new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation(displayID)));
                if (stack.m_41783_().m_128441_("DisplayItemNBT")) {
                    try {
                        toRender.m_41751_(stack.m_41783_().m_128469_("DisplayItemNBT"));
                    }
                    catch (Exception e) {
                        toRender = new ItemStack((ItemLike)Items.f_42127_);
                    }
                }
            }
            if (toRender == null) {
                animateAnyways = true;
                toRender = new ItemStack((ItemLike)Items.f_42127_);
            }
            matrixStack.m_85836_();
            matrixStack.m_252880_(0.5f, 0.5f, 0.5f);
            if (stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplayShake") && stack.m_41783_().m_128471_("DisplayShake")) {
                matrixStack.m_252880_((random.nextFloat() - 0.5f) * 0.1f, (random.nextFloat() - 0.5f) * 0.1f, (random.nextFloat() - 0.5f) * 0.1f);
            }
            if (animateAnyways || stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplayBob") && stack.m_41783_().m_128471_("DisplayBob")) {
                matrixStack.m_252880_(0.0f, 0.05f + 0.1f * Mth.m_14031_((float)(0.3f * ticksExisted)), 0.0f);
            }
            if (stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplaySpin") && stack.m_41783_().m_128471_("DisplaySpin")) {
                matrixStack.m_252781_(Axis.f_252436_.m_252977_(6.0f * ticksExisted));
            }
            if (animateAnyways || stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplayZoom") && stack.m_41783_().m_128471_("DisplayZoom")) {
                float scale = (float)(1.0 + (double)0.15f * (Math.sin(ticksExisted * 0.3f) + 1.0));
                matrixStack.m_85841_(scale, scale, scale);
            }
            if (stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplayScale") && stack.m_41783_().m_128457_("DisplayScale") != 1.0f) {
                float scale = stack.m_41783_().m_128457_("DisplayScale");
                matrixStack.m_85841_(scale, scale, scale);
            }
            Minecraft.m_91087_().m_91291_().m_269128_(toRender, transformType, combinedLight, combinedOverlay, matrixStack, buffer, null, id);
            matrixStack.m_85849_();
        }
        if (stack.m_41720_() == Citadel.EFFECT_ITEM.get()) {
            MobEffect effect;
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            if (stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplayEffect")) {
                String displayID = stack.m_41783_().m_128461_("DisplayEffect");
                effect = (MobEffect)ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(displayID));
            } else {
                if (mobEffectList == null) {
                    mobEffectList = ForgeRegistries.MOB_EFFECTS.getValues().stream().toList();
                }
                int size = mobEffectList.size();
                int time = (int)(Util.m_137550_() / 500L);
                effect = mobEffectList.get(time % size);
                if (effect == null) {
                    effect = MobEffects.f_19596_;
                }
            }
            if (effect == null) {
                effect = MobEffects.f_19596_;
            }
            MobEffectTextureManager potionspriteuploader = Minecraft.m_91087_().m_91306_();
            matrixStack.m_85836_();
            matrixStack.m_252880_(0.0f, 0.0f, 0.5f);
            TextureAtlasSprite sprite = potionspriteuploader.m_118732_(effect);
            RenderSystem.setShader(GameRenderer::m_172817_);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.setShaderTexture((int)0, (ResourceLocation)sprite.m_247685_());
            Tesselator tessellator = Tesselator.m_85913_();
            BufferBuilder bufferbuilder = tessellator.m_85915_();
            bufferbuilder.m_166779_(VertexFormat.Mode.QUADS, DefaultVertexFormat.f_85813_);
            Matrix4f mx = matrixStack.m_85850_().m_252922_();
            int br = 255;
            bufferbuilder.m_252986_(mx, 1.0f, 1.0f, 0.0f).m_7421_(sprite.m_118410_(), sprite.m_118411_()).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            bufferbuilder.m_252986_(mx, 0.0f, 1.0f, 0.0f).m_7421_(sprite.m_118409_(), sprite.m_118411_()).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            bufferbuilder.m_252986_(mx, 0.0f, 0.0f, 0.0f).m_7421_(sprite.m_118409_(), sprite.m_118412_()).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            bufferbuilder.m_252986_(mx, 1.0f, 0.0f, 0.0f).m_7421_(sprite.m_118410_(), sprite.m_118412_()).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            tessellator.m_85914_();
            matrixStack.m_85849_();
        }
        if (stack.m_41720_() == Citadel.ICON_ITEM.get()) {
            ResourceLocation texture = DEFAULT_ICON_TEXTURE;
            if (stack.m_41783_() != null && stack.m_41783_().m_128441_("IconLocation")) {
                String iconLocationStr = stack.m_41783_().m_128461_("IconLocation");
                if (LOADED_ICONS.containsKey(iconLocationStr)) {
                    texture = LOADED_ICONS.get(iconLocationStr);
                } else {
                    texture = new ResourceLocation(iconLocationStr);
                    LOADED_ICONS.put(iconLocationStr, texture);
                }
            }
            matrixStack.m_85836_();
            matrixStack.m_252880_(0.0f, 0.0f, 0.5f);
            RenderSystem.setShader(GameRenderer::m_172817_);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.setShaderTexture((int)0, (ResourceLocation)texture);
            Tesselator tessellator = Tesselator.m_85913_();
            BufferBuilder bufferbuilder = tessellator.m_85915_();
            bufferbuilder.m_166779_(VertexFormat.Mode.QUADS, DefaultVertexFormat.f_85813_);
            Matrix4f mx = matrixStack.m_85850_().m_252922_();
            int br = 255;
            bufferbuilder.m_252986_(mx, 1.0f, 1.0f, 0.0f).m_7421_(1.0f, 0.0f).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            bufferbuilder.m_252986_(mx, 0.0f, 1.0f, 0.0f).m_7421_(0.0f, 0.0f).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            bufferbuilder.m_252986_(mx, 0.0f, 0.0f, 0.0f).m_7421_(0.0f, 1.0f).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            bufferbuilder.m_252986_(mx, 1.0f, 0.0f, 0.0f).m_7421_(1.0f, 1.0f).m_6122_(br, br, br, 255).m_85969_(combinedLight).m_5752_();
            tessellator.m_85914_();
            matrixStack.m_85849_();
        }
    }
}
