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
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.AgeableMob$AgeableMobGroupData
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$TagValue
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.IHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ai.GeladaAIGroom;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import java.util.Random;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class EntityGeladaMonkey
extends Animal
implements IAnimatedEntity,
IHerdPanic {
    public static final Animation ANIMATION_SWIPE_R = Animation.create((int)13);
    public static final Animation ANIMATION_SWIPE_L = Animation.create((int)13);
    public static final Animation ANIMATION_GROOM = Animation.create((int)35);
    public static final Animation ANIMATION_CHEST = Animation.create((int)35);
    private static final EntityDataAccessor<Boolean> LEADER = SynchedEntityData.m_135353_(EntityGeladaMonkey.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityGeladaMonkey.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.m_135353_(EntityGeladaMonkey.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> GRASS_TIME = SynchedEntityData.m_135353_(EntityGeladaMonkey.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float prevSitProgress;
    public float sitProgress;
    public boolean isGrooming = false;
    public int groomerID = -1;
    private int animationTick;
    private Animation currentAnimation;
    private int sittingTime;
    private int maxSitTime;
    private int leaderFightTime;
    private HurtByTargetGoal hurtByTargetGoal = null;
    private NearestAttackableTargetGoal<EntityGeladaMonkey> leaderFightGoal = null;
    private int revengeCooldown = 0;
    private boolean hasSpedUp = false;

    protected EntityGeladaMonkey(EntityType type, Level lvl) {
        super(type, lvl);
        this.m_21441_(BlockPathTypes.WATER, -1.0f);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.geladaMonkeySpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 18.0).m_22268_(Attributes.f_22281_, 4.0).m_22268_(Attributes.f_22279_, 0.25);
    }

    public int m_5792_() {
        return 10;
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.GELADA_MONKEY_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.GELADA_MONKEY_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.GELADA_MONKEY_HURT.get();
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.5, true){

            protected double m_6639_(LivingEntity attackTarget) {
                return super.m_6639_(attackTarget) + 1.5;
            }

            public boolean m_8036_() {
                return super.m_8036_() && EntityGeladaMonkey.this.revengeCooldown <= 0;
            }

            public boolean m_8045_() {
                return super.m_8045_() && EntityGeladaMonkey.this.revengeCooldown <= 0;
            }
        });
        this.f_21345_.m_25352_(2, (Goal)new AIClearGrass());
        this.f_21345_.m_25352_(3, (Goal)new AnimalAIHerdPanic((PathfinderMob)this, 1.5));
        this.f_21345_.m_25352_(4, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(5, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(6, (Goal)new TemptGoal((PathfinderMob)this, 1.0, Ingredient.m_43938_(Stream.of(new Ingredient.TagValue(AMTagRegistry.GELADA_MONKEY_BREEDABLES), new Ingredient.TagValue(AMTagRegistry.GELADA_MONKEY_LAND_CLEARING_FOODS))), false));
        this.f_21345_.m_25352_(7, (Goal)new GeladaAIGroom(this));
        this.f_21345_.m_25352_(8, (Goal)new RandomStrollGoal((PathfinderMob)this, 1.0, 120));
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21345_.m_25352_(10, (Goal)new RandomLookAroundGoal((Mob)this));
        this.hurtByTargetGoal = new HurtByTargetGoal((PathfinderMob)this, new Class[]{EntityGeladaMonkey.class}).m_26044_(new Class[0]);
        this.f_21346_.m_25352_(1, (Goal)this.hurtByTargetGoal);
        this.leaderFightGoal = new NearestAttackableTargetGoal((Mob)this, EntityGeladaMonkey.class, 70, false, false, monkey -> this.isLeader() && this.leaderFightTime == 0 && ((EntityGeladaMonkey)monkey).isLeader() && ((EntityGeladaMonkey)monkey).leaderFightTime == 0);
        this.f_21346_.m_25352_(2, (Goal)this.leaderFightGoal);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Leader", this.isLeader());
        compound.m_128405_("GrassTime", this.getClearGrassTime());
        compound.m_128405_("FightTime", this.leaderFightTime);
        compound.m_128379_("MonkeySitting", this.isSitting());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setLeader(compound.m_128471_("Leader"));
        this.setClearGrassTime(compound.m_128451_("GrassTime"));
        this.setSitting(compound.m_128471_("MonkeySitting"));
        this.leaderFightTime = compound.m_128451_("FightTime");
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.GELADA_MONKEY_BREEDABLES);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(LEADER, (Object)false);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(HAS_TARGET, (Object)false);
        this.f_19804_.m_135372_(GRASS_TIME, (Object)0);
    }

    public boolean isLeader() {
        return (Boolean)this.f_19804_.m_135370_(LEADER) != false && !this.m_6162_();
    }

    public void setLeader(boolean leader) {
        this.f_19804_.m_135381_(LEADER, (Object)leader);
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public void setSitting(boolean sit) {
        this.f_19804_.m_135381_(SITTING, (Object)sit);
    }

    public boolean isAggro() {
        return (Boolean)this.f_19804_.m_135370_(HAS_TARGET);
    }

    public void setAggro(boolean sit) {
        this.f_19804_.m_135381_(HAS_TARGET, (Object)sit);
    }

    public int getClearGrassTime() {
        return (Integer)this.f_19804_.m_135370_(GRASS_TIME);
    }

    public void setClearGrassTime(int i) {
        this.f_19804_.m_135381_(GRASS_TIME, (Object)i);
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevSitProgress = this.sitProgress;
        if (this.isSitting()) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (!this.m_9236_().f_46443_) {
            if (this.isSitting() && ++this.sittingTime > this.maxSitTime) {
                this.setSitting(false);
                this.sittingTime = 0;
                this.maxSitTime = 75 + this.f_19796_.m_188503_(50);
            }
            if (this.m_20184_().m_82556_() < 0.03 && this.getAnimation() == NO_ANIMATION && !this.isSitting() && this.f_19796_.m_188503_(500) == 0) {
                this.sittingTime = 0;
                this.maxSitTime = 200 + this.f_19796_.m_188503_(550);
                this.setSitting(true);
            }
            if (this.isSitting() && (this.m_5448_() != null || this.m_27593_())) {
                this.setSitting(false);
            }
            if (this.m_5448_() != null && (this.getAnimation() == ANIMATION_SWIPE_L || this.getAnimation() == ANIMATION_SWIPE_R) && this.getAnimationTick() == 7 && this.m_142582_((Entity)this.m_5448_()) && this.m_20270_((Entity)this.m_5448_()) < this.m_20206_() + this.m_5448_().m_20206_() + 1.0f) {
                EntityGeladaMonkey monkey;
                LivingEntity livingEntity;
                this.m_5448_().m_147240_((double)0.4f, this.m_5448_().m_20185_() - this.m_20185_(), this.m_5448_().m_20189_() - this.m_20189_());
                float dmg = (float)this.m_21051_(Attributes.f_22281_).m_22115_();
                if (this.isLeader() && (livingEntity = this.m_5448_()) instanceof EntityGeladaMonkey && (monkey = (EntityGeladaMonkey)livingEntity).isLeader()) {
                    monkey.m_6710_((LivingEntity)this);
                    monkey.leaderFightTime = this.leaderFightTime;
                    dmg = 0.0f;
                }
                this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), dmg);
            }
            if (this.m_5448_() != null && this.m_5448_().m_6084_()) {
                LivingEntity livingEntity;
                this.setAggro(true);
                if (this.isLeader() && (livingEntity = this.m_5448_()) instanceof EntityGeladaMonkey) {
                    EntityGeladaMonkey monkey = (EntityGeladaMonkey)livingEntity;
                    if (monkey.isLeader()) {
                        ++this.leaderFightTime;
                    }
                    if (this.leaderFightTime < 10 && this.f_19796_.m_188503_(5) == 0 && this.getAnimation() == NO_ANIMATION) {
                        this.setAnimation(ANIMATION_CHEST);
                    }
                    if (Math.max(this.leaderFightTime, monkey.leaderFightTime) >= 250) {
                        this.resetAttackAI();
                        monkey.resetAttackAI();
                    }
                }
            } else {
                this.setAggro(false);
            }
            if (this.leaderFightTime < 0) {
                ++this.leaderFightTime;
            }
        }
        if (this.isAggro()) {
            if (!this.hasSpedUp) {
                this.hasSpedUp = true;
                this.m_6858_(true);
                this.m_21051_(Attributes.f_22279_).m_22100_((double)0.31f);
            }
        } else if (this.hasSpedUp) {
            this.hasSpedUp = false;
            this.m_6858_(false);
            this.m_21051_(Attributes.f_22279_).m_22100_(0.25);
        }
        if (this.getClearGrassTime() > 0) {
            this.setClearGrassTime(this.getClearGrassTime() - 1);
        }
        if (this.getClearGrassTime() < 0) {
            this.setClearGrassTime(this.getClearGrassTime() + 1);
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    private void resetAttackAI() {
        this.leaderFightTime = -500 - this.f_19796_.m_188503_(2000);
        this.m_6710_(null);
        this.m_6703_(null);
        if (this.leaderFightGoal != null) {
            this.leaderFightGoal.m_8041_();
        }
        if (this.hurtByTargetGoal != null) {
            this.hurtByTargetGoal.m_8041_();
        }
    }

    public boolean m_7327_(Entity entityIn) {
        if (this.getAnimation() == NO_ANIMATION) {
            this.attackAnimation();
        }
        return true;
    }

    public float getGeladaScale() {
        return this.m_6162_() ? 0.5f : (this.isLeader() ? 1.15f : 1.0f);
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
        return new Animation[]{ANIMATION_SWIPE_R, ANIMATION_SWIPE_L, ANIMATION_GROOM, ANIMATION_CHEST};
    }

    public boolean m_6469_(DamageSource source, float amount) {
        Entity direct;
        boolean prev = super.m_6469_(source, amount);
        if (prev && (direct = source.m_7639_()) instanceof EntityGeladaMonkey) {
            int fleeTime;
            this.revengeCooldown = fleeTime = 100 + this.m_217043_().m_188503_(5);
            this.revengeCooldown = 10 + this.m_217043_().m_188503_(30);
        }
        return prev;
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        if (itemstack.m_204117_(AMTagRegistry.GELADA_MONKEY_LAND_CLEARING_FOODS) && this.getClearGrassTime() == 0) {
            this.m_142075_(player, hand, itemstack);
            this.eatGrassWithBuddies(3 + this.f_19796_.m_188503_(2));
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel lvl, AgeableMob mob) {
        EntityGeladaMonkey baby = (EntityGeladaMonkey)((EntityType)AMEntityRegistry.GELADA_MONKEY.get()).m_20615_((Level)lvl);
        baby.setLeader(this.f_19796_.m_188503_(2) == 0);
        return baby;
    }

    public void eatGrassWithBuddies(int otherMonkies) {
        int i = 300 + this.f_19796_.m_188503_(300);
        this.setClearGrassTime(i);
        int monky = 0;
        for (EntityGeladaMonkey entity : this.m_9236_().m_45976_(EntityGeladaMonkey.class, this.m_20191_().m_82400_(15.0))) {
            if (monky >= otherMonkies || entity.m_19879_() == this.m_19879_() || entity.shouldStopBeingGroomed()) continue;
            ++monky;
            entity.setClearGrassTime(i);
        }
    }

    @Override
    public void onPanic() {
    }

    @Override
    public boolean canPanic() {
        return this.m_21188_() instanceof EntityGeladaMonkey && this.f_19796_.m_188503_(3) == 0;
    }

    public void m_7023_(Vec3 vec3d) {
        if (this.isSitting() || this.getAnimation() == ANIMATION_CHEST) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            vec3d = Vec3.f_82478_;
        }
        super.m_7023_(vec3d);
    }

    @javax.annotation.Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData spawnDataIn, @javax.annotation.Nullable CompoundTag dataTag) {
        if (spawnDataIn instanceof AgeableMob.AgeableMobGroupData) {
            AgeableMob.AgeableMobGroupData pack = (AgeableMob.AgeableMobGroupData)spawnDataIn;
            if (pack.m_146777_() == 0 || pack.m_146777_() > 4 && this.f_19796_.m_188503_(2) == 0) {
                this.setLeader(true);
            }
        } else {
            this.setLeader(this.m_217043_().m_188503_(4) == 0);
        }
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public boolean canBeGroomed() {
        return this.groomerID == -1;
    }

    public boolean shouldStopBeingGroomed() {
        return this.m_5448_() != null && this.m_5448_().m_6084_() || this.m_27593_() || this.revengeCooldown > 0;
    }

    private void attackAnimation() {
        this.setAnimation(this.f_19796_.m_188499_() ? ANIMATION_SWIPE_L : ANIMATION_SWIPE_R);
    }

    private class AIClearGrass
    extends Goal {
        private BlockPos target;

        public AIClearGrass() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            if (EntityGeladaMonkey.this.getClearGrassTime() > 0) {
                this.target = this.generateTarget();
                return this.target != null;
            }
            return false;
        }

        public boolean m_8045_() {
            return this.target != null && EntityGeladaMonkey.this.m_9236_().m_8055_(this.target).m_204336_(AMTagRegistry.GELADA_MONKEY_GRASS);
        }

        public void m_8037_() {
            EntityGeladaMonkey.this.setSitting(false);
            EntityGeladaMonkey.this.m_21573_().m_26519_((double)((float)this.target.m_123341_() + 0.5f), (double)((float)this.target.m_123342_() + 0.5f), (double)((float)this.target.m_123343_() + 0.5f), (double)1.4f);
            if (EntityGeladaMonkey.this.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < (double)3.4f) {
                if (EntityGeladaMonkey.this.getAnimation() == IAnimatedEntity.NO_ANIMATION) {
                    EntityGeladaMonkey.this.attackAnimation();
                } else if (EntityGeladaMonkey.this.getAnimationTick() > 7) {
                    EntityGeladaMonkey.this.m_9236_().m_46961_(this.target, true);
                }
            }
        }

        public BlockPos generateTarget() {
            BlockPos blockpos = null;
            Random random = new Random();
            int range = 7;
            for (int i = 0; i < 15; ++i) {
                BlockPos blockpos1 = EntityGeladaMonkey.this.m_20183_().m_7918_(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
                while (EntityGeladaMonkey.this.m_9236_().m_46859_(blockpos1) && blockpos1.m_123342_() > -63) {
                    blockpos1 = blockpos1.m_7495_();
                }
                if (!EntityGeladaMonkey.this.m_9236_().m_8055_(blockpos1).m_204336_(AMTagRegistry.GELADA_MONKEY_GRASS)) continue;
                blockpos = blockpos1;
            }
            return blockpos;
        }
    }
}
