/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.ForgeConfigSpec
 *  net.minecraftforge.common.ForgeConfigSpec$Builder
 *  org.apache.commons.lang3.tuple.Pair
 */
package com.github.alexthe666.alexsmobs.config;

import com.github.alexthe666.alexsmobs.config.CommonConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class ConfigHolder {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        Pair specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = (CommonConfig)specPair.getLeft();
        COMMON_SPEC = (ForgeConfigSpec)specPair.getRight();
    }
}
