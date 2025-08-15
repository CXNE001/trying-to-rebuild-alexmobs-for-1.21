/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.server.packs.metadata.MetadataSectionSerializer
 *  net.minecraft.util.GsonHelper
 */
package com.github.alexthe666.alexsmobs.client.render.misc;

import com.github.alexthe666.alexsmobs.client.render.misc.VoidWormMetadataSection;
import com.google.gson.JsonObject;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.util.GsonHelper;

private static class VoidWormMetadataSection.Serializer
implements MetadataSectionSerializer<VoidWormMetadataSection> {
    private VoidWormMetadataSection.Serializer() {
    }

    public VoidWormMetadataSection fromJson(JsonObject json) {
        return new VoidWormMetadataSection(GsonHelper.m_13912_((JsonObject)json, (String)"end_portal_texture"));
    }

    public String m_7991_() {
        return "void_worm";
    }
}
