/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.SplashRenderer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.alexthe666.citadel.mixin.client;

import com.github.alexthe666.citadel.client.event.EventRenderSplashText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SplashRenderer.class})
public class SplashRendererMixin {
    @Mutable
    @Shadow
    @Final
    private String f_279597_;
    private int splashTextColor = -1;

    @Inject(method={"Lnet/minecraft/client/gui/components/SplashRenderer;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/client/gui/Font;I)V"}, remap=true, at={@At(value="INVOKE", target="Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V", shift=At.Shift.BEFORE)})
    protected void citadel_preRenderSplashText(GuiGraphics guiGraphics, int width, Font font, int loadProgress, CallbackInfo ci) {
        guiGraphics.m_280168_().m_85836_();
        EventRenderSplashText.Pre event = new EventRenderSplashText.Pre(this.f_279597_, guiGraphics, Minecraft.m_91087_().getPartialTick(), 0xFFFF00);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.ALLOW) {
            this.f_279597_ = event.getSplashText();
            this.splashTextColor = event.getSplashTextColor();
        }
    }

    @Inject(method={"Lnet/minecraft/client/gui/components/SplashRenderer;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/client/gui/Font;I)V"}, remap=true, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/GuiGraphics;drawCenteredString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V", shift=At.Shift.AFTER)})
    protected void citadel_postRenderSplashText(GuiGraphics guiGraphics, int width, Font font, int loadProgress, CallbackInfo ci) {
        EventRenderSplashText.Post event = new EventRenderSplashText.Post(this.f_279597_, guiGraphics, Minecraft.m_91087_().getPartialTick());
        MinecraftForge.EVENT_BUS.post((Event)event);
        guiGraphics.m_280168_().m_85849_();
    }

    @ModifyConstant(method={"Lnet/minecraft/client/gui/components/SplashRenderer;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/client/gui/Font;I)V"}, remap=true, constant={@Constant(intValue=0xFFFF00)})
    private int citadel_splashTextColor(int value) {
        return this.splashTextColor == -1 ? value : this.splashTextColor;
    }
}
