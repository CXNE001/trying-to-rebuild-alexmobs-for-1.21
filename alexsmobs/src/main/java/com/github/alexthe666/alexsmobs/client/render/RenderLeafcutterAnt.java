/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.core.Direction
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelLeafcutterAnt;
import com.github.alexthe666.alexsmobs.client.model.ModelLeafcutterAntQueen;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerLeafcutterAntLeaf;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

public class RenderLeafcutterAnt
extends MobRenderer<EntityLeafcutterAnt, AdvancedEntityModel<EntityLeafcutterAnt>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant.png");
    private static final ResourceLocation TEXTURE_QUEEN = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant_queen.png");
    private static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant_angry.png");
    private static final ResourceLocation TEXTURE_QUEEN_ANGRY = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant_queen_angry.png");
    private final ModelLeafcutterAnt modelAnt = new ModelLeafcutterAnt();
    private final ModelLeafcutterAntQueen modelQueen = new ModelLeafcutterAntQueen();

    public RenderLeafcutterAnt(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelLeafcutterAnt(), 0.25f);
        this.m_115326_(new LayerLeafcutterAntLeaf(this));
    }

    protected void setupRotations(EntityLeafcutterAnt entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        String s;
        if (this.m_5936_((LivingEntity)entityLiving)) {
            rotationYaw += (float)(Math.cos((double)entityLiving.f_19797_ * 3.25) * Math.PI * (double)0.4f);
        }
        float trans = entityLiving.m_6162_() ? 0.25f : 0.5f;
        Pose pose = entityLiving.m_20089_();
        if (pose != Pose.SLEEPING) {
            float progresso = 1.0f - (entityLiving.prevAttachChangeProgress + (entityLiving.attachChangeProgress - entityLiving.prevAttachChangeProgress) * partialTicks);
            if (entityLiving.getAttachmentFacing() == Direction.DOWN) {
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f - rotationYaw));
                matrixStackIn.m_85837_(0.0, (double)trans, 0.0);
                if (entityLiving.f_19855_ < entityLiving.m_20186_()) {
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f * (1.0f - progresso)));
                } else {
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f * (1.0f - progresso)));
                }
                matrixStackIn.m_85837_(0.0, (double)(-trans), 0.0);
            } else if (entityLiving.getAttachmentFacing() == Direction.UP) {
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f - rotationYaw));
                matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
                matrixStackIn.m_85837_(0.0, (double)(-trans), 0.0);
            } else {
                matrixStackIn.m_85837_(0.0, (double)trans, 0.0);
                switch (entityLiving.getAttachmentFacing()) {
                    case NORTH: {
                        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f * progresso));
                        matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(0.0f));
                        break;
                    }
                    case SOUTH: {
                        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
                        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f * progresso));
                        break;
                    }
                    case WEST: {
                        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
                        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f - 90.0f * progresso));
                        matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(-90.0f));
                        break;
                    }
                    case EAST: {
                        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
                        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f * progresso - 90.0f));
                        matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(90.0f));
                    }
                }
                if (entityLiving.m_20184_().f_82480_ <= (double)-0.001f) {
                    matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-180.0f));
                }
                matrixStackIn.m_85837_(0.0, (double)(-trans), 0.0);
            }
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

    protected void scale(EntityLeafcutterAnt entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        this.f_115290_ = entitylivingbaseIn.isQueen() ? this.modelQueen : this.modelAnt;
    }

    public ResourceLocation getTextureLocation(EntityLeafcutterAnt entity) {
        if (entity.m_6784_() > 0) {
            return entity.isQueen() ? TEXTURE_QUEEN_ANGRY : TEXTURE_ANGRY;
        }
        return entity.isQueen() ? TEXTURE_QUEEN : TEXTURE;
    }
}
