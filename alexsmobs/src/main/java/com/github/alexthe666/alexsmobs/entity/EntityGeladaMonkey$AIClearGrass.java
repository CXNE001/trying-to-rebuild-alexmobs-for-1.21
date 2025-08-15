/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityGeladaMonkey.AIClearGrass
extends Goal {
    private BlockPos target;

    public EntityGeladaMonkey.AIClearGrass() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (EntityGeladaMonkey.this.getClearGrassTime() > 0) {
            this.target = this.generateTarget();
            return this.target != null;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.target != null && EntityGeladaMonkey.this.m_9236_().m_8055_(this.target).m_204336_(AMTagRegistry.GELADA_MONKEY_GRASS);
    }

    public void m_8037_() {
        EntityGeladaMonkey.this.setSitting(false);
        EntityGeladaMonkey.this.m_21573_().m_26519_((double)((float)this.target.m_123341_() + 0.5f), (double)((float)this.target.m_123342_() + 0.5f), (double)((float)this.target.m_123343_() + 0.5f), (double)1.4f);
        if (EntityGeladaMonkey.this.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < (double)3.4f) {
            if (EntityGeladaMonkey.this.getAnimation() == IAnimatedEntity.NO_ANIMATION) {
                EntityGeladaMonkey.this.attackAnimation();
            } else if (EntityGeladaMonkey.this.getAnimationTick() > 7) {
                EntityGeladaMonkey.this.m_9236_().m_46961_(this.target, true);
            }
        }
    }

    public BlockPos generateTarget() {
        BlockPos blockpos = null;
        Random random = new Random();
        int range = 7;
        for (int i = 0; i < 15; ++i) {
            BlockPos blockpos1 = EntityGeladaMonkey.this.m_20183_().m_7918_(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
            while (EntityGeladaMonkey.this.m_9236_().m_46859_(blockpos1) && blockpos1.m_123342_() > -63) {
                blockpos1 = blockpos1.m_7495_();
            }
            if (!EntityGeladaMonkey.this.m_9236_().m_8055_(blockpos1).m_204336_(AMTagRegistry.GELADA_MONKEY_GRASS)) continue;
            blockpos = blockpos1;
        }
        return blockpos;
    }
}
