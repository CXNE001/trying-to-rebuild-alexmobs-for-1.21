/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class H264Decoder.1
implements ThreadFactory {
    H264Decoder.1() {
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = Executors.defaultThreadFactory().newThread(r);
        t.setDaemon(true);
        return t;
    }
}
