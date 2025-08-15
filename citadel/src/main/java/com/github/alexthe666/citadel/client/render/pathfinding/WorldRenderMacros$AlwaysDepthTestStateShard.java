/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.renderer.RenderStateShard$DepthTestStateShard
 */
package com.github.alexthe666.citadel.client.render.pathfinding;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderStateShard;

public static class WorldRenderMacros.AlwaysDepthTestStateShard
extends RenderStateShard.DepthTestStateShard {
    public static final RenderStateShard.DepthTestStateShard ALWAYS_DEPTH_TEST = new WorldRenderMacros.AlwaysDepthTestStateShard();

    private WorldRenderMacros.AlwaysDepthTestStateShard() {
        super("true_always", -1);
        this.f_110131_ = () -> {
            RenderSystem.enableDepthTest();
            RenderSystem.depthFunc((int)519);
        };
    }
}
