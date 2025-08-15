/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
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
import com.github.alexthe666.alexsmobs.entity.EntityAnaconda;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import com.github.alexthe666.alexsmobs.entity.util.AnacondaPartIndex;
import com.github.alexthe666.alexsmobs.message.MessageHurtMultipart;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.google.common.collect.ImmutableList;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EntityAnacondaPart
extends LivingEntity
implements IHurtableMultipart {
    private static final EntityDataAccessor<Integer> BODYINDEX = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> BODY_TYPE = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Float> TARGET_YAW = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Float> SWELL = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    public EntityDimensions multipartSize;
    private float strangleProgess;
    private float prevSwell;
    private float prevStrangleProgess;
    private int headEntityId = -1;
    private double prevHeight = 0.0;
    private static final EntityDataAccessor<Boolean> YELLOW = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SHEDDING = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> BABY = SynchedEntityData.m_135353_(EntityAnacondaPart.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);

    public EntityAnacondaPart(EntityType t, Level world) {
        super(t, world);
        this.multipartSize = t.m_20680_();
    }

    public EntityAnacondaPart(EntityType t, LivingEntity parent) {
        super(t, parent.m_9236_());
        this.setParent((Entity)parent);
    }

    public InteractionResult m_6096_(Player p_19978_, InteractionHand p_19979_) {
        return this.getParent() == null ? super.m_6096_(p_19978_, p_19979_) : this.getParent().m_6096_(p_19978_, p_19979_);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 10.0).m_22268_(Attributes.f_22279_, (double)0.15f);
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268612_) || super.m_6673_(source);
    }

    public boolean m_20068_() {
        return false;
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevStrangleProgess = this.strangleProgess;
        this.prevSwell = this.getSwell();
        this.f_19817_ = false;
        this.m_20256_(Vec3.f_82478_);
        if (this.f_19797_ > 1) {
            Entity parent = this.getParent();
            this.m_6210_();
            if (!this.m_9236_().f_46443_) {
                if (parent == null) {
                    this.m_142687_(Entity.RemovalReason.DISCARDED);
                }
                if (parent != null) {
                    if (parent instanceof LivingEntity) {
                        LivingEntity livingEntityParent = (LivingEntity)parent;
                        if (livingEntityParent.f_20916_ > 0 || livingEntityParent.f_20919_ > 0) {
                            AlexsMobs.sendMSGToAll(new MessageHurtMultipart(this.m_19879_(), parent.m_19879_(), 0.0f));
                            this.f_20916_ = livingEntityParent.f_20916_;
                            this.f_20919_ = livingEntityParent.f_20919_;
                        }
                    }
                    if (parent.m_213877_()) {
                        this.m_142687_(Entity.RemovalReason.DISCARDED);
                    }
                } else if (this.f_19797_ > 20) {
                    this.m_142687_(Entity.RemovalReason.DISCARDED);
                }
                if (this.getSwell() > 0.0f) {
                    float swellInc = 0.25f;
                    if (parent instanceof EntityAnaconda || parent instanceof EntityAnacondaPart && ((EntityAnacondaPart)parent).getSwell() == 0.0f) {
                        if (this.getChild() != null) {
                            EntityAnacondaPart child = (EntityAnacondaPart)this.getChild();
                            if (child.getPartType() == AnacondaPartIndex.TAIL) {
                                if (this.getSwell() == 0.25f) {
                                    this.feedAnaconda();
                                }
                            } else {
                                child.setSwell(child.getSwell() + 0.25f);
                            }
                        }
                        this.setSwell(this.getSwell() - 0.25f);
                    }
                }
            }
        }
    }

    private void feedAnaconda() {
        Entity e = this.getParent();
        while (e instanceof EntityAnacondaPart) {
            e = ((EntityAnacondaPart)e).getParent();
        }
        if (e instanceof EntityAnaconda) {
            ((EntityAnaconda)e).feed();
        }
    }

    public Vec3 tickMultipartPosition(int headId, AnacondaPartIndex parentIndex, Vec3 parentPosition, float parentXRot, float parentYRot, float ourYRot, boolean doHeight) {
        double hgt;
        Vec3 parentButt = parentPosition.m_82549_(this.calcOffsetVec(-parentIndex.getBackOffset() * this.m_6134_(), parentXRot, parentYRot));
        Vec3 ourButt = parentButt.m_82549_(this.calcOffsetVec((-this.getPartType().getBackOffset() - 0.5f * this.m_20205_()) * this.m_6134_(), this.m_146909_(), ourYRot));
        Vec3 avg = new Vec3((parentButt.f_82479_ + ourButt.f_82479_) / 2.0, (parentButt.f_82480_ + ourButt.f_82480_) / 2.0, (parentButt.f_82481_ + ourButt.f_82481_) / 2.0);
        double d0 = parentButt.f_82479_ - ourButt.f_82479_;
        double d2 = parentButt.f_82481_ - ourButt.f_82481_;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        double d = hgt = doHeight ? this.getLowPartHeight(parentButt.f_82479_, parentButt.f_82480_, parentButt.f_82481_) + this.getHighPartHeight(ourButt.f_82479_, ourButt.f_82480_, ourButt.f_82481_) : 0.0;
        if (Math.abs(hgt - this.prevHeight) > (double)0.2f) {
            this.prevHeight = hgt;
        }
        double partYDest = Mth.m_14008_((double)((double)this.m_6134_() * this.prevHeight), (double)-0.6f, (double)0.6f);
        float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
        float rawAngle = Mth.m_14177_((float)((float)(-(Mth.m_14136_((double)partYDest, (double)d3) * 57.2957763671875))));
        float f2 = this.limitAngle(this.m_146909_(), rawAngle, 10.0f);
        this.m_146926_(f2);
        this.m_146922_(f);
        this.f_20885_ = f;
        this.m_7678_(avg.f_82479_, avg.f_82480_, avg.f_82481_, f, f2);
        this.headEntityId = headId;
        return avg;
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

    public boolean isOpaqueBlockAt(double x, double y, double z) {
        if (this.f_19794_) {
            return false;
        }
        double d = 1.0;
        Vec3 vec3 = new Vec3(x, y, z);
        AABB axisAlignedBB = AABB.m_165882_((Vec3)vec3, (double)1.0, (double)1.0E-6, (double)1.0);
        return this.m_9236_().m_45556_(axisAlignedBB).filter(Predicate.not(BlockBehaviour.BlockStateBase::m_60795_)).anyMatch(p_185969_ -> {
            BlockPos blockpos = AMBlockPos.fromVec3(vec3);
            return p_185969_.m_60828_((BlockGetter)this.m_9236_(), blockpos) && Shapes.m_83157_((VoxelShape)p_185969_.m_60812_((BlockGetter)this.m_9236_(), blockpos).m_83216_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_), (VoxelShape)Shapes.m_83064_((AABB)axisAlignedBB), (BooleanOp)BooleanOp.f_82689_);
        });
    }

    public boolean m_6040_() {
        return true;
    }

    public boolean m_6063_() {
        return false;
    }

    public boolean isFluidAt(double x, double y, double z) {
        if (this.f_19794_) {
            return false;
        }
        return !this.m_9236_().m_6425_(AMBlockPos.fromCoords(x, y, z)).m_76178_();
    }

    public boolean hurtHeadId(DamageSource source, float f) {
        Entity e;
        if (this.headEntityId != -1 && (e = this.m_9236_().m_6815_(this.headEntityId)) instanceof EntityAnaconda) {
            return e.m_6469_(source, f);
        }
        return false;
    }

    public boolean m_6469_(DamageSource source, float damage) {
        return this.hurtHeadId(source, damage);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CHILD_UUID, Optional.empty());
        this.f_19804_.m_135372_(PARENT_UUID, Optional.empty());
        this.f_19804_.m_135372_(BODYINDEX, (Object)0);
        this.f_19804_.m_135372_(BODY_TYPE, (Object)AnacondaPartIndex.NECK.ordinal());
        this.f_19804_.m_135372_(TARGET_YAW, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(SWELL, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(YELLOW, (Object)false);
        this.f_19804_.m_135372_(SHEDDING, (Object)false);
        this.f_19804_.m_135372_(BABY, (Object)false);
    }

    public void m_6138_() {
        List entities = this.m_9236_().m_45933_((Entity)this, this.m_20191_().m_82363_(0.2, 0.0, 0.2));
        Entity parent = this.getParent();
        if (parent != null) {
            entities.stream().filter(entity -> !entity.m_7306_(parent) && !(entity instanceof EntityAnacondaPart) && !(entity instanceof EntityAnaconda) && entity.m_6094_()).forEach(entity -> entity.m_7334_(parent));
        }
    }

    public Iterable<ItemStack> m_6168_() {
        return ImmutableList.of();
    }

    public ItemStack m_6844_(EquipmentSlot slotIn) {
        return ItemStack.f_41583_;
    }

    public void m_8061_(EquipmentSlot p_21036_, ItemStack p_21037_) {
    }

    public HumanoidArm m_5737_() {
        return HumanoidArm.RIGHT;
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

    public Entity getParent() {
        UUID id;
        if (!this.m_9236_().f_46443_ && (id = this.getParentId()) != null) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return null;
    }

    public void setParent(Entity entity) {
        this.setParentId(entity.m_20148_());
    }

    @Nullable
    public UUID getParentId() {
        return ((Optional)this.f_19804_.m_135370_(PARENT_UUID)).orElse(null);
    }

    public void setParentId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(PARENT_UUID, Optional.ofNullable(uniqueId));
    }

    public Entity getChild() {
        UUID id;
        if (!this.m_9236_().f_46443_ && (id = this.getChildId()) != null) {
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

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getParentId() != null) {
            compound.m_128362_("ParentUUID", this.getParentId());
        }
        if (this.getChildId() != null) {
            compound.m_128362_("ChildUUID", this.getChildId());
        }
        compound.m_128405_("BodyModel", this.getPartType().ordinal());
        compound.m_128405_("BodyIndex", this.getBodyIndex());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("ParentUUID")) {
            this.setParentId(compound.m_128342_("ParentUUID"));
        }
        if (compound.m_128403_("ChildUUID")) {
            this.setChildId(compound.m_128342_("ChildUUID"));
        }
        this.setPartType(AnacondaPartIndex.fromOrdinal(compound.m_128451_("BodyModel")));
        this.setBodyIndex(compound.m_128451_("BodyIndex"));
    }

    public boolean m_7306_(Entity entity) {
        return this == entity || this.getParent() == entity;
    }

    public boolean m_6087_() {
        return true;
    }

    @Nullable
    public ItemStack m_142340_() {
        Entity parent = this.getParent();
        return parent != null ? parent.m_142340_() : ItemStack.f_41583_;
    }

    public int getBodyIndex() {
        return (Integer)this.f_19804_.m_135370_(BODYINDEX);
    }

    public void setBodyIndex(int index) {
        this.f_19804_.m_135381_(BODYINDEX, (Object)index);
    }

    public AnacondaPartIndex getPartType() {
        return AnacondaPartIndex.fromOrdinal((Integer)this.f_19804_.m_135370_(BODY_TYPE));
    }

    public void setPartType(AnacondaPartIndex index) {
        this.f_19804_.m_135381_(BODY_TYPE, (Object)index.ordinal());
    }

    public void setTargetYaw(float f) {
        this.f_19804_.m_135381_(TARGET_YAW, (Object)Float.valueOf(f));
    }

    public void setSwell(float f) {
        this.f_19804_.m_135381_(SWELL, (Object)Float.valueOf(f));
    }

    public float getSwell() {
        return Math.min(((Float)this.f_19804_.m_135370_(SWELL)).floatValue(), 5.0f);
    }

    public float getSwellLerp(float partialTick) {
        return this.prevSwell + (Math.max(this.getSwell(), 0.0f) - this.prevSwell) * partialTick;
    }

    public float m_146908_() {
        return super.m_146908_();
    }

    public void setStrangleProgress(float f) {
        this.strangleProgess = f;
    }

    public float getStrangleProgress(float partialTick) {
        return this.prevStrangleProgess + (this.strangleProgess - this.prevStrangleProgess) * partialTick;
    }

    public void copyDataFrom(EntityAnaconda anaconda) {
        this.f_19804_.m_135381_(YELLOW, (Object)anaconda.isYellow());
        this.f_19804_.m_135381_(SHEDDING, (Object)anaconda.isShedding());
        this.f_19804_.m_135381_(BABY, (Object)anaconda.m_6162_());
    }

    public boolean isYellow() {
        return (Boolean)this.f_19804_.m_135370_(YELLOW);
    }

    public boolean isShedding() {
        return (Boolean)this.f_19804_.m_135370_(SHEDDING);
    }

    public boolean m_6162_() {
        return (Boolean)this.f_19804_.m_135370_(BABY);
    }
}
