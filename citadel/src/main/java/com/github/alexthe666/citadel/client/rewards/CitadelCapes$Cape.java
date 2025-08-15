/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.citadel.client.rewards;

import java.util.List;
import java.util.UUID;
import net.minecraft.resources.ResourceLocation;

public static class CitadelCapes.Cape {
    private final List<UUID> isFor;
    private final String identifier;
    private final ResourceLocation texture;

    public CitadelCapes.Cape(List<UUID> isFor, String identifier, ResourceLocation texture) {
        this.isFor = isFor;
        this.identifier = identifier;
        this.texture = texture;
    }

    public List<UUID> getIsFor() {
        return this.isFor;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public boolean isFor(UUID uuid) {
        return this.isFor.contains(uuid);
    }
}
