/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonParser
 *  net.minecraft.client.renderer.block.model.ItemTransform
 *  net.minecraft.client.renderer.block.model.ItemTransform$Deserializer
 *  net.minecraft.client.renderer.block.model.ItemTransforms
 *  net.minecraft.client.renderer.block.model.ItemTransforms$Deserializer
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.model;

import com.github.alexthe666.citadel.client.model.container.TabulaCubeContainer;
import com.github.alexthe666.citadel.client.model.container.TabulaCubeGroupContainer;
import com.github.alexthe666.citadel.client.model.container.TabulaModelBlock;
import com.github.alexthe666.citadel.client.model.container.TabulaModelContainer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
@Deprecated(since="2.6.2")
public enum TabulaModelHandler implements JsonDeserializationContext
{
    INSTANCE;

    private final Gson gson = new GsonBuilder().registerTypeAdapter(ItemTransform.class, (Object)new ItemTransform.Deserializer()).registerTypeAdapter(ItemTransforms.class, (Object)new ItemTransforms.Deserializer()).create();
    private final JsonParser parser = new JsonParser();
    private final TabulaModelBlock.Deserializer TabulaModelBlockDeserializer = new TabulaModelBlock.Deserializer();
    private ResourceManager manager;
    private final Set<String> enabledDomains = new HashSet<String>();

    public void addDomain(String domain) {
        this.enabledDomains.add(domain.toLowerCase(Locale.ROOT));
    }

    public TabulaModelContainer loadTabulaModel(String path) throws IOException {
        if (!((String)path).startsWith("/")) {
            path = "/" + (String)path;
        }
        if (!((String)path).endsWith(".tbl")) {
            path = (String)path + ".tbl";
        }
        InputStream stream = TabulaModelHandler.class.getResourceAsStream((String)path);
        return INSTANCE.loadTabulaModel(this.getModelJsonStream((String)path, stream));
    }

    public TabulaModelContainer loadTabulaModel(InputStream stream) {
        return (TabulaModelContainer)this.gson.fromJson((Reader)new InputStreamReader(stream), TabulaModelContainer.class);
    }

    public TabulaCubeContainer getCubeByName(String name, TabulaModelContainer model) {
        List<TabulaCubeContainer> allCubes = this.getAllCubes(model);
        for (TabulaCubeContainer cube : allCubes) {
            if (!cube.getName().equals(name)) continue;
            return cube;
        }
        return null;
    }

    public TabulaCubeContainer getCubeByIdentifier(String identifier, TabulaModelContainer model) {
        List<TabulaCubeContainer> allCubes = this.getAllCubes(model);
        for (TabulaCubeContainer cube : allCubes) {
            if (!cube.getIdentifier().equals(identifier)) continue;
            return cube;
        }
        return null;
    }

    public List<TabulaCubeContainer> getAllCubes(TabulaModelContainer model) {
        ArrayList<TabulaCubeContainer> cubes = new ArrayList<TabulaCubeContainer>();
        for (TabulaCubeGroupContainer cubeGroup : model.getCubeGroups()) {
            cubes.addAll(this.traverse(cubeGroup));
        }
        for (TabulaCubeContainer cube : model.getCubes()) {
            cubes.addAll(this.traverse(cube));
        }
        return cubes;
    }

    private List<TabulaCubeContainer> traverse(TabulaCubeGroupContainer group) {
        ArrayList<TabulaCubeContainer> retCubes = new ArrayList<TabulaCubeContainer>();
        for (TabulaCubeContainer tabulaCubeContainer : group.getCubes()) {
            retCubes.addAll(this.traverse(tabulaCubeContainer));
        }
        for (TabulaCubeGroupContainer tabulaCubeGroupContainer : group.getCubeGroups()) {
            retCubes.addAll(this.traverse(tabulaCubeGroupContainer));
        }
        return retCubes;
    }

    private List<TabulaCubeContainer> traverse(TabulaCubeContainer cube) {
        ArrayList<TabulaCubeContainer> retCubes = new ArrayList<TabulaCubeContainer>();
        retCubes.add(cube);
        for (TabulaCubeContainer child : cube.getChildren()) {
            retCubes.addAll(this.traverse(child));
        }
        return retCubes;
    }

    private InputStream getModelJsonStream(String name, InputStream file) throws IOException {
        ZipEntry entry;
        ZipInputStream zip = new ZipInputStream(file);
        while ((entry = zip.getNextEntry()) != null) {
            if (!entry.getName().equals("model.json")) continue;
            return zip;
        }
        throw new RuntimeException("No model.json present in " + name);
    }

    public <T> T deserialize(JsonElement json, Type type) throws JsonParseException {
        return (T)this.gson.fromJson(json, type);
    }
}
