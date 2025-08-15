/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  net.minecraft.core.Direction
 *  net.minecraft.util.RandomSource
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Matrix3f
 *  org.joml.Matrix3fc
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 *  org.joml.Vector4f
 */
package com.github.alexthe666.citadel.client.model.basic;

import com.github.alexthe666.citadel.client.model.basic.BasicEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

@OnlyIn(value=Dist.CLIENT)
public class BasicModelPart {
    public float textureWidth = 64.0f;
    public float textureHeight = 32.0f;
    public int textureOffsetX;
    public int textureOffsetY;
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    public boolean mirror;
    public boolean showModel = true;
    private final ObjectList<ModelBox> cubeList = new ObjectArrayList();
    private final ObjectList<BasicModelPart> childModels = new ObjectArrayList();

    public BasicModelPart(BasicEntityModel model) {
        this.setTextureSize(model.textureWidth, model.textureHeight);
    }

    public BasicModelPart(BasicEntityModel model, int texOffX, int texOffY) {
        this(model.textureWidth, model.textureHeight, texOffX, texOffY);
    }

    public BasicModelPart(int textureWidthIn, int textureHeightIn, int textureOffsetXIn, int textureOffsetYIn) {
        this.setTextureSize(textureWidthIn, textureHeightIn);
        this.setTextureOffset(textureOffsetXIn, textureOffsetYIn);
    }

    private BasicModelPart() {
    }

    public BasicModelPart getModelAngleCopy() {
        BasicModelPart BasicModelPart2 = new BasicModelPart();
        BasicModelPart2.copyModelAngles(this);
        return BasicModelPart2;
    }

    public void copyModelAngles(BasicModelPart BasicModelPartIn) {
        this.rotateAngleX = BasicModelPartIn.rotateAngleX;
        this.rotateAngleY = BasicModelPartIn.rotateAngleY;
        this.rotateAngleZ = BasicModelPartIn.rotateAngleZ;
        this.rotationPointX = BasicModelPartIn.rotationPointX;
        this.rotationPointY = BasicModelPartIn.rotationPointY;
        this.rotationPointZ = BasicModelPartIn.rotationPointZ;
    }

    public void addChild(BasicModelPart renderer) {
        this.childModels.add((Object)renderer);
    }

    public BasicModelPart setTextureOffset(int x, int y) {
        this.textureOffsetX = x;
        this.textureOffsetY = y;
        return this;
    }

