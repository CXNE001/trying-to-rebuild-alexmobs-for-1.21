/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.DefaultRandomPos
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityLaviathan;
import com.github.alexthe666.alexsmobs.entity.IHerdPanic;
import com.google.common.base.Predicate;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AnimalAIHerdPanic
extends Goal {
    protected final PathfinderMob creature;
    protected final double speed;
    protected final Predicate<? super PathfinderMob> targetEntitySelector;
    protected double randPosX;
    protected double randPosY;
    protected double randPosZ;
    protected boolean running;

    public AnimalAIHerdPanic(final PathfinderMob creature, double speedIn) {
        this.creature = creature;
        this.speed = speedIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.targetEntitySelector = new Predicate<PathfinderMob>(){

            public boolean apply(@Nullable PathfinderMob animal) {
                if (animal instanceof IHerdPanic && animal.m_6095_() == creature.m_6095_()) {
                    return ((IHerdPanic)animal).canPanic();
                }
                return false;
            }
        };
    }

    public boolean m_8036_() {
        BlockPos blockpos;
        if (this.creature.m_21188_() == null || !this.creature.m_21188_().m_6084_()) {
            return false;
        }
        if (this.creature.m_6060_() && !this.creature.m_5825_() && (blockpos = this.getRandPos((BlockGetter)this.creature.m_9236_(), (Entity)this.creature, 5, 4)) != null) {
            this.randPosX = blockpos.m_123341_();
            this.randPosY = blockpos.m_123342_();
            this.randPosZ = blockpos.m_123343_();
            return true;
        }
        if (this.creature.m_21188_() != null && this.creature instanceof IHerdPanic && ((IHerdPanic)this.creature).canPanic()) {
            List list = this.creature.m_9236_().m_6443_(this.creature.getClass(), this.getTargetableArea(), this.targetEntitySelector);
            for (PathfinderMob creatureEntity : list) {
                creatureEntity.m_6703_(this.creature.m_21188_());
            }
            return this.findRandomPositionFrom(this.creature.m_21188_());
        }
        return this.findRandomPosition();
    }

    private boolean findRandomPositionFrom(LivingEntity revengeTarget) {
        Vec3 vector3d = this.creature instanceof EntityLaviathan ? DefaultRandomPos.m_148407_((PathfinderMob)this.creature, (int)32, (int)16, (Vec3)revengeTarget.m_20182_()) : LandRandomPos.m_148521_((PathfinderMob)this.creature, (int)16, (int)7, (Vec3)revengeTarget.m_20182_());
        if (vector3d == null) {
            return false;
        }
        this.randPosX = vector3d.f_82479_;
        this.randPosY = vector3d.f_82480_;
        this.randPosZ = vector3d.f_82481_;
        return true;
    }

    protected AABB getTargetableArea() {
        Vec3 renderCenter = new Vec3(this.creature.m_20185_() + 0.5, this.creature.m_20186_() + 0.5, this.creature.m_20189_() + 0.5);
        double searchRadius = 15.0;
        AABB aabb = new AABB(-searchRadius, -searchRadius, -searchRadius, searchRadius, searchRadius, searchRadius);
        return aabb.m_82383_(renderCenter);
    }

    protected boolean findRandomPosition() {
        Vec3 vector3d = LandRandomPos.m_148488_((PathfinderMob)this.creature, (int)5, (int)4);
        if (vector3d == null) {
            return false;
        }
        this.randPosX = vector3d.f_82479_;
        this.randPosY = vector3d.f_82480_;
        this.randPosZ = vector3d.f_82481_;
        return true;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void m_8056_() {
        if (this.creature instanceof IHerdPanic) {
            ((IHerdPanic)this.creature).onPanic();
        }
        this.creature.m_21573_().m_26519_(this.randPosX, this.randPosY, this.randPosZ, this.speed);
        this.running = true;
    }

    public void m_8041_() {
        this.running = false;
    }

    public boolean m_8045_() {
        return !this.creature.m_21573_().m_26571_();
    }

    @Nullable
    protected BlockPos getRandPos(BlockGetter worldIn, Entity entityIn, int horizontalRange, int verticalRange) {
        BlockPos blockpos = entityIn.m_20183_();
        int i = blockpos.m_123341_();
        int j = blockpos.m_123342_();
        int k = blockpos.m_123343_();
        float f = horizontalRange * horizontalRange * verticalRange * 2;
        BlockPos blockpos1 = null;
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        for (int l = i - horizontalRange; l <= i + horizontalRange; ++l) {
            for (int i1 = j - verticalRange; i1 <= j + verticalRange; ++i1) {
                for (int j1 = k - horizontalRange; j1 <= k + horizontalRange; ++j1) {
                    float f1;
                    blockpos$mutable.m_122178_(l, i1, j1);
                    if (!worldIn.m_6425_((BlockPos)blockpos$mutable).m_205070_(FluidTags.f_13131_) || !((f1 = (float)((l - i) * (l - i) + (i1 - j) * (i1 - j) + (j1 - k) * (j1 - k))) < f)) continue;
                    f = f1;
                    blockpos1 = new BlockPos((Vec3i)blockpos$mutable);
                }
            }
        }
        return blockpos1;
    }
}
