/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.boxes;

import com.github.alexthe666.citadel.repack.jaad.mp4.MP4InputStream;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.BoxImpl;
import java.io.IOException;

public class FullBox
extends BoxImpl {
    protected int version;
    protected int flags;

    public FullBox(String name) {
        super(name);
    }

    @Override
    public void decode(MP4InputStream in) throws IOException {
        this.version = in.read();
        this.flags = (int)in.readBytes(3);
    }
}
