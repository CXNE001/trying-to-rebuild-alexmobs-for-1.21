/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.math.Transformation
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.resources.model.BakedModel
 *  net.minecraft.client.resources.model.Material
 *  net.minecraft.client.resources.model.ModelBaker
 *  net.minecraft.client.resources.model.ModelState
 *  net.minecraft.client.resources.model.UnbakedModel
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.citadel.client.model.container;

import com.github.alexthe666.citadel.client.model.container.TabulaModelContainer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.math.Transformation;
import java.util.Collection;
import java.util.function.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(value=Dist.CLIENT)
public class VanillaTabulaModel
implements UnbakedModel {
    private final TabulaModelContainer model;
    private final Material particle;
    private final Collection<Material> textures;
    private final ImmutableMap<ItemDisplayContext, Transformation> transforms;

    public VanillaTabulaModel(TabulaModelContainer model, Material particle, ImmutableList<Material> textures, ImmutableMap<ItemDisplayContext, Transformation> transforms) {
        this.model = model;
        this.particle = particle;
        this.textures = textures;
        this.transforms = transforms;
    }

    public Collection<ResourceLocation> m_7970_() {
        return ImmutableList.of();
    }

    public void m_5500_(Function<ResourceLocation, UnbakedModel> p_119538_) {
    }

    @Nullable
    public BakedModel m_7611_(ModelBaker p_250133_, Function<Material, TextureAtlasSprite> p_119535_, ModelState p_119536_, ResourceLocation p_119537_) {
        return null;
    }
}
