/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MoveFunction
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Spider
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$TagValue
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityRoadrunner;
import com.github.alexthe666.alexsmobs.entity.IFollower;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHurtByTargetNotBaby;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.FlyingAIFollowOwner;
import com.github.alexthe666.alexsmobs.message.MessageTarantulaHawkSting;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityTarantulaHawk
extends TamableAnimal
implements IFollower {
    public static final int STING_DURATION = 2400;
    protected static final EntityDimensions FLIGHT_SIZE = EntityDimensions.m_20398_((float)0.9f, (float)1.5f);
    private static final EntityDataAccessor<Float> FLY_ANGLE = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> NETHER = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> DRAGGING = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> DIGGING = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SCARED = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> ANGRY = SynchedEntityData.m_135353_(EntityTarantulaHawk.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float prevFlyAngle;
    public float prevSitProgress;
    public float sitProgress;
    public float prevDragProgress;
    public float dragProgress;
    public float prevFlyProgress;
    public float flyProgress;
    public float prevAttackProgress;
    public float attackProgress;
    public float prevDigProgress;
    public float digProgress;
    private boolean isLandNavigator;
    private boolean flightSize = false;
    private int timeFlying = 0;
    private boolean bredBuryFlag = false;
    private int spiderFeedings = 0;
    private int dragTime = 0;

    protected EntityTarantulaHawk(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.switchNavigator(false);
    }

    public static boolean canTarantulaHawkSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.TARANTULA_HAWK_SPAWNS) && worldIn.m_45524_(pos, 0) > 8 || EntityTarantulaHawk.isBiomeNether(worldIn, pos) || AMConfig.fireproofTarantulaHawk;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 18.0).m_22268_(Attributes.f_22284_, 4.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22279_, (double)0.3f).m_22268_(Attributes.f_22281_, 5.0);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.tarantulaHawkSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (EntityTarantulaHawk.isBiomeNether((LevelAccessor)worldIn, this.m_20183_())) {
            this.setNether(true);
        }
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private static boolean isBiomeNether(LevelAccessor worldIn, BlockPos position) {
        return worldIn.m_204166_(position).m_203656_(AMTagRegistry.SPAWNS_NETHER_TARANTULA_HAWKS);
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new SitWhenOrderedToGoal((TamableAnimal)this));
        this.f_21345_.m_25352_(2, (Goal)new FlyingAIFollowOwner(this, 1.0, 10.0f, 2.0f, false));
        this.f_21345_.m_25352_(3, (Goal)new AIFleeRoadrunners());
        this.f_21345_.m_25352_(4, (Goal)new AIMelee());
        this.f_21345_.m_25352_(5, (Goal)new AIBury());
        this.f_21345_.m_25352_(6, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(7, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_43938_(Stream.of(new Ingredient.TagValue(AMTagRegistry.TARANTULA_HAWK_BREEDABLES), new Ingredient.TagValue(AMTagRegistry.TARANTULA_HAWK_TAMEABLES), new Ingredient.TagValue(AMTagRegistry.TARANTULA_HAWK_FOODSTUFFS))), false));
        this.f_21345_.m_25352_(8, (Goal)new AIWalkIdle());
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21345_.m_25352_(10, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(2, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(3, (Goal)new AnimalAIHurtByTargetNotBaby((Animal)this, new Class[0]));
        this.f_21346_.m_25352_(4, (Goal)new EntityAINearestTarget3D((Mob)this, Spider.class, 15, true, true, null){

            public boolean m_8036_() {
                return super.m_8036_() && !EntityTarantulaHawk.this.m_6162_() && !EntityTarantulaHawk.this.isSitting();
            }
        });
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.TARANTULA_HAWK_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.TARANTULA_HAWK_HURT.get();
    }

    public boolean m_5825_() {
        return this.isNether() || AMConfig.fireproofTarantulaHawk;
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigation((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new MoveController();
            this.f_21344_ = new DirectPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FLY_ANGLE, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(NETHER, (Object)false);
        this.f_19804_.m_135372_(FLYING, (Object)false);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(DRAGGING, (Object)false);
        this.f_19804_.m_135372_(DIGGING, (Object)false);
        this.f_19804_.m_135372_(SCARED, (Object)false);
        this.f_19804_.m_135372_(ANGRY, (Object)false);
        this.f_19804_.m_135372_(ATTACK_TICK, (Object)0);
        this.f_19804_.m_135372_(COMMAND, (Object)0);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (source.m_7639_() instanceof LivingEntity && ((LivingEntity)source.m_7639_()).m_6336_() == MobType.f_21642_ && ((LivingEntity)source.m_7639_()).m_21023_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get())) {
            return false;
        }
        return super.m_6469_(source, amount);
    }

    public MobType m_6336_() {
        return MobType.f_21642_;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("HawkSitting", this.isSitting());
        compound.m_128379_("Nether", this.isNether());
        compound.m_128379_("Digging", this.isDigging());
        compound.m_128379_("Flying", this.isFlying());
        compound.m_128405_("Command", this.getCommand());
        compound.m_128405_("SpiderFeedings", this.spiderFeedings);
        compound.m_128379_("BreedFlag", this.bredBuryFlag);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_21839_(compound.m_128471_("HawkSitting"));
        this.setNether(compound.m_128471_("Nether"));
        this.setDigging(compound.m_128471_("Digging"));
        this.setFlying(compound.m_128471_("Flying"));
        this.setCommand(compound.m_128451_("Command"));
        this.spiderFeedings = compound.m_128451_("SpiderFeedings");
        this.bredBuryFlag = compound.m_128471_("BreedFlag");
    }

    public boolean m_7307_(Entity entityIn) {
        if (this.m_21824_()) {
            LivingEntity livingentity = this.m_269323_();
            if (entityIn == livingentity) {
                return true;
            }
            if (entityIn instanceof TamableAnimal) {
                return ((TamableAnimal)entityIn).m_21830_(livingentity);
            }
            if (livingentity != null) {
                return livingentity.m_7307_(entityIn);
            }
        }
        return super.m_7307_(entityIn);
    }

    public float getFlyAngle() {
        return ((Float)this.f_19804_.m_135370_(FLY_ANGLE)).floatValue();
    }

    public void setFlyAngle(float progress) {
        this.f_19804_.m_135381_(FLY_ANGLE, (Object)Float.valueOf(progress));
    }

    public boolean isFlying() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    public void setFlying(boolean flying) {
        if (flying && this.m_6162_()) {
            return;
        }
        this.f_19804_.m_135381_(FLYING, (Object)flying);
    }

    public boolean isNether() {
        return (Boolean)this.f_19804_.m_135370_(NETHER);
    }

    public void setNether(boolean sit) {
        this.f_19804_.m_135381_(NETHER, (Object)sit);
    }

    public boolean isScared() {
        return (Boolean)this.f_19804_.m_135370_(SCARED);
    }

    public void setScared(boolean sit) {
        this.f_19804_.m_135381_(SCARED, (Object)sit);
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public void m_21839_(boolean sit) {
        this.f_19804_.m_135381_(SITTING, (Object)sit);
    }

    public boolean isDragging() {
        return (Boolean)this.f_19804_.m_135370_(DRAGGING);
    }

    public void setDragging(boolean sit) {
        this.f_19804_.m_135381_(DRAGGING, (Object)sit);
    }

    public boolean isDigging() {
        return (Boolean)this.f_19804_.m_135370_(DIGGING);
    }

    public void setDigging(boolean sit) {
        this.f_19804_.m_135381_(DIGGING, (Object)sit);
    }

    public EntityDimensions m_6972_(Pose poseIn) {
        return this.isFlying() && !this.m_6162_() ? FLIGHT_SIZE : super.m_6972_(poseIn);
    }

    public void m_8119_() {
        this.prevFlyAngle = this.getFlyAngle();
        super.m_8119_();
        this.prevAttackProgress = this.attackProgress;
        this.prevFlyProgress = this.flyProgress;
        this.prevSitProgress = this.sitProgress;
        this.prevDragProgress = this.dragProgress;
        this.prevDigProgress = this.digProgress;
        boolean flying = this.isFlying();
        boolean sitting = this.isSitting();
        boolean dragging = this.isDragging();
        boolean digging = this.isDigging();
        if (flying) {
            if (this.flyProgress < 5.0f) {
                this.flyProgress += 1.0f;
            }
        } else if (this.flyProgress > 0.0f) {
            this.flyProgress -= 1.0f;
        }
        if (sitting) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (dragging) {
            if (this.dragProgress < 5.0f) {
                this.dragProgress += 1.0f;
            }
        } else if (this.dragProgress > 0.0f) {
            this.dragProgress -= 1.0f;
        }
        if (digging) {
            if (this.digProgress < 5.0f) {
                this.digProgress += 1.0f;
            }
        } else if (this.digProgress > 0.0f) {
            this.digProgress -= 1.0f;
        }
        if (this.flightSize && !flying) {
            this.m_6210_();
            this.flightSize = false;
        }
        if (!this.flightSize && this.isFlying()) {
            this.m_6210_();
            this.flightSize = true;
        }
        float threshold = 0.015f;
        if (this.isFlying() && this.f_19859_ - this.m_146908_() > threshold) {
            this.setFlyAngle(this.getFlyAngle() + 5.0f);
        } else if (this.isFlying() && this.f_19859_ - this.m_146908_() < -threshold) {
            this.setFlyAngle(this.getFlyAngle() - 5.0f);
        } else if (this.getFlyAngle() > 0.0f) {
            this.setFlyAngle(Math.max(this.getFlyAngle() - 4.0f, 0.0f));
        } else if (this.getFlyAngle() < 0.0f) {
            this.setFlyAngle(Math.min(this.getFlyAngle() + 4.0f, 0.0f));
        }
        this.setFlyAngle(Mth.m_14036_((float)this.getFlyAngle(), (float)-30.0f, (float)30.0f));
        if (!this.m_9236_().f_46443_) {
            if (this.isFlying() && this.isLandNavigator) {
                this.switchNavigator(false);
            }
            if (!this.isFlying() && !this.isLandNavigator) {
                this.switchNavigator(true);
            }
            if (this.isFlying()) {
                if (this.timeFlying % 25 == 0) {
                    this.m_5496_((SoundEvent)AMSoundRegistry.TARANTULA_HAWK_WING.get(), this.m_6121_(), this.m_6100_());
                }
                ++this.timeFlying;
                this.m_20242_(true);
                if (this.isSitting() || this.m_20159_() || this.m_27593_()) {
                    this.setFlying(false);
                }
            } else {
                this.timeFlying = 0;
                this.m_20242_(false);
            }
            if (this.m_5448_() != null && this.m_5448_() instanceof Player && !this.m_21824_()) {
                this.f_19804_.m_135381_(ANGRY, (Object)true);
            } else {
                this.f_19804_.m_135381_(ANGRY, (Object)false);
            }
        }
        if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) > 0) {
            this.f_19804_.m_135381_(ATTACK_TICK, (Object)((Integer)this.f_19804_.m_135370_(ATTACK_TICK) - 1));
            if (this.attackProgress < 5.0f) {
                this.attackProgress += 1.0f;
            }
        } else if (this.attackProgress > 0.0f) {
            this.attackProgress -= 1.0f;
        }
        if (this.isDigging() && this.m_9236_().m_8055_(this.m_20099_()).m_60815_()) {
            BlockPos posit = this.m_20099_();
            BlockState understate = this.m_9236_().m_8055_(posit);
            for (int i = 0; i < 4 + this.f_19796_.m_188503_(2); ++i) {
                double particleX = (float)posit.m_123341_() + this.f_19796_.m_188501_();
                double particleY = (float)posit.m_123342_() + 1.0f;
                double particleZ = (float)posit.m_123343_() + this.f_19796_.m_188501_();
                double motX = this.f_19796_.m_188583_() * 0.02;
                double motY = 0.1f + this.f_19796_.m_188501_() * 0.2f;
                double motZ = this.f_19796_.m_188583_() * 0.02;
                this.m_9236_().m_7106_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123794_, understate), particleX, particleY, particleZ, motX, motY, motZ);
            }
        }
        if (this.f_19797_ > 0 && this.f_19797_ % 300 == 0 && this.m_21223_() < this.m_21233_()) {
            this.m_5634_(1.0f);
        }
        if (!this.m_9236_().f_46443_ && this.isDragging() && this.m_20197_().isEmpty() && !this.isDigging()) {
            ++this.dragTime;
            if (this.dragTime > 5000) {
                this.dragTime = 0;
                for (Entity e : this.m_20197_()) {
                    e.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 10.0f);
                }
                this.m_20153_();
                this.setDragging(false);
            }
        }
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        if (!this.m_21824_() && itemstack.m_204117_(AMTagRegistry.TARANTULA_HAWK_TAMEABLES)) {
            this.m_142075_(player, hand, itemstack);
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_12465_, this.m_6121_(), this.m_6100_());
            ++this.spiderFeedings;
            if (this.spiderFeedings >= 15 && this.m_217043_().m_188503_(6) == 0 || this.spiderFeedings > 25) {
                this.m_21828_(player);
                this.m_9236_().m_7605_((Entity)this, (byte)7);
            } else {
                this.m_9236_().m_7605_((Entity)this, (byte)6);
            }
            return InteractionResult.SUCCESS;
        }
        if (this.m_21824_() && itemstack.m_204117_(AMTagRegistry.TARANTULA_HAWK_FOODSTUFFS)) {
            if (this.m_21223_() < this.m_21233_()) {
                this.m_142075_(player, hand, itemstack);
                this.m_146850_(GameEvent.f_157806_);
                this.m_5496_(SoundEvents.f_12465_, this.m_6121_(), this.m_6100_());
                this.m_5634_(5.0f);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
        if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS && this.m_21824_() && this.m_21830_((LivingEntity)player)) {
            if (player.m_6144_()) {
                if (this.m_21205_().m_41619_()) {
                    ItemStack cop = itemstack.m_41777_();
                    cop.m_41764_(1);
                    this.m_21008_(InteractionHand.MAIN_HAND, cop);
                    itemstack.m_41774_(1);
                    return InteractionResult.SUCCESS;
                }
                this.m_19983_(this.m_21205_().m_41777_());
                this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
                return InteractionResult.SUCCESS;
            }
            if (!this.m_6898_(itemstack)) {
                boolean sit;
                this.setCommand(this.getCommand() + 1);
                if (this.getCommand() == 3) {
                    this.setCommand(0);
                }
                player.m_5661_((Component)Component.m_237110_((String)("entity.alexsmobs.all.command_" + this.getCommand()), (Object[])new Object[]{this.m_7755_()}), true);
                boolean bl = sit = this.getCommand() == 2;
                if (sit) {
                    this.m_21839_(true);
                    return InteractionResult.SUCCESS;
                }
                this.m_21839_(false);
                return InteractionResult.SUCCESS;
            }
        }
        return type;
    }

    public boolean m_6898_(ItemStack stack) {
        Item item = stack.m_41720_();
        return this.m_21824_() && stack.m_204117_(AMTagRegistry.TARANTULA_HAWK_BREEDABLES);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268585_) || super.m_6673_(source);
    }

    public void m_27563_(ServerLevel world, Animal animalEntity) {
        this.bredBuryFlag = true;
        ServerPlayer serverplayerentity = this.m_27592_();
        if (serverplayerentity == null && animalEntity.m_27592_() != null) {
            serverplayerentity = animalEntity.m_27592_();
        }
        if (serverplayerentity != null) {
            serverplayerentity.m_36220_(Stats.f_12937_);
            CriteriaTriggers.f_10581_.m_147278_(serverplayerentity, (Animal)this, animalEntity, (AgeableMob)this);
        }
        this.m_146762_(6000);
        animalEntity.m_146762_(6000);
        this.m_27594_();
        animalEntity.m_27594_();
        world.m_7605_((Entity)this, (byte)7);
        world.m_7605_((Entity)this, (byte)18);
        if (world.m_46469_().m_46207_(GameRules.f_46135_)) {
            world.m_7967_((Entity)new ExperienceOrb((Level)world, this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_217043_().m_188503_(7) + 1));
        }
    }

    @Override
    public void followEntity(TamableAnimal tameable, LivingEntity owner, double followSpeed) {
        if (this.m_20270_((Entity)owner) > 5.0f) {
            this.setFlying(true);
            this.m_21566_().m_6849_(owner.m_20185_(), owner.m_20186_() + (double)owner.m_20206_(), owner.m_20189_(), followSpeed);
        } else {
            if (this.m_20096_()) {
                this.setFlying(false);
            }
            if (this.isFlying() && !this.isOverWater()) {
                BlockPos vec = this.getCrowGround(this.m_20183_());
                if (vec != null) {
                    this.m_21566_().m_6849_((double)vec.m_123341_(), (double)vec.m_123342_(), (double)vec.m_123343_(), followSpeed);
                }
            } else {
                this.m_21573_().m_5624_((Entity)owner, followSpeed);
            }
        }
    }

    public void m_19956_(Entity passenger, Entity.MoveFunction moveFunc) {
        this.m_146926_(0.0f);
        float radius = 1.0f + passenger.m_20205_() * 0.5f;
        float angle = (float)Math.PI / 180 * (this.f_20883_ - 180.0f);
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        double extraY = 0.0;
        passenger.m_6034_(this.m_20185_() + extraX, this.m_20186_() + extraY, this.m_20189_() + extraZ);
    }

    private boolean isOverWater() {
        BlockPos position = this.m_20183_();
        while (position.m_123342_() > 0 && this.m_9236_().m_46859_(position)) {
            position = position.m_7495_();
        }
        return !this.m_9236_().m_6425_(position).m_76178_() || position.m_123342_() <= 0;
    }

    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
        float radius = -9.45f - (float)this.m_217043_().m_188503_(24) - radiusAdd;
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), 0, (int)(fleePos.m_7094_() + extraZ));
        BlockPos ground = this.getCrowGround(radialPos);
        int distFromGround = (int)this.m_20186_() - ground.m_123342_();
        int flightHeight = 4 + this.m_217043_().m_188503_(10);
        BlockPos newPos = ground.m_6630_(distFromGround > 8 ? flightHeight : this.m_217043_().m_188503_(6) + 1);
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 1.0) {
            return Vec3.m_82512_((Vec3i)newPos);
        }
        return null;
    }

    private BlockPos getCrowGround(BlockPos in) {
        BlockPos position = new BlockPos(in.m_123341_(), (int)this.m_20186_(), in.m_123343_());
        while (position.m_123342_() > -64 && !this.m_9236_().m_8055_(position).m_280296_() && this.m_9236_().m_6425_(position).m_76178_()) {
            position = position.m_7495_();
        }
        return position;
    }

    public Vec3 getBlockGrounding(Vec3 fleePos) {
        float radius = -9.45f - (float)this.m_217043_().m_188503_(24);
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), (int)this.m_20186_(), (int)(fleePos.m_7094_() + extraZ));
        BlockPos ground = this.getCrowGround(radialPos);
        if (ground.m_123342_() == -64) {
            return this.m_20182_();
        }
        ground = this.m_20183_();
        while (ground.m_123342_() > -62 && !this.m_9236_().m_8055_(ground).m_280296_()) {
            ground = ground.m_7495_();
        }
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)ground.m_7494_()))) {
            return Vec3.m_82512_((Vec3i)ground);
        }
        return null;
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    private Vec3 getOrbitVec(Vec3 vector3d, float gatheringCircleDist, boolean orbitClockwise) {
        float angle = (float)Math.PI / 90 * (float)(orbitClockwise ? -this.f_19797_ : this.f_19797_);
        double extraX = gatheringCircleDist * Mth.m_14031_((float)angle);
        double extraZ = gatheringCircleDist * Mth.m_14089_((float)angle);
        if (vector3d != null) {
            Vec3 pos = new Vec3(vector3d.m_7096_() + extraX, vector3d.m_7098_() + (double)this.f_19796_.m_188503_(2) + 4.0, vector3d.m_7094_() + extraZ);
            if (this.m_9236_().m_46859_(AMBlockPos.fromVec3(pos))) {
                return pos;
            }
        }
        return null;
    }

    public int getCommand() {
        return (Integer)this.f_19804_.m_135370_(COMMAND);
    }

    public void setCommand(int command) {
        this.f_19804_.m_135381_(COMMAND, (Object)command);
    }

    private BlockPos genSandPos(BlockPos parent) {
        Level world = this.m_9236_();
        Random random = new Random();
        int range = 24;
        for (int i = 0; i < 15; ++i) {
            BlockPos sandAir = parent.m_7918_(random.nextInt(range) - range / 2, -5, random.nextInt(range) - range / 2);
            while (!world.m_46859_(sandAir) && sandAir.m_123342_() < 255) {
                sandAir = sandAir.m_7494_();
            }
            BlockState state = world.m_8055_(sandAir.m_7495_());
            if (!state.m_204336_(BlockTags.f_13029_)) continue;
            return sandAir.m_7495_();
        }
        return null;
    }

    @Override
    public boolean shouldFollow() {
        return this.getCommand() == 1 && !this.isDragging() && !this.isDigging() && (this.m_5448_() == null || !this.m_5448_().m_6084_());
    }

    public boolean isAngry() {
        return (Boolean)this.f_19804_.m_135370_(ANGRY);
    }

    private class AIFleeRoadrunners
    extends Goal {
        private int searchCooldown = 0;
        private LivingEntity fear = null;
        private Vec3 fearVec = null;

        private AIFleeRoadrunners() {
        }

        public boolean m_8036_() {
            if (this.searchCooldown <= 0) {
                this.searchCooldown = 100 + EntityTarantulaHawk.this.f_19796_.m_188503_(100);
                List list = EntityTarantulaHawk.this.m_9236_().m_45976_(EntityRoadrunner.class, EntityTarantulaHawk.this.m_20191_().m_82377_(15.0, 32.0, 15.0));
                for (EntityRoadrunner roadrunner : list) {
                    if (this.fear != null && !(EntityTarantulaHawk.this.m_20270_((Entity)this.fear) > EntityTarantulaHawk.this.m_20270_((Entity)roadrunner))) continue;
                    this.fear = roadrunner;
                }
            } else {
                --this.searchCooldown;
            }
            return EntityTarantulaHawk.this.m_6084_() && this.fear != null;
        }

        public boolean m_8045_() {
            return this.fear != null && this.fear.m_6084_() && EntityTarantulaHawk.this.m_20270_((Entity)this.fear) < 32.0f;
        }

        public void m_8056_() {
            super.m_8056_();
            EntityTarantulaHawk.this.setScared(true);
        }

        public void m_8037_() {
            if (this.fear != null) {
                if (this.fearVec == null || EntityTarantulaHawk.this.m_20238_(this.fearVec) < 4.0) {
                    this.fearVec = EntityTarantulaHawk.this.getBlockInViewAway(this.fearVec == null ? this.fear.m_20182_() : this.fearVec, 12.0f);
                }
                if (this.fearVec != null) {
                    EntityTarantulaHawk.this.setFlying(true);
                    EntityTarantulaHawk.this.m_21566_().m_6849_(this.fearVec.f_82479_, this.fearVec.f_82480_, this.fearVec.f_82481_, (double)1.1f);
                }
            }
        }

        public void m_8041_() {
            EntityTarantulaHawk.this.setScared(false);
            this.fear = null;
            this.fearVec = null;
        }
    }

    private class AIMelee
    extends Goal {
        private final EntityTarantulaHawk hawk;
        private int orbitCooldown = 0;
        private boolean clockwise = false;
        private Vec3 orbitVec = null;
        private BlockPos sandPos = null;

        public AIMelee() {
            this.hawk = EntityTarantulaHawk.this;
        }

        public boolean m_8036_() {
            return this.hawk.m_5448_() != null && !this.hawk.isSitting() && !this.hawk.isScared() && this.hawk.m_5448_().m_6084_() && !this.hawk.isDragging() && !this.hawk.isDigging() && !this.hawk.m_5448_().f_19794_ && !this.hawk.m_5448_().m_20159_();
        }

        public void m_8056_() {
            this.hawk.setDragging(false);
            this.clockwise = EntityTarantulaHawk.this.f_19796_.m_188499_();
        }

        public void m_8037_() {
            boolean paralizedWithChild;
            LivingEntity target = this.hawk.m_5448_();
            boolean paralized = target != null && target.m_6336_() == MobType.f_21642_ && !target.f_19794_ && target.m_21023_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get());
            boolean bl = paralizedWithChild = paralized && target.m_21124_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get()).m_19564_() > 0;
            if (this.sandPos == null || !EntityTarantulaHawk.this.m_9236_().m_8055_(this.sandPos).m_204336_(BlockTags.f_13029_)) {
                this.sandPos = this.hawk.genSandPos(target.m_20183_());
            }
            if (this.orbitCooldown > 0) {
                --this.orbitCooldown;
                this.hawk.setFlying(true);
                if (target != null && (this.orbitVec == null || this.hawk.m_20238_(this.orbitVec) < 4.0 || !this.hawk.m_21566_().m_24995_())) {
                    this.orbitVec = this.hawk.getOrbitVec(target.m_20182_().m_82520_(0.0, (double)target.m_20206_(), 0.0), 10 + EntityTarantulaHawk.this.f_19796_.m_188503_(2), false);
                    if (this.orbitVec != null) {
                        this.hawk.m_21566_().m_6849_(this.orbitVec.f_82479_, this.orbitVec.f_82480_, this.orbitVec.f_82481_, 1.0);
                    }
                }
            } else if ((paralized && !this.hawk.m_21824_() || paralizedWithChild && this.hawk.bredBuryFlag) && this.sandPos != null) {
                if (this.hawk.m_20096_()) {
                    this.hawk.setFlying(false);
                    this.hawk.m_21573_().m_5624_((Entity)target, 1.0);
                } else {
                    Vec3 vector3d = this.hawk.getBlockGrounding(this.hawk.m_20182_());
                    if (vector3d != null && this.hawk.isFlying()) {
                        this.hawk.m_21566_().m_6849_(vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_, 1.0);
                    }
                }
                if (this.hawk.m_20270_((Entity)target) < target.m_20205_() + 1.5f && !target.m_20159_()) {
                    this.hawk.setDragging(true);
                    this.hawk.setFlying(false);
                    target.m_7998_((Entity)this.hawk, true);
                }
            } else if (target != null && !paralizedWithChild) {
                double dist = this.hawk.m_20270_((Entity)target);
                if (dist < 10.0 && !this.hawk.isFlying()) {
                    if (this.hawk.m_20096_()) {
                        this.hawk.setFlying(false);
                    }
                    this.hawk.m_21573_().m_5624_((Entity)target, 1.0);
                } else {
                    this.hawk.setFlying(true);
                    this.hawk.m_21566_().m_6849_(target.m_20185_(), target.m_20188_(), target.m_20189_(), 1.0);
                }
                if (dist < (double)(target.m_20205_() + 2.5f)) {
                    if ((Integer)this.hawk.f_19804_.m_135370_(ATTACK_TICK) == 0 && this.hawk.attackProgress == 0.0f) {
                        this.hawk.f_19804_.m_135381_(ATTACK_TICK, (Object)7);
                    }
                    if (this.hawk.attackProgress == 5.0f) {
                        this.hawk.m_7327_((Entity)target);
                        if (this.hawk.bredBuryFlag && target.m_21223_() <= 1.0f) {
                            target.m_5634_(5.0f);
                        }
                        target.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.DEBILITATING_STING.get(), target.m_6336_() == MobType.f_21642_ ? 2400 : 600, this.hawk.bredBuryFlag ? 1 : 0));
                        if (!this.hawk.m_9236_().f_46443_ && target.m_6336_() == MobType.f_21642_) {
                            AlexsMobs.sendMSGToAll(new MessageTarantulaHawkSting(this.hawk.m_19879_(), target.m_19879_()));
                        }
                        this.orbitCooldown = target.m_6336_() == MobType.f_21642_ ? 200 + EntityTarantulaHawk.this.f_19796_.m_188503_(200) : 10 + EntityTarantulaHawk.this.f_19796_.m_188503_(20);
                    }
                }
            }
        }

        public void m_8041_() {
            this.orbitCooldown = 0;
            this.hawk.bredBuryFlag = false;
            this.clockwise = EntityTarantulaHawk.this.f_19796_.m_188499_();
            this.orbitVec = null;
            if (this.hawk.m_20197_().isEmpty()) {
                this.hawk.m_6710_(null);
            }
        }
    }

    private class AIBury
    extends Goal {
        private final EntityTarantulaHawk hawk;
        private BlockPos buryPos = null;
        private int digTime = 0;
        private double stageX;
        private double stageY;
        private double stageZ;

        private AIBury() {
            this.hawk = EntityTarantulaHawk.this;
        }

        public boolean m_8036_() {
            BlockPos pos;
            if (this.hawk.isDragging() && this.hawk.m_5448_() != null && (pos = this.hawk.genSandPos(this.hawk.m_20183_())) != null) {
                this.buryPos = pos;
                return true;
            }
            return false;
        }

        public boolean m_8045_() {
            return this.hawk.isDragging() && this.digTime < 200 && this.hawk.m_5448_() != null && this.buryPos != null && EntityTarantulaHawk.this.m_9236_().m_8055_(this.buryPos).m_204336_(BlockTags.f_13029_);
        }

        public void m_8056_() {
            this.digTime = 0;
            this.stageX = this.hawk.m_20185_();
            this.stageY = this.hawk.m_20186_();
            this.stageZ = this.hawk.m_20189_();
        }

        public void m_8041_() {
            this.digTime = 0;
            this.hawk.setDigging(false);
            this.hawk.setDragging(false);
            this.hawk.m_6710_(null);
            this.hawk.m_6703_(null);
        }

        public void m_8037_() {
            this.hawk.setFlying(false);
            this.hawk.setDragging(true);
            LivingEntity target = this.hawk.m_5448_();
            if (this.hawk.m_20238_(Vec3.m_82512_((Vec3i)this.buryPos)) < 9.0 && !this.hawk.isDigging()) {
                this.hawk.setDigging(true);
                this.stageX = target.m_20185_();
                this.stageY = target.m_20186_();
                this.stageZ = target.m_20189_();
            }
            if (this.hawk.isDigging()) {
                target.f_19794_ = true;
                ++this.digTime;
                this.hawk.m_20153_();
                target.m_6034_(this.stageX, this.stageY - (double)Math.min(3.0f, (float)this.digTime * 0.05f), this.stageZ);
                this.hawk.m_21573_().m_26519_(this.stageX, this.stageY, this.stageZ, (double)0.85f);
            } else {
                this.hawk.m_21573_().m_26519_((double)this.buryPos.m_123341_(), (double)this.buryPos.m_123342_(), (double)this.buryPos.m_123343_(), 0.5);
            }
        }
    }

    private class AIWalkIdle
    extends Goal {
        protected final EntityTarantulaHawk hawk;
        protected double x;
        protected double y;
        protected double z;
        private boolean flightTarget = false;

        public AIWalkIdle() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.hawk = EntityTarantulaHawk.this;
        }

        public boolean m_8036_() {
            if (this.hawk.m_20160_() || this.hawk.isScared() || this.hawk.isDragging() || EntityTarantulaHawk.this.getCommand() == 1 || this.hawk.m_5448_() != null && this.hawk.m_5448_().m_6084_() || this.hawk.m_20159_() || this.hawk.isSitting()) {
                return false;
            }
            if (this.hawk.m_217043_().m_188503_(30) != 0 && !this.hawk.isFlying()) {
                return false;
            }
            this.flightTarget = this.hawk.m_20096_() ? EntityTarantulaHawk.this.f_19796_.m_188499_() : EntityTarantulaHawk.this.f_19796_.m_188503_(5) > 0 && this.hawk.timeFlying < 200;
            Vec3 lvt_1_1_ = this.getPosition();
            if (lvt_1_1_ == null) {
                return false;
            }
            this.x = lvt_1_1_.f_82479_;
            this.y = lvt_1_1_.f_82480_;
            this.z = lvt_1_1_.f_82481_;
            return true;
        }

        public void m_8037_() {
            if (this.flightTarget) {
                this.hawk.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.hawk.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
            if (!this.flightTarget && EntityTarantulaHawk.this.isFlying() && this.hawk.m_20096_()) {
                this.hawk.setFlying(false);
            }
            if (EntityTarantulaHawk.this.isFlying() && this.hawk.m_20096_() && this.hawk.timeFlying > 10) {
                this.hawk.setFlying(false);
            }
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vector3d = this.hawk.m_20182_();
            if (this.hawk.isOverWater()) {
                this.flightTarget = true;
            }
            if (this.flightTarget) {
                if (this.hawk.timeFlying < 50 || this.hawk.isOverWater()) {
                    return this.hawk.getBlockInViewAway(vector3d, 0.0f);
                }
                return this.hawk.getBlockGrounding(vector3d);
            }
            return LandRandomPos.m_148488_((PathfinderMob)this.hawk, (int)10, (int)7);
        }

        public boolean m_8045_() {
            if (this.hawk.isSitting() || EntityTarantulaHawk.this.getCommand() == 1) {
                return false;
            }
            if (this.flightTarget) {
                return this.hawk.isFlying() && this.hawk.m_20275_(this.x, this.y, this.z) > 2.0;
            }
            return !this.hawk.m_21573_().m_26571_() && !this.hawk.m_20160_();
        }

        public void m_8056_() {
            if (this.flightTarget) {
                this.hawk.setFlying(true);
                this.hawk.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.hawk.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
        }

        public void m_8041_() {
            this.hawk.m_21573_().m_26573_();
            super.m_8041_();
        }
    }

    class MoveController
    extends MoveControl {
        private final Mob parentEntity;

        public MoveController() {
            super((Mob)EntityTarantulaHawk.this);
            this.parentEntity = EntityTarantulaHawk.this;
        }

        public void m_8126_() {
            if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
                double width;
                Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
                double d0 = vector3d.m_82553_();
                if (d0 < (width = this.parentEntity.m_20191_().m_82309_())) {
                    this.f_24981_ = MoveControl.Operation.WAIT;
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82490_(0.5));
                } else {
                    float angle = (float)Math.PI / 180 * (this.parentEntity.f_20883_ + 90.0f);
                    float radius = (float)Math.sin((float)this.parentEntity.f_19797_ * 0.2f) * 2.0f;
                    double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
                    double extraZ = radius * Mth.m_14089_((float)angle);
                    Vec3 vector3d1 = vector3d.m_82490_(this.f_24978_ * 0.05 / d0);
                    Vec3 strafPlus = new Vec3(extraX, 0.0, extraZ).m_82490_(0.003 * Math.min(d0, 100.0));
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(strafPlus));
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d1));
                    this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
                    if (!EntityTarantulaHawk.this.isDragging()) {
                        this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                    }
                }
            }
        }
    }
}
