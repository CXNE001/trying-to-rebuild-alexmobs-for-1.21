/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.Options
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.OptionsSubScreen
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.CommonComponents
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.client.gui.widget.ForgeSlider
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.client.rewards.CitadelPatreonRenderer;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.widget.ForgeSlider;

@OnlyIn(value=Dist.CLIENT)
public class GuiCitadelPatreonConfig
extends OptionsSubScreen {
    private ForgeSlider distSlider;
    private ForgeSlider speedSlider;
    private ForgeSlider heightSlider;
    private Button changeButton;
    private float rotateDist;
    private float rotateSpeed;
    private float rotateHeight;
    private String followType;

    public GuiCitadelPatreonConfig(Screen parentScreenIn, Options gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn, (Component)Component.m_237115_((String)"citadel.gui.patreon_customization"));
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_);
        float distance = tag.m_128441_("CitadelRotateDistance") ? tag.m_128457_("CitadelRotateDistance") : 2.0f;
        float speed = tag.m_128441_("CitadelRotateSpeed") ? tag.m_128457_("CitadelRotateSpeed") : 1.0f;
        float height = tag.m_128441_("CitadelRotateHeight") ? tag.m_128457_("CitadelRotateHeight") : 1.0f;
        this.rotateDist = GuiCitadelPatreonConfig.roundTo(distance, 3);
        this.rotateSpeed = GuiCitadelPatreonConfig.roundTo(speed, 3);
        this.rotateHeight = GuiCitadelPatreonConfig.roundTo(height, 3);
        this.followType = tag.m_128441_("CitadelFollowerType") ? tag.m_128461_("CitadelFollowerType") : "citadel";
    }

    private void setSliderValue(int i, float sliderValue) {
        boolean flag = false;
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_);
        if (i == 0) {
            this.rotateDist = GuiCitadelPatreonConfig.roundTo(sliderValue, 3);
            tag.m_128350_("CitadelRotateDistance", this.rotateDist);
        } else if (i == 1) {
            this.rotateSpeed = GuiCitadelPatreonConfig.roundTo(sliderValue, 3);
            tag.m_128350_("CitadelRotateSpeed", this.rotateSpeed);
        } else {
            this.rotateHeight = GuiCitadelPatreonConfig.roundTo(sliderValue, 3);
            tag.m_128350_("CitadelRotateHeight", this.rotateHeight);
        }
        CitadelEntityData.setCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_, tag);
        Citadel.sendMSGToServer(new PropertiesMessage("CitadelPatreonConfig", tag, Minecraft.m_91087_().f_91074_.m_19879_()));
    }

    public static float roundTo(float value, int places) {
        return value;
    }

    public void m_88315_(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.m_280273_(guiGraphics);
        guiGraphics.m_280653_(this.f_96547_, this.f_96539_, this.f_96543_ / 2, 20, 0xFFFFFF);
        super.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
    }

    protected void m_7856_() {
        super.m_7856_();
        int i = this.f_96543_ / 2;
        int j = this.f_96544_ / 6;
        Button doneButton = Button.m_253074_((Component)CommonComponents.f_130655_, p_213079_1_ -> this.f_96541_.m_91152_(this.f_96281_)).m_253046_(200, 20).m_252794_(i - 100, j + 120).m_253136_();
        this.m_142416_((GuiEventListener)doneButton);
        this.distSlider = new ForgeSlider(i - 75 - 25, j + 30, 150, 20, (Component)Component.m_237115_((String)"citadel.gui.orbit_dist").m_7220_((Component)Component.m_237115_((String)": ")), (Component)Component.m_237115_((String)""), 0.125, 5.0, this.rotateDist, 0.1, 1, true){

            protected void m_5697_() {
                GuiCitadelPatreonConfig.this.setSliderValue(0, (float)this.getValue());
            }
        };
        this.m_142416_((GuiEventListener)this.distSlider);
        Button reset1Button = Button.m_253074_((Component)Component.m_237115_((String)"citadel.gui.reset"), p_213079_1_ -> this.setSliderValue(0, 0.4f)).m_253046_(40, 20).m_252794_(i - 75 + 135, j + 30).m_253136_();
        this.m_142416_((GuiEventListener)reset1Button);
        this.speedSlider = new ForgeSlider(i - 75 - 25, j + 60, 150, 20, (Component)Component.m_237115_((String)"citadel.gui.orbit_speed").m_7220_((Component)Component.m_237115_((String)": ")), (Component)Component.m_237115_((String)""), 0.0, 5.0, this.rotateSpeed, 0.1, 2, true){

            protected void m_5697_() {
                GuiCitadelPatreonConfig.this.setSliderValue(1, (float)this.getValue());
            }
        };
        this.m_142416_((GuiEventListener)this.speedSlider);
        Button reset2Button = Button.m_253074_((Component)Component.m_237115_((String)"citadel.gui.reset"), p_213079_1_ -> this.setSliderValue(1, 0.2f)).m_253046_(40, 20).m_252794_(i - 75 + 135, j + 60).m_253136_();
        this.m_142416_((GuiEventListener)reset2Button);
        this.heightSlider = new ForgeSlider(i - 75 - 25, j + 90, 150, 20, (Component)Component.m_237115_((String)"citadel.gui.orbit_height").m_7220_((Component)Component.m_237115_((String)": ")), (Component)Component.m_237115_((String)""), 0.0, 2.0, this.rotateHeight, 0.1, 2, true){

            protected void m_5697_() {
                GuiCitadelPatreonConfig.this.setSliderValue(2, (float)this.getValue());
            }
        };
        this.m_142416_((GuiEventListener)this.heightSlider);
        Button reset3Button = Button.m_253074_((Component)Component.m_237115_((String)"citadel.gui.reset"), p_213079_1_ -> this.setSliderValue(2, 0.5f)).m_253046_(40, 20).m_252794_(i - 75 + 135, j + 90).m_253136_();
        this.m_142416_((GuiEventListener)reset3Button);
        this.changeButton = Button.m_253074_((Component)this.getTypeText(), p_213079_1_ -> {
            this.followType = CitadelPatreonRenderer.getIdOfNext(this.followType);
            CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_);
            if (tag != null) {
                tag.m_128359_("CitadelFollowerType", this.followType);
                CitadelEntityData.setCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_, tag);
            }
            Citadel.sendMSGToServer(new PropertiesMessage("CitadelPatreonConfig", tag, Minecraft.m_91087_().f_91074_.m_19879_()));
            this.changeButton.m_93666_(this.getTypeText());
        }).m_253046_(200, 20).m_252794_(i - 100, j).m_253136_();
        this.m_142416_((GuiEventListener)this.changeButton);
    }

    private Component getTypeText() {
        return Component.m_237115_((String)"citadel.gui.follower_type").m_7220_((Component)Component.m_237115_((String)("citadel.follower." + this.followType)));
    }
}
