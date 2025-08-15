/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.LightTexture
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderNameTagEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelTiger;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerTigerEyes;
import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.joml.Matrix4f;

public class RenderTiger
extends MobRenderer<EntityTiger, ModelTiger> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/tiger/tiger.png");
    private static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation("alexsmobs:textures/entity/tiger/tiger_angry.png");
    private static final ResourceLocation TEXTURE_SLEEPING = new ResourceLocation("alexsmobs:textures/entity/tiger/tiger_sleeping.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation("alexsmobs:textures/entity/tiger/tiger_white.png");
    private static final ResourceLocation TEXTURE_ANGRY_WHITE = new ResourceLocation("alexsmobs:textures/entity/tiger/tiger_white_angry.png");
    private static final ResourceLocation TEXTURE_SLEEPING_WHITE = new ResourceLocation("alexsmobs:textures/entity/tiger/tiger_white_sleeping.png");

    public RenderTiger(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelTiger(), 0.6f);
        this.m_115326_(new LayerTigerEyes(this));
    }

    protected void scale(EntityTiger entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public void render(EntityTiger entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        Direction direction;
        boolean shouldSit;
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre((LivingEntity)entityIn, (LivingEntityRenderer)this, partialTicks, matrixStackIn, bufferIn, packedLightIn))) {
            return;
        }
        matrixStackIn.m_85836_();
        ((ModelTiger)this.f_115290_).f_102608_ = this.m_115342_((LivingEntity)entityIn, partialTicks);
        ((ModelTiger)this.f_115290_).f_102609_ = shouldSit = entityIn.m_20159_() && entityIn.m_20202_() != null && entityIn.m_20202_().shouldRiderSit();
        ((ModelTiger)this.f_115290_).f_102610_ = entityIn.m_6162_();
        float f = Mth.m_14189_((float)partialTicks, (float)entityIn.f_20884_, (float)entityIn.f_20883_);
        float f1 = Mth.m_14189_((float)partialTicks, (float)entityIn.f_20886_, (float)entityIn.f_20885_);
        float f2 = f1 - f;
        if (shouldSit && entityIn.m_20202_() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)entityIn.m_20202_();
            f = Mth.m_14189_((float)partialTicks, (float)livingentity.f_20884_, (float)livingentity.f_20883_);
            f2 = f1 - f;
            float f3 = Mth.m_14177_((float)f2);
            if (f3 < -85.0f) {
                f3 = -85.0f;
            }
            if (f3 >= 85.0f) {
                f3 = 85.0f;
            }
            f = f1 - f3;
            if (f3 * f3 > 2500.0f) {
                f += f3 * 0.2f;
            }
            f2 = f1 - f;
        }
        float f6 = Mth.m_14179_((float)partialTicks, (float)entityIn.f_19860_, (float)entityIn.m_146909_());
        if (entityIn.m_20089_() == Pose.SLEEPING && (direction = entityIn.m_21259_()) != null) {
            float f4 = entityIn.m_20236_(Pose.STANDING) - 0.1f;
            matrixStackIn.m_85837_((double)((float)(-direction.m_122429_()) * f4), 0.0, (double)((float)(-direction.m_122431_()) * f4));
        }
        float f7 = this.m_6930_((LivingEntity)entityIn, partialTicks);
        this.m_7523_((LivingEntity)entityIn, matrixStackIn, f7, f, partialTicks);
        matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
        this.scale(entityIn, matrixStackIn, partialTicks);
        matrixStackIn.m_85837_(0.0, (double)-1.501f, 0.0);
        float f8 = 0.0f;
        float f5 = 0.0f;
        if (!shouldSit && entityIn.m_6084_()) {
            f8 = entityIn.f_267362_.m_267711_(partialTicks);
            f5 = entityIn.f_267362_.m_267590_(partialTicks);
            if (entityIn.m_6162_()) {
                f5 *= 3.0f;
            }
            if (f8 > 1.0f) {
                f8 = 1.0f;
            }
        }
        ((ModelTiger)this.f_115290_).m_6839_((Entity)entityIn, f5, f8, partialTicks);
        ((ModelTiger)this.f_115290_).setupAnim(entityIn, f5, f8, f7, f2, f6);
        Minecraft minecraft = Minecraft.m_91087_();
        boolean flag = this.m_5933_((LivingEntity)entityIn);
        boolean flag1 = !flag && !entityIn.m_20177_((Player)minecraft.f_91074_);
        boolean flag2 = minecraft.m_91314_((Entity)entityIn);
        RenderType rendertype = this.getRenderType(entityIn, flag, flag1, flag2);
        if (rendertype != null) {
            float stealthLevel = entityIn.prevStealthProgress + (entityIn.stealthProgress - entityIn.prevStealthProgress) * partialTicks;
            this.f_114477_ = 0.6f * (1.0f - stealthLevel * 0.1f);
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(rendertype);
            int i = RenderTiger.m_115338_((LivingEntity)entityIn, (float)this.m_6931_((LivingEntity)entityIn, partialTicks));
            ((ModelTiger)this.f_115290_).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, i, 1.0f, 1.0f, 1.0f, flag1 ? 0.15f : Mth.m_14036_((float)(1.0f - stealthLevel * 0.1f), (float)0.0f, (float)1.0f));
        }
        if (!entityIn.m_5833_()) {
            for (RenderLayer layerrenderer : this.f_115291_) {
                layerrenderer.m_6494_(matrixStackIn, bufferIn, packedLightIn, (Entity)entityIn, f5, f8, partialTicks, f7, f2, f6);
            }
        }
        matrixStackIn.m_85849_();
        Entity entity = entityIn.m_21524_();
        if (entity != null) {
            this.renderLeash(entityIn, partialTicks, matrixStackIn, bufferIn, entity);
        }
        RenderNameTagEvent renderNameplateEvent = new RenderNameTagEvent((Entity)entityIn, entityIn.m_5446_(), (EntityRenderer)this, matrixStackIn, bufferIn, packedLightIn, partialTicks);
        MinecraftForge.EVENT_BUS.post((Event)renderNameplateEvent);
        if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || this.m_6512_((Mob)entityIn))) {
            this.m_7649_((Entity)entityIn, renderNameplateEvent.getContent(), matrixStackIn, bufferIn, packedLightIn);
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post((LivingEntity)entityIn, (LivingEntityRenderer)this, partialTicks, matrixStackIn, bufferIn, packedLightIn));
    }

    private <E extends Entity> void renderLeash(EntityTiger tiger, float p_115463_, PoseStack p_115464_, MultiBufferSource p_115465_, E p_115466_) {
        p_115464_.m_85836_();
        Vec3 vec3 = p_115466_.m_7398_(p_115463_);
        double d0 = (double)(Mth.m_14179_((float)p_115463_, (float)tiger.f_20883_, (float)tiger.f_20884_) * ((float)Math.PI / 180)) + 1.5707963267948966;
        Vec3 vec31 = tiger.m_245894_(p_115463_);
        double d1 = Math.cos(d0) * vec31.f_82481_ + Math.sin(d0) * vec31.f_82479_;
        double d2 = Math.sin(d0) * vec31.f_82481_ - Math.cos(d0) * vec31.f_82479_;
        double d3 = Mth.m_14139_((double)p_115463_, (double)tiger.f_19854_, (double)tiger.m_20185_()) + d1;
        double d4 = Mth.m_14139_((double)p_115463_, (double)tiger.f_19855_, (double)tiger.m_20186_()) + vec31.f_82480_;
        double d5 = Mth.m_14139_((double)p_115463_, (double)tiger.f_19856_, (double)tiger.m_20189_()) + d2;
        p_115464_.m_85837_(d1, vec31.f_82480_, d2);
        float f = (float)(vec3.f_82479_ - d3);
        float f1 = (float)(vec3.f_82480_ - d4);
        float f2 = (float)(vec3.f_82481_ - d5);
        float f3 = 0.025f;
        VertexConsumer vertexconsumer = p_115465_.m_6299_(RenderType.m_110475_());
        Matrix4f matrix4f = p_115464_.m_85850_().m_252922_();
        float f4 = (float)(Mth.m_14193_((double)(f * f + f2 * f2)) * (double)0.025f / 2.0);
        float f5 = f2 * f4;
        float f6 = f * f4;
        BlockPos blockpos = AMBlockPos.fromVec3(tiger.m_20299_(p_115463_));
        BlockPos blockpos1 = AMBlockPos.fromVec3(p_115466_.m_20299_(p_115463_));
        int i = this.getBlockLightLevel(tiger, blockpos);
        int j = this.getBlockLightLevel(tiger, blockpos1);
        int k = tiger.m_9236_().m_45517_(LightLayer.SKY, blockpos);
        int l = tiger.m_9236_().m_45517_(LightLayer.SKY, blockpos1);
        for (int i1 = 0; i1 <= 24; ++i1) {
            RenderTiger.m_174307_(vertexconsumer, matrix4f, f, f1, f2, i, j, k, l, 0.025f, 0.025f, f5, f6, i1, false);
        }
        for (int j1 = 24; j1 >= 0; --j1) {
            RenderTiger.m_174307_(vertexconsumer, matrix4f, f, f1, f2, i, j, k, l, 0.025f, 0.0f, f5, f6, j1, true);
        }
        p_115464_.m_85849_();
    }

    protected int getBlockLightLevel(EntityTiger p_114496_, BlockPos p_114497_) {
        return p_114496_.m_6060_() ? 15 : p_114496_.m_9236_().m_45517_(LightLayer.BLOCK, p_114497_);
    }

    private static void m_174307_(VertexConsumer p_174308_, Matrix4f p_174309_, float p_174310_, float p_174311_, float p_174312_, int p_174313_, int p_174314_, int p_174315_, int p_174316_, float p_174317_, float p_174318_, float p_174319_, float p_174320_, int p_174321_, boolean p_174322_) {
        float f = (float)p_174321_ / 24.0f;
        int i = (int)Mth.m_14179_((float)f, (float)p_174313_, (float)p_174314_);
        int j = (int)Mth.m_14179_((float)f, (float)p_174315_, (float)p_174316_);
        int k = LightTexture.m_109885_((int)i, (int)j);
        float f1 = p_174321_ % 2 == (p_174322_ ? 1 : 0) ? 0.7f : 1.0f;
        float f2 = 0.5f * f1;
        float f3 = 0.4f * f1;
        float f4 = 0.3f * f1;
        float f5 = p_174310_ * f;
        float f6 = p_174311_ > 0.0f ? p_174311_ * f * f : p_174311_ - p_174311_ * (1.0f - f) * (1.0f - f);
        float f7 = p_174312_ * f;
        p_174308_.m_252986_(p_174309_, f5 - p_174319_, f6 + p_174318_, f7 + p_174320_).m_85950_(f2, f3, f4, 1.0f).m_85969_(k).m_5752_();
        p_174308_.m_252986_(p_174309_, f5 + p_174319_, f6 + p_174317_ - p_174318_, f7 - p_174320_).m_85950_(f2, f3, f4, 1.0f).m_85969_(k).m_5752_();
    }

    protected int getBlockLight2(Entity entityIn, BlockPos partialTicks) {
        return entityIn.m_6060_() ? 15 : entityIn.m_9236_().m_45517_(LightLayer.BLOCK, partialTicks);
    }

    @Nullable
    protected RenderType getRenderType(EntityTiger tiger, boolean b0, boolean b1, boolean b2) {
        if (tiger.isStealth()) {
            ResourceLocation resourcelocation = this.getTextureLocation(tiger);
            return RenderType.m_110467_((ResourceLocation)resourcelocation);
        }
        return super.m_7225_((LivingEntity)tiger, b0, b1, b2);
    }

    public ResourceLocation getTextureLocation(EntityTiger entity) {
        if (entity.m_5803_()) {
            return entity.isWhite() ? TEXTURE_SLEEPING_WHITE : TEXTURE_SLEEPING;
        }
        if (entity.m_6784_() > 0) {
            return entity.isWhite() ? TEXTURE_ANGRY_WHITE : TEXTURE_ANGRY;
        }
        return entity.isWhite() ? TEXTURE_WHITE : TEXTURE;
    }
}
