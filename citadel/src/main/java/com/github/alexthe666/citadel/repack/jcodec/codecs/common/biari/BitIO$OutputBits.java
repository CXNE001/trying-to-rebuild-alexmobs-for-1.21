/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari;

import java.io.IOException;

public static interface BitIO.OutputBits {
    public void putBit(int var1) throws IOException;

    public void flush() throws IOException;
}
