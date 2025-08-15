/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.item.ILeftClick;
import com.github.alexthe666.alexsmobs.message.MessageSwingArm;
import java.util.function.Supplier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public static class MessageSwingArm.Handler {
    public static void handle(MessageSwingArm message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (player != null) {
                ItemStack leftItem = player.m_21120_(InteractionHand.OFF_HAND);
                ItemStack rightItem = player.m_21120_(InteractionHand.MAIN_HAND);
                if (leftItem.m_41720_() instanceof ILeftClick) {
                    ((ILeftClick)leftItem.m_41720_()).onLeftClick(leftItem, (LivingEntity)player);
                }
                if (rightItem.m_41720_() instanceof ILeftClick) {
                    ((ILeftClick)rightItem.m_41720_()).onLeftClick(rightItem, (LivingEntity)player);
                }
            }
        });
    }
}
