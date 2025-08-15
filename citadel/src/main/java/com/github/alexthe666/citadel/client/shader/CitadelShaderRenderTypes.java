/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderTarget
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderStateShard
 *  net.minecraft.client.renderer.RenderStateShard$EmptyTextureStateShard
 *  net.minecraft.client.renderer.RenderStateShard$OutputStateShard
 *  net.minecraft.client.renderer.RenderStateShard$ShaderStateShard
 *  net.minecraft.client.renderer.RenderStateShard$TextureStateShard
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.RenderType$CompositeState
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.citadel.client.shader;

import com.github.alexthe666.citadel.ClientProxy;
import com.github.alexthe666.citadel.client.shader.CitadelInternalShaders;
import com.github.alexthe666.citadel.client.shader.PostEffectRegistry;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class CitadelShaderRenderTypes
extends RenderType {
    protected static final RenderStateShard.ShaderStateShard RENDERTYPE_RAINBOW_AURA_SHADER = new RenderStateShard.ShaderStateShard(CitadelInternalShaders::getRenderTypeRainbowAura);
    protected static final RenderStateShard.OutputStateShard RAINBOW_AURA_OUTPUT = new RenderStateShard.OutputStateShard("rainbow_aura_target", () -> {
        RenderTarget target = PostEffectRegistry.getRenderTargetFor(ClientProxy.RAINBOW_AURA_POST_SHADER);
        if (target != null) {
            target.m_83945_(Minecraft.m_91087_().m_91385_());
            target.m_83947_(false);
        }
    }, () -> Minecraft.m_91087_().m_91385_().m_83947_(false));

    private CitadelShaderRenderTypes(String s, VertexFormat format, VertexFormat.Mode mode, int i, boolean b1, boolean b2, Runnable runnable1, Runnable runnable2) {
        super(s, format, mode, i, b1, b2, runnable1, runnable2);
    }

    public static RenderType getRainbowAura(ResourceLocation locationIn) {
        return CitadelShaderRenderTypes.m_173215_((String)"rainbow_aura", (VertexFormat)DefaultVertexFormat.f_85818_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(RENDERTYPE_RAINBOW_AURA_SHADER).m_110661_(f_110110_).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(locationIn, false, false)).m_110685_(RenderStateShard.f_110139_).m_110663_(f_110113_).m_110675_(RAINBOW_AURA_OUTPUT).m_110691_(true));
    }
}
