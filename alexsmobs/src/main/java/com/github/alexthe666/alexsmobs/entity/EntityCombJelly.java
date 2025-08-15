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
 *  net.minecraft.util.Mth
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
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
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
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityCombJelly
extends WaterAnimal
implements Bucketable {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityCombJelly.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Float> JELLYPITCH = SynchedEntityData.m_135353_(EntityCombJelly.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.m_135353_(EntityCombJelly.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> JELLY_SCALE = SynchedEntityData.m_135353_(EntityCombJelly.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    public float prevOnLandProgress;
    public float onLandProgress;
    private BlockPos moveTarget;
    public float prevjellyPitch = 0.0f;
    public float spin;
    public float prevSpin;

    protected EntityCombJelly(EntityType<? extends WaterAnimal> animal, Level level) {
        super(animal, level);
    }

    public int m_5792_() {
        return 4;
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_8077_() || this.m_27487_();
    }

    public boolean m_6785_(double p_213397_1_) {
        return !this.m_27487_() && !this.m_8077_();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.terrapinSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canCombJellySpawn(EntityType<EntityCombJelly> entityType, ServerLevelAccessor iServerWorld, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return reason == MobSpawnType.SPAWNER || iServerWorld.m_46801_(pos) && iServerWorld.m_46801_(pos.m_7494_()) && EntityCombJelly.isLightLevelOk(pos, iServerWorld);
    }

    private static boolean isLightLevelOk(BlockPos pos, ServerLevelAccessor iServerWorld) {
        float time = iServerWorld.m_46942_(1.0f);
        int light = iServerWorld.m_46803_(pos);
        return light <= 4 && time > 0.27f && time <= 0.8f;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(VARIANT, (Object)0);
        this.f_19804_.m_135372_(JELLYPITCH, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(FROM_BUCKET, (Object)false);
        this.f_19804_.m_135372_(JELLY_SCALE, (Object)Float.valueOf(1.0f));
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.COMB_JELLY_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.COMB_JELLY_HURT.get();
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int variant) {
        this.f_19804_.m_135381_(VARIANT, (Object)variant);
    }

    public float getJellyPitch() {
        return Mth.m_14036_((float)((Float)this.f_19804_.m_135370_(JELLYPITCH)).floatValue(), (float)-90.0f, (float)90.0f);
    }

    public void setJellyPitch(float pitch) {
        this.f_19804_.m_135381_(JELLYPITCH, (Object)Float.valueOf(Mth.m_14036_((float)pitch, (float)-90.0f, (float)90.0f)));
    }

    public float getJellyScale() {
        return ((Float)this.f_19804_.m_135370_(JELLY_SCALE)).floatValue();
    }

    public void setJellyScale(float scale) {
        this.f_19804_.m_135381_(JELLY_SCALE, (Object)Float.valueOf(scale));
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

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 6.0).m_22268_(Attributes.f_22279_, (double)0.2f);
    }

    @Nonnull
    public ItemStack m_28282_() {
        ItemStack stack = new ItemStack((ItemLike)AMItemRegistry.COMB_JELLY_BUCKET.get());
        if (this.m_8077_()) {
            stack.m_41714_(this.m_7770_());
        }
        return stack;
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevOnLandProgress = this.onLandProgress;
        this.prevjellyPitch = this.getJellyPitch();
        this.prevSpin = this.spin;
        if (!this.m_20069_() && this.onLandProgress < 5.0f) {
            this.onLandProgress += 1.0f;
        }
        if (this.m_20069_() && this.onLandProgress > 0.0f) {
            this.onLandProgress -= 1.0f;
        }
        if (!this.m_9236_().f_46443_) {
            if (this.m_20069_()) {
                float decrease;
                this.m_20242_(true);
                if (this.moveTarget == null || this.f_19796_.m_188503_(120) == 0 || this.m_20275_((float)this.moveTarget.m_123341_() + 0.5f, (float)this.moveTarget.m_123342_() + 0.5f, (float)this.moveTarget.m_123343_() + 0.5f) < 5.0 || this.f_19797_ % 10 == 0 && !this.canBlockPosBeSeen(this.moveTarget)) {
                    BlockPos randPos = this.m_20183_().m_7918_(this.f_19796_.m_188503_(10) - 5, this.f_19796_.m_188503_(6) - 3, this.f_19796_.m_188503_(10) - 5);
                    if (this.m_9236_().m_6425_(randPos).m_192917_((Fluid)Fluids.f_76193_) && this.m_9236_().m_6425_(randPos.m_7494_()).m_192917_((Fluid)Fluids.f_76193_)) {
                        this.moveTarget = randPos;
                    }
                }
                if (this.m_204036_(FluidTags.f_13131_) < (double)this.m_20206_()) {
                    this.moveTarget = null;
                    this.m_20256_(this.m_20184_().m_82520_(0.0, -0.02, 0.0));
                }
                if (this.moveTarget != null) {
                    double d0 = (double)((float)this.moveTarget.m_123341_() + 0.5f) - this.m_20185_();
                    double d1 = (double)((float)this.moveTarget.m_123342_() + 0.5f) - this.m_20186_();
                    double d2 = (double)((float)this.moveTarget.m_123343_() + 0.5f) - this.m_20189_();
                    double d3 = Mth.m_14116_((float)((float)(d0 * d0 + d1 * d1 + d2 * d2)));
                    float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
                    this.m_146922_(this.m_21376_(this.m_146908_(), f, 1.0f));
                    this.f_20883_ = this.m_146908_();
                    float movSpeed = 0.004f;
                    Vec3 movingVec = new Vec3(d0 / d3, d1 / d3, d2 / d3).m_82541_();
                    this.m_20256_(this.m_20184_().m_82549_(movingVec.m_82490_((double)0.004f)));
                }
                float dist = (float)((Math.abs(this.m_20184_().m_7096_()) + Math.abs(this.m_20184_().m_7094_())) * 30.0);
                this.incrementJellyPitch(dist);
                if (this.f_19862_) {
                    this.m_20256_(this.m_20184_().m_82520_(0.0, (double)0.2f, 0.0));
                }
                if (this.getJellyPitch() > 0.0f) {
                    decrease = Math.min(0.5f, this.getJellyPitch());
                    this.decrementJellyPitch(decrease);
                }
                if (this.getJellyPitch() < 0.0f) {
                    decrease = Math.min(0.5f, -this.getJellyPitch());
                    this.incrementJellyPitch(decrease);
                }
            } else {
                this.m_20242_(false);
            }
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("FromBucket", this.m_27487_());
        compound.m_128350_("JellyScale", this.getJellyScale());
        compound.m_128405_("Variant", this.getVariant());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_27497_(compound.m_128471_("FromBucket"));
        this.setJellyScale(compound.m_128457_("JellyScale"));
        this.setVariant(compound.m_128451_("Variant"));
    }

    public boolean canBlockPosBeSeen(BlockPos pos) {
        double x = (float)pos.m_123341_() + 0.5f;
        double y = (float)pos.m_123342_() + 0.5f;
        double z = (float)pos.m_123343_() + 0.5f;
        BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(this.m_146892_(), new Vec3(x, y, z), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        double dist = result.m_82450_().m_82531_(x, y, z);
        return dist <= 1.0 || result.m_6662_() == HitResult.Type.MISS;
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_21515_() && this.m_20069_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82542_(0.9, 0.6, 0.9));
        } else {
            super.m_7023_(travelVector);
        }
    }

    @Nonnull
    protected InteractionResult m_6071_(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.m_148828_((Player)player, (InteractionHand)hand, (LivingEntity)this).orElse(super.m_6071_(player, hand));
    }

    public void m_6872_(@Nonnull ItemStack bucket) {
        if (this.m_8077_()) {
            bucket.m_41714_(this.m_7770_());
        }
        Bucketable.m_148822_((Mob)this, (ItemStack)bucket);
        CompoundTag compoundnbt = bucket.m_41784_();
        compoundnbt.m_128350_("BucketScale", this.getJellyScale());
        compoundnbt.m_128405_("BucketVariantTag", this.getVariant());
    }

    public void m_142278_(@Nonnull CompoundTag compound) {
        Bucketable.m_148825_((Mob)this, (CompoundTag)compound);
        if (compound.m_128441_("BucketScale")) {
            this.setJellyScale(compound.m_128457_("BucketScale"));
        }
        if (compound.m_128441_("BucketVariantTag")) {
            this.setVariant(compound.m_128451_("BucketVariantTag"));
        }
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setVariant(this.f_19796_.m_188503_(3));
        this.setJellyScale(0.8f + this.f_19796_.m_188501_() * 0.4f);
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void incrementJellyPitch(float pitch) {
        this.f_19804_.m_135381_(JELLYPITCH, (Object)Float.valueOf(this.getJellyPitch() + pitch));
    }

    public void decrementJellyPitch(float pitch) {
        this.f_19804_.m_135381_(JELLYPITCH, (Object)Float.valueOf(this.getJellyPitch() - pitch));
    }

    protected float m_21376_(float p_24992_, float p_24993_, float p_24994_) {
        float f1;
        float f = Mth.m_14177_((float)(p_24993_ - p_24992_));
        if (f > p_24994_) {
            f = p_24994_;
        }
        if (f < -p_24994_) {
            f = -p_24994_;
        }
        if ((f1 = p_24992_ + f) < 0.0f) {
            f1 += 360.0f;
        } else if (f1 > 360.0f) {
            f1 -= 360.0f;
        }
        return f1;
    }

    public MobType m_6336_() {
        return MobType.f_21644_;
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }
}
