/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.Citadel
 *  com.github.alexthe666.citadel.server.entity.CitadelEntityData
 *  com.github.alexthe666.citadel.server.message.PropertiesMessage
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.util;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class FlyingFishBootsUtil {
    private static final String BOOST_TICKS = "FlyingFishBoostAlexsMobs";
    private static final int MIN_BOOST_TIME = 35;

    public static void setBoostTicks(LivingEntity entity, int ticks) {
        CompoundTag lassoedTag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        lassoedTag.m_128405_(BOOST_TICKS, ticks);
        CitadelEntityData.setCitadelTag((LivingEntity)entity, (CompoundTag)lassoedTag);
        if (!entity.m_9236_().f_46443_) {
            Citadel.sendMSGToAll((Object)new PropertiesMessage("CitadelPatreonConfig", lassoedTag, entity.m_19879_()));
        } else {
            Citadel.sendMSGToServer((Object)new PropertiesMessage("CitadelPatreonConfig", lassoedTag, entity.m_19879_()));
        }
    }

    public static int getBoostTicks(LivingEntity entity) {
        CompoundTag lassoedTag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)entity);
        if (lassoedTag.m_128441_(BOOST_TICKS)) {
            return lassoedTag.m_128451_(BOOST_TICKS);
        }
        return 0;
    }

    public static boolean isWearing(LivingEntity entity) {
        return entity.m_6844_(EquipmentSlot.FEET).m_41720_() == AMItemRegistry.FLYING_FISH_BOOTS.get();
    }

    public static void tickFlyingFishBoots(LivingEntity fishy) {
        int boostTime = FlyingFishBootsUtil.getBoostTicks(fishy);
        if (boostTime <= 15 && fishy.m_20072_() && !fishy.m_20096_() && fishy.m_204036_(FluidTags.f_13131_) < (double)0.4f && fishy.f_20899_ && (!(fishy instanceof Player) || !((Player)fishy).m_150110_().f_35935_)) {
            RandomSource rand = fishy.m_217043_();
            boostTime = 35;
            Vec3 forward = new Vec3(0.0, 0.0, (double)(0.5f + rand.m_188501_() * 1.2f)).m_82496_(-fishy.m_146909_() * ((float)Math.PI / 180)).m_82524_(-fishy.m_6080_() * ((float)Math.PI / 180));
            Vec3 delta = fishy.m_20184_().m_82549_(forward);
            fishy.m_20334_(delta.f_82479_, 0.3 + (double)(rand.m_188501_() * 0.3f), delta.f_82481_);
            fishy.m_146922_(fishy.m_6080_());
        }
        if (boostTime > 0) {
            if (!fishy.m_20072_() && !fishy.m_20096_()) {
                if (fishy.m_20184_().f_82480_ < 0.0) {
                    fishy.m_20256_(fishy.m_20184_().m_82542_(1.0, 0.75, 1.0));
                }
                fishy.m_20124_(Pose.FALL_FLYING);
            }
            FlyingFishBootsUtil.setBoostTicks(fishy, boostTime - 1);
        }
    }
}
