/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator
 *  net.minecraft.world.level.pathfinder.PathFinder
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SemiAquaticPathNavigator
extends WaterBoundPathNavigation {
    public SemiAquaticPathNavigator(Mob entitylivingIn, Level worldIn) {
        super(entitylivingIn, worldIn);
    }

    protected PathFinder m_5532_(int p_179679_1_) {
        this.f_26508_ = new AmphibiousNodeEvaluator(true);
        return new PathFinder(this.f_26508_, p_179679_1_);
    }

    protected boolean m_7632_() {
        return true;
    }

    protected Vec3 m_7475_() {
        return new Vec3(this.f_26494_.m_20185_(), this.f_26494_.m_20227_(0.5), this.f_26494_.m_20189_());
    }

    protected boolean canMoveDirectly(Vec3 posVec31, Vec3 posVec32, int sizeX, int sizeY, int sizeZ) {
        Vec3 vector3d = new Vec3(posVec32.f_82479_, posVec32.f_82480_ + (double)this.f_26494_.m_20206_() * 0.5, posVec32.f_82481_);
        return this.f_26495_.m_45547_(new ClipContext(posVec31, vector3d, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this.f_26494_)).m_6662_() == HitResult.Type.MISS;
    }

    public boolean m_6342_(BlockPos pos) {
        return !this.f_26495_.m_8055_(pos.m_7495_()).m_60795_();
    }

    public void m_7008_(boolean canSwim) {
    }
}
