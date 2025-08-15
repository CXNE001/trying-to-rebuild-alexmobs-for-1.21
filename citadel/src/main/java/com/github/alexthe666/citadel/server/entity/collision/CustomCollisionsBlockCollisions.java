/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.AbstractIterator
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Cursor3D
 *  net.minecraft.core.SectionPos
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.CollisionGetter
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.EntityCollisionContext
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.citadel.server.entity.collision;

import com.github.alexthe666.citadel.server.entity.collision.ICustomCollisions;
import com.google.common.collect.AbstractIterator;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Cursor3D;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CustomCollisionsBlockCollisions
extends AbstractIterator<VoxelShape> {
    private final AABB box;
    private final CollisionContext context;
    private final Cursor3D cursor;
    private final BlockPos.MutableBlockPos pos;
    private final VoxelShape entityShape;
    private final CollisionGetter collisionGetter;
    private final boolean onlySuffocatingBlocks;
    @Nullable
    private BlockGetter cachedBlockGetter;
    private long cachedBlockGetterPos;

    public CustomCollisionsBlockCollisions(CollisionGetter p_186402_, @Nullable Entity p_186403_, AABB p_186404_) {
        this(p_186402_, p_186403_, p_186404_, false);
    }

    public CustomCollisionsBlockCollisions(CollisionGetter p_186406_, @Nullable Entity p_186407_, AABB p_186408_, boolean p_186409_) {
        this.context = p_186407_ == null ? CollisionContext.m_82749_() : CollisionContext.m_82750_((Entity)p_186407_);
        this.pos = new BlockPos.MutableBlockPos();
        this.entityShape = Shapes.m_83064_((AABB)p_186408_);
        this.collisionGetter = p_186406_;
        this.box = p_186408_;
        this.onlySuffocatingBlocks = p_186409_;
        int i = Mth.m_14107_((double)(p_186408_.f_82288_ - 1.0E-7)) - 1;
        int j = Mth.m_14107_((double)(p_186408_.f_82291_ + 1.0E-7)) + 1;
        int k = Mth.m_14107_((double)(p_186408_.f_82289_ - 1.0E-7)) - 1;
        int l = Mth.m_14107_((double)(p_186408_.f_82292_ + 1.0E-7)) + 1;
        int i1 = Mth.m_14107_((double)(p_186408_.f_82290_ - 1.0E-7)) - 1;
        int j1 = Mth.m_14107_((double)(p_186408_.f_82293_ + 1.0E-7)) + 1;
        this.cursor = new Cursor3D(i, k, i1, j, l, j1);
    }

    @Nullable
    private BlockGetter getChunk(int p_186412_, int p_186413_) {
        BlockGetter blockgetter;
        int i = SectionPos.m_123171_((int)p_186412_);
        int j = SectionPos.m_123171_((int)p_186413_);
        long k = ChunkPos.m_45589_((int)i, (int)j);
        if (this.cachedBlockGetter != null && this.cachedBlockGetterPos == k) {
            return this.cachedBlockGetter;
        }
        this.cachedBlockGetter = blockgetter = this.collisionGetter.m_7925_(i, j);
        this.cachedBlockGetterPos = k;
        return blockgetter;
    }

    protected VoxelShape computeNext() {
        while (this.cursor.m_122304_()) {
            Entity entity;
            BlockGetter blockgetter;
            int i = this.cursor.m_122305_();
            int j = this.cursor.m_122306_();
            int k = this.cursor.m_122307_();
            int l = this.cursor.m_122308_();
            if (l == 3 || (blockgetter = this.getChunk(i, k)) == null) continue;
            this.pos.m_122178_(i, j, k);
            BlockState blockstate = blockgetter.m_8055_((BlockPos)this.pos);
            if (this.onlySuffocatingBlocks && !blockstate.m_60828_(blockgetter, (BlockPos)this.pos) || l == 1 && !blockstate.m_60779_() || l == 2 && !blockstate.m_60713_(Blocks.f_50110_)) continue;
            VoxelShape voxelshape = blockstate.m_60742_((BlockGetter)this.collisionGetter, (BlockPos)this.pos, this.context);
            if (this.context instanceof EntityCollisionContext && (entity = ((EntityCollisionContext)this.context).m_193113_()) instanceof ICustomCollisions && ((ICustomCollisions)entity).canPassThrough((BlockPos)this.pos, blockstate, voxelshape)) continue;
            if (voxelshape == Shapes.m_83144_()) {
                if (!this.box.m_82314_((double)i, (double)j, (double)k, (double)i + 1.0, (double)j + 1.0, (double)k + 1.0)) continue;
                return voxelshape.m_83216_((double)i, (double)j, (double)k);
            }
            VoxelShape voxelshape1 = voxelshape.m_83216_((double)i, (double)j, (double)k);
            if (!Shapes.m_83157_((VoxelShape)voxelshape1, (VoxelShape)this.entityShape, (BooleanOp)BooleanOp.f_82689_)) continue;
            return voxelshape1;
        }
        return (VoxelShape)this.endOfData();
    }
}
