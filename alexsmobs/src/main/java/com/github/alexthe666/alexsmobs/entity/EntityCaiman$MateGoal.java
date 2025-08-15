/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.stats.Stats
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.level.GameRules
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCaiman;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.GameRules;

static class EntityCaiman.MateGoal
extends BreedGoal {
    private final EntityCaiman caiman;

    EntityCaiman.MateGoal(EntityCaiman caiman, double speedIn) {
        super((Animal)caiman, speedIn);
        this.caiman = caiman;
    }

    public boolean m_8036_() {
        return super.m_8036_() && !this.caiman.hasEgg();
    }

    protected void m_8026_() {
        ServerPlayer serverplayerentity = this.f_25113_.m_27592_();
        if (serverplayerentity == null && this.f_25115_.m_27592_() != null) {
            serverplayerentity = this.f_25115_.m_27592_();
        }
        if (serverplayerentity != null) {
            serverplayerentity.m_36220_(Stats.f_12937_);
            CriteriaTriggers.f_10581_.m_147278_(serverplayerentity, this.f_25113_, this.f_25115_, (AgeableMob)this.f_25113_);
        }
        this.caiman.setHasEgg(true);
        this.f_25113_.m_27594_();
        this.f_25115_.m_27594_();
        this.f_25113_.m_146762_(6000);
        this.f_25115_.m_146762_(6000);
        RandomSource random = this.f_25113_.m_217043_();
        if (this.f_25114_.m_46469_().m_46207_(GameRules.f_46135_)) {
            this.f_25114_.m_7967_((Entity)new ExperienceOrb(this.f_25114_, this.f_25113_.m_20185_(), this.f_25113_.m_20186_(), this.f_25113_.m_20189_(), random.m_188503_(7) + 1));
        }
    }
}
