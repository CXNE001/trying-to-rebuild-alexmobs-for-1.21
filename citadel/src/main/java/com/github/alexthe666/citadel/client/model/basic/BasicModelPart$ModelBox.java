/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.model.basic;

import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public static class BasicModelPart.ModelBox {
    private final BasicModelPart.TexturedQuad[] quads;
    public final float posX1;
    public final float posY1;
    public final float posZ1;
    public final float posX2;
    public final float posY2;
    public final float posZ2;

    public BasicModelPart.ModelBox(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirorIn, float texWidth, float texHeight) {
        this.posX1 = x;
        this.posY1 = y;
        this.posZ1 = z;
        this.posX2 = x + width;
        this.posY2 = y + height;
        this.posZ2 = z + depth;
        this.quads = new BasicModelPart.TexturedQuad[6];
        float f = x + width;
        float f1 = y + height;
        float f2 = z + depth;
        x -= deltaX;
        y -= deltaY;
        z -= deltaZ;
        f += deltaX;
        f1 += deltaY;
        f2 += deltaZ;
        if (mirorIn) {
            float f3 = f;
            f = x;
            x = f3;
        }
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex7 = new BasicModelPart.PositionTextureVertex(x, y, z, 0.0f, 0.0f);
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex = new BasicModelPart.PositionTextureVertex(f, y, z, 0.0f, 8.0f);
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex1 = new BasicModelPart.PositionTextureVertex(f, f1, z, 8.0f, 8.0f);
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex2 = new BasicModelPart.PositionTextureVertex(x, f1, z, 8.0f, 0.0f);
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex3 = new BasicModelPart.PositionTextureVertex(x, y, f2, 0.0f, 0.0f);
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex4 = new BasicModelPart.PositionTextureVertex(f, y, f2, 0.0f, 8.0f);
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex5 = new BasicModelPart.PositionTextureVertex(f, f1, f2, 8.0f, 8.0f);
        BasicModelPart.PositionTextureVertex BasicModelPart$positiontexturevertex6 = new BasicModelPart.PositionTextureVertex(x, f1, f2, 8.0f, 0.0f);
        float f4 = texOffX;
        float f5 = (float)texOffX + depth;
        float f6 = (float)texOffX + depth + width;
        float f7 = (float)texOffX + depth + width + width;
        float f8 = (float)texOffX + depth + width + depth;
        float f9 = (float)texOffX + depth + width + depth + width;
        float f10 = texOffY;
        float f11 = (float)texOffY + depth;
        float f12 = (float)texOffY + depth + height;
        this.quads[2] = new BasicModelPart.TexturedQuad(new BasicModelPart.PositionTextureVertex[]{BasicModelPart$positiontexturevertex4, BasicModelPart$positiontexturevertex3, BasicModelPart$positiontexturevertex7, BasicModelPart$positiontexturevertex}, f5, f10, f6, f11, texWidth, texHeight, mirorIn, Direction.DOWN);
        this.quads[3] = new BasicModelPart.TexturedQuad(new BasicModelPart.PositionTextureVertex[]{BasicModelPart$positiontexturevertex1, BasicModelPart$positiontexturevertex2, BasicModelPart$positiontexturevertex6, BasicModelPart$positiontexturevertex5}, f6, f11, f7, f10, texWidth, texHeight, mirorIn, Direction.UP);
        this.quads[1] = new BasicModelPart.TexturedQuad(new BasicModelPart.PositionTextureVertex[]{BasicModelPart$positiontexturevertex7, BasicModelPart$positiontexturevertex3, BasicModelPart$positiontexturevertex6, BasicModelPart$positiontexturevertex2}, f4, f11, f5, f12, texWidth, texHeight, mirorIn, Direction.WEST);
        this.quads[4] = new BasicModelPart.TexturedQuad(new BasicModelPart.PositionTextureVertex[]{BasicModelPart$positiontexturevertex, BasicModelPart$positiontexturevertex7, BasicModelPart$positiontexturevertex2, BasicModelPart$positiontexturevertex1}, f5, f11, f6, f12, texWidth, texHeight, mirorIn, Direction.NORTH);
        this.quads[0] = new BasicModelPart.TexturedQuad(new BasicModelPart.PositionTextureVertex[]{BasicModelPart$positiontexturevertex4, BasicModelPart$positiontexturevertex, BasicModelPart$positiontexturevertex1, BasicModelPart$positiontexturevertex5}, f6, f11, f8, f12, texWidth, texHeight, mirorIn, Direction.EAST);
        this.quads[5] = new BasicModelPart.TexturedQuad(new BasicModelPart.PositionTextureVertex[]{BasicModelPart$positiontexturevertex3, BasicModelPart$positiontexturevertex4, BasicModelPart$positiontexturevertex5, BasicModelPart$positiontexturevertex6}, f8, f11, f9, f12, texWidth, texHeight, mirorIn, Direction.SOUTH);
    }
}
