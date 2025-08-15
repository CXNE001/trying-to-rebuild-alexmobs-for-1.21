/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.math;

private static class CitadelSimplexNoise.Grad {
    double x;
    double y;
    double z;
    double w;

    CitadelSimplexNoise.Grad(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    CitadelSimplexNoise.Grad(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
