/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import java.util.Comparator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

public record MantisShrimpAIBreakBlocks.BlockSorter(Entity entity) implements Comparator<BlockPos>
{
    @Override
    public int compare(BlockPos pos1, BlockPos pos2) {
        double distance1 = this.getDistance(pos1);
        double distance2 = this.getDistance(pos2);
        return Double.compare(distance1, distance2);
    }

    private double getDistance(BlockPos pos) {
        double deltaX = this.entity.m_20185_() - ((double)pos.m_123341_() + 0.5);
        double deltaY = this.entity.m_20186_() + (double)this.entity.m_20192_() - ((double)pos.m_123342_() + 0.5);
        double deltaZ = this.entity.m_20189_() - ((double)pos.m_123343_() + 0.5);
        return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
    }
}
