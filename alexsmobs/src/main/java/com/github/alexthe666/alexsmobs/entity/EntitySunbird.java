/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.village.poi.PoiManager
 *  net.minecraft.world.entity.ai.village.poi.PoiManager$Occupancy
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.FlyingAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Phantom
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.entity.BeaconBlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMPointOfInterestRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicates;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntitySunbird
extends Animal
implements FlyingAnimal {
    public static final Predicate<? super Entity> SCORCH_PRED = new com.google.common.base.Predicate<Entity>(){

        public boolean apply(@Nullable Entity e) {
            return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.SUNBIRD_SCORCH_TARGETS);
        }
    };
    private static final EntityDataAccessor<Boolean> SCORCHING = SynchedEntityData.m_135353_(EntitySunbird.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float birdPitch = 0.0f;
    public float prevBirdPitch = 0.0f;
    private int beaconSearchCooldown = 50;
    private BlockPos beaconPos = null;
    private boolean orbitClockwise = false;
    private float prevScorchProgress;
    private float scorchProgress;
    private int fullScorchTime;

    protected EntitySunbird(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.f_21342_ = new MoveHelperController(this);
        this.orbitClockwise = new Random().nextBoolean();
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(SCORCHING, (Object)false);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 20.0).m_22268_(Attributes.f_22277_, 64.0).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22279_, 1.0);
    }

    public static boolean canSunbirdSpawn(EntityType<? extends Mob> typeIn, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn) {
        return true;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.sunbirdSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.SUNBIRD_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.SUNBIRD_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.SUNBIRD_HURT.get();
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(3, (Goal)new RandomFlyGoal(this));
        this.f_21345_.m_25352_(4, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 32.0f));
        this.f_21345_.m_25352_(5, (Goal)new RandomLookAroundGoal((Mob)this));
    }

    public float getBrightness() {
        return 1.0f;
    }

    public boolean m_20068_() {
        return true;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev) {
            if (source.m_7639_() != null && source.m_7639_() instanceof LivingEntity) {
                LivingEntity hurter = (LivingEntity)source.m_7639_();
                if (hurter.m_21023_((MobEffect)AMEffectRegistry.SUNBIRD_BLESSING.get())) {
                    hurter.m_21195_((MobEffect)AMEffectRegistry.SUNBIRD_BLESSING.get());
                }
                hurter.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.SUNBIRD_CURSE.get(), 600, 0));
            }
            return prev;
        }
        return prev;
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_20069_()) {
            this.m_19920_(0.02f, travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_((double)0.8f));
        } else if (this.m_20077_()) {
            this.m_19920_(0.02f, travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_(0.5));
        } else {
            BlockPos ground = AMBlockPos.fromCoords(this.m_20185_(), this.m_20186_() - 1.0, this.m_20189_());
            float f = 0.91f;
            if (this.m_20096_()) {
                f = this.m_9236_().m_8055_(ground).getFriction((LevelReader)this.m_9236_(), ground, (Entity)this) * 0.91f;
            }
            f = 0.91f;
            if (this.m_20096_()) {
                f = this.m_9236_().m_8055_(ground).getFriction((LevelReader)this.m_9236_(), ground, (Entity)this) * 0.91f;
            }
            this.m_267651_(true);
            this.m_19920_(0.2f, travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_((double)f));
        }
        this.m_267651_(false);
    }

    public void m_8119_() {
        float f2;
        super.m_8119_();
        this.prevBirdPitch = this.birdPitch;
        this.prevScorchProgress = this.scorchProgress;
        this.birdPitch = f2 = (float)(-((double)((float)this.m_20184_().f_82480_) * 57.2957763671875));
        if (this.m_9236_().f_46443_) {
            float radius = 0.35f + this.f_19796_.m_188501_() * 3.5f;
            float angle = (float)Math.PI / 180 * ((this.f_19796_.m_188499_() ? -85.0f : 85.0f) + this.f_20883_);
            float f = (float)Math.PI / 180 * this.f_20883_;
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            double extraXMotion = -0.2f * Mth.m_14031_((float)((float)(Math.PI + (double)f)));
            double extraZMotion = -0.2f * Mth.m_14089_((float)f);
            double yRandom = 0.2f + this.f_19796_.m_188501_() * 0.3f;
            this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.SUNBIRD_FEATHER.get(), this.m_20185_() + extraX, this.m_20186_() + yRandom, this.m_20189_() + extraZ, extraXMotion, 0.0, extraZMotion);
        } else {
            if (this.f_19797_ % 100 == 0) {
                if (!this.isScorching() && !this.getScorchingMobs().isEmpty()) {
                    this.setScorching(true);
                }
                List playerList = this.m_9236_().m_6443_(Player.class, this.getScorchArea(), (Predicate)Predicates.alwaysTrue());
                for (Player player : playerList) {
                    if (player.m_21023_((MobEffect)AMEffectRegistry.SUNBIRD_BLESSING.get()) || player.m_21023_((MobEffect)AMEffectRegistry.SUNBIRD_CURSE.get())) continue;
                    player.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.SUNBIRD_BLESSING.get(), 600, 0));
                }
            }
            if (this.beaconSearchCooldown > 0) {
                --this.beaconSearchCooldown;
            }
            if (this.beaconSearchCooldown <= 0) {
                this.beaconSearchCooldown = 100 + this.f_19796_.m_188503_(200);
                if (this.m_9236_() instanceof ServerLevel) {
                    List<BlockPos> beacons = this.getNearbyBeacons(this.m_20183_(), (ServerLevel)this.m_9236_(), 64);
                    BlockPos closest = null;
                    for (BlockPos pos : beacons) {
                        if (closest != null && !(this.m_20275_(closest.m_123341_(), closest.m_123342_(), closest.m_123343_()) > this.m_20275_(pos.m_123341_(), pos.m_123342_(), pos.m_123343_())) || !this.isValidBeacon(pos)) continue;
                        closest = pos;
                    }
                    if (closest != null && this.isValidBeacon(closest)) {
                        this.beaconPos = closest;
                    }
                }
                if (this.beaconPos != null && !this.isValidBeacon(this.beaconPos) && this.f_19797_ > 40) {
                    this.beaconPos = null;
                }
            }
        }
        boolean scorching = this.isScorching();
        if (scorching) {
            if (this.scorchProgress < 20.0f) {
                this.scorchProgress += 1.0f;
            }
        } else if (this.scorchProgress > 0.0f) {
            this.scorchProgress -= 1.0f;
        }
        if (scorching && this.scorchProgress == 20.0f && !this.m_9236_().f_46443_) {
            if (this.fullScorchTime > 30) {
                this.setScorching(false);
            } else if (this.fullScorchTime % 5 == 0) {
                for (Entity entity : this.getScorchingMobs()) {
                    entity.m_20254_(4);
                    if (!(entity instanceof Phantom)) continue;
                    ((Phantom)entity).m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.SUNBIRD_CURSE.get(), 200, 0));
                }
            }
            ++this.fullScorchTime;
        } else {
            this.fullScorchTime = 0;
        }
    }

    private List<LivingEntity> getScorchingMobs() {
        return this.m_9236_().m_6443_(LivingEntity.class, this.getScorchArea(), SCORCH_PRED);
    }

    public boolean isScorching() {
        return (Boolean)this.f_19804_.m_135370_(SCORCHING);
    }

    public void setScorching(boolean scorching) {
        this.f_19804_.m_135381_(SCORCHING, (Object)scorching);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128441_("BeaconPosX")) {
            int i = compound.m_128451_("BeaconPosX");
            int j = compound.m_128451_("BeaconPosY");
            int k = compound.m_128451_("BeaconPosZ");
            this.beaconPos = new BlockPos(i, j, k);
        } else {
            this.beaconPos = null;
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        BlockPos blockpos = this.beaconPos;
        if (blockpos != null) {
            compound.m_128405_("BeaconPosX", blockpos.m_123341_());
            compound.m_128405_("BeaconPosY", blockpos.m_123342_());
            compound.m_128405_("BeaconPosZ", blockpos.m_123343_());
        }
    }

    private AABB getScorchArea() {
        return this.m_20191_().m_82377_(15.0, 32.0, 15.0);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    private List<BlockPos> getNearbyBeacons(BlockPos blockpos, ServerLevel world, int range) {
        PoiManager pointofinterestmanager = world.m_8904_();
        Stream stream = pointofinterestmanager.m_27138_(poiTypeHolder -> poiTypeHolder.m_203565_(AMPointOfInterestRegistry.BEACON.getKey()), (Predicate)Predicates.alwaysTrue(), blockpos, range, PoiManager.Occupancy.ANY);
        return stream.collect(Collectors.toList());
    }

    private boolean isValidBeacon(BlockPos pos) {
        BlockEntity te = this.m_9236_().m_7702_(pos);
        return te instanceof BeaconBlockEntity && !((BeaconBlockEntity)te).m_58702_().isEmpty();
    }

    public boolean m_29443_() {
        return true;
    }

    public float getScorchProgress(float partialTick) {
        return (this.prevScorchProgress + (this.scorchProgress - this.prevScorchProgress) * partialTick) / 20.0f;
    }

    static class MoveHelperController
    extends MoveControl {
        private final EntitySunbird parentEntity;

        public MoveHelperController(EntitySunbird sunbird) {
            super((Mob)sunbird);
            this.parentEntity = sunbird;
        }

        public void m_8126_() {
            if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
                Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
                double d0 = vector3d.m_82553_();
                if (d0 < this.parentEntity.m_20191_().m_82309_()) {
                    this.f_24981_ = MoveControl.Operation.WAIT;
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82490_(0.5));
                } else {
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d.m_82490_(this.f_24978_ * 0.05 / d0)));
                    if (this.parentEntity.m_5448_() == null) {
                        Vec3 vector3d1 = this.parentEntity.m_20184_();
                        this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
                        this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                    } else {
                        double d2 = this.parentEntity.m_5448_().m_20185_() - this.parentEntity.m_20185_();
                        double d1 = this.parentEntity.m_5448_().m_20189_() - this.parentEntity.m_20189_();
                        this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)d2, (double)d1)) * 57.295776f);
                        this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                    }
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

    static class RandomFlyGoal
    extends Goal {
        private final EntitySunbird parentEntity;
        private BlockPos target = null;

        public RandomFlyGoal(EntitySunbird sunbird) {
            this.parentEntity = sunbird;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            MoveControl movementcontroller = this.parentEntity.m_21566_();
            if (!movementcontroller.m_24995_() || this.target == null) {
                this.target = this.parentEntity.beaconPos != null ? this.getBlockInViewBeacon(this.parentEntity.beaconPos, 5 + this.parentEntity.f_19796_.m_188503_(1)) : this.getBlockInViewSunbird();
                if (this.target != null) {
                    this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, this.parentEntity.beaconPos != null ? 0.8 : 1.0);
                }
                return true;
            }
            return false;
        }

        public boolean m_8045_() {
            return this.target != null && this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) > 2.4 && this.parentEntity.m_21566_().m_24995_() && !this.parentEntity.f_19862_;
        }

        public void m_8041_() {
            this.target = null;
        }

        public void m_8037_() {
            if (this.target == null) {
                this.target = this.parentEntity.beaconPos != null ? this.getBlockInViewBeacon(this.parentEntity.beaconPos, 5 + this.parentEntity.f_19796_.m_188503_(1)) : this.getBlockInViewSunbird();
            }
            if (this.parentEntity.beaconPos != null && this.parentEntity.f_19796_.m_188503_(100) == 0) {
                this.parentEntity.orbitClockwise = this.parentEntity.f_19796_.m_188499_();
            }
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, this.parentEntity.beaconPos != null ? 0.8 : 1.0);
                if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 2.5) {
                    this.target = null;
                }
            }
        }

        private BlockPos getBlockInViewBeacon(BlockPos orbitPos, float gatheringCircleDist) {
            float angle = 0.15707964f * (float)(this.parentEntity.orbitClockwise ? -this.parentEntity.f_19797_ : this.parentEntity.f_19797_);
            double extraX = gatheringCircleDist * Mth.m_14031_((float)angle);
            double extraZ = gatheringCircleDist * Mth.m_14089_((float)angle);
            if (orbitPos != null) {
                BlockPos pos = AMBlockPos.fromCoords((double)orbitPos.m_123341_() + extraX, orbitPos.m_123342_() + this.parentEntity.f_19796_.m_188503_(2) + 2, (double)orbitPos.m_123343_() + extraZ);
                if (this.parentEntity.m_9236_().m_46859_(new BlockPos((Vec3i)pos))) {
                    return pos;
                }
            }
            return null;
        }

        public BlockPos getBlockInViewSunbird() {
            float radius = -9.45f - (float)this.parentEntity.m_217043_().m_188503_(24);
            float neg = this.parentEntity.m_217043_().m_188499_() ? 1.0f : -1.0f;
            float renderYawOffset = this.parentEntity.f_20883_;
            float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.parentEntity.m_217043_().m_188501_() * neg;
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraZ = radius * Mth.m_14089_((float)angle);
            BlockPos radialPos = AMBlockPos.fromCoords(this.parentEntity.m_20185_() + extraX, 0.0, this.parentEntity.m_20189_() + extraZ);
            BlockPos ground = this.parentEntity.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, radialPos);
            int distFromGround = (int)this.parentEntity.m_20186_() - ground.m_123342_();
            int flightHeight = Math.max(ground.m_123342_(), 230 + this.parentEntity.m_217043_().m_188503_(40)) - ground.m_123342_();
            BlockPos newPos = radialPos.m_6630_(distFromGround > 16 ? flightHeight : (int)this.parentEntity.m_20186_() + this.parentEntity.m_217043_().m_188503_(16) + 1);
            if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 6.0) {
                return newPos;
            }
            return null;
        }
    }
}
