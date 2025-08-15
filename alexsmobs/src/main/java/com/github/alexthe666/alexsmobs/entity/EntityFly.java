/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.FlyingMoveControl
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.ai.util.AirAndWaterRandomPos
 *  net.minecraft.world.entity.ai.util.HoverRandomPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.FlyingAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Spider
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$TagValue
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityFly
extends Animal
implements FlyingAnimal {
    private int conversionTime = 0;
    private static final EntityDataAccessor<Boolean> NO_DESPAWN = SynchedEntityData.m_135353_(EntityFly.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);

    protected EntityFly(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
        this.f_21342_ = new FlyingMoveControl((Mob)this, 20, true);
        this.m_21441_(BlockPathTypes.DANGER_FIRE, -1.0f);
        this.m_21441_(BlockPathTypes.WATER, -1.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 16.0f);
        this.m_21441_(BlockPathTypes.COCOA, -1.0f);
        this.m_21441_(BlockPathTypes.FENCE, -1.0f);
    }

    protected void m_7355_(BlockPos pos, BlockState blockIn) {
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("NoFlyDespawn", this.isNoDespawn());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setNoDespawn(compound.m_128471_("NoFlyDespawn"));
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(NO_DESPAWN, (Object)false);
    }

    public boolean isNoDespawn() {
        return (Boolean)this.f_19804_.m_135370_(NO_DESPAWN);
    }

    public void setNoDespawn(boolean despawn) {
        this.f_19804_.m_135381_(NO_DESPAWN, (Object)despawn);
    }

    public static boolean canFlySpawn(EntityType<EntityFly> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return reason == MobSpawnType.SPAWNER || pos.m_123342_() > 63 && random.m_188503_(4) == 0 && worldIn.m_45524_(pos, 0) > 8 && worldIn.m_45517_(LightLayer.BLOCK, pos) == 0 && worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.FLY_SPAWNS);
    }

    public boolean m_6785_(double distanceToClosestPlayer) {
        return !this.m_8023_();
    }

    public boolean m_8023_() {
        return this.isNoDespawn() || this.m_8077_() || this.m_21523_() || super.m_8023_();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.flySpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public boolean isInNether() {
        return this.m_9236_().m_46472_() == Level.f_46429_ && !this.m_21525_();
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.FLY_IDLE.get();
    }

    public int m_8100_() {
        return 30;
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.FLY_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.FLY_HURT.get();
    }

    public int m_5792_() {
        return 2;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 2.0).m_22268_(Attributes.f_22280_, (double)0.8f).m_22268_(Attributes.f_22281_, 1.0).m_22268_(Attributes.f_22279_, 0.25);
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        return worldIn.m_8055_(pos).m_60795_() ? 10.0f : 0.0f;
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(2, (Goal)new TemptGoal((PathfinderMob)this, 1.25, Ingredient.m_43938_(Stream.of(new Ingredient.TagValue(AMTagRegistry.FLY_BREEDABLES), new Ingredient.TagValue(AMTagRegistry.FLY_FOODSTUFFS))), false));
        this.f_21345_.m_25352_(3, (Goal)new FollowParentGoal((Animal)this, 1.25));
        this.f_21345_.m_25352_(3, (Goal)new AvoidEntityGoal((PathfinderMob)this, Spider.class, 6.0f, 1.0, 1.2));
        this.f_21345_.m_25352_(4, (Goal)new AnnoyZombieGoal());
        this.f_21345_.m_25352_(5, (Goal)new WanderGoal());
        this.f_21345_.m_25352_(6, (Goal)new FloatGoal((Mob)this));
    }

    protected PathNavigation m_6037_(Level worldIn) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation((Mob)this, worldIn){

            public boolean m_6342_(BlockPos pos) {
                return !this.f_26495_.m_8055_(pos.m_7495_()).m_60795_();
            }
        };
        flyingpathnavigator.m_26440_(false);
        flyingpathnavigator.m_7008_(false);
        flyingpathnavigator.m_26443_(true);
        return flyingpathnavigator;
    }

    protected float m_6431_(Pose poseIn, EntityDimensions sizeIn) {
        return this.m_6162_() ? sizeIn.f_20378_ * 0.5f : sizeIn.f_20378_ * 0.5f;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        this.f_19789_ = 0.0f;
    }

    public void m_8119_() {
        super.m_8119_();
        if (this.m_6162_() && this.m_20192_() > this.m_20206_()) {
            this.m_6210_();
        }
        if (this.m_27593_() && !this.isNoDespawn()) {
            this.setNoDespawn(true);
        }
        if (this.isInNether()) {
            this.setNoDespawn(true);
            ++this.conversionTime;
            if (this.conversionTime > 300) {
                EntityCrimsonMosquito mosquito = (EntityCrimsonMosquito)((EntityType)AMEntityRegistry.CRIMSON_MOSQUITO.get()).m_20615_(this.m_9236_());
                mosquito.m_20359_((Entity)this);
                if (!this.m_9236_().f_46443_) {
                    mosquito.m_6518_((ServerLevelAccessor)this.m_9236_(), this.m_9236_().m_6436_(this.m_20183_()), MobSpawnType.CONVERSION, null, null);
                }
                this.m_9236_().m_7967_((Entity)mosquito);
                mosquito.onSpawnFromFly();
                this.m_142687_(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    public InteractionResult m_6071_(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack lvt_3_1_ = p_230254_1_.m_21120_(p_230254_2_);
        if (lvt_3_1_.m_204117_(AMTagRegistry.FLY_FOODSTUFFS)) {
            if (!p_230254_1_.m_7500_()) {
                lvt_3_1_.m_41774_(1);
            }
            this.setNoDespawn(true);
            this.m_5634_(2.0f);
            return InteractionResult.SUCCESS;
        }
        return super.m_6071_(p_230254_1_, p_230254_2_);
    }

    protected boolean makeFlySound() {
        return true;
    }

    public MobType m_6336_() {
        return MobType.f_21642_;
    }

    protected void m_203347_(TagKey<Fluid> fluidTag) {
        this.m_20256_(this.m_20184_().m_82520_(0.0, 0.01, 0.0));
    }

    @OnlyIn(value=Dist.CLIENT)
    public Vec3 m_7939_() {
        return new Vec3(0.0, (double)(0.5f * this.m_20192_()), (double)(this.m_20205_() * 0.2f));
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.FLY_BREEDABLES);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel level, AgeableMob parent) {
        EntityFly fly = (EntityFly)((EntityType)AMEntityRegistry.FLY.get()).m_20615_(this.m_9236_());
        fly.setNoDespawn(true);
        return fly;
    }

    public boolean m_29443_() {
        return true;
    }

    private class AnnoyZombieGoal
    extends Goal {
        protected final Sorter theNearestAttackableTargetSorter;
        protected final Predicate<? super Entity> targetEntitySelector;
        protected int executionChance = 8;
        protected boolean mustUpdate;
        private Entity targetEntity;
        private int cooldown = 0;

        AnnoyZombieGoal() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.theNearestAttackableTargetSorter = new Sorter((Entity)EntityFly.this);
            this.targetEntitySelector = new Predicate<Entity>(){

                public boolean apply(@Nullable Entity e) {
                    return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.FLY_TARGETS) && (!(e instanceof LivingEntity) || (double)((LivingEntity)e).m_21223_() >= 2.0);
                }
            };
        }

        public boolean m_8036_() {
            List list;
            if (EntityFly.this.m_20159_() || EntityFly.this.m_20160_()) {
                return false;
            }
            if (!this.mustUpdate) {
                long worldTime = EntityFly.this.m_9236_().m_46467_() % 10L;
                if (EntityFly.this.m_21216_() >= 100 && worldTime != 0L) {
                    return false;
                }
                if (EntityFly.this.m_217043_().m_188503_(this.executionChance) != 0 && worldTime != 0L) {
                    return false;
                }
            }
            if ((list = EntityFly.this.m_9236_().m_6443_(Entity.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector)).isEmpty()) {
                return false;
            }
            Collections.sort(list, this.theNearestAttackableTargetSorter);
            this.targetEntity = (Entity)list.get(0);
            this.mustUpdate = false;
            return true;
        }

        public boolean m_8045_() {
            return this.targetEntity != null;
        }

        public void m_8041_() {
            this.targetEntity = null;
        }

        public void m_8037_() {
            if (this.cooldown > 0) {
                --this.cooldown;
            }
            if (this.targetEntity != null) {
                if (EntityFly.this.m_21573_().m_26571_()) {
                    int i = EntityFly.this.m_217043_().m_188503_(3) - 1;
                    int k = EntityFly.this.m_217043_().m_188503_(3) - 1;
                    int l = (int)((double)(EntityFly.this.m_217043_().m_188503_(3) - 1) * Math.ceil(this.targetEntity.m_20206_()));
                    EntityFly.this.m_21573_().m_26519_(this.targetEntity.m_20185_() + (double)i, this.targetEntity.m_20186_() + (double)l, this.targetEntity.m_20189_() + (double)k, 1.0);
                }
                if (EntityFly.this.m_20280_(this.targetEntity) < 3.0) {
                    if (this.targetEntity instanceof LivingEntity && (double)((LivingEntity)this.targetEntity).m_21223_() > 2.0) {
                        if (this.cooldown == 0) {
                            this.targetEntity.m_6469_(EntityFly.this.m_269291_().m_269264_(), 1.0f);
                            this.cooldown = 100;
                        }
                    } else {
                        this.m_8041_();
                    }
                }
            }
        }

        protected double getTargetDistance() {
            return 16.0;
        }

        protected AABB getTargetableArea(double targetDistance) {
            Vec3 renderCenter = new Vec3(EntityFly.this.m_20185_() + 0.5, EntityFly.this.m_20186_() + 0.5, EntityFly.this.m_20189_() + 0.5);
            double renderRadius = 5.0;
            AABB aabb = new AABB(-renderRadius, -renderRadius, -renderRadius, renderRadius, renderRadius, renderRadius);
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

    class WanderGoal
    extends Goal {
        WanderGoal() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            return EntityFly.this.f_21344_.m_26571_() && EntityFly.this.f_19796_.m_188503_(3) == 0;
        }

        public boolean m_8045_() {
            return EntityFly.this.f_21344_.m_26572_();
        }

        public void m_8056_() {
            Vec3 vector3d = this.getRandomLocation();
            if (vector3d != null) {
                EntityFly.this.f_21344_.m_26536_(EntityFly.this.f_21344_.m_7864_(AMBlockPos.fromVec3(vector3d), 1), 1.0);
            }
        }

        @Nullable
        private Vec3 getRandomLocation() {
            Vec3 vec3 = EntityFly.this.m_20252_(0.0f);
            int i = 8;
            Vec3 vec32 = HoverRandomPos.m_148465_((PathfinderMob)EntityFly.this, (int)8, (int)7, (double)vec3.f_82479_, (double)vec3.f_82481_, (float)1.5707964f, (int)3, (int)1);
            return vec32 != null ? vec32 : AirAndWaterRandomPos.m_148357_((PathfinderMob)EntityFly.this, (int)8, (int)4, (int)-2, (double)vec3.f_82479_, (double)vec3.f_82481_, (double)1.5707963705062866);
        }
    }
}
