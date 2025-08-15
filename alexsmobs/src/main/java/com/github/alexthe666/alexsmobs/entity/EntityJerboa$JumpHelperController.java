/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.JumpControl
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityJerboa;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.JumpControl;

public static class EntityJerboa.JumpHelperController
extends JumpControl {
    private final EntityJerboa jerboa;
    private boolean canJump;

    public EntityJerboa.JumpHelperController(EntityJerboa jerboa) {
        super((Mob)jerboa);
        this.jerboa = jerboa;
    }

    public boolean getIsJumping() {
        return this.f_24897_;
    }

    public boolean canJump() {
        return this.canJump;
    }

    public void setCanJump(boolean canJumpIn) {
        this.canJump = canJumpIn;
    }

    public void m_8124_() {
        if (this.f_24897_) {
            this.jerboa.startJumping();
            this.f_24897_ = false;
        }
    }
}
