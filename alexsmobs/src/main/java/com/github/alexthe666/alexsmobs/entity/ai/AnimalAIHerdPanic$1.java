/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.IHerdPanic;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;

class AnimalAIHerdPanic.1
implements Predicate<PathfinderMob> {
    final /* synthetic */ PathfinderMob val$creature;

    AnimalAIHerdPanic.1() {
        this.val$creature = pathfinderMob;
    }

    public boolean apply(@Nullable PathfinderMob animal) {
        if (animal instanceof IHerdPanic && animal.m_6095_() == this.val$creature.m_6095_()) {
            return ((IHerdPanic)animal).canPanic();
        }
        return false;
    }
}
