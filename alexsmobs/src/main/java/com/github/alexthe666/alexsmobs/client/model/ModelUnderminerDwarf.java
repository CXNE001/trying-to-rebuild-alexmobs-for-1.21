/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.HumanoidModel$ArmPose
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.HumanoidArm
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityUnderminer;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;

public class ModelUnderminerDwarf
extends AdvancedEntityModel<EntityUnderminer> {
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox helmet;
    private final AdvancedModelBox beard;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightLeg;
    public HumanoidModel.ArmPose leftArmPose = HumanoidModel.ArmPose.EMPTY;
    public HumanoidModel.ArmPose rightArmPose = HumanoidModel.ArmPose.EMPTY;
    public boolean crouching;
    public float swimAmount;

    public ModelUnderminerDwarf() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.body.setTextureOffset(0, 36).addBox(-5.0f, -10.0f, -3.0f, 10.0f, 11.0f, 6.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -10.02f, 0.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(30, 24).addBox(-5.0f, -8.0f, -5.0f, 10.0f, 8.0f, 9.0f, 0.0f, false);
        this.head.setTextureOffset(0, 15).addBox(-5.0f, -8.0f, -5.0f, 10.0f, 8.0f, 9.0f, 0.1f, false);
        this.helmet = new AdvancedModelBox((AdvancedEntityModel)this, "helmet");
        this.helmet.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.helmet);
        this.helmet.setTextureOffset(0, 0).addBox(-6.0f, -10.0f, -5.5f, 12.0f, 4.0f, 10.0f, 0.1f, false);
        this.beard = new AdvancedModelBox((AdvancedEntityModel)this, "beard");
        this.beard.setRotationPoint(0.0f, 0.1f, -4.1f);
        this.head.addChild((BasicModelPart)this.beard);
        this.beard.setTextureOffset(0, 54).addBox(-5.0f, 0.0f, -1.0f, 10.0f, 9.0f, 2.0f, 0.0f, false);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(7.0f, -9.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.leftArm.setTextureOffset(45, 0).addBox(-2.0f, -1.0f, -2.5f, 4.0f, 13.0f, 5.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-7.0f, -9.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.rightArm.setTextureOffset(45, 0).addBox(-2.0f, -1.0f, -2.5f, 4.0f, 13.0f, 5.0f, 0.0f, true);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(2.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.leftLeg.setTextureOffset(33, 42).addBox(-2.0f, -1.0f, -3.0f, 5.0f, 11.0f, 6.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-2.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.rightLeg.setTextureOffset(33, 42).addBox(-3.0f, -1.0f, -3.0f, 5.0f, 11.0f, 6.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.body);
    }

    public void setupAnim(EntityUnderminer entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.setupHumanoidAnims(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.body, (Object)this.head, (Object)this.beard, (Object)this.helmet, (Object)this.rightLeg, (Object)this.leftLeg, (Object)this.rightArm, (Object)this.leftArm);
    }

    public void setupHumanoidAnims(EntityUnderminer entityIn, float p_102867_, float p_102868_, float p_102869_, float p_102870_, float p_102871_) {
        boolean flag2;
        boolean flag = entityIn.m_21256_() > 4;
        boolean flag1 = entityIn.m_6067_();
        this.head.rotateAngleY = p_102870_ * ((float)Math.PI / 180);
        this.head.rotateAngleX = flag ? -0.7853982f : (this.swimAmount > 0.0f ? (flag1 ? this.rotlerpRad(this.swimAmount, this.head.rotateAngleX, -0.7853982f) : this.rotlerpRad(this.swimAmount, this.head.rotateAngleX, p_102871_ * ((float)Math.PI / 180))) : p_102871_ * ((float)Math.PI / 180));
        float f = 1.0f;
        if (flag) {
            f = (float)entityIn.m_20184_().m_82556_();
            f /= 0.2f;
            f *= f * f;
        }
        if (f < 1.0f) {
            f = 1.0f;
        }
        this.rightArm.rotateAngleX = Mth.m_14089_((float)(p_102867_ * 0.6662f + (float)Math.PI)) * 2.0f * p_102868_ * 0.5f / f;
        this.leftArm.rotateAngleX = Mth.m_14089_((float)(p_102867_ * 0.6662f)) * 2.0f * p_102868_ * 0.5f / f;
        this.rightArm.rotateAngleZ = 0.0f;
        this.leftArm.rotateAngleZ = 0.0f;
        this.rightLeg.rotateAngleX = Mth.m_14089_((float)(p_102867_ * 0.6662f)) * 1.4f * p_102868_ / f;
        this.leftLeg.rotateAngleX = Mth.m_14089_((float)(p_102867_ * 0.6662f + (float)Math.PI)) * 1.4f * p_102868_ / f;
        this.rightLeg.rotateAngleY = 0.0f;
        this.leftLeg.rotateAngleY = 0.0f;
        this.rightLeg.rotateAngleZ = 0.0f;
        this.leftLeg.rotateAngleZ = 0.0f;
        if (this.f_102609_) {
            this.rightArm.rotateAngleX += -0.62831855f;
            this.leftArm.rotateAngleX += -0.62831855f;
            this.rightLeg.rotateAngleX = -1.4137167f;
            this.rightLeg.rotateAngleY = 0.31415927f;
            this.rightLeg.rotateAngleZ = 0.07853982f;
            this.leftLeg.rotateAngleX = -1.4137167f;
            this.leftLeg.rotateAngleY = -0.31415927f;
            this.leftLeg.rotateAngleZ = -0.07853982f;
        }
        this.rightArm.rotateAngleY = 0.0f;
        this.leftArm.rotateAngleY = 0.0f;
        boolean bl = flag2 = entityIn.m_5737_() == HumanoidArm.RIGHT;
        if (entityIn.m_6117_()) {
            boolean flag3;
            boolean bl2 = flag3 = entityIn.m_7655_() == InteractionHand.MAIN_HAND;
            if (flag3 == flag2) {
                this.poseRightArm(entityIn);
            } else {
                this.poseLeftArm(entityIn);
            }
        } else {
            boolean flag4;
            boolean bl3 = flag4 = flag2 ? this.leftArmPose.m_102897_() : this.rightArmPose.m_102897_();
            if (flag2 != flag4) {
                this.poseLeftArm(entityIn);
                this.poseRightArm(entityIn);
            } else {
                this.poseRightArm(entityIn);
                this.poseLeftArm(entityIn);
            }
        }
        this.setupAttackAnimation(entityIn, p_102869_);
        if (this.crouching) {
            this.body.rotateAngleX = 0.5f;
            this.rightArm.rotateAngleX += 0.4f;
            this.leftArm.rotateAngleX += 0.4f;
        }
        if (this.rightArmPose != HumanoidModel.ArmPose.SPYGLASS) {
            this.rightArm.rotateAngleZ += 1.0f * (Mth.m_14089_((float)(p_102869_ * 0.09f)) * 0.05f + 0.05f);
            this.rightArm.rotateAngleX += 1.0f * Mth.m_14031_((float)(p_102869_ * 0.067f)) * 0.05f;
        }
        if (this.leftArmPose != HumanoidModel.ArmPose.SPYGLASS) {
            this.leftArm.rotateAngleZ += -1.0f * (Mth.m_14089_((float)(p_102869_ * 0.09f)) * 0.05f + 0.05f);
            this.leftArm.rotateAngleX += -1.0f * Mth.m_14031_((float)(p_102869_ * 0.067f)) * 0.05f;
        }
        if (this.swimAmount > 0.0f) {
            float f2;
            float f5 = p_102867_ % 26.0f;
            HumanoidArm humanoidarm = this.getAttackArm(entityIn);
            float f1 = humanoidarm == HumanoidArm.RIGHT && this.f_102608_ > 0.0f ? 0.0f : this.swimAmount;
            float f3 = f2 = humanoidarm == HumanoidArm.LEFT && this.f_102608_ > 0.0f ? 0.0f : this.swimAmount;
            if (!entityIn.m_6117_()) {
                if (f5 < 14.0f) {
                    this.leftArm.rotateAngleX = this.rotlerpRad(f2, this.leftArm.rotateAngleX, 0.0f);
                    this.rightArm.rotateAngleX = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleX, (float)0.0f);
                    this.leftArm.rotateAngleY = this.rotlerpRad(f2, this.leftArm.rotateAngleY, (float)Math.PI);
                    this.rightArm.rotateAngleY = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleY, (float)((float)Math.PI));
                    this.leftArm.rotateAngleZ = this.rotlerpRad(f2, this.leftArm.rotateAngleZ, (float)Math.PI + 1.8707964f * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0f));
                    this.rightArm.rotateAngleZ = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleZ, (float)((float)Math.PI - 1.8707964f * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0f)));
                } else if (f5 >= 14.0f && f5 < 22.0f) {
                    float f6 = (f5 - 14.0f) / 8.0f;
                    this.leftArm.rotateAngleX = this.rotlerpRad(f2, this.leftArm.rotateAngleX, 1.5707964f * f6);
                    this.rightArm.rotateAngleX = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleX, (float)(1.5707964f * f6));
                    this.leftArm.rotateAngleY = this.rotlerpRad(f2, this.leftArm.rotateAngleY, (float)Math.PI);
                    this.rightArm.rotateAngleY = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleY, (float)((float)Math.PI));
                    this.leftArm.rotateAngleZ = this.rotlerpRad(f2, this.leftArm.rotateAngleZ, 5.012389f - 1.8707964f * f6);
                    this.rightArm.rotateAngleZ = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleZ, (float)(1.2707963f + 1.8707964f * f6));
                } else if (f5 >= 22.0f && f5 < 26.0f) {
                    float f32 = (f5 - 22.0f) / 4.0f;
                    this.leftArm.rotateAngleX = this.rotlerpRad(f2, this.leftArm.rotateAngleX, 1.5707964f - 1.5707964f * f32);
                    this.rightArm.rotateAngleX = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleX, (float)(1.5707964f - 1.5707964f * f32));
                    this.leftArm.rotateAngleY = this.rotlerpRad(f2, this.leftArm.rotateAngleY, (float)Math.PI);
                    this.rightArm.rotateAngleY = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleY, (float)((float)Math.PI));
                    this.leftArm.rotateAngleZ = this.rotlerpRad(f2, this.leftArm.rotateAngleZ, (float)Math.PI);
                    this.rightArm.rotateAngleZ = Mth.m_14179_((float)f1, (float)this.rightArm.rotateAngleZ, (float)((float)Math.PI));
                }
            }
            this.leftLeg.rotateAngleX = Mth.m_14179_((float)this.swimAmount, (float)this.leftLeg.rotateAngleX, (float)(0.3f * Mth.m_14089_((float)(p_102867_ * 0.33333334f + (float)Math.PI))));
            this.rightLeg.rotateAngleX = Mth.m_14179_((float)this.swimAmount, (float)this.rightLeg.rotateAngleX, (float)(0.3f * Mth.m_14089_((float)(p_102867_ * 0.33333334f))));
        }
    }

    private void poseRightArm(EntityUnderminer p_102876_) {
        switch (this.rightArmPose) {
            case EMPTY: {
                this.rightArm.rotateAngleY = 0.0f;
                break;
            }
            case BLOCK: {
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5f - 0.9424779f;
                this.rightArm.rotateAngleY = -0.5235988f;
                break;
            }
            case ITEM: {
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5f - 0.31415927f;
                this.rightArm.rotateAngleY = 0.0f;
                break;
            }
            case THROW_SPEAR: {
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5f - (float)Math.PI;
                this.rightArm.rotateAngleY = 0.0f;
                break;
            }
            case BOW_AND_ARROW: {
                this.rightArm.rotateAngleY = -0.1f + this.head.rotateAngleY;
                this.leftArm.rotateAngleY = 0.1f + this.head.rotateAngleY + 0.4f;
                this.rightArm.rotateAngleX = -1.5707964f + this.head.rotateAngleX;
                this.leftArm.rotateAngleX = -1.5707964f + this.head.rotateAngleX;
                break;
            }
            case CROSSBOW_CHARGE: 
            case CROSSBOW_HOLD: {
                break;
            }
            case SPYGLASS: {
                this.rightArm.rotateAngleX = Mth.m_14036_((float)(this.head.rotateAngleX - 1.9198622f - (p_102876_.m_6047_() ? 0.2617994f : 0.0f)), (float)-2.4f, (float)3.3f);
                this.rightArm.rotateAngleY = this.head.rotateAngleY - 0.2617994f;
                break;
            }
            case TOOT_HORN: {
                this.rightArm.rotateAngleX = Mth.m_14036_((float)this.head.rotateAngleX, (float)-1.2f, (float)1.2f) - 1.4835298f;
                this.rightArm.rotateAngleY = this.head.rotateAngleY - 0.5235988f;
            }
        }
    }

    private void poseLeftArm(EntityUnderminer p_102879_) {
        switch (this.leftArmPose) {
            case EMPTY: {
                this.leftArm.rotateAngleY = 0.0f;
                break;
            }
            case BLOCK: {
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5f - 0.9424779f;
                this.leftArm.rotateAngleY = 0.5235988f;
                break;
            }
            case ITEM: {
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5f - 0.31415927f;
                this.leftArm.rotateAngleY = 0.0f;
                break;
            }
            case THROW_SPEAR: {
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5f - (float)Math.PI;
                this.leftArm.rotateAngleY = 0.0f;
                break;
            }
            case BOW_AND_ARROW: {
                this.rightArm.rotateAngleY = -0.1f + this.head.rotateAngleY - 0.4f;
                this.leftArm.rotateAngleY = 0.1f + this.head.rotateAngleY;
                this.rightArm.rotateAngleX = -1.5707964f + this.head.rotateAngleX;
                this.leftArm.rotateAngleX = -1.5707964f + this.head.rotateAngleX;
                break;
            }
            case CROSSBOW_CHARGE: {
                break;
            }
            case CROSSBOW_HOLD: {
                break;
            }
            case SPYGLASS: {
                this.leftArm.rotateAngleX = Mth.m_14036_((float)(this.head.rotateAngleX - 1.9198622f - (p_102879_.m_6047_() ? 0.2617994f : 0.0f)), (float)-2.4f, (float)3.3f);
                this.leftArm.rotateAngleY = this.head.rotateAngleY + 0.2617994f;
                break;
            }
            case TOOT_HORN: {
                this.leftArm.rotateAngleX = Mth.m_14036_((float)this.head.rotateAngleX, (float)-1.2f, (float)1.2f) - 1.4835298f;
                this.leftArm.rotateAngleY = this.head.rotateAngleY + 0.5235988f;
            }
        }
    }

    protected void setupAttackAnimation(EntityUnderminer p_102858_, float p_102859_) {
        if (!(this.f_102608_ <= 0.0f)) {
            HumanoidArm humanoidarm = this.getAttackArm(p_102858_);
            AdvancedModelBox modelpart = this.getArm(humanoidarm);
            float f = this.f_102608_;
            this.body.rotateAngleY = Mth.m_14031_((float)(Mth.m_14116_((float)f) * ((float)Math.PI * 2))) * 0.2f;
            if (humanoidarm == HumanoidArm.LEFT) {
                this.body.rotateAngleY *= -1.0f;
            }
            this.head.rotateAngleY -= this.body.rotateAngleY;
            this.leftLeg.rotateAngleY -= this.body.rotateAngleY;
            this.rightLeg.rotateAngleY -= this.body.rotateAngleY;
            this.leftArm.rotateAngleX += this.body.rotateAngleY;
            f = 1.0f - this.f_102608_;
            f *= f;
            f *= f;
            f = 1.0f - f;
            float f1 = Mth.m_14031_((float)(f * (float)Math.PI));
            float f2 = Mth.m_14031_((float)(this.f_102608_ * (float)Math.PI)) * -(this.head.rotateAngleX - 0.7f) * 0.75f;
            modelpart.rotateAngleX -= f1 * 1.2f + f2;
            modelpart.rotateAngleY += this.body.rotateAngleY * 2.0f;
            modelpart.rotateAngleZ += Mth.m_14031_((float)(this.f_102608_ * (float)Math.PI)) * -0.4f;
        }
    }

    protected float rotlerpRad(float p_102836_, float p_102837_, float p_102838_) {
        float f = (p_102838_ - p_102837_) % ((float)Math.PI * 2);
        if (f < (float)(-Math.PI)) {
            f += (float)Math.PI * 2;
        }
        if (f >= (float)Math.PI) {
            f -= (float)Math.PI * 2;
        }
        return p_102837_ + p_102836_ * f;
    }

    private float quadraticArmUpdate(float p_102834_) {
        return -65.0f * p_102834_ + p_102834_ * p_102834_;
    }

    public void translateToHand(HumanoidArm p_102854_, PoseStack p_102855_) {
        this.getArm(p_102854_).translateAndRotate(p_102855_);
    }

    protected AdvancedModelBox getArm(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public AdvancedModelBox getHead() {
        return this.head;
    }

    private HumanoidArm getAttackArm(EntityUnderminer p_102857_) {
        HumanoidArm humanoidarm = p_102857_.m_5737_();
        return p_102857_.f_20912_ == InteractionHand.MAIN_HAND ? humanoidarm : humanoidarm.m_20828_();
    }
}
