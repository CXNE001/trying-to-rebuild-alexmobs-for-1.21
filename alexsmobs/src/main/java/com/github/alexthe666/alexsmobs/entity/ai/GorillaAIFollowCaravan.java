/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class GorillaAIFollowCaravan
extends Goal {
    public final EntityGorilla gorilla;
    private double speedModifier;
    private int distCheckCounter;

    public GorillaAIFollowCaravan(EntityGorilla llamaIn, double speedModifierIn) {
        this.gorilla = llamaIn;
        this.speedModifier = speedModifierIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (!(this.gorilla.isSilverback() || this.gorilla.inCaravan() || this.gorilla.isSitting())) {
            double dist = 15.0;
            List list = this.gorilla.m_9236_().m_45976_(EntityGorilla.class, this.gorilla.m_20191_().m_82377_(dist, dist / 2.0, dist));
            EntityGorilla gorilla = null;
            double d0 = Double.MAX_VALUE;
            for (Entity entity : list) {
                double d1;
                EntityGorilla gorilla1 = (EntityGorilla)entity;
                if (!gorilla1.inCaravan() || gorilla1.hasCaravanTrail() || (d1 = this.gorilla.m_20280_((Entity)gorilla1)) > d0) continue;
                d0 = d1;
                gorilla = gorilla1;
            }
            if (gorilla == null) {
                for (Entity entity1 : list) {
                    double d2;
                    EntityGorilla llamaentity2 = (EntityGorilla)entity1;
                    if (!llamaentity2.isSilverback() || llamaentity2.hasCaravanTrail() || (d2 = this.gorilla.m_20280_((Entity)llamaentity2)) > d0) continue;
                    d0 = d2;
                    gorilla = llamaentity2;
                }
            }
            if (gorilla == null) {
                return false;
            }
            if (d0 < 2.0) {
                return false;
            }
            if (!gorilla.isSilverback() && !this.firstIsSilverback(gorilla, 1)) {
                return false;
            }
            this.gorilla.joinCaravan(gorilla);
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        if (this.gorilla.isSitting()) {
            return false;
        }
        if (this.gorilla.inCaravan() && this.gorilla.getCaravanHead().m_6084_() && this.firstIsSilverback(this.gorilla, 0)) {
            double d0 = this.gorilla.m_20280_((Entity)this.gorilla.getCaravanHead());
            if (d0 > 676.0) {
                if (this.speedModifier <= 1.5) {
                    this.speedModifier *= 1.2;
                    this.distCheckCounter = 40;
                    return true;
                }
                if (this.distCheckCounter == 0) {
                    return false;
                }
            }
            if (this.distCheckCounter > 0) {
                --this.distCheckCounter;
            }
            return true;
        }
        return false;
    }

    public void m_8041_() {
        this.gorilla.leaveCaravan();
        this.speedModifier = 1.5;
    }

    public void m_8037_() {
        EntityGorilla llamaentity;
        if (this.gorilla.inCaravan() && !this.gorilla.isSitting() && (llamaentity = this.gorilla.getCaravanHead()) != null) {
            double d0 = this.gorilla.m_20270_((Entity)llamaentity);
            Vec3 vector3d = new Vec3(llamaentity.m_20185_() - this.gorilla.m_20185_(), llamaentity.m_20186_() - this.gorilla.m_20186_(), llamaentity.m_20189_() - this.gorilla.m_20189_()).m_82541_().m_82490_(Math.max(d0 - 2.0, 0.0));
            if (this.gorilla.m_21573_().m_26571_()) {
                try {
                    this.gorilla.m_21573_().m_26519_(this.gorilla.m_20185_() + vector3d.f_82479_, this.gorilla.m_20186_() + vector3d.f_82480_, this.gorilla.m_20189_() + vector3d.f_82481_, this.speedModifier);
                }
                catch (NullPointerException e) {
                    AlexsMobs.LOGGER.warn("gorilla encountered issue following caravan head");
                }
            }
        }
    }

    private boolean firstIsSilverback(EntityGorilla llama, int p_190858_2_) {
        if (p_190858_2_ > 8) {
            return false;
        }
        if (llama.inCaravan()) {
            if (llama.getCaravanHead().isSilverback()) {
                return true;
            }
            EntityGorilla llamaentity = llama.getCaravanHead();
            return this.firstIsSilverback(llamaentity, ++p_190858_2_);
        }
        return false;
    }
}
