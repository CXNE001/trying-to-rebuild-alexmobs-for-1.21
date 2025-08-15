/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import com.github.alexthe666.citadel.repack.jcodec.codecs.wav.WavOutput;
import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.io.File;
import java.io.IOException;

public static class WavOutput.WavOutFile
extends WavOutput {
    public WavOutput.WavOutFile(File f, AudioFormat format) throws IOException {
        super(NIOUtils.writableChannel(f), format);
    }

    @Override
    public void close() throws IOException {
        super.close();
        NIOUtils.closeQuietly(this.out);
    }
}
