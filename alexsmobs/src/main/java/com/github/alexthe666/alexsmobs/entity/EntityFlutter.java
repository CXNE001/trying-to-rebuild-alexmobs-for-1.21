/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
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
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.FlyingAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.Tags$Items
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityPollenBall;
import com.github.alexthe666.alexsmobs.entity.IFollower;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.FlyingAIFollowOwner;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.TameableAITempt;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class EntityFlutter
extends TamableAnimal
implements IFollower,
FlyingAnimal {
    private static final EntityDataAccessor<Float> FLUTTER_PITCH = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> POTTED = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> TENTACLING = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> SHOOTING = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> SHAKING_HEAD_TICKS = SynchedEntityData.m_135353_(EntityFlutter.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float prevFlyProgress;
    public float flyProgress;
    public float prevShootProgress;
    public float shootProgress;
    public float prevSitProgress;
    public float sitProgress;
    public float prevFlutterPitch;
    public float tentacleProgress;
    public float prevTentacleProgress;
    public float FlutterRotation;
    private float rotationVelocity;
    private int squishCooldown = 0;
    private float randomMotionSpeed;
    private boolean isLandNavigator;
    private int timeFlying;
    private List<String> flowersEaten = new ArrayList<String>();
    private boolean hasPotStats = false;

    protected EntityFlutter(EntityType type, Level level) {
        super(type, level);
        this.rotationVelocity = 1.0f / (this.f_19796_.m_188501_() + 1.0f) * 0.5f;
        this.switchNavigator(false);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 8.0).m_22268_(Attributes.f_22280_, (double)0.8f).m_22268_(Attributes.f_22281_, 1.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22279_, (double)0.21f);
    }

    public boolean m_6785_(double distanceToClosestPlayer) {
        return !this.m_8023_() && !this.m_8077_();
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_8077_() || this.m_21824_() || this.isPotted();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.flutterSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canFlutterSpawnInLight(EntityType<? extends EntityFlutter> p_223325_0_, ServerLevelAccessor p_223325_1_, MobSpawnType p_223325_2_, BlockPos p_223325_3_, RandomSource p_223325_4_) {
        return EntityFlutter.m_217057_(p_223325_0_, (LevelAccessor)p_223325_1_, (MobSpawnType)p_223325_2_, (BlockPos)p_223325_3_, (RandomSource)p_223325_4_);
    }

    public static <T extends Mob> boolean canFlutterSpawn(EntityType<EntityFlutter> entityType, ServerLevelAccessor iServerWorld, MobSpawnType reason, BlockPos pos, RandomSource random) {
        BlockState blockstate = iServerWorld.m_8055_(pos.m_7495_());
        return reason == MobSpawnType.SPAWNER || !iServerWorld.m_45527_(pos) && blockstate.m_204336_(AMTagRegistry.FLUTTER_SPAWNS) && pos.m_123342_() <= 64 && EntityFlutter.canFlutterSpawnInLight(entityType, iServerWorld, reason, pos, random);
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new FlyAwayFromTarget(this));
        this.f_21345_.m_25352_(2, (Goal)new TameableAITempt((Animal)this, 1.1, Ingredient.m_204132_(AMTagRegistry.FLUTTER_BREEDABLES), false){

            @Override
            public boolean shouldFollowAM(LivingEntity le) {
                return EntityFlutter.this.canEatFlower(le.m_21205_()) || EntityFlutter.this.canEatFlower(le.m_21206_()) || super.shouldFollowAM(le);
            }
        });
        this.f_21345_.m_25352_(3, (Goal)new FlyingAIFollowOwner(this, 1.3, 7.0f, 2.0f, false));
        this.f_21345_.m_25352_(4, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(5, (Goal)new AIWalkIdle());
        this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 15.0f));
        this.f_21345_.m_25352_(7, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(2, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigatorWide((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new FlightMoveController((Mob)this, 1.0f, false, true);
            this.f_21344_ = new DirectPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FLUTTER_PITCH, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(FLYING, (Object)false);
        this.f_19804_.m_135372_(POTTED, (Object)false);
        this.f_19804_.m_135372_(COMMAND, (Object)0);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(TENTACLING, (Object)false);
        this.f_19804_.m_135372_(SHOOTING, (Object)false);
        this.f_19804_.m_135372_(SHAKING_HEAD_TICKS, (Object)0);
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

    public boolean m_29443_() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    public void setFlying(boolean flying) {
        this.f_19804_.m_135381_(FLYING, (Object)flying);
    }

    public boolean isPotted() {
        return (Boolean)this.f_19804_.m_135370_(POTTED);
    }

    public void setPotted(boolean potted) {
        this.f_19804_.m_135381_(POTTED, (Object)potted);
    }

    public float getFlutterPitch() {
        return Mth.m_14036_((float)((Float)this.f_19804_.m_135370_(FLUTTER_PITCH)).floatValue(), (float)-90.0f, (float)90.0f);
    }

    public void setFlutterPitch(float pitch) {
        this.f_19804_.m_135381_(FLUTTER_PITCH, (Object)Float.valueOf(pitch));
    }

    public void incrementFlutterPitch(float pitch) {
        this.f_19804_.m_135381_(FLUTTER_PITCH, (Object)Float.valueOf(this.getFlutterPitch() + pitch));
    }

    public void decrementFlutterPitch(float pitch) {
        this.f_19804_.m_135381_(FLUTTER_PITCH, (Object)Float.valueOf(this.getFlutterPitch() - pitch));
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.FLUTTER_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.FLUTTER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.FLUTTER_HURT.get();
    }

    public void m_8119_() {
        boolean shooting;
        float decrease;
        super.m_8119_();
        this.prevShootProgress = this.shootProgress;
        this.prevFlyProgress = this.flyProgress;
        this.prevFlutterPitch = this.getFlutterPitch();
        this.prevSitProgress = this.sitProgress;
        float extraMotionSlow = 1.0f;
        float extraMotionSlowY = 1.0f;
        this.f_20883_ = this.m_146908_();
        this.f_20885_ = this.m_146908_();
        this.prevFlutterPitch = this.getFlutterPitch();
        this.prevTentacleProgress = this.tentacleProgress;
        if (this.m_29443_()) {
            if (this.flyProgress < 5.0f) {
                this.flyProgress += 1.0f;
            }
        } else if (this.flyProgress > 0.0f) {
            this.flyProgress -= 1.0f;
        }
        if (this.isSitting()) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (this.tentacleProgress < 5.0f && ((Boolean)this.f_19804_.m_135370_(TENTACLING)).booleanValue()) {
            this.tentacleProgress += 1.0f;
        }
        if (this.tentacleProgress == 5.0f && !((Boolean)this.f_19804_.m_135370_(TENTACLING)).booleanValue() && this.squishCooldown == 0 && this.m_29443_()) {
            this.squishCooldown = 10;
            this.m_5496_((SoundEvent)AMSoundRegistry.FLUTTER_FLAP.get(), this.m_6121_(), 1.5f * this.m_6100_());
        }
        if (this.tentacleProgress > 0.0f && !((Boolean)this.f_19804_.m_135370_(TENTACLING)).booleanValue()) {
            this.tentacleProgress -= 1.0f;
        }
        this.FlutterRotation += this.rotationVelocity;
        if ((double)this.FlutterRotation > 6.2831854820251465) {
            if (this.m_9236_().f_46443_) {
                this.FlutterRotation = (float)Math.PI * 2;
            } else {
                this.FlutterRotation = (float)((double)this.FlutterRotation - 6.2831854820251465);
                if (this.f_19796_.m_188503_(10) == 0) {
                    this.rotationVelocity = 1.0f / (this.f_19796_.m_188501_() + 1.0f) * 0.5f;
                }
                this.m_9236_().m_7605_((Entity)this, (byte)19);
            }
        }
        if (this.FlutterRotation < (float)Math.PI) {
            float f = this.FlutterRotation / (float)Math.PI;
            if ((double)f >= (double)0.95f) {
                this.f_19804_.m_135381_(TENTACLING, (Object)true);
                if (this.squishCooldown == 0 && this.m_29443_()) {
                    this.squishCooldown = 10;
                    this.m_146850_(GameEvent.f_223709_);
                    this.m_5496_((SoundEvent)AMSoundRegistry.FLUTTER_FLAP.get(), 3.0f, 1.5f * this.m_6100_());
                }
                this.randomMotionSpeed = 0.8f;
            } else {
                this.f_19804_.m_135381_(TENTACLING, (Object)false);
                this.randomMotionSpeed = 0.01f;
            }
        }
        if (!this.m_9236_().f_46443_) {
            if (this.m_29443_() && this.isLandNavigator) {
                this.switchNavigator(false);
            }
            if (!this.m_29443_() && !this.isLandNavigator) {
                this.switchNavigator(true);
            }
            if (this.m_29443_()) {
                this.m_20334_(this.m_20184_().f_82479_ * (double)this.randomMotionSpeed * (double)extraMotionSlow, this.m_20184_().f_82480_ * (double)this.randomMotionSpeed * (double)extraMotionSlowY, this.m_20184_().f_82481_ * (double)this.randomMotionSpeed * (double)extraMotionSlow);
                ++this.timeFlying;
                if (this.m_20096_() && this.timeFlying > 20 || this.isSitting()) {
                    this.setFlying(false);
                }
            } else {
                this.timeFlying = 0;
            }
        }
        if (!this.m_20096_() && this.m_20184_().f_82480_ < 0.0) {
            this.m_20256_(this.m_20184_().m_82542_(1.0, 0.8, 1.0));
        }
        if (this.m_29443_()) {
            float dist = (float)((Math.abs(this.m_20184_().m_7096_()) + Math.abs(this.m_20184_().m_7094_())) * 30.0);
            this.incrementFlutterPitch(-dist);
            if (this.f_19862_) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, (double)0.2f, 0.0));
            }
        }
        if (this.getFlutterPitch() > 0.0f) {
            decrease = Math.min(2.5f, this.getFlutterPitch());
            this.decrementFlutterPitch(decrease);
        }
        if (this.getFlutterPitch() < 0.0f) {
            decrease = Math.min(2.5f, -this.getFlutterPitch());
            this.incrementFlutterPitch(decrease);
        }
        if ((shooting = ((Boolean)this.f_19804_.m_135370_(SHOOTING)).booleanValue()) && this.shootProgress < 5.0f) {
            this.shootProgress += 1.0f;
        }
        if (!shooting && this.shootProgress > 0.0f) {
            this.shootProgress -= 1.0f;
        }
        if (shooting) {
            this.incrementFlutterPitch(-30.0f);
        }
        if (!this.m_9236_().f_46443_ && shooting && this.shootProgress == 5.0f) {
            if (this.m_5448_() != null) {
                this.spit(this.m_5448_());
            }
            this.f_19804_.m_135381_(SHOOTING, (Object)false);
        }
        if (this.hasPotStats && !this.isPotted()) {
            this.hasPotStats = false;
            this.m_21051_(Attributes.f_22284_).m_22100_(0.21);
            this.m_21051_(Attributes.f_22279_).m_22100_(0.21);
        }
        if (!this.hasPotStats && this.isPotted()) {
            this.hasPotStats = true;
            this.m_21051_(Attributes.f_22284_).m_22100_(16.0);
            this.m_21051_(Attributes.f_22279_).m_22100_(0.18);
        }
        if ((Integer)this.f_19804_.m_135370_(SHAKING_HEAD_TICKS) > 0) {
            this.f_19804_.m_135381_(SHAKING_HEAD_TICKS, (Object)((Integer)this.f_19804_.m_135370_(SHAKING_HEAD_TICKS) - 1));
        }
        if (this.squishCooldown > 0) {
            --this.squishCooldown;
        }
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.FLUTTER_BREEDABLES) && this.m_21824_();
    }

    private void spit(LivingEntity target) {
        EntityPollenBall llamaspitentity = new EntityPollenBall(this.m_9236_(), this);
        double d0 = target.m_20185_() - this.m_20185_();
        double d1 = target.m_20227_(0.3333333333333333) - llamaspitentity.m_20186_();
        double d2 = target.m_20189_() - this.m_20189_();
        float f = Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2))) * 0.2f;
        llamaspitentity.shoot(d0, d1 + (double)f, d2, 0.5f, 13.0f);
        if (!this.m_20067_()) {
            this.m_146850_(GameEvent.f_157778_);
            this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12098_, this.m_5720_(), 1.0f, 1.0f + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f);
        }
        this.m_9236_().m_7967_((Entity)llamaspitentity);
    }

    public boolean isShakingHead() {
        return (Integer)this.f_19804_.m_135370_(SHAKING_HEAD_TICKS) > 0;
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        Item item = itemstack.m_41720_();
        InteractionResult type = super.m_6071_(player, hand);
        if (!this.m_21824_() && this.canEatFlower(itemstack)) {
            this.m_142075_(player, hand, itemstack);
            this.flowersEaten.add(ForgeRegistries.ITEMS.getKey((Object)itemstack.m_41720_()).toString());
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_((SoundEvent)AMSoundRegistry.FLUTTER_YES.get(), this.m_6121_(), this.m_6100_());
            if (this.flowersEaten.size() > 3 && this.m_217043_().m_188503_(3) == 0 || this.flowersEaten.size() > 6) {
                this.m_21828_(player);
                this.m_9236_().m_7605_((Entity)this, (byte)7);
            } else {
                this.m_9236_().m_7605_((Entity)this, (byte)6);
            }
            return InteractionResult.SUCCESS;
        }
        if (!this.m_21824_() && itemstack.m_204117_(ItemTags.f_13149_)) {
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_((SoundEvent)AMSoundRegistry.FLUTTER_NO.get(), this.m_6121_(), this.m_6100_());
            this.f_19804_.m_135381_(SHAKING_HEAD_TICKS, (Object)20);
        }
        if (this.m_21824_() && itemstack.m_204117_(ItemTags.f_13149_) && this.m_21223_() < this.m_21233_()) {
            this.m_142075_(player, hand, itemstack);
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_11788_, this.m_6121_(), this.m_6100_());
            this.m_5634_(5.0f);
            return InteractionResult.SUCCESS;
        }
        InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
        if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS && this.m_21824_() && this.m_21830_((LivingEntity)player) && !this.m_6898_(itemstack) && !itemstack.m_204117_(ItemTags.f_13149_)) {
            boolean sit;
            if (item == Items.f_42618_ && !this.isPotted()) {
                this.setPotted(true);
                return InteractionResult.SUCCESS;
            }
            if (itemstack.m_204117_(Tags.Items.SHEARS) && this.isPotted()) {
                this.setPotted(false);
                this.m_19998_((ItemLike)Items.f_42618_);
                return InteractionResult.SUCCESS;
            }
            if (this.isPotted() && player.m_6144_()) {
                ItemStack fish = this.getFishBucket();
                if (!player.m_36356_(fish)) {
                    player.m_36176_(fish, false);
                }
                this.m_142687_(Entity.RemovalReason.DISCARDED);
                return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
            }
            this.setCommand(this.getCommand() + 1);
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
        return type;
    }

    @Override
    public void followEntity(TamableAnimal tameable, LivingEntity owner, double followSpeed) {
        if (this.m_20270_((Entity)owner) > 8.0f) {
            this.setFlying(true);
            this.m_21573_().m_26519_(owner.m_20185_(), owner.m_20186_() + (double)owner.m_20206_(), owner.m_20189_(), followSpeed);
        } else if (this.m_29443_() && !this.isOverWaterOrVoid()) {
            BlockPos vec = this.getFlutterGround(this.m_20183_());
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

    @Override
    public boolean shouldFollow() {
        return this.getCommand() == 1;
    }

    protected void m_5907_() {
        super.m_5907_();
        if (this.isPotted() && !this.m_9236_().f_46443_) {
            this.m_19998_((ItemLike)Items.f_42618_);
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

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Flying", this.m_29443_());
        compound.m_128379_("Potted", this.isPotted());
        compound.m_128405_("FlowersEaten", this.flowersEaten.size());
        for (int i = 0; i < this.flowersEaten.size(); ++i) {
            compound.m_128359_("FlowerEaten" + i, this.flowersEaten.get(i));
        }
        compound.m_128405_("FlutterCommand", this.getCommand());
        compound.m_128379_("FlutterSitting", this.isSitting());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setFlying(compound.m_128471_("Flying"));
        this.setPotted(compound.m_128471_("Potted"));
        int flowerCount = compound.m_128451_("FlowersEaten");
        this.flowersEaten = new ArrayList<String>();
        for (int i = 0; i < flowerCount; ++i) {
            String s = compound.m_128461_("FlowerEaten" + i);
            if (s == null) continue;
            this.flowersEaten.add(s);
        }
        this.setCommand(compound.m_128451_("FlutterCommand"));
        this.m_21839_(compound.m_128471_("FlutterSitting"));
    }

    private boolean isOverWaterOrVoid() {
        BlockPos position = this.m_20183_();
        while (position.m_123342_() > -63 && !this.m_9236_().m_8055_(position).m_280296_()) {
            position = position.m_7495_();
        }
        return !this.m_9236_().m_6425_(position).m_76178_() || position.m_123342_() < -63;
    }

    private BlockPos getFlutterGround(BlockPos in) {
        BlockPos position = new BlockPos(in.m_123341_(), (int)this.m_20186_(), in.m_123343_());
        while (position.m_123342_() > -63 && !this.m_9236_().m_8055_(position).m_280296_()) {
            position = position.m_7495_();
        }
        if (position.m_123342_() < -62) {
            return position.m_6630_(120 + this.f_19796_.m_188503_(5));
        }
        return position;
    }

    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
        float radius = (float)(1 + this.m_217043_().m_188503_(3)) + radiusAdd;
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + this.m_217043_().m_188501_() * neg * 0.2f;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(fleePos.m_7096_() + extraX), 0, (int)(fleePos.m_7094_() + extraZ));
        BlockPos ground = this.getFlutterGround(radialPos);
        int distFromGround = (int)this.m_20186_() - ground.m_123342_();
        int flightHeight = 3 + this.m_217043_().m_188503_(2);
        BlockPos newPos = ground.m_6630_(distFromGround > 4 ? flightHeight : distFromGround - 2 + this.m_217043_().m_188503_(4));
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 1.0) {
            return Vec3.m_82512_((Vec3i)newPos);
        }
        return null;
    }

    public Vec3 getBlockGrounding(Vec3 fleePos) {
        float radius = -9.45f - (float)this.m_217043_().m_188503_(24);
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(fleePos.m_7096_() + extraX, this.m_20186_(), fleePos.m_7094_() + extraZ);
        BlockPos ground = this.getFlutterGround(radialPos);
        if (ground.m_123342_() <= -63) {
            return Vec3.m_82514_((Vec3i)ground, (double)(110 + this.f_19796_.m_188503_(20)));
        }
        ground = this.m_20183_();
        while (ground.m_123342_() > -63 && !this.m_9236_().m_8055_(ground).m_280296_()) {
            ground = ground.m_7495_();
        }
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)ground.m_7494_()))) {
            return Vec3.m_82512_((Vec3i)ground.m_7495_());
        }
        return null;
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    protected ItemStack getFishBucket() {
        ItemStack stack = new ItemStack((ItemLike)AMItemRegistry.POTTED_FLUTTER.get());
        CompoundTag platTag = new CompoundTag();
        this.m_7380_(platTag);
        stack.m_41784_().m_128365_("FlutterData", (Tag)platTag);
        if (this.m_8077_()) {
            stack.m_41714_(this.m_7770_());
        }
        return stack;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel level, AgeableMob mobo) {
        EntityFlutter baby = (EntityFlutter)((EntityType)AMEntityRegistry.FLUTTER.get()).m_20615_(this.m_9236_());
        baby.m_21530_();
        return baby;
    }

    public boolean hasEatenFlower(ItemStack stack) {
        return this.flowersEaten != null && this.flowersEaten.contains(ForgeRegistries.ITEMS.getKey((Object)stack.m_41720_()).toString());
    }

    public boolean canEatFlower(ItemStack stack) {
        return !this.hasEatenFlower(stack) && stack.m_204117_(ItemTags.f_13149_);
    }

    private void setupShooting() {
        this.f_19804_.m_135381_(SHOOTING, (Object)true);
    }

    public void m_27563_(ServerLevel world, Animal partner) {
        super.m_27563_(world, partner);
        for (int i = 0; i < 15 + this.f_19796_.m_188503_(10); ++i) {
            BlockPos nearby = this.m_20183_().m_7918_(this.f_19796_.m_188503_(16) - 8, this.f_19796_.m_188503_(2), this.f_19796_.m_188503_(16) - 8);
            if (world.m_8055_(nearby).m_60734_() == Blocks.f_152541_) {
                world.m_46597_(nearby, Blocks.f_152542_.m_49966_());
                world.m_46796_(1505, nearby, 0);
            }
            if (world.m_8055_(nearby).m_60734_() != Blocks.f_152470_) continue;
            world.m_46597_(nearby, Blocks.f_152471_.m_49966_());
            world.m_46796_(1505, nearby, 0);
        }
    }

    private class FlyAwayFromTarget
    extends Goal {
        private final EntityFlutter parentEntity;
        private int spitCooldown = 0;
        private BlockPos shootPos = null;

        public FlyAwayFromTarget(EntityFlutter entityFlutter2) {
            this.parentEntity = entityFlutter2;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            return !this.parentEntity.isSitting() && this.parentEntity.m_5448_() != null && this.parentEntity.m_5448_().m_6084_() && !this.parentEntity.m_6162_();
        }

        public void m_8037_() {
            if (this.spitCooldown > 0) {
                --this.spitCooldown;
            }
            if (this.parentEntity.m_5448_() != null) {
                this.parentEntity.setFlying(true);
                if (this.shootPos == null || this.parentEntity.m_20270_((Entity)this.parentEntity.m_5448_()) >= 10.0f || this.parentEntity.m_5448_().m_20275_((double)((float)this.shootPos.m_123341_() + 0.5f), (double)this.shootPos.m_123342_(), (double)((float)this.shootPos.m_123343_() + 0.5f)) < 4.0) {
                    this.shootPos = this.getShootFromPos(this.parentEntity.m_5448_());
                }
                if (this.shootPos != null) {
                    this.parentEntity.m_21566_().m_6849_((double)this.shootPos.m_123341_() + 0.5, (double)this.shootPos.m_123342_() + 0.5, (double)this.shootPos.m_123343_() + 0.5, 1.5);
                }
                if (this.parentEntity.m_20270_((Entity)this.parentEntity.m_5448_()) < 25.0f) {
                    this.parentEntity.m_21391_((Entity)this.parentEntity.m_5448_(), 30.0f, 30.0f);
                    if (this.spitCooldown == 0) {
                        this.parentEntity.setupShooting();
                        this.spitCooldown = 10 + EntityFlutter.this.f_19796_.m_188503_(10);
                    }
                    this.shootPos = null;
                }
            }
        }

        public BlockPos getShootFromPos(LivingEntity target) {
            float radius = 3 + this.parentEntity.m_217043_().m_188503_(5);
            float angle = (float)Math.PI / 180 * (target.f_20885_ + 90.0f + (float)this.parentEntity.m_217043_().m_188503_(180));
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            BlockPos radialPos = AMBlockPos.fromCoords(target.m_20185_() + extraX, target.m_20186_() + 2.0, target.m_20189_() + extraZ);
            if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)radialPos))) {
                return radialPos;
            }
            return this.parentEntity.m_20183_().m_6630_((int)Math.ceil(target.m_20206_() + 1.0f));
        }
    }

    private class AIWalkIdle
    extends Goal {
        protected final EntityFlutter phage;
        protected double x;
        protected double y;
        protected double z;
        private boolean flightTarget = false;

        public AIWalkIdle() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.phage = EntityFlutter.this;
        }

        public boolean m_8036_() {
            if (this.phage.m_20160_() || this.phage.isSitting() || this.phage.shouldFollow() || this.phage.m_5448_() != null && this.phage.m_5448_().m_6084_() || this.phage.m_20159_()) {
                return false;
            }
            if (this.phage.m_217043_().m_188503_(30) != 0 && !this.phage.m_29443_() && !this.phage.m_20072_()) {
                return false;
            }
            this.flightTarget = this.phage.m_20096_() && !this.phage.m_20072_() ? EntityFlutter.this.f_19796_.m_188503_(4) == 0 && !this.phage.m_6162_() : EntityFlutter.this.f_19796_.m_188503_(5) > 0 && this.phage.timeFlying < 100 && !this.phage.m_6162_();
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
            if (this.flightTarget) {
                this.phage.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.phage.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
            if (!this.flightTarget && EntityFlutter.this.m_29443_() && this.phage.m_20096_()) {
                this.phage.setFlying(false);
            }
            if (EntityFlutter.this.m_29443_() && this.phage.m_20096_() && this.phage.timeFlying > 40) {
                this.phage.setFlying(false);
            }
        }

        @javax.annotation.Nullable
        protected Vec3 getPosition() {
            Vec3 vector3d = this.phage.m_20182_();
            if (this.phage.isOverWaterOrVoid()) {
                this.flightTarget = true;
            }
            if (this.flightTarget) {
                if (this.phage.timeFlying < 180 || this.phage.isOverWaterOrVoid()) {
                    return this.phage.getBlockInViewAway(vector3d, 0.0f);
                }
                return this.phage.getBlockGrounding(vector3d);
            }
            return LandRandomPos.m_148488_((PathfinderMob)this.phage, (int)5, (int)5);
        }

        public boolean m_8045_() {
            if (this.phage.isSitting()) {
                return false;
            }
            if (this.flightTarget) {
                return this.phage.m_29443_() && this.phage.m_20275_(this.x, this.y, this.z) > 2.0 && !this.phage.m_6162_();
            }
            return !this.phage.m_21573_().m_26571_() && !this.phage.m_20160_();
        }

        public void m_8056_() {
            if (this.flightTarget) {
                this.phage.setFlying(true);
                this.phage.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
            } else {
                this.phage.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            }
        }

        public void m_8041_() {
            this.phage.m_21573_().m_26573_();
            super.m_8041_();
        }
    }
}
