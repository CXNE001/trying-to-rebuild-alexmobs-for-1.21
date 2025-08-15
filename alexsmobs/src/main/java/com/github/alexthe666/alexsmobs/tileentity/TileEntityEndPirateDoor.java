/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.block.BlockEndPirateDoor;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileEntityEndPirateDoor
extends BlockEntity {
    private float openProgress;
    private float prevOpenProgress;
    private float wiggleProgress;
    private float prevWiggleProgress;
    public int wiggleTime;
    public int ticksExisted;

    public TileEntityEndPirateDoor(BlockPos pos, BlockState state) {
        super((BlockEntityType)AMTileEntityRegistry.END_PIRATE_DOOR.get(), pos, state);
        if (state.m_60734_() instanceof BlockEndPirateDoor && ((Boolean)state.m_61143_((Property)BlockEndPirateDoor.OPEN)).booleanValue()) {
            this.openProgress = 1.0f;
            this.prevOpenProgress = 1.0f;
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public AABB getRenderBoundingBox() {
        return new AABB(this.f_58858_, this.f_58858_.m_7918_(1, 3, 1));
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntityEndPirateDoor entity) {
        entity.tick();
    }

    public void tick() {
        this.prevOpenProgress = this.openProgress;
        this.prevWiggleProgress = this.wiggleProgress;
        boolean opened = false;
        if (this.m_58900_().m_60734_() instanceof BlockEndPirateDoor) {
            opened = (Boolean)this.m_58900_().m_61143_((Property)BlockEndPirateDoor.OPEN);
        }
        if (opened && this.openProgress == 0.0f || !opened && this.openProgress == 1.0f) {
            this.f_58857_.m_220407_(GameEvent.f_223702_, this.m_58899_(), GameEvent.Context.m_223722_((BlockState)this.m_58900_()));
            this.f_58857_.m_5594_((Player)null, this.m_58899_(), (SoundEvent)AMSoundRegistry.END_PIRATE_DOOR.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
        }
        if (opened && this.openProgress < 1.0f) {
            this.openProgress += 0.25f;
        }
        if (!opened && this.openProgress > 0.0f) {
            this.openProgress -= 0.25f;
        }
        if (this.openProgress >= 1.0f && this.prevOpenProgress < 1.0f || this.openProgress <= 0.0f && this.prevOpenProgress > 0.0f) {
            this.wiggleTime = 5;
        }
        if (this.wiggleTime > 0) {
            --this.wiggleTime;
            if (this.wiggleProgress < 1.0f) {
                this.wiggleProgress += 0.25f;
            }
        } else if (this.wiggleProgress > 0.0f) {
            this.wiggleProgress -= 0.25f;
        }
        ++this.ticksExisted;
    }

    public float getOpenProgress(float partialTick) {
        return this.prevOpenProgress + (this.openProgress - this.prevOpenProgress) * partialTick;
    }

    public float getWiggleProgress(float partialTick) {
        return this.prevWiggleProgress + (this.wiggleProgress - this.prevWiggleProgress) * partialTick;
    }
}
