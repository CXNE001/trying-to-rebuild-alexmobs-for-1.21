/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.NativeImage
 *  com.mojang.blaze3d.platform.TextureUtil
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.util.FastColor$ABGR32
 */
package com.github.alexthe666.citadel.client.texture;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.TextureUtil;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.FastColor;

public class VideoFrameTexture
extends DynamicTexture {
    public VideoFrameTexture(NativeImage image) {
        super(image);
    }

    public void m_117988_(NativeImage nativeImage) {
        super.m_117988_(nativeImage);
        if (this.m_117991_() != null) {
            TextureUtil.prepareImage((int)this.m_117963_(), (int)this.m_117991_().m_84982_(), (int)this.m_117991_().m_85084_());
            this.m_117985_();
        }
    }

    public void setPixelsFromBufferedImage(BufferedImage bufferedImage) {
        for (int i = 0; i < Math.min(this.m_117991_().m_84982_(), bufferedImage.getWidth()); ++i) {
            for (int j = 0; j < Math.min(this.m_117991_().m_85084_(), bufferedImage.getHeight()); ++j) {
                int color = bufferedImage.getRGB(i, j);
                int r = color >> 16 & 0xFF;
                int g = color >> 8 & 0xFF;
                int b = color & 0xFF;
                this.m_117991_().m_84988_(i, j, FastColor.ABGR32.m_266248_((int)255, (int)b, (int)g, (int)r));
            }
        }
        this.m_117985_();
    }
}
