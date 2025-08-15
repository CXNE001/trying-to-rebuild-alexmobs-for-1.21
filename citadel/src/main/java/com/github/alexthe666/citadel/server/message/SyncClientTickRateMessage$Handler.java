/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.citadel.server.message;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.message.SyncClientTickRateMessage;
import java.util.function.Supplier;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public static class SyncClientTickRateMessage.Handler {
    public static void handle(SyncClientTickRateMessage message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                Citadel.PROXY.handleClientTickRatePacket(message.compound);
            }
        });
    }
}
