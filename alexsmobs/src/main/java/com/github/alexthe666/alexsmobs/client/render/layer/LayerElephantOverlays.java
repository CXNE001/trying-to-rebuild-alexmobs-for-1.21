/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.DyeColor
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelElephant;
import com.github.alexthe666.alexsmobs.client.render.RenderElephant;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;

public class LayerElephantOverlays
extends RenderLayer<EntityElephant, ModelElephant> {
    private static final ResourceLocation[] ELEPHANT_DECOR_TEXTURES = new ResourceLocation[]{new ResourceLocation("alexsmobs:textures/entity/elephant/decor/white.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/orange.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/magenta.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/light_blue.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/yellow.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/lime.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/pink.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/gray.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/light_gray.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/cyan.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/purple.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/blue.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/brown.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/green.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/red.png"), new ResourceLocation("alexsmobs:textures/entity/elephant/decor/black.png")};
    private static final ResourceLocation TRADER_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/elephant/decor/trader.png");
    private static final ResourceLocation TEXTURE_CHEST = new ResourceLocation("alexsmobs:textures/entity/elephant/elephant_chest.png");
    private final ModelElephant model = new ModelElephant(0.5f);

    public LayerElephantOverlays(RenderElephant renderElephant) {
        super((RenderLayerParent)renderElephant);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityElephant elephant, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        DyeColor lvt_11_1_;
        if (elephant.isChested()) {
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110452_((ResourceLocation)TEXTURE_CHEST));
            ((ModelElephant)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)elephant, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
        if ((lvt_11_1_ = elephant.getColor()) != null || elephant.isTrader()) {
            ResourceLocation lvt_12_3_ = !elephant.isTrader() ? ELEPHANT_DECOR_TEXTURES[lvt_11_1_.m_41060_()] : TRADER_TEXTURE;
            ((ModelElephant)this.m_117386_()).m_102624_((EntityModel)this.model);
            this.model.setupAnim(elephant, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            VertexConsumer lvt_13_1_ = bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)lvt_12_3_));
            this.model.m_7695_(matrixStackIn, lvt_13_1_, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
