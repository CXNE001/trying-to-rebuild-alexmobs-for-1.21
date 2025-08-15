/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.metadata.MetadataSectionSerializer
 *  net.minecraft.server.packs.resources.Resource
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelVoidWormBody;
import com.github.alexthe666.alexsmobs.client.model.ModelVoidWormTail;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.misc.VoidWormMetadataSection;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.LivingEntity;

public abstract class LayerVoidWormGlow<T extends LivingEntity>
extends RenderLayer<T, EntityModel<T>> {
    private final ResourceManager resourceManager;
    private final Object2BooleanMap<ResourceLocation> mcmetaData;
    private EntityModel<T> layerModel;
    private final EntityModel bodyModel = new ModelVoidWormBody(1.001f);
    private final EntityModel tailModel = new ModelVoidWormTail(1.001f);

    public LayerVoidWormGlow(RenderLayerParent<T, EntityModel<T>> renderer, ResourceManager resourceManager, EntityModel<T> layerModel) {
        super(renderer);
        this.resourceManager = resourceManager;
        this.mcmetaData = new Object2BooleanOpenHashMap();
        this.layerModel = layerModel;
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T worm, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ResourceLocation texture = this.getGlowTexture((LivingEntity)worm);
        boolean special = this.isSpecialRenderer(texture);
        if (this.isGlowing((LivingEntity)worm) || special) {
            if (special) {
                if (worm instanceof EntityVoidWormPart) {
                    EntityVoidWormPart body = (EntityVoidWormPart)worm;
                    this.layerModel = body.isTail() ? this.tailModel : this.bodyModel;
                }
                this.layerModel.m_6973_(worm, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer consumer = AMRenderTypes.createMergedVertexConsumer(bufferIn.m_6299_(AMRenderTypes.VOID_WORM_PORTAL_OVERLAY), bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)texture)));
                this.layerModel.m_7695_(matrixStackIn, consumer, 240, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            } else {
                float f = this.getAlpha((LivingEntity)worm);
                this.m_117386_().m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)texture)), 240, LivingEntityRenderer.m_115338_(worm, (float)1.0f), 1.0f, 1.0f, 1.0f, f);
            }
        }
    }

    public abstract ResourceLocation getGlowTexture(LivingEntity var1);

    public abstract boolean isGlowing(LivingEntity var1);

    public abstract float getAlpha(LivingEntity var1);

    private boolean isSpecialRenderer(ResourceLocation resourceLocation) {
        if (this.mcmetaData.containsKey((Object)resourceLocation)) {
            return this.mcmetaData.getBoolean((Object)resourceLocation);
        }
        if (this.resourceManager.m_213713_(resourceLocation).isPresent()) {
            Resource resource = (Resource)this.resourceManager.m_213713_(resourceLocation).get();
            try {
                VoidWormMetadataSection section = resource.m_215509_().m_214059_((MetadataSectionSerializer)VoidWormMetadataSection.SERIALIZER).orElse(new VoidWormMetadataSection());
                this.mcmetaData.put((Object)resourceLocation, section.isEndPortalTexture());
                return section.isEndPortalTexture();
            }
            catch (Exception e) {
                e.printStackTrace();
                this.mcmetaData.put((Object)resourceLocation, false);
                return false;
            }
        }
        this.mcmetaData.put((Object)resourceLocation, false);
        return false;
    }
}
