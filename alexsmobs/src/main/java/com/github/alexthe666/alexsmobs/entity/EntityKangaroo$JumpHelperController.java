/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.JumpControl
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.JumpControl;

public static class EntityKangaroo.JumpHelperController
extends JumpControl {
    private final EntityKangaroo kangaroo;
    private boolean canJump;

    public EntityKangaroo.JumpHelperController(EntityKangaroo kangaroo) {
        super((Mob)kangaroo);
        this.kangaroo = kangaroo;
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
            this.kangaroo.startJumping();
            this.f_24897_ = false;
        }
    }
}
