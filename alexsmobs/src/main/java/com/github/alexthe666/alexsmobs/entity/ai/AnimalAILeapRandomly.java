/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityBunfungus;
import java.util.EnumSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class AnimalAILeapRandomly
extends Goal {
    private final PathfinderMob mob;
    private final int chance;
    private final int maxLeapDistance;
    private Vec3 leapToPos = null;

    public AnimalAILeapRandomly(PathfinderMob mob, int chance, int maxLeapDistance) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.mob = mob;
        this.chance = chance;
        this.maxLeapDistance = maxLeapDistance;
    }

    public boolean m_8036_() {
        Vec3 found;
        if (this.mob.m_217043_().m_188503_(this.chance) == 0 && this.mob.m_20096_() && this.mob.m_21573_().m_26571_() && (found = LandRandomPos.m_148488_((PathfinderMob)this.mob, (int)this.maxLeapDistance, (int)this.maxLeapDistance)) != null && this.mob.m_20238_(found) < (double)(this.maxLeapDistance * this.maxLeapDistance) && this.hasLineOfSightBlock(found)) {
            this.leapToPos = found;
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.leapToPos != null && this.mob.m_20238_(this.leapToPos) < (double)(this.maxLeapDistance * this.maxLeapDistance) && this.hasLineOfSightBlock(this.leapToPos);
    }

    private boolean hasLineOfSightBlock(Vec3 blockVec) {
        Vec3 Vector3d = new Vec3(this.mob.m_20185_(), this.mob.m_20188_(), this.mob.m_20189_());
        BlockHitResult result = this.mob.m_9236_().m_45547_(new ClipContext(Vector3d, blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this.mob));
        return blockVec.m_82554_(result.m_82450_()) < (double)1.2f;
    }

    public void m_8041_() {
        super.m_8041_();
        this.leapToPos = null;
    }

    public void m_8056_() {
        if (this.leapToPos != null) {
            Vec3 vector3d = this.mob.m_20184_();
            Vec3 vector3d1 = new Vec3(this.leapToPos.f_82479_ - this.mob.m_20185_(), 0.0, this.leapToPos.f_82481_ - this.mob.m_20189_());
            if (vector3d1.m_82556_() > 1.0E-7) {
                vector3d1 = vector3d1.m_82541_().m_82490_(0.9).m_82549_(vector3d.m_82490_(0.8));
            }
            if (this.mob instanceof EntityBunfungus) {
                ((EntityBunfungus)this.mob).onJump();
            }
            this.mob.m_20334_(vector3d1.f_82479_, (double)0.6f, vector3d1.f_82481_);
            this.mob.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
            this.mob.f_20883_ = this.mob.m_146908_();
            this.mob.f_20885_ = this.mob.m_146908_();
            this.leapToPos = null;
        }
    }
}
