/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelBananaSlug;
import com.github.alexthe666.alexsmobs.entity.EntityBananaSlug;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

public class RenderBananaSlug
extends MobRenderer<EntityBananaSlug, ModelBananaSlug> {
    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_0.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_2.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_3.png");
    private static final ResourceLocation TEXTURE_SLIME = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_slime.png");

    public RenderBananaSlug(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelBananaSlug(), 0.2f);
        this.m_115326_(new LayerSlime());
    }

    protected void scale(EntityBananaSlug entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.9f, 0.9f, 0.9f);
    }

    private Direction rotate(Direction attachmentFacing) {
        return attachmentFacing.m_122434_() == Direction.Axis.Y ? Direction.UP : attachmentFacing;
    }

    private void rotateForAngle(PoseStack matrixStackIn, Direction rotate, float f) {
        if (rotate.m_122434_() != Direction.Axis.Y) {
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f * f));
        }
        switch (rotate) {
            case DOWN: {
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(180.0f * f));
                break;
            }
            case UP: {
                break;
            }
            case NORTH: {
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(180.0f * f));
                break;
            }
            case SOUTH: {
                break;
            }
            case WEST: {
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(90.0f * f));
                break;
            }
            case EAST: {
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(-90.0f * f));
            }
        }
    }

    protected void setupRotations(EntityBananaSlug entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        String s;
        if (entityLiving.m_20159_()) {
            super.m_7523_((LivingEntity)entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
            return;
        }
        if (this.m_5936_((LivingEntity)entityLiving)) {
            rotationYaw += (float)(Math.cos((double)entityLiving.f_19797_ * 3.25) * Math.PI * (double)0.4f);
        }
        float trans = entityLiving.m_6162_() ? 0.2f : 0.4f;
        Pose pose = entityLiving.m_20089_();
        if (pose != Pose.SLEEPING) {
            float progress = (entityLiving.prevAttachChangeProgress + (entityLiving.attachChangeProgress - entityLiving.prevAttachChangeProgress) * partialTicks) * 0.2f;
            float yawMul = 0.0f;
            if (entityLiving.prevAttachDir == entityLiving.getAttachmentFacing() && entityLiving.getAttachmentFacing().m_122434_() == Direction.Axis.Y) {
                yawMul = 1.0f;
            }
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f - yawMul * rotationYaw));
            matrixStackIn.m_85837_(0.0, (double)trans, 0.0);
            float prevProg = 1.0f - progress;
            this.rotateForAngle(matrixStackIn, this.rotate(entityLiving.prevAttachDir), prevProg);
            this.rotateForAngle(matrixStackIn, this.rotate(entityLiving.getAttachmentFacing()), progress);
            if (entityLiving.getAttachmentFacing() != Direction.DOWN) {
                matrixStackIn.m_85837_(0.0, (double)trans, 0.0);
                if (entityLiving.m_20184_().f_82480_ <= (double)-0.001f) {
                    matrixStackIn.m_252781_(Axis.f_252392_.m_252977_(180.0f * progress));
                }
                matrixStackIn.m_85837_(0.0, (double)(-trans), 0.0);
            }
            matrixStackIn.m_85837_(0.0, (double)(-trans), 0.0);
        }
        if (entityLiving.f_20919_ > 0) {
            float f = ((float)entityLiving.f_20919_ + partialTicks - 1.0f) / 20.0f * 1.6f;
            if ((f = Mth.m_14116_((float)f)) > 1.0f) {
                f = 1.0f;
            }
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(f * this.m_6441_((LivingEntity)entityLiving)));
        } else if (entityLiving.m_21209_()) {
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f - entityLiving.m_146909_()));
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(((float)entityLiving.f_19797_ + partialTicks) * -75.0f));
        } else if (pose != Pose.SLEEPING && entityLiving.m_8077_() && ("Dinnerbone".equals(s = ChatFormatting.m_126649_((String)entityLiving.m_7755_().getString())) || "Grumm".equals(s))) {
            matrixStackIn.m_85837_(0.0, (double)(entityLiving.m_20206_() + 0.1f), 0.0);
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(180.0f));
        }
    }

    public ResourceLocation getTextureLocation(EntityBananaSlug entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_1;
            case 2 -> TEXTURE_2;
            case 3 -> TEXTURE_3;
            default -> TEXTURE_0;
        };
    }

    class LayerSlime
    extends RenderLayer<EntityBananaSlug, ModelBananaSlug> {
        public LayerSlime() {
            super((RenderLayerParent)RenderBananaSlug.this);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityBananaSlug entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            float alpha = entitylivingbaseIn.prevTrailVisability + (entitylivingbaseIn.trailVisability - entitylivingbaseIn.prevTrailVisability) * partialTicks;
            if (alpha > 0.0f) {
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE_SLIME));
                ((ModelBananaSlug)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, alpha);
            }
        }
    }
}
