/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Shulker
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$TagValue
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.LeavesBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 *  net.minecraft.world.level.storage.loot.BuiltInLootTables
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.IFollower;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFindWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeaveWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalSwimMoveControllerSink;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.MantisShrimpAIBreakBlocks;
import com.github.alexthe666.alexsmobs.entity.ai.MantisShrimpAIFryRice;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticAIRandomSwimming;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticPathNavigator;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.EnumSet;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.phys.Vec3;

public class EntityMantisShrimp
extends TamableAnimal
implements ISemiAquatic,
IFollower {
    private static final EntityDataAccessor<Float> RIGHT_EYE_PITCH = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Float> RIGHT_EYE_YAW = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Float> LEFT_EYE_PITCH = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Float> LEFT_EYE_YAW = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Integer> PUNCH_TICK = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> MOISTNESS = SynchedEntityData.m_135353_(EntityMantisShrimp.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float prevRightPitch;
    public float prevRightYaw;
    public float prevLeftPitch;
    public float prevLeftYaw;
    public float prevInWaterProgress;
    public float inWaterProgress;
    public float prevPunchProgress;
    public float punchProgress;
    private int leftLookCooldown = 0;
    private int rightLookCooldown = 0;
    private float targetRightPitch;
    private float targetRightYaw;
    private float targetLeftPitch;
    private float targetLeftYaw;
    private boolean isLandNavigator;
    private int fishFeedings;
    private int moistureAttackTime = 0;

    protected EntityMantisShrimp(EntityType type, Level world) {
        super(type, world);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
        this.switchNavigator(false);
        this.m_274367_(1.0f);
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.MANTIS_SHRIMP_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.MANTIS_SHRIMP_HURT.get();
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (this.m_6673_(source)) {
            return false;
        }
        Entity entity = source.m_7639_();
        if (entity instanceof Shulker || entity instanceof ShulkerBullet) {
            amount = (amount + 1.0f) * 0.33f;
        }
        return super.m_6469_(source, amount);
    }

    public void m_5993_(Entity entity, int score, DamageSource src) {
        LivingEntity living;
        if (entity instanceof LivingEntity && (living = (LivingEntity)entity).m_6095_() == EntityType.f_20521_) {
            CompoundTag fishNbt = new CompoundTag();
            living.m_7380_(fishNbt);
            fishNbt.m_128359_("DeathLootTable", BuiltInLootTables.f_78712_.toString());
            living.m_7378_(fishNbt);
            living.m_19998_((ItemLike)Items.f_42748_);
        }
        super.m_5993_(entity, score, src);
    }

    public static boolean canMantisShrimpSpawn(EntityType type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn) {
        BlockPos downPos = pos;
        while (downPos.m_123342_() > 1 && !worldIn.m_6425_(downPos).m_76178_()) {
            downPos = downPos.m_7495_();
        }
        boolean spawnBlock = worldIn.m_8055_(downPos).m_204336_(AMTagRegistry.MANTIS_SHRIMP_SPAWNS);
        if (worldIn.m_204166_(pos).m_203656_(AMTagRegistry.SPAWNS_WHITE_MANTIS_SHRIMP) && randomIn.m_188501_() < 0.5f) {
            return false;
        }
        return spawnBlock && downPos.m_123342_() < worldIn.m_5736_() + 1;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 20.0).m_22268_(Attributes.f_22278_, 0.1).m_22268_(Attributes.f_22284_, 8.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22281_, 3.0).m_22268_(Attributes.f_22279_, (double)0.3f);
    }

    public boolean m_6785_(double distanceToClosestPlayer) {
        return !this.m_21824_();
    }

    public MobType m_6336_() {
        return MobType.f_21642_;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.mantisShrimpSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new MantisShrimpAIFryRice(this));
        this.f_21345_.m_25352_(0, (Goal)new MantisShrimpAIBreakBlocks(this));
        this.f_21345_.m_25352_(1, (Goal)new SitWhenOrderedToGoal((TamableAnimal)this));
        this.f_21345_.m_25352_(2, (Goal)new FollowOwner(this, 1.3, 4.0f, 2.0f, false));
        this.f_21345_.m_25352_(3, (Goal)new MeleeAttackGoal((PathfinderMob)this, (double)1.2f, false));
        this.f_21345_.m_25352_(4, (Goal)new AnimalAIFindWater((PathfinderMob)this));
        this.f_21345_.m_25352_(4, (Goal)new AnimalAILeaveWater((PathfinderMob)this));
        this.f_21345_.m_25352_(5, (Goal)new BreedGoal((Animal)this, 0.8));
        this.f_21345_.m_25352_(6, (Goal)new TemptGoal((PathfinderMob)this, 1.0, Ingredient.m_43938_(Stream.of(new Ingredient.TagValue(AMTagRegistry.MANTIS_SHRIMP_BREEDABLES), new Ingredient.TagValue(AMTagRegistry.MANTIS_SHRIMP_TAMEABLES))), false));
        this.f_21345_.m_25352_(7, (Goal)new SemiAquaticAIRandomSwimming((Animal)this, 1.0, 30));
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21346_.m_25352_(1, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(2, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(3, (Goal)new EntityAINearestTarget3D((Mob)this, LivingEntity.class, 120, false, true, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.MANTIS_SHRIMP_TARGETS)){

            public boolean m_8036_() {
                return EntityMantisShrimp.this.getCommand() != 3 && !EntityMantisShrimp.this.isSitting() && super.m_8036_();
            }
        });
        this.f_21346_.m_25352_(4, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigatorWide((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new AnimalSwimMoveControllerSink((PathfinderMob)this, 1.0f, 1.0f);
            this.f_21344_ = new SemiAquaticPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.isSitting()) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            travelVector = Vec3.f_82478_;
            super.m_7023_(travelVector);
            return;
        }
        if (this.m_21515_() && this.m_20069_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_(0.9));
        } else {
            super.m_7023_(travelVector);
        }
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268722_) || source.m_276093_(DamageTypes.f_268612_) || super.m_6673_(source);
    }

    public boolean m_6040_() {
        return true;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(RIGHT_EYE_PITCH, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(RIGHT_EYE_YAW, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(LEFT_EYE_PITCH, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(LEFT_EYE_YAW, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(PUNCH_TICK, (Object)0);
        this.f_19804_.m_135372_(COMMAND, (Object)0);
        this.f_19804_.m_135372_(VARIANT, (Object)0);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(MOISTNESS, (Object)60000);
    }

    public boolean m_6898_(ItemStack stack) {
        Item item = stack.m_41720_();
        return this.m_21824_() && stack.m_204117_(AMTagRegistry.MANTIS_SHRIMP_BREEDABLES);
    }

    public boolean m_7327_(Entity entityIn) {
        this.punch();
        return true;
    }

    public void punch() {
        this.f_19804_.m_135381_(PUNCH_TICK, (Object)4);
    }

    public float getEyeYaw(boolean left) {
        return ((Float)this.f_19804_.m_135370_(left ? LEFT_EYE_YAW : RIGHT_EYE_YAW)).floatValue();
    }

    public float getEyePitch(boolean left) {
        return ((Float)this.f_19804_.m_135370_(left ? LEFT_EYE_PITCH : RIGHT_EYE_PITCH)).floatValue();
    }

    public void setEyePitch(boolean left, float pitch) {
        this.f_19804_.m_135381_(left ? LEFT_EYE_PITCH : RIGHT_EYE_PITCH, (Object)Float.valueOf(pitch));
    }

    public void setEyeYaw(boolean left, float yaw) {
        this.f_19804_.m_135381_(left ? LEFT_EYE_YAW : RIGHT_EYE_YAW, (Object)Float.valueOf(yaw));
    }

    public int getCommand() {
        return (Integer)this.f_19804_.m_135370_(COMMAND);
    }

    public void setCommand(int command) {
        this.f_19804_.m_135381_(COMMAND, (Object)command);
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public void m_21839_(boolean sit) {
        this.f_19804_.m_135381_(SITTING, (Object)sit);
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int command) {
        this.f_19804_.m_135381_(VARIANT, (Object)command);
    }

    public int getMoistness() {
        return (Integer)this.f_19804_.m_135370_(MOISTNESS);
    }

    public void setMoistness(int p_211137_1_) {
        this.f_19804_.m_135381_(MOISTNESS, (Object)p_211137_1_);
    }

    public void m_8119_() {
        super.m_8119_();
        if (this.m_21525_()) {
            this.m_20301_(this.m_6062_());
        } else if (this.m_20071_() || this.m_21205_().m_41720_() == Items.f_42447_) {
            this.setMoistness(60000);
        } else {
            this.setMoistness(this.getMoistness() - 1);
            if (this.getMoistness() <= 0 && this.moistureAttackTime-- <= 0) {
                this.setCommand(0);
                this.m_21839_(false);
                this.m_6469_(this.m_269291_().m_269483_(), this.f_19796_.m_188503_(2) == 0 ? 1.0f : 0.0f);
                this.moistureAttackTime = 20;
            }
        }
        if (this.m_21023_(MobEffects.f_19620_)) {
            this.m_20256_(this.m_20184_().m_82542_(1.0, 0.5, 1.0));
        }
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        if (!this.m_21824_() && itemstack.m_204117_(AMTagRegistry.MANTIS_SHRIMP_TAMEABLES)) {
            this.m_142075_(player, hand, itemstack);
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_12465_, this.m_6121_(), this.m_6100_());
            ++this.fishFeedings;
            if (this.fishFeedings > 10 && this.m_217043_().m_188503_(6) == 0 || this.fishFeedings > 30) {
                this.m_21828_(player);
                this.m_9236_().m_7605_((Entity)this, (byte)7);
            } else {
                this.m_9236_().m_7605_((Entity)this, (byte)6);
            }
            return InteractionResult.SUCCESS;
        }
        if (this.m_21824_() && itemstack.m_204117_(ItemTags.f_13156_)) {
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
            if (player.m_6144_() || itemstack.m_204117_(AMTagRegistry.SHRIMP_RICE_FRYABLES)) {
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
                if (this.getCommand() == 4) {
                    this.setCommand(0);
                }
                if (this.getCommand() == 3) {
                    player.m_5661_((Component)Component.m_237110_((String)"entity.alexsmobs.mantis_shrimp.command_3", (Object[])new Object[]{this.m_7755_()}), true);
                } else {
                    player.m_5661_((Component)Component.m_237110_((String)("entity.alexsmobs.all.command_" + this.getCommand()), (Object[])new Object[]{this.m_7755_()}), true);
                }
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

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("MantisShrimpSitting", this.isSitting());
        compound.m_128405_("Command", this.getCommand());
        compound.m_128405_("Moisture", this.getMoistness());
        compound.m_128405_("Variant", this.getVariant());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_21839_(compound.m_128471_("MantisShrimpSitting"));
        this.setCommand(compound.m_128451_("Command"));
        this.setVariant(compound.m_128451_("Variant"));
        this.setMoistness(compound.m_128451_("Moisture"));
    }

    public void m_8107_() {
        super.m_8107_();
        if (this.m_6162_() && this.m_20192_() > this.m_20206_()) {
            this.m_6210_();
        }
        this.prevLeftPitch = this.getEyePitch(true);
        this.prevRightPitch = this.getEyePitch(false);
        this.prevLeftYaw = this.getEyeYaw(true);
        this.prevRightYaw = this.getEyeYaw(false);
        this.prevInWaterProgress = this.inWaterProgress;
        this.prevPunchProgress = this.punchProgress;
        this.updateEyes();
        if (this.isSitting() && this.m_21573_().m_26571_()) {
            this.m_21573_().m_26573_();
        }
        if (this.m_20069_()) {
            if (this.inWaterProgress < 5.0f) {
                this.inWaterProgress += 1.0f;
            }
            if (this.isLandNavigator) {
                this.switchNavigator(false);
            }
        } else {
            if (this.inWaterProgress > 0.0f) {
                this.inWaterProgress -= 1.0f;
            }
            if (!this.isLandNavigator) {
                this.switchNavigator(true);
            }
        }
        if ((Integer)this.f_19804_.m_135370_(PUNCH_TICK) > 0) {
            if ((Integer)this.f_19804_.m_135370_(PUNCH_TICK) == 2 && this.m_5448_() != null && (double)this.m_20270_((Entity)this.m_5448_()) < 2.8) {
                if (this.m_5448_() instanceof AbstractFish && !this.m_21824_()) {
                    AbstractFish fish = (AbstractFish)this.m_5448_();
                    CompoundTag fishNbt = new CompoundTag();
                    fish.m_7380_(fishNbt);
                    fishNbt.m_128359_("DeathLootTable", BuiltInLootTables.f_78712_.toString());
                    fish.m_7378_(fishNbt);
                }
                this.m_5448_().m_147240_((double)1.7f, this.m_20185_() - this.m_5448_().m_20185_(), this.m_20189_() - this.m_5448_().m_20189_());
                float knockbackResist = (float)Mth.m_14008_((double)(1.0 - this.m_21133_(Attributes.f_22278_)), (double)0.0, (double)1.0);
                this.m_5448_().m_20256_(this.m_5448_().m_20184_().m_82520_(0.0, (double)(knockbackResist * 0.8f), 0.0));
                if (!this.m_5448_().m_20069_()) {
                    this.m_5448_().m_20254_(2);
                }
                this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21133_(Attributes.f_22281_));
            }
            if (this.punchProgress == 1.0f) {
                this.m_5496_((SoundEvent)AMSoundRegistry.MANTIS_SHRIMP_SNAP.get(), this.m_6100_(), this.m_6121_());
            }
            if (this.punchProgress == 2.0f && this.m_9236_().f_46443_ && this.m_20069_()) {
                for (int i = 0; i < 10 + this.f_19796_.m_188503_(8); ++i) {
                    double d2 = this.f_19796_.m_188583_() * 0.6;
                    double d0 = this.f_19796_.m_188583_() * 0.2;
                    double d1 = this.f_19796_.m_188583_() * 0.6;
                    float radius = this.m_20205_() * 0.85f;
                    float angle = (float)Math.PI / 180 * this.f_20883_;
                    double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle)) + this.f_19796_.m_188501_() * 0.5f - 0.25f;
                    double extraZ = radius * Mth.m_14089_((float)angle) + this.f_19796_.m_188501_() * 0.5f - 0.25f;
                    SimpleParticleType data = ParticleTypes.f_123795_;
                    this.m_9236_().m_7106_((ParticleOptions)data, this.m_20185_() + extraX, this.m_20186_() + (double)(this.m_20206_() * 0.3f) + (double)(this.f_19796_.m_188501_() * 0.15f), this.m_20189_() + extraZ, d0, d1, d2);
                }
            }
            if (this.punchProgress < 2.0f) {
                this.punchProgress += 1.0f;
            }
            this.f_19804_.m_135381_(PUNCH_TICK, (Object)((Integer)this.f_19804_.m_135370_(PUNCH_TICK) - 1));
        } else if (this.punchProgress > 0.0f) {
            this.punchProgress -= 0.25f;
        }
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

    private void updateEyes() {
        float leftPitchDist = Math.abs(this.getEyePitch(true) - this.targetLeftPitch);
        float rightPitchDist = Math.abs(this.getEyePitch(false) - this.targetRightPitch);
        float leftYawDist = Math.abs(this.getEyeYaw(true) - this.targetLeftYaw);
        float rightYawDist = Math.abs(this.getEyeYaw(false) - this.targetRightYaw);
        if (this.rightLookCooldown == 0 && this.f_19796_.m_188503_(20) == 0 && rightPitchDist < 0.5f && rightYawDist < 0.5f) {
            this.targetRightPitch = Mth.m_14036_((float)(this.f_19796_.m_188501_() * 60.0f - 30.0f), (float)-30.0f, (float)30.0f);
            this.targetRightYaw = Mth.m_14036_((float)(this.f_19796_.m_188501_() * 60.0f - 30.0f), (float)-30.0f, (float)30.0f);
            this.rightLookCooldown = 3 + this.f_19796_.m_188503_(15);
        }
        if (this.leftLookCooldown == 0 && this.f_19796_.m_188503_(20) == 0 && leftPitchDist < 0.5f && leftYawDist < 0.5f) {
            this.targetLeftPitch = Mth.m_14036_((float)(this.f_19796_.m_188501_() * 60.0f - 30.0f), (float)-30.0f, (float)30.0f);
            this.targetLeftYaw = Mth.m_14036_((float)(this.f_19796_.m_188501_() * 60.0f - 30.0f), (float)-30.0f, (float)30.0f);
            this.leftLookCooldown = 3 + this.f_19796_.m_188503_(15);
        }
        if (leftPitchDist > 0.5f) {
            if (this.getEyePitch(true) < this.targetLeftPitch) {
                this.setEyePitch(true, this.getEyePitch(true) + Math.min(leftPitchDist, 4.0f));
            }
            if (this.getEyePitch(true) > this.targetLeftPitch) {
                this.setEyePitch(true, this.getEyePitch(true) - Math.min(leftPitchDist, 4.0f));
            }
        }
        if (rightPitchDist > 0.5f) {
            if (this.getEyePitch(false) < this.targetRightPitch) {
                this.setEyePitch(false, this.getEyePitch(false) + Math.min(rightPitchDist, 4.0f));
            }
            if (this.getEyePitch(false) > this.targetRightPitch) {
                this.setEyePitch(false, this.getEyePitch(false) - Math.min(rightPitchDist, 4.0f));
            }
        }
        if (leftYawDist > 0.5f) {
            if (this.getEyeYaw(true) < this.targetLeftYaw) {
                this.setEyeYaw(true, this.getEyeYaw(true) + Math.min(leftYawDist, 4.0f));
            }
            if (this.getEyeYaw(true) > this.targetLeftYaw) {
                this.setEyeYaw(true, this.getEyeYaw(true) - Math.min(leftYawDist, 4.0f));
            }
        }
        if (rightYawDist > 0.5f) {
            if (this.getEyeYaw(false) < this.targetRightYaw) {
                this.setEyeYaw(false, this.getEyeYaw(false) + Math.min(rightYawDist, 4.0f));
            }
            if (this.getEyeYaw(false) > this.targetRightYaw) {
                this.setEyeYaw(false, this.getEyeYaw(false) - Math.min(rightYawDist, 4.0f));
            }
        }
        if (this.rightLookCooldown > 0) {
            --this.rightLookCooldown;
        }
        if (this.leftLookCooldown > 0) {
            --this.leftLookCooldown;
        }
    }

    public boolean m_6063_() {
        return false;
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        int i = reason == MobSpawnType.SPAWN_EGG ? this.m_217043_().m_188503_(4) : (worldIn.m_204166_(this.m_20183_()).m_203656_(AMTagRegistry.SPAWNS_WHITE_MANTIS_SHRIMP) ? 3 : this.m_217043_().m_188503_(3));
        this.setVariant(i);
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        EntityMantisShrimp shrimp = (EntityMantisShrimp)((EntityType)AMEntityRegistry.MANTIS_SHRIMP.get()).m_20615_((Level)serverWorld);
        shrimp.setVariant(this.m_217043_().m_188503_(3));
        return shrimp;
    }

    @Override
    public boolean shouldEnterWater() {
        return (this.m_21205_().m_41619_() || this.m_21205_().m_41720_() != Items.f_42447_) && !this.isSitting();
    }

    @Override
    public boolean shouldLeaveWater() {
        return this.m_21205_().m_41720_() == Items.f_42447_;
    }

    @Override
    public boolean shouldStopMoving() {
        return this.isSitting();
    }

    @Override
    public int getWaterSearchRange() {
        return 16;
    }

    @Override
    public boolean shouldFollow() {
        return this.getCommand() == 1;
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    protected void updateAir(int p_209207_1_) {
    }

    public static class FollowOwner
    extends Goal {
        private final EntityMantisShrimp tameable;
        private final LevelReader world;
        private final double followSpeed;
        private final float maxDist;
        private final float minDist;
        private final boolean teleportToLeaves;
        private LivingEntity owner;
        private int timeToRecalcPath;
        private float oldWaterCost;

        public FollowOwner(EntityMantisShrimp p_i225711_1_, double p_i225711_2_, float p_i225711_4_, float p_i225711_5_, boolean p_i225711_6_) {
            this.tameable = p_i225711_1_;
            this.world = p_i225711_1_.m_9236_();
            this.followSpeed = p_i225711_2_;
            this.minDist = p_i225711_4_;
            this.maxDist = p_i225711_5_;
            this.teleportToLeaves = p_i225711_6_;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            LivingEntity lvt_1_1_ = this.tameable.m_269323_();
            if (lvt_1_1_ == null) {
                return false;
            }
            if (lvt_1_1_.m_5833_()) {
                return false;
            }
            if (this.tameable.isSitting() || this.tameable.getCommand() != 1) {
                return false;
            }
            if (this.tameable.m_20280_((Entity)lvt_1_1_) < (double)(this.minDist * this.minDist)) {
                return false;
            }
            if (this.tameable.m_5448_() != null && this.tameable.m_5448_().m_6084_()) {
                return false;
            }
            this.owner = lvt_1_1_;
            return true;
        }

        public boolean m_8045_() {
            if (this.tameable.m_21573_().m_26571_()) {
                return false;
            }
            if (this.tameable.isSitting() || this.tameable.getCommand() != 1) {
                return false;
            }
            if (this.tameable.m_5448_() != null && this.tameable.m_5448_().m_6084_()) {
                return false;
            }
            return this.tameable.m_20280_((Entity)this.owner) > (double)(this.maxDist * this.maxDist);
        }

        public void m_8056_() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = this.tameable.m_21439_(BlockPathTypes.WATER);
            this.tameable.m_21441_(BlockPathTypes.WATER, 0.0f);
        }

        public void m_8041_() {
            this.owner = null;
            this.tameable.m_21573_().m_26573_();
            this.tameable.m_21441_(BlockPathTypes.WATER, this.oldWaterCost);
        }

        public void m_8037_() {
            this.tameable.m_21563_().m_24960_((Entity)this.owner, 10.0f, (float)this.tameable.m_8132_());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                if (!this.tameable.m_21523_() && !this.tameable.m_20159_()) {
                    if (this.tameable.m_20280_((Entity)this.owner) >= 144.0) {
                        this.tryToTeleportNearEntity();
                    } else {
                        this.tameable.m_21573_().m_5624_((Entity)this.owner, this.followSpeed);
                    }
                }
            }
        }

        private void tryToTeleportNearEntity() {
            BlockPos lvt_1_1_ = this.owner.m_20183_();
            for (int lvt_2_1_ = 0; lvt_2_1_ < 10; ++lvt_2_1_) {
                int lvt_3_1_ = this.getRandomNumber(-3, 3);
                int lvt_4_1_ = this.getRandomNumber(-1, 1);
                int lvt_5_1_ = this.getRandomNumber(-3, 3);
                boolean lvt_6_1_ = this.tryToTeleportToLocation(lvt_1_1_.m_123341_() + lvt_3_1_, lvt_1_1_.m_123342_() + lvt_4_1_, lvt_1_1_.m_123343_() + lvt_5_1_);
                if (!lvt_6_1_) continue;
                return;
            }
        }

        private boolean tryToTeleportToLocation(int p_226328_1_, int p_226328_2_, int p_226328_3_) {
            if (Math.abs((double)p_226328_1_ - this.owner.m_20185_()) < 2.0 && Math.abs((double)p_226328_3_ - this.owner.m_20189_()) < 2.0) {
                return false;
            }
            if (!this.isTeleportFriendlyBlock(new BlockPos(p_226328_1_, p_226328_2_, p_226328_3_))) {
                return false;
            }
            this.tameable.m_7678_((double)p_226328_1_ + 0.5, p_226328_2_, (double)p_226328_3_ + 0.5, this.tameable.m_146908_(), this.tameable.m_146909_());
            this.tameable.m_21573_().m_26573_();
            return true;
        }

        private boolean isTeleportFriendlyBlock(BlockPos p_226329_1_) {
            BlockPathTypes lvt_2_1_ = WalkNodeEvaluator.m_77604_((BlockGetter)this.world, (BlockPos.MutableBlockPos)p_226329_1_.m_122032_());
            if (this.world.m_6425_(p_226329_1_).m_205070_(FluidTags.f_13131_) || !this.world.m_6425_(p_226329_1_).m_205070_(FluidTags.f_13131_) && this.world.m_6425_(p_226329_1_.m_7495_()).m_205070_(FluidTags.f_13131_)) {
                return true;
            }
            if (lvt_2_1_ != BlockPathTypes.WALKABLE || this.tameable.getMoistness() < 2000) {
                return false;
            }
            BlockState lvt_3_1_ = this.world.m_8055_(p_226329_1_.m_7495_());
            if (!this.teleportToLeaves && lvt_3_1_.m_60734_() instanceof LeavesBlock) {
                return false;
            }
            BlockPos lvt_4_1_ = p_226329_1_.m_121996_((Vec3i)this.tameable.m_20183_());
            return this.world.m_45756_((Entity)this.tameable, this.tameable.m_20191_().m_82338_(lvt_4_1_));
        }

        private int getRandomNumber(int p_226327_1_, int p_226327_2_) {
            return this.tameable.m_217043_().m_188503_(p_226327_2_ - p_226327_1_ + 1) + p_226327_1_;
        }
    }
}
