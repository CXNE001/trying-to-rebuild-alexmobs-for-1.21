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
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class ElephantAIFollowCaravan
extends Goal {
    public final EntityElephant elephant;
    private double speedModifier;
    private int distCheckCounter;

    public ElephantAIFollowCaravan(EntityElephant llamaIn, double speedModifierIn) {
        this.elephant = llamaIn;
        this.speedModifier = speedModifierIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (this.elephant.aiItemFlag || this.elephant.m_6688_() != null) {
            return false;
        }
        if (!(this.elephant.isTusked() || this.elephant.inCaravan() || this.elephant.isSitting())) {
            double dist = 32.0;
            List list = this.elephant.m_9236_().m_45976_(EntityElephant.class, this.elephant.m_20191_().m_82377_(dist, dist / 2.0, dist));
            EntityElephant elephant = null;
            double d0 = Double.MAX_VALUE;
            for (Entity entity : list) {
                double d1;
                EntityElephant elephant1 = (EntityElephant)entity;
                if (!elephant1.inCaravan() || elephant1.hasCaravanTrail() || (d1 = this.elephant.m_20280_((Entity)elephant1)) > d0) continue;
                d0 = d1;
                elephant = elephant1;
            }
            if (elephant == null) {
                for (Entity entity1 : list) {
                    double d2;
                    EntityElephant llamaentity2 = (EntityElephant)entity1;
                    if (!llamaentity2.isTusked() || llamaentity2.m_6162_() || llamaentity2.hasCaravanTrail() || (d2 = this.elephant.m_20280_((Entity)llamaentity2)) > d0) continue;
                    d0 = d2;
                    elephant = llamaentity2;
                }
            }
            if (elephant == null) {
                return false;
            }
            if (d0 < 5.0) {
                return false;
            }
            if (!(elephant.isTusked() || elephant.m_6162_() || this.firstIsTusk(elephant, 1))) {
                return false;
            }
            this.elephant.joinCaravan(elephant);
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        if (this.elephant.isSitting() || this.elephant.aiItemFlag) {
            return false;
        }
        if (this.elephant.inCaravan() && this.elephant.getCaravanHead().m_6084_() && this.firstIsTusk(this.elephant, 0)) {
            double d0 = this.elephant.m_20280_((Entity)this.elephant.getCaravanHead());
            if (d0 > 676.0) {
                if (this.speedModifier <= 1.0) {
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
        this.elephant.leaveCaravan();
        this.speedModifier = 1.0;
    }

    public void m_8037_() {
        EntityElephant llamaentity;
        if (this.elephant.inCaravan() && !this.elephant.isSitting() && (llamaentity = this.elephant.getCaravanHead()) != null) {
            double d0 = this.elephant.m_20270_((Entity)llamaentity);
            Vec3 vector3d = new Vec3(llamaentity.m_20185_() - this.elephant.m_20185_(), llamaentity.m_20186_() - this.elephant.m_20186_(), llamaentity.m_20189_() - this.elephant.m_20189_()).m_82541_().m_82490_(Math.max(d0 - 4.0, 0.0));
            if (this.elephant.m_21573_().m_26571_()) {
                try {
                    this.elephant.m_21573_().m_26519_(this.elephant.m_20185_() + vector3d.f_82479_, this.elephant.m_20186_() + vector3d.f_82480_, this.elephant.m_20189_() + vector3d.f_82481_, this.speedModifier);
                }
                catch (NullPointerException e) {
                    AlexsMobs.LOGGER.warn("elephant encountered issue following caravan head");
                }
            }
        }
    }

    private boolean firstIsTusk(EntityElephant llama, int p_190858_2_) {
        if (p_190858_2_ > 8) {
            return false;
        }
        if (llama.inCaravan()) {
            if (llama.getCaravanHead().isTusked() && !llama.getCaravanHead().m_6162_()) {
                return true;
            }
            EntityElephant llamaentity = llama.getCaravanHead();
            return this.firstIsTusk(llamaentity, ++p_190858_2_);
        }
        return false;
    }
}
