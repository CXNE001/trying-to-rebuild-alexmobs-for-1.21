/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.item.ItemStack
 *  org.apache.commons.lang3.Validate
 */
package com.github.alexthe666.citadel.server.message;

import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.Validate;

public class PacketBufferUtils {
    public static int varIntByteCount(int toCount) {
        return (toCount & 0xFFFFFF80) == 0 ? 1 : ((toCount & 0xFFFFC000) == 0 ? 2 : ((toCount & 0xFFE00000) == 0 ? 3 : ((toCount & 0xF0000000) == 0 ? 4 : 5)));
    }

    public static int readVarInt(ByteBuf buf, int maxSize) {
        byte b0;
        Validate.isTrue((maxSize < 6 && maxSize > 0 ? 1 : 0) != 0, (String)"Varint length is between 1 and 5, not %d", (long)maxSize);
        int i = 0;
        int j = 0;
        do {
            b0 = buf.readByte();
            i |= (b0 & 0x7F) << j++ * 7;
            if (j <= maxSize) continue;
            throw new RuntimeException("VarInt too big");
        } while ((b0 & 0x80) == 128);
        return i;
    }

    public static int readVarShort(ByteBuf buf) {
        int low = buf.readUnsignedShort();
        int high = 0;
        if ((low & 0x8000) != 0) {
            low &= Short.MAX_VALUE;
            high = buf.readUnsignedByte();
        }
        return (high & 0xFF) << 15 | low;
    }

    public static void writeVarShort(ByteBuf buf, int toWrite) {
        int low = toWrite & Short.MAX_VALUE;
        int high = (toWrite & 0x7F8000) >> 15;
        if (high != 0) {
            low |= 0x8000;
        }
        buf.writeShort(low);
        if (high != 0) {
            buf.writeByte(high);
        }
    }

    public static void writeVarInt(ByteBuf to, int toWrite, int maxSize) {
        Validate.isTrue((PacketBufferUtils.varIntByteCount(toWrite) <= maxSize ? 1 : 0) != 0, (String)"Integer is too big for %d bytes", (long)maxSize);
        while ((toWrite & 0xFFFFFF80) != 0) {
            to.writeByte(toWrite & 0x7F | 0x80);
            toWrite >>>= 7;
        }
        to.writeByte(toWrite);
    }

    public static String readUTF8String(ByteBuf from) {
        int len = PacketBufferUtils.readVarInt(from, 2);
        String str = from.toString(from.readerIndex(), len, StandardCharsets.UTF_8);
        from.readerIndex(from.readerIndex() + len);
        return str;
    }

    public static void writeUTF8String(ByteBuf to, String string) {
        byte[] utf8Bytes = string.getBytes(StandardCharsets.UTF_8);
        Validate.isTrue((PacketBufferUtils.varIntByteCount(utf8Bytes.length) < 3 ? 1 : 0) != 0, (String)"The string is too long for this encoding.", (Object[])new Object[0]);
        PacketBufferUtils.writeVarInt(to, utf8Bytes.length, 2);
        to.writeBytes(utf8Bytes);
    }

    public static void writeItemStack(ByteBuf to, ItemStack stack) {
        FriendlyByteBuf pb = new FriendlyByteBuf(to);
        pb.m_130055_(stack);
    }

    public static ItemStack readItemStack(ByteBuf from) {
        FriendlyByteBuf pb = new FriendlyByteBuf(from);
        try {
            return pb.m_130267_();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeTag(ByteBuf to, CompoundTag tag) {
        FriendlyByteBuf pb = new FriendlyByteBuf(to);
        pb.m_130079_(tag);
    }

    @Nullable
    public static CompoundTag readTag(ByteBuf from) {
        FriendlyByteBuf pb = new FriendlyByteBuf(from);
        try {
            return pb.m_130260_();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
