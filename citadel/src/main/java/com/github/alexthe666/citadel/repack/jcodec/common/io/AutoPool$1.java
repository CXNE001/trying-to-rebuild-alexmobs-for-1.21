/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.io;

import com.github.alexthe666.citadel.repack.jcodec.common.io.AutoResource;
import java.util.List;

class AutoPool.1
implements Runnable {
    final /* synthetic */ List val$res;

    AutoPool.1(List list) {
        this.val$res = list;
    }

    @Override
    public void run() {
        long curTime = System.currentTimeMillis();
        for (AutoResource autoResource : this.val$res) {
            autoResource.setCurTime(curTime);
        }
    }
}
