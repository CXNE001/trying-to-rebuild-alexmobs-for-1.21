/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.eventbus.api.Cancelable
 */
package com.github.alexthe666.citadel.animation;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public static class AnimationEvent.Start<T extends Entity>
extends AnimationEvent<T> {
    public AnimationEvent.Start(T entity, Animation animation) {
        super(entity, animation);
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}
