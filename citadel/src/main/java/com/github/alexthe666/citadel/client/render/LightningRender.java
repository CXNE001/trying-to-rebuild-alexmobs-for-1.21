/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  org.apache.commons.lang3.tuple.Pair
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.citadel.client.render;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.apache.commons.lang3.tuple.Pair;
import org.joml.Matrix4f;

public class LightningRender {
    private static final float REFRESH_TIME = 3.0f;
    private static final double MAX_OWNER_TRACK_TIME = 100.0;
    private Timestamp refreshTimestamp = new Timestamp();
    private final Random random = new Random();
    private final Minecraft minecraft = Minecraft.m_91087_();
    private final Map<Object, BoltOwnerData> boltOwners = new Object2ObjectOpenHashMap();

    public void render(float partialTicks, PoseStack PoseStackIn, MultiBufferSource bufferIn) {
        VertexConsumer buffer = bufferIn.m_6299_(RenderType.m_110502_());
        Matrix4f matrix = PoseStackIn.m_85850_().m_252922_();
        Timestamp timestamp = new Timestamp(this.minecraft.f_91073_.m_46467_(), partialTicks);
        boolean refresh = timestamp.isPassed(this.refreshTimestamp, 0.3333333432674408);
        if (refresh) {
            this.refreshTimestamp = timestamp;
        }
        Iterator<Map.Entry<Object, BoltOwnerData>> iter = this.boltOwners.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Object, BoltOwnerData> entry = iter.next();
            BoltOwnerData data = entry.getValue();
            if (refresh) {
                data.bolts.removeIf(bolt -> bolt.tick(timestamp));
            }
            if (data.bolts.isEmpty() && data.lastBolt != null && data.lastBolt.getSpawnFunction().isConsecutive()) {
                data.addBolt(new BoltInstance(data.lastBolt, timestamp), timestamp);
            }
            data.bolts.forEach(bolt -> bolt.render(matrix, buffer, timestamp));
            if (!data.bolts.isEmpty() || !timestamp.isPassed(data.lastUpdateTimestamp, 100.0)) continue;
            iter.remove();
        }
    }

    public void update(Object owner, LightningBoltData newBoltData, float partialTicks) {
        if (this.minecraft.f_91073_ == null) {
            return;
        }
        BoltOwnerData data = this.boltOwners.computeIfAbsent(owner, o -> new BoltOwnerData());
        data.lastBolt = newBoltData;
        Timestamp timestamp = new Timestamp(this.minecraft.f_91073_.m_46467_(), partialTicks);
        if ((!data.lastBolt.getSpawnFunction().isConsecutive() || data.bolts.isEmpty()) && timestamp.isPassed(data.lastBoltTimestamp, data.lastBoltDelay)) {
            data.addBolt(new BoltInstance(newBoltData, timestamp), timestamp);
        }
        data.lastUpdateTimestamp = timestamp;
    }

    public class Timestamp {
        private final long ticks;
        private final float partial;

        public Timestamp() {
            this(0L, 0.0f);
        }

        public Timestamp(long ticks, float partial) {
            this.ticks = ticks;
            this.partial = partial;
        }

        public Timestamp subtract(Timestamp other) {
            long newTicks = this.ticks - other.ticks;
            float newPartial = this.partial - other.partial;
            if (newPartial < 0.0f) {
                newPartial += 1.0f;
                --newTicks;
            }
            return new Timestamp(newTicks, newPartial);
        }

        public float value() {
            return (float)this.ticks + this.partial;
        }

        public boolean isPassed(Timestamp prev, double duration) {
            long ticksPassed = this.ticks - prev.ticks;
            if ((double)ticksPassed > duration) {
                return true;
            }
            if ((duration -= (double)ticksPassed) >= 1.0) {
                return false;
            }
            return (double)(this.partial - prev.partial) >= duration;
        }
    }

    public class BoltOwnerData {
        private final Set<BoltInstance> bolts = new ObjectOpenHashSet();
        private LightningBoltData lastBolt;
        private Timestamp lastBoltTimestamp = new Timestamp();
        private Timestamp lastUpdateTimestamp = new Timestamp();
        private double lastBoltDelay;

        private void addBolt(BoltInstance instance, Timestamp timestamp) {
            this.bolts.add(instance);
            this.lastBoltDelay = instance.bolt.getSpawnFunction().getSpawnDelay(LightningRender.this.random);
            this.lastBoltTimestamp = timestamp;
        }
    }

    public class BoltInstance {
        private final LightningBoltData bolt;
        private final List<LightningBoltData.BoltQuads> renderQuads;
        private final Timestamp createdTimestamp;

        public BoltInstance(LightningBoltData bolt, Timestamp timestamp) {
            this.bolt = bolt;
            this.renderQuads = bolt.generate();
            this.createdTimestamp = timestamp;
        }

        public void render(Matrix4f matrix, VertexConsumer buffer, Timestamp timestamp) {
            float lifeScale = timestamp.subtract(this.createdTimestamp).value() / (float)this.bolt.getLifespan();
            Pair<Integer, Integer> bounds = this.bolt.getFadeFunction().getRenderBounds(this.renderQuads.size(), lifeScale);
            for (int i = ((Integer)bounds.getLeft()).intValue(); i < (Integer)bounds.getRight(); ++i) {
                this.renderQuads.get(i).getVecs().forEach(v -> buffer.m_252986_(matrix, (float)v.f_82479_, (float)v.f_82480_, (float)v.f_82481_).m_85950_(this.bolt.getColor().x(), this.bolt.getColor().y(), this.bolt.getColor().z(), this.bolt.getColor().w()).m_5752_());
            }
        }

        public boolean tick(Timestamp timestamp) {
            return timestamp.isPassed(this.createdTimestamp, this.bolt.getLifespan());
        }
    }
}
