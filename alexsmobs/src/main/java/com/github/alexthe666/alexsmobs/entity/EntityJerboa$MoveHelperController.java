/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityJerboa;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

static class EntityJerboa.MoveHelperController
extends MoveControl {
    private final EntityJerboa jerboa;
    private double nextJumpSpeed;

    public EntityJerboa.MoveHelperController(EntityJerboa jerboa) {
        super((Mob)jerboa);
        this.jerboa = jerboa;
    }

    public void m_8126_() {
        if (this.jerboa.hasJumper() && this.jerboa.m_20096_() && !this.jerboa.f_20899_ && !((EntityJerboa.JumpHelperController)this.jerboa.f_21343_).getIsJumping()) {
            this.jerboa.setMovementSpeed(0.0);
        } else if (this.m_24995_()) {
            this.jerboa.setMovementSpeed(this.nextJumpSpeed);
        }
        if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
            this.f_24981_ = MoveControl.Operation.WAIT;
            Vec3 vector3d = new Vec3(this.f_24975_ - this.jerboa.m_20185_(), this.f_24976_ - this.jerboa.m_20186_(), this.f_24977_ - this.jerboa.m_20189_());
            double d0 = vector3d.m_82553_();
            this.jerboa.m_20256_(this.jerboa.m_20184_().m_82549_(vector3d.m_82490_(this.f_24978_ * 1.0 * 0.05 / d0)));
        }
        super.m_8126_();
    }

    public void m_6849_(double x, double y, double z, double speedIn) {
        if (this.jerboa.m_20069_()) {
            speedIn = 1.5;
        }
        super.m_6849_(x, y, z, speedIn);
        if (speedIn > 0.0) {
            this.nextJumpSpeed = speedIn;
        }
    }
}
