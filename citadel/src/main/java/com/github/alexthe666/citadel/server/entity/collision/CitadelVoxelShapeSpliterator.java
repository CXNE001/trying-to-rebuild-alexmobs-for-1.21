/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Cursor3D
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.CollisionGetter
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.citadel.server.entity.collision;

import com.github.alexthe666.citadel.server.entity.collision.ICustomCollisions;
import java.util.Objects;
import java.util.Spliterators;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Cursor3D;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CitadelVoxelShapeSpliterator
extends Spliterators.AbstractSpliterator<VoxelShape> {
    @Nullable
    private final Entity entity;
    private final AABB aabb;
    private final CollisionContext context;
    private final Cursor3D cubeCoordinateIterator;
    private final BlockPos.MutableBlockPos mutablePos;
    private final VoxelShape shape;
    private final CollisionGetter reader;
    private final BiPredicate<BlockState, BlockPos> statePositionPredicate;
    private boolean needsBorderCheck;

    public CitadelVoxelShapeSpliterator(CollisionGetter reader, @Nullable Entity entity, AABB aabb) {
        this(reader, entity, aabb, (p_241459_0_, p_241459_1_) -> true);
    }

    public CitadelVoxelShapeSpliterator(CollisionGetter reader, @Nullable Entity entity, AABB aabb, BiPredicate<BlockState, BlockPos> statePositionPredicate) {
        super(Long.MAX_VALUE, 1280);
        this.context = entity == null ? CollisionContext.m_82749_() : CollisionContext.m_82750_((Entity)entity);
        this.mutablePos = new BlockPos.MutableBlockPos();
        this.shape = Shapes.m_83064_((AABB)aabb);
        this.reader = reader;
        this.needsBorderCheck = entity != null;
        this.entity = entity;
        this.aabb = aabb;
        this.statePositionPredicate = statePositionPredicate;
        int i = Mth.m_14107_((double)(aabb.f_82288_ - 1.0E-7)) - 1;
        int j = Mth.m_14107_((double)(aabb.f_82291_ + 1.0E-7)) + 1;
        int k = Mth.m_14107_((double)(aabb.f_82289_ - 1.0E-7)) - 1;
        int l = Mth.m_14107_((double)(aabb.f_82292_ + 1.0E-7)) + 1;
        int i1 = Mth.m_14107_((double)(aabb.f_82290_ - 1.0E-7)) - 1;
        int j1 = Mth.m_14107_((double)(aabb.f_82293_ + 1.0E-7)) + 1;
        this.cubeCoordinateIterator = new Cursor3D(i, k, i1, j, l, j1);
    }

    private static boolean isCloseToBorder(VoxelShape p_241460_0_, AABB p_241460_1_) {
        return Shapes.m_83157_((VoxelShape)p_241460_0_, (VoxelShape)Shapes.m_83064_((AABB)p_241460_1_.m_82400_(1.0E-7)), (BooleanOp)BooleanOp.f_82689_);
    }

    private static boolean isOutsideBorder(VoxelShape p_241461_0_, AABB p_241461_1_) {
        return Shapes.m_83157_((VoxelShape)p_241461_0_, (VoxelShape)Shapes.m_83064_((AABB)p_241461_1_.m_82406_(1.0E-7)), (BooleanOp)BooleanOp.f_82689_);
    }

    public static boolean isBoxFullyWithinWorldBorder(WorldBorder p_234877_0_, AABB p_234877_1_) {
        double d0 = Mth.m_14107_((double)p_234877_0_.m_61955_());
        double d1 = Mth.m_14107_((double)p_234877_0_.m_61956_());
        double d2 = Mth.m_14165_((double)p_234877_0_.m_61957_());
        double d3 = Mth.m_14165_((double)p_234877_0_.m_61958_());
        return p_234877_1_.f_82288_ > d0 && p_234877_1_.f_82288_ < d2 && p_234877_1_.f_82290_ > d1 && p_234877_1_.f_82290_ < d3 && p_234877_1_.f_82291_ > d0 && p_234877_1_.f_82291_ < d2 && p_234877_1_.f_82293_ > d1 && p_234877_1_.f_82293_ < d3;
    }

    @Override
    public boolean tryAdvance(Consumer<? super VoxelShape> p_tryAdvance_1_) {
        return this.needsBorderCheck && this.worldBorderCheck(p_tryAdvance_1_) || this.collisionCheck(p_tryAdvance_1_);
    }

    boolean collisionCheck(Consumer<? super VoxelShape> p_234878_1_) {
        while (this.cubeCoordinateIterator.m_122304_()) {
            BlockGetter iblockreader;
            int i = this.cubeCoordinateIterator.m_122305_();
            int j = this.cubeCoordinateIterator.m_122306_();
            int k = this.cubeCoordinateIterator.m_122307_();
            int l = this.cubeCoordinateIterator.m_122308_();
            if (l == 3 || (iblockreader = this.getChunk(i, k)) == null) continue;
            this.mutablePos.m_122178_(i, j, k);
            BlockState blockstate = iblockreader.m_8055_((BlockPos)this.mutablePos);
            if (!this.statePositionPredicate.test(blockstate, (BlockPos)this.mutablePos) || l == 1 && !blockstate.m_60779_() || l == 2 && blockstate.m_60734_() != Blocks.f_50110_) continue;
            VoxelShape voxelshape = blockstate.m_60742_((BlockGetter)this.reader, (BlockPos)this.mutablePos, this.context);
            if (this.entity instanceof ICustomCollisions && ((ICustomCollisions)this.entity).canPassThrough((BlockPos)this.mutablePos, blockstate, voxelshape)) continue;
            if (voxelshape == Shapes.m_83144_()) {
                if (!this.aabb.m_82314_((double)i, (double)j, (double)k, (double)i + 1.0, (double)j + 1.0, (double)k + 1.0)) continue;
                p_234878_1_.accept((VoxelShape)voxelshape.m_83216_((double)i, (double)j, (double)k));
                return true;
            }
            VoxelShape voxelshape1 = voxelshape.m_83216_((double)i, (double)j, (double)k);
            if (!Shapes.m_83157_((VoxelShape)voxelshape1, (VoxelShape)this.shape, (BooleanOp)BooleanOp.f_82689_)) continue;
            p_234878_1_.accept((VoxelShape)voxelshape1);
            return true;
        }
        return false;
    }

    @Nullable
    private BlockGetter getChunk(int p_234876_1_, int p_234876_2_) {
        int i = p_234876_1_ >> 4;
        int j = p_234876_2_ >> 4;
        return this.reader.m_7925_(i, j);
    }

    boolean worldBorderCheck(Consumer<? super VoxelShape> p_234879_1_) {
        VoxelShape voxelshape;
        Objects.requireNonNull(this.entity);
        this.needsBorderCheck = false;
        WorldBorder worldborder = this.reader.m_6857_();
        AABB axisalignedbb = this.entity.m_20191_();
        if (!CitadelVoxelShapeSpliterator.isBoxFullyWithinWorldBorder(worldborder, axisalignedbb) && !CitadelVoxelShapeSpliterator.isOutsideBorder(voxelshape = worldborder.m_61946_(), axisalignedbb) && CitadelVoxelShapeSpliterator.isCloseToBorder(voxelshape, axisalignedbb)) {
            p_234879_1_.accept((VoxelShape)voxelshape);
            return true;
        }
        return false;
    }
}
