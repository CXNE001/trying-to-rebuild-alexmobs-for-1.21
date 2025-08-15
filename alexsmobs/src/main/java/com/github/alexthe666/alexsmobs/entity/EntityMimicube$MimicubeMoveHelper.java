/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

private static class EntityMimicube.MimicubeMoveHelper
extends MoveControl {
    private final EntityMimicube slime;
    private float yRot;
    private int jumpDelay;
    private boolean isAggressive;

    public EntityMimicube.MimicubeMoveHelper(EntityMimicube slimeIn) {
        super((Mob)slimeIn);
        this.slime = slimeIn;
        this.yRot = 180.0f * slimeIn.m_146908_() / (float)Math.PI;
    }

    public void setDirection(float yRotIn, boolean aggressive) {
        this.yRot = yRotIn;
        this.isAggressive = aggressive;
    }

    public void setSpeed(double speedIn) {
        this.f_24978_ = speedIn;
        this.f_24981_ = MoveControl.Operation.MOVE_TO;
    }

    public void m_8126_() {
        if (this.f_24974_.m_20096_()) {
            this.f_24974_.m_7910_((float)(this.f_24978_ * this.f_24974_.m_21133_(Attributes.f_22279_)));
            if (this.jumpDelay-- <= 0 && this.f_24981_ != MoveControl.Operation.WAIT) {
                this.jumpDelay = this.slime.getJumpDelay();
                if (this.f_24974_.m_5448_() != null) {
                    this.jumpDelay /= 3;
                }
                this.slime.m_21569_().m_24901_();
                this.slime.m_5496_(this.slime.getJumpSound(), this.slime.m_6121_(), this.slime.m_6100_());
            } else {
                this.slime.f_20900_ = 0.0f;
                this.slime.f_20902_ = 0.0f;
                this.f_24974_.m_7910_(0.0f);
            }
        }
        super.m_8126_();
    }
}
