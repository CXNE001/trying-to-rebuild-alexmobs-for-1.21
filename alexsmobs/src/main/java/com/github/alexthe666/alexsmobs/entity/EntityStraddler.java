/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  com.google.common.collect.Sets
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.BlockTags
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
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Strider
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.vehicle.DismountHelper
 *  net.minecraft.world.level.CollisionGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.PathFinder
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraftforge.common.ForgeMod
 *  net.minecraftforge.fluids.FluidType
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityStradpole;
import com.github.alexthe666.alexsmobs.entity.ai.StraddlerAIShoot;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.google.common.collect.Sets;
import java.util.LinkedHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;

public class EntityStraddler
extends Monster
implements IAnimatedEntity {
    public static final Animation ANIMATION_LAUNCH = Animation.create((int)30);
    private static final EntityDataAccessor<Integer> STRADPOLE_COUNT = SynchedEntityData.m_135353_(EntityStraddler.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private int animationTick;
    private Animation currentAnimation;

    protected EntityStraddler(EntityType type, Level world) {
        super(type, world);
        this.m_21441_(BlockPathTypes.LAVA, 0.0f);
        this.m_21441_(BlockPathTypes.DANGER_FIRE, 0.0f);
        this.m_21441_(BlockPathTypes.DAMAGE_FIRE, 0.0f);
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.STRADDLER_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.STRADDLER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.STRADDLER_HURT.get();
    }

    public static boolean canStraddlerSpawn(EntityType animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(BlockTags.f_13062_);
        return spawnBlock;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 28.0).m_22268_(Attributes.f_22278_, 0.8).m_22268_(Attributes.f_22284_, 5.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22279_, (double)0.3f);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(STRADPOLE_COUNT, (Object)0);
    }

    public int getStradpoleCount() {
        return (Integer)this.f_19804_.m_135370_(STRADPOLE_COUNT);
    }

    public void setStradpoleCount(int index) {
        this.f_19804_.m_135381_(STRADPOLE_COUNT, (Object)index);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.straddlerSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new StraddlerAIShoot(this, 0.5, 30, 16.0f));
        this.f_21345_.m_25352_(7, (Goal)new RandomStrollGoal((PathfinderMob)this, 1.0, 60));
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, Strider.class, 8.0f));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(3, (Goal)new NearestAttackableTargetGoal((Mob)this, AbstractVillager.class, true));
    }

    protected void m_7840_(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
        this.m_20101_();
        if (this.m_20077_()) {
            this.f_19789_ = 0.0f;
        } else {
            super.m_7840_(p_184231_1_, p_184231_3_, p_184231_4_, p_184231_5_);
        }
    }

    public void m_7023_(Vec3 travelVector) {
        this.m_7910_((float)this.m_21133_(Attributes.f_22279_) * (this.getAnimation() == ANIMATION_LAUNCH ? 0.5f : 1.0f) * (this.m_20077_() ? 0.2f : 1.0f));
        if (this.m_21515_() && (this.m_20069_() || this.m_20077_())) {
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

    private void floatStrider() {
        if (this.m_20077_()) {
            CollisionContext lvt_1_1_ = CollisionContext.m_82750_((Entity)this);
            double d1 = this.getFluidTypeHeight((FluidType)ForgeMod.LAVA_TYPE.get());
            if (d1 <= 0.5 && d1 > 0.0) {
                if (this.m_20184_().f_82480_ < 0.0) {
                    this.m_20256_(this.m_20184_().m_82542_(1.0, 0.0, 1.0));
                }
                this.m_6853_(true);
            } else if (lvt_1_1_.m_6513_(LiquidBlock.f_54690_, this.m_20183_().m_7495_(), true) && !this.m_9236_().m_6425_(this.m_20183_().m_7494_()).m_205070_(FluidTags.f_13132_)) {
                this.m_6853_(true);
            } else {
                this.m_20334_(0.0, Math.min(d1 - 0.5, 1.0) * (double)0.2f, 0.0);
            }
        }
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    protected float m_6059_() {
        return this.f_19788_ + 0.6f;
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        if (worldIn.m_8055_(pos).m_60819_().m_205070_(FluidTags.f_13132_)) {
            return 10.0f;
        }
        return this.m_20077_() ? Float.NEGATIVE_INFINITY : 0.0f;
    }

    public Vec3 m_7688_(LivingEntity livingEntity) {
        Vec3[] avector3d = new Vec3[]{EntityStraddler.m_19903_((double)this.m_20205_(), (double)livingEntity.m_20205_(), (float)livingEntity.m_146908_()), EntityStraddler.m_19903_((double)this.m_20205_(), (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() - 22.5f)), EntityStraddler.m_19903_((double)this.m_20205_(), (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() + 22.5f)), EntityStraddler.m_19903_((double)this.m_20205_(), (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() - 45.0f)), EntityStraddler.m_19903_((double)this.m_20205_(), (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() + 45.0f))};
        LinkedHashSet set = Sets.newLinkedHashSet();
        double d0 = this.m_20191_().f_82292_;
        double d1 = this.m_20191_().f_82289_ - 0.5;
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        for (Vec3 vector3d : avector3d) {
            blockpos$mutable.m_122169_(this.m_20185_() + vector3d.f_82479_, d0, this.m_20189_() + vector3d.f_82481_);
            for (double d2 = d0; d2 > d1; d2 -= 1.0) {
                set.add(blockpos$mutable.m_7949_());
                blockpos$mutable.m_122173_(Direction.DOWN);
            }
        }
        for (BlockPos blockpos : set) {
            double d3;
            if (this.m_9236_().m_6425_(blockpos).m_205070_(FluidTags.f_13132_) || !DismountHelper.m_38439_((double)(d3 = this.m_9236_().m_45573_(blockpos)))) continue;
            Vec3 vector3d1 = Vec3.m_82514_((Vec3i)blockpos, (double)d3);
            for (Pose pose : livingEntity.m_7431_()) {
                AABB axisalignedbb = livingEntity.m_21270_(pose);
                if (!DismountHelper.m_38456_((CollisionGetter)this.m_9236_(), (LivingEntity)livingEntity, (AABB)axisalignedbb.m_82383_(vector3d1))) continue;
                livingEntity.m_20124_(pose);
                return vector3d1;
            }
        }
        return new Vec3(this.m_20185_(), this.m_20191_().f_82292_, this.m_20189_());
    }

    public boolean m_6060_() {
        return false;
    }

    public boolean canStandOnFluid(Fluid p_230285_1_) {
        return p_230285_1_.m_205067_(FluidTags.f_13132_);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("StradpoleCount", this.getStradpoleCount());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setStradpoleCount(compound.m_128451_("StradpoleCount"));
    }

    public void m_8119_() {
        super.m_8119_();
        this.floatStrider();
        this.m_20101_();
        if (this.getAnimation() == ANIMATION_LAUNCH && this.m_6084_() && this.getAnimationTick() == 2) {
            this.m_5496_(SoundEvents.f_11842_, 2.0f, 1.0f / (this.m_217043_().m_188501_() * 0.4f + 0.8f));
        }
        if (this.getAnimation() == ANIMATION_LAUNCH && this.m_6084_() && this.getAnimationTick() == 20 && this.m_5448_() != null) {
            EntityStradpole pole = (EntityStradpole)((EntityType)AMEntityRegistry.STRADPOLE.get()).m_20615_(this.m_9236_());
            pole.setParentId(this.m_20148_());
            pole.m_6034_(this.m_20185_(), this.m_20188_(), this.m_20189_());
            double d0 = this.m_5448_().m_20188_() - (double)1.1f;
            double d1 = this.m_5448_().m_20185_() - this.m_20185_();
            double d2 = d0 - pole.m_20186_();
            double d3 = this.m_5448_().m_20189_() - this.m_20189_();
            float f3 = Mth.m_14116_((float)((float)(d1 * d1 + d2 * d2 + d3 * d3))) * 0.2f;
            this.m_146850_(GameEvent.f_157778_);
            this.m_5496_(SoundEvents.f_11841_, 2.0f, 1.0f / (this.m_217043_().m_188501_() * 0.4f + 0.8f));
            pole.shoot(d1, d2 + (double)f3, d3, 2.0f, 0.0f);
            pole.m_146922_(this.m_146908_() % 360.0f);
            pole.m_146926_(Mth.m_14036_((float)this.m_146908_(), (float)-90.0f, (float)90.0f) % 360.0f);
            if (!this.m_9236_().f_46443_) {
                this.m_9236_().m_7967_((Entity)pole);
            }
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int i) {
        this.animationTick = i;
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_LAUNCH};
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new LavaPathNavigator(this, worldIn);
    }

    public boolean shouldShoot() {
        return true;
    }

    static class LavaPathNavigator
    extends GroundPathNavigation {
        LavaPathNavigator(EntityStraddler p_i231565_1_, Level p_i231565_2_) {
            super((Mob)p_i231565_1_, p_i231565_2_);
        }

        protected PathFinder m_5532_(int p_179679_1_) {
            this.f_26508_ = new WalkNodeEvaluator();
            return new PathFinder(this.f_26508_, p_179679_1_);
        }

        protected boolean m_7367_(BlockPathTypes p_230287_1_) {
            return p_230287_1_ == BlockPathTypes.LAVA || p_230287_1_ == BlockPathTypes.DAMAGE_FIRE || p_230287_1_ == BlockPathTypes.DANGER_FIRE || super.m_7367_(p_230287_1_);
        }

        public boolean m_6342_(BlockPos pos) {
            return this.f_26495_.m_8055_(pos).m_60713_(Blocks.f_49991_) || super.m_6342_(pos);
        }
    }
}
