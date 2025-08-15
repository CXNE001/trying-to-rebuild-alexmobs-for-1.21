/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.blaze3d.platform.NativeImage
 *  com.mojang.blaze3d.platform.TextureUtil
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.metadata.MetadataSectionSerializer
 *  net.minecraft.server.packs.resources.Resource
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.util.FastColor$ABGR32
 *  net.minecraft.util.GsonHelper
 */
package com.github.alexthe666.citadel.client.texture;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.TextureUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.FastColor;
import net.minecraft.util.GsonHelper;

public class ColorMappedTexture
extends SimpleTexture {
    private final int[] colors;

    public ColorMappedTexture(ResourceLocation resourceLocation, int[] colors) {
        super(resourceLocation);
        this.colors = colors;
    }

    public void m_6704_(ResourceManager resourceManager) throws IOException {
        NativeImage nativeimage = this.getNativeImage(resourceManager, this.f_118129_);
        if (nativeimage != null) {
            if (resourceManager.m_213713_(this.f_118129_).isPresent()) {
                Resource resource = (Resource)resourceManager.m_213713_(this.f_118129_).get();
                try {
                    ColorsMetadataSection section = resource.m_215509_().m_214059_((MetadataSectionSerializer)ColorsMetadataSection.SERIALIZER).orElse(new ColorsMetadataSection(null));
                    NativeImage nativeimage2 = this.getNativeImage(resourceManager, section.getColorRamp());
                    if (nativeimage2 != null) {
                        this.processColorMap(nativeimage, nativeimage2);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            TextureUtil.prepareImage((int)this.m_117963_(), (int)nativeimage.m_84982_(), (int)nativeimage.m_85084_());
            this.m_117966_();
            nativeimage.m_85040_(0, 0, 0, false);
        }
    }

    private NativeImage getNativeImage(ResourceManager resourceManager, @Nullable ResourceLocation resourceLocation) {
        Resource resource = null;
        if (resourceLocation == null) {
            return null;
        }
        try {
            resource = resourceManager.m_215593_(resourceLocation);
            InputStream inputstream = resource.m_215507_();
            NativeImage nativeimage = NativeImage.m_85058_((InputStream)inputstream);
            if (inputstream != null) {
                inputstream.close();
            }
            return nativeimage;
        }
        catch (Throwable throwable1) {
            return null;
        }
    }

    private void processColorMap(NativeImage nativeImage, NativeImage colorMap) {
        int i;
        int[] fromColorMap = new int[colorMap.m_85084_()];
        for (i = 0; i < fromColorMap.length; ++i) {
            fromColorMap[i] = colorMap.m_84985_(0, i);
        }
        for (i = 0; i < nativeImage.m_84982_(); ++i) {
            for (int j = 0; j < nativeImage.m_85084_(); ++j) {
                int colorAt = nativeImage.m_84985_(i, j);
                if (FastColor.ABGR32.m_266503_((int)colorAt) == 0) continue;
                int replaceIndex = -1;
                for (int k = 0; k < fromColorMap.length; ++k) {
                    if (colorAt != fromColorMap[k]) continue;
                    replaceIndex = k;
                }
                if (replaceIndex < 0 || this.colors.length <= replaceIndex) continue;
                int r = this.colors[replaceIndex] >> 16 & 0xFF;
                int g = this.colors[replaceIndex] >> 8 & 0xFF;
                int b = this.colors[replaceIndex] & 0xFF;
                nativeImage.m_84988_(i, j, FastColor.ABGR32.m_266248_((int)FastColor.ABGR32.m_266503_((int)colorAt), (int)b, (int)g, (int)r));
            }
        }
    }

    private static class ColorsMetadataSection {
        public static final ColorsMetadataSectionSerializer SERIALIZER = new ColorsMetadataSectionSerializer();
        private final ResourceLocation colorRamp;

        public ColorsMetadataSection(ResourceLocation colorRamp) {
            this.colorRamp = colorRamp;
        }

        private boolean areColorsEqual(int color1, int color2) {
            int r1 = color1 >> 16 & 0xFF;
            int g1 = color1 >> 8 & 0xFF;
            int b1 = color1 & 0xFF;
            int r2 = color2 >> 16 & 0xFF;
            int g2 = color2 >> 8 & 0xFF;
            int b2 = color2 & 0xFF;
            return r1 == r2 && g1 == g2 && b1 == b2;
        }

        public ResourceLocation getColorRamp() {
            return this.colorRamp;
        }
    }

    private static class ColorsMetadataSectionSerializer
    implements MetadataSectionSerializer<ColorsMetadataSection> {
        private ColorsMetadataSectionSerializer() {
        }

        public ColorsMetadataSection fromJson(JsonObject json) {
            return new ColorsMetadataSection(new ResourceLocation(GsonHelper.m_13906_((JsonObject)json, (String)"color_ramp")));
        }

        public String m_7991_() {
            return "colors";
        }
    }
}
