/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.meta.ITunesMetadataBox;

public static enum Artwork.Type {
    GIF,
    JPEG,
    PNG,
    BMP;


    static Artwork.Type forDataType(ITunesMetadataBox.DataType dataType) {
        return switch (dataType) {
            case ITunesMetadataBox.DataType.GIF -> GIF;
            case ITunesMetadataBox.DataType.JPEG -> JPEG;
            case ITunesMetadataBox.DataType.PNG -> PNG;
            case ITunesMetadataBox.DataType.BMP -> BMP;
            default -> null;
        };
    }
}
