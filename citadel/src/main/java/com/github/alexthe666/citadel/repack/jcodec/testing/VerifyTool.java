/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.testing;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.BufferH264ES;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Decoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.Frame;
import com.github.alexthe666.citadel.repack.jcodec.common.ArrayUtil;
import com.github.alexthe666.citadel.repack.jcodec.common.JCodecUtil2;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.ByteBuffer;

public class VerifyTool {
    public static void main1(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Syntax: <error folder location>");
            return;
        }
        new VerifyTool().doIt(args[0]);
    }

    private void doIt(String location) {
        File[] h264;
        for (File coded : h264 = new File(location).listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".264");
            }
        })) {
            File ref = new File(coded.getParentFile(), coded.getName().replaceAll(".264$", "_dec.yuv"));
            if (!coded.exists() || !ref.exists()) continue;
            try {
                if (this.test(coded, ref)) {
                    System.out.println(coded.getAbsolutePath() + " -- FIXED");
                    Platform.deleteFile(coded);
                    Platform.deleteFile(ref);
                    continue;
                }
                System.out.println(coded.getAbsolutePath() + " -- NOT FIXED!!!!");
            }
            catch (Throwable t) {
                System.out.println(coded.getAbsolutePath() + " -- ERROR: " + t.getMessage());
            }
        }
    }

    private boolean test(File coded, File ref) throws IOException {
        Packet nextFrame;
        BufferH264ES es = new BufferH264ES(NIOUtils.fetchFromFile(coded));
        Picture buf = Picture.create(1920, 1088, ColorSpace.YUV420);
        H264Decoder dec = new H264Decoder();
        ByteBuffer _yuv = NIOUtils.fetchFromFile(ref);
        while ((nextFrame = es.nextFrame()) != null) {
            Frame out = dec.decodeFrame(nextFrame.getData(), buf.getData()).cropped();
            Picture pic = out.createCompatible();
            pic.copyFrom(out);
            int lumaSize = pic.getWidth() * pic.getHeight();
            int crSize = lumaSize >> 2;
            int cbSize = lumaSize >> 2;
            ByteBuffer yuv = NIOUtils.read(_yuv, lumaSize + crSize + cbSize);
            if (!Platform.arrayEqualsByte(ArrayUtil.toByteArrayShifted(JCodecUtil2.getAsIntArray(yuv, lumaSize)), pic.getPlaneData(0))) {
                return false;
            }
            if (!Platform.arrayEqualsByte(ArrayUtil.toByteArrayShifted(JCodecUtil2.getAsIntArray(yuv, crSize)), pic.getPlaneData(1))) {
                return false;
            }
            if (Platform.arrayEqualsByte(ArrayUtil.toByteArrayShifted(JCodecUtil2.getAsIntArray(yuv, cbSize)), pic.getPlaneData(2))) continue;
            return false;
        }
        return true;
    }
}
