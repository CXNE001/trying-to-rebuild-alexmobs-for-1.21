/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.flv;

import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MainUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTag;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVTool;
import com.github.alexthe666.citadel.repack.jcodec.containers.flv.FLVWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public static class FLVTool.ShiftPtsProcessor
implements FLVTool.PacketProcessor {
    private static final long WRAP_AROUND_VALUE = 0x80000000L;
    private static final int HALF_WRAP_AROUND_VALUE = 0x40000000;
    private static final MainUtils.Flag FLAG_TO = MainUtils.Flag.flag("to", null, "Shift first pts to this value, and all subsequent pts accordingly.");
    private static final MainUtils.Flag FLAG_BY = MainUtils.Flag.flag("by", null, "Shift all pts by this value.");
    private static final MainUtils.Flag FLAG_WRAP_AROUND = MainUtils.Flag.flag("wrap-around", null, "Expect wrap around of timestamps.");
    private int shiftTo;
    private Integer shiftBy;
    private long ptsDelta;
    private boolean firstPtsSeen;
    private List<FLVTag> savedTags = new LinkedList<FLVTag>();
    private boolean expectWrapAround;
    private int prevPts;

    public FLVTool.ShiftPtsProcessor(int shiftTo, Integer shiftBy, boolean expectWrapAround) {
        this.shiftTo = shiftTo;
        this.shiftBy = shiftBy;
        this.expectWrapAround = true;
    }

    @Override
    public boolean processPacket(FLVTag pkt, FLVWriter writer) throws IOException {
        boolean validPkt;
        boolean avcPrivatePacket = pkt.getType() == FLVTag.Type.VIDEO && ((FLVTag.VideoTagHeader)pkt.getTagHeader()).getCodec() == Codec.H264 && ((FLVTag.AvcVideoTagHeader)pkt.getTagHeader()).getAvcPacketType() == 0;
        boolean aacPrivatePacket = pkt.getType() == FLVTag.Type.AUDIO && ((FLVTag.AudioTagHeader)pkt.getTagHeader()).getCodec() == Codec.AAC && ((FLVTag.AacAudioTagHeader)pkt.getTagHeader()).getPacketType() == 0;
        boolean bl = validPkt = pkt.getType() != FLVTag.Type.SCRIPT && !avcPrivatePacket && !aacPrivatePacket;
        if (this.expectWrapAround && validPkt && pkt.getPts() < this.prevPts && (long)this.prevPts - (long)pkt.getPts() > 0x40000000L) {
            Logger.warn("Wrap around detected: " + this.prevPts + " -> " + pkt.getPts());
            if (pkt.getPts() < -1073741824) {
                this.ptsDelta += 0x100000000L;
            } else if (pkt.getPts() >= 0) {
                this.ptsDelta += 0x80000000L;
            }
        }
        if (validPkt) {
            this.prevPts = pkt.getPts();
        }
        if (this.firstPtsSeen) {
            this.writePacket(pkt, writer);
        } else if (!validPkt) {
            this.savedTags.add(pkt);
        } else {
            if (this.shiftBy != null) {
                this.ptsDelta = this.shiftBy.intValue();
                if (this.ptsDelta + (long)pkt.getPts() < 0L) {
                    this.ptsDelta = -pkt.getPts();
                }
            } else {
                this.ptsDelta = this.shiftTo - pkt.getPts();
            }
            this.firstPtsSeen = true;
            this.emptySavedTags(writer);
            this.writePacket(pkt, writer);
        }
        return true;
    }

    private void writePacket(FLVTag pkt, FLVWriter writer) throws IOException {
        long newPts = (long)pkt.getPts() + this.ptsDelta;
        if (newPts < 0L) {
            Logger.warn("Preventing negative pts for tag @" + pkt.getPosition());
            newPts = this.shiftBy != null ? 0L : (long)this.shiftTo;
        } else if (newPts >= 0x80000000L) {
            Logger.warn("PTS wrap around @" + pkt.getPosition());
            this.ptsDelta = (newPts -= 0x80000000L) - (long)pkt.getPts();
        }
        pkt.setPts((int)newPts);
        writer.addPacket(pkt);
    }

    private void emptySavedTags(FLVWriter muxer) throws IOException {
        while (this.savedTags.size() > 0) {
            this.writePacket(this.savedTags.remove(0), muxer);
        }
    }

    @Override
    public void finish(FLVWriter muxer) throws IOException {
        this.emptySavedTags(muxer);
    }

    @Override
    public boolean hasOutput() {
        return true;
    }

    public static class Factory
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
}
