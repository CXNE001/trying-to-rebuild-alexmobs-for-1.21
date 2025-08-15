/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.blaze3d.platform.Lighting
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.math.Axis
 *  net.minecraft.Util
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.MouseHandler
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateAnchor;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateAnchorWinch;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateShipWheel;
import com.github.alexthe666.alexsmobs.client.model.ModelMysteriousWorm;
import com.github.alexthe666.alexsmobs.client.model.ModelShieldOfTheDeep;
import com.github.alexthe666.alexsmobs.client.model.ModelTransmutationTable;
import com.github.alexthe666.alexsmobs.client.render.AMMobIcons;
import com.github.alexthe666.alexsmobs.client.render.RenderLaviathan;
import com.github.alexthe666.alexsmobs.client.render.RenderMurmurBody;
import com.github.alexthe666.alexsmobs.client.render.RenderUnderminer;
import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import com.github.alexthe666.alexsmobs.entity.EntityBlobfish;
import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import com.github.alexthe666.alexsmobs.entity.EntityCosmaw;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.EntityLaviathan;
import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.entity.EntityMurmur;
import com.github.alexthe666.alexsmobs.entity.EntityUnderminer;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWorm;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.ItemStinkRay;
import com.github.alexthe666.alexsmobs.item.ItemTabIcon;
import com.github.alexthe666.alexsmobs.item.ItemVineLasso;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;

