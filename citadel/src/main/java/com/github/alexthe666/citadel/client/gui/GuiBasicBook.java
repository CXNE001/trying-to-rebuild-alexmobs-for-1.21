/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.Lighting
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.math.Axis
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.client.resources.language.I18n
 *  net.minecraft.client.resources.sounds.SimpleSoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.core.NonNullList
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.TagParser
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.Resource
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeManager
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.commons.io.IOUtils
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.client.gui.BookBlit;
import com.github.alexthe666.citadel.client.gui.BookPage;
import com.github.alexthe666.citadel.client.gui.BookPageButton;
import com.github.alexthe666.citadel.client.gui.EntityLinkButton;
import com.github.alexthe666.citadel.client.gui.LinkButton;
import com.github.alexthe666.citadel.client.gui.data.EntityLinkData;
import com.github.alexthe666.citadel.client.gui.data.EntityRenderData;
import com.github.alexthe666.citadel.client.gui.data.ImageData;
import com.github.alexthe666.citadel.client.gui.data.ItemRenderData;
import com.github.alexthe666.citadel.client.gui.data.LineData;
import com.github.alexthe666.citadel.client.gui.data.LinkData;
import com.github.alexthe666.citadel.client.gui.data.RecipeData;
import com.github.alexthe666.citadel.client.gui.data.TabulaRenderData;
import com.github.alexthe666.citadel.client.gui.data.Whitespace;
import com.github.alexthe666.citadel.client.model.TabulaModel;
import com.github.alexthe666.citadel.client.model.TabulaModelHandler;
import com.github.alexthe666.citadel.recipe.SpecialRecipeInGuideBook;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.math.Axis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.io.IOUtils;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

