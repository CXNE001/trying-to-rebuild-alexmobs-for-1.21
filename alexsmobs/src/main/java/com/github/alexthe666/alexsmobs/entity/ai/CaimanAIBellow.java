/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeMod
 *  net.minecraftforge.fluids.FluidType
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityCaiman;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.EnumSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;

public class CaimanAIBellow
extends Goal {
    private final EntityCaiman caiman;
    private int bellowTime = 0;

    public CaimanAIBellow(EntityCaiman caiman) {
        this.caiman = caiman;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return this.caiman.m_5448_() == null && this.caiman.bellowCooldown <= 0 && this.caiman.m_20072_() && !this.caiman.shouldFollow();
    }

    public boolean m_8045_() {
        return super.m_8045_() && this.bellowTime < 60;
    }

    public void m_8041_() {
        this.bellowTime = 0;
        this.caiman.bellowCooldown = 1000 + this.caiman.m_217043_().m_188503_(1000);
        this.caiman.setBellowing(false);
    }

    public void m_8037_() {
        if (this.caiman.m_20072_()) {
            double d1 = this.caiman.getFluidTypeHeight((FluidType)ForgeMod.WATER_TYPE.get());
            this.caiman.m_21573_().m_26573_();
            if (d1 > (double)0.3f) {
                double d2 = Math.pow(d1 - (double)0.3f, 2.0);
                this.caiman.m_20256_(new Vec3(this.caiman.m_20184_().f_82479_, Math.min(d2 * (double)0.08f, (double)0.04f), this.caiman.m_20184_().f_82481_));
            } else {
                this.caiman.m_20256_(new Vec3(this.caiman.m_20184_().f_82479_, (double)-0.02f, this.caiman.m_20184_().f_82481_));
            }
            if (d1 > (double)0.19f && d1 < 0.5) {
                ++this.bellowTime;
                this.caiman.m_5496_((SoundEvent)AMSoundRegistry.CAIMAN_SPLASH.get(), 1.0f, this.caiman.m_6100_());
                this.caiman.setBellowing(true);
            }
        }
    }
}
