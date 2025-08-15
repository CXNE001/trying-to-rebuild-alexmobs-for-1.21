/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MoveToBlockGoal
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.block.BlockReptileEgg;
import com.github.alexthe666.alexsmobs.entity.EntityPlatypus;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;

static class EntityPlatypus.LayEggGoal
extends MoveToBlockGoal {
    private final EntityPlatypus turtle;
    private int digTime;

    EntityPlatypus.LayEggGoal(EntityPlatypus turtle, double speedIn) {
        super((PathfinderMob)turtle, speedIn, 16);
        this.turtle = turtle;
    }

    public void m_8041_() {
        this.digTime = 0;
    }

    public boolean m_8036_() {
        return this.turtle.hasEgg() && super.m_8036_();
    }

    public boolean m_8045_() {
        return super.m_8045_() && this.turtle.hasEgg();
    }

    public double m_8052_() {
        return (double)this.turtle.m_20205_() + 0.5;
    }

    public void m_8037_() {
        super.m_8037_();
        if (!this.turtle.m_20069_() && this.m_25625_()) {
            BlockPos blockpos = this.turtle.m_20183_();
            Level world = this.turtle.m_9236_();
            this.turtle.m_146850_(GameEvent.f_157797_);
            world.m_5594_(null, blockpos, SoundEvents.f_12486_, SoundSource.BLOCKS, 0.3f, 0.9f + world.f_46441_.m_188501_() * 0.2f);
            world.m_7731_(this.f_25602_.m_7494_(), (BlockState)((Block)AMBlockRegistry.PLATYPUS_EGG.get()).m_49966_().m_61124_((Property)BlockReptileEgg.EGGS, (Comparable)Integer.valueOf(this.turtle.f_19796_.m_188503_(3) + 1)), 3);
            this.turtle.setHasEgg(false);
            this.turtle.setDigging(false);
            this.turtle.m_27601_(600);
        }
    }

    protected boolean m_6465_(LevelReader worldIn, BlockPos pos) {
        return worldIn.m_46859_(pos.m_7494_()) && BlockReptileEgg.isProperHabitat((BlockGetter)worldIn, pos);
    }
}
