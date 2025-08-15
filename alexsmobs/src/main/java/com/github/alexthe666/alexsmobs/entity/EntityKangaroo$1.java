/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;

class EntityKangaroo.1
extends SimpleContainer {
    EntityKangaroo.1(int p_19150_) {
        super(p_19150_);
    }

    public void m_5785_(Player player) {
        EntityKangaroo.this.f_19804_.m_135381_(POUCH_TICK, (Object)10);
        EntityKangaroo.this.resetKangarooSlots();
    }

    public boolean m_6542_(Player player) {
        return EntityKangaroo.this.m_6084_() && !EntityKangaroo.this.f_19817_;
    }
}
