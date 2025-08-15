/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.ProjectileUtil
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class EntitySquidGrapple
extends Entity {
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.m_135353_(EntitySquidGrapple.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Direction> ATTACHED_FACE = SynchedEntityData.m_135353_(EntitySquidGrapple.class, (EntityDataSerializer)EntityDataSerializers.f_135040_);
    private static final EntityDataAccessor<Boolean> WITHDRAWING = SynchedEntityData.m_135353_(EntitySquidGrapple.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Optional<BlockPos>> ATTACHED_POS = SynchedEntityData.m_135353_(EntitySquidGrapple.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    private int ticksWithdrawing = 0;

    public EntitySquidGrapple(EntityType type, Level level) {
        super(type, level);
    }

    public EntitySquidGrapple(Level worldIn, LivingEntity player, boolean rightHand) {
        this((EntityType)AMEntityRegistry.SQUID_GRAPPLE.get(), worldIn);
        this.setOwnerId(player.m_20148_());
        float rot = player.f_20885_ + (float)(rightHand ? 60 : -60);
        this.m_6034_(player.m_20185_() - (double)player.m_20205_() * 0.5 * (double)Mth.m_14031_((float)(rot * ((float)Math.PI / 180))), player.m_20188_() - (double)0.2f, player.m_20189_() + (double)player.m_20205_() * 0.5 * (double)Mth.m_14089_((float)(rot * ((float)Math.PI / 180))));
    }

    public EntitySquidGrapple(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this((EntityType)AMEntityRegistry.SQUID_GRAPPLE.get(), level);
    }

    protected static float lerpRotation(float f2, float f3) {
        while (f3 - f2 < -180.0f) {
            f2 -= 360.0f;
        }
        while (f3 - f2 >= 180.0f) {
            f2 += 360.0f;
        }
        return Mth.m_14179_((float)0.2f, (float)f2, (float)f3);
    }

    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3 vector3d = new Vec3(x, y, z).m_82541_().m_82520_(this.f_19796_.m_188583_() * (double)0.0075f * (double)inaccuracy, this.f_19796_.m_188583_() * (double)0.0075f * (double)inaccuracy, this.f_19796_.m_188583_() * (double)0.0075f * (double)inaccuracy).m_82490_((double)velocity);
        this.m_20256_(vector3d);
        float f = Mth.m_14116_((float)((float)(vector3d.f_82479_ * vector3d.f_82479_ + vector3d.f_82481_ * vector3d.f_82481_)));
        this.m_146922_(Mth.m_14177_((float)((float)(Mth.m_14136_((double)vector3d.f_82479_, (double)vector3d.f_82481_) * 57.2957763671875) + 180.0f)));
        this.m_146926_((float)(Mth.m_14136_((double)vector3d.f_82480_, (double)f) * 57.2957763671875));
        this.f_19859_ = this.m_146908_();
        this.f_19860_ = this.m_146909_();
    }

    public Direction getAttachmentFacing() {
        return (Direction)this.f_19804_.m_135370_(ATTACHED_FACE);
    }

    public void setAttachmentFacing(Direction direction) {
        this.f_19804_.m_135381_(ATTACHED_FACE, (Object)direction);
    }

    @Nullable
    public UUID getOwnerId() {
        return ((Optional)this.f_19804_.m_135370_(OWNER_UUID)).orElse(null);
    }

    public void setOwnerId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(OWNER_UUID, Optional.ofNullable(uniqueId));
    }

    public BlockPos getStuckToPos() {
        return ((Optional)this.f_19804_.m_135370_(ATTACHED_POS)).orElse(null);
    }

    public void setStuckToPos(BlockPos harvestedPos) {
        this.f_19804_.m_135381_(ATTACHED_POS, Optional.ofNullable(harvestedPos));
    }

    protected void m_8097_() {
        this.f_19804_.m_135372_(OWNER_UUID, Optional.empty());
        this.f_19804_.m_135372_(ATTACHED_FACE, (Object)Direction.DOWN);
        this.f_19804_.m_135372_(ATTACHED_POS, Optional.empty());
        this.f_19804_.m_135372_(WITHDRAWING, (Object)false);
    }

    public Entity getOwner() {
        UUID id = this.getOwnerId();
        if (id != null && !this.m_9236_().f_46443_) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return this.getOwnerId() == null ? null : this.m_9236_().m_46003_(this.getOwnerId());
    }

    public boolean isWithdrawing() {
        return (Boolean)this.f_19804_.m_135370_(WITHDRAWING);
    }

    public void setWithdrawing(boolean withdrawing) {
        this.f_19804_.m_135381_(WITHDRAWING, (Object)withdrawing);
    }

    public void m_8119_() {
        this.f_19860_ = this.m_146909_();
        this.f_19859_ = this.m_146908_();
        Entity entity = this.getOwner();
        if (!this.m_9236_().f_46443_) {
            if (entity == null || !entity.m_6084_()) {
                this.m_146870_();
            } else if (entity.m_6144_()) {
                this.setWithdrawing(true);
            }
        }
        if (this.isWithdrawing() && entity != null) {
            super.m_8119_();
            ++this.ticksWithdrawing;
            this.setStuckToPos(null);
            Vec3 withDrawTo = entity.m_146892_().m_82520_(0.0, (double)-0.2f, 0.0);
            if (withDrawTo.m_82554_(this.m_20182_()) > (double)1.2f && this.ticksWithdrawing < 200) {
                Vec3 move = new Vec3(withDrawTo.f_82479_ - this.m_20185_(), withDrawTo.f_82480_ - this.m_20186_(), withDrawTo.f_82481_ - this.m_20189_());
                Vec3 vector3d = move.m_82541_().m_82490_(1.2);
                this.m_20256_(vector3d.m_82490_(0.99));
                double d0 = this.m_20185_() + vector3d.f_82479_;
                double d1 = this.m_20186_() + vector3d.f_82480_;
                double d2 = this.m_20189_() + vector3d.f_82481_;
                float f = Mth.m_14116_((float)((float)(move.f_82479_ * move.f_82479_ + move.f_82481_ * move.f_82481_)));
                if (!this.m_9236_().f_46443_) {
                    this.m_146922_(Mth.m_14177_((float)((float)(-Mth.m_14136_((double)move.f_82479_, (double)move.f_82481_) * 57.2957763671875))) - 180.0f);
                    this.m_146926_((float)(Mth.m_14136_((double)move.f_82480_, (double)f) * 57.2957763671875));
                    this.f_19859_ = this.m_146908_();
                    this.f_19860_ = this.m_146909_();
                }
                this.m_6034_(d0, d1, d2);
            } else {
                this.m_146870_();
            }
        } else if (this.m_9236_().f_46443_ || this.m_9236_().m_46805_(this.m_20183_())) {
            if (this.getStuckToPos() == null) {
                super.m_8119_();
                Vec3 vector3d = this.m_20184_();
                HitResult raytraceresult = ProjectileUtil.m_278158_((Entity)this, newentity -> false);
                if (raytraceresult != null && raytraceresult.m_6662_() != HitResult.Type.MISS) {
                    this.onImpact(raytraceresult);
                }
                this.m_20101_();
                double d0 = this.m_20185_() + vector3d.f_82479_;
                double d1 = this.m_20186_() + vector3d.f_82480_;
                double d2 = this.m_20189_() + vector3d.f_82481_;
                this.updateRotation();
                this.m_20256_(vector3d.m_82490_(0.99));
                if (this.m_9236_().m_45556_(this.m_20191_()).noneMatch(BlockBehaviour.BlockStateBase::m_60795_) && !this.m_20069_()) {
                    this.m_20256_(Vec3.f_82478_);
                } else {
                    this.m_6034_(d0, d1, d2);
                }
                if (!this.m_20068_()) {
                    this.m_20256_(this.m_20184_().m_82520_(0.0, (double)-0.1f, 0.0));
                }
            } else {
                BlockState state = this.m_9236_().m_8055_(this.getStuckToPos());
                Vec3 vec3 = new Vec3((double)((float)this.getStuckToPos().m_123341_() + 0.5f), (double)((float)this.getStuckToPos().m_123342_() + 0.5f), (double)((float)this.getStuckToPos().m_123343_() + 0.5f));
                Vec3 offset = new Vec3((double)((float)this.getAttachmentFacing().m_122429_() * 0.55f), (double)((float)this.getAttachmentFacing().m_122430_() * 0.55f), (double)((float)this.getAttachmentFacing().m_122431_() * 0.55f));
                this.m_146884_(vec3.m_82549_(offset));
                float targetX = this.m_146909_();
                float targetY = this.m_146908_();
                switch (this.getAttachmentFacing()) {
                    case UP: {
                        targetX = 0.0f;
                        break;
                    }
                    case DOWN: {
                        targetX = 180.0f;
                        break;
                    }
                    case NORTH: {
                        targetX = -90.0f;
                        targetY = 0.0f;
                        break;
                    }
                    case EAST: {
                        targetX = -90.0f;
                        targetY = 90.0f;
                        break;
                    }
                    case SOUTH: {
                        targetX = -90.0f;
                        targetY = 180.0f;
                        break;
                    }
                    case WEST: {
                        targetX = -90.0f;
                        targetY = -90.0f;
                    }
                }
                this.m_146926_(targetX);
                this.m_146922_(targetY);
                if (entity != null && entity.m_20270_((Entity)this) > 2.0f) {
                    float entitySwing = 1.0f;
                    if (entity instanceof LivingEntity) {
                        LivingEntity living = (LivingEntity)entity;
                        float detract = living.f_20900_ * living.f_20900_ + living.f_20901_ * living.f_20901_ + living.f_20902_ * living.f_20902_;
                        entitySwing = (float)((double)entitySwing - Math.min(1.0, Math.sqrt(detract) * (double)0.333f));
                    }
                    Vec3 move = new Vec3(this.m_20185_() - entity.m_20185_(), this.m_20186_() - (double)entity.m_20192_() / 2.0 - entity.m_20186_(), this.m_20189_() - entity.m_20189_());
                    entity.m_20256_(entity.m_20184_().m_82549_(move.m_82541_().m_82490_(0.2 * (double)entitySwing)));
                    if (!entity.m_20096_()) {
                        entity.f_19789_ = 0.0f;
                    }
                }
                if (state.m_60795_()) {
                    this.setWithdrawing(true);
                }
            }
        } else {
            this.m_146870_();
        }
    }

    protected float rotlerp(float in, float target, float maxShift) {
        float f1;
        float f = Mth.m_14177_((float)(target - in));
        if (f > maxShift) {
            f = maxShift;
        }
        if (f < -maxShift) {
            f = -maxShift;
        }
        if ((f1 = in + f) < 0.0f) {
            f1 += 360.0f;
        } else if (f1 > 360.0f) {
            f1 -= 360.0f;
        }
        return f1;
    }

    private void updateRotation() {
    }

    protected void onImpact(HitResult result) {
        HitResult.Type raytraceresult$type = result.m_6662_();
        if (!this.m_9236_().f_46443_ && raytraceresult$type == HitResult.Type.BLOCK && this.getStuckToPos() == null) {
            this.m_20256_(Vec3.f_82478_);
            this.setStuckToPos(((BlockHitResult)result).m_82425_());
            this.setAttachmentFacing(((BlockHitResult)result).m_82434_());
        }
    }

    protected void m_7378_(CompoundTag compound) {
        if (this.getOwnerId() != null) {
            compound.m_128362_("OwnerUUID", this.getOwnerId());
        }
    }

    protected void m_7380_(CompoundTag compound) {
        if (compound.m_128403_("OwnerUUID")) {
            this.setOwnerId(compound.m_128342_("OwnerUUID"));
        }
    }

    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }
}
