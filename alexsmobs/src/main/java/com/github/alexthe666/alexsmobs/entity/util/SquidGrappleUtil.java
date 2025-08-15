/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.server.entity.CitadelEntityData
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity.util;

import com.github.alexthe666.alexsmobs.entity.EntitySquidGrapple;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SquidGrappleUtil {
    private static final String HOOK_1 = "SquidGrappleHook1AlexsMobs";
    private static final String HOOK_2 = "SquidGrappleHook2AlexsMobs";
    private static final String HOOK_3 = "SquidGrappleHook3AlexsMobs";
    private static final String HOOK_4 = "SquidGrappleHook4AlexsMobs";
    private static final String LAST_REPLACED_HOOK = "LastSquidGrappleHookAlexsMobs";

    public static int onFireHook(LivingEntity entity, UUID newHookUUID) {
        EntitySquidGrapple hook;
        int index;
        String indexStr;
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        if (tag.m_128441_(indexStr = SquidGrappleUtil.getHookStrFromIndex(index = SquidGrappleUtil.getFirstAvailableHookIndex(entity))) && (hook = SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(indexStr))) != null && !hook.m_213877_()) {
            hook.setWithdrawing(true);
        }
        tag.m_128362_(indexStr, newHookUUID);
        CitadelEntityData.setCitadelTag((LivingEntity)entity, (CompoundTag)tag);
        return index;
    }

    public static int getFirstAvailableHookIndex(LivingEntity entity) {
        int nulls = SquidGrappleUtil.getAnyNullHooks(entity);
        if (nulls != -1) {
            return nulls;
        }
        int i = SquidGrappleUtil.getHookCount(entity);
        if (i < 4) {
            return i;
        }
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        int j = tag.m_128451_(LAST_REPLACED_HOOK);
        tag.m_128405_(LAST_REPLACED_HOOK, (j + 1) % 4);
        CitadelEntityData.setCitadelTag((LivingEntity)entity, (CompoundTag)tag);
        return j;
    }

    public static String getHookStrFromIndex(int i) {
        switch (i) {
            case 0: {
                return HOOK_1;
            }
            case 1: {
                return HOOK_2;
            }
            case 2: {
                return HOOK_3;
            }
            case 3: {
                return HOOK_4;
            }
        }
        return HOOK_1;
    }

    public static int getAnyNullHooks(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        if (!tag.m_128441_(HOOK_1) || SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_1)) == null) {
            return 0;
        }
        if (!tag.m_128441_(HOOK_2) || SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_2)) == null) {
            return 1;
        }
        if (!tag.m_128441_(HOOK_3) || SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_3)) == null) {
            return 2;
        }
        if (!tag.m_128441_(HOOK_4) || SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_4)) == null) {
            return 3;
        }
        return -1;
    }

    public static int getHookCount(LivingEntity entity) {
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        int count = 0;
        if (tag.m_128441_(HOOK_1) && SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_1)) != null) {
            ++count;
        }
        if (tag.m_128441_(HOOK_2) && SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_2)) != null) {
            ++count;
        }
        if (tag.m_128441_(HOOK_3) && SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_3)) != null) {
            ++count;
        }
        if (tag.m_128441_(HOOK_4) && SquidGrappleUtil.getHookEntity(entity.m_9236_(), tag.m_128342_(HOOK_4)) != null) {
            ++count;
        }
        return count;
    }

    public static EntitySquidGrapple getHookEntity(Level level, UUID id) {
        if (id != null && !level.f_46443_) {
            Entity e = ((ServerLevel)level).m_8791_(id);
            return e instanceof EntitySquidGrapple ? (EntitySquidGrapple)e : null;
        }
        return null;
    }
}
