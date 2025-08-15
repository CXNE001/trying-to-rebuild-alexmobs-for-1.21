/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticAIRandomSwimming;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

private class EntityMimicOctopus.AISwim
extends SemiAquaticAIRandomSwimming {
    public EntityMimicOctopus.AISwim() {
        super((Animal)EntityMimicOctopus.this, 1.0, 35);
    }

    @Override
    protected Vec3 findSurfaceTarget(PathfinderMob creature, int i, int i1) {
        if (creature.m_217043_().m_188503_(5) == 0) {
            return super.findSurfaceTarget(creature, i, i1);
        }
        BlockPos downPos = creature.m_20183_();
        while (creature.m_9236_().m_6425_(downPos).m_205070_(FluidTags.f_13131_) || creature.m_9236_().m_6425_(downPos).m_205070_(FluidTags.f_13132_)) {
            downPos = downPos.m_7495_();
        }
        if (EntityMimicOctopus.this.m_9236_().m_8055_(downPos).m_60815_() && EntityMimicOctopus.this.m_9236_().m_8055_(downPos).m_60734_() != Blocks.f_50450_) {
            return new Vec3((double)((float)downPos.m_123341_() + 0.5f), (double)downPos.m_123342_(), (double)((float)downPos.m_123343_() + 0.5f));
        }
        return null;
    }
}
