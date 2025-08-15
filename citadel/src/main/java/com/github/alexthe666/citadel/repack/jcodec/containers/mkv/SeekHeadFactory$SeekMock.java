/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv;

import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBase;

public static class SeekHeadFactory.SeekMock {
    public long dataOffset;
    byte[] id;
    int size;
    int seekPointerSize;

    public static SeekHeadFactory.SeekMock make(EbmlBase e) {
        SeekHeadFactory.SeekMock z = new SeekHeadFactory.SeekMock();
        z.id = e.id;
        z.size = (int)e.size();
        return z;
    }
}
