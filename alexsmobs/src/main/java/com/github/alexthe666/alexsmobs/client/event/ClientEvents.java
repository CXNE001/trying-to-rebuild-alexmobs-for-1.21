/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.event.EventGetFluidRenderType
 *  com.github.alexthe666.citadel.client.event.EventGetOutlineColor
 *  com.github.alexthe666.citadel.client.event.EventGetStarBrightness
 *  com.github.alexthe666.citadel.client.event.EventPosePlayerHand
 *  com.google.common.base.MoreObjects
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  com.mojang.math.Axis
 *  net.minecraft.CrashReport
 *  net.minecraft.CrashReportCategory
 *  net.minecraft.ReportedException
 *  net.minecraft.client.CameraType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.ItemInHandRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.LiquidBlockRenderer
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.npc.WanderingTrader
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.level.material.FogType
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.client.event.RenderGuiOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderLevelStageEvent
 *  net.minecraftforge.client.event.RenderLevelStageEvent$Stage
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderNameTagEvent
 *  net.minecraftforge.client.event.ViewportEvent$ComputeCameraAngles
 *  net.minecraftforge.client.event.ViewportEvent$ComputeFogColor
 *  net.minecraftforge.client.event.ViewportEvent$RenderFog
 *  net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  net.minecraftforge.eventbus.api.EventPriority
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package com.github.alexthe666.alexsmobs.client.event;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.ClientProxy;
import com.github.alexthe666.alexsmobs.client.model.ModelRockyChestplateRolling;
import com.github.alexthe666.alexsmobs.client.model.ModelWanderingVillagerRider;
import com.github.alexthe666.alexsmobs.client.model.layered.AMModelLayers;
import com.github.alexthe666.alexsmobs.client.render.AMItemstackRenderer;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.LavaVisionFluidRenderer;
import com.github.alexthe666.alexsmobs.client.render.RenderVineLasso;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.effect.EffectPowerDown;
import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import com.github.alexthe666.alexsmobs.entity.EntityBlueJay;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.github.alexthe666.alexsmobs.entity.IFalconry;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.alexsmobs.entity.util.RockyChestplateUtil;
import com.github.alexthe666.alexsmobs.entity.util.VineLassoUtil;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.ItemDimensionalCarver;
import com.github.alexthe666.alexsmobs.message.MessageUpdateEagleControls;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.client.event.EventGetFluidRenderType;
import com.github.alexthe666.citadel.client.event.EventGetOutlineColor;
import com.github.alexthe666.citadel.client.event.EventGetStarBrightness;
import com.github.alexthe666.citadel.client.event.EventPosePlayerHand;
import com.google.common.base.MoreObjects;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(value=Dist.CLIENT)
public class ClientEvents {
    private static final ResourceLocation ROCKY_CHESTPLATE_TEXTURE = new ResourceLocation("alexsmobs:textures/armor/rocky_chestplate.png");
    private static final ModelRockyChestplateRolling ROCKY_CHESTPLATE_MODEL = new ModelRockyChestplateRolling();
    private boolean previousLavaVision = false;
    private LiquidBlockRenderer previousFluidRenderer;
    public long lastStaticTick = -1L;
    public static int renderStaticScreenFor = 0;

