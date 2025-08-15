/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.AABB
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.block.BlockVoidWormBeak;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;

public class TileEntityVoidWormBeak
extends BlockEntity {
    private float chompProgress;
    private float prevChompProgress;
    public int ticksExisted;

    public TileEntityVoidWormBeak(BlockPos pos, BlockState state) {
        super((BlockEntityType)AMTileEntityRegistry.VOID_WORM_BEAK.get(), pos, state);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityVoidWormBeak entity) {
        entity.tick();
    }

    public void tick() {
        this.prevChompProgress = this.chompProgress;
        boolean powered = false;
        if (this.m_58900_().m_60734_() instanceof BlockVoidWormBeak) {
            powered = (Boolean)this.m_58900_().m_61143_((Property)BlockVoidWormBeak.POWERED);
        }
        if (powered && this.chompProgress < 5.0f) {
            this.chompProgress += 1.0f;
        }
        if (!powered && this.chompProgress > 0.0f) {
            this.chompProgress -= 1.0f;
        }
        if (this.chompProgress >= 5.0f && !this.f_58857_.f_46443_ && this.ticksExisted % 5 == 0) {
            float i = (float)this.m_58899_().m_123341_() + 0.5f;
            float j = (float)this.m_58899_().m_123342_() + 0.5f;
            float k = (float)this.m_58899_().m_123343_() + 0.5f;
            float d0 = 0.5f;
            for (LivingEntity entity : this.f_58857_.m_45976_(LivingEntity.class, new AABB((double)i - (double)d0, (double)j - (double)d0, (double)k - (double)d0, (double)i + (double)d0, (double)j + (double)d0, (double)k + (double)d0))) {
                entity.m_6469_(entity.m_269291_().m_269264_(), 5.0f);
            }
        }
        ++this.ticksExisted;
    }

    public float getChompProgress(float partialTick) {
        return this.prevChompProgress + (this.chompProgress - this.prevChompProgress) * partialTick;
    }
}
