/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ArmedModel
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.ItemInHandRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelUnderminerDwarf;
import com.github.alexthe666.alexsmobs.client.render.RenderUnderminer;
import com.github.alexthe666.alexsmobs.entity.EntityUnderminer;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class LayerUnderminerItem
extends RenderLayer<EntityUnderminer, EntityModel<EntityUnderminer>> {
    public LayerUnderminerItem(RenderUnderminer render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityUnderminer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isFullyHidden()) {
            float f1;
            ItemStack itemstack = entitylivingbaseIn.m_6844_(EquipmentSlot.MAINHAND);
            if (RenderUnderminer.renderWithPickaxe) {
                itemstack = new ItemStack((ItemLike)AMItemRegistry.GHOSTLY_PICKAXE.get());
            }
            matrixStackIn.m_85836_();
            matrixStackIn.m_85836_();
            float f = entitylivingbaseIn.m_5737_() == HumanoidArm.LEFT ? 0.1f : -0.1f;
            float f2 = f1 = entitylivingbaseIn.isDwarf() ? 0.5f : 0.45f;
            if (entitylivingbaseIn.isDwarf()) {
                matrixStackIn.m_252880_(0.0f, 1.0f, 0.0f);
                f *= 0.3f;
            } else {
                matrixStackIn.m_252880_(0.0f, 0.2f, 0.0f);
            }
            this.translateToHand(entitylivingbaseIn.m_5737_(), matrixStackIn);
            matrixStackIn.m_252880_(f, f1, -0.15f);
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f));
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
            ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
            renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.m_85849_();
            matrixStackIn.m_85849_();
        }
    }

    protected void translateToHand(HumanoidArm arm, PoseStack matrixStack) {
        if (this.m_117386_() instanceof ModelUnderminerDwarf) {
            ((ModelUnderminerDwarf)this.m_117386_()).translateToHand(arm, matrixStack);
        } else if (this.m_117386_() instanceof ArmedModel) {
            ((ArmedModel)this.m_117386_()).m_6002_(arm, matrixStack);
        }
    }
}
