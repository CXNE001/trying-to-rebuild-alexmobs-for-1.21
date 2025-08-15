/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import com.github.alexthe666.alexsmobs.message.MessageKangarooInventorySync;
import java.util.function.Supplier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public static class MessageKangarooInventorySync.Handler {
    public static void handle(MessageKangarooInventorySync message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            Entity entity;
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = AlexsMobs.PROXY.getClientSidePlayer();
            }
            if (player != null && player.m_9236_() != null && (entity = player.m_9236_().m_6815_(message.kangaroo)) instanceof EntityKangaroo && ((EntityKangaroo)entity).kangarooInventory != null && message.slotId >= 0) {
                ((EntityKangaroo)entity).kangarooInventory.m_6836_(message.slotId, message.stack);
            }
        });
    }
}
