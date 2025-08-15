/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.server.tick.modifier;

import com.github.alexthe666.citadel.server.tick.modifier.CelestialTickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.GlobalTickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.LocalEntityTickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.LocalPositionTickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifier;

public enum TickRateModifierType {
    GLOBAL(GlobalTickRateModifier.class, false, 0),
    CELESTIAL(CelestialTickRateModifier.class, false, 1),
    LOCAL_POSITION(LocalPositionTickRateModifier.class, true, 2),
    LOCAL_ENTITY(LocalEntityTickRateModifier.class, true, 3);

    private final Class<? extends TickRateModifier> clazz;
    private final boolean local;
    private final int id;

    private TickRateModifierType(Class<? extends TickRateModifier> clazz, boolean local, int id) {
        this.clazz = clazz;
        this.local = local;
        this.id = id;
    }

    public Class<? extends TickRateModifier> getTickRateClass() {
        return this.clazz;
    }

    public boolean isLocal() {
        return this.local;
    }

    public int toId() {
        return this.id;
    }

    public static TickRateModifierType fromId(int id) {
        for (TickRateModifierType type : TickRateModifierType.values()) {
            if (type.id != id) continue;
            return type;
        }
        return CELESTIAL;
    }
}
