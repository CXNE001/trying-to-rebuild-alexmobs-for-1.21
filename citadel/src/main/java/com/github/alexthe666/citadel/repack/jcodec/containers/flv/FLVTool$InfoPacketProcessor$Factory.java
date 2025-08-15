/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;

public static class FLVTool.InfoPacketProcessor.Factory
implements FLVTool.PacketProcessorFactory {
    private static final MainUtils.Flag FLAG_CHECK = MainUtils.Flag.flag("check", null, "Check sanity and report errors only, no packet dump will be generated.");
    private static final MainUtils.Flag FLAG_STREAM = MainUtils.Flag.flag("stream", null, "Stream selector, can be one of: ['video', 'audio', 'script'].");

    @Override
    public FLVTool.PacketProcessor newPacketProcessor(MainUtils.Cmd flags) {
        return new FLVTool.InfoPacketProcessor(flags.getBooleanFlagD(FLAG_CHECK, false), flags.getEnumFlagD(FLAG_STREAM, null, FLVTag.Type.class));
    }

    @Override
    public MainUtils.Flag[] getFlags() {
        return new MainUtils.Flag[]{FLAG_CHECK, FLAG_STREAM};
    }
}
