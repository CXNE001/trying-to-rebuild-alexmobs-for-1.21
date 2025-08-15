/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 *  net.minecraft.world.entity.ai.goal.FollowBoatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
 *  net.minecraft.world.entity.ai.goal.TryFindWaterGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.animal.AbstractSchoolingFish
 *  net.minecraft.world.entity.animal.Squid
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.monster.Guardian
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.entity.ai.AquaticMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticPathNavigator;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FollowBoatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityHammerheadShark
extends WaterAnimal {
    private static final Predicate<LivingEntity> INJURED_PREDICATE = mob -> (double)mob.m_21223_() <= (double)mob.m_21233_() / 2.0;

    protected EntityHammerheadShark(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.f_21342_ = new AquaticMoveController((PathfinderMob)this, 1.0f);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.hammerheadSharkSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new SemiAquaticPathNavigator((Mob)this, worldIn);
    }

    protected SoundEvent m_5592_() {
        return SoundEvents.f_11759_;
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return SoundEvents.f_11761_;
    }

    public void m_7023_(Vec3 travelVector) {
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

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new TryFindWaterGoal((PathfinderMob)this));
        this.f_21345_.m_25352_(1, (Goal)new CirclePreyGoal(this, 1.0f));
        this.f_21345_.m_25352_(4, (Goal)new RandomSwimmingGoal((PathfinderMob)this, (double)0.6f, 7));
        this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(8, (Goal)new FollowBoatGoal((PathfinderMob)this));
        this.f_21345_.m_25352_(9, (Goal)new AvoidEntityGoal((PathfinderMob)this, Guardian.class, 8.0f, 1.0, 1.0));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<LivingEntity>((Mob)this, LivingEntity.class, 50, false, true, INJURED_PREDICATE));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<Squid>((Mob)this, Squid.class, 50, false, true, null));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<EntityMimicOctopus>((Mob)this, EntityMimicOctopus.class, 80, false, true, null));
        this.f_21346_.m_25352_(3, new EntityAINearestTarget3D<AbstractSchoolingFish>((Mob)this, AbstractSchoolingFish.class, 70, false, true, null));
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() == HitResult.Type.BLOCK;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 30.0).m_22268_(Attributes.f_22284_, 0.0).m_22268_(Attributes.f_22281_, 5.0).m_22268_(Attributes.f_22279_, 0.5);
    }

    public static <T extends Mob> boolean canHammerheadSharkSpawn(EntityType<EntityHammerheadShark> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        if (p_223364_3_.m_123342_() > 45 && p_223364_3_.m_123342_() < p_223364_1_.m_5736_()) {
            return p_223364_1_.m_6425_(p_223364_3_).m_205070_(FluidTags.f_13131_);
        }
        return false;
    }

    private static class CirclePreyGoal
    extends Goal {
        EntityHammerheadShark shark;
        float speed;
        float circlingTime = 0.0f;
        float circleDistance = 5.0f;
        float maxCirclingTime = 80.0f;
        boolean clockwise = false;

        public CirclePreyGoal(EntityHammerheadShark shark, float speed) {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            this.shark = shark;
            this.speed = speed;
        }

        public boolean m_8036_() {
            return this.shark.m_5448_() != null;
        }

        public boolean m_8045_() {
            return this.shark.m_5448_() != null;
        }

        public void m_8056_() {
            this.circlingTime = 0.0f;
            this.maxCirclingTime = 360 + this.shark.f_19796_.m_188503_(80);
            this.circleDistance = 5.0f + this.shark.f_19796_.m_188501_() * 5.0f;
            this.clockwise = this.shark.f_19796_.m_188499_();
        }

        public void m_8041_() {
            this.circlingTime = 0.0f;
            this.maxCirclingTime = 360 + this.shark.f_19796_.m_188503_(80);
            this.circleDistance = 5.0f + this.shark.f_19796_.m_188501_() * 5.0f;
            this.clockwise = this.shark.f_19796_.m_188499_();
        }

        public void m_8037_() {
            LivingEntity prey = this.shark.m_5448_();
            if (prey != null) {
                double dist = this.shark.m_20270_((Entity)prey);
                if (this.circlingTime >= this.maxCirclingTime) {
                    this.shark.m_21391_((Entity)prey, 30.0f, 30.0f);
                    this.shark.m_21573_().m_5624_((Entity)prey, 1.5);
                    if (dist < 2.0) {
                        this.shark.m_7327_((Entity)prey);
                        if (this.shark.f_19796_.m_188501_() < 0.3f) {
                            this.shark.m_19983_(new ItemStack((ItemLike)AMItemRegistry.SHARK_TOOTH.get()));
                        }
                        this.m_8041_();
                    }
                } else if (dist <= 25.0) {
                    this.circlingTime += 1.0f;
                    BlockPos circlePos = this.getSharkCirclePos(prey);
                    if (circlePos != null) {
                        this.shark.m_21573_().m_26519_((double)circlePos.m_123341_() + 0.5, (double)circlePos.m_123342_() + 0.5, (double)circlePos.m_123343_() + 0.5, 0.6);
                    }
                } else {
                    this.shark.m_21391_((Entity)prey, 30.0f, 30.0f);
                    this.shark.m_21573_().m_5624_((Entity)prey, 0.8);
                }
            }
        }

        public BlockPos getSharkCirclePos(LivingEntity target) {
            float angle = (float)Math.PI / 180 * (this.clockwise ? -this.circlingTime : this.circlingTime);
            double extraX = this.circleDistance * Mth.m_14031_((float)angle);
            double extraZ = this.circleDistance * Mth.m_14089_((float)angle);
            BlockPos ground = AMBlockPos.fromCoords(target.m_20185_() + 0.5 + extraX, this.shark.m_20186_(), target.m_20189_() + 0.5 + extraZ);
            if (this.shark.m_9236_().m_6425_(ground).m_205070_(FluidTags.f_13131_)) {
                return ground;
            }
            return null;
        }
    }
}
