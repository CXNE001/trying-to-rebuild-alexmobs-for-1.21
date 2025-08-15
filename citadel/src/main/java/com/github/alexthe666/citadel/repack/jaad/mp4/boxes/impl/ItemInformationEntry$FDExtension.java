/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl;

import com.github.alexthe666.citadel.repack.jaad.mp4.MP4InputStream;
import com.github.alexthe666.citadel.repack.jaad.mp4.boxes.impl.ItemInformationEntry;
import java.io.IOException;

public static class ItemInformationEntry.FDExtension
extends ItemInformationEntry.Extension {
    private String contentLocation;
    private String contentMD5;
    private long contentLength;
    private long transferLength;
    private long[] groupID;

    @Override
    void decode(MP4InputStream in) throws IOException {
        this.contentLocation = in.readUTFString(100, "UTF-8");
        this.contentMD5 = in.readUTFString(100, "UTF-8");
        this.contentLength = in.readBytes(8);
        this.transferLength = in.readBytes(8);
        int entryCount = in.read();
        this.groupID = new long[entryCount];
        for (int i = 0; i < entryCount; ++i) {
            this.groupID[i] = in.readBytes(4);
        }
    }

    public String getContentLocation() {
        return this.contentLocation;
    }

    public String getContentMD5() {
        return this.contentMD5;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public long getTransferLength() {
        return this.transferLength;
    }

    public long[] getGroupID() {
        return this.groupID;
    }
}
