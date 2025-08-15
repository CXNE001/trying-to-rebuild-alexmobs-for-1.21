/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.BreathAirGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.WitherSkeleton
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityBoneSerpentPart;
import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import com.github.alexthe666.alexsmobs.entity.EntityStraddleboard;
import com.github.alexthe666.alexsmobs.entity.ai.BoneSerpentAIFindLava;
import com.github.alexthe666.alexsmobs.entity.ai.BoneSerpentAIJump;
import com.github.alexthe666.alexsmobs.entity.ai.BoneSerpentAIMeleeJump;
import com.github.alexthe666.alexsmobs.entity.ai.BoneSerpentPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.LavaAndWaterAIRandomSwimming;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class EntityBoneSerpent
extends Monster {
    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID = SynchedEntityData.m_135353_(EntityBoneSerpent.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final Predicate<LivingEntity> NOT_RIDING_STRADDLEBOARD_FRIENDLY = entity -> entity.m_6084_() && (entity.m_20202_() == null || !(entity.m_20202_() instanceof EntityStraddleboard) || !((EntityStraddleboard)entity.m_20202_()).shouldSerpentFriend());
    private static final Predicate<EntityStraddleboard> STRADDLEBOARD_FRIENDLY = entity -> entity.m_20160_() && entity.shouldSerpentFriend();
    public int jumpCooldown = 0;
    private boolean isLandNavigator;
    private int boardCheckCooldown = 0;
    private EntityStraddleboard boardToBoast = null;

    protected EntityBoneSerpent(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.LAVA, 0.0f);
        this.switchNavigator(false);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.boneSeprentSpawnRolls, this.m_217043_(), spawnReasonIn) && super.m_5545_(worldIn, spawnReasonIn);
    }

    public int m_5792_() {
        return 1;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.BONE_SERPENT_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.BONE_SERPENT_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.BONE_SERPENT_HURT.get();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 25.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22281_, 5.0).m_22268_(Attributes.f_22279_, (double)1.45f);
    }

    public int m_6056_() {
        return 256;
    }

    public boolean m_7301_(MobEffectInstance potioneffectIn) {
        if (potioneffectIn.m_19544_() == MobEffects.f_19615_) {
            return false;
        }
        return super.m_7301_(potioneffectIn);
    }

    public MobType m_6336_() {
        return MobType.f_21641_;
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        if (worldIn.m_8055_(pos).m_60819_().m_205070_(FluidTags.f_13131_) || worldIn.m_8055_(pos).m_60819_().m_205070_(FluidTags.f_13132_)) {
            return 10.0f;
        }
        return this.m_20077_() ? Float.NEGATIVE_INFINITY : 0.0f;
    }

    public boolean m_6063_() {
        return false;
    }

    public boolean m_6573_(Player player) {
        return true;
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    public static boolean canBoneSerpentSpawn(EntityType<EntityBoneSerpent> p_234314_0_, LevelAccessor p_234314_1_, MobSpawnType p_234314_2_, BlockPos p_234314_3_, RandomSource p_234314_4_) {
        BlockPos.MutableBlockPos blockpos$mutable = p_234314_3_.m_122032_();
        do {
            blockpos$mutable.m_122173_(Direction.UP);
        } while (p_234314_1_.m_6425_((BlockPos)blockpos$mutable).m_205070_(FluidTags.f_13132_));
        return p_234314_1_.m_8055_((BlockPos)blockpos$mutable).m_60795_();
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new BreathAirGoal((PathfinderMob)this));
        this.f_21345_.m_25352_(0, (Goal)new BoneSerpentAIFindLava(this));
        this.f_21345_.m_25352_(1, (Goal)new BoneSerpentAIMeleeJump(this));
        this.f_21345_.m_25352_(2, (Goal)new BoneSerpentAIJump(this, 10));
        this.f_21345_.m_25352_(3, (Goal)new LavaAndWaterAIRandomSwimming((PathfinderMob)this, 1.0, 8));
        this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(5, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]).m_26044_(new Class[0]));
        if (!AMConfig.neutralBoneSerpents) {
            this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<Player>((Mob)this, Player.class, 10, true, false, NOT_RIDING_STRADDLEBOARD_FRIENDLY));
            this.f_21346_.m_25352_(3, new EntityAINearestTarget3D<AbstractVillager>((Mob)this, AbstractVillager.class, 10, true, false, NOT_RIDING_STRADDLEBOARD_FRIENDLY));
        }
        this.f_21346_.m_25352_(4, new EntityAINearestTarget3D<WitherSkeleton>((Mob)this, WitherSkeleton.class, 10, true, false, NOT_RIDING_STRADDLEBOARD_FRIENDLY));
        this.f_21346_.m_25352_(5, new EntityAINearestTarget3D<EntitySoulVulture>((Mob)this, EntitySoulVulture.class, 10, true, false, NOT_RIDING_STRADDLEBOARD_FRIENDLY));
    }

    public void m_7023_(Vec3 travelVector) {
        boolean liquid;
        boolean bl = liquid = this.m_20077_() || this.m_20069_();
        if (this.m_21515_() && liquid) {
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

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = this.m_6037_(this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new BoneSerpentMoveController(this);
            this.f_21344_ = new BoneSerpentPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getChildId() != null) {
            compound.m_128362_("ChildUUID", this.getChildId());
        }
    }

    public void m_6138_() {
        List entities = this.m_9236_().m_45933_((Entity)this, this.m_20191_().m_82363_(0.2, 0.0, 0.2));
        entities.stream().filter(entity -> !(entity instanceof EntityBoneSerpentPart) && entity.m_6094_()).forEach(entity -> entity.m_7334_((Entity)this));
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268671_) || source.m_276093_(DamageTypes.f_268722_) || source.m_276093_(DamageTypes.f_268612_) || source.m_276093_(DamageTypes.f_268546_) || source.m_269533_(DamageTypeTags.f_268745_) || super.m_6673_(source);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("ChildUUID")) {
            this.setChildId(compound.m_128342_("ChildUUID"));
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CHILD_UUID, Optional.empty());
    }

    @Nullable
    public UUID getChildId() {
        return ((Optional)this.f_19804_.m_135370_(CHILD_UUID)).orElse(null);
    }

    public void setChildId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(CHILD_UUID, Optional.ofNullable(uniqueId));
    }

    public Entity getChild() {
        UUID id = this.getChildId();
        if (id != null && !this.m_9236_().f_46443_) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return null;
    }

    public void m_8119_() {
        boolean ground;
        super.m_8119_();
        this.f_19817_ = false;
        boolean bl = ground = !this.m_20077_() && !this.m_20069_() && this.m_20096_();
        if (this.jumpCooldown > 0) {
            --this.jumpCooldown;
            float f2 = -((float)this.m_20184_().f_82480_ * 57.295776f);
            this.m_146926_(f2);
        }
        if (ground) {
            if (!this.isLandNavigator) {
                this.switchNavigator(true);
            }
        } else if (this.isLandNavigator) {
            this.switchNavigator(false);
        }
        if (!this.m_9236_().f_46443_) {
            Entity child = this.getChild();
            if (child == null) {
                Object partParent = this;
                int segments = 7 + this.m_217043_().m_188503_(8);
                for (int i = 0; i < segments; ++i) {
                    EntityBoneSerpentPart part = new EntityBoneSerpentPart((EntityType)AMEntityRegistry.BONE_SERPENT_PART.get(), (LivingEntity)partParent, 0.9f, 180.0f, 0.0f);
                    part.setParent((Entity)partParent);
                    part.setBodyIndex(i);
                    if (partParent == this) {
                        this.setChildId(part.m_20148_());
                    }
                    part.setInitialPartPos((Entity)this);
                    partParent = part;
                    if (i == segments - 1) {
                        part.setTail(true);
                    }
                    this.m_9236_().m_7967_((Entity)part);
                }
            }
            if (this.boardCheckCooldown <= 0) {
                this.boardCheckCooldown = 100 + this.f_19796_.m_188503_(150);
                List list = this.m_9236_().m_6443_(EntityStraddleboard.class, this.m_20191_().m_82377_(100.0, 15.0, 100.0), STRADDLEBOARD_FRIENDLY);
                EntityStraddleboard closestBoard = null;
                for (EntityStraddleboard board : list) {
                    if (closestBoard != null && !(this.m_20270_(closestBoard) > this.m_20270_(board))) continue;
                    closestBoard = board;
                }
                this.boardToBoast = closestBoard;
            } else {
                --this.boardCheckCooldown;
            }
            if (this.boardToBoast != null) {
                if (this.m_20270_(this.boardToBoast) > 200.0f) {
                    this.boardToBoast = null;
                } else {
                    if (this.jumpCooldown == 0 && (this.m_20077_() || this.m_20069_()) && this.m_20270_(this.boardToBoast) < 15.0f) {
                        float up = 0.7f + this.m_217043_().m_188501_() * 0.8f;
                        Vec3 vector3d1 = this.m_20154_();
                        this.m_20256_(this.m_20184_().m_82520_(vector3d1.m_7096_() * 0.6, (double)up, vector3d1.m_7098_() * 0.6));
                        this.m_21573_().m_26573_();
                        this.jumpCooldown = this.m_217043_().m_188503_(300) + 100;
                    }
                    if (this.m_20270_(this.boardToBoast) > 5.0f) {
                        this.m_21573_().m_5624_((Entity)this.boardToBoast, 1.5);
                    } else {
                        this.m_21573_().m_26573_();
                    }
                }
            }
        }
    }

    public boolean m_6040_() {
        return true;
    }

    static class BoneSerpentMoveController
    extends MoveControl {
        private final EntityBoneSerpent dolphin;

        public BoneSerpentMoveController(EntityBoneSerpent dolphinIn) {
            super((Mob)dolphinIn);
            this.dolphin = dolphinIn;
        }

        public void m_8126_() {
            if (this.dolphin.m_20069_() || this.dolphin.m_20077_()) {
                this.dolphin.m_20256_(this.dolphin.m_20184_().m_82520_(0.0, 0.005, 0.0));
            }
            if (this.f_24981_ == MoveControl.Operation.MOVE_TO && !this.dolphin.m_21573_().m_26571_()) {
                double d2;
                double d1;
                double d0 = this.f_24975_ - this.dolphin.m_20185_();
                double d3 = d0 * d0 + (d1 = this.f_24976_ - this.dolphin.m_20186_()) * d1 + (d2 = this.f_24977_ - this.dolphin.m_20189_()) * d2;
                if (d3 < 2.500000277905201E-7) {
                    this.f_24974_.m_21564_(0.0f);
                } else {
                    float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
                    this.dolphin.m_146922_(this.m_24991_(this.dolphin.m_146908_(), f, 10.0f));
                    this.dolphin.f_20883_ = this.dolphin.m_146908_();
                    this.dolphin.f_20885_ = this.dolphin.m_146908_();
                    float f1 = (float)(this.f_24978_ * this.dolphin.m_21133_(Attributes.f_22279_));
                    if (this.dolphin.m_20069_() || this.dolphin.m_20077_()) {
                        this.dolphin.m_7910_(f1 * 0.02f);
                        float f2 = -((float)(Mth.m_14136_((double)d1, (double)Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2)))) * 57.2957763671875));
                        f2 = Mth.m_14036_((float)Mth.m_14177_((float)f2), (float)-85.0f, (float)85.0f);
                        this.dolphin.m_20256_(this.dolphin.m_20184_().m_82520_(0.0, (double)this.dolphin.m_6113_() * d1 * 0.6, 0.0));
                        this.dolphin.m_146926_(this.m_24991_(this.dolphin.m_146909_(), f2, 1.0f));
                        float f3 = Mth.m_14089_((float)(this.dolphin.m_146909_() * ((float)Math.PI / 180)));
                        float f4 = Mth.m_14031_((float)(this.dolphin.m_146909_() * ((float)Math.PI / 180)));
                        this.dolphin.f_20902_ = f3 * f1;
                        this.dolphin.f_20901_ = -f4 * f1;
                    } else {
                        this.dolphin.m_7910_(f1 * 0.1f);
                    }
                }
            } else {
                this.dolphin.m_7910_(0.0f);
                this.dolphin.m_21570_(0.0f);
                this.dolphin.m_21567_(0.0f);
                this.dolphin.m_21564_(0.0f);
            }
        }
    }
}
