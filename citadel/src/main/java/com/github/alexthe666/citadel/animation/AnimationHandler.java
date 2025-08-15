/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.commons.lang3.ArrayUtils
 */
package com.github.alexthe666.citadel.animation;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationEvent;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.server.message.AnimationMessage;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.ArrayUtils;

public enum AnimationHandler {
    INSTANCE;


    public <T extends Entity> void sendAnimationMessage(T entity, Animation animation) {
        if (entity.m_9236_().f_46443_) {
            return;
        }
        ((IAnimatedEntity)entity).setAnimation(animation);
        Citadel.sendMSGToAll(new AnimationMessage(entity.m_19879_(), ArrayUtils.indexOf((Object[])((IAnimatedEntity)entity).getAnimations(), (Object)animation)));
    }

    public <T extends Entity> void updateAnimations(T entity) {
        if (((IAnimatedEntity)entity).getAnimation() == null) {
            ((IAnimatedEntity)entity).setAnimation(IAnimatedEntity.NO_ANIMATION);
        } else if (((IAnimatedEntity)entity).getAnimation() != IAnimatedEntity.NO_ANIMATION) {
            AnimationEvent.Start<T> event;
            if (((IAnimatedEntity)entity).getAnimationTick() == 0 && !MinecraftForge.EVENT_BUS.post(event = new AnimationEvent.Start<T>(entity, ((IAnimatedEntity)entity).getAnimation()))) {
                this.sendAnimationMessage(entity, event.getAnimation());
            }
            if (((IAnimatedEntity)entity).getAnimationTick() < ((IAnimatedEntity)entity).getAnimation().getDuration()) {
                ((IAnimatedEntity)entity).setAnimationTick(((IAnimatedEntity)entity).getAnimationTick() + 1);
                MinecraftForge.EVENT_BUS.post(new AnimationEvent.Tick<T>(entity, ((IAnimatedEntity)entity).getAnimation(), ((IAnimatedEntity)entity).getAnimationTick()));
            }
            if (((IAnimatedEntity)entity).getAnimationTick() == ((IAnimatedEntity)entity).getAnimation().getDuration()) {
                ((IAnimatedEntity)entity).setAnimationTick(0);
                ((IAnimatedEntity)entity).setAnimation(IAnimatedEntity.NO_ANIMATION);
            }
        }
    }
}
