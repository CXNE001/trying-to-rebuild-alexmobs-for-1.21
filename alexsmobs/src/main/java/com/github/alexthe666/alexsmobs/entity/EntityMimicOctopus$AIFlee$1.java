/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

class EntityMimicOctopus.AIFlee.1
implements Predicate<Entity> {
    final /* synthetic */ EntityMimicOctopus val$this$0;

    EntityMimicOctopus.AIFlee.1() {
        this.val$this$0 = entityMimicOctopus;
    }

    public boolean apply(@Nullable Entity e) {
        return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.MIMIC_OCTOPUS_FEARS) || e instanceof Player && !((Player)e).m_7500_();
    }
}
