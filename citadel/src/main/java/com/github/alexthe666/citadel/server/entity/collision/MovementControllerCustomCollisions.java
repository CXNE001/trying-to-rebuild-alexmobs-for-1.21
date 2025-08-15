/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.NodeEvaluator
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.citadel.server.entity.collision;

import com.github.alexthe666.citadel.server.entity.collision.ICustomCollisions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MovementControllerCustomCollisions
extends MoveControl {
    public MovementControllerCustomCollisions(Mob mob) {
        super(mob);
    }

    public void m_8126_() {
        if (this.f_24981_ == MoveControl.Operation.STRAFE) {
            float f8;
            float f = (float)this.f_24974_.m_21133_(Attributes.f_22279_);
            float f1 = (float)this.f_24978_ * f;
            float f2 = this.f_24979_;
            float f3 = this.f_24980_;
            float f4 = Mth.m_14116_((float)(f2 * f2 + f3 * f3));
            if (f4 < 1.0f) {
                f4 = 1.0f;
            }
            f4 = f1 / f4;
            float f5 = Mth.m_14031_((float)(this.f_24974_.m_146908_() * ((float)Math.PI / 180)));
            float f6 = Mth.m_14089_((float)(this.f_24974_.m_146908_() * ((float)Math.PI / 180)));
            float f7 = (f2 *= f4) * f6 - (f3 *= f4) * f5;
            if (!this.isWalkable(f7, f8 = f3 * f6 + f2 * f5)) {
                this.f_24979_ = 1.0f;
                this.f_24980_ = 0.0f;
            }
            this.f_24974_.m_7910_(f1);
            this.f_24974_.m_21564_(this.f_24979_);
            this.f_24974_.m_21570_(this.f_24980_);
            this.f_24981_ = MoveControl.Operation.WAIT;
        } else if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
            this.f_24981_ = MoveControl.Operation.WAIT;
            double d0 = this.f_24975_ - this.f_24974_.m_20185_();
            double d1 = this.f_24977_ - this.f_24974_.m_20189_();
            double d2 = this.f_24976_ - this.f_24974_.m_20186_();
            double d3 = d0 * d0 + d2 * d2 + d1 * d1;
            if (d3 < 2.500000277905201E-7) {
                this.f_24974_.m_21564_(0.0f);
                return;
            }
            float f9 = (float)(Mth.m_14136_((double)d1, (double)d0) * 57.2957763671875) - 90.0f;
            this.f_24974_.m_146922_(this.m_24991_(this.f_24974_.m_146908_(), f9, 90.0f));
            this.f_24974_.m_7910_((float)(this.f_24978_ * this.f_24974_.m_21133_(Attributes.f_22279_)));
            BlockPos blockpos = this.f_24974_.m_20183_();
            BlockState blockstate = this.f_24974_.m_9236_().m_8055_(blockpos);
            VoxelShape voxelshape = blockstate.m_60816_((BlockGetter)this.f_24974_.m_9236_(), blockpos);
            if (!(this.f_24974_ instanceof ICustomCollisions && ((ICustomCollisions)this.f_24974_).canPassThrough(blockpos, blockstate, voxelshape) || !(d2 > (double)this.f_24974_.getStepHeight() && d0 * d0 + d1 * d1 < (double)Math.max(1.0f, this.f_24974_.m_20205_())) && (voxelshape.m_83281_() || !(this.f_24974_.m_20186_() < voxelshape.m_83297_(Direction.Axis.Y) + (double)blockpos.m_123342_()) || blockstate.m_204336_(BlockTags.f_13103_) || blockstate.m_204336_(BlockTags.f_13039_)))) {
                this.f_24974_.m_21569_().m_24901_();
                this.f_24981_ = MoveControl.Operation.JUMPING;
            }
        } else if (this.f_24981_ == MoveControl.Operation.JUMPING) {
            this.f_24974_.m_7910_((float)(this.f_24978_ * this.f_24974_.m_21133_(Attributes.f_22279_)));
            if (this.f_24974_.m_20096_()) {
                this.f_24981_ = MoveControl.Operation.WAIT;
            }
        } else {
            this.f_24974_.m_21564_(0.0f);
        }
    }

    private boolean isWalkable(float p_234024_1_, float p_234024_2_) {
        PathNavigation pathnavigator = this.f_24974_.m_21573_();
        if (pathnavigator != null) {
            NodeEvaluator nodeprocessor = pathnavigator.m_26575_();
            return nodeprocessor == null || nodeprocessor.m_8086_((BlockGetter)this.f_24974_.m_9236_(), Mth.m_14107_((double)(this.f_24974_.m_20185_() + (double)p_234024_1_)), Mth.m_14107_((double)this.f_24974_.m_20186_()), Mth.m_14107_((double)(this.f_24974_.m_20189_() + (double)p_234024_2_))) == BlockPathTypes.WALKABLE;
        }
        return true;
    }
}
