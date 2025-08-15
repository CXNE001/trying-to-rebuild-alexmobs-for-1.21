/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
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
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MessageSyncPathReached {
    public Set<BlockPos> reached = new HashSet<BlockPos>();

    public MessageSyncPathReached(Set<BlockPos> reached) {
        this.reached = reached;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.reached.size());
        for (BlockPos node : this.reached) {
            buf.m_130064_(node);
        }
    }

    public static MessageSyncPathReached read(FriendlyByteBuf buf) {
        int size = buf.readInt();
        HashSet<BlockPos> reached = new HashSet<BlockPos>();
        for (int i = 0; i < size; ++i) {
            reached.add(buf.m_130135_());
        }
        return new MessageSyncPathReached(reached);
    }

    public static class Handler {
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
}
