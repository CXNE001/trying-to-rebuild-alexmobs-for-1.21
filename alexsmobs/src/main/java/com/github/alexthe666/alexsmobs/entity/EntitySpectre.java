/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.FlyingAnimal
 *  net.minecraft.world.entity.decoration.LeashFenceKnotEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntitySpectre
extends Animal
implements FlyingAnimal {
    private static final EntityDataAccessor<Integer> CARDINAL_ORDINAL = SynchedEntityData.m_135353_(EntitySpectre.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public float birdPitch = 0.0f;
    public float prevBirdPitch = 0.0f;
    public Vec3 lurePos = null;

    protected EntitySpectre(EntityType type, Level world) {
        super(type, world);
        this.f_21342_ = new MoveHelperController(this);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.spectreSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canSpectreSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        BlockState blockstate = worldIn.m_8055_(pos.m_7495_());
        return true;
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.SPECTRE_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.SPECTRE_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.SPECTRE_HURT.get();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 50.0).m_22268_(Attributes.f_22277_, 64.0).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22279_, 1.0);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CARDINAL_ORDINAL, (Object)Direction.NORTH.m_122411_());
    }

    public int getCardinalInt() {
        return (Integer)this.f_19804_.m_135370_(CARDINAL_ORDINAL);
    }

    public void setCardinalInt(int command) {
        this.f_19804_.m_135381_(CARDINAL_ORDINAL, (Object)command);
    }

    public Direction getCardinalDirection() {
        return Direction.m_122376_((int)this.getCardinalInt());
    }

    public void setCardinalDirection(Direction dir) {
        this.setCardinalInt(dir.m_122411_());
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new TemptHeartGoal(this, 1.0, Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.SOUL_HEART.get()}), false));
        this.f_21345_.m_25352_(2, (Goal)new FlyGoal(this));
    }

    public boolean m_6673_(DamageSource source) {
        return !source.m_276093_(DamageTypes.f_268515_) && !source.m_276093_(DamageTypes.f_268724_) && !source.m_19390_() && !source.m_269533_(DamageTypeTags.f_268738_) || super.m_6673_(source);
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.m_146926_(0.0f);
        this.randomizeDirection();
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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

    public void m_8119_() {
        super.m_8119_();
        Vec3 vector3d1 = this.m_20184_();
        this.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
        this.f_20883_ = this.m_146908_();
        this.prevBirdPitch = this.birdPitch;
        this.f_19794_ = true;
        this.birdPitch = (float)(-((double)((float)this.m_20184_().f_82480_ * 0.5f) * 57.2957763671875));
        if (this.m_21524_() != null && !(this.m_21524_() instanceof LeashFenceKnotEntity)) {
            Entity entity = this.m_21524_();
            float f = this.m_20270_(entity);
            if (f > 10.0f) {
                double d0 = (this.m_20185_() - entity.m_20185_()) / (double)f;
                double d1 = (this.m_20186_() - entity.m_20186_()) / (double)f;
                double d2 = (this.m_20189_() - entity.m_20189_()) / (double)f;
                entity.m_20256_(entity.m_20184_().m_82520_(Math.copySign(d0 * d0 * 0.4, d0), Math.copySign(d1 * d1 * 0.4, d1), Math.copySign(d2 * d2 * 0.4, d2)));
            }
            entity.f_19789_ = 0.0f;
            if (entity.m_20184_().f_82480_ < 0.0) {
                entity.m_20256_(entity.m_20184_().m_82542_(1.0, (double)0.7f, 1.0));
            }
            if (entity.m_6144_()) {
                this.m_21455_(true, true);
            }
        }
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return null;
    }

    protected void m_6119_() {
        if (this.m_21524_() != null) {
            if (this.m_21524_().m_20159_() || this.m_21524_() instanceof LeashFenceKnotEntity) {
                super.m_6119_();
                return;
            }
            float f = this.m_20270_(this.m_21524_());
            if (f > 30.0f) {
                double lvt_3_1_ = (this.m_21524_().m_20185_() - this.m_20185_()) / (double)f;
                double lvt_5_1_ = (this.m_21524_().m_20186_() - this.m_20186_()) / (double)f;
                double lvt_7_1_ = (this.m_21524_().m_20189_() - this.m_20189_()) / (double)f;
                this.m_20256_(this.m_20184_().m_82520_(Math.copySign(lvt_3_1_ * lvt_3_1_ * 0.4, lvt_3_1_), Math.copySign(lvt_5_1_ * lvt_5_1_ * 0.4, lvt_5_1_), Math.copySign(lvt_7_1_ * lvt_7_1_ * 0.4, lvt_7_1_)));
            }
        }
        if (this.f_21359_ != null) {
            this.m_21528_();
        }
        if (!(this.m_21524_() == null || this.m_6084_() && this.m_21524_().m_6084_())) {
            this.m_21455_(true, true);
        }
    }

    private void randomizeDirection() {
        this.setCardinalInt(2 + this.f_19796_.m_188503_(3));
    }

    public boolean m_29443_() {
        return true;
    }

    static class MoveHelperController
    extends MoveControl {
        private final EntitySpectre parentEntity;

        public MoveHelperController(EntitySpectre sunbird) {
            super((Mob)sunbird);
            this.parentEntity = sunbird;
        }

        public void m_8126_() {
            if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
                Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
                double d5 = vector3d.m_82553_();
                if (d5 < 0.3) {
                    this.f_24981_ = MoveControl.Operation.WAIT;
                    this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82490_(0.5));
                } else {
                    double d0 = this.f_24975_ - this.parentEntity.m_20185_();
                    double d1 = this.f_24976_ - this.parentEntity.m_20186_();
                    double d2 = this.f_24977_ - this.parentEntity.m_20189_();
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

    static class TemptHeartGoal
    extends Goal {
        protected final EntitySpectre creature;
        private final TargetingConditions ENTITY_PREDICATE = TargetingConditions.m_148353_().m_26883_(64.0).m_26893_().m_148355_();
        private final double speed;
        private final Ingredient temptItem;
        protected Player closestPlayer;
        private int delayTemptCounter;

        public TemptHeartGoal(EntitySpectre p_i47822_1_, double p_i47822_2_, Ingredient p_i47822_4_, boolean p_i47822_5_) {
            this(p_i47822_1_, p_i47822_2_, p_i47822_5_, p_i47822_4_);
        }

        public TemptHeartGoal(EntitySpectre p_i47823_1_, double p_i47823_2_, boolean p_i47823_4_, Ingredient p_i47823_5_) {
            this.creature = p_i47823_1_;
            this.speed = p_i47823_2_;
            this.temptItem = p_i47823_5_;
        }

        public boolean m_8036_() {
            if (this.delayTemptCounter > 0) {
                --this.delayTemptCounter;
                return false;
            }
            this.closestPlayer = this.creature.m_9236_().m_45946_(this.ENTITY_PREDICATE, (LivingEntity)this.creature);
            if (this.closestPlayer == null || this.creature.m_21524_() == this.closestPlayer) {
                return false;
            }
            return this.isTempting(this.closestPlayer.m_21205_()) || this.isTempting(this.closestPlayer.m_21206_());
        }

        protected boolean isTempting(ItemStack p_188508_1_) {
            return this.temptItem.test(p_188508_1_);
        }

        public boolean m_8045_() {
            return this.m_8036_();
        }

        public void m_8056_() {
            this.creature.lurePos = this.closestPlayer.m_20182_();
        }

        public void m_8041_() {
            this.closestPlayer = null;
            this.delayTemptCounter = 100;
            this.creature.lurePos = null;
        }

        public void m_8037_() {
            this.creature.m_21563_().m_24960_((Entity)this.closestPlayer, (float)(this.creature.m_8085_() + 20), (float)this.creature.m_8132_());
            if (this.creature.m_20280_((Entity)this.closestPlayer) < 6.25) {
                this.creature.m_21573_().m_26573_();
            } else {
                this.creature.m_21566_().m_6849_(this.closestPlayer.m_20185_(), this.closestPlayer.m_20186_() + (double)this.closestPlayer.m_20192_(), this.closestPlayer.m_20189_(), this.speed);
            }
        }
    }

    private class FlyGoal
    extends Goal {
        private final EntitySpectre parentEntity;
        boolean island = false;
        float circlingTime = 0.0f;
        float circleDistance = 14.0f;
        float maxCirclingTime = 80.0f;
        boolean clockwise = false;
        private BlockPos target = null;
        private int islandCheckTime = 20;

        public FlyGoal(EntitySpectre sunbird) {
            this.parentEntity = sunbird;
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            if (this.parentEntity.lurePos != null) {
                return false;
            }
            MoveControl movementcontroller = this.parentEntity.m_21566_();
            this.clockwise = EntitySpectre.this.f_19796_.m_188499_();
            this.circleDistance = 5 + EntitySpectre.this.f_19796_.m_188503_(10);
            if (!movementcontroller.m_24995_() || this.target == null) {
                BlockPos blockPos = this.target = this.island ? this.getIslandPos(this.parentEntity.m_20183_()) : this.getBlockFromDirection();
                if (this.target != null) {
                    this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
                }
                return true;
            }
            return false;
        }

        public boolean m_8045_() {
            return this.parentEntity.lurePos == null;
        }

        public void m_8041_() {
            this.island = false;
            this.islandCheckTime = 0;
            this.circleDistance = 5 + EntitySpectre.this.f_19796_.m_188503_(10);
            this.circlingTime = 0.0f;
            this.clockwise = EntitySpectre.this.f_19796_.m_188499_();
            this.target = null;
        }

        public void m_8037_() {
            if (this.islandCheckTime-- <= 0) {
                this.islandCheckTime = 20;
                if (this.circlingTime == 0.0f) {
                    boolean bl = this.island = this.parentEntity.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.parentEntity.m_20183_()).m_123342_() > 2;
                    if (this.island) {
                        this.parentEntity.randomizeDirection();
                    }
                }
            }
            if (this.island) {
                this.circlingTime += 1.0f;
                if (this.circlingTime > 100.0f) {
                    this.island = false;
                    this.islandCheckTime = 1200;
                }
            } else if (this.circlingTime > 0.0f) {
                this.circlingTime -= 1.0f;
            }
            if (this.target == null) {
                BlockPos blockPos = this.target = this.island ? this.getIslandPos(this.parentEntity.m_20183_()) : this.getBlockFromDirection();
            }
            if (!this.island) {
                this.parentEntity.m_146922_(this.parentEntity.getCardinalDirection().m_122435_());
            }
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
                if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 5.5) {
                    this.target = null;
                }
            }
        }

        public BlockPos getBlockFromDirection() {
            float radius = 15.0f;
            BlockPos forwards = this.parentEntity.m_20183_().m_5484_(this.parentEntity.getCardinalDirection(), (int)Math.ceil(radius));
            int height = 0;
            height = EntitySpectre.this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, forwards).m_123342_() < 15 ? 70 + EntitySpectre.this.f_19796_.m_188503_(2) : EntitySpectre.this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, forwards).m_123342_() + 10 + EntitySpectre.this.f_19796_.m_188503_(10);
            return new BlockPos(forwards.m_123341_(), height, forwards.m_123343_());
        }

        public BlockPos getIslandPos(BlockPos orbit) {
            float angle = 0.05235988f * (this.clockwise ? -this.circlingTime : this.circlingTime);
            double extraX = this.circleDistance * Mth.m_14031_((float)angle);
            double extraZ = this.circleDistance * Mth.m_14089_((float)angle);
            int height = EntitySpectre.this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, orbit).m_123342_();
            if (height < 3) {
                this.island = false;
                return this.getBlockFromDirection();
            }
            return new BlockPos((int)((double)orbit.m_123341_() + extraX), Math.min(height + 10, orbit.m_123342_() + EntitySpectre.this.f_19796_.m_188503_(3) - EntitySpectre.this.f_19796_.m_188503_(1)), (int)((double)orbit.m_123343_() + extraZ));
        }
    }
}
