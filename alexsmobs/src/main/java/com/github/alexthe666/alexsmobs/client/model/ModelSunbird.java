/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySunbird;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelSunbird
extends AdvancedEntityModel<EntitySunbird> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox hair;
    private final AdvancedModelBox left_wing;
    private final AdvancedModelBox left_wing1;
    private final AdvancedModelBox left_wing2;
    private final AdvancedModelBox right_wing;
    private final AdvancedModelBox right_wing1;
    private final AdvancedModelBox right_wing2;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox right_foot;

    public ModelSunbird() {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -13.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(106, 38).addBox(-7.0f, -5.0f, -11.0f, 14.0f, 12.0f, 23.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -1.0f, -12.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(0, 38).addBox(-3.0f, -3.0f, -12.0f, 6.0f, 6.0f, 13.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 1.0f, -13.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 0).addBox(-4.0f, -5.0f, -7.0f, 8.0f, 8.0f, 8.0f, 0.0f, false);
        this.head.setTextureOffset(12, 17).addBox(-2.0f, -2.0f, -12.0f, 4.0f, 3.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(0, 0).addBox(-1.0f, 1.0f, -12.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
        this.hair = new AdvancedModelBox((AdvancedEntityModel)this, "hair");
        this.hair.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.hair);
        this.hair.setTextureOffset(0, 17).addBox(0.0f, -8.0f, -9.0f, 0.0f, 8.0f, 11.0f, 0.0f, false);
        this.left_wing = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing");
        this.left_wing.setRotationPoint(8.0f, -3.0f, -8.0f);
        this.body.addChild((BasicModelPart)this.left_wing);
        this.left_wing.setTextureOffset(0, 119).addBox(-1.0f, -3.0f, -5.2f, 15.0f, 5.0f, 6.0f, 0.0f, false);
        this.left_wing1 = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing1");
        this.left_wing1.setRotationPoint(0.0f, -1.0f, 0.0f);
        this.left_wing.addChild((BasicModelPart)this.left_wing1);
        this.left_wing1.setTextureOffset(103, 80).addBox(-1.0f, 0.0f, -8.0f, 33.0f, 0.0f, 37.0f, 0.0f, false);
        this.left_wing2 = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing2");
        this.left_wing2.setRotationPoint(32.0f, 0.0f, 0.0f);
        this.left_wing1.addChild((BasicModelPart)this.left_wing2);
        this.left_wing2.setTextureOffset(0, 0).addBox(0.0f, 0.0f, -8.0f, 50.0f, 0.0f, 37.0f, 0.0f, false);
        this.right_wing = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing");
        this.right_wing.setRotationPoint(-8.0f, -3.0f, -8.0f);
        this.body.addChild((BasicModelPart)this.right_wing);
        this.right_wing.setTextureOffset(0, 119).addBox(-14.0f, -3.0f, -5.2f, 15.0f, 5.0f, 6.0f, 0.0f, true);
        this.right_wing1 = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing1");
        this.right_wing1.setRotationPoint(0.0f, -1.0f, 0.0f);
        this.right_wing.addChild((BasicModelPart)this.right_wing1);
        this.right_wing1.setTextureOffset(103, 80).addBox(-32.0f, 0.0f, -8.0f, 33.0f, 0.0f, 37.0f, 0.0f, true);
        this.right_wing2 = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing2");
        this.right_wing2.setRotationPoint(-32.0f, 0.0f, 0.0f);
        this.right_wing1.addChild((BasicModelPart)this.right_wing2);
        this.right_wing2.setTextureOffset(0, 0).addBox(-50.0f, 0.0f, -8.0f, 50.0f, 0.0f, 37.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setRotationPoint(0.0f, -5.0f, 12.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 80).addBox(-23.0f, 0.0f, 0.0f, 32.0f, 0.0f, 38.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setRotationPoint(-6.0f, 0.0f, 38.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(0, 38).addBox(-16.0f, 0.0f, 0.0f, 32.0f, 0.0f, 41.0f, 0.0f, false);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(3.0f, 8.0f, 8.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 58).addBox(-2.0f, -1.0f, -5.0f, 5.0f, 4.0f, 8.0f, 0.0f, false);
        this.left_foot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot");
        this.left_foot.setRotationPoint(0.5f, 3.0f, -2.0f);
        this.left_leg.addChild((BasicModelPart)this.left_foot);
        this.setRotationAngle(this.left_foot, 0.0436f, 0.0f, 0.0f);
        this.left_foot.setTextureOffset(22, 66).addBox(-2.0f, 0.0f, -5.0f, 4.0f, 3.0f, 5.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-3.0f, 8.0f, 8.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 58).addBox(-3.0f, -1.0f, -5.0f, 5.0f, 4.0f, 8.0f, 0.0f, true);
        this.right_foot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot");
        this.right_foot.setRotationPoint(-0.5f, 3.0f, -2.0f);
        this.right_leg.addChild((BasicModelPart)this.right_foot);
        this.setRotationAngle(this.right_foot, 0.0436f, 0.0f, 0.0f);
        this.right_foot.setTextureOffset(22, 66).addBox(-2.0f, 0.0f, -5.0f, 4.0f, 3.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.hair, (Object)this.body, (Object)this.tail1, (Object)this.tail2, (Object)this.left_wing, (Object)this.left_wing1, (Object)this.left_wing2, (Object)this.right_wing, (Object)this.right_wing1, (Object)this.right_wing2, (Object)this.left_leg, (Object[])new AdvancedModelBox[]{this.right_leg, this.right_foot, this.left_foot, this.neck, this.head});
    }

    public void setupAnim(EntitySunbird entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flySpeed = 0.2f;
        float flyDegree = 0.6f;
        this.flap(this.right_wing, flySpeed, flyDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.left_wing, flySpeed, flyDegree, true, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.right_wing2, flySpeed, flyDegree, false, -1.2f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.left_wing2, flySpeed, flyDegree, true, -1.2f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.tail1, flySpeed, flyDegree * 0.1f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.tail1, flySpeed, flyDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, flySpeed, flyDegree * 0.2f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, flySpeed, flyDegree * 0.2f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, flySpeed, flyDegree * 6.0f, false, limbSwing, limbSwingAmount);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.neck, this.head});
        float partialTick = Minecraft.m_91087_().m_91296_();
        float birdPitch = entityIn.prevBirdPitch + (entityIn.birdPitch - entityIn.prevBirdPitch) * partialTick;
        this.body.rotateAngleX = birdPitch * ((float)Math.PI / 180);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
