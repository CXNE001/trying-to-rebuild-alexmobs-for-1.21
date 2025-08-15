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

import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelSpectre
extends AdvancedEntityModel<EntitySpectre> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox spine;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox wing_left;
    private final AdvancedModelBox wing_right;
    private final AdvancedModelBox wing_left_p;
    private final AdvancedModelBox wing_right_p;

    public ModelSpectre() {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -0.5f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, 0.0f, -0.7854f, 0.0f);
        this.body.setTextureOffset(43, 0).addBox(-12.0f, -5.5f, -12.0f, 24.0f, 6.0f, 24.0f, 0.0f, false);
        this.spine = new AdvancedModelBox((AdvancedEntityModel)this, "spine");
        this.spine.setPos(0.0f, -5.5f, 0.0f);
        this.body.addChild((BasicModelPart)this.spine);
        this.setRotationAngle(this.spine, 0.0f, 0.7854f, 0.0f);
        this.spine.setTextureOffset(0, 0).addBox(0.0f, -3.0f, -14.0f, 0.0f, 8.0f, 42.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, 1.0f, 28.0f);
        this.spine.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(76, 31).addBox(0.0f, -6.0f, 0.0f, 0.0f, 11.0f, 27.0f, 0.0f, false);
        this.wing_left_p = new AdvancedModelBox((AdvancedEntityModel)this, "wing_left_p");
        this.wing_left_p.setPos(12.0f, -2.5f, -12.0f);
        this.body.addChild((BasicModelPart)this.wing_left_p);
        this.wing_left = new AdvancedModelBox((AdvancedEntityModel)this, "wing_left");
        this.wing_left_p.addChild((BasicModelPart)this.wing_left);
        this.wing_left.setTextureOffset(76, 76).addBox(0.0f, -1.5f, 0.0f, 26.0f, 3.0f, 23.0f, 0.0f, false);
        this.wing_right_p = new AdvancedModelBox((AdvancedEntityModel)this, "wing_right_p");
        this.wing_right_p.setPos(-12.0f, -2.5f, 12.0f);
        this.body.addChild((BasicModelPart)this.wing_right_p);
        this.setRotationAngle(this.wing_right_p, 0.0f, 1.5708f, 0.0f);
        this.wing_right = new AdvancedModelBox((AdvancedEntityModel)this, "wing_right");
        this.wing_right_p.addChild((BasicModelPart)this.wing_right);
        this.wing_right.setTextureOffset(0, 51).addBox(-26.0f, -1.5f, 0.0f, 26.0f, 3.0f, 23.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.wing_right_p, (Object)this.wing_left_p, (Object)this.wing_left, (Object)this.wing_right, (Object)this.spine, (Object)this.tail);
    }

    public void setupAnim(EntitySpectre entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flySpeed = 0.2f;
        float flyDegree = 0.6f;
        this.swing(this.spine, flySpeed, flyDegree * 0.05f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.tail, flySpeed, flyDegree * 0.7f, true, 3.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.wing_left, flySpeed, flyDegree * 0.85f, true, 7.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.wing_right, flySpeed, flyDegree * 0.85f, false, 7.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.root, flySpeed, flyDegree * 0.15f, true, 7.3f, 0.0f, ageInTicks, 1.0f);
        float partialTick = Minecraft.m_91087_().m_91296_();
        float birdPitch = entity.prevBirdPitch + (entity.birdPitch - entity.prevBirdPitch) * partialTick;
        this.root.rotateAngleX += birdPitch * ((float)Math.PI / 180);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
