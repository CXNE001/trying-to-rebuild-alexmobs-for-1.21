/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.geom.ModelLayerLocation
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.client.event.EntityRenderersEvent$RegisterLayerDefinitions
 */
package com.github.alexthe666.alexsmobs.client.model.layered;

import com.github.alexthe666.alexsmobs.client.model.ModelWanderingVillagerRider;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelAMElytra;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFedora;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFlyingFishBoots;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFrontierCap;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFroststalkerHelmet;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelMooseHeadgear;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelNoveltyHat;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelRoadrunnerBoots;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelRockyChestplate;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelSombrero;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelSpikedTurtleShell;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelUnsettlingKimono;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;

@OnlyIn(value=Dist.CLIENT)
public class AMModelLayers {
    public static final ModelLayerLocation AM_ELYTRA = AMModelLayers.createLocation("am_elytra", "main");
    public static final ModelLayerLocation SITTING_WANDERING_VILLAGER = AMModelLayers.createLocation("sitting_wandering_villager", "main");
    public static final ModelLayerLocation ROADRUNNER_BOOTS = AMModelLayers.createLocation("roadrunner_boots", "main");
    public static final ModelLayerLocation MOOSE_HEADGEAR = AMModelLayers.createLocation("moose_headgear", "main");
    public static final ModelLayerLocation FRONTIER_CAP = AMModelLayers.createLocation("frontier_cap", "main");
    public static final ModelLayerLocation SPIKED_TURTLE_SHELL = AMModelLayers.createLocation("spiked_turtle_shell", "main");
    public static final ModelLayerLocation FEDORA = AMModelLayers.createLocation("fedora", "main");
    public static final ModelLayerLocation SOMBRERO = AMModelLayers.createLocation("sombrero", "main");
    public static final ModelLayerLocation SOMBRERO_GOOFY_FASHION = AMModelLayers.createLocation("sombrero_goofy_fashion", "main");
    public static final ModelLayerLocation FROSTSTALKER_HELMET = AMModelLayers.createLocation("froststalker_helmet", "main");
    public static final ModelLayerLocation ROCKY_CHESTPLATE = AMModelLayers.createLocation("rocky_chestplate", "main");
    public static final ModelLayerLocation FLYING_FISH_BOOTS = AMModelLayers.createLocation("flying_fish_boots", "main");
    public static final ModelLayerLocation NOVELTY_HAT = AMModelLayers.createLocation("novelty_hat", "main");
    public static final ModelLayerLocation UNDERMINER = AMModelLayers.createLocation("underminer", "main");
    public static final ModelLayerLocation UNSETTLING_KIMONO = AMModelLayers.createLocation("unsettling_kimono", "main");

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SITTING_WANDERING_VILLAGER, () -> LayerDefinition.m_171565_((MeshDefinition)ModelWanderingVillagerRider.m_171052_(), (int)64, (int)64));
        event.registerLayerDefinition(UNDERMINER, () -> LayerDefinition.m_171565_((MeshDefinition)HumanoidModel.m_170681_((CubeDeformation)CubeDeformation.f_171458_, (float)0.05f), (int)64, (int)64));
        event.registerLayerDefinition(ROADRUNNER_BOOTS, () -> ModelRoadrunnerBoots.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(MOOSE_HEADGEAR, () -> ModelMooseHeadgear.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(FRONTIER_CAP, () -> ModelFrontierCap.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(SPIKED_TURTLE_SHELL, () -> ModelSpikedTurtleShell.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(FEDORA, () -> ModelFedora.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(AM_ELYTRA, () -> ModelAMElytra.createLayer(new CubeDeformation(1.0f)));
        event.registerLayerDefinition(SOMBRERO, () -> ModelSombrero.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(SOMBRERO_GOOFY_FASHION, () -> ModelSombrero.createArmorLayerAprilFools(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(FROSTSTALKER_HELMET, () -> ModelFroststalkerHelmet.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(ROCKY_CHESTPLATE, () -> ModelRockyChestplate.createArmorLayer(new CubeDeformation(0.7f)));
        event.registerLayerDefinition(FLYING_FISH_BOOTS, () -> ModelFlyingFishBoots.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(NOVELTY_HAT, () -> ModelNoveltyHat.createArmorLayer(new CubeDeformation(0.5f)));
        event.registerLayerDefinition(UNSETTLING_KIMONO, () -> ModelUnsettlingKimono.createArmorLayer(new CubeDeformation(0.5f)));
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation("alexsmobs", model), layer);
    }
}
