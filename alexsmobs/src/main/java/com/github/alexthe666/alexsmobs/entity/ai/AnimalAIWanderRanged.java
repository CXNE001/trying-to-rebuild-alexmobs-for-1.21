/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

public class AnimalAIWanderRanged
extends RandomStrollGoal {
    protected final float probability;
    protected final int xzRange;
    protected final int yRange;

    public AnimalAIWanderRanged(PathfinderMob creature, int chance, double speedIn, int xzRange, int yRange) {
        this(creature, chance, speedIn, 0.001f, xzRange, yRange);
    }

    public boolean m_8036_() {
        Vec3 lvt_1_1_;
        if (this.f_25725_.m_20160_() && !(this.f_25725_ instanceof EntityKangaroo)) {
            return false;
        }
        if (!this.f_25731_) {
            if (this.f_25725_.m_21216_() >= 100) {
                return false;
            }
            if (this.f_25725_.m_217043_().m_188503_(this.f_25730_) != 0) {
                return false;
            }
        }
        if ((lvt_1_1_ = this.m_7037_()) == null) {
            return false;
        }
        this.f_25726_ = lvt_1_1_.f_82479_;
        this.f_25727_ = lvt_1_1_.f_82480_;
        this.f_25728_ = lvt_1_1_.f_82481_;
        this.f_25731_ = false;
        return true;
    }

    public AnimalAIWanderRanged(PathfinderMob creature, int chance, double speedIn, float probabilityIn, int xzRange, int yRange) {
        super(creature, speedIn, chance);
        this.probability = probabilityIn;
        this.xzRange = xzRange;
        this.yRange = yRange;
    }

    @Nullable
    protected Vec3 m_7037_() {
        if (this.f_25725_.m_20072_()) {
            Vec3 vector3d = LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)this.xzRange, (int)this.yRange);
            return vector3d == null ? super.m_7037_() : vector3d;
        }
        return this.f_25725_.m_217043_().m_188501_() >= this.probability ? LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)this.xzRange, (int)this.yRange) : super.m_7037_();
    }
}
