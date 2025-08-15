/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  net.minecraft.Util
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ItemParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.Container
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.AgeableMob$AgeableMobGroupData
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MoveFunction
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.PanicGoal
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Bee
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.npc.WanderingTrader
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ChestMenu
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.WoolCarpetBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.Tags$Items
 *  net.minecraftforge.network.NetworkHooks
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AdvancedPathNavigateNoTeleport;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.entity.ai.ElephantAIFollowCaravan;
import com.github.alexthe666.alexsmobs.entity.ai.ElephantAIForageLeaves;
import com.github.alexthe666.alexsmobs.entity.ai.ElephantAIVillagerRide;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;

public class EntityElephant
extends TamableAnimal
implements ITargetsDroppedItems,
IAnimatedEntity {
    public static final Animation ANIMATION_TRUMPET_0 = Animation.create((int)20);
    public static final Animation ANIMATION_TRUMPET_1 = Animation.create((int)30);
    public static final Animation ANIMATION_CHARGE_PREPARE = Animation.create((int)25);
    public static final Animation ANIMATION_STOMP = Animation.create((int)20);
    public static final Animation ANIMATION_FLING = Animation.create((int)25);
    public static final Animation ANIMATION_EAT = Animation.create((int)30);
    public static final Animation ANIMATION_BREAKLEAVES = Animation.create((int)20);
    protected static final EntityDimensions TUSKED_SIZE = EntityDimensions.m_20398_((float)3.7f, (float)3.75f);
    private static final EntityDataAccessor<Boolean> TUSKED = SynchedEntityData.m_135353_(EntityElephant.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityElephant.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> STANDING = SynchedEntityData.m_135353_(EntityElephant.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> CHESTED = SynchedEntityData.m_135353_(EntityElephant.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> CARPET_COLOR = SynchedEntityData.m_135353_(EntityElephant.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> TRADER = SynchedEntityData.m_135353_(EntityElephant.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public static final Map<DyeColor, Item> DYE_COLOR_ITEM_MAP = (Map)Util.m_137469_((Object)Maps.newHashMap(), map -> {
        map.put(DyeColor.WHITE, Items.f_42130_);
        map.put(DyeColor.ORANGE, Items.f_42131_);
        map.put(DyeColor.MAGENTA, Items.f_42132_);
        map.put(DyeColor.LIGHT_BLUE, Items.f_42133_);
        map.put(DyeColor.YELLOW, Items.f_42134_);
        map.put(DyeColor.LIME, Items.f_42135_);
        map.put(DyeColor.PINK, Items.f_42136_);
        map.put(DyeColor.GRAY, Items.f_42137_);
        map.put(DyeColor.LIGHT_GRAY, Items.f_42138_);
        map.put(DyeColor.CYAN, Items.f_42139_);
        map.put(DyeColor.PURPLE, Items.f_42140_);
        map.put(DyeColor.BLUE, Items.f_42141_);
        map.put(DyeColor.BROWN, Items.f_42142_);
        map.put(DyeColor.GREEN, Items.f_42143_);
        map.put(DyeColor.RED, Items.f_42197_);
        map.put(DyeColor.BLACK, Items.f_42198_);
    });
    private static final ResourceLocation TRADER_LOOT = new ResourceLocation("alexsmobs", "gameplay/trader_elephant_chest");
    public boolean forcedSit = false;
    public float prevSitProgress;
    public float sitProgress;
    public float prevStandProgress;
    public float standProgress;
    public int maxStandTime = 75;
    public boolean aiItemFlag = false;
    public SimpleContainer elephantInventory;
    private int animationTick;
    private Animation currentAnimation;
    private final boolean hasTuskedAttributes = false;
    private int standingTime = 0;
    @Nullable
    private EntityElephant caravanHead;
    @Nullable
    private EntityElephant caravanTail;
    private boolean hasChestVarChanged = false;
    private boolean hasChargedSpeed = false;
    private boolean charging;
    private int chargeCooldown = 0;
    private int chargingTicks = 0;
    @Nullable
    private UUID blossomThrowerUUID = null;
    private int despawnDelay = 47999;

    protected EntityElephant(EntityType type, Level world) {
        super(type, world);
        this.initElephantInventory();
        this.m_274367_(1.1f);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 85.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22278_, (double)0.9f).m_22268_(Attributes.f_22281_, 10.0).m_22268_(Attributes.f_22279_, (double)0.35f);
    }

    @Nullable
    public static DyeColor getCarpetColor(ItemStack stack) {
        Block lvt_1_1_ = Block.m_49814_((Item)stack.m_41720_());
        return lvt_1_1_ instanceof WoolCarpetBlock ? ((WoolCarpetBlock)lvt_1_1_).m_58309_() : null;
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.ELEPHANT_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.ELEPHANT_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.ELEPHANT_DIE.get();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.elephantSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    private void initElephantInventory() {
        SimpleContainer animalchest = this.elephantInventory;
        this.elephantInventory = new SimpleContainer(54){

            public boolean m_6542_(Player player) {
                return EntityElephant.this.m_6084_() && !EntityElephant.this.f_19817_;
            }
        };
        if (animalchest != null) {
            int i = Math.min(animalchest.m_6643_(), this.elephantInventory.m_6643_());
            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = animalchest.m_8020_(j);
                if (itemstack.m_41619_()) continue;
                this.elephantInventory.m_6836_(j, itemstack.m_41777_());
            }
        }
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new AdvancedPathNavigateNoTeleport((Mob)this, worldIn, true);
    }

    public int m_8085_() {
        return super.m_8085_();
    }

    protected boolean m_6107_() {
        return super.m_6107_() || this.isSitting() || this.getAnimation() == ANIMATION_CHARGE_PREPARE && this.getAnimationTick() < 10;
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new SitWhenOrderedToGoal((TamableAnimal)this));
        this.f_21345_.m_25352_(2, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.0, true));
        this.f_21345_.m_25352_(2, (Goal)new PanicGoal());
        this.f_21345_.m_25352_(2, (Goal)new ElephantAIVillagerRide(this, 1.0));
        this.f_21345_.m_25352_(3, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(4, (Goal)new TemptGoal((PathfinderMob)this, 1.0, Ingredient.m_204132_(AMTagRegistry.ELEPHANT_TAMEABLES), false));
        this.f_21345_.m_25352_(5, (Goal)new ElephantAIForageLeaves(this));
        this.f_21345_.m_25352_(6, (Goal)new FollowParentGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(7, (Goal)new ElephantAIFollowCaravan(this, 0.5));
        this.f_21345_.m_25352_(8, (Goal)new AvoidEntityGoal((PathfinderMob)this, Bee.class, 6.0f, 1.0, 1.2));
        this.f_21345_.m_25352_(9, (Goal)new AIWalkIdle(this, 0.5));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal().m_26044_(new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(3, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(4, new CreatureAITargetItems((PathfinderMob)this, false));
    }

    public boolean m_6898_(ItemStack stack) {
        Item item = stack.m_41720_();
        return this.m_21824_() && stack.m_204117_(AMTagRegistry.ELEPHANT_BREEDABLES);
    }

    protected void m_7355_(BlockPos pos, BlockState state) {
        if (!this.m_6162_()) {
            this.m_5496_((SoundEvent)AMSoundRegistry.ELEPHANT_WALK.get(), 0.2f, 1.0f);
        } else {
            super.m_7355_(pos, state);
        }
    }

    @Nullable
    public LivingEntity m_6688_() {
        for (Entity passenger : this.m_20197_()) {
            if (!(passenger instanceof Player)) continue;
            return (LivingEntity)passenger;
        }
        return null;
    }

    @Nullable
    public AbstractVillager getControllingVillager() {
        for (Entity passenger : this.m_20197_()) {
            if (!(passenger instanceof AbstractVillager)) continue;
            return (AbstractVillager)passenger;
        }
        return null;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(TUSKED, (Object)false);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(STANDING, (Object)false);
        this.f_19804_.m_135372_(CHESTED, (Object)false);
        this.f_19804_.m_135372_(TRADER, (Object)false);
        this.f_19804_.m_135372_(CARPET_COLOR, (Object)-1);
    }

    public void m_8119_() {
        Player rider;
        super.m_8119_();
        this.prevSitProgress = this.sitProgress;
        this.prevStandProgress = this.standProgress;
        if (this.isSitting()) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (this.isStanding()) {
            if (this.standProgress < 5.0f) {
                this.standProgress += 0.5f;
            }
        } else if (this.standProgress > 0.0f) {
            this.standProgress -= 0.5f;
        }
        if (this.isStanding() && ++this.standingTime > this.maxStandTime) {
            this.setStanding(false);
            this.standingTime = 0;
            this.maxStandTime = 75 + this.f_19796_.m_188503_(50);
        }
        if (this.isSitting() && this.isStanding()) {
            this.setStanding(false);
        }
        if (this.hasChestVarChanged && this.elephantInventory != null && !this.isChested()) {
            for (int i = 3; i < 18; ++i) {
                if (this.elephantInventory.m_8020_(i).m_41619_()) continue;
                if (!this.m_9236_().f_46443_) {
                    this.m_5552_(this.elephantInventory.m_8020_(i), 1.0f);
                }
                this.elephantInventory.m_8016_(i);
            }
            this.hasChestVarChanged = false;
        }
        if (this.isTusked() && !this.m_6162_()) {
            this.m_6210_();
        }
        if (this.charging) {
            ++this.chargingTicks;
        }
        if (!this.m_21205_().m_41619_() && this.canTargetItem(this.m_21205_())) {
            if (this.getAnimation() == NO_ANIMATION) {
                this.setAnimation(ANIMATION_EAT);
            }
            if (this.getAnimation() == ANIMATION_EAT && this.getAnimationTick() == 17) {
                this.eatItemEffect(this.m_21205_());
                if (this.m_21205_().m_204117_(AMTagRegistry.ELEPHANT_TAMEABLES) && !this.m_21824_() && (!this.isTusked() || this.m_6162_()) && this.blossomThrowerUUID != null) {
                    if (this.f_19796_.m_188503_(3) == 0) {
                        this.m_7105_(true);
                        this.m_21816_(this.blossomThrowerUUID);
                        Player player = this.m_9236_().m_46003_(this.blossomThrowerUUID);
                        if (player != null) {
                            this.m_21828_(player);
                        }
                        for (Entity passenger : this.m_20197_()) {
                            passenger.m_6038_();
                        }
                        this.m_9236_().m_7605_((Entity)this, (byte)7);
                    } else {
                        this.m_9236_().m_7605_((Entity)this, (byte)6);
                    }
                }
                this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
                this.m_5634_(10.0f);
            }
        }
        if (this.chargeCooldown > 0) {
            --this.chargeCooldown;
        }
        this.chargingTicks = this.charging ? ++this.chargingTicks : 0;
        if (this.getAnimation() == ANIMATION_CHARGE_PREPARE) {
            this.f_20883_ = this.m_146908_();
            if (this.getAnimationTick() == 20) {
                this.charging = true;
            }
        }
        if (this.m_6688_() != null && this.charging && this.chargingTicks > 100) {
            this.charging = false;
            this.chargeCooldown = 200;
        }
        LivingEntity target = this.m_5448_();
        double maxAttackMod = 0.0;
        if (this.m_6688_() != null && this.m_6688_() instanceof Player && (rider = (Player)this.m_6688_()).m_21214_() != null && !this.m_7307_((Entity)rider.m_21214_())) {
            UUID preyUUID = rider.m_21214_().m_20148_();
            if (!this.m_20148_().equals(preyUUID)) {
                target = rider.m_21214_();
                maxAttackMod = 4.0;
            }
        }
        if (!this.m_9236_().f_46443_ && target != null) {
            double dist;
            if (this.m_20270_((Entity)target) > this.m_20205_() * 0.5f + 0.5f && this.m_6688_() == null && this.isTusked() && this.m_142582_((Entity)target) && this.getAnimation() == NO_ANIMATION && !this.charging && this.chargeCooldown == 0) {
                this.setAnimation(ANIMATION_CHARGE_PREPARE);
            }
            if (this.getAnimation() == ANIMATION_CHARGE_PREPARE && this.m_6688_() == null) {
                this.m_21391_((Entity)target, 360.0f, 30.0f);
                this.f_20883_ = this.m_146908_();
                if (this.getAnimationTick() == 20) {
                    this.charging = true;
                }
            }
            if ((double)this.m_20270_((Entity)target) < 10.0 && this.charging) {
                this.setAnimation(ANIMATION_FLING);
            }
            if ((double)this.m_20270_((Entity)target) < 2.1 && this.charging) {
                target.m_147240_(1.0, target.m_20185_() - this.m_20185_(), target.m_20189_() - this.m_20189_());
                target.f_19812_ = true;
                target.m_20256_(target.m_20184_().m_82520_(0.0, (double)0.7f, 0.0));
                target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 2.4f * (float)this.m_21051_(Attributes.f_22281_).m_22115_());
                this.launch((Entity)target, true);
                this.charging = false;
                this.chargeCooldown = 400;
            }
            if ((dist = (double)this.m_20270_((Entity)target)) < 4.5 + maxAttackMod && this.getAnimation() == ANIMATION_FLING && this.getAnimationTick() == 15) {
                target.m_147240_(1.0, target.m_20185_() - this.m_20185_(), target.m_20189_() - this.m_20189_());
                target.m_20256_(target.m_20184_().m_82520_(0.0, (double)0.3f, 0.0));
                this.launch((Entity)target, false);
                target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21051_(Attributes.f_22281_).m_22115_());
            }
            if (dist < 4.5 + maxAttackMod && this.getAnimation() == ANIMATION_STOMP && this.getAnimationTick() == 17) {
                target.m_147240_((double)0.3f, target.m_20185_() - this.m_20185_(), target.m_20189_() - this.m_20189_());
                target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21051_(Attributes.f_22281_).m_22115_());
            }
        }
        if (!this.m_9236_().f_46443_ && this.m_5448_() == null && this.m_6688_() == null) {
            this.charging = false;
        }
        if (this.charging && !this.hasChargedSpeed) {
            this.m_21051_(Attributes.f_22279_).m_22100_((double)0.65f);
            this.hasChargedSpeed = true;
        }
        if (!this.charging && this.hasChargedSpeed) {
            this.m_21051_(Attributes.f_22279_).m_22100_((double)0.35f);
            this.hasChargedSpeed = false;
        }
        if (!this.m_9236_().f_46443_ && this.m_217043_().m_188503_(400) == 0 && this.getAnimation() == NO_ANIMATION) {
            this.setAnimation(this.m_217043_().m_188499_() ? ANIMATION_TRUMPET_0 : ANIMATION_TRUMPET_1);
        }
        if (this.getAnimation() == ANIMATION_TRUMPET_0 && this.getAnimationTick() == 8 || this.getAnimation() == ANIMATION_TRUMPET_1 && this.getAnimationTick() == 4) {
            this.m_146850_(GameEvent.f_223709_);
            this.m_5496_((SoundEvent)AMSoundRegistry.ELEPHANT_TRUMPET.get(), this.m_6121_(), this.m_6100_());
        }
        if (this.m_6084_() && this.charging) {
            for (Entity entity : this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82400_(1.0))) {
                if (this.m_21824_() && this.m_7307_(entity) || !this.m_21824_() && entity instanceof EntityElephant || entity == this) continue;
                entity.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 8.0f + this.f_19796_.m_188501_() * 8.0f);
                this.launch(entity, true);
            }
            this.m_274367_(2.0f);
        } else {
            this.m_274367_(1.1f);
        }
        if (!this.m_21824_() && this.isTrader() && !this.m_9236_().f_46443_) {
            this.tryDespawn();
        }
        if (this.m_5448_() != null && !this.m_5448_().m_6084_()) {
            this.m_6710_(null);
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public void m_8107_() {
        super.m_8107_();
        if (this.m_6162_() && this.m_20192_() > this.m_20206_()) {
            this.m_6210_();
        }
    }

    private boolean canDespawn() {
        return !this.m_21824_() && this.isTrader();
    }

    private void tryDespawn() {
        if (this.canDespawn()) {
            int riderDelay;
            if (this.getControllingVillager() instanceof WanderingTrader && (riderDelay = ((WanderingTrader)this.getControllingVillager()).m_35876_()) > 0) {
                this.despawnDelay = riderDelay;
            }
            --this.despawnDelay;
            if (this.despawnDelay <= 0) {
                this.m_21455_(true, false);
                this.elephantInventory.m_6211_();
                if (this.getControllingVillager() != null) {
                    this.getControllingVillager().m_142687_(Entity.RemovalReason.DISCARDED);
                }
                this.m_142687_(Entity.RemovalReason.DISCARDED);
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

    private void eatItemEffect(ItemStack heldItemMainhand) {
        this.m_146850_(GameEvent.f_157806_);
        this.m_5496_(SoundEvents.f_12465_, this.m_6100_(), this.m_6121_());
        for (int i = 0; i < 8 + this.f_19796_.m_188503_(3); ++i) {
            double d2 = this.f_19796_.m_188583_() * 0.02;
            double d0 = this.f_19796_.m_188583_() * 0.02;
            double d1 = this.f_19796_.m_188583_() * 0.02;
            float radius = this.m_20205_() * 0.65f;
            float angle = (float)Math.PI / 180 * this.f_20883_;
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            ItemParticleOption data = new ItemParticleOption(ParticleTypes.f_123752_, heldItemMainhand);
            if (heldItemMainhand.m_41720_() instanceof BlockItem) {
                data = new BlockParticleOption(ParticleTypes.f_123794_, ((BlockItem)heldItemMainhand.m_41720_()).m_40614_().m_49966_());
            }
            this.m_9236_().m_7106_((ParticleOptions)data, this.m_20185_() + extraX, this.m_20186_() + (double)(this.m_20206_() * 0.6f), this.m_20189_() + extraZ, d0, d1, d2);
        }
    }

    private boolean isChargePlayer(Entity controllingPassenger) {
        return true;
    }

    public boolean m_7327_(Entity entityIn) {
        if (this.getAnimation() == NO_ANIMATION && !this.charging) {
            this.setAnimation(this.f_19796_.m_188499_() ? ANIMATION_FLING : ANIMATION_STOMP);
        }
        return true;
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack stack = player.m_21120_(hand);
        boolean owner = this.m_21824_() && this.m_21830_((LivingEntity)player);
        InteractionResult type = super.m_6071_(player, hand);
        if (this.isChested() && player.m_6144_()) {
            this.openGUI(player);
            return InteractionResult.SUCCESS;
        }
        if (this.canTargetItem(stack) && this.m_21205_().m_41619_()) {
            ItemStack rippedStack = stack.m_41777_();
            rippedStack.m_41764_(1);
            stack.m_41774_(1);
            this.m_21008_(InteractionHand.MAIN_HAND, rippedStack);
            if (rippedStack.m_204117_(AMTagRegistry.ELEPHANT_TAMEABLES)) {
                this.blossomThrowerUUID = player.m_20148_();
            }
            return InteractionResult.SUCCESS;
        }
        if (owner && stack.m_204117_(ItemTags.f_215867_)) {
            DyeColor color = EntityElephant.getCarpetColor(stack);
            if (color != this.getColor()) {
                if (this.getColor() != null) {
                    this.m_19998_((ItemLike)this.getCarpetItemBeingWorn());
                }
                this.m_146850_(GameEvent.f_223708_);
                this.m_5496_(SoundEvents.f_12100_, 1.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
                if (!this.m_9236_().f_46443_ && player instanceof ServerPlayer) {
                    ServerPlayer serverPlayer = (ServerPlayer)player;
                    AMAdvancementTriggerRegistry.ELEPHANT_SWAG.trigger(serverPlayer);
                }
                stack.m_41774_(1);
                this.setColor(color);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        if (owner && this.getColor() != null && stack.m_204117_(Tags.Items.SHEARS)) {
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_(SoundEvents.f_12344_, 1.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
            if (this.getColor() != null) {
                this.m_19998_((ItemLike)this.getCarpetItemBeingWorn());
            }
            this.setColor(null);
            return InteractionResult.SUCCESS;
        }
        if (owner && !this.isChested() && stack.m_204117_(Tags.Items.CHESTS_WOODEN)) {
            this.setChested(true);
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_(SoundEvents.f_11811_, 1.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
            if (!player.m_150110_().f_35937_) {
                stack.m_41774_(1);
            }
            return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
        }
        if (owner && this.isChested() && stack.m_204117_(Tags.Items.SHEARS)) {
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_(SoundEvents.f_12344_, 1.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
            this.m_19998_((ItemLike)Blocks.f_50087_);
            for (int i = 0; i < this.elephantInventory.m_6643_(); ++i) {
                this.m_19983_(this.elephantInventory.m_8020_(i));
            }
            this.elephantInventory.m_6211_();
            this.setChested(false);
            return InteractionResult.SUCCESS;
        }
        if (owner && !this.m_6162_() && type != InteractionResult.CONSUME) {
            if (!this.m_9236_().f_46443_) {
                player.m_20329_((Entity)this);
            }
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    public EntityDimensions m_6972_(Pose poseIn) {
        return this.isTusked() && !this.m_6162_() ? TUSKED_SIZE : super.m_6972_(poseIn);
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_TRUMPET_0, ANIMATION_TRUMPET_1, ANIMATION_CHARGE_PREPARE, ANIMATION_STOMP, ANIMATION_FLING, ANIMATION_EAT, ANIMATION_BREAKLEAVES};
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    public Item getCarpetItemBeingWorn() {
        if (this.getColor() != null) {
            return DYE_COLOR_ITEM_MAP.get(this.getColor());
        }
        return Items.f_41852_;
    }

    protected void m_5907_() {
        super.m_5907_();
        if (this.isChested()) {
            if (!this.m_9236_().f_46443_) {
                this.m_19998_((ItemLike)Blocks.f_50087_);
            }
            for (int i = 0; i < this.elephantInventory.m_6643_(); ++i) {
                this.m_19983_(this.elephantInventory.m_8020_(i));
            }
            this.elephantInventory.m_6211_();
            this.setChested(false);
        }
        if (!this.isTrader() && this.getColor() != null) {
            if (!this.m_9236_().f_46443_) {
                this.m_19998_((ItemLike)this.getCarpetItemBeingWorn());
            }
            this.setColor(null);
        }
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        EntityElephant baby = (EntityElephant)((EntityType)AMEntityRegistry.ELEPHANT.get()).m_20615_((Level)serverWorld);
        baby.setTusked(this.getNearestTusked((LevelAccessor)this.m_9236_(), 15.0) == null || this.f_19796_.m_188503_(2) == 0);
        return baby;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Tusked", this.isTusked());
        compound.m_128379_("ElephantSitting", this.isSitting());
        compound.m_128379_("Standing", this.isStanding());
        compound.m_128379_("Chested", this.isChested());
        compound.m_128379_("Trader", this.isTrader());
        compound.m_128379_("ForcedToSit", this.forcedSit);
        compound.m_128379_("Tamed", this.m_21824_());
        compound.m_128405_("ChargeCooldown", this.chargeCooldown);
        compound.m_128405_("Carpet", ((Integer)this.f_19804_.m_135370_(CARPET_COLOR)).intValue());
        compound.m_128405_("DespawnDelay", this.despawnDelay);
        if (this.elephantInventory != null) {
            ListTag nbttaglist = new ListTag();
            for (int i = 0; i < this.elephantInventory.m_6643_(); ++i) {
                ItemStack itemstack = this.elephantInventory.m_8020_(i);
                if (itemstack.m_41619_()) continue;
                CompoundTag CompoundNBT = new CompoundTag();
                CompoundNBT.m_128344_("Slot", (byte)i);
                itemstack.m_41739_(CompoundNBT);
                nbttaglist.add((Object)CompoundNBT);
            }
            compound.m_128365_("Items", (Tag)nbttaglist);
        }
    }

    public boolean m_7301_(MobEffectInstance potioneffectIn) {
        if (potioneffectIn.m_19544_() == MobEffects.f_19615_) {
            return false;
        }
        return super.m_7301_(potioneffectIn);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_7105_(compound.m_128471_("Tamed"));
        this.setTusked(compound.m_128471_("Tusked"));
        this.setStanding(compound.m_128471_("Standing"));
        this.m_21839_(compound.m_128471_("ElephantSitting"));
        this.setChested(compound.m_128471_("Chested"));
        this.setTrader(compound.m_128471_("Trader"));
        this.forcedSit = compound.m_128471_("ForcedToSit");
        this.chargeCooldown = compound.m_128451_("ChargeCooldown");
        this.f_19804_.m_135381_(CARPET_COLOR, (Object)compound.m_128451_("Carpet"));
        if (this.elephantInventory != null) {
            ListTag nbttaglist = compound.m_128437_("Items", 10);
            this.initElephantInventory();
            for (int i = 0; i < nbttaglist.size(); ++i) {
                CompoundTag CompoundNBT = nbttaglist.m_128728_(i);
                int j = CompoundNBT.m_128445_("Slot") & 0xFF;
                this.elephantInventory.m_6836_(j, ItemStack.m_41712_((CompoundTag)CompoundNBT));
            }
        } else {
            ListTag nbttaglist = compound.m_128437_("Items", 10);
            this.initElephantInventory();
            for (int i = 0; i < nbttaglist.size(); ++i) {
                CompoundTag CompoundNBT = nbttaglist.m_128728_(i);
                int j = CompoundNBT.m_128445_("Slot") & 0xFF;
                this.initElephantInventory();
                this.elephantInventory.m_6836_(j, ItemStack.m_41712_((CompoundTag)CompoundNBT));
            }
        }
        if (compound.m_128425_("DespawnDelay", 99)) {
            this.despawnDelay = compound.m_128451_("DespawnDelay");
        }
    }

    public boolean isChested() {
        return (Boolean)this.f_19804_.m_135370_(CHESTED);
    }

    public void setChested(boolean chested) {
        this.f_19804_.m_135381_(CHESTED, (Object)chested);
        this.hasChestVarChanged = true;
    }

    public boolean setSlot(int inventorySlot, @Nullable ItemStack itemStackIn) {
        int j = inventorySlot - 500 + 2;
        if (j >= 0 && j < this.elephantInventory.m_6643_()) {
            this.elephantInventory.m_6836_(j, itemStackIn);
            return true;
        }
        return false;
    }

    public void m_6667_(DamageSource cause) {
        super.m_6667_(cause);
        if (this.elephantInventory != null && !this.m_9236_().f_46443_) {
            for (int i = 0; i < this.elephantInventory.m_6643_(); ++i) {
                ItemStack itemstack = this.elephantInventory.m_8020_(i);
                if (itemstack.m_41619_()) continue;
                this.m_5552_(itemstack, 0.0f);
            }
        }
    }

    public boolean isStanding() {
        return (Boolean)this.f_19804_.m_135370_(STANDING);
    }

    public void setStanding(boolean standing) {
        this.f_19804_.m_135381_(STANDING, (Object)standing);
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public void m_21839_(boolean sit) {
        this.f_19804_.m_135381_(SITTING, (Object)sit);
    }

    @Nullable
    public DyeColor getColor() {
        int lvt_1_1_ = (Integer)this.f_19804_.m_135370_(CARPET_COLOR);
        return lvt_1_1_ == -1 ? null : DyeColor.m_41053_((int)lvt_1_1_);
    }

    public void setColor(@Nullable DyeColor color) {
        this.f_19804_.m_135381_(CARPET_COLOR, (Object)(color == null ? -1 : color.m_41060_()));
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (spawnDataIn instanceof AgeableMob.AgeableMobGroupData) {
            AgeableMob.AgeableMobGroupData lvt_6_1_ = (AgeableMob.AgeableMobGroupData)spawnDataIn;
            if (lvt_6_1_.m_146777_() == 0) {
                this.setTusked(true);
            }
        } else {
            this.setTusked(this.m_217043_().m_188499_());
        }
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    public EntityElephant getNearestTusked(LevelAccessor world, double dist) {
        List list = world.m_45976_(this.getClass(), this.m_20191_().m_82377_(dist, dist / 2.0, dist));
        if (list.isEmpty()) {
            return null;
        }
        EntityElephant elephant1 = null;
        double d0 = Double.MAX_VALUE;
        for (EntityElephant elephant : list) {
            double d1;
            if (!elephant.isTusked() || (d1 = this.m_20280_((Entity)elephant)) > d0) continue;
            d0 = d1;
            elephant1 = elephant;
        }
        return elephant1;
    }

    public boolean isTusked() {
        return (Boolean)this.f_19804_.m_135370_(TUSKED);
    }

    public void setTusked(boolean tusked) {
        boolean prev = this.isTusked();
        if (!prev && tusked) {
            this.m_21051_(Attributes.f_22276_).m_22100_(110.0);
            this.m_21051_(Attributes.f_22281_).m_22100_(15.0);
            this.m_21153_(150.0f);
        } else {
            this.m_21051_(Attributes.f_22276_).m_22100_(85.0);
            this.m_21051_(Attributes.f_22281_).m_22100_(10.0);
        }
        this.f_19804_.m_135381_(TUSKED, (Object)tusked);
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.ELEPHANT_FOODSTUFFS);
    }

    @Override
    public void onGetItem(ItemEntity e) {
        ItemStack duplicate = e.m_32055_().m_41777_();
        duplicate.m_41764_(1);
        if (!this.m_21120_(InteractionHand.MAIN_HAND).m_41619_() && !this.m_9236_().f_46443_) {
            this.m_5552_(this.m_21120_(InteractionHand.MAIN_HAND), 0.0f);
        }
        Entity itemThrower = e.m_19749_();
        this.blossomThrowerUUID = duplicate.m_204117_(AMTagRegistry.ELEPHANT_TAMEABLES) && itemThrower != null ? itemThrower.m_20148_() : null;
        this.m_21008_(InteractionHand.MAIN_HAND, duplicate);
        this.aiItemFlag = false;
    }

    @Override
    public void onFindTarget(ItemEntity e) {
        this.aiItemFlag = true;
    }

    public void addElephantLoot(@Nullable Player player, int seed) {
        if (this.m_9236_().m_7654_() != null) {
            LootTable loottable = this.m_9236_().m_7654_().m_278653_().m_278676_(TRADER_LOOT);
            LootParams.Builder lootcontext$builder = new LootParams.Builder((ServerLevel)this.m_9236_());
            loottable.m_287188_((Container)this.elephantInventory, lootcontext$builder.m_287235_(LootContextParamSets.f_81410_), (long)seed);
        }
    }

    public void leaveCaravan() {
        if (this.caravanHead != null) {
            this.caravanHead.caravanTail = null;
        }
        this.caravanHead = null;
    }

    public void joinCaravan(EntityElephant caravanHeadIn) {
        this.caravanHead = caravanHeadIn;
        this.caravanHead.caravanTail = this;
    }

    public boolean hasCaravanTrail() {
        return this.caravanTail != null;
    }

    public boolean inCaravan() {
        return this.caravanHead != null;
    }

    @Nullable
    public EntityElephant getCaravanHead() {
        return this.caravanHead;
    }

    @Override
    public double getMaxDistToItem() {
        return Math.pow(this.m_20205_() + 3.0f, 2.0);
    }

    public void m_19956_(Entity passenger, Entity.MoveFunction moveFunc) {
        if (this.m_20363_(passenger)) {
            float sinWave;
            float standAdd = -0.3f * this.standProgress;
            float scale = this.m_6162_() ? 0.5f : (this.isTusked() ? 1.1f : 1.0f);
            float sitAdd = -0.065f * this.sitProgress;
            float scaleY = scale * (2.4f * sitAdd - 0.4f * standAdd);
            if (passenger instanceof AbstractVillager) {
                AbstractVillager villager = (AbstractVillager)passenger;
                scaleY -= 0.3f;
            }
            float radius = scale * (0.5f + standAdd);
            float angle = (float)Math.PI / 180 * this.f_20883_;
            if (this.getAnimation() == ANIMATION_CHARGE_PREPARE) {
                sinWave = Mth.m_14031_((float)((float)(Math.PI * (double)((float)this.getAnimationTick() / 25.0f))));
                radius += sinWave * 0.2f * scale;
            }
            if (this.getAnimation() == ANIMATION_STOMP) {
                sinWave = Mth.m_14031_((float)((float)(Math.PI * (double)((float)this.getAnimationTick() / 20.0f))));
                radius -= sinWave * 1.0f * scale;
                scaleY += sinWave * 0.7f * scale;
            }
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            passenger.m_6034_(this.m_20185_() + extraX, this.m_20186_() + this.m_6048_() + (double)scaleY + passenger.m_6049_(), this.m_20189_() + extraZ);
        }
    }

    protected Vec3 m_274312_(Player player, Vec3 deltaIn) {
        if (player.f_20902_ != 0.0f) {
            float f = player.f_20902_ < 0.0f ? 0.5f : 1.0f;
            return new Vec3((double)(player.f_20900_ * 0.25f), 0.0, (double)(player.f_20902_ * 0.5f * f));
        }
        this.m_6858_(false);
        return Vec3.f_82478_;
    }

    protected void m_274498_(Player player, Vec3 vec3) {
        super.m_274498_(player, vec3);
        if (player.f_20902_ != 0.0f || player.f_20900_ != 0.0f) {
            this.m_19915_(player.m_146908_(), player.m_146909_() * 0.25f);
            this.f_20883_ = this.f_20885_ = this.m_146908_();
            this.f_19859_ = this.f_20885_;
            this.m_274367_(1.0f);
            this.m_21573_().m_26573_();
            this.m_6710_(null);
            this.m_6858_(true);
        }
    }

    protected float m_245547_(Player rider) {
        return (float)this.m_21133_(Attributes.f_22279_);
    }

    public double m_6048_() {
        float scale = this.m_6162_() ? 0.5f : (this.isTusked() ? 1.1f : 1.0f);
        float f = Math.min(0.25f, this.f_267362_.m_267731_());
        float f1 = this.f_267362_.m_267756_();
        float sitAdd = 0.0f;
        float standAdd = 0.0f;
        return (double)this.m_20206_() - (double)0.05f - (double)scale * ((double)(0.1f * Mth.m_14089_((float)(f1 * 1.4f)) * 1.4f * f) + (double)sitAdd + (double)standAdd);
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

    public void m_7023_(Vec3 vec3d) {
        if (this.isSitting()) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            vec3d = Vec3.f_82478_;
        }
        super.m_7023_(vec3d);
    }

    public void openGUI(Player playerEntity) {
        if (!this.m_9236_().f_46443_ && !this.m_20363_((Entity)playerEntity)) {
            NetworkHooks.openScreen((ServerPlayer)((ServerPlayer)playerEntity), (MenuProvider)new MenuProvider(){

                public AbstractContainerMenu m_7208_(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
                    return ChestMenu.m_39246_((int)p_createMenu_1_, (Inventory)p_createMenu_2_, (Container)EntityElephant.this.elephantInventory);
                }

                public Component m_5446_() {
                    return Component.m_237115_((String)"entity.alexsmobs.elephant.chest");
                }
            });
        }
    }

    public boolean isTrader() {
        return (Boolean)this.f_19804_.m_135370_(TRADER);
    }

    public void setTrader(boolean trader) {
        this.f_19804_.m_135381_(TRADER, (Object)trader);
    }

    public boolean triggerCharge(ItemStack stack) {
        if (this.m_6688_() != null && this.chargeCooldown == 0 && !this.charging && this.getAnimation() == NO_ANIMATION && this.isTusked()) {
            this.setAnimation(ANIMATION_CHARGE_PREPARE);
            this.eatItemEffect(stack);
            this.m_5634_(2.0f);
            return true;
        }
        return false;
    }

    public boolean canSpawnWithTraderHere() {
        return this.m_9236_().m_46749_(this.m_20183_()) && this.m_6914_((LevelReader)this.m_9236_()) && this.m_9236_().m_46859_(this.m_20183_().m_6630_(4));
    }

    class PanicGoal
    extends net.minecraft.world.entity.ai.goal.PanicGoal {
        public PanicGoal() {
            super((PathfinderMob)EntityElephant.this, 1.0);
        }

        public boolean m_8036_() {
            return (EntityElephant.this.m_6162_() || !EntityElephant.this.isTusked() || EntityElephant.this.m_6060_()) && super.m_8036_();
        }
    }

    private class AIWalkIdle
    extends RandomStrollGoal {
        public AIWalkIdle(EntityElephant e, double v) {
            super((PathfinderMob)e, v);
        }

        public boolean m_8036_() {
            this.f_25730_ = EntityElephant.this.isTusked() || !EntityElephant.this.inCaravan() ? 50 : 120;
            return super.m_8036_();
        }

        @Nullable
        protected Vec3 m_7037_() {
            return LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)(EntityElephant.this.isTusked() || !EntityElephant.this.inCaravan() ? 25 : 10), (int)7);
        }
    }

    class HurtByTargetGoal
    extends net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal {
        public HurtByTargetGoal() {
            super((PathfinderMob)EntityElephant.this, new Class[0]);
        }

        public void m_8056_() {
            if (EntityElephant.this.m_6162_() || !EntityElephant.this.isTusked()) {
                this.m_26047_();
                this.m_8041_();
            } else {
                super.m_8056_();
            }
        }

        protected void m_5766_(Mob mobIn, LivingEntity targetIn) {
            if (!(!(mobIn instanceof EntityElephant) || mobIn.m_6162_() && ((EntityElephant)mobIn).isTusked())) {
                super.m_5766_(mobIn, targetIn);
            }
        }
    }
}
