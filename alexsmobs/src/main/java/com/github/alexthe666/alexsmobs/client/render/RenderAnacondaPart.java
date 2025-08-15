/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelAnaconda;
import com.github.alexthe666.alexsmobs.client.render.RenderAnaconda;
import com.github.alexthe666.alexsmobs.entity.EntityAnacondaPart;
import com.github.alexthe666.alexsmobs.entity.util.AnacondaPartIndex;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

public class RenderAnacondaPart
extends LivingEntityRenderer<EntityAnacondaPart, AdvancedEntityModel<EntityAnacondaPart>> {
    private final ModelAnaconda<EntityAnacondaPart> neckModel = new ModelAnaconda(AnacondaPartIndex.NECK);
    private final ModelAnaconda<EntityAnacondaPart> bodyModel = new ModelAnaconda(AnacondaPartIndex.BODY);
    private final ModelAnaconda<EntityAnacondaPart> tailModel = new ModelAnaconda(AnacondaPartIndex.TAIL);

    public RenderAnacondaPart(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelAnaconda(AnacondaPartIndex.NECK), 0.3f);
    }

    protected void setupRotations(EntityAnacondaPart entity, PoseStack stack, float pitchIn, float yawIn, float partialTickTime) {
        String s;
        Pose pose;
        float newYaw = entity.f_20885_;
        if (this.m_5936_(entity)) {
            newYaw += (float)(Math.cos((double)entity.f_19797_ * 3.25) * Math.PI * (double)0.4f);
        }
        if ((pose = entity.m_20089_()) != Pose.SLEEPING) {
            stack.m_252781_(Axis.f_252436_.m_252977_(180.0f - newYaw));
            stack.m_252781_(Axis.f_252529_.m_252977_(entity.m_146909_()));
        }
        if (entity.f_20919_ > 0) {
            float f = ((float)entity.f_20919_ + partialTickTime - 1.0f) / 20.0f * 1.6f;
            if ((f = Mth.m_14116_((float)f)) > 1.0f) {
                f = 1.0f;
            }
            stack.m_252781_(Axis.f_252403_.m_252977_(f * this.m_6441_(entity)));
        } else if (entity.m_8077_() && ("Dinnerbone".equals(s = ChatFormatting.m_126649_((String)entity.m_7755_().getString())) || "Grumm".equals(s))) {
            stack.m_85837_(0.0, (double)(entity.m_20206_() + 0.1f), 0.0);
            stack.m_252781_(Axis.f_252403_.m_252977_(180.0f));
        }
    }

    protected boolean shouldShowName(EntityAnacondaPart entity) {
        return super.m_6512_((LivingEntity)entity) && (entity.m_6052_() || entity.m_8077_() && entity == this.f_114476_.f_114359_);
    }

    protected void scale(EntityAnacondaPart entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        this.f_115290_ = this.getModelForType(entitylivingbaseIn.getPartType());
        matrixStackIn.m_85841_(entitylivingbaseIn.m_6134_(), entitylivingbaseIn.m_6134_(), entitylivingbaseIn.m_6134_());
    }

    private AdvancedEntityModel<EntityAnacondaPart> getModelForType(AnacondaPartIndex partType) {
        switch (partType) {
            case BODY: {
                return this.bodyModel;
            }
            case NECK: {
                return this.neckModel;
            }
            case TAIL: {
                return this.tailModel;
            }
        }
        return this.bodyModel;
    }

    public ResourceLocation getTextureLocation(EntityAnacondaPart entity) {
        return RenderAnaconda.getAnacondaTexture(entity.isYellow(), entity.isShedding());
    }
}
