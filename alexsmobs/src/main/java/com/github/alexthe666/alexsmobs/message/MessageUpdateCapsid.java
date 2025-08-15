/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.server.message.PacketBufferUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityCapsid;
import com.github.alexthe666.citadel.server.message.PacketBufferUtils;
import io.netty.buffer.ByteBuf;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class MessageUpdateCapsid {
    public long blockPos;
    public ItemStack heldStack;

    public MessageUpdateCapsid(long blockPos, ItemStack heldStack) {
        this.blockPos = blockPos;
        this.heldStack = heldStack;
    }

    public MessageUpdateCapsid() {
    }

    public static MessageUpdateCapsid read(FriendlyByteBuf buf) {
        return new MessageUpdateCapsid(buf.readLong(), PacketBufferUtils.readItemStack((ByteBuf)buf));
    }

    public static void write(MessageUpdateCapsid message, FriendlyByteBuf buf) {
        buf.writeLong(message.blockPos);
        PacketBufferUtils.writeItemStack((ByteBuf)buf, (ItemStack)message.heldStack);
    }

    public static class Handler {
        public static void handle(MessageUpdateCapsid message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> {
                ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
                if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                    player = AlexsMobs.PROXY.getClientSidePlayer();
                }
                if (player != null && player.m_9236_() != null) {
                    BlockPos pos = BlockPos.m_122022_((long)message.blockPos);
                    if (player.m_9236_().m_7702_(pos) != null && player.m_9236_().m_7702_(pos) instanceof TileEntityCapsid) {
                        TileEntityCapsid podium = (TileEntityCapsid)player.m_9236_().m_7702_(pos);
                        podium.m_6836_(0, message.heldStack);
                    }
                }
            });
        }
    }
}
