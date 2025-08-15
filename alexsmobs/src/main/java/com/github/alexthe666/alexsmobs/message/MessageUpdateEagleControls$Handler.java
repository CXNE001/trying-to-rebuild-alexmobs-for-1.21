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
import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import com.github.alexthe666.alexsmobs.message.MessageUpdateEagleControls;
import java.util.function.Supplier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public static class MessageUpdateEagleControls.Handler {
    public static void handle(MessageUpdateEagleControls message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            Entity entity;
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = AlexsMobs.PROXY.getClientSidePlayer();
            }
            if (player != null && player.m_9236_() != null && (entity = player.m_9236_().m_6815_(message.eagleId)) instanceof EntityBaldEagle) {
                Entity over = null;
                if (message.overEntityId >= 0) {
                    over = player.m_9236_().m_6815_(message.overEntityId);
                }
                ((EntityBaldEagle)entity).directFromPlayer(message.rotationYaw, message.rotationPitch, message.chunkLoad, over);
            }
        });
    }
}
