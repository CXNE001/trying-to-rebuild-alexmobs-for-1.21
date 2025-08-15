/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.AgeableMob$AgeableMobGroupData
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 *  net.minecraft.world.entity.ai.goal.BreathAirGoal
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$TagValue
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.biome.Biomes
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityFlyingFish;
import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import com.github.alexthe666.alexsmobs.entity.IHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFindWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeaveWater;
import com.github.alexthe666.alexsmobs.entity.ai.AquaticMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.SealAIBask;
import com.github.alexthe666.alexsmobs.entity.ai.SealAIDiveForItems;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticPathNavigator;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class EntitySeal
extends Animal
implements ISemiAquatic,
IHerdPanic,
ITargetsDroppedItems {
    private static final EntityDataAccessor<Float> SWIM_ANGLE = SynchedEntityData.m_135353_(EntitySeal.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> BASKING = SynchedEntityData.m_135353_(EntitySeal.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> DIGGING = SynchedEntityData.m_135353_(EntitySeal.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> ARCTIC = SynchedEntityData.m_135353_(EntitySeal.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntitySeal.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> BOB_TICKS = SynchedEntityData.m_135353_(EntitySeal.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float prevSwimAngle;
    public float prevBaskProgress;
    public float baskProgress;
    public float prevDigProgress;
    public float digProgress;
    public float prevBobbingProgress;
    public float bobbingProgress;
    public int revengeCooldown = 0;
    public UUID feederUUID = null;
    private int baskingTimer = 0;
    private int swimTimer = -1000;
    private int ticksSinceInWater = 0;
    private boolean isLandNavigator;
    public int fishFeedings = 0;

    protected EntitySeal(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
        this.switchNavigator(false);
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.SEAL_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.SEAL_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.SEAL_HURT.get();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 10.0).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22279_, (double)0.18f);
    }

    public static boolean canSealSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        Holder holder = worldIn.m_204166_(pos);
        if (!holder.m_203565_(Biomes.f_48211_) && !holder.m_203565_(Biomes.f_48172_)) {
            boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.SEAL_SPAWNS);
            return spawnBlock && worldIn.m_45524_(pos, 0) > 8;
        }
        return worldIn.m_45524_(pos, 0) > 8 && worldIn.m_8055_(pos.m_7495_()).m_60713_(Blocks.f_50126_);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new SealAIBask(this));
        this.f_21345_.m_25352_(1, (Goal)new BreathAirGoal((PathfinderMob)this));
        this.f_21345_.m_25352_(2, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(3, (Goal)new AnimalAIFindWater((PathfinderMob)this));
        this.f_21345_.m_25352_(3, (Goal)new AnimalAILeaveWater((PathfinderMob)this));
        this.f_21345_.m_25352_(4, (Goal)new AnimalAIHerdPanic((PathfinderMob)this, 1.6));
        this.f_21345_.m_25352_(5, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.0, true));
        this.f_21345_.m_25352_(6, (Goal)new SealAIDiveForItems(this));
        this.f_21345_.m_25352_(7, (Goal)new RandomSwimmingGoal((PathfinderMob)this, 1.0, 7));
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21345_.m_25352_(9, (Goal)new AvoidEntityGoal((PathfinderMob)this, EntityOrca.class, 20.0f, 1.3, 1.0));
        this.f_21345_.m_25352_(10, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_43938_(Stream.of(new Ingredient.TagValue(AMTagRegistry.SEAL_BREEDABLES), new Ingredient.TagValue(AMTagRegistry.SEAL_OFFERINGS))), false));
        this.f_21346_.m_25352_(1, (Goal)new NearestAttackableTargetGoal((Mob)this, EntityFlyingFish.class, 55, true, true, null));
        this.f_21346_.m_25352_(2, new CreatureAITargetItems((PathfinderMob)this, false));
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigatorWide((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new AquaticMoveController((PathfinderMob)this, 1.5f);
            this.f_21344_ = new SemiAquaticPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev) {
            int fleeTime;
            double range = 15.0;
            this.revengeCooldown = fleeTime = 100 + this.m_217043_().m_188503_(150);
            List list = this.m_9236_().m_45976_(this.getClass(), this.m_20191_().m_82377_(15.0, 7.5, 15.0));
            for (EntitySeal gaz : list) {
                gaz.revengeCooldown = fleeTime;
                gaz.setBasking(false);
            }
            this.setBasking(false);
        }
        return prev;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(SWIM_ANGLE, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(BASKING, (Object)false);
        this.f_19804_.m_135372_(DIGGING, (Object)false);
        this.f_19804_.m_135372_(ARCTIC, (Object)false);
        this.f_19804_.m_135372_(VARIANT, (Object)0);
        this.f_19804_.m_135372_(BOB_TICKS, (Object)0);
    }

    public boolean isTearsEasterEgg() {
        String s = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        return s != null && s.toLowerCase().contains("he was");
    }

    public void m_267651_(boolean flying) {
        float f1 = (float)Mth.m_184648_((double)(this.m_20185_() - this.f_19854_), (double)0.0, (double)(this.m_20189_() - this.f_19856_));
        float f2 = Math.min(f1 * (this.m_20069_() ? 4.0f : 48.0f), 1.0f);
        this.f_267362_.m_267566_(f2, 0.4f);
    }

    public float getSwimAngle() {
        return ((Float)this.f_19804_.m_135370_(SWIM_ANGLE)).floatValue();
    }

    public void setSwimAngle(float progress) {
        this.f_19804_.m_135381_(SWIM_ANGLE, (Object)Float.valueOf(progress));
    }

    public void m_8119_() {
        int bob;
        super.m_8119_();
        this.prevBaskProgress = this.baskProgress;
        this.prevDigProgress = this.digProgress;
        this.prevBobbingProgress = this.bobbingProgress;
        this.prevSwimAngle = this.getSwimAngle();
        boolean dig = this.isDigging() && this.m_20072_();
        float f2 = (float)(-((double)((float)this.m_20184_().f_82480_) * 57.2957763671875));
        if (this.m_20069_()) {
            this.m_146926_(f2 * 2.5f);
            if (this.isLandNavigator) {
                this.switchNavigator(false);
            }
        } else if (!this.isLandNavigator) {
            this.switchNavigator(true);
        }
        if (this.isBasking()) {
            if (this.baskProgress < 5.0f) {
                this.baskProgress += 1.0f;
            }
        } else if (this.baskProgress > 0.0f) {
            this.baskProgress -= 1.0f;
        }
        if (dig) {
            if (this.digProgress < 5.0f) {
                this.digProgress += 1.0f;
            }
        } else if (this.digProgress > 0.0f) {
            this.digProgress -= 1.0f;
        }
        if (dig && this.m_9236_().m_8055_(this.m_20099_()).m_60815_()) {
            BlockPos posit = this.m_20099_();
            BlockState understate = this.m_9236_().m_8055_(posit);
            for (int i = 0; i < 4 + this.f_19796_.m_188503_(2); ++i) {
                double particleX = (float)posit.m_123341_() + this.f_19796_.m_188501_();
                double particleY = (float)posit.m_123342_() + 1.0f;
                double particleZ = (float)posit.m_123343_() + this.f_19796_.m_188501_();
                double motX = this.f_19796_.m_188583_() * 0.02;
                double motY = 0.1f + this.f_19796_.m_188501_() * 0.2f;
                double motZ = this.f_19796_.m_188583_() * 0.02;
                this.m_9236_().m_7106_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123794_, understate), particleX, particleY, particleZ, motX, motY, motZ);
            }
        }
        if (!this.m_9236_().f_46443_) {
            if (this.isBasking()) {
                if (this.m_21188_() != null || this.m_27593_() || this.revengeCooldown > 0 || this.m_20072_() || this.m_5448_() != null || this.baskingTimer > 1000 && this.m_217043_().m_188503_(100) == 0) {
                    this.setBasking(false);
                }
            } else if (!(this.m_5448_() != null || this.m_27593_() || this.m_21188_() != null || this.revengeCooldown != 0 || this.isBasking() || this.baskingTimer != 0 || this.m_217043_().m_188503_(15) != 0 || this.m_20072_())) {
                this.setBasking(true);
            }
            if (this.revengeCooldown > 0) {
                --this.revengeCooldown;
            }
            if (this.revengeCooldown == 0 && this.m_21188_() != null) {
                this.m_6703_(null);
            }
            float threshold = 0.05f;
            if (this.m_20069_() && this.f_19859_ - this.m_146908_() > threshold) {
                this.setSwimAngle(this.getSwimAngle() + 2.0f);
            } else if (this.m_20069_() && this.f_19859_ - this.m_146908_() < -threshold) {
                this.setSwimAngle(this.getSwimAngle() - 2.0f);
            } else if (this.getSwimAngle() > 0.0f) {
                this.setSwimAngle(Math.max(this.getSwimAngle() - 10.0f, 0.0f));
            } else if (this.getSwimAngle() < 0.0f) {
                this.setSwimAngle(Math.min(this.getSwimAngle() + 10.0f, 0.0f));
            }
            this.setSwimAngle(Mth.m_14036_((float)this.getSwimAngle(), (float)-70.0f, (float)70.0f));
            this.baskingTimer = this.isBasking() ? ++this.baskingTimer : 0;
            if (this.m_20069_()) {
                ++this.swimTimer;
                this.ticksSinceInWater = 0;
            } else {
                ++this.ticksSinceInWater;
                --this.swimTimer;
            }
        }
        if ((bob = ((Integer)this.f_19804_.m_135370_(BOB_TICKS)).intValue()) > 0) {
            --bob;
            if (this.bobbingProgress < 5.0f) {
                this.bobbingProgress += 1.0f;
            }
            this.f_19804_.m_135381_(BOB_TICKS, (Object)bob);
        } else {
            if (this.bobbingProgress > 0.0f) {
                this.bobbingProgress -= 1.0f;
            }
            if (!this.m_9236_().f_46443_ && this.f_19796_.m_188503_(300) == 0 && !this.m_20069_() && this.revengeCooldown == 0) {
                bob = 20 + this.f_19796_.m_188503_(20);
                this.f_19804_.m_135381_(BOB_TICKS, (Object)bob);
            }
        }
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int variant) {
        this.f_19804_.m_135381_(VARIANT, (Object)variant);
    }

    public boolean isBasking() {
        return (Boolean)this.f_19804_.m_135370_(BASKING);
    }

    public void setBasking(boolean basking) {
        this.f_19804_.m_135381_(BASKING, (Object)basking);
    }

    public boolean isDigging() {
        return (Boolean)this.f_19804_.m_135370_(DIGGING);
    }

    public void setDigging(boolean digging) {
        this.f_19804_.m_135381_(DIGGING, (Object)digging);
    }

    public boolean isArctic() {
        return (Boolean)this.f_19804_.m_135370_(ARCTIC);
    }

    public void setArctic(boolean arctic) {
        this.f_19804_.m_135381_(ARCTIC, (Object)arctic);
    }

    public int m_6062_() {
        return 4800;
    }

    protected int m_7305_(int currentAir) {
        return this.m_6062_();
    }

    public int m_8132_() {
        return 1;
    }

    public int m_8085_() {
        return 1;
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData data, @Nullable CompoundTag dataTag) {
        int i;
        this.setArctic(this.isBiomeArctic((LevelAccessor)worldIn, this.m_20183_()));
        if (data instanceof SealGroupData) {
            i = ((SealGroupData)((Object)data)).variant;
        } else {
            i = this.f_19796_.m_188503_(2);
            data = new SealGroupData(i);
        }
        this.setVariant(i);
        this.m_20301_(this.m_6062_());
        this.m_146926_(0.0f);
        return super.m_6518_(worldIn, difficultyIn, reason, data, dataTag);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Arctic", this.isArctic());
        compound.m_128379_("Basking", this.isBasking());
        compound.m_128405_("BaskingTimer", this.baskingTimer);
        compound.m_128405_("SwimTimer", this.swimTimer);
        compound.m_128405_("FishFeedings", this.fishFeedings);
        compound.m_128405_("Variant", this.getVariant());
        if (this.feederUUID != null) {
            compound.m_128362_("FeederUUID", this.feederUUID);
        }
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setArctic(compound.m_128471_("Arctic"));
        this.setBasking(compound.m_128471_("Basking"));
        this.baskingTimer = compound.m_128451_("BaskingTimer");
        this.swimTimer = compound.m_128451_("SwimTimer");
        this.fishFeedings = compound.m_128451_("FishFeedings");
        if (compound.m_128403_("FeederUUID")) {
            this.feederUUID = compound.m_128342_("FeederUUID");
        }
        this.setVariant(compound.m_128451_("Variant"));
    }

    private boolean isBiomeArctic(LevelAccessor worldIn, BlockPos position) {
        return worldIn.m_204166_(position).m_203656_(AMTagRegistry.SPAWNS_WHITE_SEALS);
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_21515_() && this.m_20069_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_(0.9));
            if (this.m_5448_() == null) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, -0.005, 0.0));
            }
            if (this.isDigging()) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, -0.02, 0.0));
            }
        } else {
            super.m_7023_(travelVector);
        }
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.SEAL_BREEDABLES);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        EntitySeal seal = (EntitySeal)((EntityType)AMEntityRegistry.SEAL.get()).m_20615_((Level)serverWorld);
        seal.setArctic(this.isBiomeArctic((LevelAccessor)serverWorld, this.m_20183_()));
        return seal;
    }

    @Override
    public boolean shouldEnterWater() {
        return !this.shouldLeaveWater() && this.swimTimer <= -1000;
    }

    @Override
    public boolean shouldLeaveWater() {
        if (!this.m_20197_().isEmpty()) {
            return false;
        }
        if (this.m_5448_() != null && !this.m_5448_().m_20069_()) {
            return true;
        }
        return this.swimTimer > 600;
    }

    @Override
    public boolean shouldStopMoving() {
        return this.isBasking();
    }

    @Override
    public int getWaterSearchRange() {
        return 32;
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.SEAL_OFFERINGS) || stack.m_204117_(AMTagRegistry.SEAL_BREEDABLES);
    }

    @Override
    public void onGetItem(ItemEntity e) {
        if (e.m_32055_().m_204117_(AMTagRegistry.SEAL_OFFERINGS)) {
            ++this.fishFeedings;
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_11788_, this.m_6121_(), this.m_6100_());
            Entity itemThrower = e.m_19749_();
            if (this.fishFeedings >= 3) {
                if (itemThrower != null) {
                    this.feederUUID = itemThrower.m_20148_();
                }
                this.fishFeedings = 0;
            }
        } else {
            this.feederUUID = null;
        }
        this.m_5634_(10.0f);
    }

    @Override
    public void onPanic() {
    }

    @Override
    public boolean canPanic() {
        return !this.isBasking();
    }

    public static class SealGroupData
    extends AgeableMob.AgeableMobGroupData {
        public final int variant;

        SealGroupData(int variant) {
            super(true);
            this.variant = variant;
        }
    }
}
