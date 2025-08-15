/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Mth
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.model;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.container.Transform;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class ModelAnimator {
    private int tempTick = 0;
    private int prevTempTick;
    private boolean correctAnimation = false;
    private IAnimatedEntity entity;
    private final HashMap<AdvancedModelBox, Transform> transformMap = new HashMap();
    private final HashMap<AdvancedModelBox, Transform> prevTransformMap = new HashMap();

    public static ModelAnimator create() {
        return new ModelAnimator();
    }

    public IAnimatedEntity getEntity() {
        return this.entity;
    }

    public void update(IAnimatedEntity entity) {
        this.prevTempTick = 0;
        this.tempTick = 0;
        this.correctAnimation = false;
        this.entity = entity;
        this.transformMap.clear();
        this.prevTransformMap.clear();
    }

    public boolean setAnimation(Animation animation) {
        this.prevTempTick = 0;
        this.tempTick = 0;
        this.correctAnimation = this.entity.getAnimation() == animation;
        return this.correctAnimation;
    }

    public void startKeyframe(int duration) {
        if (!this.correctAnimation) {
            return;
        }
        this.prevTempTick = this.tempTick;
        this.tempTick += duration;
    }

    public void setStaticKeyframe(int duration) {
        this.startKeyframe(duration);
        this.endKeyframe(true);
    }

    public void resetKeyframe(int duration) {
        this.startKeyframe(duration);
        this.endKeyframe();
    }

    public void rotate(AdvancedModelBox box, float x, float y, float z) {
        if (!this.correctAnimation) {
            return;
        }
        this.getTransform(box).addRotation(x, y, z);
    }

    public void move(AdvancedModelBox box, float x, float y, float z) {
        if (!this.correctAnimation) {
            return;
        }
        this.getTransform(box).addOffset(x, y, z);
    }

    private Transform getTransform(AdvancedModelBox box) {
        return this.transformMap.computeIfAbsent(box, b -> new Transform());
    }

    public void endKeyframe() {
        this.endKeyframe(false);
    }

    private void endKeyframe(boolean stationary) {
        if (!this.correctAnimation) {
            return;
        }
        int animationTick = this.entity.getAnimationTick();
        if (animationTick >= this.prevTempTick && animationTick < this.tempTick) {
            if (stationary) {
                for (AdvancedModelBox box : this.prevTransformMap.keySet()) {
                    Transform transform = this.prevTransformMap.get(box);
                    box.rotateAngleX += transform.getRotationX();
                    box.rotateAngleY += transform.getRotationY();
                    box.rotateAngleZ += transform.getRotationZ();
                    box.rotationPointX += transform.getOffsetX();
                    box.rotationPointY += transform.getOffsetY();
                    box.rotationPointZ += transform.getOffsetZ();
                }
            } else {
                Transform transform;
                float frameTime = Minecraft.m_91087_().m_91104_() ? 0.0f : Minecraft.m_91087_().m_91296_();
                float tick = ((float)(animationTick - this.prevTempTick) + frameTime) / (float)(this.tempTick - this.prevTempTick);
                float inc = Mth.m_14031_((float)((float)((double)tick * Math.PI / 2.0)));
                float dec = 1.0f - inc;
                for (AdvancedModelBox box : this.prevTransformMap.keySet()) {
                    transform = this.prevTransformMap.get(box);
                    box.rotateAngleX += dec * transform.getRotationX();
                    box.rotateAngleY += dec * transform.getRotationY();
                    box.rotateAngleZ += dec * transform.getRotationZ();
                    box.rotationPointX += dec * transform.getOffsetX();
                    box.rotationPointY += dec * transform.getOffsetY();
                    box.rotationPointZ += dec * transform.getOffsetZ();
                }
                for (AdvancedModelBox box : this.transformMap.keySet()) {
                    transform = this.transformMap.get(box);
                    box.rotateAngleX += inc * transform.getRotationX();
                    box.rotateAngleY += inc * transform.getRotationY();
                    box.rotateAngleZ += inc * transform.getRotationZ();
                    box.rotationPointX += inc * transform.getOffsetX();
                    box.rotationPointY += inc * transform.getOffsetY();
                    box.rotationPointZ += inc * transform.getOffsetZ();
                }
            }
        }
        if (!stationary) {
            this.prevTransformMap.clear();
            this.prevTransformMap.putAll(this.transformMap);
            this.transformMap.clear();
        }
    }
}
