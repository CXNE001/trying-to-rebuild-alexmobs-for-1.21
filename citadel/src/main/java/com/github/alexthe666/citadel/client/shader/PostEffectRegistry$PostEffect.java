/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderTarget
 *  net.minecraft.client.renderer.PostChain
 */
package com.github.alexthe666.citadel.client.shader;

import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.renderer.PostChain;

private static class PostEffectRegistry.PostEffect {
    private final PostChain postChain;
    private final RenderTarget renderTarget;
    private boolean enabled;

    public PostEffectRegistry.PostEffect(PostChain postChain, RenderTarget renderTarget, boolean enabled) {
        this.postChain = postChain;
        this.renderTarget = renderTarget;
        this.enabled = enabled;
    }

    public PostChain getPostChain() {
        return this.postChain;
    }

    public RenderTarget getRenderTarget() {
        return this.renderTarget;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void close() {
        if (this.postChain != null) {
            this.postChain.close();
        }
    }

    public void resize(int x, int y) {
        if (this.postChain != null) {
            this.postChain.m_110025_(x, y);
        }
    }
}
