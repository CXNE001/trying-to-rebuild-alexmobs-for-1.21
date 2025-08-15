/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.DirectionalBlock
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.DirectionProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.entity.EntityGust;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

public class BlockGustmaker
extends Block {
    public static final DirectionProperty FACING = DirectionalBlock.f_52588_;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.f_61360_;

    public BlockGustmaker() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283761_).m_60999_().m_60978_(1.5f));
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)TRIGGERED, (Comparable)Boolean.valueOf(false)));
    }

    public static Vec3 getDispensePosition(BlockPos coords, Direction dir) {
        double d0 = (double)coords.m_123341_() + 0.5 + 0.7 * (double)dir.m_122429_();
        double d1 = (double)coords.m_123342_() + 0.15 + 0.7 * (double)dir.m_122430_();
        double d2 = (double)coords.m_123343_() + 0.5 + 0.7 * (double)dir.m_122431_();
        return new Vec3(d0, d1, d2);
    }

    public void m_6861_(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        this.tickGustmaker(state, worldIn, pos, false);
    }

    public void m_213897_(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        this.tickGustmaker(state, (Level)worldIn, pos, true);
    }

    public void tickGustmaker(BlockState state, Level worldIn, BlockPos pos, boolean tickOff) {
        boolean flag = worldIn.m_276867_(pos) || worldIn.m_276867_(pos.m_7495_()) || worldIn.m_276867_(pos.m_7494_());
        boolean flag1 = (Boolean)state.m_61143_((Property)TRIGGERED);
        if (flag && !flag1) {
            if (worldIn.m_46749_(pos)) {
                Vec3 dispensePosition = BlockGustmaker.getDispensePosition(pos, (Direction)state.m_61143_((Property)FACING));
                Vec3 gustDir = Vec3.m_82528_((Vec3i)((Direction)state.m_61143_((Property)FACING)).m_122436_()).m_82542_(0.1, 0.1, 0.1);
                EntityGust gust = new EntityGust(worldIn);
                gust.setGustDir((float)gustDir.f_82479_, (float)gustDir.f_82480_, (float)gustDir.f_82481_);
                gust.m_6034_(dispensePosition.f_82479_, dispensePosition.f_82480_, dispensePosition.f_82481_);
                if (((Direction)state.m_61143_((Property)FACING)).m_122434_() == Direction.Axis.Y) {
                    gust.setVertical(true);
                }
                if (!worldIn.f_46443_) {
                    worldIn.m_7967_((Entity)gust);
                }
            }
            worldIn.m_7731_(pos, (BlockState)state.m_61124_((Property)TRIGGERED, (Comparable)Boolean.valueOf(true)), 2);
            worldIn.m_186460_(pos, (Block)this, 20);
        } else if (flag1 && tickOff) {
            worldIn.m_186460_(pos, (Block)this, 20);
            worldIn.m_7731_(pos, (BlockState)state.m_61124_((Property)TRIGGERED, (Comparable)Boolean.valueOf(false)), 2);
        }
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        return (BlockState)this.m_49966_().m_61124_((Property)FACING, (Comparable)context.m_7820_().m_122424_());
    }

    public BlockState m_6843_(BlockState state, Rotation rot) {
        return (BlockState)state.m_61124_((Property)FACING, (Comparable)rot.m_55954_((Direction)state.m_61143_((Property)FACING)));
    }

    public BlockState m_6943_(BlockState state, Mirror mirrorIn) {
        return state.m_60717_(mirrorIn.m_54846_((Direction)state.m_61143_((Property)FACING)));
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{FACING, TRIGGERED});
    }
}
