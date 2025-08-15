/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.io;

import com.github.alexthe666.citadel.repack.jcodec.common.io.AutoResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class AutoPool {
    private final List<AutoResource> resources = Collections.synchronizedList(new ArrayList());
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, this.daemonThreadFactory());
    private static AutoPool instance = new AutoPool();

    private AutoPool() {
        final List<AutoResource> res = this.resources;
        this.scheduler.scheduleAtFixedRate(new Runnable(){

            @Override
            public void run() {
                long curTime = System.currentTimeMillis();
                for (AutoResource autoResource : res) {
                    autoResource.setCurTime(curTime);
                }
            }
        }, 0L, 100L, TimeUnit.MILLISECONDS);
    }

    private ThreadFactory daemonThreadFactory() {
        return new ThreadFactory(){

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName(AutoPool.class.getName());
                return t;
            }
        };
    }

    public static AutoPool getInstance() {
        return instance;
    }

    public void add(AutoResource res) {
        this.resources.add(res);
    }
}
