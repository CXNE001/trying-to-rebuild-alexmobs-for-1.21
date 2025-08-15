/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.SliceHeaderReader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnit;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnitType;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SeqParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceHeader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceType;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.write.SliceHeaderWriter;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.mp4.AvcCBox;
import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SampleEntry;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.VideoSampleEntry;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class H264Utils {
    public static ByteBuffer nextNALUnit(ByteBuffer buf) {
        H264Utils.skipToNALUnit(buf);
        if (buf.hasArray()) {
            return H264Utils.gotoNALUnitWithArray(buf);
        }
        return H264Utils.gotoNALUnit(buf);
    }

    public static final void skipToNALUnit(ByteBuffer buf) {
        if (!buf.hasRemaining()) {
            return;
        }
        int val = -1;
        while (buf.hasRemaining()) {
            val <<= 8;
            if (((val |= buf.get() & 0xFF) & 0xFFFFFF) != 1) continue;
            buf.position(buf.position());
            break;
        }
    }

    public static final ByteBuffer gotoNALUnit(ByteBuffer buf) {
        if (!buf.hasRemaining()) {
            return null;
        }
        int from = buf.position();
        ByteBuffer result = buf.slice();
        result.order(ByteOrder.BIG_ENDIAN);
        int val = -1;
        while (buf.hasRemaining()) {
            val <<= 8;
            if (((val |= buf.get() & 0xFF) & 0xFFFFFF) != 1) continue;
            buf.position(buf.position() - (val == 1 ? 4 : 3));
            result.limit(buf.position() - from);
            break;
        }
        return result;
    }

    public static final ByteBuffer gotoNALUnitWithArray(ByteBuffer buf) {
        int pos;
        if (!buf.hasRemaining()) {
            return null;
        }
        int from = buf.position();
        ByteBuffer result = buf.slice();
        result.order(ByteOrder.BIG_ENDIAN);
        byte[] arr = buf.array();
        int posFrom = pos = from + buf.arrayOffset();
        int lim = buf.limit() + buf.arrayOffset();
        while (pos < lim) {
            byte b = arr[pos];
            if ((b & 0xFE) == 0) {
                while (b == 0 && ++pos < lim) {
                    b = arr[pos];
                }
                if (b == 1 && pos - posFrom >= 2 && arr[pos - 1] == 0 && arr[pos - 2] == 0) {
                    int lenSize = pos - posFrom >= 3 && arr[pos - 3] == 0 ? 4 : 3;
                    buf.position(pos + 1 - buf.arrayOffset() - lenSize);
                    result.limit(buf.position() - from);
                    return result;
                }
            }
            pos += 3;
        }
        buf.position(buf.limit());
        return result;
    }

    public static final void unescapeNAL(ByteBuffer _buf) {
        if (_buf.remaining() < 2) {
            return;
        }
        ByteBuffer _in = _buf.duplicate();
        ByteBuffer out = _buf.duplicate();
        byte p1 = _in.get();
        out.put(p1);
        byte p2 = _in.get();
        out.put(p2);
        while (_in.hasRemaining()) {
            byte b = _in.get();
            if (p1 != 0 || p2 != 0 || b != 3) {
                out.put(b);
            }
            p1 = p2;
            p2 = b;
        }
        _buf.limit(out.position());
    }

    public static final void escapeNALinplace(ByteBuffer src) {
        int[] loc = H264Utils.searchEscapeLocations(src);
        int old = src.limit();
        src.limit(src.limit() + loc.length);
        int newPos = src.limit() - 1;
        int oldPos = old - 1;
        int locIdx = loc.length - 1;
        while (newPos >= src.position()) {
            src.put(newPos, src.get(oldPos));
            if (locIdx >= 0 && loc[locIdx] == oldPos) {
                src.put(--newPos, (byte)3);
                --locIdx;
            }
            --newPos;
            --oldPos;
        }
    }

    private static int[] searchEscapeLocations(ByteBuffer src) {
        IntArrayList points = IntArrayList.createIntArrayList();
        ByteBuffer search = src.duplicate();
        int p = search.getShort();
        while (search.hasRemaining()) {
            byte b = search.get();
            if (p == 0 && (b & 0xFFFFFFFC) == 0) {
                points.add(search.position() - 1);
                p = 3;
            }
            p = p << 8 & 0xFFFF;
            p |= b & 0xFF;
        }
        int[] array = points.toArray();
        return array;
    }

    public static final void escapeNAL(ByteBuffer src, ByteBuffer dst) {
        int p1 = src.get();
        int p2 = src.get();
        dst.put((byte)p1);
        dst.put((byte)p2);
        while (src.hasRemaining()) {
            int b = src.get();
            if (p1 == 0 && p2 == 0 && (b & 0xFF) <= 3) {
                dst.put((byte)3);
                p1 = p2;
                p2 = 3;
            }
            dst.put((byte)b);
            p1 = p2;
            p2 = b;
        }
    }

    public static List<ByteBuffer> splitMOVPacket(ByteBuffer buf, AvcCBox avcC) {
        int len;
        ArrayList<ByteBuffer> result = new ArrayList<ByteBuffer>();
        int nls = avcC.getNalLengthSize();
        ByteBuffer dup = buf.duplicate();
        while (dup.remaining() >= nls && (len = H264Utils.readLen(dup, nls)) != 0) {
            result.add(NIOUtils.read(dup, len));
        }
        return result;
    }

    private static int readLen(ByteBuffer dup, int nls) {
        switch (nls) {
            case 1: {
                return dup.get() & 0xFF;
            }
            case 2: {
                return dup.getShort() & 0xFFFF;
            }
            case 3: {
                return (dup.getShort() & 0xFFFF) << 8 | dup.get() & 0xFF;
            }
            case 4: {
                return dup.getInt();
            }
        }
        throw new IllegalArgumentException("NAL Unit length size can not be " + nls);
    }

    public static void encodeMOVPacketInplace(ByteBuffer avcFrame) {
        ByteBuffer buf;
        ByteBuffer dup = avcFrame.duplicate();
        ByteBuffer d1 = avcFrame.duplicate();
        int tot = d1.position();
        while ((buf = H264Utils.nextNALUnit(dup)) != null) {
            d1.position(tot);
            d1.putInt(buf.remaining());
            tot += buf.remaining() + 4;
        }
    }

    public static ByteBuffer encodeMOVPacket(ByteBuffer avcFrame) {
        ByteBuffer buf;
        ByteBuffer dup = avcFrame.duplicate();
        ArrayList<ByteBuffer> list = new ArrayList<ByteBuffer>();
        int totalLen = 0;
        while ((buf = H264Utils.nextNALUnit(dup)) != null) {
            list.add(buf);
            totalLen += buf.remaining();
        }
        ByteBuffer result = ByteBuffer.allocate(list.size() * 4 + totalLen);
        for (ByteBuffer byteBuffer : list) {
            result.putInt(byteBuffer.remaining());
            result.put(byteBuffer);
        }
        result.flip();
        return result;
    }

    public static ByteBuffer decodeMOVPacket(ByteBuffer result, AvcCBox avcC) {
        if (avcC.getNalLengthSize() == 4) {
            H264Utils.decodeMOVPacketInplace(result, avcC);
            return result;
        }
        return H264Utils.joinNALUnits(H264Utils.splitMOVPacket(result, avcC));
    }

    public static void decodeMOVPacketInplace(ByteBuffer result, AvcCBox avcC) {
        if (avcC.getNalLengthSize() != 4) {
            throw new IllegalArgumentException("Can only inplace decode AVC MOV packet with nal_length_size = 4.");
        }
        ByteBuffer dup = result.duplicate();
        while (dup.remaining() >= 4) {
            int size = dup.getInt();
            dup.position(dup.position() - 4);
            dup.putInt(1);
            dup.position(dup.position() + size);
        }
    }

    public static void wipePS(ByteBuffer _in, ByteBuffer out, List<ByteBuffer> spsList, List<ByteBuffer> ppsList) {
        ByteBuffer buf;
        ByteBuffer dup = _in.duplicate();
        while (dup.hasRemaining() && (buf = H264Utils.nextNALUnit(dup)) != null) {
            NALUnit nu = NALUnit.read(buf.duplicate());
            if (nu.type == NALUnitType.PPS) {
                if (ppsList == null) continue;
                ppsList.add(NIOUtils.duplicate(buf));
                continue;
            }
            if (nu.type == NALUnitType.SPS) {
                if (spsList == null) continue;
                spsList.add(NIOUtils.duplicate(buf));
                continue;
            }
            if (out == null) continue;
            out.putInt(1);
            out.put(buf);
        }
        if (out != null) {
            out.flip();
        }
    }

    public static void wipePSinplace(ByteBuffer _in, Collection<ByteBuffer> spsList, Collection<ByteBuffer> ppsList) {
        ByteBuffer buf;
        ByteBuffer dup = _in.duplicate();
        while (dup.hasRemaining() && (buf = H264Utils.nextNALUnit(dup)) != null) {
            NALUnit nu = NALUnit.read(buf);
            if (nu.type == NALUnitType.PPS) {
                if (ppsList != null) {
                    ppsList.add(NIOUtils.duplicate(buf));
                }
                _in.position(dup.position());
                continue;
            }
            if (nu.type == NALUnitType.SPS) {
                if (spsList != null) {
                    spsList.add(NIOUtils.duplicate(buf));
                }
                _in.position(dup.position());
                continue;
            }
            if (nu.type != NALUnitType.IDR_SLICE && nu.type != NALUnitType.NON_IDR_SLICE) continue;
            break;
        }
    }

    public static AvcCBox createAvcC(SeqParameterSet sps, PictureParameterSet pps, int nalLengthSize) {
        ByteBuffer serialSps = ByteBuffer.allocate(512);
        sps.write(serialSps);
        serialSps.flip();
        H264Utils.escapeNALinplace(serialSps);
        ByteBuffer serialPps = ByteBuffer.allocate(512);
        pps.write(serialPps);
        serialPps.flip();
        H264Utils.escapeNALinplace(serialPps);
        AvcCBox avcC = AvcCBox.createAvcCBox(sps.profileIdc, 0, sps.levelIdc, nalLengthSize, Arrays.asList(serialSps), Arrays.asList(serialPps));
        return avcC;
    }

    public static AvcCBox createAvcCFromList(List<SeqParameterSet> initSPS, List<PictureParameterSet> initPPS, int nalLengthSize) {
        List<ByteBuffer> serialSps = H264Utils.saveSPS(initSPS);
        List<ByteBuffer> serialPps = H264Utils.savePPS(initPPS);
        SeqParameterSet sps = initSPS.get(0);
        return AvcCBox.createAvcCBox(sps.profileIdc, 0, sps.levelIdc, nalLengthSize, serialSps, serialPps);
    }

    public static List<ByteBuffer> savePPS(List<PictureParameterSet> initPPS) {
        ArrayList<ByteBuffer> serialPps = new ArrayList<ByteBuffer>();
        for (PictureParameterSet pps : initPPS) {
            ByteBuffer bb1 = ByteBuffer.allocate(512);
            pps.write(bb1);
            bb1.flip();
            H264Utils.escapeNALinplace(bb1);
            serialPps.add(bb1);
        }
        return serialPps;
    }

    public static List<ByteBuffer> saveSPS(List<SeqParameterSet> initSPS) {
        ArrayList<ByteBuffer> serialSps = new ArrayList<ByteBuffer>();
        for (SeqParameterSet sps : initSPS) {
            ByteBuffer bb1 = ByteBuffer.allocate(512);
            sps.write(bb1);
            bb1.flip();
            H264Utils.escapeNALinplace(bb1);
            serialSps.add(bb1);
        }
        return serialSps;
    }

    public static SampleEntry createMOVSampleEntryFromBytes(ByteBuffer codecPrivate) {
        List<ByteBuffer> rawSPS = H264Utils.getRawSPS(codecPrivate.duplicate());
        List<ByteBuffer> rawPPS = H264Utils.getRawPPS(codecPrivate.duplicate());
        return H264Utils.createMOVSampleEntryFromSpsPpsList(rawSPS, rawPPS, 4);
    }

    public static SampleEntry createMOVSampleEntryFromSpsPpsList(List<ByteBuffer> spsList, List<ByteBuffer> ppsList, int nalLengthSize) {
        AvcCBox avcC = H264Utils.createAvcCFromPS(spsList, ppsList, nalLengthSize);
        return H264Utils.createMOVSampleEntryFromAvcC(avcC);
    }

    public static AvcCBox createAvcCFromBytes(ByteBuffer codecPrivate) {
        List<ByteBuffer> rawSPS = H264Utils.getRawSPS(codecPrivate.duplicate());
        List<ByteBuffer> rawPPS = H264Utils.getRawPPS(codecPrivate.duplicate());
        return H264Utils.createAvcCFromPS(rawSPS, rawPPS, 4);
    }

    public static AvcCBox createAvcCFromPS(List<ByteBuffer> spsList, List<ByteBuffer> ppsList, int nalLengthSize) {
        SeqParameterSet sps = H264Utils.readSPS(NIOUtils.duplicate(spsList.get(0)));
        return AvcCBox.createAvcCBox(sps.profileIdc, 0, sps.levelIdc, nalLengthSize, spsList, ppsList);
    }

    public static SampleEntry createMOVSampleEntryFromAvcC(AvcCBox avcC) {
        SeqParameterSet sps = SeqParameterSet.read(avcC.getSpsList().get(0).duplicate());
        int codedWidth = sps.picWidthInMbsMinus1 + 1 << 4;
        int codedHeight = SeqParameterSet.getPicHeightInMbs(sps) << 4;
        VideoSampleEntry se = VideoSampleEntry.videoSampleEntry("avc1", H264Utils.getPicSize(sps), "JCodec");
        se.add(avcC);
        return se;
    }

    public static SampleEntry createMOVSampleEntryFromSpsPps(SeqParameterSet initSPS, PictureParameterSet initPPS, int nalLengthSize) {
        ByteBuffer bb1 = ByteBuffer.allocate(512);
        ByteBuffer bb2 = ByteBuffer.allocate(512);
        initSPS.write(bb1);
        initPPS.write(bb2);
        bb1.flip();
        bb2.flip();
        return H264Utils.createMOVSampleEntryFromBuffer(bb1, bb2, nalLengthSize);
    }

    public static SampleEntry createMOVSampleEntryFromBuffer(ByteBuffer sps, ByteBuffer pps, int nalLengthSize) {
        return H264Utils.createMOVSampleEntryFromSpsPpsList(Arrays.asList(sps), Arrays.asList(pps), nalLengthSize);
    }

    public static boolean iFrame(ByteBuffer _data) {
        ByteBuffer segment;
        ByteBuffer data = _data.duplicate();
        while ((segment = H264Utils.nextNALUnit(data)) != null) {
            NALUnitType type = NALUnit.read((ByteBuffer)segment).type;
            if (type != NALUnitType.IDR_SLICE && type != NALUnitType.NON_IDR_SLICE) continue;
            H264Utils.unescapeNAL(segment);
            BitReader reader = BitReader.createBitReader(segment);
            SliceHeader part1 = SliceHeaderReader.readPart1(reader);
            return part1.sliceType == SliceType.I;
        }
        return false;
    }

    public static boolean isByteBufferIDRSlice(ByteBuffer _data) {
        ByteBuffer segment;
        ByteBuffer data = _data.duplicate();
        while ((segment = H264Utils.nextNALUnit(data)) != null) {
            if (NALUnit.read((ByteBuffer)segment).type != NALUnitType.IDR_SLICE) continue;
            return true;
        }
        return false;
    }

    public static boolean idrSlice(List<ByteBuffer> _data) {
        for (ByteBuffer segment : _data) {
            if (NALUnit.read((ByteBuffer)segment.duplicate()).type != NALUnitType.IDR_SLICE) continue;
            return true;
        }
        return false;
    }

    public static void saveRawFrame(ByteBuffer data, AvcCBox avcC, File f) throws IOException {
        FileChannelWrapper raw = NIOUtils.writableChannel(f);
        H264Utils.saveStreamParams(avcC, raw);
        raw.write(data.duplicate());
        raw.close();
    }

    public static void saveStreamParams(AvcCBox avcC, SeekableByteChannel raw) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        for (ByteBuffer byteBuffer : avcC.getSpsList()) {
            raw.write(ByteBuffer.wrap(new byte[]{0, 0, 0, 1, 103}));
            H264Utils.escapeNAL(byteBuffer.duplicate(), bb);
            bb.flip();
            raw.write(bb);
            bb.clear();
        }
        for (ByteBuffer byteBuffer : avcC.getPpsList()) {
            raw.write(ByteBuffer.wrap(new byte[]{0, 0, 0, 1, 104}));
            H264Utils.escapeNAL(byteBuffer.duplicate(), bb);
            bb.flip();
            raw.write(bb);
            bb.clear();
        }
    }

    public static List<ByteBuffer> splitFrame(ByteBuffer frame) {
        ByteBuffer segment;
        ArrayList<ByteBuffer> result = new ArrayList<ByteBuffer>();
        while ((segment = H264Utils.nextNALUnit(frame)) != null) {
            result.add(segment);
        }
        return result;
    }

    public static ByteBuffer joinNALUnits(List<ByteBuffer> nalUnits) {
        int size = 0;
        for (ByteBuffer nal : nalUnits) {
            size += 4 + nal.remaining();
        }
        ByteBuffer allocate = ByteBuffer.allocate(size);
        H264Utils.joinNALUnitsToBuffer(nalUnits, allocate);
        return allocate;
    }

    public static void joinNALUnitsToBuffer(List<ByteBuffer> nalUnits, ByteBuffer out) {
        for (ByteBuffer nal : nalUnits) {
            out.putInt(1);
            out.put(nal.duplicate());
        }
    }

    public static ByteBuffer getAvcCData(AvcCBox avcC) {
        ByteBuffer bb = ByteBuffer.allocate(2048);
        avcC.doWrite(bb);
        bb.flip();
        return bb;
    }

    public static AvcCBox parseAVCC(VideoSampleEntry vse) {
        Box lb = NodeBox.findFirst(vse, Box.class, "avcC");
        if (lb instanceof AvcCBox) {
            return (AvcCBox)lb;
        }
        return H264Utils.parseAVCCFromBuffer(((Box.LeafBox)lb).getData().duplicate());
    }

    public static ByteBuffer saveCodecPrivate(List<ByteBuffer> spsList, List<ByteBuffer> ppsList) {
        int totalCodecPrivateSize = 0;
        for (ByteBuffer byteBuffer : spsList) {
            totalCodecPrivateSize += byteBuffer.remaining() + 5;
        }
        for (ByteBuffer byteBuffer : ppsList) {
            totalCodecPrivateSize += byteBuffer.remaining() + 5;
        }
        ByteBuffer bb = ByteBuffer.allocate(totalCodecPrivateSize);
        for (ByteBuffer byteBuffer : spsList) {
            bb.putInt(1);
            bb.put((byte)103);
            bb.put(byteBuffer.duplicate());
        }
        for (ByteBuffer byteBuffer : ppsList) {
            bb.putInt(1);
            bb.put((byte)104);
            bb.put(byteBuffer.duplicate());
        }
        bb.flip();
        return bb;
    }

    public static ByteBuffer avcCToAnnexB(AvcCBox avcC) {
        return H264Utils.saveCodecPrivate(avcC.getSpsList(), avcC.getPpsList());
    }

    public static AvcCBox parseAVCCFromBuffer(ByteBuffer bb) {
        return AvcCBox.parseAvcCBox(bb);
    }

    public static ByteBuffer writeSPS(SeqParameterSet sps, int approxSize) {
        ByteBuffer output = ByteBuffer.allocate(approxSize + 8);
        sps.write(output);
        output.flip();
        H264Utils.escapeNALinplace(output);
        return output;
    }

    public static SeqParameterSet readSPS(ByteBuffer data) {
        ByteBuffer input = NIOUtils.duplicate(data);
        H264Utils.unescapeNAL(input);
        SeqParameterSet sps = SeqParameterSet.read(input);
        return sps;
    }

    public static ByteBuffer writePPS(PictureParameterSet pps, int approxSize) {
        ByteBuffer output = ByteBuffer.allocate(approxSize + 8);
        pps.write(output);
        output.flip();
        H264Utils.escapeNALinplace(output);
        return output;
    }

    public static PictureParameterSet readPPS(ByteBuffer data) {
        ByteBuffer input = NIOUtils.duplicate(data);
        H264Utils.unescapeNAL(input);
        PictureParameterSet pps = PictureParameterSet.read(input);
        return pps;
    }

    public static PictureParameterSet findPPS(List<PictureParameterSet> ppss, int id) {
        for (PictureParameterSet pps : ppss) {
            if (pps.picParameterSetId != id) continue;
            return pps;
        }
        return null;
    }

    public static SeqParameterSet findSPS(List<SeqParameterSet> spss, int id) {
        for (SeqParameterSet sps : spss) {
            if (sps.seqParameterSetId != id) continue;
            return sps;
        }
        return null;
    }

    public static Size getPicSize(SeqParameterSet sps) {
        int w = sps.picWidthInMbsMinus1 + 1 << 4;
        int h = SeqParameterSet.getPicHeightInMbs(sps) << 4;
        if (sps.frameCroppingFlag) {
            w -= sps.frameCropLeftOffset + sps.frameCropRightOffset << sps.chromaFormatIdc.compWidth[1];
            h -= sps.frameCropTopOffset + sps.frameCropBottomOffset << sps.chromaFormatIdc.compHeight[1];
        }
        return new Size(w, h);
    }

    public static List<SeqParameterSet> readSPSFromBufferList(List<ByteBuffer> spsList) {
        ArrayList<SeqParameterSet> result = new ArrayList<SeqParameterSet>();
        for (ByteBuffer byteBuffer : spsList) {
            result.add(H264Utils.readSPS(NIOUtils.duplicate(byteBuffer)));
        }
        return result;
    }

    public static List<PictureParameterSet> readPPSFromBufferList(List<ByteBuffer> ppsList) {
        ArrayList<PictureParameterSet> result = new ArrayList<PictureParameterSet>();
        for (ByteBuffer byteBuffer : ppsList) {
            result.add(H264Utils.readPPS(NIOUtils.duplicate(byteBuffer)));
        }
        return result;
    }

    public static List<ByteBuffer> writePPSList(List<PictureParameterSet> allPps) {
        ArrayList<ByteBuffer> result = new ArrayList<ByteBuffer>();
        for (PictureParameterSet pps : allPps) {
            result.add(H264Utils.writePPS(pps, 64));
        }
        return result;
    }

    public static List<ByteBuffer> writeSPSList(List<SeqParameterSet> allSps) {
        ArrayList<ByteBuffer> result = new ArrayList<ByteBuffer>();
        for (SeqParameterSet sps : allSps) {
            result.add(H264Utils.writeSPS(sps, 256));
        }
        return result;
    }

    public static void dumpFrame(FileChannelWrapper ch, SeqParameterSet[] values, PictureParameterSet[] values2, List<ByteBuffer> nalUnits) throws IOException {
        int i;
        for (i = 0; i < values.length; ++i) {
            SeqParameterSet sps = values[i];
            NIOUtils.writeInt(ch, 1);
            NIOUtils.writeByte(ch, (byte)103);
            ch.write(H264Utils.writeSPS(sps, 128));
        }
        for (i = 0; i < values2.length; ++i) {
            PictureParameterSet pps = values2[i];
            NIOUtils.writeInt(ch, 1);
            NIOUtils.writeByte(ch, (byte)104);
            ch.write(H264Utils.writePPS(pps, 256));
        }
        for (ByteBuffer byteBuffer : nalUnits) {
            NIOUtils.writeInt(ch, 1);
            ch.write(byteBuffer.duplicate());
        }
    }

    public static void toNAL(ByteBuffer codecPrivate, SeqParameterSet sps, PictureParameterSet pps) {
        ByteBuffer bb1 = ByteBuffer.allocate(512);
        ByteBuffer bb2 = ByteBuffer.allocate(512);
        sps.write(bb1);
        pps.write(bb2);
        bb1.flip();
        bb2.flip();
        H264Utils.putNAL(codecPrivate, bb1, 103);
        H264Utils.putNAL(codecPrivate, bb2, 104);
    }

    public static void toNALList(ByteBuffer codecPrivate, List<ByteBuffer> spsList2, List<ByteBuffer> ppsList2) {
        for (ByteBuffer byteBuffer : spsList2) {
            H264Utils.putNAL(codecPrivate, byteBuffer, 103);
        }
        for (ByteBuffer byteBuffer : ppsList2) {
            H264Utils.putNAL(codecPrivate, byteBuffer, 104);
        }
    }

    private static void putNAL(ByteBuffer codecPrivate, ByteBuffer byteBuffer, int nalType) {
        ByteBuffer dst = ByteBuffer.allocate(byteBuffer.remaining() * 2);
        H264Utils.escapeNAL(byteBuffer, dst);
        dst.flip();
        codecPrivate.putInt(1);
        codecPrivate.put((byte)nalType);
        codecPrivate.put(dst);
    }

    public static List<ByteBuffer> getRawPPS(ByteBuffer codecPrivate) {
        return H264Utils.getRawNALUnitsOfType(codecPrivate, NALUnitType.PPS);
    }

    public static List<ByteBuffer> getRawSPS(ByteBuffer codecPrivate) {
        return H264Utils.getRawNALUnitsOfType(codecPrivate, NALUnitType.SPS);
    }

    public static List<ByteBuffer> getRawNALUnitsOfType(ByteBuffer codecPrivate, NALUnitType type) {
        ArrayList<ByteBuffer> result = new ArrayList<ByteBuffer>();
        for (ByteBuffer bb : H264Utils.splitFrame(codecPrivate.duplicate())) {
            NALUnit nu = NALUnit.read(bb);
            if (nu.type != type) continue;
            result.add(bb);
        }
        return result;
    }

    public static class MvList2D {
        private int[] list;
        private int stride;
        private int width;
        private int height;
        private static final int NA = Mv.packMv(0, 0, -1);

        public MvList2D(int width, int height) {
            this.list = new int[(width << 1) * height];
            this.stride = width << 1;
            this.width = width;
            this.height = height;
            this.clear();
        }

        public void clear() {
            for (int i = 0; i < this.list.length; i += 2) {
                int n = NA;
                this.list[i + 1] = n;
                this.list[i] = n;
            }
        }

        public int mv0X(int offX, int offY) {
            return Mv.mvX(this.list[(offX << 1) + this.stride * offY]);
        }

        public int mv0Y(int offX, int offY) {
            return Mv.mvY(this.list[(offX << 1) + this.stride * offY]);
        }

        public int mv0R(int offX, int offY) {
            return Mv.mvRef(this.list[(offX << 1) + this.stride * offY]);
        }

        public int mv1X(int offX, int offY) {
            return Mv.mvX(this.list[(offX << 1) + this.stride * offY + 1]);
        }

        public int mv1Y(int offX, int offY) {
            return Mv.mvY(this.list[(offX << 1) + this.stride * offY + 1]);
        }

        public int mv1R(int offX, int offY) {
            return Mv.mvRef(this.list[(offX << 1) + this.stride * offY + 1]);
        }

        public int getMv(int offX, int offY, int forward) {
            return this.list[(offX << 1) + this.stride * offY + forward];
        }

        public void setMv(int offX, int offY, int forward, int mv) {
            this.list[(offX << 1) + this.stride * offY + forward] = mv;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }
    }

    public static class MvList {
        private int[] list;
        private static final int NA = Mv.packMv(0, 0, -1);

        public MvList(int size) {
            this.list = new int[size << 1];
            this.clear();
        }

        public void clear() {
            for (int i = 0; i < this.list.length; i += 2) {
                int n = NA;
                this.list[i + 1] = n;
                this.list[i] = n;
            }
        }

        public int mv0X(int off) {
            return Mv.mvX(this.list[off << 1]);
        }

        public int mv0Y(int off) {
            return Mv.mvY(this.list[off << 1]);
        }

        public int mv0R(int off) {
            return Mv.mvRef(this.list[off << 1]);
        }

        public int mv1X(int off) {
            return Mv.mvX(this.list[(off << 1) + 1]);
        }

        public int mv1Y(int off) {
            return Mv.mvY(this.list[(off << 1) + 1]);
        }

        public int mv1R(int off) {
            return Mv.mvRef(this.list[(off << 1) + 1]);
        }

        public int getMv(int off, int forward) {
            return this.list[(off << 1) + forward];
        }

        public void setMv(int off, int forward, int mv) {
            this.list[(off << 1) + forward] = mv;
        }

        public void setPair(int off, int mv0, int mv1) {
            this.list[off << 1] = mv0;
            this.list[(off << 1) + 1] = mv1;
        }

        public void copyPair(int off, MvList other, int otherOff) {
            this.list[off << 1] = other.list[otherOff << 1];
            this.list[(off << 1) + 1] = other.list[(otherOff << 1) + 1];
        }
    }

    public static class Mv {
        public static int mvX(int mv) {
            return mv << 18 >> 18;
        }

        public static int mvY(int mv) {
            return mv << 6 >> 20;
        }

        public static int mvRef(int mv) {
            return mv >> 26;
        }

        public static int packMv(int mvx, int mvy, int r) {
            return (r & 0x3F) << 26 | (mvy & 0xFFF) << 14 | mvx & 0x3FFF;
        }

        public static int mvC(int mv, int comp) {
            return comp == 0 ? Mv.mvX(mv) : Mv.mvY(mv);
        }
    }

    public static abstract class SliceHeaderTweaker {
        protected List<SeqParameterSet> sps;
        protected List<PictureParameterSet> pps;

        protected abstract void tweak(SliceHeader var1);

        public SliceHeader run(ByteBuffer is, ByteBuffer os, NALUnit nu) {
            ByteBuffer nal = os.duplicate();
            H264Utils.unescapeNAL(is);
            BitReader reader = BitReader.createBitReader(is);
            SliceHeader sh = SliceHeaderReader.readPart1(reader);
            PictureParameterSet pp = H264Utils.findPPS(this.pps, sh.picParameterSetId);
            return this.part2(is, os, nu, H264Utils.findSPS(this.sps, pp.picParameterSetId), pp, nal, reader, sh);
        }

        public SliceHeader runSpsPps(ByteBuffer is, ByteBuffer os, NALUnit nu, SeqParameterSet sps, PictureParameterSet pps) {
            ByteBuffer nal = os.duplicate();
            H264Utils.unescapeNAL(is);
            BitReader reader = BitReader.createBitReader(is);
            SliceHeader sh = SliceHeaderReader.readPart1(reader);
            return this.part2(is, os, nu, sps, pps, nal, reader, sh);
        }

        private SliceHeader part2(ByteBuffer is, ByteBuffer os, NALUnit nu, SeqParameterSet sps, PictureParameterSet pps, ByteBuffer nal, BitReader reader, SliceHeader sh) {
            BitWriter writer = new BitWriter(os);
            SliceHeaderReader.readPart2(sh, nu, sps, pps, reader);
            this.tweak(sh);
            SliceHeaderWriter.write(sh, nu.type == NALUnitType.IDR_SLICE, nu.nal_ref_idc, writer);
            if (pps.entropyCodingModeFlag) {
                this.copyDataCABAC(is, os, reader, writer);
            } else {
                this.copyDataCAVLC(is, os, reader, writer);
            }
            nal.limit(os.position());
            H264Utils.escapeNALinplace(nal);
            os.position(nal.limit());
            return sh;
        }

        private void copyDataCAVLC(ByteBuffer is, ByteBuffer os, BitReader reader, BitWriter writer) {
            int wLeft = 8 - writer.curBit();
            if (wLeft != 0) {
                writer.writeNBit(reader.readNBit(wLeft), wLeft);
            }
            writer.flush();
            int shift = reader.curBit();
            if (shift != 0) {
                int mShift = 8 - shift;
                int inp = reader.readNBit(mShift);
                reader.stop();
                while (is.hasRemaining()) {
                    int out = inp << shift;
                    inp = is.get() & 0xFF;
                    os.put((byte)(out |= inp >> mShift));
                }
                os.put((byte)(inp << shift));
            } else {
                reader.stop();
                os.put(is);
            }
        }

        private void copyDataCABAC(ByteBuffer is, ByteBuffer os, BitReader reader, BitWriter writer) {
            long rem;
            long bp = reader.curBit();
            if (bp != 0L && (long)((1 << (int)(8L - bp)) - 1) != (rem = (long)reader.readNBit(8 - (int)bp))) {
                throw new RuntimeException("Invalid CABAC padding");
            }
            if (writer.curBit() != 0) {
                writer.writeNBit(255, 8 - writer.curBit());
            }
            writer.flush();
            reader.stop();
            os.put(is);
        }
    }
}
