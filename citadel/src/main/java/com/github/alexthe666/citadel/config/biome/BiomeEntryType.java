/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.config.biome;

@Deprecated(since="2.6.2")
public enum BiomeEntryType {
    REGISTRY_NAME(false),
    BIOME_TAG(false),
    BIOME_DICT(true),
    BIOME_CATEGORY(true);

    private final boolean depreciated;

    private BiomeEntryType(boolean depreciated) {
        this.depreciated = depreciated;
    }

    public boolean isDepreciated() {
        return this.depreciated;
    }
}
