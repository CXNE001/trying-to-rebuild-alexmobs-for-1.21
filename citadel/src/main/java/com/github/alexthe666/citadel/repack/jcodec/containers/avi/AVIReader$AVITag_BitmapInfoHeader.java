/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.containers.avi.AVIReader;
import java.io.IOException;

static class AVIReader.AVITag_BitmapInfoHeader
extends AVIReader.AVIChunk {
    private int biSize;
    private int biWidth;
    private int biHeight;
    private short biPlanes;
    private short biBitCount;
    private int biCompression;
    private int biSizeImage;
    private int biXPelsPerMeter;
    private int biYPelsPerMeter;
    private int biClrUsed;
    private int biClrImportant;
    private byte r;
    private byte g;
    private byte b;
    private byte x;

    AVIReader.AVITag_BitmapInfoHeader() {
    }

    @Override
    public void read(int dwFourCC, DataReader raf) throws IOException {
        super.read(dwFourCC, raf);
        this.biSize = raf.readInt();
        this.biWidth = raf.readInt();
        this.biHeight = raf.readInt();
        this.biPlanes = raf.readShort();
        this.biBitCount = raf.readShort();
        this.biCompression = raf.readInt();
        this.biSizeImage = raf.readInt();
        this.biXPelsPerMeter = raf.readInt();
        this.biYPelsPerMeter = raf.readInt();
        this.biClrUsed = raf.readInt();
        this.biClrImportant = raf.readInt();
        if (this.getChunkSize() == 56) {
            this.r = raf.readByte();
            this.g = raf.readByte();
            this.b = raf.readByte();
            this.x = raf.readByte();
        }
    }

    @Override
    public int getChunkSize() {
        return this.biSize;
    }

    @Override
    public String toString() {
        return "\tCHUNK [" + AVIReader.toFourCC(this.dwFourCC) + "], BitsPerPixel [" + this.biBitCount + "], Resolution [" + ((long)this.biWidth & 0xFFFFFFFFL) + " x " + ((long)this.biHeight & 0xFFFFFFFFL) + "], Planes [" + this.biPlanes + "]";
    }
}
