/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.Model
 *  net.minecraft.client.model.geom.ModelLayers
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.DyeableLeatherItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.client.ForgeHooksClient
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelMimicube;
import com.github.alexthe666.alexsmobs.client.render.RenderMimicube;
import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

public class LayerMimicubeHelmet
extends RenderLayer<EntityMimicube, ModelMimicube> {
    private static final Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();
    private final HumanoidModel defaultBipedModel;
    private final RenderMimicube renderer;

    public LayerMimicubeHelmet(RenderMimicube render, EntityRendererProvider.Context renderManagerIn) {
        super((RenderLayerParent)render);
        this.renderer = render;
        this.defaultBipedModel = new HumanoidModel(renderManagerIn.m_174023_(ModelLayers.f_171261_));
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

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityMimicube cube, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ArmorItem armoritem;
        matrixStackIn.m_85836_();
        ItemStack itemstack = cube.m_6844_(EquipmentSlot.HEAD);
        float helmetSwap = Mth.m_14179_((float)partialTicks, (float)cube.prevHelmetSwapProgress, (float)cube.helmetSwapProgress) * 0.2f;
        if (itemstack.m_41720_() instanceof ArmorItem && (armoritem = (ArmorItem)itemstack.m_41720_()).m_40402_() == EquipmentSlot.HEAD) {
            HumanoidModel<?> a = this.defaultBipedModel;
            boolean notAVanillaModel = (a = this.getArmorModelHook((LivingEntity)cube, itemstack, EquipmentSlot.HEAD, (HumanoidModel)a)) != this.defaultBipedModel;
            this.setModelSlotVisible(a, EquipmentSlot.HEAD);
            boolean flag = false;
            ((ModelMimicube)this.renderer.m_7200_()).root.translateAndRotate(matrixStackIn);
            ((ModelMimicube)this.renderer.m_7200_()).innerbody.translateAndRotate(matrixStackIn);
            matrixStackIn.m_252880_(0.0f, notAVanillaModel ? 0.25f : -0.75f, 0.0f);
            matrixStackIn.m_85841_(1.0f + 0.3f * (1.0f - helmetSwap), 1.0f + 0.3f * (1.0f - helmetSwap), 1.0f + 0.3f * (1.0f - helmetSwap));
            boolean flag1 = itemstack.m_41790_();
            int clampedLight = helmetSwap > 0.0f ? (int)(-100.0f * helmetSwap) : packedLightIn;
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(360.0f * helmetSwap));
            if (armoritem instanceof DyeableLeatherItem) {
                int i = ((DyeableLeatherItem)armoritem).m_41121_(itemstack);
                float f = (float)(i >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                this.renderArmor(cube, matrixStackIn, bufferIn, clampedLight, flag1, a, f, f1, f2, LayerMimicubeHelmet.getArmorResource((Entity)cube, itemstack, EquipmentSlot.HEAD, null), notAVanillaModel);
                this.renderArmor(cube, matrixStackIn, bufferIn, clampedLight, flag1, a, 1.0f, 1.0f, 1.0f, LayerMimicubeHelmet.getArmorResource((Entity)cube, itemstack, EquipmentSlot.HEAD, "overlay"), notAVanillaModel);
            } else {
                this.renderArmor(cube, matrixStackIn, bufferIn, clampedLight, flag1, a, 1.0f, 1.0f, 1.0f, LayerMimicubeHelmet.getArmorResource((Entity)cube, itemstack, EquipmentSlot.HEAD, null), notAVanillaModel);
            }
        }
        matrixStackIn.m_85849_();
    }

    private void renderArmor(EntityMimicube entity, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, boolean glintIn, HumanoidModel modelIn, float red, float green, float blue, ResourceLocation armorResource, boolean notAVanillaModel) {
        VertexConsumer ivertexbuilder = ItemRenderer.m_115211_((MultiBufferSource)bufferIn, (RenderType)RenderType.m_110458_((ResourceLocation)armorResource), (boolean)false, (boolean)glintIn);
        if (notAVanillaModel) {
            ((ModelMimicube)this.renderer.m_7200_()).m_102624_((EntityModel)modelIn);
            modelIn.f_102810_.f_104201_ = 0.0f;
            modelIn.f_102808_.m_104227_(0.0f, 1.0f, 0.0f);
            modelIn.f_102809_.f_104201_ = 0.0f;
            modelIn.f_102808_.f_104203_ = ((ModelMimicube)this.renderer.m_7200_()).body.rotateAngleX;
            modelIn.f_102808_.f_104204_ = ((ModelMimicube)this.renderer.m_7200_()).body.rotateAngleY;
            modelIn.f_102808_.f_104205_ = ((ModelMimicube)this.renderer.m_7200_()).body.rotateAngleZ;
            modelIn.f_102808_.f_104200_ = ((ModelMimicube)this.renderer.m_7200_()).body.rotationPointX;
            modelIn.f_102808_.f_104201_ = ((ModelMimicube)this.renderer.m_7200_()).body.rotationPointY;
            modelIn.f_102808_.f_104202_ = ((ModelMimicube)this.renderer.m_7200_()).body.rotationPointZ;
            modelIn.f_102809_.m_104315_(modelIn.f_102808_);
            modelIn.f_102810_.m_104315_(modelIn.f_102808_);
        }
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
        try {
            Model basicModel = ForgeHooksClient.getArmorModel((LivingEntity)entity, (ItemStack)itemStack, (EquipmentSlot)slot, (HumanoidModel)model);
            return basicModel instanceof HumanoidModel ? (HumanoidModel)basicModel : model;
        }
        catch (Exception e) {
            return model;
        }
    }
}
