/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MoveToBlockGoal
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class LeafcutterAntAIForageLeaves
extends MoveToBlockGoal {
    private final EntityLeafcutterAnt ant;
    private int idleAtLeavesTime = 0;
    private int randomLeafCheckCooldown = 40;
    private BlockPos logStartPos = null;
    private BlockPos logTopPos = null;
    private final int searchRange;
    private final int verticalSearchRange;
    private int moveToCooldown = 0;

    public LeafcutterAntAIForageLeaves(EntityLeafcutterAnt LeafcutterAnt) {
        super((PathfinderMob)LeafcutterAnt, 1.0, 15, 3);
        this.searchRange = 15;
        this.verticalSearchRange = 3;
        this.ant = LeafcutterAnt;
    }

    public boolean m_8036_() {
        return !this.ant.m_6162_() && !this.ant.hasLeaf() && !this.ant.m_6162_() && !this.ant.isQueen() && super.m_8036_();
    }

    public boolean m_8045_() {
        return super.m_8045_() && !this.ant.hasLeaf();
    }

    public void m_8041_() {
        this.idleAtLeavesTime = 0;
        this.logStartPos = null;
        this.logTopPos = null;
    }

    public void m_8056_() {
        this.moveToCooldown = 10 + this.ant.m_217043_().m_188503_(10);
    }

    public double m_8052_() {
        return 2.0;
    }

    public boolean m_8064_() {
        return this.f_25601_ % 40 == 0 && this.logStartPos == null;
    }

    public void m_8037_() {
        if (this.moveToCooldown > 0) {
            --this.moveToCooldown;
        }
        if (this.randomLeafCheckCooldown > 0) {
            --this.randomLeafCheckCooldown;
        } else {
            this.randomLeafCheckCooldown = 30 + this.ant.m_217043_().m_188503_(50);
            for (Direction dir : Direction.values()) {
                BlockPos offset = this.ant.m_20183_().m_121945_(dir);
                if (!this.m_6465_((LevelReader)this.ant.m_9236_(), offset) || this.ant.m_217043_().m_188503_(1) != 0) continue;
                this.f_25602_ = offset;
                this.logStartPos = null;
            }
        }
        if (this.ant.getAttachmentFacing() == Direction.UP) {
            this.ant.m_21566_().m_6849_((double)((float)this.f_25602_.m_123341_() + 0.5f), (double)this.f_25602_.m_123342_() - 1.0, (double)((float)this.f_25602_.m_123343_() + 0.5f), 1.0);
            this.ant.m_20256_(this.ant.m_20184_().m_82520_(0.0, 0.5, 0.0));
            if (this.ant.m_217043_().m_188503_(2) == 0 && this.m_6465_((LevelReader)this.ant.m_9236_(), this.ant.m_20183_().m_7494_())) {
                this.f_25602_ = this.ant.m_20183_().m_7494_();
            }
        } else if ((double)this.f_25602_.m_123342_() > this.ant.m_20186_() + 2.0 || this.logStartPos != null) {
            this.ant.m_21573_().m_26573_();
            if (this.ant.m_217043_().m_188503_(5) == 0 && this.m_6465_((LevelReader)this.ant.m_9236_(), this.ant.m_20183_().m_7495_())) {
                this.f_25602_ = this.ant.m_20183_().m_7495_();
            }
            if (this.logStartPos != null) {
                double xDif = (double)this.logStartPos.m_123341_() + 0.5 - this.ant.m_20185_();
                double zDif = (double)this.logStartPos.m_123343_() + 0.5 - this.ant.m_20189_();
                float f = (float)(Mth.m_14136_((double)zDif, (double)xDif) * 57.2957763671875) - 90.0f;
                this.ant.m_146922_(f);
                this.ant.f_20883_ = this.ant.m_146908_();
                Vec3 vec = new Vec3((double)this.logStartPos.m_123341_() + 0.5, this.ant.m_20186_(), (double)this.logStartPos.m_123343_() + 0.5);
                vec = vec.m_82546_(this.ant.m_20182_());
                if (this.ant.m_20096_() || this.ant.m_6147_()) {
                    this.ant.m_20256_(vec.m_82541_().m_82542_(0.1, 0.0, 0.1).m_82520_(0.0, this.ant.m_20184_().f_82480_, 0.0));
                }
                if (this.moveToCooldown <= 0) {
                    this.moveToCooldown = 20 + this.ant.m_217043_().m_188503_(30);
                    this.ant.m_21573_().m_26519_((double)this.logStartPos.m_123341_(), this.ant.m_20186_(), (double)this.logStartPos.m_123343_(), 1.0);
                }
                if (Math.abs(xDif) < 0.6 && Math.abs(zDif) < 0.6) {
                    this.ant.m_20256_(this.ant.m_20184_().m_82542_(0.0, 1.0, 0.0));
                    this.ant.m_21566_().m_6849_((double)this.logStartPos.m_123341_() + 0.5, this.ant.m_20186_() + 2.0, (double)this.logStartPos.m_123343_() + 0.5, 1.0);
                    BlockPos test = new BlockPos(this.logStartPos.m_123341_(), (int)this.ant.m_20186_(), this.logStartPos.m_123343_());
                    if (!this.ant.m_9236_().m_8055_(test).m_204336_(BlockTags.f_13106_) && this.ant.getAttachmentFacing() == Direction.DOWN) {
                        this.m_8041_();
                        return;
                    }
                }
            } else {
                for (int i = 0; i < 15; ++i) {
                    BlockPos test = this.f_25602_.m_7918_(6 - this.ant.m_217043_().m_188503_(12), -this.ant.m_217043_().m_188503_(7), 6 - this.ant.m_217043_().m_188503_(12));
                    if (!this.ant.m_9236_().m_8055_(test).m_204336_(BlockTags.f_13106_)) continue;
                    this.logStartPos = test;
                    break;
                }
            }
            ++this.f_25601_;
        } else {
            super.m_8037_();
            this.logStartPos = null;
        }
        if (this.m_25625_() || this.ant.m_20183_().m_7494_().equals((Object)this.f_25602_)) {
            this.ant.m_7618_(EntityAnchorArgument.Anchor.EYES, new Vec3((double)this.f_25602_.m_123341_() + 0.5, (double)this.f_25602_.m_123342_(), (double)this.f_25602_.m_123343_() + 0.5));
            this.ant.setAnimation(EntityLeafcutterAnt.ANIMATION_BITE);
            if (this.idleAtLeavesTime >= 6) {
                this.ant.setLeafHarvestedPos(this.f_25602_);
                this.ant.setLeafHarvestedState(this.ant.m_9236_().m_8055_(this.f_25602_));
                if (!this.ant.hasLeaf()) {
                    this.breakLeaves();
                }
                this.ant.setLeaf(true);
                this.m_8041_();
                this.idleAtLeavesTime = 0;
            } else {
                ++this.idleAtLeavesTime;
            }
        }
    }

    private void breakLeaves() {
        BlockState blockstate = this.ant.m_9236_().m_8055_(this.f_25602_);
        if (blockstate.m_204336_(AMTagRegistry.LEAFCUTTER_ANT_BREAKABLES) && ForgeEventFactory.getMobGriefingEvent((Level)this.ant.m_9236_(), (Entity)this.ant)) {
            this.ant.m_9236_().m_46961_(this.f_25602_, false);
            if ((double)this.ant.m_217043_().m_188501_() > AMConfig.leafcutterAntBreakLeavesChance) {
                this.ant.m_9236_().m_46597_(this.f_25602_, blockstate);
            }
        }
    }

    protected boolean m_6465_(LevelReader worldIn, BlockPos pos) {
        return worldIn.m_8055_(pos).m_204336_(AMTagRegistry.LEAFCUTTER_ANT_BREAKABLES);
    }

    protected boolean m_25626_() {
        int i = this.searchRange;
        int j = this.verticalSearchRange;
        BlockPos blockpos = this.f_25598_.m_20183_();
        if (this.ant.hasHive() && this.ant.getHivePos() != null) {
            blockpos = this.ant.getHivePos();
            i *= 2;
        }
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int k = this.f_25603_;
        while (k <= j) {
            for (int l = 0; l < i; ++l) {
                int i1 = 0;
                while (i1 <= l) {
                    int j1;
                    int n = j1 = i1 < l && i1 > -l ? l : 0;
                    while (j1 <= l) {
                        blockpos$mutableblockpos.m_122154_((Vec3i)blockpos, i1, k - 1, j1);
                        if (this.f_25598_.m_21444_((BlockPos)blockpos$mutableblockpos) && this.m_6465_((LevelReader)this.f_25598_.m_9236_(), (BlockPos)blockpos$mutableblockpos)) {
                            this.f_25602_ = blockpos$mutableblockpos;
                            return true;
                        }
                        j1 = j1 > 0 ? -j1 : 1 - j1;
                    }
                    i1 = i1 > 0 ? -i1 : 1 - i1;
                }
            }
            k = k > 0 ? -k : 1 - k;
        }
        return false;
    }
}
