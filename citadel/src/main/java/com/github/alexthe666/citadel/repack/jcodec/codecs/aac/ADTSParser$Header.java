/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.aac;

import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.AACConts;

public static class ADTSParser.Header {
    private int objectType;
    private int chanConfig;
    private int crcAbsent;
    private int numAACFrames;
    private int samplingIndex;
    private int samples;
    private int size;

    public ADTSParser.Header(int object_type, int chanConfig, int crcAbsent, int numAACFrames, int samplingIndex, int size) {
        this.objectType = object_type;
        this.chanConfig = chanConfig;
        this.crcAbsent = crcAbsent;
        this.numAACFrames = numAACFrames;
        this.samplingIndex = samplingIndex;
        this.size = size;
    }

    public int getObjectType() {
        return this.objectType;
    }

    public int getChanConfig() {
        return this.chanConfig;
    }

    public int getCrcAbsent() {
        return this.crcAbsent;
    }

    public int getNumAACFrames() {
        return this.numAACFrames;
    }

    public int getSamplingIndex() {
        return this.samplingIndex;
    }

    public int getSamples() {
        return this.samples;
    }

    public int getSize() {
        return this.size;
    }

    public int getSampleRate() {
        return AACConts.AAC_SAMPLE_RATES[this.samplingIndex];
    }
}
