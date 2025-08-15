/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;

public static class FLVTool.FixPtsProcessor.Factory
implements FLVTool.PacketProcessorFactory {
    @Override
    public FLVTool.PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
        return new FLVTool.FixPtsProcessor();
    }

    @Override
    public MainUtils.Flag[] getFlags() {
        return new MainUtils.Flag[0];
    }
}
