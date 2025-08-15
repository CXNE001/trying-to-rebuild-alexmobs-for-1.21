/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelVoidWorm;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerVoidWormGlow;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWorm;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class RenderVoidWormHead
extends MobRenderer<EntityVoidWorm, ModelVoidWorm> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_head.png");
    private static final ResourceLocation TEXTURE_GLOW = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_head_glow.png");

    public RenderVoidWormHead(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelVoidWorm(0.0f), 1.0f);
        this.m_115326_(new LayerVoidWormGlow((RenderLayerParent)this, renderManagerIn.m_174026_(), (EntityModel)new ModelVoidWorm(1.001f)){

            @Override
            public ResourceLocation getGlowTexture(LivingEntity worm) {
                return TEXTURE_GLOW;
            }

            @Override
            public boolean isGlowing(LivingEntity worm) {
                return true;
            }

            @Override
            public float getAlpha(LivingEntity livingEntity) {
                return 1.0f;
            }
        });
    }

    @Nullable
    protected RenderType getRenderType(EntityVoidWorm jelly, boolean normal, boolean invis, boolean outline) {
        ResourceLocation resourcelocation = this.getTextureLocation(jelly);
        if (invis) {
            return RenderType.m_110467_((ResourceLocation)resourcelocation);
        }
        if (normal) {
            return RenderType.m_110473_((ResourceLocation)resourcelocation);
        }
        return outline ? RenderType.m_110491_((ResourceLocation)resourcelocation) : null;
    }

    public boolean shouldRender(EntityVoidWorm worm, Frustum camera, double camX, double camY, double camZ) {
        return worm.getPortalTicks() <= 0 && super.m_5523_((Mob)worm, camera, camX, camY, camZ);
    }

    public ResourceLocation getTextureLocation(EntityVoidWorm entity) {
        return TEXTURE;
    }
}
