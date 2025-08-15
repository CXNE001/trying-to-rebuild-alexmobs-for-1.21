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
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MoveFunction
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.EntityHemolymph;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityWarpedMosco
extends Monster
implements IAnimatedEntity {
    public static final Animation ANIMATION_PUNCH_R = Animation.create((int)25);
    public static final Animation ANIMATION_PUNCH_L = Animation.create((int)25);
    public static final Animation ANIMATION_SLAM = Animation.create((int)35);
    public static final Animation ANIMATION_SUCK = Animation.create((int)60);
    public static final Animation ANIMATION_SPIT = Animation.create((int)60);
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityWarpedMosco.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HAND_SIDE = SynchedEntityData.m_135353_(EntityWarpedMosco.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float flyLeftProgress;
    public float prevLeftFlyProgress;
    public float flyRightProgress;
    public float prevFlyRightProgress;
    private int animationTick;
    private Animation currentAnimation;
    private boolean isLandNavigator;
    private int timeFlying;
    private int loopSoundTick = 0;

    protected EntityWarpedMosco(EntityType entityType, Level world) {
        super(entityType, world);
        this.f_21364_ = 30;
        this.switchNavigator(false);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 100.0).m_22268_(Attributes.f_22277_, 128.0).m_22268_(Attributes.f_22281_, 10.0).m_22268_(Attributes.f_22284_, 10.0).m_22268_(Attributes.f_22278_, 1.0).m_22268_(Attributes.f_22285_, 2.0).m_22268_(Attributes.f_22279_, 0.3);
    }

    public MobType m_6336_() {
        return MobType.f_21642_;
    }

    private static Animation getRandomAttack(RandomSource rand) {
        return switch (rand.m_188503_(4)) {
            case 0 -> ANIMATION_PUNCH_L;
            case 1 -> ANIMATION_PUNCH_R;
            case 2 -> ANIMATION_SLAM;
            case 3 -> ANIMATION_SUCK;
            default -> ANIMATION_SUCK;
        };
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.WARPED_MOSCO_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.WARPED_MOSCO_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.WARPED_MOSCO_HURT.get();
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(0, (Goal)new AttackGoal());
        this.f_21345_.m_25352_(4, (Goal)new AIWalkIdle());
        this.f_21345_.m_25352_(4, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 32.0f));
        this.f_21345_.m_25352_(5, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[]{EntityCrimsonMosquito.class, EntityWarpedMosco.class}));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<Player>((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<LivingEntity>((Mob)this, LivingEntity.class, 50, false, true, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.CRIMSON_MOSQUITO_TARGETS)));
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigatorWide((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new FlightMoveController((Mob)this, 0.7f, false);
            this.f_21344_ = new DirectPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FLYING, (Object)false);
        this.f_19804_.m_135372_(HAND_SIDE, (Object)true);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean isFlying() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    public void setFlying(boolean flying) {
        this.setDashRight(flying != this.isFlying() ? this.f_19796_.m_188499_() : this.isDashRight());
        this.f_19804_.m_135381_(FLYING, (Object)flying);
    }

    public boolean isDashRight() {
        return (Boolean)this.f_19804_.m_135370_(HAND_SIDE);
    }

    public void setDashRight(boolean right) {
        this.f_19804_.m_135381_(HAND_SIDE, (Object)right);
    }

    public void m_8119_() {
        LivingEntity target;
        super.m_8119_();
        this.prevFlyRightProgress = this.flyRightProgress;
        this.prevLeftFlyProgress = this.flyLeftProgress;
        boolean dashRight = this.isDashRight();
        boolean flying = this.isFlying();
        if (flying && dashRight && this.flyRightProgress < 5.0f) {
            this.flyRightProgress += 1.0f;
        }
        if (!(flying && dashRight || !(this.flyRightProgress > 0.0f))) {
            this.flyRightProgress -= 1.0f;
        }
        if (flying && !dashRight && this.flyLeftProgress < 5.0f) {
            this.flyLeftProgress += 1.0f;
        }
        if ((!flying || dashRight) && this.flyLeftProgress > 0.0f) {
            this.flyLeftProgress -= 1.0f;
        }
        if (!this.m_9236_().f_46443_) {
            if (flying) {
                if (this.isLandNavigator) {
                    this.switchNavigator(false);
                }
            } else if (!this.isLandNavigator) {
                this.switchNavigator(true);
            }
        }
        if (flying) {
            if (this.loopSoundTick == 0) {
                this.m_5496_((SoundEvent)AMSoundRegistry.MOSQUITO_LOOP.get(), this.m_6121_(), this.m_6100_() * 0.3f);
            }
            ++this.loopSoundTick;
            if (this.loopSoundTick > 100) {
                this.loopSoundTick = 0;
            }
            ++this.timeFlying;
            this.m_20242_(true);
            if (this.m_20159_() || this.m_20160_()) {
                this.setFlying(false);
            }
        } else {
            this.timeFlying = 0;
            this.m_20242_(false);
        }
        if (this.f_19862_ && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
            boolean flag = false;
            AABB axisalignedbb = this.m_20191_().m_82400_(0.2);
            for (BlockPos blockpos : BlockPos.m_121976_((int)Mth.m_14107_((double)axisalignedbb.f_82288_), (int)Mth.m_14107_((double)axisalignedbb.f_82289_), (int)Mth.m_14107_((double)axisalignedbb.f_82290_), (int)Mth.m_14107_((double)axisalignedbb.f_82291_), (int)Mth.m_14107_((double)axisalignedbb.f_82292_), (int)Mth.m_14107_((double)axisalignedbb.f_82293_))) {
                BlockState blockstate = this.m_9236_().m_8055_(blockpos);
                if (!blockstate.m_204336_(AMTagRegistry.WARPED_MOSCO_BREAKABLES)) continue;
                flag = this.m_9236_().m_46953_(blockpos, true, (Entity)this) || flag;
            }
            if (!flag && this.m_20096_()) {
                this.m_6135_();
            }
        }
        if ((target = this.m_5448_()) != null && this.m_6084_()) {
            if (this.getAnimation() == ANIMATION_SUCK && this.getAnimationTick() == 3 && this.m_20270_((Entity)target) < 4.7f) {
                target.m_7998_((Entity)this, true);
            }
            if (this.getAnimation() == ANIMATION_SLAM && this.getAnimationTick() == 19) {
                for (Entity entity : this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82400_(5.0))) {
                    if (this.m_7307_(entity) || entity instanceof EntityWarpedMosco || entity == this) continue;
                    entity.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 10.0f + this.f_19796_.m_188501_() * 8.0f);
                    this.launch(entity, true);
                }
            }
            if ((this.getAnimation() == ANIMATION_PUNCH_R || this.getAnimation() == ANIMATION_PUNCH_L) && this.getAnimationTick() == 13 && this.m_20270_((Entity)target) < 4.7f) {
                target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21133_(Attributes.f_22281_));
                this.knockbackRidiculous(target, 0.9f);
            }
        }
        if (this.getAnimation() == ANIMATION_SLAM && this.getAnimationTick() == 19) {
            this.spawnGroundEffects();
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public void spawnGroundEffects() {
        float radius = 2.3f;
        double extraY = 0.8f;
        for (int i = 0; i < 4; ++i) {
            for (int i1 = 0; i1 < 20 + this.f_19796_.m_188503_(12); ++i1) {
                double motionX = this.m_217043_().m_188583_() * 0.07;
                double motionY = this.m_217043_().m_188583_() * 0.07;
                double motionZ = this.m_217043_().m_188583_() * 0.07;
                float angle = (float)Math.PI / 180 * this.f_20883_ + (float)i1;
                double extraX = 2.3f * Mth.m_14031_((float)((float)Math.PI + angle));
                double extraZ = 2.3f * Mth.m_14089_((float)angle);
                BlockPos ground = this.getMoscoGround(new BlockPos(Mth.m_14107_((double)(this.m_20185_() + extraX)), Mth.m_14107_((double)(this.m_20186_() + (double)0.8f)) - 1, Mth.m_14107_((double)(this.m_20189_() + extraZ))));
                BlockState state = this.m_9236_().m_8055_(ground);
                if (!state.m_280296_() || !this.m_9236_().f_46443_) continue;
                this.m_9236_().m_6493_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123794_, state), true, this.m_20185_() + extraX, (double)ground.m_123342_() + (double)0.8f, this.m_20189_() + extraZ, motionX, motionY, motionZ);
            }
        }
    }

    private void launch(Entity e, boolean huge) {
        if (e.m_20096_()) {
            double d0 = e.m_20185_() - this.m_20185_();
            double d1 = e.m_20189_() - this.m_20189_();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001);
            float f = huge ? 2.0f : 0.5f;
            e.m_5997_(d0 / d2 * (double)f, huge ? 0.5 : (double)0.2f, d1 / d2 * (double)f);
        }
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int i) {
        this.animationTick = i;
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_PUNCH_L, ANIMATION_PUNCH_R, ANIMATION_SLAM, ANIMATION_SUCK, ANIMATION_SPIT};
    }

    private BlockPos getMoscoGround(BlockPos in) {
        BlockPos position = new BlockPos(in.m_123341_(), (int)this.m_20186_(), in.m_123343_());
        while (position.m_123342_() > -62 && !this.m_9236_().m_8055_(position).m_280296_() && this.m_9236_().m_6425_(position).m_76178_()) {
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
        BlockPos radialPos = AMBlockPos.fromCoords(fleePos.m_7096_() + extraX, this.m_20186_(), fleePos.m_7094_() + extraZ);
        BlockPos ground = this.getMoscoGround(radialPos);
        if (ground.m_123342_() == -62) {
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

    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
        float radius = -9.45f - (float)this.m_217043_().m_188503_(24) - radiusAdd;
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), 0, (int)(fleePos.m_7094_() + extraZ));
        BlockPos ground = this.getMoscoGround(radialPos);
        int distFromGround = (int)this.m_20186_() - ground.m_123342_();
        int flightHeight = 4 + this.m_217043_().m_188503_(10);
        BlockPos newPos = ground.m_6630_(distFromGround > 8 ? flightHeight : this.m_217043_().m_188503_(6) + 1);
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 1.0) {
            return Vec3.m_82512_((Vec3i)newPos);
        }
        return null;
    }

    public void knockbackRidiculous(LivingEntity target, float power) {
        target.m_147240_((double)power, this.m_20185_() - target.m_20185_(), this.m_20189_() - target.m_20189_());
        float knockbackResist = (float)Mth.m_14008_((double)(1.0 - this.m_21133_(Attributes.f_22278_)), (double)0.0, (double)1.0);
        target.m_20256_(target.m_20184_().m_82520_(0.0, (double)(knockbackResist * power * 0.45f), 0.0));
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    private boolean isOverLiquid() {
        BlockPos position = this.m_20183_();
        while (position.m_123342_() > 2 && this.m_9236_().m_46859_(position)) {
            position = position.m_7495_();
        }
        return !this.m_9236_().m_6425_(position).m_76178_();
    }

    public void m_7023_(Vec3 travelVector) {
        if ((this.getAnimation() == ANIMATION_SUCK || this.getAnimation() == ANIMATION_SLAM) && this.getAnimationTick() > 8) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            travelVector = Vec3.f_82478_;
            super.m_7023_(travelVector);
            return;
        }
        super.m_7023_(travelVector);
    }

    public void m_19956_(Entity passenger, Entity.MoveFunction moveFunc) {
        super.m_19956_(passenger, moveFunc);
        if (this.m_20363_(passenger)) {
            int tick = 5;
            if (this.getAnimation() == ANIMATION_SUCK) {
                tick = this.getAnimationTick();
            } else {
                passenger.m_8127_();
            }
            float radius = 2.0f;
            float angle = (float)Math.PI / 180 * this.f_20883_;
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            double extraY = tick < 10 ? 0.0 : (double)(0.15f * (float)Mth.m_14045_((int)(tick - 10), (int)0, (int)15));
            passenger.m_6034_(this.m_20185_() + extraX, this.m_20186_() + extraY + (double)0.1f, this.m_20189_() + extraZ);
            if ((tick - 10) % 4 == 0) {
                this.m_7292_(new MobEffectInstance(MobEffects.f_19605_, 100, 1));
                passenger.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21133_(Attributes.f_22281_));
            }
        }
    }

    public boolean canRiderInteract() {
        return true;
    }

    public boolean shouldRiderSit() {
        return false;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.warpedMoscoSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    private void spit(LivingEntity target) {
        if (this.getAnimation() != ANIMATION_SPIT) {
            return;
        }
        this.m_21391_((Entity)target, 100.0f, 100.0f);
        this.f_20883_ = this.f_20885_;
        for (int i = 0; i < 2 + this.f_19796_.m_188503_(2); ++i) {
            EntityHemolymph llamaspitentity = new EntityHemolymph(this.m_9236_(), this);
            double d0 = target.m_20185_() - this.m_20185_();
            double d1 = target.m_20227_(0.3333333333333333) - llamaspitentity.m_20186_();
            double d2 = target.m_20189_() - this.m_20189_();
            float f = Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2))) * 0.2f;
            llamaspitentity.shoot(d0, d1 + (double)f, d2, 1.5f, 5.0f);
            if (!this.m_20067_()) {
                this.m_146850_(GameEvent.f_157778_);
                this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12098_, this.m_5720_(), 1.0f, 1.0f + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f);
            }
            this.m_9236_().m_7967_((Entity)llamaspitentity);
        }
    }

    private boolean shouldRangeAttack(LivingEntity target) {
        if ((double)this.m_21223_() < Math.floor(this.m_21233_() * 0.25f)) {
            return true;
        }
        return this.m_21223_() < this.m_21223_() * 0.5f && this.m_20270_((Entity)target) > 10.0f;
    }

    private class AttackGoal
    extends Goal {
        private int upTicks = 0;
        private int dashCooldown = 0;
        private boolean ranged = false;
        private BlockPos farTarget = null;

        public boolean m_8036_() {
            return EntityWarpedMosco.this.m_5448_() != null;
        }

        public void m_8037_() {
            if (this.dashCooldown > 0) {
                --this.dashCooldown;
            }
            if (EntityWarpedMosco.this.m_5448_() != null) {
                LivingEntity target = EntityWarpedMosco.this.m_5448_();
                this.ranged = EntityWarpedMosco.this.shouldRangeAttack(target);
                if (EntityWarpedMosco.this.isFlying() || this.ranged || EntityWarpedMosco.this.m_20270_((Entity)target) > 12.0f && !EntityWarpedMosco.this.isTargetBlocked(target.m_20182_().m_82520_(0.0, (double)(target.m_20206_() * 0.6f), 0.0))) {
                    float speedRush = 5.0f;
                    ++this.upTicks;
                    EntityWarpedMosco.this.setFlying(true);
                    if (this.ranged) {
                        if (this.farTarget == null || EntityWarpedMosco.this.m_20238_(Vec3.m_82512_((Vec3i)this.farTarget)) < 9.0) {
                            this.farTarget = this.getAvoidTarget(target);
                        }
                        if (this.farTarget != null) {
                            EntityWarpedMosco.this.m_21566_().m_6849_((double)this.farTarget.m_123341_(), (double)((float)this.farTarget.m_123342_() + target.m_20192_() * 0.6f), (double)this.farTarget.m_123343_(), 3.0);
                        }
                        EntityWarpedMosco.this.setAnimation(ANIMATION_SPIT);
                        if (this.upTicks % 30 == 0) {
                            EntityWarpedMosco.this.m_5634_(1.0f);
                        }
                        int tick = EntityWarpedMosco.this.getAnimationTick();
                        switch (tick) {
                            case 10: 
                            case 20: 
                            case 30: 
                            case 40: {
                                EntityWarpedMosco.this.spit(target);
                            }
                        }
                    } else if (this.upTicks > 20 || EntityWarpedMosco.this.m_20270_((Entity)target) < 6.0f) {
                        EntityWarpedMosco.this.m_21566_().m_6849_(target.m_20185_(), target.m_20186_() + (double)(target.m_20192_() * 0.6f), target.m_20189_(), (double)speedRush);
                    } else {
                        EntityWarpedMosco.this.m_21566_().m_6849_(EntityWarpedMosco.this.m_20185_(), EntityWarpedMosco.this.m_20186_() + 3.0, EntityWarpedMosco.this.m_20189_(), 0.5);
                    }
                } else {
                    EntityWarpedMosco.this.m_21573_().m_5624_((Entity)EntityWarpedMosco.this.m_5448_(), 1.25);
                }
                if (EntityWarpedMosco.this.isFlying()) {
                    if (EntityWarpedMosco.this.m_20270_((Entity)target) < 4.3f) {
                        if (this.dashCooldown == 0 || target.m_20096_() || target.m_20077_() || target.m_20069_()) {
                            target.m_6469_(EntityWarpedMosco.this.m_269291_().m_269333_((LivingEntity)EntityWarpedMosco.this), 5.0f);
                            EntityWarpedMosco.this.knockbackRidiculous(target, 1.0f);
                            this.dashCooldown = 30;
                        }
                        float groundHeight = EntityWarpedMosco.this.getMoscoGround(EntityWarpedMosco.this.m_20183_()).m_123342_();
                        if (Math.abs(EntityWarpedMosco.this.m_20186_() - (double)groundHeight) < 3.0 && !EntityWarpedMosco.this.isOverLiquid()) {
                            EntityWarpedMosco.this.timeFlying += 300;
                            EntityWarpedMosco.this.setFlying(false);
                        }
                    }
                } else if (EntityWarpedMosco.this.m_20270_((Entity)target) < 4.0f && EntityWarpedMosco.this.getAnimation() == IAnimatedEntity.NO_ANIMATION) {
                    Animation animation = EntityWarpedMosco.getRandomAttack(EntityWarpedMosco.this.f_19796_);
                    if (animation == ANIMATION_SUCK && target.m_20159_()) {
                        animation = ANIMATION_SLAM;
                    }
                    EntityWarpedMosco.this.setAnimation(animation);
                }
            }
        }

        public BlockPos getAvoidTarget(LivingEntity target) {
            float radius = 10 + EntityWarpedMosco.this.m_217043_().m_188503_(8);
            float angle = (float)Math.PI / 180 * (target.f_20885_ + 90.0f + (float)EntityWarpedMosco.this.m_217043_().m_188503_(180));
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            BlockPos radialPos = AMBlockPos.fromCoords(target.m_20185_() + extraX, target.m_20186_() + 1.0, target.m_20189_() + extraZ);
            BlockPos ground = radialPos;
            if (EntityWarpedMosco.this.m_20238_(Vec3.m_82512_((Vec3i)ground)) > 30.0 && !EntityWarpedMosco.this.isTargetBlocked(Vec3.m_82512_((Vec3i)ground)) && EntityWarpedMosco.this.m_20238_(Vec3.m_82512_((Vec3i)ground)) > 6.0) {
                return ground;
            }
            return EntityWarpedMosco.this.m_20183_();
        }

        public void m_8041_() {
            this.upTicks = 0;
            this.dashCooldown = 0;
            this.ranged = false;
        }
    }

    private class AIWalkIdle
    extends Goal {
        protected final EntityWarpedMosco mosco;
        protected double x;
        protected double y;
        protected double z;
        private boolean flightTarget = false;

        public AIWalkIdle() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.mosco = EntityWarpedMosco.this;
        }

        public boolean m_8036_() {
            if (this.mosco.m_20160_() || this.mosco.m_5448_() != null && this.mosco.m_5448_().m_6084_() || this.mosco.m_20159_()) {
                return false;
            }
            if (this.mosco.m_217043_().m_188503_(30) != 0 && !this.mosco.isFlying()) {
                return false;
            }
            this.flightTarget = this.mosco.m_20096_() ? EntityWarpedMosco.this.f_19796_.m_188503_(8) == 0 : EntityWarpedMosco.this.f_19796_.m_188503_(5) > 0 && this.mosco.timeFlying < 200;
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
                this.mosco.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.mosco.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
            if (!this.flightTarget && EntityWarpedMosco.this.isFlying() && this.mosco.m_20096_()) {
                this.mosco.setFlying(false);
            }
            if (EntityWarpedMosco.this.isFlying() && this.mosco.m_20096_() && this.mosco.timeFlying > 10) {
                this.mosco.setFlying(false);
            }
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vector3d = this.mosco.m_20182_();
            if (this.mosco.isOverLiquid()) {
                this.flightTarget = true;
            }
            if (this.flightTarget) {
                if (this.mosco.timeFlying < 50 || this.mosco.isOverLiquid()) {
                    return this.mosco.getBlockInViewAway(vector3d, 0.0f);
                }
                return this.mosco.getBlockGrounding(vector3d);
            }
            return LandRandomPos.m_148488_((PathfinderMob)this.mosco, (int)20, (int)7);
        }

        public boolean m_8045_() {
            if (this.flightTarget) {
                return this.mosco.isFlying() && this.mosco.m_20275_(this.x, this.y, this.z) > 20.0 && !this.mosco.f_19862_;
            }
            return !this.mosco.m_21573_().m_26571_() && !this.mosco.m_20160_();
        }

        public void m_8056_() {
            if (this.flightTarget) {
                this.mosco.setFlying(true);
                this.mosco.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.mosco.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
        }

        public void m_8041_() {
            this.mosco.m_21573_().m_26573_();
            super.m_8041_();
        }
    }
}
