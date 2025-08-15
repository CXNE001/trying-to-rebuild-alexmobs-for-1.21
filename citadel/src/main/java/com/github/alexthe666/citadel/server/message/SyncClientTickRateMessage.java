/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.citadel.server.message;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.message.PacketBufferUtils;
import io.netty.buffer.ByteBuf;
import java.util.function.Supplier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class SyncClientTickRateMessage {
    private final CompoundTag compound;

    public SyncClientTickRateMessage(CompoundTag compound) {
        this.compound = compound;
    }

    public static void write(SyncClientTickRateMessage message, FriendlyByteBuf packetBuffer) {
        PacketBufferUtils.writeTag((ByteBuf)packetBuffer, message.compound);
    }

    public static SyncClientTickRateMessage read(FriendlyByteBuf packetBuffer) {
        return new SyncClientTickRateMessage(PacketBufferUtils.readTag((ByteBuf)packetBuffer));
    }

    public static class Handler {
        public static void handle(SyncClientTickRateMessage message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> {
                if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                    Citadel.PROXY.handleClientTickRatePacket(message.compound);
                }
            });
        }
    }
}
