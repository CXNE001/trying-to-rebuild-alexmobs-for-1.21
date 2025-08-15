/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.Model
 *  net.minecraft.client.model.geom.ModelLayers
 *  net.minecraft.client.renderer.ItemInHandRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.DyeableLeatherItem
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.joml.Quaternionf
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelKangaroo;
import com.github.alexthe666.alexsmobs.client.render.RenderKangaroo;
import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.ForgeHooksClient;
import org.joml.Quaternionf;

public class LayerKangarooArmor
extends RenderLayer<EntityKangaroo, ModelKangaroo> {
    private static final Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();
    private final HumanoidModel defaultBipedModel;
    private final RenderKangaroo renderer;

    public LayerKangarooArmor(RenderKangaroo render, EntityRendererProvider.Context context) {
        super((RenderLayerParent)render);
        this.defaultBipedModel = new HumanoidModel(context.m_174023_(ModelLayers.f_171261_));
        this.renderer = render;
    }

    public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
        ArmorItem item = (ArmorItem)stack.m_41720_();
        String texture = item.m_40401_().m_6082_();
        String domain = "minecraft";
        int idx = texture.indexOf(58);
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }
        String s1 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, 1, type == null ? "" : String.format("_%s", type));
        ResourceLocation resourcelocation = ARMOR_TEXTURE_RES_MAP.get(s1 = ForgeHooksClient.getArmorTexture((Entity)entity, (ItemStack)stack, (String)s1, (EquipmentSlot)slot, (String)type));
        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            ARMOR_TEXTURE_RES_MAP.put(s1, resourcelocation);
        }
        return resourcelocation;
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityKangaroo roo, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStackIn.m_85836_();
        if (roo.isRoger()) {
            ItemStack haloStack = new ItemStack((ItemLike)AMItemRegistry.HALO.get());
            matrixStackIn.m_85836_();
            this.translateToHead(matrixStackIn);
            float f = 0.1f * (float)Math.sin(((float)roo.f_19797_ + partialTicks) * 0.1f) + (roo.m_6162_() ? 0.2f : 0.0f);
            matrixStackIn.m_252880_(0.0f, -0.75f - f, -0.2f);
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
            matrixStackIn.m_85841_(1.3f, 1.3f, 1.3f);
            ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
            renderer.m_269530_((LivingEntity)roo, haloStack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.m_85849_();
        }
        if (!roo.m_6162_()) {
            ArmorItem armoritem;
            float f2;
            float f1;
            float f;
            int i;
            int clampedLight;
            boolean flag1;
            boolean notAVanillaModel;
            HumanoidModel<?> a;
            matrixStackIn.m_85836_();
            ItemStack itemstack = roo.m_6844_(EquipmentSlot.HEAD);
            if (itemstack.m_41720_() instanceof ArmorItem) {
                ArmorItem armoritem2 = (ArmorItem)itemstack.m_41720_();
                if (itemstack.canEquip(EquipmentSlot.HEAD, (Entity)roo)) {
                    a = this.defaultBipedModel;
                    notAVanillaModel = (a = this.getArmorModelHook((LivingEntity)roo, itemstack, EquipmentSlot.HEAD, (HumanoidModel)a)) != this.defaultBipedModel;
                    this.setModelSlotVisible(a, EquipmentSlot.HEAD);
                    this.translateToHead(matrixStackIn);
                    matrixStackIn.m_252880_(0.0f, 0.015f, -0.05f);
                    if (itemstack.m_41720_() == AMItemRegistry.FEDORA.get()) {
                        matrixStackIn.m_252880_(0.0f, 0.05f, 0.0f);
                    }
                    matrixStackIn.m_85841_(0.7f, 0.7f, 0.7f);
                    flag1 = itemstack.m_41790_();
                    clampedLight = packedLightIn;
                    if (armoritem2 instanceof DyeableLeatherItem) {
                        i = ((DyeableLeatherItem)armoritem2).m_41121_(itemstack);
                        f = (float)(i >> 16 & 0xFF) / 255.0f;
                        f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                        f2 = (float)(i & 0xFF) / 255.0f;
                        this.renderHelmet(roo, matrixStackIn, bufferIn, clampedLight, flag1, a, f, f1, f2, LayerKangarooArmor.getArmorResource((Entity)roo, itemstack, EquipmentSlot.HEAD, null), notAVanillaModel);
                        this.renderHelmet(roo, matrixStackIn, bufferIn, clampedLight, flag1, a, 1.0f, 1.0f, 1.0f, LayerKangarooArmor.getArmorResource((Entity)roo, itemstack, EquipmentSlot.HEAD, "overlay"), notAVanillaModel);
                    } else {
                        this.renderHelmet(roo, matrixStackIn, bufferIn, clampedLight, flag1, a, 1.0f, 1.0f, 1.0f, LayerKangarooArmor.getArmorResource((Entity)roo, itemstack, EquipmentSlot.HEAD, null), notAVanillaModel);
                    }
                }
            } else {
                this.translateToHead(matrixStackIn);
                matrixStackIn.m_85837_(0.0, -0.2, (double)-0.1f);
                matrixStackIn.m_252781_(new Quaternionf().rotateX((float)Math.PI));
                matrixStackIn.m_252781_(new Quaternionf().rotateY((float)Math.PI));
                matrixStackIn.m_85841_(1.0f, 1.0f, 1.0f);
                Minecraft.m_91087_().m_91291_().m_269128_(itemstack, ItemDisplayContext.FIXED, packedLightIn, OverlayTexture.f_118083_, matrixStackIn, bufferIn, roo.m_9236_(), 0);
            }
            matrixStackIn.m_85849_();
            matrixStackIn.m_85836_();
            itemstack = roo.m_6844_(EquipmentSlot.CHEST);
            if (itemstack.m_41720_() instanceof ArmorItem && (armoritem = (ArmorItem)itemstack.m_41720_()).m_40402_() == EquipmentSlot.CHEST) {
                a = this.defaultBipedModel;
                notAVanillaModel = (a = this.getArmorModelHook((LivingEntity)roo, itemstack, EquipmentSlot.CHEST, (HumanoidModel)a)) != this.defaultBipedModel;
                this.setModelSlotVisible(a, EquipmentSlot.CHEST);
                this.translateToChest(matrixStackIn);
                matrixStackIn.m_252880_(0.0f, 0.25f, 0.0f);
                matrixStackIn.m_85841_(1.0f, 1.0f, 1.0f);
                flag1 = itemstack.m_41790_();
                clampedLight = packedLightIn;
                if (armoritem instanceof DyeableLeatherItem) {
                    i = ((DyeableLeatherItem)armoritem).m_41121_(itemstack);
                    f = (float)(i >> 16 & 0xFF) / 255.0f;
                    f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                    f2 = (float)(i & 0xFF) / 255.0f;
                    this.renderChestplate(roo, matrixStackIn, bufferIn, clampedLight, flag1, a, f, f1, f2, LayerKangarooArmor.getArmorResource((Entity)roo, itemstack, EquipmentSlot.CHEST, null), notAVanillaModel);
                    this.renderChestplate(roo, matrixStackIn, bufferIn, clampedLight, flag1, a, 1.0f, 1.0f, 1.0f, LayerKangarooArmor.getArmorResource((Entity)roo, itemstack, EquipmentSlot.CHEST, "overlay"), notAVanillaModel);
                } else {
                    this.renderChestplate(roo, matrixStackIn, bufferIn, clampedLight, flag1, a, 1.0f, 1.0f, 1.0f, LayerKangarooArmor.getArmorResource((Entity)roo, itemstack, EquipmentSlot.CHEST, null), notAVanillaModel);
                }
            }
            matrixStackIn.m_85849_();
        }
        matrixStackIn.m_85849_();
    }

    private void translateToHead(PoseStack matrixStackIn) {
        this.translateToChest(matrixStackIn);
        ((ModelKangaroo)this.renderer.m_7200_()).neck.translateAndRotate(matrixStackIn);
        ((ModelKangaroo)this.renderer.m_7200_()).head.translateAndRotate(matrixStackIn);
    }

    private void translateToChest(PoseStack matrixStackIn) {
        ((ModelKangaroo)this.renderer.m_7200_()).root.translateAndRotate(matrixStackIn);
        ((ModelKangaroo)this.renderer.m_7200_()).body.translateAndRotate(matrixStackIn);
        ((ModelKangaroo)this.renderer.m_7200_()).chest.translateAndRotate(matrixStackIn);
    }

    private void renderChestplate(EntityKangaroo entity, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, boolean glintIn, HumanoidModel modelIn, float red, float green, float blue, ResourceLocation armorResource, boolean notAVanillaModel) {
        VertexConsumer ivertexbuilder = ItemRenderer.m_115211_((MultiBufferSource)bufferIn, (RenderType)RenderType.m_110458_((ResourceLocation)armorResource), (boolean)false, (boolean)glintIn);
        ((ModelKangaroo)this.renderer.m_7200_()).m_102624_((EntityModel)modelIn);
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * Minecraft.m_91087_().m_91296_();
        modelIn.f_102810_.f_104203_ = 1.5707964f;
        modelIn.f_102810_.f_104204_ = 0.0f;
        modelIn.f_102810_.f_104205_ = 0.0f;
        modelIn.f_102810_.f_104200_ = 0.0f;
        modelIn.f_102810_.f_104201_ = 0.25f;
        modelIn.f_102810_.f_104202_ = -7.6f;
        modelIn.f_102811_.f_104200_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotationPointX;
        modelIn.f_102811_.f_104201_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotationPointY;
        modelIn.f_102811_.f_104202_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotationPointZ;
        modelIn.f_102811_.f_104203_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotateAngleX;
        modelIn.f_102811_.f_104204_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotateAngleY;
        modelIn.f_102811_.f_104205_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotateAngleZ;
        modelIn.f_102812_.f_104200_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotationPointX;
        modelIn.f_102812_.f_104201_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotationPointY;
        modelIn.f_102812_.f_104202_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotationPointZ;
        modelIn.f_102812_.f_104203_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotateAngleX;
        modelIn.f_102812_.f_104204_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotateAngleY;
        modelIn.f_102812_.f_104205_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotateAngleZ;
        modelIn.f_102812_.f_104201_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotationPointY - 4.0f + sitProgress * 0.25f;
        modelIn.f_102811_.f_104201_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotationPointY - 4.0f + sitProgress * 0.25f;
        modelIn.f_102812_.f_104202_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_left.rotationPointZ - 0.5f;
        modelIn.f_102811_.f_104202_ = ((ModelKangaroo)this.renderer.m_7200_()).arm_right.rotationPointZ - 0.5f;
        modelIn.f_102810_.f_104207_ = false;
        modelIn.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, red, green, blue, 1.0f);
        modelIn.f_102810_.f_104207_ = true;
        modelIn.f_102811_.f_104207_ = false;
        modelIn.f_102812_.f_104207_ = false;
        matrixStackIn.m_85836_();
        matrixStackIn.m_85841_(1.1f, 1.65f, 1.1f);
        modelIn.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, red, green, blue, 1.0f);
        matrixStackIn.m_85849_();
        modelIn.f_102811_.f_104207_ = true;
        modelIn.f_102812_.f_104207_ = true;
    }

    private void renderHelmet(EntityKangaroo entity, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, boolean glintIn, HumanoidModel modelIn, float red, float green, float blue, ResourceLocation armorResource, boolean notAVanillaModel) {
        VertexConsumer ivertexbuilder = ItemRenderer.m_115211_((MultiBufferSource)bufferIn, (RenderType)RenderType.m_110458_((ResourceLocation)armorResource), (boolean)false, (boolean)glintIn);
        ((ModelKangaroo)this.renderer.m_7200_()).m_102624_((EntityModel)modelIn);
        modelIn.f_102808_.f_104203_ = 0.0f;
        modelIn.f_102808_.f_104204_ = 0.0f;
        modelIn.f_102808_.f_104205_ = 0.0f;
        modelIn.f_102809_.f_104203_ = 0.0f;
        modelIn.f_102809_.f_104204_ = 0.0f;
        modelIn.f_102809_.f_104205_ = 0.0f;
        modelIn.f_102808_.f_104200_ = 0.0f;
        modelIn.f_102808_.f_104201_ = 0.0f;
        modelIn.f_102808_.f_104202_ = 0.0f;
        modelIn.f_102809_.f_104200_ = 0.0f;
        modelIn.f_102809_.f_104201_ = 0.0f;
        modelIn.f_102809_.f_104202_ = 0.0f;
        modelIn.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, red, green, blue, 1.0f);
    }

    protected void setModelSlotVisible(HumanoidModel p_188359_1_, EquipmentSlot slotIn) {
        this.setModelVisible(p_188359_1_);
        switch (slotIn) {
            case HEAD: {
                p_188359_1_.f_102808_.f_104207_ = true;
                p_188359_1_.f_102809_.f_104207_ = true;
                break;
            }
            case CHEST: {
                p_188359_1_.f_102810_.f_104207_ = true;
                p_188359_1_.f_102811_.f_104207_ = true;
                p_188359_1_.f_102812_.f_104207_ = true;
                break;
            }
            case LEGS: {
                p_188359_1_.f_102810_.f_104207_ = true;
                p_188359_1_.f_102813_.f_104207_ = true;
                p_188359_1_.f_102814_.f_104207_ = true;
                break;
            }
            case FEET: {
                p_188359_1_.f_102813_.f_104207_ = true;
                p_188359_1_.f_102814_.f_104207_ = true;
            }
        }
    }

    protected void setModelVisible(HumanoidModel model) {
        model.m_8009_(false);
    }

    protected HumanoidModel<?> getArmorModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel model) {
        Model basicModel = ForgeHooksClient.getArmorModel((LivingEntity)entity, (ItemStack)itemStack, (EquipmentSlot)slot, (HumanoidModel)model);
        return basicModel instanceof HumanoidModel ? (HumanoidModel)basicModel : model;
    }
}