    public BasicModelPart addBox(String partName, float x, float y, float z, int width, int height, int depth, float delta, int texX, int texY) {
        this.setTextureOffset(texX, texY);
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, delta, delta, delta, this.mirror, false);
        return this;
    }

    public BasicModelPart addBox(float x, float y, float z, float width, float height, float depth) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0f, 0.0f, 0.0f, this.mirror, false);
        return this;
    }

    public BasicModelPart addBox(float x, float y, float z, float width, float height, float depth, boolean mirrorIn) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0f, 0.0f, 0.0f, mirrorIn, false);
        return this;
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float delta) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, delta, delta, delta, this.mirror, false);
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, this.mirror, false);
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float delta, boolean mirrorIn) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, delta, delta, delta, mirrorIn, false);
    }

    private void addBox(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirorIn, boolean p_228305_13_) {
        this.cubeList.add((Object)new ModelBox(texOffX, texOffY, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, mirorIn, this.textureWidth, this.textureHeight));
    }

    public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn) {
        this.rotationPointX = rotationPointXIn;
        this.rotationPointY = rotationPointYIn;
        this.rotationPointZ = rotationPointZIn;
    }

    public void render(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn) {
        this.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void render(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (!(!this.showModel || this.cubeList.isEmpty() && this.childModels.isEmpty())) {
            matrixStackIn.m_85836_();
            this.translateRotate(matrixStackIn);
            this.doRender(matrixStackIn.m_85850_(), bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            for (BasicModelPart BasicModelPart2 : this.childModels) {
                BasicModelPart2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            }
            matrixStackIn.m_85849_();
        }
    }

    public void translateRotate(PoseStack matrixStackIn) {
        matrixStackIn.m_85837_((double)(this.rotationPointX / 16.0f), (double)(this.rotationPointY / 16.0f), (double)(this.rotationPointZ / 16.0f));
        if (this.rotateAngleZ != 0.0f) {
            matrixStackIn.m_252781_(Axis.f_252403_.m_252961_(this.rotateAngleZ));
        }
        if (this.rotateAngleY != 0.0f) {
            matrixStackIn.m_252781_(Axis.f_252436_.m_252961_(this.rotateAngleY));
        }
        if (this.rotateAngleX != 0.0f) {
            matrixStackIn.m_252781_(Axis.f_252529_.m_252961_(this.rotateAngleX));
        }
    }

    private void doRender(PoseStack.Pose matrixEntryIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        Matrix4f matrix4f = matrixEntryIn.m_252922_();
        Matrix3f matrix3f = matrixEntryIn.m_252943_();
        for (ModelBox BasicModelPart$modelbox : this.cubeList) {
            for (TexturedQuad BasicModelPart$texturedquad : BasicModelPart$modelbox.quads) {
                Vector3f vector3f = new Vector3f((Vector3fc)BasicModelPart$texturedquad.normal);
                vector3f.mul((Matrix3fc)matrix3f);
                float f = vector3f.x();
                float f1 = vector3f.y();
                float f2 = vector3f.z();
                for (int i = 0; i < 4; ++i) {
                    PositionTextureVertex BasicModelPart$positiontexturevertex = BasicModelPart$texturedquad.vertexPositions[i];
                    float f3 = BasicModelPart$positiontexturevertex.position.x() / 16.0f;
                    float f4 = BasicModelPart$positiontexturevertex.position.y() / 16.0f;
                    float f5 = BasicModelPart$positiontexturevertex.position.z() / 16.0f;
                    Vector4f vector4f = new Vector4f(f3, f4, f5, 1.0f);
                    vector4f.mul((Matrix4fc)matrix4f);
                    bufferIn.m_5954_(vector4f.x(), vector4f.y(), vector4f.z(), red, green, blue, alpha, BasicModelPart$positiontexturevertex.textureU, BasicModelPart$positiontexturevertex.textureV, packedOverlayIn, packedLightIn, f, f1, f2);
                }
            }
        }
    }

    public BasicModelPart setTextureSize(int textureWidthIn, int textureHeightIn) {
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;
        return this;
    }

    public ModelBox getRandomCube(RandomSource randomIn) {
        return this.cubeList.size() > 0 ? (ModelBox)this.cubeList.get(randomIn.m_188503_(this.cubeList.size())) : null;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class ModelBox {
        private final TexturedQuad[] quads;
        public final float posX1;
        public final float posY1;
        public final float posZ1;
        public final float posX2;
        public final float posY2;
        public final float posZ2;

        public ModelBox(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirorIn, float texWidth, float texHeight) {
            this.posX1 = x;
            this.posY1 = y;
            this.posZ1 = z;
            this.posX2 = x + width;
            this.posY2 = y + height;
            this.posZ2 = z + depth;
            this.quads = new TexturedQuad[6];
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
            PositionTextureVertex BasicModelPart$positiontexturevertex7 = new PositionTextureVertex(x, y, z, 0.0f, 0.0f);
            PositionTextureVertex BasicModelPart$positiontexturevertex = new PositionTextureVertex(f, y, z, 0.0f, 8.0f);
            PositionTextureVertex BasicModelPart$positiontexturevertex1 = new PositionTextureVertex(f, f1, z, 8.0f, 8.0f);
            PositionTextureVertex BasicModelPart$positiontexturevertex2 = new PositionTextureVertex(x, f1, z, 8.0f, 0.0f);
            PositionTextureVertex BasicModelPart$positiontexturevertex3 = new PositionTextureVertex(x, y, f2, 0.0f, 0.0f);
            PositionTextureVertex BasicModelPart$positiontexturevertex4 = new PositionTextureVertex(f, y, f2, 0.0f, 8.0f);
            PositionTextureVertex BasicModelPart$positiontexturevertex5 = new PositionTextureVertex(f, f1, f2, 8.0f, 8.0f);
            PositionTextureVertex BasicModelPart$positiontexturevertex6 = new PositionTextureVertex(x, f1, f2, 8.0f, 0.0f);
            float f4 = texOffX;
            float f5 = (float)texOffX + depth;
            float f6 = (float)texOffX + depth + width;
            float f7 = (float)texOffX + depth + width + width;
            float f8 = (float)texOffX + depth + width + depth;
            float f9 = (float)texOffX + depth + width + depth + width;
            float f10 = texOffY;
            float f11 = (float)texOffY + depth;
            float f12 = (float)texOffY + depth + height;
            this.quads[2] = new TexturedQuad(new PositionTextureVertex[]{BasicModelPart$positiontexturevertex4, BasicModelPart$positiontexturevertex3, BasicModelPart$positiontexturevertex7, BasicModelPart$positiontexturevertex}, f5, f10, f6, f11, texWidth, texHeight, mirorIn, Direction.DOWN);
            this.quads[3] = new TexturedQuad(new PositionTextureVertex[]{BasicModelPart$positiontexturevertex1, BasicModelPart$positiontexturevertex2, BasicModelPart$positiontexturevertex6, BasicModelPart$positiontexturevertex5}, f6, f11, f7, f10, texWidth, texHeight, mirorIn, Direction.UP);
            this.quads[1] = new TexturedQuad(new PositionTextureVertex[]{BasicModelPart$positiontexturevertex7, BasicModelPart$positiontexturevertex3, BasicModelPart$positiontexturevertex6, BasicModelPart$positiontexturevertex2}, f4, f11, f5, f12, texWidth, texHeight, mirorIn, Direction.WEST);
            this.quads[4] = new TexturedQuad(new PositionTextureVertex[]{BasicModelPart$positiontexturevertex, BasicModelPart$positiontexturevertex7, BasicModelPart$positiontexturevertex2, BasicModelPart$positiontexturevertex1}, f5, f11, f6, f12, texWidth, texHeight, mirorIn, Direction.NORTH);
            this.quads[0] = new TexturedQuad(new PositionTextureVertex[]{BasicModelPart$positiontexturevertex4, BasicModelPart$positiontexturevertex, BasicModelPart$positiontexturevertex1, BasicModelPart$positiontexturevertex5}, f6, f11, f8, f12, texWidth, texHeight, mirorIn, Direction.EAST);
            this.quads[5] = new TexturedQuad(new PositionTextureVertex[]{BasicModelPart$positiontexturevertex3, BasicModelPart$positiontexturevertex4, BasicModelPart$positiontexturevertex5, BasicModelPart$positiontexturevertex6}, f8, f11, f9, f12, texWidth, texHeight, mirorIn, Direction.SOUTH);
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    static class TexturedQuad {
        public final PositionTextureVertex[] vertexPositions;
        public final Vector3f normal;

        public TexturedQuad(PositionTextureVertex[] positionsIn, float u1, float v1, float u2, float v2, float texWidth, float texHeight, boolean mirrorIn, Direction directionIn) {
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
                    PositionTextureVertex BasicModelPart$positiontexturevertex = positionsIn[j];
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

    @OnlyIn(value=Dist.CLIENT)
    static class PositionTextureVertex {
        public final Vector3f position;
        public final float textureU;
        public final float textureV;

        public PositionTextureVertex(float x, float y, float z, float texU, float texV) {
            this(new Vector3f(x, y, z), texU, texV);
        }

        public PositionTextureVertex setTextureUV(float texU, float texV) {
            return new PositionTextureVertex(this.position, texU, texV);
        }

        public PositionTextureVertex(Vector3f posIn, float texU, float texV) {
            this.position = posIn;
            this.textureU = texU;
            this.textureV = texV;
        }
    }
}
