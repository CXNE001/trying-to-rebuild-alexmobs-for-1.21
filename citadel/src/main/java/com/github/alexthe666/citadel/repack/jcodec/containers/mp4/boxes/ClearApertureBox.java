/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FullBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import java.nio.ByteBuffer;

public class ClearApertureBox
extends FullBox {
    public static final String CLEF = "clef";
    protected float width;
    protected float height;

    public static ClearApertureBox createClearApertureBox(int width, int height) {
        ClearApertureBox clef = new ClearApertureBox(new Header(CLEF));
        clef.width = width;
        clef.height = height;
        return clef;
    }

    public ClearApertureBox(Header atom) {
        super(atom);
    }

    @Override
    public void parse(ByteBuffer input) {
        super.parse(input);
        this.width = (float)input.getInt() / 65536.0f;
        this.height = (float)input.getInt() / 65536.0f;
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        super.doWrite(out);
        out.putInt((int)(this.width * 65536.0f));
        out.putInt((int)(this.height * 65536.0f));
    }

    @Override
    public int estimateSize() {
        return 20;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
