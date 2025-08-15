/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.block.BlockEndPirateAnchor;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileEntityEndPirateAnchor
extends BlockEntity {
    private static final List<BlockPos> VALID_OFFSET_BOXES_NS = Lists.newArrayList((Object[])new BlockPos[]{new BlockPos(0, 0, 0), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(1, 1, 0), new BlockPos(-1, 1, 0), new BlockPos(0, 1, 0), new BlockPos(0, 2, 0)});
    private static final List<BlockPos> VALID_OFFSET_BOXES_EW = Lists.newArrayList((Object[])new BlockPos[]{new BlockPos(0, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(0, 1, 1), new BlockPos(0, 1, -1), new BlockPos(0, 1, 0), new BlockPos(0, 2, 0)});

    public TileEntityEndPirateAnchor(BlockPos pos, BlockState state) {
        super((BlockEntityType)AMTileEntityRegistry.END_PIRATE_ANCHOR.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityEndPirateAnchor entity) {
        entity.tick();
    }

    @OnlyIn(value=Dist.CLIENT)
    public AABB getRenderBoundingBox() {
        return new AABB(this.f_58858_.m_7918_(-1, 0, -1), this.f_58858_.m_7918_(1, 3, 1));
    }

    public static List<BlockPos> getValidBBPositions(boolean eastOrWest) {
        return eastOrWest ? VALID_OFFSET_BOXES_EW : VALID_OFFSET_BOXES_NS;
    }

    public boolean hasAllAnchorBlocks() {
        for (BlockPos pos : TileEntityEndPirateAnchor.getValidBBPositions((Boolean)this.m_58900_().m_61143_((Property)BlockEndPirateAnchor.EASTORWEST))) {
            if (this.m_58904_().m_8055_(this.m_58899_().m_121955_((Vec3i)pos)).m_60734_() instanceof BlockEndPirateAnchor) continue;
            return false;
        }
        return true;
    }

    private void tick() {
    }
}
