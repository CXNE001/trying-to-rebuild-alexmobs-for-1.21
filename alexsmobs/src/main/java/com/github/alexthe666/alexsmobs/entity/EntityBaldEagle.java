/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
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
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MoveFunction
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$TagValue
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.Tags$Items
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.IFalconry;
import com.github.alexthe666.alexsmobs.entity.IFollower;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHurtByTargetNotBaby;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.FlyingAIFollowOwner;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.message.MessageMosquitoDismount;
import com.github.alexthe666.alexsmobs.message.MessageMosquitoMountPlayer;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
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
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class EntityBaldEagle
extends TamableAnimal
implements IFollower,
IFalconry {
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityBaldEagle.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> TACKLING = SynchedEntityData.m_135353_(EntityBaldEagle.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HAS_CAP = SynchedEntityData.m_135353_(EntityBaldEagle.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.m_135353_(EntityBaldEagle.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.m_135353_(EntityBaldEagle.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityBaldEagle.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> LAUNCHED = SynchedEntityData.m_135353_(EntityBaldEagle.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final Ingredient TEMPT_ITEMS = Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42583_, (ItemLike)AMItemRegistry.FISH_OIL.get()});
    public float prevAttackProgress;
    public float attackProgress;
    public float prevFlyProgress;
    public float flyProgress;
    public float prevTackleProgress;
    public float tackleProgress;
    public float prevSwoopProgress;
    public float swoopProgress;
    public float prevFlapAmount;
    public float flapAmount;
    public float birdPitch = 0.0f;
    public float prevBirdPitch = 0.0f;
    public float prevSitProgress;
    public float sitProgress;
    private boolean isLandNavigator;
    private int timeFlying;
    private BlockPos orbitPos = null;
    private double orbitDist = 5.0;
    private boolean orbitClockwise = false;
    private int passengerTimer = 0;
    private int launchTime = 0;
    private int lastPlayerControlTime = 0;
    private int returnControlTime = 0;
    private int tackleCapCooldown = 0;
    private boolean controlledFlag = false;
    private int chunkLoadCooldown;
    private int stillTicksCounter = 0;

    protected EntityBaldEagle(EntityType<? extends TamableAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.switchNavigator(true);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 16.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22281_, 5.0).m_22268_(Attributes.f_22279_, (double)0.3f);
    }

    public static boolean canEagleSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return worldIn.m_45524_(pos, 0) > 8;
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this){

            public boolean m_8036_() {
                return super.m_8036_() && (EntityBaldEagle.this.m_20146_() < 30 || EntityBaldEagle.this.m_5448_() == null || !EntityBaldEagle.this.m_5448_().m_20072_() && EntityBaldEagle.this.m_20186_() > EntityBaldEagle.this.m_5448_().m_20186_());
            }
        });
        this.f_21345_.m_25352_(1, (Goal)new SitWhenOrderedToGoal((TamableAnimal)this));
        this.f_21345_.m_25352_(2, (Goal)new FlyingAIFollowOwner(this, 1.0, 25.0f, 2.0f, false));
        this.f_21345_.m_25352_(3, (Goal)new AITackle());
        this.f_21345_.m_25352_(4, (Goal)new AILandOnGlove());
        this.f_21345_.m_25352_(5, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(6, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_43938_(Stream.of(new Ingredient.TagValue(AMTagRegistry.BALD_EAGLE_TAMEABLES), new Ingredient.TagValue(AMTagRegistry.BALD_EAGLE_FOODSTUFFS))), false));
        this.f_21345_.m_25352_(7, (Goal)new AIWanderIdle());
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f){

            public boolean m_8036_() {
                return EntityBaldEagle.this.returnControlTime == 0 && super.m_8036_();
            }
        });
        this.f_21345_.m_25352_(9, (Goal)new RandomLookAroundGoal((Mob)this){

            public boolean m_8036_() {
                return EntityBaldEagle.this.returnControlTime == 0 && super.m_8036_();
            }
        });
        this.f_21346_.m_25352_(1, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(2, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(3, (Goal)new AnimalAIHurtByTargetNotBaby((Animal)this, new Class[0]));
        this.f_21346_.m_25352_(4, (Goal)new EntityAINearestTarget3D((Mob)this, LivingEntity.class, 55, true, true, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.BALD_EAGLE_TARGETS)){

            public boolean m_8036_() {
                return super.m_8036_() && !EntityBaldEagle.this.isLaunched() && EntityBaldEagle.this.getCommand() == 0;
            }
        });
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.BALD_EAGLE_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.BALD_EAGLE_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.BALD_EAGLE_HURT.get();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.baldEagleSpawnRolls, this.m_217043_(), spawnReasonIn);
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

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.BALD_EAGLE_BREEDABLES);
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigatorWide((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new MoveHelper(this);
            this.f_21344_ = new DirectPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    public boolean m_20223_(CompoundTag compound) {
        String s = this.m_20078_();
        compound.m_128359_("id", s);
        super.m_20223_(compound);
        return true;
    }

    public boolean m_20086_(CompoundTag compound) {
        if (!this.m_21824_()) {
            return super.m_20086_(compound);
        }
        String s = this.m_20078_();
        compound.m_128359_("id", s);
        this.m_20240_(compound);
        return true;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("BirdSitting", this.isSitting());
        compound.m_128379_("Launched", this.isLaunched());
        compound.m_128379_("HasCap", this.hasCap());
        compound.m_128405_("EagleCommand", this.getCommand());
        compound.m_128405_("LaunchTime", this.launchTime);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_21839_(compound.m_128471_("BirdSitting"));
        this.setLaunched(compound.m_128471_("Launched"));
        this.setCap(compound.m_128471_("HasCap"));
        this.setCommand(compound.m_128451_("EagleCommand"));
        this.launchTime = compound.m_128451_("LaunchTime");
    }

    public void m_7023_(Vec3 vec3d) {
        if (!this.shouldHoodedReturn() && this.hasCap() && this.m_21824_() && !this.m_20159_() || this.isSitting()) {
            super.m_7023_(Vec3.f_82478_);
            return;
        }
        super.m_7023_(vec3d);
    }

    public boolean m_7327_(Entity entityIn) {
        if (this.attackProgress == 0.0f && (Integer)this.f_19804_.m_135370_(ATTACK_TICK) == 0 && entityIn.m_6084_()) {
            double dist;
            double d = dist = this.isSitting() ? (double)(entityIn.m_20205_() + 1.0f) : (double)(entityIn.m_20205_() + 5.0f);
            if ((double)this.m_20270_(entityIn) < dist) {
                this.f_19804_.m_135381_(ATTACK_TICK, (Object)5);
            }
        }
        return true;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FLYING, (Object)false);
        this.f_19804_.m_135372_(HAS_CAP, (Object)false);
        this.f_19804_.m_135372_(TACKLING, (Object)false);
        this.f_19804_.m_135372_(LAUNCHED, (Object)false);
        this.f_19804_.m_135372_(ATTACK_TICK, (Object)0);
        this.f_19804_.m_135372_(COMMAND, (Object)0);
        this.f_19804_.m_135372_(SITTING, (Object)false);
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public void m_21839_(boolean sit) {
        this.f_19804_.m_135381_(SITTING, (Object)sit);
    }

    public int getCommand() {
        return (Integer)this.f_19804_.m_135370_(COMMAND);
    }

    public void setCommand(int command) {
        this.f_19804_.m_135381_(COMMAND, (Object)command);
    }

    public boolean isLaunched() {
        return (Boolean)this.f_19804_.m_135370_(LAUNCHED);
    }

    public void setLaunched(boolean flying) {
        this.f_19804_.m_135381_(LAUNCHED, (Object)flying);
    }

    public boolean isFlying() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    public void setFlying(boolean flying) {
        if (flying && this.m_6162_()) {
            flying = false;
        }
        this.f_19804_.m_135381_(FLYING, (Object)flying);
    }

    public boolean hasCap() {
        return (Boolean)this.f_19804_.m_135370_(HAS_CAP);
    }

    public void setCap(boolean cap) {
        this.f_19804_.m_135381_(HAS_CAP, (Object)cap);
    }

    public boolean isTackling() {
        return (Boolean)this.f_19804_.m_135370_(TACKLING);
    }

    public void setTackling(boolean tackling) {
        this.f_19804_.m_135381_(TACKLING, (Object)tackling);
    }

    @Override
    public void followEntity(TamableAnimal tameable, LivingEntity owner, double followSpeed) {
        if (this.m_20270_((Entity)owner) > 15.0f) {
            this.setFlying(true);
            this.m_21566_().m_6849_(owner.m_20185_(), owner.m_20186_() + (double)owner.m_20206_(), owner.m_20189_(), followSpeed);
        } else if (this.isFlying() && !this.isOverWaterOrVoid()) {
            BlockPos vec = this.getCrowGround(this.m_20183_());
            if (vec != null) {
                this.m_21566_().m_6849_((double)vec.m_123341_(), (double)vec.m_123342_(), (double)vec.m_123343_(), followSpeed);
            }
            if (this.m_20096_()) {
                this.setFlying(false);
            }
        } else {
            this.m_21573_().m_5624_((Entity)owner, followSpeed);
        }
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268612_) || super.m_6673_(source);
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        Item item = itemstack.m_41720_();
        InteractionResult type = super.m_6071_(player, hand);
        if (itemstack.m_204117_(AMTagRegistry.BALD_EAGLE_FOODSTUFFS) && this.m_21223_() < this.m_21233_()) {
            this.m_5634_(10.0f);
            if (!player.m_7500_()) {
                itemstack.m_41774_(1);
            }
            this.m_9236_().m_7605_((Entity)this, (byte)7);
            return InteractionResult.CONSUME;
        }
        if (itemstack.m_204117_(AMTagRegistry.BALD_EAGLE_TAMEABLES)) {
            if (itemstack.hasCraftingRemainingItem() && !player.m_150110_().f_35937_) {
                this.m_19983_(itemstack.getCraftingRemainingItem());
            }
            if (!player.m_7500_()) {
                itemstack.m_41774_(1);
            }
            if (this.f_19796_.m_188499_()) {
                this.m_9236_().m_7605_((Entity)this, (byte)7);
                this.m_21828_(player);
                this.setCommand(1);
            } else {
                this.m_9236_().m_7605_((Entity)this, (byte)6);
            }
            return InteractionResult.CONSUME;
        }
        if (this.m_21824_() && !this.m_6898_(itemstack)) {
            if (!this.m_6162_() && item == AMItemRegistry.FALCONRY_HOOD.get()) {
                if (!this.hasCap()) {
                    this.setCap(true);
                    if (!player.m_7500_()) {
                        itemstack.m_41774_(1);
                    }
                    this.m_146850_(GameEvent.f_223708_);
                    this.m_5496_(SoundEvents.f_11678_, this.m_6121_(), this.m_6100_());
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (itemstack.m_204117_(Tags.Items.SHEARS) && this.hasCap()) {
                    this.m_146850_(GameEvent.f_223708_);
                    this.m_5496_(SoundEvents.f_12344_, 1.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
                    if (!this.m_9236_().f_46443_ && player instanceof ServerPlayer) {
                        itemstack.m_220157_(1, this.f_19796_, (ServerPlayer)player);
                    }
                    this.m_19998_((ItemLike)AMItemRegistry.FALCONRY_HOOD.get());
                    this.setCap(false);
                    return InteractionResult.SUCCESS;
                }
                if (!(this.m_6162_() || this.getRidingFalcons((LivingEntity)player) > 0 || player.m_21120_(InteractionHand.MAIN_HAND).m_41720_() != AMItemRegistry.FALCONRY_GLOVE.get() && player.m_21120_(InteractionHand.OFF_HAND).m_41720_() != AMItemRegistry.FALCONRY_GLOVE.get())) {
                    this.f_19851_ = 30;
                    this.setLaunched(false);
                    this.m_20153_();
                    this.m_7998_((Entity)player, true);
                    if (!this.m_9236_().f_46443_) {
                        AlexsMobs.sendMSGToAll(new MessageMosquitoMountPlayer(this.m_19879_(), player.m_19879_()));
                    }
                    return InteractionResult.SUCCESS;
                }
                InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
                if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS) {
                    boolean sit;
                    this.setCommand((this.getCommand() + 1) % 3);
                    if (this.getCommand() == 3) {
                        this.setCommand(0);
                    }
                    player.m_5661_((Component)Component.m_237110_((String)("entity.alexsmobs.all.command_" + this.getCommand()), (Object[])new Object[]{this.m_7755_()}), true);
                    boolean bl = sit = this.getCommand() == 2;
                    if (sit) {
                        this.m_21839_(true);
                        return InteractionResult.SUCCESS;
                    }
                    this.m_21839_(false);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return type;
    }

    @Override
    public boolean shouldFollow() {
        return this.getCommand() == 1 && !this.isLaunched();
    }

    public void m_6083_() {
        Entity entity = this.m_20202_();
        if (!(!this.m_20159_() || entity.m_6084_() && this.m_6084_())) {
            this.m_8127_();
        } else if (this.m_21824_() && entity instanceof LivingEntity && this.m_21830_((LivingEntity)entity)) {
            this.m_20334_(0.0, 0.0, 0.0);
            this.m_8119_();
            if (this.m_20159_()) {
                Entity mount = this.m_20202_();
                if (mount instanceof Player) {
                    float yawAdd = 0.0f;
                    if (((Player)mount).m_21120_(InteractionHand.MAIN_HAND).m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
                        yawAdd = ((Player)mount).m_5737_() == HumanoidArm.LEFT ? 135.0f : -135.0f;
                    } else if (((Player)mount).m_21120_(InteractionHand.OFF_HAND).m_41720_() == AMItemRegistry.FALCONRY_GLOVE.get()) {
                        yawAdd = ((Player)mount).m_5737_() == HumanoidArm.LEFT ? -135.0f : 135.0f;
                    } else {
                        this.setCommand(2);
                        this.m_21839_(true);
                        this.m_6038_();
                        this.m_20359_(mount);
                    }
                    float birdYaw = yawAdd * 0.5f;
                    this.f_20883_ = Mth.m_14177_((float)(((LivingEntity)mount).f_20883_ + birdYaw));
                    this.m_146922_(Mth.m_14177_((float)(mount.m_146908_() + birdYaw)));
                    this.f_20885_ = Mth.m_14177_((float)(((LivingEntity)mount).f_20885_ + birdYaw));
                    float radius = 0.6f;
                    float angle = (float)Math.PI / 180 * (((LivingEntity)mount).f_20883_ - 180.0f + yawAdd);
                    double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
                    double extraZ = radius * Mth.m_14089_((float)angle);
                    this.m_6034_(mount.m_20185_() + extraX, Math.max(mount.m_20186_() + (double)(mount.m_20206_() * 0.45f), mount.m_20186_()), mount.m_20189_() + extraZ);
                }
                if (!mount.m_6084_()) {
                    this.m_6038_();
                }
            }
        } else {
            super.m_6083_();
        }
    }

    public void m_8119_() {
        int attackTick;
        boolean sit;
        float yMot;
        super.m_8119_();
        this.prevAttackProgress = this.attackProgress;
        this.prevBirdPitch = this.birdPitch;
        this.prevTackleProgress = this.tackleProgress;
        this.prevFlyProgress = this.flyProgress;
        this.prevFlapAmount = this.flapAmount;
        this.prevSwoopProgress = this.swoopProgress;
        this.prevSitProgress = this.sitProgress;
        this.birdPitch = yMot = -((float)this.m_20184_().f_82480_ * 57.295776f);
        if (this.isFlying()) {
            if (this.flyProgress < 5.0f) {
                this.flyProgress += 1.0f;
            }
        } else if (this.flyProgress > 0.0f) {
            this.flyProgress -= 1.0f;
        }
        if (this.isTackling()) {
            if (this.tackleProgress < 5.0f) {
                this.tackleProgress += 1.0f;
            }
        } else if (this.tackleProgress > 0.0f) {
            this.tackleProgress -= 1.0f;
        }
        boolean bl = sit = this.isSitting() || this.m_20159_();
        if (sit) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        this.launchTime = this.isLaunched() ? ++this.launchTime : 0;
        if (this.lastPlayerControlTime > 0) {
            --this.lastPlayerControlTime;
        }
        if (this.lastPlayerControlTime <= 0) {
            this.controlledFlag = false;
        }
        if (yMot < 0.1f) {
            this.flapAmount = Math.min(-yMot * 0.2f, 1.0f);
            if (this.swoopProgress > 0.0f) {
                this.swoopProgress -= 1.0f;
            }
        } else {
            this.flapAmount = this.flapAmount > 0.0f ? (this.flapAmount -= Math.min(this.flapAmount, 0.1f)) : 0.0f;
            if (this.swoopProgress < yMot * 0.2f) {
                this.swoopProgress = Math.min(yMot * 0.2f, this.swoopProgress + 1.0f);
            }
        }
        if (this.isTackling()) {
            this.flapAmount = Math.min(2.0f, this.flapAmount + 0.2f);
        }
        if (!this.m_9236_().f_46443_) {
            if (this.isFlying()) {
                if (this.isLandNavigator) {
                    this.switchNavigator(false);
                }
            } else if (!this.isLandNavigator) {
                this.switchNavigator(true);
            }
            if (!(this.tackleCapCooldown != 0 || !this.isTackling() || this.m_20160_() || this.m_5448_() != null && this.m_5448_().m_6084_())) {
                this.setTackling(false);
            }
            if (this.isFlying()) {
                ++this.timeFlying;
                this.m_20242_(true);
                if ((this.isSitting() || this.m_20159_() || this.m_27593_()) && !this.isLaunched()) {
                    this.setFlying(false);
                }
                if (this.m_5448_() != null && this.m_5448_().m_20186_() < this.m_20185_() && !this.m_20160_()) {
                    this.m_20256_(this.m_20184_().m_82542_(1.0, 0.9, 1.0));
                }
            } else {
                this.timeFlying = 0;
                this.m_20242_(false);
            }
            if (this.m_20072_() && this.m_20160_()) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, (double)0.1f, 0.0));
            }
            if (this.isSitting() && !this.isLaunched()) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, (double)-0.1f, 0.0));
            }
            if (this.m_5448_() != null && this.m_20072_()) {
                this.timeFlying = 0;
                this.setFlying(true);
            }
            if (this.m_20096_() && this.timeFlying > 30 && this.isFlying() && !this.m_20072_()) {
                this.setFlying(false);
            }
        }
        if ((attackTick = ((Integer)this.f_19804_.m_135370_(ATTACK_TICK)).intValue()) > 0) {
            if (attackTick == 2 && this.m_5448_() != null && (double)this.m_20270_((Entity)this.m_5448_()) < (double)this.m_5448_().m_20205_() + 2.0) {
                this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 2.0f);
            }
            this.f_19804_.m_135381_(ATTACK_TICK, (Object)((Integer)this.f_19804_.m_135370_(ATTACK_TICK) - 1));
            if (this.attackProgress < 5.0f) {
                this.attackProgress += 1.0f;
            }
        } else if (this.attackProgress > 0.0f) {
            this.attackProgress -= 1.0f;
        }
        if (this.m_20159_()) {
            this.setFlying(false);
            this.setTackling(false);
        }
        if (this.f_19851_ > 0) {
            --this.f_19851_;
        }
        if (this.returnControlTime > 0) {
            --this.returnControlTime;
        }
        if (this.tackleCapCooldown > 0) {
            --this.tackleCapCooldown;
        }
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return (AgeableMob)((EntityType)AMEntityRegistry.BALD_EAGLE.get()).m_20615_((Level)p_241840_1_);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
        float radius = -9.45f - (float)this.m_217043_().m_188503_(24) - radiusAdd;
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), 0, (int)(fleePos.m_7094_() + extraZ));
        BlockPos ground = this.getCrowGround(radialPos);
        int distFromGround = (int)this.m_20186_() - ground.m_123342_();
        int flightHeight = 7 + this.m_217043_().m_188503_(10);
        BlockPos newPos = ground.m_6630_(distFromGround > 8 ? flightHeight : this.m_217043_().m_188503_(7) + 4);
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 1.0) {
            return Vec3.m_82512_((Vec3i)newPos);
        }
        return null;
    }

    private BlockPos getCrowGround(BlockPos in) {
        BlockPos position = new BlockPos(in.m_123341_(), (int)this.m_20186_(), in.m_123343_());
        while (position.m_123342_() < 320 && !this.m_9236_().m_6425_(position).m_76178_()) {
            position = position.m_7494_();
        }
        while (position.m_123342_() > -64 && !this.m_9236_().m_8055_(position).m_280296_()) {
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
        BlockPos ground = this.getCrowGround(radialPos);
        if (ground.m_123342_() == -64) {
            return this.m_20182_();
        }
        ground = this.m_20183_();
        while (ground.m_123342_() > -64 && !this.m_9236_().m_8055_(ground).m_280296_()) {
            ground = ground.m_7495_();
        }
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)ground.m_7494_()))) {
            return Vec3.m_82512_((Vec3i)ground);
        }
        return null;
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    private Vec3 getOrbitVec(Vec3 vector3d, float gatheringCircleDist) {
        float angle = (float)Math.PI / 180 * (float)this.orbitDist * (float)(this.orbitClockwise ? -this.f_19797_ : this.f_19797_);
        double extraX = gatheringCircleDist * Mth.m_14031_((float)angle);
        double extraZ = gatheringCircleDist * Mth.m_14089_((float)angle);
        if (this.orbitPos != null) {
            Vec3 pos = new Vec3((double)this.orbitPos.m_123341_() + extraX, (double)(this.orbitPos.m_123342_() + this.f_19796_.m_188503_(2) - 2), (double)this.orbitPos.m_123343_() + extraZ);
            if (this.m_9236_().m_46859_(AMBlockPos.fromVec3(pos))) {
                return pos;
            }
        }
        return null;
    }

    private boolean isOverWaterOrVoid() {
        BlockPos position = this.m_20183_();
        while (position.m_123342_() > -64 && this.m_9236_().m_46859_(position)) {
            position = position.m_7495_();
        }
        return !this.m_9236_().m_6425_(position).m_76178_() || position.m_123342_() <= -64;
    }

    public void m_19956_(Entity passenger, Entity.MoveFunction moveFunc) {
        if (this.m_20363_(passenger)) {
            float radius = 0.3f;
            float angle = (float)Math.PI / 180 * this.f_20883_;
            double extraX = 0.3f * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = 0.3f * Mth.m_14089_((float)angle);
            passenger.m_146922_(this.f_20883_ + 90.0f);
            if (passenger instanceof LivingEntity) {
                LivingEntity living = (LivingEntity)passenger;
                living.f_20883_ = this.f_20883_ + 90.0f;
            }
            float extraY = 0.0f;
            if (passenger instanceof AbstractFish && !passenger.m_20072_()) {
                extraY = 0.1f;
            }
            moveFunc.m_20372_(passenger, this.m_20185_() + extraX, this.m_20186_() - (double)0.3f + (double)extraY + (double)(passenger.m_20206_() * 0.3f), this.m_20189_() + extraZ);
            ++this.passengerTimer;
            if (this.m_6084_() && this.passengerTimer > 0 && this.passengerTimer % 40 == 0) {
                passenger.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 1.0f);
            }
        }
    }

    public boolean canBeRiddenInWater(Entity rider) {
        return true;
    }

    public Vec3 m_7688_(LivingEntity livingEntity) {
        return new Vec3(this.m_20185_(), this.m_20191_().f_82289_, this.m_20189_());
    }

    public boolean shouldHoodedReturn() {
        if (this.m_269323_() != null && (!this.m_269323_().m_6084_() || this.m_269323_().m_6144_())) {
            return true;
        }
        return !this.m_6084_() || this.f_19817_ || this.launchTime > 12000 || this.f_19818_ > 0 || this.m_213877_();
    }

    public void m_142687_(Entity.RemovalReason reason) {
        if (this.lastPlayerControlTime == 0 && !this.m_20159_()) {
            super.m_142687_(reason);
        }
    }

    public void directFromPlayer(float rotationYaw, float rotationPitch, boolean loadChunk, Entity over) {
        LivingEntity owner = this.m_269323_();
        if (owner != null && this.m_20270_((Entity)owner) > 150.0f) {
            this.returnControlTime = 100;
        }
        this.stillTicksCounter = Math.abs(this.f_19854_ - this.m_20185_()) > (double)0.1f || Math.abs(this.f_19855_ - this.m_20186_()) > (double)0.1f || Math.abs(this.f_19856_ - this.m_20189_()) > (double)0.1f ? 0 : ++this.stillTicksCounter;
        int stillTPthreshold = AMConfig.falconryTeleportsBack ? 200 : 6000;
        this.m_21839_(false);
        this.setLaunched(true);
        if (owner != null && (this.returnControlTime > 0 && AMConfig.falconryTeleportsBack || this.stillTicksCounter > stillTPthreshold && this.m_20270_((Entity)owner) > 30.0f)) {
            this.m_20359_((Entity)owner);
            this.returnControlTime = 0;
            this.stillTicksCounter = 0;
            this.launchTime = Math.max(this.launchTime, 12000);
        }
        if (!this.m_9236_().f_46443_) {
            if (this.returnControlTime > 0 && owner != null) {
                this.m_21563_().m_24960_((Entity)owner, 30.0f, 30.0f);
            } else {
                this.f_20883_ = rotationYaw;
                this.m_146922_(rotationYaw);
                this.f_20885_ = rotationYaw;
                this.m_146926_(rotationPitch);
            }
            if (rotationPitch < 10.0f && this.m_20096_()) {
                this.setFlying(true);
            }
            float yawOffset = rotationYaw + 90.0f;
            float rad = 3.0f;
            float speed = 1.2f;
            if (this.returnControlTime > 0) {
                this.m_21566_().m_6849_(owner.m_20185_(), owner.m_20186_() + 10.0, owner.m_20189_(), (double)1.2f);
            } else {
                this.m_21566_().m_6849_(this.m_20185_() + 4.5 * Math.cos(yawOffset * ((float)Math.PI / 180)), this.m_20186_() - 3.0 * Math.sin(rotationPitch * ((float)Math.PI / 180)), this.m_20189_() + 3.0 * Math.sin(yawOffset * ((float)Math.PI / 180)), (double)1.2f);
            }
            if (loadChunk) {
                this.loadChunkOnServer(this.m_20183_());
            }
            this.m_6703_(null);
            this.m_6710_(null);
            if (over == null) {
                List list = this.m_9236_().m_6249_((Entity)this, this.m_20191_().m_82400_(3.0), EntitySelector.f_20406_);
                Entity closest = null;
                for (Entity e : list) {
                    if (closest != null && !(this.m_20270_(e) < this.m_20270_(closest))) continue;
                    closest = e;
                }
                over = closest;
            }
        }
        if (over != null && over != owner && !this.m_7307_(over) && this.canFalconryAttack(over) && this.tackleCapCooldown == 0 && (double)this.m_20270_(over) <= (double)over.m_20205_() + 4.0) {
            this.setTackling(true);
            if ((double)this.m_20270_(over) <= (double)over.m_20205_() + 2.0) {
                float speedDamage = (float)Math.ceil(Mth.m_14008_((double)(this.m_20184_().m_82553_() + 0.2), (double)0.0, (double)1.2) * 3.333);
                over.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 5.0f + speedDamage + (float)this.f_19796_.m_188503_(2));
                this.tackleCapCooldown = 22;
            }
        }
        this.lastPlayerControlTime = 10;
        this.controlledFlag = true;
    }

    @Override
    public float getHandOffset() {
        return 0.8f;
    }

    private boolean canFalconryAttack(Entity over) {
        return !(over instanceof ItemEntity) && (!(over instanceof LivingEntity) || !this.m_21830_((LivingEntity)over));
    }

    public void awardKillScore(LivingEntity entity, int score, DamageSource src) {
        if (this.isLaunched() && this.hasCap() && this.m_21824_() && this.m_269323_() != null && this.m_269323_() instanceof ServerPlayer && this.m_20270_((Entity)this.m_269323_()) >= 100.0f) {
            AMAdvancementTriggerRegistry.BALD_EAGLE_CHALLENGE.trigger((ServerPlayer)this.m_269323_());
        }
        super.m_5993_((Entity)entity, score, src);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (this.m_6673_(source)) {
            return false;
        }
        Entity entity = source.m_7639_();
        if (entity != null && this.m_21824_() && !(entity instanceof Player) && !(entity instanceof AbstractArrow) && this.isLaunched()) {
            amount = (amount + 1.0f) / 4.0f;
        }
        return super.m_6469_(source, amount);
    }

    public void loadChunkOnServer(BlockPos center) {
        if (!this.m_9236_().f_46443_) {
            ServerLevel serverWorld = (ServerLevel)this.m_9236_();
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    ChunkPos pos = new ChunkPos(this.m_20183_().m_7918_(i * 16, 0, j * 16));
                    serverWorld.m_8602_(pos.f_45578_, pos.f_45579_, true);
                }
            }
        }
    }

    @Override
    public void onLaunch(Player player, Entity pointedEntity) {
        this.setLaunched(true);
        this.m_21839_(false);
        this.setCommand(0);
        if (this.hasCap()) {
            this.setFlying(true);
            this.m_21566_().m_6849_(this.m_20185_(), this.m_20186_(), this.m_20189_(), (double)0.1f);
            if (this.m_9236_().f_46443_) {
                AlexsMobs.sendMSGToServer(new MessageMosquitoDismount(this.m_19879_(), player.m_19879_()));
            }
            AlexsMobs.PROXY.setRenderViewEntity((Entity)this);
        } else {
            this.m_21573_().m_26573_();
            this.m_21566_().m_6849_(this.m_20185_(), this.m_20186_(), this.m_20189_(), (double)0.1f);
            if (pointedEntity != null && pointedEntity.m_6084_() && !this.m_7307_(pointedEntity)) {
                this.setFlying(true);
                if (pointedEntity instanceof LivingEntity) {
                    LivingEntity pointedLivingEntity = (LivingEntity)pointedEntity;
                    this.m_6710_(pointedLivingEntity);
                }
            } else {
                this.setFlying(false);
                this.setCommand(2);
                this.m_21839_(true);
            }
        }
    }

    private class AITackle
    extends Goal {
        protected EntityBaldEagle eagle;
        private int circleTime;
        private int maxCircleTime = 10;

        public AITackle() {
            this.eagle = EntityBaldEagle.this;
        }

        public boolean m_8036_() {
            return this.eagle.m_5448_() != null && !this.eagle.controlledFlag && !this.eagle.m_20160_();
        }

        public void m_8056_() {
            this.eagle.orbitPos = null;
        }

        public void m_8041_() {
            this.circleTime = 0;
            this.maxCircleTime = 60 + EntityBaldEagle.this.f_19796_.m_188503_(60);
        }

        public void m_8037_() {
            boolean smallPrey;
            LivingEntity target = this.eagle.m_5448_();
            boolean bl = smallPrey = target != null && target.m_20206_() < 1.0f && target.m_20205_() < 0.7f && !(target instanceof EntityBaldEagle) || target instanceof AbstractFish;
            if (this.eagle.orbitPos != null && this.circleTime < this.maxCircleTime) {
                Vec3 vec;
                ++this.circleTime;
                this.eagle.setTackling(false);
                this.eagle.setFlying(true);
                if (target != null) {
                    int i = 0;
                    int up = 2 + this.eagle.m_217043_().m_188503_(4);
                    this.eagle.orbitPos = target.m_20183_().m_6630_((int)target.m_20206_());
                    while (this.eagle.m_9236_().m_46859_(this.eagle.orbitPos) && i < up) {
                        ++i;
                        this.eagle.orbitPos = this.eagle.orbitPos.m_7494_();
                    }
                }
                if ((vec = this.eagle.getOrbitVec(Vec3.f_82478_, 4 + EntityBaldEagle.this.f_19796_.m_188503_(2))) != null) {
                    this.eagle.m_21566_().m_6849_(vec.f_82479_, vec.f_82480_, vec.f_82481_, (double)1.2f);
                }
            } else if (target != null) {
                if (this.eagle.isFlying() || this.eagle.m_20072_()) {
                    double d0 = this.eagle.m_20185_() - target.m_20185_();
                    double d2 = this.eagle.m_20189_() - target.m_20189_();
                    double xzDist = Math.sqrt(d0 * d0 + d2 * d2);
                    double yAddition = target.m_20206_();
                    if (xzDist > 15.0) {
                        yAddition = 3.0;
                    }
                    this.eagle.setTackling(true);
                    this.eagle.m_21566_().m_6849_(target.m_20185_(), target.m_20186_() + yAddition, target.m_20189_(), this.eagle.m_20072_() ? (double)1.3f : 1.0);
                } else {
                    this.eagle.m_21573_().m_5624_((Entity)target, 1.0);
                }
                if (this.eagle.m_20270_((Entity)target) < target.m_20205_() + 2.5f) {
                    if (this.eagle.isTackling()) {
                        if (smallPrey) {
                            this.eagle.setFlying(true);
                            this.eagle.timeFlying = 0;
                            float radius = 0.3f;
                            float angle = (float)Math.PI / 180 * this.eagle.f_20883_;
                            double extraX = 0.3f * Mth.m_14031_((float)((float)Math.PI + angle));
                            double extraZ = 0.3f * Mth.m_14089_((float)angle);
                            target.m_146922_(this.eagle.f_20883_ + 90.0f);
                            if (target instanceof LivingEntity) {
                                LivingEntity living = target;
                                living.f_20883_ = this.eagle.f_20883_ + 90.0f;
                            }
                            target.m_6034_(this.eagle.m_20185_() + extraX, this.eagle.m_20186_() - (double)0.4f + (double)(target.m_20206_() * 0.45f), this.eagle.m_20189_() + extraZ);
                            target.m_7998_((Entity)this.eagle, true);
                        } else {
                            target.m_6469_(this.eagle.m_269291_().m_269333_((LivingEntity)this.eagle), 5.0f);
                            this.eagle.setFlying(false);
                            this.eagle.orbitPos = target.m_20183_().m_6630_(2);
                            this.circleTime = 0;
                            this.maxCircleTime = 60 + EntityBaldEagle.this.f_19796_.m_188503_(60);
                        }
                    } else {
                        this.eagle.m_7327_((Entity)target);
                    }
                } else if (this.eagle.m_20270_((Entity)target) > 12.0f || target.m_20072_()) {
                    this.eagle.setFlying(true);
                }
            }
            if (this.eagle.isLaunched()) {
                this.eagle.setFlying(true);
            }
        }
    }

    private class AILandOnGlove
    extends Goal {
        protected EntityBaldEagle eagle;
        private int seperateTime = 0;

        public AILandOnGlove() {
            this.eagle = EntityBaldEagle.this;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            return this.eagle.isLaunched() && !this.eagle.controlledFlag && this.eagle.m_21824_() && !this.eagle.m_20159_() && !this.eagle.m_20160_() && (this.eagle.m_5448_() == null || !this.eagle.m_5448_().m_6084_());
        }

        public void m_8037_() {
            LivingEntity owner;
            if (this.eagle.m_20184_().m_82556_() < 0.03) {
                ++this.seperateTime;
            }
            if ((owner = this.eagle.m_269323_()) != null) {
                if (this.seperateTime > 200) {
                    this.seperateTime = 0;
                    this.eagle.m_20359_((Entity)owner);
                }
                this.eagle.setFlying(true);
                double d0 = this.eagle.m_20185_() - owner.m_20185_();
                double d2 = this.eagle.m_20189_() - owner.m_20189_();
                double xzDist = Math.sqrt(d0 * d0 + d2 * d2);
                double yAdd = xzDist > 14.0 ? 5.0 : 0.0;
                this.eagle.m_21566_().m_6849_(owner.m_20185_(), owner.m_20186_() + yAdd + (double)owner.m_20192_(), owner.m_20189_(), 1.0);
                if ((double)this.eagle.m_20270_((Entity)owner) < (double)owner.m_20205_() + 1.4) {
                    this.eagle.setLaunched(false);
                    if (this.eagle.getRidingFalcons(owner) <= 0) {
                        this.eagle.m_20329_((Entity)owner);
                        if (!this.eagle.m_9236_().f_46443_) {
                            AlexsMobs.sendMSGToAll(new MessageMosquitoMountPlayer(this.eagle.m_19879_(), owner.m_19879_()));
                        }
                    } else {
                        this.eagle.setCommand(2);
                        this.eagle.m_21839_(true);
                    }
                }
            }
        }

        public void m_8041_() {
            this.seperateTime = 0;
        }
    }

    private class AIWanderIdle
    extends Goal {
        protected final EntityBaldEagle eagle;
        protected double x;
        protected double y;
        protected double z;
        private boolean flightTarget = false;
        private int orbitResetCooldown = 0;
        private int maxOrbitTime = 360;
        private int orbitTime = 0;

        public AIWanderIdle() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.eagle = EntityBaldEagle.this;
        }

        public boolean m_8036_() {
            if (this.orbitResetCooldown < 0) {
                ++this.orbitResetCooldown;
            }
            if (this.eagle.m_5448_() != null && this.eagle.m_5448_().m_6084_() && !this.eagle.m_20160_() || this.eagle.m_20159_() || this.eagle.isSitting() || this.eagle.controlledFlag) {
                return false;
            }
            if (this.eagle.m_217043_().m_188503_(15) != 0 && !this.eagle.isFlying()) {
                return false;
            }
            if (this.eagle.m_6162_()) {
                this.flightTarget = false;
            } else if (this.eagle.m_20072_()) {
                this.flightTarget = true;
            } else if (this.eagle.hasCap()) {
                this.flightTarget = false;
            } else if (this.eagle.m_20096_()) {
                this.flightTarget = EntityBaldEagle.this.f_19796_.m_188499_();
            } else {
                if (this.orbitResetCooldown == 0 && EntityBaldEagle.this.f_19796_.m_188503_(6) == 0) {
                    this.orbitResetCooldown = 400;
                    this.eagle.orbitPos = this.eagle.m_20183_();
                    this.eagle.orbitDist = 4 + EntityBaldEagle.this.f_19796_.m_188503_(5);
                    this.eagle.orbitClockwise = EntityBaldEagle.this.f_19796_.m_188499_();
                    this.orbitTime = 0;
                    this.maxOrbitTime = (int)(360.0f + 360.0f * EntityBaldEagle.this.f_19796_.m_188501_());
                }
                this.flightTarget = this.eagle.m_20160_() || EntityBaldEagle.this.f_19796_.m_188503_(7) > 0 && this.eagle.timeFlying < 700;
            }
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
            if (this.orbitResetCooldown > 0) {
                --this.orbitResetCooldown;
            }
            if (this.orbitResetCooldown < 0) {
                ++this.orbitResetCooldown;
            }
            if (this.orbitResetCooldown > 0 && this.eagle.orbitPos != null) {
                if (this.orbitTime < this.maxOrbitTime && !this.eagle.m_20072_()) {
                    ++this.orbitTime;
                } else {
                    this.orbitTime = 0;
                    this.eagle.orbitPos = null;
                    this.orbitResetCooldown = -400 - EntityBaldEagle.this.f_19796_.m_188503_(400);
                }
            }
            if (this.eagle.f_19862_ && !this.eagle.m_20096_()) {
                this.m_8041_();
            }
            if (this.flightTarget) {
                this.eagle.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else if (!this.eagle.m_20096_() && this.eagle.isFlying()) {
                if (!this.eagle.m_20072_()) {
                    this.eagle.m_20256_(this.eagle.m_20184_().m_82542_((double)1.2f, (double)0.6f, (double)1.2f));
                }
            } else {
                this.eagle.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
            if (!this.flightTarget && this.eagle.m_20096_() && EntityBaldEagle.this.isFlying()) {
                this.eagle.setFlying(false);
                this.orbitTime = 0;
                this.eagle.orbitPos = null;
                this.orbitResetCooldown = -400 - EntityBaldEagle.this.f_19796_.m_188503_(400);
            }
            if (this.eagle.timeFlying > 30 && EntityBaldEagle.this.isFlying() && (!EntityBaldEagle.this.m_9236_().m_46859_(this.eagle.m_20099_()) || this.eagle.m_20096_()) && !this.eagle.m_20072_()) {
                this.eagle.setFlying(false);
                this.orbitTime = 0;
                this.eagle.orbitPos = null;
                this.orbitResetCooldown = -400 - EntityBaldEagle.this.f_19796_.m_188503_(400);
            }
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vector3d = this.eagle.m_20182_();
            if (this.eagle.m_21824_() && this.eagle.getCommand() == 1 && this.eagle.m_269323_() != null) {
                vector3d = this.eagle.m_269323_().m_20182_();
                this.eagle.orbitPos = this.eagle.m_269323_().m_20183_();
            }
            if (this.orbitResetCooldown > 0 && this.eagle.orbitPos != null) {
                return this.eagle.getOrbitVec(vector3d, 4 + EntityBaldEagle.this.f_19796_.m_188503_(2));
            }
            if (this.eagle.m_20160_() || this.eagle.isOverWaterOrVoid()) {
                this.flightTarget = true;
            }
            if (this.flightTarget) {
                if (this.eagle.timeFlying < 500 || this.eagle.m_20160_() || this.eagle.isOverWaterOrVoid()) {
                    return this.eagle.getBlockInViewAway(vector3d, 0.0f);
                }
                return this.eagle.getBlockGrounding(vector3d);
            }
            return LandRandomPos.m_148488_((PathfinderMob)this.eagle, (int)10, (int)7);
        }

        public boolean m_8045_() {
            if (this.eagle.isSitting()) {
                return false;
            }
            if (this.flightTarget) {
                return this.eagle.isFlying() && this.eagle.m_20275_(this.x, this.y, this.z) > 2.0;
            }
            return !this.eagle.m_21573_().m_26571_() && !this.eagle.m_20160_();
        }

        public void m_8056_() {
            if (this.flightTarget) {
                this.eagle.setFlying(true);
                this.eagle.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.eagle.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
        }

        public void m_8041_() {
            this.eagle.m_21573_().m_26573_();
            super.m_8041_();
        }
    }

    static class MoveHelper
    extends MoveControl {
        private final EntityBaldEagle parentEntity;

        public MoveHelper(EntityBaldEagle bird) {
            super((Mob)bird);
            this.parentEntity = bird;
        }

        public void m_8126_() {
            if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
                Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
                double d5 = vector3d.m_82553_();
                if (d5 < 0.3) {
                    this.f_24981_ = MoveControl.Operation.WAIT;
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82490_(0.5));
                } else {
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d.m_82490_(this.f_24978_ * 0.05 / d5)));
                    Vec3 vector3d1 = this.parentEntity.m_20184_();
                    this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
                    this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                }
            }
        }

        private boolean canReach(Vec3 p_220673_1_, int p_220673_2_) {
            AABB axisalignedbb = this.parentEntity.m_20191_();
            for (int i = 1; i < p_220673_2_; ++i) {
                axisalignedbb = axisalignedbb.m_82383_(p_220673_1_);
                if (this.parentEntity.m_9236_().m_45756_((Entity)this.parentEntity, axisalignedbb)) continue;
                return false;
            }
            return true;
        }
    }
}
