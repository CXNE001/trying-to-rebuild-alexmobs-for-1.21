/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.ClearApertureBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;

public class EncodedPixelBox
extends ClearApertureBox {
    public static final String ENOF = "enof";

    public static EncodedPixelBox createEncodedPixelBox(int width, int height) {
        EncodedPixelBox enof = new EncodedPixelBox(new Header(ENOF));
        enof.width = width;
        enof.height = height;
        return enof;
    }

    public EncodedPixelBox(Header atom) {
        super(atom);
    }
}
