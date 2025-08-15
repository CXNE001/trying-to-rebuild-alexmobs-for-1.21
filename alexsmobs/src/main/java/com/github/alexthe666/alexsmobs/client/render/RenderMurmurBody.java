/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMurmurBody;
import com.github.alexthe666.alexsmobs.client.model.ModelMurmurHead;
import com.github.alexthe666.alexsmobs.client.model.ModelMurmurNeck;
import com.github.alexthe666.alexsmobs.entity.EntityMurmur;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class RenderMurmurBody
extends MobRenderer<EntityMurmur, ModelMurmurBody> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/murmur.png");
    public static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation("alexsmobs:textures/entity/murmur_angry.png");
    public static boolean renderWithHead = false;
    private static final ModelMurmurNeck NECK_MODEL = new ModelMurmurNeck();
    private static final ModelMurmurHead HEAD_MODEL = new ModelMurmurHead();

    public RenderMurmurBody(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelMurmurBody(), 0.5f);
    }

    protected void scale(EntityMurmur entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.85f, 0.85f, 0.85f);
    }

    public void render(EntityMurmur body, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.m_7392_((Mob)body, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (renderWithHead || body.shouldRenderFakeHead()) {
            float f = Mth.m_14189_((float)partialTicks, (float)body.f_20884_, (float)body.f_20883_);
            float f7 = this.m_6930_((LivingEntity)body, partialTicks);
            ResourceLocation loc = this.getTextureLocation(body);
            int overlayCoords = RenderMurmurBody.m_115338_((LivingEntity)body, (float)this.m_6931_((LivingEntity)body, partialTicks));
            matrixStackIn.m_85836_();
            this.m_7523_((LivingEntity)body, matrixStackIn, f7, f, partialTicks);
            matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.0f, -2.9f, 0.0f);
            this.scale(body, matrixStackIn, partialTicks);
            HEAD_MODEL.resetToDefaultPose();
            HEAD_MODEL.animateHair(f7);
            HEAD_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)loc)), packedLightIn, overlayCoords, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_252880_(0.0f, 0.5f, 0.0f);
            NECK_MODEL.resetToDefaultPose();
            NECK_MODEL.setAttributes(0.5f, 0.0f, 0.0f, 0.0f);
            NECK_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)loc)), packedLightIn, overlayCoords, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
            matrixStackIn.m_85849_();
        }
    }

    public ResourceLocation getTextureLocation(EntityMurmur entity) {
        return entity.isAngry() ? TEXTURE_ANGRY : TEXTURE;
    }
}
