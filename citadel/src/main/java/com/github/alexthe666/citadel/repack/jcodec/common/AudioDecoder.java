/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioCodecMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.model.AudioBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface AudioDecoder {
    public AudioBuffer decodeFrame(ByteBuffer var1, ByteBuffer var2) throws IOException;

    public AudioCodecMeta getCodecMeta(ByteBuffer var1) throws IOException;
}
