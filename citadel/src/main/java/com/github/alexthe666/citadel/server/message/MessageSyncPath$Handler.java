/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.citadel.server.message;

import com.github.alexthe666.citadel.client.render.pathfinding.PathfindingDebugRenderer;
import com.github.alexthe666.citadel.server.message.MessageSyncPath;
import java.util.function.Supplier;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public static class MessageSyncPath.Handler {
    public static boolean handle(MessageSyncPath message, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            ((NetworkEvent.Context)contextSupplier.get()).setPacketHandled(true);
            if (((NetworkEvent.Context)contextSupplier.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                PathfindingDebugRenderer.lastDebugNodesVisited = message.lastDebugNodesVisited;
                PathfindingDebugRenderer.lastDebugNodesNotVisited = message.lastDebugNodesNotVisited;
                PathfindingDebugRenderer.lastDebugNodesPath = message.lastDebugNodesPath;
            }
        });
        return true;
    }
}
