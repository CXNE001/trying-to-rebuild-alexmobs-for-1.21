/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class MessageSendVisualFlagFromServer {
    public int entityID;
    public int flag;

    public MessageSendVisualFlagFromServer(int entityID, int flag) {
        this.entityID = entityID;
        this.flag = flag;
    }

    public MessageSendVisualFlagFromServer() {
    }

    public static MessageSendVisualFlagFromServer read(FriendlyByteBuf buf) {
        return new MessageSendVisualFlagFromServer(buf.readInt(), buf.readInt());
    }

    public static void write(MessageSendVisualFlagFromServer message, FriendlyByteBuf buf) {
        buf.writeInt(message.entityID);
        buf.writeInt(message.flag);
    }

    public static class Handler {
        public static void handle(MessageSendVisualFlagFromServer message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> {
                ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
                if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                    player = AlexsMobs.PROXY.getClientSidePlayer();
                }
                if (player != null && player.m_9236_() != null) {
                    Entity entity = player.m_9236_().m_6815_(message.entityID);
                    AlexsMobs.PROXY.processVisualFlag(entity, message.flag);
                }
            });
        }
    }
}
