/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityMurmurHead;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeaveWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class EntityMurmur
extends Monster
implements ISemiAquatic {
    private static final EntityDataAccessor<Optional<UUID>> HEAD_UUID = SynchedEntityData.m_135353_(EntityMurmur.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Integer> HEAD_ID = SynchedEntityData.m_135353_(EntityMurmur.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private boolean renderFakeHead = true;

    protected EntityMurmur(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.f_21364_ = 10;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 30.0).m_22268_(Attributes.f_22277_, 48.0).m_22268_(Attributes.f_22281_, 3.0).m_22268_(Attributes.f_22278_, (double)0.3f).m_22268_(Attributes.f_22279_, (double)0.2f);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new AnimalAILeaveWater((PathfinderMob)this));
        this.f_21345_.m_25352_(2, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 55, 1.0, 14, 7));
        this.f_21346_.m_25352_(0, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.MURMUR_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.MURMUR_HURT.get();
    }

    protected void m_7355_(BlockPos pos, BlockState blockIn) {
    }

    public static <T extends Mob> boolean checkMurmurSpawnRules(EntityType<EntityMurmur> entityType, ServerLevelAccessor iServerWorld, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return reason == MobSpawnType.SPAWNER || !iServerWorld.m_45527_(pos) && (pos.m_123342_() <= AMConfig.murmurSpawnHeight || iServerWorld.m_204166_(pos).m_203656_(AMTagRegistry.SPAWNS_MURMURS_IGNORE_HEIGHT)) && EntityMurmur.m_219013_(entityType, (ServerLevelAccessor)iServerWorld, (MobSpawnType)reason, (BlockPos)pos, (RandomSource)random);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.murmurSpawnRolls, this.m_217043_(), spawnReasonIn) && super.m_5545_(worldIn, spawnReasonIn);
    }

    public boolean m_7307_(Entity entity) {
        return this.getHeadUUID() != null && entity.m_20148_().equals(this.getHeadUUID()) || super.m_7307_(entity);
    }

    public MobType m_6336_() {
        return MobType.f_21641_;
    }

    protected float m_6431_(Pose pose, EntityDimensions dimensions) {
        return dimensions.f_20378_ * 1.2f;
    }

    protected float m_6108_() {
        return 0.9f;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(HEAD_UUID, Optional.empty());
        this.f_19804_.m_135372_(HEAD_ID, (Object)-1);
    }

    @Nullable
    public UUID getHeadUUID() {
        return ((Optional)this.f_19804_.m_135370_(HEAD_UUID)).orElse(null);
    }

    public void setHeadUUID(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(HEAD_UUID, Optional.ofNullable(uniqueId));
    }

    public Entity getHead() {
        if (!this.m_9236_().f_46443_) {
            UUID id = this.getHeadUUID();
            return id == null ? null : ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        int id = (Integer)this.f_19804_.m_135370_(HEAD_ID);
        return id == -1 ? null : this.m_9236_().m_6815_(id);
    }

    public boolean shouldRenderFakeHead() {
        return this.renderFakeHead;
    }

    public void m_8119_() {
        Entity head;
        super.m_8119_();
        if (this.renderFakeHead) {
            this.renderFakeHead = false;
        }
        this.f_20883_ = this.m_146908_();
        this.f_20885_ = Mth.m_14036_((float)this.f_20885_, (float)(this.f_20883_ - 70.0f), (float)(this.f_20883_ + 70.0f));
        if (!this.m_9236_().f_46443_ && (head = this.getHead()) == null) {
            LivingEntity created = this.createHead();
            this.setHeadUUID(created.m_20148_());
            this.f_19804_.m_135381_(HEAD_ID, (Object)created.m_19879_());
        }
    }

    public Vec3 getNeckBottom(float partialTick) {
        double d0 = Mth.m_14139_((double)partialTick, (double)this.f_19854_, (double)this.m_20185_());
        double d1 = Mth.m_14139_((double)partialTick, (double)this.f_19855_, (double)this.m_20186_());
        double d2 = Mth.m_14139_((double)partialTick, (double)this.f_19856_, (double)this.m_20189_());
        double height = (double)(this.m_20206_() - 0.4f) + this.calculateWalkBounce(partialTick);
        Vec3 rotatedOnDeath = new Vec3(0.0, height, 0.0);
        if (this.f_20919_ > 0) {
            float f = ((float)this.f_20919_ + partialTick - 1.0f) / 20.0f * 1.6f;
            if ((f = Mth.m_14116_((float)f)) > 1.0f) {
                f = 1.0f;
            }
            rotatedOnDeath = rotatedOnDeath.m_82520_((double)(f * 0.1f), (double)(f * 0.4f), 0.0).m_82535_((float)((double)f * Math.PI / 2.0)).m_82524_(-this.f_20883_ * ((float)Math.PI / 180));
        }
        return new Vec3(d0, d1, d2).m_82549_(rotatedOnDeath);
    }

    public double calculateWalkBounce(float partialTick) {
        float limbSwingAmount = this.f_267362_.m_267711_(partialTick);
        float limbSwing = this.f_267362_.m_267756_() - this.f_267362_.m_267731_() * (1.0f - partialTick);
        return Math.abs(Math.sin(limbSwing * 0.9f) * (double)limbSwingAmount * 0.25);
    }

    @Override
    public boolean shouldEnterWater() {
        return false;
    }

    @Override
    public boolean shouldLeaveWater() {
        return true;
    }

    @Override
    public boolean shouldStopMoving() {
        return false;
    }

    @Override
    public int getWaterSearchRange() {
        return 5;
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("HeadUUID")) {
            this.setHeadUUID(compound.m_128342_("HeadUUID"));
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getHeadUUID() != null) {
            compound.m_128362_("HeadUUID", this.getHeadUUID());
        }
    }

    private LivingEntity createHead() {
        EntityMurmurHead head = new EntityMurmurHead(this);
        this.m_9236_().m_7967_((Entity)head);
        return head;
    }

    public boolean isAngry() {
        Entity entity = this.getHead();
        if (entity instanceof EntityMurmurHead) {
            return ((EntityMurmurHead)entity).isAngry();
        }
        return false;
    }
}
