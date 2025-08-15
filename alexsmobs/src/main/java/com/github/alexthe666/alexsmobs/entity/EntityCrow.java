/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
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
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
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
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockAndTintGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.common.capabilities.ForgeCapabilities
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.items.IItemHandler
 *  net.minecraftforge.items.ItemHandlerHelper
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.entity.ai.CrowAICircleCrops;
import com.github.alexthe666.alexsmobs.entity.ai.CrowAIFollowOwner;
import com.github.alexthe666.alexsmobs.entity.ai.CrowAIMelee;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.message.MessageCrowDismount;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicate;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
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
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class EntityCrow
extends TamableAnimal
implements ITargetsDroppedItems {
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityCrow.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.m_135353_(EntityCrow.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityCrow.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.m_135353_(EntityCrow.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Optional<BlockPos>> PERCH_POS = SynchedEntityData.m_135353_(EntityCrow.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    public float prevFlyProgress;
    public float flyProgress;
    public float prevAttackProgress;
    public float attackProgress;
    public int fleePumpkinFlag = 0;
    public boolean aiItemFlag = false;
    public boolean aiItemFrameFlag = false;
    public float prevSitProgress;
    public float sitProgress;
    private boolean isLandNavigator;
    private int timeFlying = 0;
    @Nullable
    private UUID seedThrowerID;
    private int heldItemTime = 0;
    private int checkPerchCooldown = 0;
    private final boolean gatheringClockwise = false;

    protected EntityCrow(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.m_21441_(BlockPathTypes.DANGER_FIRE, -1.0f);
        this.m_21441_(BlockPathTypes.WATER, -1.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 16.0f);
        this.m_21441_(BlockPathTypes.COCOA, -1.0f);
        this.m_21441_(BlockPathTypes.FENCE, -1.0f);
        this.switchNavigator(false);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 8.0).m_22268_(Attributes.f_22281_, 1.0).m_22268_(Attributes.f_22279_, (double)0.2f);
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new SitWhenOrderedToGoal((TamableAnimal)this));
        this.f_21345_.m_25352_(2, (Goal)new CrowAIMelee(this));
        this.f_21345_.m_25352_(3, (Goal)new CrowAIFollowOwner(this, 1.0, 4.0f, 2.0f, true));
        this.f_21345_.m_25352_(4, (Goal)new AIDepositChests());
        this.f_21345_.m_25352_(4, (Goal)new AIScatter());
        this.f_21345_.m_25352_(5, (Goal)new AIAvoidPumpkins());
        this.f_21345_.m_25352_(5, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(6, (Goal)new CrowAICircleCrops(this));
        this.f_21345_.m_25352_(7, (Goal)new AIWalkIdle());
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, PathfinderMob.class, 6.0f));
        this.f_21345_.m_25352_(10, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new AITargetItems((PathfinderMob)this, false, false, 40, 16));
        this.f_21346_.m_25352_(2, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(3, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(4, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[]{Player.class}).m_26044_(new Class[0]));
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.crowSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static <T extends Mob> boolean canCrowSpawn(EntityType<EntityCrow> crow, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return EntityCrow.m_186209_((BlockAndTintGetter)worldIn, (BlockPos)pos);
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

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigation((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new FlightMoveController((Mob)this, 0.7f, false);
            this.f_21344_ = new DirectPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) {
        return false;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev;
        if (this.m_6673_(source)) {
            return false;
        }
        Entity entity = source.m_7639_();
        this.m_21839_(false);
        if (entity != null && this.m_21824_() && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
            amount = (amount + 1.0f) / 4.0f;
        }
        if (this.m_20159_()) {
            this.m_8127_();
        }
        if ((prev = super.m_6469_(source, amount)) && !this.m_21205_().m_41619_()) {
            this.m_19983_(this.m_21205_().m_41777_());
            this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
        }
        return prev;
    }

    public void m_6083_() {
        Entity entity = this.m_20202_();
        if (this.m_20159_() && !entity.m_6084_()) {
            this.m_8127_();
        } else if (this.m_21824_() && entity instanceof LivingEntity && this.m_21830_((LivingEntity)entity)) {
            this.m_20334_(0.0, 0.0, 0.0);
            this.m_8119_();
            Entity riding = this.m_20202_();
            if (this.m_20159_()) {
                int i = riding.m_20197_().indexOf(this);
                float radius = 0.43f;
                float angle = (float)Math.PI / 180 * (((Player)riding).f_20883_ + (float)(i == 0 ? -90 : 90));
                double extraX = 0.43f * Mth.m_14031_((float)((float)Math.PI + angle));
                double extraZ = 0.43f * Mth.m_14089_((float)angle);
                double extraY = riding.m_6144_() ? 1.25 : 1.45;
                this.f_20885_ = ((Player)riding).f_20885_;
                this.f_19859_ = ((Player)riding).f_20885_;
                this.m_6034_(riding.m_20185_() + extraX, riding.m_20186_() + extraY, riding.m_20189_() + extraZ);
                if (!riding.m_6084_() || this.f_19851_ == 0 && riding.m_6144_() || ((Player)riding).m_21255_() || this.m_5448_() != null && this.m_5448_().m_6084_()) {
                    this.m_6038_();
                    if (!this.m_9236_().f_46443_) {
                        AlexsMobs.sendMSGToAll(new MessageCrowDismount(this.m_19879_(), riding.m_19879_()));
                    }
                }
            }
        } else {
            super.m_6083_();
        }
    }

    public int getRidingCrows(LivingEntity player) {
        int crowCount = 0;
        for (Entity e : player.m_20197_()) {
            if (!(e instanceof EntityCrow)) continue;
            ++crowCount;
        }
        return crowCount;
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.CROW_BREEDABLES) && this.m_21824_();
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        if (!this.m_21205_().m_41619_() && type != InteractionResult.SUCCESS) {
            this.m_19983_(this.m_21205_().m_41777_());
            this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
            return InteractionResult.SUCCESS;
        }
        InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
        if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS && this.m_21824_() && this.m_21830_((LivingEntity)player) && !this.m_6898_(itemstack)) {
            if (this.isCrowEdible(itemstack) && this.m_21205_().m_41619_()) {
                ItemStack cop = itemstack.m_41777_();
                cop.m_41764_(1);
                this.m_21008_(InteractionHand.MAIN_HAND, cop);
                itemstack.m_41774_(1);
            }
            this.setCommand(this.getCommand() + 1);
            if (this.getCommand() == 4) {
                this.setCommand(0);
            }
            if (this.getCommand() == 3) {
                player.m_5661_((Component)Component.m_237110_((String)"entity.alexsmobs.crow.command_3", (Object[])new Object[]{this.m_7755_()}), true);
            } else {
                player.m_5661_((Component)Component.m_237110_((String)("entity.alexsmobs.all.command_" + this.getCommand()), (Object[])new Object[]{this.m_7755_()}), true);
            }
            boolean sit = this.getCommand() == 2;
            this.m_21839_(sit);
            return InteractionResult.SUCCESS;
        }
        return super.m_6071_(player, hand);
    }

    public void m_8119_() {
        boolean isSittingOrPassenger;
        super.m_8119_();
        this.prevAttackProgress = this.attackProgress;
        this.prevFlyProgress = this.flyProgress;
        this.prevSitProgress = this.sitProgress;
        boolean bl = isSittingOrPassenger = this.isSitting() || this.m_20159_();
        if (isSittingOrPassenger) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (this.isFlying()) {
            if (this.flyProgress < 5.0f) {
                this.flyProgress += 1.0f;
            }
        } else if (this.flyProgress > 0.0f) {
            this.flyProgress -= 1.0f;
        }
        if (this.fleePumpkinFlag > 0) {
            --this.fleePumpkinFlag;
        }
        if (!this.m_9236_().f_46443_) {
            boolean isFlying = this.isFlying();
            if (isFlying && this.isLandNavigator) {
                this.switchNavigator(false);
            }
            if (!isFlying && !this.isLandNavigator) {
                this.switchNavigator(true);
            }
            if (isFlying) {
                ++this.timeFlying;
                this.m_20242_(true);
                if (this.isSitting() || this.m_20159_() || this.m_27593_()) {
                    this.setFlying(false);
                }
            } else {
                this.timeFlying = 0;
                this.m_20242_(false);
            }
        }
        if (!this.m_21205_().m_41619_()) {
            ++this.heldItemTime;
            if (this.heldItemTime > 60 && this.isCrowEdible(this.m_21205_()) && (!this.m_21824_() || this.m_21223_() < this.m_21233_())) {
                this.heldItemTime = 0;
                this.m_5634_(4.0f);
                this.m_146850_(GameEvent.f_157806_);
                this.m_5496_(SoundEvents.f_12190_, this.m_6121_(), this.m_6100_());
                if (this.seedThrowerID != null && this.m_21205_().m_204117_(AMTagRegistry.CROW_TAMEABLES) && !this.m_21824_()) {
                    if (this.m_217043_().m_188501_() < 0.3f) {
                        this.m_7105_(true);
                        this.setCommand(1);
                        this.m_21816_(this.seedThrowerID);
                        Player player = this.m_9236_().m_46003_(this.seedThrowerID);
                        if (player instanceof ServerPlayer) {
                            ServerPlayer serverPlayer = (ServerPlayer)player;
                            CriteriaTriggers.f_10590_.m_68829_(serverPlayer, (Animal)this);
                        }
                        this.m_9236_().m_7605_((Entity)this, (byte)7);
                    } else {
                        this.m_9236_().m_7605_((Entity)this, (byte)6);
                    }
                }
                if (this.m_21205_().hasCraftingRemainingItem()) {
                    this.m_19983_(this.m_21205_().getCraftingRemainingItem());
                }
                this.m_21205_().m_41774_(1);
            }
        } else {
            this.heldItemTime = 0;
        }
        if (this.f_19851_ > 0) {
            --this.f_19851_;
        }
        if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) > 0) {
            this.f_19804_.m_135381_(ATTACK_TICK, (Object)((Integer)this.f_19804_.m_135370_(ATTACK_TICK) - 1));
            if (this.attackProgress < 5.0f) {
                this.attackProgress += 1.0f;
            }
        } else if (this.attackProgress > 0.0f) {
            this.attackProgress -= 1.0f;
        }
        if (this.checkPerchCooldown > 0) {
            --this.checkPerchCooldown;
        }
        if (this.m_21824_()) {
            if (this.checkPerchCooldown == 0) {
                this.checkPerchCooldown = 50;
                BlockState below = this.m_20075_();
                if (below.m_204336_(AMTagRegistry.CROW_HOME_BLOCKS)) {
                    this.m_5634_(1.0f);
                    this.m_9236_().m_7605_((Entity)this, (byte)67);
                    this.setPerchPos(this.m_20099_());
                }
            }
            if (this.getCommand() == 3 && this.getPerchPos() != null && this.checkPerchCooldown == 0) {
                this.checkPerchCooldown = 120;
                BlockState below = this.m_9236_().m_8055_(this.getPerchPos());
                if (below.m_204336_(AMTagRegistry.CROW_HOME_BLOCKS)) {
                    this.m_9236_().m_7605_((Entity)this, (byte)68);
                    this.setPerchPos(null);
                    this.setCommand(2);
                    this.m_21839_(true);
                }
            }
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id == 67) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.f_19796_.m_188583_() * 0.02;
                double d1 = this.f_19796_.m_188583_() * 0.02;
                double d2 = this.f_19796_.m_188583_() * 0.02;
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123748_, this.m_20208_(1.0), this.m_20187_() + 0.5, this.m_20262_(1.0), d0, d1, d2);
            }
        } else if (id == 68) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.f_19796_.m_188583_() * 0.02;
                double d1 = this.f_19796_.m_188583_() * 0.02;
                double d2 = this.f_19796_.m_188583_() * 0.02;
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123792_, this.m_20208_(1.0), this.m_20187_() + 0.5, this.m_20262_(1.0), d0, d1, d2);
            }
        } else {
            super.m_7822_(id);
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Flying", this.isFlying());
        compound.m_128379_("MonkeySitting", this.isSitting());
        compound.m_128405_("Command", this.getCommand());
        if (this.getPerchPos() != null) {
            compound.m_128405_("PerchX", this.getPerchPos().m_123341_());
            compound.m_128405_("PerchY", this.getPerchPos().m_123342_());
            compound.m_128405_("PerchZ", this.getPerchPos().m_123343_());
        }
    }

    public void m_7023_(Vec3 vec3d) {
        if (this.isSitting()) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            vec3d = Vec3.f_82478_;
        }
        if (this.m_20069_() && this.m_20184_().f_82480_ > 0.0) {
            this.m_20256_(this.m_20184_().m_82542_(1.0, 0.5, 1.0));
        }
        super.m_7023_(vec3d);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setFlying(compound.m_128471_("Flying"));
        this.m_21839_(compound.m_128471_("MonkeySitting"));
        this.setCommand(compound.m_128451_("Command"));
        if (compound.m_128441_("PerchX") && compound.m_128441_("PerchY") && compound.m_128441_("PerchZ")) {
            this.setPerchPos(new BlockPos(compound.m_128451_("PerchX"), compound.m_128451_("PerchY"), compound.m_128451_("PerchZ")));
        }
    }

    @Override
    public boolean isFlying() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    @Override
    public void setFlying(boolean flying) {
        if (flying && this.m_6162_()) {
            return;
        }
        this.f_19804_.m_135381_(FLYING, (Object)flying);
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

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FLYING, (Object)false);
        this.f_19804_.m_135372_(ATTACK_TICK, (Object)0);
        this.f_19804_.m_135372_(COMMAND, (Object)0);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(PERCH_POS, Optional.empty());
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268612_) || source.m_276093_(DamageTypes.f_268671_) || source.m_276093_(DamageTypes.f_268585_) || super.m_6673_(source);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return (AgeableMob)((EntityType)AMEntityRegistry.CROW.get()).m_20615_((Level)serverWorld);
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    public int m_8100_() {
        return 60;
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.CROW_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.CROW_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.CROW_HURT.get();
    }

    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
        BlockPos newPos;
        float radius = -9.450001f - (float)this.m_217043_().m_188503_(24) - radiusAdd;
        float angle = this.getAngle1();
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), 0, (int)(fleePos.m_7094_() + extraZ));
        BlockPos ground = this.getCrowGround(radialPos);
        int distFromGround = (int)this.m_20186_() - ground.m_123342_();
        if (distFromGround > 8) {
            int flightHeight = 4 + this.m_217043_().m_188503_(10);
            newPos = ground.m_6630_(flightHeight);
        } else {
            newPos = ground.m_6630_(this.m_217043_().m_188503_(6) + 1);
        }
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 1.0) {
            return Vec3.m_82512_((Vec3i)newPos);
        }
        return null;
    }

    private BlockPos getCrowGround(BlockPos in) {
        BlockPos position = new BlockPos(in.m_123341_(), (int)this.m_20186_(), in.m_123343_());
        while (position.m_123342_() > -64 && !this.m_9236_().m_8055_(position).m_280296_() && this.m_9236_().m_6425_(position).m_76178_()) {
            position = position.m_7495_();
        }
        return position;
    }

    public Vec3 getBlockGrounding(Vec3 fleePos) {
        float radius = -9.450001f - (float)this.m_217043_().m_188503_(24);
        float angle = this.getAngle1();
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), (int)this.m_20186_(), (int)(fleePos.m_7094_() + extraZ));
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

    private float getAngle1() {
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        return (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
    }

    private boolean isOverWater() {
        BlockPos position = this.m_20183_();
        while (position.m_123342_() > -64 && this.m_9236_().m_46859_(position)) {
            position = position.m_7495_();
        }
        return !this.m_9236_().m_6425_(position).m_76178_();
    }

    @Override
    public void peck() {
        this.f_19804_.m_135381_(ATTACK_TICK, (Object)7);
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return stack != null && this.isCrowEdible(stack) || this.m_21824_();
    }

    private boolean isCrowEdible(ItemStack stack) {
        return stack.m_41720_().m_41472_() || stack.m_204117_(AMTagRegistry.CROW_FOODSTUFFS);
    }

    @Override
    public double getMaxDistToItem() {
        return 1.0;
    }

    @Override
    public void onGetItem(ItemEntity e) {
        ItemStack duplicate = e.m_32055_().m_41777_();
        duplicate.m_41764_(1);
        if (!this.m_21120_(InteractionHand.MAIN_HAND).m_41619_() && !this.m_9236_().f_46443_) {
            this.m_5552_(this.m_21120_(InteractionHand.MAIN_HAND), 0.0f);
        }
        this.m_21008_(InteractionHand.MAIN_HAND, duplicate);
        Entity itemThrower = e.m_19749_();
        this.seedThrowerID = e.m_32055_().m_204117_(AMTagRegistry.CROW_TAMEABLES) && !this.m_21824_() && itemThrower != null ? itemThrower.m_20148_() : null;
    }

    public BlockPos getPerchPos() {
        return ((Optional)this.f_19804_.m_135370_(PERCH_POS)).orElse(null);
    }

    public void setPerchPos(BlockPos pos) {
        this.f_19804_.m_135381_(PERCH_POS, Optional.ofNullable(pos));
    }

    private Vec3 getGatheringVec(Vec3 vector3d, float gatheringCircleDist) {
        if (this.getPerchPos() != null) {
            float angle = 0.13962634f * (float)this.f_19797_;
            double extraX = gatheringCircleDist * Mth.m_14031_((float)angle);
            double extraZ = gatheringCircleDist * Mth.m_14089_((float)angle);
            Vec3 pos = new Vec3((double)this.getPerchPos().m_123341_() + extraX, (double)(this.getPerchPos().m_123342_() + 2), (double)this.getPerchPos().m_123343_() + extraZ);
            if (this.m_9236_().m_46859_(AMBlockPos.fromVec3(pos))) {
                return pos;
            }
        }
        return null;
    }

    private class AIDepositChests
    extends Goal {
        protected final Sorter theNearestAttackableTargetSorter;
        protected final Predicate<ItemFrame> targetEntitySelector;
        protected int executionChance = 8;
        protected boolean mustUpdate;
        private ItemFrame targetEntity;
        private Vec3 flightTarget = null;
        private int cooldown = 0;

        AIDepositChests() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.theNearestAttackableTargetSorter = new Sorter((Entity)EntityCrow.this);
            this.targetEntitySelector = new Predicate<ItemFrame>(){

                public boolean apply(@Nullable ItemFrame e) {
                    LazyOptional handler;
                    BlockPos hangingPosition = e.m_31748_().m_121945_(e.m_6350_().m_122424_());
                    BlockEntity entity = e.m_9236_().m_7702_(hangingPosition);
                    if (entity != null && (handler = entity.getCapability(ForgeCapabilities.ITEM_HANDLER, e.m_6350_().m_122424_())) != null && handler.isPresent()) {
                        return ItemStack.m_41656_((ItemStack)e.m_31822_(), (ItemStack)EntityCrow.this.m_21205_());
                    }
                    return false;
                }
            };
        }

        public boolean m_8036_() {
            List list;
            long worldTime;
            if (EntityCrow.this.m_20159_() || EntityCrow.this.aiItemFlag || EntityCrow.this.m_20160_() || EntityCrow.this.isSitting() || EntityCrow.this.getCommand() != 3) {
                return false;
            }
            if (EntityCrow.this.m_21205_().m_41619_()) {
                return false;
            }
            if (!this.mustUpdate && (worldTime = EntityCrow.this.m_9236_().m_46467_() % 10L) != 0L) {
                if (EntityCrow.this.m_21216_() >= 100) {
                    return false;
                }
                if (EntityCrow.this.m_217043_().m_188503_(this.executionChance) != 0) {
                    return false;
                }
            }
            if ((list = EntityCrow.this.m_9236_().m_6443_(ItemFrame.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector)).isEmpty()) {
                return false;
            }
            list.sort(this.theNearestAttackableTargetSorter);
            this.targetEntity = (ItemFrame)list.get(0);
            this.mustUpdate = false;
            EntityCrow.this.aiItemFrameFlag = true;
            return true;
        }

        public boolean m_8045_() {
            return this.targetEntity != null && EntityCrow.this.getCommand() == 3 && !EntityCrow.this.m_21205_().m_41619_();
        }

        public void m_8041_() {
            this.flightTarget = null;
            this.targetEntity = null;
            EntityCrow.this.aiItemFrameFlag = false;
        }

        public void m_8037_() {
            if (this.cooldown > 0) {
                --this.cooldown;
            }
            if (this.flightTarget != null) {
                EntityCrow.this.setFlying(true);
                if (EntityCrow.this.f_19862_) {
                    EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, EntityCrow.this.m_20186_() + 1.0, this.flightTarget.f_82481_, 1.0);
                } else {
                    EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, this.flightTarget.f_82480_, this.flightTarget.f_82481_, 1.0);
                }
            }
            if (this.targetEntity != null) {
                this.flightTarget = this.targetEntity.m_20182_();
                if (EntityCrow.this.m_20270_((Entity)this.targetEntity) < 2.0f) {
                    try {
                        BlockPos hangingPosition = this.targetEntity.m_31748_().m_121945_(this.targetEntity.m_6350_().m_122424_());
                        BlockEntity entity = this.targetEntity.m_9236_().m_7702_(hangingPosition);
                        Direction deposit = this.targetEntity.m_6350_();
                        LazyOptional handler = entity.getCapability(ForgeCapabilities.ITEM_HANDLER, deposit);
                        if (handler.orElse(null) != null && this.cooldown == 0) {
                            ItemStack duplicate = EntityCrow.this.m_21120_(InteractionHand.MAIN_HAND).m_41777_();
                            ItemStack insertSimulate = ItemHandlerHelper.insertItem((IItemHandler)((IItemHandler)handler.orElse(null)), (ItemStack)duplicate, (boolean)true);
                            if (!insertSimulate.equals(duplicate)) {
                                ItemStack shrunkenStack = ItemHandlerHelper.insertItem((IItemHandler)((IItemHandler)handler.orElse(null)), (ItemStack)duplicate, (boolean)false);
                                if (shrunkenStack.m_41619_()) {
                                    EntityCrow.this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
                                } else {
                                    EntityCrow.this.m_21008_(InteractionHand.MAIN_HAND, shrunkenStack);
                                }
                                EntityCrow.this.peck();
                            } else {
                                this.cooldown = 20;
                            }
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    this.m_8041_();
                }
            }
        }

        protected double getTargetDistance() {
            return 4.0;
        }

        protected AABB getTargetableArea(double targetDistance) {
            Vec3 renderCenter = new Vec3(EntityCrow.this.m_20185_(), EntityCrow.this.m_20186_(), EntityCrow.this.m_20189_());
            AABB aabb = new AABB(-16.0, -16.0, -16.0, 16.0, 16.0, 16.0);
            return aabb.m_82383_(renderCenter);
        }

        public record Sorter(Entity theEntity) implements Comparator<Entity>
        {
            @Override
            public int compare(Entity p_compare_1_, Entity p_compare_2_) {
                double d0 = this.theEntity.m_20280_(p_compare_1_);
                double d1 = this.theEntity.m_20280_(p_compare_2_);
                return Double.compare(d0, d1);
            }
        }
    }

    private class AIScatter
    extends Goal {
        protected final Sorter theNearestAttackableTargetSorter;
        protected final Predicate<? super Entity> targetEntitySelector;
        protected int executionChance = 8;
        protected boolean mustUpdate;
        private Entity targetEntity;
        private Vec3 flightTarget = null;
        private int cooldown = 0;

        AIScatter() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.theNearestAttackableTargetSorter = new Sorter((Entity)EntityCrow.this);
            this.targetEntitySelector = new Predicate<Entity>(){

                public boolean apply(@Nullable Entity e) {
                    return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.SCATTERS_CROWS) || e instanceof Player && !((Player)e).m_7500_();
                }
            };
        }

        public boolean m_8036_() {
            List list;
            long worldTime;
            if (EntityCrow.this.m_20159_() || EntityCrow.this.aiItemFlag || EntityCrow.this.m_20160_() || EntityCrow.this.m_21824_()) {
                return false;
            }
            if (!this.mustUpdate && (worldTime = EntityCrow.this.m_9236_().m_46467_() % 10L) != 0L) {
                if (EntityCrow.this.m_21216_() >= 100) {
                    return false;
                }
                if (EntityCrow.this.m_217043_().m_188503_(this.executionChance) != 0) {
                    return false;
                }
            }
            if ((list = EntityCrow.this.m_9236_().m_6443_(Entity.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector)).isEmpty()) {
                return false;
            }
            list.sort(this.theNearestAttackableTargetSorter);
            this.targetEntity = (Entity)list.get(0);
            this.mustUpdate = false;
            return true;
        }

        public boolean m_8045_() {
            return this.targetEntity != null && !EntityCrow.this.m_21824_();
        }

        public void m_8041_() {
            this.flightTarget = null;
            this.targetEntity = null;
        }

        public void m_8037_() {
            if (this.cooldown > 0) {
                --this.cooldown;
            }
            if (this.flightTarget != null) {
                EntityCrow.this.setFlying(true);
                EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, this.flightTarget.f_82480_, this.flightTarget.f_82481_, 1.0);
                if (this.cooldown == 0 && EntityCrow.this.isTargetBlocked(this.flightTarget)) {
                    this.cooldown = 30;
                    this.flightTarget = null;
                }
            }
            if (this.targetEntity != null) {
                Vec3 vec;
                if ((EntityCrow.this.m_20096_() || this.flightTarget == null || EntityCrow.this.m_20238_(this.flightTarget) < 3.0) && (vec = EntityCrow.this.getBlockInViewAway(this.targetEntity.m_20182_(), 0.0f)) != null && vec.m_7098_() > EntityCrow.this.m_20186_()) {
                    this.flightTarget = vec;
                }
                if (EntityCrow.this.m_20270_(this.targetEntity) > 20.0f) {
                    this.m_8041_();
                }
            }
        }

        protected double getTargetDistance() {
            return 4.0;
        }

        protected AABB getTargetableArea(double targetDistance) {
            Vec3 renderCenter = new Vec3(EntityCrow.this.m_20185_(), EntityCrow.this.m_20186_() + 0.5, EntityCrow.this.m_20189_());
            AABB aabb = new AABB(-2.0, -2.0, -2.0, 2.0, 2.0, 2.0);
            return aabb.m_82383_(renderCenter);
        }

        public record Sorter(Entity theEntity) implements Comparator<Entity>
        {
            @Override
            public int compare(Entity p_compare_1_, Entity p_compare_2_) {
                double d0 = this.theEntity.m_20280_(p_compare_1_);
                double d1 = this.theEntity.m_20280_(p_compare_2_);
                return Double.compare(d0, d1);
            }
        }
    }

    private class AIAvoidPumpkins
    extends Goal {
        private final int searchLength;
        private final int verticalSearchRange;
        protected BlockPos destinationBlock;
        protected int runDelay = 70;
        private Vec3 flightTarget;

        private AIAvoidPumpkins() {
            this.searchLength = 20;
            this.verticalSearchRange = 1;
        }

        public boolean m_8045_() {
            return this.destinationBlock != null && this.isPumpkin(EntityCrow.this.m_9236_(), this.destinationBlock.m_122032_()) && this.isCloseToPumpkin(16.0);
        }

        public boolean isCloseToPumpkin(double dist) {
            return this.destinationBlock == null || EntityCrow.this.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
        }

        public boolean m_8036_() {
            if (EntityCrow.this.m_21824_()) {
                return false;
            }
            if (this.runDelay > 0) {
                --this.runDelay;
                return false;
            }
            this.runDelay = 70 + EntityCrow.this.f_19796_.m_188503_(150);
            return this.searchForDestination();
        }

        public void m_8056_() {
            EntityCrow.this.fleePumpkinFlag = 200;
            Vec3 vec = EntityCrow.this.getBlockInViewAway(Vec3.m_82512_((Vec3i)this.destinationBlock), 10.0f);
            if (vec != null) {
                this.flightTarget = vec;
                EntityCrow.this.setFlying(true);
                EntityCrow.this.m_21566_().m_6849_(vec.f_82479_, vec.f_82480_, vec.f_82481_, 1.0);
            }
        }

        public void m_8037_() {
            if (this.isCloseToPumpkin(16.0)) {
                Vec3 vec;
                EntityCrow.this.fleePumpkinFlag = 200;
                if ((this.flightTarget == null || EntityCrow.this.m_20238_(this.flightTarget) < 2.0) && (vec = EntityCrow.this.getBlockInViewAway(Vec3.m_82512_((Vec3i)this.destinationBlock), 10.0f)) != null) {
                    this.flightTarget = vec;
                    EntityCrow.this.setFlying(true);
                }
                if (this.flightTarget != null) {
                    EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, this.flightTarget.f_82480_, this.flightTarget.f_82481_, 1.0);
                }
            }
        }

        public void m_8041_() {
            this.flightTarget = null;
        }

        protected boolean searchForDestination() {
            int lvt_1_1_ = this.searchLength;
            BlockPos lvt_3_1_ = EntityCrow.this.m_20183_();
            BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
            for (int lvt_5_1_ = -8; lvt_5_1_ <= 2; ++lvt_5_1_) {
                for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                    int lvt_7_1_ = 0;
                    while (lvt_7_1_ <= lvt_6_1_) {
                        int lvt_8_1_;
                        int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                        while (lvt_8_1_ <= lvt_6_1_) {
                            lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                            if (this.isPumpkin(EntityCrow.this.m_9236_(), lvt_4_1_)) {
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

        private boolean isPumpkin(Level world, BlockPos.MutableBlockPos lvt_4_1_) {
            return world.m_8055_((BlockPos)lvt_4_1_).m_204336_(AMTagRegistry.CROW_FEARS);
        }
    }

    private class AIWalkIdle
    extends Goal {
        protected final EntityCrow crow;
        protected double x;
        protected double y;
        protected double z;
        private boolean flightTarget = false;

        public AIWalkIdle() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.crow = EntityCrow.this;
        }

        public boolean m_8036_() {
            Vec3 lvt_1_1_;
            if (this.crow.m_20160_() || EntityCrow.this.getCommand() == 1 || EntityCrow.this.aiItemFlag || this.crow.m_5448_() != null && this.crow.m_5448_().m_6084_() || this.crow.m_20159_() || this.crow.isSitting()) {
                return false;
            }
            if (this.crow.m_217043_().m_188503_(30) != 0 && !this.crow.isFlying()) {
                return false;
            }
            if (this.crow.m_20096_()) {
                this.flightTarget = EntityCrow.this.f_19796_.m_188499_();
            } else {
                boolean bl = this.flightTarget = EntityCrow.this.f_19796_.m_188503_(5) > 0 && this.crow.timeFlying < 200;
            }
            if (this.crow.getCommand() == 3) {
                if (this.crow.aiItemFrameFlag) {
                    return false;
                }
                this.flightTarget = true;
            }
            if ((lvt_1_1_ = this.getPosition()) == null) {
                return false;
            }
            this.x = lvt_1_1_.f_82479_;
            this.y = lvt_1_1_.f_82480_;
            this.z = lvt_1_1_.f_82481_;
            return true;
        }

        public void m_8037_() {
            if (this.flightTarget) {
                this.crow.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.crow.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
                if (EntityCrow.this.isFlying() && this.crow.m_20096_()) {
                    this.crow.setFlying(false);
                }
            }
            if (EntityCrow.this.isFlying() && this.crow.m_20096_() && this.crow.timeFlying > 10) {
                this.crow.setFlying(false);
            }
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vector3d = this.crow.m_20182_();
            if (this.crow.getCommand() == 3 && this.crow.getPerchPos() != null) {
                return this.crow.getGatheringVec(vector3d, 4 + EntityCrow.this.f_19796_.m_188503_(2));
            }
            if (this.crow.isOverWater()) {
                this.flightTarget = true;
            }
            if (this.flightTarget) {
                if (this.crow.timeFlying < 50 || this.crow.isOverWater()) {
                    return this.crow.getBlockInViewAway(vector3d, 0.0f);
                }
                return this.crow.getBlockGrounding(vector3d);
            }
            return LandRandomPos.m_148488_((PathfinderMob)this.crow, (int)10, (int)7);
        }

        public boolean m_8045_() {
            if (this.crow.aiItemFlag || this.crow.isSitting() || EntityCrow.this.getCommand() == 1) {
                return false;
            }
            if (this.flightTarget) {
                return this.crow.isFlying() && this.crow.m_20275_(this.x, this.y, this.z) > 2.0;
            }
            return !this.crow.m_21573_().m_26571_() && !this.crow.m_20160_();
        }

        public void m_8056_() {
            if (this.flightTarget) {
                this.crow.setFlying(true);
                this.crow.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.crow.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
        }

        public void m_8041_() {
            this.crow.m_21573_().m_26573_();
            super.m_8041_();
        }
    }

    private static class AITargetItems
    extends CreatureAITargetItems {
        public AITargetItems(PathfinderMob creature, boolean checkSight, boolean onlyNearby, int tickThreshold, int radius) {
            super(creature, checkSight, onlyNearby, tickThreshold, radius);
            this.executionChance = 1;
        }

        @Override
        public void m_8041_() {
            super.m_8041_();
            ((EntityCrow)this.f_26135_).aiItemFlag = false;
        }

        @Override
        public boolean m_8036_() {
            return super.m_8036_() && !((EntityCrow)this.f_26135_).isSitting() && (this.f_26135_.m_5448_() == null || !this.f_26135_.m_5448_().m_6084_());
        }

        @Override
        public boolean m_8045_() {
            return super.m_8045_() && !((EntityCrow)this.f_26135_).isSitting() && (this.f_26135_.m_5448_() == null || !this.f_26135_.m_5448_().m_6084_());
        }

        @Override
        protected void moveTo() {
            EntityCrow crow = (EntityCrow)this.f_26135_;
            if (this.targetEntity != null) {
                crow.aiItemFlag = true;
                if (this.f_26135_.m_20270_((Entity)this.targetEntity) < 2.0f) {
                    crow.m_21566_().m_6849_(this.targetEntity.m_20185_(), this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                    crow.peck();
                }
                if (this.f_26135_.m_20270_((Entity)this.targetEntity) > 8.0f || crow.isFlying()) {
                    crow.setFlying(true);
                    if (!crow.m_142582_((Entity)this.targetEntity)) {
                        crow.m_21566_().m_6849_(this.targetEntity.m_20185_(), 1.0 + crow.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                    } else {
                        float f2;
                        float f = (float)(crow.m_20185_() - this.targetEntity.m_20185_());
                        float xzDist = Mth.m_14116_((float)(f * f + (f2 = (float)(crow.m_20189_() - this.targetEntity.m_20189_())) * f2));
                        float f1 = xzDist < 5.0f ? 0.0f : 1.8f;
                        crow.m_21566_().m_6849_(this.targetEntity.m_20185_(), (double)f1 + this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                    }
                } else {
                    this.f_26135_.m_21573_().m_26519_(this.targetEntity.m_20185_(), this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                }
            }
        }

        @Override
        public void m_8037_() {
            super.m_8037_();
            this.moveTo();
        }
    }
}
