/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  net.minecraft.client.resources.language.I18n
 *  net.minecraft.util.GsonHelper
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.client.gui.data.EntityLinkData;
import com.github.alexthe666.citadel.client.gui.data.EntityRenderData;
import com.github.alexthe666.citadel.client.gui.data.ImageData;
import com.github.alexthe666.citadel.client.gui.data.ItemRenderData;
import com.github.alexthe666.citadel.client.gui.data.LinkData;
import com.github.alexthe666.citadel.client.gui.data.RecipeData;
import com.github.alexthe666.citadel.client.gui.data.TabulaRenderData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class BookPage {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(BookPage.class, (Object)new Deserializer()).create();
    public String translatableTitle = null;
    private String parent = "";
    private String textFileToReadFrom = "";
    private List<LinkData> linkedButtons = new ArrayList<LinkData>();
    private List<EntityLinkData> linkedEntites = new ArrayList<EntityLinkData>();
    private List<ItemRenderData> itemRenders = new ArrayList<ItemRenderData>();
    private List<RecipeData> recipes = new ArrayList<RecipeData>();
    private List<TabulaRenderData> tabulaRenders = new ArrayList<TabulaRenderData>();
    private List<EntityRenderData> entityRenders = new ArrayList<EntityRenderData>();
    private List<ImageData> images = new ArrayList<ImageData>();
    private final String title;

    public BookPage(String parent, String textFileToReadFrom, List<LinkData> linkedButtons, List<EntityLinkData> linkedEntities, List<ItemRenderData> itemRenders, List<RecipeData> recipes, List<TabulaRenderData> tabulaRenders, List<EntityRenderData> entityRenders, List<ImageData> images, String title) {
        this.parent = parent;
        this.textFileToReadFrom = textFileToReadFrom;
        this.linkedButtons = linkedButtons;
        this.itemRenders = itemRenders;
        this.linkedEntites = linkedEntities;
        this.recipes = recipes;
        this.tabulaRenders = tabulaRenders;
        this.entityRenders = entityRenders;
        this.images = images;
        this.title = title;
    }

    public static BookPage deserialize(Reader readerIn) {
        return (BookPage)GsonHelper.m_13776_((Gson)GSON, (Reader)readerIn, BookPage.class);
    }

    public static BookPage deserialize(String jsonString) {
        return BookPage.deserialize(new StringReader(jsonString));
    }

    public String getParent() {
        return this.parent;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTextFileToReadFrom() {
        return this.textFileToReadFrom;
    }

    public List<LinkData> getLinkedButtons() {
        return this.linkedButtons;
    }

    public List<EntityLinkData> getLinkedEntities() {
        return this.linkedEntites;
    }

    public List<ItemRenderData> getItemRenders() {
        return this.itemRenders;
    }

    public List<RecipeData> getRecipes() {
        return this.recipes;
    }

    public List<TabulaRenderData> getTabulaRenders() {
        return this.tabulaRenders;
    }

    public List<EntityRenderData> getEntityRenders() {
        return this.entityRenders;
    }

    public List<ImageData> getImages() {
        return this.images;
    }

    public String generateTitle() {
        if (this.translatableTitle != null) {
            return I18n.m_118938_((String)this.translatableTitle, (Object[])new Object[0]);
        }
        return this.title;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Deserializer
    implements JsonDeserializer<BookPage> {
        public BookPage deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = GsonHelper.m_13918_((JsonElement)p_deserialize_1_, (String)"book page");
            LinkData[] linkedPageRead = (LinkData[])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"linked_page_buttons", (Object)new LinkData[0], (JsonDeserializationContext)p_deserialize_3_, LinkData[].class);
            EntityLinkData[] linkedEntitesRead = (EntityLinkData[])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"entity_buttons", (Object)new EntityLinkData[0], (JsonDeserializationContext)p_deserialize_3_, EntityLinkData[].class);
            ItemRenderData[] itemRendersRead = (ItemRenderData[])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"item_renders", (Object)new ItemRenderData[0], (JsonDeserializationContext)p_deserialize_3_, ItemRenderData[].class);
            RecipeData[] recipesRead = (RecipeData[])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"recipes", (Object)new RecipeData[0], (JsonDeserializationContext)p_deserialize_3_, RecipeData[].class);
            TabulaRenderData[] tabulaRendersRead = (TabulaRenderData[])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"tabula_renders", (Object)new TabulaRenderData[0], (JsonDeserializationContext)p_deserialize_3_, TabulaRenderData[].class);
            EntityRenderData[] entityRendersRead = (EntityRenderData[])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"entity_renders", (Object)new EntityRenderData[0], (JsonDeserializationContext)p_deserialize_3_, EntityRenderData[].class);
            ImageData[] imagesRead = (ImageData[])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"images", (Object)new ImageData[0], (JsonDeserializationContext)p_deserialize_3_, ImageData[].class);
            String readParent = "";
            if (jsonobject.has("parent")) {
                readParent = GsonHelper.m_13906_((JsonObject)jsonobject, (String)"parent");
            }
            String readTextFile = "";
            if (jsonobject.has("text")) {
                readTextFile = GsonHelper.m_13906_((JsonObject)jsonobject, (String)"text");
            }
            String title = "";
            if (jsonobject.has("title")) {
                title = GsonHelper.m_13906_((JsonObject)jsonobject, (String)"title");
            }
            BookPage page = new BookPage(readParent, readTextFile, Arrays.asList(linkedPageRead), Arrays.asList(linkedEntitesRead), Arrays.asList(itemRendersRead), Arrays.asList(recipesRead), Arrays.asList(tabulaRendersRead), Arrays.asList(entityRendersRead), Arrays.asList(imagesRead), title);
            if (jsonobject.has("title")) {
                page.translatableTitle = GsonHelper.m_13906_((JsonObject)jsonobject, (String)"title");
            }
            return page;
        }
    }
}
