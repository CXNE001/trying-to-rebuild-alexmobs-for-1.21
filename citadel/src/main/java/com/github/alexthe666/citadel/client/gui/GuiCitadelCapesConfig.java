/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.Lighting
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.Options
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.OptionsSubScreen
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.CommonComponents
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.ClientProxy;
import com.github.alexthe666.citadel.client.rewards.CitadelCapes;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;

@OnlyIn(value=Dist.CLIENT)
public class GuiCitadelCapesConfig
extends OptionsSubScreen {
    @Nullable
    private String capeType;
    private Button button;

    public GuiCitadelCapesConfig(Screen parentScreenIn, Options gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn, (Component)Component.m_237115_((String)"citadel.gui.capes"));
        CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_);
        this.capeType = tag.m_128441_("CitadelCapeType") && !tag.m_128461_("CitadelCapeType").isEmpty() ? tag.m_128461_("CitadelCapeType") : null;
    }

    public void m_88315_(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.m_280273_(guiGraphics);
        guiGraphics.m_280653_(this.f_96547_, this.f_96539_, this.f_96543_ / 2, 20, 0xFFFFFF);
        super.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
        int i = this.f_96543_ / 2;
        int j = this.f_96544_ / 6;
        guiGraphics.m_280168_().m_85836_();
        ClientProxy.hideFollower = true;
        GuiCitadelCapesConfig.renderBackwardsEntity(i, j + 144, 60, 0.0f, 0.0f, (LivingEntity)Minecraft.m_91087_().f_91074_);
        ClientProxy.hideFollower = false;
        guiGraphics.m_280168_().m_85849_();
    }

    public static void renderBackwardsEntity(int x, int y, int size, float angleXComponent, float angleYComponent, LivingEntity entity) {
        float f = angleXComponent;
        float f1 = angleYComponent;
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.m_85836_();
        posestack.m_85837_((double)x, (double)y, 1050.0);
        posestack.m_85841_(1.0f, 1.0f, -1.0f);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.m_85837_(0.0, 0.0, 1000.0);
        posestack1.m_85841_((float)size, (float)size, (float)size);
        Quaternionf quaternion = Axis.f_252403_.m_252977_(180.0f);
        Quaternionf quaternion1 = Axis.f_252529_.m_252977_(f1 * 20.0f);
        quaternion.mul((Quaternionfc)quaternion1);
        quaternion.mul((Quaternionfc)Axis.f_252436_.m_252977_(180.0f));
        posestack1.m_252781_(quaternion);
        float f2 = entity.f_20883_;
        float f3 = entity.m_146908_();
        float f4 = entity.m_146909_();
        float f5 = entity.f_20886_;
        float f6 = entity.f_20885_;
        entity.f_20883_ = 180.0f + f * 20.0f;
        entity.m_146922_(180.0f + f * 40.0f);
        entity.m_146926_(-f1 * 20.0f);
        entity.f_20885_ = entity.m_146908_();
        entity.f_20886_ = entity.m_146908_();
        Lighting.m_166384_();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.m_91087_().m_91290_();
        quaternion1.conjugate();
        entityrenderdispatcher.m_252923_(quaternion1);
        entityrenderdispatcher.m_114468_(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.m_91087_().m_91269_().m_110104_();
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.m_114384_((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f, posestack1, (MultiBufferSource)multibuffersource$buffersource, 0xF000F0));
        multibuffersource$buffersource.m_109911_();
        entityrenderdispatcher.m_114468_(true);
        entity.f_20883_ = f2;
        entity.m_146922_(f3);
        entity.m_146926_(f4);
        entity.f_20886_ = f5;
        entity.f_20885_ = f6;
        posestack.m_85849_();
        RenderSystem.applyModelViewMatrix();
        Lighting.m_84931_();
    }

    protected void m_7856_() {
        super.m_7856_();
        int i = this.f_96543_ / 2;
        int j = this.f_96544_ / 6;
        Button doneButton = Button.m_253074_((Component)CommonComponents.f_130655_, p_213079_1_ -> this.f_96541_.m_91152_(this.f_96281_)).m_253046_(200, 20).m_252794_(i - 100, j + 160).m_253136_();
        this.m_142416_((GuiEventListener)doneButton);
        this.button = Button.m_253074_((Component)this.getTypeText(), p_213079_1_ -> {
            CitadelCapes.Cape nextCape = CitadelCapes.getNextCape(this.capeType, Minecraft.m_91087_().f_91074_.m_20148_());
            this.capeType = nextCape == null ? null : nextCape.getIdentifier();
            CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_);
            if (tag != null) {
                if (this.capeType == null) {
                    tag.m_128359_("CitadelCapeType", "");
                    tag.m_128379_("CitadelCapeDisabled", true);
                } else {
                    tag.m_128359_("CitadelCapeType", this.capeType);
                    tag.m_128379_("CitadelCapeDisabled", false);
                }
                CitadelEntityData.setCitadelTag((LivingEntity)Minecraft.m_91087_().f_91074_, tag);
            }
            Citadel.sendMSGToServer(new PropertiesMessage("CitadelTagUpdate", tag, Minecraft.m_91087_().f_91074_.m_19879_()));
            this.button.m_93666_(this.getTypeText());
        }).m_253046_(200, 20).m_252794_(i - 100, j).m_253136_();
        this.m_142416_((GuiEventListener)this.button);
    }

    private Component getTypeText() {
        CitadelCapes.Cape cape;
        MutableComponent suffix = this.capeType == null ? Component.m_237115_((String)"citadel.gui.no_cape") : ((cape = CitadelCapes.getById(this.capeType)) == null ? Component.m_237115_((String)"citadel.gui.no_cape") : Component.m_237115_((String)("cape." + cape.getIdentifier())));
        return Component.m_237115_((String)"citadel.gui.cape_type").m_130946_(" ").m_7220_((Component)suffix);
    }
}
