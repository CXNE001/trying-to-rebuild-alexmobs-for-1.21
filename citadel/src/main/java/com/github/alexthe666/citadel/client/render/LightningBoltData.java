/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 *  org.apache.commons.lang3.tuple.Pair
 *  org.joml.Vector4f
 */
package com.github.alexthe666.citadel.client.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.tuple.Pair;
import org.joml.Vector4f;

public class LightningBoltData {
    private final Random random = new Random();
    private final BoltRenderInfo renderInfo;
    private final Vec3 start;
    private final Vec3 end;
    private final int segments;
    private int count = 1;
    private float size = 0.1f;
    private int lifespan = 30;
    private SpawnFunction spawnFunction = SpawnFunction.delay(60.0f);
    private FadeFunction fadeFunction = FadeFunction.fade(0.5f);

    public LightningBoltData(Vec3 start, Vec3 end) {
        this(BoltRenderInfo.DEFAULT, start, end, (int)Math.sqrt(start.m_82554_(end) * 100.0));
    }

    public LightningBoltData(BoltRenderInfo info, Vec3 start, Vec3 end, int segments) {
        this.renderInfo = info;
        this.start = start;
        this.end = end;
        this.segments = segments;
    }

    public LightningBoltData count(int count) {
        this.count = count;
        return this;
    }

    public LightningBoltData size(float size) {
        this.size = size;
        return this;
    }

    public LightningBoltData spawn(SpawnFunction spawnFunction) {
        this.spawnFunction = spawnFunction;
        return this;
    }

    public LightningBoltData fade(FadeFunction fadeFunction) {
        this.fadeFunction = fadeFunction;
        return this;
    }

    public LightningBoltData lifespan(int lifespan) {
        this.lifespan = lifespan;
        return this;
    }

    public int getLifespan() {
        return this.lifespan;
    }

    public SpawnFunction getSpawnFunction() {
        return this.spawnFunction;
    }

    public FadeFunction getFadeFunction() {
        return this.fadeFunction;
    }

    public Vector4f getColor() {
        return this.renderInfo.color;
    }

    public List<BoltQuads> generate() {
        ArrayList<BoltQuads> quads = new ArrayList<BoltQuads>();
        Vec3 diff = this.end.m_82546_(this.start);
        float totalDistance = (float)diff.m_82553_();
        block0: for (int i = 0; i < this.count; ++i) {
            LinkedList<BoltInstructions> drawQueue = new LinkedList<BoltInstructions>();
            drawQueue.add(new BoltInstructions(this.start, 0.0f, new Vec3(0.0, 0.0, 0.0), null, false));
            while (!drawQueue.isEmpty()) {
                Vec3 segmentEnd;
                BoltInstructions data = (BoltInstructions)drawQueue.poll();
                Vec3 perpendicularDist = data.perpendicularDist;
                float progress = data.progress + 1.0f / (float)this.segments * (1.0f - this.renderInfo.parallelNoise + this.random.nextFloat() * this.renderInfo.parallelNoise * 2.0f);
                if (progress >= 1.0f) {
                    segmentEnd = this.end;
                } else {
                    float segmentDiffScale = this.renderInfo.spreadFunction.getMaxSpread(progress);
                    float maxDiff = this.renderInfo.spreadFactor * segmentDiffScale * totalDistance * this.renderInfo.randomFunction.getRandom(this.random);
                    Vec3 randVec = LightningBoltData.findRandomOrthogonalVector(diff, this.random);
                    perpendicularDist = this.renderInfo.segmentSpreader.getSegmentAdd(perpendicularDist, randVec, maxDiff, segmentDiffScale, progress);
                    segmentEnd = this.start.m_82549_(diff.m_82490_((double)progress)).m_82549_(perpendicularDist);
                }
                float boltSize = this.size * (0.5f + (1.0f - progress) * 0.5f);
                Pair<BoltQuads, QuadCache> quadData = this.createQuads(data.cache, data.start, segmentEnd, boltSize);
                quads.add((BoltQuads)quadData.getLeft());
                if (segmentEnd == this.end) continue block0;
                if (!data.isBranch) {
                    drawQueue.add(new BoltInstructions(segmentEnd, progress, perpendicularDist, (QuadCache)quadData.getRight(), false));
                } else if (this.random.nextFloat() < this.renderInfo.branchContinuationFactor) {
                    drawQueue.add(new BoltInstructions(segmentEnd, progress, perpendicularDist, (QuadCache)quadData.getRight(), true));
                }
                while (this.random.nextFloat() < this.renderInfo.branchInitiationFactor * (1.0f - progress)) {
                    drawQueue.add(new BoltInstructions(segmentEnd, progress, perpendicularDist, (QuadCache)quadData.getRight(), true));
                }
            }
        }
        return quads;
    }

