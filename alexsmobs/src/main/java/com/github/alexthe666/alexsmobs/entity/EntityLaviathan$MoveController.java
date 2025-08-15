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

import com.github.alexthe666.alexsmobs.entity.EntityLaviathan;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

static class EntityLaviathan.MoveController
extends MoveControl {
    private final EntityLaviathan laviathan;

    public EntityLaviathan.MoveController(EntityLaviathan dolphinIn) {
        super((Mob)dolphinIn);
        this.laviathan = dolphinIn;
    }

    public void m_8126_() {
        float speed = (float)(this.f_24978_ * 3.0 * this.laviathan.m_21133_(Attributes.f_22279_));
        if (!(this.f_24981_ != MoveControl.Operation.MOVE_TO || this.laviathan.m_21573_().m_26571_() && this.laviathan.m_6688_() == null)) {
            double lvt_5_1_;
            double lvt_3_1_;
            double lvt_1_1_ = this.f_24975_ - this.laviathan.m_20185_();
            double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + (lvt_3_1_ = this.f_24976_ - this.laviathan.m_20186_()) * lvt_3_1_ + (lvt_5_1_ = this.f_24977_ - this.laviathan.m_20189_()) * lvt_5_1_;
            if (lvt_7_1_ < 2.5) {
                this.laviathan.m_21564_(0.0f);
            } else {
                float lvt_9_1_ = (float)(Mth.m_14136_((double)lvt_5_1_, (double)lvt_1_1_) * 57.2957763671875) - 90.0f;
                this.laviathan.m_146922_(this.m_24991_(this.laviathan.m_146908_(), lvt_9_1_, 5.0f));
                this.laviathan.m_5616_(this.m_24991_(this.laviathan.m_6080_(), lvt_9_1_, 90.0f));
                if (this.laviathan.shouldSwim()) {
                    this.laviathan.m_7910_(speed * 0.03f);
                    float lvt_11_1_ = -((float)(Mth.m_14136_((double)lvt_3_1_, (double)Mth.m_14116_((float)((float)(lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_)))) * 57.2957763671875));
                    lvt_11_1_ = Mth.m_14036_((float)Mth.m_14177_((float)lvt_11_1_), (float)-85.0f, (float)85.0f);
                    this.laviathan.m_146926_(this.m_24991_(this.laviathan.m_146909_(), lvt_11_1_, 25.0f));
                    float lvt_12_1_ = Mth.m_14089_((float)(this.laviathan.m_146909_() * ((float)Math.PI / 180)));
                    float lvt_13_1_ = Mth.m_14031_((float)(this.laviathan.m_146909_() * ((float)Math.PI / 180)));
                    this.laviathan.f_20902_ = lvt_12_1_ * speed;
                    this.laviathan.f_20901_ = -lvt_13_1_ * speed;
                } else {
                    this.laviathan.m_7910_(speed * 0.1f);
                }
            }
        } else if (!this.laviathan.m_9236_().m_8055_(this.laviathan.m_20183_().m_7494_()).m_60819_().m_76178_() && this.laviathan.getChillTime() <= 0) {
            this.laviathan.m_20256_(this.laviathan.m_20184_().m_82520_(0.0, -0.05, 0.0));
        }
    }
}
