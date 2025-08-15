/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.citadel.client.rewards;

import com.github.alexthe666.citadel.CitadelConstants;
import com.github.alexthe666.citadel.ClientProxy;
import com.github.alexthe666.citadel.client.rewards.CitadelPatreonRenderer;
import com.github.alexthe666.citadel.client.shader.CitadelShaderRenderTypes;
import com.github.alexthe666.citadel.client.shader.PostEffectRegistry;
import com.github.alexthe666.citadel.client.texture.CitadelTextureManager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class SpaceStationPatreonRenderer
extends CitadelPatreonRenderer {
    private static final ResourceLocation CITADEL_TEXTURE = new ResourceLocation("citadel", "textures/patreon/citadel_model.png");
    private static final ResourceLocation CITADEL_LIGHTS_TEXTURE = new ResourceLocation("citadel", "textures/patreon/citadel_model_glow.png");
    private final ResourceLocation resourceLocation;
    private final int[] colors;

    public SpaceStationPatreonRenderer(ResourceLocation resourceLocation, int[] colors) {
        this.resourceLocation = resourceLocation;
        this.colors = colors;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource buffer, int light, float partialTick, LivingEntity entity, float distanceIn, float rotateSpeed, float rotateHeight) {
        float tick = (float)entity.f_19797_ + partialTick;
        float bob = (float)(Math.sin(tick * 0.1f) * 1.0 * (double)0.05f - (double)0.05f);
        float scale = 0.4f;
        float rotation = Mth.m_14177_((float)(tick * rotateSpeed % 360.0f));
        matrixStackIn.m_85836_();
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(rotation));
        matrixStackIn.m_252880_(0.0f, entity.m_20206_() + bob + (rotateHeight - 1.0f), entity.m_20205_() * distanceIn);
        matrixStackIn.m_85836_();
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(75.0f));
        matrixStackIn.m_85841_(scale, scale, scale);
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(rotation * 10.0f));
        ClientProxy.CITADEL_MODEL.resetToDefaultPose();
        if (CitadelConstants.debugShaders()) {
            PostEffectRegistry.renderEffectForNextTick(ClientProxy.RAINBOW_AURA_POST_SHADER);
            ClientProxy.CITADEL_MODEL.m_7695_(matrixStackIn, buffer.m_6299_(CitadelShaderRenderTypes.getRainbowAura(CITADEL_TEXTURE)), light, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            ClientProxy.CITADEL_MODEL.m_7695_(matrixStackIn, buffer.m_6299_(RenderType.m_110458_((ResourceLocation)CitadelTextureManager.getColorMappedTexture(this.resourceLocation, CITADEL_TEXTURE, this.colors))), light, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            ClientProxy.CITADEL_MODEL.m_7695_(matrixStackIn, buffer.m_6299_(RenderType.m_110488_((ResourceLocation)CITADEL_LIGHTS_TEXTURE)), light, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }
}
