/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.model;

import com.github.alexthe666.citadel.client.model.TabulaModelRenderUtils;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public static class TabulaModelRenderUtils.ModelBox {
    public final TabulaModelRenderUtils.TexturedQuad[] quads;
    public final float posX1;
    public final float posY1;
    public final float posZ1;
    public final float posX2;
    public final float posY2;
    public final float posZ2;

    public TabulaModelRenderUtils.ModelBox(int p_i225950_1_, int p_i225950_2_, float p_i225950_3_, float p_i225950_4_, float p_i225950_5_, float p_i225950_6_, float p_i225950_7_, float p_i225950_8_, float p_i225950_9_, float p_i225950_10_, float p_i225950_11_, boolean p_i225950_12_, float p_i225950_13_, float p_i225950_14_) {
        this.posX1 = p_i225950_3_;
        this.posY1 = p_i225950_4_;
        this.posZ1 = p_i225950_5_;
        this.posX2 = p_i225950_3_ + p_i225950_6_;
        this.posY2 = p_i225950_4_ + p_i225950_7_;
        this.posZ2 = p_i225950_5_ + p_i225950_8_;
        this.quads = new TabulaModelRenderUtils.TexturedQuad[6];
        float lvt_15_1_ = p_i225950_3_ + p_i225950_6_;
        float lvt_16_1_ = p_i225950_4_ + p_i225950_7_;
        float lvt_17_1_ = p_i225950_5_ + p_i225950_8_;
        p_i225950_3_ -= p_i225950_9_;
        p_i225950_4_ -= p_i225950_10_;
        p_i225950_5_ -= p_i225950_11_;
        lvt_15_1_ += p_i225950_9_;
        lvt_16_1_ += p_i225950_10_;
        lvt_17_1_ += p_i225950_11_;
        if (p_i225950_12_) {
            float lvt_18_1_ = lvt_15_1_;
            lvt_15_1_ = p_i225950_3_;
            p_i225950_3_ = lvt_18_1_;
        }
        TabulaModelRenderUtils.PositionTextureVertex lvt_18_2_ = new TabulaModelRenderUtils.PositionTextureVertex(p_i225950_3_, p_i225950_4_, p_i225950_5_, 0.0f, 0.0f);
        TabulaModelRenderUtils.PositionTextureVertex lvt_19_1_ = new TabulaModelRenderUtils.PositionTextureVertex(lvt_15_1_, p_i225950_4_, p_i225950_5_, 0.0f, 8.0f);
        TabulaModelRenderUtils.PositionTextureVertex lvt_20_1_ = new TabulaModelRenderUtils.PositionTextureVertex(lvt_15_1_, lvt_16_1_, p_i225950_5_, 8.0f, 8.0f);
        TabulaModelRenderUtils.PositionTextureVertex lvt_21_1_ = new TabulaModelRenderUtils.PositionTextureVertex(p_i225950_3_, lvt_16_1_, p_i225950_5_, 8.0f, 0.0f);
        TabulaModelRenderUtils.PositionTextureVertex lvt_22_1_ = new TabulaModelRenderUtils.PositionTextureVertex(p_i225950_3_, p_i225950_4_, lvt_17_1_, 0.0f, 0.0f);
        TabulaModelRenderUtils.PositionTextureVertex lvt_23_1_ = new TabulaModelRenderUtils.PositionTextureVertex(lvt_15_1_, p_i225950_4_, lvt_17_1_, 0.0f, 8.0f);
        TabulaModelRenderUtils.PositionTextureVertex lvt_24_1_ = new TabulaModelRenderUtils.PositionTextureVertex(lvt_15_1_, lvt_16_1_, lvt_17_1_, 8.0f, 8.0f);
        TabulaModelRenderUtils.PositionTextureVertex lvt_25_1_ = new TabulaModelRenderUtils.PositionTextureVertex(p_i225950_3_, lvt_16_1_, lvt_17_1_, 8.0f, 0.0f);
        float lvt_26_1_ = p_i225950_1_;
        float lvt_27_1_ = (float)p_i225950_1_ + p_i225950_8_;
        float lvt_28_1_ = (float)p_i225950_1_ + p_i225950_8_ + p_i225950_6_;
        float lvt_29_1_ = (float)p_i225950_1_ + p_i225950_8_ + p_i225950_6_ + p_i225950_6_;
        float lvt_30_1_ = (float)p_i225950_1_ + p_i225950_8_ + p_i225950_6_ + p_i225950_8_;
        float lvt_31_1_ = (float)p_i225950_1_ + p_i225950_8_ + p_i225950_6_ + p_i225950_8_ + p_i225950_6_;
        float lvt_32_1_ = p_i225950_2_;
        float lvt_33_1_ = (float)p_i225950_2_ + p_i225950_8_;
        float lvt_34_1_ = (float)p_i225950_2_ + p_i225950_8_ + p_i225950_7_;
        this.quads[2] = new TabulaModelRenderUtils.TexturedQuad(new TabulaModelRenderUtils.PositionTextureVertex[]{lvt_23_1_, lvt_22_1_, lvt_18_2_, lvt_19_1_}, lvt_27_1_, lvt_32_1_, lvt_28_1_, lvt_33_1_, p_i225950_13_, p_i225950_14_, p_i225950_12_, Direction.DOWN);
        this.quads[3] = new TabulaModelRenderUtils.TexturedQuad(new TabulaModelRenderUtils.PositionTextureVertex[]{lvt_20_1_, lvt_21_1_, lvt_25_1_, lvt_24_1_}, lvt_28_1_, lvt_33_1_, lvt_29_1_, lvt_32_1_, p_i225950_13_, p_i225950_14_, p_i225950_12_, Direction.UP);
        this.quads[1] = new TabulaModelRenderUtils.TexturedQuad(new TabulaModelRenderUtils.PositionTextureVertex[]{lvt_18_2_, lvt_22_1_, lvt_25_1_, lvt_21_1_}, lvt_26_1_, lvt_33_1_, lvt_27_1_, lvt_34_1_, p_i225950_13_, p_i225950_14_, p_i225950_12_, Direction.WEST);
        this.quads[4] = new TabulaModelRenderUtils.TexturedQuad(new TabulaModelRenderUtils.PositionTextureVertex[]{lvt_19_1_, lvt_18_2_, lvt_21_1_, lvt_20_1_}, lvt_27_1_, lvt_33_1_, lvt_28_1_, lvt_34_1_, p_i225950_13_, p_i225950_14_, p_i225950_12_, Direction.NORTH);
        this.quads[0] = new TabulaModelRenderUtils.TexturedQuad(new TabulaModelRenderUtils.PositionTextureVertex[]{lvt_23_1_, lvt_19_1_, lvt_20_1_, lvt_24_1_}, lvt_28_1_, lvt_33_1_, lvt_30_1_, lvt_34_1_, p_i225950_13_, p_i225950_14_, p_i225950_12_, Direction.EAST);
        this.quads[5] = new TabulaModelRenderUtils.TexturedQuad(new TabulaModelRenderUtils.PositionTextureVertex[]{lvt_22_1_, lvt_23_1_, lvt_24_1_, lvt_25_1_}, lvt_30_1_, lvt_33_1_, lvt_31_1_, lvt_34_1_, p_i225950_13_, p_i225950_14_, p_i225950_12_, Direction.SOUTH);
    }
}
