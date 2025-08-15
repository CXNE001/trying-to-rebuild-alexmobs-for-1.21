/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.Citadel
 *  com.github.alexthe666.citadel.server.entity.CitadelEntityData
 *  com.github.alexthe666.citadel.server.message.PropertiesMessage
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.entity.util;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class VineLassoUtil {
    private static final String LASSO_PACKET = "LassoSentPacketAlexsMobs";
    private static final String LASSO_REMOVED = "LassoRemovedAlexsMobs";
    private static final String LASSOED_TO_TAG = "LassoOwnerAlexsMobs";
    private static final String LASSOED_TO_ENTITY_ID_TAG = "LassoOwnerIDAlexsMobs";

    public static void lassoTo(@Nullable LivingEntity lassoer, LivingEntity lassoed) {
        CompoundTag lassoedTag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)lassoed);
        if (lassoer == null) {
            lassoedTag.m_128362_(LASSOED_TO_TAG, UUID.randomUUID());
            lassoedTag.m_128405_(LASSOED_TO_ENTITY_ID_TAG, -1);
            lassoedTag.m_128379_(LASSO_REMOVED, true);
        } else if (!lassoedTag.m_128441_(LASSOED_TO_ENTITY_ID_TAG) || lassoedTag.m_128451_(LASSOED_TO_ENTITY_ID_TAG) == -1) {
            lassoedTag.m_128362_(LASSOED_TO_TAG, lassoer.m_20148_());
            lassoedTag.m_128405_(LASSOED_TO_ENTITY_ID_TAG, lassoer.m_19879_());
            lassoedTag.m_128379_(LASSO_REMOVED, false);
        }
        lassoedTag.m_128379_(LASSO_PACKET, true);
        CitadelEntityData.setCitadelTag((LivingEntity)lassoed, (CompoundTag)lassoedTag);
        if (!lassoed.m_9236_().f_46443_) {
            Citadel.sendMSGToAll((Object)new PropertiesMessage("CitadelPatreonConfig", lassoedTag, lassoed.m_19879_()));
        }
    }

    public static boolean hasLassoData(LivingEntity lasso) {
        CompoundTag lassoedTag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)lasso);
        return lassoedTag.m_128441_(LASSOED_TO_ENTITY_ID_TAG) && !lassoedTag.m_128471_(LASSO_REMOVED) && lassoedTag.m_128451_(LASSOED_TO_ENTITY_ID_TAG) != -1;
    }

    public static Entity getLassoedTo(LivingEntity lassoed) {
        CompoundTag lassoedTag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)lassoed);
        if (lassoedTag.m_128471_(LASSO_REMOVED)) {
            return null;
        }
        if (VineLassoUtil.hasLassoData(lassoed)) {
            Entity found;
            UUID uuid;
            if (lassoed.m_9236_().f_46443_ && lassoedTag.m_128441_(LASSOED_TO_ENTITY_ID_TAG)) {
                int i = lassoedTag.m_128451_(LASSOED_TO_ENTITY_ID_TAG);
                if (i != -1) {
                    Entity found2 = lassoed.m_9236_().m_6815_(i);
                    if (found2 != null) {
                        return found2;
                    }
                    UUID uuid2 = lassoedTag.m_128342_(LASSOED_TO_TAG);
                    if (uuid2 != null) {
                        return lassoed.m_9236_().m_46003_(uuid2);
                    }
                }
            } else if (lassoed.m_9236_() instanceof ServerLevel && (uuid = lassoedTag.m_128342_(LASSOED_TO_TAG)) != null && (found = ((ServerLevel)lassoed.m_9236_()).m_8791_(uuid)) != null) {
                lassoedTag.m_128405_(LASSOED_TO_ENTITY_ID_TAG, found.m_19879_());
                return found;
            }
        }
        return null;
    }

    public static void tickLasso(LivingEntity lassoed) {
        Entity lassoedOwner;
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)lassoed);
        if (!lassoed.m_9236_().f_46443_ && (tag.m_128441_(LASSO_PACKET) || tag.m_128471_(LASSO_REMOVED))) {
            tag.m_128379_(LASSO_PACKET, false);
            CitadelEntityData.setCitadelTag((LivingEntity)lassoed, (CompoundTag)tag);
            Citadel.sendMSGToAll((Object)new PropertiesMessage("CitadelPatreonConfig", tag, lassoed.m_19879_()));
        }
        if ((lassoedOwner = VineLassoUtil.getLassoedTo(lassoed)) != null) {
            double distance = lassoed.m_20270_(lassoedOwner);
            if (lassoed instanceof Mob) {
                Mob mob = (Mob)lassoed;
                if (distance > 3.0) {
                    mob.m_21573_().m_5624_(lassoedOwner, 1.0);
                } else {
                    mob.m_21573_().m_26573_();
                }
            }
            if (distance > 10.0) {
                double d0 = (lassoedOwner.m_20185_() - lassoed.m_20185_()) / distance;
                double d1 = (lassoedOwner.m_20186_() - lassoed.m_20186_()) / distance;
                double d2 = (lassoedOwner.m_20189_() - lassoed.m_20189_()) / distance;
                double yd = Math.copySign(d1 * d1 * 0.4, d1);
                if (lassoed instanceof Player) {
                    yd = 0.0;
                }
                lassoed.m_20256_(lassoed.m_20184_().m_82520_(Math.copySign(d0 * d0 * 0.4, d0), yd, Math.copySign(d2 * d2 * 0.4, d2)));
            }
        }
    }
}
