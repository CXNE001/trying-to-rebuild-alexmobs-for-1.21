/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.citadel.server.message;

import com.github.alexthe666.citadel.client.render.pathfinding.PathfindingDebugRenderer;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.MNode;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MessageSyncPath {
    public Set<MNode> lastDebugNodesVisited = new HashSet<MNode>();
    public Set<MNode> lastDebugNodesNotVisited = new HashSet<MNode>();
    public Set<MNode> lastDebugNodesPath = new HashSet<MNode>();

    public MessageSyncPath(Set<MNode> lastDebugNodesVisited, Set<MNode> lastDebugNodesNotVisited, Set<MNode> lastDebugNodesPath) {
        this.lastDebugNodesVisited = lastDebugNodesVisited;
        this.lastDebugNodesNotVisited = lastDebugNodesNotVisited;
        this.lastDebugNodesPath = lastDebugNodesPath;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.lastDebugNodesVisited.size());
        for (MNode MNode2 : this.lastDebugNodesVisited) {
            MNode2.serializeToBuf(buf);
        }
        buf.writeInt(this.lastDebugNodesNotVisited.size());
        for (MNode MNode2 : this.lastDebugNodesNotVisited) {
            MNode2.serializeToBuf(buf);
        }
        buf.writeInt(this.lastDebugNodesPath.size());
        for (MNode MNode2 : this.lastDebugNodesPath) {
            MNode2.serializeToBuf(buf);
        }
    }

    public static MessageSyncPath read(FriendlyByteBuf buf) {
        int size = buf.readInt();
        HashSet<MNode> lastDebugNodesVisited = new HashSet<MNode>();
        for (int i = 0; i < size; ++i) {
            lastDebugNodesVisited.add(new MNode(buf));
        }
        size = buf.readInt();
        HashSet<MNode> lastDebugNodesNotVisited = new HashSet<MNode>();
        for (int i = 0; i < size; ++i) {
            lastDebugNodesNotVisited.add(new MNode(buf));
        }
        size = buf.readInt();
        HashSet<MNode> lastDebugNodesPath = new HashSet<MNode>();
        for (int i = 0; i < size; ++i) {
            lastDebugNodesPath.add(new MNode(buf));
        }
        return new MessageSyncPath(lastDebugNodesVisited, lastDebugNodesNotVisited, lastDebugNodesPath);
    }

    public static class Handler {
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
}
