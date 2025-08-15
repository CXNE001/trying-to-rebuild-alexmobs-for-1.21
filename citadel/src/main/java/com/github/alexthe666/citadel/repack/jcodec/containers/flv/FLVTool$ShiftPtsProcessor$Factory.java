/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;

public static class FLVTool.ShiftPtsProcessor.Factory
implements FLVTool.PacketProcessorFactory {
    @Override
    public FLVTool.PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
        return new FLVTool.ShiftPtsProcessor(flags.getIntegerFlagD(FLAG_TO, 0), flags.getIntegerFlag(FLAG_BY), flags.getBooleanFlagD(FLAG_WRAP_AROUND, false));
    }

    @Override
    public MainUtils.Flag[] getFlags() {
        return new MainUtils.Flag[]{FLAG_TO, FLAG_BY, FLAG_WRAP_AROUND};
    }
}
