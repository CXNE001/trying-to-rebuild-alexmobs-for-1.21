/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es.DecoderConfig;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es.DecoderSpecific;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es.Descriptor;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es.ES;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es.NodeDescriptor;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es.SL;
import com.github.alexthe666.citadel.repack.jcodec.common.JCodecUtil2;
import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.UsedViaReflection;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DescriptorParser {
    private static final int ES_TAG = 3;
    private static final int DC_TAG = 4;
    private static final int DS_TAG = 5;
    private static final int SL_TAG = 6;

    public static Descriptor read(ByteBuffer input) {
        if (input.remaining() < 2) {
            return null;
        }
        int tag = input.get() & 0xFF;
        int size = JCodecUtil2.readBER32(input);
        ByteBuffer byteBuffer = NIOUtils.read(input, size);
        switch (tag) {
            case 3: {
                return DescriptorParser.parseES(byteBuffer);
            }
            case 6: {
                return DescriptorParser.parseSL(byteBuffer);
            }
            case 4: {
                return DescriptorParser.parseDecoderConfig(byteBuffer);
            }
            case 5: {
                return DescriptorParser.parseDecoderSpecific(byteBuffer);
            }
        }
        throw new RuntimeException("unknown tag " + tag);
    }

    @UsedViaReflection
    private static NodeDescriptor parseNodeDesc(ByteBuffer input) {
        Descriptor d;
        ArrayList<Descriptor> children = new ArrayList<Descriptor>();
        do {
            if ((d = DescriptorParser.read(input)) == null) continue;
            children.add(d);
        } while (d != null);
        return new NodeDescriptor(0, children);
    }

    private static ES parseES(ByteBuffer input) {
        short trackId = input.getShort();
        input.get();
        NodeDescriptor node = DescriptorParser.parseNodeDesc(input);
        return new ES((int)trackId, node.getChildren());
    }

    private static SL parseSL(ByteBuffer input) {
        Preconditions.checkState(2 == (input.get() & 0xFF));
        return new SL();
    }

    private static DecoderSpecific parseDecoderSpecific(ByteBuffer input) {
        ByteBuffer data = NIOUtils.readBuf(input);
        return new DecoderSpecific(data);
    }

    private static DecoderConfig parseDecoderConfig(ByteBuffer input) {
        int objectType = input.get() & 0xFF;
        input.get();
        int bufSize = (input.get() & 0xFF) << 16 | input.getShort() & 0xFFFF;
        int maxBitrate = input.getInt();
        int avgBitrate = input.getInt();
        NodeDescriptor node = DescriptorParser.parseNodeDesc(input);
        return new DecoderConfig(objectType, bufSize, maxBitrate, avgBitrate, node.getChildren());
    }
}
