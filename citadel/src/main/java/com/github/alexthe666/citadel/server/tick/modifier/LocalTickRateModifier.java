/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.citadel.server.tick.modifier;

import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifierType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class LocalTickRateModifier
extends TickRateModifier {
    private double range;
    private final ResourceKey<Level> dimension;

    public LocalTickRateModifier(TickRateModifierType localPosition, double range, ResourceKey<Level> dimension, int durationInMasterTicks, float tickRateMultiplier) {
        super(localPosition, durationInMasterTicks, tickRateMultiplier);
        this.range = range;
        this.dimension = dimension;
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = super.toTag();
        tag.m_128347_("Range", this.range);
        tag.m_128359_("Dimension", this.dimension.m_135782_().toString());
        return tag;
    }

    public LocalTickRateModifier(CompoundTag tag) {
        super(tag);
        this.range = tag.m_128459_("Range");
        ResourceKey dimFromTag = Level.f_46428_;
        if (tag.m_128441_("Dimension")) {
            dimFromTag = ResourceKey.m_135785_((ResourceKey)Registries.f_256858_, (ResourceLocation)new ResourceLocation(tag.m_128461_("dimension")));
        }
        this.dimension = dimFromTag;
    }

    public double getRange() {
        return this.range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public abstract Vec3 getCenter(Level var1);

    @Override
    public boolean appliesTo(Level level, double x, double y, double z) {
        Vec3 center = this.getCenter(level);
        return center.m_82531_(x, y, z) < this.range * this.range;
    }
}
