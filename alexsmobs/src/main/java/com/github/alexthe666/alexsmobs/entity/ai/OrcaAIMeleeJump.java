/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.JumpGoal
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class OrcaAIMeleeJump
extends JumpGoal {
    private final EntityOrca dolphin;
    private int attackCooldown = 0;
    private boolean inWater;

    public OrcaAIMeleeJump(EntityOrca dolphin) {
        this.dolphin = dolphin;
    }

    public boolean m_8036_() {
        if (this.dolphin.m_5448_() == null || !this.dolphin.shouldUseJumpAttack(this.dolphin.m_5448_()) || this.dolphin.m_20096_() || !this.dolphin.m_20069_() || this.dolphin.jumpCooldown > 0) {
            return false;
        }
        BlockPos blockpos = this.dolphin.m_20183_();
        return true;
    }

    public boolean m_8045_() {
        double d0 = this.dolphin.m_20184_().f_82480_;
        return !(this.dolphin.m_5448_() == null || this.dolphin.jumpCooldown <= 0 || d0 * d0 < (double)0.03f && this.dolphin.m_146909_() != 0.0f && Math.abs(this.dolphin.m_146909_()) < 10.0f && this.dolphin.m_20069_() || this.dolphin.m_20096_());
    }

    public boolean m_6767_() {
        return false;
    }

    public void m_8056_() {
        LivingEntity target = this.dolphin.m_5448_();
        if (target != null) {
            double distanceXZ = this.dolphin.m_20275_(target.m_20185_(), this.dolphin.m_20186_(), target.m_20189_());
            if (distanceXZ < 150.0) {
                this.dolphin.m_21391_((Entity)target, 260.0f, 30.0f);
                double smoothX = Mth.m_14008_((double)Math.abs(target.m_20185_() - this.dolphin.m_20185_()), (double)0.0, (double)1.0);
                double smoothZ = Mth.m_14008_((double)Math.abs(target.m_20189_() - this.dolphin.m_20189_()), (double)0.0, (double)1.0);
                double d0 = (target.m_20185_() - this.dolphin.m_20185_()) * 0.3 * smoothX;
                double d2 = (target.m_20189_() - this.dolphin.m_20189_()) * 0.3 * smoothZ;
                float up = 1.0f + this.dolphin.m_217043_().m_188501_() * 0.8f;
                this.dolphin.m_20256_(this.dolphin.m_20184_().m_82520_(d0 * 0.3, (double)up, d2 * 0.3));
                this.dolphin.m_21573_().m_26573_();
                this.dolphin.jumpCooldown = this.dolphin.m_217043_().m_188503_(32) + 64;
            } else {
                this.dolphin.m_21573_().m_5624_((Entity)target, 1.0);
            }
        }
    }

    public void m_8041_() {
        this.dolphin.m_146926_(0.0f);
        this.attackCooldown = 0;
    }

    public void m_8037_() {
        LivingEntity target;
        boolean flag = this.inWater;
        if (!flag) {
            FluidState fluidstate = this.dolphin.m_9236_().m_6425_(this.dolphin.m_20183_());
            this.inWater = fluidstate.m_205070_(FluidTags.f_13131_);
        }
        if (this.attackCooldown > 0) {
            --this.attackCooldown;
        }
        if (this.inWater && !flag) {
            this.dolphin.m_5496_(SoundEvents.f_11805_, 1.0f, 1.0f);
        }
        if ((target = this.dolphin.m_5448_()) != null) {
            if (this.dolphin.m_20270_((Entity)target) < 3.0f && this.attackCooldown <= 0) {
                this.dolphin.onJumpHit(target);
                this.attackCooldown = 20;
            } else if (this.dolphin.m_20270_((Entity)target) < 5.0f) {
                this.dolphin.setAnimation(EntityOrca.ANIMATION_BITE);
            }
        }
        Vec3 vector3d = this.dolphin.m_20184_();
        if (vector3d.f_82480_ * vector3d.f_82480_ < (double)0.1f && this.dolphin.m_146909_() != 0.0f) {
            this.dolphin.m_146926_(Mth.m_14189_((float)this.dolphin.m_146909_(), (float)0.0f, (float)0.2f));
        } else {
            double d0 = Math.sqrt(vector3d.m_165925_());
            double d1 = Math.signum(-vector3d.f_82480_) * Math.acos(d0 / vector3d.m_82553_()) * 57.2957763671875;
            this.dolphin.m_146926_((float)d1);
        }
    }
}