    private static Vec3 findRandomOrthogonalVector(Vec3 vec, Random rand) {
        Vec3 newVec = new Vec3(-0.5 + rand.nextDouble(), -0.5 + rand.nextDouble(), -0.5 + rand.nextDouble());
        return vec.m_82537_(newVec).m_82541_();
    }

    private Pair<BoltQuads, QuadCache> createQuads(QuadCache cache, Vec3 startPos, Vec3 end, float size) {
        Vec3 diff = end.m_82546_(startPos);
        Vec3 rightAdd = diff.m_82537_(new Vec3(0.5, 0.5, 0.5)).m_82541_().m_82490_((double)size);
        Vec3 backAdd = diff.m_82537_(rightAdd).m_82541_().m_82490_((double)size);
        Vec3 rightAddSplit = rightAdd.m_82490_(0.5);
        Vec3 start = cache != null ? cache.prevEnd : startPos;
        Vec3 startRight = cache != null ? cache.prevEndRight : start.m_82549_(rightAdd);
        Vec3 startBack = cache != null ? cache.prevEndBack : start.m_82549_(rightAddSplit).m_82549_(backAdd);
        Vec3 endRight = end.m_82549_(rightAdd);
        Vec3 endBack = end.m_82549_(rightAddSplit).m_82549_(backAdd);
        BoltQuads quads = new BoltQuads();
        quads.addQuad(start, end, endRight, startRight);
        quads.addQuad(startRight, endRight, end, start);
        quads.addQuad(startRight, endRight, endBack, startBack);
        quads.addQuad(startBack, endBack, endRight, startRight);
        return Pair.of((Object)quads, (Object)new QuadCache(end, endRight, endBack));
    }

    public static class BoltRenderInfo {
        public static final BoltRenderInfo DEFAULT = new BoltRenderInfo();
        public static final BoltRenderInfo ELECTRICITY = BoltRenderInfo.electricity();
        private float parallelNoise = 0.1f;
        private float spreadFactor = 0.1f;
        private float branchInitiationFactor = 0.0f;
        private float branchContinuationFactor = 0.0f;
        private Vector4f color = new Vector4f(0.45f, 0.45f, 0.5f, 0.8f);
        private final RandomFunction randomFunction = RandomFunction.GAUSSIAN;
        private final SpreadFunction spreadFunction = SpreadFunction.SINE;
        private SegmentSpreader segmentSpreader = SegmentSpreader.NO_MEMORY;

        public static BoltRenderInfo electricity() {
            return new BoltRenderInfo(0.5f, 0.25f, 0.25f, 0.15f, new Vector4f(0.7f, 0.45f, 0.89f, 0.8f), 0.8f);
        }

        public BoltRenderInfo() {
        }

        public BoltRenderInfo(float parallelNoise, float spreadFactor, float branchInitiationFactor, float branchContinuationFactor, Vector4f color, float closeness) {
            this.parallelNoise = parallelNoise;
            this.spreadFactor = spreadFactor;
            this.branchInitiationFactor = branchInitiationFactor;
            this.branchContinuationFactor = branchContinuationFactor;
            this.color = color;
            this.segmentSpreader = SegmentSpreader.memory(closeness);
        }
    }

    public static interface SpawnFunction {
        public static final SpawnFunction NO_DELAY = rand -> Pair.of((Object)Float.valueOf(0.0f), (Object)Float.valueOf(0.0f));
        public static final SpawnFunction CONSECUTIVE = new SpawnFunction(){

            @Override
            public Pair<Float, Float> getSpawnDelayBounds(Random rand) {
                return Pair.of((Object)Float.valueOf(0.0f), (Object)Float.valueOf(0.0f));
            }

            @Override
            public boolean isConsecutive() {
                return true;
            }
        };

