/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.CameraType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelBaldEagle;
import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class RenderBaldEagle
extends MobRenderer<EntityBaldEagle, ModelBaldEagle> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/bald_eagle.png");
    private static final ResourceLocation TEXTURE_CAP = new ResourceLocation("alexsmobs:textures/entity/bald_eagle_hood.png");

    public RenderBaldEagle(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelBaldEagle(), 0.3f);
        this.m_115326_(new CapLayer(this));
    }

    public boolean shouldRender(EntityBaldEagle baldEagle, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
        if (baldEagle.m_20159_() && baldEagle.m_20202_() instanceof Player && Minecraft.m_91087_().f_91074_ == baldEagle.m_20202_() && Minecraft.m_91087_().f_91066_.m_92176_() == CameraType.FIRST_PERSON) {
            return false;
        }
        return super.m_5523_((Mob)baldEagle, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
    }

    protected void scale(EntityBaldEagle eagle, PoseStack matrixStackIn, float partialTickTime) {
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
                    matrixStackIn.m_252880_(-0.2f, 0.5f, -0.18f);
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(40.0f));
                    matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(70.0f));
                } else {
                    matrixStackIn.m_252880_(0.3f, -0.7f, 0.5f);
                    ((HumanoidModel)((LivingEntityRenderer)playerRender).m_7200_()).f_102811_.m_104299_(matrixStackIn);
                    matrixStackIn.m_252880_(0.2f, 0.5f, -0.18f);
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(40.0f));
                    matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-70.0f));
                }
            }
        }
    }

    public ResourceLocation getTextureLocation(EntityBaldEagle entity) {
        return TEXTURE;
    }

    static class CapLayer
    extends RenderLayer<EntityBaldEagle, ModelBaldEagle> {
        public CapLayer(RenderBaldEagle p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityBaldEagle entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entitylivingbaseIn.hasCap()) {
                VertexConsumer lead = bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE_CAP));
                ((ModelBaldEagle)this.m_117386_()).m_7695_(matrixStackIn, lead, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
}
