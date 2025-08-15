/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms;

import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.AdvancedPathNavigate;
import net.minecraft.core.Direction;

static class AdvancedPathNavigate.1 {
    static final /* synthetic */ int[] $SwitchMap$com$github$alexthe666$citadel$server$entity$pathfinding$raycoms$AdvancedPathNavigate$MovementType;
    static final /* synthetic */ int[] $SwitchMap$net$minecraft$core$Direction;

    static {
        $SwitchMap$net$minecraft$core$Direction = new int[Direction.values().length];
        try {
            AdvancedPathNavigate.1.$SwitchMap$net$minecraft$core$Direction[Direction.NORTH.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AdvancedPathNavigate.1.$SwitchMap$net$minecraft$core$Direction[Direction.SOUTH.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AdvancedPathNavigate.1.$SwitchMap$net$minecraft$core$Direction[Direction.WEST.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AdvancedPathNavigate.1.$SwitchMap$net$minecraft$core$Direction[Direction.EAST.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AdvancedPathNavigate.1.$SwitchMap$net$minecraft$core$Direction[Direction.UP.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        $SwitchMap$com$github$alexthe666$citadel$server$entity$pathfinding$raycoms$AdvancedPathNavigate$MovementType = new int[AdvancedPathNavigate.MovementType.values().length];
        try {
            AdvancedPathNavigate.1.$SwitchMap$com$github$alexthe666$citadel$server$entity$pathfinding$raycoms$AdvancedPathNavigate$MovementType[AdvancedPathNavigate.MovementType.FLYING.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AdvancedPathNavigate.1.$SwitchMap$com$github$alexthe666$citadel$server$entity$pathfinding$raycoms$AdvancedPathNavigate$MovementType[AdvancedPathNavigate.MovementType.WALKING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            AdvancedPathNavigate.1.$SwitchMap$com$github$alexthe666$citadel$server$entity$pathfinding$raycoms$AdvancedPathNavigate$MovementType[AdvancedPathNavigate.MovementType.CLIMBING.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
