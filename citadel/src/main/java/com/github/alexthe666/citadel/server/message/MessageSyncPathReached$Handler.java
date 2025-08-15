/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.citadel.server.message;

import com.github.alexthe666.citadel.client.render.pathfinding.PathfindingDebugRenderer;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.MNode;
import com.github.alexthe666.citadel.server.message.MessageSyncPathReached;
import java.util.function.Supplier;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public static class MessageSyncPathReached.Handler {
    public static boolean handle(MessageSyncPathReached message, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            ((NetworkEvent.Context)contextSupplier.get()).setPacketHandled(true);
            if (((NetworkEvent.Context)contextSupplier.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                for (MNode node : PathfindingDebugRenderer.lastDebugNodesPath) {
                    if (!message.reached.contains(node.pos)) continue;
                    node.setReachedByWorker(true);
                }
            }
        });
        return true;
    }
}
