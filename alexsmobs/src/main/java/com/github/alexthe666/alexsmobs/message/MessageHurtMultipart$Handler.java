/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageType
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import com.github.alexthe666.alexsmobs.message.MessageHurtMultipart;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public static class MessageHurtMultipart.Handler {
    public static void handle(MessageHurtMultipart message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = AlexsMobs.PROXY.getClientSidePlayer();
            }
            if (player != null && player.m_9236_() != null) {
                Holder holder;
                Entity part = player.m_9236_().m_6815_(message.part);
                Entity parent = player.m_9236_().m_6815_(message.parent);
                Registry registry = (Registry)player.m_9236_().m_9598_().m_6632_(Registries.f_268580_).get();
                DamageType dmg = (DamageType)registry.m_7745_(new ResourceLocation(message.damageType));
                if (dmg != null && (holder = (Holder)registry.m_203300_(registry.m_7447_((Object)dmg)).orElseGet(null)) != null) {
                    DamageSource source = new DamageSource((Holder)registry.m_203300_(registry.m_7447_((Object)dmg)).get());
                    if (part instanceof IHurtableMultipart && parent instanceof LivingEntity) {
                        ((IHurtableMultipart)part).onAttackedFromServer((LivingEntity)parent, message.damage, source);
                    }
                    if (part == null && parent != null && parent.isMultipartEntity()) {
                        parent.m_6469_(source, message.damage);
                    }
                }
            }
        });
    }
}
