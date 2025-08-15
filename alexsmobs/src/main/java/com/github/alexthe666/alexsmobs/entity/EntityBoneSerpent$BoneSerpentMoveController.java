/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityBoneSerpent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

static class EntityBoneSerpent.BoneSerpentMoveController
extends MoveControl {
    private final EntityBoneSerpent dolphin;

    public EntityBoneSerpent.BoneSerpentMoveController(EntityBoneSerpent dolphinIn) {
        super((Mob)dolphinIn);
        this.dolphin = dolphinIn;
    }

    public void m_8126_() {
        if (this.dolphin.m_20069_() || this.dolphin.m_20077_()) {
            this.dolphin.m_20256_(this.dolphin.m_20184_().m_82520_(0.0, 0.005, 0.0));
        }
        if (this.f_24981_ == MoveControl.Operation.MOVE_TO && !this.dolphin.m_21573_().m_26571_()) {
            double d2;
            double d1;
            double d0 = this.f_24975_ - this.dolphin.m_20185_();
            double d3 = d0 * d0 + (d1 = this.f_24976_ - this.dolphin.m_20186_()) * d1 + (d2 = this.f_24977_ - this.dolphin.m_20189_()) * d2;
            if (d3 < 2.500000277905201E-7) {
                this.f_24974_.m_21564_(0.0f);
            } else {
                float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
                this.dolphin.m_146922_(this.m_24991_(this.dolphin.m_146908_(), f, 10.0f));
                this.dolphin.f_20883_ = this.dolphin.m_146908_();
                this.dolphin.f_20885_ = this.dolphin.m_146908_();
                float f1 = (float)(this.f_24978_ * this.dolphin.m_21133_(Attributes.f_22279_));
                if (this.dolphin.m_20069_() || this.dolphin.m_20077_()) {
                    this.dolphin.m_7910_(f1 * 0.02f);
                    float f2 = -((float)(Mth.m_14136_((double)d1, (double)Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2)))) * 57.2957763671875));
                    f2 = Mth.m_14036_((float)Mth.m_14177_((float)f2), (float)-85.0f, (float)85.0f);
                    this.dolphin.m_20256_(this.dolphin.m_20184_().m_82520_(0.0, (double)this.dolphin.m_6113_() * d1 * 0.6, 0.0));
                    this.dolphin.m_146926_(this.m_24991_(this.dolphin.m_146909_(), f2, 1.0f));
                    float f3 = Mth.m_14089_((float)(this.dolphin.m_146909_() * ((float)Math.PI / 180)));
                    float f4 = Mth.m_14031_((float)(this.dolphin.m_146909_() * ((float)Math.PI / 180)));
                    this.dolphin.f_20902_ = f3 * f1;
                    this.dolphin.f_20901_ = -f4 * f1;
                } else {
                    this.dolphin.m_7910_(f1 * 0.1f);
                }
            }
        } else {
            this.dolphin.m_7910_(0.0f);
            this.dolphin.m_21570_(0.0f);
            this.dolphin.m_21567_(0.0f);
            this.dolphin.m_21564_(0.0f);
        }
    }
}
