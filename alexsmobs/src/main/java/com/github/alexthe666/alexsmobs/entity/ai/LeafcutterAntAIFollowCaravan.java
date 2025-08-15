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
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class LeafcutterAntAIFollowCaravan
extends Goal {
    public final EntityLeafcutterAnt ant;
    private double speedModifier;
    private int distCheckCounter;
    private int executionChance = 30;

    public LeafcutterAntAIFollowCaravan(EntityLeafcutterAnt llamaIn, double speedModifierIn) {
        this.ant = llamaIn;
        this.speedModifier = speedModifierIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        long worldTime = this.ant.m_9236_().m_46467_() % 10L;
        if (this.ant.m_21216_() >= 100 && worldTime != 0L) {
            return false;
        }
        if (this.ant.m_217043_().m_188503_(this.executionChance) != 0 && worldTime != 0L) {
            return false;
        }
        if (!(this.ant.shouldLeadCaravan() || this.ant.m_6162_() || this.ant.isQueen() || this.ant.inCaravan() || this.ant.hasLeaf())) {
            double dist = 15.0;
            List list = this.ant.m_9236_().m_45976_(EntityLeafcutterAnt.class, this.ant.m_20191_().m_82377_(dist, dist / 2.0, dist));
            EntityLeafcutterAnt LeafcutterAnt = null;
            double d0 = Double.MAX_VALUE;
            for (Entity entity : list) {
                double d1;
                EntityLeafcutterAnt LeafcutterAnt1 = (EntityLeafcutterAnt)entity;
                if (!LeafcutterAnt1.inCaravan() || LeafcutterAnt1.hasCaravanTrail() || (d1 = this.ant.m_20280_((Entity)LeafcutterAnt1)) > d0) continue;
                d0 = d1;
                LeafcutterAnt = LeafcutterAnt1;
            }
            if (LeafcutterAnt == null) {
                for (Entity entity1 : list) {
                    double d2;
                    EntityLeafcutterAnt llamaentity2 = (EntityLeafcutterAnt)entity1;
                    if (!llamaentity2.shouldLeadCaravan() || llamaentity2.hasCaravanTrail() || (d2 = this.ant.m_20280_((Entity)llamaentity2)) > d0) continue;
                    d0 = d2;
                    LeafcutterAnt = llamaentity2;
                }
            }
            if (LeafcutterAnt == null) {
                return false;
            }
            if (d0 < 2.0) {
                return false;
            }
            if (!LeafcutterAnt.shouldLeadCaravan() && !this.firstIsSilverback(LeafcutterAnt, 1)) {
                return false;
            }
            this.ant.joinCaravan(LeafcutterAnt);
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        if (this.ant.inCaravan() && this.ant.getCaravanHead().m_6084_() && this.firstIsSilverback(this.ant, 0)) {
            double d0 = this.ant.m_20280_((Entity)this.ant.getCaravanHead());
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
        this.ant.leaveCaravan();
        this.speedModifier = 1.5;
    }

    public void m_8037_() {
        EntityLeafcutterAnt llamaentity;
        if (this.ant.inCaravan() && !this.ant.shouldLeadCaravan() && (llamaentity = this.ant.getCaravanHead()) != null) {
            double d0 = this.ant.m_20270_((Entity)llamaentity);
            Vec3 vector3d = new Vec3(llamaentity.m_20185_() - this.ant.m_20185_(), llamaentity.m_20186_() - this.ant.m_20186_(), llamaentity.m_20189_() - this.ant.m_20189_()).m_82541_().m_82490_(Math.max(d0 - 2.0, 0.0));
            if (this.ant.m_21573_().m_26571_()) {
                try {
                    this.ant.m_21573_().m_26519_(this.ant.m_20185_() + vector3d.f_82479_, this.ant.m_20186_() + vector3d.f_82480_, this.ant.m_20189_() + vector3d.f_82481_, this.speedModifier);
                }
                catch (NullPointerException e) {
                    AlexsMobs.LOGGER.warn("leafcutter ant encountered issue following caravan head");
                }
            }
        }
    }

    private boolean firstIsSilverback(EntityLeafcutterAnt llama, int p_190858_2_) {
        if (p_190858_2_ > 8) {
            return false;
        }
        if (llama.inCaravan()) {
            if (llama.getCaravanHead().shouldLeadCaravan()) {
                return true;
            }
            EntityLeafcutterAnt llamaentity = llama.getCaravanHead();
            return this.firstIsSilverback(llamaentity, ++p_190858_2_);
        }
        return false;
    }
}
