/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.Boxes;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.DataBox;

private static class IListBox.LocalBoxes
extends Boxes {
    IListBox.LocalBoxes() {
        this.mappings.put(DataBox.fourcc(), DataBox.class);
    }
}
