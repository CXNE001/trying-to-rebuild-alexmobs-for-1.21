/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.LadderBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.pathfinder.Node
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms;

import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.AbstractAdvancedPathNavigate;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.IStuckHandler;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.SurfaceType;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.phys.Vec3;

public class PathingStuckHandler
implements IStuckHandler {
    protected static final double MIN_TARGET_DIST = 3.0;
    protected final List<Direction> directions = Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
    protected static final int MIN_TP_DELAY = 2400;
    protected static final int MIN_DIST_FOR_TP = 10;
    protected int teleportRange = 0;
    protected int timePerBlockDistance = 100;
    protected int stuckLevel = 0;
    protected int globalTimeout = 0;
    protected BlockPos prevDestination = BlockPos.f_121853_;
    protected boolean canBreakBlocks = false;
    protected boolean canPlaceLadders = false;
    protected boolean canBuildLeafBridges = false;
    protected boolean canTeleportGoal = false;
    protected boolean takeDamageOnCompleteStuck = false;
    protected float damagePct = 0.2f;
    protected int completeStuckBlockBreakRange = 0;
    protected boolean hadPath = false;
    protected int lastPathIndex = -1;
    protected int progressedNodes = 0;
    protected int delayBeforeActions;
    protected int delayToNextUnstuckAction = this.delayBeforeActions = 1200;
    protected BlockPos moveAwayStartPos = BlockPos.f_121853_;
    protected final Random rand = new Random();

    protected PathingStuckHandler() {
    }

    public static PathingStuckHandler createStuckHandler() {
        return new PathingStuckHandler();
    }

    @Override
    public void checkStuck(AbstractAdvancedPathNavigate navigator) {
        if (navigator.getDesiredPos() == null || navigator.getDesiredPos().equals((Object)BlockPos.f_121853_)) {
            return;
        }
        double distanceToGoal = navigator.getOurEntity().m_20182_().m_82554_(new Vec3((double)navigator.getDesiredPos().m_123341_(), (double)navigator.getDesiredPos().m_123342_(), (double)navigator.getDesiredPos().m_123343_()));
        if (distanceToGoal < 3.0) {
            this.resetGlobalStuckTimers();
            return;
        }
        if (this.prevDestination.equals((Object)navigator.getDesiredPos())) {
            ++this.globalTimeout;
            if ((double)this.globalTimeout > Math.max(2400.0, (double)this.timePerBlockDistance * Math.max(10.0, distanceToGoal))) {
                this.completeStuckAction(navigator);
            }
        } else {
            this.resetGlobalStuckTimers();
        }
        this.prevDestination = navigator.getDesiredPos();
        if (navigator.m_26570_() == null || navigator.m_26570_().m_77392_()) {
            this.lastPathIndex = -1;
            this.progressedNodes = 0;
            if (!this.hadPath) {
                this.tryUnstuck(navigator);
            }
        } else if (navigator.m_26570_().m_77399_() == this.lastPathIndex) {
            this.tryUnstuck(navigator);
        } else if (this.lastPathIndex != -1 && navigator.m_26570_().m_77406_().m_123331_((Vec3i)this.prevDestination) < 25.0) {
            int n = this.progressedNodes = navigator.m_26570_().m_77399_() > this.lastPathIndex ? this.progressedNodes + 1 : this.progressedNodes - 1;
            if (!(this.progressedNodes <= 5 || navigator.m_26570_().m_77395_() != null && this.moveAwayStartPos.equals((Object)navigator.m_26570_().m_77395_().m_77288_()))) {
                this.resetStuckTimers();
            }
        }
        this.lastPathIndex = navigator.m_26570_() != null ? navigator.m_26570_().m_77399_() : -1;
        this.hadPath = navigator.m_26570_() != null && !navigator.m_26570_().m_77392_();
    }

    protected void resetGlobalStuckTimers() {
        this.globalTimeout = 0;
        this.prevDestination = BlockPos.f_121853_;
        this.resetStuckTimers();
    }

    public void completeStuckAction(AbstractAdvancedPathNavigate navigator) {
        BlockPos tpPos;
        BlockPos desired = navigator.getDesiredPos();
        Level world = navigator.getOurEntity().m_9236_();
        Mob entity = navigator.getOurEntity();
        if (this.canTeleportGoal && (tpPos = PathingStuckHandler.findAround(world, desired, 10, 10, (posworld, pos) -> SurfaceType.getSurfaceType(posworld, posworld.m_8055_(pos.m_7495_()), pos.m_7495_()) == SurfaceType.WALKABLE && SurfaceType.getSurfaceType(posworld, posworld.m_8055_(pos), pos) == SurfaceType.DROPABLE && SurfaceType.getSurfaceType(posworld, posworld.m_8055_(pos.m_7494_()), pos.m_7494_()) == SurfaceType.DROPABLE)) != null) {
            entity.m_6021_((double)tpPos.m_123341_() + 0.5, (double)tpPos.m_123342_(), (double)tpPos.m_123343_() + 0.5);
        }
        if (this.takeDamageOnCompleteStuck) {
            entity.m_6469_(new DamageSource(entity.m_9236_().m_269111_().m_269318_().m_269150_(), (Entity)entity), entity.m_21233_() * this.damagePct);
        }
        if (this.completeStuckBlockBreakRange > 0) {
            Direction facing = PathingStuckHandler.getFacing(entity.m_20183_(), navigator.getDesiredPos());
            for (int i = 1; i <= this.completeStuckBlockBreakRange; ++i) {
                if (world.m_46859_(new BlockPos((Vec3i)entity.m_20183_()).m_5484_(facing, i)) && world.m_46859_(new BlockPos((Vec3i)entity.m_20183_()).m_5484_(facing, i).m_7494_())) continue;
                this.breakBlocksAhead(world, new BlockPos((Vec3i)entity.m_20183_()).m_5484_(facing, i - 1), facing);
                break;
            }
        }
        navigator.m_26573_();
        this.resetGlobalStuckTimers();
    }

    public void tryUnstuck(AbstractAdvancedPathNavigate navigator) {
        if (this.delayToNextUnstuckAction-- > 0) {
            return;
        }
        this.delayToNextUnstuckAction = 50;
        if (this.stuckLevel == 0) {
            ++this.stuckLevel;
            this.delayToNextUnstuckAction = 100;
            navigator.m_26573_();
            return;
        }
        if (this.stuckLevel == 1) {
            ++this.stuckLevel;
            this.delayToNextUnstuckAction = 200;
            navigator.m_26573_();
            navigator.moveAwayFromXYZ(new BlockPos((Vec3i)navigator.getOurEntity().m_20183_()), 10.0, 1.0, false);
            navigator.getPathingOptions().setCanClimb(false);
            this.moveAwayStartPos = navigator.getOurEntity().m_20183_();
            return;
        }
        if (this.stuckLevel == 2 && this.teleportRange > 0 && this.hadPath) {
            int index = Math.min(navigator.m_26570_().m_77399_() + this.teleportRange, navigator.m_26570_().m_77398_() - 1);
            Node togo = navigator.m_26570_().m_77375_(index);
            navigator.getOurEntity().m_6021_((double)togo.f_77271_ + 0.5, (double)togo.f_77272_, (double)togo.f_77273_ + 0.5);
            this.delayToNextUnstuckAction = 300;
        }
        if (this.stuckLevel >= 3 && this.stuckLevel <= 5) {
            if (this.canPlaceLadders && this.rand.nextBoolean()) {
                this.delayToNextUnstuckAction = 200;
                this.placeLadders(navigator);
            } else if (this.canBuildLeafBridges && this.rand.nextBoolean()) {
                this.delayToNextUnstuckAction = 100;
                this.placeLeaves(navigator);
            }
        }
        if (this.stuckLevel >= 6 && this.stuckLevel <= 8 && this.canBreakBlocks) {
            this.delayToNextUnstuckAction = 200;
            this.breakBlocks(navigator);
        }
        this.chanceStuckLevel();
        if (this.stuckLevel == 9) {
            this.completeStuckAction(navigator);
            this.resetStuckTimers();
        }
    }

    protected void chanceStuckLevel() {
        ++this.stuckLevel;
        if (this.stuckLevel > 1 && this.rand.nextInt(6) == 0) {
            this.stuckLevel -= 2;
        }
    }

    protected void resetStuckTimers() {
        this.delayToNextUnstuckAction = this.delayBeforeActions;
        this.lastPathIndex = -1;
        this.progressedNodes = 0;
        this.stuckLevel = 0;
        this.moveAwayStartPos = BlockPos.f_121853_;
    }

    public void breakBlocksAhead(Level world, BlockPos start, Direction facing) {
        if (!world.m_46859_(start.m_6630_(3))) {
            this.setAirIfPossible(world, start.m_6630_(3));
            return;
        }
        if (!world.m_46859_(start.m_7494_().m_121945_(facing))) {
            this.setAirIfPossible(world, start.m_7494_().m_121945_(facing));
            return;
        }
        if (!world.m_46859_(start.m_121945_(facing))) {
            this.setAirIfPossible(world, start.m_121945_(facing));
        }
    }

    protected void setAirIfPossible(Level world, BlockPos pos) {
        Block blockAtPos = world.m_8055_(pos).m_60734_();
        world.m_46597_(pos, Blocks.f_50016_.m_49966_());
    }

    protected void placeLadders(AbstractAdvancedPathNavigate navigator) {
        Level world = navigator.getOurEntity().m_9236_();
        Mob entity = navigator.getOurEntity();
        BlockPos entityPos = entity.m_20183_();
        while (world.m_8055_(entityPos).m_60734_() == Blocks.f_50155_) {
            entityPos = entityPos.m_7494_();
        }
        this.tryPlaceLadderAt(world, entityPos);
        this.tryPlaceLadderAt(world, entityPos.m_7494_());
        this.tryPlaceLadderAt(world, entityPos.m_6630_(2));
    }

    protected void placeLeaves(AbstractAdvancedPathNavigate navigator) {
        Level world = navigator.getOurEntity().m_9236_();
        Mob entity = navigator.getOurEntity();
        Direction badFacing = PathingStuckHandler.getFacing(entity.m_20183_(), navigator.getDesiredPos()).m_122424_();
        for (Direction dir : this.directions) {
            if (dir == badFacing || !world.m_46859_(entity.m_20183_().m_7495_().m_121945_(dir))) continue;
            world.m_46597_(entity.m_20183_().m_7495_().m_121945_(dir), Blocks.f_50054_.m_49966_());
        }
    }

    public static Direction getFacing(BlockPos pos, BlockPos neighbor) {
        BlockPos vector = neighbor.m_121996_((Vec3i)pos);
        return Direction.m_122372_((float)vector.m_123341_(), (float)vector.m_123342_(), (float)(-vector.m_123343_()));
    }

    public void breakBlocks(AbstractAdvancedPathNavigate navigator) {
        Level world = navigator.getOurEntity().m_9236_();
        Mob entity = navigator.getOurEntity();
        Direction facing = PathingStuckHandler.getFacing(entity.m_20183_(), navigator.getDesiredPos());
        this.breakBlocksAhead(world, entity.m_20183_(), facing);
    }

    protected void tryPlaceLadderAt(Level world, BlockPos pos) {
        BlockState state = world.m_8055_(pos);
        if (state.m_60734_() != Blocks.f_50155_ && !state.m_60815_() && world.m_6425_(pos).m_76178_()) {
            for (Direction dir : this.directions) {
                BlockState toPlace = (BlockState)Blocks.f_50155_.m_49966_().m_61124_((Property)LadderBlock.f_54337_, (Comparable)dir.m_122424_());
                if (!world.m_8055_(pos.m_121945_(dir)).m_280296_() || !Blocks.f_50155_.m_7898_(toPlace, (LevelReader)world, pos)) continue;
                world.m_46597_(pos, toPlace);
                break;
            }
        }
    }

    public PathingStuckHandler withBlockBreaks() {
        this.canBreakBlocks = true;
        return this;
    }

    public PathingStuckHandler withPlaceLadders() {
        this.canPlaceLadders = true;
        return this;
    }

    public PathingStuckHandler withBuildLeafBridges() {
        this.canBuildLeafBridges = true;
        return this;
    }

    public PathingStuckHandler withTeleportSteps(int steps) {
        this.teleportRange = steps;
        return this;
    }

    public PathingStuckHandler withTeleportOnFullStuck() {
        this.canTeleportGoal = true;
        return this;
    }

    public PathingStuckHandler withTakeDamageOnStuck(float damagePct) {
        this.damagePct = damagePct;
        this.takeDamageOnCompleteStuck = true;
        return this;
    }

    public PathingStuckHandler withTimePerBlockDistance(int time) {
        this.timePerBlockDistance = time;
        return this;
    }

    public PathingStuckHandler withDelayBeforeStuckActions(int delay) {
        this.delayBeforeActions = delay;
        return this;
    }

    public PathingStuckHandler withCompleteStuckBlockBreak(int range) {
        this.completeStuckBlockBreakRange = range;
        return this;
    }

    public static BlockPos findAround(Level world, BlockPos start, int vRange, int hRange, BiPredicate<BlockGetter, BlockPos> predicate) {
        if (vRange < 1 && hRange < 1) {
            return null;
        }
        if (predicate.test((BlockGetter)world, start)) {
            return start;
        }
        int y = 0;
        int y_offset = 1;
        for (int i = 0; i < hRange + 2; ++i) {
            for (int steps = 1; steps <= vRange; ++steps) {
                int z;
                int x;
                BlockPos temp = start.m_7918_(-steps, y, -steps);
                for (x = 0; x <= steps; ++x) {
                    if (!predicate.test((BlockGetter)world, temp = temp.m_7918_(1, 0, 0))) continue;
                    return temp;
                }
                for (z = 0; z <= steps; ++z) {
                    if (!predicate.test((BlockGetter)world, temp = temp.m_7918_(0, 0, 1))) continue;
                    return temp;
                }
                for (x = 0; x <= steps; ++x) {
                    if (!predicate.test((BlockGetter)world, temp = temp.m_7918_(-1, 0, 0))) continue;
                    return temp;
                }
                for (z = 0; z <= steps; ++z) {
                    if (!predicate.test((BlockGetter)world, temp = temp.m_7918_(0, 0, -1))) continue;
                    return temp;
                }
            }
            y_offset = y_offset > 0 ? y_offset + 1 : y_offset - 1;
            if (world.m_151558_() > start.m_123342_() + (y += (y_offset *= -1))) continue;
            return null;
        }
        return null;
    }
}
