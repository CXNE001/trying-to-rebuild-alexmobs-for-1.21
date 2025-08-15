/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.NeutralMob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.FlyingAnimal
 *  net.minecraft.world.entity.monster.EnderMan
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
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
import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityEndergrade;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.message.MessageMosquitoDismount;
import com.github.alexthe666.alexsmobs.message.MessageMosquitoMountPlayer;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.EnumSet;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityEnderiophage
extends Animal
implements Enemy,
FlyingAnimal {
    private static final EntityDataAccessor<Float> PHAGE_PITCH = SynchedEntityData.m_135353_(EntityEnderiophage.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityEnderiophage.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> MISSING_EYE = SynchedEntityData.m_135353_(EntityEnderiophage.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> PHAGE_SCALE = SynchedEntityData.m_135353_(EntityEnderiophage.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityEnderiophage.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final Predicate<LivingEntity> ENDERGRADE_OR_INFECTED = entity -> entity instanceof EntityEndergrade || entity.m_21023_((MobEffect)AMEffectRegistry.ENDER_FLU.get());
    public float prevPhagePitch;
    public float tentacleAngle;
    public float lastTentacleAngle;
    public float phageRotation;
    public float prevFlyProgress;
    public float flyProgress;
    public int passengerIndex = 0;
    public float prevEnderiophageScale = 1.0f;
    private float rotationVelocity = 1.0f / (this.f_19796_.m_188501_() + 1.0f) * 0.2f;
    private int slowDownTicks = 0;
    private float randomMotionSpeed;
    private boolean isLandNavigator;
    private int timeFlying = 0;
    private int fleeAfterStealTime = 0;
    private int attachTime = 0;
    private int dismountCooldown = 0;
    private int squishCooldown = 0;
    private PathfinderMob angryEnderman = null;

    protected EntityEnderiophage(EntityType type, Level world) {
        super(type, world);
        this.switchNavigator(false);
        this.f_21364_ = 5;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 20.0).m_22268_(Attributes.f_22277_, 16.0).m_22268_(Attributes.f_22279_, (double)0.15f).m_22268_(Attributes.f_22281_, 2.0);
    }

    public static boolean canEnderiophageSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return true;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.enderiophageSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    private void doInitialPosing(LevelAccessor world) {
        BlockPos down = this.getPhageGround(this.m_20183_());
        this.m_6034_((float)down.m_123341_() + 0.5f, down.m_123342_() + 1, (float)down.m_123343_() + 0.5f);
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (reason == MobSpawnType.NATURAL) {
            this.doInitialPosing((LevelAccessor)worldIn);
        }
        this.setSkinForDimension();
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public int m_5792_() {
        return 2;
    }

    public float getPhageScale() {
        return ((Float)this.f_19804_.m_135370_(PHAGE_SCALE)).floatValue();
    }

    public void setPhageScale(float scale) {
        this.f_19804_.m_135381_(PHAGE_SCALE, (Object)Float.valueOf(scale));
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int variant) {
        this.f_19804_.m_135381_(VARIANT, (Object)variant);
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new FlyTowardsTarget(this));
        this.f_21345_.m_25352_(2, (Goal)new AIWalkIdle());
        this.f_21346_.m_25352_(1, (Goal)new EntityAINearestTarget3D((Mob)this, EnderMan.class, 15, true, true, null){

            public boolean m_8036_() {
                return EntityEnderiophage.this.isMissingEye() && super.m_8036_();
            }

            public boolean m_8045_() {
                return EntityEnderiophage.this.isMissingEye() && super.m_8045_();
            }
        });
        this.f_21346_.m_25352_(1, (Goal)new EntityAINearestTarget3D((Mob)this, LivingEntity.class, 15, true, true, ENDERGRADE_OR_INFECTED){

            public boolean m_8036_() {
                return !EntityEnderiophage.this.isMissingEye() && EntityEnderiophage.this.fleeAfterStealTime == 0 && super.m_8036_();
            }

            public boolean m_8045_() {
                return !EntityEnderiophage.this.isMissingEye() && super.m_8045_();
            }
        });
        this.f_21346_.m_25352_(3, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[]{EnderMan.class}));
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigatorWide((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new FlightMoveController((Mob)this, 1.0f, false, true);
            this.f_21344_ = new DirectPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(VARIANT, (Object)0);
        this.f_19804_.m_135372_(PHAGE_PITCH, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(PHAGE_SCALE, (Object)Float.valueOf(1.0f));
        this.f_19804_.m_135372_(FLYING, (Object)false);
        this.f_19804_.m_135372_(MISSING_EYE, (Object)false);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean isInOverworld() {
        return this.m_9236_().m_46472_() == Level.f_46428_ && !this.m_21525_();
    }

    public boolean isInNether() {
        return this.m_9236_().m_46472_() == Level.f_46429_ && !this.m_21525_();
    }

    public void setStandardFleeTime() {
        this.fleeAfterStealTime = 20;
    }

    public void m_6083_() {
        Entity entity = this.m_20202_();
        if (this.m_20159_() && !entity.m_6084_()) {
            this.m_8127_();
        } else {
            this.m_20334_(0.0, 0.0, 0.0);
            this.m_8119_();
            if (this.m_20159_()) {
                ++this.attachTime;
                Entity mount = this.m_20202_();
                if (mount instanceof LivingEntity) {
                    this.passengerIndex = mount.m_20197_().indexOf((Object)this);
                    this.f_20883_ = ((LivingEntity)mount).f_20883_;
                    this.m_146922_(((LivingEntity)mount).m_146908_());
                    this.f_20885_ = ((LivingEntity)mount).f_20885_;
                    this.f_19859_ = ((LivingEntity)mount).f_20885_;
                    float radius = mount.m_20205_();
                    float angle = (float)Math.PI / 180 * (((LivingEntity)mount).f_20883_ + (float)this.passengerIndex * 90.0f);
                    double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
                    double extraZ = radius * Mth.m_14089_((float)angle);
                    this.m_6034_(mount.m_20185_() + extraX, Math.max(mount.m_20186_() + (double)(mount.m_20192_() * 0.25f), mount.m_20186_()), mount.m_20189_() + extraZ);
                    if (!mount.m_6084_() || mount instanceof Player && ((Player)mount).m_7500_()) {
                        this.m_6038_();
                    }
                    this.setPhagePitch(0.0f);
                    if (!this.m_9236_().f_46443_ && this.attachTime > 15) {
                        LivingEntity target = (LivingEntity)mount;
                        float dmg = 1.0f;
                        if (target.m_21223_() > target.m_21233_() * 0.2f) {
                            dmg = 6.0f;
                        }
                        if (((double)target.m_21223_() < 1.5 || mount.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), dmg)) && mount instanceof LivingEntity) {
                            this.dismountCooldown = 100;
                            if (mount instanceof EnderMan) {
                                this.setMissingEye(false);
                                this.m_146850_(GameEvent.f_157806_);
                                this.m_5496_(SoundEvents.f_11897_, this.m_6121_(), this.m_6100_());
                                this.m_5634_(5.0f);
                                ((EnderMan)mount).m_7292_(new MobEffectInstance(MobEffects.f_19610_, 400));
                                this.fleeAfterStealTime = 400;
                                this.setFlying(true);
                                this.angryEnderman = (PathfinderMob)mount;
                            } else if (this.f_19796_.m_188503_(3) == 0) {
                                if (!this.isMissingEye()) {
                                    if (target.m_21124_((MobEffect)AMEffectRegistry.ENDER_FLU.get()) == null) {
                                        target.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.ENDER_FLU.get(), 12000));
                                    } else {
                                        MobEffectInstance inst = target.m_21124_((MobEffect)AMEffectRegistry.ENDER_FLU.get());
                                        int duration = 12000;
                                        int level = 0;
                                        if (inst != null) {
                                            duration = inst.m_19557_();
                                            level = inst.m_19564_();
                                        }
                                        target.m_21195_((MobEffect)AMEffectRegistry.ENDER_FLU.get());
                                        target.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.ENDER_FLU.get(), duration, Math.min(level + 1, 4)));
                                    }
                                    this.m_5634_(5.0f);
                                    this.m_146850_(GameEvent.f_223709_);
                                    this.m_5496_(SoundEvents.f_12018_, this.m_6121_(), this.m_6100_());
                                    this.setMissingEye(true);
                                }
                                if (!this.m_9236_().f_46443_) {
                                    this.m_6710_(null);
                                    this.m_21335_(null);
                                    this.m_6703_(null);
                                    this.f_21345_.m_25386_().forEach(Goal::m_8041_);
                                    this.f_21346_.m_25386_().forEach(Goal::m_8041_);
                                }
                            }
                        }
                        if (((LivingEntity)mount).m_21223_() <= 0.0f || this.fleeAfterStealTime > 0 || this.isMissingEye() && !(mount instanceof EnderMan) || !this.isMissingEye() && mount instanceof EnderMan) {
                            this.m_6038_();
                            this.m_6710_(null);
                            this.dismountCooldown = 100;
                            AlexsMobs.sendMSGToAll(new MessageMosquitoDismount(this.m_19879_(), mount.m_19879_()));
                            this.setFlying(true);
                        }
                    }
                }
            }
        }
    }

    public boolean canRiderInteract() {
        return true;
    }

    public void onSpawnFromEffect() {
        this.prevEnderiophageScale = 0.2f;
        this.setPhageScale(0.2f);
    }

    public void setSkinForDimension() {
        if (this.isInNether()) {
            this.setVariant(2);
        } else if (this.isInOverworld()) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.ENDERIOPHAGE_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.ENDERIOPHAGE_HURT.get();
    }

    protected void m_7355_(BlockPos pos, BlockState state) {
        this.m_5496_((SoundEvent)AMSoundRegistry.ENDERIOPHAGE_WALK.get(), 0.4f, 1.0f);
    }

    protected float m_6059_() {
        return this.f_19788_ + 0.3f;
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevEnderiophageScale = this.getPhageScale();
        float extraMotionSlow = 1.0f;
        float extraMotionSlowY = 1.0f;
        if (this.slowDownTicks > 0) {
            --this.slowDownTicks;
            extraMotionSlow = 0.33f;
            extraMotionSlowY = 0.1f;
        }
        if (this.dismountCooldown > 0) {
            --this.dismountCooldown;
        }
        if (this.squishCooldown > 0) {
            --this.squishCooldown;
        }
        if (!this.m_9236_().f_46443_) {
            if (!this.m_20159_() && this.attachTime != 0) {
                this.attachTime = 0;
            }
            if (this.fleeAfterStealTime > 0) {
                if (this.angryEnderman != null) {
                    Vec3 vec = this.getBlockInViewAway(this.angryEnderman.m_20182_(), 10.0f);
                    if (this.fleeAfterStealTime < 5) {
                        if (this.angryEnderman instanceof NeutralMob) {
                            ((NeutralMob)this.angryEnderman).m_21662_();
                        }
                        try {
                            this.angryEnderman.f_21345_.m_25386_().forEach(Goal::m_8041_);
                            this.angryEnderman.f_21346_.m_25386_().forEach(Goal::m_8041_);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.angryEnderman = null;
                    }
                    if (vec != null) {
                        this.setFlying(true);
                        this.m_21566_().m_6849_(vec.f_82479_, vec.f_82480_, vec.f_82481_, (double)1.3f);
                    }
                }
                --this.fleeAfterStealTime;
            }
        }
        this.f_20883_ = this.m_146908_();
        this.f_20885_ = this.m_146908_();
        this.setPhagePitch(-90.0f);
        if (this.m_6084_() && this.m_29443_() && this.randomMotionSpeed > 0.75f && this.m_20184_().m_82556_() > 0.02 && this.m_9236_().f_46443_) {
            float pitch = -this.getPhagePitch() / 90.0f;
            float radius = this.m_20205_() * 0.2f * -pitch;
            float angle = (float)Math.PI / 180 * this.m_146908_();
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraY = 0.2f - (1.0f - pitch) * 0.15f;
            double extraZ = radius * Mth.m_14089_((float)angle);
            double motX = extraX * 8.0 + this.f_19796_.m_188583_() * (double)0.05f;
            double motY = -0.1f;
            double motZ = extraZ + this.f_19796_.m_188583_() * (double)0.05f;
            this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.DNA.get(), this.m_20185_() + extraX, this.m_20186_() + extraY, this.m_20189_() + extraZ, motX, motY, motZ);
        }
        this.prevPhagePitch = this.getPhagePitch();
        this.prevFlyProgress = this.flyProgress;
        if (this.m_29443_()) {
            if (this.flyProgress < 5.0f) {
                this.flyProgress += 1.0f;
            }
        } else if (this.flyProgress > 0.0f) {
            this.flyProgress -= 1.0f;
        }
        this.lastTentacleAngle = this.tentacleAngle;
        this.phageRotation += this.rotationVelocity;
        if ((double)this.phageRotation > Math.PI * 2) {
            if (this.m_9236_().f_46443_) {
                this.phageRotation = (float)Math.PI * 2;
            } else {
                this.phageRotation = (float)((double)this.phageRotation - Math.PI * 2);
                if (this.f_19796_.m_188503_(10) == 0) {
                    this.rotationVelocity = 1.0f / (this.f_19796_.m_188501_() + 1.0f) * 0.2f;
                }
                this.m_9236_().m_7605_((Entity)this, (byte)19);
            }
        }
        if (this.phageRotation < (float)Math.PI) {
            float f = this.phageRotation / (float)Math.PI;
            this.tentacleAngle = Mth.m_14031_((float)(f * f * (float)Math.PI)) * 4.275f;
            if ((double)f > 0.75) {
                if (this.squishCooldown == 0 && this.m_29443_()) {
                    this.squishCooldown = 20;
                    this.m_5496_((SoundEvent)AMSoundRegistry.ENDERIOPHAGE_SQUISH.get(), 3.0f, this.m_6100_());
                }
                this.randomMotionSpeed = 1.0f;
            } else {
                this.randomMotionSpeed = 0.01f;
            }
        }
        if (!this.m_9236_().f_46443_) {
            if (this.m_29443_() && this.isLandNavigator) {
                this.switchNavigator(false);
            }
            if (!this.m_29443_() && !this.isLandNavigator) {
                this.switchNavigator(true);
            }
            if (this.m_29443_()) {
                this.m_20334_(this.m_20184_().f_82479_ * (double)this.randomMotionSpeed * (double)extraMotionSlow, this.m_20184_().f_82480_ * (double)this.randomMotionSpeed * (double)extraMotionSlowY, this.m_20184_().f_82481_ * (double)this.randomMotionSpeed * (double)extraMotionSlow);
                ++this.timeFlying;
                if (this.m_20096_() && this.timeFlying > 100) {
                    this.setFlying(false);
                }
            } else {
                this.timeFlying = 0;
            }
            if (this.isMissingEye() && this.m_5448_() != null && !(this.m_5448_() instanceof EnderMan)) {
                this.m_6710_(null);
            }
        }
        if (!this.m_20096_() && this.m_20184_().f_82480_ < 0.0) {
            this.m_20256_(this.m_20184_().m_82542_(1.0, 0.6, 1.0));
        }
        if (this.m_29443_()) {
            float phageDist = -((float)((Math.abs(this.m_20184_().m_7096_()) + Math.abs(this.m_20184_().m_7094_())) * 6.0));
            this.incrementPhagePitch(phageDist * 1.0f);
            this.setPhagePitch(Mth.m_14036_((float)this.getPhagePitch(), (float)-90.0f, (float)10.0f));
            float plateau = 2.0f;
            if (this.getPhagePitch() > plateau) {
                this.decrementPhagePitch(phageDist * Math.abs(this.getPhagePitch()) / 90.0f);
            }
            if (this.getPhagePitch() < -plateau) {
                this.incrementPhagePitch(phageDist * Math.abs(this.getPhagePitch()) / 90.0f);
            }
            if (this.getPhagePitch() > 2.0f) {
                this.decrementPhagePitch(1.0f);
            } else if (this.getPhagePitch() < -2.0f) {
                this.incrementPhagePitch(1.0f);
            }
            if (this.f_19862_) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, (double)0.2f, 0.0));
            }
        } else {
            if (this.getPhagePitch() > 0.0f) {
                float decrease = Math.min(2.0f, this.getPhagePitch());
                this.decrementPhagePitch(decrease);
            }
            if (this.getPhagePitch() < 0.0f) {
                float decrease = Math.min(2.0f, -this.getPhagePitch());
                this.incrementPhagePitch(decrease);
            }
        }
        if (this.getPhageScale() < 1.0f) {
            this.setPhageScale(this.getPhageScale() + 0.05f);
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Flying", this.m_29443_());
        compound.m_128379_("MissingEye", this.isMissingEye());
        compound.m_128405_("Variant", this.getVariant());
        compound.m_128405_("SlowDownTicks", this.slowDownTicks);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setFlying(compound.m_128471_("Flying"));
        this.setMissingEye(compound.m_128471_("MissingEye"));
        this.setVariant(compound.m_128451_("Variant"));
        this.slowDownTicks = compound.m_128451_("SlowDownTicks");
    }

    public boolean isMissingEye() {
        return (Boolean)this.f_19804_.m_135370_(MISSING_EYE);
    }

    public void setMissingEye(boolean missingEye) {
        this.f_19804_.m_135381_(MISSING_EYE, (Object)missingEye);
    }

    public boolean m_29443_() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    public void setFlying(boolean flying) {
        this.f_19804_.m_135381_(FLYING, (Object)flying);
    }

    public float getPhagePitch() {
        return ((Float)this.f_19804_.m_135370_(PHAGE_PITCH)).floatValue();
    }

    public void setPhagePitch(float pitch) {
        this.f_19804_.m_135381_(PHAGE_PITCH, (Object)Float.valueOf(pitch));
    }

    public void incrementPhagePitch(float pitch) {
        this.f_19804_.m_135381_(PHAGE_PITCH, (Object)Float.valueOf(this.getPhagePitch() + pitch));
    }

    public void decrementPhagePitch(float pitch) {
        this.f_19804_.m_135381_(PHAGE_PITCH, (Object)Float.valueOf(this.getPhagePitch() - pitch));
    }

    protected float m_6431_(Pose poseIn, EntityDimensions sizeIn) {
        return 1.8f;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return null;
    }

    private boolean isOverWaterOrVoid() {
        BlockPos position = this.m_20183_();
        while (position.m_123342_() > -63 && !this.m_9236_().m_8055_(position).m_280296_()) {
            position = position.m_7495_();
        }
        return !this.m_9236_().m_6425_(position).m_76178_() || position.m_123342_() < -63;
    }

    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
        float radius = -9.45f - (float)this.m_217043_().m_188503_(24) - radiusAdd;
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), 0, (int)(fleePos.m_7094_() + extraZ));
        BlockPos ground = this.getPhageGround(radialPos);
        int distFromGround = (int)this.m_20186_() - ground.m_123342_();
        int flightHeight = 6 + this.m_217043_().m_188503_(10);
        BlockPos newPos = ground.m_6630_(distFromGround > 8 || this.fleeAfterStealTime > 0 ? flightHeight : this.m_217043_().m_188503_(6) + 5);
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 1.0) {
            return Vec3.m_82512_((Vec3i)newPos);
        }
        return null;
    }

    private BlockPos getPhageGround(BlockPos in) {
        BlockPos position = new BlockPos(in.m_123341_(), (int)this.m_20186_(), in.m_123343_());
        while (position.m_123342_() > -63 && !this.m_9236_().m_8055_(position).m_280296_()) {
            position = position.m_7495_();
        }
        if (position.m_123342_() < -62) {
            return position.m_6630_(120 + this.f_19796_.m_188503_(5));
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
        BlockPos radialPos = AMBlockPos.fromCoords(fleePos.m_7096_() + extraX, this.m_20186_(), fleePos.m_7094_() + extraZ);
        BlockPos ground = this.getPhageGround(radialPos);
        if (ground.m_123342_() <= -63) {
            return Vec3.m_82514_((Vec3i)ground, (double)(110 + this.f_19796_.m_188503_(20)));
        }
        ground = this.m_20183_();
        while (ground.m_123342_() > -63 && !this.m_9236_().m_8055_(ground).m_280296_()) {
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

    public boolean m_6469_(DamageSource source, float amount) {
        if (this.m_6673_(source)) {
            return false;
        }
        Entity entity = source.m_7639_();
        if (entity instanceof EnderMan) {
            amount = (amount + 1.0f) * 0.35f;
            this.angryEnderman = (EnderMan)entity;
        }
        return super.m_6469_(source, amount);
    }

    public static class FlyTowardsTarget
    extends Goal {
        private final EntityEnderiophage parentEntity;

        public FlyTowardsTarget(EntityEnderiophage phage) {
            this.parentEntity = phage;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            return !this.parentEntity.m_20159_() && this.parentEntity.m_5448_() != null && !this.isBittenByPhage((Entity)this.parentEntity.m_5448_()) && this.parentEntity.fleeAfterStealTime == 0;
        }

        public boolean m_8045_() {
            return this.parentEntity.m_5448_() != null && !this.isBittenByPhage((Entity)this.parentEntity.m_5448_()) && !this.parentEntity.f_19862_ && !this.parentEntity.m_20159_() && this.parentEntity.m_29443_() && this.parentEntity.m_21566_().m_24995_() && this.parentEntity.fleeAfterStealTime == 0 && (this.parentEntity.m_5448_() instanceof EnderMan || !this.parentEntity.isMissingEye());
        }

        public boolean isBittenByPhage(Entity entity) {
            int phageCount = 0;
            for (Entity e : entity.m_20197_()) {
                if (!(e instanceof EntityEnderiophage)) continue;
                ++phageCount;
            }
            return phageCount > 3;
        }

        public void m_8041_() {
        }

        public void m_8037_() {
            if (this.parentEntity.m_5448_() != null) {
                boolean isWithinReach;
                float width = this.parentEntity.m_5448_().m_20205_() + this.parentEntity.m_20205_() + 2.0f;
                boolean bl = isWithinReach = this.parentEntity.m_20280_((Entity)this.parentEntity.m_5448_()) < (double)(width * width);
                if (this.parentEntity.m_29443_() || isWithinReach) {
                    this.parentEntity.m_21566_().m_6849_(this.parentEntity.m_5448_().m_20185_(), this.parentEntity.m_5448_().m_20186_(), this.parentEntity.m_5448_().m_20189_(), isWithinReach ? 1.6 : 1.0);
                } else {
                    this.parentEntity.m_21573_().m_26519_(this.parentEntity.m_5448_().m_20185_(), this.parentEntity.m_5448_().m_20186_(), this.parentEntity.m_5448_().m_20189_(), 1.2);
                }
                if (this.parentEntity.m_5448_().m_20186_() > this.parentEntity.m_20186_() + (double)1.2f) {
                    this.parentEntity.setFlying(true);
                }
                if (this.parentEntity.dismountCooldown == 0 && this.parentEntity.m_20191_().m_82377_(0.3, 0.3, 0.3).m_82381_(this.parentEntity.m_5448_().m_20191_()) && !this.isBittenByPhage((Entity)this.parentEntity.m_5448_())) {
                    this.parentEntity.m_7998_((Entity)this.parentEntity.m_5448_(), true);
                    if (!this.parentEntity.m_9236_().f_46443_) {
                        AlexsMobs.sendMSGToAll(new MessageMosquitoMountPlayer(this.parentEntity.m_19879_(), this.parentEntity.m_5448_().m_19879_()));
                    }
                }
            }
        }
    }

    private class AIWalkIdle
    extends Goal {
        protected final EntityEnderiophage phage;
        protected double x;
        protected double y;
        protected double z;
        private boolean flightTarget = false;

        public AIWalkIdle() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.phage = EntityEnderiophage.this;
        }

        public boolean m_8036_() {
            Vec3 lvt_1_1_;
            if (this.phage.m_20160_() || this.phage.m_5448_() != null && this.phage.m_5448_().m_6084_() || this.phage.m_20159_()) {
                return false;
            }
            if (this.phage.m_217043_().m_188503_(30) != 0 && !this.phage.m_29443_() && this.phage.fleeAfterStealTime == 0) {
                return false;
            }
            if (this.phage.m_20096_()) {
                this.flightTarget = EntityEnderiophage.this.f_19796_.m_188503_(12) == 0;
            } else {
                boolean bl = this.flightTarget = EntityEnderiophage.this.f_19796_.m_188503_(5) > 0 && this.phage.timeFlying < 100;
            }
            if (this.phage.fleeAfterStealTime > 0) {
                this.flightTarget = true;
            }
            if ((lvt_1_1_ = this.getPosition()) == null) {
                return false;
            }
            this.x = lvt_1_1_.f_82479_;
            this.y = lvt_1_1_.f_82480_;
            this.z = lvt_1_1_.f_82481_;
            return true;
        }

        public void m_8037_() {
            if (this.flightTarget) {
                this.phage.m_21566_().m_6849_(this.x, this.y, this.z, EntityEnderiophage.this.fleeAfterStealTime == 0 ? (double)1.3f : 1.0);
            } else {
                this.phage.m_21573_().m_26519_(this.x, this.y, this.z, EntityEnderiophage.this.fleeAfterStealTime == 0 ? (double)1.3f : 1.0);
            }
            if (!this.flightTarget && EntityEnderiophage.this.m_29443_() && this.phage.m_20096_()) {
                this.phage.setFlying(false);
            }
            if (EntityEnderiophage.this.m_29443_() && this.phage.m_20096_() && this.phage.timeFlying > 100 && this.phage.fleeAfterStealTime == 0) {
                this.phage.setFlying(false);
            }
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vector3d = this.phage.m_20182_();
            if (this.phage.isOverWaterOrVoid()) {
                this.flightTarget = true;
            }
            if (this.flightTarget) {
                if (this.phage.timeFlying < 50 || EntityEnderiophage.this.fleeAfterStealTime > 0 || this.phage.isOverWaterOrVoid()) {
                    return this.phage.getBlockInViewAway(vector3d, 0.0f);
                }
                return this.phage.getBlockGrounding(vector3d);
            }
            return LandRandomPos.m_148488_((PathfinderMob)this.phage, (int)10, (int)7);
        }

        public boolean m_8045_() {
            if (this.flightTarget) {
                return this.phage.m_29443_() && this.phage.m_20275_(this.x, this.y, this.z) > 2.0;
            }
            return !this.phage.m_21573_().m_26571_() && !this.phage.m_20160_();
        }

        public void m_8056_() {
            if (this.flightTarget) {
                this.phage.setFlying(true);
                this.phage.m_21566_().m_6849_(this.x, this.y, this.z, EntityEnderiophage.this.fleeAfterStealTime == 0 ? (double)1.3f : 1.0);
            } else {
                this.phage.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
        }

        public void m_8041_() {
            this.phage.m_21573_().m_26573_();
            super.m_8041_();
        }
    }
}
