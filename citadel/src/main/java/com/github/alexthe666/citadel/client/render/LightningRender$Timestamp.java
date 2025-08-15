/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.client.render;

public class LightningRender.Timestamp {
    private final long ticks;
    private final float partial;

    public LightningRender.Timestamp() {
        this(0L, 0.0f);
    }

    public LightningRender.Timestamp(long ticks, float partial) {
        this.ticks = ticks;
        this.partial = partial;
    }

    public LightningRender.Timestamp subtract(LightningRender.Timestamp other) {
        long newTicks = this.ticks - other.ticks;
        float newPartial = this.partial - other.partial;
        if (newPartial < 0.0f) {
            newPartial += 1.0f;
            --newTicks;
        }
        return new LightningRender.Timestamp(newTicks, newPartial);
    }

    public float value() {
        return (float)this.ticks + this.partial;
    }

    public boolean isPassed(LightningRender.Timestamp prev, double duration) {
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
