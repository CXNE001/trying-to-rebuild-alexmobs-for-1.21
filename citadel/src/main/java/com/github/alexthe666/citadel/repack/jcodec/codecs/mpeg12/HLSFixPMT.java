/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MTSUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class HLSFixPMT {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void fix(File file) throws IOException {
        RandomAccessFile ra = null;
        try {
            ra = new RandomAccessFile(file, "rw");
            byte[] tsPkt = new byte[188];
            while (ra.read(tsPkt) == 188) {
                Preconditions.checkState(71 == (tsPkt[0] & 0xFF));
                int guidFlags = (tsPkt[1] & 0xFF) << 8 | tsPkt[2] & 0xFF;
                int guid = guidFlags & 0x1FFF;
                int payloadStart = guidFlags >> 14 & 1;
                int b0 = tsPkt[3] & 0xFF;
                int counter = b0 & 0xF;
                int payloadOff = 0;
                if ((b0 & 0x20) != 0) {
                    payloadOff = (tsPkt[4 + payloadOff] & 0xFF) + 1;
                }
                if (payloadStart == 1) {
                    payloadOff += (tsPkt[4 + payloadOff] & 0xFF) + 1;
                }
                if (guid != 0) continue;
                if (payloadStart == 0) {
                    throw new RuntimeException("PAT spans multiple TS packets, not supported!!!!!!");
                }
                ByteBuffer bb = ByteBuffer.wrap(tsPkt, 4 + payloadOff, 184 - payloadOff);
                HLSFixPMT.fixPAT(bb);
                ra.seek(ra.getFilePointer() - 188L);
                ra.write(tsPkt);
            }
        }
        finally {
            if (ra != null) {
                ra.close();
            }
        }
    }

    public static void fixPAT(ByteBuffer data) {
        ByteBuffer table = data.duplicate();
        MTSUtils.parseSection(data);
        ByteBuffer newPmt = data.duplicate();
        while (data.remaining() > 4) {
            short num = data.getShort();
            short pid = data.getShort();
            if (num == 0) continue;
            newPmt.putShort(num);
            newPmt.putShort(pid);
        }
        if (newPmt.position() != data.position()) {
            ByteBuffer section = table.duplicate();
            section.get();
            int sectionLen = newPmt.position() - table.position() + 1;
            section.putShort((short)(sectionLen & 0xFFF | 0xB000));
            CRC32 crc32 = new CRC32();
            table.limit(newPmt.position());
            crc32.update(NIOUtils.toArray(table));
            newPmt.putInt((int)crc32.getValue());
            while (newPmt.hasRemaining()) {
                newPmt.put((byte)-1);
            }
        }
    }

    public static void main1(String[] args) throws IOException {
        File hlsPkg;
        if (args.length < 1) {
            HLSFixPMT.exit("Please specify package location");
        }
        if (!(hlsPkg = new File(args[0])).isDirectory()) {
            HLSFixPMT.exit("Not an HLS package, expected a folder");
        }
        File[] listFiles = hlsPkg.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ts");
            }
        });
        HLSFixPMT fix = new HLSFixPMT();
        for (int i = 0; i < listFiles.length; ++i) {
            File file = listFiles[i];
            System.err.println("Processing: " + file.getName());
            fix.fix(file);
        }
    }

    private static void exit(String message) {
        System.err.println("Syntax: hls_fixpmt <hls package location>");
        System.err.println(message);
        System.exit(-1);
    }
}
