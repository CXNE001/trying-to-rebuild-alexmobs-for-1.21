/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.github.alexthe666.citadel.mixin.client;

import com.github.alexthe666.citadel.client.rewards.CitadelCapes;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={AbstractClientPlayer.class})
public abstract class AbstractClientPlayerMixin
extends Player {
    public AbstractClientPlayerMixin(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Inject(at={@At(value="HEAD")}, remap=true, method={"Lnet/minecraft/client/player/AbstractClientPlayer;getCloakTextureLocation()Lnet/minecraft/resources/ResourceLocation;"}, cancellable=true)
    private void citadel_getCapeLocation(CallbackInfoReturnable<ResourceLocation> cir) {
        CitadelCapes.Cape cape = CitadelCapes.getCurrentCape(this);
        if (cape != null) {
            cir.setReturnValue((Object)cape.getTexture());
        }
    }

    @Inject(at={@At(value="HEAD")}, remap=true, method={"Lnet/minecraft/client/player/AbstractClientPlayer;getElytraTextureLocation()Lnet/minecraft/resources/ResourceLocation;"}, cancellable=true)
    private void citadel_getElytraLocation(CallbackInfoReturnable<ResourceLocation> cir) {
        CitadelCapes.Cape cape = CitadelCapes.getCurrentCape(this);
        if (cape != null) {
            cir.setReturnValue((Object)cape.getTexture());
        }
    }
}
