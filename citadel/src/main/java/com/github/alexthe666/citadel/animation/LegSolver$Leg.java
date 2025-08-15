/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.citadel.animation;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public static final class LegSolver.Leg {
    public final float forward;
    public final float side;
    private final float range;
    private float height;
    private float prevHeight;
    private final boolean isWing;

    public LegSolver.Leg(float forward, float side, float range, boolean isWing) {
        this.forward = forward;
        this.side = side;
        this.range = range;
        this.isWing = isWing;
    }

    public float getHeight(float delta) {
        return this.prevHeight + (this.height - this.prevHeight) * delta;
    }

    public void update(LivingEntity entity, double sideX, double sideZ, double forwardX, double forwardZ, float scale) {
        this.prevHeight = this.height;
        double posY = entity.m_20186_();
        float settledHeight = this.settle(entity, entity.m_20185_() + sideX * (double)this.side + forwardX * (double)this.forward, posY, entity.m_20189_() + sideZ * (double)this.side + forwardZ * (double)this.forward, this.height);
        this.height = Mth.m_14036_((float)settledHeight, (float)(-this.range * scale), (float)(this.range * scale));
    }

    private float settle(LivingEntity entity, double x, double y, double z, float height) {
        BlockPos pos = new BlockPos((int)Math.floor(x), (int)Math.floor(y + 0.001), (int)Math.floor(z));
        Vec3 vec3 = new Vec3(x, y, z);
        float dist = this.getDistance(entity.m_9236_(), pos, vec3);
        dist = (double)(1.0f - dist) < 0.001 ? this.getDistance(entity.m_9236_(), pos.m_7495_(), vec3) + (float)y % 1.0f : (float)((double)dist - (1.0 - y % 1.0));
        if (entity.m_20096_() && height <= dist) {
            return height == dist ? height : Math.min(height + this.getFallSpeed(), dist);
        }
        if (height > 0.0f) {
            return height == dist ? height : Math.max(height - this.getRiseSpeed(), dist);
        }
        return height;
    }

    private float getDistance(Level world, BlockPos pos, Vec3 position) {
        BlockState state = world.m_8055_(pos);
        VoxelShape shape = state.m_60812_((BlockGetter)world, pos);
        if (shape.m_83281_()) {
            return 1.0f;
        }
        return 1.0f - (float)shape.m_83297_(Direction.Axis.Y);
    }

    private float getFallSpeed() {
        return 0.25f;
    }

    private float getRiseSpeed() {
        return 0.25f;
    }
}
