/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelGiantSquid;
import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.EntityGiantSquidPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class RenderGiantSquid
extends MobRenderer<EntityGiantSquid, ModelGiantSquid> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/giant_squid.png");
    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation("alexsmobs:textures/entity/giant_squid_blue.png");
    private static final ResourceLocation TEXTURE_DEPRESSURIZED = new ResourceLocation("alexsmobs:textures/entity/giant_squid_depressurized.png");

    public RenderGiantSquid(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelGiantSquid(), 1.0f);
        this.m_115326_(new LayerDepressurization(this));
    }

    protected float getFlipDegrees(EntityGiantSquid squid) {
        return 0.0f;
    }

    public boolean shouldRender(EntityGiantSquid livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        if (livingEntityIn.isCaptured() && livingEntityIn.m_6084_()) {
            return false;
        }
        if (super.m_5523_((Mob)livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        for (EntityGiantSquidPart part : livingEntityIn.allParts) {
            if (!camera.m_113029_(part.m_20191_())) continue;
            return true;
        }
        return false;
    }

    protected void scale(EntityGiantSquid entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityGiantSquid entity) {
        return entity.isBlue() ? TEXTURE_BLUE : TEXTURE;
    }

    static class LayerDepressurization
    extends RenderLayer<EntityGiantSquid, ModelGiantSquid> {
        public LayerDepressurization(RenderGiantSquid render) {
            super((RenderLayerParent)render);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGiantSquid squid, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE_DEPRESSURIZED));
            float alpha = squid.prevDepressurization + (squid.getDepressurization() - squid.prevDepressurization) * partialTicks;
            ((ModelGiantSquid)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)squid, (float)0.0f), 1.0f, 1.0f, 1.0f, alpha);
        }
    }
}
