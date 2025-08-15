/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCentipedeBody;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;

public class EntityCentipedeTail
extends EntityCentipedeBody {
    protected EntityCentipedeTail(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public MobType m_6336_() {
        return MobType.f_21642_;
    }
}
