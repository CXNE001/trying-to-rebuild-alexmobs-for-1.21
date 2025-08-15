/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso;

public interface Mapper {
    public boolean leftAvailable(int var1);

    public boolean topAvailable(int var1);

    public int getAddress(int var1);

    public int getMbX(int var1);

    public int getMbY(int var1);

    public boolean topRightAvailable(int var1);

    public boolean topLeftAvailable(int var1);
}
