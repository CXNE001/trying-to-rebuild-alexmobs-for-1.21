/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.citadel.server.tick.modifier;

import com.github.alexthe666.citadel.server.entity.IModifiesTime;
import com.github.alexthe666.citadel.server.tick.modifier.LocalTickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifierType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class LocalEntityTickRateModifier
extends LocalTickRateModifier {
    private int entityId;
    private EntityType expectedEntityType;

    public LocalEntityTickRateModifier(int entityId, EntityType expectedEntityType, double range, ResourceKey<Level> dimension, int durationInMasterTicks, float tickRateMultiplier) {
        super(TickRateModifierType.LOCAL_ENTITY, range, dimension, durationInMasterTicks, tickRateMultiplier);
        this.entityId = entityId;
        this.expectedEntityType = expectedEntityType;
    }

    public LocalEntityTickRateModifier(CompoundTag tag) {
        super(tag);
        this.entityId = tag.m_128451_("EntityId");
        EntityType type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(tag.m_128461_("EntityType")));
        this.expectedEntityType = type == null ? EntityType.f_20510_ : type;
    }

    @Override
    public Vec3 getCenter(Level level) {
        Entity entity = level.m_6815_(this.entityId);
        if (this.isEntityValid(level) && entity != null) {
            return entity.m_20182_();
        }
        return Vec3.f_82478_;
    }

    @Override
    public boolean appliesTo(Level level, double x, double y, double z) {
        return super.appliesTo(level, x, y, z) && this.isEntityValid(level);
    }

    public boolean isEntityValid(Level level) {
        Entity entity = level.m_6815_(this.entityId);
        return entity != null && entity.isAddedToWorld() && entity.m_6095_().equals(this.expectedEntityType) && entity.m_6084_() && (!(entity instanceof IModifiesTime) || ((IModifiesTime)entity).isTimeModificationValid(this));
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = super.toTag();
        tag.m_128405_("EntityId", this.entityId);
        ResourceLocation resourcelocation = ForgeRegistries.ENTITY_TYPES.getKey((Object)this.expectedEntityType);
        if (resourcelocation != null) {
            tag.m_128359_("EntityType", resourcelocation.toString());
        }
        return tag;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public EntityType getExpectedEntityType() {
        return this.expectedEntityType;
    }

    public void setExpectedEntityType(EntityType expectedEntityType) {
        this.expectedEntityType = expectedEntityType;
    }
}
