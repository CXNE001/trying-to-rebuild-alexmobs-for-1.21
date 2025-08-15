/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;

public static class AliasBox.ExtraField {
    short type;
    int len;
    byte[] data;

    public AliasBox.ExtraField(short type, int len, byte[] bs) {
        this.type = type;
        this.len = len;
        this.data = bs;
    }

    public String toString() {
        return Platform.stringFromCharset4(this.data, 0, this.len, this.type == 14 || this.type == 15 ? "UTF-16" : "UTF-8");
    }
}
