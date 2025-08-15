/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.DataRefBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;

public class DataInfoBox
extends NodeBox {
    public static String fourcc() {
        return "dinf";
    }

    public static DataInfoBox createDataInfoBox() {
        return new DataInfoBox(new Header(DataInfoBox.fourcc()));
    }

    public DataInfoBox(Header atom) {
        super(atom);
    }

    public DataRefBox getDref() {
        return NodeBox.findFirst(this, DataRefBox.class, "dref");
    }
}
