/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.util.StringRepresentable
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.block;

import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockEndPirateSail
extends Block {
    public static final BooleanProperty EASTORWEST = BooleanProperty.m_61465_((String)"eastorwest");
    public static final EnumProperty<SailType> SAIL = EnumProperty.m_61587_((String)"sail", SailType.class);
    protected static final VoxelShape EW_AABB = Block.m_49796_((double)7.0, (double)0.0, (double)0.0, (double)9.0, (double)16.0, (double)16.0);
    protected static final VoxelShape NS_AABB = Block.m_49796_((double)0.0, (double)0.0, (double)7.0, (double)16.0, (double)16.0, (double)9.0);

    public BlockEndPirateSail(boolean spectre) {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283743_).m_60955_().m_60991_((a, b, c) -> true).m_60918_(SoundType.f_56745_).m_60953_(state -> 5).m_60999_().m_60978_(0.4f));
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)EASTORWEST, (Comparable)Boolean.valueOf(false))).m_61124_(SAIL, (Comparable)((Object)SailType.SINGLE)));
    }

    public VoxelShape m_5940_(BlockState p_52807_, BlockGetter p_52808_, BlockPos p_52809_, CollisionContext p_52810_) {
        return (Boolean)p_52807_.m_61143_((Property)EASTORWEST) != false ? EW_AABB : NS_AABB;
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> p_58032_) {
        p_58032_.m_61104_(new Property[]{EASTORWEST, SAIL});
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        boolean axis;
        Level levelreader = context.m_43725_();
        BlockPos blockpos = context.m_8083_();
        BlockPos actualPos = context.m_8083_().m_121945_(context.m_43719_().m_122424_());
        BlockPos u = blockpos.m_7494_();
        BlockPos d = blockpos.m_7495_();
        BlockState clickState = levelreader.m_8055_(actualPos);
        BlockState upState = levelreader.m_8055_(u);
        BlockState downState = levelreader.m_8055_(d);
        boolean bl = context.m_43719_().m_122434_() == Direction.Axis.Y ? context.m_8125_().m_122434_() == Direction.Axis.X : (axis = context.m_43719_().m_122434_() != Direction.Axis.X);
        if (clickState.m_60734_() instanceof BlockEndPirateSail) {
            axis = (Boolean)clickState.m_61143_((Property)EASTORWEST);
        }
        BlockState axisState = (BlockState)this.m_49966_().m_61124_((Property)EASTORWEST, (Comparable)Boolean.valueOf(axis));
        return (BlockState)axisState.m_61124_(SAIL, (Comparable)((Object)BlockEndPirateSail.getSailTypeFor(axisState, downState, upState)));
    }

    public BlockState m_7417_(BlockState state, Direction direction, BlockState state2, LevelAccessor levelreader, BlockPos blockpos, BlockPos pos2) {
        BlockPos u = blockpos.m_7494_();
        BlockPos d = blockpos.m_7495_();
        BlockState upState = levelreader.m_8055_(u);
        BlockState downState = levelreader.m_8055_(d);
        return (BlockState)state.m_61124_(SAIL, (Comparable)((Object)BlockEndPirateSail.getSailTypeFor(state, downState, upState)));
    }

    private static SailType getSailTypeFor(BlockState us, BlockState below, BlockState above) {
        if (below.m_60734_() instanceof BlockEndPirateSail && below.m_61143_((Property)EASTORWEST) == us.m_61143_((Property)EASTORWEST)) {
            return above.m_60734_() instanceof BlockEndPirateSail ? SailType.MIDDLE : SailType.TOP;
        }
        if (above.m_60734_() instanceof BlockEndPirateSail && above.m_61143_((Property)EASTORWEST) == us.m_61143_((Property)EASTORWEST)) {
            return SailType.BOTTOM;
        }
        return SailType.SINGLE;
    }

    private static enum SailType implements StringRepresentable
    {
        SINGLE,
        TOP,
        MIDDLE,
        BOTTOM;


        public String toString() {
            return this.m_7912_();
        }

        public String m_7912_() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
