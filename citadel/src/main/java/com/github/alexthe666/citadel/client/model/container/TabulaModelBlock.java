/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.block.model.BlockElement
 *  net.minecraft.client.renderer.block.model.BlockElement$Deserializer
 *  net.minecraft.client.renderer.block.model.BlockElementFace
 *  net.minecraft.client.renderer.block.model.BlockElementFace$Deserializer
 *  net.minecraft.client.renderer.block.model.BlockFaceUV
 *  net.minecraft.client.renderer.block.model.BlockFaceUV$Deserializer
 *  net.minecraft.client.renderer.block.model.ItemOverride
 *  net.minecraft.client.renderer.block.model.ItemOverride$Deserializer
 *  net.minecraft.client.renderer.block.model.ItemTransform
 *  net.minecraft.client.renderer.block.model.ItemTransform$Deserializer
 *  net.minecraft.client.renderer.block.model.ItemTransforms
 *  net.minecraft.client.renderer.block.model.ItemTransforms$Deserializer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.github.alexthe666.citadel.client.model.container;

import com.github.alexthe666.citadel.client.model.container.JsonUtils;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.BlockElementFace;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(value=Dist.CLIENT)
@Deprecated(since="2.6.2")
public class TabulaModelBlock {
    private static final Logger LOGGER = LogManager.getLogger();
    @VisibleForTesting
    static final Gson SERIALIZER = new GsonBuilder().registerTypeAdapter(TabulaModelBlock.class, (Object)new Deserializer()).registerTypeAdapter(BlockElement.class, (Object)new BlockElement.Deserializer()).registerTypeAdapter(BlockElementFace.class, (Object)new BlockElementFace.Deserializer()).registerTypeAdapter(BlockFaceUV.class, (Object)new BlockFaceUV.Deserializer()).registerTypeAdapter(ItemTransform.class, (Object)new ItemTransform.Deserializer()).registerTypeAdapter(ItemTransforms.class, (Object)new ItemTransforms.Deserializer()).registerTypeAdapter(ItemOverride.class, (Object)new ItemOverride.Deserializer()).create();
    private final List<BlockElement> elements;
    private final boolean gui3d;
    public final boolean ambientOcclusion;
    private final ItemTransforms cameraTransforms;
    private final List<ItemOverride> overrides;
    public String name = "";
    @VisibleForTesting
    public final Map<String, String> textures;
    @VisibleForTesting
    public TabulaModelBlock parent;
    @VisibleForTesting
    protected ResourceLocation parentLocation;

    public static TabulaModelBlock deserialize(Reader readerIn) {
        return JsonUtils.gsonDeserialize(SERIALIZER, readerIn, TabulaModelBlock.class, false);
    }

    public static TabulaModelBlock deserialize(String jsonString) {
        return TabulaModelBlock.deserialize(new StringReader(jsonString));
    }

    public TabulaModelBlock(@Nullable ResourceLocation parentLocationIn, List<BlockElement> elementsIn, Map<String, String> texturesIn, boolean ambientOcclusionIn, boolean gui3dIn, ItemTransforms cameraTransformsIn, List<ItemOverride> overridesIn) {
        this.elements = elementsIn;
        this.ambientOcclusion = ambientOcclusionIn;
        this.gui3d = gui3dIn;
        this.textures = texturesIn;
        this.parentLocation = parentLocationIn;
        this.cameraTransforms = cameraTransformsIn;
        this.overrides = overridesIn;
    }

    public List<BlockElement> getElements() {
        return this.elements.isEmpty() && this.hasParent() ? this.parent.getElements() : this.elements;
    }

    private boolean hasParent() {
        return this.parent != null;
    }

    public boolean isAmbientOcclusion() {
        return this.hasParent() ? this.parent.isAmbientOcclusion() : this.ambientOcclusion;
    }

    public boolean isGui3d() {
        return this.gui3d;
    }

    public boolean isResolved() {
        return this.parentLocation == null || this.parent != null && this.parent.isResolved();
    }

    public void getParentFromMap(Map<ResourceLocation, TabulaModelBlock> p_178299_1_) {
        if (this.parentLocation != null) {
            this.parent = p_178299_1_.get(this.parentLocation);
        }
    }

    public Collection<ResourceLocation> getOverrideLocations() {
        HashSet set = Sets.newHashSet();
        for (ItemOverride itemoverride : this.overrides) {
            set.add(itemoverride.m_111718_());
        }
        return set;
    }

    public List<ItemOverride> getOverrides() {
        return this.overrides;
    }

    public boolean isTexturePresent(String textureName) {
        return !"missingno".equals(this.resolveTextureName(textureName));
    }

    public String resolveTextureName(String textureName) {
        if (!this.startsWithHash((String)textureName)) {
            textureName = "#" + (String)textureName;
        }
        return this.resolveTextureName((String)textureName, new Bookkeep(this));
    }

    private String resolveTextureName(String textureName, Bookkeep p_178302_2_) {
        if (this.startsWithHash(textureName)) {
            if (this == p_178302_2_.modelExt) {
                LOGGER.warn("Unable to resolve texture due to upward reference: {} in {}", (Object)textureName, (Object)this.name);
                return "missingno";
            }
            String s = this.textures.get(textureName.substring(1));
            if (s == null && this.hasParent()) {
                s = this.parent.resolveTextureName(textureName, p_178302_2_);
            }
            p_178302_2_.modelExt = this;
            if (s != null && this.startsWithHash(s)) {
                s = p_178302_2_.model.resolveTextureName(s, p_178302_2_);
            }
            return s != null && !this.startsWithHash(s) ? s : "missingno";
        }
        return textureName;
    }

