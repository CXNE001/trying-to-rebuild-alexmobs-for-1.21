/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.util.RandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.ai.LavaAndWaterAIRandomSwimming;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.phys.Vec3;

public class LaviathanAIRandomSwimming
extends LavaAndWaterAIRandomSwimming {
    public LaviathanAIRandomSwimming(PathfinderMob creature, double speed, int chance) {
        super(creature, speed, chance);
    }

    @Override
    @Nullable
    protected Vec3 m_7037_() {
        BlockPos pos = this.f_25725_.m_20183_().m_121955_((Vec3i)RandomPos.m_217851_((RandomSource)this.f_25725_.m_217043_(), (int)16, (int)5));
        int i = 0;
        while (pos != null && this.f_25725_.m_9236_().m_8055_(new BlockPos((Vec3i)pos)).m_60819_().m_76178_() && i++ < 10) {
            pos = this.f_25725_.m_20183_().m_121955_((Vec3i)RandomPos.m_217851_((RandomSource)this.f_25725_.m_217043_(), (int)16, (int)5));
        }
        if (this.f_25725_.m_9236_().m_8055_(new BlockPos((Vec3i)pos)).m_60819_().m_76178_()) {
            return null;
        }
        if (this.f_25725_.m_217043_().m_188503_(3) == 0) {
            while (!this.f_25725_.m_9236_().m_8055_(pos).m_60819_().m_76178_() && pos.m_123342_() < this.f_25725_.m_9236_().m_151558_()) {
                pos = pos.m_7494_();
            }
            pos = pos.m_7495_();
        }
        return new Vec3((double)((float)pos.m_123341_() + 0.5f), (double)((float)pos.m_123342_() + 0.5f), (double)((float)pos.m_123343_() + 0.5f));
    }
}
