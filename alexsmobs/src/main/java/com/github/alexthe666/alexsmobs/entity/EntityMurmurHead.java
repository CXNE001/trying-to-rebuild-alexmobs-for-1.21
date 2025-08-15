/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.animal.FlyingAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityMurmur;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EntityMurmurHead
extends Monster
implements FlyingAnimal {
    private static final EntityDataAccessor<Optional<UUID>> BODY_UUID = SynchedEntityData.m_135353_(EntityMurmurHead.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Integer> BODY_ID = SynchedEntityData.m_135353_(EntityMurmurHead.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> PULLED_IN = SynchedEntityData.m_135353_(EntityMurmurHead.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> ANGRY = SynchedEntityData.m_135353_(EntityMurmurHead.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public double prevXHair;
    public double prevYHair;
    public double prevZHair;
    public double xHair;
    public double yHair;
    public double zHair;
    public float angerProgress;
    public float prevAngerProgress;
    private boolean prevLaunched = false;

    protected EntityMurmurHead(EntityType type, Level level) {
        super(type, level);
        this.f_21342_ = new MoveController();
    }

    protected EntityMurmurHead(EntityMurmur parent) {
        this((EntityType)AMEntityRegistry.MURMUR_HEAD.get(), parent.m_9236_());
        this.setBodyId(parent.m_20148_());
        this.doSpawnPositioning(parent);
    }

    protected PathNavigation m_6037_(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation((Mob)this, this.m_9236_());
        flyingpathnavigation.m_26440_(false);
        flyingpathnavigation.m_7008_(true);
        flyingpathnavigation.m_26443_(true);
        return flyingpathnavigation;
    }

    public int m_213860_() {
        return 0;
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new AttackGoal());
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(9, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<Player>((Mob)this, Player.class, 10, false, true, null));
        this.f_21346_.m_25352_(3, new EntityAINearestTarget3D<AbstractVillager>((Mob)this, AbstractVillager.class, 30, false, true, null));
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(BODY_UUID, Optional.empty());
        this.f_19804_.m_135372_(BODY_ID, (Object)-1);
        this.f_19804_.m_135372_(PULLED_IN, (Object)true);
        this.f_19804_.m_135372_(ANGRY, (Object)false);
    }

    private void doSpawnPositioning(EntityMurmur parent) {
        this.m_146884_(parent.getNeckBottom(1.0f).m_82520_(0.0, 0.5, 0.0));
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 30.0).m_22268_(Attributes.f_22277_, 48.0).m_22268_(Attributes.f_22281_, 3.0).m_22268_(Attributes.f_22279_, (double)0.2f);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean m_20068_() {
        return true;
    }

    public boolean isPulledIn() {
        return (Boolean)this.f_19804_.m_135370_(PULLED_IN);
    }

    public void setPulledIn(boolean pulledIn) {
        this.f_19804_.m_135381_(PULLED_IN, (Object)pulledIn);
    }

    public boolean isAngry() {
        return (Boolean)this.f_19804_.m_135370_(ANGRY) != false || !this.m_6084_();
    }

    public void setAngry(boolean angry) {
        this.f_19804_.m_135381_(ANGRY, (Object)angry);
    }

    public Vec3 getNeckTop(float partialTick) {
        double d0 = Mth.m_14139_((double)partialTick, (double)this.f_19854_, (double)this.m_20185_());
        double d1 = Mth.m_14139_((double)partialTick, (double)this.f_19855_, (double)this.m_20186_());
        double d2 = Mth.m_14139_((double)partialTick, (double)this.f_19856_, (double)this.m_20189_());
        double bounce = 0.0;
        Entity body = this.getBody();
        if (body instanceof EntityMurmur) {
            bounce = ((EntityMurmur)body).calculateWalkBounce(partialTick);
        }
        return new Vec3(d0, d1 + bounce, d2);
    }

    public Vec3 getNeckBottom(float partialTick) {
        Entity body = this.getBody();
        Vec3 top = this.getNeckTop(partialTick);
        if (body instanceof EntityMurmur) {
            EntityMurmur murmur = (EntityMurmur)body;
            Vec3 bodyBase = murmur.getNeckBottom(partialTick);
            double sub = top.m_82546_(bodyBase).m_165924_();
            return sub <= 0.06 ? new Vec3(top.f_82479_, bodyBase.f_82480_, top.f_82481_) : bodyBase;
        }
        return top.m_82520_(0.0, -0.5, 0.0);
    }

    public boolean hasNeckBottom() {
        return true;
    }

    public MobType m_6336_() {
        return MobType.f_21641_;
    }

    @Nullable
    public UUID getBodyId() {
        return ((Optional)this.f_19804_.m_135370_(BODY_UUID)).orElse(null);
    }

    public void setBodyId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(BODY_UUID, Optional.ofNullable(uniqueId));
    }

    public Entity getBody() {
        if (!this.m_9236_().f_46443_) {
            UUID id = this.getBodyId();
            return id == null ? null : ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        int id = (Integer)this.f_19804_.m_135370_(BODY_ID);
        return id == -1 ? null : this.m_9236_().m_6815_(id);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("BodyUUID")) {
            this.setBodyId(compound.m_128342_("BodyUUID"));
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getBodyId() != null) {
            compound.m_128362_("BodyUUID", this.getBodyId());
        }
    }

    protected float m_6431_(Pose pose, EntityDimensions dimensions) {
        return dimensions.f_20378_ * 0.35f;
    }

    public void m_8119_() {
        super.m_8119_();
        this.f_20885_ = Mth.m_14036_((float)this.f_20885_, (float)(this.f_20883_ - 70.0f), (float)(this.f_20883_ + 70.0f));
        this.prevAngerProgress = this.angerProgress;
        if (this.isAngry() && this.angerProgress < 5.0f) {
            this.angerProgress += 1.0f;
        }
        if (!this.isAngry() && this.angerProgress > 0.0f) {
            this.angerProgress -= 1.0f;
        }
        this.moveHair();
        Entity body = this.getBody();
        if (!this.m_9236_().f_46443_) {
            if (body instanceof EntityMurmur) {
                EntityMurmur murmur = (EntityMurmur)body;
                this.f_19804_.m_135381_(BODY_ID, (Object)body.m_19879_());
                if (this.isPulledIn() && murmur.m_6084_()) {
                    Vec3 base = murmur.getNeckBottom(1.0f).m_82520_(0.0, (double)0.55f, 0.0);
                    Vec3 vec3 = base.m_82546_(this.m_20182_());
                    if (vec3.m_82553_() < 1.0) {
                        this.m_6034_(base.f_82479_, base.f_82480_, base.f_82481_);
                        this.f_19794_ = false;
                    } else {
                        this.f_19794_ = true;
                        vec3 = base.m_82546_(this.m_20182_()).m_82541_();
                        float f = this.m_5448_() != null && this.m_5448_().m_6084_() ? 0.3f : 0.15f;
                        this.m_20256_(vec3.m_82490_((double)f));
                    }
                    this.m_146922_(murmur.m_146908_());
                    this.f_20883_ = murmur.m_146908_();
                } else {
                    this.f_19794_ = false;
                }
                LivingEntity headTarget = this.m_5448_();
                LivingEntity bodyTarget = murmur.m_5448_();
                if (headTarget != null && headTarget.m_6084_()) {
                    if (murmur.m_6779_(headTarget)) {
                        murmur.m_6710_(headTarget);
                    } else {
                        this.m_6710_(null);
                        murmur.m_6710_(null);
                    }
                } else if (bodyTarget != null && bodyTarget.m_6084_() && this.m_6779_(bodyTarget)) {
                    this.m_6710_(bodyTarget);
                }
                if (body.m_213877_()) {
                    this.m_142687_(Entity.RemovalReason.DISCARDED);
                }
            }
            if (body == null && this.f_19797_ > 20) {
                this.m_142687_(Entity.RemovalReason.DISCARDED);
            }
        } else if (body instanceof EntityMurmur) {
            EntityMurmur murmur = (EntityMurmur)body;
            if (murmur.f_20916_ > 0 || murmur.f_20919_ > 0) {
                this.f_20916_ = murmur.f_20916_;
                this.f_20919_ = murmur.f_20919_;
            }
        }
        if (this.prevLaunched && !this.isPulledIn()) {
            this.m_5496_((SoundEvent)AMSoundRegistry.MURMUR_NECK.get(), 3.0f * this.m_6121_(), this.m_6100_());
        }
        this.prevLaunched = this.isPulledIn();
    }

    public boolean m_6063_() {
        return false;
    }

    public boolean m_6469_(DamageSource source, float damage) {
        Entity body = this.getBody();
        if (this.m_6673_(source)) {
            return false;
        }
        if (body != null && body.m_6469_(source, 0.5f * damage)) {
            return true;
        }
        return super.m_6469_(source, damage);
    }

    public boolean m_6673_(DamageSource damageSource) {
        return super.m_6673_(damageSource) || damageSource.m_276093_(DamageTypes.f_268612_);
    }

    private void moveHair() {
        this.prevXHair = this.xHair;
        this.prevYHair = this.yHair;
        this.prevZHair = this.zHair;
        double d0 = this.m_20185_() - this.xHair;
        double d1 = this.m_20186_() - this.yHair;
        double d2 = this.m_20189_() - this.zHair;
        if (d0 > 10.0) {
            this.prevXHair = this.xHair = this.m_20185_();
        }
        if (d2 > 10.0) {
            this.prevZHair = this.zHair = this.m_20189_();
        }
        if (d1 > 10.0) {
            this.prevYHair = this.yHair = this.m_20186_();
        }
        if (d0 < -10.0) {
            this.prevXHair = this.xHair = this.m_20185_();
        }
        if (d2 < -10.0) {
            this.prevZHair = this.zHair = this.m_20189_();
        }
        if (d1 < -10.0) {
            this.prevYHair = this.yHair = this.m_20186_();
        }
        this.xHair += d0 * 0.25;
        this.zHair += d2 * 0.25;
        this.yHair += d1 * 0.25;
    }

    public boolean m_7307_(Entity entity) {
        return this.getBodyId() != null && entity.m_20148_().equals(this.getBodyId()) || super.m_7307_(entity);
    }

    public void m_8032_() {
        if (this.isPulledIn() && !this.isAngry()) {
            super.m_8032_();
        }
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.MURMUR_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return this.getBody() == null ? (SoundEvent)AMSoundRegistry.MURMUR_HURT.get() : null;
    }

    protected SoundEvent m_5592_() {
        return this.getBody() == null ? (SoundEvent)AMSoundRegistry.MURMUR_HURT.get() : null;
    }

    public boolean m_29443_() {
        return true;
    }

    protected void m_7355_(BlockPos pos, BlockState blockIn) {
    }

    class MoveController
    extends MoveControl {
        private final Mob parentEntity;

        public MoveController() {
            super((Mob)EntityMurmurHead.this);
            this.parentEntity = EntityMurmurHead.this;
        }

        public void m_8126_() {
            if (EntityMurmurHead.this.isPulledIn()) {
                return;
            }
            float angle = (float)Math.PI / 180 * (this.parentEntity.f_20883_ + 90.0f);
            float radius = (float)Math.sin((float)this.parentEntity.f_19797_ * 0.2f) * 2.0f;
            double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
            double extraY = (double)radius * -Math.cos((double)angle - 1.5707963267948966);
            double extraZ = radius * Mth.m_14089_((float)angle);
            Vec3 strafPlus = new Vec3(extraX, extraY, extraZ);
            if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
                Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
                double d0 = vector3d.m_82553_();
                double width = this.parentEntity.m_20191_().m_82309_();
                Vec3 shimmy = Vec3.f_82478_;
                LivingEntity attackTarget = this.parentEntity.m_5448_();
                if (attackTarget != null && this.parentEntity.f_19862_) {
                    shimmy = new Vec3(0.0, 0.005, 0.0);
                }
                Vec3 vector3d1 = vector3d.m_82490_(this.f_24978_ * 0.05 / d0);
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d1.m_82549_(strafPlus.m_82490_(0.003 * Math.min(d0, 100.0)).m_82549_(shimmy))));
                if (attackTarget == null) {
                    if (d0 >= width) {
                        Vec3 deltaMovement = this.parentEntity.m_20184_();
                        this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)deltaMovement.f_82479_, (double)deltaMovement.f_82481_)) * 57.295776f);
                        this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                    }
                } else {
                    double d2 = attackTarget.m_20185_() - this.parentEntity.m_20185_();
                    double d1 = attackTarget.m_20189_() - this.parentEntity.m_20189_();
                    this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)d2, (double)d1)) * 57.295776f);
                    this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                }
            } else if (this.f_24981_ == MoveControl.Operation.WAIT) {
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(strafPlus.m_82490_(0.003)));
            }
        }
    }

    private class AttackGoal
    extends Goal {
        private int time;
        private int biteCooldown = 0;
        private Vec3 emergeFrom = Vec3.f_82478_;
        private float emergeAngle = 0.0f;

        public AttackGoal() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            return EntityMurmurHead.this.m_5448_() != null && EntityMurmurHead.this.m_5448_().m_6084_();
        }

        public void m_8056_() {
            this.time = 0;
            this.biteCooldown = 0;
            EntityMurmurHead.this.setPulledIn(false);
        }

        public void m_8041_() {
            this.time = 0;
            EntityMurmurHead.this.setPulledIn(true);
            EntityMurmurHead.this.setAngry(false);
        }

        public void m_8037_() {
            LivingEntity target = EntityMurmurHead.this.m_5448_();
            Entity body = EntityMurmurHead.this.getBody();
            if (target != null) {
                double bodyDist;
                double dist = Math.sqrt(EntityMurmurHead.this.m_20238_(target.m_146892_()));
                double d = bodyDist = body != null ? (double)body.m_20270_((Entity)target) : 0.0;
                if (bodyDist > 16.0 && this.time > 30 && body instanceof EntityMurmur) {
                    EntityMurmur murmur = (EntityMurmur)body;
                    murmur.m_6710_(target);
                    murmur.m_21573_().m_5624_((Entity)target, 1.35);
                }
                if (bodyDist > 64.0) {
                    EntityMurmurHead.this.setPulledIn(true);
                } else if (this.biteCooldown == 0) {
                    EntityMurmurHead.this.setPulledIn(false);
                    Vec3 moveTo = target.m_146892_();
                    if (this.time > 30) {
                        if (!EntityMurmurHead.this.isAngry()) {
                            EntityMurmurHead.this.m_5496_((SoundEvent)AMSoundRegistry.MURMUR_ANGER.get(), 1.5f * EntityMurmurHead.this.m_6121_(), EntityMurmurHead.this.m_6100_());
                            EntityMurmurHead.this.m_146850_(GameEvent.f_223709_);
                        }
                        EntityMurmurHead.this.setAngry(true);
                        EntityMurmurHead.this.m_21573_().m_26519_(moveTo.f_82479_, moveTo.f_82480_, moveTo.f_82481_, 1.3);
                    } else {
                        if (this.time == 0) {
                            this.emergeFrom = EntityMurmurHead.this.getNeckTop(1.0f).m_82520_(0.0, 0.5, 0.0);
                            Vec3 vec3 = moveTo.m_82546_(this.emergeFrom);
                        }
                        boolean clockwise = false;
                        float circleDistance = 2.5f;
                        float circlingTime = 30 * this.time;
                        float angle = (float)Math.PI / 180 * (clockwise ? -circlingTime : circlingTime);
                        double extraX = circleDistance * Mth.m_14031_((float)((float)Math.PI + angle));
                        double extraZ = circleDistance * Mth.m_14089_((float)angle);
                        double y = Math.max(this.emergeFrom.f_82480_ + 2.0, target.m_20188_());
                        Vec3 vec3 = new Vec3(this.emergeFrom.f_82479_ + extraX, y, this.emergeFrom.f_82481_ + extraZ);
                        EntityMurmurHead.this.m_21573_().m_26519_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_, 0.7);
                    }
                    EntityMurmurHead.this.m_7618_(EntityAnchorArgument.Anchor.EYES, moveTo);
                    if (dist < 1.5 && EntityMurmurHead.this.m_142582_((Entity)target)) {
                        EntityMurmurHead.this.m_5496_((SoundEvent)AMSoundRegistry.MURMUR_ATTACK.get(), EntityMurmurHead.this.m_6121_(), EntityMurmurHead.this.m_6100_());
                        this.biteCooldown = 5 + EntityMurmurHead.this.m_217043_().m_188503_(15);
                        target.m_6469_(EntityMurmurHead.this.m_269291_().m_269333_((LivingEntity)EntityMurmurHead.this), 5.0f);
                    }
                } else {
                    EntityMurmurHead.this.setPulledIn(true);
                    EntityMurmurHead.this.m_7618_(EntityAnchorArgument.Anchor.EYES, target.m_146892_());
                    EntityMurmurHead.this.setAngry(false);
                }
                ++this.time;
            }
            if (this.biteCooldown > 0) {
                --this.biteCooldown;
            }
        }
    }
}
