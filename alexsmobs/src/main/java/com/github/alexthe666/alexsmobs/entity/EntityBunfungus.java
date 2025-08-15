/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ItemParticleOption
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
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeapRandomly;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.BunfungusAIBeg;
import com.github.alexthe666.alexsmobs.entity.ai.BunfungusAIMelee;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EntityBunfungus
extends PathfinderMob
implements IAnimatedEntity {
    public static final Animation ANIMATION_SLAM = Animation.create((int)20);
    public static final Animation ANIMATION_BELLY = Animation.create((int)10);
    public static final Animation ANIMATION_EAT = Animation.create((int)20);
    private static final EntityDataAccessor<Boolean> JUMP_ACTIVE = SynchedEntityData.m_135353_(EntityBunfungus.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.m_135353_(EntityBunfungus.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> BEGGING = SynchedEntityData.m_135353_(EntityBunfungus.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> CARROTED = SynchedEntityData.m_135353_(EntityBunfungus.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> TRANSFORMS_IN = SynchedEntityData.m_135353_(EntityBunfungus.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float jumpProgress;
    public float prevJumpProgress;
    public float reboundProgress;
    public float prevReboundProgress;
    public float sleepProgress;
    public float prevSleepProgress;
    public float interestedProgress;
    public float prevInterestedProgress;
    private int animationTick;
    private Animation currentAnimation;
    public int prevTransformTime;
    public static final int MAX_TRANSFORM_TIME = 50;

    protected EntityBunfungus(EntityType t, Level lvl) {
        super(t, lvl);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 80.0).m_22268_(Attributes.f_22281_, 8.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22279_, (double)0.21f);
    }

    public void m_8032_() {
        if (!this.m_5803_()) {
            super.m_8032_();
        }
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.BUNFUNGUS_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.BUNFUNGUS_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.BUNFUNGUS_HURT.get();
    }

    public boolean m_6785_(double p_27598_) {
        return false;
    }

    public static boolean canBunfungusSpawn(EntityType type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn) {
        return worldIn.m_8055_(pos.m_7495_()).m_60815_();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.mungusSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new GroundPathNavigatorWide((Mob)this, worldIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new BunfungusAIMelee(this));
        this.f_21345_.m_25352_(2, (Goal)new BunfungusAIBeg(this, 1.0));
        this.f_21345_.m_25352_(3, (Goal)new AnimalAIWanderRanged(this, 60, 1.0, 16, 7){

            @Override
            public boolean m_8036_() {
                return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
            }
        });
        this.f_21345_.m_25352_(4, (Goal)new AnimalAILeapRandomly(this, 60, 7){

            @Override
            public boolean m_8036_() {
                return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
            }
        });
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 10.0f){

            public boolean m_8036_() {
                return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
            }
        });
        this.f_21345_.m_25352_(10, (Goal)new RandomLookAroundGoal((Mob)this){

            public boolean m_8036_() {
                return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
            }
        });
        this.f_21346_.m_25352_(2, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(3, (Goal)new NearestAttackableTargetGoal((Mob)this, Mob.class, 5, false, false, mob -> mob instanceof Enemy && !(mob instanceof Creeper) && (mob.m_6336_() != MobType.f_21644_ || !mob.m_20072_()) && !mob.m_6095_().m_204039_(AMTagRegistry.BUNFUNGUS_IGNORES)));
    }

    private boolean canUseComplexAI() {
        return !this.isRabbitForm() && !this.m_5803_();
    }

    protected float m_6108_() {
        return 0.98f;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(JUMP_ACTIVE, (Object)false);
        this.f_19804_.m_135372_(SLEEPING, (Object)false);
        this.f_19804_.m_135372_(BEGGING, (Object)false);
        this.f_19804_.m_135372_(CARROTED, (Object)false);
        this.f_19804_.m_135372_(TRANSFORMS_IN, (Object)0);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public void m_8119_() {
        double d2;
        super.m_8119_();
        this.prevJumpProgress = this.jumpProgress;
        this.prevReboundProgress = this.reboundProgress;
        this.prevSleepProgress = this.sleepProgress;
        this.prevInterestedProgress = this.interestedProgress;
        this.prevTransformTime = this.transformsIn();
        if (!this.m_9236_().f_46443_) {
            this.f_19804_.m_135381_(JUMP_ACTIVE, (Object)(!this.m_20096_() ? 1 : 0));
        }
        if (((Boolean)this.f_19804_.m_135370_(JUMP_ACTIVE)).booleanValue() && !this.m_20072_()) {
            if (this.jumpProgress < 5.0f) {
                this.jumpProgress += 0.5f;
                if (this.reboundProgress > 0.0f) {
                    this.reboundProgress -= 1.0f;
                }
            }
            if (this.jumpProgress >= 5.0f && this.reboundProgress < 5.0f) {
                this.reboundProgress += 0.5f;
            }
        } else {
            if (this.reboundProgress > 0.0f) {
                this.reboundProgress = Math.max(this.reboundProgress - 1.0f, 0.0f);
            }
            if (this.jumpProgress > 0.0f) {
                this.jumpProgress = Math.max(this.jumpProgress - 1.0f, 0.0f);
            }
        }
        if (this.isSleepingPose()) {
            if (this.sleepProgress < 5.0f) {
                this.sleepProgress += 1.0f;
            }
        } else if (this.sleepProgress > 0.0f) {
            this.sleepProgress -= 1.0f;
        }
        if (this.isBegging()) {
            if (this.interestedProgress < 5.0f) {
                this.interestedProgress += 1.0f;
            }
        } else if (this.interestedProgress > 0.0f) {
            this.interestedProgress -= 1.0f;
        }
        if (!this.m_9236_().f_46443_) {
            LivingEntity target = this.m_5448_();
            if (target != null && target.m_6084_()) {
                if (this.m_5803_()) {
                    this.setSleeping(false);
                }
                double dist = this.m_20270_((Entity)target);
                boolean flag = false;
                if (this.getAnimationTick() == 5) {
                    if (dist < 3.5 && this.getAnimation() == ANIMATION_BELLY) {
                        for (LivingEntity entity : this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82400_(2.0))) {
                            if (entity != target && !(entity instanceof Monster) || entity.m_6095_().m_204039_(AMTagRegistry.BUNFUNGUS_IGNORE_AOE_ATTACKS)) continue;
                            flag = true;
                            this.launch(entity);
                            entity.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21051_(Attributes.f_22281_).m_22115_());
                        }
                    } else if (dist < 2.5 && this.getAnimation() == ANIMATION_SLAM) {
                        for (LivingEntity entity : this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82400_(2.0))) {
                            if (entity != target && !(entity instanceof Monster) || entity.m_6095_().m_204039_(AMTagRegistry.BUNFUNGUS_IGNORE_AOE_ATTACKS)) continue;
                            flag = true;
                            entity.m_147240_((double)0.2f, entity.m_20185_() - this.m_20185_(), entity.m_20189_() - this.m_20189_());
                            entity.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21051_(Attributes.f_22281_).m_22115_());
                        }
                    }
                }
                if (flag) {
                    this.m_5496_((SoundEvent)AMSoundRegistry.BUNFUNGUS_ATTACK.get(), this.m_6121_(), this.m_6100_());
                }
            }
            if (this.f_19797_ % 40 == 0) {
                this.m_5634_(1.0f);
            }
        }
        if (this.getAnimation() == NO_ANIMATION && this.isCarrot(this.m_21120_(InteractionHand.MAIN_HAND))) {
            this.setAnimation(ANIMATION_EAT);
        }
        if (this.getAnimation() == ANIMATION_EAT) {
            if (this.getAnimationTick() % 4 == 0) {
                this.m_146850_(GameEvent.f_157806_);
                this.m_5496_(SoundEvents.f_11912_, this.m_6121_(), this.m_6100_());
            }
            if (this.getAnimationTick() >= 18) {
                ItemStack stack = this.m_21120_(InteractionHand.MAIN_HAND);
                if (!stack.m_41619_()) {
                    stack.m_41774_(1);
                    this.setCarroted(true);
                    this.m_7292_(new MobEffectInstance(MobEffects.f_19600_, 1000));
                    this.m_7292_(new MobEffectInstance(MobEffects.f_19605_, 1000, 1));
                    this.m_5634_(8.0f);
                }
            } else {
                for (int i = 0; i < 3; ++i) {
                    d2 = this.f_19796_.m_188583_() * 0.02;
                    double d0 = this.f_19796_.m_188583_() * 0.02;
                    double d1 = this.f_19796_.m_188583_() * 0.02;
                    this.m_9236_().m_7106_((ParticleOptions)new ItemParticleOption(ParticleTypes.f_123752_, this.m_21120_(InteractionHand.MAIN_HAND)), this.m_20185_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, this.m_20186_() + (double)(this.m_20206_() * 0.5f) + (double)(this.f_19796_.m_188501_() * this.m_20206_() * 0.5f), this.m_20189_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, d0, d1, d2);
                }
            }
        }
        if (!this.m_9236_().f_46443_ && this.transformsIn() > 0) {
            this.setTransformsIn(this.transformsIn() - 1);
        }
        if (this.m_9236_().f_46443_) {
            if (this.isRabbitForm()) {
                for (int i = 0; i < 3; ++i) {
                    d2 = this.f_19796_.m_188583_() * 0.02;
                    double d0 = this.f_19796_.m_188583_() * 0.02;
                    double d1 = this.f_19796_.m_188583_() * 0.02;
                    float f1 = (float)(50 - this.transformsIn()) / 50.0f;
                    float scale = f1 * 0.5f + 0.15f;
                    this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.BUNFUNGUS_TRANSFORMATION.get(), this.m_20208_(scale), this.m_20227_(this.f_19796_.m_188500_() * (double)scale), this.m_20262_(scale), d0, d1, d2);
                }
            }
            if (this.m_5803_() && this.f_19796_.m_188501_() < 0.3f) {
                double d0 = this.f_19796_.m_188583_() * 0.02;
                float radius = this.m_20205_() * (0.7f + this.f_19796_.m_188501_() * 0.1f);
                float angle = (float)Math.PI / 180 * this.f_20883_;
                double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle)) + this.f_19796_.m_188501_() * 0.5f - 0.25f;
                double extraZ = radius * Mth.m_14089_((float)angle) + this.f_19796_.m_188501_() * 0.5f - 0.25f;
                ParticleOptions data = this.f_19796_.m_188501_() < 0.3f ? (ParticleOptions)AMParticleRegistry.BUNFUNGUS_TRANSFORMATION.get() : (ParticleOptions)AMParticleRegistry.FUNGUS_BUBBLE.get();
                this.m_9236_().m_7106_(data, this.m_20185_() + extraX, this.m_20186_() + (double)(this.f_19796_.m_188501_() * 0.1f), this.m_20189_() + extraZ, 0.0, d0, 0.0);
            }
        } else if (this.m_9236_().m_46461_() && this.m_5448_() == null && !this.isBegging() && !this.m_20072_()) {
            if (this.f_19797_ % 10 == 0 && this.m_217043_().m_188503_(300) == 0) {
                this.setSleeping(true);
            }
        } else if (this.m_5803_()) {
            this.setSleeping(false);
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    private void launch(LivingEntity target) {
        if (target.m_20096_()) {
            double d0 = target.m_20185_() - this.m_20185_();
            double d1 = target.m_20189_() - this.m_20189_();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001);
            float f = 6.0f + this.f_19796_.m_188501_() * 2.0f;
            target.m_5997_(d0 / d2 * (double)f, (double)(0.6f + this.f_19796_.m_188501_() * 0.7f), d1 / d2 * (double)f);
        }
    }

    public boolean m_5803_() {
        return (Boolean)this.f_19804_.m_135370_(SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.f_19804_.m_135381_(SLEEPING, (Object)sleeping);
    }

    public boolean isSleepingPose() {
        return this.m_5803_() || this.getAnimation() == ANIMATION_SLAM && this.getAnimationTick() < 10;
    }

    public boolean isCarroted() {
        return (Boolean)this.f_19804_.m_135370_(CARROTED);
    }

    public void setCarroted(boolean head) {
        this.f_19804_.m_135381_(CARROTED, (Object)head);
    }

    public boolean isBegging() {
        return (Boolean)this.f_19804_.m_135370_(BEGGING) != false && this.getAnimation() != ANIMATION_EAT;
    }

    public void setBegging(boolean begging) {
        this.f_19804_.m_135381_(BEGGING, (Object)begging);
    }

    public int transformsIn() {
        return Math.min((Integer)this.f_19804_.m_135370_(TRANSFORMS_IN), 50);
    }

    public boolean isRabbitForm() {
        return this.transformsIn() > 0;
    }

    public void setTransformsIn(int time) {
        this.f_19804_.m_135381_(TRANSFORMS_IN, (Object)time);
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
        if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS && this.m_21120_(InteractionHand.MAIN_HAND).m_41619_() && this.isCarrot(itemstack) && this.m_21205_().m_41619_()) {
            ItemStack cop = itemstack.m_41777_();
            cop.m_41764_(1);
            this.m_21008_(InteractionHand.MAIN_HAND, cop);
            if (!player.m_7500_()) {
                itemstack.m_41774_(1);
            }
        }
        return type;
    }

    public void m_7023_(Vec3 travelVector) {
        if (!this.isRabbitForm() && !this.m_5803_()) {
            super.m_7023_(travelVector);
        } else {
            super.m_7023_(Vec3.f_82478_);
        }
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_EAT, ANIMATION_BELLY, ANIMATION_SLAM};
    }

    public boolean isCarrot(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.BUNFUNGUS_FOODSTUFFS);
    }

    public boolean defendsMungusAgainst(LivingEntity lastHurtByMob) {
        return !(lastHurtByMob instanceof Player) || this.isCarroted();
    }

    public void onJump() {
    }
}
