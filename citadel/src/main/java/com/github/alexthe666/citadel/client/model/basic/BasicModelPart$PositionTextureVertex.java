/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Vector3f
 */
package com.github.alexthe666.citadel.client.model.basic;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(value=Dist.CLIENT)
static class BasicModelPart.PositionTextureVertex {
    public final Vector3f position;
    public final float textureU;
    public final float textureV;

    public BasicModelPart.PositionTextureVertex(float x, float y, float z, float texU, float texV) {
        this(new Vector3f(x, y, z), texU, texV);
    }

    public BasicModelPart.PositionTextureVertex setTextureUV(float texU, float texV) {
        return new BasicModelPart.PositionTextureVertex(this.position, texU, texV);
    }

    public BasicModelPart.PositionTextureVertex(Vector3f posIn, float texU, float texV) {
        this.position = posIn;
        this.textureU = texU;
        this.textureV = texV;
    }
}
