/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  net.minecraft.util.GsonHelper
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.client.gui.BookPage;
import com.github.alexthe666.citadel.client.gui.data.EntityLinkData;
import com.github.alexthe666.citadel.client.gui.data.EntityRenderData;
import com.github.alexthe666.citadel.client.gui.data.ImageData;
import com.github.alexthe666.citadel.client.gui.data.ItemRenderData;
import com.github.alexthe666.citadel.client.gui.data.LinkData;
import com.github.alexthe666.citadel.client.gui.data.RecipeData;
import com.github.alexthe666.citadel.client.gui.data.TabulaRenderData;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Arrays;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public static class BookPage.Deserializer
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
