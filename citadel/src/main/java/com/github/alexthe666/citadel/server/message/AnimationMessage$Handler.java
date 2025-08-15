/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.citadel.server.message;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.message.AnimationMessage;
import java.util.function.Supplier;
import net.minecraftforge.network.NetworkEvent;

public static class AnimationMessage.Handler {
    public static void handle(AnimationMessage message, Supplier<NetworkEvent.Context> context) {
        Citadel.PROXY.handleAnimationPacket(message.entityID, message.index);
        context.get().setPacketHandled(true);
    }
}
