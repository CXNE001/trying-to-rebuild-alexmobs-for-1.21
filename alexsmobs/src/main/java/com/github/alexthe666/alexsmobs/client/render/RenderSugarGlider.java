/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.CameraType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  org.joml.Quaternionf
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSugarGlider;
import com.github.alexthe666.alexsmobs.entity.EntitySugarGlider;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import org.joml.Quaternionf;

public class RenderSugarGlider
extends MobRenderer<EntitySugarGlider, ModelSugarGlider> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/sugar_glider.png");

    public RenderSugarGlider(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelSugarGlider(), 0.35f);
    }

    private Direction rotate(Direction attachmentFacing) {
        return attachmentFacing.m_122434_() == Direction.Axis.Y ? Direction.UP : attachmentFacing;
    }

    protected void setupRotations(EntitySugarGlider entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
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
            float prevProg = entityLiving.prevAttachChangeProgress + (entityLiving.attachChangeProgress - entityLiving.prevAttachChangeProgress) * partialTicks;
            float yawMul = 0.0f;
            if (entityLiving.prevAttachDir == entityLiving.getAttachmentFacing() && entityLiving.getAttachmentFacing().m_122434_() == Direction.Axis.Y) {
                yawMul = 1.0f;
            }
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f - yawMul * rotationYaw));
            if (entityLiving.getAttachmentFacing() == Direction.DOWN) {
                matrixStackIn.m_85837_(0.0, (double)trans, 0.0);
                if (entityLiving.f_19855_ <= entityLiving.m_20186_()) {
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f * prevProg));
                } else {
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f * prevProg));
                }
                matrixStackIn.m_85837_(0.0, (double)(-trans), 0.0);
            }
            matrixStackIn.m_85837_(0.0, (double)trans, 0.0);
            Quaternionf current = this.rotate(entityLiving.getAttachmentFacing()).m_253075_();
            current.mul(1.0f - prevProg);
            matrixStackIn.m_252781_(current);
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
        } else if (entityLiving.m_8077_() && ("Dinnerbone".equals(s = ChatFormatting.m_126649_((String)entityLiving.m_7755_().getString())) || "Grumm".equals(s))) {
            matrixStackIn.m_85837_(0.0, (double)(entityLiving.m_20206_() + 0.1f), 0.0);
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(180.0f));
        }
    }

    protected void scale(EntitySugarGlider mob, PoseStack matrixStackIn, float partialTickTime) {
        if (mob.m_20159_() && mob.m_20202_() != null && mob.m_20202_() instanceof Player) {
            Player mount = (Player)mob.m_20202_();
            EntityRenderer playerRender = Minecraft.m_91087_().m_91290_().m_114382_((Entity)mount);
            if ((Minecraft.m_91087_().f_91074_ != mount || Minecraft.m_91087_().f_91066_.m_92176_() != CameraType.FIRST_PERSON) && playerRender instanceof LivingEntityRenderer && ((LivingEntityRenderer)playerRender).m_7200_() instanceof HumanoidModel) {
                matrixStackIn.m_252880_(0.0f, 0.5f, 0.0f);
                ((HumanoidModel)((LivingEntityRenderer)playerRender).m_7200_()).f_102808_.m_104299_(matrixStackIn);
                matrixStackIn.m_252880_(0.0f, -0.5f, 0.0f);
            }
        }
    }

    public ResourceLocation getTextureLocation(EntitySugarGlider entity) {
        return TEXTURE;
    }
}
