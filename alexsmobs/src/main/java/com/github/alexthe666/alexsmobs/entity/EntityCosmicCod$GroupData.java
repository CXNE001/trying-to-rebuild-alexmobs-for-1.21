/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.AgeableMob$AgeableMobGroupData
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCosmicCod;
import net.minecraft.world.entity.AgeableMob;

public static class EntityCosmicCod.GroupData
extends AgeableMob.AgeableMobGroupData {
    public final EntityCosmicCod groupLeader;

    public EntityCosmicCod.GroupData(EntityCosmicCod groupLeaderIn) {
        super(0.05f);
        this.groupLeader = groupLeaderIn;
    }
}
