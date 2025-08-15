/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.Boxes;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MdtaBox;

private static class KeysBox.LocalBoxes
extends Boxes {
    KeysBox.LocalBoxes() {
        this.mappings.put(MdtaBox.fourcc(), MdtaBox.class);
    }
}
