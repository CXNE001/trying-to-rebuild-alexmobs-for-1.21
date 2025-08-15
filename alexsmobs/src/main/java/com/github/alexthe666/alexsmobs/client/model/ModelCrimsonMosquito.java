/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.ModelAnimator
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelCrimsonMosquito
extends AdvancedEntityModel<EntityCrimsonMosquito> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox wingL;
    private final AdvancedModelBox wingR;
    private final AdvancedModelBox legsL;
    private final AdvancedModelBox legL1;
    private final AdvancedModelBox legL2;
    private final AdvancedModelBox legL3;
    private final AdvancedModelBox legsR;
    private final AdvancedModelBox legR1;
    private final AdvancedModelBox legR2;
    private final AdvancedModelBox legR3;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox head;
    private final AdvancedModelBox antennaL;
    private final AdvancedModelBox antennaR;
    private final AdvancedModelBox mouth;
    private ModelAnimator animator;

    public ModelCrimsonMosquito() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -9.5f, -0.25f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(31, 65).addBox(-3.0f, -3.5f, -3.75f, 6.0f, 6.0f, 6.0f, 0.0f, false);
        this.wingL = new AdvancedModelBox((AdvancedEntityModel)this, "wingL");
        this.wingL.setPos(2.7f, -3.2f, -0.75f);
        this.body.addChild((BasicModelPart)this.wingL);
        this.wingL.setTextureOffset(37, 59).addBox(0.0f, 0.0f, -1.0f, 18.0f, 0.0f, 5.0f, 0.0f, false);
        this.wingR = new AdvancedModelBox((AdvancedEntityModel)this, "wingR");
        this.wingR.setPos(-2.7f, -3.2f, -0.75f);
        this.body.addChild((BasicModelPart)this.wingR);
        this.wingR.setTextureOffset(37, 53).addBox(-18.0f, 0.0f, -1.0f, 18.0f, 0.0f, 5.0f, 0.0f, false);
        this.legsL = new AdvancedModelBox((AdvancedEntityModel)this, "legsL");
        this.legsL.setPos(3.0f, 2.5f, -2.75f);
        this.body.addChild((BasicModelPart)this.legsL);
        this.legL1 = new AdvancedModelBox((AdvancedEntityModel)this, "legL1");
        this.legL1.setPos(0.0f, 0.0f, 0.0f);
        this.legsL.addChild((BasicModelPart)this.legL1);
        this.setRotationAngle(this.legL1, 0.0f, 0.5236f, 0.0f);
        this.legL1.setTextureOffset(0, 51).addBox(0.0f, -8.0f, 0.0f, 18.0f, 15.0f, 0.0f, 0.0f, false);
        this.legL2 = new AdvancedModelBox((AdvancedEntityModel)this, "legL2");
        this.legL2.setPos(0.0f, 0.0f, 0.4f);
        this.legsL.addChild((BasicModelPart)this.legL2);
        this.legL2.setTextureOffset(37, 16).addBox(0.0f, -8.0f, 0.0f, 18.0f, 15.0f, 0.0f, 0.0f, false);
        this.legL3 = new AdvancedModelBox((AdvancedEntityModel)this, "legL3");
        this.legL3.setPos(0.0f, 0.0f, 0.9f);
        this.legsL.addChild((BasicModelPart)this.legL3);
        this.setRotationAngle(this.legL3, 0.0f, -0.8727f, 0.0f);
        this.legL3.setTextureOffset(37, 0).addBox(0.0f, -8.0f, 0.0f, 18.0f, 15.0f, 0.0f, 0.0f, false);
        this.legsR = new AdvancedModelBox((AdvancedEntityModel)this, "legsR");
        this.legsR.setPos(-3.0f, 2.5f, -2.75f);
        this.body.addChild((BasicModelPart)this.legsR);
        this.legR1 = new AdvancedModelBox((AdvancedEntityModel)this, "legR1");
        this.legR1.setPos(0.0f, 0.0f, 0.0f);
        this.legsR.addChild((BasicModelPart)this.legR1);
        this.setRotationAngle(this.legR1, 0.0f, -0.5236f, 0.0f);
        this.legR1.setTextureOffset(37, 37).addBox(-18.0f, -8.0f, 0.0f, 18.0f, 15.0f, 0.0f, 0.0f, false);
        this.legR2 = new AdvancedModelBox((AdvancedEntityModel)this, "legR2");
        this.legR2.setPos(0.0f, 0.0f, 0.4f);
        this.legsR.addChild((BasicModelPart)this.legR2);
        this.legR2.setTextureOffset(0, 35).addBox(-18.0f, -8.0f, 0.0f, 18.0f, 15.0f, 0.0f, 0.0f, false);
        this.legR3 = new AdvancedModelBox((AdvancedEntityModel)this, "legR3");
        this.legR3.setPos(0.0f, 0.0f, 0.9f);
        this.legsR.addChild((BasicModelPart)this.legR3);
        this.setRotationAngle(this.legR3, 0.0f, 0.8727f, 0.0f);
        this.legR3.setTextureOffset(0, 19).addBox(-18.0f, -8.0f, 0.0f, 18.0f, 15.0f, 0.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -1.5f, 2.25f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(48, 83).addBox(-2.0f, -1.4f, 0.0f, 4.0f, 4.0f, 16.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.5f, -3.75f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(56, 65).addBox(-2.0f, -2.0f, -4.0f, 4.0f, 4.0f, 4.0f, 0.0f, false);
        this.antennaL = new AdvancedModelBox((AdvancedEntityModel)this, "antennaL");
        this.antennaL.setPos(1.0f, -0.1f, -4.0f);
        this.head.addChild((BasicModelPart)this.antennaL);
        this.setRotationAngle(this.antennaL, 1.2217f, -0.48f, 0.0436f);
        this.antennaL.setTextureOffset(5, 0).addBox(0.0f, -8.0f, 0.0f, 0.0f, 8.0f, 2.0f, 0.0f, false);
        this.antennaR = new AdvancedModelBox((AdvancedEntityModel)this, "antennaR");
        this.antennaR.setPos(-1.0f, -0.1f, -4.0f);
        this.head.addChild((BasicModelPart)this.antennaR);
        this.setRotationAngle(this.antennaR, 1.2217f, 0.48f, -0.0436f);
        this.antennaR.setTextureOffset(0, 0).addBox(0.0f, -8.0f, 0.0f, 0.0f, 8.0f, 2.0f, 0.0f, false);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setPos(0.0f, 2.0f, -3.5f);
        this.head.addChild((BasicModelPart)this.mouth);
        this.setRotationAngle(this.mouth, -1.0036f, 0.0f, 0.0f);
        this.mouth.setTextureOffset(23, 0).addBox(-0.5f, 0.0f, -1.0f, 1.0f, 8.0f, 1.0f, 0.0f, false);
        this.animator = ModelAnimator.create();
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntityCrimsonMosquito entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flySpeed = 0.5f;
        float flyDegree = 0.5f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float flyProgress = entityIn.prevFlyProgress + (entityIn.flyProgress - entityIn.prevFlyProgress) * partialTick;
        float shootProgress = entityIn.prevShootProgress + (entityIn.shootProgress - entityIn.prevShootProgress) * partialTick;
        this.walk(this.antennaR, flySpeed, flyDegree * 0.15f, false, 0.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.antennaL, flySpeed, flyDegree * 0.15f, false, 0.0f, 0.1f, ageInTicks, 1.0f);
        boolean flappingWings = flyProgress > 0.0f || entityIn.randomWingFlapTick > 0;
        this.progressRotationPrev(this.head, shootProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.mouth, shootProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        if (entityIn.m_20159_()) {
            this.progressRotationPrev(this.body, 5.0f, Maths.rad(-90.0), Maths.rad(180.0), 0.0f, 5.0f);
            this.progressRotationPrev(this.head, 5.0f, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.mouth, 5.0f, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
            float legRot = 50.0f;
            this.progressRotationPrev(this.legR1, 5.0f, 0.0f, 0.0f, Maths.rad(-legRot), 5.0f);
            this.progressRotationPrev(this.legR2, 5.0f, 0.0f, 0.0f, Maths.rad(-legRot), 5.0f);
            this.progressRotationPrev(this.legR3, 5.0f, 0.0f, 0.0f, Maths.rad(-legRot), 5.0f);
            this.progressRotationPrev(this.legL1, 5.0f, 0.0f, 0.0f, Maths.rad(legRot), 5.0f);
            this.progressRotationPrev(this.legL2, 5.0f, 0.0f, 0.0f, Maths.rad(legRot), 5.0f);
            this.progressRotationPrev(this.legL3, 5.0f, 0.0f, 0.0f, Maths.rad(legRot), 5.0f);
            this.mouth.setScale(1.0f, (float)((double)0.85f + Math.sin(ageInTicks) * (double)0.15f), 1.0f);
        } else {
            this.mouth.setScale(1.0f, 1.0f, 1.0f);
        }
        if (shootProgress > 0.0f) {
            this.mouth.setScale(1.0f + shootProgress * 0.1f, 1.0f - shootProgress * 0.1f, 1.0f + shootProgress * 0.1f);
        }
        if (flappingWings) {
            this.flap(this.wingL, flySpeed * 3.3f, flyDegree, true, 0.0f, 0.2f, ageInTicks, 1.0f);
            this.flap(this.wingR, flySpeed * 3.3f, flyDegree, false, 0.0f, 0.2f, ageInTicks, 1.0f);
        } else {
            this.wingR.rotateAngleX = Maths.rad(30.0);
            this.wingR.rotateAngleY = Maths.rad(70.0);
            this.wingL.rotateAngleX = Maths.rad(30.0);
            this.wingL.rotateAngleY = Maths.rad(-70.0);
        }
        if (flyProgress > 0.0f) {
            this.progressPositionPrev(this.body, flyProgress, 0.0f, -10.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.legL1, flyProgress, 0.0f, Maths.rad(-30.0), Maths.rad(60.0), 5.0f);
            this.progressRotationPrev(this.legR1, flyProgress, 0.0f, Maths.rad(30.0), Maths.rad(-60.0), 5.0f);
            this.progressRotationPrev(this.legL2, flyProgress, 0.0f, Maths.rad(-20.0), Maths.rad(60.0), 5.0f);
            this.progressRotationPrev(this.legR2, flyProgress, 0.0f, Maths.rad(20.0), Maths.rad(-60.0), 5.0f);
            this.progressRotationPrev(this.legL3, flyProgress, 0.0f, Maths.rad(-5.0), Maths.rad(60.0), 5.0f);
            this.progressRotationPrev(this.legR3, flyProgress, 0.0f, Maths.rad(5.0), Maths.rad(-60.0), 5.0f);
            this.bob(this.body, flySpeed * 0.5f, flyDegree * 5.0f, false, ageInTicks, 1.0f);
            this.flap(this.legL1, flySpeed, flyDegree * 0.5f, true, 1.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legR1, flySpeed, flyDegree * 0.5f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legL2, flySpeed, flyDegree * 0.5f, true, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legR2, flySpeed, flyDegree * 0.5f, false, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legL3, flySpeed, flyDegree * 0.5f, true, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legR3, flySpeed, flyDegree * 0.5f, false, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.walk(this.tail, flySpeed, flyDegree * 0.15f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        }
        float bloatScale = 1.0f + (float)entityIn.getBloodLevel() * 0.1f;
        this.tail.rotateAngleX -= (float)entityIn.getBloodLevel() * 0.05f;
        this.tail.setScale(bloatScale, bloatScale, bloatScale);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.wingL, (Object)this.wingR, (Object)this.legsL, (Object)this.legL1, (Object)this.legL2, (Object)this.legL3, (Object)this.legsR, (Object)this.legR1, (Object)this.legR2, (Object)this.legR3, (Object[])new AdvancedModelBox[]{this.tail, this.head, this.antennaL, this.antennaR, this.mouth});
    }

    public void setRotationAngle(AdvancedModelBox modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
