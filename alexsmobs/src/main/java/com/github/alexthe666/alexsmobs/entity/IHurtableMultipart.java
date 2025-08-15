/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public interface IHurtableMultipart {
    public void onAttackedFromServer(LivingEntity var1, float var2, DamageSource var3);

    default public Vec3 calcOffsetVec(float offsetZ, float xRot, float yRot) {
        return new Vec3(0.0, 0.0, (double)offsetZ).m_82496_(xRot * ((float)Math.PI / 180)).m_82524_(-yRot * ((float)Math.PI / 180));
    }

    default public float limitAngle(float sourceAngle, float targetAngle, float maximumChange) {
        float f1;
        float f = Mth.m_14177_((float)(targetAngle - sourceAngle));
        if (f > maximumChange) {
            f = maximumChange;
        }
        if (f < -maximumChange) {
            f = -maximumChange;
        }
        if ((f1 = sourceAngle + f) < 0.0f) {
            f1 += 360.0f;
        } else if (f1 > 360.0f) {
            f1 -= 360.0f;
        }
        return f1;
    }
}
