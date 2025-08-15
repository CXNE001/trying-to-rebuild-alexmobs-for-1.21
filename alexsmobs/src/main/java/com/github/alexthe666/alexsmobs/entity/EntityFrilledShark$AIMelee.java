/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.animal.Squid
 *  net.minecraft.world.entity.monster.Drowned
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

private class EntityFrilledShark.AIMelee
extends Goal {
    public EntityFrilledShark.AIMelee() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return EntityFrilledShark.this.m_5448_() != null && EntityFrilledShark.this.m_5448_().m_6084_();
    }

    public void m_8037_() {
        LivingEntity target = EntityFrilledShark.this.m_5448_();
        double speed = 1.0;
        if (EntityFrilledShark.this.m_20270_((Entity)target) < 10.0f) {
            if ((double)EntityFrilledShark.this.m_20270_((Entity)target) < 1.9) {
                EntityFrilledShark.this.m_7327_((Entity)target);
                speed = 0.8f;
            } else {
                speed = 0.6f;
                EntityFrilledShark.this.m_21391_((Entity)target, 70.0f, 70.0f);
                if (target instanceof Squid) {
                    Vec3 mouth = EntityFrilledShark.this.m_20182_();
                    float squidSpeed = 0.07f;
                    ((Squid)target).m_29958_((float)(mouth.f_82479_ - target.m_20185_()) * squidSpeed, (float)(mouth.f_82480_ - target.m_20188_()) * squidSpeed, (float)(mouth.f_82481_ - target.m_20189_()) * squidSpeed);
                    EntityFrilledShark.this.m_9236_().m_7605_((Entity)EntityFrilledShark.this, (byte)68);
                }
            }
        }
        if (target instanceof Drowned || target instanceof Player) {
            speed = 1.0;
        }
        EntityFrilledShark.this.m_21573_().m_5624_((Entity)target, speed);
    }
}
