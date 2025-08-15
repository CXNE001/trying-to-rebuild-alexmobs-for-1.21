/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ItemParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.Container
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
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LeapAtTargetGoal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.ai.util.DefaultRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.IronGolem
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.item.trading.MerchantOffers
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.common.Tags$Items
 *  net.minecraftforge.fluids.FluidType
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityBlueJay;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.github.alexthe666.alexsmobs.entity.IFollower;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHurtByTargetNotBaby;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILootChests;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIPanicBaby;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.entity.ai.ILootsChests;
import com.github.alexthe666.alexsmobs.entity.ai.RaccoonAIBeg;
import com.github.alexthe666.alexsmobs.entity.ai.RaccoonAIWash;
import com.github.alexthe666.alexsmobs.entity.ai.TameableAIDestroyTurtleEggs;
import com.github.alexthe666.alexsmobs.entity.ai.TameableAIFollowOwner;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
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
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidType;

public class EntityRaccoon
extends TamableAnimal
implements IAnimatedEntity,
IFollower,
ITargetsDroppedItems,
ILootsChests {
    private static final EntityDataAccessor<Boolean> STANDING = SynchedEntityData.m_135353_(EntityRaccoon.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityRaccoon.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> BEGGING = SynchedEntityData.m_135353_(EntityRaccoon.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> WASHING = SynchedEntityData.m_135353_(EntityRaccoon.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Optional<BlockPos>> WASH_POS = SynchedEntityData.m_135353_(EntityRaccoon.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.m_135353_(EntityRaccoon.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> CARPET_COLOR = SynchedEntityData.m_135353_(EntityRaccoon.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float prevStandProgress;
    public float standProgress;
    public float prevBegProgress;
    public float begProgress;
    public float prevWashProgress;
    public float washProgress;
    public float prevSitProgress;
    public float sitProgress;
    public int maxStandTime = 75;
    private int standingTime = 0;
    private int stealCooldown = 0;
    public int lookForWaterBeforeEatingTimer = 0;
    private int animationTick;
    private Animation currentAnimation;
    private int pickupItemCooldown = 0;
    @Nullable
    private UUID eggThrowerUUID = null;
    public boolean forcedSit = false;
    public static final Animation ANIMATION_ATTACK = Animation.create((int)12);
    private static final TargetingConditions VILLAGER_STEAL_PREDICATE = TargetingConditions.m_148352_().m_26883_(20.0).m_148355_();
    private static final TargetingConditions IRON_GOLEM_PREDICATE = TargetingConditions.m_148352_().m_26883_(20.0).m_148355_();

    protected EntityRaccoon(EntityType type, Level world) {
        super(type, world);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
    }

    protected float m_6108_() {
        return 0.98f;
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.RACCOON_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.RACCOON_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.RACCOON_HURT.get();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.raccoonSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new SitWhenOrderedToGoal((TamableAnimal)this));
        this.f_21345_.m_25352_(1, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(2, (Goal)new RaccoonAIWash(this));
        this.f_21345_.m_25352_(3, (Goal)new TameableAIFollowOwner(this, 1.3, 10.0f, 2.0f, false));
        this.f_21345_.m_25352_(4, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(5, (Goal)new LeapAtTargetGoal((Mob)this, 0.4f));
        this.f_21345_.m_25352_(6, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.1, true));
        this.f_21345_.m_25352_(7, (Goal)new AnimalAILootChests((Animal)this, 16));
        this.f_21345_.m_25352_(8, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(9, (Goal)new RaccoonAIBeg(this, 0.65));
        this.f_21345_.m_25352_(10, (Goal)new AnimalAIPanicBaby((Animal)this, 1.25));
        this.f_21345_.m_25352_(11, (Goal)new AIStealFromVillagers(this));
        this.f_21345_.m_25352_(12, (Goal)new StrollGoal(200));
        this.f_21345_.m_25352_(13, (Goal)new TameableAIDestroyTurtleEggs(this, 1.0, 3));
        this.f_21345_.m_25352_(14, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 120, 1.0, 14, 7));
        this.f_21345_.m_25352_(15, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 15.0f));
        this.f_21345_.m_25352_(15, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new AnimalAIHurtByTargetNotBaby((Animal)this, new Class[0]));
        this.f_21346_.m_25352_(1, new CreatureAITargetItems((PathfinderMob)this, false));
        this.f_21346_.m_25352_(3, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(4, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
    }

    public boolean m_7307_(Entity entityIn) {
        if (entityIn instanceof EntityBlueJay) {
            EntityBlueJay jay = (EntityBlueJay)entityIn;
            return jay.getRaccoonUUID() != null && jay.getRaccoonUUID().equals(this.m_20148_());
        }
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

    public boolean m_7327_(Entity entityIn) {
        if (this.getAnimation() == NO_ANIMATION) {
            this.setAnimation(ANIMATION_ATTACK);
        }
        return true;
    }

    protected void m_5907_() {
        super.m_5907_();
        if (this.getColor() != null) {
            if (!this.m_9236_().f_46443_) {
                this.m_19998_((ItemLike)this.getCarpetItemBeingWorn());
            }
            this.setColor(null);
        }
    }

    @Nullable
    public DyeColor getColor() {
        int lvt_1_1_ = (Integer)this.f_19804_.m_135370_(CARPET_COLOR);
        return lvt_1_1_ == -1 ? null : DyeColor.m_41053_((int)lvt_1_1_);
    }

    public void setColor(@Nullable DyeColor color) {
        this.f_19804_.m_135381_(CARPET_COLOR, (Object)(color == null ? -1 : color.m_41060_()));
    }

    public Item getCarpetItemBeingWorn() {
        if (this.getColor() != null) {
            return EntityElephant.DYE_COLOR_ITEM_MAP.get(this.getColor());
        }
        return Items.f_41852_;
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.RACCOON_BREEDABLES);
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        boolean owner;
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        boolean bl = owner = this.m_21824_() && this.m_21830_((LivingEntity)player);
        if (itemstack.m_204117_(AMTagRegistry.RACCOON_TEAMING_FOODS) && this.bondWithBlueJays(player.m_20148_())) {
            this.m_142075_(player, hand, itemstack);
            this.m_9236_().m_7605_((Entity)this, (byte)93);
            return InteractionResult.SUCCESS;
        }
        if (this.m_21824_() && !this.m_21205_().m_41619_()) {
            if (!this.m_9236_().f_46443_) {
                this.m_19983_(this.m_21205_().m_41777_());
            }
            this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
            this.pickupItemCooldown = 60;
            return InteractionResult.SUCCESS;
        }
        if (owner && itemstack.m_204117_(ItemTags.f_215867_)) {
            DyeColor color = EntityElephant.getCarpetColor(itemstack);
            if (color != this.getColor()) {
                if (this.getColor() != null) {
                    this.m_19998_((ItemLike)this.getCarpetItemBeingWorn());
                }
                this.m_146850_(GameEvent.f_223708_);
                this.m_5496_(SoundEvents.f_12100_, 1.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
                itemstack.m_41774_(1);
                this.setColor(color);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        if (owner && this.getColor() != null && itemstack.m_204117_(Tags.Items.SHEARS)) {
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_(SoundEvents.f_12344_, 1.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
            if (this.getColor() != null) {
                this.m_19998_((ItemLike)this.getCarpetItemBeingWorn());
            }
            this.setColor(null);
            return InteractionResult.SUCCESS;
        }
        if (this.m_21824_() && EntityRaccoon.isRaccoonFood(itemstack) && !this.m_6898_(itemstack) && this.m_21223_() < this.m_21233_()) {
            if (this.m_21205_().m_41619_()) {
                ItemStack copy = itemstack.m_41777_();
                copy.m_41764_(1);
                this.m_21008_(InteractionHand.MAIN_HAND, copy);
                this.onEatItem();
                if (itemstack.hasCraftingRemainingItem()) {
                    this.m_19983_(itemstack.getCraftingRemainingItem());
                }
                if (!player.m_7500_()) {
                    itemstack.m_41774_(1);
                }
                this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
            } else {
                this.m_146850_(GameEvent.f_157806_);
                this.m_5496_(SoundEvents.f_11912_, this.m_6121_(), this.m_6100_());
                this.m_5634_(5.0f);
            }
            this.m_142075_(player, hand, itemstack);
            return InteractionResult.SUCCESS;
        }
        InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
        if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS && this.m_21824_() && this.m_21830_((LivingEntity)player) && !EntityRaccoon.isRaccoonFood(itemstack) && !player.m_6144_()) {
            boolean sit;
            this.setCommand(this.getCommand() + 1);
            if (this.getCommand() == 3) {
                this.setCommand(0);
            }
            player.m_5661_((Component)Component.m_237110_((String)("entity.alexsmobs.all.command_" + this.getCommand()), (Object[])new Object[]{this.m_7755_()}), true);
            boolean bl2 = sit = this.getCommand() == 2;
            if (sit) {
                this.forcedSit = true;
                this.m_21839_(true);
                return InteractionResult.SUCCESS;
            }
            this.forcedSit = false;
            this.m_21839_(false);
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("RacSitting", this.isSitting());
        compound.m_128379_("ForcedToSit", this.forcedSit);
        compound.m_128405_("RacCommand", this.getCommand());
        compound.m_128405_("Carpet", ((Integer)this.f_19804_.m_135370_(CARPET_COLOR)).intValue());
        compound.m_128405_("StealCooldown", this.stealCooldown);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_21839_(compound.m_128471_("RacSitting"));
        this.forcedSit = compound.m_128471_("ForcedToSit");
        this.setCommand(compound.m_128451_("RacCommand"));
        this.f_19804_.m_135381_(CARPET_COLOR, (Object)compound.m_128451_("Carpet"));
        this.stealCooldown = compound.m_128451_("StealCooldown");
    }

    public void setCommand(int command) {
        this.f_19804_.m_135381_(COMMAND, (Object)command);
    }

    public int getCommand() {
        return (Integer)this.f_19804_.m_135370_(COMMAND);
    }

    public void m_21839_(boolean sit) {
        this.f_19804_.m_135381_(SITTING, (Object)sit);
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public static boolean isRaccoonFood(ItemStack stack) {
        return stack.m_41614_() || stack.m_204117_(AMTagRegistry.RACCOON_FOODSTUFFS);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 9.0).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22279_, 0.25);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (this.m_6673_(source)) {
            return false;
        }
        Entity entity = source.m_7639_();
        this.m_21839_(false);
        if (entity != null && this.m_21824_() && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
            amount = (amount + 1.0f) / 4.0f;
        }
        return super.m_6469_(source, amount);
    }

    protected void m_8022_() {
        boolean flag = !(this.m_6688_() instanceof Mob);
        boolean flag1 = !(this.m_20202_() instanceof Boat);
        boolean flag2 = this.m_146895_() instanceof EntityBlueJay;
        this.f_21345_.m_25360_(Goal.Flag.MOVE, flag || flag2);
        this.f_21345_.m_25360_(Goal.Flag.JUMP, flag && flag1 || flag2);
        this.f_21345_.m_25360_(Goal.Flag.LOOK, flag || flag2);
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevStandProgress = this.standProgress;
        this.prevBegProgress = this.begProgress;
        this.prevWashProgress = this.washProgress;
        this.prevSitProgress = this.sitProgress;
        if (this.isStanding()) {
            if (this.standProgress < 5.0f) {
                this.standProgress += 1.0f;
            }
        } else if (this.standProgress > 0.0f) {
            this.standProgress -= 1.0f;
        }
        if (this.isBegging()) {
            if (this.begProgress < 5.0f) {
                this.begProgress += 1.0f;
            }
        } else if (this.begProgress > 0.0f) {
            this.begProgress -= 1.0f;
        }
        if (this.isWashing()) {
            if (this.washProgress < 5.0f) {
                this.washProgress += 1.0f;
            }
        } else if (this.washProgress > 0.0f) {
            this.washProgress -= 1.0f;
        }
        if (this.isSitting()) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (this.isStanding() && ++this.standingTime > this.maxStandTime) {
            this.setStanding(false);
            this.standingTime = 0;
            this.maxStandTime = 75 + this.f_19796_.m_188503_(50);
        }
        if (!this.m_9236_().f_46443_) {
            if (this.lookForWaterBeforeEatingTimer > 0) {
                --this.lookForWaterBeforeEatingTimer;
            } else if (!this.isWashing() && this.canTargetItem(this.m_21205_())) {
                this.onEatItem();
                if (this.m_21205_().hasCraftingRemainingItem()) {
                    this.m_19983_(this.m_21205_().getCraftingRemainingItem());
                }
                this.m_21205_().m_41774_(1);
            }
        }
        if (this.isWashing() && this.getWashPos() != null) {
            BlockPos washingPos = this.getWashPos();
            if (this.m_20275_((double)washingPos.m_123341_() + 0.5, (double)washingPos.m_123342_() + 0.5, (double)washingPos.m_123343_() + 0.5) < 3.0) {
                int j = 0;
                while ((float)j < 4.0f) {
                    double d2 = this.f_19796_.m_188500_();
                    double d3 = this.f_19796_.m_188500_();
                    Vec3 vector3d = this.m_20184_();
                    this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123769_, (double)washingPos.m_123341_() + d2, (double)((float)washingPos.m_123342_() + 0.8f), (double)washingPos.m_123343_() + d3, vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_);
                    ++j;
                }
            } else {
                this.setWashing(false);
            }
        }
        if (!this.m_9236_().f_46443_ && this.m_5448_() != null && this.m_142582_((Entity)this.m_5448_()) && this.m_20270_((Entity)this.m_5448_()) < 4.0f && this.getAnimation() == ANIMATION_ATTACK && this.getAnimationTick() == 5) {
            float f1 = this.m_146908_() * ((float)Math.PI / 180);
            this.m_20256_(this.m_20184_().m_82520_((double)(-Mth.m_14031_((float)f1) * -0.06f), 0.0, (double)(Mth.m_14089_((float)f1) * -0.06f)));
            this.m_5448_().m_147240_((double)0.35f, this.m_5448_().m_20185_() - this.m_20185_(), this.m_5448_().m_20189_() - this.m_20189_());
            this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)this.m_21051_(Attributes.f_22281_).m_22115_());
        }
        if (this.stealCooldown > 0) {
            --this.stealCooldown;
        }
        if (this.pickupItemCooldown > 0) {
            --this.pickupItemCooldown;
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public void onEatItem() {
        this.m_5634_(10.0f);
        this.m_9236_().m_7605_((Entity)this, (byte)92);
        this.m_146850_(GameEvent.f_157806_);
        this.m_5496_(SoundEvents.f_11912_, this.m_6121_(), this.m_6100_());
    }

    public void postWashItem(ItemStack stack) {
        if (stack.m_204117_(AMTagRegistry.RACCOON_TAMEABLES) && this.eggThrowerUUID != null && !this.m_21824_()) {
            if (this.m_217043_().m_188501_() < 0.3f) {
                this.m_7105_(true);
                this.m_21816_(this.eggThrowerUUID);
                Player player = this.m_9236_().m_46003_(this.eggThrowerUUID);
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.f_10590_.m_68829_((ServerPlayer)player, (Animal)this);
                }
                this.m_9236_().m_7605_((Entity)this, (byte)7);
            } else {
                this.m_9236_().m_7605_((Entity)this, (byte)6);
            }
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id == 92) {
            for (int i = 0; i < 6 + this.f_19796_.m_188503_(3); ++i) {
                double d2 = this.f_19796_.m_188583_() * 0.02;
                double d0 = this.f_19796_.m_188583_() * 0.02;
                double d1 = this.f_19796_.m_188583_() * 0.02;
                this.m_9236_().m_7106_((ParticleOptions)new ItemParticleOption(ParticleTypes.f_123752_, this.m_21120_(InteractionHand.MAIN_HAND)), this.m_20185_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, this.m_20186_() + (double)(this.m_20206_() * 0.5f) + (double)(this.f_19796_.m_188501_() * this.m_20206_() * 0.5f), this.m_20189_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, d0, d1, d2);
            }
        } else if (id == 93) {
            for (int i = 0; i < 6 + this.f_19796_.m_188503_(3); ++i) {
                double d2 = this.f_19796_.m_188583_() * 0.02;
                double d0 = this.f_19796_.m_188583_() * 0.02;
                double d1 = this.f_19796_.m_188583_() * 0.02;
                this.m_9236_().m_7106_((ParticleOptions)new ItemParticleOption(ParticleTypes.f_123752_, new ItemStack((ItemLike)Items.f_151079_)), this.m_20185_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, this.m_20186_() + (double)(this.m_20206_() * 0.5f) + (double)(this.f_19796_.m_188501_() * this.m_20206_() * 0.5f), this.m_20189_() + (double)(this.f_19796_.m_188501_() * this.m_20205_()) - (double)this.m_20205_() * 0.5, d0, d1, d2);
            }
        } else {
            super.m_7822_(id);
        }
    }

    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        return true;
    }

    public boolean isStanding() {
        return (Boolean)this.f_19804_.m_135370_(STANDING);
    }

    public void setStanding(boolean standing) {
        this.f_19804_.m_135381_(STANDING, (Object)standing);
    }

    public boolean isBegging() {
        return (Boolean)this.f_19804_.m_135370_(BEGGING);
    }

    public void setBegging(boolean begging) {
        this.f_19804_.m_135381_(BEGGING, (Object)begging);
    }

    public boolean isWashing() {
        return (Boolean)this.f_19804_.m_135370_(WASHING);
    }

    public void setWashing(boolean washing) {
        this.f_19804_.m_135381_(WASHING, (Object)washing);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(STANDING, (Object)false);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(BEGGING, (Object)false);
        this.f_19804_.m_135372_(WASHING, (Object)false);
        this.f_19804_.m_135372_(CARPET_COLOR, (Object)-1);
        this.f_19804_.m_135372_(COMMAND, (Object)0);
        this.f_19804_.m_135372_(WASH_POS, Optional.empty());
    }

    public BlockPos getWashPos() {
        return ((Optional)this.f_19804_.m_135370_(WASH_POS)).orElse(null);
    }

    public void setWashPos(BlockPos washingPos) {
        this.f_19804_.m_135381_(WASH_POS, Optional.ofNullable(washingPos));
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
        if (animation == ANIMATION_ATTACK) {
            this.maxStandTime = 15;
            this.setStanding(true);
        }
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_ATTACK};
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return (AgeableMob)((EntityType)AMEntityRegistry.RACCOON.get()).m_20615_((Level)serverWorld);
    }

    public void m_7023_(Vec3 vec3d) {
        if (this.isSitting() || this.isWashing()) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            vec3d = Vec3.f_82478_;
        }
        super.m_7023_(vec3d);
    }

    @Override
    public boolean shouldFollow() {
        return this.getCommand() == 1;
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return EntityRaccoon.isRaccoonFood(stack) && this.pickupItemCooldown == 0;
    }

    @Override
    public void onGetItem(ItemEntity e) {
        this.lookForWaterBeforeEatingTimer = 100;
        ItemStack duplicate = e.m_32055_().m_41777_();
        duplicate.m_41764_(1);
        if (!this.m_21120_(InteractionHand.MAIN_HAND).m_41619_() && !this.m_9236_().f_46443_) {
            this.m_5552_(this.m_21120_(InteractionHand.MAIN_HAND), 0.0f);
        }
        Entity thrower = e.m_19749_();
        if (e.m_32055_().m_204117_(AMTagRegistry.RACCOON_TEAMING_FOODS) && thrower != null && this.bondWithBlueJays(thrower.m_20148_())) {
            this.m_9236_().m_7605_((Entity)this, (byte)93);
        } else {
            this.m_21008_(InteractionHand.MAIN_HAND, duplicate);
        }
        this.eggThrowerUUID = e.m_32055_().m_204117_(AMTagRegistry.RACCOON_TAMEABLES) && thrower != null ? thrower.m_20148_() : null;
    }

    public double m_6048_() {
        return (double)this.m_20206_() * 0.45;
    }

    private boolean bondWithBlueJays(UUID uuid) {
        AABB allyBox = this.m_20191_().m_82400_(48.0);
        boolean any = false;
        for (EntityBlueJay entity : this.m_9236_().m_45976_(EntityBlueJay.class, allyBox)) {
            if (entity.getFeedTime() <= 0 || entity.getLastFeederUUID() == null || !entity.getLastFeederUUID().equals(uuid)) continue;
            entity.setRaccoon((Entity)this);
            entity.setFeedTime(0);
            any = true;
        }
        return any;
    }

    @Override
    public boolean isLootable(Container inventory) {
        for (int i = 0; i < inventory.m_6643_(); ++i) {
            if (!this.shouldLootItem(inventory.m_8020_(i))) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldLootItem(ItemStack stack) {
        return EntityRaccoon.isRaccoonFood(stack);
    }

    public boolean isHoldingSugar() {
        return this.m_21205_().m_204117_(AMTagRegistry.RACOON_DISSOLVES);
    }

    public BlockPos getLightPosition() {
        BlockPos pos = AMBlockPos.fromVec3(this.m_20182_());
        if (!this.m_9236_().m_8055_(pos).m_60815_()) {
            return pos.m_7494_();
        }
        return pos;
    }

    public boolean isRigby() {
        String name = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        if (name == null) {
            return false;
        }
        String lowercaseName = name.toLowerCase(Locale.ROOT);
        return lowercaseName.contains("rigby");
    }

    private class AIStealFromVillagers
    extends Goal {
        EntityRaccoon raccoon;
        AbstractVillager target;
        int golemCheckTime = 0;
        int cooldown = 0;
        int fleeTime = 0;

        private AIStealFromVillagers(EntityRaccoon raccoon) {
            this.raccoon = raccoon;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            if (this.cooldown > 0) {
                --this.cooldown;
                return false;
            }
            if (this.raccoon != null && this.raccoon.stealCooldown == 0 && this.raccoon.m_21205_() != null && this.raccoon.m_21205_().m_41619_()) {
                AbstractVillager villager = this.getNearbyVillagers();
                if (!this.isGolemNearby() && villager != null) {
                    this.target = villager;
                }
                this.cooldown = 150;
                return this.target != null;
            }
            return false;
        }

        public boolean m_8045_() {
            return this.target != null && this.raccoon != null;
        }

        public void m_8041_() {
            this.target = null;
            this.cooldown = 200 + EntityRaccoon.this.f_19796_.m_188503_(200);
            this.golemCheckTime = 0;
            this.fleeTime = 0;
        }

        public void m_8037_() {
            if (this.target != null) {
                ++this.golemCheckTime;
                if (this.fleeTime > 0) {
                    Vec3 fleevec;
                    --this.fleeTime;
                    if (this.raccoon.m_21573_().m_26571_() && (fleevec = DefaultRandomPos.m_148407_((PathfinderMob)this.raccoon, (int)16, (int)7, (Vec3)this.raccoon.m_20182_())) != null) {
                        this.raccoon.m_21573_().m_26519_(fleevec.f_82479_, fleevec.f_82480_, fleevec.f_82481_, (double)1.3f);
                    }
                    if (this.fleeTime == 0) {
                        this.m_8041_();
                    }
                } else {
                    this.raccoon.m_21573_().m_5624_((Entity)this.target, 1.0);
                    if (this.raccoon.m_20270_((Entity)this.target) < 1.7f) {
                        this.raccoon.setStanding(true);
                        this.raccoon.maxStandTime = 15;
                        MerchantOffers offers = this.target.m_6616_();
                        if (offers == null || offers.isEmpty() || offers.size() < 1) {
                            this.m_8041_();
                        } else {
                            MerchantOffer offer = (MerchantOffer)offers.get(offers.size() <= 1 ? 0 : this.raccoon.m_217043_().m_188503_(offers.size() - 1));
                            if (offer != null) {
                                ItemStack stealStack;
                                ItemStack itemStack = stealStack = offer.m_45368_().m_41720_() == Items.f_42616_ ? offer.m_45352_() : offer.m_45368_();
                                if (stealStack.m_41619_()) {
                                    this.m_8041_();
                                } else {
                                    offer.m_45374_();
                                    ItemStack copy = stealStack.m_41777_();
                                    copy.m_41764_(1);
                                    this.raccoon.m_21008_(InteractionHand.MAIN_HAND, copy);
                                    this.fleeTime = 60 + EntityRaccoon.this.f_19796_.m_188503_(60);
                                    this.raccoon.m_21573_().m_26573_();
                                    EntityRaccoon.this.lookForWaterBeforeEatingTimer = 120 + EntityRaccoon.this.f_19796_.m_188503_(60);
                                    this.target.m_6469_(EntityRaccoon.this.m_269291_().m_269264_(), 0.0f);
                                    this.raccoon.stealCooldown = 24000 + EntityRaccoon.this.f_19796_.m_188503_(48000);
                                }
                            }
                        }
                    }
                    if (this.golemCheckTime % 30 == 0 && EntityRaccoon.this.f_19796_.m_188499_() && this.isGolemNearby()) {
                        this.m_8041_();
                    }
                }
            }
        }

        @Nullable
        private boolean isGolemNearby() {
            List lvt_1_1_ = this.raccoon.m_9236_().m_45971_(IronGolem.class, IRON_GOLEM_PREDICATE, (LivingEntity)this.raccoon, this.raccoon.m_20191_().m_82400_(25.0));
            return !lvt_1_1_.isEmpty();
        }

        @Nullable
        private AbstractVillager getNearbyVillagers() {
            List lvt_1_1_ = this.raccoon.m_9236_().m_45971_(AbstractVillager.class, VILLAGER_STEAL_PREDICATE, (LivingEntity)this.raccoon, this.raccoon.m_20191_().m_82400_(20.0));
            double lvt_2_1_ = 10000.0;
            AbstractVillager lvt_4_1_ = null;
            for (AbstractVillager lvt_6_1_ : lvt_1_1_) {
                if (!(lvt_6_1_.m_21223_() > 2.0f) || lvt_6_1_.m_6616_().isEmpty() || !(this.raccoon.m_20280_((Entity)lvt_6_1_) < lvt_2_1_)) continue;
                lvt_4_1_ = lvt_6_1_;
                lvt_2_1_ = this.raccoon.m_20280_((Entity)lvt_6_1_);
            }
            return lvt_4_1_;
        }
    }

    class StrollGoal
    extends MoveThroughVillageGoal {
        public StrollGoal(int p_i50726_3_) {
            super((PathfinderMob)EntityRaccoon.this, 1.0, true, p_i50726_3_, () -> false);
        }

        public void m_8056_() {
            super.m_8056_();
        }

        public boolean m_8036_() {
            return super.m_8036_() && this.canFoxMove();
        }

        public boolean m_8045_() {
            return super.m_8045_() && this.canFoxMove();
        }

        private boolean canFoxMove() {
            return !EntityRaccoon.this.isWashing() && !EntityRaccoon.this.isSitting() && EntityRaccoon.this.m_5448_() == null;
        }
    }
}
