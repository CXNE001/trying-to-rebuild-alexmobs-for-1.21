/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import java.io.IOException;
import java.nio.FloatBuffer;

public interface AudioSource {
    public AudioFormat getFormat();

    public int readFloat(FloatBuffer var1) throws IOException;
}
