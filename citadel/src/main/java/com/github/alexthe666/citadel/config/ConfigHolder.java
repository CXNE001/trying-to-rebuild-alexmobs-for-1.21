/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.ForgeConfigSpec
 *  net.minecraftforge.common.ForgeConfigSpec$Builder
 *  org.apache.commons.lang3.tuple.Pair
 */
package com.github.alexthe666.citadel.config;

import com.github.alexthe666.citadel.config.ServerConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHolder {
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ServerConfig SERVER;

    static {
        Pair specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER = (ServerConfig)specPair.getLeft();
        SERVER_SPEC = (ForgeConfigSpec)specPair.getRight();
    }
}