        public static SpawnFunction delay(float delay) {
            return rand -> Pair.of((Object)Float.valueOf(delay), (Object)Float.valueOf(delay));
        }

        public static SpawnFunction noise(float delay, float noise) {
            return rand -> Pair.of((Object)Float.valueOf(delay - noise), (Object)Float.valueOf(delay + noise));
        }

        public Pair<Float, Float> getSpawnDelayBounds(Random var1);

        default public float getSpawnDelay(Random rand) {
            Pair<Float, Float> bounds = this.getSpawnDelayBounds(rand);
            return ((Float)bounds.getLeft()).floatValue() + (((Float)bounds.getRight()).floatValue() - ((Float)bounds.getLeft()).floatValue()) * rand.nextFloat();
        }

        default public boolean isConsecutive() {
            return false;
        }
    }

    public static interface FadeFunction {
        public static final FadeFunction NONE = (totalBolts, lifeScale) -> Pair.of((Object)0, (Object)totalBolts);

        public static FadeFunction fade(float fade) {
            return (totalBolts, lifeScale) -> {
                int start = lifeScale > 1.0f - fade ? (int)((float)totalBolts * (lifeScale - (1.0f - fade)) / fade) : 0;
                int end = lifeScale < fade ? (int)((float)totalBolts * (lifeScale / fade)) : totalBolts;
                return Pair.of((Object)start, (Object)end);
            };
        }

        public Pair<Integer, Integer> getRenderBounds(int var1, float var2);
    }

    protected static class BoltInstructions {
        private final Vec3 start;
        private final Vec3 perpendicularDist;
        private final QuadCache cache;
        private final float progress;
        private final boolean isBranch;

        private BoltInstructions(Vec3 start, float progress, Vec3 perpendicularDist, QuadCache cache, boolean isBranch) {
            this.start = start;
            this.perpendicularDist = perpendicularDist;
            this.progress = progress;
            this.cache = cache;
            this.isBranch = isBranch;
        }
    }

    private static class QuadCache {
        private final Vec3 prevEnd;
        private final Vec3 prevEndRight;
        private final Vec3 prevEndBack;

        private QuadCache(Vec3 prevEnd, Vec3 prevEndRight, Vec3 prevEndBack) {
            this.prevEnd = prevEnd;
            this.prevEndRight = prevEndRight;
            this.prevEndBack = prevEndBack;
        }
    }

    public static interface SpreadFunction {
        public static final SpreadFunction LINEAR_ASCENT = progress -> progress;
        public static final SpreadFunction LINEAR_ASCENT_DESCENT = progress -> (progress - Math.max(0.0f, 2.0f * progress - 1.0f)) / 0.5f;
        public static final SpreadFunction SINE = progress -> (float)Math.sin(Math.PI * (double)progress);

        public float getMaxSpread(float var1);
    }

    public static interface RandomFunction {
        public static final RandomFunction UNIFORM = Random::nextFloat;
        public static final RandomFunction GAUSSIAN = rand -> (float)rand.nextGaussian();

        public float getRandom(Random var1);
    }

    public static interface SegmentSpreader {
        public static final SegmentSpreader NO_MEMORY = (perpendicularDist, randVec, maxDiff, scale, progress) -> randVec.m_82490_((double)maxDiff);

        public static SegmentSpreader memory(float memoryFactor) {
            return (perpendicularDist, randVec, maxDiff, spreadScale, progress) -> {
                float nextDiff = maxDiff * (1.0f - memoryFactor);
                Vec3 cur = randVec.m_82490_((double)nextDiff);
                if (progress > 0.5f) {
                    cur = cur.m_82549_(perpendicularDist.m_82490_((double)(-1.0f * (1.0f - spreadScale))));
                }
                return perpendicularDist.m_82549_(cur);
            };
        }

        public Vec3 getSegmentAdd(Vec3 var1, Vec3 var2, float var3, float var4, float var5);
    }

    public class BoltQuads {
        private final List<Vec3> vecs = new ArrayList<Vec3>();

        protected void addQuad(Vec3 ... quadVecs) {
            this.vecs.addAll(Arrays.asList(quadVecs));
        }

        public List<Vec3> getVecs() {
            return this.vecs;
        }
    }
}
