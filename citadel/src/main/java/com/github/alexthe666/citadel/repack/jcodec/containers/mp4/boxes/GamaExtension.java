/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Box;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class GamaExtension
extends Box {
    private float gamma;

    public static GamaExtension createGamaExtension(float gamma) {
        GamaExtension gamaExtension = new GamaExtension(new Header(GamaExtension.fourcc()));
        gamaExtension.gamma = gamma;
        return gamaExtension;
    }

    public GamaExtension(Header header) {
        super(header);
    }

    @Override
    public void parse(ByteBuffer input) {
        float g = input.getInt();
        this.gamma = g / 65536.0f;
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        out.putInt((int)(this.gamma * 65536.0f));
    }

    public float getGamma() {
        return this.gamma;
    }

    public static String fourcc() {
        return "gama";
    }

    @Override
    public int estimateSize() {
        return 12;
    }
}
