/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.message.MessageSetPupfishChunkOnClient;
import java.util.function.Supplier;
import net.minecraftforge.network.NetworkEvent;

public static class MessageSetPupfishChunkOnClient.Handler {
    public static void handle(MessageSetPupfishChunkOnClient message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> AlexsMobs.PROXY.setPupfishChunkForItem(message.chunkX, message.chunkZ));
    }
}
