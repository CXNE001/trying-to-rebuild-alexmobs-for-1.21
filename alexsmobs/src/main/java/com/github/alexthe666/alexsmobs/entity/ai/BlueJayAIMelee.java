/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityBlueJay;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class BlueJayAIMelee
extends Goal {
    private final EntityBlueJay blueJay;
    float circlingTime = 0.0f;
    float circleDistance = 1.0f;
    float yLevel = 2.0f;
    boolean clockwise = false;
    private int maxCircleTime;

    public BlueJayAIMelee(EntityBlueJay blueJay) {
        this.blueJay = blueJay;
    }

    public boolean m_8036_() {
        LivingEntity entity = this.blueJay.m_5448_();
        return entity != null && entity.m_6084_();
    }

    public void m_8056_() {
        this.clockwise = this.blueJay.m_217043_().m_188499_();
        this.yLevel = this.blueJay.m_217043_().m_188503_(2);
        this.circlingTime = 0.0f;
        this.maxCircleTime = 20 + this.blueJay.m_217043_().m_188503_(20);
        this.circleDistance = 0.5f + this.blueJay.m_217043_().m_188501_() * 2.0f;
    }

    public void m_8041_() {
        this.clockwise = this.blueJay.m_217043_().m_188499_();
        this.yLevel = this.blueJay.m_217043_().m_188503_(2);
        this.circlingTime = 0.0f;
        this.maxCircleTime = 20 + this.blueJay.m_217043_().m_188503_(20);
        this.circleDistance = 0.5f + this.blueJay.m_217043_().m_188501_() * 2.0f;
        if (this.blueJay.m_20096_()) {
            this.blueJay.setFlying(false);
        }
    }

    public void m_8037_() {
        LivingEntity target;
        if (this.blueJay.isFlying()) {
            this.circlingTime += 1.0f;
        }
        if ((target = this.blueJay.m_5448_()) != null) {
            if (this.blueJay.m_20270_((Entity)target) < 3.0f) {
                this.blueJay.peck();
                target.m_6469_(target.m_269291_().m_269264_(), 1.0f);
                this.m_8041_();
            }
            if (this.circlingTime > (float)this.maxCircleTime) {
                this.blueJay.m_21566_().m_6849_(target.m_20185_(), target.m_20186_() + (double)(target.m_20192_() / 2.0f), target.m_20189_(), (double)1.6f);
            } else {
                Vec3 circlePos = this.getVultureCirclePos(target.m_20182_());
                if (circlePos == null) {
                    circlePos = target.m_20182_();
                }
                this.blueJay.setFlying(true);
                this.blueJay.m_21566_().m_6849_(circlePos.m_7096_(), circlePos.m_7098_() + (double)target.m_20192_() + (double)0.2f, circlePos.m_7094_(), (double)1.6f);
            }
        }
    }

    public Vec3 getVultureCirclePos(Vec3 target) {
        float angle = 0.2268928f * (this.clockwise ? -this.circlingTime : this.circlingTime);
        double extraX = this.circleDistance * Mth.m_14031_((float)angle);
        double extraZ = this.circleDistance * Mth.m_14089_((float)angle);
        Vec3 pos = new Vec3(target.m_7096_() + extraX, target.m_7098_() + (double)this.yLevel, target.m_7094_() + extraZ);
        if (this.blueJay.m_9236_().m_46859_(AMBlockPos.fromVec3(pos))) {
            return pos;
        }
        return null;
    }
}
