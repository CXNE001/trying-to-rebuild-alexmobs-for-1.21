/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.level.pathfinder.Path
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.PathFindingStatus;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.annotation.Nullable;
import net.minecraft.world.level.pathfinder.Path;

public class PathResult<T extends Callable<Path>> {
    protected PathFindingStatus status = PathFindingStatus.IN_PROGRESS_COMPUTING;
    private static boolean threadException = false;
    private volatile boolean pathReachesDestination = false;
    private Path path = null;
    private Future<Path> pathCalculation = null;
    private T job = null;
    private boolean pathingDoneAndProcessed = false;

    public PathFindingStatus getStatus() {
        return this.status;
    }

    public void setStatus(PathFindingStatus s) {
        this.status = s;
    }

    public boolean isInProgress() {
        return this.isComputing() || this.status == PathFindingStatus.IN_PROGRESS_FOLLOWING;
    }

    public boolean isComputing() {
        return this.status == PathFindingStatus.IN_PROGRESS_COMPUTING;
    }

    public boolean failedToReachDestination() {
        return this.isFinished() && !this.pathReachesDestination;
    }

    public boolean isPathReachingDestination() {
        return this.isFinished() && this.path != null && this.pathReachesDestination;
    }

    public void setPathReachesDestination(boolean value) {
        this.pathReachesDestination = value;
    }

    public boolean isCancelled() {
        return this.status == PathFindingStatus.CANCELLED;
    }

    public int getPathLength() {
        return this.path.m_77398_();
    }

    public boolean hasPath() {
        return this.path != null;
    }

    @Nullable
    public Path getPath() {
        return this.path;
    }

    public T getJob() {
        return this.job;
    }

    public void setJob(T job) {
        this.job = job;
    }

    public void startJob(ExecutorService executorService) {
        if (this.job != null) {
            try {
                if (!threadException) {
                    this.pathCalculation = executorService.submit(this.job);
                }
            }
            catch (NullPointerException e) {
                Citadel.LOGGER.error("Mod tried to move an entity from non server thread", (Throwable)e);
            }
            catch (RuntimeException e) {
                threadException = true;
                Citadel.LOGGER.catching((Throwable)e);
            }
            catch (Exception e) {
                Citadel.LOGGER.catching((Throwable)e);
            }
        }
    }

    public void processCalculationResults() {
        if (this.pathingDoneAndProcessed) {
            return;
        }
        try {
            this.path = this.pathCalculation.get();
            this.pathCalculation = null;
            this.setStatus(PathFindingStatus.CALCULATION_COMPLETE);
        }
        catch (InterruptedException | ExecutionException e) {
            Citadel.LOGGER.catching((Throwable)e);
        }
    }

    public boolean isCalculatingPath() {
        return this.pathCalculation != null && !this.pathCalculation.isDone();
    }

    public boolean isFinished() {
        if (!this.pathingDoneAndProcessed && this.pathCalculation != null && this.pathCalculation.isDone()) {
            this.processCalculationResults();
            this.pathingDoneAndProcessed = true;
        }
        return this.pathingDoneAndProcessed;
    }

    public void cancel() {
        if (this.pathCalculation != null) {
            this.pathCalculation.cancel(false);
            this.pathCalculation = null;
        }
        this.pathingDoneAndProcessed = true;
    }
}
