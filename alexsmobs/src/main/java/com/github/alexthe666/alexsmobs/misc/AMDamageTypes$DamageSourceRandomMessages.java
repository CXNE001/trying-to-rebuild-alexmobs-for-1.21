/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageType
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.alexsmobs.misc;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

private static class AMDamageTypes.DamageSourceRandomMessages
extends DamageSource {
    public AMDamageTypes.DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2, @Nullable Vec3 from) {
        super(damageTypeHolder, entity1, entity2, from);
    }

    public AMDamageTypes.DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2) {
        super(damageTypeHolder, entity1, entity2);
    }

    public AMDamageTypes.DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, Vec3 from) {
        super(damageTypeHolder, from);
    }

    public AMDamageTypes.DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity) {
        super(damageTypeHolder, entity);
    }

    public AMDamageTypes.DamageSourceRandomMessages(Holder<DamageType> p_270475_) {
        super(p_270475_);
    }

    public Component m_6157_(LivingEntity attacked) {
        int type = attacked.m_217043_().m_188503_(3);
        LivingEntity livingentity = attacked.m_21232_();
        String s = "death.attack." + this.m_19385_() + "_" + type;
        String s1 = s + ".player";
        return livingentity != null ? Component.m_237110_((String)s1, (Object[])new Object[]{attacked.m_5446_(), livingentity.m_5446_()}) : Component.m_237110_((String)s, (Object[])new Object[]{attacked.m_5446_()});
    }
}
