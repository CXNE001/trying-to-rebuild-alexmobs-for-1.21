/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
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
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import com.github.alexthe666.alexsmobs.message.MessageHurtMultipart;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EntityCentipedeBody
extends Mob
implements IHurtableMultipart {
    private static final EntityDataAccessor<Integer> BODYINDEX = SynchedEntityData.m_135353_(EntityCentipedeBody.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Float> BODY_XROT = SynchedEntityData.m_135353_(EntityCentipedeBody.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.m_135353_(EntityCentipedeBody.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID = SynchedEntityData.m_135353_(EntityCentipedeBody.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    public EntityDimensions multipartSize;
    protected float radius;
    protected float angleYaw;
    protected float damageMultiplier = 1.0f;
    private double prevHeight = 0.0;

    protected EntityCentipedeBody(EntityType type, Level worldIn) {
        super(type, worldIn);
        this.multipartSize = type.m_20680_();
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.getParent() != null;
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268612_) || super.m_6673_(source);
    }

    public MobType m_6336_() {
        return MobType.f_21642_;
    }

    public boolean m_20068_() {
        return false;
    }

    public void m_8119_() {
        super.m_8119_();
        this.f_19817_ = false;
        this.m_20256_(Vec3.f_82478_);
        if (this.f_19797_ > 1) {
            Entity parent = this.getParent();
            this.m_6210_();
            if (parent != null && !this.m_9236_().f_46443_) {
                if (parent instanceof LivingEntity) {
                    LivingEntity parentEntity = (LivingEntity)parent;
                    if (parentEntity.f_20916_ > 0 || parentEntity.f_20919_ > 0) {
                        AlexsMobs.sendMSGToAll(new MessageHurtMultipart(this.m_19879_(), parent.m_19879_(), 0.0f));
                        this.f_20916_ = parentEntity.f_20916_;
                        this.f_20919_ = parentEntity.f_20919_;
                    }
                }
                if (parent.m_213877_()) {
                    this.m_142687_(Entity.RemovalReason.DISCARDED);
                }
            } else if (!this.m_9236_().f_46443_ && this.f_19797_ > 20) {
                this.m_142687_(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    public EntityCentipedeBody(EntityType t, LivingEntity parent, float radius, float angleYaw, float offsetY) {
        super(t, parent.m_9236_());
        this.setParent((Entity)parent);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getParentId() != null) {
            compound.m_128362_("ParentUUID", this.getParentId());
        }
        if (this.getChildId() != null) {
            compound.m_128362_("ChildUUID", this.getChildId());
        }
        compound.m_128405_("BodyIndex", this.getBodyIndex());
        compound.m_128350_("PartAngle", this.angleYaw);
        compound.m_128350_("PartRadius", this.radius);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("ParentUUID")) {
            this.setParentId(compound.m_128342_("ParentUUID"));
        }
        if (compound.m_128403_("ChildUUID")) {
            this.setChildId(compound.m_128342_("ChildUUID"));
        }
        this.setBodyIndex(compound.m_128451_("BodyIndex"));
        this.angleYaw = compound.m_128457_("PartAngle");
        this.radius = compound.m_128457_("PartRadius");
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(PARENT_UUID, Optional.empty());
        this.f_19804_.m_135372_(CHILD_UUID, Optional.empty());
        this.f_19804_.m_135372_(BODYINDEX, (Object)0);
        this.f_19804_.m_135372_(BODY_XROT, (Object)Float.valueOf(0.0f));
    }

    public Entity getParent() {
        UUID id = this.getParentId();
        if (id != null && !this.m_9236_().f_46443_) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return null;
    }

    public void setParent(Entity entity) {
        this.setParentId(entity.m_20148_());
    }

    public Entity getChild() {
        UUID id = this.getChildId();
        if (id != null && !this.m_9236_().f_46443_) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return null;
    }

    @Nullable
    public UUID getChildId() {
        return ((Optional)this.f_19804_.m_135370_(CHILD_UUID)).orElse(null);
    }

    public void setChildId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(CHILD_UUID, Optional.ofNullable(uniqueId));
    }

    public boolean m_7306_(Entity entity) {
        return this == entity || this.getParent() == entity;
    }

    public boolean m_6469_(DamageSource source, float damage) {
        boolean prev;
        Entity parent = this.getParent();
        boolean bl = prev = parent != null && parent.m_6469_(source, damage * this.damageMultiplier);
        if (prev && !this.m_9236_().f_46443_) {
            AlexsMobs.sendMSGToAll(new MessageHurtMultipart(this.m_19879_(), parent.m_19879_(), damage * this.damageMultiplier));
        }
        return prev;
    }

    public boolean m_6087_() {
        return true;
    }

    public void m_6138_() {
        List entities = this.m_9236_().m_45933_((Entity)this, this.m_20191_().m_82363_(0.2, 0.0, 0.2));
        Entity parent = this.getParent();
        if (parent != null) {
            entities.stream().filter(entity -> entity != parent && !(entity instanceof EntityCentipedeBody) && entity.m_6094_()).forEach(entity -> entity.m_7334_(parent));
        }
    }

    public boolean m_20329_(Entity entityIn) {
        if (!(entityIn instanceof AbstractMinecart) && !(entityIn instanceof Boat)) {
            return super.m_20329_(entityIn);
        }
        return false;
    }

    public int getBodyIndex() {
        return (Integer)this.f_19804_.m_135370_(BODYINDEX);
    }

    public void setBodyIndex(int index) {
        this.f_19804_.m_135381_(BODYINDEX, (Object)index);
    }

    @Nullable
    public UUID getParentId() {
        return ((Optional)this.f_19804_.m_135370_(PARENT_UUID)).orElse(null);
    }

    public void setParentId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(PARENT_UUID, Optional.ofNullable(uniqueId));
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 10.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22284_, 6.0).m_22268_(Attributes.f_22281_, 8.0).m_22268_(Attributes.f_22278_, 0.5).m_22268_(Attributes.f_22279_, 0.25);
    }

    public Vec3 tickMultipartPosition(int headId, float parentOffset, Vec3 parentPosition, float parentXRot, float ourYRot, boolean doHeight) {
        double hgt;
        float yDif = doHeight ? 1.0f - 0.95f * (float)Math.min(Math.abs(parentPosition.f_82480_ - this.m_20186_()), 1.0) : 1.0f;
        Vec3 parentFront = parentPosition.m_82549_(this.calcOffsetVec(yDif * parentOffset * this.m_6134_(), parentXRot, ourYRot));
        Vec3 parentButt = parentPosition.m_82549_(this.calcOffsetVec(yDif * -parentOffset * this.m_6134_(), parentXRot, ourYRot));
        Vec3 ourButt = parentButt.m_82549_(this.calcOffsetVec((yDif * -this.getBackOffset() - 0.5f * this.m_20205_()) * this.m_6134_(), this.m_146909_(), ourYRot));
        Vec3 avg = new Vec3((parentButt.f_82479_ + ourButt.f_82479_) / 2.0, (parentButt.f_82480_ + ourButt.f_82480_) / 2.0, (parentButt.f_82481_ + ourButt.f_82481_) / 2.0);
        double d0 = parentButt.f_82479_ - ourButt.f_82479_;
        double d2 = parentButt.f_82481_ - ourButt.f_82481_;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        double d = hgt = doHeight ? this.getLowPartHeight(parentButt.f_82479_, parentButt.f_82480_, parentButt.f_82481_) + this.getHighPartHeight(ourButt.f_82479_, ourButt.f_82480_, ourButt.f_82481_) : 0.0;
        if (Math.abs(this.prevHeight - hgt) > 0.2) {
            this.prevHeight = hgt;
        }
        if (!this.isOpaqueBlockAt(parentFront.f_82479_, parentFront.f_82480_ + (double)0.4f, parentFront.f_82481_) && Math.abs(this.prevHeight) > 1.0) {
            this.prevHeight = 0.0;
        }
        double partYDest = Mth.m_14008_((double)this.prevHeight, (double)-0.4f, (double)0.4f);
        float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
        float rawAngle = Mth.m_14177_((float)((float)(-(Mth.m_14136_((double)partYDest, (double)d3) * 57.2957763671875))));
        float f2 = this.limitAngle(this.m_146909_(), rawAngle, 10.0f);
        this.m_146926_(f2);
        this.f_19804_.m_135381_(BODY_XROT, (Object)Float.valueOf(f2));
        this.m_146922_(f);
        this.f_20885_ = f;
        this.m_7678_(avg.f_82479_, avg.f_82480_, avg.f_82481_, f, f2);
        return avg;
    }

    public float m_146909_() {
        return ((Float)this.f_19804_.m_135370_(BODY_XROT)).floatValue();
    }

    public double getLowPartHeight(double x, double yIn, double z) {
        double checkAt;
        if (this.isFluidAt(x, yIn, z)) {
            return 0.0;
        }
        for (checkAt = 0.0; checkAt > -3.0 && !this.isOpaqueBlockAt(x, yIn + checkAt, z); checkAt -= 0.2) {
        }
        return checkAt;
    }

    public double getHighPartHeight(double x, double yIn, double z) {
        double checkAt;
        if (this.isFluidAt(x, yIn, z)) {
            return 0.0;
        }
        for (checkAt = 0.0; checkAt <= 3.0 && this.isOpaqueBlockAt(x, yIn + checkAt, z); checkAt += 0.2) {
        }
        return checkAt;
    }

    public boolean isFluidAt(double x, double y, double z) {
        if (this.f_19794_) {
            return false;
        }
        return !this.m_9236_().m_6425_(AMBlockPos.fromCoords(x, y, z)).m_76178_();
    }

    public boolean isOpaqueBlockAt(double x, double y, double z) {
        if (this.f_19794_) {
            return false;
        }
        float f = 1.0f;
        Vec3 vec3 = new Vec3(x, y, z);
        AABB axisalignedbb = AABB.m_165882_((Vec3)vec3, (double)1.0, (double)1.0E-6, (double)1.0);
        return this.m_9236_().m_45556_(axisalignedbb).filter(Predicate.not(BlockBehaviour.BlockStateBase::m_60795_)).anyMatch(p_185969_ -> {
            BlockPos blockpos = AMBlockPos.fromVec3(vec3);
            return p_185969_.m_60828_((BlockGetter)this.m_9236_(), blockpos) && Shapes.m_83157_((VoxelShape)p_185969_.m_60812_((BlockGetter)this.m_9236_(), blockpos).m_83216_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_), (VoxelShape)Shapes.m_83064_((AABB)axisalignedbb), (BooleanOp)BooleanOp.f_82689_);
        });
    }

    public boolean m_6040_() {
        return true;
    }

    public float getBackOffset() {
        return 0.5f;
    }

    @Override
    public void onAttackedFromServer(LivingEntity parent, float damage, DamageSource damageSource) {
        if (parent.f_20919_ > 0) {
            this.f_20919_ = parent.f_20919_;
        }
        if (parent.f_20916_ > 0) {
            this.f_20916_ = parent.f_20916_;
        }
    }
}
