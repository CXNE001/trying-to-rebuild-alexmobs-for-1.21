/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.meta.ITunesMetadataBox;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Artwork {
    private Type type;
    private byte[] data;
    private Image image;

    Artwork(Type type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return this.type;
    }

    public byte[] getData() {
        return this.data;
    }

    public Image getImage() throws IOException {
        try {
            if (this.image == null) {
                this.image = ImageIO.read(new ByteArrayInputStream(this.data));
            }
            return this.image;
        }
        catch (IOException e) {
            Logger.getLogger("MP4 API").log(Level.SEVERE, "Artwork.getImage failed: {0}", e.toString());
            throw e;
        }
    }

    public static enum Type {
        GIF,
        JPEG,
        PNG,
        BMP;


        static Type forDataType(ITunesMetadataBox.DataType dataType) {
            return switch (dataType) {
                case ITunesMetadataBox.DataType.GIF -> GIF;
                case ITunesMetadataBox.DataType.JPEG -> JPEG;
                case ITunesMetadataBox.DataType.PNG -> PNG;
                case ITunesMetadataBox.DataType.BMP -> BMP;
                default -> null;
            };
        }
    }
}
