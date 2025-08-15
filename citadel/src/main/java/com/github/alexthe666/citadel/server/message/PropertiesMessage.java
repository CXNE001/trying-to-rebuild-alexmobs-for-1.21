/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.citadel.server.message;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PacketBufferUtils;
import io.netty.buffer.ByteBuf;
import java.util.function.Supplier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class PropertiesMessage {
    private final String propertyID;
    private final CompoundTag compound;
    private final int entityID;

    public PropertiesMessage(String propertyID, CompoundTag compound, int entityID) {
        this.propertyID = propertyID;
        this.compound = compound;
        this.entityID = entityID;
    }

    public static void write(PropertiesMessage message, FriendlyByteBuf packetBuffer) {
        PacketBufferUtils.writeUTF8String((ByteBuf)packetBuffer, message.propertyID);
        PacketBufferUtils.writeTag((ByteBuf)packetBuffer, message.compound);
        packetBuffer.writeInt(message.entityID);
    }

    public static PropertiesMessage read(FriendlyByteBuf packetBuffer) {
        return new PropertiesMessage(PacketBufferUtils.readUTF8String((ByteBuf)packetBuffer), PacketBufferUtils.readTag((ByteBuf)packetBuffer), packetBuffer.readInt());
    }

    public static class Handler {
        public static void handle(PropertiesMessage message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> {
                if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                    Citadel.PROXY.handlePropertiesPacket(message.propertyID, message.compound, message.entityID);
                } else {
                    Entity e = ((NetworkEvent.Context)context.get()).getSender().m_9236_().m_6815_(message.entityID);
                    if (e instanceof LivingEntity && (message.propertyID.equals("CitadelPatreonConfig") || message.propertyID.equals("CitadelTagUpdate"))) {
                        CitadelEntityData.setCitadelTag((LivingEntity)e, message.compound);
                    }
                }
            });
        }
    }
}
