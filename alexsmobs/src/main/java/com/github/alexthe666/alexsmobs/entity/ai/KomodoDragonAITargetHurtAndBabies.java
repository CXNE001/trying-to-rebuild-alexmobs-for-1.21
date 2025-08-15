/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class KomodoDragonAITargetHurtAndBabies
extends NearestAttackableTargetGoal {
    public KomodoDragonAITargetHurtAndBabies(Mob goalOwnerIn, Class targetClassIn, boolean checkSight) {
        super(goalOwnerIn, targetClassIn, checkSight);
    }
}
