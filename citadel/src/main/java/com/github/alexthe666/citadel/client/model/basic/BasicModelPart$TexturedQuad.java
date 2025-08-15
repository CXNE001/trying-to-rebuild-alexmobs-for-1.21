/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Vector3f
 */
package com.github.alexthe666.citadel.client.model.basic;

import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(value=Dist.CLIENT)
static class BasicModelPart.TexturedQuad {
    public final BasicModelPart.PositionTextureVertex[] vertexPositions;
    public final Vector3f normal;

    public BasicModelPart.TexturedQuad(BasicModelPart.PositionTextureVertex[] positionsIn, float u1, float v1, float u2, float v2, float texWidth, float texHeight, boolean mirrorIn, Direction directionIn) {
        this.vertexPositions = positionsIn;
        float f = 0.0f / texWidth;
        float f1 = 0.0f / texHeight;
        positionsIn[0] = positionsIn[0].setTextureUV(u2 / texWidth - f, v1 / texHeight + f1);
        positionsIn[1] = positionsIn[1].setTextureUV(u1 / texWidth + f, v1 / texHeight + f1);
        positionsIn[2] = positionsIn[2].setTextureUV(u1 / texWidth + f, v2 / texHeight - f1);
        positionsIn[3] = positionsIn[3].setTextureUV(u2 / texWidth - f, v2 / texHeight - f1);
        if (mirrorIn) {
            int i = positionsIn.length;
            for (int j = 0; j < i / 2; ++j) {
                BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex = positionsIn[j];
                positionsIn[j] = positionsIn[i - 1 - j];
                positionsIn[i - 1 - j] = BasicModelPart$positiontexturevertex;
            }
        }
        this.normal = directionIn.m_253071_();
        if (mirrorIn) {
            this.normal.mul(-1.0f, 1.0f, 1.0f);
        }
    }
}
