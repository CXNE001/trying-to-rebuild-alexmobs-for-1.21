/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.message.MessageSendVisualFlagFromServer;
import com.github.alexthe666.alexsmobs.misc.AMDamageTypes;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityFarseer
extends Monster
implements IAnimatedEntity {
    public static final Animation ANIMATION_EMERGE = Animation.create((int)50);
    private static final int HANDS = 4;
    private static final EntityDataAccessor<Boolean> ANGRY = SynchedEntityData.m_135353_(EntityFarseer.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HAS_EMERGED = SynchedEntityData.m_135353_(EntityFarseer.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> MELEEING = SynchedEntityData.m_135353_(EntityFarseer.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> LASER_ENTITY_ID = SynchedEntityData.m_135353_(EntityFarseer.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> LASER_ATTACK_LVL = SynchedEntityData.m_135353_(EntityFarseer.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Float> LASER_DISTANCE = SynchedEntityData.m_135353_(EntityFarseer.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    public static final int LASER_ATTACK_DURATION = 10;
    public final double[][] positions = new double[64][4];
    public final float[] claspProgress = new float[4];
    public final float[] prevClaspProgress = new float[4];
    public final float[] strikeProgress = new float[4];
    public final float[] prevStrikeProgress = new float[4];
    public final boolean[] isStriking = new boolean[4];
    public int posPointer = -1;
    public float angryProgress;
    public float prevAngryProgress;
    public Vec3 angryShakeVec = Vec3.f_82478_;
    public float prevLaserLvl;
    private float faceCameraProgress;
    private float prevFaceCameraProgress;
    private LivingEntity laserTargetEntity;
    private int claspingHand = -1;
    private int animationTick;
    private Animation currentAnimation;
    private int meleeCooldown = 0;

    protected EntityFarseer(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.f_21342_ = new MoveController();
        this.f_21364_ = 20;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 70.0).m_22268_(Attributes.f_22284_, 6.0).m_22268_(Attributes.f_22280_, 0.5).m_22268_(Attributes.f_22281_, 4.5).m_22268_(Attributes.f_22279_, (double)0.35f);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.farseerSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public boolean m_20068_() {
        return true;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected float m_6431_(Pose pose, EntityDimensions dimensions) {
        return dimensions.f_20378_ * 0.7f;
    }

    protected PathNavigation m_6037_(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation((Mob)this, this.m_9236_());
        flyingpathnavigation.m_26440_(false);
        flyingpathnavigation.m_7008_(true);
        flyingpathnavigation.m_26443_(true);
        return flyingpathnavigation;
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new AttackGoal());
        this.f_21345_.m_25352_(3, (Goal)new RandomFlyGoal(this));
        this.f_21345_.m_25352_(4, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 10.0f));
        this.f_21345_.m_25352_(5, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<Player>((Mob)this, Player.class, 3, false, true, null));
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Emerged", this.hasEmerged());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setHasEmerged(compound.m_128471_("Emerged"));
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.FARSEER_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.FARSEER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.FARSEER_HURT.get();
    }

    public static boolean checkFarseerSpawnRules(EntityType<? extends Monster> animal, ServerLevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return worldIn.m_46791_() != Difficulty.PEACEFUL && EntityFarseer.m_219009_((ServerLevelAccessor)worldIn, (BlockPos)pos, (RandomSource)random) && EntityFarseer.isFarseerArea(worldIn, pos);
    }

    private static boolean isFarseerArea(ServerLevelAccessor iServerWorld, BlockPos pos) {
        return !AMConfig.restrictFarseerSpawns || iServerWorld.m_6857_().m_61941_((double)pos.m_123341_(), (double)pos.m_123343_()) < (double)AMConfig.farseerBorderSpawnDistance;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(HAS_EMERGED, (Object)false);
        this.f_19804_.m_135372_(MELEEING, (Object)false);
        this.f_19804_.m_135372_(ANGRY, (Object)false);
        this.f_19804_.m_135372_(LASER_ENTITY_ID, (Object)-1);
        this.f_19804_.m_135372_(LASER_ATTACK_LVL, (Object)0);
        this.f_19804_.m_135372_(LASER_DISTANCE, (Object)Float.valueOf(0.0f));
    }

    public boolean isAngry() {
        return (Boolean)this.f_19804_.m_135370_(ANGRY);
    }

    public void setAngry(boolean angry) {
        this.f_19804_.m_135381_(ANGRY, (Object)angry);
    }

    public boolean hasLaser() {
        return (Integer)this.f_19804_.m_135370_(LASER_ENTITY_ID) != -1 && this.getAnimation() != ANIMATION_EMERGE;
    }

    public int getLaserAttackLvl() {
        return (Integer)this.f_19804_.m_135370_(LASER_ATTACK_LVL);
    }

    public float getLaserDistance() {
        return ((Float)this.f_19804_.m_135370_(LASER_DISTANCE)).floatValue();
    }

    public void m_7350_(EntityDataAccessor<?> p_32834_) {
        super.m_7350_(p_32834_);
        if (LASER_ENTITY_ID.equals(p_32834_)) {
            this.laserTargetEntity = null;
        }
    }

    @Nullable
    public LivingEntity getLaserTarget() {
        if (!this.hasLaser()) {
            return null;
        }
        if (this.m_9236_().f_46443_) {
            if (this.laserTargetEntity != null) {
                return this.laserTargetEntity;
            }
            Entity fromID = this.m_9236_().m_6815_(((Integer)this.f_19804_.m_135370_(LASER_ENTITY_ID)).intValue());
            if (fromID instanceof LivingEntity) {
                this.laserTargetEntity = (LivingEntity)fromID;
                return this.laserTargetEntity;
            }
            return null;
        }
        return this.m_5448_();
    }

    public boolean hasEmerged() {
        return (Boolean)this.f_19804_.m_135370_(HAS_EMERGED);
    }

    public void setHasEmerged(boolean emerged) {
        this.f_19804_.m_135381_(HAS_EMERGED, (Object)emerged);
    }

    public void m_8119_() {
        int i;
        super.m_8119_();
        this.prevFaceCameraProgress = this.faceCameraProgress;
        this.prevLaserLvl = this.getLaserAttackLvl();
        if (this.getAnimation() == ANIMATION_EMERGE) {
            this.setHasEmerged(true);
            this.faceCameraProgress = 1.0f;
        } else if (this.faceCameraProgress > 0.0f) {
            this.faceCameraProgress = Math.max(0.0f, this.faceCameraProgress - 0.2f);
        }
        this.prevAngryProgress = this.angryProgress;
        for (i = 0; i < 4; ++i) {
            this.prevClaspProgress[i] = this.claspProgress[i];
            this.prevStrikeProgress[i] = this.strikeProgress[i];
        }
        if (this.posPointer < 0) {
            for (i = 0; i < this.positions.length; ++i) {
                this.positions[i][0] = this.m_20185_();
                this.positions[i][1] = this.m_20186_();
                this.positions[i][2] = this.m_20189_();
                this.positions[i][3] = this.f_20883_;
            }
        }
        if (++this.posPointer == this.positions.length) {
            this.posPointer = 0;
        }
        this.positions[this.posPointer][0] = this.m_20185_();
        this.positions[this.posPointer][1] = this.m_20186_();
        this.positions[this.posPointer][2] = this.m_20189_();
        this.positions[this.posPointer][3] = this.f_20883_;
        if (this.isAngry() && this.angryProgress < 5.0f) {
            this.angryProgress += 1.0f;
        }
        if (!this.isAngry() && this.angryProgress > 0.0f) {
            this.angryProgress -= 1.0f;
        }
        if (this.m_6084_()) {
            LivingEntity livingentity;
            int i2;
            LivingEntity target;
            if (this.f_19796_.m_188503_(this.isAngry() ? 12 : 40) == 0 && this.claspingHand == -1 && this.claspProgress[i = Mth.m_14045_((int)this.f_19796_.m_188503_(4), (int)0, (int)3)] == 0.0f) {
                this.claspingHand = i;
            }
            if (this.claspingHand >= 0) {
                if (this.claspProgress[this.claspingHand] < 5.0f) {
                    int n = this.claspingHand;
                    this.claspProgress[n] = this.claspProgress[n] + 1.0f;
                } else {
                    this.claspingHand = -1;
                }
            } else {
                for (i = 0; i < 4; ++i) {
                    if (!(this.claspProgress[i] > 0.0f)) continue;
                    int n = i;
                    this.claspProgress[n] = this.claspProgress[n] - 1.0f;
                }
            }
            if (!this.hasEmerged()) {
                this.m_6842_(true);
                if (this.m_9236_().m_45914_(this.m_20185_(), this.m_20186_(), this.m_20189_(), 9.0)) {
                    this.setAnimation(ANIMATION_EMERGE);
                }
            } else {
                this.m_6842_(this.m_21023_(MobEffects.f_19609_));
            }
            if (this.getAnimation() == ANIMATION_EMERGE) {
                if (this.m_9236_().f_46443_) {
                    this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.STATIC_SPARK.get(), this.m_20208_(0.75), this.m_20187_(), this.m_20262_(0.75), (double)((this.m_217043_().m_188501_() - 0.5f) * 0.2f), (double)(this.m_217043_().m_188501_() * 0.2f), (double)((this.m_217043_().m_188501_() - 0.5f) * 0.2f));
                }
                if (this.getAnimationTick() == 1) {
                    this.m_5496_((SoundEvent)AMSoundRegistry.FARSEER_EMERGE.get(), this.m_6121_(), this.m_6100_());
                }
            }
            if ((target = this.m_5448_()) != null && ((Boolean)this.f_19804_.m_135370_(MELEEING)).booleanValue() && this.meleeCooldown == 0) {
                this.meleeCooldown = 5;
                i2 = this.f_19796_.m_188503_(4);
                this.isStriking[i2] = true;
                this.m_9236_().m_7605_((Entity)this, (byte)(40 + i2));
            }
            if (this.meleeCooldown > 0) {
                --this.meleeCooldown;
            }
            for (i2 = 0; i2 < 4; ++i2) {
                if (!this.isStriking[i2] || !((Boolean)this.f_19804_.m_135370_(MELEEING)).booleanValue()) {
                    if (!(this.strikeProgress[i2] > 0.0f)) continue;
                    int n = i2;
                    this.strikeProgress[n] = this.strikeProgress[n] - 1.0f;
                    continue;
                }
                if (!this.isStriking[i2]) continue;
                if (this.strikeProgress[i2] < 5.0f) {
                    int n = i2;
                    this.strikeProgress[n] = this.strikeProgress[n] + 1.0f;
                }
                if (this.strikeProgress[i2] != 5.0f) continue;
                this.isStriking[i2] = false;
                this.m_9236_().m_7605_((Entity)this, (byte)(44 + i2));
                if (target == null || !(this.m_20270_((Entity)target) <= 4.0f)) continue;
                target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)(5 + this.f_19796_.m_188503_(5)));
            }
            if (this.hasLaser() && (livingentity = this.getLaserTarget()) != null) {
                Vec3 hit = this.calculateLaserHit(livingentity.m_146892_());
                this.f_19804_.m_135381_(LASER_DISTANCE, (Object)Float.valueOf((float)hit.m_82554_(this.m_146892_())));
                this.m_21563_().m_24960_((Entity)livingentity, 90.0f, 90.0f);
                this.m_21563_().m_8128_();
                double d0 = hit.f_82479_ - this.m_20185_();
                double d1 = hit.f_82480_ - this.m_20188_();
                double d2 = hit.f_82481_ - this.m_20189_();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d0 /= d3;
                d1 /= d3;
                d2 /= d3;
                float progress = (float)this.getLaserAttackLvl() / 10.0f;
                double d4 = this.f_19796_.m_188500_();
                while (d4 < d3 * (double)progress) {
                    double width = (d4 += 0.5 + 2.0 * this.f_19796_.m_188500_()) / (d3 * (double)progress);
                    double d5 = (this.f_19796_.m_188500_() - 0.5) * width;
                    double d6 = (this.f_19796_.m_188500_() - 0.5) * width;
                    this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.STATIC_SPARK.get(), this.m_20185_() + d0 * d4 + d5, this.m_20188_() + d1 * d4, this.m_20189_() + d2 * d4 + d6, (double)((this.m_217043_().m_188501_() - 0.5f) * 0.2f), (double)(this.m_217043_().m_188501_() * 0.2f), (double)((this.m_217043_().m_188501_() - 0.5f) * 0.2f));
                }
            }
        }
        this.angryShakeVec = this.isAngry() ? new Vec3((double)(this.f_19796_.m_188501_() - 0.5f), (double)(this.f_19796_.m_188501_() - 0.5f), (double)(this.f_19796_.m_188501_() - 0.5f)) : Vec3.f_82478_;
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id >= 40 && id <= 43) {
            int i = id - 40;
            this.isStriking[i] = true;
        } else if (id >= 44 && id <= 48) {
            int i = id - 44;
            this.isStriking[i] = false;
        } else {
            super.m_7822_(id);
        }
    }

    public double getLatencyVar(int pointer, int index, float partialTick) {
        if (this.m_21224_()) {
            partialTick = 1.0f;
        }
        int i = this.posPointer - pointer & 0x3F;
        int j = this.posPointer - pointer - 1 & 0x3F;
        double d0 = this.positions[j][index];
        double d1 = Mth.m_14175_((double)(this.positions[i][index] - d0));
        return d0 + d1 * (double)partialTick;
    }

    public Vec3 getLatencyOffsetVec(int offset, float partialTick) {
        double d0 = Mth.m_14139_((double)partialTick, (double)this.f_19790_, (double)this.m_20185_());
        double d1 = Mth.m_14139_((double)partialTick, (double)this.f_19791_, (double)this.m_20186_());
        double d2 = Mth.m_14139_((double)partialTick, (double)this.f_19792_, (double)this.m_20189_());
        float renderYaw = (float)this.getLatencyVar(offset, 3, partialTick);
        return new Vec3(this.getLatencyVar(offset, 0, partialTick) - d0, this.getLatencyVar(offset, 1, partialTick) - d1, this.getLatencyVar(offset, 2, partialTick) - d2).m_82524_(renderYaw * ((float)Math.PI / 180));
    }

    public Vec3 calculateAfterimagePos(float partialTick, boolean flip, float speed) {
        float f = (partialTick + (float)this.f_19797_) * speed;
        float f1 = 0.1f;
        Vec3 v = new Vec3((double)((float)Math.sin(f) * f1), (double)((float)Math.cos((double)f - 1.5707963267948966) * f1), (double)(-((float)Math.cos(f)) * f1));
        if (flip) {
            return new Vec3(v.f_82481_, -v.f_82480_, v.f_82479_);
        }
        return v;
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int i) {
        this.animationTick = i;
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_EMERGE};
    }

    public int getPortalFrame() {
        if (this.getAnimation() == ANIMATION_EMERGE) {
            if (this.getAnimationTick() < 10) {
                return 0;
            }
            if (this.getAnimationTick() < 20) {
                return 1;
            }
            if (this.getAnimationTick() < 30) {
                return 2;
            }
            if (this.getAnimationTick() > 40) {
                int i = 50 - this.getAnimationTick();
                return i < 6 ? (i < 3 ? 0 : 1) : 2;
            }
            return 3;
        }
        return 0;
    }

    public float getPortalOpacity(float partialTicks) {
        if (this.getAnimation() == ANIMATION_EMERGE) {
            float tick = (float)(this.getAnimationTick() - 1) + partialTicks;
            if (tick < 5.0f) {
                return tick / 5.0f;
            }
            return 1.0f;
        }
        return 0.0f;
    }

    public float getFarseerOpacity(float partialTicks) {
        if (this.getAnimation() == ANIMATION_EMERGE) {
            float tick = (float)(this.getAnimationTick() - 1) + partialTicks;
            float prog = tick / (float)ANIMATION_EMERGE.getDuration();
            return prog > 0.5f ? (prog - 0.5f) / 0.5f : 0.0f;
        }
        return 1.0f;
    }

    public float getFacingCameraAmount(float partialTicks) {
        return this.prevFaceCameraProgress + (this.faceCameraProgress - this.prevFaceCameraProgress) * partialTicks;
    }

    public boolean m_21515_() {
        return super.m_21515_() && this.getAnimation() != ANIMATION_EMERGE && this.hasEmerged();
    }

    private Vec3 calculateLaserHit(Vec3 target) {
        Vec3 eyes = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        BlockHitResult hitResult = this.m_9236_().m_45547_(new ClipContext(eyes, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        return hitResult.m_82450_();
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    public void m_7023_(Vec3 vec3) {
        if (this.m_21515_() || this.m_6109_()) {
            if (this.m_20069_()) {
                this.m_19920_(0.02f, vec3);
                this.m_6478_(MoverType.SELF, this.m_20184_());
                this.m_20256_(this.m_20184_().m_82490_((double)0.8f));
            } else if (this.m_20077_()) {
                this.m_19920_(0.02f, vec3);
                this.m_6478_(MoverType.SELF, this.m_20184_());
                this.m_20256_(this.m_20184_().m_82490_(0.5));
            } else {
                this.m_19920_(this.m_6113_(), vec3);
                this.m_6478_(MoverType.SELF, this.m_20184_());
                this.m_20256_(this.m_20184_().m_82490_((double)0.91f));
            }
        }
        this.m_267651_(false);
    }

    public boolean m_6673_(DamageSource dmg) {
        return super.m_6673_(dmg) || this.getAnimation() == ANIMATION_EMERGE;
    }

    private boolean canUseLaser() {
        return !this.m_21023_(MobEffects.f_19610_);
    }

    class MoveController
    extends MoveControl {
        private final Mob parentEntity;

        public MoveController() {
            super((Mob)EntityFarseer.this);
            this.parentEntity = EntityFarseer.this;
        }

        public void m_8126_() {
            float angle = (float)Math.PI / 180 * (this.parentEntity.f_20883_ + 90.0f);
            float radius = (float)Math.sin((float)this.parentEntity.f_19797_ * 0.2f) * 2.0f;
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraY = (double)radius * -Math.cos((double)angle - 1.5707963267948966);
            double extraZ = radius * Mth.m_14089_((float)angle);
            Vec3 strafPlus = new Vec3(extraX, extraY, extraZ);
            if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
                Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
                double d0 = vector3d.m_82553_();
                double width = this.parentEntity.m_20191_().m_82309_();
                Vec3 shimmy = Vec3.f_82478_;
                LivingEntity attackTarget = this.parentEntity.m_5448_();
                if (attackTarget != null && this.parentEntity.f_19862_) {
                    shimmy = new Vec3(0.0, 0.005, 0.0);
                }
                Vec3 vector3d1 = vector3d.m_82490_(this.f_24978_ * 0.05 / d0);
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d1.m_82549_(strafPlus.m_82490_(0.003 * Math.min(d0, 100.0)).m_82549_(shimmy))));
                if (d0 >= width) {
                    this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
                    if (EntityFarseer.this.hasLaser()) {
                        this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                    }
                }
            } else if (this.f_24981_ == MoveControl.Operation.WAIT) {
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(strafPlus.m_82490_(0.003)));
            }
        }
    }

    private class AttackGoal
    extends Goal {
        private boolean attackDecision = true;
        private int timeSinceLastSuccessfulAttack = 0;
        private int laserCooldown = 0;
        private int laserUseTime = 0;
        private int lasersShot = 0;

        public AttackGoal() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            return EntityFarseer.this.m_5448_() != null && EntityFarseer.this.m_5448_().m_6084_();
        }

        public void m_8041_() {
            this.lasersShot = 0;
            this.laserCooldown = 0;
            this.laserUseTime = 0;
            this.attackDecision = EntityFarseer.this.m_217043_().m_188499_();
            EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)-1);
            this.timeSinceLastSuccessfulAttack = 0;
            EntityFarseer.this.setAngry(false);
        }

        public void m_8037_() {
            super.m_8037_();
            LivingEntity target = EntityFarseer.this.m_5448_();
            if (this.laserCooldown > 0) {
                --this.laserCooldown;
            }
            ++this.timeSinceLastSuccessfulAttack;
            if (this.timeSinceLastSuccessfulAttack > 100) {
                this.timeSinceLastSuccessfulAttack = 0;
                boolean bl = this.attackDecision = !this.attackDecision;
            }
            if (target != null) {
                double dist = EntityFarseer.this.m_20270_((Entity)target);
                boolean canLaserHit = this.willLaserHit(target);
                if (this.laserCooldown == 0 && this.attackDecision && canLaserHit && dist > 2.0) {
                    EntityFarseer.this.setAngry(true);
                    EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)target.m_19879_());
                    if (this.laserUseTime == 0) {
                        EntityFarseer.this.m_5496_((SoundEvent)AMSoundRegistry.FARSEER_BEAM.get(), EntityFarseer.this.m_6121_(), EntityFarseer.this.m_6100_());
                    }
                    ++this.laserUseTime;
                    if (this.laserUseTime > 10) {
                        this.laserUseTime = 0;
                        if (canLaserHit) {
                            float healthTenth = target.m_21233_() * 0.1f;
                            if (target.m_6469_(AMDamageTypes.causeFarseerDamage((LivingEntity)EntityFarseer.this), (float)EntityFarseer.this.f_19796_.m_188503_(2) + Math.max(6.0f, healthTenth)) && !target.m_6084_()) {
                                AlexsMobs.sendMSGToAll(new MessageSendVisualFlagFromServer(target.m_19879_(), 87));
                            }
                            this.timeSinceLastSuccessfulAttack = 0;
                        }
                        if (this.lasersShot++ > 5) {
                            this.lasersShot = 0;
                            this.laserCooldown = 80 + EntityFarseer.this.f_19796_.m_188503_(40);
                            EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)-1);
                            this.attackDecision = EntityFarseer.this.m_217043_().m_188499_();
                        }
                    }
                    EntityFarseer.this.f_19804_.m_135381_(LASER_ATTACK_LVL, (Object)this.laserUseTime);
                    EntityFarseer.this.m_21391_((Entity)target, 180.0f, 180.0f);
                    if (dist < 17.0 && canLaserHit) {
                        EntityFarseer.this.m_21573_().m_26573_();
                    } else {
                        EntityFarseer.this.m_21573_().m_5624_((Entity)target, 1.0);
                    }
                    EntityFarseer.this.f_19804_.m_135381_(MELEEING, (Object)false);
                } else {
                    if (!canLaserHit && dist > 10.0) {
                        EntityFarseer.this.setAngry(false);
                    }
                    if (EntityFarseer.this.hasLaser()) {
                        EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)-1);
                    }
                    EntityFarseer.this.f_19804_.m_135381_(MELEEING, (Object)(dist < 4.0 ? 1 : 0));
                    if (dist < 4.0) {
                        this.timeSinceLastSuccessfulAttack = 0;
                    } else {
                        EntityFarseer.this.m_21573_().m_5624_((Entity)target, 1.0);
                        EntityFarseer.this.f_21342_.m_6849_(target.m_20185_(), target.m_20188_(), target.m_20189_(), 1.0);
                    }
                }
            }
        }

        private boolean willLaserHit(LivingEntity target) {
            Vec3 vec = EntityFarseer.this.calculateLaserHit(target.m_146892_());
            return vec.m_82554_(target.m_146892_()) < 1.0 && EntityFarseer.this.canUseLaser();
        }
    }

    private static class RandomFlyGoal
    extends Goal {
        private final EntityFarseer parentEntity;
        private BlockPos target = null;
        private final float speed = 0.6f;

        public RandomFlyGoal(EntityFarseer mosquito) {
            this.parentEntity = mosquito;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            if (this.parentEntity.m_21573_().m_26571_() && this.parentEntity.m_5448_() == null && this.parentEntity.m_217043_().m_188503_(4) == 0) {
                this.target = this.getBlockInViewFarseer();
                if (this.target != null) {
                    this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, (double)0.6f);
                    return true;
                }
            }
            return false;
        }

        public boolean m_8045_() {
            return this.target != null && this.parentEntity.m_5448_() == null;
        }

        public void m_8041_() {
            this.target = null;
        }

        public void m_8037_() {
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, (double)0.6f);
                if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 4.0 || this.parentEntity.f_19862_) {
                    this.target = null;
                }
            }
        }

        private BlockPos getFarseerGround(BlockPos in) {
            BlockPos position = new BlockPos(in.m_123341_(), (int)this.parentEntity.m_20186_(), in.m_123343_());
            while (position.m_123342_() < 256 && !this.parentEntity.m_9236_().m_6425_(position).m_76178_()) {
                position = position.m_7494_();
            }
            while (position.m_123342_() > 1 && this.parentEntity.m_9236_().m_46859_(position)) {
                position = position.m_7495_();
            }
            return position;
        }

        public BlockPos getBlockInViewFarseer() {
            float radius = 5 + this.parentEntity.m_217043_().m_188503_(10);
            float neg = this.parentEntity.m_217043_().m_188499_() ? 1.0f : -1.0f;
            float renderYawOffset = this.parentEntity.m_146908_();
            float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f * (this.parentEntity.m_217043_().m_188501_() * neg);
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            BlockPos radialPos = new BlockPos((int)(this.parentEntity.m_20185_() + extraX), (int)this.parentEntity.m_20186_(), (int)(this.parentEntity.m_20189_() + extraZ));
            BlockPos ground = this.getFarseerGround(radialPos).m_6630_(2 + this.parentEntity.f_19796_.m_188503_(2));
            if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)ground.m_7494_()))) {
                return ground;
            }
            return null;
        }
    }
}
