/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

public class AnimalAILeaveWaterLava
extends Goal {
    private final PathfinderMob creature;
    private BlockPos targetPos;
    private final int executionChance = 30;

    public AnimalAILeaveWaterLava(PathfinderMob creature) {
        this.creature = creature;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if ((this.creature.m_9236_().m_6425_(this.creature.m_20183_()).m_205070_(FluidTags.f_13131_) || this.creature.m_9236_().m_6425_(this.creature.m_20183_()).m_205070_(FluidTags.f_13132_)) && this.creature instanceof ISemiAquatic && ((ISemiAquatic)this.creature).shouldLeaveWater() && (this.creature.m_5448_() != null || this.creature.m_217043_().m_188503_(30) == 0)) {
            this.targetPos = this.generateTarget();
            return this.targetPos != null;
        }
        return false;
    }

    public void m_8056_() {
        if (this.targetPos != null) {
            this.creature.m_21573_().m_26519_((double)this.targetPos.m_123341_(), (double)this.targetPos.m_123342_(), (double)this.targetPos.m_123343_(), 1.0);
        }
    }

    public void m_8037_() {
        if (this.targetPos != null) {
            this.creature.m_21573_().m_26519_((double)this.targetPos.m_123341_(), (double)this.targetPos.m_123342_(), (double)this.targetPos.m_123343_(), 1.0);
        }
        if (this.creature.f_19862_ && (this.creature.m_20069_() || this.creature.m_20077_())) {
            float f1 = this.creature.m_146908_() * ((float)Math.PI / 180);
            this.creature.m_20256_(this.creature.m_20184_().m_82520_((double)(-Mth.m_14031_((float)f1) * 0.2f), 0.1, (double)(Mth.m_14089_((float)f1) * 0.2f)));
        }
    }

    public boolean m_8045_() {
        if (this.creature instanceof ISemiAquatic && !((ISemiAquatic)this.creature).shouldLeaveWater()) {
            this.creature.m_21573_().m_26573_();
            return false;
        }
        return !this.creature.m_21573_().m_26571_() && this.targetPos != null && !this.creature.m_9236_().m_6425_(this.targetPos).m_205070_(FluidTags.f_13131_) && !this.creature.m_9236_().m_6425_(this.targetPos).m_205070_(FluidTags.f_13132_);
    }

    public BlockPos generateTarget() {
        Vec3 vector3d = LandRandomPos.m_148488_((PathfinderMob)this.creature, (int)23, (int)7);
        for (int tries = 0; vector3d != null && tries < 8; ++tries) {
            boolean waterDetected = false;
            for (BlockPos blockpos1 : BlockPos.m_121976_((int)Mth.m_14107_((double)(vector3d.f_82479_ - 2.0)), (int)Mth.m_14107_((double)(vector3d.f_82480_ - 1.0)), (int)Mth.m_14107_((double)(vector3d.f_82481_ - 2.0)), (int)Mth.m_14107_((double)(vector3d.f_82479_ + 2.0)), (int)Mth.m_14107_((double)vector3d.f_82480_), (int)Mth.m_14107_((double)(vector3d.f_82481_ + 2.0)))) {
                if (!this.creature.m_9236_().m_6425_(blockpos1).m_205070_(FluidTags.f_13131_) && !this.creature.m_9236_().m_6425_(blockpos1).m_205070_(FluidTags.f_13132_)) continue;
                waterDetected = true;
                break;
            }
            if (!waterDetected) {
                return AMBlockPos.fromVec3(vector3d);
            }
            vector3d = LandRandomPos.m_148488_((PathfinderMob)this.creature, (int)23, (int)7);
        }
        return null;
    }
}
