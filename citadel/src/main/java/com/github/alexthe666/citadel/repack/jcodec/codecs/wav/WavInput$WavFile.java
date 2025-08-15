/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.wav;

import com.github.alexthe666.citadel.repack.jcodec.codecs.wav.WavInput;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.io.File;
import java.io.IOException;

public static class WavInput.WavFile
extends WavInput {
    public WavInput.WavFile(File f) throws IOException {
        super(NIOUtils.readableChannel(f));
    }

    @Override
    public void close() throws IOException {
        super.close();
        this._in.close();
    }
}