    private boolean startsWithHash(String hash) {
        return hash.charAt(0) == '#';
    }

    @Nullable
    public ResourceLocation getParentLocation() {
        return this.parentLocation;
    }

    public TabulaModelBlock getRootModel() {
        return this.hasParent() ? this.parent.getRootModel() : this;
    }

    public ItemTransforms getAllTransforms() {
        ItemTransform itemtransformvec3f = this.getTransform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND);
        ItemTransform itemtransformvec3f1 = this.getTransform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND);
        ItemTransform itemtransformvec3f2 = this.getTransform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND);
        ItemTransform itemtransformvec3f3 = this.getTransform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND);
        ItemTransform itemtransformvec3f4 = this.getTransform(ItemDisplayContext.HEAD);
        ItemTransform itemtransformvec3f5 = this.getTransform(ItemDisplayContext.GUI);
        ItemTransform itemtransformvec3f6 = this.getTransform(ItemDisplayContext.GROUND);
        ItemTransform itemtransformvec3f7 = this.getTransform(ItemDisplayContext.FIXED);
        return new ItemTransforms(itemtransformvec3f, itemtransformvec3f1, itemtransformvec3f2, itemtransformvec3f3, itemtransformvec3f4, itemtransformvec3f5, itemtransformvec3f6, itemtransformvec3f7);
    }

    private ItemTransform getTransform(ItemDisplayContext type) {
        return this.parent != null && !this.cameraTransforms.m_269504_(type) ? this.parent.getTransform(type) : this.cameraTransforms.m_269404_(type);
    }

    public static void checkModelHierarchy(Map<ResourceLocation, TabulaModelBlock> p_178312_0_) {
        for (TabulaModelBlock TabulaModelBlock2 : p_178312_0_.values()) {
            try {
                TabulaModelBlock TabulaModelBlock1 = TabulaModelBlock2.parent;
                TabulaModelBlock TabulaModelBlock22 = TabulaModelBlock1.parent;
                while (TabulaModelBlock1 != TabulaModelBlock22) {
                    TabulaModelBlock1 = TabulaModelBlock1.parent;
                    TabulaModelBlock22 = TabulaModelBlock22.parent.parent;
                }
                throw new LoopException();
            }
            catch (NullPointerException nullPointerException) {
            }
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    static final class Bookkeep {
        public final TabulaModelBlock model;
        public TabulaModelBlock modelExt;

        private Bookkeep(TabulaModelBlock modelIn) {
            this.model = modelIn;
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class LoopException
    extends RuntimeException {
    }

    public static class Deserializer
    implements JsonDeserializer<TabulaModelBlock> {
        public TabulaModelBlock deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            List<BlockElement> list = this.getModelElements(p_deserialize_3_, jsonobject);
            String s = this.getParent(jsonobject);
            Map<String, String> map = this.getTextures(jsonobject);
            boolean flag = this.getAmbientOcclusionEnabled(jsonobject);
            ItemTransforms itemcameratransforms = ItemTransforms.f_111786_;
            if (jsonobject.has("display")) {
                JsonObject jsonobject1 = JsonUtils.getJsonObject(jsonobject, "display");
                itemcameratransforms = (ItemTransforms)p_deserialize_3_.deserialize((JsonElement)jsonobject1, ItemTransforms.class);
            }
            List<ItemOverride> list1 = this.getItemOverrides(p_deserialize_3_, jsonobject);
            ResourceLocation resourcelocation = s.isEmpty() ? null : new ResourceLocation(s);
            return new TabulaModelBlock(resourcelocation, list, map, flag, true, itemcameratransforms, list1);
        }

        protected List<ItemOverride> getItemOverrides(JsonDeserializationContext deserializationContext, JsonObject object) {
            ArrayList list = Lists.newArrayList();
            if (object.has("overrides")) {
                for (JsonElement jsonelement : JsonUtils.getJsonArray(object, "overrides")) {
                    list.add((ItemOverride)deserializationContext.deserialize(jsonelement, ItemOverride.class));
                }
            }
            return list;
        }

        private Map<String, String> getTextures(JsonObject object) {
            HashMap map = Maps.newHashMap();
            if (object.has("textures")) {
                JsonObject jsonobject = object.getAsJsonObject("textures");
                for (Map.Entry entry : jsonobject.entrySet()) {
                    map.put((String)entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
                }
            }
            return map;
        }

        private String getParent(JsonObject object) {
            return JsonUtils.getString(object, "parent", "");
        }

        protected boolean getAmbientOcclusionEnabled(JsonObject object) {
            return JsonUtils.getBoolean(object, "ambientocclusion", true);
        }

        protected List<BlockElement> getModelElements(JsonDeserializationContext deserializationContext, JsonObject object) {
            ArrayList list = Lists.newArrayList();
            if (object.has("elements")) {
                for (JsonElement jsonelement : JsonUtils.getJsonArray(object, "elements")) {
                    list.add((BlockElement)deserializationContext.deserialize(jsonelement, BlockElement.class));
                }
            }
            return list;
        }
    }
}
