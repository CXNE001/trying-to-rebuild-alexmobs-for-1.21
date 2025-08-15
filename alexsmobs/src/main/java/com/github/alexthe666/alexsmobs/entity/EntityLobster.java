/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFindWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeaveWater;
import com.github.alexthe666.alexsmobs.entity.ai.BottomFeederAIWander;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticPathNavigator;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class EntityLobster
extends WaterAnimal
implements ISemiAquatic,
Bucketable {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.m_135353_(EntityLobster.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.m_135353_(EntityLobster.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityLobster.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float attackProgress;
    public float prevAttackProgress;
    private int attackCooldown = 0;

    protected EntityLobster(EntityType type, Level p_i48565_2_) {
        super(type, p_i48565_2_);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
    }

    public int m_5792_() {
        return 7;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 5.0).m_22268_(Attributes.f_22284_, 2.0).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22279_, (double)0.15f);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.lobsterSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.LOBSTER_HURT.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.LOBSTER_HURT.get();
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "blue";
            case 2 -> "yellow";
            case 3 -> "redblue";
            case 4 -> "black";
            case 5 -> "white";
            default -> "red";
        };
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new AnimalAIFindWater((PathfinderMob)this));
        this.f_21345_.m_25352_(1, (Goal)new AnimalAILeaveWater((PathfinderMob)this));
        this.f_21345_.m_25352_(3, (Goal)new BottomFeederAIWander((PathfinderMob)this, 1.0, 10, 50));
        this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(5, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
    }

    public MobType m_6336_() {
        return MobType.f_21642_;
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_21515_() && this.m_20069_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            if (this.f_20899_) {
                this.m_20256_(this.m_20184_().m_82490_(1.4));
                this.m_20256_(this.m_20184_().m_82520_(0.0, 0.72, 0.0));
            } else {
                this.m_20256_(this.m_20184_().m_82490_(0.4));
                this.m_20256_(this.m_20184_().m_82520_(0.0, -0.08, 0.0));
            }
        } else {
            super.m_7023_(travelVector);
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(VARIANT, (Object)0);
        this.f_19804_.m_135372_(ATTACK_TICK, (Object)0);
        this.f_19804_.m_135372_(FROM_BUCKET, (Object)false);
    }

    @Nonnull
    public ItemStack m_28282_() {
        ItemStack stack = new ItemStack((ItemLike)AMItemRegistry.LOBSTER_BUCKET.get());
        if (this.m_8077_()) {
            stack.m_41714_(this.m_7770_());
        }
        return stack;
    }

    public void m_6872_(@Nonnull ItemStack bucket) {
        if (this.m_8077_()) {
            bucket.m_41714_(this.m_7770_());
        }
        Bucketable.m_148822_((Mob)this, (ItemStack)bucket);
        CompoundTag compoundnbt = bucket.m_41784_();
        compoundnbt.m_128405_("BucketVariantTag", this.getVariant());
    }

    public void m_142278_(@Nonnull CompoundTag compound) {
        Bucketable.m_148825_((Mob)this, (CompoundTag)compound);
        if (compound.m_128425_("BucketVariantTag", 3)) {
            this.setVariant(compound.m_128451_("BucketVariantTag"));
        }
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_27487_();
    }

    public boolean m_6785_(double p_27492_) {
        return !this.m_27487_() && !this.m_8077_();
    }

    @Nonnull
    protected InteractionResult m_6071_(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.m_148828_((Player)player, (InteractionHand)hand, (LivingEntity)this).orElse(super.m_6071_(player, hand));
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        return worldIn.m_6425_(pos.m_7495_()).m_76178_() && worldIn.m_6425_(pos).m_205070_(FluidTags.f_13131_) ? 10.0f : super.m_5610_(pos, worldIn);
    }

    public boolean m_7327_(Entity entityIn) {
        this.f_19804_.m_135381_(ATTACK_TICK, (Object)5);
        return super.m_7327_(entityIn);
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevAttackProgress = this.attackProgress;
        if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) > 0) {
            if (this.attackProgress == 3.0f) {
                this.m_5496_((SoundEvent)AMSoundRegistry.LOBSTER_ATTACK.get(), this.m_6121_(), this.m_6100_());
            }
            if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) == 2 && this.m_5448_() != null && (double)this.m_20270_((Entity)this.m_5448_()) < 1.3) {
                this.m_5448_().m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 2.0f);
            }
            this.f_19804_.m_135381_(ATTACK_TICK, (Object)((Integer)this.f_19804_.m_135370_(ATTACK_TICK) - 1));
            if (this.attackProgress < 5.0f) {
                this.attackProgress += 1.0f;
            }
        } else if (this.attackProgress > 0.0f) {
            this.attackProgress -= 1.0f;
        }
        if (this.attackCooldown > 0) {
            --this.attackCooldown;
        }
        if (this.m_5448_() != null && this.m_20270_((Entity)this.m_5448_()) <= 1.0f && this.attackCooldown == 0) {
            this.m_21391_((Entity)this.m_5448_(), 180.0f, 20.0f);
            this.m_7327_((Entity)this.m_5448_());
            this.attackCooldown = 20;
        }
    }

    protected void m_6229_(int air) {
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int variant) {
        this.f_19804_.m_135381_(VARIANT, (Object)variant);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("Variant", this.getVariant());
        compound.m_128379_("FromBucket", this.m_27487_());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setVariant(compound.m_128451_("Variant"));
        this.m_27497_(compound.m_128471_("FromBucket"));
    }

    public boolean m_27487_() {
        return (Boolean)this.f_19804_.m_135370_(FROM_BUCKET);
    }

    public void m_27497_(boolean p_203706_1_) {
        this.f_19804_.m_135381_(FROM_BUCKET, (Object)p_203706_1_);
    }

    @Nonnull
    public SoundEvent m_142623_() {
        return SoundEvents.f_11782_;
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.m_217043_().m_188501_();
        if ((double)variantChange <= 1.0E-5) {
            this.setVariant(5);
        } else if ((double)variantChange <= 2.0E-5) {
            this.setVariant(4);
        } else if (variantChange <= 0.05f) {
            this.setVariant(3);
        } else if (variantChange <= 0.1f) {
            this.setVariant(2);
        } else if (variantChange <= 0.25f) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected PathNavigation m_6037_(Level worldIn) {
        SemiAquaticPathNavigator flyingpathnavigator = new SemiAquaticPathNavigator((Mob)this, worldIn){

            @Override
            public boolean m_6342_(BlockPos pos) {
                return this.f_26495_.m_8055_(pos).m_60819_().m_76178_();
            }
        };
        return flyingpathnavigator;
    }

    @Override
    public boolean shouldEnterWater() {
        return true;
    }

    @Override
    public boolean shouldLeaveWater() {
        return false;
    }

    @Override
    public boolean shouldStopMoving() {
        return false;
    }

    @Override
    public int getWaterSearchRange() {
        return 5;
    }

    public static <T extends Mob> boolean canLobsterSpawn(EntityType type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn) {
        boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.LOBSTER_SPAWNS);
        return spawnBlock || worldIn.m_6425_(pos).m_205070_(FluidTags.f_13131_);
    }
}
