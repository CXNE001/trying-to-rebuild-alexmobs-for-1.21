/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.DefaultAttributes
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.client.event.EntityRenderersEvent$AddLayers
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.alexsmobs.client;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerRainbow;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.stream.Collectors;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

@OnlyIn(value=Dist.CLIENT)
public class ClientLayerRegistry {
    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        ImmutableList entityTypes = ImmutableList.copyOf((Collection)ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(DefaultAttributes::m_22301_).map(entityType -> entityType).collect(Collectors.toList()));
        entityTypes.forEach(entityType -> ClientLayerRegistry.addLayerIfApplicable((EntityType<? extends LivingEntity>)entityType, event));
        for (String skinType : event.getSkins()) {
            event.getSkin(skinType).m_115326_((RenderLayer)new LayerRainbow((RenderLayerParent)event.getSkin(skinType)));
        }
    }

    private static void addLayerIfApplicable(EntityType<? extends LivingEntity> entityType, EntityRenderersEvent.AddLayers event) {
        LivingEntityRenderer renderer = null;
        if (entityType != EntityType.f_20565_) {
            try {
                renderer = event.getRenderer(entityType);
            }
            catch (Exception e) {
                AlexsMobs.LOGGER.warn("Could not apply rainbow color layer to " + ForgeRegistries.ENTITY_TYPES.getKey(entityType) + ", has custom renderer that is not LivingEntityRenderer.");
            }
            if (renderer != null) {
                renderer.m_115326_((RenderLayer)new LayerRainbow((RenderLayerParent)renderer));
            }
        }
    }
}
