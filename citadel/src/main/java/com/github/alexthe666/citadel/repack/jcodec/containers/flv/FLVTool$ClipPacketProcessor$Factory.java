/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;

public static class FLVTool.ClipPacketProcessor.Factory
implements FLVTool.PacketProcessorFactory {
    @Override
    public FLVTool.PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
        return new FLVTool.ClipPacketProcessor(flags.getDoubleFlag(FLAG_FROM), flags.getDoubleFlag(FLAG_TO));
    }

    @Override
    public MainUtils.Flag[] getFlags() {
        return new MainUtils.Flag[]{FLAG_FROM, FLAG_TO};
    }
}
