/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class MessageInteractMultipart {
    public boolean offhand;
    public int parent;

    public MessageInteractMultipart(int parent, boolean offhand) {
        this.parent = parent;
        this.offhand = offhand;
    }

    public MessageInteractMultipart() {
    }

    public static MessageInteractMultipart read(FriendlyByteBuf buf) {
        return new MessageInteractMultipart(buf.readInt(), buf.readBoolean());
    }

    public static void write(MessageInteractMultipart message, FriendlyByteBuf buf) {
        buf.writeInt(message.parent);
        buf.writeBoolean(message.offhand);
    }

    public static class Handler {
        public static void handle(MessageInteractMultipart message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> {
                Entity parent;
                ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
                if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                    player = AlexsMobs.PROXY.getClientSidePlayer();
                }
                if (player != null && player.m_9236_() != null && player.m_20270_(parent = player.m_9236_().m_6815_(message.parent)) < 20.0f && parent instanceof Mob) {
                    player.m_36157_(parent, message.offhand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
                }
            });
        }
    }
}
