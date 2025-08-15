/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.server.message.PacketBufferUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.citadel.server.message.PacketBufferUtils;
import io.netty.buffer.ByteBuf;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class MessageUpdateTransmutablesToDisplay {
    private int playerId;
    public ItemStack stack1;
    public ItemStack stack2;
    public ItemStack stack3;

    public MessageUpdateTransmutablesToDisplay(int playerId, ItemStack stack1, ItemStack stack2, ItemStack stack3) {
        this.stack1 = stack1;
        this.stack2 = stack2;
        this.stack3 = stack3;
        this.playerId = playerId;
    }

    public MessageUpdateTransmutablesToDisplay() {
    }

    public static MessageUpdateTransmutablesToDisplay read(FriendlyByteBuf buf) {
        return new MessageUpdateTransmutablesToDisplay(buf.readInt(), PacketBufferUtils.readItemStack((ByteBuf)buf), PacketBufferUtils.readItemStack((ByteBuf)buf), PacketBufferUtils.readItemStack((ByteBuf)buf));
    }

    public static void write(MessageUpdateTransmutablesToDisplay message, FriendlyByteBuf buf) {
        buf.writeInt(message.playerId);
        PacketBufferUtils.writeItemStack((ByteBuf)buf, (ItemStack)message.stack1);
        PacketBufferUtils.writeItemStack((ByteBuf)buf, (ItemStack)message.stack2);
        PacketBufferUtils.writeItemStack((ByteBuf)buf, (ItemStack)message.stack3);
    }

    public static class Handler {
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
}
