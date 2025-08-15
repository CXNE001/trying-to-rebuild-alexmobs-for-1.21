/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg;

import com.github.alexthe666.citadel.repack.jcodec.api.UnhandledStateException;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg.FrameHeader;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg.JpegConst;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg.ScanHeader;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.VideoDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.dct.SimpleIDCT10Bit;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLC;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLCBuilder;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rect;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class JpegDecoder
extends VideoDecoder {
    private boolean interlace;
    private boolean topFieldFirst;
    int[] buf = new int[64];

    public void setInterlace(boolean interlace, boolean topFieldFirst) {
        this.interlace = interlace;
        this.topFieldFirst = topFieldFirst;
    }

    private Picture decodeScan(ByteBuffer data, FrameHeader header, ScanHeader scan, VLC[] huffTables, int[][] quant, byte[][] data2, int field, int step) {
        int blockW = header.getHmax();
        int blockH = header.getVmax();
        int mcuW = blockW << 3;
        int mcuH = blockH << 3;
        int width = header.width;
        int height = header.height;
        int xBlocks = width + mcuW - 1 >> blockW + 2;
        int yBlocks = height + mcuH - 1 >> blockH + 2;
        int nn = blockW + blockH;
        Picture result = new Picture(xBlocks << blockW + 2, yBlocks << blockH + 2, data2, null, nn == 4 ? ColorSpace.YUV420J : (nn == 3 ? ColorSpace.YUV422J : ColorSpace.YUV444J), 0, new Rect(0, 0, width, height));
        BitReader bits = BitReader.createBitReader(data);
        int[] dcPredictor = new int[]{1024, 1024, 1024};
        for (int by = 0; by < yBlocks; ++by) {
            for (int bx = 0; bx < xBlocks && bits.moreData(); ++bx) {
                this.decodeMCU(bits, dcPredictor, quant, huffTables, result, bx, by, blockW, blockH, field, step);
            }
        }
        return result;
    }

    private static void putBlock(byte[] plane, int stride, int[] patch, int x, int y, int field, int step) {
        int dstride = step * stride;
        int off = field * stride + y * dstride + x;
        int poff = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                plane[j + off] = (byte)(MathUtil.clip(patch[j + poff], 0, 255) - 128);
            }
            off += dstride;
            poff += 8;
        }
    }

    private void decodeMCU(BitReader bits, int[] dcPredictor, int[][] quant, VLC[] huff, Picture result, int bx, int by, int blockH, int blockV, int field, int step) {
        int sx = bx << blockH - 1;
        int sy = by << blockV - 1;
        for (int i = 0; i < blockV; ++i) {
            for (int j = 0; j < blockH; ++j) {
                this.decodeBlock(bits, dcPredictor, quant, huff, result, this.buf, sx + j << 3, sy + i << 3, 0, 0, field, step);
            }
        }
        this.decodeBlock(bits, dcPredictor, quant, huff, result, this.buf, bx << 3, by << 3, 1, 1, field, step);
        this.decodeBlock(bits, dcPredictor, quant, huff, result, this.buf, bx << 3, by << 3, 2, 1, field, step);
    }

    void decodeBlock(BitReader bits, int[] dcPredictor, int[][] quant, VLC[] huff, Picture result, int[] buf, int blkX, int blkY, int plane, int chroma, int field, int step) {
        Arrays.fill(buf, 0);
        dcPredictor[plane] = buf[0] = JpegDecoder.readDCValue(bits, huff[chroma]) * quant[chroma][0] + dcPredictor[plane];
        this.readACValues(bits, buf, huff[chroma + 2], quant[chroma]);
        SimpleIDCT10Bit.idct10(buf, 0);
        JpegDecoder.putBlock(result.getPlaneData(plane), result.getPlaneWidth(plane), buf, blkX, blkY, field, step);
    }

    static int readDCValue(BitReader _in, VLC table) {
        int code = table.readVLC16(_in);
        return code != 0 ? JpegDecoder.toValue(_in.readNBit(code), code) : 0;
    }

    void readACValues(BitReader _in, int[] target, VLC table, int[] quantTable) {
        int code;
        int curOff = 1;
        do {
            if ((code = table.readVLC16(_in)) == 240) {
                curOff += 16;
                continue;
            }
            if (code <= 0) continue;
            int rle = code >> 4;
            int len = code & 0xF;
            target[JpegConst.naturalOrder[curOff += rle]] = JpegDecoder.toValue(_in.readNBit(len), len) * quantTable[curOff];
            ++curOff;
        } while (code != 0 && curOff < 64);
    }

    static int toValue(int raw, int length) {
        return length >= 1 && raw < 1 << length - 1 ? -(1 << length) + 1 + raw : raw;
    }

    @Override
    public Picture decodeFrame(ByteBuffer data, byte[][] data2) {
        if (this.interlace) {
            Picture r1 = this.decodeField(data, data2, this.topFieldFirst ? 0 : 1, 2);
            Picture r2 = this.decodeField(data, data2, this.topFieldFirst ? 1 : 0, 2);
            return Picture.createPicture(r1.getWidth(), r1.getHeight() << 1, data2, r1.getColor());
        }
        return this.decodeField(data, data2, 0, 1);
    }

    public Picture decodeField(ByteBuffer data, byte[][] data2, int field, int step) {
        Picture result = null;
        FrameHeader header = null;
        VLC[] huffTables = new VLC[]{JpegConst.YDC_DEFAULT, JpegConst.CDC_DEFAULT, JpegConst.YAC_DEFAULT, JpegConst.CAC_DEFAULT};
        int[][] quant = new int[][]{JpegConst.DEFAULT_QUANT_LUMA, JpegConst.DEFAULT_QUANT_CHROMA};
        ScanHeader scan = null;
        boolean skipToNext = false;
        while (data.hasRemaining()) {
            ByteBuffer buf;
            int b;
            int marker;
            if (!skipToNext) {
                marker = data.get() & 0xFF;
            } else {
                while ((marker = data.get() & 0xFF) != 255) {
                }
            }
            skipToNext = false;
            if (marker == 0) continue;
            if (marker != 255) {
                throw new RuntimeException("@" + Long.toHexString(data.position()) + " Marker expected: 0x" + Integer.toHexString(marker));
            }
            while ((b = data.get() & 0xFF) == 255) {
            }
            if (b == 192) {
                header = FrameHeader.read(data);
                continue;
            }
            if (b == 196) {
                int len1 = data.getShort() & 0xFFFF;
                buf = NIOUtils.read(data, len1 - 2);
                while (buf.hasRemaining()) {
                    int tableNo = buf.get() & 0xFF;
                    huffTables[tableNo & 1 | tableNo >> 3 & 2] = JpegDecoder.readHuffmanTable(buf);
                }
                continue;
            }
            if (b == 219) {
                int len4 = data.getShort() & 0xFFFF;
                buf = NIOUtils.read(data, len4 - 2);
                while (buf.hasRemaining()) {
                    int ind = buf.get() & 0xFF;
                    quant[ind] = JpegDecoder.readQuantTable(buf);
                }
                continue;
            }
            if (b == 218) {
                if (scan != null) {
                    throw new UnhandledStateException("unhandled - more than one scan header");
                }
                scan = ScanHeader.read(data);
                result = this.decodeScan(JpegDecoder.readToMarker(data), header, scan, huffTables, quant, data2, field, step);
                continue;
            }
            if (b == 216 || b >= 208 && b <= 215) {
                Logger.warn("SOI not supported.");
                skipToNext = true;
                continue;
            }
            if (b == 217) break;
            if (b >= 224 && b <= 254) {
                int len3 = data.getShort() & 0xFFFF;
                NIOUtils.read(data, len3 - 2);
                continue;
            }
            if (b == 221) {
                Logger.warn("DRI not supported.");
                skipToNext = true;
                continue;
            }
            if (b != 0) {
                Logger.warn("unhandled marker " + JpegConst.markerToString(b));
            }
            skipToNext = true;
        }
        return result;
    }

    private static ByteBuffer readToMarker(ByteBuffer data) {
        ByteBuffer out = ByteBuffer.allocate(data.remaining());
        while (data.hasRemaining()) {
            byte b0 = data.get();
            if (b0 == -1) {
                byte b1 = data.get();
                if (b1 == 0) {
                    out.put((byte)-1);
                    continue;
                }
                data.position(data.position() - 2);
                break;
            }
            out.put(b0);
        }
        out.flip();
        return out;
    }

    private static VLC readHuffmanTable(ByteBuffer data) {
        VLCBuilder builder = new VLCBuilder();
        byte[] levelSizes = NIOUtils.toArray(NIOUtils.read(data, 16));
        int levelStart = 0;
        for (int i = 0; i < 16; ++i) {
            int length = levelSizes[i] & 0xFF;
            for (int c = 0; c < length; ++c) {
                int val = data.get() & 0xFF;
                int code = levelStart++;
                builder.setInt(code, i + 1, val);
            }
            levelStart <<= 1;
        }
        return builder.getVLC();
    }

    private static int[] readQuantTable(ByteBuffer data) {
        int[] result = new int[64];
        for (int i = 0; i < 64; ++i) {
            result[i] = data.get() & 0xFF;
        }
        return result;
    }

    @Override
    public VideoCodecMeta getCodecMeta(ByteBuffer data) {
        FrameHeader header = null;
        while (data.hasRemaining()) {
            int type;
            while (data.hasRemaining() && (data.get() & 0xFF) != 255) {
            }
            while ((type = data.get() & 0xFF) == 255) {
            }
            if (type != 192) continue;
            header = FrameHeader.read(data);
            break;
        }
        if (header != null) {
            int blockH;
            int blockW = header.getHmax();
            int nn = blockW + (blockH = header.getVmax());
            ColorSpace color = nn == 4 ? ColorSpace.YUV420J : (nn == 3 ? ColorSpace.YUV422J : ColorSpace.YUV444J);
            return VideoCodecMeta.createSimpleVideoCodecMeta(new Size(header.width, header.height), color);
        }
        return null;
    }
}
