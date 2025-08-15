/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.citadel.client.rewards;

import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CitadelCapes {
    private static final List<Cape> CAPES = new ArrayList<Cape>();
    private static final Map<UUID, Boolean> HAS_CAPES_ENABLED = new LinkedHashMap<UUID, Boolean>();

    public static void addCapeFor(List<UUID> uuids, String translationKey, ResourceLocation texture) {
        CAPES.add(new Cape(uuids, translationKey, texture));
    }

    public static List<Cape> getCapesFor(UUID uuid) {
        return CAPES.isEmpty() ? CAPES : CAPES.stream().filter(cape -> cape.isFor(uuid)).toList();
    }

    public static Cape getNextCape(String currentID, UUID playerUUID) {
        int i;
        if (CAPES.isEmpty()) {
            return null;
        }
        int currentIndex = -1;
        for (i = 0; i < CAPES.size(); ++i) {
            if (!CAPES.get(i).getIdentifier().equals(currentID)) continue;
            currentIndex = i;
            break;
        }
        for (i = currentIndex + 1; i < CAPES.size(); ++i) {
            if (!CAPES.get(i).isFor(playerUUID)) continue;
            return CAPES.get(i);
        }
        return null;
    }

    @Nullable
    public static Cape getById(String identifier) {
        for (int i = 0; i < CAPES.size(); ++i) {
            if (!CAPES.get(i).getIdentifier().equals(identifier)) continue;
            return CAPES.get(i);
        }
        return null;
    }

    @Nullable
    private static Cape getFirstApplicable(Player player) {
        for (int i = 0; i < CAPES.size(); ++i) {
            if (!CAPES.get(i).isFor(player.m_20148_())) continue;
            return CAPES.get(i);
        }
        return null;
    }

    public static Cape getCurrentCape(Player player) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)player);
        if (tag.m_128471_("CitadelCapeDisabled")) {
            return null;
        }
        if (tag.m_128441_("CitadelCapeType")) {
            if (tag.m_128461_("CitadelCapeType").isEmpty()) {
                return CitadelCapes.getFirstApplicable(player);
            }
            return CitadelCapes.getById(tag.m_128461_("CitadelCapeType"));
        }
        return null;
    }

    public static class Cape {
        private final List<UUID> isFor;
        private final String identifier;
        private final ResourceLocation texture;

        public Cape(List<UUID> isFor, String identifier, ResourceLocation texture) {
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
}
