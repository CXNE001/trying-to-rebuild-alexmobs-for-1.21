/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.es.Descriptor;
import java.nio.ByteBuffer;

public class SL
extends Descriptor {
    public SL() {
        super(SL.tag(), 0);
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        out.put((byte)2);
    }

    public static int tag() {
        return 6;
    }
}
