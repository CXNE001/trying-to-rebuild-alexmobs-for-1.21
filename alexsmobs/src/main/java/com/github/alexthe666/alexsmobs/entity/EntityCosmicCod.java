/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob$AgeableMobGroupData
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MovementEmission
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.EntityTeleportEvent$EnderEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.CosmicCodAIFollowLeader;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.List;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityTeleportEvent;

public class EntityCosmicCod
extends Mob
implements Bucketable {
    private static final EntityDataAccessor<Float> FISH_PITCH = SynchedEntityData.m_135353_(EntityCosmicCod.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.m_135353_(EntityCosmicCod.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float prevFishPitch;
    private int baitballCooldown;
    private int circleTime;
    private int maxCircleTime;
    private BlockPos circlePos;
    private int teleportIn;
    private EntityCosmicCod groupLeader;
    private int groupSize;

    protected EntityCosmicCod(EntityType<? extends Mob> mob, Level level) {
        super(mob, level);
        this.baitballCooldown = 100 + this.f_19796_.m_188503_(100);
        this.circleTime = 0;
        this.maxCircleTime = 300;
        this.groupSize = 1;
        this.m_21441_(BlockPathTypes.WATER, -1.0f);
        this.f_21342_ = new FlightMoveController(this, 1.0f, false, true);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.cosmicCodSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.COSMIC_COD_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.COSMIC_COD_HURT.get();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 4.0).m_22268_(Attributes.f_22279_, (double)0.35f);
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new AISwimIdle(this));
        this.f_21345_.m_25352_(1, (Goal)new CosmicCodAIFollowLeader(this));
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FISH_PITCH, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(FROM_BUCKET, (Object)false);
    }

    public boolean m_27487_() {
        return (Boolean)this.f_19804_.m_135370_(FROM_BUCKET);
    }

    public void m_27497_(boolean p_203706_1_) {
        this.f_19804_.m_135381_(FROM_BUCKET, (Object)p_203706_1_);
    }

    @Nonnull
    public SoundEvent m_142623_() {
        return SoundEvents.f_11782_;
    }

    @Nonnull
    public ItemStack m_28282_() {
        ItemStack stack = new ItemStack((ItemLike)AMItemRegistry.COSMIC_COD_BUCKET.get());
        if (this.m_8077_()) {
            stack.m_41714_(this.m_7770_());
        }
        return stack;
    }

    public void m_6872_(@Nonnull ItemStack bucket) {
        if (this.m_8077_()) {
            bucket.m_41714_(this.m_7770_());
        }
        CompoundTag platTag = new CompoundTag();
        this.m_7380_(platTag);
        CompoundTag compound = bucket.m_41784_();
        compound.m_128365_("CosmicCodData", (Tag)platTag);
    }

    public void m_142278_(@Nonnull CompoundTag compound) {
        if (compound.m_128441_("CosmicCodData")) {
            this.m_7378_(compound.m_128469_("CosmicCodData"));
        }
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_8077_() || this.m_27487_();
    }

    public boolean m_6785_(double p_213397_1_) {
        return !this.m_27487_() && !this.m_8077_();
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("FromBucket", this.m_27487_());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_27497_(compound.m_128471_("FromBucket"));
    }

    public boolean m_6126_() {
        return true;
    }

    private void doInitialPosing(LevelAccessor world, GroupData data) {
        BlockPos down = this.m_20183_();
        while (world.m_46859_(down) && down.m_123342_() > -62) {
            down = down.m_7495_();
        }
        if (down.m_123342_() <= -60) {
            if (data != null && data.groupLeader != null) {
                this.m_6034_((float)down.m_123341_() + 0.5f, data.groupLeader.m_20186_() - 1.0 + (double)this.f_19796_.m_188503_(1), (float)down.m_123343_() + 0.5f);
            } else {
                this.m_6034_((float)down.m_123341_() + 0.5f, down.m_123342_() + 90 + this.f_19796_.m_188503_(60), (float)down.m_123343_() + 0.5f);
            }
        } else {
            this.m_6034_((float)down.m_123341_() + 0.5f, down.m_123342_() + 1, (float)down.m_123343_() + 0.5f);
        }
    }

    protected Entity.MovementEmission m_142319_() {
        return Entity.MovementEmission.EVENTS;
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevFishPitch = this.getFishPitch();
        if (!this.m_9236_().f_46443_) {
            double ydist = this.f_19855_ - this.m_20186_();
            float fishDist = (float)((Math.abs(this.m_20184_().f_82479_) + Math.abs(this.m_20184_().f_82481_)) * 6.0) / this.getPitchSensitivity();
            this.incrementFishPitch((float)ydist * 10.0f * this.getPitchSensitivity());
            this.setFishPitch(Mth.m_14036_((float)this.getFishPitch(), (float)-60.0f, (float)40.0f));
            if (this.getFishPitch() > 2.0f) {
                this.decrementFishPitch(fishDist * Math.abs(this.getFishPitch()) / 90.0f);
            }
            if (this.getFishPitch() < -2.0f) {
                this.incrementFishPitch(fishDist * Math.abs(this.getFishPitch()) / 90.0f);
            }
            if (this.getFishPitch() > 2.0f) {
                this.decrementFishPitch(1.0f);
            } else if (this.getFishPitch() < -2.0f) {
                this.incrementFishPitch(1.0f);
            }
            if (this.baitballCooldown > 0) {
                --this.baitballCooldown;
            }
        }
        if (this.teleportIn > 0) {
            --this.teleportIn;
            if (this.teleportIn == 0 && !this.m_9236_().f_46443_) {
                double range = 8.0;
                AABB bb = new AABB(this.m_20185_() - 8.0, this.m_20186_() - 8.0, this.m_20189_() - 8.0, this.m_20185_() + 8.0, this.m_20186_() + 8.0, this.m_20189_() + 8.0);
                List list = this.m_9236_().m_45976_(EntityCosmicCod.class, bb);
                Vec3 vec3 = this.teleport();
                if (vec3 != null) {
                    this.baitballCooldown = 5;
                    for (EntityCosmicCod cod : list) {
                        if (cod == this) continue;
                        cod.baitballCooldown = 5;
                        cod.teleport(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_);
                    }
                }
            }
        }
    }

    public void m_7822_(byte msg) {
        if (msg == 46) {
            this.m_146850_(GameEvent.f_238175_);
            this.m_5496_(SoundEvents.f_11852_, 1.0f, 1.0f);
        }
        super.m_7822_(msg);
    }

    public void resetBaitballCooldown() {
        this.baitballCooldown = 120 + this.f_19796_.m_188503_(100);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev) {
            this.teleportIn = 5;
        }
        return prev;
    }

    private float getPitchSensitivity() {
        return 3.0f;
    }

    public boolean m_20068_() {
        return true;
    }

    public boolean m_6040_() {
        return true;
    }

    public boolean isPushedByWater() {
        return false;
    }

    public float getFishPitch() {
        return ((Float)this.f_19804_.m_135370_(FISH_PITCH)).floatValue();
    }

    public void setFishPitch(float pitch) {
        this.f_19804_.m_135381_(FISH_PITCH, (Object)Float.valueOf(pitch));
    }

    public void incrementFishPitch(float pitch) {
        this.f_19804_.m_135381_(FISH_PITCH, (Object)Float.valueOf(this.getFishPitch() + pitch));
    }

    public void decrementFishPitch(float pitch) {
        this.f_19804_.m_135381_(FISH_PITCH, (Object)Float.valueOf(this.getFishPitch() - pitch));
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean canBlockPosBeSeen(BlockPos pos) {
        double x = (float)pos.m_123341_() + 0.5f;
        double y = (float)pos.m_123342_() + 0.5f;
        double z = (float)pos.m_123343_() + 0.5f;
        BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(this.m_146892_(), new Vec3(x, y, z), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        double dist = result.m_82450_().m_82531_(x, y, z);
        return dist <= 1.0 || result.m_6662_() == HitResult.Type.MISS;
    }

    protected Vec3 teleport() {
        double d2;
        double d1;
        double d0;
        if (!this.m_9236_().m_5776_() && this.m_6084_() && this.teleport(d0 = this.m_20185_() + (this.f_19796_.m_188500_() - 0.5) * 64.0, d1 = this.m_20186_() + (double)(this.f_19796_.m_188503_(64) - 32), d2 = this.m_20189_() + (this.f_19796_.m_188500_() - 0.5) * 64.0)) {
            this.circlePos = null;
            return new Vec3(d0, d1, d2);
        }
        return null;
    }

    private boolean teleport(double x, double y, double z) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(x, y, z);
        BlockState blockstate = this.m_9236_().m_8055_((BlockPos)blockpos$mutableblockpos);
        boolean flag = blockstate.m_60795_();
        if (flag && !blockstate.m_60819_().m_205070_(FluidTags.f_13131_)) {
            this.m_5496_(SoundEvents.f_11852_, 1.0f, 1.0f);
            EntityTeleportEvent.EnderEntity event = ForgeEventFactory.onEnderTeleport((LivingEntity)this, (double)x, (double)y, (double)z);
            if (event.isCanceled()) {
                return false;
            }
            this.m_9236_().m_7605_((Entity)this, (byte)46);
            this.m_6021_(event.getTargetX(), event.getTargetY(), event.getTargetZ());
            return true;
        }
        return false;
    }

    public void leaveGroup() {
        if (this.groupLeader != null) {
            this.groupLeader.decreaseGroupSize();
        }
        this.groupLeader = null;
    }

    protected boolean hasNoLeader() {
        return !this.hasGroupLeader();
    }

    public boolean hasGroupLeader() {
        return this.groupLeader != null && this.groupLeader.m_6084_();
    }

    private void increaseGroupSize() {
        ++this.groupSize;
    }

    private void decreaseGroupSize() {
        --this.groupSize;
    }

    public boolean canGroupGrow() {
        return this.isGroupLeader() && this.groupSize < this.getMaxGroupSize();
    }

    private int getMaxGroupSize() {
        return 15;
    }

    public int m_5792_() {
        return 7;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    public boolean isGroupLeader() {
        return this.groupSize > 1;
    }

    public boolean inRangeOfGroupLeader() {
        return this.m_20280_((Entity)this.groupLeader) <= 121.0;
    }

    public void moveToGroupLeader() {
        if (this.hasGroupLeader()) {
            this.m_21566_().m_6849_(this.groupLeader.m_20185_(), this.groupLeader.m_20186_(), this.groupLeader.m_20189_(), 1.0);
        }
    }

    public EntityCosmicCod createAndSetLeader(EntityCosmicCod leader) {
        this.groupLeader = leader;
        leader.increaseGroupSize();
        return leader;
    }

    public void createFromStream(Stream<EntityCosmicCod> stream) {
        stream.limit(this.getMaxGroupSize() - this.groupSize).filter(fishe -> fishe != this).forEach(fishe -> fishe.createAndSetLeader(this));
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (spawnDataIn == null) {
            spawnDataIn = new GroupData(this);
        } else {
            this.createAndSetLeader(((GroupData)((Object)spawnDataIn)).groupLeader);
        }
        if (reason == MobSpawnType.NATURAL && spawnDataIn instanceof GroupData) {
            this.doInitialPosing((LevelAccessor)worldIn, (GroupData)((Object)spawnDataIn));
        }
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public boolean isCircling() {
        return this.circlePos != null && this.circleTime < this.maxCircleTime;
    }

    @Nonnull
    protected InteractionResult m_6071_(@Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        if (itemstack.m_41720_() == Items.f_42446_ && this.m_6084_()) {
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_(this.m_142623_(), 1.0f, 1.0f);
            ItemStack itemstack1 = this.m_28282_();
            this.m_6872_(itemstack1);
            ItemStack itemstack2 = ItemUtils.m_41817_((ItemStack)itemstack, (Player)player, (ItemStack)itemstack1, (boolean)false);
            player.m_21008_(hand, itemstack2);
            Level level = this.m_9236_();
            if (!this.m_9236_().f_46443_) {
                CriteriaTriggers.f_10576_.m_38772_((ServerPlayer)player, itemstack1);
            }
            this.m_146870_();
            return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
        }
        return super.m_6071_(player, hand);
    }

    private static class AISwimIdle
    extends Goal {
        private final EntityCosmicCod cod;
        float circleDistance = 5.0f;
        boolean clockwise = false;

        public AISwimIdle(EntityCosmicCod cod) {
            this.cod = cod;
        }

        public boolean m_8036_() {
            return this.cod.isGroupLeader() || this.cod.hasNoLeader() || this.cod.hasGroupLeader() && this.cod.groupLeader.circlePos != null;
        }

        public void m_8037_() {
            if (this.cod.circleTime > this.cod.maxCircleTime) {
                this.cod.circleTime = 0;
                this.cod.circlePos = null;
            }
            if (this.cod.circlePos != null && this.cod.circleTime <= this.cod.maxCircleTime) {
                ++this.cod.circleTime;
                Vec3 movePos = this.getSharkCirclePos(this.cod.circlePos);
                this.cod.m_21566_().m_6849_(movePos.m_7096_(), movePos.m_7098_(), movePos.m_7094_(), 1.0);
            } else if (this.cod.isGroupLeader()) {
                if (this.cod.baitballCooldown == 0) {
                    this.cod.resetBaitballCooldown();
                    if (this.cod.circlePos == null || this.cod.circleTime >= this.cod.maxCircleTime) {
                        this.cod.circleTime = 0;
                        this.cod.maxCircleTime = 360 + this.cod.f_19796_.m_188503_(80);
                        this.circleDistance = 1.0f + this.cod.f_19796_.m_188501_();
                        this.clockwise = this.cod.f_19796_.m_188499_();
                        this.cod.circlePos = this.cod.m_20183_().m_7494_();
                    }
                }
            } else if (this.cod.f_19796_.m_188503_(40) == 0 || this.cod.hasNoLeader()) {
                Vec3 movepos = this.cod.m_20182_().m_82520_((double)(this.cod.f_19796_.m_188503_(4) - 2), this.cod.m_20186_() < 0.0 ? 1.0 : (double)(this.cod.f_19796_.m_188503_(4) - 2), (double)(this.cod.f_19796_.m_188503_(4) - 2));
                this.cod.m_21566_().m_6849_(movepos.f_82479_, movepos.f_82480_, movepos.f_82481_, 1.0);
            } else if (this.cod.hasGroupLeader() && this.cod.groupLeader.circlePos != null && this.cod.circlePos == null) {
                this.cod.circlePos = this.cod.groupLeader.circlePos;
                this.cod.circleTime = this.cod.groupLeader.circleTime;
                this.cod.maxCircleTime = this.cod.groupLeader.maxCircleTime;
                this.circleDistance = 1.0f + this.cod.f_19796_.m_188501_();
                this.clockwise = this.cod.f_19796_.m_188499_();
            }
        }

        public Vec3 getSharkCirclePos(BlockPos target) {
            float prog = 1.0f - (float)this.cod.circleTime / (float)this.cod.maxCircleTime;
            float angle = 0.17453292f * (float)(this.clockwise ? -this.cod.circleTime : this.cod.circleTime);
            float circleDistanceTimesProg = this.circleDistance * prog;
            double extraX = (circleDistanceTimesProg + 0.75f) * Mth.m_14031_((float)angle);
            double extraZ = (circleDistanceTimesProg + 0.75f) * prog * Mth.m_14089_((float)angle);
            return new Vec3((double)((float)target.m_123341_() + 0.5f) + extraX, (double)Math.max(target.m_123342_() + this.cod.f_19796_.m_188503_(4) - 2, -62), (double)((float)target.m_123343_() + 0.5f) + extraZ);
        }
    }

    public static class GroupData
    extends AgeableMob.AgeableMobGroupData {
        public final EntityCosmicCod groupLeader;

        public GroupData(EntityCosmicCod groupLeaderIn) {
            super(0.05f);
            this.groupLeader = groupLeaderIn;
        }
    }
}
