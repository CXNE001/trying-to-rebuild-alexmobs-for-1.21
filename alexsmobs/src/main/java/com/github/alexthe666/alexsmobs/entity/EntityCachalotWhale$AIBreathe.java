/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.pathfinder.PathComputationType
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;

class EntityCachalotWhale.AIBreathe
extends Goal {
    public EntityCachalotWhale.AIBreathe() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityCachalotWhale.this.m_20146_() < 140;
    }

    public boolean m_8045_() {
        return this.m_8036_();
    }

    public boolean m_6767_() {
        return false;
    }

    public void m_8056_() {
        this.navigate();
    }

    private void navigate() {
        Iterable lvt_1_1_ = BlockPos.m_121976_((int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20185_() - 1.0)), (int)Mth.m_14107_((double)EntityCachalotWhale.this.m_20186_()), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20189_() - 1.0)), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20185_() + 1.0)), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20186_() + 8.0)), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20189_() + 1.0)));
        BlockPos lvt_2_1_ = null;
        for (BlockPos lvt_4_1_ : lvt_1_1_) {
            if (!this.canBreatheAt((LevelReader)EntityCachalotWhale.this.m_9236_(), lvt_4_1_)) continue;
            lvt_2_1_ = lvt_4_1_.m_6625_((int)((double)EntityCachalotWhale.this.m_20206_() * 0.25));
            break;
        }
        if (lvt_2_1_ == null) {
            lvt_2_1_ = AMBlockPos.fromCoords(EntityCachalotWhale.this.m_20185_(), EntityCachalotWhale.this.m_20186_() + 4.0, EntityCachalotWhale.this.m_20189_());
        }
        if (EntityCachalotWhale.this.m_204029_(FluidTags.f_13131_)) {
            EntityCachalotWhale.this.m_20256_(EntityCachalotWhale.this.m_20184_().m_82520_(0.0, (double)0.05f, 0.0));
        }
        EntityCachalotWhale.this.m_21573_().m_26519_((double)lvt_2_1_.m_123341_(), (double)lvt_2_1_.m_123342_(), (double)lvt_2_1_.m_123343_(), 0.7);
    }

    public void m_8037_() {
        this.navigate();
    }

    private boolean canBreatheAt(LevelReader p_205140_1_, BlockPos p_205140_2_) {
        BlockState lvt_3_1_ = p_205140_1_.m_8055_(p_205140_2_);
        return (p_205140_1_.m_6425_(p_205140_2_).m_76178_() || lvt_3_1_.m_60713_(Blocks.f_50628_)) && lvt_3_1_.m_60647_((BlockGetter)p_205140_1_, p_205140_2_, PathComputationType.LAND);
    }
}
