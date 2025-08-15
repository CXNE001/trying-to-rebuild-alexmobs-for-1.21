/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Rotation
 */
package com.github.alexthe666.citadel.client.game;

import net.minecraft.world.level.block.Rotation;

static class Tetris.1 {
    static final /* synthetic */ int[] $SwitchMap$net$minecraft$world$level$block$Rotation;

    static {
        $SwitchMap$net$minecraft$world$level$block$Rotation = new int[Rotation.values().length];
        try {
            Tetris.1.$SwitchMap$net$minecraft$world$level$block$Rotation[Rotation.COUNTERCLOCKWISE_90.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            Tetris.1.$SwitchMap$net$minecraft$world$level$block$Rotation[Rotation.CLOCKWISE_90.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            Tetris.1.$SwitchMap$net$minecraft$world$level$block$Rotation[Rotation.CLOCKWISE_180.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
