/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.io;

import com.github.alexthe666.citadel.repack.jcodec.common.io.AutoPool;
import java.util.concurrent.ThreadFactory;

class AutoPool.2
implements ThreadFactory {
    AutoPool.2() {
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.setName(AutoPool.class.getName());
        return t;
    }
}
