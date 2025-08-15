/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.write.CAVLCWriter;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SEI {
    public SEIMessage[] messages;

    public SEI(SEIMessage[] messages) {
        this.messages = messages;
    }

    public static SEI read(ByteBuffer is) {
        SEIMessage msg;
        ArrayList<SEIMessage> messages = new ArrayList<SEIMessage>();
        do {
            if ((msg = SEI.sei_message(is)) == null) continue;
            messages.add(msg);
        } while (msg != null);
        return new SEI(messages.toArray(new SEIMessage[0]));
    }

    private static SEIMessage sei_message(ByteBuffer is) {
        int payloadType = 0;
        int b = 0;
        while (is.hasRemaining() && (b = is.get() & 0xFF) == 255) {
            payloadType += 255;
        }
        if (!is.hasRemaining()) {
            return null;
        }
        payloadType += b;
        int payloadSize = 0;
        while (is.hasRemaining() && (b = is.get() & 0xFF) == 255) {
            payloadSize += 255;
        }
        if (!is.hasRemaining()) {
            return null;
        }
        byte[] payload = SEI.sei_payload(payloadType, payloadSize += b, is);
        if (payload.length != payloadSize) {
            return null;
        }
        return new SEIMessage(payloadType, payloadSize, payload);
    }

    private static byte[] sei_payload(int payloadType, int payloadSize, ByteBuffer is) {
        byte[] res = new byte[payloadSize];
        is.get(res);
        return res;
    }

    public void write(ByteBuffer out) {
        BitWriter writer = new BitWriter(out);
        CAVLCWriter.writeTrailingBits(writer);
    }

    public static class SEIMessage {
        public int payloadType;
        public int payloadSize;
        public byte[] payload;

        public SEIMessage(int payloadType2, int payloadSize2, byte[] payload2) {
            this.payload = payload2;
            this.payloadType = payloadType2;
            this.payloadSize = payloadSize2;
        }
    }
}
