/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelVoidWormBody;
import com.github.alexthe666.alexsmobs.client.model.ModelVoidWormTail;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerVoidWormGlow;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

public class RenderVoidWormBody
extends LivingEntityRenderer<EntityVoidWormPart, EntityModel<EntityVoidWormPart>> {
    private static final ResourceLocation TEXTURE_BODY = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_body.png");
    private static final ResourceLocation TEXTURE_BODY_HURT = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_body_hurt.png");
    private static final ResourceLocation TEXTURE_BODY_GLOW = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_body_glow.png");
    private static final ResourceLocation TEXTURE_TAIL = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_tail.png");
    private static final ResourceLocation TEXTURE_TAIL_HURT = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_tail_hurt.png");
    private static final ResourceLocation TEXTURE_TAIL_GLOW = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_tail_glow.png");
    private final ModelVoidWormBody bodyModel = new ModelVoidWormBody(0.0f);
    private final ModelVoidWormTail tailModel = new ModelVoidWormTail(0.0f);

    public RenderVoidWormBody(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelVoidWormBody(0.0f), 1.0f);
        this.m_115326_(new LayerVoidWormGlow((RenderLayerParent)this, renderManagerIn.m_174026_(), (EntityModel)new ModelVoidWormBody(0.0f)){

            @Override
            public ResourceLocation getGlowTexture(LivingEntity worm) {
                return ((EntityVoidWormPart)worm).isTail() ? TEXTURE_TAIL_GLOW : TEXTURE_BODY_GLOW;
            }

            @Override
            public boolean isGlowing(LivingEntity worm) {
                return !((EntityVoidWormPart)worm).isHurt();
            }

            @Override
            public float getAlpha(LivingEntity livingEntity) {
                EntityVoidWormPart worm = (EntityVoidWormPart)livingEntity;
                return (float)Mth.m_14008_((double)(((double)worm.m_21223_() - worm.getHealthThreshold()) / ((double)worm.m_21233_() - worm.getHealthThreshold())), (double)0.0, (double)1.0);
            }
        });
    }

    public boolean shouldRender(EntityVoidWormPart worm, Frustum camera, double camX, double camY, double camZ) {
        return worm.getPortalTicks() <= 0 && super.m_5523_((Entity)worm, camera, camX, camY, camZ);
    }

    public ResourceLocation getTextureLocation(EntityVoidWormPart entity) {
        if (entity.isHurt()) {
            return entity.isTail() ? TEXTURE_TAIL_HURT : TEXTURE_BODY_HURT;
        }
        return entity.isTail() ? TEXTURE_TAIL : TEXTURE_BODY;
    }

    protected void setupRotations(EntityVoidWormPart entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        Pose pose = entityLiving.m_20089_();
        if (pose != Pose.SLEEPING) {
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f - entityLiving.getWormYaw(partialTicks)));
        }
        if (entityLiving.f_20919_ > 0) {
            float f = ((float)entityLiving.f_20919_ + partialTicks - 1.0f) / 20.0f * 1.6f;
            if ((f = Mth.m_14116_((float)f)) > 1.0f) {
                f = 1.0f;
            }
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(f * this.m_6441_(entityLiving)));
        }
    }

    protected void scale(EntityVoidWormPart entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        this.f_115290_ = entitylivingbaseIn.isTail() ? this.tailModel : this.bodyModel;
        matrixStackIn.m_85841_(entitylivingbaseIn.getWormScale(), entitylivingbaseIn.getWormScale(), entitylivingbaseIn.getWormScale());
    }

    protected boolean shouldShowName(EntityVoidWormPart entity) {
        return super.m_6512_((LivingEntity)entity) && (entity.m_6052_() || entity.m_8077_() && entity == this.f_114476_.f_114359_);
    }
}
