/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  com.github.alexthe666.citadel.server.entity.collision.ICustomCollisions
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
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.TimeUtil
 *  net.minecraft.util.valueproviders.UniformInt
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.NeutralMob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.PathFinder
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIPanicBaby;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.MovementControllerCustomCollisions;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.server.entity.collision.ICustomCollisions;
import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;
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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EntityTiger
extends Animal
implements ICustomCollisions,
IAnimatedEntity,
NeutralMob,
ITargetsDroppedItems {
    public static final Animation ANIMATION_PAW_R = Animation.create((int)15);
    public static final Animation ANIMATION_PAW_L = Animation.create((int)15);
    public static final Animation ANIMATION_TAIL_FLICK = Animation.create((int)45);
    public static final Animation ANIMATION_LEAP = Animation.create((int)20);
    private static final EntityDataAccessor<Boolean> WHITE = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> STEALTH_MODE = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HOLDING = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> LAST_SCARED_MOB_ID = SynchedEntityData.m_135353_(EntityTiger.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final UniformInt ANGRY_TIMER = TimeUtil.m_145020_((int)40, (int)80);
    private static final Predicate<LivingEntity> NO_BLESSING_EFFECT = mob -> !mob.m_21023_((MobEffect)AMEffectRegistry.TIGERS_BLESSING.get());
    public float prevSitProgress;
    public float sitProgress;
    public float prevSleepProgress;
    public float sleepProgress;
    public float prevHoldProgress;
    public float holdProgress;
    public float prevStealthProgress;
    public float stealthProgress;
    private int animationTick;
    private Animation currentAnimation;
    private boolean hasSpedUp = false;
    private UUID lastHurtBy;
    private int sittingTime;
    private int maxSitTime;
    private int holdTime = 0;
    private int prevScaredMobId = -1;
    private boolean dontSitFlag = false;

    protected EntityTiger(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
        this.f_21342_ = new MovementControllerCustomCollisions((Mob)this);
    }

    public static boolean canTigerSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return worldIn.m_45524_(pos, 0) > 8;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 50.0).m_22268_(Attributes.f_22281_, 12.0).m_22268_(Attributes.f_22279_, 0.25).m_22268_(Attributes.f_22277_, 86.0);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.tigerSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        return worldIn.m_6425_(pos.m_7495_()).m_76178_() && worldIn.m_6425_(pos).m_205070_(FluidTags.f_13131_) ? 0.0f : super.m_5610_(pos, worldIn);
    }

    public boolean m_6914_(LevelReader worldIn) {
        return !worldIn.m_46855_(this.m_20191_());
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("TigerSitting", this.isSitting());
        compound.m_128379_("TigerSleeping", this.m_5803_());
        compound.m_128379_("White", this.isWhite());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setSitting(compound.m_128471_("TigerSitting"));
        this.setSleeping(compound.m_128471_("TigerSleeping"));
        this.setWhite(compound.m_128471_("White"));
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(WHITE, (Object)false);
        this.f_19804_.m_135372_(RUNNING, (Object)false);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(STEALTH_MODE, (Object)false);
        this.f_19804_.m_135372_(HOLDING, (Object)false);
        this.f_19804_.m_135372_(SLEEPING, (Object)false);
        this.f_19804_.m_135372_(ANGER_TIME, (Object)0);
        this.f_19804_.m_135372_(LAST_SCARED_MOB_ID, (Object)-1);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(2, (Goal)new AnimalAIPanicBaby(this, 1.25));
        this.f_21345_.m_25352_(3, (Goal)new AIMelee());
        this.f_21345_.m_25352_(5, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(6, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(7, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 60, 1.0, 14, 7));
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 25.0f));
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, new CreatureAITargetItems((PathfinderMob)this, false, 10));
        this.f_21346_.m_25352_(2, (Goal)new AngerGoal(this));
        this.f_21346_.m_25352_(3, (Goal)new AttackPlayerGoal());
        this.f_21346_.m_25352_(4, (Goal)new NearestAttackableTargetGoal((Mob)this, LivingEntity.class, 220, false, false, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.TIGER_TARGETS)){

            public boolean m_8036_() {
                return !EntityTiger.this.m_6162_() && super.m_8036_();
            }
        });
        this.f_21346_.m_25352_(5, (Goal)new ResetUniversalAngerTargetGoal((Mob)this, true));
    }

    protected SoundEvent m_7515_() {
        return this.isStealth() ? super.m_7515_() : (this.m_6784_() > 0 ? (SoundEvent)AMSoundRegistry.TIGER_ANGRY.get() : (SoundEvent)AMSoundRegistry.TIGER_IDLE.get());
    }

    public int m_8100_() {
        return this.m_6784_() > 0 ? 40 : 80;
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.TIGER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.TIGER_HURT.get();
    }

    protected float m_6108_() {
        return 0.99f;
    }

    public boolean shouldMove() {
        return !this.isSitting() && !this.m_5803_() && !this.isHolding();
    }

    public double m_20968_(@Nullable Entity lookingEntity) {
        if (this.isStealth()) {
            return 0.2;
        }
        return super.m_20968_(lookingEntity);
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.TIGER_BREEDABLES);
    }

    public void awardKillScore(LivingEntity entity, int score, DamageSource src) {
        this.m_5634_(5.0f);
        super.m_5993_((Entity)entity, score, src);
    }

    public void m_7023_(Vec3 vec3d) {
        if (!this.shouldMove()) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            vec3d = Vec3.f_82478_;
        }
        super.m_7023_(vec3d);
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new Navigator((Mob)this, worldIn);
    }

    public boolean isWhite() {
        return (Boolean)this.f_19804_.m_135370_(WHITE);
    }

    public void setWhite(boolean white) {
        this.f_19804_.m_135381_(WHITE, (Object)white);
    }

    public boolean isRunning() {
        return (Boolean)this.f_19804_.m_135370_(RUNNING);
    }

    public void setRunning(boolean running) {
        this.f_19804_.m_135381_(RUNNING, (Object)running);
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public void setSitting(boolean bar) {
        this.f_19804_.m_135381_(SITTING, (Object)bar);
    }

    public boolean isStealth() {
        return (Boolean)this.f_19804_.m_135370_(STEALTH_MODE);
    }

    public void setStealth(boolean bar) {
        this.f_19804_.m_135381_(STEALTH_MODE, (Object)bar);
    }

    public boolean isHolding() {
        return (Boolean)this.f_19804_.m_135370_(HOLDING);
    }

    public void setHolding(boolean running) {
        this.f_19804_.m_135381_(HOLDING, (Object)running);
    }

    public boolean m_5803_() {
        return (Boolean)this.f_19804_.m_135370_(SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.f_19804_.m_135381_(SLEEPING, (Object)sleeping);
    }

    public int m_6784_() {
        return (Integer)this.f_19804_.m_135370_(ANGER_TIME);
    }

    public void m_7870_(int time) {
        this.f_19804_.m_135381_(ANGER_TIME, (Object)time);
    }

    public UUID m_6120_() {
        return this.lastHurtBy;
    }

    public void m_6925_(@Nullable UUID target) {
        this.lastHurtBy = target;
    }

    public void m_6825_() {
        this.m_7870_(ANGRY_TIMER.m_214085_(this.f_19796_));
    }

    protected void m_8024_() {
        if (!this.m_9236_().f_46443_) {
            this.m_21666_((ServerLevel)this.m_9236_(), false);
        }
    }

    public boolean m_20039_(BlockPos pos, BlockState blockstate) {
        return blockstate.m_60734_() != Blocks.f_50571_ && !blockstate.m_204336_(BlockTags.f_13035_) && super.m_20039_(pos, blockstate);
    }

    public Vec3 m_20272_(Vec3 vec3) {
        return ICustomCollisions.getAllowedMovementForEntity((Entity)this, (Vec3)vec3);
    }

    public void m_8119_() {
        Entity e;
        super.m_8119_();
        this.prevSitProgress = this.sitProgress;
        this.prevSleepProgress = this.sleepProgress;
        this.prevHoldProgress = this.holdProgress;
        this.prevStealthProgress = this.stealthProgress;
        boolean sitting = this.isSitting();
        boolean sleeping = this.m_5803_();
        boolean holding = this.isHolding();
        boolean stealth = this.isStealth();
        if (sitting) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (sleeping) {
            if (this.sleepProgress < 5.0f) {
                this.sleepProgress += 1.0f;
            }
        } else if (this.sleepProgress > 0.0f) {
            this.sleepProgress -= 1.0f;
        }
        if (holding) {
            if (this.holdProgress < 5.0f) {
                this.holdProgress += 1.0f;
            }
        } else if (this.holdProgress > 0.0f) {
            this.holdProgress -= 1.0f;
        }
        if (stealth) {
            if (this.stealthProgress < 10.0f) {
                this.stealthProgress += 0.25f;
            }
        } else if (this.stealthProgress > 0.0f) {
            this.stealthProgress -= 1.0f;
        }
        if (!this.m_9236_().f_46443_) {
            if (this.isRunning() && !this.hasSpedUp) {
                this.hasSpedUp = true;
                this.m_274367_(1.0f);
                this.m_6858_(true);
                this.m_21051_(Attributes.f_22279_).m_22100_((double)0.4f);
            }
            if (!this.isRunning() && this.hasSpedUp) {
                this.hasSpedUp = false;
                this.m_274367_(0.6f);
                this.m_6858_(false);
                this.m_21051_(Attributes.f_22279_).m_22100_(0.25);
            }
            if ((this.isSitting() || this.m_5803_()) && (++this.sittingTime > this.maxSitTime || this.m_5448_() != null || this.m_27593_() || this.dontSitFlag || this.m_20072_())) {
                this.setSitting(false);
                this.setSleeping(false);
                this.sittingTime = 0;
                this.maxSitTime = 100 + this.f_19796_.m_188503_(50);
            }
            if (!(this.m_5448_() != null || this.dontSitFlag || !(this.m_20184_().m_82556_() < 0.03) || this.getAnimation() != NO_ANIMATION || this.m_5803_() || this.isSitting() || this.m_20072_() || this.f_19796_.m_188503_(100) != 0)) {
                this.sittingTime = 0;
                if (this.m_217043_().m_188499_()) {
                    this.maxSitTime = 100 + this.f_19796_.m_188503_(550);
                    this.setSitting(true);
                    this.setSleeping(false);
                } else {
                    this.maxSitTime = 200 + this.f_19796_.m_188503_(550);
                    this.setSitting(false);
                    this.setSleeping(true);
                }
            }
            if (this.m_20184_().m_82556_() < 0.03 && this.getAnimation() == NO_ANIMATION && !this.m_5803_() && !this.isSitting() && this.f_19796_.m_188503_(100) == 0) {
                this.setAnimation(ANIMATION_TAIL_FLICK);
            }
        }
        if (this.isHolding()) {
            this.m_6858_(false);
            this.setRunning(false);
            LivingEntity target = this.m_5448_();
            if (!this.m_9236_().f_46443_ && target != null && target.m_6084_()) {
                this.m_146926_(0.0f);
                float radius = 1.0f + target.m_20205_() * 0.5f;
                float angle = (float)Math.PI / 180 * this.f_20883_;
                double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
                double extraZ = radius * Mth.m_14089_((float)angle);
                double extraY = -0.5;
                Vec3 minus = new Vec3(this.m_20185_() + extraX - target.m_20185_(), this.m_20186_() + -0.5 - target.m_20186_(), this.m_20189_() + extraZ - target.m_20189_());
                target.m_20256_(minus);
                target.f_19812_ = true;
                if (this.holdTime % 20 == 0) {
                    target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)(5 + this.m_217043_().m_188503_(2)));
                }
                if (target.m_20270_((Entity)this) > 8.0f) {
                    this.setHolding(false);
                    this.holdTime = 150;
                }
            }
            ++this.holdTime;
            if (this.holdTime > 100) {
                this.holdTime = 0;
                this.setHolding(false);
            }
        } else {
            this.holdTime = 0;
        }
        if (this.prevScaredMobId != (Integer)this.f_19804_.m_135370_(LAST_SCARED_MOB_ID) && this.m_9236_().f_46443_ && (e = this.m_9236_().m_6815_(((Integer)this.f_19804_.m_135370_(LAST_SCARED_MOB_ID)).intValue())) != null) {
            double d2 = this.f_19796_.m_188583_() * 0.1;
            double d0 = this.f_19796_.m_188583_() * 0.1;
            double d1 = this.f_19796_.m_188583_() * 0.1;
            this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.SHOCKED.get(), e.m_20185_(), e.m_20188_() + (double)(e.m_20206_() * 0.15f) + (double)(this.f_19796_.m_188501_() * e.m_20206_() * 0.15f), e.m_20189_(), d0, d1, d2);
        }
        if (this.m_5448_() != null && this.m_5448_().m_21023_((MobEffect)AMEffectRegistry.TIGERS_BLESSING.get())) {
            this.m_6710_(null);
            this.m_6703_(null);
        }
        this.prevScaredMobId = (Integer)this.f_19804_.m_135370_(LAST_SCARED_MOB_ID);
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev) {
            LivingEntity hurter;
            if (source.m_7639_() != null && source.m_7639_() instanceof LivingEntity && (hurter = (LivingEntity)source.m_7639_()).m_21023_((MobEffect)AMEffectRegistry.TIGERS_BLESSING.get())) {
                hurter.m_21195_((MobEffect)AMEffectRegistry.TIGERS_BLESSING.get());
            }
            return prev;
        }
        return prev;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public BlockPos getLightPosition() {
        BlockPos pos = AMBlockPos.fromVec3(this.m_20182_());
        if (!this.m_9236_().m_8055_(pos).m_60815_()) {
            return pos.m_7494_();
        }
        return pos;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        boolean whiteOther = p_241840_2_ instanceof EntityTiger && ((EntityTiger)p_241840_2_).isWhite();
        EntityTiger baby = (EntityTiger)((EntityType)AMEntityRegistry.TIGER.get()).m_20615_((Level)p_241840_1_);
        double whiteChance = 0.1;
        if (this.isWhite() && whiteOther) {
            whiteChance = 0.8;
        }
        if (this.isWhite() != whiteOther) {
            whiteChance = 0.4;
        }
        baby.setWhite(this.f_19796_.m_188500_() < whiteChance);
        return baby;
    }

    public boolean canPassThrough(BlockPos mutablePos, BlockState blockstate, VoxelShape voxelshape) {
        return blockstate.m_60734_() == Blocks.f_50571_ || blockstate.m_204336_(BlockTags.f_13035_);
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_PAW_R, ANIMATION_PAW_L, ANIMATION_LEAP, ANIMATION_TAIL_FLICK};
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    public void m_7334_(Entity entityIn) {
        if (!this.isHolding() || entityIn != this.m_5448_()) {
            super.m_7334_(entityIn);
        }
    }

    protected void m_7324_(Entity entityIn) {
        if (!this.isHolding() || entityIn != this.m_5448_()) {
            super.m_7324_(entityIn);
        }
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return stack.m_41720_().m_41472_() && stack.m_41720_().m_41473_() != null && stack.m_41720_().m_41473_().m_38746_() && stack.m_41720_() != Items.f_42583_;
    }

    @Override
    public double getMaxDistToItem() {
        return 3.0;
    }

    @Override
    public void onGetItem(ItemEntity e) {
        this.dontSitFlag = false;
        ItemStack stack = e.m_32055_();
        if (stack.m_41720_().m_41472_() && stack.m_41720_().m_41473_() != null && stack.m_41720_().m_41473_().m_38746_() && stack.m_41720_() != Items.f_42583_) {
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_11788_, this.m_6100_(), this.m_6121_());
            this.m_5634_(5.0f);
            Entity thrower = e.m_19749_();
            if (thrower != null && (double)this.f_19796_.m_188501_() < this.getChanceForEffect(stack) && this.m_9236_().m_46003_(thrower.m_20148_()) != null) {
                Player player = this.m_9236_().m_46003_(thrower.m_20148_());
                player.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.TIGERS_BLESSING.get(), 12000));
                this.m_6710_(null);
                this.m_6703_(null);
            }
        }
    }

    @Override
    public void onFindTarget(ItemEntity e) {
        this.dontSitFlag = true;
        this.setSitting(false);
        this.setSleeping(false);
    }

    public double getChanceForEffect(ItemStack stack) {
        if (stack.m_41720_() == Items.f_42485_ || stack.m_41720_() == Items.f_42486_) {
            return 0.4f;
        }
        if (stack.m_41720_() == Items.f_42581_ || stack.m_41720_() == Items.f_42582_) {
            return 0.3f;
        }
        return 0.1f;
    }

    protected void m_6135_() {
        if (!this.m_5803_() && !this.isSitting()) {
            super.m_6135_();
        }
    }

    private class AIMelee
    extends Goal {
        private final EntityTiger tiger;
        private int jumpAttemptCooldown = 0;

        public AIMelee() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            this.tiger = EntityTiger.this;
        }

        public boolean m_8036_() {
            return this.tiger.m_5448_() != null && this.tiger.m_5448_().m_6084_();
        }

        public void m_8037_() {
            LivingEntity target;
            if (this.jumpAttemptCooldown > 0) {
                --this.jumpAttemptCooldown;
            }
            if ((target = this.tiger.m_5448_()) != null && target.m_6084_()) {
                double dist = this.tiger.m_20270_((Entity)target);
                if (dist < 10.0 && this.tiger.m_21188_() != null && this.tiger.m_21188_().m_6084_()) {
                    this.tiger.setStealth(false);
                } else if (dist > 20.0) {
                    this.tiger.setRunning(false);
                    this.tiger.setStealth(true);
                }
                if (dist <= 20.0) {
                    this.tiger.setStealth(false);
                    this.tiger.setRunning(true);
                    if (((Integer)this.tiger.f_19804_.m_135370_(LAST_SCARED_MOB_ID)).intValue() != target.m_19879_()) {
                        this.tiger.f_19804_.m_135381_(LAST_SCARED_MOB_ID, (Object)target.m_19879_());
                        target.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.FEAR.get(), 100, 0, true, false));
                    }
                }
                if (dist < 12.0 && this.tiger.getAnimation() == IAnimatedEntity.NO_ANIMATION && this.tiger.m_20096_() && this.jumpAttemptCooldown == 0 && !this.tiger.isHolding()) {
                    this.tiger.setAnimation(ANIMATION_LEAP);
                    this.jumpAttemptCooldown = 70;
                }
                if ((this.jumpAttemptCooldown > 0 || this.tiger.m_20072_()) && !this.tiger.isHolding() && this.tiger.getAnimation() == IAnimatedEntity.NO_ANIMATION && dist < (double)(4.0f + target.m_20205_())) {
                    this.tiger.setAnimation(this.tiger.m_217043_().m_188499_() ? ANIMATION_PAW_L : ANIMATION_PAW_R);
                }
                if (dist < (double)(4.0f + target.m_20205_()) && (this.tiger.getAnimation() == ANIMATION_PAW_L || this.tiger.getAnimation() == ANIMATION_PAW_R) && this.tiger.getAnimationTick() == 8) {
                    target.m_6469_(this.tiger.m_269291_().m_269333_((LivingEntity)this.tiger), (float)(7 + this.tiger.m_217043_().m_188503_(5)));
                }
                if (this.tiger.getAnimation() == ANIMATION_LEAP) {
                    this.tiger.m_21573_().m_26573_();
                    Vec3 vec = target.m_20182_().m_82546_(this.tiger.m_20182_());
                    this.tiger.m_146922_(-((float)Mth.m_14136_((double)vec.f_82479_, (double)vec.f_82481_)) * 57.295776f);
                    this.tiger.f_20883_ = this.tiger.m_146908_();
                    if (this.tiger.getAnimationTick() >= 5 && this.tiger.getAnimationTick() < 11 && this.tiger.m_20096_()) {
                        Vec3 vector3d1 = new Vec3(target.m_20185_() - this.tiger.m_20185_(), 0.0, target.m_20189_() - this.tiger.m_20189_());
                        if (vector3d1.m_82556_() > 1.0E-7) {
                            vector3d1 = vector3d1.m_82541_().m_82490_(Math.min(dist, 15.0) * (double)0.2f);
                        }
                        this.tiger.m_20334_(vector3d1.f_82479_, vector3d1.f_82480_ + (double)0.3f + (double)0.1f * Mth.m_14008_((double)(target.m_20188_() - this.tiger.m_20186_()), (double)0.0, (double)2.0), vector3d1.f_82481_);
                    }
                    if (dist < (double)(target.m_20205_() + 3.0f) && this.tiger.getAnimationTick() >= 15) {
                        target.m_6469_(this.tiger.m_269291_().m_269333_((LivingEntity)this.tiger), 2.0f);
                        this.tiger.setRunning(false);
                        this.tiger.setStealth(false);
                        this.tiger.setHolding(true);
                    }
                } else if (target != null) {
                    this.tiger.m_21573_().m_5624_((Entity)target, this.tiger.isStealth() ? 0.75 : 1.0);
                }
            }
        }

        public void m_8041_() {
            this.tiger.setStealth(false);
            this.tiger.setRunning(false);
            this.tiger.setHolding(false);
        }
    }

    class AngerGoal
    extends HurtByTargetGoal {
        AngerGoal(EntityTiger beeIn) {
            super((PathfinderMob)beeIn, new Class[0]);
        }

        public boolean m_8045_() {
            return EntityTiger.this.m_21660_() && super.m_8045_();
        }

        public void m_8056_() {
            super.m_8056_();
            if (EntityTiger.this.m_6162_()) {
                this.m_26047_();
                this.m_8041_();
            }
        }

        protected void m_5766_(Mob mobIn, LivingEntity targetIn) {
            if (!mobIn.m_6162_()) {
                super.m_5766_(mobIn, targetIn);
            }
        }
    }

    class AttackPlayerGoal
    extends NearestAttackableTargetGoal<Player> {
        public AttackPlayerGoal() {
            super((Mob)EntityTiger.this, Player.class, 100, false, true, NO_BLESSING_EFFECT);
        }

        public boolean m_8036_() {
            if (EntityTiger.this.m_6162_()) {
                return false;
            }
            return super.m_8036_();
        }

        protected double m_7623_() {
            return 4.0;
        }
    }

    static class Navigator
    extends GroundPathNavigatorWide {
        public Navigator(Mob mob, Level world) {
            super(mob, world, 1.2f);
        }

        protected PathFinder m_5532_(int i) {
            this.f_26508_ = new TigerNodeEvaluator();
            return new PathFinder(this.f_26508_, i);
        }
    }

    static class TigerNodeEvaluator
    extends WalkNodeEvaluator {
        TigerNodeEvaluator() {
        }

        protected BlockPathTypes m_264405_(BlockGetter level, BlockPos pos, BlockPathTypes typeIn) {
            return typeIn == BlockPathTypes.LEAVES || level.m_8055_(pos).m_60734_() == Blocks.f_50571_ ? BlockPathTypes.OPEN : super.m_264405_(level, pos, typeIn);
        }
    }
}
