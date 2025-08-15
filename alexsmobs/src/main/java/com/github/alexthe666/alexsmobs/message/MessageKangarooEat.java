/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ItemParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import java.util.function.Supplier;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class MessageKangarooEat {
    public int kangaroo;
    public ItemStack stack;

    public MessageKangarooEat(int kangaroo, ItemStack stack) {
        this.kangaroo = kangaroo;
        this.stack = stack;
    }

    public MessageKangarooEat() {
    }

    public static MessageKangarooEat read(FriendlyByteBuf buf) {
        return new MessageKangarooEat(buf.readInt(), buf.m_130267_());
    }

    public static void write(MessageKangarooEat message, FriendlyByteBuf buf) {
        buf.writeInt(message.kangaroo);
        buf.m_130055_(message.stack);
    }

    public static class Handler {
        public static void handle(MessageKangarooEat message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> {
                Entity entity;
                ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
                if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                    player = AlexsMobs.PROXY.getClientSidePlayer();
                }
                if (player != null && player.m_9236_() != null && (entity = player.m_9236_().m_6815_(message.kangaroo)) instanceof EntityKangaroo) {
                    EntityKangaroo kangaroo = (EntityKangaroo)entity;
                    if (((EntityKangaroo)entity).kangarooInventory != null) {
                        for (int i = 0; i < 7; ++i) {
                            double d2 = kangaroo.m_217043_().m_188583_() * 0.02;
                            double d0 = kangaroo.m_217043_().m_188583_() * 0.02;
                            double d1 = kangaroo.m_217043_().m_188583_() * 0.02;
                            entity.m_9236_().m_7106_((ParticleOptions)new ItemParticleOption(ParticleTypes.f_123752_, message.stack), entity.m_20185_() + (double)(kangaroo.m_217043_().m_188501_() * entity.m_20205_()) - (double)entity.m_20205_() * 0.5, entity.m_20186_() + (double)(entity.m_20206_() * 0.5f) + (double)(kangaroo.m_217043_().m_188501_() * entity.m_20206_() * 0.5f), entity.m_20189_() + (double)(kangaroo.m_217043_().m_188501_() * entity.m_20205_()) - (double)entity.m_20205_() * 0.5, d0, d1, d2);
                        }
                    }
                }
            });
        }
    }
}
