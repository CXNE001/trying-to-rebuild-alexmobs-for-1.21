/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.ModList
 */
package com.github.alexthe666.citadel.compat;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.compat.terrablender.TerrablenderCompat;
import net.minecraftforge.fml.ModList;

public class ModCompatBridge {
    private static boolean terrablender;

    public static void afterAllModsLoaded() {
        if (ModList.get().isLoaded("terrablender")) {
            Citadel.LOGGER.info("adding citadel surface rules via terrablender...");
            TerrablenderCompat.setup();
            terrablender = true;
        }
    }

    public static boolean usingTerrablender() {
        return terrablender;
    }
}
