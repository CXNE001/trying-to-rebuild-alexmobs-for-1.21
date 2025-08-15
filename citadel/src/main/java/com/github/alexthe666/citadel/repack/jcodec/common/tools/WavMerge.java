/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.tools;

import com.github.alexthe666.citadel.repack.jcodec.codecs.wav.WavHeader;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioUtil;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.IOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class WavMerge {
    public static void main1(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("wavmerge <output wav> <input wav> .... <input wav>");
            System.exit(-1);
        }
        File out = new File(args[0]);
        File[] ins = new File[args.length - 1];
        for (int i = 1; i < args.length; ++i) {
            ins[i - 1] = new File(args[i]);
        }
        WavMerge.merge(out, ins);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void merge(File result, File[] src) throws IOException {
        FileChannelWrapper out = null;
        ReadableByteChannel[] inputs = new ReadableByteChannel[src.length];
        WavHeader[] headers = new WavHeader[src.length];
        ByteBuffer[] ins = new ByteBuffer[src.length];
        try {
            short sampleSize = -1;
            for (int i = 0; i < src.length; ++i) {
                inputs[i] = NIOUtils.readableChannel(src[i]);
                WavHeader hdr = WavHeader.readChannel(inputs[i]);
                if (sampleSize != -1 && sampleSize != hdr.fmt.bitsPerSample) {
                    throw new RuntimeException("Input files have different sample sizes");
                }
                sampleSize = hdr.fmt.bitsPerSample;
                headers[i] = hdr;
                ins[i] = ByteBuffer.allocate(hdr.getFormat().framesToBytes(4096));
            }
            ByteBuffer outb = ByteBuffer.allocate(headers[0].getFormat().framesToBytes(4096) * src.length);
            WavHeader newHeader = WavHeader.multiChannelWav(headers);
            out = NIOUtils.writableChannel(result);
            newHeader.write(out);
            boolean readOnce = true;
            while (true) {
                readOnce = false;
                for (int i = 0; i < ins.length; ++i) {
                    if (inputs[i] == null) continue;
                    ins[i].clear();
                    if (inputs[i].read(ins[i]) == -1) {
                        NIOUtils.closeQuietly(inputs[i]);
                        inputs[i] = null;
                    } else {
                        readOnce = true;
                    }
                    ins[i].flip();
                }
                if (!readOnce) break;
                outb.clear();
                AudioUtil.interleave(headers[0].getFormat(), ins, outb);
                outb.flip();
                out.write(outb);
            }
        }
        catch (Throwable throwable) {
            IOUtils.closeQuietly(out);
            for (ReadableByteChannel inputStream : inputs) {
                IOUtils.closeQuietly(inputStream);
            }
            throw throwable;
        }
        IOUtils.closeQuietly(out);
        for (ReadableByteChannel inputStream : inputs) {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
