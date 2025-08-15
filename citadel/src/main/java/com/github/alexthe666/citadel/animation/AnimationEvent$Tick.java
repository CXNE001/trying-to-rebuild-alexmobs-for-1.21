/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.citadel.animation;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationEvent;
import net.minecraft.world.entity.Entity;

public static class AnimationEvent.Tick<T extends Entity>
extends AnimationEvent<T> {
    protected int tick;

    public AnimationEvent.Tick(T entity, Animation animation, int tick) {
        super(entity, animation);
        this.tick = tick;
    }

    public int getTick() {
        return this.tick;
    }
}
