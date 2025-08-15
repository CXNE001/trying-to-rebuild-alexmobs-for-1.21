/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.block.BlockHummingbirdFeeder;
import com.github.alexthe666.alexsmobs.entity.EntityHummingbird;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;

private class EntityHummingbird.AIUseFeeder
extends Goal {
    int runCooldown = 0;
    private int idleAtFlowerTime = 0;
    private BlockPos localFeeder;

    public EntityHummingbird.AIUseFeeder(EntityHummingbird entityHummingbird2) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    public void m_8041_() {
        this.localFeeder = null;
        this.idleAtFlowerTime = 0;
    }

    public boolean m_8036_() {
        if (EntityHummingbird.this.sipCooldown > 0) {
            return false;
        }
        if (this.runCooldown > 0) {
            --this.runCooldown;
        } else {
            BlockPos feedPos = EntityHummingbird.this.getFeederPos();
            if (feedPos != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(feedPos))) {
                this.localFeeder = feedPos;
                return true;
            }
            List<BlockPos> beacons = EntityHummingbird.this.getNearbyFeeders(EntityHummingbird.this.m_20183_(), (ServerLevel)EntityHummingbird.this.m_9236_(), 64);
            BlockPos closest = null;
            for (BlockPos pos : beacons) {
                if (closest != null && !(EntityHummingbird.this.m_20275_(closest.m_123341_(), closest.m_123342_(), closest.m_123343_()) > EntityHummingbird.this.m_20275_(pos.m_123341_(), pos.m_123342_(), pos.m_123343_())) || !this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(pos))) continue;
                closest = pos;
            }
            if (closest != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(closest))) {
                this.localFeeder = closest;
                return true;
            }
        }
        this.runCooldown = 400 + EntityHummingbird.this.f_19796_.m_188503_(600);
        return false;
    }

    public boolean m_8045_() {
        return this.localFeeder != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder)) && EntityHummingbird.this.sipCooldown == 0;
    }

    public void m_8037_() {
        if (this.localFeeder != null && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder))) {
            if (EntityHummingbird.this.m_20186_() > (double)this.localFeeder.m_123342_() && !EntityHummingbird.this.m_20096_()) {
                EntityHummingbird.this.m_21566_().m_6849_((double)((float)this.localFeeder.m_123341_() + 0.5f), (double)((float)this.localFeeder.m_123342_() + 0.1f), (double)((float)this.localFeeder.m_123343_() + 0.5f), 1.0);
            } else {
                EntityHummingbird.this.m_21566_().m_6849_((double)(this.localFeeder.m_123341_() + EntityHummingbird.this.f_19796_.m_188503_(4) - 2), EntityHummingbird.this.m_20186_() + 1.0, (double)(this.localFeeder.m_123343_() + EntityHummingbird.this.f_19796_.m_188503_(4) - 2), 1.0);
            }
            Vec3 vec = Vec3.m_82514_((Vec3i)this.localFeeder, (double)0.1f);
            double dist = Mth.m_14116_((float)((float)EntityHummingbird.this.m_20238_(vec)));
            if (dist < 2.5 && EntityHummingbird.this.m_20186_() > (double)this.localFeeder.m_123342_()) {
                EntityHummingbird.this.m_7618_(EntityAnchorArgument.Anchor.EYES, vec);
                ++this.idleAtFlowerTime;
                EntityHummingbird.this.setFeederPos(this.localFeeder);
                EntityHummingbird.this.m_9236_().m_7605_((Entity)EntityHummingbird.this, (byte)68);
                if (this.idleAtFlowerTime > 55) {
                    if (EntityHummingbird.this.getCropsPollinated() > 2 && EntityHummingbird.this.f_19796_.m_188503_(25) == 0 && this.isValidFeeder(EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder))) {
                        EntityHummingbird.this.m_9236_().m_46597_(this.localFeeder, (BlockState)EntityHummingbird.this.m_9236_().m_8055_(this.localFeeder).m_61124_((Property)BlockHummingbirdFeeder.CONTENTS, (Comparable)Integer.valueOf(0)));
                    }
                    EntityHummingbird.this.setCropsPollinated(EntityHummingbird.this.getCropsPollinated() + 1);
                    EntityHummingbird.this.sipCooldown = 120 + EntityHummingbird.this.f_19796_.m_188503_(1200);
                    EntityHummingbird.this.pollinateCooldown = Math.max(0, EntityHummingbird.this.pollinateCooldown / 3);
                    this.runCooldown = 400 + EntityHummingbird.this.f_19796_.m_188503_(600);
                    this.m_8041_();
                }
            }
        }
    }

    public boolean isValidFeeder(BlockState state) {
        return state.m_60734_() instanceof BlockHummingbirdFeeder && (Integer)state.m_61143_((Property)BlockHummingbirdFeeder.CONTENTS) == 3;
    }
}
