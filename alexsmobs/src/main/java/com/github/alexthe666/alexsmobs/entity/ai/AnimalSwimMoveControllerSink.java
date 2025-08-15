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

import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class AnimalSwimMoveControllerSink
extends MoveControl {
    private final PathfinderMob entity;
    private final float speedMulti;
    private float ySpeedMod = 1.0f;
    private float yawLimit = 10.0f;

    public AnimalSwimMoveControllerSink(PathfinderMob entity, float speedMulti, float ySpeedMod) {
        super((Mob)entity);
        this.entity = entity;
        this.speedMulti = speedMulti;
        this.ySpeedMod = ySpeedMod;
    }

    public AnimalSwimMoveControllerSink(PathfinderMob entity, float speedMulti, float ySpeedMod, float yawLimit) {
        super((Mob)entity);
        this.entity = entity;
        this.speedMulti = speedMulti;
        this.ySpeedMod = ySpeedMod;
        this.yawLimit = yawLimit;
    }

    public void m_8126_() {
        if (this.entity instanceof ISemiAquatic && ((ISemiAquatic)this.entity).shouldStopMoving()) {
            this.entity.m_7910_(0.0f);
            return;
        }
        if (this.f_24981_ == MoveControl.Operation.MOVE_TO && !this.entity.m_21573_().m_26571_()) {
            double lvt_5_1_;
            double lvt_3_1_;
            double lvt_1_1_ = this.f_24975_ - this.entity.m_20185_();
            double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + (lvt_3_1_ = this.f_24976_ - this.entity.m_20186_()) * lvt_3_1_ + (lvt_5_1_ = this.f_24977_ - this.entity.m_20189_()) * lvt_5_1_;
            if (lvt_7_1_ < 2.500000277905201E-7) {
                this.f_24974_.m_21564_(0.0f);
            } else {
                float lvt_9_1_ = (float)(Mth.m_14136_((double)lvt_5_1_, (double)lvt_1_1_) * 57.2957763671875) - 90.0f;
                this.entity.m_146922_(this.m_24991_(this.entity.m_146908_(), lvt_9_1_, this.yawLimit));
                this.entity.f_20883_ = this.entity.m_146908_();
                this.entity.f_20885_ = this.entity.m_146908_();
                float lvt_10_1_ = (float)(this.f_24978_ * (double)this.speedMulti * 3.0 * this.entity.m_21133_(Attributes.f_22279_));
                if (this.entity.m_20069_()) {
                    if (lvt_3_1_ > 0.0 && this.entity.f_19862_) {
                        this.entity.m_20256_(this.entity.m_20184_().m_82520_(0.0, (double)0.08f, 0.0));
                    } else {
                        this.entity.m_20256_(this.entity.m_20184_().m_82520_(0.0, (double)this.entity.m_6113_() * lvt_3_1_ * 0.6 * (double)this.ySpeedMod, 0.0));
                    }
                    this.entity.m_7910_(lvt_10_1_ * 0.02f);
                    float lvt_11_1_ = -((float)(Mth.m_14136_((double)lvt_3_1_, (double)Mth.m_14116_((float)((float)(lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_)))) * 57.2957763671875));
                    lvt_11_1_ = Mth.m_14036_((float)Mth.m_14177_((float)lvt_11_1_), (float)-85.0f, (float)85.0f);
                    this.entity.m_146926_(this.m_24991_(this.entity.m_146909_(), lvt_11_1_, 5.0f));
                    float lvt_12_1_ = Mth.m_14089_((float)(this.entity.m_146909_() * ((float)Math.PI / 180)));
                    float lvt_13_1_ = Mth.m_14031_((float)(this.entity.m_146909_() * ((float)Math.PI / 180)));
                    this.entity.f_20902_ = lvt_12_1_ * lvt_10_1_;
                    this.entity.f_20901_ = -lvt_13_1_ * lvt_10_1_;
                } else {
                    this.entity.m_7910_(lvt_10_1_ * 0.1f);
                }
            }
        } else {
            if (this.entity instanceof EntityMimicOctopus && !this.entity.m_20096_()) {
                this.entity.m_20256_(this.entity.m_20184_().m_82520_(0.0, -0.02, 0.0));
            }
            this.entity.m_7910_(0.0f);
            this.entity.m_21570_(0.0f);
            this.entity.m_21567_(0.0f);
            this.entity.m_21564_(0.0f);
        }
    }
}
