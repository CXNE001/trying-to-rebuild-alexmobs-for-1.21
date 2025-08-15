/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.dpx;

import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.ImageElement;

public class ImageHeader {
    public short orientation;
    public short numberOfImageElements;
    public int linesPerImageElement;
    public int pixelsPerLine;
    public ImageElement imageElement1;
}
