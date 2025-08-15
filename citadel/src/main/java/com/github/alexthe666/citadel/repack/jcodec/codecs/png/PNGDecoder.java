/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.png;

import com.github.alexthe666.citadel.repack.jcodec.codecs.png.IHDR;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class PNGDecoder
extends VideoDecoder {
    private static final int FILTER_TYPE_LOCO = 64;
    private static final int FILTER_VALUE_NONE = 0;
    private static final int FILTER_VALUE_SUB = 1;
    private static final int FILTER_VALUE_UP = 2;
    private static final int FILTER_VALUE_AVG = 3;
    private static final int FILTER_VALUE_PAETH = 4;
    private static final int PNG_COLOR_TYPE_GRAY = 0;
    private static final int PNG_COLOR_TYPE_PALETTE = 3;
    private static final int PNG_COLOR_TYPE_RGB = 2;
    private static final int alphaR = 127;
    private static final int alphaG = 127;
    private static final int alphaB = 127;
    private static final int[] logPassStep = new int[]{3, 3, 2, 2, 1, 1, 0};
    private static final int[] logPassRowStep = new int[]{3, 3, 3, 2, 2, 1, 1};
    private static final int[] passOff = new int[]{0, 4, 0, 2, 0, 1, 0};
    private static final int[] passRowOff = new int[]{0, 0, 4, 0, 2, 0, 1};
    private byte[] ca = new byte[4];

    @Override
    public Picture decodeFrame(ByteBuffer data, byte[][] buffer) {
        if (!PNGDecoder.ispng(data)) {
            throw new RuntimeException("Not a PNG file.");
        }
        IHDR ihdr = null;
        PLTE plte = null;
        TRNS trns = null;
        ArrayList<ByteBuffer> list = new ArrayList<ByteBuffer>();
        block9: while (data.remaining() >= 8) {
            int length = data.getInt();
            int tag = data.getInt();
            if (data.remaining() < length) break;
            switch (tag) {
                case 1229472850: {
                    ihdr = new IHDR();
                    ihdr.parse(data);
                    continue block9;
                }
                case 1347179589: {
                    plte = new PLTE();
                    plte.parse(data, length);
                    continue block9;
                }
                case 1951551059: {
                    if (ihdr == null) {
                        throw new IllegalStateException("tRNS tag before IHDR");
                    }
                    trns = new TRNS(ihdr.colorType);
                    trns.parse(data, length);
                    continue block9;
                }
                case 1229209940: {
                    list.add(NIOUtils.read(data, length));
                    NIOUtils.skip(data, 4);
                    continue block9;
                }
                case 1229278788: {
                    NIOUtils.skip(data, 4);
                    continue block9;
                }
            }
            data.position(data.position() + length + 4);
        }
        if (ihdr != null) {
            try {
                this.decodeData(ihdr, plte, trns, list, buffer);
            }
            catch (DataFormatException e) {
                return null;
            }
            return Picture.createPicture(ihdr.width, ihdr.height, buffer, ihdr.colorSpace());
        }
        throw new IllegalStateException("no IHDR tag");
    }

    private void decodeData(IHDR ihdr, PLTE plte, TRNS trns, List<ByteBuffer> list, byte[][] buffer) throws DataFormatException {
        int bpp = ihdr.getBitsPerPixel() + 7 >> 3;
        int passes = ihdr.interlaceType == 0 ? 1 : 7;
        Inflater inflater = new Inflater();
        Iterator<ByteBuffer> it = list.iterator();
        block7: for (int pass = 0; pass < passes; ++pass) {
            int colStep;
            int rowStep;
            int colStart;
            int rowStart;
            int rowSize;
            if (ihdr.interlaceType == 0) {
                rowSize = ihdr.rowSize() + 1;
                rowStart = 0;
                colStart = 0;
                rowStep = 1;
                colStep = 1;
            } else {
                int round = (1 << logPassStep[pass]) - 1;
                rowSize = (ihdr.width + round >> logPassStep[pass]) + 1;
                rowStart = passRowOff[pass];
                rowStep = 1 << logPassRowStep[pass];
                colStart = passOff[pass];
                colStep = 1 << logPassStep[pass];
            }
            byte[] lastRow = new byte[rowSize - 1];
            byte[] uncompressed = new byte[rowSize];
            int bptr = 3 * (ihdr.width * rowStart + colStart);
            for (int row = rowStart; row < ihdr.height; row += rowStep) {
                int nalpha;
                int alpha;
                int j;
                int i;
                int count = inflater.inflate(uncompressed);
                if (count < uncompressed.length && inflater.needsInput()) {
                    if (!it.hasNext()) {
                        Logger.warn(String.format("Data truncation at row %d", row));
                        continue block7;
                    }
                    ByteBuffer next = it.next();
                    inflater.setInput(NIOUtils.toArray(next));
                    int toRead = uncompressed.length - count;
                    count = inflater.inflate(uncompressed, count, toRead);
                    if (count != toRead) {
                        Logger.warn(String.format("Data truncation at row %d", row));
                        continue block7;
                    }
                }
                byte filter = uncompressed[0];
                switch (filter) {
                    case 0: {
                        System.arraycopy(uncompressed, 1, lastRow, 0, rowSize - 1);
                        break;
                    }
                    case 1: {
                        PNGDecoder.filterSub(uncompressed, rowSize - 1, lastRow, bpp);
                        break;
                    }
                    case 2: {
                        PNGDecoder.filterUp(uncompressed, rowSize - 1, lastRow);
                        break;
                    }
                    case 3: {
                        PNGDecoder.filterAvg(uncompressed, rowSize - 1, lastRow, bpp);
                        break;
                    }
                    case 4: {
                        this.filterPaeth(uncompressed, rowSize - 1, lastRow, bpp);
                    }
                }
                int bptrWas = bptr;
                if ((ihdr.colorType & 1) != 0) {
                    i = 0;
                    while (i < rowSize - 1) {
                        int plt = plte.palette[lastRow[i] & 0xFF];
                        buffer[0][bptr] = (byte)((plt >> 16 & 0xFF) - 128);
                        buffer[0][bptr + 1] = (byte)((plt >> 8 & 0xFF) - 128);
                        buffer[0][bptr + 2] = (byte)((plt & 0xFF) - 128);
                        i += bpp;
                        bptr += 3 * colStep;
                    }
                } else if ((ihdr.colorType & 2) != 0) {
                    i = 0;
                    while (i < rowSize - 1) {
                        buffer[0][bptr] = (byte)((lastRow[i] & 0xFF) - 128);
                        buffer[0][bptr + 1] = (byte)((lastRow[i + 1] & 0xFF) - 128);
                        buffer[0][bptr + 2] = (byte)((lastRow[i + 2] & 0xFF) - 128);
                        i += bpp;
                        bptr += 3 * colStep;
                    }
                } else {
                    i = 0;
                    while (i < rowSize - 1) {
                        byte by = (byte)((lastRow[i] & 0xFF) - 128);
                        buffer[0][bptr + 2] = by;
                        buffer[0][bptr + 1] = by;
                        buffer[0][bptr] = by;
                        i += bpp;
                        bptr += 3 * colStep;
                    }
                }
                if ((ihdr.colorType & 4) != 0) {
                    i = bpp - 1;
                    j = bptrWas;
                    while (i < rowSize - 1) {
                        alpha = lastRow[i] & 0xFF;
                        nalpha = 256 - alpha;
                        buffer[0][j] = (byte)(127 * nalpha + buffer[0][j] * alpha >> 8);
                        buffer[0][j + 1] = (byte)(127 * nalpha + buffer[0][j + 1] * alpha >> 8);
                        buffer[0][j + 2] = (byte)(127 * nalpha + buffer[0][j + 2] * alpha >> 8);
                        i += bpp;
                        j += 3 * colStep;
                    }
                } else if (trns != null) {
                    if (ihdr.colorType == 3) {
                        i = 0;
                        j = bptrWas;
                        while (i < rowSize - 1) {
                            alpha = trns.alphaPal[lastRow[i] & 0xFF] & 0xFF;
                            nalpha = 256 - alpha;
                            buffer[0][j] = (byte)(127 * nalpha + buffer[0][j] * alpha >> 8);
                            buffer[0][j + 1] = (byte)(127 * nalpha + buffer[0][j + 1] * alpha >> 8);
                            buffer[0][j + 2] = (byte)(127 * nalpha + buffer[0][j + 2] * alpha >> 8);
                            ++i;
                            j += 3 * colStep;
                        }
                    } else if (ihdr.colorType == 2) {
                        int ar = (trns.alphaR & 0xFF) - 128;
                        int ag = (trns.alphaG & 0xFF) - 128;
                        int ab = (trns.alphaB & 0xFF) - 128;
                        if (ab != 127 || ag != 127 || ar != 127) {
                            int i2 = 0;
                            int j2 = bptrWas;
                            while (i2 < rowSize - 1) {
                                if (buffer[0][j2] == ar && buffer[0][j2 + 1] == ag && buffer[0][j2 + 2] == ab) {
                                    buffer[0][j2] = 127;
                                    buffer[0][j2 + 1] = 127;
                                    buffer[0][j2 + 2] = 127;
                                }
                                i2 += bpp;
                                j2 += 3 * colStep;
                            }
                        }
                    } else if (ihdr.colorType == 0) {
                        i = 0;
                        j = bptrWas;
                        while (i < rowSize - 1) {
                            if (lastRow[i] == trns.alphaGrey) {
                                buffer[0][j] = 127;
                                buffer[0][j + 1] = 127;
                                buffer[0][j + 2] = 127;
                            }
                            ++i;
                            j += 3 * colStep;
                        }
                    }
                }
                bptr = bptrWas + 3 * ihdr.width * rowStep;
            }
        }
    }

    private void filterPaeth(byte[] uncompressed, int rowSize, byte[] lastRow, int bpp) {
        int i;
        for (i = 0; i < bpp; ++i) {
            this.ca[i] = lastRow[i];
            lastRow[i] = (byte)((uncompressed[i + 1] & 0xFF) + (lastRow[i] & 0xFF));
        }
        for (i = bpp; i < rowSize; ++i) {
            int a = lastRow[i - bpp] & 0xFF;
            int b = lastRow[i] & 0xFF;
            int c = this.ca[i % bpp] & 0xFF;
            int p = b - c;
            int pc = a - c;
            int pa = MathUtil.abs(p);
            int pb = MathUtil.abs(pc);
            pc = MathUtil.abs(p + pc);
            p = pa <= pb && pa <= pc ? a : (pb <= pc ? b : c);
            this.ca[i % bpp] = lastRow[i];
            lastRow[i] = (byte)(p + (uncompressed[i + 1] & 0xFF));
        }
    }

    private static void filterSub(byte[] uncompressed, int rowSize, byte[] lastRow, int bpp) {
        switch (bpp) {
            case 1: {
                PNGDecoder.filterSub1(uncompressed, lastRow, rowSize);
                break;
            }
            case 2: {
                PNGDecoder.filterSub2(uncompressed, lastRow, rowSize);
                break;
            }
            case 3: {
                PNGDecoder.filterSub3(uncompressed, lastRow, rowSize);
                break;
            }
            default: {
                PNGDecoder.filterSub4(uncompressed, lastRow, rowSize);
            }
        }
    }

    private static void filterAvg(byte[] uncompressed, int rowSize, byte[] lastRow, int bpp) {
        switch (bpp) {
            case 1: {
                PNGDecoder.filterAvg1(uncompressed, lastRow, rowSize);
                break;
            }
            case 2: {
                PNGDecoder.filterAvg2(uncompressed, lastRow, rowSize);
                break;
            }
            case 3: {
                PNGDecoder.filterAvg3(uncompressed, lastRow, rowSize);
                break;
            }
            default: {
                PNGDecoder.filterAvg4(uncompressed, lastRow, rowSize);
            }
        }
    }

    private static void filterSub1(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p = lastRow[0] = uncompressed[1];
        for (int i = 1; i < rowSize; ++i) {
            p = lastRow[i] = (byte)((p & 0xFF) + (uncompressed[i + 1] & 0xFF));
        }
    }

    private static void filterUp(byte[] uncompressed, int rowSize, byte[] lastRow) {
        for (int i = 0; i < rowSize; ++i) {
            lastRow[i] = (byte)((lastRow[i] & 0xFF) + (uncompressed[i + 1] & 0xFF));
        }
    }

    private static void filterAvg1(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p = lastRow[0] = (byte)((uncompressed[1] & 0xFF) + ((lastRow[0] & 0xFF) >> 1));
        for (int i = 1; i < rowSize; ++i) {
            p = lastRow[i] = (byte)(((lastRow[i] & 0xFF) + (p & 0xFF) >> 1) + (uncompressed[i + 1] & 0xFF));
        }
    }

    private static void filterSub2(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p0 = lastRow[0] = uncompressed[1];
        byte p1 = lastRow[1] = uncompressed[2];
        for (int i = 2; i < rowSize; i += 2) {
            p0 = lastRow[i] = (byte)((p0 & 0xFF) + (uncompressed[1 + i] & 0xFF));
            byte by = (byte)((p1 & 0xFF) + (uncompressed[2 + i] & 0xFF));
            lastRow[i + 1] = by;
            p1 = by;
        }
    }

    private static void filterAvg2(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p0 = lastRow[0] = (byte)((uncompressed[1] & 0xFF) + ((lastRow[0] & 0xFF) >> 1));
        byte p1 = lastRow[1] = (byte)((uncompressed[2] & 0xFF) + ((lastRow[1] & 0xFF) >> 1));
        for (int i = 2; i < rowSize; i += 2) {
            p0 = lastRow[i] = (byte)(((lastRow[i] & 0xFF) + (p0 & 0xFF) >> 1) + (uncompressed[1 + i] & 0xFF));
            byte by = (byte)(((lastRow[i + 1] & 0xFF) + (p1 & 0xFF) >> 1) + (uncompressed[i + 2] & 0xFF));
            lastRow[i + 1] = by;
            p1 = by;
        }
    }

    private static void filterSub3(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p0 = lastRow[0] = uncompressed[1];
        byte p1 = lastRow[1] = uncompressed[2];
        byte p2 = lastRow[2] = uncompressed[3];
        for (int i = 3; i < rowSize; i += 3) {
            p0 = lastRow[i] = (byte)((p0 & 0xFF) + (uncompressed[i + 1] & 0xFF));
            byte by = (byte)((p1 & 0xFF) + (uncompressed[i + 2] & 0xFF));
            lastRow[i + 1] = by;
            p1 = by;
            byte by2 = (byte)((p2 & 0xFF) + (uncompressed[i + 3] & 0xFF));
            lastRow[i + 2] = by2;
            p2 = by2;
        }
    }

    private static void filterAvg3(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p0 = lastRow[0] = (byte)((uncompressed[1] & 0xFF) + ((lastRow[0] & 0xFF) >> 1));
        byte p1 = lastRow[1] = (byte)((uncompressed[2] & 0xFF) + ((lastRow[1] & 0xFF) >> 1));
        byte p2 = lastRow[2] = (byte)((uncompressed[3] & 0xFF) + ((lastRow[2] & 0xFF) >> 1));
        for (int i = 3; i < rowSize; i += 3) {
            p0 = lastRow[i] = (byte)(((lastRow[i] & 0xFF) + (p0 & 0xFF) >> 1) + (uncompressed[i + 1] & 0xFF));
            byte by = (byte)(((lastRow[i + 1] & 0xFF) + (p1 & 0xFF) >> 1) + (uncompressed[i + 2] & 0xFF));
            lastRow[i + 1] = by;
            p1 = by;
            byte by2 = (byte)(((lastRow[i + 2] & 0xFF) + (p2 & 0xFF) >> 1) + (uncompressed[i + 3] & 0xFF));
            lastRow[i + 2] = by2;
            p2 = by2;
        }
    }

    private static void filterSub4(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p0 = lastRow[0] = uncompressed[1];
        byte p1 = lastRow[1] = uncompressed[2];
        byte p2 = lastRow[2] = uncompressed[3];
        byte p3 = lastRow[3] = uncompressed[4];
        for (int i = 4; i < rowSize; i += 4) {
            p0 = lastRow[i] = (byte)((p0 & 0xFF) + (uncompressed[i + 1] & 0xFF));
            byte by = (byte)((p1 & 0xFF) + (uncompressed[i + 2] & 0xFF));
            lastRow[i + 1] = by;
            p1 = by;
            byte by2 = (byte)((p2 & 0xFF) + (uncompressed[i + 3] & 0xFF));
            lastRow[i + 2] = by2;
            p2 = by2;
            byte by3 = (byte)((p3 & 0xFF) + (uncompressed[i + 4] & 0xFF));
            lastRow[i + 3] = by3;
            p3 = by3;
        }
    }

    private static void filterAvg4(byte[] uncompressed, byte[] lastRow, int rowSize) {
        byte p0 = lastRow[0] = (byte)((uncompressed[1] & 0xFF) + ((lastRow[0] & 0xFF) >> 1));
        byte p1 = lastRow[1] = (byte)((uncompressed[2] & 0xFF) + ((lastRow[1] & 0xFF) >> 1));
        byte p2 = lastRow[2] = (byte)((uncompressed[3] & 0xFF) + ((lastRow[2] & 0xFF) >> 1));
        byte p3 = lastRow[3] = (byte)((uncompressed[4] & 0xFF) + ((lastRow[3] & 0xFF) >> 1));
        for (int i = 4; i < rowSize; i += 4) {
            p0 = lastRow[i] = (byte)(((lastRow[i] & 0xFF) + (p0 & 0xFF) >> 1) + (uncompressed[i + 1] & 0xFF));
            byte by = (byte)(((lastRow[i + 1] & 0xFF) + (p1 & 0xFF) >> 1) + (uncompressed[i + 2] & 0xFF));
            lastRow[i + 1] = by;
            p1 = by;
            byte by2 = (byte)(((lastRow[i + 2] & 0xFF) + (p2 & 0xFF) >> 1) + (uncompressed[i + 3] & 0xFF));
            lastRow[i + 2] = by2;
            p2 = by2;
            byte by3 = (byte)(((lastRow[i + 3] & 0xFF) + (p3 & 0xFF) >> 1) + (uncompressed[i + 4] & 0xFF));
            lastRow[i + 3] = by3;
            p3 = by3;
        }
    }

    @Override
    public VideoCodecMeta getCodecMeta(ByteBuffer _data) {
        ByteBuffer data = _data.duplicate();
        if (!PNGDecoder.ispng(data)) {
            throw new RuntimeException("Not a PNG file.");
        }
        while (data.remaining() >= 8) {
            int length = data.getInt();
            int tag = data.getInt();
            if (data.remaining() < length) break;
            switch (tag) {
                case 1229472850: {
                    IHDR ihdr = new IHDR();
                    ihdr.parse(data);
                    return VideoCodecMeta.createSimpleVideoCodecMeta(new Size(ihdr.width, ihdr.height), ColorSpace.RGB);
                }
            }
            data.position(data.position() + length + 4);
        }
        return null;
    }

    private static boolean ispng(ByteBuffer data) {
        int sighi = data.getInt();
        int siglo = data.getInt();
        boolean ispng = !(sighi != -1991225785 && sighi != -1974645177 || siglo != 218765834 && siglo != 218765834);
        return ispng;
    }

    public static int probe(ByteBuffer data) {
        if (!PNGDecoder.ispng(data)) {
            return 100;
        }
        return 0;
    }

    public static byte[] deflate(byte[] data, Inflater inflater) throws DataFormatException {
        inflater.setInput(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[16384];
        while (!inflater.needsInput()) {
            int count = inflater.inflate(buffer);
            baos.write(buffer, 0, count);
            System.out.println(baos.size());
        }
        return baos.toByteArray();
    }

    public static class TRNS {
        private int colorType;
        byte[] alphaPal;
        byte alphaGrey;
        byte alphaR;
        byte alphaG;
        byte alphaB;

        TRNS(byte colorType) {
            this.colorType = colorType;
        }

        public void parse(ByteBuffer data, int length) {
            if (this.colorType == 3) {
                this.alphaPal = new byte[256];
                data.get(this.alphaPal, 0, length);
                for (int i = length; i < 256; ++i) {
                    this.alphaPal[i] = -1;
                }
            } else if (this.colorType == 0) {
                this.alphaGrey = data.get();
            } else if (this.colorType == 2) {
                this.alphaR = data.get();
                this.alphaG = data.get();
                this.alphaG = data.get();
            }
            data.getInt();
        }
    }

    private static class PLTE {
        int[] palette;

        private PLTE() {
        }

        public void parse(ByteBuffer data, int length) {
            int i;
            if (length % 3 != 0 || length > 768) {
                throw new RuntimeException("Invalid data");
            }
            int n = length / 3;
            this.palette = new int[n];
            for (i = 0; i < n; ++i) {
                this.palette[i] = 0xFF000000 | (data.get() & 0xFF) << 16 | (data.get() & 0xFF) << 8 | data.get() & 0xFF;
            }
            while (i < 256) {
                this.palette[i] = -16777216;
                ++i;
            }
            data.getInt();
        }
    }
}
