/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector4f
 */
package com.github.alexthe666.citadel.client.render;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import org.joml.Vector4f;

public static class LightningBoltData.BoltRenderInfo {
    public static final LightningBoltData.BoltRenderInfo DEFAULT = new LightningBoltData.BoltRenderInfo();
    public static final LightningBoltData.BoltRenderInfo ELECTRICITY = LightningBoltData.BoltRenderInfo.electricity();
    private float parallelNoise = 0.1f;
    private float spreadFactor = 0.1f;
    private float branchInitiationFactor = 0.0f;
    private float branchContinuationFactor = 0.0f;
    private Vector4f color = new Vector4f(0.45f, 0.45f, 0.5f, 0.8f);
    private final LightningBoltData.RandomFunction randomFunction = LightningBoltData.RandomFunction.GAUSSIAN;
    private final LightningBoltData.SpreadFunction spreadFunction = LightningBoltData.SpreadFunction.SINE;
    private LightningBoltData.SegmentSpreader segmentSpreader = LightningBoltData.SegmentSpreader.NO_MEMORY;

    public static LightningBoltData.BoltRenderInfo electricity() {
        return new LightningBoltData.BoltRenderInfo(0.5f, 0.25f, 0.25f, 0.15f, new Vector4f(0.7f, 0.45f, 0.89f, 0.8f), 0.8f);
    }

    public LightningBoltData.BoltRenderInfo() {
    }

    public LightningBoltData.BoltRenderInfo(float parallelNoise, float spreadFactor, float branchInitiationFactor, float branchContinuationFactor, Vector4f color, float closeness) {
        this.parallelNoise = parallelNoise;
        this.spreadFactor = spreadFactor;
        this.branchInitiationFactor = branchInitiationFactor;
        this.branchContinuationFactor = branchContinuationFactor;
        this.color = color;
        this.segmentSpreader = LightningBoltData.SegmentSpreader.memory(closeness);
    }
}
