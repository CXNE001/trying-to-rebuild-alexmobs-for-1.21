/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.pathjobs;

public interface ICustomSizeNavigator {
    public boolean isSmallerThanBlock();

    public float getXZNavSize();

    public int getYNavSize();
}
