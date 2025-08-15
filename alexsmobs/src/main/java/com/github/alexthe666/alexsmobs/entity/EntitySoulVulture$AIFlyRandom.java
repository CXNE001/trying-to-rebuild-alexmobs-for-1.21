/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntitySoulVulture.AIFlyRandom
extends Goal {
    private final EntitySoulVulture vulture;
    private BlockPos target = null;

    public EntitySoulVulture.AIFlyRandom(EntitySoulVulture vulture) {
        this.vulture = vulture;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (this.vulture.getPerchPos() != null || this.vulture.shouldSwoop()) {
            return false;
        }
        MoveControl movementcontroller = this.vulture.m_21566_();
        if (!movementcontroller.m_24995_() || this.target == null) {
            this.target = this.getBlockInViewVulture();
            if (this.target != null) {
                this.vulture.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
            }
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        if (this.vulture.getPerchPos() != null || this.vulture.shouldSwoop()) {
            return false;
        }
        return this.target != null && this.vulture.m_20238_(Vec3.m_82512_((Vec3i)this.target)) > 2.4 && this.vulture.m_21566_().m_24995_() && !this.vulture.f_19862_;
    }

    public void m_8041_() {
        this.target = null;
    }

    public void m_8037_() {
        if (this.target == null) {
            this.target = this.getBlockInViewVulture();
        }
        if (this.target != null) {
            this.vulture.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
            if (this.vulture.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 2.5) {
                this.target = null;
            }
        }
    }

    public BlockPos getBlockInViewVulture() {
        float radius = -9.45f - (float)this.vulture.m_217043_().m_188503_(10);
        float neg = this.vulture.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.vulture.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.vulture.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(this.vulture.m_20185_() + extraX), (int)this.vulture.m_20186_(), (int)(this.vulture.m_20189_() + extraZ));
        while (EntitySoulVulture.this.m_9236_().m_46859_(radialPos) && radialPos.m_123342_() > 2) {
            radialPos = radialPos.m_7495_();
        }
        BlockPos newPos = radialPos.m_6630_(this.vulture.m_20186_() - (double)radialPos.m_123342_() > 16.0 ? 4 : this.vulture.m_217043_().m_188503_(5) + 5);
        if (!this.vulture.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.vulture.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 6.0) {
            return newPos;
        }
        return null;
    }
}
