/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.alexsmobs.entity;

public interface ISemiAquatic {
    public boolean shouldEnterWater();

    public boolean shouldLeaveWater();

    public boolean shouldStopMoving();

    public int getWaterSearchRange();
}
