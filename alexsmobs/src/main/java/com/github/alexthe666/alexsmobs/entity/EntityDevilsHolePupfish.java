/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
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
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntitySelector
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
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.PanicGoal
 *  net.minecraft.world.entity.ai.goal.TryFindWaterGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.animal.FlyingAnimal
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIRandomSwimming;
import com.github.alexthe666.alexsmobs.entity.ai.AquaticMoveController;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.alexsmobs.world.AMWorldData;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
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
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class EntityDevilsHolePupfish
extends WaterAnimal
implements FlyingAnimal,
Bucketable {
    public static final ResourceLocation PUPFISH_REWARD = new ResourceLocation("alexsmobs", "gameplay/pupfish_reward");
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.m_135353_(EntityDevilsHolePupfish.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> PUPFISH_SCALE = SynchedEntityData.m_135353_(EntityDevilsHolePupfish.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Integer> FEEDING_TIME = SynchedEntityData.m_135353_(EntityDevilsHolePupfish.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> BABY_AGE = SynchedEntityData.m_135353_(EntityDevilsHolePupfish.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Optional<BlockPos>> FEEDING_POS = SynchedEntityData.m_135353_(EntityDevilsHolePupfish.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    public float prevOnLandProgress;
    public float onLandProgress;
    public float prevFeedProgress;
    public float feedProgress;
    private EntityDevilsHolePupfish chasePartner;
    private int chaseTime = 0;
    private boolean chaseDriver;
    private boolean breedNextChase;
    private int chaseCooldown = 0;
    private int maxChaseTime = 300;

    protected EntityDevilsHolePupfish(EntityType<? extends WaterAnimal> type, Level level) {
        super(type, level);
        this.f_21342_ = new AquaticMoveController((PathfinderMob)this, 1.0f, 15.0f);
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new WaterBoundPathNavigation((Mob)this, worldIn);
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.DEVILS_HOLE_PUPFISH_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.DEVILS_HOLE_PUPFISH_HURT.get();
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(1, (Goal)new TryFindWaterGoal((PathfinderMob)this));
        this.f_21345_.m_25352_(2, (Goal)new EatMossGoal(this));
        this.f_21345_.m_25352_(3, (Goal)new ChaseGoal(this));
        this.f_21345_.m_25352_(4, (Goal)new PanicGoal((PathfinderMob)this, 1.0));
        this.f_21345_.m_25352_(5, (Goal)new AnimalAIRandomSwimming((PathfinderMob)this, 1.0, 12, 5));
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 2.0).m_22268_(Attributes.f_22279_, (double)0.34f);
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_8077_() || this.m_27487_();
    }

    public static boolean canPupfishSpawn(EntityType<EntityDevilsHolePupfish> entityType, ServerLevelAccessor iServerWorld, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return reason == MobSpawnType.SPAWNER || EntityDevilsHolePupfish.isPupfishChunk(iServerWorld, pos) && iServerWorld.m_6425_(pos).m_205070_(FluidTags.f_13131_) && EntityDevilsHolePupfish.isInCave(iServerWorld, pos);
    }

    private static boolean isPupfishChunk(ServerLevelAccessor iServerWorld, BlockPos pos) {
        AMWorldData data = AMWorldData.get((Level)iServerWorld.m_6018_());
        return data != null && data.isInPupfishChunk(pos);
    }

    private static boolean isInCave(ServerLevelAccessor iServerWorld, BlockPos pos) {
        while (iServerWorld.m_6425_(pos).m_205070_(FluidTags.f_13131_)) {
            pos = pos.m_7494_();
        }
        return !iServerWorld.m_45527_(pos) && pos.m_123342_() < iServerWorld.m_5736_();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.devilsHolePupfishSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public int m_5792_() {
        return 6;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FROM_BUCKET, (Object)false);
        this.f_19804_.m_135372_(PUPFISH_SCALE, (Object)Float.valueOf(1.0f));
        this.f_19804_.m_135372_(FEEDING_TIME, (Object)0);
        this.f_19804_.m_135372_(BABY_AGE, (Object)0);
        this.f_19804_.m_135372_(FEEDING_POS, Optional.empty());
    }

    public void m_8119_() {
        BlockPos feedingPos;
        int feedingTime;
        boolean inWaterOrBubble;
        super.m_8119_();
        this.prevOnLandProgress = this.onLandProgress;
        this.prevFeedProgress = this.feedProgress;
        if (this.chaseCooldown > 0) {
            --this.chaseCooldown;
        }
        if (!(inWaterOrBubble = this.m_20072_()) && this.onLandProgress < 5.0f) {
            this.onLandProgress += 1.0f;
        }
        if (inWaterOrBubble && this.onLandProgress > 0.0f) {
            this.onLandProgress -= 1.0f;
        }
        if ((feedingTime = this.getFeedingTime()) > 0 && this.feedProgress < 5.0f) {
            this.feedProgress += 1.0f;
        }
        if (feedingTime <= 0 && this.feedProgress > 0.0f) {
            this.feedProgress -= 1.0f;
        }
        if (this.m_6162_()) {
            this.setBabyAge(this.getBabyAge() + 1);
        }
        if ((feedingPos = (BlockPos)((Optional)this.f_19804_.m_135370_(FEEDING_POS)).orElse(null)) == null) {
            float f2 = (float)(-((double)((float)this.m_20184_().f_82480_ * 2.2f) * 57.2957763671875));
            this.m_146926_(f2);
        } else if (this.getFeedingTime() > 0) {
            Vec3 face = Vec3.m_82512_((Vec3i)feedingPos).m_82546_(this.m_20182_());
            double d0 = face.m_165924_();
            this.m_146926_((float)(-Mth.m_14136_((double)face.f_82480_, (double)d0) * 57.2957763671875));
            this.m_146922_((float)Mth.m_14136_((double)face.f_82481_, (double)face.f_82479_) * 57.295776f - 90.0f);
            this.f_20883_ = this.m_146908_();
            this.f_20885_ = this.m_146908_();
            BlockState state = this.m_9236_().m_8055_(feedingPos);
            if (this.f_19796_.m_188503_(2) == 0 && !state.m_60795_()) {
                Vec3 mouth = new Vec3(0.0, (double)(this.m_20206_() * 0.5f), (double)(0.4f * this.getPupfishScale())).m_82496_(this.m_146909_() * ((float)Math.PI / 180)).m_82524_(-this.m_146908_() * ((float)Math.PI / 180));
                for (int i = 0; i < 4 + this.f_19796_.m_188503_(2); ++i) {
                    double motX = this.f_19796_.m_188583_() * 0.02;
                    double motY = 0.1f + this.f_19796_.m_188501_() * 0.2f;
                    double motZ = this.f_19796_.m_188583_() * 0.02;
                    this.m_9236_().m_7106_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123794_, state), this.m_20185_() + mouth.f_82479_, this.m_20186_() + mouth.f_82480_, this.m_20189_() + mouth.f_82481_, motX, motY, motZ);
                }
            }
        }
        if (!this.m_20072_() && this.m_6084_() && this.m_20096_() && this.f_19796_.m_188501_() < 0.5f) {
            this.m_20256_(this.m_20184_().m_82520_((double)((this.f_19796_.m_188501_() * 2.0f - 1.0f) * 0.2f), 0.5, (double)((this.f_19796_.m_188501_() * 2.0f - 1.0f) * 0.2f)));
            this.m_146922_(this.f_19796_.m_188501_() * 360.0f);
            this.m_5496_(SoundEvents.f_11760_, this.m_6121_(), this.m_6100_());
        }
    }

    public EntityDimensions m_6972_(Pose poseIn) {
        return super.m_6972_(poseIn).m_20388_(this.getPupfishScale());
    }

    public boolean m_27487_() {
        return (Boolean)this.f_19804_.m_135370_(FROM_BUCKET);
    }

    public void m_27497_(boolean bucketed) {
        this.f_19804_.m_135381_(FROM_BUCKET, (Object)bucketed);
    }

    public void m_6872_(@Nonnull ItemStack bucket) {
        if (this.m_8077_()) {
            bucket.m_41714_(this.m_7770_());
        }
        Bucketable.m_148822_((Mob)this, (ItemStack)bucket);
        CompoundTag compound = bucket.m_41784_();
        compound.m_128350_("BucketScale", this.getPupfishScale());
        compound.m_128350_("BabyAge", (float)this.getBabyAge());
    }

    public void m_142278_(@Nonnull CompoundTag compound) {
        Bucketable.m_148825_((Mob)this, (CompoundTag)compound);
        if (compound.m_128441_("BucketScale")) {
            this.setPupfishScale(compound.m_128457_("BucketScale"));
        }
        if (compound.m_128441_("BabyAge")) {
            this.setBabyAge(compound.m_128451_("BabyAge"));
        }
    }

    @Nonnull
    public ItemStack m_28282_() {
        ItemStack stack = new ItemStack((ItemLike)AMItemRegistry.DEVILS_HOLE_PUPFISH_BUCKET.get());
        if (this.m_8077_()) {
            stack.m_41714_(this.m_7770_());
        }
        return stack;
    }

    public SoundEvent m_142623_() {
        return SoundEvents.f_11782_;
    }

    public float getPupfishScale() {
        return ((Float)this.f_19804_.m_135370_(PUPFISH_SCALE)).floatValue();
    }

    public void setPupfishScale(float scale) {
        this.f_19804_.m_135381_(PUPFISH_SCALE, (Object)Float.valueOf(scale));
    }

    public int getFeedingTime() {
        return (Integer)this.f_19804_.m_135370_(FEEDING_TIME);
    }

    public void setFeedingTime(int feedingTime) {
        this.f_19804_.m_135381_(FEEDING_TIME, (Object)feedingTime);
    }

    public int getBabyAge() {
        return (Integer)this.f_19804_.m_135370_(BABY_AGE);
    }

    public void setBabyAge(int babyAge) {
        this.f_19804_.m_135381_(BABY_AGE, (Object)babyAge);
    }

    public boolean m_6162_() {
        return this.getBabyAge() < 0;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("FromBucket", this.m_27487_());
        compound.m_128379_("BreedNextChase", this.breedNextChase);
        compound.m_128350_("PupfishScale", this.getPupfishScale());
        compound.m_128405_("BabyAge", this.getBabyAge());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_27497_(compound.m_128471_("FromBucket"));
        this.breedNextChase = compound.m_128471_("BreedNextChase");
        this.setPupfishScale(compound.m_128457_("PupfishScale"));
        this.setBabyAge(compound.m_128451_("BabyAge"));
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setPupfishScale(0.65f + this.f_19796_.m_188501_() * 0.35f);
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void m_6229_(int i) {
        if (this.m_6084_() && !this.m_20072_()) {
            this.m_20301_(i - 1);
            if (this.m_20146_() == -20) {
                this.m_20301_(0);
                this.m_6469_(this.m_269291_().m_269483_(), 2.0f);
            }
        } else {
            this.m_20301_(this.m_6062_());
        }
    }

    public int m_6062_() {
        return 600;
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_21515_() && this.m_20069_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            float f = 0.6f;
            this.m_20256_(this.m_20184_().m_82542_(0.9, (double)f, 0.9));
            if (this.m_5448_() == null) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, -0.005, 0.0));
            }
        } else {
            super.m_7023_(travelVector);
        }
    }

    protected void m_5625_(float f) {
        if (this.f_19796_.m_188503_(2) == 0) {
            this.m_5496_(this.m_5501_(), 0.2f, 1.3f + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.4f);
        }
    }

    protected SoundEvent m_5501_() {
        return SoundEvents.f_11938_;
    }

    protected void m_7355_(BlockPos pos, BlockState state) {
    }

    public boolean m_29443_() {
        return false;
    }

    private boolean canSeeBlock(BlockPos destinationBlock) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        Vec3 blockVec = Vec3.m_82512_((Vec3i)destinationBlock);
        BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(Vector3d, blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        return result.m_82425_().equals((Object)destinationBlock);
    }

    private static List<ItemStack> getFoodLoot(EntityDevilsHolePupfish pupfish) {
        LootTable loottable = pupfish.m_9236_().m_7654_().m_278653_().m_278676_(PUPFISH_REWARD);
        return loottable.m_287195_(new LootParams.Builder((ServerLevel)pupfish.m_9236_()).m_287286_(LootContextParams.f_81455_, (Object)pupfish).m_287235_(LootContextParamSets.f_81417_));
    }

    public boolean m_6785_(double dist) {
        return !this.m_27487_() && !this.m_8077_() && !this.m_6162_();
    }

    private void spawnBabiesWith(EntityDevilsHolePupfish chasePartner) {
        EntityDevilsHolePupfish baby = (EntityDevilsHolePupfish)((EntityType)AMEntityRegistry.DEVILS_HOLE_PUPFISH.get()).m_20615_(this.m_9236_());
        baby.m_20359_((Entity)this);
        baby.setPupfishScale(0.65f + this.f_19796_.m_188501_() * 0.35f);
        baby.setBabyAge(-24000);
        this.m_9236_().m_7967_((Entity)baby);
    }

    @Nonnull
    protected InteractionResult m_6071_(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.m_148828_((Player)player, (InteractionHand)hand, (LivingEntity)this).orElse(super.m_6071_(player, hand));
    }

    private class EatMossGoal
    extends Goal {
        private final int searchLength;
        private final int verticalSearchRange;
        protected BlockPos destinationBlock;
        private final EntityDevilsHolePupfish pupfish;
        private int runDelay = 70;
        private int maxFeedTime = 200;

        private EatMossGoal(EntityDevilsHolePupfish pupfish) {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.pupfish = pupfish;
            this.searchLength = 16;
            this.verticalSearchRange = 6;
        }

        public boolean m_8045_() {
            return this.destinationBlock != null && this.isMossBlock(this.pupfish.m_9236_(), this.destinationBlock.m_122032_()) && this.isCloseToMoss(16.0);
        }

        public boolean isCloseToMoss(double dist) {
            return this.destinationBlock == null || this.pupfish.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
        }

        public boolean m_8036_() {
            if (!this.pupfish.m_20072_()) {
                return false;
            }
            if (this.runDelay > 0) {
                --this.runDelay;
                return false;
            }
            this.runDelay = 200 + this.pupfish.f_19796_.m_188503_(150);
            return this.searchForDestination();
        }

        public void m_8056_() {
            this.maxFeedTime = 60 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(60);
        }

        public void m_8037_() {
            Vec3 vec = Vec3.m_82512_((Vec3i)this.destinationBlock);
            if (vec != null) {
                this.pupfish.m_21573_().m_26519_(vec.f_82479_, vec.f_82480_, vec.f_82481_, 1.0);
                if (this.pupfish.m_20238_(vec) < (double)1.15f) {
                    this.pupfish.f_19804_.m_135381_(FEEDING_POS, Optional.of(this.destinationBlock));
                    Vec3 face = vec.m_82546_(this.pupfish.m_20182_());
                    this.pupfish.m_20256_(this.pupfish.m_20184_().m_82549_(face.m_82541_().m_82490_((double)0.1f)));
                    this.pupfish.setFeedingTime(this.pupfish.getFeedingTime() + 1);
                    if (this.pupfish.getFeedingTime() > this.maxFeedTime) {
                        List<ItemStack> lootList;
                        this.destinationBlock = null;
                        if (EntityDevilsHolePupfish.this.f_19796_.m_188503_(3) == 0 && !(lootList = EntityDevilsHolePupfish.getFoodLoot(this.pupfish)).isEmpty()) {
                            for (ItemStack stack : lootList) {
                                ItemEntity e = this.pupfish.m_19983_(stack.m_41777_());
                                e.f_19812_ = true;
                                e.m_20256_(e.m_20184_().m_82542_(0.2, 0.2, 0.2));
                            }
                        }
                        if (EntityDevilsHolePupfish.this.f_19796_.m_188503_(3) == 0 && !this.pupfish.m_6162_()) {
                            this.pupfish.breedNextChase = true;
                        }
                    }
                } else {
                    this.pupfish.f_19804_.m_135381_(FEEDING_POS, Optional.empty());
                }
            }
        }

        public void m_8041_() {
            this.pupfish.f_19804_.m_135381_(FEEDING_POS, Optional.empty());
            this.destinationBlock = null;
            this.pupfish.setFeedingTime(0);
        }

        protected boolean searchForDestination() {
            int lvt_1_1_ = this.searchLength;
            BlockPos lvt_3_1_ = this.pupfish.m_20183_();
            BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
            for (int lvt_5_1_ = -8; lvt_5_1_ <= 2; ++lvt_5_1_) {
                for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                    int lvt_7_1_ = 0;
                    while (lvt_7_1_ <= lvt_6_1_) {
                        int lvt_8_1_;
                        int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                        while (lvt_8_1_ <= lvt_6_1_) {
                            lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                            if (this.isMossBlock(this.pupfish.m_9236_(), lvt_4_1_) && this.pupfish.canSeeBlock((BlockPos)lvt_4_1_)) {
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

        private boolean isMossBlock(Level world, BlockPos.MutableBlockPos pos) {
            return world.m_8055_((BlockPos)pos).m_204336_(AMTagRegistry.PUPFISH_EATABLES);
        }
    }

    private class ChaseGoal
    extends Goal {
        private final EntityDevilsHolePupfish pupfish;
        private final Predicate<Entity> validChasePartner;
        private int executionCooldown = 50;

        public ChaseGoal(EntityDevilsHolePupfish pupfish) {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.pupfish = pupfish;
            this.validChasePartner = pupfish1 -> {
                EntityDevilsHolePupfish otherFish;
                return pupfish1 instanceof EntityDevilsHolePupfish && (otherFish = (EntityDevilsHolePupfish)((Object)pupfish1)).m_19879_() != this.pupfish.m_19879_() && otherFish.chasePartner == null && otherFish.chaseCooldown <= 0;
            };
        }

        public boolean m_8036_() {
            if (!this.pupfish.m_20072_() || this.pupfish.chaseTime > this.pupfish.maxChaseTime || this.pupfish.chaseCooldown > 0) {
                return false;
            }
            if (this.pupfish.chasePartner != null && this.pupfish.chasePartner.m_6084_()) {
                return true;
            }
            if (this.executionCooldown > 0) {
                --this.executionCooldown;
            } else {
                this.executionCooldown = 50 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(50);
                if (this.pupfish.chasePartner == null || !this.pupfish.chasePartner.m_6084_()) {
                    EntityDevilsHolePupfish closestPupfish;
                    List list = this.pupfish.m_9236_().m_6443_(EntityDevilsHolePupfish.class, this.pupfish.m_20191_().m_82377_(10.0, 8.0, 10.0), EntitySelector.f_20408_.and(this.validChasePartner));
                    list.sort(Comparator.comparingDouble(arg_0 -> ((EntityDevilsHolePupfish)this.pupfish).m_20280_(arg_0)));
                    if (!list.isEmpty() && (closestPupfish = (EntityDevilsHolePupfish)((Object)list.get(0))) != null) {
                        this.pupfish.chasePartner = closestPupfish;
                        closestPupfish.chasePartner = this.pupfish;
                        this.pupfish.chaseDriver = true;
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }

        public boolean m_8045_() {
            return this.pupfish.chasePartner != null && this.pupfish.chasePartner.m_6084_() && this.pupfish.chaseTime < this.pupfish.maxChaseTime;
        }

        public void m_8056_() {
            this.pupfish.chaseDriver = !this.pupfish.chasePartner.chaseDriver;
            this.pupfish.chaseTime = 0;
            this.pupfish.maxChaseTime = 600;
        }

        public void m_8041_() {
            this.pupfish.chaseTime = 0;
            this.pupfish.chaseCooldown = 100 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(100);
            this.executionCooldown = 50 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(20);
            if (this.pupfish.breedNextChase) {
                this.pupfish.spawnBabiesWith(this.pupfish.chasePartner);
                this.pupfish.chasePartner.breedNextChase = false;
                this.pupfish.breedNextChase = false;
            }
            this.pupfish.chasePartner = null;
        }

        public void m_8037_() {
            ++this.pupfish.chaseTime;
            if (this.pupfish.chasePartner == null || !this.pupfish.chaseDriver) {
                return;
            }
            float chaserSpeed = 1.2f + EntityDevilsHolePupfish.this.f_19796_.m_188501_() * 0.45f;
            float chasedSpeed = 0.2f + chaserSpeed * 0.7f;
            EntityDevilsHolePupfish flee = this.pupfish.chaseDriver ? this.pupfish.chasePartner : this.pupfish;
            EntityDevilsHolePupfish driver = this.pupfish.chaseDriver ? this.pupfish : this.pupfish.chasePartner;
            driver.m_21573_().m_26519_(flee.m_20185_(), flee.m_20227_(0.5), flee.m_20189_(), (double)chaserSpeed);
            Vec3 from = flee.m_20182_().m_82520_((double)(EntityDevilsHolePupfish.this.f_19796_.m_188501_() - 0.5f), (double)(EntityDevilsHolePupfish.this.f_19796_.m_188501_() - 0.5f), (double)(EntityDevilsHolePupfish.this.f_19796_.m_188501_() - 0.5f)).m_82546_(driver.m_20182_()).m_82541_().m_82490_((double)(2.0f + EntityDevilsHolePupfish.this.f_19796_.m_188501_() * 2.0f));
            Vec3 to = flee.m_20182_().m_82549_(from);
            flee.m_21573_().m_26519_(to.f_82479_, to.f_82480_, to.f_82481_, (double)chasedSpeed);
            if (EntityDevilsHolePupfish.this.f_19796_.m_188503_(50) == 0) {
                this.pupfish.chaseDriver = !this.pupfish.chaseDriver;
                this.pupfish.chasePartner.chaseDriver = !this.pupfish.chasePartner.chaseDriver;
            }
        }
    }
}
