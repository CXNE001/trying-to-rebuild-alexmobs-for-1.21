/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.AgeableMob$AgeableMobGroupData
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FollowBoatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TryFindWaterGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.PathComputationType
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.entity.PartEntity
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotEcho;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotPart;
import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFollowParentRanged;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHurtByTargetNotBaby;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIRandomSwimming;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalSwimMoveControllerSink;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowBoatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityCachalotWhale
extends Animal {
    private static final TargetingConditions REWARD_PLAYER_PREDICATE = TargetingConditions.m_148353_().m_26883_(50.0).m_148355_();
    private static final EntityDataAccessor<Boolean> CHARGING = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> BEACHED = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> ALBINO = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> DESPAWN_BEACH = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> GRABBING = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HOLDING_SQUID_LEFT = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> CAUGHT_ID = SynchedEntityData.m_135353_(EntityCachalotWhale.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public final double[][] ringBuffer = new double[64][3];
    public final EntityCachalotPart headPart;
    public final EntityCachalotPart bodyFrontPart;
    public final EntityCachalotPart bodyPart;
    public final EntityCachalotPart tail1Part;
    public final EntityCachalotPart tail2Part;
    public final EntityCachalotPart tail3Part;
    public final EntityCachalotPart[] whaleParts;
    private final boolean hasAlbinoAttribute = false;
    public int ringBufferIndex = -1;
    public float prevChargingProgress;
    public float chargeProgress;
    public float prevSleepProgress;
    public float sleepProgress;
    public float prevBeachedProgress;
    public float beachedProgress;
    public float prevGrabProgress;
    public float grabProgress;
    public int grabTime = 0;
    private boolean receivedEcho = false;
    private boolean waitForEchoFlag = true;
    private int echoTimer = 0;
    private boolean prevEyesInWater = false;
    private int spoutTimer = 0;
    private int chargeCooldown = 0;
    private float whaleSpeedMod = 1.0f;
    private int rewardTime = 0;
    private Player rewardPlayer;
    private int blockBreakCounter;
    private int despawnDelay = 47999;
    private int echoSoundCooldown = 0;
    private boolean hasRewardedPlayer = false;

    public EntityCachalotWhale(EntityType type, Level world) {
        super(type, world);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.f_21342_ = new AnimalSwimMoveControllerSink((PathfinderMob)this, 1.0f, 1.0f, 6.0f);
        this.f_21365_ = new SmoothSwimmingLookControl((Mob)this, 4);
        this.headPart = new EntityCachalotPart(this, 3.0f, 3.5f);
        this.bodyFrontPart = new EntityCachalotPart(this, 4.0f, 4.0f);
        this.bodyPart = new EntityCachalotPart(this, 5.0f, 4.0f);
        this.tail1Part = new EntityCachalotPart(this, 4.0f, 3.0f);
        this.tail2Part = new EntityCachalotPart(this, 3.0f, 2.0f);
        this.tail3Part = new EntityCachalotPart(this, 3.0f, 0.7f);
        this.whaleParts = new EntityCachalotPart[]{this.headPart, this.bodyFrontPart, this.bodyPart, this.tail1Part, this.tail2Part, this.tail3Part};
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 160.0).m_22268_(Attributes.f_22278_, 1.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22279_, (double)1.2f).m_22268_(Attributes.f_22281_, 30.0);
    }

    public static <T extends Mob> boolean canCachalotWhaleSpawn(EntityType<T> entityType, ServerLevelAccessor iServerWorld, MobSpawnType reason, BlockPos pos, RandomSource random) {
        BlockPos up = pos;
        while (up.m_123342_() < iServerWorld.m_151558_() && iServerWorld.m_6425_(up).m_205070_(FluidTags.f_13131_)) {
            up = up.m_7494_();
        }
        return iServerWorld.m_6425_(up.m_7495_()).m_205070_(FluidTags.f_13131_) && up.m_123342_() < iServerWorld.m_5736_() + 15 && iServerWorld.m_45527_(up);
    }

    public boolean m_6785_(double distanceToClosestPlayer) {
        return !this.m_5803_() && !this.isCharging() && !this.isDespawnBeach() && !this.isAlbino();
    }

    private boolean canDespawn() {
        return this.isDespawnBeach();
    }

    private void tryDespawn() {
        if (this.canDespawn()) {
            --this.despawnDelay;
            if (this.despawnDelay <= 0) {
                this.m_21455_(true, false);
                this.m_142687_(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.CACHALOT_WHALE_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.CACHALOT_WHALE_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.CACHALOT_WHALE_HURT.get();
    }

    public void scaleParts() {
        for (EntityCachalotPart parts : this.whaleParts) {
            float prev = parts.scale;
            float f = parts.scale = this.m_6162_() ? 0.5f : 1.0f;
            if (prev == parts.scale) continue;
            parts.m_6210_();
        }
    }

    public boolean m_6087_() {
        return true;
    }

    public void m_6138_() {
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        return super.m_6071_(player, hand);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Albino", this.isAlbino());
        compound.m_128379_("Beached", this.isBeached());
        compound.m_128379_("BeachedDespawnFlag", this.isDespawnBeach());
        compound.m_128379_("GivenReward", this.hasRewardedPlayer);
        compound.m_128405_("DespawnDelay", this.despawnDelay);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setAlbino(compound.m_128471_("Albino"));
        this.setBeached(compound.m_128471_("Beached"));
        this.setDespawnBeach(compound.m_128471_("BeachedDespawnFlag"));
        if (compound.m_128425_("DespawnDelay", 99)) {
            this.despawnDelay = compound.m_128451_("DespawnDelay");
        }
        this.hasRewardedPlayer = compound.m_128471_("GivenReward");
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CHARGING, (Object)false);
        this.f_19804_.m_135372_(SLEEPING, (Object)false);
        this.f_19804_.m_135372_(BEACHED, (Object)false);
        this.f_19804_.m_135372_(ALBINO, (Object)false);
        this.f_19804_.m_135372_(GRABBING, (Object)false);
        this.f_19804_.m_135372_(HOLDING_SQUID_LEFT, (Object)false);
        this.f_19804_.m_135372_(DESPAWN_BEACH, (Object)false);
        this.f_19804_.m_135372_(CAUGHT_ID, (Object)-1);
    }

    public boolean hasCaughtSquid() {
        return (Integer)this.f_19804_.m_135370_(CAUGHT_ID) != -1;
    }

    private void setCaughtSquidId(int i) {
        this.f_19804_.m_135381_(CAUGHT_ID, (Object)i);
    }

    @Nullable
    public Entity getCaughtSquid() {
        if (!this.hasCaughtSquid()) {
            return null;
        }
        return this.m_9236_().m_6815_(((Integer)this.f_19804_.m_135370_(CAUGHT_ID)).intValue());
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new AIBreathe());
        this.f_21345_.m_25352_(1, (Goal)new TryFindWaterGoal((PathfinderMob)this));
        this.f_21345_.m_25352_(2, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(3, (Goal)new AnimalAIFollowParentRanged(this, 1.1f, 32.0f, 10.0f));
        this.f_21345_.m_25352_(4, (Goal)new AnimalAIRandomSwimming((PathfinderMob)this, 0.6, 10, 24, true){

            @Override
            public boolean m_8036_() {
                return !EntityCachalotWhale.this.m_5803_() && !EntityCachalotWhale.this.isBeached() && super.m_8036_();
            }
        });
        this.f_21345_.m_25352_(5, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 20.0f));
        this.f_21345_.m_25352_(7, (Goal)new FollowBoatGoal((PathfinderMob)this));
        this.f_21346_.m_25352_(1, (Goal)new AnimalAIHurtByTargetNotBaby(this, new Class[0]).m_26044_(new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new EntityAINearestTarget3D((Mob)this, LivingEntity.class, 30, false, true, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.CACHALOT_WHALE_TARGETS)){

            public boolean m_8036_() {
                return !EntityCachalotWhale.this.m_5803_() && !EntityCachalotWhale.this.isBeached() && super.m_8036_();
            }
        });
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new WaterBoundPathNavigation((Mob)this, worldIn);
    }

    public void m_8024_() {
        super.m_8024_();
        this.breakBlock();
    }

    public void breakBlock() {
        if (this.blockBreakCounter > 0) {
            --this.blockBreakCounter;
            return;
        }
        boolean flag = false;
        if (!this.m_9236_().f_46443_ && this.blockBreakCounter == 0 && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
            TagKey<Block> breakables = this.isCharging() && this.m_5448_() != null && AMConfig.cachalotDestruction ? AMTagRegistry.CACHALOT_WHALE_BREAKABLES : AMTagRegistry.ORCA_BREAKABLES;
            for (int a = (int)Math.round(this.m_20191_().f_82288_); a <= (int)Math.round(this.m_20191_().f_82291_); ++a) {
                for (int b = (int)Math.round(this.m_20191_().f_82289_) - 1; b <= (int)Math.round(this.m_20191_().f_82292_) + 1 && b <= 127; ++b) {
                    for (int c = (int)Math.round(this.m_20191_().f_82290_); c <= (int)Math.round(this.m_20191_().f_82293_); ++c) {
                        Block block;
                        BlockPos pos = new BlockPos(a, b, c);
                        BlockState state = this.m_9236_().m_8055_(pos);
                        FluidState fluidState = this.m_9236_().m_6425_(pos);
                        if (state.m_60795_() || state.m_60808_((BlockGetter)this.m_9236_(), pos).m_83281_() || !state.m_204336_(breakables) || !fluidState.m_76178_() || (block = state.m_60734_()) == Blocks.f_50016_) continue;
                        this.m_20256_(this.m_20184_().m_82542_((double)0.6f, 1.0, (double)0.6f));
                        flag = true;
                        this.m_9236_().m_46961_(pos, true);
                        if (!state.m_204336_(BlockTags.f_13047_)) continue;
                        this.m_9236_().m_46597_(pos, Blocks.f_49990_.m_49966_());
                    }
                }
            }
        }
        if (flag) {
            this.blockBreakCounter = this.isCharging() && this.m_5448_() != null ? 2 : 20;
        }
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_21515_() && this.m_20069_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_(0.9));
        } else {
            super.m_7023_(travelVector);
        }
    }

    private void spawnSpoutParticles() {
        if (this.m_6084_()) {
            float radius = this.headPart.m_20205_() * 0.5f;
            for (int j = 0; j < 5 + this.f_19796_.m_188503_(4); ++j) {
                float angle = (float)Math.PI / 180 * this.f_20883_;
                double extraX = (double)(radius * (1.0f + this.f_19796_.m_188501_() * 0.13f) * Mth.m_14031_((float)((float)Math.PI + angle)) + (this.f_19796_.m_188501_() - 0.5f)) + this.m_20184_().f_82479_ * 2.0;
                double extraZ = (double)(radius * (1.0f + this.f_19796_.m_188501_() * 0.13f) * Mth.m_14089_((float)angle) + (this.f_19796_.m_188501_() - 0.5f)) + this.m_20184_().f_82481_ * 2.0;
                double motX = this.f_19796_.m_188583_();
                double motZ = this.f_19796_.m_188583_();
                this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.WHALE_SPLASH.get(), this.headPart.m_20185_() + extraX, this.headPart.m_20186_() + (double)this.headPart.m_20206_(), this.headPart.m_20189_() + extraZ, motX * (double)0.1f + this.m_20184_().f_82479_, 2.0, motZ * (double)0.1f + this.m_20184_().f_82481_);
            }
        }
    }

    public boolean isCharging() {
        return (Boolean)this.f_19804_.m_135370_(CHARGING);
    }

    public void setCharging(boolean charging) {
        this.f_19804_.m_135381_(CHARGING, (Object)charging);
    }

    public boolean m_5803_() {
        return (Boolean)this.f_19804_.m_135370_(SLEEPING);
    }

    public void setSleeping(boolean charging) {
        this.f_19804_.m_135381_(SLEEPING, (Object)charging);
    }

    public boolean isBeached() {
        return (Boolean)this.f_19804_.m_135370_(BEACHED);
    }

    public void setBeached(boolean charging) {
        this.f_19804_.m_135381_(BEACHED, (Object)charging);
    }

    public boolean isGrabbing() {
        return (Boolean)this.f_19804_.m_135370_(GRABBING);
    }

    public void setGrabbing(boolean charging) {
        this.f_19804_.m_135381_(GRABBING, (Object)charging);
    }

    public boolean isHoldingSquidLeft() {
        return (Boolean)this.f_19804_.m_135370_(HOLDING_SQUID_LEFT);
    }

    public void setHoldingSquidLeft(boolean charging) {
        this.f_19804_.m_135381_(HOLDING_SQUID_LEFT, (Object)charging);
    }

    public boolean isAlbino() {
        return (Boolean)this.f_19804_.m_135370_(ALBINO);
    }

    public void setAlbino(boolean albino) {
        boolean prev = this.isAlbino();
        if (!prev && albino) {
            this.m_21051_(Attributes.f_22276_).m_22100_(230.0);
            this.m_21051_(Attributes.f_22281_).m_22100_(45.0);
            this.m_21153_(230.0f);
        } else {
            this.m_21051_(Attributes.f_22276_).m_22100_(160.0);
            this.m_21051_(Attributes.f_22281_).m_22100_(30.0);
        }
        this.f_19804_.m_135381_(ALBINO, (Object)albino);
    }

    public boolean isDespawnBeach() {
        return (Boolean)this.f_19804_.m_135370_(DESPAWN_BEACH);
    }

    public void setDespawnBeach(boolean despawn) {
        this.f_19804_.m_135381_(DESPAWN_BEACH, (Object)despawn);
    }

    protected float m_6121_() {
        return this.m_20067_() ? 0.0f : (float)AMConfig.cachalotVolume;
    }

    public void m_8107_() {
        super.m_8107_();
        this.scaleParts();
        if (this.echoSoundCooldown > 0) {
            --this.echoSoundCooldown;
        }
        if (this.m_5803_()) {
            this.m_21573_().m_26573_();
            this.m_146926_(-90.0f);
            this.whaleSpeedMod = 0.0f;
            if (this.m_204029_(FluidTags.f_13131_) && this.m_20146_() < 200) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, 0.06, 0.0));
            } else {
                BlockPos waterPos = this.m_20183_();
                while (this.m_9236_().m_6425_(waterPos).m_205070_(FluidTags.f_13131_) && waterPos.m_123342_() < 255) {
                    waterPos = waterPos.m_7494_();
                }
                double d = (double)waterPos.m_123342_() - this.m_20186_();
                int n = this.m_6162_() ? 7 : 12;
                if (d < (double)n) {
                    this.m_20256_(this.m_20184_().m_82520_(0.0, -0.06, 0.0));
                }
                if (this.f_19796_.m_188503_(100) == 0) {
                    this.m_20256_(this.m_20184_().m_82520_(0.0, this.f_19796_.m_188583_() * 0.06, 0.0));
                }
            }
        } else if (this.whaleSpeedMod == 0.0f) {
            this.whaleSpeedMod = 1.0f;
        }
        float rPitch = -((float)this.m_20184_().f_82480_ * 57.295776f);
        if (this.isGrabbing()) {
            this.m_146926_(0.0f);
        } else {
            this.m_146926_(Mth.m_14036_((float)rPitch, (float)-90.0f, (float)90.0f));
        }
        if (this.m_20096_() && !this.m_20072_()) {
            this.setBeached(true);
            this.m_146926_(0.0f);
            this.setSleeping(false);
        }
        if (this.isBeached()) {
            this.whaleSpeedMod = 0.0f;
            this.m_20256_(this.m_20184_().m_82542_(0.5, 1.0, 0.5));
            if (this.m_204029_(FluidTags.f_13131_)) {
                Player entity = this.m_9236_().m_45946_(REWARD_PLAYER_PREDICATE, (LivingEntity)this);
                if (this.m_21188_() != entity) {
                    this.rewardPlayer = entity;
                }
                this.despawnDelay = 47999;
                this.setBeached(false);
            }
        }
        if (this.rewardPlayer != null && !this.hasRewardedPlayer && this.m_20072_()) {
            double d0 = this.rewardPlayer.m_20185_() - this.m_20185_();
            double d1 = this.rewardPlayer.m_20188_() - this.m_20188_();
            double d2 = this.rewardPlayer.m_20189_() - this.m_20189_();
            double d3 = Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2)));
            float targetYaw = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
            float targetPitch = (float)(-(Mth.m_14136_((double)d1, (double)d3) * 57.2957763671875));
            this.m_146922_(this.m_146908_() + Mth.m_14036_((float)(targetYaw - this.m_146908_()), (float)-2.0f, (float)2.0f));
            this.m_146926_(this.m_146909_() + Mth.m_14036_((float)(targetPitch - this.m_146909_()), (float)-2.0f, (float)2.0f));
            this.f_20883_ = this.m_146908_();
            this.whaleSpeedMod = 0.1f;
            this.m_21566_().m_6849_(this.rewardPlayer.m_20185_(), this.rewardPlayer.m_20186_(), this.rewardPlayer.m_20189_(), 0.5);
            if (this.m_20270_((Entity)this.rewardPlayer) < 10.0f) {
                if (!this.m_9236_().f_46443_) {
                    Vec3 vec = this.getMouthVec();
                    ItemEntity itementity = new ItemEntity(this.m_9236_(), vec.f_82479_, vec.f_82480_, vec.f_82481_, new ItemStack((ItemLike)AMItemRegistry.AMBERGRIS.get(), 2 + this.f_19796_.m_188503_(2)));
                    itementity.m_32060_();
                    this.m_9236_().m_7967_((Entity)itementity);
                }
                this.hasRewardedPlayer = true;
                this.rewardPlayer = null;
            }
        }
        this.prevChargingProgress = this.chargeProgress;
        this.prevSleepProgress = this.sleepProgress;
        this.prevBeachedProgress = this.beachedProgress;
        this.prevGrabProgress = this.grabProgress;
        if (this.f_19797_ % 200 == 0) {
            this.m_5634_(2.0f);
        }
        if (this.isCharging()) {
            if (this.chargeProgress < 10.0f) {
                this.chargeProgress += 1.0f;
            }
        } else if (this.chargeProgress > 0.0f) {
            this.chargeProgress -= 1.0f;
        }
        if (this.m_5803_()) {
            if (this.sleepProgress < 10.0f) {
                this.sleepProgress += 1.0f;
            }
        } else if (this.sleepProgress > 0.0f) {
            this.sleepProgress -= 1.0f;
        }
        if (this.isBeached()) {
            if (this.beachedProgress < 10.0f) {
                this.beachedProgress += 1.0f;
            }
        } else if (this.beachedProgress > 0.0f) {
            this.beachedProgress -= 1.0f;
        }
        if (this.isGrabbing()) {
            if (this.grabProgress < 10.0f) {
                this.grabProgress += 1.0f;
            }
            ++this.grabTime;
        } else {
            if (this.grabProgress > 0.0f) {
                this.grabProgress -= 1.0f;
            }
            this.grabTime = 0;
        }
        this.f_20885_ = this.m_146908_();
        this.f_20883_ = this.m_146908_();
        if (!this.m_21525_()) {
            if (this.ringBufferIndex < 0) {
                for (int i = 0; i < this.ringBuffer.length; ++i) {
                    this.ringBuffer[i][0] = this.m_146908_();
                    this.ringBuffer[i][1] = this.m_20186_();
                }
            }
            ++this.ringBufferIndex;
            if (this.ringBufferIndex == this.ringBuffer.length) {
                this.ringBufferIndex = 0;
            }
            this.ringBuffer[this.ringBufferIndex][0] = this.m_146908_();
            this.ringBuffer[this.ringBufferIndex][1] = this.m_20186_();
            Vec3[] avector3d = new Vec3[this.whaleParts.length];
            for (int j = 0; j < this.whaleParts.length; ++j) {
                this.whaleParts[j].collideWithNearbyEntities();
                avector3d[j] = new Vec3(this.whaleParts[j].m_20185_(), this.whaleParts[j].m_20186_(), this.whaleParts[j].m_20189_());
            }
            float f15 = (float)(this.getMovementOffsets(5, 1.0f)[1] - this.getMovementOffsets(10, 1.0f)[1]) * 10.0f * ((float)Math.PI / 180);
            float f16 = Mth.m_14089_((float)f15);
            float f17 = this.m_146908_() * ((float)Math.PI / 180);
            float pitch = this.m_146909_() * ((float)Math.PI / 180);
            float xRotDiv90 = Math.abs(this.m_146909_() / 90.0f);
            float f3 = Mth.m_14031_((float)f17) * (1.0f - xRotDiv90);
            float f18 = Mth.m_14089_((float)f17) * (1.0f - xRotDiv90);
            this.setPartPosition(this.bodyPart, f3 * 0.5f, -pitch * 0.5f, -f18 * 0.5f);
            this.setPartPosition(this.bodyFrontPart, f3 * -3.5f, -pitch * 3.0f, f18 * 3.5f);
            this.setPartPosition(this.headPart, f3 * -7.0f, -pitch * 5.0f, -f18 * -7.0f);
            double[] adouble = this.getMovementOffsets(5, 1.0f);
            for (int k = 0; k < 3; ++k) {
                EntityCachalotPart enderdragonpartentity = k == 0 ? this.tail1Part : (k == 1 ? this.tail2Part : this.tail3Part);
                double[] adouble1 = this.getMovementOffsets(15 + k * 5, 1.0f);
                float f7 = this.m_146908_() * ((float)Math.PI / 180) + (float)Mth.m_14175_((double)(adouble1[0] - adouble[0])) * ((float)Math.PI / 180);
                float f19 = 1.0f - Math.abs(this.m_146909_() / 90.0f);
                float f20 = Mth.m_14031_((float)f7) * f19;
                float f21 = Mth.m_14089_((float)f7) * f19;
                float f22 = -3.6f;
                float f23 = (float)(k + 1) * -3.6f - 2.0f;
                this.setPartPosition(enderdragonpartentity, -(f3 * 0.5f + f20 * f23) * f16, pitch * 1.5f * (float)(k + 1), (f18 * 0.5f + f21 * f23) * f16);
            }
            for (int l = 0; l < this.whaleParts.length; ++l) {
                this.whaleParts[l].f_19854_ = avector3d[l].f_82479_;
                this.whaleParts[l].f_19855_ = avector3d[l].f_82480_;
                this.whaleParts[l].f_19856_ = avector3d[l].f_82481_;
                this.whaleParts[l].f_19790_ = avector3d[l].f_82479_;
                this.whaleParts[l].f_19791_ = avector3d[l].f_82480_;
                this.whaleParts[l].f_19792_ = avector3d[l].f_82481_;
            }
        }
        if (!this.m_9236_().f_46443_) {
            LivingEntity target = this.m_5448_();
            if (target == null || !target.m_6084_()) {
                this.setGrabbing(false);
                this.whaleSpeedMod = this.m_5803_() ? 0.0f : 1.0f;
                this.setCharging(false);
                this.setCaughtSquidId(-1);
            } else if (!this.isBeached() && !this.m_5803_() && this.rewardPlayer == null) {
                if (this.isGrabbing() && this.m_5448_().m_6084_()) {
                    this.setCaughtSquidId(this.m_5448_().m_19879_());
                    this.whaleSpeedMod = 0.1f;
                    float scale = this.m_6162_() ? 0.5f : 1.0f;
                    float offsetAngle = -((float)Math.cos((float)this.grabTime * 0.3f)) * 0.1f * this.grabProgress;
                    float renderYaw = (float)this.getMovementOffsets(0, 1.0f)[0];
                    Vec3 extraVec = new Vec3(0.0, 0.0, -3.0).m_82496_(-this.m_146909_() * ((float)Math.PI / 180)).m_82524_(-renderYaw * ((float)Math.PI / 180));
                    Vec3 backOfHead = this.headPart.m_20182_().m_82549_(extraVec);
                    Vec3 swingVec = new Vec3(this.isHoldingSquidLeft() ? (double)1.4f : (double)-1.4f, -0.1, 3.0).m_82496_(-this.m_146909_() * ((float)Math.PI / 180)).m_82524_(-renderYaw * ((float)Math.PI / 180)).m_82524_(offsetAngle);
                    Vec3 mouth = backOfHead.m_82549_(swingVec).m_82490_((double)scale);
                    this.m_5448_().m_6034_(mouth.f_82479_, mouth.f_82480_, mouth.f_82481_);
                    if (this.isHoldingSquidLeft()) {
                        this.m_5448_().m_146922_(this.f_20883_ + 90.0f - (float)Math.toDegrees(offsetAngle));
                    } else {
                        this.m_5448_().m_146922_(this.f_20883_ - 90.0f - (float)Math.toDegrees(offsetAngle));
                    }
                    if (this.m_5448_() instanceof EntityGiantSquid && ((EntityGiantSquid)this.m_5448_()).tickCaptured(this)) {
                        this.setGrabbing(false);
                        this.m_5448_().m_146884_(this.m_7688_(this.m_5448_()));
                    }
                    if (this.grabTime % 20 == 0 && this.grabTime > 30) {
                        this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)(4 + this.f_19796_.m_188503_(4)));
                    }
                    if (this.grabTime > 300) {
                        this.setGrabbing(false);
                        this.m_5448_().m_146884_(this.m_7688_(this.m_5448_()));
                    }
                } else {
                    this.setCaughtSquidId(-1);
                    this.m_21391_((Entity)target, 360.0f, 360.0f);
                    boolean bl = this.waitForEchoFlag = this.m_21188_() == null || !this.m_21188_().m_7306_((Entity)target);
                    if (target instanceof Player || !target.m_20072_()) {
                        this.waitForEchoFlag = false;
                    }
                    if (this.waitForEchoFlag && !this.receivedEcho) {
                        this.setCharging(false);
                        this.whaleSpeedMod = 0.25f;
                        if (this.echoTimer % 10 == 0) {
                            if (this.echoTimer % 40 == 0) {
                                this.m_5496_((SoundEvent)AMSoundRegistry.CACHALOT_WHALE_CLICK.get(), this.m_6121_(), this.m_6100_());
                                this.m_146850_(GameEvent.f_223709_);
                            }
                            EntityCachalotEcho echo = new EntityCachalotEcho(this.m_9236_(), this);
                            float radius = this.headPart.m_20205_() * 0.5f;
                            float angle = (float)Math.PI / 180 * this.f_20883_;
                            double extraX = (double)(radius * (1.0f + this.f_19796_.m_188501_() * 0.13f) * Mth.m_14031_((float)((float)Math.PI + angle)) + (this.f_19796_.m_188501_() - 0.5f)) + this.m_20184_().f_82479_ * 2.0;
                            double extraZ = (double)(radius * (1.0f + this.f_19796_.m_188501_() * 0.13f) * Mth.m_14089_((float)angle) + (this.f_19796_.m_188501_() - 0.5f)) + this.m_20184_().f_82481_ * 2.0;
                            double x = this.headPart.m_20185_() + extraX;
                            double y = this.headPart.m_20186_() + (double)this.headPart.m_20206_() * 0.5;
                            double z = this.headPart.m_20189_() + extraZ;
                            echo.m_6034_(x, y, z);
                            double d0 = target.m_20185_() - x;
                            double d1 = target.m_20227_(0.1) - y;
                            double d2 = target.m_20189_() - z;
                            echo.shoot(d0, d1, d2, 1.0f, 0.0f);
                            this.m_9236_().m_7967_((Entity)echo);
                        }
                        ++this.echoTimer;
                    }
                    if (!this.waitForEchoFlag || this.receivedEcho) {
                        double d0 = target.m_20185_() - this.m_20185_();
                        double d1 = target.m_20188_() - this.m_20188_();
                        double d2 = target.m_20189_() - this.m_20189_();
                        double d3 = Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2)));
                        float targetYaw = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
                        float targetPitch = (float)(-(Mth.m_14136_((double)d1, (double)d3) * 57.2957763671875));
                        this.m_146926_(this.m_146909_() + Mth.m_14036_((float)(targetPitch - this.m_146909_()), (float)-2.0f, (float)2.0f));
                        if (d0 * d0 + d2 * d2 >= 4.0) {
                            this.m_146922_(this.m_146908_() + Mth.m_14036_((float)(targetYaw - this.m_146908_()), (float)-2.0f, (float)2.0f));
                            this.f_20883_ = this.m_146908_();
                        }
                        if (this.chargeCooldown <= 0 && Math.abs(Mth.m_14177_((float)targetYaw) - Mth.m_14177_((float)this.m_146908_())) < 4.0f) {
                            this.setCharging(true);
                            this.whaleSpeedMod = 1.2f;
                            double distSq = d0 * d0 + d2 * d2;
                            if (distSq < 4.0) {
                                this.m_146922_(this.f_19859_);
                                this.f_20883_ = this.f_19859_;
                                this.m_20256_(this.m_20184_().m_82542_(0.8, 1.0, 0.8));
                            } else {
                                if (this.m_20069_() && target.m_20069_()) {
                                    Vec3 vector3d = this.m_20184_();
                                    Vec3 vector3d1 = new Vec3(target.m_20185_() - this.m_20185_(), target.m_20186_() - this.m_20186_(), target.m_20189_() - this.m_20189_());
                                    if (vector3d1.m_82556_() > 1.0E-7) {
                                        vector3d1 = vector3d1.m_82541_().m_82490_(0.5).m_82549_(vector3d.m_82490_(0.8));
                                    }
                                    this.m_20334_(vector3d1.f_82479_, vector3d1.f_82480_, vector3d1.f_82481_);
                                }
                                this.m_21566_().m_6849_(target.m_20185_(), target.m_20186_(), target.m_20189_(), 1.0);
                            }
                            if (this.isCharging() && this.m_20270_((Entity)target) < this.m_20205_() && this.chargeProgress > 4.0f) {
                                if (target instanceof EntityGiantSquid && !this.m_6162_()) {
                                    this.setGrabbing(true);
                                    this.setHoldingSquidLeft(this.f_19796_.m_188499_());
                                } else {
                                    target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21133_(Attributes.f_22281_));
                                }
                                this.setCharging(false);
                                Entity vector3d1 = target.m_20202_();
                                if (vector3d1 instanceof Boat) {
                                    Boat boat = (Boat)vector3d1;
                                    for (int i = 0; i < 3; ++i) {
                                        this.m_19998_((ItemLike)boat.m_28554_().m_38434_());
                                    }
                                    for (int j = 0; j < 2; ++j) {
                                        this.m_19998_((ItemLike)Items.f_42398_);
                                    }
                                    target.m_6038_();
                                    boat.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 1000.0f);
                                    boat.m_142687_(Entity.RemovalReason.DISCARDED);
                                }
                                int n = this.chargeCooldown = target instanceof Player ? 30 : 100;
                                if (this.f_19796_.m_188503_(10) == 0) {
                                    Vec3 vec = this.getMouthVec();
                                    ItemEntity itementity = new ItemEntity(this.m_9236_(), vec.f_82479_, vec.f_82480_, vec.f_82481_, new ItemStack((ItemLike)AMItemRegistry.CACHALOT_WHALE_TOOTH.get()));
                                    itementity.m_32060_();
                                    this.m_9236_().m_7967_((Entity)itementity);
                                }
                            }
                        }
                    }
                }
            }
            if (this.chargeCooldown > 0) {
                --this.chargeCooldown;
            }
            if (this.spoutTimer > 0) {
                this.m_9236_().m_7605_((Entity)this, (byte)67);
                --this.spoutTimer;
                this.m_146926_(0.0f);
                this.m_20256_(this.m_20184_().m_82542_(0.0, 0.0, 0.0));
            }
            if (this.isSleepTime() && !this.m_5803_() && this.m_20072_() && this.m_5448_() == null) {
                this.setSleeping(true);
            }
            if (this.m_5803_() && (!this.isSleepTime() || this.m_5448_() != null)) {
                this.setSleeping(false);
            }
            if (target instanceof Player && ((Player)target).m_7500_()) {
                this.m_6710_(null);
            }
        }
        if (this.m_6084_() && this.isCharging()) {
            for (Entity entity : this.m_9236_().m_45976_(LivingEntity.class, this.headPart.m_20191_().m_82400_(1.0))) {
                if (this.m_7307_(entity) || entity instanceof EntityCachalotPart || entity == this) continue;
                this.launch(entity, true);
            }
        }
        if (this.m_20069_() && !this.m_204029_(FluidTags.f_13131_) && this.m_20146_() > 140) {
            this.m_20256_(this.m_20184_().m_82520_(0.0, -0.06, 0.0));
        }
        if (!this.m_9236_().f_46443_) {
            this.tryDespawn();
        }
        this.prevEyesInWater = this.m_204029_(FluidTags.f_13131_);
    }

    private void launch(Entity e, boolean huge) {
        if ((e.m_20096_() || e.m_20069_()) && !(e instanceof EntityCachalotWhale)) {
            double d0 = e.m_20185_() - this.m_20185_();
            double d1 = e.m_20189_() - this.m_20189_();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001);
            float f = huge ? 2.0f : 0.5f;
            e.m_5997_(d0 / d2 * (double)f, huge ? 0.5 : (double)0.2f, d1 / d2 * (double)f);
        }
    }

    private boolean isSleepTime() {
        long time = this.m_9236_().m_46468_();
        return time > 18000L && time < 22812L && this.m_20072_();
    }

    public Vec3 getReturnEchoVector() {
        return this.getVec(0.5);
    }

    public Vec3 getMouthVec() {
        return this.getVec(0.25);
    }

    private Vec3 getVec(double yShift) {
        float radius = this.headPart.m_20205_() * 0.5f;
        float angle = (float)Math.PI / 180 * this.f_20883_;
        double extraX = (double)(radius * (1.0f + this.f_19796_.m_188501_() * 0.13f) * Mth.m_14031_((float)((float)Math.PI + angle)) + (this.f_19796_.m_188501_() - 0.5f)) + this.m_20184_().f_82479_ * 2.0;
        double extraZ = (double)(radius * (1.0f + this.f_19796_.m_188501_() * 0.13f) * Mth.m_14089_((float)angle) + (this.f_19796_.m_188501_() - 0.5f)) + this.m_20184_().f_82481_ * 2.0;
        double x = this.headPart.m_20185_() + extraX;
        double y = this.headPart.m_20186_() + yShift;
        double z = this.headPart.m_20189_() + extraZ;
        return new Vec3(x, y, z);
    }

    public void m_6710_(@Nullable LivingEntity entitylivingbaseIn) {
        LivingEntity prev = this.m_5448_();
        if (prev != entitylivingbaseIn && entitylivingbaseIn != null) {
            this.receivedEcho = false;
        }
        super.m_6710_(entitylivingbaseIn);
    }

    public double[] getMovementOffsets(int p_70974_1_, float partialTicks) {
        if (this.m_21224_()) {
            partialTicks = 0.0f;
        }
        partialTicks = 1.0f - partialTicks;
        int i = this.ringBufferIndex - p_70974_1_ & 0x3F;
        int j = this.ringBufferIndex - p_70974_1_ - 1 & 0x3F;
        double[] adouble = new double[3];
        double d0 = this.ringBuffer[i][0];
        double d1 = this.ringBuffer[j][0] - d0;
        adouble[0] = d0 + d1 * (double)partialTicks;
        d0 = this.ringBuffer[i][1];
        d1 = this.ringBuffer[j][1] - d0;
        adouble[1] = d0 + d1 * (double)partialTicks;
        adouble[2] = Mth.m_14139_((double)partialTicks, (double)this.ringBuffer[i][2], (double)this.ringBuffer[j][2]);
        return adouble;
    }

    public void m_7334_(Entity entityIn) {
    }

    private void setPartPosition(EntityCachalotPart part, double offsetX, double offsetY, double offsetZ) {
        part.m_6034_(this.m_20185_() + offsetX * (double)part.scale, this.m_20186_() + offsetY * (double)part.scale, this.m_20189_() + offsetZ * (double)part.scale);
    }

    public boolean isMultipartEntity() {
        return true;
    }

    public PartEntity<?>[] getParts() {
        return this.whaleParts;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        EntityCachalotWhale whale = (EntityCachalotWhale)((EntityType)AMEntityRegistry.CACHALOT_WHALE.get()).m_20615_((Level)serverWorld);
        whale.setAlbino(this.isAlbino());
        return whale;
    }

    public boolean attackEntityPartFrom(EntityCachalotPart entityCachalotPart, DamageSource source, float amount) {
        return this.m_6469_(source, amount);
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.m_20301_(this.m_6062_());
        this.m_146926_(0.0f);
        if (spawnDataIn == null) {
            spawnDataIn = new AgeableMob.AgeableMobGroupData(0.75f);
        }
        this.setAlbino(this.f_19796_.m_188503_(100) == 0);
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public boolean m_6040_() {
        return false;
    }

    public void m_6075_() {
        int i = this.m_20146_();
        super.m_6075_();
        this.updateAir(i);
    }

    public boolean m_6063_() {
        return this.isBeached();
    }

    public MobType m_6336_() {
        return MobType.f_21644_;
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    protected void updateAir(int p_209207_1_) {
    }

    public int m_6062_() {
        return 4000;
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id == 67) {
            this.spawnSpoutParticles();
        } else {
            super.m_7822_(id);
        }
    }

    protected int m_7305_(int currentAir) {
        if (!this.m_9236_().f_46443_ && this.prevEyesInWater && this.spoutTimer <= 0 && !this.m_204029_(FluidTags.f_13131_) && currentAir < this.m_6062_() / 2) {
            this.spoutTimer = 20 + this.f_19796_.m_188503_(10);
        }
        return this.m_6062_();
    }

    public int m_8132_() {
        return 1;
    }

    public int m_8085_() {
        return 3;
    }

    public void recieveEcho() {
        this.receivedEcho = true;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.cachalotWhaleSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public Vec3 m_7688_(LivingEntity dismount) {
        Vec3 mouth = this.getMouthVec();
        BlockPos pos = AMBlockPos.fromVec3(mouth);
        while (!this.m_9236_().m_46859_(pos) && !this.m_9236_().m_46801_(pos) && pos.m_123342_() < this.m_9236_().m_151558_()) {
            pos = pos.m_7494_();
        }
        return new Vec3(mouth.f_82479_, (double)((float)pos.m_123342_() + 0.5f), mouth.f_82481_);
    }

    class AIBreathe
    extends Goal {
        public AIBreathe() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            return EntityCachalotWhale.this.m_20146_() < 140;
        }

        public boolean m_8045_() {
            return this.m_8036_();
        }

        public boolean m_6767_() {
            return false;
        }

        public void m_8056_() {
            this.navigate();
        }

        private void navigate() {
            Iterable lvt_1_1_ = BlockPos.m_121976_((int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20185_() - 1.0)), (int)Mth.m_14107_((double)EntityCachalotWhale.this.m_20186_()), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20189_() - 1.0)), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20185_() + 1.0)), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20186_() + 8.0)), (int)Mth.m_14107_((double)(EntityCachalotWhale.this.m_20189_() + 1.0)));
            BlockPos lvt_2_1_ = null;
            for (BlockPos lvt_4_1_ : lvt_1_1_) {
                if (!this.canBreatheAt((LevelReader)EntityCachalotWhale.this.m_9236_(), lvt_4_1_)) continue;
                lvt_2_1_ = lvt_4_1_.m_6625_((int)((double)EntityCachalotWhale.this.m_20206_() * 0.25));
                break;
            }
            if (lvt_2_1_ == null) {
                lvt_2_1_ = AMBlockPos.fromCoords(EntityCachalotWhale.this.m_20185_(), EntityCachalotWhale.this.m_20186_() + 4.0, EntityCachalotWhale.this.m_20189_());
            }
            if (EntityCachalotWhale.this.m_204029_(FluidTags.f_13131_)) {
                EntityCachalotWhale.this.m_20256_(EntityCachalotWhale.this.m_20184_().m_82520_(0.0, (double)0.05f, 0.0));
            }
            EntityCachalotWhale.this.m_21573_().m_26519_((double)lvt_2_1_.m_123341_(), (double)lvt_2_1_.m_123342_(), (double)lvt_2_1_.m_123343_(), 0.7);
        }

        public void m_8037_() {
            this.navigate();
        }

        private boolean canBreatheAt(LevelReader p_205140_1_, BlockPos p_205140_2_) {
            BlockState lvt_3_1_ = p_205140_1_.m_8055_(p_205140_2_);
            return (p_205140_1_.m_6425_(p_205140_2_).m_76178_() || lvt_3_1_.m_60713_(Blocks.f_50628_)) && lvt_3_1_.m_60647_((BlockGetter)p_205140_1_, p_205140_2_, PathComputationType.LAND);
        }
    }
}
