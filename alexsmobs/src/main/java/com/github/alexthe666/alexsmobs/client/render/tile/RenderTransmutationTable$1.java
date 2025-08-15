/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 */
package com.github.alexthe666.alexsmobs.client.render.tile;

import net.minecraft.core.Direction;

static class RenderTransmutationTable.1 {
    static final /* synthetic */ int[] $SwitchMap$net$minecraft$core$Direction;

    static {
        $SwitchMap$net$minecraft$core$Direction = new int[Direction.values().length];
        try {
            RenderTransmutationTable.1.$SwitchMap$net$minecraft$core$Direction[Direction.NORTH.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            RenderTransmutationTable.1.$SwitchMap$net$minecraft$core$Direction[Direction.EAST.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            RenderTransmutationTable.1.$SwitchMap$net$minecraft$core$Direction[Direction.SOUTH.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            RenderTransmutationTable.1.$SwitchMap$net$minecraft$core$Direction[Direction.WEST.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
