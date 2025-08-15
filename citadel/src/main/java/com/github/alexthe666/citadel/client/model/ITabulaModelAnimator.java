/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.model;

import com.github.alexthe666.citadel.client.model.TabulaModel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
@FunctionalInterface
public interface ITabulaModelAnimator<T extends Entity> {
    public void setRotationAngles(TabulaModel var1, T var2, float var3, float var4, float var5, float var6, float var7, float var8);
}
