/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.MoveControl
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;

static class EntityKangaroo.MoveHelperController
extends MoveControl {
    private final EntityKangaroo kangaroo;
    private double nextJumpSpeed;

    public EntityKangaroo.MoveHelperController(EntityKangaroo kangaroo) {
        super((Mob)kangaroo);
        this.kangaroo = kangaroo;
    }

    public void m_8126_() {
        if (this.kangaroo.hasJumper() && this.kangaroo.m_20096_() && !this.kangaroo.f_20899_ && !((EntityKangaroo.JumpHelperController)this.kangaroo.f_21343_).getIsJumping()) {
            this.kangaroo.setMovementSpeed(0.0);
        } else if (this.m_24995_()) {
            this.kangaroo.setMovementSpeed(this.nextJumpSpeed);
        }
        super.m_8126_();
    }

    public void m_6849_(double x, double y, double z, double speedIn) {
        if (this.kangaroo.m_20069_()) {
            speedIn = 1.5;
        }
        super.m_6849_(x, y, z, speedIn);
        if (speedIn > 0.0) {
            this.nextJumpSpeed = speedIn;
        }
    }
}
