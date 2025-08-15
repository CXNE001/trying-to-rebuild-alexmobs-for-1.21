/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MoveToBlockGoal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class GorillaAIForageLeaves
extends MoveToBlockGoal {
    private final EntityGorilla gorilla;
    private int idleAtLeavesTime = 0;
    private boolean isAboveDestinationBear;

    public GorillaAIForageLeaves(EntityGorilla gorilla) {
        super((PathfinderMob)gorilla, 1.0, 32, 3);
        this.gorilla = gorilla;
    }

    public boolean m_8036_() {
        return !this.gorilla.m_6162_() && this.gorilla.m_21205_().m_41619_() && super.m_8036_();
    }

    public void m_8041_() {
        this.idleAtLeavesTime = 0;
    }

    public double m_8052_() {
        return 2.0;
    }

    public void m_8037_() {
        super.m_8037_();
        BlockPos blockpos = this.m_6669_();
        if (!this.isWithinXZDist(blockpos, this.f_25598_.m_20182_(), this.m_8052_())) {
            this.isAboveDestinationBear = false;
            ++this.f_25601_;
            if (this.m_8064_()) {
                this.f_25598_.m_21573_().m_26519_((double)blockpos.m_123341_() + 0.5, (double)blockpos.m_123342_(), (double)blockpos.m_123343_() + 0.5, this.f_25599_);
            }
        } else {
            this.isAboveDestinationBear = true;
            --this.f_25601_;
        }
        if (this.m_25625_() && Math.abs(this.gorilla.m_20186_() - (double)this.f_25602_.m_123342_()) <= 3.0) {
            this.gorilla.m_7618_(EntityAnchorArgument.Anchor.EYES, new Vec3((double)this.f_25602_.m_123341_() + 0.5, (double)this.f_25602_.m_123342_(), (double)this.f_25602_.m_123343_() + 0.5));
            if (this.gorilla.m_20186_() + 2.0 < (double)this.f_25602_.m_123342_()) {
                this.gorilla.setAnimation(this.gorilla.m_217043_().m_188499_() ? EntityGorilla.ANIMATION_BREAKBLOCK_L : EntityGorilla.ANIMATION_BREAKBLOCK_R);
                this.gorilla.maxStandTime = 60;
                this.gorilla.setStanding(true);
            } else if (this.gorilla.getAnimation() == IAnimatedEntity.NO_ANIMATION) {
                this.gorilla.setAnimation(this.gorilla.m_217043_().m_188499_() ? EntityGorilla.ANIMATION_BREAKBLOCK_L : EntityGorilla.ANIMATION_BREAKBLOCK_R);
            }
            if (this.idleAtLeavesTime >= 20) {
                this.breakLeaves();
            } else {
                ++this.idleAtLeavesTime;
            }
        }
    }

    private boolean isWithinXZDist(BlockPos blockpos, Vec3 positionVec, double distance) {
        return blockpos.m_123331_((Vec3i)AMBlockPos.fromCoords(positionVec.m_7096_(), blockpos.m_123342_(), positionVec.m_7094_())) < distance * distance;
    }

    protected boolean m_25625_() {
        return this.isAboveDestinationBear;
    }

    private void breakLeaves() {
        BlockState blockstate;
        if (ForgeEventFactory.getMobGriefingEvent((Level)this.gorilla.m_9236_(), (Entity)this.gorilla) && (blockstate = this.gorilla.m_9236_().m_8055_(this.f_25602_)).m_204336_(AMTagRegistry.GORILLA_BREAKABLES)) {
            this.gorilla.m_9236_().m_46961_(this.f_25602_, false);
            RandomSource rand = this.gorilla.m_217043_();
            ItemStack stack = new ItemStack((ItemLike)blockstate.m_60734_().m_5456_());
            ItemEntity itementity = new ItemEntity(this.gorilla.m_9236_(), (double)((float)this.f_25602_.m_123341_() + rand.m_188501_()), (double)((float)this.f_25602_.m_123342_() + rand.m_188501_()), (double)((float)this.f_25602_.m_123343_() + rand.m_188501_()), stack);
            itementity.m_32060_();
            this.gorilla.m_9236_().m_7967_((Entity)itementity);
            if (blockstate.m_204336_(AMTagRegistry.DROPS_BANANAS) && rand.m_188503_(30) == 0) {
                ItemStack banana = new ItemStack((ItemLike)AMItemRegistry.BANANA.get());
                ItemEntity itementity2 = new ItemEntity(this.gorilla.m_9236_(), (double)((float)this.f_25602_.m_123341_() + rand.m_188501_()), (double)((float)this.f_25602_.m_123342_() + rand.m_188501_()), (double)((float)this.f_25602_.m_123343_() + rand.m_188501_()), banana);
                itementity2.m_32060_();
                this.gorilla.m_9236_().m_7967_((Entity)itementity2);
            }
            this.m_8041_();
        }
    }

    protected boolean m_6465_(LevelReader worldIn, BlockPos pos) {
        return worldIn.m_8055_(pos).m_204336_(AMTagRegistry.GORILLA_BREAKABLES);
    }
}
