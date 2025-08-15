/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.citadel.server.entity;

import com.github.alexthe666.citadel.server.entity.ICitadelDataEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class CitadelEntityData {
    public static CompoundTag getOrCreateCitadelTag(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getCitadelTag(entity);
        return tag == null ? new CompoundTag() : tag;
    }

    public static CompoundTag getCitadelTag(LivingEntity entity) {
        return entity instanceof ICitadelDataEntity ? ((ICitadelDataEntity)entity).getCitadelEntityData() : new CompoundTag();
    }

    public static void setCitadelTag(LivingEntity entity, CompoundTag tag) {
        if (entity instanceof ICitadelDataEntity) {
            ((ICitadelDataEntity)entity).setCitadelEntityData(tag);
        }
    }
}
