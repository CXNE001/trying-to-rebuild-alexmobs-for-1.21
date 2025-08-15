/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.SyncSamplesBox;

public class PartialSyncSamplesBox
extends SyncSamplesBox {
    public static final String STPS = "stps";

    public PartialSyncSamplesBox(Header header) {
        super(header);
    }
}
