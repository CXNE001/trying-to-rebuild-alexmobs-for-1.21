/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntitySandShot;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class EntityGuster
extends Monster {
    private static final EntityDataAccessor<Integer> LIFT_ENTITY = SynchedEntityData.m_135353_(EntityGuster.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityGuster.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private LivingEntity liftedEntity;
    private int liftingTime = 0;
    private int maxLiftTime = 40;
    private int shootingTicks;
    public static final ResourceLocation RED_LOOT = new ResourceLocation("alexsmobs", "entities/guster_red");
    public static final ResourceLocation SOUL_LOOT = new ResourceLocation("alexsmobs", "entities/guster_soul");

    protected EntityGuster(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.m_274367_(1.0f);
        this.m_21441_(BlockPathTypes.WATER, -1.0f);
    }

    public int m_8100_() {
        return 80;
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.GUSTER_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.GUSTER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.GUSTER_HURT.get();
    }

    public boolean m_6126_() {
        return true;
    }

    @Nullable
    protected ResourceLocation m_7582_() {
        return this.getVariant() == 2 ? SOUL_LOOT : (this.getVariant() == 1 ? RED_LOOT : super.m_7582_());
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 16.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22281_, 1.0).m_22268_(Attributes.f_22279_, 0.2);
    }

    public static boolean canGusterSpawn(EntityType animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(BlockTags.f_13029_);
        return spawnBlock && (!AMConfig.limitGusterSpawnsToWeather || worldIn.m_6106_() != null && (worldIn.m_6106_().m_6534_() || worldIn.m_6106_().m_6533_()) || EntityGuster.isBiomeNether(worldIn, pos));
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.gusterSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new MeleeGoal());
        this.f_21345_.m_25352_(1, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 60, 1.0, 10, 7));
        this.f_21345_.m_25352_(2, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.f_21345_.m_25352_(2, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(3, (Goal)new NearestAttackableTargetGoal((Mob)this, AbstractVillager.class, true));
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new GroundPathNavigatorWide((Mob)this, worldIn);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected void m_7355_(BlockPos pos, BlockState blockIn) {
    }

    public void m_7324_(Entity entityIn) {
        if (this.getLiftedEntity() == null && this.liftingTime >= 0 && !(entityIn instanceof EntityGuster)) {
            this.setLiftedEntity(entityIn.m_19879_());
            this.maxLiftTime = 30 + this.f_19796_.m_188503_(30);
        }
    }

    public boolean hasLiftedEntity() {
        return (Integer)this.f_19804_.m_135370_(LIFT_ENTITY) != 0;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(LIFT_ENTITY, (Object)0);
        this.f_19804_.m_135372_(VARIANT, (Object)0);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (this.m_6673_(source)) {
            return false;
        }
        if (source.m_269533_(DamageTypeTags.f_268524_)) {
            amount = (amount + 1.0f) / 3.0f;
        }
        return super.m_6469_(source, amount);
    }

    private void spit(LivingEntity target) {
        EntitySandShot sghot = new EntitySandShot(this.m_9236_(), this);
        double d0 = target.m_20185_() - this.m_20185_();
        double d1 = target.m_20227_(0.3333333333333333) - sghot.m_20186_();
        double d2 = target.m_20189_() - this.m_20189_();
        float f = Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2))) * 0.35f;
        sghot.shoot(d0, d1 + (double)f, d2, 1.0f, 10.0f);
        sghot.setVariant(this.getVariant());
        if (!this.m_20067_()) {
            this.m_146850_(GameEvent.f_157778_);
            this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12331_, this.m_5720_(), 1.0f, 1.0f + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f);
        }
        this.m_9236_().m_7967_((Entity)sghot);
    }

    public double m_20188_() {
        return this.m_20186_() + 1.0;
    }

    @Nullable
    public Entity getLiftedEntity() {
        if (!this.hasLiftedEntity()) {
            return null;
        }
        return this.m_9236_().m_6815_(((Integer)this.f_19804_.m_135370_(LIFT_ENTITY)).intValue());
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (EntityGuster.isBiomeNether((LevelAccessor)worldIn, this.m_20183_())) {
            this.setVariant(2);
        } else if (EntityGuster.isBiomeRed((LevelAccessor)worldIn, this.m_20183_())) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }
        this.m_20301_(this.m_6062_());
        this.m_146926_(0.0f);
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private void setLiftedEntity(int p_175463_1_) {
        this.f_19804_.m_135381_(LIFT_ENTITY, (Object)p_175463_1_);
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int variant) {
        this.f_19804_.m_135381_(VARIANT, (Object)variant);
    }

    public void m_8107_() {
        super.m_8107_();
        Entity lifted = this.getLiftedEntity();
        if (lifted == null && !this.m_9236_().f_46443_ && this.f_19797_ % 15 == 0) {
            List list = this.m_9236_().m_45976_(ItemEntity.class, this.m_20191_().m_82400_((double)0.8f));
            ItemEntity closestItem = null;
            for (int i = 0; i < list.size(); ++i) {
                ItemEntity entity = (ItemEntity)list.get(i);
                if (!entity.m_20096_() || closestItem != null && !(this.m_20270_((Entity)closestItem) > this.m_20270_((Entity)entity))) continue;
                closestItem = entity;
            }
            if (closestItem != null) {
                this.setLiftedEntity(closestItem.m_19879_());
                this.maxLiftTime = 30 + this.f_19796_.m_188503_(30);
            }
        }
        float f = (float)this.m_20186_();
        if (this.m_6084_()) {
            ParticleOptions type = this.getVariant() == 2 ? (ParticleOptions)AMParticleRegistry.GUSTER_SAND_SPIN_SOUL.get() : (this.getVariant() == 1 ? (ParticleOptions)AMParticleRegistry.GUSTER_SAND_SPIN_RED.get() : (ParticleOptions)AMParticleRegistry.GUSTER_SAND_SPIN.get());
            for (int j = 0; j < 4; ++j) {
                float f1 = (this.f_19796_.m_188501_() * 2.0f - 1.0f) * this.m_20205_() * 0.95f;
                float f2 = (this.f_19796_.m_188501_() * 2.0f - 1.0f) * this.m_20205_() * 0.95f;
                this.m_9236_().m_7106_(type, this.m_20185_() + (double)f1, (double)f, this.m_20189_() + (double)f2, this.m_20185_(), this.m_20186_() + (double)(this.f_19796_.m_188501_() * this.m_20206_()) + (double)0.2f, this.m_20189_());
            }
        }
        if (lifted != null && this.liftingTime >= 0) {
            ++this.liftingTime;
            float resist = 1.0f;
            if (lifted instanceof LivingEntity) {
                resist = (float)Mth.m_14008_((double)(1.0 - ((LivingEntity)lifted).m_21133_(Attributes.f_22278_)), (double)0.0, (double)1.0);
            }
            float radius = 1.0f + (float)this.liftingTime * 0.05f;
            if (lifted instanceof ItemEntity) {
                radius = 0.2f + (float)this.liftingTime * 0.025f;
            }
            float angle = (float)this.liftingTime * -0.25f;
            double extraX = this.m_20185_() + (double)(radius * Mth.m_14031_((float)((float)Math.PI + angle)));
            double extraZ = this.m_20189_() + (double)(radius * Mth.m_14089_((float)angle));
            double d0 = (extraX - lifted.m_20185_()) * (double)resist;
            double d1 = (extraZ - lifted.m_20189_()) * (double)resist;
            lifted.m_20334_(d0, 0.1 * (double)resist, d1);
            lifted.f_19812_ = true;
            if (this.liftingTime > this.maxLiftTime) {
                this.setLiftedEntity(0);
                this.liftingTime = -20;
                this.maxLiftTime = 30 + this.f_19796_.m_188503_(30);
            }
        } else if (this.liftingTime < 0) {
            ++this.liftingTime;
        } else if (this.m_5448_() != null && this.m_20270_((Entity)this.m_5448_()) < this.m_20205_() + 1.0f && !(this.m_5448_() instanceof EntityGuster)) {
            this.setLiftedEntity(this.m_5448_().m_19879_());
            this.maxLiftTime = 30 + this.f_19796_.m_188503_(30);
        }
        if (!this.m_9236_().f_46443_ && this.shootingTicks >= 0) {
            if (this.shootingTicks <= 0) {
                if (this.m_5448_() != null && (lifted == null || lifted.m_19879_() != this.m_5448_().m_19879_()) && this.m_6084_()) {
                    this.spit(this.m_5448_());
                }
                this.shootingTicks = 40 + this.f_19796_.m_188503_(40);
            } else {
                --this.shootingTicks;
            }
        }
        Vec3 vector3d = this.m_20184_();
        if (!this.m_20096_() && vector3d.f_82480_ < 0.0) {
            this.m_20256_(vector3d.m_82542_(1.0, 0.6, 1.0));
        }
    }

    public boolean isGooglyEyes() {
        String s = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        return s != null && s.toLowerCase().contains("tweester");
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("Variant", this.getVariant());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setVariant(compound.m_128451_("Variant"));
    }

    private static boolean isBiomeRed(LevelAccessor worldIn, BlockPos position) {
        return worldIn.m_204166_(position).m_203656_(AMTagRegistry.SPAWNS_RED_GUSTERS);
    }

    private static boolean isBiomeNether(LevelAccessor worldIn, BlockPos position) {
        return worldIn.m_204166_(position).m_203656_(AMTagRegistry.SPAWNS_SOUL_GUSTERS);
    }

    public static int getColorForVariant(int variant) {
        if (variant == 2) {
            return 5127475;
        }
        if (variant == 1) {
            return 13000999;
        }
        return 15975305;
    }

    private class MeleeGoal
    extends Goal {
        public boolean m_8036_() {
            return EntityGuster.this.m_5448_() != null;
        }

        public void m_8037_() {
            Entity thrownEntity = EntityGuster.this.getLiftedEntity();
            if (EntityGuster.this.m_5448_() != null) {
                if (thrownEntity != null && thrownEntity.m_19879_() == EntityGuster.this.m_5448_().m_19879_()) {
                    EntityGuster.this.m_21573_().m_26573_();
                } else {
                    EntityGuster.this.m_21573_().m_5624_((Entity)EntityGuster.this.m_5448_(), 1.25);
                }
            }
        }
    }
}
