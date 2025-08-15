/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.projectile.ProjectileUtil
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class EntityGust
extends Entity {
    protected static final EntityDataAccessor<Boolean> VERTICAL = SynchedEntityData.m_135353_(EntityGust.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    protected static final EntityDataAccessor<Float> X_DIR = SynchedEntityData.m_135353_(EntityGust.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    protected static final EntityDataAccessor<Float> Y_DIR = SynchedEntityData.m_135353_(EntityGust.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    protected static final EntityDataAccessor<Float> Z_DIR = SynchedEntityData.m_135353_(EntityGust.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private Entity pushedEntity = null;

    public EntityGust(EntityType p_i50162_1_, Level p_i50162_2_) {
        super(p_i50162_1_, p_i50162_2_);
    }

    public EntityGust(Level worldIn) {
        this((EntityType)AMEntityRegistry.GUST.get(), worldIn);
    }

    public EntityGust(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this((EntityType)AMEntityRegistry.GUST.get(), world);
    }

    public void m_7334_(Entity entityIn) {
    }

    protected static float lerpRotation(float p_234614_0_, float p_234614_1_) {
        while (p_234614_1_ - p_234614_0_ < -180.0f) {
            p_234614_0_ -= 360.0f;
        }
        while (p_234614_1_ - p_234614_0_ >= 180.0f) {
            p_234614_0_ += 360.0f;
        }
        return Mth.m_14179_((float)0.2f, (float)p_234614_0_, (float)p_234614_1_);
    }

    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public void m_8119_() {
        super.m_8119_();
        if (this.f_19797_ > 300) {
            this.m_142687_(Entity.RemovalReason.DISCARDED);
        }
        for (int i = 0; i < 1 + this.f_19796_.m_188503_(1); ++i) {
            this.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.GUSTER_SAND_SPIN.get(), this.m_20185_() + (double)(0.5f * (this.f_19796_.m_188501_() - 0.5f)), this.m_20186_() + (double)(0.5f * (this.f_19796_.m_188501_() - 0.5f)), this.m_20189_() + (double)(0.5f * (this.f_19796_.m_188501_() - 0.5f)), this.m_20185_(), this.m_20186_() + 0.5, this.m_20189_());
        }
        Vec3 vector3d = new Vec3((double)((Float)this.f_19804_.m_135370_(X_DIR)).floatValue(), (double)((Float)this.f_19804_.m_135370_(Y_DIR)).floatValue(), (double)((Float)this.f_19804_.m_135370_(Z_DIR)).floatValue());
        HitResult raytraceresult = ProjectileUtil.m_278158_((Entity)this, this::canHitEntity);
        if (raytraceresult != null && raytraceresult.m_6662_() != HitResult.Type.MISS && this.f_19797_ > 4) {
            this.onImpact(raytraceresult);
        }
        List list = this.m_9236_().m_45976_(Entity.class, this.m_20191_().m_82400_(0.1));
        if (this.pushedEntity != null && this.m_20270_(this.pushedEntity) > 2.0f) {
            this.pushedEntity = null;
        }
        double d0 = this.m_20185_() + vector3d.f_82479_;
        double d1 = this.m_20186_() + vector3d.f_82480_;
        double d2 = this.m_20189_() + vector3d.f_82481_;
        if (this.m_20186_() > (double)this.m_9236_().m_151558_()) {
            this.m_142687_(Entity.RemovalReason.DISCARDED);
        }
        this.updateRotation();
        if (this.m_20072_()) {
            this.m_142687_(Entity.RemovalReason.DISCARDED);
        } else {
            this.m_20256_(vector3d);
            this.m_20256_(this.m_20184_().m_82520_(0.0, (double)-0.06f, 0.0));
            this.m_6034_(d0, d1, d2);
            if (this.pushedEntity != null) {
                this.pushedEntity.m_20256_(this.m_20184_().m_82520_(0.0, 0.063, 0.0));
            }
            for (Entity e : list) {
                e.m_20256_(this.m_20184_().m_82520_(0.0, 0.068, 0.0));
                if (e.m_20184_().f_82480_ < 0.0) {
                    e.m_20256_(e.m_20184_().m_82542_(1.0, 0.0, 1.0));
                }
                e.f_19789_ = 0.0f;
            }
        }
    }

    public void setGustDir(float x, float y, float z) {
        this.f_19804_.m_135381_(X_DIR, (Object)Float.valueOf(x));
        this.f_19804_.m_135381_(Y_DIR, (Object)Float.valueOf(y));
        this.f_19804_.m_135381_(Z_DIR, (Object)Float.valueOf(z));
    }

    public float getGustDir(int xyz) {
        return ((Float)this.f_19804_.m_135370_(xyz == 2 ? Z_DIR : (xyz == 1 ? Y_DIR : X_DIR))).floatValue();
    }

    protected void onEntityHit(EntityHitResult result) {
        Entity entity = result.m_82443_();
        if (entity instanceof EntityGust) {
            EntityGust other = (EntityGust)entity;
            double avgX = (other.m_20185_() + this.m_20185_()) / 2.0;
            double avgY = (other.m_20186_() + this.m_20186_()) / 2.0;
            double avgZ = (other.m_20189_() + this.m_20189_()) / 2.0;
            other.m_6034_(avgX, avgY, avgZ);
            other.setGustDir(other.getGustDir(0) + this.getGustDir(0), other.getGustDir(1) + this.getGustDir(1), other.getGustDir(2) + this.getGustDir(2));
            if (this.m_6084_() && other.m_6084_()) {
                this.m_142687_(Entity.RemovalReason.DISCARDED);
            }
        } else if (entity != null) {
            this.pushedEntity = entity;
        }
    }

    protected boolean canHitEntity(Entity p_230298_1_) {
        return !p_230298_1_.m_5833_();
    }

    protected void onHitBlock(BlockHitResult p_230299_1_) {
        if (p_230299_1_.m_82425_() != null) {
            BlockPos pos = p_230299_1_.m_82425_();
            if (this.m_9236_().m_46801_(pos) && !this.m_9236_().f_46443_) {
                this.m_142687_(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    protected void m_8097_() {
        this.f_19804_.m_135372_(VERTICAL, (Object)false);
        this.f_19804_.m_135372_(X_DIR, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(Y_DIR, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(Z_DIR, (Object)Float.valueOf(0.0f));
    }

    protected void m_7380_(CompoundTag compound) {
        compound.m_128379_("VerticalTornado", this.getVertical());
        compound.m_128350_("GustDirX", ((Float)this.f_19804_.m_135370_(X_DIR)).floatValue());
        compound.m_128350_("GustDirY", ((Float)this.f_19804_.m_135370_(Y_DIR)).floatValue());
        compound.m_128350_("GustDirZ", ((Float)this.f_19804_.m_135370_(Z_DIR)).floatValue());
    }

    protected void m_7378_(CompoundTag compound) {
        this.f_19804_.m_135381_(X_DIR, (Object)Float.valueOf(compound.m_128457_("GustDirX")));
        this.f_19804_.m_135381_(Y_DIR, (Object)Float.valueOf(compound.m_128457_("GustDirX")));
        this.f_19804_.m_135381_(Z_DIR, (Object)Float.valueOf(compound.m_128457_("GustDirX")));
        this.setVertical(compound.m_128471_("VerticalTornado"));
    }

    public void setVertical(boolean vertical) {
        this.f_19804_.m_135381_(VERTICAL, (Object)vertical);
    }

    public boolean getVertical() {
        return (Boolean)this.f_19804_.m_135370_(VERTICAL);
    }

    protected void onImpact(HitResult result) {
        HitResult.Type raytraceresult$type = result.m_6662_();
        if (raytraceresult$type == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult)result);
        } else if (raytraceresult$type == HitResult.Type.BLOCK) {
            this.onHitBlock((BlockHitResult)result);
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_6001_(double x, double y, double z) {
        this.m_20334_(x, y, z);
        if (this.f_19860_ == 0.0f && this.f_19859_ == 0.0f) {
            float f = Mth.m_14116_((float)((float)(x * x + z * z)));
            this.m_146926_((float)(Mth.m_14136_((double)y, (double)f) * 57.2957763671875));
            this.m_146922_((float)(Mth.m_14136_((double)x, (double)z) * 57.2957763671875));
            this.f_19860_ = this.m_146909_();
            this.f_19859_ = this.m_146908_();
            this.m_7678_(this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), this.m_146909_());
        }
    }

    protected void updateRotation() {
        Vec3 vector3d = this.m_20184_();
        float f = Mth.m_14116_((float)((float)(vector3d.f_82479_ * vector3d.f_82479_ + vector3d.f_82481_ * vector3d.f_82481_)));
        this.m_146926_(EntityGust.lerpRotation(this.f_19860_, (float)(Mth.m_14136_((double)vector3d.f_82480_, (double)f) * 57.2957763671875)));
        this.m_146922_(EntityGust.lerpRotation(this.f_19859_, (float)(Mth.m_14136_((double)vector3d.f_82479_, (double)vector3d.f_82481_) * 57.2957763671875)));
    }
}
