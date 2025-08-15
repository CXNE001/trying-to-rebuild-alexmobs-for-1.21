/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.village.poi.PoiManager
 *  net.minecraft.world.entity.ai.village.poi.PoiManager$Occupancy
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMPointOfInterestRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityLeafcutterAnthill;
import com.google.common.base.Predicates;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

private class EntityLeafcutterAnt.ReturnToHiveGoal
extends Goal {
    private int searchCooldown = 1;
    private BlockPos hivePos;
    private int approachTime = 0;
    private int moveToCooldown = 0;

    public boolean m_8036_() {
        if (EntityLeafcutterAnt.this.stayOutOfHiveCountdown > 0) {
            return false;
        }
        if (EntityLeafcutterAnt.this.hasLeaf() || EntityLeafcutterAnt.this.isQueen()) {
            --this.searchCooldown;
            BlockPos hive = EntityLeafcutterAnt.this.hivePos;
            if (hive != null && EntityLeafcutterAnt.this.m_9236_().m_7702_(hive) instanceof TileEntityLeafcutterAnthill) {
                this.hivePos = hive;
                return true;
            }
            if (this.searchCooldown <= 0) {
                this.searchCooldown = 400;
                PoiManager pointofinterestmanager = ((ServerLevel)EntityLeafcutterAnt.this.m_9236_()).m_8904_();
                Stream stream = pointofinterestmanager.m_27138_(poiTypeHolder -> poiTypeHolder.m_203565_(AMPointOfInterestRegistry.LEAFCUTTER_ANT_HILL.getKey()), (Predicate)Predicates.alwaysTrue(), EntityLeafcutterAnt.this.m_20183_(), 100, PoiManager.Occupancy.ANY);
                List listOfHives = stream.collect(Collectors.toList());
                BlockPos ret = null;
                for (BlockPos pos : listOfHives) {
                    if (ret != null && !(pos.m_123331_((Vec3i)EntityLeafcutterAnt.this.m_20183_()) < ret.m_123331_((Vec3i)EntityLeafcutterAnt.this.m_20183_()))) continue;
                    ret = pos;
                }
                this.hivePos = ret;
                EntityLeafcutterAnt.this.hivePos = ret;
                return this.hivePos != null;
            }
        }
        return false;
    }

    public boolean m_8045_() {
        return this.hivePos != null && EntityLeafcutterAnt.this.m_20238_(Vec3.m_82514_((Vec3i)this.hivePos, (double)1.0)) > 1.0;
    }

    public void m_8041_() {
        this.hivePos = null;
        this.searchCooldown = 20;
        this.approachTime = 0;
    }

    public void m_8056_() {
        this.searchCooldown = 20;
        this.approachTime = 0;
        this.moveToCooldown = 10 + EntityLeafcutterAnt.this.f_19796_.m_188503_(10);
    }

    public void m_8037_() {
        if (this.moveToCooldown > 0) {
            --this.moveToCooldown;
        }
        if (this.hivePos != null) {
            BlockEntity tileentity;
            double dist = EntityLeafcutterAnt.this.m_20238_(Vec3.m_82514_((Vec3i)this.hivePos, (double)1.0));
            if (dist < (double)1.2f && EntityLeafcutterAnt.this.m_20099_().equals((Object)this.hivePos) && (tileentity = EntityLeafcutterAnt.this.m_9236_().m_7702_(this.hivePos)) instanceof TileEntityLeafcutterAnthill) {
                TileEntityLeafcutterAnthill beehivetileentity = (TileEntityLeafcutterAnthill)tileentity;
                beehivetileentity.tryEnterHive(EntityLeafcutterAnt.this, EntityLeafcutterAnt.this.hasLeaf());
            }
            if (dist < 16.0) {
                ++this.approachTime;
                if (dist < 4.0) {
                    Vec3 center = Vec3.m_82514_((Vec3i)this.hivePos, (double)1.1f);
                    Vec3 add = center.m_82546_(EntityLeafcutterAnt.this.m_20182_());
                    if (add.m_82553_() > 1.0) {
                        add = add.m_82541_();
                    }
                    add = add.m_82490_((double)0.2f);
                    EntityLeafcutterAnt.this.m_20256_(EntityLeafcutterAnt.this.m_20184_().m_82549_(add));
                }
                int n = this.approachTime < 200 ? 2 : 10;
                if (dist < (double)n && EntityLeafcutterAnt.this.m_20186_() >= (double)this.hivePos.m_123342_()) {
                    if (EntityLeafcutterAnt.this.getAttachmentFacing() != Direction.DOWN) {
                        EntityLeafcutterAnt.this.m_20256_(EntityLeafcutterAnt.this.m_20184_().m_82520_(0.0, 0.1, 0.0));
                    }
                    EntityLeafcutterAnt.this.m_21566_().m_6849_((double)this.hivePos.m_123341_() + 0.5, (double)this.hivePos.m_123342_() + 1.5, (double)this.hivePos.m_123343_() + 0.5, 1.0);
                }
                if (this.moveToCooldown <= 0) {
                    this.moveToCooldown = 50 + EntityLeafcutterAnt.this.f_19796_.m_188503_(30);
                    EntityLeafcutterAnt.this.f_21344_.m_26566_();
                    EntityLeafcutterAnt.this.f_21344_.m_26519_((double)this.hivePos.m_123341_() + 0.5, (double)this.hivePos.m_123342_() + (double)1.6f, (double)this.hivePos.m_123343_() + 0.5, 1.0);
                }
            } else {
                this.startMovingToFar(this.hivePos);
            }
        }
    }

    private boolean startMovingToFar(BlockPos pos) {
        if (this.moveToCooldown <= 0) {
            this.moveToCooldown = 50 + EntityLeafcutterAnt.this.f_19796_.m_188503_(30);
            EntityLeafcutterAnt.this.f_21344_.m_26529_(10.0f);
            EntityLeafcutterAnt.this.f_21344_.m_26519_((double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), 1.0);
        }
        return EntityLeafcutterAnt.this.f_21344_.m_26570_() != null && EntityLeafcutterAnt.this.f_21344_.m_26570_().m_77403_();
    }
}
