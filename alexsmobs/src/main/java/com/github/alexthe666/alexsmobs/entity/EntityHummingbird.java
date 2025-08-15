/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 *  javax.annotation.Nullable
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.ai.village.poi.PoiManager
 *  net.minecraft.world.entity.ai.village.poi.PoiManager$Occupancy
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.block.BlockHummingbirdFeeder;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.HummingbirdAIPollinate;
import com.github.alexthe666.alexsmobs.entity.ai.HummingbirdAIWander;
import com.github.alexthe666.alexsmobs.misc.AMPointOfInterestRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicates;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityHummingbird
extends Animal {
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.m_135353_(EntityHummingbird.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityHummingbird.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> CROPS_POLLINATED = SynchedEntityData.m_135353_(EntityHummingbird.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Optional<BlockPos>> FEEDER_POS = SynchedEntityData.m_135353_(EntityHummingbird.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    public float flyProgress;
    public float prevFlyProgress;
    public float movingProgress;
    public float prevMovingProgress;
    public int hummingStill = 0;
    public int pollinateCooldown = 0;
    public int sipCooldown = 0;
    private int loopSoundTick = 0;
    private boolean sippy;
    public float sipProgress;
    public float prevSipProgress;

    protected EntityHummingbird(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.f_21342_ = new FlightMoveController((Mob)this, 1.5f);
        this.m_21441_(BlockPathTypes.DANGER_FIRE, -1.0f);
        this.m_21441_(BlockPathTypes.WATER, -1.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 16.0f);
        this.m_21441_(BlockPathTypes.COCOA, -1.0f);
        this.m_21441_(BlockPathTypes.FENCE, -1.0f);
        this.m_21441_(BlockPathTypes.LEAVES, 0.0f);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.hummingbirdSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.HUMMINGBIRD_IDLE.get();
    }

    public int m_8100_() {
        return 60;
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.HUMMINGBIRD_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.HUMMINGBIRD_HURT.get();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 4.0).m_22268_(Attributes.f_22280_, 7.0).m_22268_(Attributes.f_22281_, 0.0).m_22268_(Attributes.f_22279_, (double)0.45f);
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.HUMMINGBIRD_BREEDABLES);
    }

    public int m_5792_() {
        return 7;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(2, (Goal)new TemptGoal((PathfinderMob)this, 1.0, Ingredient.m_204132_(AMTagRegistry.HUMMINGBIRD_BREEDABLES), false));
        this.f_21345_.m_25352_(3, (Goal)new FollowParentGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(4, (Goal)new AIUseFeeder(this));
        this.f_21345_.m_25352_(4, (Goal)new HummingbirdAIPollinate(this));
        this.f_21345_.m_25352_(5, (Goal)new HummingbirdAIWander(this, 16, 6, 15, 1.0f));
        this.f_21345_.m_25352_(6, (Goal)new FloatGoal((Mob)this));
    }

    protected void m_7355_(BlockPos pos, BlockState blockIn) {
    }

    protected PathNavigation m_6037_(Level worldIn) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation((Mob)this, worldIn){

            public boolean m_6342_(BlockPos pos) {
                return !this.f_26495_.m_8055_(pos.m_6625_(2)).m_60795_();
            }
        };
        flyingpathnavigator.m_26440_(false);
        flyingpathnavigator.m_7008_(false);
        flyingpathnavigator.m_26443_(true);
        return flyingpathnavigator;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected boolean makeFlySound() {
        return true;
    }

    protected float m_6431_(Pose poseIn, EntityDimensions sizeIn) {
        return this.m_6162_() ? sizeIn.f_20378_ * 0.5f : sizeIn.f_20378_ * 0.5f;
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        return worldIn.m_8055_(pos).m_60795_() ? 10.0f : 0.0f;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("Variant", this.getVariant());
        compound.m_128405_("CropsPollinated", this.getCropsPollinated());
        compound.m_128405_("PollinateCooldown", this.pollinateCooldown);
        BlockPos blockpos = this.getFeederPos();
        if (blockpos != null) {
            compound.m_128405_("HLPX", blockpos.m_123341_());
            compound.m_128405_("HLPY", blockpos.m_123342_());
            compound.m_128405_("HLPZ", blockpos.m_123343_());
        }
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setVariant(compound.m_128451_("Variant"));
        this.setCropsPollinated(compound.m_128451_("CropsPollinated"));
        this.pollinateCooldown = compound.m_128451_("PollinateCooldown");
        if (compound.m_128441_("HLPX")) {
            int i = compound.m_128451_("HLPX");
            int j = compound.m_128451_("HLPY");
            int k = compound.m_128451_("HLPZ");
            this.f_19804_.m_135381_(FEEDER_POS, Optional.of(new BlockPos(i, j, k)));
        } else {
            this.f_19804_.m_135381_(FEEDER_POS, Optional.empty());
        }
    }

    public BlockPos getFeederPos() {
        return ((Optional)this.f_19804_.m_135370_(FEEDER_POS)).orElse(null);
    }

    public void setFeederPos(BlockPos pos) {
        this.f_19804_.m_135381_(FEEDER_POS, Optional.ofNullable(pos));
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(FLYING, (Object)false);
        this.f_19804_.m_135372_(VARIANT, (Object)0);
        this.f_19804_.m_135372_(CROPS_POLLINATED, (Object)0);
        this.f_19804_.m_135372_(FEEDER_POS, Optional.empty());
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setVariant(this.m_217043_().m_188503_(3));
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private List<BlockPos> getNearbyFeeders(BlockPos blockpos, ServerLevel world, int range) {
        PoiManager pointofinterestmanager = world.m_8904_();
        Stream stream = pointofinterestmanager.m_27138_(poiTypeHolder -> poiTypeHolder.m_203565_(AMPointOfInterestRegistry.HUMMINGBIRD_FEEDER.getKey()), (Predicate)Predicates.alwaysTrue(), blockpos, range, PoiManager.Occupancy.ANY);
        return stream.collect(Collectors.toList());
    }

    public boolean isFlying() {
        return (Boolean)this.f_19804_.m_135370_(FLYING);
    }

    public void setFlying(boolean flying) {
        this.f_19804_.m_135381_(FLYING, (Object)flying);
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int variant) {
        this.f_19804_.m_135381_(VARIANT, (Object)variant);
    }

    public int getCropsPollinated() {
        return (Integer)this.f_19804_.m_135370_(CROPS_POLLINATED);
    }

    public void setCropsPollinated(int crops) {
        this.f_19804_.m_135381_(CROPS_POLLINATED, (Object)crops);
    }

    public void m_8119_() {
        boolean flag;
        super.m_8119_();
        Vec3 vector3d = this.m_20184_();
        boolean bl = flag = this.m_20184_().f_82479_ * this.m_20184_().f_82479_ + this.m_20184_().f_82481_ * this.m_20184_().f_82481_ >= 0.001;
        if (!this.m_20096_() && vector3d.f_82480_ < 0.0) {
            this.m_20256_(vector3d.m_82542_(1.0, 0.4, 1.0));
        }
        this.setFlying(true);
        this.m_20242_(true);
        if (this.isFlying() && this.flyProgress < 5.0f) {
            this.flyProgress += 1.0f;
        }
        if (!this.isFlying() && this.flyProgress > 0.0f) {
            this.flyProgress -= 1.0f;
        }
        if (this.sippy && this.sipProgress < 5.0f) {
            this.sipProgress += 1.0f;
        }
        if (!this.sippy && this.sipProgress > 0.0f) {
            this.sipProgress -= 1.0f;
        }
        if (this.sippy && this.sipProgress == 5.0f) {
            this.sippy = false;
        }
        if (flag && this.movingProgress < 5.0f) {
            this.movingProgress += 1.0f;
        }
        if (!flag && this.movingProgress > 0.0f) {
            this.movingProgress -= 1.0f;
        }
        this.hummingStill = this.m_20184_().m_82556_() < 1.0E-7 ? ++this.hummingStill : 0;
        if (this.pollinateCooldown > 0) {
            --this.pollinateCooldown;
        }
        if (this.sipCooldown > 0) {
            --this.sipCooldown;
        }
        if (this.loopSoundTick == 0) {
            this.m_5496_((SoundEvent)AMSoundRegistry.HUMMINGBIRD_LOOP.get(), this.m_6121_() * 0.33f, this.m_6100_());
        }
        ++this.loopSoundTick;
        if (this.loopSoundTick > 27) {
            this.loopSoundTick = 0;
        }
        this.prevFlyProgress = this.flyProgress;
        this.prevMovingProgress = this.movingProgress;
        this.prevSipProgress = this.sipProgress;
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id == 68) {
            if (this.getFeederPos() != null) {
                if (this.f_19796_.m_188501_() < 0.2f) {
                    double d2 = this.f_19796_.m_188583_() * 0.02;
                    double d0 = this.f_19796_.m_188583_() * 0.02;
                    double d1 = this.f_19796_.m_188583_() * 0.02;
                    this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123782_, (double)((float)this.getFeederPos().m_123341_() + 0.2f) + (double)(this.f_19796_.m_188501_() * 0.6f), (double)((float)this.getFeederPos().m_123342_() + 0.1f), (double)((float)this.getFeederPos().m_123343_() + 0.2f) + (double)(this.f_19796_.m_188501_() * 0.6f), d0, d1, d2);
                }
                this.sippy = true;
            }
        } else {
            super.m_7822_(id);
        }
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return (AgeableMob)((EntityType)AMEntityRegistry.HUMMINGBIRD.get()).m_20615_((Level)serverWorld);
    }

    public static <T extends Mob> boolean canHummingbirdSpawn(EntityType<EntityHummingbird> hummingbird, LevelAccessor worldIn, MobSpawnType reason, BlockPos p_223317_3_, RandomSource random) {
        BlockState blockstate = worldIn.m_8055_(p_223317_3_.m_7495_());
        return (blockstate.m_204336_(AMTagRegistry.HUMMINGBIRD_SPAWNS) || blockstate.m_60713_(Blocks.f_50016_)) && worldIn.m_45524_(p_223317_3_, 0) > 8;
    }

    public boolean canBlockBeSeen(BlockPos pos) {
        double x = (float)pos.m_123341_() + 0.5f;
        double y = (float)pos.m_123342_() + 0.5f;
        double z = (float)pos.m_123343_() + 0.5f;
        BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(new Vec3(this.m_20185_(), this.m_20186_() + (double)this.m_20192_(), this.m_20189_()), new Vec3(x, y, z), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        double dist = result.m_82450_().m_82531_(x, y, z);
        return dist <= 1.0 || result.m_6662_() == HitResult.Type.MISS;
    }

    private class AIUseFeeder
    extends Goal {
        int runCooldown = 0;
        private int idleAtFlowerTime = 0;
        private BlockPos localFeeder;

        public AIUseFeeder(EntityHummingbird entityHummingbird2) {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
        }

        public void m_8041_() {
            this.localFeeder = null;
            this.idleAtFlowerTime = 0;
        }

        public boolean m_8036_() {
            if (EntityHummingbird.this.sipCooldown > 0) {
                return false;
            }
            if (this.runCooldown > 0) {
                --this.runCooldown;
            } else {
                BlockPos feedPos = EntityHummingbird.this.getFeederPos();
                if (feedPos != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(feedPos))) {
                    this.localFeeder = feedPos;
                    return true;
                }
                List<BlockPos> beacons = EntityHummingbird.this.getNearbyFeeders(EntityHummingbird.this.m_20183_(), (ServerLevel)EntityHummingbird.this.m_9236_(), 64);
                BlockPos closest = null;
                for (BlockPos pos : beacons) {
                    if (closest != null && !(EntityHummingbird.this.m_20275_(closest.m_123341_(), closest.m_123342_(), closest.m_123343_()) > EntityHummingbird.this.m_20275_(pos.m_123341_(), pos.m_123342_(), pos.m_123343_())) || !this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(pos))) continue;
                    closest = pos;
                }
                if (closest != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(closest))) {
                    this.localFeeder = closest;
                    return true;
                }
            }
            this.runCooldown = 400 + EntityHummingbird.this.f_19796_.m_188503_(600);
            return false;
        }

        public boolean m_8045_() {
            return this.localFeeder != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder)) && EntityHummingbird.this.sipCooldown == 0;
        }

        public void m_8037_() {
            if (this.localFeeder != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder))) {
                if (EntityHummingbird.this.m_20186_() > (double)this.localFeeder.m_123342_() && !EntityHummingbird.this.m_20096_()) {
                    EntityHummingbird.this.m_21566_().m_6849_((double)((float)this.localFeeder.m_123341_() + 0.5f), (double)((float)this.localFeeder.m_123342_() + 0.1f), (double)((float)this.localFeeder.m_123343_() + 0.5f), 1.0);
                } else {
                    EntityHummingbird.this.m_21566_().m_6849_((double)(this.localFeeder.m_123341_() + EntityHummingbird.this.f_19796_.m_188503_(4) - 2), EntityHummingbird.this.m_20186_() + 1.0, (double)(this.localFeeder.m_123343_() + EntityHummingbird.this.f_19796_.m_188503_(4) - 2), 1.0);
                }
                Vec3 vec = Vec3.m_82514_((Vec3i)this.localFeeder, (double)0.1f);
                double dist = Mth.m_14116_((float)((float)EntityHummingbird.this.m_20238_(vec)));
                if (dist < 2.5 && EntityHummingbird.this.m_20186_() > (double)this.localFeeder.m_123342_()) {
                    EntityHummingbird.this.m_7618_(EntityAnchorArgument.Anchor.EYES, vec);
                    ++this.idleAtFlowerTime;
                    EntityHummingbird.this.setFeederPos(this.localFeeder);
                    EntityHummingbird.this.m_9236_().m_7605_((Entity)EntityHummingbird.this, (byte)68);
                    if (this.idleAtFlowerTime > 55) {
                        if (EntityHummingbird.this.getCropsPollinated() > 2 && EntityHummingbird.this.f_19796_.m_188503_(25) == 0 && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder))) {
                            EntityHummingbird.this.m_9236_().m_46597_(this.localFeeder, (BlockState)EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder).m_61124_((Property)BlockHummingbirdFeeder.CONTENTS, (Comparable)Integer.valueOf(0)));
                        }
                        EntityHummingbird.this.setCropsPollinated(EntityHummingbird.this.getCropsPollinated() + 1);
                        EntityHummingbird.this.sipCooldown = 120 + EntityHummingbird.this.f_19796_.m_188503_(1200);
                        EntityHummingbird.this.pollinateCooldown = Math.max(0, EntityHummingbird.this.pollinateCooldown / 3);
                        this.runCooldown = 400 + EntityHummingbird.this.f_19796_.m_188503_(600);
                        this.m_8041_();
                    }
                }
            }
        }

        public boolean isValidFeeder(BlockState state) {
            return state.m_60734_() instanceof BlockHummingbirdFeeder && (Integer)state.m_61143_((Property)BlockHummingbirdFeeder.CONTENTS) == 3;
        }
    }
}
