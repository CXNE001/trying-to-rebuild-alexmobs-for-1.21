/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityMurmurHead;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelMurmurHead
extends AdvancedEntityModel<EntityMurmurHead> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox head;
    private final AdvancedModelBox backHair;
    private final AdvancedModelBox leftHair;
    private final AdvancedModelBox rightHair;

    public ModelMurmurHead() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -1.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(37, 41).addBox(-4.5f, -8.0f, -4.5f, 9.0f, 9.0f, 9.0f, 0.0f, false);
        this.head.setTextureOffset(0, 41).addBox(-4.5f, -8.0f, -4.5f, 9.0f, 9.0f, 9.0f, 0.2f, false);
        this.backHair = new AdvancedModelBox((AdvancedEntityModel)this, "backHair");
        this.backHair.setRotationPoint(0.0f, -5.0f, 5.0f);
        this.head.addChild((BasicModelPart)this.backHair);
        this.backHair.setTextureOffset(49, 0).addBox(-5.5f, -2.0f, -1.5f, 11.0f, 20.0f, 3.0f, 0.0f, false);
        this.leftHair = new AdvancedModelBox((AdvancedEntityModel)this, "leftHair");
        this.leftHair.setRotationPoint(4.5f, -5.0f, 1.0f);
        this.head.addChild((BasicModelPart)this.leftHair);
        this.leftHair.setTextureOffset(17, 60).addBox(-1.0f, -2.0f, -2.5f, 2.0f, 16.0f, 5.0f, 0.0f, false);
        this.rightHair = new AdvancedModelBox((AdvancedEntityModel)this, "rightHair");
        this.rightHair.setRotationPoint(-4.5f, -5.0f, 1.0f);
        this.head.addChild((BasicModelPart)this.rightHair);
        this.rightHair.setTextureOffset(17, 60).addBox(-1.0f, -2.0f, -2.5f, 2.0f, 16.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.head, (Object)this.leftHair, (Object)this.rightHair, (Object)this.backHair);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void animateHair(float ageInTicks) {
        float idleSpeed = 0.05f;
        float idleDegree = 0.1f;
        this.walk(this.backHair, idleSpeed, idleDegree * 0.5f, false, 0.0f, -0.05f, ageInTicks, 1.0f);
        this.flap(this.rightHair, idleSpeed, idleDegree * 0.5f, false, 1.0f, 0.05f, ageInTicks, 1.0f);
        this.flap(this.leftHair, idleSpeed, idleDegree * 0.5f, true, 1.0f, 0.05f, ageInTicks, 1.0f);
    }

    public void setupAnim(EntityMurmurHead entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float angerProgress = entity.prevAngerProgress + (entity.angerProgress - entity.prevAngerProgress) * partialTicks;
        if (ageInTicks > 5.0f) {
            float hairAnimateScale = Math.min(1.0f, (ageInTicks - 5.0f) / 10.0f);
            double d0 = Mth.m_14139_((double)partialTicks, (double)entity.prevXHair, (double)entity.xHair) - Mth.m_14139_((double)partialTicks, (double)entity.f_19854_, (double)entity.m_20185_());
            double d1 = Mth.m_14139_((double)partialTicks, (double)entity.prevYHair, (double)entity.yHair) - Mth.m_14139_((double)partialTicks, (double)entity.f_19855_, (double)entity.m_20186_());
            double d2 = Mth.m_14139_((double)partialTicks, (double)entity.prevZHair, (double)entity.zHair) - Mth.m_14139_((double)partialTicks, (double)entity.f_19856_, (double)entity.m_20189_());
            float f = entity.f_20884_ + (entity.f_20883_ - entity.f_20884_);
            double d3 = Mth.m_14031_((float)(f * ((float)Math.PI / 180)));
            double d4 = -Mth.m_14089_((float)(f * ((float)Math.PI / 180)));
            float f1 = (float)d1 * 10.0f;
            f1 = Mth.m_14036_((float)f1, (float)-6.0f, (float)32.0f) * hairAnimateScale;
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0f;
            f2 = Mth.m_14036_((float)f2, (float)0.0f, (float)150.0f) * hairAnimateScale;
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0f;
            f3 = Mth.m_14036_((float)f3, (float)-20.0f, (float)20.0f) * hairAnimateScale;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            float hairX = Maths.rad(6.0f + f2 / 2.0f + (f1 += Mth.m_14031_((float)(Mth.m_14179_((float)partialTicks, (float)entity.f_19867_, (float)entity.f_19787_) * 6.0f)) * 32.0f * 1.0f) - 180.0f);
            float hairY = Maths.rad(f3 / 2.0f);
            float hairZ = Maths.rad(180.0f - f3 / 2.0f);
            this.backHair.rotateAngleX -= hairX;
            this.backHair.rotateAngleY -= hairY;
            this.backHair.rotateAngleZ -= hairZ;
            this.rightHair.rotateAngleX -= hairX;
            this.rightHair.rotateAngleY -= hairY;
            this.rightHair.rotateAngleZ -= hairZ;
            this.leftHair.rotateAngleX -= hairX;
            this.leftHair.rotateAngleY -= hairY;
            this.leftHair.rotateAngleZ -= hairZ;
        }
        this.animateHair(ageInTicks);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        this.progressRotationPrev(this.backHair, angerProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightHair, angerProgress, Maths.rad(-10.0), 0.0f, Maths.rad(25.0), 5.0f);
        this.progressRotationPrev(this.leftHair, angerProgress, Maths.rad(-10.0), 0.0f, Maths.rad(-25.0), 5.0f);
    }
}
