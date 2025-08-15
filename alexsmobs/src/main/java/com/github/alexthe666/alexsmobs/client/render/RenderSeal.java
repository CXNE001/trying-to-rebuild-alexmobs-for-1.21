/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.Font$DisplayMode
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSeal;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerSealItem;
import com.github.alexthe666.alexsmobs.entity.EntitySeal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.ArrayList;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.client.ForgeHooksClient;
import org.joml.Matrix4f;

public class RenderSeal
extends MobRenderer<EntitySeal, ModelSeal> {
    private static final ResourceLocation TEXTURE_BROWN_0 = new ResourceLocation("alexsmobs:textures/entity/seal/seal_brown_0.png");
    private static final ResourceLocation TEXTURE_BROWN_1 = new ResourceLocation("alexsmobs:textures/entity/seal/seal_brown_1.png");
    private static final ResourceLocation TEXTURE_ARCTIC_0 = new ResourceLocation("alexsmobs:textures/entity/seal/seal_arctic_0.png");
    private static final ResourceLocation TEXTURE_ARCTIC_1 = new ResourceLocation("alexsmobs:textures/entity/seal/seal_arctic_1.png");
    private static final ResourceLocation TEXTURE_ARCTIC_BABY = new ResourceLocation("alexsmobs:textures/entity/seal/seal_arctic_baby.png");
    private static final ResourceLocation TEXTURE_TEARS = new ResourceLocation("alexsmobs:textures/entity/seal/seal_crying.png");
    private static final ResourceLocation TEXTURE_TONGUE = new ResourceLocation("alexsmobs:textures/entity/seal/seal_tongue.png");

    public RenderSeal(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelSeal(), 0.45f);
        this.m_115326_(new LayerSealItem(this));
        this.m_115326_(new SealTearsLayer(this));
    }

    protected boolean shouldShowName(EntitySeal seal) {
        return super.m_6512_((Mob)seal) || seal.isTearsEasterEgg();
    }

    public ResourceLocation getTextureLocation(EntitySeal entity) {
        if (entity.isArctic()) {
            return entity.m_6162_() ? TEXTURE_ARCTIC_BABY : (entity.getVariant() == 1 ? TEXTURE_ARCTIC_1 : TEXTURE_ARCTIC_0);
        }
        return entity.getVariant() == 1 ? TEXTURE_BROWN_1 : TEXTURE_BROWN_0;
    }

    protected void renderNameTag(EntitySeal seal, Component text, PoseStack poseStack, MultiBufferSource bufferSrc, int numberIn) {
        if (seal.isTearsEasterEgg()) {
            double d0 = this.f_114476_.m_114471_((Entity)seal);
            if (ForgeHooksClient.isNameplateInRenderDistance((Entity)seal, (double)d0)) {
                boolean flag = !seal.m_20163_();
                float f = seal.m_20206_() + 0.5f;
                String[] split = text.m_130668_(512).split(" ");
                StringBuilder recombined = new StringBuilder();
                ArrayList<String> strings = new ArrayList<String>();
                for (int wordIndex = 0; wordIndex < split.length; ++wordIndex) {
                    recombined.append(split[wordIndex]).append(" ");
                    if (recombined.length() <= 15 && wordIndex != split.length - 1) continue;
                    strings.add(recombined.toString());
                    recombined = new StringBuilder();
                }
                int i = 10 - 10 * strings.size();
                poseStack.m_85836_();
                poseStack.m_85837_(0.0, (double)f, 0.0);
                poseStack.m_252781_(this.f_114476_.m_253208_());
                poseStack.m_85841_(-0.025f, -0.025f, 0.025f);
                Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
                float f1 = 1.0f;
                int j = -1;
                Font font = this.m_114481_();
                String widest = "";
                for (String print : strings) {
                    if (font.m_92895_(widest) >= font.m_92895_(print)) continue;
                    widest = print;
                }
                float widestCenter = -font.m_92895_(widest) / 2;
                for (String print : strings) {
                    float f2 = -font.m_92895_(print) / 2;
                    poseStack.m_85837_(0.0, 0.0, 0.1);
                    font.m_271703_(widest, widestCenter, (float)i, j, false, matrix4f, bufferSrc, Font.DisplayMode.NORMAL, j, 240);
                    poseStack.m_85837_(0.0, 0.0, -0.1);
                    font.m_271703_(print, f2, (float)i, 1, false, matrix4f, bufferSrc, Font.DisplayMode.NORMAL, j, 240);
                    font.m_271703_(print, f2, (float)i, 0, false, matrix4f, bufferSrc, Font.DisplayMode.NORMAL, j, 240);
                    i += 10;
                }
                poseStack.m_85849_();
            }
        } else {
            super.m_7649_((Entity)seal, text, poseStack, bufferSrc, numberIn);
        }
    }

    static class SealTearsLayer
    extends RenderLayer<EntitySeal, ModelSeal> {
        public SealTearsLayer(RenderSeal p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntitySeal entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entitylivingbaseIn.isTearsEasterEgg()) {
                VertexConsumer lead = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_TEARS));
                ((ModelSeal)this.m_117386_()).m_7695_(matrixStackIn, lead, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
}
