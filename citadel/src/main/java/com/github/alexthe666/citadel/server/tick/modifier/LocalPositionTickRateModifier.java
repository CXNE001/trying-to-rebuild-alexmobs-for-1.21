/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.citadel.server.tick.modifier;

import com.github.alexthe666.citadel.server.tick.modifier.LocalTickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifierType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LocalPositionTickRateModifier
extends LocalTickRateModifier {
    private Vec3 center;

    public LocalPositionTickRateModifier(Vec3 center, double range, ResourceKey<Level> dimension, int durationInMasterTicks, float tickRateMultiplier) {
        super(TickRateModifierType.LOCAL_POSITION, range, dimension, durationInMasterTicks, tickRateMultiplier);
        this.center = center;
    }

    public LocalPositionTickRateModifier(CompoundTag tag) {
        super(tag);
        this.center = new Vec3(tag.m_128459_("CenterX"), tag.m_128459_("CenterY"), tag.m_128459_("CenterZ"));
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = super.toTag();
        tag.m_128347_("CenterX", this.center.f_82479_);
        tag.m_128347_("CenterY", this.center.f_82480_);
        tag.m_128347_("CenterZ", this.center.f_82481_);
        return tag;
    }

    public Vec3 getCenter() {
        return this.center;
    }

    @Override
    public Vec3 getCenter(Level level) {
        return this.getCenter();
    }

    public void setCenter(Vec3 center) {
        this.center = center;
    }
}
