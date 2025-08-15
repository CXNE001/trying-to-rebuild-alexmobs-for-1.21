/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.citadel.client.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.world.phys.Vec3;

public class LightningBoltData.BoltQuads {
    private final List<Vec3> vecs = new ArrayList<Vec3>();

    protected void addQuad(Vec3 ... quadVecs) {
        this.vecs.addAll(Arrays.asList(quadVecs));
    }

    public List<Vec3> getVecs() {
        return this.vecs;
    }
}
