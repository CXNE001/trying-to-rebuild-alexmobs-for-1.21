/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.citadel.server.tick.modifier;

import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifierType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

public class GlobalTickRateModifier
extends TickRateModifier {
    public GlobalTickRateModifier(int durationInMasterTicks, float tickRateMultiplier) {
        super(TickRateModifierType.GLOBAL, durationInMasterTicks, tickRateMultiplier);
    }

    public GlobalTickRateModifier(CompoundTag tag) {
        super(tag);
    }

    @Override
    public boolean appliesTo(Level level, double x, double y, double z) {
        return true;
    }
}
