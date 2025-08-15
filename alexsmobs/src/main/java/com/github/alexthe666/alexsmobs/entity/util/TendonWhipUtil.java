/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.Citadel
 *  com.github.alexthe666.citadel.server.entity.CitadelEntityData
 *  com.github.alexthe666.citadel.server.message.PropertiesMessage
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity.util;

import com.github.alexthe666.alexsmobs.entity.EntityTendonSegment;
import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class TendonWhipUtil {
    private static final String LAST_TENDON_UUID = "LastTendonUUIDAlexsMobs";
    private static final String LAST_TENDON_ID = "LastTendonIDAlexsMobs";

    private static void sync(LivingEntity enchanted, CompoundTag tag) {
        CitadelEntityData.setCitadelTag((LivingEntity)enchanted, (CompoundTag)tag);
        if (!enchanted.m_9236_().f_46443_) {
            Citadel.sendMSGToAll((Object)new PropertiesMessage("CitadelTagUpdate", tag, enchanted.m_19879_()));
        } else {
            Citadel.sendMSGToServer((Object)new PropertiesMessage("CitadelTagUpdate", tag, enchanted.m_19879_()));
        }
    }

    public static void setLastTendon(LivingEntity entity, EntityTendonSegment tendon) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        if (tendon == null) {
            tag.m_128473_(LAST_TENDON_UUID);
            tag.m_128405_(LAST_TENDON_ID, -1);
        } else {
            tag.m_128362_(LAST_TENDON_UUID, tendon.m_20148_());
            tag.m_128405_(LAST_TENDON_ID, tendon.m_19879_());
        }
        TendonWhipUtil.sync(entity, tag);
    }

    private static UUID getLastTendonUUID(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        if (tag.m_128441_(LAST_TENDON_UUID)) {
            return tag.m_128342_(LAST_TENDON_UUID);
        }
        return null;
    }

    public static int getLastTendonId(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        if (tag.m_128441_(LAST_TENDON_ID)) {
            return tag.m_128451_(LAST_TENDON_ID);
        }
        return -1;
    }

    public static void retractFarTendons(Level level, LivingEntity player) {
        EntityTendonSegment last = TendonWhipUtil.getLastTendon(player);
        if (last != null) {
            last.m_142687_(Entity.RemovalReason.DISCARDED);
            TendonWhipUtil.setLastTendon(player, null);
        }
    }

    public static boolean canLaunchTendons(Level level, LivingEntity player) {
        EntityTendonSegment last = TendonWhipUtil.getLastTendon(player);
        if (last != null) {
            return last.m_213877_() || last.m_20270_((Entity)player) > 30.0f;
        }
        return true;
    }

    public static EntityTendonSegment getLastTendon(LivingEntity player) {
        UUID uuid = TendonWhipUtil.getLastTendonUUID(player);
        int id = TendonWhipUtil.getLastTendonId(player);
        if (!player.m_9236_().f_46443_) {
            if (uuid != null) {
                Entity e = player.m_9236_().m_6815_(id);
                return e instanceof EntityTendonSegment ? (EntityTendonSegment)e : null;
            }
        } else if (id != -1) {
            Entity e = player.m_9236_().m_6815_(id);
            return e instanceof EntityTendonSegment ? (EntityTendonSegment)e : null;
        }
        return null;
    }
}
