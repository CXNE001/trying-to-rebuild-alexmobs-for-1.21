/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.DataInfoBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;

public class MediaInfoBox
extends NodeBox {
    public static String fourcc() {
        return "minf";
    }

    public static MediaInfoBox createMediaInfoBox() {
        return new MediaInfoBox(new Header(MediaInfoBox.fourcc()));
    }

    public MediaInfoBox(Header atom) {
        super(atom);
    }

    public DataInfoBox getDinf() {
        return NodeBox.findFirst(this, DataInfoBox.class, "dinf");
    }

    public NodeBox getStbl() {
        return NodeBox.findFirst(this, NodeBox.class, "stbl");
    }
}
