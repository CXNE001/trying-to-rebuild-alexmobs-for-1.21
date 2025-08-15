/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Utils;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.SliceHeaderReader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnit;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnitType;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SeqParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceHeader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.write.SliceHeaderWriter;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;
import java.nio.ByteBuffer;
import java.util.List;

public static abstract class H264Utils.SliceHeaderTweaker {
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
