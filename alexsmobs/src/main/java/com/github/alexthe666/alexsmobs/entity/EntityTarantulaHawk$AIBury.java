/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityTarantulaHawk.AIBury
extends Goal {
    private final EntityTarantulaHawk hawk;
    private BlockPos buryPos = null;
    private int digTime = 0;
    private double stageX;
    private double stageY;
    private double stageZ;

    private EntityTarantulaHawk.AIBury() {
        this.hawk = EntityTarantulaHawk.this;
    }

    public boolean m_8036_() {
        BlockPos pos;
        if (this.hawk.isDragging() && this.hawk.m_5448_() != null && (pos = this.hawk.genSandPos(this.hawk.m_20183_())) != null) {
            this.buryPos = pos;
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.hawk.isDragging() && this.digTime < 200 && this.hawk.m_5448_() != null && this.buryPos != null && EntityTarantulaHawk.this.m_9236_().m_8055_(this.buryPos).m_204336_(BlockTags.f_13029_);
    }

    public void m_8056_() {
        this.digTime = 0;
        this.stageX = this.hawk.m_20185_();
        this.stageY = this.hawk.m_20186_();
        this.stageZ = this.hawk.m_20189_();
    }

    public void m_8041_() {
        this.digTime = 0;
        this.hawk.setDigging(false);
        this.hawk.setDragging(false);
        this.hawk.m_6710_(null);
        this.hawk.m_6703_(null);
    }

    public void m_8037_() {
        this.hawk.setFlying(false);
        this.hawk.setDragging(true);
        LivingEntity target = this.hawk.m_5448_();
        if (this.hawk.m_20238_(Vec3.m_82512_((Vec3i)this.buryPos)) < 9.0 && !this.hawk.isDigging()) {
            this.hawk.setDigging(true);
            this.stageX = target.m_20185_();
            this.stageY = target.m_20186_();
            this.stageZ = target.m_20189_();
        }
        if (this.hawk.isDigging()) {
            target.f_19794_ = true;
            ++this.digTime;
            this.hawk.m_20153_();
            target.m_6034_(this.stageX, this.stageY - (double)Math.min(3.0f, (float)this.digTime * 0.05f), this.stageZ);
            this.hawk.m_21573_().m_26519_(this.stageX, this.stageY, this.stageZ, (double)0.85f);
        } else {
            this.hawk.m_21573_().m_26519_((double)this.buryPos.m_123341_(), (double)this.buryPos.m_123342_(), (double)this.buryPos.m_123343_(), 0.5);
        }
    }
}
