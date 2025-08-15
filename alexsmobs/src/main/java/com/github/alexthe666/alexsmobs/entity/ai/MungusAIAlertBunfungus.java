/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.target.TargetGoal
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityBunfungus;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MungusAIAlertBunfungus
extends TargetGoal {
    private static final TargetingConditions HURT_BY_TARGETING = TargetingConditions.m_148352_().m_148355_().m_26893_();
    private static final int ALERT_RANGE_Y = 10;
    private boolean alertSameType;
    private int timestamp;
    private final Class<?>[] toIgnoreDamage;
    @Nullable
    private Class<?>[] toIgnoreAlert;

    public MungusAIAlertBunfungus(PathfinderMob p_26039_, Class<?> ... p_26040_) {
        super((Mob)p_26039_, true);
        this.toIgnoreDamage = p_26040_;
        this.m_7021_(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean m_8036_() {
        int i = this.f_26135_.m_21213_();
        LivingEntity livingentity = this.f_26135_.m_21188_();
        if (i != this.timestamp && livingentity != null) {
            if (livingentity.m_6095_() == EntityType.f_20532_ && this.f_26135_.m_9236_().m_46469_().m_46207_(GameRules.f_46127_)) {
                return false;
            }
            for (Class<?> oclass : this.toIgnoreDamage) {
                if (!oclass.isAssignableFrom(livingentity.getClass())) continue;
                return false;
            }
            return this.m_26150_(livingentity, HURT_BY_TARGETING);
        }
        return false;
    }

    public void m_8056_() {
        this.f_26135_.m_6710_(this.f_26135_.m_21188_());
        this.f_26137_ = this.f_26135_.m_5448_();
        this.timestamp = this.f_26135_.m_21213_();
        this.f_26138_ = 300;
        this.alertOthers();
        super.m_8056_();
    }

    protected void alertOthers() {
        double d0 = this.m_7623_();
        AABB aabb = AABB.m_82333_((Vec3)this.f_26135_.m_20182_()).m_82377_(d0, 10.0, d0);
        List list = this.f_26135_.m_9236_().m_6443_(EntityBunfungus.class, aabb, EntitySelector.f_20408_);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            EntityBunfungus mob = (EntityBunfungus)((Object)iterator.next());
            if (this.f_26135_ == mob || mob.m_5448_() != null || mob.m_7307_((Entity)this.f_26135_.m_21188_()) || !mob.defendsMungusAgainst(this.f_26135_.m_21188_())) continue;
            if (this.toIgnoreAlert != null) {
                boolean flag = false;
                for (Class<?> oclass : this.toIgnoreAlert) {
                    if (((Object)((Object)mob)).getClass() != oclass) continue;
                    flag = true;
                    break;
                }
                if (flag) continue;
            }
            this.alertOther((Mob)mob, this.f_26135_.m_21188_());
        }
        return;
    }

    protected void alertOther(Mob p_26042_, LivingEntity p_26043_) {
        p_26042_.m_6710_(p_26043_);
    }
}
