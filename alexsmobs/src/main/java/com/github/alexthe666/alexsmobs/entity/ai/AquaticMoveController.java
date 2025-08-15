/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.EntityWarpedToad;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class AquaticMoveController
extends MoveControl {
    private final PathfinderMob entity;
    private final float speedMulti;
    private float yawLimit = 3.0f;

    public AquaticMoveController(PathfinderMob entity, float speedMulti) {
        super((Mob)entity);
        this.entity = entity;
        this.speedMulti = speedMulti;
    }

    public AquaticMoveController(PathfinderMob entity, float speedMulti, float yawLimit) {
        super((Mob)entity);
        this.entity = entity;
        this.yawLimit = yawLimit;
        this.speedMulti = speedMulti;
    }

    public void m_8126_() {
        if (this.entity.m_20069_() || this.entity instanceof EntityWarpedToad && this.entity.m_20077_()) {
            this.entity.m_20256_(this.entity.m_20184_().m_82520_(0.0, 0.005, 0.0));
        }
        if (this.entity instanceof ISemiAquatic && ((ISemiAquatic)this.entity).shouldStopMoving()) {
            this.entity.m_7910_(0.0f);
            return;
        }
        if (this.f_24981_ == MoveControl.Operation.MOVE_TO && !this.entity.m_21573_().m_26571_()) {
            double d0 = this.f_24975_ - this.entity.m_20185_();
            double d1 = this.f_24976_ - this.entity.m_20186_();
            double d2 = this.f_24977_ - this.entity.m_20189_();
            double d3 = Mth.m_14116_((float)((float)(d0 * d0 + d1 * d1 + d2 * d2)));
            d1 /= d3;
            float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
            if (this.entity instanceof EntityGiantSquid) {
                ((EntityGiantSquid)this.entity).directPitch(d0, d1, d2, d3);
            } else {
                this.entity.m_146922_(this.m_24991_(this.entity.m_146908_(), f, this.yawLimit));
                this.entity.f_20883_ = this.entity.m_146908_();
            }
            float f1 = (float)(this.f_24978_ * this.entity.m_21133_(Attributes.f_22279_) * (double)this.speedMulti);
            this.entity.m_7910_(f1 * 0.4f);
            this.entity.m_20256_(this.entity.m_20184_().m_82520_(0.0, (double)this.entity.m_6113_() * d1 * 0.6, 0.0));
        } else {
            this.entity.m_7910_(0.0f);
        }
    }
}
