/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.message.MessageUpdateTransmutablesToDisplay;
import java.util.function.Supplier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public static class MessageUpdateTransmutablesToDisplay.Handler {
    public static void handle(MessageUpdateTransmutablesToDisplay message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = AlexsMobs.PROXY.getClientSidePlayer();
            }
            if (player.m_19879_() == message.playerId) {
                AlexsMobs.PROXY.setDisplayTransmuteResult(0, message.stack1);
                AlexsMobs.PROXY.setDisplayTransmuteResult(1, message.stack2);
                AlexsMobs.PROXY.setDisplayTransmuteResult(2, message.stack3);
            }
        });
    }
}
