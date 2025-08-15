/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.StructureTags
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.village.poi.PoiManager
 *  net.minecraft.world.entity.ai.village.poi.PoiManager$Occupancy
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.level.gameevent.GameEvent
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotEcho;
import com.github.alexthe666.alexsmobs.message.MessageSetPupfishChunkOnClient;
import com.github.alexthe666.alexsmobs.misc.AMPointOfInterestRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.world.AMWorldData;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemEcholocator
extends Item {
    public EchoType type;

    public ItemEcholocator(Item.Properties properties, EchoType ender) {
        super(properties);
        this.type = ender;
    }

    private List<BlockPos> getNearbyPortals(BlockPos blockpos, ServerLevel world, int range) {
        if (this.type == EchoType.ENDER) {
            PoiManager pointofinterestmanager = world.m_8904_();
            Stream stream = pointofinterestmanager.m_27138_(poiTypeHolder -> poiTypeHolder.m_203565_(AMPointOfInterestRegistry.END_PORTAL_FRAME.getKey()), (Predicate)Predicates.alwaysTrue(), blockpos, range, PoiManager.Occupancy.ANY);
            List<BlockPos> portals = stream.collect(Collectors.toList());
            if (portals.isEmpty()) {
                BlockPos nearestMapStructure = world.m_215011_(StructureTags.f_215882_, blockpos, 100, false);
                return nearestMapStructure == null ? Collections.emptyList() : List.of(nearestMapStructure);
            }
            return portals;
        }
        if (this.type == EchoType.PUPFISH) {
            AMWorldData data = AMWorldData.get((Level)world);
            if (data != null && data.getPupfishChunk() != null) {
                AlexsMobs.sendMSGToAll(new MessageSetPupfishChunkOnClient(data.getPupfishChunk().f_45578_, data.getPupfishChunk().f_45579_));
                if (!data.isInPupfishChunk(blockpos)) {
                    return Collections.singletonList(data.getPupfishChunk().m_151394_(blockpos.m_123342_()));
                }
            }
            return Collections.emptyList();
        }
        RandomSource random = world.m_213780_();
        for (int i = 0; i < 256; ++i) {
            BlockPos checkPos = blockpos.m_7918_(random.m_188503_(range) - range / 2, random.m_188503_(range) / 2 - range / 2, random.m_188503_(range) - range / 2);
            if (!this.isCaveAir((Level)world, checkPos)) continue;
            return Collections.singletonList(checkPos);
        }
        return Collections.emptyList();
    }

    private boolean isCaveAir(Level world, BlockPos checkPos) {
        return world.m_8055_(checkPos).m_60795_() && world.m_45517_(LightLayer.SKY, checkPos) == 0 && world.m_45517_(LightLayer.BLOCK, checkPos) < 4;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player livingEntityIn, InteractionHand handIn) {
        ItemStack stack = livingEntityIn.m_21120_(handIn);
        boolean left = false;
        if (livingEntityIn.m_7655_() == InteractionHand.OFF_HAND && livingEntityIn.m_5737_() == HumanoidArm.RIGHT || livingEntityIn.m_7655_() == InteractionHand.MAIN_HAND && livingEntityIn.m_5737_() == HumanoidArm.LEFT) {
            left = true;
        }
        EntityCachalotEcho whaleEcho = new EntityCachalotEcho(worldIn, (LivingEntity)livingEntityIn, !left, this.type == EchoType.PUPFISH);
        if (!worldIn.f_46443_ && worldIn instanceof ServerLevel) {
            BlockPos playerPos = livingEntityIn.m_20183_();
            List<BlockPos> portals = this.getNearbyPortals(playerPos, (ServerLevel)worldIn, 128);
            BlockPos pos = null;
            if (this.type == EchoType.ENDER) {
                for (BlockPos portalPos : portals) {
                    if (pos != null && !(pos.m_123331_((Vec3i)playerPos) > portalPos.m_123331_((Vec3i)playerPos))) continue;
                    pos = portalPos;
                }
            } else if (this.type == EchoType.PUPFISH) {
                for (BlockPos portalPos : portals) {
                    if (pos != null && !(pos.m_123331_((Vec3i)playerPos) > portalPos.m_123331_((Vec3i)playerPos))) continue;
                    pos = portalPos;
                }
            } else {
                CompoundTag nbt = stack.m_41784_();
                if (nbt.m_128441_("CavePos") && nbt.m_128471_("ValidCavePos")) {
                    pos = BlockPos.m_122022_((long)nbt.m_128454_("CavePos"));
                    if (this.isCaveAir(worldIn, pos) || 1000000.0 < pos.m_123331_((Vec3i)playerPos)) {
                        nbt.m_128379_("ValidCavePos", false);
                    }
                } else {
                    for (BlockPos portalPos : portals) {
                        if (pos != null && !(pos.m_123331_((Vec3i)playerPos) < portalPos.m_123331_((Vec3i)playerPos))) continue;
                        pos = portalPos;
                    }
                    if (pos != null) {
                        nbt.m_128356_("CavePos", pos.m_121878_());
                        nbt.m_128379_("ValidCavePos", true);
                        stack.m_41751_(nbt);
                    }
                }
            }
            if (pos != null) {
                double d0 = (double)((float)pos.m_123341_() + 0.5f) - whaleEcho.m_20185_();
                double d1 = (double)((float)pos.m_123342_() + 0.5f) - whaleEcho.m_20186_();
                double d2 = (double)((float)pos.m_123343_() + 0.5f) - whaleEcho.m_20189_();
                whaleEcho.f_19797_ = 15;
                whaleEcho.shoot(d0, d1, d2, 0.4f, 0.3f);
                worldIn.m_7967_((Entity)whaleEcho);
                livingEntityIn.m_146850_(GameEvent.f_223698_);
                worldIn.m_6263_((Player)null, whaleEcho.m_20185_(), whaleEcho.m_20186_(), whaleEcho.m_20189_(), (SoundEvent)AMSoundRegistry.CACHALOT_WHALE_CLICK.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                stack.m_41622_(1, (LivingEntity)livingEntityIn, player -> player.m_21190_(livingEntityIn.m_7655_()));
            }
        }
        livingEntityIn.m_36335_().m_41524_((Item)this, 5);
        return InteractionResultHolder.m_19092_((Object)stack, (boolean)worldIn.m_5776_());
    }

    public static enum EchoType {
        ECHOLOCATION,
        ENDER,
        PUPFISH;

    }
}
