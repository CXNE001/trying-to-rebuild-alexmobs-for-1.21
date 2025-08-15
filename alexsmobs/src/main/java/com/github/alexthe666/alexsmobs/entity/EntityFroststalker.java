/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.BiomeTags
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.item.enchantment.Enchantments
 *  net.minecraft.world.item.enchantment.FrostWalkerEnchantment
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeHooks
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityIceShard;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFindWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeaveWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.FroststalkerAIFollowLeader;
import com.github.alexthe666.alexsmobs.entity.ai.FroststalkerAIMelee;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;

public class EntityFroststalker
extends Animal
implements IAnimatedEntity,
ISemiAquatic {
    public static final ResourceLocation SPIKED_LOOT = new ResourceLocation("alexsmobs", "entities/froststalker_spikes");
    public static final Animation ANIMATION_BITE = Animation.create((int)13);
    public static final Animation ANIMATION_SPEAK = Animation.create((int)11);
    public static final Animation ANIMATION_SLASH_L = Animation.create((int)12);
    public static final Animation ANIMATION_SLASH_R = Animation.create((int)12);
    public static final Animation ANIMATION_SHOVE = Animation.create((int)12);
    private static final EntityDataAccessor<Boolean> SPIKES = SynchedEntityData.m_135353_(EntityFroststalker.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> TACKLING = SynchedEntityData.m_135353_(EntityFroststalker.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SPIKE_SHAKING = SynchedEntityData.m_135353_(EntityFroststalker.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> BIPEDAL = SynchedEntityData.m_135353_(EntityFroststalker.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> TURN_ANGLE = SynchedEntityData.m_135353_(EntityFroststalker.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    public static final Predicate<Player> VALID_LEADER_PLAYERS = player -> player.m_6844_(EquipmentSlot.HEAD).m_150930_((Item)AMItemRegistry.FROSTSTALKER_HELMET.get());
    public float bipedProgress;
    public float prevBipedProgress;
    public float tackleProgress;
    public float prevTackleProgress;
    public float spikeShakeProgress;
    public float prevSpikeShakeProgress;
    public float prevTurnAngle;
    private int animationTick;
    private Animation currentAnimation;
    private int standingTime;
    private int currentSpeedMode;
    private LivingEntity leader;
    private int packSize;
    private int shakeTime;
    private boolean hasSpikedArmor;
    private int fleeFireFlag;
    private int resetLeaderCooldown;

    protected EntityFroststalker(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.standingTime = 400 - this.f_19796_.m_188503_(700);
        this.currentSpeedMode = -1;
        this.packSize = 1;
        this.shakeTime = 0;
        this.hasSpikedArmor = false;
        this.resetLeaderCooldown = 100;
        this.m_21441_(BlockPathTypes.LAVA, -1.0f);
        this.m_21441_(BlockPathTypes.DANGER_FIRE, -1.0f);
        this.m_21441_(BlockPathTypes.DAMAGE_FIRE, -1.0f);
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.FROSTSTALKER_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.FROSTSTALKER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.FROSTSTALKER_HURT.get();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.froststalkerSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canFroststalkerSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return worldIn.m_45524_(pos, 0) > 8 && (worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.FROSTSTALKER_SPAWNS) || worldIn.m_8055_(pos.m_7495_()).m_280296_());
    }

    @Nullable
    protected ResourceLocation m_7582_() {
        return this.hasSpikes() ? SPIKED_LOOT : super.m_7582_();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 24.0).m_22268_(Attributes.f_22284_, 2.0).m_22268_(Attributes.f_22281_, 4.5).m_22268_(Attributes.f_22279_, (double)0.3f);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev;
        if (source.m_269533_(DamageTypeTags.f_268745_)) {
            amount *= 2.0f;
        }
        if ((prev = super.m_6469_(source, amount)) && this.hasSpikes() && !this.isSpikeShaking() && source.m_7639_() != null && source.m_7639_().m_20270_((Entity)this) < 10.0f) {
            this.setSpikeShaking(true);
            this.shakeTime = 20 + this.f_19796_.m_188503_(60);
            this.standFor(this.shakeTime + 10);
        }
        return prev;
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this){

            public void m_8037_() {
                if (EntityFroststalker.this.m_217043_().m_188501_() < 0.8f) {
                    if (EntityFroststalker.this.hasSpikes()) {
                        EntityFroststalker.this.jumpUnderwater();
                    } else {
                        EntityFroststalker.this.m_21569_().m_24901_();
                    }
                }
            }
        });
        this.f_21345_.m_25352_(1, (Goal)new AIAvoidFire());
        this.f_21345_.m_25352_(2, (Goal)new FroststalkerAIMelee(this));
        this.f_21345_.m_25352_(3, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(4, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(5, (Goal)new FroststalkerAIFollowLeader(this));
        this.f_21345_.m_25352_(6, (Goal)new AnimalAIFindWater((PathfinderMob)this));
        this.f_21345_.m_25352_(7, (Goal)new AnimalAILeaveWater((PathfinderMob)this));
        this.f_21345_.m_25352_(8, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 90, 1.0, 7, 7));
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, LivingEntity.class, 15.0f));
        this.f_21345_.m_25352_(9, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[]{EntityFroststalker.class}).m_26044_(new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, LivingEntity.class, 40, false, true, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.FROSTSTALKER_TARGETS)));
        this.f_21346_.m_25352_(3, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, 80, false, true, livingEntity -> !livingEntity.m_6844_(EquipmentSlot.HEAD).m_150930_((Item)AMItemRegistry.FROSTSTALKER_HELMET.get())));
    }

    private void jumpUnderwater() {
        BlockPos pos = this.m_20097_();
        if (this.m_9236_().m_46801_(pos) && !this.m_9236_().m_46801_(pos.m_7494_())) {
            this.m_6034_(this.m_20185_(), this.m_20186_() + 1.0, this.m_20189_());
            this.m_9236_().m_46597_(pos, Blocks.f_50449_.m_49966_());
            this.m_9236_().m_186460_(pos, Blocks.f_50449_, Mth.m_216271_((RandomSource)this.m_217043_(), (int)60, (int)120));
        }
        double d0 = 0.2f;
        Vec3 vec3 = this.m_20184_();
        this.m_20334_(vec3.f_82479_, d0, vec3.f_82481_);
    }

    public void m_27595_(@Nullable Player player) {
        if (player != null && this.isValidLeader((LivingEntity)player)) {
            super.m_27595_(player);
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(TURN_ANGLE, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(SPIKES, (Object)true);
        this.f_19804_.m_135372_(BIPEDAL, (Object)false);
        this.f_19804_.m_135372_(SPIKE_SHAKING, (Object)false);
        this.f_19804_.m_135372_(TACKLING, (Object)false);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Spiked", this.hasSpikes());
        compound.m_128379_("Bipedal", this.isBipedal());
        compound.m_128379_("SpikeShaking", this.isSpikeShaking());
        compound.m_128405_("StandingTime", this.standingTime);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setSpiked(compound.m_128471_("Spiked"));
        this.setBipedal(compound.m_128471_("Bipedal"));
        this.setSpikeShaking(compound.m_128471_("SpikeShaking"));
        this.standingTime = compound.m_128451_("StandingTime");
    }

    public BlockPos m_21534_() {
        return this.leader == null ? super.m_21534_() : this.leader.m_20097_();
    }

    public boolean m_21536_() {
        return this.isFollower();
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.FROSTSTALKER_BREEDABLES);
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevBipedProgress = this.bipedProgress;
        this.prevTackleProgress = this.tackleProgress;
        this.prevSpikeShakeProgress = this.spikeShakeProgress;
        this.prevTurnAngle = this.getTurnAngle();
        if (this.isBipedal()) {
            if (this.bipedProgress < 5.0f) {
                this.bipedProgress += 1.0f;
            }
        } else if (this.bipedProgress > 0.0f) {
            this.bipedProgress -= 1.0f;
        }
        if (this.isTackling()) {
            if (this.tackleProgress < 5.0f) {
                this.tackleProgress += 1.0f;
            }
        } else if (this.tackleProgress > 0.0f) {
            this.tackleProgress -= 1.0f;
        }
        if (this.isSpikeShaking()) {
            if (this.spikeShakeProgress < 5.0f) {
                this.spikeShakeProgress += 1.0f;
            }
            if (this.currentSpeedMode != 2) {
                this.currentSpeedMode = 2;
                this.m_21051_(Attributes.f_22279_).m_22100_((double)0.1f);
            }
        } else {
            if (this.spikeShakeProgress > 0.0f) {
                this.spikeShakeProgress -= 1.0f;
            }
            if (this.isBipedal()) {
                if (this.currentSpeedMode != 0) {
                    this.currentSpeedMode = 0;
                    this.m_21051_(Attributes.f_22279_).m_22100_((double)0.35f);
                }
            } else if (this.currentSpeedMode != 1) {
                this.currentSpeedMode = 1;
                this.m_21051_(Attributes.f_22279_).m_22100_(0.25);
            }
        }
        if (this.hasSpikes()) {
            if (!this.hasSpikedArmor) {
                this.hasSpikedArmor = true;
                this.m_21051_(Attributes.f_22284_).m_22100_(12.0);
            }
        } else if (this.hasSpikedArmor) {
            this.hasSpikedArmor = false;
            this.m_21051_(Attributes.f_22284_).m_22100_(0.0);
        }
        if (!this.m_9236_().f_46443_) {
            boolean attackAnim;
            if (this.f_19797_ % 200 == 0) {
                if (this.m_20071_() && !this.hasSpikes()) {
                    this.setSpiked(true);
                }
                if (this.isHotBiome() && !this.m_20071_()) {
                    this.m_7292_(new MobEffectInstance(MobEffects.f_19613_, 400));
                    if (this.f_19796_.m_188503_(2) == 0 && !this.m_20071_()) {
                        this.setSpiked(false);
                    }
                }
            }
            float threshold = 1.0f;
            boolean flag = false;
            if (this.isBipedal() && this.f_19859_ - this.m_146908_() > threshold) {
                this.setTurnAngle(this.getTurnAngle() + 5.0f);
                flag = true;
            }
            if (this.isBipedal() && this.f_19859_ - this.m_146908_() < -threshold) {
                this.setTurnAngle(this.getTurnAngle() - 5.0f);
                flag = true;
            }
            if (!flag) {
                if (this.getTurnAngle() > 0.0f) {
                    this.setTurnAngle(Math.max(this.getTurnAngle() - 10.0f, 0.0f));
                }
                if (this.getTurnAngle() < 0.0f) {
                    this.setTurnAngle(Math.min(this.getTurnAngle() + 10.0f, 0.0f));
                }
            }
            this.setTurnAngle(Mth.m_14036_((float)this.getTurnAngle(), (float)-60.0f, (float)60.0f));
            if (this.standingTime > 0) {
                --this.standingTime;
            }
            if (this.standingTime < 0) {
                ++this.standingTime;
            }
            if (this.standingTime <= 0 && this.isBipedal()) {
                this.standingTime = -200 - this.f_19796_.m_188503_(400);
                this.setBipedal(false);
            }
            if (this.standingTime == 0 && !this.isBipedal() && this.m_20184_().m_82556_() >= 0.03) {
                this.standingTime = 200 + this.f_19796_.m_188503_(600);
                this.setBipedal(true);
            }
            if (this.shakeTime > 0) {
                if (this.shakeTime % 5 == 0) {
                    int spikeCount = 2 + this.f_19796_.m_188503_(4);
                    for (int i = 0; i < spikeCount; ++i) {
                        float f = (float)(i + 1) / (float)spikeCount * 360.0f;
                        EntityIceShard shard = new EntityIceShard(this.m_9236_(), this);
                        shard.shootFromRotation((Entity)this, this.m_146909_() - (float)this.f_19796_.m_188503_(40), f, 0.0f, 0.15f + this.f_19796_.m_188501_() * 0.2f, 1.0f);
                        this.m_9236_().m_7967_((Entity)shard);
                    }
                }
                --this.shakeTime;
            }
            if (this.shakeTime == 0 && this.isSpikeShaking()) {
                this.setSpikeShaking(false);
                if (this.f_19796_.m_188503_(2) == 0) {
                    this.setSpiked(false);
                }
            }
            if (this.m_5448_() != null && this.isValidLeader(this.m_5448_())) {
                this.m_6710_(null);
            }
            if (!(this.m_5448_() == null || this.isValidLeader(this.m_5448_()) || !this.m_5448_().m_6084_() || this.m_21188_() != null && this.m_21188_().m_6084_())) {
                this.m_6703_(this.m_5448_());
            }
            LivingEntity playerTarget = null;
            if (this.leader instanceof Player && ((playerTarget = this.leader.m_21214_()) == null || !playerTarget.m_6084_() || playerTarget instanceof EntityFroststalker)) {
                playerTarget = this.leader.m_21188_();
            }
            if (playerTarget != null && playerTarget.m_6084_() && !(playerTarget instanceof EntityFroststalker)) {
                this.m_6710_(playerTarget);
            }
            boolean bl = attackAnim = this.getAnimation() == ANIMATION_BITE && this.getAnimationTick() == 5 || this.getAnimation() == ANIMATION_SHOVE && this.getAnimationTick() == 8 || this.getAnimation() == ANIMATION_SLASH_L && this.getAnimationTick() == 7 || this.getAnimation() == ANIMATION_SLASH_R && this.getAnimationTick() == 7;
            if (this.m_5448_() != null && attackAnim) {
                this.m_5448_().m_147240_((double)0.2f, this.m_5448_().m_20185_() - this.m_20185_(), this.m_5448_().m_20189_() - this.m_20189_());
                this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21133_(Attributes.f_22281_));
            }
        }
        if (this.fleeFireFlag > 0) {
            --this.fleeFireFlag;
        }
        if (!this.m_9236_().f_46443_) {
            if (this.resetLeaderCooldown > 0) {
                --this.resetLeaderCooldown;
            } else {
                this.resetLeaderCooldown = 200 + this.m_217043_().m_188503_(200);
                this.lookForPlayerLeader();
            }
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    private void lookForPlayerLeader() {
        if (!(this.leader instanceof Player)) {
            float range = 10.0f;
            List playerList = this.m_9236_().m_6443_(Player.class, this.m_20191_().m_82377_((double)range, (double)range, (double)range), VALID_LEADER_PLAYERS);
            Player closestPlayer = null;
            for (Player player : playerList) {
                if (closestPlayer != null && !(player.m_20270_((Entity)this) < closestPlayer.m_20270_((Entity)this))) continue;
                closestPlayer = player;
            }
            if (closestPlayer != null) {
                this.stopFollowing();
                this.startFollowing((LivingEntity)closestPlayer);
            }
        }
    }

    public boolean isFleeingFire() {
        return this.fleeFireFlag > 0;
    }

    public boolean isHotBiome() {
        if (this.m_21525_()) {
            return false;
        }
        if (this.m_9236_().m_46472_() == Level.f_46429_) {
            return true;
        }
        int i = Mth.m_14107_((double)this.m_20185_());
        int k = Mth.m_14107_((double)this.m_20189_());
        return this.m_9236_().m_204166_(new BlockPos(i, 0, k)).m_203656_(BiomeTags.f_263828_);
    }

    public void standFor(int time) {
        this.setBipedal(true);
        this.standingTime = time;
    }

    protected float m_6118_() {
        return 0.52f * this.m_20098_();
    }

    protected void m_6135_() {
        double d0 = (double)this.m_6118_() + (double)this.m_285755_();
        Vec3 vec3 = this.m_20184_();
        this.m_20334_(vec3.f_82479_, d0, vec3.f_82481_);
        float f = this.m_146908_() * ((float)Math.PI / 180);
        this.m_20256_(this.m_20184_().m_82520_((double)(-Mth.m_14031_((float)f) * 0.2f), 0.0, (double)(Mth.m_14089_((float)f) * 0.2f)));
        this.f_19812_ = true;
        ForgeHooks.onLivingJump((LivingEntity)this);
    }

    public void frostJump() {
        this.m_6135_();
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
        return new Animation[]{ANIMATION_BITE, ANIMATION_SPEAK, ANIMATION_SLASH_L, ANIMATION_SLASH_R, ANIMATION_SHOVE};
    }

    public float getTurnAngle() {
        return ((Float)this.f_19804_.m_135370_(TURN_ANGLE)).floatValue();
    }

    public void setTurnAngle(float progress) {
        this.f_19804_.m_135381_(TURN_ANGLE, (Object)Float.valueOf(progress));
    }

    public boolean hasSpikes() {
        return (Boolean)this.f_19804_.m_135370_(SPIKES);
    }

    public void setSpiked(boolean bar) {
        this.f_19804_.m_135381_(SPIKES, (Object)bar);
    }

    public boolean isTackling() {
        return (Boolean)this.f_19804_.m_135370_(TACKLING);
    }

    public void setTackling(boolean bar) {
        this.f_19804_.m_135381_(TACKLING, (Object)bar);
    }

    public boolean isBipedal() {
        return (Boolean)this.f_19804_.m_135370_(BIPEDAL);
    }

    public void setBipedal(boolean bar) {
        this.f_19804_.m_135381_(BIPEDAL, (Object)bar);
    }

    public boolean isSpikeShaking() {
        return (Boolean)this.f_19804_.m_135370_(SPIKE_SHAKING);
    }

    public void setSpikeShaking(boolean bar) {
        this.f_19804_.m_135381_(SPIKE_SHAKING, (Object)bar);
    }

    public boolean isFollower() {
        return this.leader != null && this.isValidLeader(this.leader);
    }

    public boolean isValidLeader(LivingEntity leader) {
        if (leader instanceof Player) {
            if (this.m_21188_() != null && this.m_21188_().equals((Object)leader)) {
                return false;
            }
            return leader.m_6844_(EquipmentSlot.HEAD).m_150930_((Item)AMItemRegistry.FROSTSTALKER_HELMET.get());
        }
        return leader.m_6084_() && leader instanceof EntityFroststalker;
    }

    public boolean m_7327_(Entity entityIn) {
        if (this.getAnimation() == NO_ANIMATION) {
            int anim = this.f_19796_.m_188503_(4);
            switch (anim) {
                case 0: {
                    this.setAnimation(ANIMATION_SHOVE);
                    break;
                }
                case 1: {
                    this.setAnimation(ANIMATION_BITE);
                    break;
                }
                case 2: {
                    this.setAnimation(ANIMATION_SLASH_L);
                    break;
                }
                case 3: {
                    this.setAnimation(ANIMATION_SLASH_R);
                }
            }
        }
        return true;
    }

    public LivingEntity startFollowing(LivingEntity leader) {
        this.leader = leader;
        if (leader instanceof EntityFroststalker) {
            ((EntityFroststalker)leader).addFollower();
        }
        return leader;
    }

    public void stopFollowing() {
        if (this.leader instanceof EntityFroststalker) {
            ((EntityFroststalker)this.leader).removeFollower();
        }
        this.leader = null;
    }

    private void addFollower() {
        ++this.packSize;
    }

    private void removeFollower() {
        --this.packSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.packSize < this.getMaxPackSize() && this.isValidLeader((LivingEntity)this);
    }

    public boolean hasFollowers() {
        return this.packSize > 1;
    }

    public int m_5792_() {
        return 6;
    }

    public int getMaxPackSize() {
        return this.m_5792_();
    }

    public void addFollowers(Stream<EntityFroststalker> p_27534_) {
        p_27534_.limit(this.getMaxPackSize() - this.packSize).filter(p_27538_ -> p_27538_ != this).forEach(p_27536_ -> p_27536_.startFollowing((LivingEntity)this));
    }

    public boolean inRangeOfLeader() {
        return (double)this.m_20270_((Entity)this.leader) <= 60.0;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            double speed = 1.0;
            if (this.leader instanceof Player) {
                speed = 1.3;
                if (this.m_20270_((Entity)this.leader) > 24.0f) {
                    speed = 1.4f;
                    this.standFor(20);
                }
            }
            if (this.m_20270_((Entity)this.leader) > 6.0f && this.m_21573_().m_26571_()) {
                this.m_21573_().m_5624_((Entity)this.leader, speed);
            }
        }
    }

    protected void m_5806_(BlockPos pos) {
        int i = EnchantmentHelper.m_44836_((Enchantment)Enchantments.f_44974_, (LivingEntity)this);
        if (i > 0 || this.hasSpikes()) {
            FrostWalkerEnchantment.m_45018_((LivingEntity)this, (Level)this.m_9236_(), (BlockPos)pos, (int)(i == 0 ? -1 : i));
        }
        if (this.m_6757_(this.m_20075_())) {
            this.m_21185_();
        }
        this.m_21186_();
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor p_27528_, DifficultyInstance p_27529_, MobSpawnType p_27530_, @Nullable SpawnGroupData p_27531_, @Nullable CompoundTag p_27532_) {
        this.m_21051_(Attributes.f_22277_).m_22125_(new AttributeModifier("Random spawn bonus", this.f_19796_.m_188583_() * 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
        if (p_27531_ == null) {
            p_27531_ = new SchoolSpawnGroupData(this);
        } else {
            this.startFollowing((LivingEntity)((SchoolSpawnGroupData)p_27531_).leader);
        }
        return p_27531_;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_146743_, AgeableMob p_146744_) {
        return (AgeableMob)((EntityType)AMEntityRegistry.FROSTSTALKER.get()).m_20615_((Level)p_146743_);
    }

    @Override
    public boolean shouldEnterWater() {
        return !this.hasSpikes() && (this.m_5448_() == null || !this.m_5448_().m_6084_());
    }

    @Override
    public boolean shouldLeaveWater() {
        return this.hasSpikes() || this.m_5448_() != null && this.m_5448_().m_6084_();
    }

    @Override
    public boolean shouldStopMoving() {
        return false;
    }

    @Override
    public int getWaterSearchRange() {
        return 10;
    }

    private class AIAvoidFire
    extends Goal {
        private final int searchLength;
        private final int verticalSearchRange;
        protected BlockPos destinationBlock;
        protected int runDelay = 20;
        private Vec3 fleeTarget;

        private AIAvoidFire() {
            this.searchLength = 20;
            this.verticalSearchRange = 1;
        }

        public boolean m_8045_() {
            return this.destinationBlock != null && this.isFire(EntityFroststalker.this.m_9236_(), this.destinationBlock.m_122032_()) && this.isCloseToFire(16.0);
        }

        public boolean isCloseToFire(double dist) {
            return this.destinationBlock == null || EntityFroststalker.this.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
        }

        public boolean m_8036_() {
            if (this.runDelay > 0) {
                --this.runDelay;
                return false;
            }
            this.runDelay = 30 + EntityFroststalker.this.f_19796_.m_188503_(100);
            return this.searchForDestination();
        }

        public void m_8056_() {
            EntityFroststalker.this.fleeFireFlag = 200;
            Vec3 vec = LandRandomPos.m_148521_((PathfinderMob)EntityFroststalker.this, (int)15, (int)5, (Vec3)Vec3.m_82512_((Vec3i)this.destinationBlock));
            if (vec != null) {
                EntityFroststalker.this.standFor(100 + EntityFroststalker.this.f_19796_.m_188503_(100));
                this.fleeTarget = vec;
                EntityFroststalker.this.m_21573_().m_26519_(vec.f_82479_, vec.f_82480_, vec.f_82481_, (double)1.2f);
            }
        }

        public void m_8037_() {
            if (this.isCloseToFire(16.0)) {
                Vec3 vec;
                EntityFroststalker.this.fleeFireFlag = 200;
                if ((this.fleeTarget == null || EntityFroststalker.this.m_20238_(this.fleeTarget) < 2.0) && (vec = LandRandomPos.m_148521_((PathfinderMob)EntityFroststalker.this, (int)15, (int)5, (Vec3)Vec3.m_82512_((Vec3i)this.destinationBlock))) != null) {
                    this.fleeTarget = vec;
                }
                if (this.fleeTarget != null) {
                    EntityFroststalker.this.m_21573_().m_26519_(this.fleeTarget.f_82479_, this.fleeTarget.f_82480_, this.fleeTarget.f_82481_, 1.0);
                }
            }
        }

        public void m_8041_() {
            this.fleeTarget = null;
        }

        protected boolean searchForDestination() {
            int lvt_1_1_ = this.searchLength;
            int lvt_2_1_ = this.verticalSearchRange;
            BlockPos lvt_3_1_ = EntityFroststalker.this.m_20183_();
            BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
            for (int lvt_5_1_ = -8; lvt_5_1_ <= 2; ++lvt_5_1_) {
                for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                    int lvt_7_1_ = 0;
                    while (lvt_7_1_ <= lvt_6_1_) {
                        int lvt_8_1_;
                        int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                        while (lvt_8_1_ <= lvt_6_1_) {
                            lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                            if (this.isFire(EntityFroststalker.this.m_9236_(), lvt_4_1_)) {
                                this.destinationBlock = lvt_4_1_;
                                return true;
                            }
                            lvt_8_1_ = lvt_8_1_ > 0 ? -lvt_8_1_ : 1 - lvt_8_1_;
                        }
                        lvt_7_1_ = lvt_7_1_ > 0 ? -lvt_7_1_ : 1 - lvt_7_1_;
                    }
                }
            }
            return false;
        }

        private boolean isFire(Level world, BlockPos.MutableBlockPos lvt_4_1_) {
            return world.m_8055_((BlockPos)lvt_4_1_).m_204336_(AMTagRegistry.FROSTSTALKER_FEARS);
        }
    }

    public static class SchoolSpawnGroupData
    implements SpawnGroupData {
        public final EntityFroststalker leader;

        public SchoolSpawnGroupData(EntityFroststalker p_27553_) {
            this.leader = p_27553_;
        }
    }
}
