/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.LightLayer
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelRaccoon;
import com.github.alexthe666.alexsmobs.client.render.RenderRaccoon;
import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LightLayer;

public class LayerRaccoonEyes
extends RenderLayer<EntityRaccoon, ModelRaccoon> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/raccoon_eyes.png");

    public LayerRaccoonEyes(RenderRaccoon render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityRaccoon raccoon, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        long roundedTime = raccoon.m_9236_().m_46468_() % 24000L;
        boolean night = roundedTime >= 13000L && roundedTime <= 22000L;
        BlockPos ratPos = raccoon.getLightPosition();
        int i = raccoon.m_9236_().m_45517_(LightLayer.SKY, ratPos);
        int j = raccoon.m_9236_().m_45517_(LightLayer.BLOCK, ratPos);
        int brightness = night ? j : Math.max(i, j);
        if (brightness < 7 && !raccoon.isRigby()) {
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)TEXTURE));
            ((ModelRaccoon)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)raccoon, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
