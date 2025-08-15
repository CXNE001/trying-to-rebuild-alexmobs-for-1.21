/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$DestFactor
 *  com.mojang.blaze3d.platform.GlStateManager$SourceFactor
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  com.mojang.blaze3d.vertex.VertexMultiConsumer
 *  net.minecraft.Util
 *  net.minecraft.client.renderer.RenderStateShard
 *  net.minecraft.client.renderer.RenderStateShard$EmptyTextureStateShard
 *  net.minecraft.client.renderer.RenderStateShard$MultiTextureStateShard
 *  net.minecraft.client.renderer.RenderStateShard$TextureStateShard
 *  net.minecraft.client.renderer.RenderStateShard$TexturingStateShard
 *  net.minecraft.client.renderer.RenderStateShard$TransparencyStateShard
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.RenderType$CompositeState
 *  net.minecraft.client.renderer.blockentity.TheEndPortalRenderer
 *  net.minecraft.resources.ResourceLocation
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class AMRenderTypes
extends RenderType {
    public static final ResourceLocation STATIC_TEXTURE = new ResourceLocation("alexsmobs:textures/static.png");
    private static boolean encounteredMultiConsumerError = false;
    protected static final RenderStateShard.TexturingStateShard RAINBOW_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> AMRenderTypes.setupRainbowTexturing(1.2f, 4L), () -> RenderSystem.resetTextureMatrix());
    protected static final RenderStateShard.TexturingStateShard COMB_JELLY_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> AMRenderTypes.setupRainbowTexturing(2.0f, 16L), () -> RenderSystem.resetTextureMatrix());
    protected static final RenderStateShard.TexturingStateShard RAINBOW_TEXTURING_LARGE = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> AMRenderTypes.setupRainbowTexturing2(5.0f, 14L), () -> RenderSystem.resetTextureMatrix());
    protected static final RenderStateShard.TexturingStateShard WEEZER_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> AMRenderTypes.setupRainbowTexturing2(7.0f, 16L), () -> RenderSystem.resetTextureMatrix());
    protected static final RenderStateShard.TexturingStateShard STATIC_PORTAL_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> AMRenderTypes.setupStaticTexturing(1.1f, 12L), () -> RenderSystem.resetTextureMatrix());
    protected static final RenderStateShard.TexturingStateShard STATIC_PARTICLE_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> AMRenderTypes.setupStaticTexturing(0.1f, 12L), () -> RenderSystem.resetTextureMatrix());
    protected static final RenderStateShard.TexturingStateShard STATIC_ENTITY_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> AMRenderTypes.setupStaticTexturing(3.0f, 12L), () -> RenderSystem.resetTextureMatrix());
    public static final RenderType COMBJELLY_RAINBOW_GLINT = AMRenderTypes.m_173215_((String)"cj_rainbow_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_rainbow.png"), true, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110134_).m_110683_(COMB_JELLY_TEXTURING).m_110691_(false));
    public static final RenderType RAINBOW_GLINT = AMRenderTypes.m_173215_((String)"rainbow_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_rainbow.png"), true, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110137_).m_110683_(RAINBOW_TEXTURING).m_110677_(f_110154_).m_110691_(true));
    public static final RenderType TRANS_GLINT = AMRenderTypes.m_173215_((String)"trans_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_trans.png"), true, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110137_).m_110683_(RAINBOW_TEXTURING).m_110677_(f_110154_).m_110691_(true));
    public static final RenderType NONBI_GLINT = AMRenderTypes.m_173215_((String)"nonbi_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_nonbi.png"), true, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110137_).m_110683_(RAINBOW_TEXTURING).m_110677_(f_110154_).m_110691_(true));
    public static final RenderType BI_GLINT = AMRenderTypes.m_173215_((String)"bi_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_bi.png"), true, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110137_).m_110683_(RAINBOW_TEXTURING).m_110677_(f_110154_).m_110691_(true));
    public static final RenderType ACE_GLINT = AMRenderTypes.m_173215_((String)"ace_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_ace.png"), true, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110137_).m_110683_(RAINBOW_TEXTURING).m_110677_(f_110154_).m_110691_(true));
    public static final RenderType BRAZIL_GLINT = AMRenderTypes.m_173215_((String)"brazil_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_brazil.png"), true, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110137_).m_110683_(RAINBOW_TEXTURING_LARGE).m_110677_(f_110154_).m_110691_(true));
    public static final RenderType WEEZER_GLINT = AMRenderTypes.m_173215_((String)"weezer_glint", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/rainbow_jelly_overlays/glint_weezer.png"), false, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110685_(f_110137_).m_110683_(WEEZER_TEXTURING).m_110677_(f_110154_).m_110691_(true));
    public static final RenderType STATIC_PORTAL = AMRenderTypes.m_173215_((String)"static_portal", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(STATIC_TEXTURE, false, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110683_(STATIC_PORTAL_TEXTURING).m_110677_(f_110154_).m_110685_(f_110139_).m_110691_(true));
    public static final RenderType STATIC_PARTICLE = AMRenderTypes.m_173215_((String)"static_particle", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(STATIC_TEXTURE, false, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110683_(STATIC_PARTICLE_TEXTURING).m_110677_(f_110154_).m_110685_(f_110139_).m_110691_(true));
    public static final RenderType STATIC_ENTITY = AMRenderTypes.m_173215_((String)"static_entity", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(STATIC_TEXTURE, false, false)).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110112_).m_110683_(STATIC_ENTITY_TEXTURING).m_110677_(f_110154_).m_110685_(f_110139_).m_110691_(true));
    public static final RenderType VOID_WORM_PORTAL_OVERLAY = AMRenderTypes.m_173215_((String)"void_worm_portal_overlay", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173093_).m_110663_(f_110112_).m_110661_(f_110110_).m_110685_(f_110134_).m_173290_((RenderStateShard.EmptyTextureStateShard)RenderStateShard.MultiTextureStateShard.m_173127_().m_173132_(TheEndPortalRenderer.f_112626_, false, false).m_173132_(TheEndPortalRenderer.f_112627_, false, false).m_173131_()).m_110691_(false));
    protected static final RenderStateShard.TransparencyStateShard WORM_TRANSPARANCY = new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    protected static final RenderStateShard.TransparencyStateShard MIMICUBE_TRANSPARANCY = new RenderStateShard.TransparencyStateShard("mimicube_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    protected static final RenderStateShard.TransparencyStateShard GHOST_TRANSPARANCY = new RenderStateShard.TransparencyStateShard("translucent_ghost_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    public AMRenderTypes(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    public static RenderType getTransparentMimicube(ResourceLocation texture) {
        RenderType.CompositeState lvt_1_1_ = RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(texture, false, false)).m_173292_(f_173065_).m_110685_(f_110139_).m_110677_(f_110154_).m_110675_(f_110125_).m_110661_(f_110158_).m_110671_(f_110152_).m_110677_(f_110154_).m_110687_(RenderStateShard.f_110114_).m_110663_(RenderStateShard.f_110113_).m_110691_(true);
        return AMRenderTypes.m_173215_((String)"mimicube", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)true, (RenderType.CompositeState)lvt_1_1_);
    }

    public static RenderType getEyesFlickering(ResourceLocation p_228652_0_, float lightLevel) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(p_228652_0_, false, false);
        return AMRenderTypes.m_173215_((String)"eye_flickering", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(f_173065_).m_110685_(f_110139_).m_110661_(f_110110_).m_110671_(f_110152_).m_110677_(f_110154_).m_110691_(false));
    }

    public static RenderType getFullBright(ResourceLocation p_228652_0_) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(p_228652_0_, false, false);
        return AMRenderTypes.m_173215_((String)"full_bright", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(f_173065_).m_110685_(f_110139_).m_110661_(f_110110_).m_110671_(f_110152_).m_110677_(f_110154_).m_110691_(false));
    }

    public static RenderType getFreddy(ResourceLocation p_228652_0_) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(p_228652_0_, false, false);
        return AMRenderTypes.m_173215_((String)"freddy", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(f_173065_).m_110685_(f_110139_).m_110671_(RenderStateShard.f_110153_).m_110661_(f_110110_).m_110677_(f_110154_).m_110691_(true));
    }

    public static RenderType getFrilledSharkTeeth(ResourceLocation p_228652_0_) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(p_228652_0_, false, false);
        return AMRenderTypes.m_173215_((String)"sharkteeth", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(f_173065_).m_110685_(f_110134_).m_110661_(f_110110_).m_110671_(f_110152_).m_110677_(f_110154_).m_110691_(false));
    }

    public static RenderType getEyesNoCull(ResourceLocation p_228652_0_) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(p_228652_0_, false, false);
        return AMRenderTypes.m_173215_((String)"eyes_no_cull", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(f_173065_).m_110685_(f_110135_).m_110687_(f_110115_).m_110661_(f_110110_).m_110691_(false));
    }

    public static RenderType getSpectreBones(ResourceLocation p_228652_0_) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(p_228652_0_, false, false);
        return AMRenderTypes.m_173215_((String)"spectre_bones", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(f_173073_).m_110685_(GHOST_TRANSPARANCY).m_110663_(f_110113_).m_110687_(f_110114_).m_110661_(f_110110_).m_110671_(f_110153_).m_110677_(f_110154_).m_110691_(false));
    }

    public static RenderType getGhost(ResourceLocation p_228652_0_) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(p_228652_0_, false, false);
        return AMRenderTypes.m_173215_((String)"ghost_am", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)262144, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(f_173073_).m_110687_(f_110114_).m_110663_(f_110112_).m_110671_(f_110153_).m_110677_(f_110154_).m_110685_(GHOST_TRANSPARANCY).m_110661_(RenderStateShard.f_110110_).m_110691_(true));
    }

    public static RenderType getEyesAlphaEnabled(ResourceLocation locationIn) {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.m_110628_().m_173292_(f_173073_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(locationIn, false, false)).m_110685_(WORM_TRANSPARANCY).m_110661_(f_110110_).m_110671_(f_110152_).m_110677_(f_110154_).m_110663_(f_110112_).m_110691_(true);
        return AMRenderTypes.m_173215_((String)"eye_alpha", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)false, (RenderType.CompositeState)rendertype$compositestate);
    }

    public static RenderType getEyesNoFog(ResourceLocation locationIn) {
        RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(locationIn, false, false);
        return AMRenderTypes.m_173215_((String)"eyes_nofog", (VertexFormat)DefaultVertexFormat.f_85818_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173077_).m_173290_((RenderStateShard.EmptyTextureStateShard)renderstateshard$texturestateshard).m_110685_(f_110136_).m_110687_(f_110114_).m_110661_(f_110110_).m_110663_(f_110113_).m_110677_(f_110154_).m_110691_(true));
    }

    public static RenderType getSunbirdShine() {
        return AMRenderTypes.m_173215_((String)"sunbird_shine", (VertexFormat)DefaultVertexFormat.f_85817_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(f_173083_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/entity/sunbird_shine.png"), true, true)).m_110671_(f_110152_).m_110661_(RenderStateShard.f_110110_).m_110685_(RenderStateShard.f_110139_).m_110677_(f_110154_).m_110663_(f_110113_).m_110691_(true));
    }

    public static RenderType getSkulkBoom() {
        RenderType.CompositeState renderState = RenderType.CompositeState.m_110628_().m_173292_(f_173074_).m_110661_(f_110110_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(new ResourceLocation("alexsmobs:textures/particle/skulk_boom.png"), true, true)).m_110685_(f_110139_).m_110671_(f_110152_).m_110677_(f_110154_).m_110687_(f_110115_).m_110663_(f_110113_).m_110669_(f_110119_).m_110691_(false);
        return AMRenderTypes.m_173215_((String)"skulk_boom", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)true, (RenderType.CompositeState)renderState);
    }

    public static RenderType getUnderminer(ResourceLocation texture) {
        RenderType.CompositeState renderState = RenderType.CompositeState.m_110628_().m_173292_(f_173074_).m_110661_(f_110110_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(texture, false, false)).m_110685_(f_110139_).m_110671_(f_110152_).m_110677_(f_110154_).m_110687_(f_110114_).m_110663_(f_110113_).m_110669_(f_110117_).m_110691_(false);
        return AMRenderTypes.m_173215_((String)"underminer", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)true, (RenderType.CompositeState)renderState);
    }

    public static RenderType getGhostPickaxe(ResourceLocation texture) {
        RenderType.CompositeState renderState = RenderType.CompositeState.m_110628_().m_173292_(f_173064_).m_110661_(f_110110_).m_110675_(f_110129_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(texture, false, false)).m_110685_(RenderStateShard.f_110136_).m_110671_(f_110152_).m_110677_(f_110154_).m_110687_(f_110114_).m_110663_(f_110113_).m_110669_(f_110117_).m_110691_(false);
        return AMRenderTypes.m_173215_((String)"ghost_pickaxe", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)true, (RenderType.CompositeState)renderState);
    }

    public static RenderType getGhostCrumbling(ResourceLocation texture) {
        RenderStateShard.TextureStateShard lvt_1_1_ = new RenderStateShard.TextureStateShard(texture, false, false);
        return AMRenderTypes.m_173215_((String)"ghost_crumbling_am", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)262144, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_((RenderStateShard.EmptyTextureStateShard)lvt_1_1_).m_173292_(RenderStateShard.f_173074_).m_110685_(f_110136_).m_110661_(f_110110_).m_110671_(f_110152_).m_110677_(f_110154_).m_110669_(f_110119_).m_110663_(f_110113_).m_110661_(RenderStateShard.f_110110_).m_110691_(true));
    }

    private static void setupRainbowTexturing(float in, long time) {
        long i = Util.m_137550_() * time;
        float f = (float)(i % 110000L) / 110000.0f;
        float f1 = (float)(i % 30000L) / 30000.0f;
        Matrix4f matrix4f = new Matrix4f().translation(0.0f, f1, 0.0f);
        matrix4f.scale(in);
        RenderSystem.setTextureMatrix((Matrix4f)matrix4f);
    }

    private static void setupRainbowTexturing2(float in, long time) {
        long i = Util.m_137550_() * time;
        float f = (float)(i % 110000L) / 110000.0f;
        float f1 = (float)(i % 30000L) / 30000.0f;
        float f2 = (float)Math.sin((float)i / 30000.0f);
        Matrix4f matrix4f = new Matrix4f().translation(f1, f2, 0.0f);
        matrix4f.scale(in);
        RenderSystem.setTextureMatrix((Matrix4f)matrix4f);
    }

    private static void setupStaticTexturing(float in, long time) {
        long i = Util.m_137550_() * time;
        float f = (float)(i % 110000L) / 110000.0f;
        float f1 = (float)(i % 30000L) / 30000.0f;
        float f2 = (float)Math.floor((float)(i % 3000L) / 3000.0f * 4.0f);
        float f3 = (float)Math.sin((float)i / 30000.0f) * 0.05f;
        Matrix4f matrix4f = new Matrix4f().translation(f1, f2 * 0.25f + f3, 0.0f);
        matrix4f.scale(in * 1.5f, in * 0.25f, in);
        RenderSystem.setTextureMatrix((Matrix4f)matrix4f);
    }

    public static RenderType getFarseerBeam() {
        RenderType.CompositeState renderState = RenderType.CompositeState.m_110628_().m_173292_(f_173074_).m_110661_(f_110158_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(STATIC_TEXTURE, false, false)).m_110685_(f_110139_).m_110671_(f_110152_).m_110677_(f_110154_).m_110687_(f_110115_).m_110663_(f_110113_).m_110669_(f_110119_).m_110691_(false);
        return AMRenderTypes.m_173215_((String)"farseer_beam", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)true, (RenderType.CompositeState)renderState);
    }

    public static VertexConsumer createMergedVertexConsumer(VertexConsumer consumer1, VertexConsumer consumer2) {
        VertexConsumer vertexConsumer = consumer2;
        if (!encounteredMultiConsumerError) {
            try {
                vertexConsumer = VertexMultiConsumer.m_86168_((VertexConsumer)consumer1, (VertexConsumer)consumer2);
            }
            catch (Exception e) {
                AlexsMobs.LOGGER.warn("Encountered issue mixing two render types together. Likely an issue with Optifine or other rendering mod. This warning will only display once.");
                encounteredMultiConsumerError = true;
            }
        }
        return vertexConsumer;
    }
}
