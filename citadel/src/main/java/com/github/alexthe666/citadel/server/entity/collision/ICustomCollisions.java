/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.CollisionGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.citadel.server.entity.collision;

import com.github.alexthe666.citadel.server.entity.collision.CustomCollisionsBlockCollisions;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public interface ICustomCollisions {
    public static Vec3 getAllowedMovementForEntity(Entity entity, Vec3 vecIN) {
        boolean flag3;
        AABB aabb = entity.m_20191_();
        List list = entity.m_9236_().m_183134_(entity, aabb.m_82369_(vecIN));
        Vec3 vec3 = vecIN.m_82556_() == 0.0 ? vecIN : ICustomCollisions.collideBoundingBox2(entity, vecIN, aabb, entity.m_9236_(), list);
        boolean flag = vecIN.f_82479_ != vec3.f_82479_;
        boolean flag1 = vecIN.f_82480_ != vec3.f_82480_;
        boolean flag2 = vecIN.f_82481_ != vec3.f_82481_;
        boolean bl = flag3 = entity.m_20096_() || flag1 && vecIN.f_82480_ < 0.0;
        if (entity.getStepHeight() > 0.0f && flag3 && (flag || flag2)) {
            Vec3 vec33;
            Vec3 vec31 = ICustomCollisions.collideBoundingBox2(entity, new Vec3(vecIN.f_82479_, (double)entity.getStepHeight(), vecIN.f_82481_), aabb, entity.m_9236_(), list);
            Vec3 vec32 = ICustomCollisions.collideBoundingBox2(entity, new Vec3(0.0, (double)entity.getStepHeight(), 0.0), aabb.m_82363_(vecIN.f_82479_, 0.0, vecIN.f_82481_), entity.m_9236_(), list);
            if (vec32.f_82480_ < (double)entity.getStepHeight() && (vec33 = ICustomCollisions.collideBoundingBox2(entity, new Vec3(vecIN.f_82479_, 0.0, vecIN.f_82481_), aabb.m_82383_(vec32), entity.m_9236_(), list).m_82549_(vec32)).m_165925_() > vec31.m_165925_()) {
                vec31 = vec33;
            }
            if (vec31.m_165925_() > vec3.m_165925_()) {
                return vec31.m_82549_(ICustomCollisions.collideBoundingBox2(entity, new Vec3(0.0, -vec31.f_82480_ + vecIN.f_82480_, 0.0), aabb.m_82383_(vec31), entity.m_9236_(), list));
            }
        }
        return vec3;
    }

    public boolean canPassThrough(BlockPos var1, BlockState var2, VoxelShape var3);

    private static Vec3 collideBoundingBox2(@Nullable Entity p_198895_, Vec3 p_198896_, AABB p_198897_, Level p_198898_, List<VoxelShape> p_198899_) {
        boolean flag;
        ImmutableList.Builder builder = ImmutableList.builderWithExpectedSize((int)(p_198899_.size() + 1));
        if (!p_198899_.isEmpty()) {
            builder.addAll(p_198899_);
        }
        WorldBorder worldborder = p_198898_.m_6857_();
        boolean bl = flag = p_198895_ != null && worldborder.m_187566_(p_198895_, p_198897_.m_82369_(p_198896_));
        if (flag) {
            builder.add((Object)worldborder.m_61946_());
        }
        builder.addAll((Iterator)((Object)new CustomCollisionsBlockCollisions((CollisionGetter)p_198898_, p_198895_, p_198897_.m_82369_(p_198896_))));
        return ICustomCollisions.collideWithShapes2(p_198896_, p_198897_, (List<VoxelShape>)builder.build());
    }

    private static Vec3 collideWithShapes2(Vec3 p_198901_, AABB p_198902_, List<VoxelShape> p_198903_) {
        boolean flag;
        if (p_198903_.isEmpty()) {
            return p_198901_;
        }
        double d0 = p_198901_.f_82479_;
        double d1 = p_198901_.f_82480_;
        double d2 = p_198901_.f_82481_;
        if (d1 != 0.0 && (d1 = Shapes.m_193135_((Direction.Axis)Direction.Axis.Y, (AABB)p_198902_, p_198903_, (double)d1)) != 0.0) {
            p_198902_ = p_198902_.m_82386_(0.0, d1, 0.0);
        }
        boolean bl = flag = Math.abs(d0) < Math.abs(d2);
        if (flag && d2 != 0.0 && (d2 = Shapes.m_193135_((Direction.Axis)Direction.Axis.Z, (AABB)p_198902_, p_198903_, (double)d2)) != 0.0) {
            p_198902_ = p_198902_.m_82386_(0.0, 0.0, d2);
        }
        if (d0 != 0.0) {
            d0 = Shapes.m_193135_((Direction.Axis)Direction.Axis.X, (AABB)p_198902_, p_198903_, (double)d0);
            if (!flag && d0 != 0.0) {
                p_198902_ = p_198902_.m_82386_(d0, 0.0, 0.0);
            }
        }
        if (!flag && d2 != 0.0) {
            d2 = Shapes.m_193135_((Direction.Axis)Direction.Axis.Z, (AABB)p_198902_, p_198903_, (double)d2);
        }
        return new Vec3(d0, d1, d2);
    }
}
