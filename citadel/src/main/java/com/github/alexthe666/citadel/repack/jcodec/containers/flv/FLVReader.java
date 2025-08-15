/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVMetadata;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FLVReader {
    private static final int REPOSITION_BUFFER_READS = 10;
    private static final int TAG_HEADER_SIZE = 15;
    private static final int READ_BUFFER_SIZE = 1024;
    private int frameNo;
    private ByteBuffer readBuf;
    private SeekableByteChannel ch;
    private boolean eof;
    private static boolean platformBigEndian = ByteBuffer.allocate(0).order() == ByteOrder.BIG_ENDIAN;
    public static Codec[] audioCodecMapping = new Codec[]{Codec.PCM, Codec.ADPCM, Codec.MP3, Codec.PCM, Codec.NELLYMOSER, Codec.NELLYMOSER, Codec.NELLYMOSER, Codec.G711, Codec.G711, null, Codec.AAC, Codec.SPEEX, Codec.MP3, null};
    public static Codec[] videoCodecMapping = new Codec[]{null, null, Codec.SORENSON, Codec.FLASH_SCREEN_VIDEO, Codec.VP6, Codec.VP6, Codec.FLASH_SCREEN_V2, Codec.H264};
    public static int[] sampleRates = new int[]{5500, 11000, 22000, 44100};

    public FLVReader(SeekableByteChannel ch) throws IOException {
        this.ch = ch;
        this.readBuf = ByteBuffer.allocate(1024);
        this.readBuf.order(ByteOrder.BIG_ENDIAN);
        this.initialRead(ch);
        if (!FLVReader.readHeader(this.readBuf)) {
            this.readBuf.position(0);
            if (!this.repositionFile()) {
                throw new RuntimeException("Invalid FLV file");
            }
            Logger.warn(String.format("Parsing a corrupt FLV file, first tag found at %d. %s", this.readBuf.position(), this.readBuf.position() == 0 ? "Did you forget the FLV 9-byte header?" : ""));
        }
    }

    private void initialRead(ReadableByteChannel ch) throws IOException {
        this.readBuf.clear();
        if (ch.read(this.readBuf) == -1) {
            this.eof = true;
        }
        this.readBuf.flip();
    }

    public FLVTag readNextPacket() throws IOException {
        if (this.eof) {
            return null;
        }
        FLVTag pkt = this.parsePacket(this.readBuf);
        if (pkt == null && !this.eof) {
            FLVReader.moveRemainderToTheStart(this.readBuf);
            if (this.ch.read(this.readBuf) == -1) {
                this.eof = true;
                return null;
            }
            while (MathUtil.log2(this.readBuf.capacity()) <= 22) {
                this.readBuf.flip();
                pkt = this.parsePacket(this.readBuf);
                if (pkt != null || this.readBuf.position() > 0) break;
                ByteBuffer newBuf = ByteBuffer.allocate(this.readBuf.capacity() << 2);
                newBuf.put(this.readBuf);
                this.readBuf = newBuf;
                if (this.ch.read(this.readBuf) != -1) continue;
                this.eof = true;
                return null;
            }
        }
        return pkt;
    }

    public FLVTag readPrevPacket() throws IOException {
        int startOfLastPacket = this.readBuf.getInt();
        this.readBuf.position(this.readBuf.position() - 4);
        if (this.readBuf.position() > startOfLastPacket) {
            this.readBuf.position(this.readBuf.position() - startOfLastPacket);
            return this.parsePacket(this.readBuf);
        }
        long oldPos = this.ch.position() - (long)this.readBuf.remaining();
        if (oldPos <= 9L) {
            return null;
        }
        long newPos = Math.max(0L, oldPos - (long)(this.readBuf.capacity() / 2));
        this.ch.setPosition(newPos);
        this.readBuf.clear();
        this.ch.read(this.readBuf);
        this.readBuf.flip();
        this.readBuf.position((int)(oldPos - newPos));
        return this.readPrevPacket();
    }

    private static void moveRemainderToTheStart(ByteBuffer readBuf) {
        int rem = readBuf.remaining();
        for (int i = 0; i < rem; ++i) {
            readBuf.put(i, readBuf.get());
        }
        readBuf.clear();
        readBuf.position(rem);
    }

    public FLVTag parsePacket(ByteBuffer readBuf) throws IOException {
        FLVTag.TagHeader tagHeader;
        FLVTag.Type type;
        ByteBuffer payload;
        int streamId;
        int timestamp;
        int packetType;
        int prevPacketSize;
        long packetPos;
        while (true) {
            int thisPacketSize;
            if (readBuf.remaining() < 15) {
                return null;
            }
            int pos = readBuf.position();
            packetPos = this.ch.position() - (long)readBuf.remaining();
            prevPacketSize = readBuf.getInt();
            packetType = readBuf.get() & 0xFF;
            int payloadSize = (readBuf.getShort() & 0xFFFF) << 8 | readBuf.get() & 0xFF;
            timestamp = (readBuf.getShort() & 0xFFFF) << 8 | readBuf.get() & 0xFF | (readBuf.get() & 0xFF) << 24;
            streamId = (readBuf.getShort() & 0xFFFF) << 8 | readBuf.get() & 0xFF;
            if (readBuf.remaining() >= payloadSize + 4 && (thisPacketSize = readBuf.getInt(readBuf.position() + payloadSize)) != payloadSize + 11) {
                readBuf.position(readBuf.position() - 15);
                if (!this.repositionFile()) {
                    Logger.error(String.format("Corrupt FLV stream at %d, failed to reposition!", packetPos));
                    this.ch.setPosition(this.ch.size());
                    this.eof = true;
                    return null;
                }
                Logger.warn(String.format("Corrupt FLV stream at %d, repositioned to %d.", packetPos, this.ch.position() - (long)readBuf.remaining()));
                continue;
            }
            if (readBuf.remaining() < payloadSize) {
                readBuf.position(pos);
                return null;
            }
            if (packetType != 8 && packetType != 9 && packetType != 18) {
                NIOUtils.skip(readBuf, payloadSize);
                continue;
            }
            payload = NIOUtils.clone(NIOUtils.read(readBuf, payloadSize));
            if (packetType == 8) {
                type = FLVTag.Type.AUDIO;
                tagHeader = FLVReader.parseAudioTagHeader(payload.duplicate());
                break;
            }
            if (packetType == 9) {
                type = FLVTag.Type.VIDEO;
                tagHeader = FLVReader.parseVideoTagHeader(payload.duplicate());
                break;
            }
            if (packetType == 18) {
                type = FLVTag.Type.SCRIPT;
                tagHeader = null;
                break;
            }
            System.out.println("NON AV packet");
        }
        boolean keyFrame = false;
        if (tagHeader != null && tagHeader instanceof FLVTag.VideoTagHeader) {
            FLVTag.VideoTagHeader vth = (FLVTag.VideoTagHeader)tagHeader;
            keyFrame &= vth.getFrameType() == 1;
        }
        return new FLVTag(type, packetPos, tagHeader, timestamp, payload, keyFrame &= packetType == 8 || packetType == 9, this.frameNo++, streamId, prevPacketSize);
    }

    public static boolean readHeader(ByteBuffer readBuf) {
        return readBuf.remaining() >= 9 && readBuf.get() == 70 && readBuf.get() == 76 && readBuf.get() == 86 && readBuf.get() == 1 && (readBuf.get() & 5) != 0 && readBuf.getInt() == 9;
    }

    public static FLVMetadata parseMetadata(ByteBuffer bb) {
        if ("onMetaData".equals(FLVReader.readAMFData(bb, -1))) {
            return new FLVMetadata((Map)FLVReader.readAMFData(bb, -1));
        }
        return null;
    }

    private static Object readAMFData(ByteBuffer input, int type) {
        if (type == -1) {
            type = input.get() & 0xFF;
        }
        switch (type) {
            case 0: {
                return input.getDouble();
            }
            case 1: {
                return input.get() == 1;
            }
            case 2: {
                return FLVReader.readAMFString(input);
            }
            case 3: {
                return FLVReader.readAMFObject(input);
            }
            case 8: {
                return FLVReader.readAMFEcmaArray(input);
            }
            case 10: {
                return FLVReader.readAMFStrictArray(input);
            }
            case 11: {
                Date date = new Date((long)input.getDouble());
                input.getShort();
                return date;
            }
            case 13: {
                return "UNDEFINED";
            }
        }
        return null;
    }

    private static Object readAMFStrictArray(ByteBuffer input) {
        int count = input.getInt();
        Object[] result = new Object[count];
        for (int i = 0; i < count; ++i) {
            result[i] = FLVReader.readAMFData(input, -1);
        }
        return result;
    }

    private static String readAMFString(ByteBuffer input) {
        int size = input.getShort() & 0xFFFF;
        return Platform.stringFromCharset(NIOUtils.toArray(NIOUtils.read(input, size)), "UTF-8");
    }

    private static Object readAMFObject(ByteBuffer input) {
        HashMap<String, Object> array = new HashMap<String, Object>();
        while (true) {
            String key = FLVReader.readAMFString(input);
            int dataType = input.get() & 0xFF;
            if (dataType == 9) break;
            array.put(key, FLVReader.readAMFData(input, dataType));
        }
        return array;
    }

    private static Object readAMFEcmaArray(ByteBuffer input) {
        long size = input.getInt();
        HashMap<String, Object> array = new HashMap<String, Object>();
        int i = 0;
        while ((long)i < size) {
            String key = FLVReader.readAMFString(input);
            int dataType = input.get() & 0xFF;
            array.put(key, FLVReader.readAMFData(input, dataType));
            ++i;
        }
        return array;
    }

    public static FLVTag.VideoTagHeader parseVideoTagHeader(ByteBuffer dup) {
        byte b0 = dup.get();
        int frameType = (b0 & 0xFF) >> 4;
        int codecId = b0 & 0xF;
        Codec codec = videoCodecMapping[codecId];
        if (codecId == 7) {
            byte avcPacketType = dup.get();
            int compOffset = dup.getShort() << 8 | dup.get() & 0xFF;
            return new FLVTag.AvcVideoTagHeader(codec, frameType, avcPacketType, compOffset);
        }
        return new FLVTag.VideoTagHeader(codec, frameType);
    }

    public static FLVTag.TagHeader parseAudioTagHeader(ByteBuffer dup) {
        byte b = dup.get();
        int codecId = (b & 0xFF) >> 4;
        int sampleRate = sampleRates[b >> 2 & 3];
        if (codecId == 4 || codecId == 11) {
            sampleRate = 16000;
        }
        if (codecId == 5 || codecId == 14) {
            sampleRate = 8000;
        }
        int sampleSizeInBits = (b & 2) == 0 ? 8 : 16;
        boolean signed = codecId != 3 && codecId != 0 || sampleSizeInBits == 16;
        int channelCount = 1 + (b & 1);
        if (codecId == 11) {
            channelCount = 1;
        }
        AudioFormat audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channelCount, signed, codecId == 3 ? false : platformBigEndian);
        Codec codec = audioCodecMapping[codecId];
        if (codecId == 10) {
            byte packetType = dup.get();
            return new FLVTag.AacAudioTagHeader(codec, audioFormat, packetType);
        }
        return new FLVTag.AudioTagHeader(codec, audioFormat);
    }

    public static int probe(ByteBuffer buf) {
        try {
            FLVReader.readHeader(buf);
            return 100;
        }
        catch (RuntimeException e) {
            return 0;
        }
    }

    public void reset() throws IOException {
        this.initialRead(this.ch);
    }

    public void reposition() throws IOException {
        this.reset();
        if (!FLVReader.positionAtPacket(this.readBuf)) {
            throw new RuntimeException("Could not find at FLV tag start");
        }
    }

    public static boolean positionAtPacket(ByteBuffer readBuf) {
        ByteBuffer dup = readBuf.duplicate();
        int payloadSize = 0;
        NIOUtils.skip(dup, 5);
        while (dup.hasRemaining()) {
            payloadSize = (payloadSize & 0xFFFF) << 8 | dup.get() & 0xFF;
            int pointerPos = dup.position() + 7 + payloadSize;
            if (dup.position() < 8 || pointerPos >= dup.limit() - 4 || dup.getInt(pointerPos) - payloadSize != 11) continue;
            readBuf.position(dup.position() - 8);
            return true;
        }
        return false;
    }

    public boolean repositionFile() throws IOException {
        int payloadSize = 0;
        for (int i = 0; i < 10; ++i) {
            while (this.readBuf.hasRemaining()) {
                payloadSize = (payloadSize & 0xFFFF) << 8 | this.readBuf.get() & 0xFF;
                int pointerPos = this.readBuf.position() + 7 + payloadSize;
                if (this.readBuf.position() < 8 || pointerPos >= this.readBuf.limit() - 4 || this.readBuf.getInt(pointerPos) - payloadSize != 11) continue;
                this.readBuf.position(this.readBuf.position() - 8);
                return true;
            }
            this.initialRead(this.ch);
            if (!this.readBuf.hasRemaining()) break;
        }
        return false;
    }
}
