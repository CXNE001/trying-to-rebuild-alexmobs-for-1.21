/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelTerrapin;
import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.util.TerrapinTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

public class RenderTerrapin
extends MobRenderer<EntityTerrapin, ModelTerrapin> {
    private static final ResourceLocation[] SHELL_TEXTURES = new ResourceLocation[]{new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_shell_pattern_0.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_shell_pattern_1.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_shell_pattern_2.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_shell_pattern_3.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_shell_pattern_4.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_shell_pattern_5.png")};
    private static final ResourceLocation[] SKIN_PATTERN_TEXTURES = new ResourceLocation[]{new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_skin_pattern_0.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_skin_pattern_1.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_skin_pattern_2.png"), new ResourceLocation("alexsmobs:textures/entity/terrapin/overlay/terrapin_skin_pattern_3.png")};

    public RenderTerrapin(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelTerrapin(), 0.3f);
        this.m_115326_(new TurtleOverlayLayer(this, 0));
        this.m_115326_(new TurtleOverlayLayer(this, 1));
        this.m_115326_(new TurtleOverlayLayer(this, 2));
    }

    protected void scale(EntityTerrapin entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityTerrapin entity) {
        if (entity.isKoopa()) {
            return TerrapinTypes.KOOPA.getTexture();
        }
        return entity.getTurtleType().getTexture();
    }

    protected void setupRotations(EntityTerrapin entity, PoseStack stack, float pitchIn, float yawIn, float partialTickTime) {
        Pose pose;
        if (this.m_5936_((LivingEntity)entity)) {
            yawIn += (float)(Math.cos((double)entity.f_19797_ * 3.25) * Math.PI * (double)0.4f);
        }
        if ((pose = entity.m_20089_()) != Pose.SLEEPING && !entity.isSpinning()) {
            stack.m_252781_(Axis.f_252436_.m_252977_(180.0f - yawIn));
        }
        if (entity.f_20919_ > 0) {
            float f = ((float)entity.f_20919_ + partialTickTime - 1.0f) / 20.0f * 1.6f;
            if ((f = Mth.m_14116_((float)f)) > 1.0f) {
                f = 1.0f;
            }
            stack.m_252781_(Axis.f_252403_.m_252977_(f * this.m_6441_((LivingEntity)entity)));
        } else if (entity.m_21209_()) {
            stack.m_252781_(Axis.f_252529_.m_252977_(-90.0f - entity.m_146909_()));
            stack.m_252781_(Axis.f_252436_.m_252977_(((float)entity.f_19797_ + partialTickTime) * -75.0f));
        } else if (pose != Pose.SLEEPING && RenderTerrapin.m_194453_((LivingEntity)entity)) {
            stack.m_85837_(0.0, (double)(entity.m_20206_() + 0.1f), 0.0);
            stack.m_252781_(Axis.f_252403_.m_252977_(180.0f));
        }
    }

    static class TurtleOverlayLayer
    extends RenderLayer<EntityTerrapin, ModelTerrapin> {
        private final int layer;

        public TurtleOverlayLayer(RenderTerrapin render, int layer) {
            super((RenderLayerParent)render);
            this.layer = layer;
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource buffer, int packedLightIn, EntityTerrapin turtle, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (turtle.getTurtleType() == TerrapinTypes.OVERLAY && !turtle.isKoopa()) {
                ResourceLocation tex;
                ResourceLocation resourceLocation = this.layer == 0 ? this.m_117347_((Entity)turtle) : (tex = this.layer == 1 ? SHELL_TEXTURES[turtle.getShellType() % SHELL_TEXTURES.length] : SKIN_PATTERN_TEXTURES[turtle.getSkinType() % SKIN_PATTERN_TEXTURES.length]);
                int color = this.layer == 0 ? turtle.getTurtleColor() : (this.layer == 1 ? turtle.getShellColor() : turtle.getSkinColor());
                float r = (float)(color >> 16 & 0xFF) / 255.0f;
                float g = (float)(color >> 8 & 0xFF) / 255.0f;
                float b = (float)(color & 0xFF) / 255.0f;
                TurtleOverlayLayer.m_117376_((EntityModel)this.m_117386_(), (ResourceLocation)tex, (PoseStack)matrixStackIn, (MultiBufferSource)buffer, (int)packedLightIn, (LivingEntity)turtle, (float)r, (float)g, (float)b);
            }
        }
    }
}
