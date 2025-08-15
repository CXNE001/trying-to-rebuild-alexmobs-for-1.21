/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVWriter;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.IOException;

public static class FLVTool.ClipPacketProcessor
implements FLVTool.PacketProcessor {
    private static FLVTag h264Config;
    private boolean copying = false;
    private Double from;
    private Double to;
    private static final MainUtils.Flag FLAG_FROM;
    private static final MainUtils.Flag FLAG_TO;

    public FLVTool.ClipPacketProcessor(Double from, Double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean processPacket(FLVTag pkt, FLVWriter writer) throws IOException {
        if (pkt.getType() == FLVTag.Type.VIDEO && pkt.getTagHeader().getCodec() == Codec.H264 && ((FLVTag.AvcVideoTagHeader)pkt.getTagHeader()).getAvcPacketType() == 0) {
            h264Config = pkt;
            System.out.println("GOT AVCC");
        }
        if (!this.copying && (this.from == null || pkt.getPtsD() > this.from) && pkt.getType() == FLVTag.Type.VIDEO && pkt.isKeyFrame() && h264Config != null) {
            System.out.println("Starting at packet: " + Platform.toJSON(pkt));
            this.copying = true;
            h264Config.setPts(pkt.getPts());
            writer.addPacket(h264Config);
        }
        if (this.to != null && pkt.getPtsD() >= this.to) {
            System.out.println("Stopping at packet: " + Platform.toJSON(pkt));
            return false;
        }
        if (this.copying) {
            writer.addPacket(pkt);
        }
        return true;
    }

    @Override
    public void finish(FLVWriter muxer) {
    }

    @Override
    public boolean hasOutput() {
        return true;
    }

    static {
        FLAG_FROM = MainUtils.Flag.flag("from", null, "From timestamp (in seconds, i.e 67.49)");
        FLAG_TO = MainUtils.Flag.flag("to", null, "To timestamp");
    }

    public static class Factory
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
}
