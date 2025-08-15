/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.util.DefaultRandomPos
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.levelgen.structure.StructurePiece
 *  net.minecraft.world.level.levelgen.structure.StructureStart
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.Vec3;

public class MonsterAIWalkThroughHallsOfStructure
extends RandomStrollGoal {
    private TagKey<Structure> structureTagKey;
    private double maximumDistance = 0.0;
    private double maximumYDistance = 3.0;
    private int errorCooldown = 0;

    public MonsterAIWalkThroughHallsOfStructure(PathfinderMob mob, double speed, int chance, TagKey<Structure> structureTagKey, double maximumDistance) {
        super(mob, speed, chance, false);
        this.structureTagKey = structureTagKey;
        this.maximumDistance = 32.0;
    }

    public boolean m_8036_() {
        if (this.errorCooldown > 0) {
            --this.errorCooldown;
        }
        return super.m_8036_();
    }

    public void m_8037_() {
        super.m_8037_();
        if (this.errorCooldown > 0) {
            --this.errorCooldown;
        }
    }

    @Nullable
    protected Vec3 m_7037_() {
        StructureStart start = this.getNearestStructure(this.f_25725_.m_20183_());
        if (start != null && start.m_73603_() && this.errorCooldown <= 0) {
            List pieces = start.m_73602_();
            ArrayList<BlockPos> validPieceCenters = new ArrayList<BlockPos>();
            for (StructurePiece piece : pieces) {
                BoundingBox boundingbox = piece.m_73547_();
                BlockPos blockpos = boundingbox.m_162394_();
                BlockPos blockpos1 = new BlockPos(blockpos.m_123341_(), boundingbox.m_162396_(), blockpos.m_123343_());
                double yDist = Math.abs(blockpos1.m_123342_() - this.f_25725_.m_20183_().m_123342_());
                if (!(this.f_25725_.m_20238_(Vec3.m_82512_((Vec3i)blockpos1)) <= this.maximumDistance * this.maximumDistance) || !(yDist < this.maximumYDistance)) continue;
                validPieceCenters.add(blockpos1);
            }
            if (!validPieceCenters.isEmpty()) {
                BlockPos randomCenter = validPieceCenters.size() > 1 ? (BlockPos)validPieceCenters.get(this.f_25725_.m_217043_().m_188503_(validPieceCenters.size() - 1)) : (BlockPos)validPieceCenters.get(0);
                return Vec3.m_82512_((Vec3i)randomCenter.m_7918_(this.f_25725_.m_217043_().m_188503_(2) - 1, 0, this.f_25725_.m_217043_().m_188503_(2) - 1));
            }
        }
        return this.getPositionTowardsAnywhere();
    }

    @Nullable
    private Vec3 getPositionTowardsAnywhere() {
        return DefaultRandomPos.m_148403_((PathfinderMob)this.f_25725_, (int)10, (int)7);
    }

    @Nullable
    private StructureStart getNearestStructure(BlockPos pos) {
        ServerLevel serverlevel = (ServerLevel)this.f_25725_.m_9236_();
        try {
            StructureStart start = serverlevel.m_215010_().m_220491_(pos, this.structureTagKey);
            if (start.m_73603_()) {
                return start;
            }
            BlockPos nearestOf = serverlevel.m_215011_(this.structureTagKey, pos, (int)(this.maximumDistance / 16.0), false);
            if (nearestOf == null || nearestOf.m_203198_(this.f_25725_.m_20185_(), this.f_25725_.m_20186_(), this.f_25725_.m_20189_()) > 256.0 || !serverlevel.m_46749_(nearestOf)) {
                return null;
            }
            return serverlevel.m_215010_().m_220491_(nearestOf, this.structureTagKey);
        }
        catch (Exception e) {
            AlexsMobs.LOGGER.warn(this.f_25725_ + " encountered an issue searching for a nearby structure.");
            this.errorCooldown = 2000 + this.f_25725_.m_217043_().m_188503_(2000);
            return null;
        }
    }
}
