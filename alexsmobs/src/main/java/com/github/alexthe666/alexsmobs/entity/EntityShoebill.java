/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ItemParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Turtle
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityAlligatorSnappingTurtle;
import com.github.alexthe666.alexsmobs.entity.EntityCaiman;
import com.github.alexthe666.alexsmobs.entity.EntityCrocodile;
import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWadeSwimming;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.ShoebillAIFish;
import com.github.alexthe666.alexsmobs.entity.ai.ShoebillAIFlightFlee;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityShoebill
extends Animal
implements IAnimatedEntity,
ITargetsDroppedItems {
    public static final Animation ANIMATION_FISH = Animation.create((int)40);
    public static final Animation ANIMATION_BEAKSHAKE = Animation.create((int)20);
    public static final Animation ANIMATION_ATTACK = Animation.create((int)20);
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityShoebill.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float prevFlyProgress;
    public float flyProgress;
    public int revengeCooldown = 0;
    private int animationTick;
    private Animation currentAnimation;
    private boolean isLandNavigator;
    public int fishingCooldown = 1200 + this.f_19796_.m_188503_(1200);
    public int lureLevel = 0;
    public int luckLevel = 0;
    public static final Predicate<LivingEntity> TARGET_BABY = animal -> animal.m_6162_();

    protected EntityShoebill(EntityType type, Level world) {
        super(type, world);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
        this.switchNavigator(false);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.shoebillSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 10.0).m_22268_(Attributes.f_22281_, 4.0).m_22268_(Attributes.f_22279_, (double)0.2f);
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.SHOEBILL_HURT.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.SHOEBILL_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.SHOEBILL_HURT.get();
    }

    public boolean m_6898_(ItemStack stack) {
        return false;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev && source.m_7639_() != null && !(source.m_7639_() instanceof AbstractFish)) {
            int fleeTime;
            double range = 15.0;
            this.revengeCooldown = fleeTime = 100 + this.m_217043_().m_188503_(150);
            List list = this.m_9236_().m_45976_(this.getClass(), this.m_20191_().m_82377_(range, range / 2.0, range));
            for (EntityShoebill gaz : list) {
                gaz.revengeCooldown = fleeTime;
            }
        }
        return prev;
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
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new AnimalAIWadeSwimming((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new ShoebillAIFish(this));
        this.f_21345_.m_25352_(3, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.2, true));
        this.f_21345_.m_25352_(4, (Goal)new ShoebillAIFlightFlee(this));
        this.f_21345_.m_25352_(5, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_204132_(AMTagRegistry.SHOEBILL_FOODSTUFFS), false));
        this.f_21345_.m_25352_(6, (Goal)new RandomStrollGoal((PathfinderMob)this, 1.0, 1400));
        this.f_21345_.m_25352_(7, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(7, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21346_.m_25352_(1, new EntityAINearestTarget3D<AbstractFish>((Mob)this, AbstractFish.class, 30, false, true, null));
        this.f_21346_.m_25352_(2, new CreatureAITargetItems((PathfinderMob)this, false, 10));
        this.f_21346_.m_25352_(3, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[]{Player.class}).m_26044_(new Class[0]));
        this.f_21346_.m_25352_(4, (Goal)new NearestAttackableTargetGoal((Mob)this, EntityAlligatorSnappingTurtle.class, 40, false, false, TARGET_BABY));
        this.f_21346_.m_25352_(5, (Goal)new NearestAttackableTargetGoal((Mob)this, Turtle.class, 40, false, false, TARGET_BABY));
        this.f_21346_.m_25352_(6, (Goal)new NearestAttackableTargetGoal((Mob)this, EntityCrocodile.class, 40, false, false, TARGET_BABY));
        this.f_21346_.m_25352_(7, (Goal)new NearestAttackableTargetGoal((Mob)this, EntityCaiman.class, 40, false, false, TARGET_BABY));
        this.f_21346_.m_25352_(8, new EntityAINearestTarget3D<EntityTerrapin>((Mob)this, EntityTerrapin.class, 100, false, true, null));
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public void m_8119_() {
        super.m_8119_();
        if (this.m_20069_()) {
            this.m_274367_(1.2f);
        } else {
            this.m_274367_(0.6f);
        }
        this.prevFlyProgress = this.flyProgress;
        boolean flying = this.isFlying();
        if (flying) {
            if (this.flyProgress < 5.0f) {
                this.flyProgress += 1.0f;
            }
        } else if (this.flyProgress > 0.0f) {
            this.flyProgress -= 1.0f;
        }
        if (this.revengeCooldown > 0) {
            --this.revengeCooldown;
        }
        if (this.revengeCooldown == 0 && this.m_21188_() != null) {
            this.m_6703_(null);
        }
        if (!this.m_9236_().f_46443_) {
            if (this.fishingCooldown > 0) {
                --this.fishingCooldown;
            }
            if (this.getAnimation() == NO_ANIMATION && this.m_217043_().m_188503_(700) == 0) {
                this.setAnimation(ANIMATION_BEAKSHAKE);
            }
            if (flying) {
                if (this.isLandNavigator) {
                    this.switchNavigator(false);
                }
            } else if (!this.isLandNavigator) {
                this.switchNavigator(true);
            }
            if (this.revengeCooldown > 0 && !this.isFlying() && (this.m_20096_() || this.m_20069_())) {
                this.setFlying(false);
            }
            if (this.isFlying()) {
                this.m_20242_(true);
            } else {
                this.m_20242_(false);
            }
        }
        if (!this.m_9236_().f_46443_ && this.m_5448_() != null && this.getAnimation() == ANIMATION_ATTACK && this.getAnimationTick() == 9 && this.m_142582_((Entity)this.m_5448_())) {
            this.m_5448_().m_147240_((double)0.3f, this.m_5448_().m_20185_() - this.m_20185_(), this.m_5448_().m_20189_() - this.m_20189_());
            this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21051_(Attributes.f_22281_).m_22115_());
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Flying", this.isFlying());
        compound.m_128405_("FishingTimer", this.fishingCooldown);
        compound.m_128405_("FishingLuck", this.luckLevel);
        compound.m_128405_("FishingLure", this.lureLevel);
        compound.m_128405_("RevengeCooldownTimer", this.revengeCooldown);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setFlying(compound.m_128471_("Flying"));
        this.fishingCooldown = compound.m_128451_("FishingTimer");
        this.luckLevel = compound.m_128451_("FishingLuck");
        this.lureLevel = compound.m_128451_("FishingLure");
        this.revengeCooldown = compound.m_128451_("RevengeCooldownTimer");
    }

    protected float m_6108_() {
        return 0.98f;
    }

    public boolean m_7327_(Entity entityIn) {
        if (this.getAnimation() == NO_ANIMATION) {
            this.setAnimation(ANIMATION_ATTACK);
        }
        return true;
    }

    @Override
    public boolean isFlying() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    @Override
    public void setFlying(boolean flying) {
        this.f_19804_.m_135381_(FLYING, (Object)flying);
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
        return new Animation[]{ANIMATION_FISH, ANIMATION_BEAKSHAKE, ANIMATION_ATTACK};
    }

    public InteractionResult m_6071_(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack lvt_3_1_ = p_230254_1_.m_21120_(p_230254_2_);
        if (lvt_3_1_.m_204117_(AMTagRegistry.SHOEBILL_LUCK_FOODS) && this.m_6084_()) {
            if (this.luckLevel < 10) {
                this.luckLevel = Mth.m_14045_((int)(this.luckLevel + 1), (int)0, (int)10);
                for (int i = 0; i < 6 + this.f_19796_.m_188503_(3); ++i) {
                    double d2 = this.f_19796_.m_188583_() * 0.02;
                    double d0 = this.f_19796_.m_188583_() * 0.02;
                    double d1 = this.f_19796_.m_188583_() * 0.02;
                    this.m_9236_().m_7106_((ParticleOptions)new ItemParticleOption(ParticleTypes.f_123752_, lvt_3_1_), this.m_20185_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, this.m_20186_() + (double)(this.m_20206_() * 0.5f) + (double)(this.f_19796_.m_188501_() * this.m_20206_() * 0.5f), this.m_20189_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, d0, d1, d2);
                }
                this.m_146850_(GameEvent.f_157806_);
                this.m_5496_(SoundEvents.f_11788_, this.m_6121_(), this.m_6100_());
                lvt_3_1_.m_41774_(1);
                return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
            }
            if (this.getAnimation() == NO_ANIMATION) {
                this.setAnimation(ANIMATION_BEAKSHAKE);
            }
            return InteractionResult.SUCCESS;
        }
        if (lvt_3_1_.m_204117_(AMTagRegistry.SHOEBILL_LURE_FOODS) && this.m_6084_()) {
            if (this.lureLevel < 10) {
                this.lureLevel = Mth.m_14045_((int)(this.lureLevel + 1), (int)0, (int)10);
                this.fishingCooldown = Mth.m_14045_((int)(this.fishingCooldown - 200), (int)200, (int)2400);
                for (int i = 0; i < 6 + this.f_19796_.m_188503_(3); ++i) {
                    double d2 = this.f_19796_.m_188583_() * 0.02;
                    double d0 = this.f_19796_.m_188583_() * 0.02;
                    double d1 = this.f_19796_.m_188583_() * 0.02;
                    this.m_9236_().m_7106_((ParticleOptions)new ItemParticleOption(ParticleTypes.f_123752_, lvt_3_1_), this.m_20185_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, this.m_20186_() + (double)(this.m_20206_() * 0.5f) + (double)(this.f_19796_.m_188501_() * this.m_20206_() * 0.5f), this.m_20189_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, d0, d1, d2);
                }
                lvt_3_1_.m_41774_(1);
                this.m_146850_(GameEvent.f_157806_);
                this.m_5496_(SoundEvents.f_11788_, this.m_6121_(), this.m_6100_());
                return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
            }
            if (this.getAnimation() == NO_ANIMATION) {
                this.setAnimation(ANIMATION_BEAKSHAKE);
            }
            return InteractionResult.SUCCESS;
        }
        return super.m_6071_(p_230254_1_, p_230254_2_);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return (AgeableMob)((EntityType)AMEntityRegistry.SHOEBILL.get()).m_20615_((Level)serverWorld);
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.SHOEBILL_FOODSTUFFS) || stack.m_204117_(AMTagRegistry.SHOEBILL_LUCK_FOODS) && this.luckLevel < 10 || stack.m_204117_(AMTagRegistry.SHOEBILL_LURE_FOODS) && this.lureLevel < 10;
    }

    public void resetFishingCooldown() {
        this.fishingCooldown = Math.max(1200 + this.f_19796_.m_188503_(1200) - this.lureLevel * 120, 200);
    }

    @Override
    public void onGetItem(ItemEntity e) {
        this.m_146850_(GameEvent.f_157806_);
        this.m_5496_(SoundEvents.f_11788_, this.m_6121_(), this.m_6100_());
        if (e.m_32055_().m_204117_(AMTagRegistry.SHOEBILL_LUCK_FOODS)) {
            this.luckLevel = Mth.m_14045_((int)(this.luckLevel + 1), (int)0, (int)10);
        } else if (e.m_32055_().m_204117_(AMTagRegistry.SHOEBILL_LURE_FOODS)) {
            this.lureLevel = Mth.m_14045_((int)(this.lureLevel + 1), (int)0, (int)10);
        }
        this.m_5634_(5.0f);
    }
}
