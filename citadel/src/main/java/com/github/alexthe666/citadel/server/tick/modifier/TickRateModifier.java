/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.citadel.server.tick.modifier;

import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifierType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

public abstract class TickRateModifier {
    private final TickRateModifierType type;
    private float maxDuration;
    private float duration;
    private float tickRateMultiplier;

    public TickRateModifier(TickRateModifierType type, int maxDuration, float tickRateMultiplier) {
        this.type = type;
        this.maxDuration = maxDuration;
        this.tickRateMultiplier = tickRateMultiplier;
    }

    public TickRateModifier(CompoundTag tag) {
        this.type = TickRateModifierType.fromId(tag.m_128451_("TickRateType"));
        this.maxDuration = tag.m_128457_("MaxDuration");
        this.duration = tag.m_128457_("Duration");
        this.tickRateMultiplier = tag.m_128457_("SpeedMultiplier");
    }

    public TickRateModifierType getType() {
        return this.type;
    }

    public float getMaxDuration() {
        return this.maxDuration;
    }

    public float getTickRateMultiplier() {
        return this.tickRateMultiplier;
    }

    public void setMaxDuration(float maxDuration) {
        this.maxDuration = maxDuration;
    }

    public void setTickRateMultiplier(float tickRateMultiplier) {
        this.tickRateMultiplier = tickRateMultiplier;
    }

    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();
        tag.m_128405_("TickRateType", this.type.toId());
        tag.m_128350_("MaxDuration", this.maxDuration);
        tag.m_128350_("Duration", this.duration);
        tag.m_128350_("SpeedMultiplier", this.tickRateMultiplier);
        return tag;
    }

    public static TickRateModifier fromTag(CompoundTag tag) {
        TickRateModifierType typeFromNbt = TickRateModifierType.fromId(tag.m_128451_("TickRateType"));
        try {
            return typeFromNbt.getTickRateClass().getConstructor(CompoundTag.class).newInstance(tag);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isGlobal() {
        return this.type.isLocal();
    }

    public void masterTick() {
        this.duration += 1.0f;
    }

    public boolean doRemove() {
        float f = this.tickRateMultiplier == 0.0f || this.getType() == TickRateModifierType.CELESTIAL ? 1.0f : 1.0f / this.tickRateMultiplier;
        return this.duration >= this.maxDuration * f;
    }

    public abstract boolean appliesTo(Level var1, double var2, double var4, double var6);
}
