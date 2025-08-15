/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeHooks
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityMantisShrimp;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.stream.Collectors;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;

public class MantisShrimpAIBreakBlocks
extends Goal {
    private final EntityMantisShrimp mantisShrimp;
    private int idleAtFlowerTime = 0;
    private int timeoutCounter = 0;
    private int searchCooldown = 0;
    private boolean isAboveDestinationBear;
    private BlockPos destinationBlock;
    private final BlockSorter targetSorter;

    public MantisShrimpAIBreakBlocks(EntityMantisShrimp mantisShrimp) {
        this.mantisShrimp = mantisShrimp;
        this.targetSorter = new BlockSorter((Entity)mantisShrimp);
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void m_8056_() {
        super.m_8056_();
    }

    public boolean m_8036_() {
        if (!(this.mantisShrimp.m_6162_() || this.mantisShrimp.m_5448_() != null && this.mantisShrimp.m_5448_().m_6084_() || this.mantisShrimp.getCommand() != 3 || this.mantisShrimp.m_21205_().m_41619_())) {
            if (this.searchCooldown <= 0) {
                this.resetTarget();
                this.searchCooldown = 100 + this.mantisShrimp.m_217043_().m_188503_(200);
                return this.destinationBlock != null;
            }
            --this.searchCooldown;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.destinationBlock != null && this.timeoutCounter < 1200 && (this.mantisShrimp.m_5448_() == null || !this.mantisShrimp.m_5448_().m_6084_()) && this.mantisShrimp.getCommand() == 3 && !this.mantisShrimp.m_21205_().m_41619_();
    }

    public void m_8041_() {
        this.searchCooldown = 50;
        this.timeoutCounter = 0;
        this.destinationBlock = null;
    }

    public double getTargetDistanceSq() {
        return 2.3;
    }

    public void m_8037_() {
        BlockPos blockpos = this.destinationBlock;
        float yDist = (float)Math.abs((double)blockpos.m_123342_() - this.mantisShrimp.m_20186_() - (double)(this.mantisShrimp.m_20206_() / 2.0f));
        this.mantisShrimp.m_21573_().m_26519_((double)blockpos.m_123341_() + 0.5, (double)blockpos.m_123342_() + 0.5, (double)blockpos.m_123343_() + 0.5, 1.0);
        if (!this.isWithinXZDist(blockpos, this.mantisShrimp.m_20182_(), this.getTargetDistanceSq()) || yDist > 2.0f) {
            this.isAboveDestinationBear = false;
            ++this.timeoutCounter;
        } else {
            this.isAboveDestinationBear = true;
            --this.timeoutCounter;
        }
        if (this.timeoutCounter > 2400) {
            this.m_8041_();
        }
        if (this.getIsAboveDestination()) {
            this.mantisShrimp.m_7618_(EntityAnchorArgument.Anchor.EYES, new Vec3((double)this.destinationBlock.m_123341_() + 0.5, (double)this.destinationBlock.m_123342_(), (double)this.destinationBlock.m_123343_() + 0.5));
            if (this.idleAtFlowerTime >= 2) {
                this.idleAtFlowerTime = 0;
                this.breakBlock();
                this.m_8041_();
            } else {
                this.mantisShrimp.punch();
                ++this.idleAtFlowerTime;
            }
        }
    }

    private void resetTarget() {
        ArrayList<BlockPos> allBlocks = new ArrayList<BlockPos>();
        int radius = 16;
        for (BlockPos pos : BlockPos.m_121990_((BlockPos)this.mantisShrimp.m_20183_().m_7918_(-radius, -radius, -radius), (BlockPos)this.mantisShrimp.m_20183_().m_7918_(radius, radius, radius)).map(BlockPos::m_7949_).collect(Collectors.toList())) {
            if (this.mantisShrimp.m_9236_().m_46859_(pos) || !this.shouldMoveTo((LevelReader)this.mantisShrimp.m_9236_(), pos) || this.mantisShrimp.m_20069_() && !this.isBlockTouchingWater(pos)) continue;
            allBlocks.add(pos);
        }
        if (!allBlocks.isEmpty()) {
            allBlocks.sort(this.targetSorter);
            for (BlockPos pos : allBlocks) {
                if (!this.hasLineOfSightBlock(pos)) continue;
                this.destinationBlock = pos;
                return;
            }
        }
        this.destinationBlock = null;
    }

    private boolean isBlockTouchingWater(BlockPos pos) {
        for (Direction dir : Direction.values()) {
            if (!this.mantisShrimp.m_9236_().m_6425_(pos.m_121945_(dir)).m_205070_(FluidTags.f_13131_)) continue;
            return true;
        }
        return false;
    }

    private boolean isWithinXZDist(BlockPos blockpos, Vec3 positionVec, double distance) {
        return blockpos.m_123331_((Vec3i)AMBlockPos.fromCoords(positionVec.m_7096_(), blockpos.m_123342_(), positionVec.m_7094_())) < distance * distance;
    }

    protected boolean getIsAboveDestination() {
        return this.isAboveDestinationBear;
    }

    private void breakBlock() {
        if (this.shouldMoveTo((LevelReader)this.mantisShrimp.m_9236_(), this.destinationBlock)) {
            BlockState state = this.mantisShrimp.m_9236_().m_8055_(this.destinationBlock);
            if (!this.mantisShrimp.m_9236_().m_46859_(this.destinationBlock) && ForgeHooks.canEntityDestroy((Level)this.mantisShrimp.m_9236_(), (BlockPos)this.destinationBlock, (LivingEntity)this.mantisShrimp) && state.m_60800_((BlockGetter)this.mantisShrimp.m_9236_(), this.destinationBlock) >= 0.0f) {
                this.mantisShrimp.m_9236_().m_46961_(this.destinationBlock, true);
            }
        }
    }

    private boolean hasLineOfSightBlock(BlockPos destinationBlock) {
        Vec3 Vector3d = new Vec3(this.mantisShrimp.m_20185_(), this.mantisShrimp.m_20188_(), this.mantisShrimp.m_20189_());
        Vec3 blockVec = Vec3.m_82512_((Vec3i)destinationBlock);
        BlockHitResult result = this.mantisShrimp.m_9236_().m_45547_(new ClipContext(Vector3d, blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this.mantisShrimp));
        return result.m_82425_().equals((Object)destinationBlock);
    }

    protected boolean shouldMoveTo(LevelReader worldIn, BlockPos pos) {
        Item blockItem = worldIn.m_8055_(pos).m_60734_().m_5456_();
        return this.mantisShrimp.m_21205_().m_41720_() == blockItem;
    }

    public record BlockSorter(Entity entity) implements Comparator<BlockPos>
    {
        @Override
        public int compare(BlockPos pos1, BlockPos pos2) {
            double distance1 = this.getDistance(pos1);
            double distance2 = this.getDistance(pos2);
            return Double.compare(distance1, distance2);
        }

        private double getDistance(BlockPos pos) {
            double deltaX = this.entity.m_20185_() - ((double)pos.m_123341_() + 0.5);
            double deltaY = this.entity.m_20186_() + (double)this.entity.m_20192_() - ((double)pos.m_123342_() + 0.5);
            double deltaZ = this.entity.m_20189_() - ((double)pos.m_123343_() + 0.5);
            return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
        }
    }
}
