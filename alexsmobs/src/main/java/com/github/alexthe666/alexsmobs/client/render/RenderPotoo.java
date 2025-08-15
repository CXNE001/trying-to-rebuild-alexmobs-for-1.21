/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.CameraType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelPotoo;
import com.github.alexthe666.alexsmobs.entity.EntityPotoo;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class RenderPotoo
extends MobRenderer<EntityPotoo, ModelPotoo> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/potoo.png");

    public RenderPotoo(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelPotoo(), 0.35f);
    }

    public boolean shouldRender(EntityPotoo bird, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
        if (bird.m_20159_() && bird.m_20202_() instanceof Player && Minecraft.m_91087_().f_91074_ == bird.m_20202_() && Minecraft.m_91087_().f_91066_.m_92176_() == CameraType.FIRST_PERSON) {
            return false;
        }
        return super.m_5523_((Mob)bird, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
    }

    protected void scale(EntityPotoo eagle, PoseStack matrixStackIn, float partialTickTime) {
        if (eagle.m_20159_() && eagle.m_20202_() != null && eagle.m_20202_() instanceof Player) {
            Player mount = (Player)eagle.m_20202_();
            boolean leftHand = false;
            if (mount.m_21120_(InteractionHand.MAIN_HAND).m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
                leftHand = mount.m_5737_() == HumanoidArm.LEFT;
            } else if (mount.m_21120_(InteractionHand.OFF_HAND).m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
                leftHand = mount.m_5737_() != HumanoidArm.LEFT;
            }
            EntityRenderer playerRender = Minecraft.m_91087_().m_91290_().m_114382_((Entity)mount);
            if ((Minecraft.m_91087_().f_91074_ != mount || Minecraft.m_91087_().f_91066_.m_92176_() != CameraType.FIRST_PERSON) && playerRender instanceof LivingEntityRenderer && ((LivingEntityRenderer)playerRender).m_7200_() instanceof HumanoidModel) {
                if (leftHand) {
                    matrixStackIn.m_252880_(-0.3f, -0.7f, 0.5f);
                    ((HumanoidModel)((LivingEntityRenderer)playerRender).m_7200_()).f_102812_.m_104299_(matrixStackIn);
                    matrixStackIn.m_252880_(-0.1f, 0.6f, -0.1f);
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(55.0f));
                    matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(70.0f));
                } else {
                    matrixStackIn.m_252880_(0.3f, -0.7f, 0.5f);
                    ((HumanoidModel)((LivingEntityRenderer)playerRender).m_7200_()).f_102811_.m_104299_(matrixStackIn);
                    matrixStackIn.m_252880_(0.1f, 0.6f, -0.1f);
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(55.0f));
                    matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-70.0f));
                }
            }
        }
    }

    public ResourceLocation getTextureLocation(EntityPotoo entity) {
        return TEXTURE;
    }
}
