/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.client.sounds.SoundEngine
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.github.alexthe666.citadel.mixin.client;

import com.github.alexthe666.citadel.client.tick.ClientTickRateTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={SoundEngine.class})
public class SoundEngineMixin {
    @Inject(method={"Lnet/minecraft/client/sounds/SoundEngine;calculatePitch(Lnet/minecraft/client/resources/sounds/SoundInstance;)F"}, remap=true, cancellable=true, at={@At(value="RETURN")})
    protected void citadel_setupRotations(SoundInstance soundInstance, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue((Object)Float.valueOf(((Float)cir.getReturnValue()).floatValue() * ClientTickRateTracker.getForClient(Minecraft.m_91087_()).modifySoundPitch(soundInstance)));
    }
}
