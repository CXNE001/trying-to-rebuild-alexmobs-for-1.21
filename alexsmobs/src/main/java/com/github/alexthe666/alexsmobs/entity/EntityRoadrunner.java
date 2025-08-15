/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
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
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.PanicGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityRattlesnake;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class EntityRoadrunner
extends Animal {
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0f;
    public float wingRotation;
    public float destPos;
    public float prevAttackProgress;
    public float attackProgress;
    private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.m_135353_(EntityRoadrunner.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public int timeUntilNextFeather = this.f_19796_.m_188503_(24000) + 24000;
    private boolean hasMeepSpeed = false;

    protected EntityRoadrunner(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new PanicGoal((PathfinderMob)this, 1.1));
        this.f_21345_.m_25352_(1, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.0, false));
        this.f_21345_.m_25352_(2, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(4, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(4, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_204132_(AMTagRegistry.ROADRUNNER_BREEDABLES), false));
        this.f_21345_.m_25352_(5, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 50, 1.0, 25, 7));
        this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21345_.m_25352_(7, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new NearestAttackableTargetGoal((Mob)this, EntityRattlesnake.class, 55, true, true, null));
        this.f_21346_.m_25352_(2, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[]{EntityRattlesnake.class, Player.class}).m_26044_(new Class[0]));
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128441_("FeatherTime")) {
            this.timeUntilNextFeather = compound.m_128451_("FeatherTime");
        }
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.roadrunnerSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("FeatherTime", this.timeUntilNextFeather);
    }

    protected SoundEvent m_7515_() {
        return this.isMeep() || this.f_19796_.m_188503_(2000) == 0 ? (SoundEvent)AMSoundRegistry.ROADRUNNER_MEEP.get() : (SoundEvent)AMSoundRegistry.ROADRUNNER_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.ROADRUNNER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.ROADRUNNER_HURT.get();
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(ATTACK_TICK, (Object)0);
    }

    public boolean m_7327_(Entity entityIn) {
        this.f_19804_.m_135381_(ATTACK_TICK, (Object)5);
        return true;
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268585_) || source.m_19385_().equals("anvil") || super.m_6673_(source);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 8.0).m_22268_(Attributes.f_22281_, 1.0).m_22268_(Attributes.f_22279_, (double)0.45f).m_22268_(Attributes.f_22277_, 10.0);
    }

    public void m_8107_() {
        super.m_8107_();
        this.oFlap = this.wingRotation;
        this.prevAttackProgress = this.attackProgress;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.m_20096_() ? -1 : 4) * 0.3);
        this.destPos = Mth.m_14036_((float)this.destPos, (float)0.0f, (float)1.0f);
        if (!this.m_20096_() && this.wingRotDelta < 1.0f) {
            this.wingRotDelta = 1.0f;
        }
        if (!this.m_9236_().f_46443_ && this.m_6084_() && !this.m_6162_() && --this.timeUntilNextFeather <= 0) {
            this.m_19998_((ItemLike)AMItemRegistry.ROADRUNNER_FEATHER.get());
            this.timeUntilNextFeather = this.f_19796_.m_188503_(24000) + 24000;
        }
        this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9);
        Vec3 vector3d = this.m_20184_();
        if (!this.m_20096_() && vector3d.f_82480_ < 0.0) {
            this.m_20256_(vector3d.m_82542_(1.0, 0.8, 1.0));
        }
        this.wingRotation += this.wingRotDelta * 2.0f;
        if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) > 0) {
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
    }

    public void m_8119_() {
        super.m_8119_();
        if (this.isMeep()) {
            if (!this.hasMeepSpeed) {
                this.m_21051_(Attributes.f_22279_).m_22100_(1.0);
                this.hasMeepSpeed = true;
            }
        } else if (this.hasMeepSpeed) {
            this.m_21051_(Attributes.f_22279_).m_22100_((double)0.45f);
            this.hasMeepSpeed = false;
        }
        if (this.m_9236_().f_46443_ && this.isMeep() && this.m_20096_() && !this.m_20072_() && this.m_20184_().m_82556_() > 0.03) {
            Vec3 vector3d = this.m_20252_(0.0f);
            float yRotRad = this.m_146908_() * ((float)Math.PI / 180);
            float f = Mth.m_14089_((float)yRotRad) * 0.2f;
            float f1 = Mth.m_14031_((float)yRotRad) * 0.2f;
            float f2 = 1.2f - this.f_19796_.m_188501_() * 0.7f;
            for (int i = 0; i < 2; ++i) {
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123777_, this.m_20185_() - vector3d.f_82479_ * (double)f2 + (double)f, this.m_20186_() + (double)(this.f_19796_.m_188501_() * 0.2f), this.m_20189_() - vector3d.f_82481_ * (double)f2 + (double)f1, 0.0, 0.0, 0.0);
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123777_, this.m_20185_() - vector3d.f_82479_ * (double)f2 - (double)f, this.m_20186_() + (double)(this.f_19796_.m_188501_() * 0.2f), this.m_20189_() - vector3d.f_82481_ * (double)f2 - (double)f1, 0.0, 0.0, 0.0);
            }
        }
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected void m_7355_(BlockPos pos, BlockState blockIn) {
        if (!this.isMeep()) {
            this.m_5496_(SoundEvents.f_11754_, 0.15f, 1.0f);
        }
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.ROADRUNNER_BREEDABLES);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return (AgeableMob)((EntityType)AMEntityRegistry.ROADRUNNER.get()).m_20615_((Level)p_241840_1_);
    }

    public static boolean canRoadrunnerSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.ROADRUNNER_SPAWNS);
        return spawnBlock && worldIn.m_45524_(pos, 0) > 8;
    }

    public boolean isMeep() {
        String s = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        return s != null && s.toLowerCase().contains("meep") || AlexsMobs.isAprilFools();
    }
}
