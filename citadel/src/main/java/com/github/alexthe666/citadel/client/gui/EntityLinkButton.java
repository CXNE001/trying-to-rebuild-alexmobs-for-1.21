/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.Lighting
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Button$OnPress
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.network.chat.CommonComponents
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.client.gui.BookBlit;
import com.github.alexthe666.citadel.client.gui.GuiBasicBook;
import com.github.alexthe666.citadel.client.gui.data.EntityLinkData;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class EntityLinkButton
extends Button {
    private static final Map<String, Entity> renderedEntites = new HashMap<String, Entity>();
    private static final Quaternionf ENTITY_ROTATION = new Quaternionf().rotationXYZ((float)Math.toRadians(30.0), (float)Math.toRadians(130.0), (float)Math.PI);
    private final EntityLinkData data;
    private final GuiBasicBook bookGUI;

    public EntityLinkButton(GuiBasicBook bookGUI, EntityLinkData linkData, int k, int l, Button.OnPress o) {
        super(k + linkData.getX() - 12, l + linkData.getY(), (int)(24.0 * linkData.getScale()), (int)(24.0 * linkData.getScale()), CommonComponents.f_237098_, o, f_252438_);
        this.data = linkData;
        this.bookGUI = bookGUI;
    }

    public void m_87963_(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int lvt_5_1_ = 0;
        int lvt_6_1_ = 30;
        float f = (float)this.data.getScale();
        guiGraphics.m_280168_().m_85836_();
        guiGraphics.m_280168_().m_252880_((float)this.m_252754_(), (float)this.m_252907_(), 0.0f);
        guiGraphics.m_280168_().m_85841_(f, f, 1.0f);
        this.drawBtn(false, guiGraphics, 0, 0, lvt_5_1_, lvt_6_1_, 24, 24);
        Entity model = null;
        EntityType type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(this.data.getEntity()));
        if (type != null) {
            model = renderedEntites.putIfAbsent(this.data.getEntity(), type.m_20615_((Level)Minecraft.m_91087_().f_91073_));
        }
        guiGraphics.m_280588_(this.m_252754_() + Math.round(f * 4.0f), this.m_252907_() + Math.round(f * 4.0f), this.m_252754_() + Math.round(f * 20.0f), this.m_252907_() + Math.round(f * 20.0f));
        if (model != null) {
            model.f_19797_ = Minecraft.m_91087_().f_91074_.f_19797_;
            float renderScale = (float)(this.data.getEntityScale() * (double)f * 10.0);
            this.renderEntityInInventory(guiGraphics, 11 + (int)((double)this.data.getOffset_x() * this.data.getEntityScale()), 22 + (int)((double)this.data.getOffset_y() * this.data.getEntityScale()), renderScale, ENTITY_ROTATION, model);
        }
        guiGraphics.m_280618_();
        if (this.f_93622_) {
            this.bookGUI.setEntityTooltip(this.data.getHoverText());
            lvt_5_1_ = 48;
        } else {
            lvt_5_1_ = 24;
        }
        this.drawBtn(!this.f_93622_, guiGraphics, 0, 0, lvt_5_1_, lvt_6_1_, 24, 24);
        guiGraphics.m_280168_().m_85849_();
    }

    public void drawBtn(boolean color, GuiGraphics guiGraphics, int p_238474_2_, int p_238474_3_, int p_238474_4_, int p_238474_5_, int p_238474_6_, int p_238474_7_) {
        if (color) {
            int widgetColor = this.bookGUI.getWidgetColor();
            int r = (widgetColor & 0xFF0000) >> 16;
            int g = (widgetColor & 0xFF00) >> 8;
            int b = widgetColor & 0xFF;
            BookBlit.blitWithColor(guiGraphics, this.bookGUI.getBookWidgetTexture(), p_238474_2_, p_238474_3_, 0, (float)p_238474_4_, (float)p_238474_5_, p_238474_6_, p_238474_7_, 256, 256, r, g, b, 255);
        } else {
            guiGraphics.m_280398_(this.bookGUI.getBookWidgetTexture(), p_238474_2_, p_238474_3_, 0, (float)p_238474_4_, (float)p_238474_5_, p_238474_6_, p_238474_7_, 256, 256);
        }
    }

    public void renderEntityInInventory(GuiGraphics guiGraphics, int xPos, int yPos, float scale, Quaternionf rotation, Entity entity) {
        guiGraphics.m_280168_().m_85836_();
        guiGraphics.m_280168_().m_85837_((double)xPos, (double)yPos, 50.0);
        guiGraphics.m_280168_().m_252931_(new Matrix4f().scaling(scale, scale, -scale));
        guiGraphics.m_280168_().m_252781_(rotation);
        Vector3f light0 = new Vector3f(1.0f, -1.0f, -1.0f).normalize();
        Vector3f light1 = new Vector3f(-1.0f, 1.0f, 1.0f).normalize();
        RenderSystem.setShaderLights((Vector3f)light0, (Vector3f)light1);
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.m_91087_().m_91290_();
        entityrenderdispatcher.m_114468_(false);
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.m_114384_(entity, 0.0, 0.0, 0.0, 0.0f, 1.0f, guiGraphics.m_280168_(), (MultiBufferSource)guiGraphics.m_280091_(), 0xF000F0));
        guiGraphics.m_280262_();
        entityrenderdispatcher.m_114468_(true);
        guiGraphics.m_280168_().m_85849_();
        Lighting.m_84931_();
    }
}
