/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.server.world;

public interface ModifiableTickRateServer {
    public void setGlobalTickLengthMs(long var1);

    public long getMasterMs();

    default public void resetGlobalTickLengthMs() {
        this.setGlobalTickLengthMs(-1L);
    }
}
