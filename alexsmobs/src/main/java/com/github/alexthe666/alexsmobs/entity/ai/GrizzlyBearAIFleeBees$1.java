/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.animal.Bee
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Bee;

class GrizzlyBearAIFleeBees.1
implements Predicate<Bee> {
    GrizzlyBearAIFleeBees.1() {
    }

    public boolean apply(@Nullable Bee p_apply_1_) {
        return p_apply_1_.m_6084_() && GrizzlyBearAIFleeBees.this.entity.m_21574_().m_148306_((Entity)p_apply_1_) && !GrizzlyBearAIFleeBees.this.entity.m_7307_((Entity)p_apply_1_) && p_apply_1_.m_6784_() > 0;
    }
}
