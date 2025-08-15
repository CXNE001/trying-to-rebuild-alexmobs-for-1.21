/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.fml.loading.FMLPaths
 *  org.apache.commons.io.FileUtils
 */
package com.github.alexthe666.citadel.config.biome;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;

@Deprecated(since="2.6.2")
public class SpawnBiomeConfig {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(SpawnBiomeData.class, (Object)new SpawnBiomeData.Deserializer()).create();
    private final ResourceLocation fileName;

    private SpawnBiomeConfig(ResourceLocation fileName) {
        this.fileName = !fileName.m_135827_().endsWith(".json") ? new ResourceLocation(fileName.m_135827_(), fileName.m_135815_() + ".json") : fileName;
    }

    public static SpawnBiomeData create(ResourceLocation fileName, SpawnBiomeData dataDefault) {
        SpawnBiomeConfig config = new SpawnBiomeConfig(fileName);
        SpawnBiomeData data = config.getConfigData(dataDefault);
        return data;
    }

    public static <T> T getOrCreateConfigFile(File configDir, String configName, T defaults, Type type) {
        File configFile = new File(configDir, configName);
        if (!configFile.exists()) {
            try {
                FileUtils.write((File)configFile, (CharSequence)GSON.toJson(defaults));
            }
            catch (IOException e) {
                Citadel.LOGGER.error("Spawn Biome Config: Could not write " + configFile, (Throwable)e);
            }
        }
        try {
            return (T)GSON.fromJson(FileUtils.readFileToString((File)configFile), type);
        }
        catch (Exception e) {
            Citadel.LOGGER.error("Spawn Biome Config: Could not load " + configFile, (Throwable)e);
            return defaults;
        }
    }

    private File getConfigDirFile() {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path jsonPath = Paths.get(configPath.toAbsolutePath().toString(), this.fileName.m_135827_());
        return jsonPath.toFile();
    }

    private SpawnBiomeData getConfigData(SpawnBiomeData defaultConfigData) {
        SpawnBiomeData configData = SpawnBiomeConfig.getOrCreateConfigFile(this.getConfigDirFile(), this.fileName.m_135815_(), defaultConfigData, new TypeToken<SpawnBiomeData>(){}.getType());
        return configData;
    }
}
