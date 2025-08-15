/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;

public static interface FLVTool.PacketProcessorFactory {
    public FLVTool.PacketProcessor newPacketProcessor(MainUtils.Cmd var1);

    public MainUtils.Flag[] getFlags();
}
