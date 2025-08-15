/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.thread.BlockableEventLoop
 *  net.minecraftforge.common.util.LogicalSidedProvider
 *  net.minecraftforge.fml.LogicalSide
 *  org.jetbrains.annotations.NotNull
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.PathfindingConstants;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import org.jetbrains.annotations.NotNull;

public final class Pathfinding {
    private static final BlockingQueue<Runnable> jobQueue = new LinkedBlockingDeque<Runnable>();
    private static ThreadPoolExecutor executor;

    private Pathfinding() {
    }

    public static boolean isDebug() {
        return PathfindingConstants.isDebugMode;
    }

    public static ThreadPoolExecutor getExecutor() {
        if (executor == null) {
            executor = new ThreadPoolExecutor(1, PathfindingConstants.pathfindingThreads, 10L, TimeUnit.SECONDS, jobQueue, new CitadelThreadFactory());
        }
        return executor;
    }

    public static class CitadelThreadFactory
    implements ThreadFactory {
        public static int id;

        @Override
        public Thread newThread(@NotNull Runnable runnable) throws RuntimeException {
            ClassLoader classLoader;
            BlockableEventLoop workqueue = (BlockableEventLoop)LogicalSidedProvider.WORKQUEUE.get(LogicalSide.SERVER);
            if (workqueue.m_18695_()) {
                classLoader = Thread.currentThread().getContextClassLoader();
            } else if (workqueue instanceof MinecraftServer) {
                MinecraftServer server = (MinecraftServer)workqueue;
                classLoader = server.m_6304_().getContextClassLoader();
            } else {
                classLoader = (ClassLoader)((CompletableFuture)CompletableFuture.supplyAsync(() -> Thread.currentThread().getContextClassLoader(), (Executor)workqueue).orTimeout(10L, TimeUnit.SECONDS).exceptionally(ex -> {
                    throw new RuntimeException(String.format("Couldn't join threads within timeout range. Tried joining '%s' on '%s'", Thread.currentThread().getName(), workqueue.m_7326_()));
                })).join();
            }
            Thread thread = new Thread(runnable, "Citadel Pathfinding Worker #" + id++);
            thread.setDaemon(true);
            thread.setPriority(10);
            if (thread.getContextClassLoader() != classLoader) {
                Citadel.LOGGER.info("Corrected CCL of new Citadel Pathfinding Thread, was: " + thread.getContextClassLoader().toString());
                thread.setContextClassLoader(classLoader);
            }
            thread.setUncaughtExceptionHandler((thread1, throwable) -> Citadel.LOGGER.error("Citadel Pathfinding Thread errored! ", throwable));
            return thread;
        }
    }
}