public abstract class GuiBasicBook
extends Screen {
    private static final ResourceLocation BOOK_PAGE_TEXTURE = new ResourceLocation("citadel:textures/gui/book/book_pages.png");
    private static final ResourceLocation BOOK_BINDING_TEXTURE = new ResourceLocation("citadel:textures/gui/book/book_binding.png");
    private static final ResourceLocation BOOK_WIDGET_TEXTURE = new ResourceLocation("citadel:textures/gui/book/widgets.png");
    private static final ResourceLocation BOOK_BUTTONS_TEXTURE = new ResourceLocation("citadel:textures/gui/book/link_buttons.png");
    protected final List<LineData> lines = new ArrayList<LineData>();
    protected final List<LinkData> links = new ArrayList<LinkData>();
    protected final List<ItemRenderData> itemRenders = new ArrayList<ItemRenderData>();
    protected final List<RecipeData> recipes = new ArrayList<RecipeData>();
    protected final List<TabulaRenderData> tabulaRenders = new ArrayList<TabulaRenderData>();
    protected final List<EntityRenderData> entityRenders = new ArrayList<EntityRenderData>();
    protected final List<EntityLinkData> entityLinks = new ArrayList<EntityLinkData>();
    protected final List<ImageData> images = new ArrayList<ImageData>();
    protected final List<Whitespace> yIndexesToSkip = new ArrayList<Whitespace>();
    private final Map<String, TabulaModel> renderedTabulaModels = new HashMap<String, TabulaModel>();
    private final Map<String, Entity> renderedEntites = new HashMap<String, Entity>();
    private final Map<String, ResourceLocation> textureMap = new HashMap<String, ResourceLocation>();
    protected ItemStack bookStack;
    protected int xSize = 390;
    protected int ySize = 320;
    protected int currentPageCounter = 0;
    protected int maxPagesFromPrinting = 0;
    protected int linesFromJSON = 0;
    protected int linesFromPrinting = 0;
    protected ResourceLocation prevPageJSON;
    protected ResourceLocation currentPageJSON;
    protected ResourceLocation currentPageText = null;
    protected BookPageButton buttonNextPage;
    protected BookPageButton buttonPreviousPage;
    protected BookPage internalPage = null;
    protected String writtenTitle = "";
    protected int preservedPageIndex = 0;
    protected String entityTooltip;
    private int mouseX;
    private int mouseY;

    public GuiBasicBook(ItemStack bookStack, Component title) {
        super(title);
        this.bookStack = bookStack;
        this.currentPageJSON = this.getRootPage();
    }

    public static void drawTabulaModelOnScreen(GuiGraphics guiGraphics, TabulaModel model, ResourceLocation tex, int posX, int posY, float scale, boolean follow, double xRot, double yRot, double zRot, float mouseX, float mouseY) {
        float f = (float)Math.atan(mouseX / 40.0f);
        float f1 = (float)Math.atan(mouseY / 40.0f);
        PoseStack matrixstack = new PoseStack();
        matrixstack.m_85837_((double)posX, (double)posY, 120.0);
        matrixstack.m_85841_(scale, scale, scale);
        Quaternionf quaternion = Axis.f_252403_.m_252977_(0.0f);
        Quaternionf quaternion1 = Axis.f_252529_.m_252977_(f1 * 20.0f);
        if (follow) {
            quaternion.mul((Quaternionfc)quaternion1);
        }
        matrixstack.m_252781_(quaternion);
        if (follow) {
            matrixstack.m_252781_(Axis.f_252436_.m_252977_(180.0f + f * 40.0f));
        }
        matrixstack.m_252781_(Axis.f_252529_.m_252977_((float)(-xRot)));
        matrixstack.m_252781_(Axis.f_252436_.m_252977_((float)yRot));
        matrixstack.m_252781_(Axis.f_252403_.m_252977_((float)zRot));
        EntityRenderDispatcher entityrenderermanager = Minecraft.m_91087_().m_91290_();
        quaternion1.conjugate();
        entityrenderermanager.m_252923_(quaternion1);
        entityrenderermanager.m_114468_(false);
        MultiBufferSource.BufferSource irendertypebuffer$impl = Minecraft.m_91087_().m_91269_().m_110104_();
        RenderSystem.runAsFancy(() -> {
            VertexConsumer ivertexbuilder = irendertypebuffer$impl.m_6299_(RenderType.m_110458_((ResourceLocation)tex));
            model.resetToDefaultPose();
            model.m_7695_(matrixstack, ivertexbuilder, 0xF000F0, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        });
        Lighting.m_84931_();
    }

    public void drawEntityOnScreen(GuiGraphics guiGraphics, MultiBufferSource bufferSource, int posX, int posY, float zOff, float scale, boolean follow, double xRot, double yRot, double zRot, float mouseX, float mouseY, Entity entity) {
        float customYaw = (float)posX - mouseX;
        float customPitch = (float)posY - mouseY;
        float f = (float)Math.atan(customYaw / 40.0f);
        float f1 = (float)Math.atan(customPitch / 40.0f);
        if (follow) {
            float setX = f1 * 20.0f;
            float setY = f * 20.0f;
            entity.m_146926_(setX);
            entity.m_146922_(setY);
            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).f_20883_ = setY;
                ((LivingEntity)entity).f_20884_ = setY;
                ((LivingEntity)entity).f_20885_ = setY;
                ((LivingEntity)entity).f_20886_ = setY;
            }
        } else {
            f = 0.0f;
            f1 = 0.0f;
        }
        guiGraphics.m_280168_().m_85836_();
        guiGraphics.m_280168_().m_252880_((float)posX, (float)posY, zOff);
        guiGraphics.m_280168_().m_252931_(new Matrix4f().scaling(scale, scale, -scale));
        Quaternionf quaternion = Axis.f_252403_.m_252977_(180.0f);
        Quaternionf quaternion1 = Axis.f_252529_.m_252977_(f1 * 20.0f);
        quaternion.mul((Quaternionfc)quaternion1);
        quaternion.mul((Quaternionfc)Axis.f_252495_.m_252977_((float)xRot));
        quaternion.mul((Quaternionfc)Axis.f_252436_.m_252977_((float)yRot));
        quaternion.mul((Quaternionfc)Axis.f_252403_.m_252977_((float)zRot));
        guiGraphics.m_280168_().m_252781_(quaternion);
        Vector3f light0 = new Vector3f(1.0f, -1.0f, -1.0f).normalize();
        Vector3f light1 = new Vector3f(-1.0f, 1.0f, 1.0f).normalize();
        RenderSystem.setShaderLights((Vector3f)light0, (Vector3f)light1);
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.m_91087_().m_91290_();
        quaternion1.conjugate();
        entityrenderdispatcher.m_252923_(quaternion1);
        entityrenderdispatcher.m_114468_(false);
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.m_114384_(entity, 0.0, 0.0, 0.0, 0.0f, 1.0f, guiGraphics.m_280168_(), bufferSource, 240));
        entityrenderdispatcher.m_114468_(true);
        entity.m_146922_(0.0f);
        entity.m_146926_(0.0f);
        if (entity instanceof LivingEntity) {
            ((LivingEntity)entity).f_20883_ = 0.0f;
            ((LivingEntity)entity).f_20886_ = 0.0f;
            ((LivingEntity)entity).f_20885_ = 0.0f;
        }
        guiGraphics.m_280262_();
        entityrenderdispatcher.m_114468_(true);
        guiGraphics.m_280168_().m_85849_();
        Lighting.m_84931_();
    }

    protected void m_7856_() {
        super.m_7856_();
        this.playBookOpeningSound();
        this.addNextPreviousButtons();
        this.addLinkButtons();
    }

    private void addNextPreviousButtons() {
        int k = (this.f_96543_ - this.xSize) / 2;
        int l = (this.f_96544_ - this.ySize + 128) / 2;
        this.buttonPreviousPage = (BookPageButton)this.m_142416_((GuiEventListener)new BookPageButton(this, k + 10, l + 180, false, p_214208_1_ -> this.onSwitchPage(false), true));
        this.buttonNextPage = (BookPageButton)this.m_142416_((GuiEventListener)new BookPageButton(this, k + 365, l + 180, true, p_214205_1_ -> this.onSwitchPage(true), true));
    }

    private void addLinkButtons() {
        this.f_169369_.clear();
        this.m_169413_();
        this.addNextPreviousButtons();
        int k = (this.f_96543_ - this.xSize) / 2;
        int l = (this.f_96544_ - this.ySize + 128) / 2;
        for (LinkData linkData : this.links) {
            if (linkData.getPage() == this.currentPageCounter) {
                int maxLength = Math.max(100, Minecraft.m_91087_().f_91062_.m_92895_(linkData.getTitleText()) + 20);
                this.yIndexesToSkip.add(new Whitespace(linkData.getPage(), linkData.getX() - maxLength / 2, linkData.getY(), 100, 20));
                this.m_142416_((GuiEventListener)new LinkButton(this, k + linkData.getX() - maxLength / 2, l + linkData.getY(), maxLength, 20, (Component)Component.m_237115_((String)linkData.getTitleText()), linkData.getDisplayItem(), p_213021_1_ -> {
                    this.prevPageJSON = this.currentPageJSON;
                    this.currentPageJSON = new ResourceLocation(this.getTextFileDirectory() + linkData.getLinkedPage());
                    this.preservedPageIndex = this.currentPageCounter;
                    this.currentPageCounter = 0;
                    this.addNextPreviousButtons();
                }));
            }
            if (linkData.getPage() <= this.maxPagesFromPrinting) continue;
            this.maxPagesFromPrinting = linkData.getPage();
        }
        for (EntityLinkData entityLinkData : this.entityLinks) {
            if (entityLinkData.getPage() == this.currentPageCounter) {
                this.yIndexesToSkip.add(new Whitespace(entityLinkData.getPage(), entityLinkData.getX() - 12, entityLinkData.getY(), 100, 20));
                this.m_142416_((GuiEventListener)new EntityLinkButton(this, entityLinkData, k, l, p_213021_1_ -> {
                    this.prevPageJSON = this.currentPageJSON;
                    this.currentPageJSON = new ResourceLocation(this.getTextFileDirectory() + entityLinkData.getLinkedPage());
                    this.preservedPageIndex = this.currentPageCounter;
                    this.currentPageCounter = 0;
                    this.addNextPreviousButtons();
                }));
            }
            if (entityLinkData.getPage() <= this.maxPagesFromPrinting) continue;
            this.maxPagesFromPrinting = entityLinkData.getPage();
        }
    }

    private void onSwitchPage(boolean next) {
        if (next) {
            if (this.currentPageCounter < this.maxPagesFromPrinting) {
                ++this.currentPageCounter;
            }
        } else if (this.currentPageCounter > 0) {
            --this.currentPageCounter;
        } else if (this.internalPage != null && !this.internalPage.getParent().isEmpty()) {
            this.prevPageJSON = this.currentPageJSON;
            this.currentPageJSON = new ResourceLocation(this.getTextFileDirectory() + this.internalPage.getParent());
            this.currentPageCounter = this.preservedPageIndex;
            this.preservedPageIndex = 0;
        }
        this.refreshSpacing();
    }

    public void m_88315_(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
        this.mouseX = x;
        this.mouseY = y;
        int bindingColor = this.getBindingColor();
        int bindingR = bindingColor >> 16 & 0xFF;
        int bindingG = bindingColor >> 8 & 0xFF;
        int bindingB = bindingColor & 0xFF;
        this.m_280273_(guiGraphics);
        int k = (this.f_96543_ - this.xSize) / 2;
        int l = (this.f_96544_ - this.ySize + 128) / 2;
        BookBlit.blitWithColor(guiGraphics, this.getBookBindingTexture(), k, l, 0.0f, 0.0f, this.xSize, this.ySize, this.xSize, this.ySize, bindingR, bindingG, bindingB, 255);
        BookBlit.blitWithColor(guiGraphics, this.getBookPageTexture(), k, l, 0.0f, 0.0f, this.xSize, this.ySize, this.xSize, this.ySize, 255, 255, 255, 255);
        if (this.internalPage == null || this.currentPageJSON != this.prevPageJSON || this.prevPageJSON == null) {
            this.internalPage = this.generatePage(this.currentPageJSON);
            if (this.internalPage != null) {
                this.refreshSpacing();
            }
        }
        if (this.internalPage != null) {
            this.writePageText(guiGraphics, x, y);
        }
        super.m_88315_(guiGraphics, x, y, partialTicks);
        this.prevPageJSON = this.currentPageJSON;
        if (this.internalPage != null) {
            guiGraphics.m_280168_().m_85836_();
            this.renderOtherWidgets(guiGraphics, x, y, this.internalPage);
            guiGraphics.m_280168_().m_85849_();
        }
        if (this.entityTooltip != null) {
            guiGraphics.m_280168_().m_85836_();
            guiGraphics.m_280168_().m_252880_(0.0f, 0.0f, 550.0f);
            guiGraphics.m_280245_(this.f_96547_, Minecraft.m_91087_().f_91062_.m_92923_((FormattedText)Component.m_237115_((String)this.entityTooltip), Math.max(this.f_96543_ / 2 - 43, 170)), x, y);
            this.entityTooltip = null;
            guiGraphics.m_280168_().m_85849_();
        }
    }

    private void refreshSpacing() {
        if (this.internalPage != null) {
            String lang = Minecraft.m_91087_().m_91102_().m_264236_().toLowerCase();
            this.currentPageText = new ResourceLocation(this.getTextFileDirectory() + lang + "/" + this.internalPage.getTextFileToReadFrom());
            boolean invalid = false;
            try {
                InputStream is = Minecraft.m_91087_().m_91098_().m_215595_(this.currentPageText);
                is.close();
            }
            catch (Exception e) {
                invalid = true;
                Citadel.LOGGER.warn("Could not find language file for translation, defaulting to english");
                this.currentPageText = new ResourceLocation(this.getTextFileDirectory() + "en_us/" + this.internalPage.getTextFileToReadFrom());
            }
            this.readInPageWidgets(this.internalPage);
            this.addWidgetSpacing();
            this.addLinkButtons();
            this.readInPageText(this.currentPageText);
        }
    }

    private Item getItemByRegistryName(String registryName) {
        return (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(registryName));
    }

    private Recipe getRecipeByName(String registryName) {
        try {
            RecipeManager manager = Minecraft.m_91087_().f_91073_.m_7465_();
            if (manager.m_44043_(new ResourceLocation(registryName)).isPresent()) {
                return (Recipe)manager.m_44043_(new ResourceLocation(registryName)).get();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addWidgetSpacing() {
        this.yIndexesToSkip.clear();
        for (ItemRenderData itemRenderData : this.itemRenders) {
            Item item = this.getItemByRegistryName(itemRenderData.getItem());
            if (item == null) continue;
            this.yIndexesToSkip.add(new Whitespace(itemRenderData.getPage(), itemRenderData.getX(), itemRenderData.getY(), (int)(itemRenderData.getScale() * 17.0), (int)(itemRenderData.getScale() * 15.0)));
        }
        for (RecipeData recipeData : this.recipes) {
            Recipe recipe = this.getRecipeByName(recipeData.getRecipe());
            if (recipe == null) continue;
            this.yIndexesToSkip.add(new Whitespace(recipeData.getPage(), recipeData.getX(), recipeData.getY() - (int)(recipeData.getScale() * 15.0), (int)(recipeData.getScale() * 35.0), (int)(recipeData.getScale() * 60.0), true));
        }
        for (ImageData imageData : this.images) {
            if (imageData == null) continue;
            this.yIndexesToSkip.add(new Whitespace(imageData.getPage(), imageData.getX(), imageData.getY(), (int)(imageData.getScale() * (double)imageData.getWidth()), (int)(imageData.getScale() * (double)imageData.getHeight() * (double)0.8f)));
        }
        if (!this.writtenTitle.isEmpty()) {
            this.yIndexesToSkip.add(new Whitespace(0, 20, 5, 70, 15));
        }
    }

    private void renderOtherWidgets(GuiGraphics guiGraphics, int x, int y, BookPage page) {
        CompoundTag tag;
        int color = this.getBindingColor();
        int r = (color & 0xFF0000) >> 16;
        int g = (color & 0xFF00) >> 8;
        int b = color & 0xFF;
        int k = (this.f_96543_ - this.xSize) / 2;
        int l = (this.f_96544_ - this.ySize + 128) / 2;
        for (ImageData imageData : this.images) {
            if (imageData.getPage() != this.currentPageCounter || imageData == null) continue;
            ResourceLocation tex = this.textureMap.get(imageData.getTexture());
            if (tex == null) {
                tex = new ResourceLocation(imageData.getTexture());
                this.textureMap.put(imageData.getTexture(), tex);
            }
            float scale = (float)imageData.getScale();
            guiGraphics.m_280168_().m_85836_();
            guiGraphics.m_280168_().m_252880_((float)(k + imageData.getX()), (float)(l + imageData.getY()), 0.0f);
            guiGraphics.m_280168_().m_85841_(scale, scale, scale);
            guiGraphics.m_280218_(tex, 0, 0, imageData.getU(), imageData.getV(), imageData.getWidth(), imageData.getHeight());
            guiGraphics.m_280168_().m_85849_();
        }
        for (RecipeData recipeData : this.recipes) {
            if (recipeData.getPage() != this.currentPageCounter) continue;
            guiGraphics.m_280168_().m_85836_();
            guiGraphics.m_280168_().m_252880_((float)(k + recipeData.getX()), (float)(l + recipeData.getY()), 0.0f);
            float scale = (float)recipeData.getScale();
            guiGraphics.m_280168_().m_85841_(scale, scale, scale);
            guiGraphics.m_280218_(this.getBookWidgetTexture(), 0, 0, 0, 88, 116, 53);
            guiGraphics.m_280168_().m_85849_();
        }
        for (TabulaRenderData tabulaRenderData : this.tabulaRenders) {
            if (tabulaRenderData.getPage() != this.currentPageCounter) continue;
            TabulaModel model = null;
            ResourceLocation texture = this.textureMap.get(tabulaRenderData.getTexture()) != null ? this.textureMap.get(tabulaRenderData.getTexture()) : this.textureMap.put(tabulaRenderData.getTexture(), new ResourceLocation(tabulaRenderData.getTexture()));
            if (this.renderedTabulaModels.get(tabulaRenderData.getModel()) != null) {
                model = this.renderedTabulaModels.get(tabulaRenderData.getModel());
            } else {
                try {
                    model = new TabulaModel(TabulaModelHandler.INSTANCE.loadTabulaModel("/assets/" + tabulaRenderData.getModel().split(":")[0] + "/" + tabulaRenderData.getModel().split(":")[1]));
                }
                catch (Exception e) {
                    Citadel.LOGGER.warn("Could not load in tabula model for book at " + tabulaRenderData.getModel());
                }
                this.renderedTabulaModels.put(tabulaRenderData.getModel(), model);
            }
            if (model == null || texture == null) continue;
            float scale = (float)tabulaRenderData.getScale();
            GuiBasicBook.drawTabulaModelOnScreen(guiGraphics, model, texture, k + tabulaRenderData.getX(), l + tabulaRenderData.getY(), 30.0f * scale, tabulaRenderData.isFollow_cursor(), tabulaRenderData.getRot_x(), tabulaRenderData.getRot_y(), tabulaRenderData.getRot_z(), this.mouseX, this.mouseY);
        }
        for (EntityRenderData data : this.entityRenders) {
            if (data.getPage() != this.currentPageCounter) continue;
            Entity model = null;
            EntityType type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(data.getEntity()));
            if (type != null) {
                model = this.renderedEntites.putIfAbsent(data.getEntity(), type.m_20615_((Level)Minecraft.m_91087_().f_91073_));
            }
            if (model == null) continue;
            float scale = (float)data.getScale();
            model.f_19797_ = Minecraft.m_91087_().f_91074_.f_19797_;
            if (data.getEntityData() != null) {
                try {
                    tag = TagParser.m_129359_((String)data.getEntityData());
                    model.m_20258_(tag);
                }
                catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
            }
            this.drawEntityOnScreen(guiGraphics, (MultiBufferSource)guiGraphics.m_280091_(), k + data.getX(), l + data.getY(), 1050.0f, 30.0f * scale, data.isFollow_cursor(), data.getRot_x(), data.getRot_y(), data.getRot_z(), this.mouseX, this.mouseY, model);
        }
        for (RecipeData recipeData : this.recipes) {
            Recipe recipe;
            if (recipeData.getPage() != this.currentPageCounter || (recipe = this.getRecipeByName(recipeData.getRecipe())) == null) continue;
            this.renderRecipe(guiGraphics, recipe, recipeData, k, l);
        }
        for (ItemRenderData itemRenderData : this.itemRenders) {
            Item item;
            if (itemRenderData.getPage() != this.currentPageCounter || (item = this.getItemByRegistryName(itemRenderData.getItem())) == null) continue;
            float scale = (float)itemRenderData.getScale();
            ItemStack stack = new ItemStack((ItemLike)item);
            if (itemRenderData.getItemTag() != null && !itemRenderData.getItemTag().isEmpty()) {
                tag = null;
                try {
                    tag = TagParser.m_129359_((String)itemRenderData.getItemTag());
                }
                catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
                stack.m_41751_(tag);
            }
            guiGraphics.m_280168_().m_85836_();
            guiGraphics.m_280168_().m_252880_((float)k, (float)l, 0.0f);
            guiGraphics.m_280168_().m_85841_(scale, scale, scale);
            guiGraphics.m_280480_(stack, itemRenderData.getX(), itemRenderData.getY());
            guiGraphics.m_280168_().m_85849_();
        }
    }

    protected void renderRecipe(GuiGraphics guiGraphics, Recipe recipe, RecipeData recipeData, int k, int l) {
        int playerTicks = Minecraft.m_91087_().f_91074_.f_19797_;
        float scale = (float)recipeData.getScale();
        NonNullList<Ingredient> ingredients = recipe instanceof SpecialRecipeInGuideBook ? ((SpecialRecipeInGuideBook)recipe).getDisplayIngredients() : recipe.m_7527_();
        NonNullList displayedStacks = NonNullList.m_122779_();
        for (int i = 0; i < ingredients.size(); ++i) {
            Ingredient ing = (Ingredient)ingredients.get(i);
            ItemStack stack = ItemStack.f_41583_;
            if (!ing.m_43947_()) {
                if (ing.m_43908_().length > 1) {
                    int currentIndex = (int)((float)playerTicks / 20.0f % (float)ing.m_43908_().length);
                    stack = ing.m_43908_()[currentIndex];
                } else {
                    stack = ing.m_43908_()[0];
                }
            }
            if (!stack.m_41619_()) {
                guiGraphics.m_280168_().m_85836_();
                guiGraphics.m_280168_().m_252880_((float)k, (float)l, 32.0f);
                guiGraphics.m_280168_().m_252880_((float)((int)((float)recipeData.getX() + (float)(i % 3 * 20) * scale)), (float)((int)((float)recipeData.getY() + (float)(i / 3 * 20) * scale)), 0.0f);
                guiGraphics.m_280168_().m_85841_(scale, scale, scale);
                guiGraphics.m_280480_(stack, 0, 0);
                guiGraphics.m_280168_().m_85849_();
            }
            displayedStacks.add(i, (Object)stack);
        }
        guiGraphics.m_280168_().m_85836_();
        guiGraphics.m_280168_().m_252880_((float)k, (float)l, 32.0f);
        float finScale = scale * 1.5f;
        guiGraphics.m_280168_().m_252880_((float)recipeData.getX() + 70.0f * finScale, (float)recipeData.getY() + 10.0f * finScale, 0.0f);
        guiGraphics.m_280168_().m_85841_(finScale, finScale, finScale);
        ItemStack result = recipe.m_8043_(Minecraft.m_91087_().f_91073_.m_9598_());
        if (recipe instanceof SpecialRecipeInGuideBook) {
            result = ((SpecialRecipeInGuideBook)recipe).getDisplayResultFor((NonNullList<ItemStack>)displayedStacks);
        }
        guiGraphics.m_280168_().m_252880_(0.0f, 0.0f, 100.0f);
        guiGraphics.m_280480_(result, 0, 0);
        guiGraphics.m_280168_().m_85849_();
    }

    protected void writePageText(GuiGraphics guiGraphics, int x, int y) {
        Font font = this.f_96547_;
        int k = (this.f_96543_ - this.xSize) / 2;
        int l = (this.f_96544_ - this.ySize + 128) / 2;
        for (LineData line : this.lines) {
            if (line.getPage() != this.currentPageCounter) continue;
            guiGraphics.m_280056_(font, line.getText(), k + 10 + line.getxIndex(), l + 10 + line.getyIndex() * 12, this.getTextColor(), false);
        }
        if (this.currentPageCounter == 0 && !this.writtenTitle.isEmpty()) {
            String actualTitle = I18n.m_118938_((String)this.writtenTitle, (Object[])new Object[0]);
            guiGraphics.m_280168_().m_85836_();
            float scale = 2.0f;
            if (font.m_92895_(actualTitle) > 80) {
                scale = 2.0f - Mth.m_14036_((float)((float)(font.m_92895_(actualTitle) - 80) * 0.011f), (float)0.0f, (float)1.95f);
            }
            guiGraphics.m_280168_().m_252880_((float)(k + 10), (float)(l + 10), 0.0f);
            guiGraphics.m_280168_().m_85841_(scale, scale, scale);
            guiGraphics.m_280056_(font, actualTitle, 0, 0, this.getTitleColor(), false);
            guiGraphics.m_280168_().m_85849_();
        }
        this.buttonNextPage.f_93624_ = this.currentPageCounter < this.maxPagesFromPrinting;
        this.buttonPreviousPage.f_93624_ = this.currentPageCounter > 0 || !this.currentPageJSON.equals((Object)this.getRootPage());
    }

    public boolean m_7043_() {
        return false;
    }

    protected void playBookOpeningSound() {
        Minecraft.m_91087_().m_91106_().m_120367_((SoundInstance)SimpleSoundInstance.m_119752_((SoundEvent)SoundEvents.f_11713_, (float)1.0f));
    }

    protected void playBookClosingSound() {
    }

    protected abstract int getBindingColor();

    protected int getWidgetColor() {
        return this.getBindingColor();
    }

    protected int getTextColor() {
        return 0x303030;
    }

    protected int getTitleColor() {
        return 12233880;
    }

    public abstract ResourceLocation getRootPage();

    public abstract String getTextFileDirectory();

    protected ResourceLocation getBookPageTexture() {
        return BOOK_PAGE_TEXTURE;
    }

    protected ResourceLocation getBookBindingTexture() {
        return BOOK_BINDING_TEXTURE;
    }

    protected ResourceLocation getBookWidgetTexture() {
        return BOOK_WIDGET_TEXTURE;
    }

    protected void playPageFlipSound() {
    }

    @Nullable
    protected BookPage generatePage(ResourceLocation res) {
        Optional resource = null;
        BookPage page = null;
        try {
            resource = Minecraft.m_91087_().m_91098_().m_213713_(res);
            try {
                resource = Minecraft.m_91087_().m_91098_().m_213713_(res);
                if (resource.isPresent()) {
                    BufferedReader inputstream = ((Resource)resource.get()).m_215508_();
                    page = BookPage.deserialize(inputstream);
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        catch (Exception e) {
            return null;
        }
        return page;
    }

    protected void readInPageWidgets(BookPage page) {
        this.links.clear();
        this.itemRenders.clear();
        this.recipes.clear();
        this.tabulaRenders.clear();
        this.entityRenders.clear();
        this.images.clear();
        this.entityLinks.clear();
        this.links.addAll(page.getLinkedButtons());
        this.entityLinks.addAll(page.getLinkedEntities());
        this.itemRenders.addAll(page.getItemRenders());
        this.recipes.addAll(page.getRecipes());
        this.tabulaRenders.addAll(page.getTabulaRenders());
        this.entityRenders.addAll(page.getEntityRenders());
        this.images.addAll(page.getImages());
        this.writtenTitle = page.generateTitle();
    }

    protected void readInPageText(ResourceLocation res) {
        Object resource = null;
        int xIndex = 0;
        int actualTextX = 0;
        int yIndex = 0;
        try {
            BufferedReader bufferedreader = Minecraft.m_91087_().m_91098_().m_215597_(res);
            try {
                List readStrings = IOUtils.readLines((Reader)bufferedreader);
                this.linesFromJSON = readStrings.size();
                this.lines.clear();
                ArrayList<String> splitBySpaces = new ArrayList<String>();
                for (String line : readStrings) {
                    splitBySpaces.addAll(Arrays.asList(line.split(" ")));
                }
                Object lineToPrint = "";
                this.linesFromPrinting = 0;
                int page = 0;
                for (int i = 0; i < splitBySpaces.size(); ++i) {
                    String word = (String)splitBySpaces.get(i);
                    int cutoffPoint = xIndex > 100 ? 30 : 35;
                    boolean newline = word.equals("<NEWLINE>");
                    for (Whitespace indexes : this.yIndexesToSkip) {
                        int indexPage = indexes.getPage();
                        if (indexPage != page) continue;
                        int buttonX = indexes.getX();
                        int buttonY = indexes.getY();
                        int width = indexes.getWidth();
                        int height = indexes.getHeight();
                        if (indexes.isDown()) {
                            if (!((float)yIndex >= (float)buttonY / 12.0f) || !((float)yIndex <= (float)(buttonY + height) / 12.0f) || (buttonX >= 90 || xIndex >= 90) && (buttonX < 90 || xIndex < 90)) continue;
                            yIndex += 2;
                            continue;
                        }
                        if (!((float)yIndex >= (float)(buttonY - height) / 12.0f) || !((float)yIndex <= (float)(buttonY + height) / 12.0f) || (buttonX >= 90 || xIndex >= 90) && (buttonX < 90 || xIndex < 90)) continue;
                        ++yIndex;
                    }
                    boolean last = i == splitBySpaces.size() - 1;
                    actualTextX += word.length() + 1;
                    if (((String)lineToPrint).length() + word.length() + 1 >= cutoffPoint || newline) {
                        ++this.linesFromPrinting;
                        if (yIndex > 13) {
                            if (xIndex > 0) {
                                ++page;
                                xIndex = 0;
                                yIndex = 0;
                            } else {
                                xIndex = 200;
                                yIndex = 0;
                            }
                        }
                        if (last) {
                            lineToPrint = (String)lineToPrint + " " + word;
                        }
                        this.lines.add(new LineData(xIndex, yIndex, (String)lineToPrint, page));
                        ++yIndex;
                        actualTextX = 0;
                        if (newline) {
                            ++yIndex;
                        }
                        lineToPrint = word.equals("<NEWLINE>") ? "" : word;
                        continue;
                    }
                    lineToPrint = (String)lineToPrint + " " + word;
                    if (!last) continue;
                    ++this.linesFromPrinting;
                    this.lines.add(new LineData(xIndex, yIndex, (String)lineToPrint, page));
                    ++yIndex;
                    actualTextX = 0;
                    if (!newline) continue;
                    ++yIndex;
                }
                this.maxPagesFromPrinting = page;
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        catch (Exception e) {
            Citadel.LOGGER.warn("Could not load in page .txt from json from page, page: " + res);
        }
    }

    public void setEntityTooltip(String hoverText) {
        this.entityTooltip = hoverText;
    }

    public ResourceLocation getBookButtonsTexture() {
        return BOOK_BUTTONS_TEXTURE;
    }
}
