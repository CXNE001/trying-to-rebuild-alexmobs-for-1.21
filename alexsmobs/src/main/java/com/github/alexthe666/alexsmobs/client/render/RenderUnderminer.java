/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  javax.annotation.Nullable
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
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.BlockAndTintGetter
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderNameTagEvent
 *  net.minecraftforge.client.model.data.ModelData
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelUnderminerDwarf;
import com.github.alexthe666.alexsmobs.client.model.layered.AMModelLayers;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerUnderminerItem;
import com.github.alexthe666.alexsmobs.entity.EntityUnderminer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
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
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class RenderUnderminer
extends MobRenderer<EntityUnderminer, EntityModel<EntityUnderminer>> {
    private static final ResourceLocation TEXTURE_DWARF = new ResourceLocation("alexsmobs:textures/entity/underminer_dwarf.png");
    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/underminer_0.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/underminer_1.png");
    public static final List<ResourceLocation> BREAKING_LOCATIONS = IntStream.range(0, 10).mapToObj(destroyStage -> new ResourceLocation("alexsmobs:textures/block/ghostly_pickaxe/destroy_stage_" + destroyStage + ".png")).collect(Collectors.toList());
    private static final ModelUnderminerDwarf DWARF_MODEL = new ModelUnderminerDwarf();
    private static HumanoidModel<EntityUnderminer> NORMAL_MODEL = null;
    private static final List<RenderType> DESTROY_TYPES = BREAKING_LOCATIONS.stream().map(AMRenderTypes::getGhostCrumbling).collect(Collectors.toList());
    public static boolean renderWithPickaxe = false;

    public RenderUnderminer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)DWARF_MODEL, 0.4f);
        NORMAL_MODEL = new HumanoidModel(Minecraft.m_91087_().m_167973_().m_171103_(AMModelLayers.UNDERMINER));
        this.m_115326_(new LayerUnderminerItem(this));
    }

    protected void scale(EntityUnderminer entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.925f, 0.925f, 0.925f);
    }

    public boolean shouldRender(EntityUnderminer livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        BlockPos pos;
        if (super.m_5523_((Mob)livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        if (livingEntityIn.getMiningPos() != null && (pos = livingEntityIn.getMiningPos()) != null) {
            Vec3 vector3d = Vec3.m_82528_((Vec3i)pos);
            Vec3 vector3dCorner = Vec3.m_82528_((Vec3i)pos).m_82520_(1.0, 1.0, 1.0);
            return camera.m_113029_(new AABB(vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_, vector3dCorner.f_82479_, vector3dCorner.f_82480_, vector3dCorner.f_82481_));
        }
        return false;
    }

    protected float getFlipDegrees(EntityUnderminer entityUnderminer) {
        return 0.0f;
    }

    public void render(EntityUnderminer entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        Direction direction;
        boolean shouldSit;
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre((LivingEntity)entityIn, (LivingEntityRenderer)this, partialTicks, matrixStackIn, bufferIn, packedLightIn))) {
            return;
        }
        matrixStackIn.m_85836_();
        this.f_115290_.f_102608_ = this.m_115342_((LivingEntity)entityIn, partialTicks);
        this.f_115290_.f_102609_ = shouldSit = entityIn.m_20159_() && entityIn.m_20202_() != null && entityIn.m_20202_().shouldRiderSit();
        this.f_115290_.f_102610_ = entityIn.m_6162_();
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
        this.f_115290_ = entityIn.isDwarf() ? DWARF_MODEL : NORMAL_MODEL;
        this.f_115290_.m_6839_((Entity)entityIn, f5, f8, partialTicks);
        this.f_115290_.m_6973_((Entity)entityIn, f5, f8, f7, f2, f6);
        Minecraft minecraft = Minecraft.m_91087_();
        boolean flag = this.m_5933_((LivingEntity)entityIn);
        boolean flag1 = !flag && !entityIn.m_20177_((Player)minecraft.f_91074_);
        boolean flag2 = minecraft.m_91314_((Entity)entityIn);
        RenderType rendertype = this.getRenderType(entityIn, flag, flag1, flag2);
        if (rendertype != null && !entityIn.isFullyHidden()) {
            float hide = (entityIn.prevHidingProgress + (entityIn.hidingProgress - entityIn.prevHidingProgress) * partialTicks) * 0.1f;
            float alpha = (1.0f - hide) * 0.6f;
            this.f_114477_ = 0.9f * alpha;
            int i = RenderUnderminer.m_115338_((LivingEntity)entityIn, (float)this.m_6931_((LivingEntity)entityIn, partialTicks));
            this.renderUnderminerModel(matrixStackIn, bufferIn, rendertype, partialTicks, packedLightIn, i, flag1 ? 0.15f : Mth.m_14036_((float)alpha, (float)0.0f, (float)1.0f), entityIn);
        } else {
            this.f_114477_ = 0.0f;
        }
        if (!entityIn.m_5833_()) {
            for (RenderLayer layerrenderer : this.f_115291_) {
                layerrenderer.m_6494_(matrixStackIn, bufferIn, packedLightIn, (Entity)entityIn, f5, f8, partialTicks, f7, f2, f6);
            }
        }
        matrixStackIn.m_85849_();
        RenderNameTagEvent renderNameplateEvent = new RenderNameTagEvent((Entity)entityIn, entityIn.m_5446_(), (EntityRenderer)this, matrixStackIn, bufferIn, packedLightIn, partialTicks);
        MinecraftForge.EVENT_BUS.post((Event)renderNameplateEvent);
        if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || this.m_6512_((Mob)entityIn))) {
            this.m_7649_((Entity)entityIn, renderNameplateEvent.getContent(), matrixStackIn, bufferIn, packedLightIn);
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post((LivingEntity)entityIn, (LivingEntityRenderer)this, partialTicks, matrixStackIn, bufferIn, packedLightIn));
        BlockPos miningPos = entityIn.getMiningPos();
        if (miningPos != null) {
            matrixStackIn.m_85836_();
            double d0 = Mth.m_14139_((double)partialTicks, (double)entityIn.f_19854_, (double)entityIn.m_20185_());
            double d1 = Mth.m_14139_((double)partialTicks, (double)entityIn.f_19855_, (double)entityIn.m_20186_());
            double d2 = Mth.m_14139_((double)partialTicks, (double)entityIn.f_19856_, (double)entityIn.m_20189_());
            matrixStackIn.m_85837_((double)miningPos.m_123341_() - d0, (double)miningPos.m_123342_() - d1, (double)miningPos.m_123343_() - d2);
            int progress = Math.round((float)(DESTROY_TYPES.size() - 1) * Mth.m_14036_((float)entityIn.getMiningProgress(), (float)0.0f, (float)1.0f));
            PoseStack.Pose posestack$pose = matrixStackIn.m_85850_();
            SheetedDecalTextureGenerator vertexconsumer1 = new SheetedDecalTextureGenerator(bufferIn.m_6299_(DESTROY_TYPES.get(progress)), posestack$pose.m_252922_(), posestack$pose.m_252943_(), 1.0f);
            ModelData modelData = entityIn.m_9236_().getModelDataManager().getAt(miningPos);
            Minecraft.m_91087_().m_91289_().renderBreakingTexture(entityIn.m_9236_().m_8055_(miningPos), miningPos, (BlockAndTintGetter)entityIn.m_9236_(), matrixStackIn, (VertexConsumer)vertexconsumer1, modelData == null ? ModelData.EMPTY : modelData);
            matrixStackIn.m_85849_();
        }
    }

    private void renderUnderminerModel(PoseStack matrixStackIn, MultiBufferSource source, RenderType defRenderType, float partialTicks, int packedLightIn, int overlayColors, float alphaIn, EntityUnderminer entityIn) {
        boolean hurt = Math.max(entityIn.f_20916_, entityIn.f_20919_) > 0;
        this.f_115290_.m_7695_(matrixStackIn, source.m_6299_(defRenderType), packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entityIn, (float)0.0f), hurt ? 0.4f : 1.0f, hurt ? 0.8f : 1.0f, hurt ? 0.7f : 1.0f, alphaIn);
    }

    @Nullable
    protected RenderType getRenderType(EntityUnderminer farseer, boolean normal, boolean invis, boolean outline) {
        ResourceLocation resourcelocation = this.getTextureLocation(farseer);
        return outline ? RenderType.m_110491_((ResourceLocation)resourcelocation) : AMRenderTypes.getUnderminer(resourcelocation);
    }

    public ResourceLocation getTextureLocation(EntityUnderminer entity) {
        return entity.isDwarf() ? TEXTURE_DWARF : (entity.getVariant() == 0 ? TEXTURE_0 : TEXTURE_1);
    }
}
