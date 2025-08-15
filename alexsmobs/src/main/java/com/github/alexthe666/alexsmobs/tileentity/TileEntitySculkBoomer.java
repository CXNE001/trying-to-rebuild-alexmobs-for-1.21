/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.VibrationParticleOption
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.ClipBlockStateContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.BlockPositionSource
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.level.gameevent.GameEventListener
 *  net.minecraft.world.level.gameevent.PositionSource
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.block.BlockSculkBoomer;
import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.VibrationParticleOption;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TileEntitySculkBoomer
extends BlockEntity
implements GameEventListener {
    private final BlockPositionSource blockPosSource;
    private boolean prevOpen;
    private int screamTime;

    public TileEntitySculkBoomer(BlockPos pos, BlockState state) {
        super((BlockEntityType)AMTileEntityRegistry.SCULK_BOOMER.get(), pos, state);
        this.blockPosSource = new BlockPositionSource(this.f_58858_);
        this.prevOpen = false;
        this.screamTime = 0;
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, TileEntitySculkBoomer tileEntity) {
        boolean hasPower = false;
        if (state.m_60734_() instanceof BlockSculkBoomer && !tileEntity.m_58901_()) {
            if (tileEntity.screamTime < 0 && !((Boolean)state.m_61143_((Property)BlockSculkBoomer.POWERED)).booleanValue()) {
                AABB screamBox = new AABB((double)(pos.m_123341_() - 4), (double)((float)pos.m_123342_() - 0.25f), (double)(pos.m_123343_() - 4), (double)(pos.m_123341_() + 4), (double)((float)pos.m_123342_() + 0.25f), (double)((float)pos.m_123343_() + 4.0f));
                level.m_46597_(pos, (BlockState)state.m_61124_((Property)BlockSculkBoomer.OPEN, (Comparable)Boolean.valueOf(true)));
                ++tileEntity.screamTime;
                if (tileEntity.screamTime >= 0) {
                    tileEntity.screamTime = 100;
                    level.m_46597_(pos, (BlockState)state.m_61124_((Property)BlockSculkBoomer.OPEN, (Comparable)Boolean.valueOf(false)));
                }
                float screamProgress = 1.0f - (float)tileEntity.screamTime / -20.0f;
                Vec3 center = screamBox.m_82399_();
                for (LivingEntity entity : level.m_45976_(LivingEntity.class, screamBox)) {
                    double distance = 0.5 + entity.m_20182_().m_82546_(center).m_165924_();
                    if (!(distance < (double)(4.0f * screamProgress)) || !(distance > (double)(3.5f * screamProgress)) || TileEntitySculkBoomer.isOccluded(level, Vec3.m_82512_((Vec3i)pos), entity.m_20182_())) continue;
                    entity.m_6469_(entity.m_269291_().m_269425_(), (float)(6 + entity.m_217043_().m_188503_(3)));
                    entity.m_147240_((double)0.4f, center.f_82479_ - entity.m_20185_(), center.f_82481_ - entity.m_20189_());
                }
            }
            if (tileEntity.screamTime > 0) {
                --tileEntity.screamTime;
            }
            boolean openNow = (Boolean)state.m_61143_((Property)BlockSculkBoomer.OPEN);
            if (!tileEntity.prevOpen && openNow) {
                SoundEvent sound = (SoundEvent)AMSoundRegistry.SCULK_BOOMER.get();
                if (level.m_213780_().m_188503_(100) == 0) {
                    sound = (SoundEvent)AMSoundRegistry.SCULK_BOOMER_FART.get();
                }
                level.m_5594_((Player)null, pos, sound, SoundSource.BLOCKS, 4.0f, level.f_46441_.m_188501_() * 0.2f + 0.9f);
                level.m_7106_((ParticleOptions)AMParticleRegistry.SKULK_BOOM.get(), (double)((float)pos.m_123341_() + 0.5f), (double)((float)pos.m_123342_() + 0.5f), (double)((float)pos.m_123343_() + 0.5f), 0.0, 0.0, 0.0);
            }
            tileEntity.prevOpen = openNow;
        }
    }

    public void tick() {
    }

    public void m_142466_(CompoundTag tag) {
        super.m_142466_(tag);
        if (tag.m_128425_("ScreamCooldown", 99)) {
            this.screamTime = tag.m_128451_("ScreamCooldown");
        }
    }

    protected void m_183515_(CompoundTag tag) {
        super.m_183515_(tag);
        tag.m_128405_("ScreamCooldown", this.screamTime);
    }

    public PositionSource m_142460_() {
        return this.blockPosSource;
    }

    public int m_142078_() {
        return 8;
    }

    public boolean m_214068_(ServerLevel serverLevel, GameEvent event, GameEvent.Context message, Vec3 from) {
        if (event == GameEvent.f_223700_ && !TileEntitySculkBoomer.isOccluded((Level)serverLevel, Vec3.m_82512_((Vec3i)this.m_58899_()), from)) {
            double distance = from.m_82554_(Vec3.m_82512_((Vec3i)this.m_58899_()));
            serverLevel.m_8767_((ParticleOptions)new VibrationParticleOption((PositionSource)new BlockPositionSource(this.m_58899_()), Mth.m_14107_((double)distance)), from.f_82479_, from.f_82480_, from.f_82481_, 1, 0.0, 0.0, 0.0, 0.0);
            if (this.screamTime == 0) {
                this.screamTime = -20;
            }
        }
        return false;
    }

    private static boolean isOccluded(Level level, Vec3 vec1, Vec3 vec2) {
        Vec3 vec3 = new Vec3((double)Mth.m_14107_((double)vec1.f_82479_) + 0.5, (double)Mth.m_14107_((double)vec1.f_82480_) + 0.5, (double)Mth.m_14107_((double)vec1.f_82481_) + 0.5);
        Vec3 vec31 = new Vec3((double)Mth.m_14107_((double)vec2.f_82479_) + 0.5, (double)Mth.m_14107_((double)vec2.f_82480_) + 0.5, (double)Mth.m_14107_((double)vec2.f_82481_) + 0.5);
        for (Direction direction : Direction.values()) {
            Vec3 vec32 = vec3.m_231075_(direction, (double)1.0E-5f);
            if (level.m_151353_(new ClipBlockStateContext(vec32, vec31, p_223780_ -> p_223780_.m_204336_(BlockTags.f_144272_))).m_6662_() == HitResult.Type.BLOCK) continue;
            return false;
        }
        return true;
    }
}
