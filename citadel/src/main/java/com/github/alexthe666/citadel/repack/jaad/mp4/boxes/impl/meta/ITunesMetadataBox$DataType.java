/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.meta;

public static enum ITunesMetadataBox.DataType {
    IMPLICIT,
    UTF8,
    UTF16,
    HTML,
    XML,
    UUID,
    ISRC,
    MI3P,
    GIF,
    JPEG,
    PNG,
    URL,
    DURATION,
    DATETIME,
    GENRE,
    INTEGER,
    RIAA,
    UPC,
    BMP,
    UNDEFINED;

    private static final ITunesMetadataBox.DataType[] TYPES;

    private static ITunesMetadataBox.DataType forInt(int i) {
        ITunesMetadataBox.DataType type = null;
        if (i >= 0 && i < TYPES.length) {
            type = TYPES[i];
        }
        if (type == null) {
            type = UNDEFINED;
        }
        return type;
    }

    static {
        TYPES = new ITunesMetadataBox.DataType[]{IMPLICIT, UTF8, UTF16, null, null, null, HTML, XML, UUID, ISRC, MI3P, null, GIF, JPEG, PNG, URL, DURATION, DATETIME, GENRE, null, null, INTEGER, null, null, RIAA, UPC, null, BMP};
    }
}
