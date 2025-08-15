/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.storage.loot.BuiltInLootTables
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityAnacondaPart;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFindWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeaveWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIPanicBaby;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalSwimMoveControllerSink;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticAIRandomSwimming;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticPathNavigator;
import com.github.alexthe666.alexsmobs.entity.util.AnacondaPartIndex;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.phys.Vec3;

public class EntityAnaconda
extends Animal
implements ISemiAquatic {
    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID = SynchedEntityData.m_135353_(EntityAnaconda.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Integer> CHILD_ID = SynchedEntityData.m_135353_(EntityAnaconda.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> STRANGLING = SynchedEntityData.m_135353_(EntityAnaconda.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> YELLOW = SynchedEntityData.m_135353_(EntityAnaconda.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> SHEDTIME = SynchedEntityData.m_135353_(EntityAnaconda.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public final float[] ringBuffer = new float[64];
    public int ringBufferIndex = -1;
    private EntityAnacondaPart[] parts;
    private float prevStrangleProgress = 0.0f;
    private float strangleProgress = 0.0f;
    private int strangleTimer = 0;
    private int shedCooldown = 0;
    private int feedings = 0;
    private boolean isLandNavigator;
    private int swimTimer = -1000;
    private int passiveFor = 0;

    protected EntityAnaconda(EntityType t, Level world) {
        super(t, world);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
        this.switchNavigator(true);
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.ANACONDA_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.ANACONDA_HURT.get();
    }

    protected void m_7355_(BlockPos pos, BlockState state) {
        if (!this.m_6162_()) {
            this.m_5496_((SoundEvent)AMSoundRegistry.ANACONDA_SLITHER.get(), 1.0f, 1.0f);
        } else {
            super.m_7355_(pos, state);
        }
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 40.0).m_22268_(Attributes.f_22279_, (double)0.15f);
    }

    public static boolean canAnacondaSpawn(EntityType type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn) {
        boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.ANACONDA_SPAWNS);
        return spawnBlock && pos.m_123342_() < worldIn.m_5736_() + 4;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.anacondaSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigatorWide((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new AnimalSwimMoveControllerSink((PathfinderMob)this, 1.3f, 1.0f);
            this.f_21344_ = new SemiAquaticPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new AnimalAIPanicBaby(this, 1.25));
        this.f_21345_.m_25352_(2, (Goal)new AIMelee());
        this.f_21345_.m_25352_(3, (Goal)new AnimalAIFindWater((PathfinderMob)this));
        this.f_21345_.m_25352_(3, (Goal)new AnimalAILeaveWater((PathfinderMob)this));
        this.f_21345_.m_25352_(4, (Goal)new TemptGoal((PathfinderMob)this, 1.25, Ingredient.m_204132_(AMTagRegistry.ANACONDA_FOODSTUFFS), false));
        this.f_21345_.m_25352_(5, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(6, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(7, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 60, 1.0, 14, 7));
        this.f_21345_.m_25352_(8, (Goal)new SemiAquaticAIRandomSwimming(this, 1.5, 7));
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 25.0f));
        this.f_21345_.m_25352_(9, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new NearestAttackableTargetGoal((Mob)this, LivingEntity.class, 200, false, false, AMEntityRegistry.buildPredicateFromTag(AMTagRegistry.ANACONDA_TARGETS)));
        this.f_21346_.m_25352_(2, (Goal)new EntityAINearestTarget3D((Mob)this, Player.class, 110, false, true, null){

            public boolean m_8036_() {
                return !EntityAnaconda.this.m_6162_() && EntityAnaconda.this.passiveFor == 0 && EntityAnaconda.this.m_9236_().m_46791_() != Difficulty.PEACEFUL && !EntityAnaconda.this.m_27593_() && super.m_8036_();
            }
        });
        this.f_21346_.m_25352_(3, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
    }

    protected float m_6431_(Pose p_33799_, EntityDimensions p_33800_) {
        return this.m_6162_() ? 0.15f : 0.3f;
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        if (this.m_6898_(itemstack)) {
            this.m_6710_(null);
            this.passiveFor = 3600 + this.f_19796_.m_188503_(3600);
        }
        return super.m_6071_(player, hand);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("ChildUUID")) {
            this.setChildId(compound.m_128342_("ChildUUID"));
        }
        this.feedings = compound.m_128451_("Feedings");
        this.setSheddingTime(compound.m_128451_("ShedTime"));
        this.setYellow(compound.m_128471_("Yellow"));
        this.shedCooldown = compound.m_128451_("ShedCooldown");
        this.passiveFor = compound.m_128451_("PassiveFor");
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getChildId() != null) {
            compound.m_128362_("ChildUUID", this.getChildId());
        }
        compound.m_128405_("Feedings", this.feedings);
        compound.m_128405_("ShedTime", this.getSheddingTime());
        compound.m_128379_("Yellow", this.isYellow());
        compound.m_128405_("ShedCooldown", this.shedCooldown);
        compound.m_128405_("PassiveFor", this.passiveFor);
    }

    public void m_6138_() {
        List entities = this.m_9236_().m_45933_((Entity)this, this.m_20191_().m_82363_(0.2, 0.0, 0.2));
        entities.stream().filter(entity -> !(entity instanceof EntityAnacondaPart) && entity.m_6094_()).forEach(entity -> entity.m_7334_((Entity)this));
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CHILD_UUID, Optional.empty());
        this.f_19804_.m_135372_(CHILD_ID, (Object)-1);
        this.f_19804_.m_135372_(STRANGLING, (Object)false);
        this.f_19804_.m_135372_(YELLOW, (Object)false);
        this.f_19804_.m_135372_(SHEDTIME, (Object)0);
    }

    @Nullable
    public UUID getChildId() {
        return ((Optional)this.f_19804_.m_135370_(CHILD_UUID)).orElse(null);
    }

    public void setChildId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(CHILD_UUID, Optional.ofNullable(uniqueId));
    }

    public int getSheddingTime() {
        return (Integer)this.f_19804_.m_135370_(SHEDTIME);
    }

    public void setSheddingTime(int shedtime) {
        this.f_19804_.m_135381_(SHEDTIME, (Object)shedtime);
    }

    public boolean isStrangling() {
        return (Boolean)this.f_19804_.m_135370_(STRANGLING);
    }

    public void setStrangling(boolean running) {
        this.f_19804_.m_135381_(STRANGLING, (Object)running);
    }

    public boolean isYellow() {
        return (Boolean)this.f_19804_.m_135370_(YELLOW);
    }

    public void setYellow(boolean yellow) {
        this.f_19804_.m_135381_(YELLOW, (Object)yellow);
    }

    public int m_8132_() {
        return 1;
    }

    public int m_8085_() {
        return 3;
    }

    public Entity getChild() {
        UUID id = this.getChildId();
        if (id != null && !this.m_9236_().f_46443_) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return null;
    }

    public boolean m_6040_() {
        return true;
    }

    public boolean m_6063_() {
        return false;
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    public void m_8119_() {
        super.m_8119_();
        if (this.passiveFor > 0) {
            --this.passiveFor;
        }
        if (this.m_20069_()) {
            if (this.isLandNavigator) {
                this.switchNavigator(false);
            }
        } else if (!this.isLandNavigator) {
            this.switchNavigator(true);
        }
        this.prevStrangleProgress = this.strangleProgress;
        if (this.isStrangling()) {
            if (this.strangleProgress < 5.0f) {
                this.strangleProgress += 1.0f;
            }
        } else if (this.strangleProgress > 0.0f) {
            this.strangleProgress -= 1.0f;
        }
        this.f_20883_ = this.m_146908_();
        this.f_20885_ = Mth.m_14036_((float)this.f_20885_, (float)(this.f_20883_ - 70.0f), (float)(this.f_20883_ + 70.0f));
        if (this.isStrangling()) {
            if (!this.m_9236_().f_46443_ && this.m_5448_() != null && this.m_5448_().m_6084_()) {
                this.m_146926_(0.0f);
                LivingEntity target = this.m_5448_();
                float radius = this.m_5448_().m_20205_() * -0.5f;
                float angle = (float)Math.PI / 180 * (target.f_20883_ - 45.0f);
                double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
                double extraZ = radius * Mth.m_14089_((float)angle);
                Vec3 targetVec = new Vec3(extraX + target.m_20185_(), target.m_20227_(1.0), extraZ + target.m_20189_());
                Vec3 moveVec = targetVec.m_82546_(this.m_20182_()).m_82490_(1.0);
                this.m_20256_(moveVec);
                if (!target.m_20096_()) {
                    target.m_20256_(new Vec3(0.0, (double)-0.08f, 0.0));
                } else {
                    target.m_20256_(Vec3.f_82478_);
                }
                if (this.strangleTimer >= 40 && this.strangleTimer % 20 == 0) {
                    double health = Mth.m_14036_((float)this.m_5448_().m_21233_(), (float)4.0f, (float)50.0f);
                    this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), (float)Math.max(4.0, 0.25 * health));
                }
                if (this.m_5448_() == null || !this.m_5448_().m_6084_()) {
                    this.strangleTimer = 0;
                    this.setStrangling(false);
                }
            }
            this.f_19789_ = 0.0f;
            ++this.strangleTimer;
            this.m_20242_(true);
        } else {
            this.m_20242_(false);
        }
        if (this.ringBufferIndex < 0) {
            for (int i = 0; i < this.ringBuffer.length; ++i) {
                this.ringBuffer[i] = this.m_146908_();
            }
        }
        ++this.ringBufferIndex;
        if (this.ringBufferIndex == this.ringBuffer.length) {
            this.ringBufferIndex = 0;
        }
        this.ringBuffer[this.ringBufferIndex] = this.m_146908_();
        if (!this.m_9236_().f_46443_) {
            float prevReqRot;
            int segments = 7;
            Entity child = this.getChild();
            if (child == null) {
                Object partParent = this;
                this.parts = new EntityAnacondaPart[7];
                AnacondaPartIndex partIndex = AnacondaPartIndex.HEAD;
                Vec3 prevPos = this.m_20182_();
                for (int i = 0; i < 7; ++i) {
                    prevReqRot = this.calcPartRotation(i) + this.getYawForPart(i);
                    float reqRot = this.calcPartRotation(i + 1) + this.getYawForPart(i);
                    EntityAnacondaPart part = new EntityAnacondaPart((EntityType)AMEntityRegistry.ANACONDA_PART.get(), (LivingEntity)this);
                    part.setParent((Entity)partParent);
                    part.copyDataFrom(this);
                    part.setBodyIndex(i);
                    part.setPartType(AnacondaPartIndex.sizeAt(1 + i));
                    if (partParent == this) {
                        this.setChildId(part.m_20148_());
                        this.f_19804_.m_135381_(CHILD_ID, (Object)part.m_19879_());
                    }
                    if (partParent instanceof EntityAnacondaPart) {
                        ((EntityAnacondaPart)partParent).setChildId(part.m_20148_());
                    }
                    part.m_146884_(part.tickMultipartPosition(this.m_19879_(), partIndex, prevPos, this.m_146909_(), prevReqRot, reqRot, false));
                    partParent = part;
                    this.m_9236_().m_7967_((Entity)part);
                    this.parts[i] = part;
                    partIndex = part.getPartType();
                    prevPos = part.m_20182_();
                }
            }
            if (this.shouldReplaceParts() && this.getChild() instanceof EntityAnacondaPart) {
                this.parts = new EntityAnacondaPart[7];
                this.parts[0] = (EntityAnacondaPart)this.getChild();
                this.f_19804_.m_135381_(CHILD_ID, (Object)this.parts[0].m_19879_());
                for (int i = 1; i < this.parts.length && this.parts[i - 1].getChild() instanceof EntityAnacondaPart; ++i) {
                    this.parts[i] = (EntityAnacondaPart)this.parts[i - 1].getChild();
                }
            }
            AnacondaPartIndex partIndex = AnacondaPartIndex.HEAD;
            Vec3 prev = this.m_20182_();
            float xRot = this.m_146909_();
            for (int i = 0; i < 7; ++i) {
                if (this.parts[i] == null) continue;
                prevReqRot = this.calcPartRotation(i) + this.getYawForPart(i);
                float reqRot = this.calcPartRotation(i + 1) + this.getYawForPart(i);
                this.parts[i].setStrangleProgress(this.strangleProgress);
                this.parts[i].copyDataFrom(this);
                prev = this.parts[i].tickMultipartPosition(this.m_19879_(), partIndex, prev, xRot, prevReqRot, reqRot, true);
                partIndex = this.parts[i].getPartType();
                xRot = this.parts[i].m_146909_();
            }
            this.swimTimer = this.m_20069_() ? Math.max(this.swimTimer + 1, 0) : Math.min(this.swimTimer - 1, 0);
        }
        if (this.shedCooldown > 0) {
            --this.shedCooldown;
        }
        if (this.getSheddingTime() > 0) {
            this.setSheddingTime(this.getSheddingTime() - 1);
            if (this.getSheddingTime() == 0) {
                this.spawnItemAtOffset(new ItemStack((ItemLike)AMItemRegistry.SHED_SNAKE_SKIN.get()), 1.0f + this.f_19796_.m_188501_(), 0.2f);
                this.shedCooldown = 1000 + this.f_19796_.m_188503_(2000);
            }
        }
    }

    private boolean shouldReplaceParts() {
        if (this.parts == null || this.parts[0] == null) {
            return true;
        }
        for (int i = 0; i < 7; ++i) {
            if (this.parts[i] != null) continue;
            return true;
        }
        return false;
    }

    private float getYawForPart(int i) {
        return this.getRingBuffer(4 + i * 2, 1.0f);
    }

    public float getRingBuffer(int bufferOffset, float partialTicks) {
        if (this.m_21224_()) {
            partialTicks = 0.0f;
        }
        partialTicks = 1.0f - partialTicks;
        int i = this.ringBufferIndex - bufferOffset & 0x3F;
        int j = this.ringBufferIndex - bufferOffset - 1 & 0x3F;
        float d0 = this.ringBuffer[i];
        float d1 = this.ringBuffer[j] - d0;
        return Mth.m_14177_((float)(d0 + d1 * partialTicks));
    }

    public float m_6134_() {
        return this.m_6162_() ? 0.75f : 1.0f;
    }

    public boolean m_6094_() {
        return !this.isStrangling();
    }

    public boolean shouldMove() {
        return !this.isStrangling();
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.ANACONDA_FOODSTUFFS);
    }

    public void m_7023_(Vec3 travelVector) {
        if (!this.shouldMove()) {
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
            if (this.m_5448_() == null) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, -0.005, 0.0));
            }
        } else {
            super.m_7023_(travelVector);
        }
    }

    public float getStrangleProgress(float partialTick) {
        return this.prevStrangleProgress + (this.strangleProgress - this.prevStrangleProgress) * partialTick;
    }

    private float calcPartRotation(int i) {
        float f = 1.0f - this.strangleProgress * 0.2f;
        float strangleIntensity = (float)((double)Mth.m_14036_((float)(this.strangleTimer * 3), (float)0.0f, (float)100.0f) * (1.0 + (double)0.2f * Math.sin(0.15f * (float)this.strangleTimer)));
        return (float)(40.0 * -Math.sin(this.f_19787_ * 3.0f - (float)i)) * f + this.strangleProgress * 0.2f * (float)i * strangleIntensity;
    }

    @Nullable
    public ItemEntity spawnItemAtOffset(ItemStack stack, float f, float f1) {
        if (stack.m_41619_()) {
            return null;
        }
        if (this.m_9236_().f_46443_) {
            return null;
        }
        Vec3 vec = new Vec3(0.0, 0.0, (double)f).m_82524_(-f * ((float)Math.PI / 180));
        ItemEntity itementity = new ItemEntity(this.m_9236_(), this.m_20185_() + vec.f_82479_, this.m_20186_() + (double)f1, this.m_20189_() + vec.f_82481_, stack);
        itementity.m_32060_();
        if (this.captureDrops() != null) {
            this.captureDrops().add(itementity);
        } else {
            this.m_9236_().m_7967_((Entity)itementity);
        }
        return itementity;
    }

    @Override
    public boolean shouldEnterWater() {
        return this.m_5448_() == null && !this.shouldLeaveWater() && this.swimTimer <= -1000;
    }

    @Override
    public boolean shouldLeaveWater() {
        if (!this.m_20197_().isEmpty()) {
            return false;
        }
        if (this.m_5448_() != null && !this.m_5448_().m_20069_()) {
            return true;
        }
        return this.swimTimer > 600 || this.isShedding();
    }

    @Override
    public boolean shouldStopMoving() {
        return !this.shouldMove();
    }

    @Override
    public int getWaterSearchRange() {
        return 12;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob mob) {
        EntityAnaconda anaconda = (EntityAnaconda)((EntityType)AMEntityRegistry.ANACONDA.get()).m_20615_((Level)serverWorld);
        anaconda.setYellow(this.isYellow());
        return anaconda;
    }

    public void m_5993_(Entity entity, int score, DamageSource src) {
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            CompoundTag emptyNbt = new CompoundTag();
            living.m_7380_(emptyNbt);
            emptyNbt.m_128359_("DeathLootTable", BuiltInLootTables.f_78712_.toString());
            living.m_7378_(emptyNbt);
            if (this.getChild() instanceof EntityAnacondaPart) {
                ((EntityAnacondaPart)this.getChild()).setSwell(5.0f);
            }
        }
        super.m_5993_(entity, score, src);
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268612_) || super.m_6673_(source);
    }

    public boolean m_6779_(LivingEntity livingEntity) {
        boolean prev = super.m_6779_(livingEntity);
        if (prev && this.passiveFor > 0 && livingEntity instanceof Player && (this.m_21188_() == null || !this.m_21188_().m_20148_().equals(livingEntity.m_20148_()))) {
            return false;
        }
        return prev;
    }

    public void feed() {
        this.m_5634_(10.0f);
        ++this.feedings;
        if (this.feedings >= 3 && this.feedings % 3 == 0 && this.shedCooldown <= 0) {
            this.setSheddingTime(this.m_217043_().m_188503_(500) + 500);
        }
    }

    public boolean isShedding() {
        return this.getSheddingTime() > 0;
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setYellow(this.f_19796_.m_188499_());
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private class AIMelee
    extends Goal {
        private final EntityAnaconda snake;
        private int jumpAttemptCooldown = 0;

        public AIMelee() {
            this.snake = EntityAnaconda.this;
        }

        public boolean m_8036_() {
            return this.snake.m_5448_() != null && this.snake.m_5448_().m_6084_();
        }

        public void m_8037_() {
            LivingEntity target;
            if (this.jumpAttemptCooldown > 0) {
                --this.jumpAttemptCooldown;
            }
            if ((target = this.snake.m_5448_()) != null && target.m_6084_()) {
                if (this.jumpAttemptCooldown == 0 && this.snake.m_20270_((Entity)target) < 1.0f + target.m_20205_() && !this.snake.isStrangling()) {
                    target.m_6469_(this.snake.m_269291_().m_269333_((LivingEntity)this.snake), 4.0f);
                    this.snake.setStrangling(target.m_20205_() <= 2.0f && !(target instanceof EntityAnaconda));
                    this.snake.m_5496_((SoundEvent)AMSoundRegistry.ANACONDA_ATTACK.get(), this.snake.m_6121_(), this.snake.m_6100_());
                    this.jumpAttemptCooldown = 5 + EntityAnaconda.this.f_19796_.m_188503_(5);
                }
                if (this.snake.isStrangling()) {
                    this.snake.m_21573_().m_26573_();
                } else {
                    try {
                        this.snake.m_21573_().m_5624_((Entity)target, (double)1.3f);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void m_8041_() {
            this.snake.setStrangling(false);
        }
    }
}
