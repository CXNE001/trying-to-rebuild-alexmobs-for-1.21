/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
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
 *  net.minecraft.util.TimeUtil
 *  net.minecraft.util.valueproviders.UniformInt
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
 *  net.minecraft.world.entity.NeutralMob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.gameevent.GameEvent
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHurtByTargetNotBaby;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIRideParent;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.AnteaterAIRaidNest;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
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
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;

public class EntityAnteater
extends Animal
implements NeutralMob,
IAnimatedEntity,
ITargetsDroppedItems {
    public static final Animation ANIMATION_SLASH_R = Animation.create((int)20);
    public static final Animation ANIMATION_TOUNGE_IDLE = Animation.create((int)10);
    public static final Animation ANIMATION_SLASH_L = Animation.create((int)20);
    private static final EntityDataAccessor<Boolean> STANDING = SynchedEntityData.m_135353_(EntityAnteater.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> LEANING_DOWN = SynchedEntityData.m_135353_(EntityAnteater.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> ANT_ON_TONGUE = SynchedEntityData.m_135353_(EntityAnteater.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.m_135353_(EntityAnteater.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float prevStandProgress;
    public float standProgress;
    public float prevTongueProgress;
    public float tongueProgress;
    public float prevLeaningProgress;
    public float leaningProgress;
    public int eatAntCooldown = 0;
    public int ticksAntOnTongue = 0;
    private int animationTick;
    private Animation currentAnimation;
    private int maxStandTime = 75;
    private int standingTime = 0;
    private int antsEatenRecently = 0;
    private int heldItemTime;
    private UUID lastHurtBy;
    private static final UniformInt ANGRY_TIMER = TimeUtil.m_145020_((int)30, (int)60);

    protected EntityAnteater(EntityType type, Level world) {
        super(type, world);
        this.m_274367_(1.0f);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 20.0).m_22268_(Attributes.f_22281_, 6.0).m_22268_(Attributes.f_22279_, 0.25);
    }

    public static boolean canAnteaterSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return worldIn.m_45524_(pos, 0) > 8;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.anteaterSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(2, (Goal)new AIMelee());
        this.f_21345_.m_25352_(3, (Goal)new AnteaterAIRaidNest(this));
        this.f_21345_.m_25352_(4, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(5, (Goal)new AnimalAIRideParent(this, 1.25));
        this.f_21345_.m_25352_(6, (Goal)new TemptGoal((PathfinderMob)this, 1.2, Ingredient.m_204132_(AMTagRegistry.ANTEATER_FOODSTUFFS), false));
        this.f_21345_.m_25352_(7, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 110, 1.0, 10, 7));
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 10.0f));
        this.f_21345_.m_25352_(9, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, new CreatureAITargetItems((PathfinderMob)this, false, false, 25, 16));
        this.f_21346_.m_25352_(2, (Goal)new AnimalAIHurtByTargetNotBaby(this, new Class[0]));
        this.f_21346_.m_25352_(3, (Goal)new AITargetAnts());
    }

    public boolean m_6673_(DamageSource source) {
        return super.m_6673_(source) || source.m_7640_() != null && source.m_7640_() instanceof EntityLeafcutterAnt;
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.ANTEATER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.ANTEATER_HURT.get();
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Standing", this.isStanding());
        compound.m_128405_("AntCooldown", this.eatAntCooldown);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setStanding(compound.m_128471_("Standing"));
        this.eatAntCooldown = compound.m_128451_("AntCooldown");
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.ANTEATER_BREEDABLES);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(STANDING, (Object)Boolean.FALSE);
        this.f_19804_.m_135372_(ANT_ON_TONGUE, (Object)Boolean.FALSE);
        this.f_19804_.m_135372_(LEANING_DOWN, (Object)Boolean.FALSE);
        this.f_19804_.m_135372_(ANGER_TIME, (Object)0);
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

    public boolean isStanding() {
        return (Boolean)this.f_19804_.m_135370_(STANDING);
    }

    public void setStanding(boolean standing) {
        this.f_19804_.m_135381_(STANDING, (Object)standing);
    }

    public boolean hasAntOnTongue() {
        return (Boolean)this.f_19804_.m_135370_(ANT_ON_TONGUE);
    }

    public void setAntOnTongue(boolean standing) {
        this.f_19804_.m_135381_(ANT_ON_TONGUE, (Object)standing);
    }

    public boolean m_7337_(Entity entity) {
        return !(entity instanceof EntityLeafcutterAnt) && super.m_7337_(entity);
    }

    public void m_7334_(Entity entity) {
        if (!(entity instanceof EntityLeafcutterAnt)) {
            super.m_7334_(entity);
        }
    }

    public boolean isLeaning() {
        return (Boolean)this.f_19804_.m_135370_(LEANING_DOWN);
    }

    public void setLeaning(boolean leaning) {
        this.f_19804_.m_135381_(LEANING_DOWN, (Object)leaning);
    }

    protected boolean m_6107_() {
        return super.m_6107_();
    }

    protected void m_8024_() {
        if (!this.m_9236_().f_46443_) {
            this.m_21666_((ServerLevel)this.m_9236_(), false);
        }
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        boolean isFoodstuff = itemstack.m_204117_(AMTagRegistry.ANTEATER_FOODSTUFFS);
        if (isFoodstuff) {
            ItemStack rippedStack = itemstack.m_41777_();
            rippedStack.m_41764_(1);
            this.m_21662_();
            this.m_5634_(4.0f);
            this.m_21008_(InteractionHand.MAIN_HAND, rippedStack);
            if (itemstack.m_204117_(AMTagRegistry.ANTEATER_BREEDABLES)) {
                return type;
            }
            this.m_142075_(player, hand, itemstack);
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    public void m_8119_() {
        Object object;
        boolean isTongueOut;
        super.m_8119_();
        this.prevStandProgress = this.standProgress;
        this.prevTongueProgress = this.tongueProgress;
        this.prevLeaningProgress = this.leaningProgress;
        if (this.isStanding()) {
            if (this.standProgress < 5.0f) {
                this.standProgress += 1.0f;
            }
        } else if (this.standProgress > 0.0f) {
            this.standProgress -= 1.0f;
        }
        boolean bl = isTongueOut = this.getAnimation() == ANIMATION_TOUNGE_IDLE;
        if (isTongueOut) {
            if (this.tongueProgress < 5.0f) {
                this.tongueProgress += 1.0f;
            }
        } else if (this.tongueProgress > 0.0f) {
            this.tongueProgress -= 1.0f;
        }
        if (this.isLeaning()) {
            if (this.leaningProgress < 5.0f) {
                this.leaningProgress += 1.0f;
            }
        } else if (this.leaningProgress > 0.0f) {
            this.leaningProgress -= 1.0f;
        }
        if (this.isStanding() && ++this.standingTime > this.maxStandTime) {
            this.setStanding(false);
            this.standingTime = 0;
            this.maxStandTime = 75 + this.f_19796_.m_188503_(50);
        }
        if (this.m_20159_() && (object = this.m_20202_()) instanceof EntityAnteater) {
            EntityAnteater mount = (EntityAnteater)object;
            if (this.m_6162_()) {
                this.m_146922_(mount.f_20883_);
                this.f_20885_ = mount.f_20883_;
                this.f_20883_ = mount.f_20883_;
            } else {
                this.m_6038_();
            }
        }
        if (this.eatAntCooldown > 0) {
            --this.eatAntCooldown;
        }
        if (this.antsEatenRecently >= 3 && this.eatAntCooldown <= 0) {
            this.resetAntCooldown();
        }
        if (this.ticksAntOnTongue > 10 && this.hasAntOnTongue()) {
            this.m_5634_(6.0f);
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_11912_, this.m_6121_(), this.m_6100_());
            this.setAntOnTongue(false);
        }
        this.ticksAntOnTongue = this.hasAntOnTongue() ? ++this.ticksAntOnTongue : 0;
        if (!this.m_9236_().f_46443_ && this.getTongueStickOut() > 0.6f && !this.hasAntOnTongue() && this.antsEatenRecently < 3) {
            EntityLeafcutterAnt closestAnt = null;
            for (EntityLeafcutterAnt entity : this.m_9236_().m_45976_(EntityLeafcutterAnt.class, this.m_20191_().m_82400_((double)2.6f))) {
                if (closestAnt != null && (!(entity.m_20270_((Entity)this) < closestAnt.m_20270_((Entity)this)) || !this.m_142582_((Entity)entity))) continue;
                closestAnt = entity;
            }
            if (closestAnt != null) {
                closestAnt.m_142687_(Entity.RemovalReason.KILLED);
                this.ticksAntOnTongue = 0;
                this.setAntOnTongue(true);
                ++this.antsEatenRecently;
            }
        }
        if (!this.m_21205_().m_41619_()) {
            ++this.heldItemTime;
            if (this.heldItemTime > 10 && this.getTongueStickOut() < 0.3f && this.canTargetItem(this.m_21205_())) {
                this.heldItemTime = 0;
                this.m_5634_(4.0f);
                this.m_5496_(SoundEvents.f_11912_, this.m_6121_(), this.m_6100_());
                this.m_146850_(GameEvent.f_157806_);
                if (this.m_21205_().hasCraftingRemainingItem()) {
                    this.m_19983_(this.m_21205_().getCraftingRemainingItem());
                }
                this.m_21662_();
                this.m_21205_().m_41774_(1);
            }
        } else {
            this.heldItemTime = 0;
        }
        if (!this.m_9236_().f_46443_) {
            LivingEntity attackTarget;
            if (this.m_217043_().m_188503_(300) == 0) {
                this.setAnimation(ANIMATION_TOUNGE_IDLE);
            }
            if ((attackTarget = this.m_5448_()) != null && this.m_20270_((Entity)attackTarget) < attackTarget.m_20205_() + this.m_20205_() + 2.0f && this.getAnimationTick() == 7) {
                if (this.getAnimation() == ANIMATION_SLASH_L) {
                    this.m_7327_((Entity)attackTarget);
                    float rot = this.m_146908_() + 90.0f;
                    attackTarget.m_147240_(0.5, (double)Mth.m_14031_((float)(rot * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(rot * ((float)Math.PI / 180)))));
                } else if (this.getAnimation() == ANIMATION_SLASH_R) {
                    this.m_7327_((Entity)attackTarget);
                    float rot = this.m_146908_() - 90.0f;
                    attackTarget.m_147240_(0.5, (double)Mth.m_14031_((float)(rot * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(rot * ((float)Math.PI / 180)))));
                }
            }
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public void resetAntCooldown() {
        this.eatAntCooldown = 600 + this.f_19796_.m_188503_(1000);
        this.antsEatenRecently = 0;
    }

    public void standFor(int time) {
        this.setStanding(true);
        this.maxStandTime = time;
    }

    public float getTongueStickOut() {
        if (this.tongueProgress > 0.0f) {
            double tongueM = Math.min(Math.sin((float)this.f_19797_ * 0.15f), 0.0);
            return (float)(-tongueM) * (this.tongueProgress * 0.2f);
        }
        return 0.0f;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel level, AgeableMob parent) {
        return (AgeableMob)((EntityType)AMEntityRegistry.ANTEATER.get()).m_20615_(this.m_9236_());
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

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return !this.hasAntOnTongue() && stack.m_204117_(AMTagRegistry.INSECT_ITEMS);
    }

    @Override
    public void onGetItem(ItemEntity e) {
        ItemStack duplicate = e.m_32055_().m_41777_();
        duplicate.m_41764_(1);
        if (!this.m_21120_(InteractionHand.MAIN_HAND).m_41619_() && !this.m_9236_().f_46443_) {
            this.m_5552_(this.m_21120_(InteractionHand.MAIN_HAND), 0.0f);
        }
        this.setAnimation(ANIMATION_TOUNGE_IDLE);
        this.m_21008_(InteractionHand.MAIN_HAND, duplicate);
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_SLASH_L, ANIMATION_SLASH_R, ANIMATION_TOUNGE_IDLE};
    }

    private boolean shouldTargetAnts() {
        return !this.m_21660_();
    }

    public boolean isPeter() {
        String name = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        if (name == null) {
            return false;
        }
        String lowercaseName = name.toLowerCase(Locale.ROOT);
        return lowercaseName.contains("peter") || lowercaseName.contains("petr") || lowercaseName.contains("zot");
    }

    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (spawnDataIn == null) {
            spawnDataIn = new AgeableMob.AgeableMobGroupData(0.5f);
        }
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private class AIMelee
    extends Goal {
        public AIMelee() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            return EntityAnteater.this.m_5448_() != null && EntityAnteater.this.m_5448_().m_6084_() && !EntityAnteater.this.m_6162_();
        }

        public void m_8037_() {
            LivingEntity enemy = EntityAnteater.this.m_5448_();
            if (enemy != null) {
                double attackReachSqr = this.getAttackReachSqr(enemy);
                double distToEnemySqr = EntityAnteater.this.m_20270_((Entity)enemy);
                EntityAnteater.this.m_21391_((Entity)enemy, 100.0f, 5.0f);
                if (enemy instanceof EntityLeafcutterAnt) {
                    if (distToEnemySqr <= attackReachSqr + 1.5) {
                        EntityAnteater.this.setAnimation(ANIMATION_TOUNGE_IDLE);
                    } else {
                        EntityAnteater.this.m_21391_((Entity)enemy, 5.0f, 5.0f);
                    }
                    EntityAnteater.this.m_21573_().m_5624_((Entity)enemy, 1.0);
                } else {
                    if (distToEnemySqr <= attackReachSqr) {
                        EntityAnteater.this.m_21573_().m_5624_((Entity)enemy, 1.0);
                        EntityAnteater.this.setAnimation(EntityAnteater.this.m_217043_().m_188499_() ? ANIMATION_SLASH_L : ANIMATION_SLASH_R);
                    }
                    double x = enemy.m_20185_() - EntityAnteater.this.m_20185_();
                    double z = enemy.m_20189_() - EntityAnteater.this.m_20189_();
                    float f = (float)(Mth.m_14136_((double)z, (double)x) * 57.2957763671875) - 90.0f;
                    EntityAnteater.this.m_146922_(f);
                    EntityAnteater.this.f_20883_ = f;
                    EntityAnteater.this.setStanding(true);
                }
            }
        }

        public void m_8041_() {
            EntityAnteater.this.setStanding(false);
            super.m_8041_();
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return 2.0f + attackTarget.m_20205_();
        }
    }

    private class AITargetAnts
    extends NearestAttackableTargetGoal {
        private static final Predicate<EntityLeafcutterAnt> QUEEN_ANT = entity -> !entity.isQueen();

        public AITargetAnts() {
            super((Mob)EntityAnteater.this, EntityLeafcutterAnt.class, 30, true, false, QUEEN_ANT);
        }

        public boolean m_8036_() {
            return EntityAnteater.this.shouldTargetAnts() && !EntityAnteater.this.m_6162_() && !EntityAnteater.this.hasAntOnTongue() && !EntityAnteater.this.isStanding() && super.m_8036_();
        }

        public boolean m_8045_() {
            return EntityAnteater.this.shouldTargetAnts() && !EntityAnteater.this.hasAntOnTongue() && !EntityAnteater.this.isStanding() && super.m_8045_();
        }
    }
}
