/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileEntityEndPirateFlag
extends BlockEntity {
    public int ticksExisted;

    public TileEntityEndPirateFlag(BlockPos pos, BlockState state) {
        super((BlockEntityType)AMTileEntityRegistry.END_PIRATE_FLAG.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityEndPirateFlag entity) {
        entity.tick();
    }

    @OnlyIn(value=Dist.CLIENT)
    public AABB getRenderBoundingBox() {
        return new AABB(this.f_58858_.m_7918_(-2, -2, -2), this.f_58858_.m_7918_(2, 2, 2));
    }

    public void tick() {
        ++this.ticksExisted;
    }
}
