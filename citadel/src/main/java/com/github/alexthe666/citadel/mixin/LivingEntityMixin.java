/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.server.entity.ICitadelDataEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LivingEntity.class})
public abstract class LivingEntityMixin
extends Entity
implements ICitadelDataEntity {
    private static final EntityDataAccessor<CompoundTag> CITADEL_DATA = SynchedEntityData.m_135353_(LivingEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135042_);

    protected LivingEntityMixin(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(at={@At(value="TAIL")}, remap=true, method={"Lnet/minecraft/world/entity/LivingEntity;defineSynchedData()V"})
    private void citadel_registerData(CallbackInfo ci) {
        this.f_19804_.m_135372_(CITADEL_DATA, (Object)new CompoundTag());
    }

    @Inject(at={@At(value="TAIL")}, remap=true, method={"Lnet/minecraft/world/entity/LivingEntity;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"})
    private void citadel_writeAdditional(CompoundTag compoundNBT, CallbackInfo ci) {
        CompoundTag citadelDat = this.getCitadelEntityData();
        if (citadelDat != null) {
            compoundNBT.m_128365_("CitadelData", (Tag)citadelDat);
        }
    }

    @Inject(at={@At(value="TAIL")}, remap=true, method={"Lnet/minecraft/world/entity/LivingEntity;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"})
    private void citadel_readAdditional(CompoundTag compoundNBT, CallbackInfo ci) {
        if (compoundNBT.m_128441_("CitadelData")) {
            this.setCitadelEntityData(compoundNBT.m_128469_("CitadelData"));
        }
    }

    @Override
    public CompoundTag getCitadelEntityData() {
        return (CompoundTag)this.f_19804_.m_135370_(CITADEL_DATA);
    }

    @Override
    public void setCitadelEntityData(CompoundTag nbt) {
        this.f_19804_.m_135381_(CITADEL_DATA, (Object)nbt);
    }
}
