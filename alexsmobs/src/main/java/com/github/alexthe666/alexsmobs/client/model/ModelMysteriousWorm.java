/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.client.render.AMItemstackRenderer;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class ModelMysteriousWorm
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox head;
    private final AdvancedModelBox body1;
    private final AdvancedModelBox body2;
    private final AdvancedModelBox body3;

    public ModelMysteriousWorm() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -2.0f, -6.0f);
        this.root.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(14, 0).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 4.0f, 2.0f, 0.0f, false);
        this.head.setTextureOffset(0, 19).addBox(-1.0f, -1.0f, -4.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
        this.body1 = new AdvancedModelBox((AdvancedEntityModel)this, "body1");
        this.body1.setPos(0.0f, 0.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.body1);
        this.body1.setTextureOffset(0, 11).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 5.0f, 0.0f, false);
        this.body2 = new AdvancedModelBox((AdvancedEntityModel)this, "body2");
        this.body2.setPos(0.0f, 0.0f, 5.0f);
        this.body1.addChild((BasicModelPart)this.body2);
        this.body2.setTextureOffset(10, 14).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 5.0f, 0.1f, false);
        this.body3 = new AdvancedModelBox((AdvancedEntityModel)this, "body3");
        this.body3.setPos(0.0f, 0.0f, 5.0f);
        this.body2.addChild((BasicModelPart)this.body3);
        this.body3.setTextureOffset(0, 0).addBox(-1.5f, -1.5f, 0.0f, 3.0f, 3.0f, 7.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.head, (Object)this.body1, (Object)this.body2, (Object)this.body3);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public void animateStack(ItemStack itemStackIn) {
        float tick;
        this.resetToDefaultPose();
        float partialTick = Minecraft.m_91087_().m_91296_();
        float f = tick = Minecraft.m_91087_().f_91074_ == null ? 0.0f : partialTick + (float)Minecraft.m_91087_().f_91074_.f_19797_;
        if (Minecraft.m_91087_().m_91104_()) {
            tick = AMItemstackRenderer.ticksExisted;
        }
        AdvancedModelBox[] tail = new AdvancedModelBox[]{this.head, this.body1, this.body2, this.body3};
        this.chainSwing(tail, 0.7f, 0.2f, -3.0, tick, 1.0f);
        this.chainFlap(tail, 0.7f, 0.2f, -3.0, tick, 1.0f);
        this.chainWave(tail, 0.7f, 0.2f, -3.0, tick, Mth.m_14036_((float)((float)(1.0 + Math.sin((double)tick * 0.04))), (float)0.0f, (float)0.5f) * 2.0f);
    }
}
