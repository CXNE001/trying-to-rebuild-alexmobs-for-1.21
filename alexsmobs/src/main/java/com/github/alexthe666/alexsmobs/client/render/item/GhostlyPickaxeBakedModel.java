/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.texture.TextureAtlas
 *  net.minecraft.client.resources.model.BakedModel
 *  net.minecraft.core.Direction
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.model.BakedModelWrapper
 *  net.minecraftforge.client.model.data.ModelData
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.alexsmobs.client.render.item;

import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GhostlyPickaxeBakedModel
extends BakedModelWrapper {
    public GhostlyPickaxeBakedModel(BakedModel bakedModel) {
        super(bakedModel);
    }

    public List<BakedQuad> m_213637_(@Nullable BlockState state, @Nullable Direction side, RandomSource rand) {
        return GhostlyPickaxeBakedModel.transformQuads(super.m_213637_(state, side, rand));
    }

    public List<RenderType> getRenderTypes(ItemStack itemStack, boolean fabulous) {
        return List.of(AMRenderTypes.getGhostPickaxe(TextureAtlas.f_118259_));
    }

    public BakedModel applyTransform(ItemDisplayContext cameraTransformType, PoseStack poseStack, boolean applyLeftHandTransform) {
        this.m_7442_().m_269404_(cameraTransformType).m_111763_(applyLeftHandTransform, poseStack);
        return this;
    }

    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType renderType) {
        return GhostlyPickaxeBakedModel.transformQuads(this.originalModel.getQuads(state, side, rand, extraData, renderType));
    }

    private static List<BakedQuad> transformQuads(List<BakedQuad> oldQuads) {
        ArrayList<BakedQuad> quads = new ArrayList<BakedQuad>();
        for (BakedQuad quad : oldQuads) {
            quads.add(GhostlyPickaxeBakedModel.setFullbright(quad));
        }
        return quads;
    }

    private static BakedQuad setFullbright(BakedQuad quad) {
        int[] vertexData = (int[])quad.m_111303_().clone();
        int step = vertexData.length / 4;
        vertexData[6] = 0xF000F0;
        vertexData[6 + step] = 0xF000F0;
        vertexData[6 + 2 * step] = 0xF000F0;
        vertexData[6 + 3 * step] = 0xF000F0;
        return new BakedQuad(vertexData, quad.m_111305_(), quad.m_111306_(), quad.m_173410_(), quad.m_111307_());
    }

    public List<BakedModel> getRenderPasses(ItemStack itemStack, boolean fabulous) {
        return List.of(this);
    }
}
