/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVWriter;
import java.io.IOException;

public static interface FLVTool.PacketProcessor {
    public boolean processPacket(FLVTag var1, FLVWriter var2) throws IOException;

    public boolean hasOutput();

    public void finish(FLVWriter var1) throws IOException;
}
