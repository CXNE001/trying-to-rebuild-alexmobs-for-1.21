/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  org.apache.commons.lang3.tuple.Pair
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.citadel.client.render;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import com.github.alexthe666.citadel.client.render.LightningRender;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.joml.Matrix4f;

public class LightningRender.BoltInstance {
    private final LightningBoltData bolt;
    private final List<LightningBoltData.BoltQuads> renderQuads;
    private final LightningRender.Timestamp createdTimestamp;

    public LightningRender.BoltInstance(LightningBoltData bolt, LightningRender.Timestamp timestamp) {
        this.bolt = bolt;
        this.renderQuads = bolt.generate();
        this.createdTimestamp = timestamp;
    }

    public void render(Matrix4f matrix, VertexConsumer buffer, LightningRender.Timestamp timestamp) {
        float lifeScale = timestamp.subtract(this.createdTimestamp).value() / (float)this.bolt.getLifespan();
        Pair<Integer, Integer> bounds = this.bolt.getFadeFunction().getRenderBounds(this.renderQuads.size(), lifeScale);
        for (int i = ((Integer)bounds.getLeft()).intValue(); i < (Integer)bounds.getRight(); ++i) {
            this.renderQuads.get(i).getVecs().forEach(v -> buffer.m_252986_(matrix, (float)v.f_82479_, (float)v.f_82480_, (float)v.f_82481_).m_85950_(this.bolt.getColor().x(), this.bolt.getColor().y(), this.bolt.getColor().z(), this.bolt.getColor().w()).m_5752_());
        }
    }

    public boolean tick(LightningRender.Timestamp timestamp) {
        return timestamp.isPassed(this.createdTimestamp, this.bolt.getLifespan());
    }
}
