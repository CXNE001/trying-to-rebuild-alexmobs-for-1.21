/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.audio;

import java.io.IOException;
import java.nio.FloatBuffer;

public interface AudioSink {
    public void writeFloat(FloatBuffer var1) throws IOException;
}
