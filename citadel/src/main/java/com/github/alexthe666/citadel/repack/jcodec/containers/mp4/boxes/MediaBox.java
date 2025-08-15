/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MediaInfoBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;

public class MediaBox
extends NodeBox {
    public static String fourcc() {
        return "mdia";
    }

    public static MediaBox createMediaBox() {
        return new MediaBox(new Header(MediaBox.fourcc()));
    }

    public MediaBox(Header atom) {
        super(atom);
    }

    public MediaInfoBox getMinf() {
        return NodeBox.findFirst(this, MediaInfoBox.class, "minf");
    }
}
