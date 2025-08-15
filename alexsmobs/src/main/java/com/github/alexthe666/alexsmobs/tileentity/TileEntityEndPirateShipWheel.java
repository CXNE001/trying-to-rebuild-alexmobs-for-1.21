/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.Mth
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
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileEntityEndPirateShipWheel
extends BlockEntity {
    private float wheelRot;
    private float prevWheelRot;
    private float targetWheelRot;
    public int ticksExisted;

    public TileEntityEndPirateShipWheel(BlockPos pos, BlockState state) {
        super((BlockEntityType)AMTileEntityRegistry.END_PIRATE_SHIP_WHEEL.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityEndPirateShipWheel entity) {
        entity.tick();
    }

    @OnlyIn(value=Dist.CLIENT)
    public AABB getRenderBoundingBox() {
        return new AABB(this.f_58858_.m_7918_(-2, -2, -2), this.f_58858_.m_7918_(2, 2, 2));
    }

    public void tick() {
        this.prevWheelRot = this.wheelRot;
        float scale = Math.abs(Math.abs(this.targetWheelRot - this.wheelRot) / 180.0f);
        float progress = Mth.m_14036_((float)(10.0f * scale), (float)1.0f, (float)10.0f);
        if (this.wheelRot < this.targetWheelRot) {
            this.wheelRot = Math.min(this.targetWheelRot, this.wheelRot + progress);
        }
        if (this.wheelRot > this.targetWheelRot) {
            this.wheelRot = Math.max(this.targetWheelRot, this.wheelRot - progress);
        }
        ++this.ticksExisted;
    }

    public void rotate(boolean clockwise) {
        if (Math.abs(this.wheelRot - this.targetWheelRot) < 90.0f) {
            this.targetWheelRot = clockwise ? (this.targetWheelRot += 180.0f) : (this.targetWheelRot -= 180.0f);
        }
    }

    public float getWheelRot(float partialTick) {
        return this.prevWheelRot + (this.wheelRot - this.prevWheelRot) * partialTick;
    }
}
