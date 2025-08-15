/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.block.entity.BannerPattern
 *  net.minecraftforge.registries.DeferredRegister
 */
package com.github.alexthe666.alexsmobs.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;

public class AMBannerRegistry {
    public static final DeferredRegister<BannerPattern> DEF_REG = DeferredRegister.create((ResourceKey)Registries.f_256969_, (String)"alexsmobs");

    static {
        DEF_REG.register("bear", () -> new BannerPattern("bear"));
        DEF_REG.register("australia_0", () -> new BannerPattern("australia_0"));
        DEF_REG.register("australia_1", () -> new BannerPattern("australia_1"));
        DEF_REG.register("new_mexico", () -> new BannerPattern("new_mexico"));
        DEF_REG.register("brazil", () -> new BannerPattern("brazil"));
    }
}
