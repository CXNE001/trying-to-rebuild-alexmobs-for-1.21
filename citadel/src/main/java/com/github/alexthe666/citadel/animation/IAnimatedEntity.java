/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.animation;

import com.github.alexthe666.citadel.animation.Animation;

public interface IAnimatedEntity {
    public static final Animation NO_ANIMATION = Animation.create(0);

    public int getAnimationTick();

    public void setAnimationTick(int var1);

    public Animation getAnimation();

    public void setAnimation(Animation var1);

    public Animation[] getAnimations();
}
