/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Bee
 *  net.minecraft.world.level.pathfinder.Path
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityGrizzlyBear;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.google.common.base.Predicate;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class GrizzlyBearAIFleeBees
extends Goal {
    private final double farSpeed;
    private final double nearSpeed;
    private final float avoidDistance;
    private final Predicate<Bee> avoidTargetSelector = new Predicate<Bee>(){

        public boolean apply(@Nullable Bee p_apply_1_) {
            return p_apply_1_.m_6084_() && GrizzlyBearAIFleeBees.this.entity.m_21574_().m_148306_((Entity)p_apply_1_) && !GrizzlyBearAIFleeBees.this.entity.m_7307_((Entity)p_apply_1_) && p_apply_1_.m_6784_() > 0;
        }
    };
    protected EntityGrizzlyBear entity;
    protected Bee closestLivingEntity;
    private Path path;

    public GrizzlyBearAIFleeBees(EntityGrizzlyBear entityIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
        this.entity = entityIn;
        this.avoidDistance = avoidDistanceIn;
        this.farSpeed = farSpeedIn;
        this.nearSpeed = nearSpeedIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (this.entity.m_21824_()) {
            return false;
        }
        if (this.entity.isSitting() && !this.entity.forcedSit) {
            this.entity.m_21839_(false);
        }
        if (this.entity.isSitting()) {
            return false;
        }
        List beeEntities = this.entity.m_9236_().m_6443_(Bee.class, this.entity.m_20191_().m_82377_((double)this.avoidDistance, 8.0, (double)this.avoidDistance), this.avoidTargetSelector);
        if (beeEntities.isEmpty()) {
            return false;
        }
        this.closestLivingEntity = (Bee)beeEntities.get(0);
        Vec3 vec3d = LandRandomPos.m_148521_((PathfinderMob)this.entity, (int)16, (int)7, (Vec3)new Vec3(this.closestLivingEntity.m_20185_(), this.closestLivingEntity.m_20186_(), this.closestLivingEntity.m_20189_()));
        if (vec3d == null) {
            return false;
        }
        if (this.closestLivingEntity.m_20275_(vec3d.f_82479_, vec3d.f_82480_, vec3d.f_82481_) < this.closestLivingEntity.m_20280_((Entity)this.entity)) {
            return false;
        }
        this.path = this.entity.m_21573_().m_7864_(AMBlockPos.fromCoords(vec3d.f_82479_, vec3d.f_82480_, vec3d.f_82481_), 0);
        return this.path != null;
    }

    public boolean m_8045_() {
        return !this.entity.m_21573_().m_26571_();
    }

    public void m_8056_() {
        this.entity.m_21573_().m_26536_(this.path, this.farSpeed);
    }

    public void m_8041_() {
        this.entity.m_21573_().m_26573_();
        this.closestLivingEntity = null;
    }

    public void m_8037_() {
        if (this.closestLivingEntity != null && this.closestLivingEntity.m_6784_() <= 0) {
            this.m_8041_();
        }
        this.entity.m_21573_().m_26517_(this.getRunSpeed());
    }

    public double getRunSpeed() {
        return 0.7f;
    }
}