    @SubscribeEvent
    public void onOutlineEntityColor(EventGetOutlineColor event) {
        EntityBlueJay jay;
        Entity entity;
        if (event.getEntityIn() instanceof Enemy && AlexsMobs.PROXY.getSingingBlueJayId() != -1 && (entity = event.getEntityIn().m_9236_().m_6815_(AlexsMobs.PROXY.getSingingBlueJayId())) instanceof EntityBlueJay && (jay = (EntityBlueJay)entity).m_6084_() && jay.isMakingMonstersBlue()) {
            event.setColor(4953598);
            event.setResult(Event.Result.ALLOW);
        }
        if (event.getEntityIn() instanceof ItemEntity && ((ItemEntity)event.getEntityIn()).m_32055_().m_204117_(AMTagRegistry.VOID_WORM_DROPS)) {
            int fromColor = 0;
            int toColor = 2221567;
            float startR = (float)(fromColor >> 16 & 0xFF) / 255.0f;
            float startG = (float)(fromColor >> 8 & 0xFF) / 255.0f;
            float startB = (float)(fromColor & 0xFF) / 255.0f;
            float endR = (float)(toColor >> 16 & 0xFF) / 255.0f;
            float endG = (float)(toColor >> 8 & 0xFF) / 255.0f;
            float endB = (float)(toColor & 0xFF) / 255.0f;
            float f = (float)(Math.cos(0.4f * ((float)event.getEntityIn().f_19797_ + Minecraft.m_91087_().m_91296_())) + 1.0) * 0.5f;
            float r = (endR - startR) * f + startR;
            float g = (endG - startG) * f + startG;
            float b = (endB - startB) * f + startB;
            int j = ((int)(r * 255.0f) & 0xFF) << 16 | ((int)(g * 255.0f) & 0xFF) << 8 | ((int)(b * 255.0f) & 0xFF) << 0;
            event.setColor(j);
            event.setResult(Event.Result.ALLOW);
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onGetStarBrightness(EventGetStarBrightness event) {
        if (Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.POWER_DOWN.get()) && Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)AMEffectRegistry.POWER_DOWN.get()) != null) {
            MobEffectInstance instance = Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)AMEffectRegistry.POWER_DOWN.get());
            EffectPowerDown powerDown = (EffectPowerDown)instance.m_19544_();
            int duration = instance.m_19557_();
            float partialTicks = Minecraft.m_91087_().m_91296_();
            float f = ((float)Math.min(powerDown.getActiveTime(), duration) + partialTicks) * 0.1f;
            event.setBrightness(0.0f);
            event.setResult(Event.Result.ALLOW);
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onFogColor(ViewportEvent.ComputeFogColor event) {
        if (Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.POWER_DOWN.get()) && Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)AMEffectRegistry.POWER_DOWN.get()) != null) {
            event.setBlue(0.0f);
            event.setRed(0.0f);
            event.setGreen(0.0f);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    @OnlyIn(value=Dist.CLIENT)
    public void onFogDensity(ViewportEvent.RenderFog event) {
        FogType fogType = event.getCamera().m_167685_();
        if (Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.LAVA_VISION.get()) && fogType == FogType.LAVA) {
            event.setNearPlaneDistance(-8.0f);
            event.setFarPlaneDistance(50.0f);
            event.setCanceled(true);
        }
        if (Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.POWER_DOWN.get()) && fogType == FogType.NONE && Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)AMEffectRegistry.POWER_DOWN.get()) != null) {
            float initEnd = event.getFarPlaneDistance();
            MobEffectInstance instance = Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)AMEffectRegistry.POWER_DOWN.get());
            EffectPowerDown powerDown = (EffectPowerDown)instance.m_19544_();
            int duration = instance.m_19557_();
            float partialTicks = Minecraft.m_91087_().m_91296_();
            float f = Math.min(20.0f, Math.min((float)powerDown.getActiveTime() + partialTicks, (float)duration + partialTicks)) * 0.05f;
            event.setNearPlaneDistance(-8.0f);
            float f1 = 8.0f + (1.0f - f) * Math.max(0.0f, initEnd - 8.0f);
            event.setFarPlaneDistance(f1);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onPreRenderEntity(RenderLivingEvent.Pre event) {
        if (RockyChestplateUtil.isRockyRolling(event.getEntity())) {
            event.setCanceled(true);
            event.getPoseStack().m_85836_();
            float limbSwing = event.getEntity().f_267362_.m_267756_() - event.getEntity().f_267362_.m_267731_() * (1.0f - event.getPartialTick());
            float limbSwingAmount = event.getEntity().f_267362_.m_267711_((float)event.getPackedLight());
            float yRot = event.getEntity().f_20884_ + (event.getEntity().f_20883_ - event.getEntity().f_20884_) * event.getPartialTick();
            float roll = event.getEntity().f_19867_ + (event.getEntity().f_19787_ - event.getEntity().f_19867_) * event.getPartialTick();
            VertexConsumer vertexconsumer = ItemRenderer.m_115184_((MultiBufferSource)event.getMultiBufferSource(), (RenderType)RenderType.m_110431_((ResourceLocation)ROCKY_CHESTPLATE_TEXTURE), (boolean)false, (boolean)event.getEntity().m_6844_(EquipmentSlot.CHEST).m_41790_());
            event.getPoseStack().m_85837_(0.0, (double)(event.getEntity().m_20206_() - event.getEntity().m_20206_() * 0.5f), 0.0);
            event.getPoseStack().m_252781_(Axis.f_252392_.m_252977_(180.0f + yRot));
            event.getPoseStack().m_252781_(Axis.f_252403_.m_252977_(180.0f));
            event.getPoseStack().m_252781_(Axis.f_252529_.m_252977_(100.0f * roll));
            ROCKY_CHESTPLATE_MODEL.setupAnim(event.getEntity(), limbSwing, limbSwingAmount, (float)event.getEntity().f_19797_ + event.getPartialTick(), 0.0f, 0.0f);
            ROCKY_CHESTPLATE_MODEL.m_7695_(event.getPoseStack(), vertexconsumer, event.getPackedLight(), OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            event.getPoseStack().m_85849_();
            MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
            return;
        }
        if (event.getEntity() instanceof WanderingTrader && event.getEntity().m_6095_() == EntityType.f_20494_ && event.getEntity().m_20202_() instanceof EntityElephant && !(event.getRenderer().f_115290_ instanceof ModelWanderingVillagerRider)) {
            event.getRenderer().f_115290_ = new ModelWanderingVillagerRider(Minecraft.m_91087_().m_167973_().m_171103_(AMModelLayers.SITTING_WANDERING_VILLAGER));
        }
        if (event.getEntity().m_21023_((MobEffect)AMEffectRegistry.CLINGING.get()) && event.getEntity().m_20192_() < event.getEntity().m_20206_() * 0.45f || event.getEntity().m_21023_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get()) && event.getEntity().m_6336_() == MobType.f_21642_ && event.getEntity().m_20205_() > event.getEntity().m_20206_()) {
            event.getPoseStack().m_85836_();
            event.getPoseStack().m_85837_(0.0, (double)(event.getEntity().m_20206_() + 0.1f), 0.0);
            event.getPoseStack().m_252781_(Axis.f_252403_.m_252977_(180.0f));
            event.getEntity().f_20884_ = -event.getEntity().f_20884_;
            event.getEntity().f_20883_ = -event.getEntity().f_20883_;
            event.getEntity().f_20886_ = -event.getEntity().f_20886_;
            event.getEntity().f_20885_ = -event.getEntity().f_20885_;
        }
        if (event.getEntity().m_21023_((MobEffect)AMEffectRegistry.ENDER_FLU.get())) {
            event.getPoseStack().m_85836_();
            event.getPoseStack().m_252781_(Axis.f_252436_.m_252977_((float)(Math.cos((double)event.getEntity().f_19797_ * 7.0) * Math.PI * (double)1.2f)));
            float vibrate = 0.05f;
            event.getPoseStack().m_252880_((event.getEntity().m_217043_().m_188501_() - 0.5f) * vibrate, (event.getEntity().m_217043_().m_188501_() - 0.5f) * vibrate, (event.getEntity().m_217043_().m_188501_() - 0.5f) * vibrate);
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onPostRenderEntity(RenderLivingEvent.Post event) {
        Entity lassoedOwner;
        if (RockyChestplateUtil.isRockyRolling(event.getEntity())) {
            return;
        }
        if (event.getEntity().m_21023_((MobEffect)AMEffectRegistry.ENDER_FLU.get())) {
            event.getPoseStack().m_85849_();
        }
        if (event.getEntity().m_21023_((MobEffect)AMEffectRegistry.CLINGING.get()) && event.getEntity().m_20192_() < event.getEntity().m_20206_() * 0.45f || event.getEntity().m_21023_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get()) && event.getEntity().m_6336_() == MobType.f_21642_ && event.getEntity().m_20205_() > event.getEntity().m_20206_()) {
            event.getPoseStack().m_85849_();
            event.getEntity().f_20884_ = -event.getEntity().f_20884_;
            event.getEntity().f_20883_ = -event.getEntity().f_20883_;
            event.getEntity().f_20886_ = -event.getEntity().f_20886_;
            event.getEntity().f_20885_ = -event.getEntity().f_20885_;
        }
        if (VineLassoUtil.hasLassoData(event.getEntity()) && !(event.getEntity() instanceof Player) && (lassoedOwner = VineLassoUtil.getLassoedTo(event.getEntity())) instanceof LivingEntity && lassoedOwner != event.getEntity()) {
            double d0 = Mth.m_14139_((double)event.getPartialTick(), (double)event.getEntity().f_19790_, (double)event.getEntity().m_20185_());
            double d1 = Mth.m_14139_((double)event.getPartialTick(), (double)event.getEntity().f_19791_, (double)event.getEntity().m_20186_());
            double d2 = Mth.m_14139_((double)event.getPartialTick(), (double)event.getEntity().f_19792_, (double)event.getEntity().m_20189_());
            event.getPoseStack().m_85836_();
            event.getPoseStack().m_85837_(-d0, -d1, -d2);
            RenderVineLasso.renderVine((Entity)event.getEntity(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), (LivingEntity)lassoedOwner, ((LivingEntity)lassoedOwner).m_5737_() == HumanoidArm.LEFT, 0.1f);
            event.getPoseStack().m_85849_();
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onPoseHand(EventPosePlayerHand event) {
        boolean usingLasso;
        LivingEntity player = (LivingEntity)event.getEntityIn();
        float f = Minecraft.m_91087_().m_91296_();
        boolean leftHand = false;
        boolean bl = usingLasso = player.m_6117_() && player.m_21211_().m_150930_((Item)AMItemRegistry.VINE_LASSO.get());
        if (player.m_21120_(InteractionHand.MAIN_HAND).m_41720_() == AMItemRegistry.VINE_LASSO.get()) {
            leftHand = player.m_5737_() == HumanoidArm.LEFT;
        } else if (player.m_21120_(InteractionHand.OFF_HAND).m_41720_() == AMItemRegistry.VINE_LASSO.get()) {
            boolean bl2 = leftHand = player.m_5737_() != HumanoidArm.LEFT;
        }
        if (leftHand && event.isLeftHand() && usingLasso) {
            event.setResult(Event.Result.ALLOW);
            event.getModel().f_102812_.f_104203_ = Maths.rad(-120.0) + Mth.m_14031_((float)((float)player.f_19797_ + f)) * 0.5f;
            event.getModel().f_102812_.f_104204_ = Maths.rad(-20.0) + Mth.m_14089_((float)((float)player.f_19797_ + f)) * 0.5f;
        }
        if (!leftHand && !event.isLeftHand() && usingLasso) {
            event.setResult(Event.Result.ALLOW);
            event.getModel().f_102811_.f_104203_ = Maths.rad(-120.0) + Mth.m_14031_((float)((float)player.f_19797_ + f)) * 0.5f;
            event.getModel().f_102811_.f_104204_ = Maths.rad(20.0) - Mth.m_14089_((float)((float)player.f_19797_ + f)) * 0.5f;
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onRenderHand(RenderHandEvent event) {
        if (Minecraft.m_91087_().m_91288_() instanceof IFalconry) {
            event.setCanceled(true);
        }
        if (!Minecraft.m_91087_().f_91074_.m_20197_().isEmpty() && event.getHand() == InteractionHand.MAIN_HAND) {
            LocalPlayer player = Minecraft.m_91087_().f_91074_;
            boolean leftHand = false;
            if (player.m_21120_(InteractionHand.MAIN_HAND).m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
                leftHand = player.m_5737_() == HumanoidArm.LEFT;
            } else if (player.m_21120_(InteractionHand.OFF_HAND).m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
                leftHand = player.m_5737_() != HumanoidArm.LEFT;
            }
            for (Entity entity : player.m_20197_()) {
                if (!(entity instanceof IFalconry)) continue;
                IFalconry falconry = (IFalconry)entity;
                float yaw = player.f_20884_ + (player.f_20883_ - player.f_20884_) * event.getPartialTick();
                ClientProxy.currentUnrenderedEntities.remove(entity.m_20148_());
                PoseStack matrixStackIn = event.getPoseStack();
                matrixStackIn.m_85836_();
                matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
                matrixStackIn.m_252880_(leftHand ? -falconry.getHandOffset() : falconry.getHandOffset(), -0.6f, -1.0f);
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(yaw));
                if (leftHand) {
                    matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
                } else {
                    matrixStackIn.m_252781_(Axis.f_252392_.m_252977_(90.0f));
                }
                this.renderEntity(entity, 0.0, 0.0, 0.0, 0.0f, event.getPartialTick(), matrixStackIn, event.getMultiBufferSource(), event.getPackedLight());
                matrixStackIn.m_85849_();
                ClientProxy.currentUnrenderedEntities.add(entity.m_20148_());
            }
        }
        if (Minecraft.m_91087_().f_91074_.m_21211_().m_41720_() instanceof ItemDimensionalCarver && event.getItemStack().m_41720_() instanceof ItemDimensionalCarver) {
            PoseStack matrixStackIn = event.getPoseStack();
            matrixStackIn.m_85836_();
            ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
            InteractionHand hand = (InteractionHand)MoreObjects.firstNonNull((Object)Minecraft.m_91087_().f_91074_.f_20912_, (Object)InteractionHand.MAIN_HAND);
            float f = Minecraft.m_91087_().f_91074_.m_21324_(event.getPartialTick());
            float f5 = -0.4f * Mth.m_14031_((float)(Mth.m_14116_((float)f) * (float)Math.PI));
            float f6 = 0.2f * Mth.m_14031_((float)(Mth.m_14116_((float)f) * ((float)Math.PI * 2)));
            float f10 = -0.2f * Mth.m_14031_((float)(f * (float)Math.PI));
            HumanoidArm handside = hand == InteractionHand.MAIN_HAND ? Minecraft.m_91087_().f_91074_.m_5737_() : Minecraft.m_91087_().f_91074_.m_5737_().m_20828_();
            boolean flag3 = handside == HumanoidArm.RIGHT;
            int l = flag3 ? 1 : -1;
            matrixStackIn.m_252880_((float)l * f5, f6, f10);
        }
    }

    public <E extends Entity> void renderEntity(E entityIn, double x, double y, double z, float yaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLight) {
        block4: {
            EntityRenderer render = null;
            EntityRenderDispatcher manager = Minecraft.m_91087_().m_91290_();
            try {
                render = manager.m_114382_(entityIn);
                if (render == null) break block4;
                try {
                    render.m_7392_(entityIn, yaw, partialTicks, matrixStack, bufferIn, packedLight);
                }
                catch (Throwable throwable1) {
                    throw new ReportedException(CrashReport.m_127521_((Throwable)throwable1, (String)"Rendering entity in world"));
                }
            }
            catch (Throwable throwable3) {
                CrashReport crashreport = CrashReport.m_127521_((Throwable)throwable3, (String)"Rendering entity in world");
                CrashReportCategory crashreportcategory = crashreport.m_127514_("Entity being rendered");
                entityIn.m_7976_(crashreportcategory);
                CrashReportCategory crashreportcategory1 = crashreport.m_127514_("Renderer details");
                crashreportcategory1.m_128159_("Assigned renderer", (Object)render);
                crashreportcategory1.m_128159_("Rotation", (Object)Float.valueOf(yaw));
                crashreportcategory1.m_128159_("Delta", (Object)Float.valueOf(partialTicks));
                throw new ReportedException(crashreport);
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onRenderNameplate(RenderNameTagEvent event) {
        if (Minecraft.m_91087_().m_91288_() instanceof EntityBaldEagle && event.getEntity() == Minecraft.m_91087_().f_91074_ && Minecraft.m_91087_().m_91091_()) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onRenderWorldLastEvent(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            if (!AMConfig.shadersCompat) {
                if (Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.LAVA_VISION.get())) {
                    if (!this.previousLavaVision) {
                        this.previousFluidRenderer = Minecraft.m_91087_().m_91289_().f_110901_;
                        Minecraft.m_91087_().m_91289_().f_110901_ = new LavaVisionFluidRenderer();
                        this.updateAllChunks();
                    }
                } else if (this.previousLavaVision) {
                    if (this.previousFluidRenderer != null) {
                        Minecraft.m_91087_().m_91289_().f_110901_ = this.previousFluidRenderer;
                    }
                    this.updateAllChunks();
                }
                this.previousLavaVision = Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.LAVA_VISION.get());
                if (AMConfig.clingingFlipEffect) {
                    if (Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.CLINGING.get()) && Minecraft.m_91087_().f_91074_.m_20192_() < Minecraft.m_91087_().f_91074_.m_20206_() * 0.45f) {
                        Minecraft.m_91087_().f_91063_.m_109128_(new ResourceLocation("shaders/post/flip.json"));
                    } else if (Minecraft.m_91087_().f_91063_.m_109149_() != null && Minecraft.m_91087_().f_91063_.m_109149_().m_110022_().equals("minecraft:shaders/post/flip.json")) {
                        Minecraft.m_91087_().f_91063_.m_109086_();
                    }
                }
            }
            if (Minecraft.m_91087_().m_91288_() instanceof EntityBaldEagle) {
                EntityBaldEagle eagle = (EntityBaldEagle)Minecraft.m_91087_().m_91288_();
                LocalPlayer playerEntity = Minecraft.m_91087_().f_91074_;
                if (((EntityBaldEagle)Minecraft.m_91087_().m_91288_()).shouldHoodedReturn() || eagle.m_213877_()) {
                    Minecraft.m_91087_().m_91118_((Entity)playerEntity);
                    Minecraft.m_91087_().f_91066_.m_92157_(CameraType.values()[AlexsMobs.PROXY.getPreviousPOV()]);
                } else {
                    float rotX = Mth.m_14177_((float)(playerEntity.m_146908_() + playerEntity.f_20885_));
                    float rotY = playerEntity.m_146909_();
                    Entity over = null;
                    if (Minecraft.m_91087_().f_91077_ instanceof EntityHitResult) {
                        over = ((EntityHitResult)Minecraft.m_91087_().f_91077_).m_82443_();
                    } else {
                        Minecraft.m_91087_().f_91077_ = null;
                    }
                    boolean loadChunks = playerEntity.m_9236_().m_46468_() % 10L == 0L;
                    ((EntityBaldEagle)Minecraft.m_91087_().m_91288_()).directFromPlayer(rotX, rotY, false, over);
                    AlexsMobs.NETWORK_WRAPPER.sendToServer((Object)new MessageUpdateEagleControls(Minecraft.m_91087_().m_91288_().m_19879_(), rotX, rotY, loadChunks, over == null ? -1 : over.m_19879_()));
                }
            }
        }
    }

    private void updateAllChunks() {
        if (Minecraft.m_91087_().f_91060_.f_109469_ != null) {
            int length = Minecraft.m_91087_().f_91060_.f_109469_.f_110843_.length;
            for (int i = 0; i < length; ++i) {
                Minecraft.m_91087_().f_91060_.f_109469_.f_110843_[i].f_112792_ = true;
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public void onGetFluidRenderType(EventGetFluidRenderType event) {
        if (Minecraft.m_91087_().f_91074_.m_21023_((MobEffect)AMEffectRegistry.LAVA_VISION.get()) && (event.getFluidState().m_192917_((Fluid)Fluids.f_76195_) || event.getFluidState().m_192917_((Fluid)Fluids.f_76194_))) {
            event.setRenderType(RenderType.m_110466_());
            event.setResult(Event.Result.ALLOW);
        }
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            AMItemstackRenderer.incrementTick();
        }
    }

    @SubscribeEvent
    public void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        if (Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)AMEffectRegistry.EARTHQUAKE.get()) != null && !Minecraft.m_91087_().m_91104_()) {
            int duration = Minecraft.m_91087_().f_91074_.m_21124_((MobEffect)AMEffectRegistry.EARTHQUAKE.get()).m_19557_();
            float f = ((float)Math.min(10, duration) + Minecraft.m_91087_().m_91296_()) * 0.1f;
            double intensity = (double)f * (Double)Minecraft.m_91087_().f_91066_.m_231924_().m_231551_();
            RandomSource rng = Minecraft.m_91087_().f_91074_.m_217043_();
            event.getCamera().m_90568_((double)(rng.m_188501_() * 0.1f) * intensity, (double)(rng.m_188501_() * 0.2f) * intensity, (double)(rng.m_188501_() * 0.4f) * intensity);
        }
    }

    @SubscribeEvent
    public void onPostGameOverlay(RenderGuiOverlayEvent.Post event) {
        if (renderStaticScreenFor > 0) {
            if (Minecraft.m_91087_().f_91074_.m_6084_() && this.lastStaticTick != Minecraft.m_91087_().f_91073_.m_46467_()) {
                --renderStaticScreenFor;
            }
            float staticLevel = (float)renderStaticScreenFor / 60.0f;
            if (event.getOverlay().id().equals((Object)VanillaGuiOverlay.HELMET.id())) {
                float screenWidth = event.getWindow().m_85443_();
                float screenHeight = event.getWindow().m_85444_();
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask((boolean)false);
                float ageInTicks = (float)Minecraft.m_91087_().f_91073_.m_46467_() + event.getPartialTick();
                float staticIndexX = (float)Math.sin(ageInTicks * 0.2f) * 2.0f;
                float staticIndexY = (float)Math.cos(ageInTicks * 0.2f + 3.0f) * 2.0f;
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShader(GameRenderer::m_172817_);
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)staticLevel);
                RenderSystem.setShaderTexture((int)0, (ResourceLocation)AMRenderTypes.STATIC_TEXTURE);
                Tesselator tesselator = Tesselator.m_85913_();
                BufferBuilder bufferbuilder = tesselator.m_85915_();
                bufferbuilder.m_166779_(VertexFormat.Mode.QUADS, DefaultVertexFormat.f_85817_);
                float minU = 10.0f * staticIndexX * 0.125f;
                float maxU = 10.0f * (0.5f + staticIndexX * 0.125f);
                float minV = 10.0f * staticIndexY * 0.125f;
                float maxV = 10.0f * (0.125f + staticIndexY * 0.125f);
                bufferbuilder.m_5483_(0.0, (double)screenHeight, -190.0).m_7421_(minU, maxV).m_5752_();
                bufferbuilder.m_5483_((double)screenWidth, (double)screenHeight, -190.0).m_7421_(maxU, maxV).m_5752_();
                bufferbuilder.m_5483_((double)screenWidth, 0.0, -190.0).m_7421_(maxU, minV).m_5752_();
                bufferbuilder.m_5483_(0.0, 0.0, -190.0).m_7421_(minU, minV).m_5752_();
                tesselator.m_85914_();
                RenderSystem.depthMask((boolean)true);
                RenderSystem.enableDepthTest();
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            this.lastStaticTick = Minecraft.m_91087_().f_91073_.m_46467_();
        }
    }
}