public class AMItemstackRenderer
extends BlockEntityWithoutLevelRenderer {
    public static int ticksExisted = 0;
    private static final ModelShieldOfTheDeep SHIELD_OF_THE_DEEP_MODEL = new ModelShieldOfTheDeep();
    private static final ResourceLocation SHIELD_OF_THE_DEEP_TEXTURE = new ResourceLocation("alexsmobs:textures/armor/shield_of_the_deep.png");
    private static final ModelMysteriousWorm MYTERIOUS_WORM_MODEL = new ModelMysteriousWorm();
    private static final ResourceLocation MYTERIOUS_WORM_TEXTURE = new ResourceLocation("alexsmobs:textures/item/mysterious_worm_model.png");
    private static final ModelEndPirateAnchor ANCHOR_MODEL = new ModelEndPirateAnchor();
    private static final ResourceLocation ANCHOR_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/end_pirate/anchor.png");
    private static final ModelEndPirateAnchorWinch WINCH_MODEL = new ModelEndPirateAnchorWinch();
    private static final ResourceLocation WINCH_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/end_pirate/anchor_winch.png");
    private static final ModelEndPirateShipWheel SHIP_WHEEL_MODEL = new ModelEndPirateShipWheel();
    private static final ResourceLocation SHIP_WHEEL_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/end_pirate/ship_wheel.png");
    private static final ResourceLocation TRANSMUTATION_TABLE_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/farseer/transmutation_table.png");
    private static final ResourceLocation TRANSMUTATION_TABLE_GLOW_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/farseer/transmutation_table_glow.png");
    private static final ResourceLocation TRANSMUTATION_TABLE_OVERLAY = new ResourceLocation("alexsmobs:textures/entity/farseer/transmutation_table_overlay.png");
    private static final ModelTransmutationTable TRANSMUTATION_TABLE_MODEL = new ModelTransmutationTable(0.0f);
    private static final ModelTransmutationTable TRANSMUTATION_TABLE_OVERLAY_MODEL = new ModelTransmutationTable(0.01f);
    private static List<ItemStack> DIMENSIONAL_CARVER_SHARDS;
    private final Map<String, Entity> renderedEntites = new HashMap<String, Entity>();
    private final List<EntityType> blockedRenderEntities = new ArrayList<EntityType>();

    public AMItemstackRenderer() {
        super(null, null);
    }

    public static void incrementTick() {
        ++ticksExisted;
    }

    private static float getScaleFor(EntityType type, List<Pair<EntityType, Float>> mobIcons) {
        for (Pair<EntityType, Float> pair : mobIcons) {
            if (pair.getFirst() != type) continue;
            return ((Float)pair.getSecond()).floatValue();
        }
        return 1.0f;
    }

    private static List<ItemStack> getDimensionalCarverShards() {
        if (DIMENSIONAL_CARVER_SHARDS == null || DIMENSIONAL_CARVER_SHARDS.isEmpty()) {
            DIMENSIONAL_CARVER_SHARDS = (List)Util.m_137469_((Object)Lists.newArrayList(), list -> {
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_0"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_1"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_2"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_3"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_4"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_5"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_6"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_7"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_8"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_9"))));
                list.add(new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexsmobs:dimensional_carver_shard_10"))));
            });
        }
        return DIMENSIONAL_CARVER_SHARDS;
    }

    public static void drawEntityOnScreen(PoseStack matrixstack, int posX, int posY, float scale, boolean follow, double xRot, double yRot, double zRot, float mouseX, float mouseY, Entity entity) {
        float f = (float)Math.atan(-mouseX / 40.0f);
        float f1 = (float)Math.atan(mouseY / 40.0f);
        matrixstack.m_85841_(scale, scale, scale);
        entity.m_6853_(false);
        float partialTicks = Minecraft.m_91087_().m_91296_();
        Quaternionf quaternion = Axis.f_252403_.m_252977_(180.0f);
        Quaternionf quaternion1 = Axis.f_252529_.m_252977_(20.0f);
        float partialTicksForRender = Minecraft.m_91087_().m_91104_() || entity instanceof EntityMimicOctopus ? 0.0f : partialTicks;
        int tick = Minecraft.m_91087_().f_91074_ == null || Minecraft.m_91087_().m_91104_() ? ticksExisted : Minecraft.m_91087_().f_91074_.f_19797_;
        if (follow) {
            float yaw = f * 45.0f;
            entity.m_146922_(yaw);
            entity.f_19797_ = tick;
            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).f_20883_ = yaw;
                ((LivingEntity)entity).f_20884_ = yaw;
                ((LivingEntity)entity).f_20885_ = yaw;
                ((LivingEntity)entity).f_20886_ = yaw;
            }
            quaternion1 = Axis.f_252529_.m_252977_(f1 * 20.0f);
            quaternion.mul((Quaternionfc)quaternion1);
        }
        matrixstack.m_252781_(quaternion);
        matrixstack.m_252781_(Axis.f_252529_.m_252977_((float)(-xRot)));
        matrixstack.m_252781_(Axis.f_252436_.m_252977_((float)yRot));
        matrixstack.m_252781_(Axis.f_252403_.m_252977_((float)zRot));
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.m_91087_().m_91290_();
        quaternion1.conjugate();
        entityrenderdispatcher.m_252923_(quaternion1);
        entityrenderdispatcher.m_114468_(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.m_91087_().m_91269_().m_110104_();
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.m_114384_(entity, 0.0, 0.0, 0.0, 0.0f, partialTicksForRender, matrixstack, (MultiBufferSource)multibuffersource$buffersource, 0xF000F0));
        multibuffersource$buffersource.m_109911_();
        entityrenderdispatcher.m_114468_(true);
        entity.m_146922_(0.0f);
        entity.m_146926_(0.0f);
        if (entity instanceof LivingEntity) {
            ((LivingEntity)entity).f_20883_ = 0.0f;
            ((LivingEntity)entity).f_20886_ = 0.0f;
            ((LivingEntity)entity).f_20885_ = 0.0f;
        }
        RenderSystem.applyModelViewMatrix();
        Lighting.m_84931_();
    }

    public void m_108829_(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        int tick = Minecraft.m_91087_().f_91074_ == null || Minecraft.m_91087_().m_91104_() ? ticksExisted : Minecraft.m_91087_().f_91074_.f_19797_;
        ClientLevel level = Minecraft.m_91087_().f_91073_;
        if (itemStackIn.m_41720_() == AMItemRegistry.SHIELD_OF_THE_DEEP.get()) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.4f, -0.75f, 0.5f);
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-180.0f));
            VertexConsumer vertexconsumer = ItemRenderer.m_115184_((MultiBufferSource)bufferIn, (RenderType)RenderType.m_110431_((ResourceLocation)SHIELD_OF_THE_DEEP_TEXTURE), (boolean)false, (boolean)itemStackIn.m_41790_());
            SHIELD_OF_THE_DEEP_MODEL.m_7695_(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
        }
        if (itemStackIn.m_41720_() == AMItemRegistry.MYSTERIOUS_WORM.get()) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.0f, -2.0f, 0.0f);
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-180.0f));
            MYTERIOUS_WORM_MODEL.animateStack(itemStackIn);
            MYTERIOUS_WORM_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)MYTERIOUS_WORM_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
        }
        if (itemStackIn.m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
            matrixStackIn.m_252880_(0.5f, 0.5f, 0.5f);
            if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
                Minecraft.m_91087_().m_91291_().m_269128_(new ItemStack((ItemLike)AMItemRegistry.FALCONRY_GLOVE_HAND.get()), transformType, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            } else {
                Minecraft.m_91087_().m_91291_().m_269128_(new ItemStack((ItemLike)AMItemRegistry.FALCONRY_GLOVE_INVENTORY.get()), transformType, transformType == ItemDisplayContext.GROUND ? combinedLightIn : 240, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            }
        }
        if (itemStackIn.m_41720_() == AMItemRegistry.VINE_LASSO.get()) {
            matrixStackIn.m_252880_(0.5f, 0.5f, 0.5f);
            if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
                if (ItemVineLasso.isItemInUse(itemStackIn)) {
                    if (transformType.m_269069_()) {
                        matrixStackIn.m_252880_(transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ? -0.3f : 0.3f, 0.0f, -0.5f);
                    }
                    matrixStackIn.m_252781_(Axis.f_252436_.m_252961_((float)tick + Minecraft.m_91087_().m_91296_()));
                }
                Minecraft.m_91087_().m_91291_().m_269128_(new ItemStack((ItemLike)AMItemRegistry.VINE_LASSO_HAND.get()), transformType, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            } else {
                Minecraft.m_91087_().m_91291_().m_269128_(new ItemStack((ItemLike)AMItemRegistry.VINE_LASSO_INVENTORY.get()), transformType, transformType == ItemDisplayContext.GROUND ? combinedLightIn : 240, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            }
        }
        if (itemStackIn.m_41720_() == AMItemRegistry.SKELEWAG_SWORD.get()) {
            matrixStackIn.m_252880_(0.5f, 0.5f, 0.5f);
            ItemStack spriteItem = new ItemStack((ItemLike)AMItemRegistry.SKELEWAG_SWORD_INVENTORY.get());
            ItemStack handItem = new ItemStack((ItemLike)AMItemRegistry.SKELEWAG_SWORD_HAND.get());
            spriteItem.m_41751_(itemStackIn.m_41783_());
            handItem.m_41751_(itemStackIn.m_41783_());
            if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
                Minecraft.m_91087_().m_91291_().m_269128_(handItem, transformType, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            } else {
                Minecraft.m_91087_().m_91291_().m_269128_(spriteItem, transformType, transformType == ItemDisplayContext.GROUND ? combinedLightIn : 240, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            }
        }
        if (itemStackIn.m_41720_() == ((Block)AMBlockRegistry.TRANSMUTATION_TABLE.get()).m_5456_()) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.5f, 1.6f, 0.5f);
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-180.0f));
            TRANSMUTATION_TABLE_MODEL.resetToDefaultPose();
            TRANSMUTATION_TABLE_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TRANSMUTATION_TABLE_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            TRANSMUTATION_TABLE_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_234338_((ResourceLocation)TRANSMUTATION_TABLE_GLOW_TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            TRANSMUTATION_TABLE_OVERLAY_MODEL.resetToDefaultPose();
            VertexConsumer staticyOverlay = bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)TRANSMUTATION_TABLE_OVERLAY));
            TRANSMUTATION_TABLE_OVERLAY_MODEL.m_7695_(matrixStackIn, staticyOverlay, combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
        }
        if (itemStackIn.m_41720_() == AMItemRegistry.SHATTERED_DIMENSIONAL_CARVER.get()) {
            matrixStackIn.m_252880_(0.5f, 0.5f, 0.5f);
            float f = (float)tick + Minecraft.m_91087_().m_91296_();
            List<ItemStack> shards = AMItemstackRenderer.getDimensionalCarverShards();
            matrixStackIn.m_85836_();
            if (transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
                matrixStackIn.m_252880_(-0.2f, 0.0f, 0.0f);
                matrixStackIn.m_85841_(1.3f, 1.3f, 1.3f);
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
                matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(60.0f));
            }
            for (int i = 0; i < shards.size(); ++i) {
                matrixStackIn.m_85836_();
                ItemStack shard = shards.get(i);
                matrixStackIn.m_252880_((float)Math.sin(f * 0.15f + (float)i * 1.0f) * 0.035f, -((float)Math.cos(f * 0.15f + (float)i * 1.0f)) * 0.035f, (float)Math.cos((double)(f * 0.15f + (float)i * 0.5f) + 1.5707963267948966) * 0.025f);
                Minecraft.m_91087_().m_91291_().m_269128_(shard, transformType, transformType == ItemDisplayContext.GROUND ? combinedLightIn : 240, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
                matrixStackIn.m_85849_();
            }
            matrixStackIn.m_85849_();
        }
        if (itemStackIn.m_41720_() == AMItemRegistry.STINK_RAY.get()) {
            matrixStackIn.m_252880_(0.5f, 0.5f, 0.5f);
            ItemStack hand = new ItemStack(ItemStinkRay.isUsable(itemStackIn) ? (ItemLike)AMItemRegistry.STINK_RAY_HAND.get() : (ItemLike)AMItemRegistry.STINK_RAY_EMPTY_HAND.get());
            ItemStack inventory = new ItemStack(ItemStinkRay.isUsable(itemStackIn) ? (ItemLike)AMItemRegistry.STINK_RAY_INVENTORY.get() : (ItemLike)AMItemRegistry.STINK_RAY_EMPTY_INVENTORY.get());
            if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
                Minecraft.m_91087_().m_91291_().m_269128_(hand, transformType, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            } else {
                Minecraft.m_91087_().m_91291_().m_269128_(inventory, transformType, transformType == ItemDisplayContext.GROUND ? combinedLightIn : 240, combinedOverlayIn, matrixStackIn, bufferIn, (Level)level, 0);
            }
        }
        if (itemStackIn.m_41720_() == AMItemRegistry.TAB_ICON.get()) {
            Entity fakeEntity = null;
            List<Pair<EntityType, Float>> mobIcons = AMMobIcons.getMobIcons();
            int entityIndex = tick / 40 % mobIcons.size();
            float scale = 1.0f;
            int flags = 0;
            if (level != null) {
                if (ItemTabIcon.hasCustomEntityDisplay(itemStackIn)) {
                    flags = itemStackIn.m_41783_().m_128451_("DisplayMobFlags");
                    String index = ItemTabIcon.getCustomDisplayEntityString(itemStackIn);
                    EntityType local = ItemTabIcon.getEntityType(itemStackIn.m_41783_());
                    scale = AMItemstackRenderer.getScaleFor(local, mobIcons);
                    if (itemStackIn.m_41783_().m_128457_("DisplayMobScale") > 0.0f) {
                        scale = itemStackIn.m_41783_().m_128457_("DisplayMobScale");
                    }
                    if (this.renderedEntites.get(index) == null && !this.blockedRenderEntities.contains(local)) {
                        try {
                            Entity entity = local.m_20615_((Level)level);
                            if (entity instanceof EntityBlobfish) {
                                ((EntityBlobfish)entity).setDepressurized(true);
                            }
                            this.renderedEntites.put(local.m_20675_(), entity);
                            fakeEntity = entity;
                        }
                        catch (Exception e) {
                            this.blockedRenderEntities.add(local);
                            AlexsMobs.LOGGER.error("Could not render item for entity: " + local);
                        }
                    } else {
                        fakeEntity = this.renderedEntites.get(local.m_20675_());
                    }
                } else {
                    EntityType type = (EntityType)mobIcons.get(entityIndex).getFirst();
                    scale = ((Float)mobIcons.get(entityIndex).getSecond()).floatValue();
                    if (type != null) {
                        if (this.renderedEntites.get(type.m_20675_()) == null && !this.blockedRenderEntities.contains(type)) {
                            try {
                                Entity entity = type.m_20615_((Level)level);
                                if (entity instanceof EntityBlobfish) {
                                    ((EntityBlobfish)entity).setDepressurized(true);
                                }
                                this.renderedEntites.put(type.m_20675_(), entity);
                                fakeEntity = entity;
                            }
                            catch (Exception e) {
                                this.blockedRenderEntities.add(type);
                                AlexsMobs.LOGGER.error("Could not render item for entity: " + type);
                            }
                        } else {
                            fakeEntity = this.renderedEntites.get(type.m_20675_());
                        }
                    }
                }
            }
            if (fakeEntity instanceof EntityCockroach) {
                if (flags == 99) {
                    matrixStackIn.m_252880_(0.0f, 0.25f, 0.0f);
                    matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-80.0f));
                    ((EntityCockroach)fakeEntity).setMaracas(true);
                } else {
                    ((EntityCockroach)fakeEntity).setMaracas(false);
                }
            }
            if (fakeEntity instanceof EntityElephant) {
                if (flags == 99) {
                    ((EntityElephant)fakeEntity).setTusked(true);
                    ((EntityElephant)fakeEntity).setColor(null);
                } else if (flags == 98) {
                    ((EntityElephant)fakeEntity).setTusked(false);
                    ((EntityElephant)fakeEntity).setColor(DyeColor.BROWN);
                } else {
                    ((EntityElephant)fakeEntity).setTusked(false);
                    ((EntityElephant)fakeEntity).setColor(null);
                }
            }
            if (fakeEntity instanceof EntityBaldEagle) {
                if (flags == 98) {
                    ((EntityBaldEagle)fakeEntity).setCap(true);
                } else {
                    ((EntityBaldEagle)fakeEntity).setCap(false);
                }
            }
            if (fakeEntity instanceof EntityVoidWorm) {
                matrixStackIn.m_252880_(0.0f, 0.5f, 0.0f);
            }
            if (fakeEntity instanceof EntityMimicOctopus) {
                matrixStackIn.m_252880_(0.0f, 0.5f, 0.0f);
            }
            if (fakeEntity instanceof EntityLaviathan) {
                RenderLaviathan.renderWithoutShaking = true;
                matrixStackIn.m_252880_(0.0f, 0.3f, 0.0f);
            }
            if (fakeEntity instanceof EntityCosmaw) {
                matrixStackIn.m_252880_(0.0f, 0.2f, 0.0f);
            }
            if (fakeEntity instanceof EntityGiantSquid) {
                matrixStackIn.m_252880_(0.0f, 0.5f, 0.3f);
            }
            if (fakeEntity instanceof EntityUnderminer) {
                RenderUnderminer.renderWithPickaxe = true;
            }
            if (fakeEntity instanceof EntityMurmur) {
                RenderMurmurBody.renderWithHead = true;
                matrixStackIn.m_252880_(0.0f, -0.2f, 0.0f);
            }
            if (fakeEntity != null) {
                MouseHandler mouseHelper = Minecraft.m_91087_().f_91067_;
                double mouseX = mouseHelper.m_91589_() * (double)Minecraft.m_91087_().m_91268_().m_85445_() / (double)Minecraft.m_91087_().m_91268_().m_85443_();
                double mouseY = mouseHelper.m_91594_() * (double)Minecraft.m_91087_().m_91268_().m_85446_() / (double)Minecraft.m_91087_().m_91268_().m_85444_();
                matrixStackIn.m_252880_(0.5f, 0.0f, 0.0f);
                matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
                if (transformType != ItemDisplayContext.GUI) {
                    mouseX = 0.0;
                    mouseY = 0.0;
                }
                try {
                    AMItemstackRenderer.drawEntityOnScreen(matrixStackIn, 0, 0, scale, true, 0.0, -45.0, 0.0, (float)mouseX, (float)mouseY, fakeEntity);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (fakeEntity instanceof EntityLaviathan) {
                RenderLaviathan.renderWithoutShaking = false;
            }
            if (fakeEntity instanceof EntityUnderminer) {
                RenderUnderminer.renderWithPickaxe = false;
            }
            if (fakeEntity instanceof EntityMurmur) {
                RenderMurmurBody.renderWithHead = false;
            }
        }
    }
}
