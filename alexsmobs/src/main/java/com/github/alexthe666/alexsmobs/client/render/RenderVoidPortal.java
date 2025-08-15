/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityVoidPortal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderVoidPortal
extends EntityRenderer<EntityVoidPortal> {
    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/portal_idle_0.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/portal_idle_1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/portal_idle_2.png");
    private static final ResourceLocation TEXTURE_SHATTERED_0 = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/shattered/portal_idle_0.png");
    private static final ResourceLocation TEXTURE_SHATTERED_1 = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/shattered/portal_idle_1.png");
    private static final ResourceLocation TEXTURE_SHATTERED_2 = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/shattered/portal_idle_2.png");
    private static final ResourceLocation[] TEXTURE_PROGRESS = new ResourceLocation[10];
    private static final ResourceLocation[] TEXTURE_SHATTERED_PROGRESS = new ResourceLocation[10];

    public RenderVoidPortal(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
        for (int i = 0; i < 10; ++i) {
            RenderVoidPortal.TEXTURE_PROGRESS[i] = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/portal_grow_" + i + ".png");
            RenderVoidPortal.TEXTURE_SHATTERED_PROGRESS[i] = new ResourceLocation("alexsmobs:textures/entity/void_worm/portal/shattered/portal_grow_" + i + ".png");
        }
    }

    public void render(EntityVoidPortal entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.m_85836_();
        matrixStackIn.m_252781_(entityIn.getAttachmentFacing().m_122424_().m_253075_());
        matrixStackIn.m_85837_(0.5, 0.0, 0.5);
        matrixStackIn.m_85841_(2.0f, 2.0f, 2.0f);
        this.renderPortal(entityIn, matrixStackIn, bufferIn, false);
        if (entityIn.isShattered()) {
            float off = 0.01f;
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.0f, off, 0.0f);
            this.renderPortal(entityIn, matrixStackIn, bufferIn, true);
            matrixStackIn.m_85849_();
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.0f, -off, 0.0f);
            this.renderPortal(entityIn, matrixStackIn, bufferIn, true);
            matrixStackIn.m_85849_();
        }
        matrixStackIn.m_85849_();
        super.m_7392_((Entity)entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private void renderPortal(EntityVoidPortal entityIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, boolean shattered) {
        ResourceLocation tex = entityIn.getLifespan() < 20 ? this.getGrowingTexture((int)((float)entityIn.getLifespan() * 0.5f % 10.0f), shattered) : (entityIn.f_19797_ < 20 ? this.getGrowingTexture((int)((float)entityIn.f_19797_ * 0.5f % 10.0f), shattered) : this.getIdleTexture(entityIn.f_19797_ % 9, shattered));
        VertexConsumer ivertexbuilder = shattered ? AMRenderTypes.createMergedVertexConsumer(bufferIn.m_6299_(AMRenderTypes.STATIC_PORTAL), bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)tex))) : bufferIn.m_6299_(AMRenderTypes.getFullBright(tex));
        this.renderArc(matrixStackIn, ivertexbuilder);
    }

    private void renderArc(PoseStack matrixStackIn, VertexConsumer ivertexbuilder) {
        matrixStackIn.m_85836_();
        PoseStack.Pose lvt_19_1_ = matrixStackIn.m_85850_();
        Matrix4f lvt_20_1_ = lvt_19_1_.m_252922_();
        Matrix3f lvt_21_1_ = lvt_19_1_.m_252943_();
        this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, -1, 0, -1, 0.0f, 0.0f, 1, 0, 1, 240);
        this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, -1, 0, 1, 0.0f, 1.0f, 1, 0, 1, 240);
        this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, 1, 0, 1, 1.0f, 1.0f, 1, 0, 1, 240);
        this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, 1, 0, -1, 1.0f, 0.0f, 1, 0, 1, 240);
        matrixStackIn.m_85849_();
    }

    public ResourceLocation getTextureLocation(EntityVoidPortal entity) {
        return TEXTURE_0;
    }

    public void drawVertex(Matrix4f p_229039_1_, Matrix3f p_229039_2_, VertexConsumer p_229039_3_, int p_229039_4_, int p_229039_5_, int p_229039_6_, float p_229039_7_, float p_229039_8_, int p_229039_9_, int p_229039_10_, int p_229039_11_, int p_229039_12_) {
        p_229039_3_.m_252986_(p_229039_1_, (float)p_229039_4_, (float)p_229039_5_, (float)p_229039_6_).m_6122_(255, 255, 255, 255).m_7421_(p_229039_7_, p_229039_8_).m_86008_(OverlayTexture.f_118083_).m_85969_(p_229039_12_).m_252939_(p_229039_2_, (float)p_229039_9_, (float)p_229039_11_, (float)p_229039_10_).m_5752_();
    }

    public ResourceLocation getIdleTexture(int age, boolean shattered) {
        if (age < 3) {
            return shattered ? TEXTURE_SHATTERED_0 : TEXTURE_0;
        }
        if (age < 6) {
            return shattered ? TEXTURE_SHATTERED_1 : TEXTURE_1;
        }
        if (age < 10) {
            return shattered ? TEXTURE_SHATTERED_2 : TEXTURE_2;
        }
        return shattered ? TEXTURE_SHATTERED_0 : TEXTURE_0;
    }

    public ResourceLocation getGrowingTexture(int age, boolean shattered) {
        return shattered ? TEXTURE_SHATTERED_PROGRESS[Mth.m_14045_((int)age, (int)0, (int)9)] : TEXTURE_PROGRESS[Mth.m_14045_((int)age, (int)0, (int)9)];
    }
}
