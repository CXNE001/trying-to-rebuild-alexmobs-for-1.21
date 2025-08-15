/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Vector3f
 */
package com.github.alexthe666.citadel.client.model;

import com.github.alexthe666.citadel.client.model.TabulaModelRenderUtils;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(value=Dist.CLIENT)
static class TabulaModelRenderUtils.TexturedQuad {
    public final TabulaModelRenderUtils.PositionTextureVertex[] vertexPositions;
    public final Vector3f normal;

    public TabulaModelRenderUtils.TexturedQuad(TabulaModelRenderUtils.PositionTextureVertex[] p_i225951_1_, float p_i225951_2_, float p_i225951_3_, float p_i225951_4_, float p_i225951_5_, float p_i225951_6_, float p_i225951_7_, boolean p_i225951_8_, Direction p_i225951_9_) {
        this.vertexPositions = p_i225951_1_;
        float lvt_10_1_ = 0.0f / p_i225951_6_;
        float lvt_11_1_ = 0.0f / p_i225951_7_;
        p_i225951_1_[0] = p_i225951_1_[0].setTextureUV(p_i225951_4_ / p_i225951_6_ - lvt_10_1_, p_i225951_3_ / p_i225951_7_ + lvt_11_1_);
        p_i225951_1_[1] = p_i225951_1_[1].setTextureUV(p_i225951_2_ / p_i225951_6_ + lvt_10_1_, p_i225951_3_ / p_i225951_7_ + lvt_11_1_);
        p_i225951_1_[2] = p_i225951_1_[2].setTextureUV(p_i225951_2_ / p_i225951_6_ + lvt_10_1_, p_i225951_5_ / p_i225951_7_ - lvt_11_1_);
        p_i225951_1_[3] = p_i225951_1_[3].setTextureUV(p_i225951_4_ / p_i225951_6_ - lvt_10_1_, p_i225951_5_ / p_i225951_7_ - lvt_11_1_);
        if (p_i225951_8_) {
            int lvt_12_1_ = p_i225951_1_.length;
            for (int lvt_13_1_ = 0; lvt_13_1_ < lvt_12_1_ / 2; ++lvt_13_1_) {
                TabulaModelRenderUtils.PositionTextureVertex lvt_14_1_ = p_i225951_1_[lvt_13_1_];
                p_i225951_1_[lvt_13_1_] = p_i225951_1_[lvt_12_1_ - 1 - lvt_13_1_];
                p_i225951_1_[lvt_12_1_ - 1 - lvt_13_1_] = lvt_14_1_;
            }
        }
        this.normal = p_i225951_9_.m_253071_();
        if (p_i225951_8_) {
            this.normal.mul(-1.0f, 1.0f, 1.0f);
        }
    }
}
