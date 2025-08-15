/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.Model
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.alexthe666.citadel.mixin.client;

import com.github.alexthe666.citadel.client.event.EventPosePlayerHand;
import java.util.function.Function;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={HumanoidModel.class})
public abstract class HumanoidModelMixin
extends Model {
    public HumanoidModelMixin(Function<ResourceLocation, RenderType> p_103110_) {
        super(p_103110_);
    }

    @Inject(at={@At(value="HEAD")}, remap=true, method={"Lnet/minecraft/client/model/HumanoidModel;poseRightArm(Lnet/minecraft/world/entity/LivingEntity;)V"}, cancellable=true)
    private void citadel_poseRightArm(LivingEntity entity, CallbackInfo ci) {
        EventPosePlayerHand event = new EventPosePlayerHand(entity, (HumanoidModel)this, false);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.ALLOW) {
            ci.cancel();
        }
    }

    @Inject(at={@At(value="HEAD")}, remap=true, method={"Lnet/minecraft/client/model/HumanoidModel;poseLeftArm(Lnet/minecraft/world/entity/LivingEntity;)V"}, cancellable=true)
    private void citadel_poseLeftArm(LivingEntity entity, CallbackInfo ci) {
        EventPosePlayerHand event = new EventPosePlayerHand(entity, (HumanoidModel)this, true);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.ALLOW) {
            ci.cancel();
        }
    }
}
