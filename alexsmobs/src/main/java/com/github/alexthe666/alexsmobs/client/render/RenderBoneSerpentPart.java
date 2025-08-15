/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelBoneSerpentBody;
import com.github.alexthe666.alexsmobs.client.model.ModelBoneSerpentTail;
import com.github.alexthe666.alexsmobs.entity.EntityBoneSerpentPart;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class RenderBoneSerpentPart
extends LivingEntityRenderer<EntityBoneSerpentPart, AdvancedEntityModel<EntityBoneSerpentPart>> {
    private static final ResourceLocation TEXTURE_BODY = new ResourceLocation("alexsmobs:textures/entity/bone_serpent_mid.png");
    private static final ResourceLocation TEXTURE_TAIL = new ResourceLocation("alexsmobs:textures/entity/bone_serpent_tail.png");
    private final ModelBoneSerpentBody bodyModel = new ModelBoneSerpentBody();
    private final ModelBoneSerpentTail tailModel = new ModelBoneSerpentTail();

    public RenderBoneSerpentPart(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelBoneSerpentBody(), 0.3f);
    }

    protected boolean shouldShowName(EntityBoneSerpentPart entity) {
        return super.m_6512_((LivingEntity)entity) && (entity.m_6052_() || entity.m_8077_() && entity == this.f_114476_.f_114359_);
    }

    protected void scale(EntityBoneSerpentPart entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        this.f_115290_ = entitylivingbaseIn.isTail() ? this.tailModel : this.bodyModel;
    }

    public ResourceLocation getTextureLocation(EntityBoneSerpentPart entity) {
        return entity.isTail() ? TEXTURE_TAIL : TEXTURE_BODY;
    }
}
