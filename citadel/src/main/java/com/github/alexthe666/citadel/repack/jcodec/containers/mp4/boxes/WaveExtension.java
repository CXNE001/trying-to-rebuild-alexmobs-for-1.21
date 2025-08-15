/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;

public class WaveExtension
extends NodeBox {
    public static String fourcc() {
        return "wave";
    }

    public WaveExtension(Header atom) {
        super(atom);
    }
}
