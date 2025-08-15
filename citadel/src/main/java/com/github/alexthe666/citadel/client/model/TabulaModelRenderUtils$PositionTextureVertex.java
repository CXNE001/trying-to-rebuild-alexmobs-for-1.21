/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Vector3f
 */
package com.github.alexthe666.citadel.client.model;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(value=Dist.CLIENT)
static class TabulaModelRenderUtils.PositionTextureVertex {
    public final Vector3f position;
    public final float textureU;
    public final float textureV;

    public TabulaModelRenderUtils.PositionTextureVertex(float p_i1158_1_, float p_i1158_2_, float p_i1158_3_, float p_i1158_4_, float p_i1158_5_) {
        this(new Vector3f(p_i1158_1_, p_i1158_2_, p_i1158_3_), p_i1158_4_, p_i1158_5_);
    }

    public TabulaModelRenderUtils.PositionTextureVertex setTextureUV(float p_78240_1_, float p_78240_2_) {
        return new TabulaModelRenderUtils.PositionTextureVertex(this.position, p_78240_1_, p_78240_2_);
    }

    public TabulaModelRenderUtils.PositionTextureVertex(Vector3f p_i225952_1_, float p_i225952_2_, float p_i225952_3_) {
        this.position = p_i225952_1_;
        this.textureU = p_i225952_2_;
        this.textureV = p_i225952_3_;
    }
}
